package com.lhfeiyu.action.front.chat;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.po.domain.Chat;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.ChatService;
import com.lhfeiyu.service.domain.PushService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;

@Controller
@RequestMapping(value="/chat")
public class ChatAction {
	
	@Autowired
	private ChatService chatService;
	@Autowired
	private PushService pushService;
	@Autowired
	private AuthCheckService authCheckService;
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value="/{receiverSerial}", method=RequestMethod.GET)
	public ModelAndView chat(ModelMap modelMap,HttpServletRequest request
			,@PathVariable String receiverSerial
			,@RequestParam(required=false) String gSerial
			,@RequestParam(required=false) String r) {
		try {
			JSONObject json = new JSONObject();
			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				String jumpUrl = "/chat/"+receiverSerial;
				if(Check.isNotNull(gSerial))jumpUrl += "?gSerial="+gSerial;
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			String userSerial = sessionUser.getSerial();
			if(userSerial.equals(receiverSerial)){//不能和自己聊天
				modelMap.put("receiverName", sessionUser.getUsername());
				Result.failure(modelMap, LhTip.msg_chat_self, LhTip.code_chat_self);
				return new ModelAndView(LhPage.chat, modelMap);
			}
			//Map<String,Object> map = chatService.initYtxChat(sessionUser, receiverSerial, gSerial);
			//modelMap.putAll(map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/chat/"+receiverSerial, modelMap);
		}
		return new ModelAndView(LhPage.chat, modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/doPush")
	public JSONObject doPush(HttpServletRequest request,@RequestParam String msgContent
			,@RequestParam String[] receiver,@RequestParam String pushType) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			//chatService.doPush(json, user, msgContent, receiver, pushType);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/chat/doPush", json);
		}
		return Result.success(json);
	}
	
	/*@ResponseBody
	@RequestMapping(value="/createGroup")
	public JSONObject createGroup(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			ChatGroup group = new ChatGroup();
	    	group.setPermission("1");
	    	group.setScope("1");
	    	group.setThirdName("8a48b551505b4af001505f21acd1091c");
	    	group.setThirdPassword("cbd8df3f3fd74cd78c852b0c4c1aa198");
	    	ChatGroupService.createGroup(group);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载店铺列表出现异常_", json);
		}
		return Result.success(json);
	}*/
	
	@ResponseBody
	@RequestMapping(value="/addChat")
	public JSONObject addChat(HttpServletRequest request, @ModelAttribute Chat chat) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			int userId = sessionUser.getId();
			String username = sessionUser.getUsername();
			chat.setSenderId(userId);
			chatService.addUpdateChat(json, chat, username);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/chat/addChat", json);
		}
		return Result.success(json);
	}
	
	
	/*@ResponseBody
	@RequestMapping(value="/addChatQuick")
	public JSONObject addChatQuick(HttpServletRequest request,@ModelAttribute ChatQuick chat) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			int userId = user.getId();
			String groupId = chat.getChatGroupId();
			if(null == groupId){
				json.put("status", "failure");
				json.put("msg", "参数不足,无法发送消息");
				return Result.success(json);
			}
			chat.setSerial(CommonGenerator.getSerialByDate("cq"));
			Date d = new Date();
			chat.setCreatedAt(d);
			chat.setCreatedBy(user.getUsername());
			chat.setSendTime(d);
			chat.setSenderId(userId);
			cqService.insertSelective(chat);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_添加即时拍聊天消息记录出现异常_", json);
		}
		return Result.success(json);
	}*/
	
	@ResponseBody
	@RequestMapping(value="/getChatList", method=RequestMethod.POST)
	public JSONObject getChatList(HttpServletRequest request,
			@RequestParam(required=false) String currentUserChat, 
			@RequestParam(required=false) String twoPeopleChat) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			int userId = user.getId();
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("orderBy", "main_status ASC, A.read_time");
			map.put("ascOrdesc", "DESC");
			if(null == twoPeopleChat ){
				if(null != currentUserChat && !"".equals(currentUserChat)){
					//map.put("notRead", 1);
					map.put("receiverId", userId);
				}else{
					map.put("showSingle",1);//只查询两个人对话的聊天记录
					map.put("senderId", userId);
				}
			}else{
				map.put("showSingle",1);//只查询两个人对话的聊天记录
			}
			chatService.getChatList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/chat/getChatList", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateChatRead")
	public JSONObject updateChatRead(HttpServletRequest request,@RequestParam String serial) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Date d = new Date();
			Chat chat = new Chat();
			chat.setUpdatedAt(d);
			chat.setUpdatedBy(user.getUsername());
			chat.setReadTime(d);
			chat.setMainStatus(2);
			chat.setSerial(serial);
			chatService.updateBySerial(chat);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/chat/updateChatRead", json);
		}
		return Result.success(json);
	}
	
}
