package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ProvinceCityAreaMapper;
import com.lhfeiyu.domain.AliyunOSS;
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.PictureService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.thirdparty.wx.business.AuthAccess;
import com.lhfeiyu.thirdparty.wx.util.CommonUtil;
import com.lhfeiyu.thirdparty.wx.util.ConfigUtil;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.ImgFromUrl;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.vo.WxUser;

@Service("baseAuthCheckService")
public class BaseAuthCheckService {
	@Autowired
	UserService userService;
	@Autowired
	ProvinceCityAreaMapper pcaMapper;
	@Autowired
	PictureService pictureService;
	
	
	public ModelMap checkWxLogin(HttpServletRequest request, ModelMap modelMap, JSONObject json, boolean forceLogin, String r){
		if(null == modelMap)return null;
		User user = ActionUtil.checkSession4User(request.getSession());
		if(null != user){
			if( Check.isNull(r) ){ //如果推广人为空，就将自己设置为推广人
				r = user.getSerial();
				modelMap.put("r", r);
			}
		}else{
			String status = checkWxLogin(request, forceLogin);//未登陆 - 微信自动登陆
			if(Check.isNotNull(status)){
				json.put(LhTip.key_loginStatus, status);
				modelMap.put(LhTip.key_loginStatus, status);
			}
		}
		modelMap.put("paramJson", json);
		return modelMap;
	}
	
	public JSONObject checkWxLogin(HttpServletRequest request, JSONObject json, boolean forceLogin, String r){
		if(null == json)return null;
		User user = ActionUtil.checkSession4User(request.getSession());
		if(null != user){
			if( Check.isNull(r) ){ //如果推广人为空，就将自己设置为推广人
				r = user.getSerial();
				json.put("r", r);
			}
		}else{
			String status = checkWxLogin(request, forceLogin);
			if(Check.isNotNull(status)){
				json.put(LhTip.key_loginStatus, status);
			}
		}
		return json;
	}
	
	private String checkWxLogin(HttpServletRequest request, boolean forceLogin){
		String status = checkWxLoginCore(request);//未登陆 - 微信自动登陆
		if(!forceLogin){
			status = LhTip.code_login_notForce;//非强制登陆
		}
		return status;
	}
	
