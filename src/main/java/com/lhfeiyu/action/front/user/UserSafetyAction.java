package com.lhfeiyu.action.front.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.domain.AliyunOSS;
import com.lhfeiyu.po.domain.Dict;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.service.domain.PictureService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.thirdparty.wx.business.AuthAccess;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.IDCard_Verify;
import com.lhfeiyu.tools.ImageUtils;
import com.lhfeiyu.tools.Result;

@Controller
@RequestMapping(value = "/user/safety")
public class UserSafetyAction {

	@Autowired
	private UserService userService;
	@Autowired
	private DictService dictService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	PictureService pictureService;
	private static Logger logger = Logger.getLogger("R");

	/** 页面：支付安全 */
	@RequestMapping(value = "/paySafety", method = RequestMethod.GET)
	public ModelAndView paySafety(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.paySafety;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if (null == sessionUser) {
				String jumpUrl = "/loginPasswordFind";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User user = userService.selectByPrimaryKey(sessionUser.getId());
			modelMap.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/** 页面：找回登陆密码 */
	@RequestMapping(value = "/loginPassRetrieve", method = RequestMethod.GET)
	public ModelAndView loginPassRetrieve(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.loginPassRetrieve;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if (null == sessionUser) {
				String jumpUrl = "/loginPassRetrieve";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User user = userService.selectByPrimaryKey(sessionUser.getId());
			modelMap.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_找回密码页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/** 修改密码 */
	@RequestMapping(value = "/loginPassSet", method = RequestMethod.GET)
	public ModelAndView loginPassSet(ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.loginPasswordUpdate;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if (null == sessionUser) {
				String jumpUrl = "/loginPassSet";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User user = userService.selectByPrimaryKey(sessionUser.getId());
			if (null == user.getPhone() || "".equals(user.getPhone())) {
				modelMap.put("noPhone", "noPhone");
			}
			/*
			 * User sessionUser = ActionUtil.checkSession4User(session); if(null
			 * == sessionUser){ return Result.userSessionInvalid(modelMap,
			 * "/loginPasswordUpdate"); } User user =
			 * userService.selectByPrimaryKey(sessionUser.getId());
			 */
			modelMap.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_修改密码页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/**
	 * 设置找回密码邮箱
	 */
	@RequestMapping(value = "/loginPasswordMailUpdate", method = RequestMethod.GET)
	public ModelAndView loginPasswordMailUpdate(ModelMap modelMap, HttpSession session, @RequestParam(required = false) String r, HttpServletRequest request) {
		String path = LhPage.loginPasswordMailUpdate;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(session);
			if (null == sessionUser) {
				String jumpUrl = "/loginPasswordMailUpdate";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dictCode", "smtp");
			List<Dict> dictList = dictService.selectListByCondition(map);
			if (dictList.size() > 0) {
				Dict dict = dictList.get(0);
				modelMap.put("dictJson", JSON.toJSON(dict));
				modelMap.put("id", dict.getId());
			}
			modelMap.put("user", sessionUser);
		} catch (Exception e) {
			path = LhPage.error;
			logger.error("LH_ERROR_加载设置找回密码页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/**
	 * 找回密码
	 **/
	@ResponseBody
	@RequestMapping(value = "/findLoginPassword", method = RequestMethod.POST)
	public JSONObject findLoginPassword(HttpServletRequest request, @RequestParam String email) {
		JSONObject json = new JSONObject();
		try {
			if (null != email && !"".equals(email)) {
				User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
				if (null == sessionUser) {
					return Result.userSessionInvalid(json);// 返回session过期的json提示
				}
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_找回密码出现异常_", json);
		}
		return Result.success(json);
	}

	/** 页面：手机号验证  */
	@RequestMapping(value="/phoneValidate", method=RequestMethod.GET)
	public ModelAndView phoneValidate(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r){
		String path = LhPage.phoneValidate;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if(null == sessionUser){
				String jumpUrl = "/phoneValidate";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User user = userService.selectByPrimaryKey(sessionUser.getId());
			String showPhone = subPhoneNum(user.getPhone());
			modelMap.put("user", user);
			modelMap.put("showPhone", showPhone);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	private String subPhoneNum(String mobile) {
		String str = "";
        for (int i = 0; i < mobile.length(); i++) {
            if (i == mobile.length()-11) {
                str += mobile.charAt(i);
            } else if(i == mobile.length()-10) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-9) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-3) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-2) {
             str += mobile.charAt(i);
            }else if(i == mobile.length()-1) {
             str += mobile.charAt(i);
            }else {
              str += "*";
            }
        }
		return str;
	}
	/** 验证手机号码是否已经注册 */
	@ResponseBody
	@RequestMapping(value = "/checkUserPhoneExist")
	public JSONObject checkUserPhoneExist(HttpServletRequest request, @RequestParam String phone) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);
			int userCount = userService.selectCountByCondition(map);
			if (userCount > 0) {
				return Result.success(json, "手机号码已占用", LhTip.code_exist_yes);
			} else {
				return Result.success(json, LhTip.msg_exist_no, LhTip.code_exist_no);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/user/checkPhoneExist", json);
		}
		return Result.success(json);
	}  
	
//	@ResponseBody
//	@RequestMapping(value = "/updateUserPhone")
//	public JSONObject updateUserPhone(HttpServletRequest request, 
//			@RequestParam String phone) {
//		JSONObject json = new JSONObject();
//		try {
//			User sessionUser = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
//			if (null == sessionUser) {
//				return Result.userSessionInvalid(json);// 返回session过期的json提示
//			}
////			User user = userService.selectByPrimaryKey(sessionUser.getId());
//			sessionUser.setPhone(phone);
//			userService.updateByPrimaryKey(sessionUser);
//			
//		} catch (Exception e) {
//			Result.catchError(e, logger, this.getClass().getName() + "/user/checkPhoneExist", json);
//		}
//		return Result.success(json,"验证通过!!");
//	}

	/** 页面：个人实名认证 */
	@RequestMapping(value = "/realNameAuthentication", method = RequestMethod.GET)
	public ModelAndView realNameAuthentication(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.realNameAuthentication;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			
			if (null == sessionUser) {
				String jumpUrl = "/phoneValidate";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User user = userService.selectByPrimaryKey(sessionUser.getId());
			System.out.println(user);
			if(null == user.getIsRealAuth()){
				modelMap.put("isRealAuths", "未认证");
			}else{
				modelMap.put("isRealAuths", "已认证");
				return new ModelAndView("redirect:/user/safety/validateSuccess");
			}
			modelMap.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/** 页面：个人实名认证 */
	@RequestMapping(value = "/individualAuthentication", method = RequestMethod.GET)
	public ModelAndView individualAuthentication(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.individualAuthentication;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if (null == sessionUser) {
				String jumpUrl = "/phoneValidate";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User user = userService.selectByPrimaryKey(sessionUser.getId());
			modelMap.put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	/** 页面：个人实名认证 */
	@RequestMapping(value = "/individualMsgAuthentication", method = RequestMethod.GET)
	public ModelAndView individualMsgAuthentication(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false) String r) {
		String path = LhPage.individualMsgAuthentication;
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if (null == sessionUser) {
				String jumpUrl = "/phoneValidate";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User user = userService.selectByPrimaryKey(sessionUser.getId());
			modelMap.put("user", user);
			
			// 微信验证
			String ticket = AuthAccess.getWxDataFromProperty("ticket");// 从Property文件中获取ticket,如果文件中没有，则会远程获取
			String url = "http://weipaike.net/user/safety/individualMsgAuthentication";
			url = ActionUtil.buildPromoterUrl(url, r);
			System.out.println("url: " + url);
			modelMap = AuthAccess.getSign(modelMap, ticket, url);
			
		} catch (Exception e) {
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_" + e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	
	/** 实名申请 */
	@ResponseBody
	@RequestMapping(value = "/addUserAuthentication", method = RequestMethod.POST)
	public JSONObject addUserAuthentication(HttpServletRequest request,ModelMap modelMap,
			HttpSession session,
			@ModelAttribute User baseUser) {
		System.out.println("0000");
		JSONObject json = new JSONObject();
		session.setAttribute("userPicPaths", baseUser.getPicPaths());
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			//认证开始
			String realName = baseUser.getRealName();
			String idcardNum = String.valueOf(baseUser.getIdcardNum());
			User newUser = userService.selectByPrimaryKey(user.getId());
			IDCard_Verify idCardVerify = new IDCard_Verify();
			JSONObject result = idCardVerify.idcard_verify(realName,idcardNum);
			System.out.println("aaaa"+result);
			int status=Integer.parseInt(result.getString("status"));
			int code=Integer.parseInt(result.getJSONObject("data").getString("code"));
			String message=result.getJSONObject("data").getString("message");
			String idCardPhoto=result.getJSONObject("data").getString("idCardPhoto");
			String path=request.getSession().getServletContext().getRealPath("/file/user/");
			String imgName = "userIdCard"+System.currentTimeMillis()+".jpg";
			String idCardImg = ImageUtils.decodeBase64ToImage(idCardPhoto, path, "\\"+imgName);
			System.out.println(idCardImg);
			//status==2001//2001=正常服务
			//code==1001//不一致
			if(code == 1000){
				newUser.setRealName(realName);
				newUser.setIdcardNum(baseUser.getIdcardNum());
				newUser.setPicPath("/file/user/"+imgName);
				userService.updateByPrimaryKey(newUser);
				modelMap.put("paramJson", json);					
				return Result.success(json,"验证成功");
			}else{
				return Result.success(json,"验证失败");
			}
//				//验证结束	
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/addForum", json);
		}
		System.out.println(json);
		return Result.success(json,"验证成功");
	}

	/** 页面：认证结果  */
	@RequestMapping(value="/validateSuccess", method=RequestMethod.GET)
	public ModelAndView validateSuccess(ModelMap modelMap,HttpServletRequest request
			,HttpSession session,@RequestParam(required=false) String r){
		String path = LhPage.validateSuccess;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if(null == sessionUser){
				String jumpUrl = "/index";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			User newUser = userService.selectByPrimaryKey(sessionUser.getId());
			modelMap.put("user", newUser);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	/** 实名保存信息 */
	@ResponseBody
	@RequestMapping(value = "/saveUserAuthentication", method = RequestMethod.POST)
	public JSONObject saveUserAuthentication(HttpServletRequest request,ModelMap modelMap,
			HttpSession session,@ModelAttribute User baseUser) {
		JSONObject json = new JSONObject();
		session.setAttribute("userPicPaths", baseUser.getPicPaths());
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			User newUser = userService.selectByPrimaryKey(user.getId());
			
			String filePaths = (String) session.getAttribute("userPicPaths");
			String basePath = request.getServletContext().getRealPath("/");
			String endpoint = LhConst.oss_endpoint;
			String accessKeyId = LhConst.oss_accessKeyId;
			String accessKeySecret = LhConst.oss_accessKeySecret;
			String bucketName = LhConst.oss_bucketName;
			String bucketEndpoint = LhConst.oss_bucketEndpoint;
			AliyunOSS oss = AliyunOSS.buildOSS(null, null, endpoint, accessKeyId, accessKeySecret, bucketName,
					bucketEndpoint);
			String mediaId = filePaths;
			if (Check.isNotNull(mediaId) && !mediaId.equalsIgnoreCase("undefined")) {// 此处为微信服务器获取用户上传的图片
				json = pictureService.getPicPathsByWxServerIds(json, mediaId, basePath, oss);
				String picPaths = json.getString("picPaths");
				if (Check.isNotNull(picPaths)) {
					if (picPaths.startsWith(","))
						picPaths = picPaths.substring(1);
					String localPath = "/file/wx/" + picPaths.substring(picPaths.lastIndexOf("/"), picPaths.length());
					newUser.setPicPaths(picPaths);
					// apply.setFile2(localPath);
				}
			}
			newUser.setIsRealAuth(1);
			System.out.println("身份证图片地址filePaths： "+newUser);
			userService.updateByPrimaryKey(newUser);
			
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/addForum", json);
		}
		System.out.println(json);
		return Result.success(json,"保存成功");
	}	
	
	/** 页面：企业认证流程  */
	@RequestMapping(value="/enterpriseAuthentication", method=RequestMethod.GET)
	public ModelAndView enterpriseAuthentication(ModelMap modelMap,HttpServletRequest request
			,HttpSession session,@RequestParam(required=false) String r){
		String path = LhPage.enterpriseAuthentication;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if(null == sessionUser){
				String jumpUrl = "/index";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	/** 页面：企业认证 */
	@RequestMapping(value="/enterpriseMsgAuthentication", method=RequestMethod.GET)
	public ModelAndView enterpriseMsgAuthentication(ModelMap modelMap,HttpServletRequest request
			,HttpSession session,@RequestParam(required=false) String r){
		String path = LhPage.enterpriseMsgAuthentication;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request);
			if(null == sessionUser){
				String jumpUrl = "/index";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_支付安全页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}

	/** 验证邮箱是否已经注册 */
	@ResponseBody
	@RequestMapping(value = "/checkUserEmailExist")
	public JSONObject checkUserEmailExist(HttpServletRequest request, @RequestParam String email) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", email);
			int userCount = userService.selectCountByCondition(map);
			if (userCount > 0) {
				return Result.success(json, LhTip.msg_exist_yes, LhTip.code_exist_yes);
			} else {
				return Result.success(json, LhTip.msg_exist_no, LhTip.code_exist_no);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/user/checkEmailExist", json);
		}
		return Result.success(json);
	}

	}
