package com.lhfeiyu.action.front.chat;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Chat;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.ChatService;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

@Controller
@RequestMapping(value="/message")
public class MessageAction {
	private static Logger logger = Logger.getLogger("R");
	@Autowired
	private ChatService chatService;
	@Autowired
	private NoticeService noticeSerrvice;
	@RequestMapping(value="/message")
	public ModelAndView chat(ModelMap modelMap,HttpServletRequest request) {
		String path = LhPage.message;
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				new ModelAndView("/index");
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("sender_id", sessionUser.getId());
			map.put("notRead", 1);
			map.put("groupBy", "send_time");
			Notice notice =  noticeSerrvice.selectByCondition(map);
			modelMap.put("notice", notice);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/message", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getMessageList")
	public JSONObject getMessageList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			int userId = sessionUser.getId();
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("receiverId", sessionUser.getId());
			map.put("notDeal", 1);
			map.put("orderBy", "send_time");
			map.put("ascOrdesc", "DESC");
			List<Chat> baseChat = chatService.selectListByCondition(map);
			json.put("rows", baseChat);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/message/getMessageList", json);
		}
		return Result.success(json);
	}
	@ResponseBody
	@RequestMapping(value="/updateMessage", method=RequestMethod.POST)
	public JSONObject updateMessage(HttpServletRequest request,
			@ModelAttribute Chat chat) {
		JSONObject json = new JSONObject();
		try {
			User sessionUser = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == sessionUser){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Date date = new Date();
			Chat chat2 = chatService.selectByPrimaryKey(chat.getId());
			chat2.setReadTime(date);
			chat2.setMainStatus(2);
			chatService.updateByPrimaryKey(chat2);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/message/getMessageList", json);
		}
		return Result.success(json);
	}
}

