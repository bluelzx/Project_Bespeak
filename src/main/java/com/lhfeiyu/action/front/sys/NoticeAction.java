package com.lhfeiyu.action.front.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.ChatService;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class NoticeAction {
	@Autowired
	private NoticeService  noticeService;
	@Autowired
	private ChatService  chatService;
	//@Autowired
	//private OrderInfoService  orderInfoService;
	
	private static Logger logger = Logger.getLogger("R");

	@ResponseBody
	@RequestMapping(value="/getMyNoticeList", method=RequestMethod.POST)
	public JSONObject getMyNotice(HttpServletRequest request,
			@RequestParam(required=false) Integer onlyCount) {
		List<Notice> noticeList = null;
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				json.put("status", "failure");
				json.put("msg", "未登陆");
				return Result.success(json);
			}
			Integer userId = user.getId();
			Map<String, Object> map = ActionUtil.getAllParam(request);
			map.put("receiverId", userId);
			if(null == onlyCount){
				noticeList = noticeService.selectListByCondition(map);
				json.put("rows", noticeList);
			}
			Integer chatCount = chatService.selectCountByCondition(map);
			Integer total = noticeService.selectCountByCondition(map);
			/*map.clear();
			map.put("orderInfoCount", 1);
			map.put("orderGoodsStatus", "1,3");
			map.put("userId", user.getId());
			Integer orderInfoCount = orderInfoService.selectCountByCondition(map);*/
			map.clear();
			map.put("offerStatus", 5);
			map.put("payStatus", 1);
			map.put("orderGoodsStatus", 1);
			map.put("userId",userId );
			//TODO 修改
			/*int waitPayMoneyCount = orderInfoService.selectCountByCondition(map);//待付款
			map.clear();
			map.put("shippingStatus", 2);
			map.put("payStatus", 3);
			map.put("orderGoodsStatus", 1);
			map.put("userId", userId);
			int  shipedCount = orderInfoService.selectCountByCondition(map);//待收货
			map.clear();
			map.put("shippingStatus", 3);
			map.put("payStatus", 3);
			map.put("overComment", 1);
			map.put("orderGoodsStatus", 2);
			map.put("userId", userId);
			//map.put("commentUserId", userId);
			int  commentingCount = orderInfoService.selectCountByCondition(map);//待评价
			map.clear();
			map.put("shippingStatus", 4);
			//map.put("payStatus", 3);
			map.put("orderGoodsStatus", 1);
			map.put("userId", userId);
			map.put("orderGoodsStatus", 3);
			int  returnGoodsCount = orderInfoService.selectCountByCondition(map);//退货
			map.clear();
			map.put("payStatus", 3);
			map.put("shippingStatus", 1);
			map.put("shopUserId", userId);
			map.put("orderGoodsStatus", 1);
			int  shippingCount = orderInfoService.selectCountByCondition(map);//待发货
*/			map.clear();
			map.put("userId", userId);
			map.put("offerStatus","1");
			//Integer goodsOfferCount = goodsOffersService.selectCountByCondition(map);
			/*json.put("chatCount", chatCount);
			json.put("orderInfoCount", shippingCount+returnGoodsCount+commentingCount+shipedCount+waitPayMoneyCount);
			json.put("goodsOfferCount", goodsOfferCount);
			json.put("returnGoodsCount", returnGoodsCount);
			json.put("shippingCount", shippingCount);
			json.put("shipedCount", shipedCount);
			json.put("commentingCount", commentingCount);
			json.put("waitPayMoneyCount", waitPayMoneyCount);*/
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载公告列表出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateNoticeRead")
	public JSONObject updateChatRead(HttpServletRequest request,@RequestParam String serial) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Date d = new Date();
			Notice notice = new Notice();
			notice.setUpdatedAt(d);
			notice.setUpdatedBy(user.getUsername());
			notice.setSerial(serial);
			notice.setReadStatus(2);
			notice.setMainStatus(2);
			notice.setReadTime(d);
			noticeService.updateBySerial(notice);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_通知消息已读出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/sendNotice")
	public JSONObject sendNotice(HttpServletRequest request,@ModelAttribute Notice notice) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Date d = new Date();
			String content = notice.getContent();
			content = user.getUsername()+content;
			notice.setContent(content);
			notice.setCreatedAt(d);
			notice.setCreatedBy(user.getUsername());
			notice.setSerial(CommonGenerator.getSerialByDate("n"));
			notice.setReadStatus(1);
			notice.setMainStatus(1);
			noticeService.insert(notice);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_发送开通消息出现异常_", json);
		}
		return Result.success(json);
	}
	
	
}
