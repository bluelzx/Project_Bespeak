package com.lhfeiyu.action.front.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserPraise;
import com.lhfeiyu.service.domain.UserPraiseService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
public class UserPraiseAction {
	@Autowired
	private UserPraiseService userPraiseService;
	
	private static Logger logger = Logger.getLogger("R");
	
//	@ResponseBody
//	@RequestMapping(value="/addOrUpdateUserPraise", method=RequestMethod.POST)
//	public JSONObject addOrUpdateUserPraise(@ModelAttribute UserPraise userPraise,HttpServletRequest request) {
//		JSONObject json = new JSONObject();
//		try {
//			userPraise.setMainStatus(1);
//			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
//			if(null == user){
//				return Result.userSessionInvalid(json);//返回session过期的json提示
//			}
//			Integer userId = user.getId();
//			userPraise.setUserId(userId);
//			String username = user.getUsername();
//			Date date = new Date();
//			if(null == userPraise.getId()){//添加
//				if(null == userPraise.getPraiseType() || null == userPraise.getPraiseId()){
//					return Result.success(json, "参数不足", "param_error");
//				}
//				if(null == userPraise.getCreatedAt()){
//					userPraise.setCreatedAt(date); 
//				}
//				userPraise.setUserId(userId);
//				userPraise.setCreatedBy(username);
//				Map<String,Object> map = new HashMap<String,Object>();
//				map.put("praiseType", userPraise.getPraiseType());
//				map.put("praiseId", userPraise.getPraiseId());
//				map.put("userId", userId);
//				int count = userPraiseService.selectCountByCondition(map);
//				if(count > 0){
//					return Result.success(json, "已经点赞", "alreadyExist_error");
//				}
//				userPraiseService.insert(userPraise);
//				Result.success(json, null, null);
//			}
//			json.put("praiseId", userPraise.getId());
//			json.put("userAvatar", user.getAvatar());
//		} catch (Exception e) {
//			Result.catchError(e, logger, "LH_ERROR_新增用户点赞出现异常_", json);
//		}
//		return Result.success(json);
//	}
	
	@ResponseBody
	@RequestMapping(value="/deleteUserPraise", method=RequestMethod.POST)
	public JSONObject deleteUserPraise(HttpServletRequest request
			,@ModelAttribute UserPraise userPraise) {
		JSONObject json = new JSONObject();
		
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
//			if(null == user){
//				return Result.userSessionInvalid(json);//返回session过期的json提示
//			}
//			int sessionUserId = user.getId();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("praiseId", userPraise);
//			map.put("userId", sessionUserId);
//			System.out.println("000userId"+sessionUserId+"praiseId:"+praiseId);
//			userPraise = userPraiseService.selectByCondition(map);
//			if(null != userPraise){
//				praiseId = userPraise.getId();
//				if(Check.integerNotEqual(sessionUserId, userPraise.getUserId())){
//					return Result.failure(json, "您没有该权限", "authority_error");
//				}
				
				userPraiseService.deleteByCondition("user_id="+userPraise.getUserId()+" AND praise_id="+userPraise.getPraiseId()+" AND praise_type="+userPraise.getPraiseType());
				
				json.put("userPraise", userPraise);
				Result.success(json);
//			}else{
//				return Result.failure(json, "数据不存在", "null_error");
//			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_取消点赞出现异常_", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/addUserPraise", method=RequestMethod.POST)
	public JSONObject addUserPraise(HttpServletRequest request,
			@ModelAttribute UserPraise userPraise) {
		JSONObject json = new JSONObject();
		try {
			userPraise.setMainStatus(1);
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
//			if(null == user){
//				return Result.userSessionInvalid(json);//返回session过期的json提示
//			}
//			Integer userId = user.getId();
//			userPraise.setUserId(userId);
//			String username = user.getUsername();
			Date date = new Date();
		
				if(null == userPraise.getPraiseType() || null == userPraise.getPraiseId()){
					return Result.success(json, "参数不足", "param_error");
				}
				if(null == userPraise.getCreatedAt()){
					userPraise.setCreatedAt(date); 
				}
//				userPraise.setUserId(userId);
//				userPraise.setCreatedBy(username);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("praiseType", userPraise.getPraiseType());
				map.put("praiseId", userPraise.getPraiseId());
				map.put("userId", userPraise.getUserId());
				int count = userPraiseService.selectCountByCondition(map);
				if(count > 0){
					json.put("flag", false);
					return Result.success(json, "已经点赞", "alreadyExist_error");
				}
				userPraiseService.insert(userPraise);
				Result.success(json, null, null);
				json.put("flag", true);
			json.put("praiseId", userPraise.getId());
//			json.put("userAvatar", user.getAvatar());
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_新增用户点赞出现异常_", json);
		}
		return Result.success(json);
	}
	
}