	private String checkWxLoginCore(HttpServletRequest request){
		if(null == request)return LhTip.code_login_doLogin;
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if(Check.isNull(code) || Check.isNull(state)){
			return LhTip.code_login_wxRedirect;
		}
		//用户没有登陆，并且通过微信认证渠道进入
		String openId = getWxOpenId(code);
		if(Check.isNull(openId)){
			return LhTip.code_login_wxRedirect;
		}
		//正确获取了OPENID
		HttpSession session = request.getSession();
		Map<String, Object> map = CommonGenerator.getHashMap();
		map.clear();
		//map.put("thirdName", openId);
		map.put("wxOpenid", openId);
		User user = userService.selectByCondition(map);
		if(null != user){//该openId对应的用户已经注册过，则直接将用户信息放入Session
			System.out.println("checkWxLoginCore: "+openId);
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getId());
			return LhTip.code_login_already;
			//return openId;//虽然数据库中有对应OPENID,但是需要通过密码才能登陆：通知登陆
		}
		//新用户
		JSONObject wxUserJson = getWxUserInfo(session, openId);//通过OPENID获取用户基本信息（名称，头像）
		WxUser wxUser = JSONObject.toJavaObject(wxUserJson, WxUser.class);
		if(null == wxUser)return LhTip.code_login_doLogin;
		Integer isFocus = wxUser.getSubscribe();
		if(null != isFocus && isFocus == 0){//用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
			return LhTip.code_login_jumpToFocus;
		}
		user = resetUser(); //新建一个用户，有默认密码和默认头像
		user.setUsername(wxUser.getNickname());
		user.setNickName(wxUser.getNickname());
		user.setWxOpenid(wxUser.getOpenid());//openid
		user.setSex(wxUser.getSex());
		if(Check.isNotNull(wxUser.getProvince())){//省：名称转ID
			map.clear();
			map.put("areaName", wxUser.getProvince());
			ProvinceCityArea pca = pcaMapper.selectByCondition(map);
			if(null != pca){
				user.setProvince(pca.getId());
			}
		}
		if(Check.isNotNull(wxUser.getCity())){//市：名称转ID
			map.clear();
			map.put("areaName", wxUser.getCity());
			ProvinceCityArea pca = pcaMapper.selectByCondition(map);
			if(null != pca){
				user.setCity(pca.getId());
			}
		}
		String avatar = wxUser.getHeadimgurl();//微信用户头像
		System.out.println("wxUser.getHeadimgurl(): " + avatar);
		if(Check.isNotNull(avatar)){//头像
			String endpoint = LhConst.oss_endpoint;
		    String accessKeyId = LhConst.oss_accessKeyId;
		    String accessKeySecret = LhConst.oss_accessKeySecret;
		    String bucketName = LhConst.oss_bucketName;
		    String bucketEndpoint = LhConst.oss_bucketEndpoint;
		    AliyunOSS oss = AliyunOSS.buildOSS(null, null, endpoint, accessKeyId, accessKeySecret, bucketName, bucketEndpoint);
			String basePath = request.getServletContext().getRealPath("/");
			String filePath = ImgFromUrl.saveImgFromUrl(basePath, null, wxUser.getHeadimgurl(), oss);//根据头像URL地址保存到本地
			System.out.println("wxUser.getHeadimgurl()_filePath: "+filePath);
			if(Check.isNull(filePath)){
				filePath = LhAssets.defaultUserAvatar;
			}
			user.setAvatar(filePath);
		}
		JSONObject json = new JSONObject();
		user = userService.addRegUser(json, user);//注册新用户
		if(null != user && null != user.getId()){

			session.setAttribute("user", user);
			session.setAttribute("userId", user.getId());
			return LhTip.code_login_bindPhoneSetPassword;//不需要重新登陆，但是提示请尽快设置登陆密码和绑定手机号
		}
		return LhTip.code_login_doLogin;//1:需要重新登陆
	}
	
	public User resetUser(){
		Date date = new Date();
		User newUser = new User();
		newUser.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_user));
		//newUser.setUsername(user.getUsername());
		//newUser.setPhone(user.getPhone());
		newUser.setPassword(Md5Util.encrypt(LhConst.user_default_password));//默认密码
		newUser.setMainStatus(1);//启用
		newUser.setLastLoginTime(date);
		newUser.setCreatedAt(date);
		newUser.setAvatar(LhAssets.defaultUserAvatar);
		return newUser;
	}
	
	/**
	 * 根据code获取微信用户的openid
	 * @param code
	 * @return openId String
	 */
	public String getWxOpenId(String code){
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ConfigUtil.APPID+"&secret="+ConfigUtil.APP_SECRECT+"&code="+code+"&grant_type=authorization_code";
		String result =CommonUtil.httpsRequestForStr(requestUrl, "GET", null);
		JSONObject resultJson = JSONObject.parseObject(result);
		String openId = resultJson.getString("openid");//根据access_token获取ticket
		System.out.println("user_openId:"+openId);
		return openId;
	}
	
	public JSONObject getWxUserInfo(HttpSession session,String openId){
		
		/*String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ConfigUtil.APPID+"&secret="+ConfigUtil.APP_SECRECT;
		String tokenResult =CommonUtil.httpsRequestForStr(tokenUrl, "GET", null);
		JSONObject tokenJson = JSONObject.parseObject(tokenResult);
		String access_token = tokenJson.getString("access_token");*/
		
		String access_token = AuthAccess.getWxDataFromProperty("access_token");//从Property文件中获取ticket,如果文件中没有，则会远程获取
		System.out.println("getWxUserInfo-access_token:"+access_token);
		
		String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
		String userInfoResult =CommonUtil.httpsRequestForStr(userInfoUrl, "GET", null);
		
		if(userInfoResult.contains("errcode")){//返回失败信息，就更新access_token再尝试一次
			access_token = AuthAccess.setPropertyFromRemote("access_token");
		}
		userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
		userInfoResult =CommonUtil.httpsRequestForStr(userInfoUrl, "GET", null);
		
		//System.out.println("result:"+userInfoResult);
		JSONObject userInfoJson = JSONObject.parseObject(userInfoResult);
		System.out.println("getWxUserInfo-userInfoJson:"+userInfoJson);
		
		return userInfoJson;
	}

}
