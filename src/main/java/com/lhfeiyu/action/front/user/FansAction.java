package com.lhfeiyu.action.front.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Fans;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.FansService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class FansAction {
	@Autowired
	private FansService fansService;
	@Autowired
	private AuthCheckService authCheckService;
	@Autowired
	private UserService userService;
	
	private static Logger logger = Logger.getLogger("R"); 
	
	/**藏友页面*/
	@RequestMapping(value="/fans")
	public ModelAndView fans(ModelMap modelMap,HttpServletRequest request
			,@RequestParam(required=false) String r) {
		String path = LhPage.fans;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				String jumpUrl = "/fans";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("currentUserId", user.getId());
			modelMap.put("label", "fans");
			
			/*//粉丝页面,登陆聊天组件,发送红包
			Integer userId = user.getId();
			Integer receiverId = 3;//临时测试
			int rId = receiverId;
			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			User receiver = userService.selectByPrimaryKey(receiverId);
			//String sig = ConstField.generateSubSig(user.getThirdName(),user.getThirdPassword(),timeStamp);//帐号密码登陆
			String sig = ConstField.generateSubSig(user.getSerial(), timeStamp);//应用登陆
			modelMap.put("senderId", userId);
			modelMap.put("userTokenId", user.getThirdName());
			modelMap.put("userTokenPswd", user.getThirdPassword());
			modelMap.put("senderName", user.getUsername());
			modelMap.put("senderAvatar", user.getAvatar());
			modelMap.put("sig", sig);
			modelMap.put("timeStamp", timeStamp);
			modelMap.put("receiverId", rId);
			modelMap.put("receiverTokenId", receiver.getThirdName());
			modelMap.put("receiverName", receiver.getUsername());
			modelMap.put("receiverAvatar", receiver.getAvatar());
			modelMap.put("senderSerial", user.getSerial());//应用登陆对应的账号：用户序号
			modelMap.put("receiverSerial", receiver.getSerial());//应用登陆对应的账号：用户序号
			 */			
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/fans", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**藏友页面*/
	@RequestMapping(value="/notice")
	public ModelAndView notice(ModelMap modelMap, HttpServletRequest request
			,@RequestParam(required=false) String r) {
		String path = LhPage.fans;
		try{
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());
			if(null == sessionUser){
				String jumpUrl = "/notice";
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("currentUserId", sessionUser.getId());
			modelMap.put("label", "notice");
			
			/*//粉丝页面,登陆聊天组件,发送红包
			Integer userId = user.getId();
			Integer receiverId = 3;//临时测试
			int rId = receiverId;
			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			User receiver = userService.selectByPrimaryKey(receiverId);
			//String sig = ConstField.generateSubSig(user.getThirdName(),user.getThirdPassword(),timeStamp);//帐号密码登陆
			String sig = ConstField.generateSubSig(user.getSerial(), timeStamp);//应用登陆
			modelMap.put("senderId", userId);
			modelMap.put("userTokenId", user.getThirdName());
			modelMap.put("userTokenPswd", user.getThirdPassword());
			modelMap.put("senderName", user.getUsername());
			modelMap.put("senderAvatar", user.getAvatar());
			modelMap.put("sig", sig);
			modelMap.put("timeStamp", timeStamp);
			modelMap.put("receiverId", rId);
			modelMap.put("receiverTokenId", receiver.getThirdName());
			modelMap.put("receiverName", receiver.getUsername());
			modelMap.put("receiverAvatar", receiver.getAvatar());
			modelMap.put("senderSerial", user.getSerial());//应用登陆对应的账号：用户序号
			modelMap.put("receiverSerial", receiver.getSerial());//应用登陆对应的账号：用户序号
			*/			
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/notice", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**粉丝排名*//*
	@RequestMapping(value="/fansRanking")
	public ModelAndView fansRanking(ModelMap modelMap,HttpSession session
			,@RequestParam(required=false) String r,HttpServletRequest request) {
		String path = LhPage.fansRanking;
		path = LhPage.frontIndex;//TODO-tempHide-粉丝榜
		try{
			User sessionUser = ActionUtil.checkSession4User(session);
			if(null == sessionUser){
				return Result.userSessionInvalid(modelMap, "/fansRanking");
			}else{
				modelMap.put("currentUserId", sessionUser.getId());
			}
			modelMap = authCheckService.checkWxLogin(request, modelMap);
			modelMap = Check.actionLhPagePromoterCheck(session, modelMap, r);
		}catch(Exception e){
			e.printStackTrace();
			path = LhPage.error;
			logger.error("LH_ERROR_加载粉丝排名的页面出现异常_"+e.getMessage());
		}
		return new ModelAndView(path, modelMap);
	}
	*/
	@ResponseBody
	@RequestMapping(value="/addOrUpdateFans", method=RequestMethod.POST)
	public JSONObject addOrUpdateFans(@ModelAttribute Fans fans,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Date date = new Date();
			Integer userId = user.getId();
			String username = user.getUsername();
			fans.setFansId(userId);
			String userSerial = fans.getUserSerial();
			if(Check.isNull(userSerial)){
				return Result.failure(json, "请指定需要关注的用户", "userSerial_null");
			}
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("serial", userSerial);
			User toUser = userService.selectByCondition(map);
			if(null == toUser){
				return Result.failure(json, "关注的用户不存在", "user_null");
			}
			fans.setUserId(toUser.getId());
			if(null == fans.getId()){//添加
				//Map<String,Object> map = CommonGenerator.getHashMap();
				map.clear();
				map.put("userId", fans.getUserId());
				map.put("fansId", userId);
				int count = fansService.selectCountByCondition(map);
				if(count > 0){
					return Result.failure(json, "您已经关注该用户", "focus_already");
				}
				fans.setCreatedBy(username);
				fans.setCreatedAt(date);
				fansService.insert(fans);
			}else{//修改 - 只有新增或删除
				/*fans.setUpdatedBy(username);
				fans.setUpdatedAt(date);
				fansService.updateByPrimaryKeySelective(fans);*/
			}
			json.put("id", fans.getId());
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/addOrUpdateFans", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getFansList", method=RequestMethod.POST)
	public JSONObject getFansList(HttpServletRequest request) {
		List<Fans> fansList = null;
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = ActionUtil.getAllParam(request);
			fansList = fansService.selectListByCondition(map);
			Integer total = fansService.selectCountByCondition(map);
			json.put("rows", fansList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/getFansList", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/checkFocus", method=RequestMethod.POST)
	public JSONObject checkFocus(HttpServletRequest request,@RequestParam Integer userId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			fansService.checkFocus(json, user, userId);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/checkFocus", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/cancelFocus", method=RequestMethod.POST)
	public JSONObject deleteFans(HttpServletRequest request, @RequestParam String userSerial) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("serial", userSerial);
			User toUser = userService.selectByCondition(map);
			if(null == toUser){
				return Result.failure(json, "用户不存在", "user_null");
			}
			fansService.cancelFocus(json, user, toUser.getId());
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/deleteFans", json);
		}
		return Result.success(json);
	}
	
}
