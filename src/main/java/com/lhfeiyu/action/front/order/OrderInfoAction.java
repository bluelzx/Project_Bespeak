package com.lhfeiyu.action.front.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.lhfeiyu.po.domain.Bespeak;
import com.lhfeiyu.po.domain.Coupon;
import com.lhfeiyu.po.domain.Course;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.OrderGoods;
import com.lhfeiyu.po.domain.OrderInfo;
import com.lhfeiyu.po.domain.People;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.base.BaseBespeakService;
import com.lhfeiyu.service.base.BaseCouponService;
import com.lhfeiyu.service.base.BaseProviderService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.service.domain.NoticeService;
import com.lhfeiyu.service.domain.OrderGoodsService;
import com.lhfeiyu.service.domain.OrderInfoService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

@Controller
public class OrderInfoAction {
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private BaseCouponService couponService;
	@Autowired
	private BaseBespeakService bespeakService;
	//通知消息dbService
	@Autowired
	private NoticeService noticeService;
	//用户Service
	@Autowired
	private UserService userService;
	//店铺Service
	@Autowired
	private ShopService shopService;
	//技师Service
	@Autowired
	private BaseProviderService providerService;
	

	private static Logger logger = Logger.getLogger("R");

	@RequestMapping(value="/orderIndex")
	public ModelAndView goodsStore(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(required=false) String r) {
		String path = LhPage.order;
		try{
			JSONObject json = new JSONObject();
			User user = ActionUtil.checkSession4User(request.getSession());
			if(null == user){
				String jumpUrl = "/login";
//				if(Check.isNotNull(shopId)){
//					jumpUrl += "?shopId="+shopId;
//					json.put("shopId", shopId);
//				}
				return Result.userSessionInvalid(modelMap, ActionUtil.buildPromoterUrl(jumpUrl, r));
			}
			modelMap.put("paramJson", json);
		}catch(Exception e){
			path = LhPage.error;
			Result.catchError(e, logger, this.getClass().getName()+"/orderIndex", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/getOrderInfoList", method = RequestMethod.POST)
	public JSONObject getOrderInfoList(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String pageFrom) {
		List<OrderInfo> orderInfoList = null;
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
//			if (null == user) {
//				return Result.userSessionInvalid(json);// 返回session过期的json提示
//			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			Integer userId = user.getId();
//			System.out.println(userId);
			map.put("userId", userId);
//			Integer userId = 1;
//			if (Check.isNotNull(pageFrom)) {
//				if (pageFrom.equals("shipping")) {
//					map.put("orderStatus", 1);
//					map.put("shippingStatus", 1);
//					map.put("payStatus", 3);
//					map.put("orderGoodsStatus", 1);
//					map.put("shopUserId", userId);
//					map.put("expressStateNotOver", userId);
//					map.put("mainStatus", 1);
//				} else if (pageFrom.equals("shipped")) {
//					map.put("orderStatus", 1);
//					map.put("shippingStatus", 2);
//					map.put("payStatus", 3);
//					map.put("orderGoodsStatus", 1);
//					map.put("userId", userId);
//					map.put("expressStateNotOver", userId);
//					map.put("mainStatus", 1);
//				} else if (pageFrom.equals("waitPayMoney")) {
//					map.put("orderStatus", 1);
//					map.put("shippingStatus", 1);
//					map.put("payStatus", 1);
//					map.put("orderGoodsStatus", 1);
//					map.put("userId", userId);
//					map.put("expressStateNotOver", userId);
//					map.put("mainStatus", 1);
//				} else if (pageFrom.equals("returnGoods")) {
//					map.put("orderStatus", 6);
//					map.put("payStatus", 3);
//					map.put("orderGoodsStatus", 5);
//					map.put("sellerOrbuyer", userId);
//					map.put("orderInfoMainStatus", 1);
//				}
//			}
			if (Check.isNotNull(pageFrom)) {
				if (pageFrom.equals("order-all")) {
					
				}else if(pageFrom.equals("order-ing")){// 全部
					map.put("orderStatusCode", "shipping_status_receive");
				}else if(pageFrom.equals("order-evaluated")){//评价
					map.put("orderStatusCode", "order_status_done");
				}else if(pageFrom.equals("order-cancel")){//取消
					map.put("orderStatusCode", "order_status_cancel");
				}else if(pageFrom.equals("order-tuikuang")){//退款中
					map.put("orderStatusCode", "order_status_apply_return");
				}
			}
			orderInfoList = orderInfoService.selectListByCondition(map);
//			System.out.println("map"+map);
//			System.out.println("orderInfoList"+orderInfoList);
			Integer total = orderInfoService.selectCountByCondition(map);
			json.put("rows", orderInfoList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取订单信息出现异常_", json);
		}
		return Result.success(json);
	}

	//订单详情
	@RequestMapping(value = "/orderInfo/{orderInfoId}")
	public ModelAndView orderInfoxx(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer orderInfoId,
			@RequestParam(required = false) String r) {
		String path = LhPage.orderInfo;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderInfoId);
			json.put("orderInfoId", orderInfoId);
			//通过goodsid拿到具体服务
			Goods goods= goodsService.selectByPrimaryKey(orderInfo.getGoodsId());
			orderInfo.setGoodsName(goods.getGoodsName());
			orderInfo.setPicPath(goods.getPicPath());
			orderInfo.setShopPrice(goods.getShopPrice());
			// 通过couponid拿到优惠卷信息
			Coupon coupon=couponService.selectByPrimaryKey(orderInfo.getCouponId());
			//拿到优惠券类型
	try {
//		String ii="<img src='../images/front/order-img.png' class='orderImg'>";
			if(coupon.getWay()==1){
			    orderInfo.setCouponType("金额优惠券: -");
			    orderInfo.setCouponMoney(coupon.getMoney());
			}else if(coupon.getWay()==2){
				orderInfo.setCouponType("企业折扣券: ");
				orderInfo.setCouponzhekou(coupon.getDiscount());
				orderInfo.setCouponCode("折");
			}else{
				orderInfo.setCouponType("无优惠");
			}
		} catch (Exception e) {
				orderInfo.setCouponType("无优惠");
		}
	//拿到类型
	if(orderInfo.getShippingId()==2) {
		orderInfo.setShippingName("上门服务");
	}else{
		orderInfo.setShippingName("门店服务");
	}
			modelMap.addAttribute("orderInfo", orderInfo);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/orderInfo" + "/" + orderInfoId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	//订单申请退款详情
	@RequestMapping(value = "/orderInfoRefund/{orderInfoId}")
	public ModelAndView orderInfoRefund(ModelMap modelMap, HttpServletRequest request, 
			@PathVariable Integer orderInfoId,
			@RequestParam(required = false) String r) {
		String path = LhPage.orderInfoRefund;
		try {
			JSONObject json = new JSONObject();
//			modelMap = authCheckService.checkWxLogin(request, modelMap, json, true, r);
			OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderInfoId);
			json.put("orderInfoId", orderInfoId);
			//通过goodsid拿到具体服务
			Goods goods= goodsService.selectByPrimaryKey(orderInfo.getGoodsId());
			orderInfo.setGoodsName(goods.getGoodsName());
			orderInfo.setPicPath(goods.getPicPath());
			orderInfo.setShopPrice(goods.getShopPrice());
			// 通过couponid拿到优惠卷信息
			Coupon coupon=couponService.selectByPrimaryKey(orderInfo.getCouponId());
			//拿到优惠券类型
	try {
//		String ii="<img src='../images/front/order-img.png' class='orderImg'>";
			if(coupon.getWay()==1){
			    orderInfo.setCouponType("金额优惠券: -");
			    orderInfo.setCouponMoney(coupon.getMoney());
			}else if(coupon.getWay()==2){
				orderInfo.setCouponType("企业折扣券: ");
				orderInfo.setCouponzhekou(coupon.getDiscount());
				orderInfo.setCouponCode("折");
			}else{
				orderInfo.setCouponType("无优惠");
			}
		} catch (Exception e) {
				orderInfo.setCouponType("无优惠");
		}
	//拿到类型
	if(orderInfo.getShippingId()==2) {
		orderInfo.setShippingName("上门服务");
	}else{
		orderInfo.setShippingName("门店服务");
	}
			modelMap.addAttribute("orderInfo", orderInfo);
			modelMap.put("paramJson", json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName() + "/orderInfo" + "/" + orderInfoId, modelMap);
		}
		
		return new ModelAndView(path, modelMap);
	}
	//2016年8月22日14:49:16
	/** 提醒商家（订单） */
	@ResponseBody
	@RequestMapping(value = "/addOneShopNotice", method = RequestMethod.POST)
	public JSONObject addOneShopNotice(HttpServletRequest request, HttpSession session, @RequestParam Integer orderId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示 }
			} // //Integer userId =
			user.getId();
			// 添加一条商家消息
			
			OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
			if(orderInfo.getAttrInt()!=2){
			orderInfo.setAttrInt(2);
			orderInfoService.updateByPrimaryKeySelective(orderInfo);
			User user1=userService.selectByPrimaryKey(orderInfo.getUserId());//获取到下单人信息
			Shop shop=shopService.selectByPrimaryKey(orderInfo.getShopId());
			User user2=userService.selectByPrimaryKey(shop.getUserId());//获取到商家负责人信息
			Date date = new Date();
			Notice notice=new Notice();
			notice.setSendTime(date);
			notice.setSenderId(orderInfo.getUserId());//发送人id
			notice.setSenderName(user1.getRealName());//发送人名
//			notice.setContent(orderInfo.getRemark());//发送人的加备注详细信息
			notice.setReceiverGroupId(orderInfo.getShopId());//接受的店铺id
			notice.setReceiverId(shop.getUserId());//接受的店铺负责人id
			notice.setTypeCode("ShopNotice");//设置类型Code
			notice.setReceiverIds(user2.getUsername());//设置接受的店铺管理员名
			notice.setTitle("来自"+user1.getRealName()+"的一个新的发单提醒！");
			noticeService.insert(notice);
			}
			json.put("sequence","ok");
			// json = orderInfoService.receiveGoods(user, orderId, json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_商家订单提醒出现异常_", json);
		}
		return Result.success(json);
	}
	
	//2016年8月22日14:49:16
	/** 提醒技师（订单） */
		@ResponseBody
		@RequestMapping(value = "/addOneProviderNotice", method = RequestMethod.POST)
		public JSONObject addOneProviderNotice(HttpServletRequest request, HttpSession session, @RequestParam Integer orderId) {
			JSONObject json = new JSONObject();
			try {
				User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
				if (null == user) {
					return Result.userSessionInvalid(json);// 返回session过期的json提示 }
				} // //Integer userId
				user.getId();
				// 添加一条技师消息
				OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
//				System.out.println(orderInfo.getAttrInt2());
				if(orderInfo.getAttrInt2()!=2){
				orderInfo.setAttrInt2(2);
				orderInfoService.updateByPrimaryKeySelective(orderInfo);
//				orderInfo.setAttrInt2(2);
//				orderInfoService.updateOrderInfo(json, orderInfo, user.getUsername());
				User user1=userService.selectByPrimaryKey(orderInfo.getUserId());//获取到下单人信息
				Provider provider=providerService.selectByPrimaryKey(orderInfo.getProviderId());
				Date date = new Date();
				Notice notice=new Notice();
				notice.setSendTime(date);
				notice.setSenderId(orderInfo.getUserId());//发送人id
				notice.setSenderName(user1.getRealName());//发送人名
				notice.setTypeCode("ProviderNotice");//设置类型Code
//				notice.setContent(orderInfo.getRemark());//发送人的加备注详细信息
				notice.setReceiverGroupId(orderInfo.getShopId());//接受的店铺id
				notice.setReceiverId(provider.getId());//接受的店铺负责人id
				notice.setReceiverIds(provider.getUsername());//设置接受的技师名
				notice.setTitle("来自"+user1.getRealName()+"的一个新的服务提醒！");
				noticeService.insert(notice);
				}
				json.put("sequence","ok");
				// json = orderInfoService.receiveGoods(user, orderId, json);
			} catch (Exception e) {
				Result.catchError(e, logger, "LH_ERROR_服务提醒出现异常_", json);
			}
			return Result.success(json);
		}
	//2016年8月23日17:11:14 退款中点击买家手动确认退款/ReturnMoneyOk
		@ResponseBody
		@RequestMapping(value = "/ReturnMoneyOk", method = RequestMethod.POST)
		public JSONObject ReturnMoneyOk(HttpServletRequest request, HttpSession session, @RequestParam Integer orderId) {
			JSONObject json = new JSONObject();
			try {
				User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
				if (null == user) {
					return Result.userSessionInvalid(json);// 返回session过期的json提示 }
				} // //Integer userId
				user.getId();
				// 查询到当前订单消息
				OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
//				System.out.println(orderInfo.getOrderStatusCode());
				if(orderInfo.getOrderStatusCode().equals("order_status_apply_return")){
				orderInfo.setOrderStatusCode("order_status_return");
				orderInfoService.updateByPrimaryKeySelective(orderInfo);
				User user1=userService.selectByPrimaryKey(orderInfo.getUserId());//获取到下单人信息
				Shop shop=shopService.selectByPrimaryKey(orderInfo.getShopId());
				User user2=userService.selectByPrimaryKey(shop.getUserId());//获取到商家负责人信息
				Date date = new Date();
				Notice notice=new Notice();
				notice.setSendTime(date);
				notice.setSenderId(orderInfo.getUserId());//发送人id
				notice.setSenderName(user1.getRealName());//发送人名
				notice.setContent(orderInfo.getRemark());//发送人的加备注说明详细信息
				notice.setReceiverGroupId(orderInfo.getShopId());//接受的店铺id
				notice.setReceiverId(shop.getUserId());//接受的店铺负责人id
				notice.setTypeCode("ShopNotice");//设置类型Code
				notice.setReceiverIds(user2.getUsername());//设置接受的店铺管理员名
				notice.setTitle("来自"+user1.getRealName()+"的一个新的确认退款消息！");
				noticeService.insert(notice);
				}
				json.put("sequence","ok");
				// json = orderInfoService.receiveGoods(user, orderId, json);
			} catch (Exception e) {
				Result.catchError(e, logger, "LH_ERROR_确认退款消息出现异常_", json);
			}
			return Result.success(json);
		}
	// /GoodsStateOk用户确认服务完成2016年8月24日14:56:25
	   @ResponseBody
	   @RequestMapping(value = "/GoodsStateOk", method = RequestMethod.POST)
	   public JSONObject GoodsStateOk(HttpServletRequest request, HttpSession session, @RequestParam Integer orderId) {
			JSONObject json = new JSONObject();
			try {
				User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
				if (null == user) {
					return Result.userSessionInvalid(json);// 返回session过期的json提示 }
				}
				//Integer userId
				user.getId();
				OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
			if(orderInfo.getOrderStatusCode().equals("shipping_status_receive")){
				orderInfo.setOrderStatusCode("shipping_status_done");
				orderInfoService.updateByPrimaryKeySelective(orderInfo);
				// 向一条技师消息
				User user1=userService.selectByPrimaryKey(orderInfo.getUserId());//获取到下单人信息
				Provider provider=providerService.selectByPrimaryKey(orderInfo.getProviderId());
				Date date = new Date();
				Notice notice=new Notice();
				notice.setSendTime(date);
				notice.setSenderId(orderInfo.getUserId());//发送人id
				notice.setSenderName(user1.getRealName());//发送人名
				notice.setTypeCode("ProviderNotice");//设置类型Code
//				notice.setContent(orderInfo.getRemark());//发送人的加备注详细信息
				notice.setReceiverGroupId(orderInfo.getShopId());//接受的店铺id
				notice.setReceiverId(provider.getId());//接受的店铺负责人id
				notice.setReceiverIds(provider.getUsername());//设置接受的技师名
				notice.setTitle("来自"+user1.getRealName()+"的一个服务确认完成消息！");
				noticeService.insert(notice);
			}
				json.put("sequence","ok");
					// json = orderInfoService.receiveGoods(user, orderId, json);
			} catch (Exception e) {
					Result.catchError(e, logger, "LH_ERROR_服务提醒出现异常_", json);
			}
			return Result.success(json);
		}
	//设置退款功能界面
		@ResponseBody
		@RequestMapping(value = "/ReturnMoneyUIApply", method = RequestMethod.POST)
		public JSONObject ReturnMoneyUIApply(HttpServletRequest request,ModelMap modelMap, @ModelAttribute OrderInfo orderinfoRQ) {
			JSONObject json = new JSONObject();
			try {
				User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
				if (null == user) {
					return Result.userSessionInvalid(json);// 返回session过期的json提示 }
				} // //Integer userId
				user.getId();
//				System.out.println("orderinfoRQ.getId()"+orderinfoRQ.getId());
				// 查询到当前订单状态并核对之后发送退款提醒消息
				OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderinfoRQ.getId());
//				System.out.println(orderInfo.getOrderStatusCode());
				//一切退款操作前判断是否付款
		if(orderInfo.getPayStatusCode().equals("pay_status_done")){
				User user1=userService.selectByPrimaryKey(orderInfo.getUserId());//获取到下单人信息
				Shop shop=shopService.selectByPrimaryKey(orderInfo.getShopId());
				User user2=userService.selectByPrimaryKey(shop.getUserId());//获取到商家负责人信息
				Date date = new Date();
				Notice notice=new Notice();
				notice.setSendTime(date);
				notice.setSenderId(orderInfo.getUserId());//发送人id
				notice.setSenderName(user1.getRealName());//发送人名
				notice.setContent(orderInfo.getRemark());//发送人的加备注说明详细信息
				notice.setReceiverGroupId(orderInfo.getShopId());//接受的店铺id
				notice.setReceiverId(shop.getUserId());//接受的店铺负责人id
				notice.setTypeCode("ShopNotice");//设置类型Code
				notice.setReceiverIds(user2.getUsername());//设置接受的店铺管理员名
				//（已付款用户）
				notice.setTitle("来自"+user1.getRealName()+"的一个新的退款申请消息！");
				// 当前状态为服务未开始显示退款
				if(orderInfo.getOrderStatusCode().equals("shipping_status_todo")){
				// 更新订单状态为申请退款
				orderInfo.setOrderStatusCode("order_status_apply_return");
				orderInfoService.updateByPrimaryKeySelective(orderInfo);
				}else if(orderInfo.getOrderStatusCode().equals("shipping_status_receive")){
				//当前状态为接受服务中显示退款
					// 更新订单状态为申请退款
					orderInfo.setOrderStatusCode("order_status_apply_return");
					orderInfo.setAttrStr(orderinfoRQ.getAttrStr());// 退款说明
					orderInfo.setAttrStr2(orderinfoRQ.getAttrStr2());// 退款凭证截图
					orderInfoService.updateByPrimaryKeySelective(orderInfo);
				}else if(orderInfo.getOrderStatusCode().equals("shipping_status_done")){
					//当前状态为服务完成显示退款
					// 更新订单状态为申请退款
					orderInfo.setOrderStatusCode("order_status_apply_return");
					orderInfo.setAttrStr(orderinfoRQ.getAttrStr());
					orderInfo.setAttrStr2(orderinfoRQ.getAttrStr2());
					orderInfoService.updateByPrimaryKeySelective(orderInfo);
				}else if(orderInfo.getOrderStatusCode().equals("order_status_todo1")){
					//当前状态为派单中显示退款
					// 更新订单状态为申请退款
					orderInfo.setOrderStatusCode("order_status_apply_return");
					orderInfoService.updateByPrimaryKeySelective(orderInfo);
					orderInfo.setAttrStr(orderinfoRQ.getAttrStr());
					orderInfo.setAttrStr2(orderinfoRQ.getAttrStr2());
				}else if(orderInfo.getOrderStatusCode().equals("order_status_cancel")){
					//当前状态为订单取消显示退款
					// 更新订单状态为申请退款
					orderInfo.setOrderStatusCode("order_status_apply_return");
					orderInfo.setAttrStr(orderinfoRQ.getAttrStr());
					orderInfo.setAttrStr2(orderinfoRQ.getAttrStr2());
					orderInfoService.updateByPrimaryKeySelective(orderInfo);
				}else if(orderInfo.getOrderStatusCode().equals("order_status_noreturn")){
					//当前状态为申请退款驳回显示再次退款
					// 更新订单状态为申请退款
					orderInfo.setOrderStatusCode("order_status_apply_return");
					orderInfo.setAttrStr(orderinfoRQ.getAttrStr());
					orderInfo.setAttrStr2(orderinfoRQ.getAttrStr2());
					orderInfoService.updateByPrimaryKeySelective(orderInfo);
				}
		}
				json.put("sequence","ok");
				// json = orderInfoService.receiveGoods(user, orderId, json);
			} catch (Exception e) {
				Result.catchError(e, logger, "LH_ERROR_服务提醒出现异常_", json);
			}
			return Result.success(json);
		}
		//设置订单取消时的状态改变（判断付款状态和当前状态来改变）
		@ResponseBody
		@RequestMapping(value = "/cancelOrderApply", method = RequestMethod.POST)
		public JSONObject cancelOrderApply(HttpServletRequest request, HttpSession session, @RequestParam Integer orderId) {
			JSONObject json = new JSONObject();
			try {
				User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
				if (null == user) {
						return Result.userSessionInvalid(json);// 返回session过期的json提示 }
				} // //Integer userId
				user.getId();
				// 查询到当前订单状态并核对之后发送退款提醒消息
				OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
//				System.out.println(orderInfo.getOrderStatusCode());
				// 当前状态为服务未开始显示退款
				//同时判断两个状态（付款和订单状态）
				if(orderInfo.getOrderStatusCode().equals("order_status_todo")&&orderInfo.getPayStatusCode().equals("pay_status_done")){
				//  已经付款取消订单显示申请退款
				orderInfo.setOrderStatusCode("order_status_apply_return");
				orderInfoService.updateByPrimaryKeySelective(orderInfo);
//				User user1=userService.selectByPrimaryKey(orderInfo.getUserId());//获取到下单人信息
//				Shop shop=shopService.selectByPrimaryKey(orderInfo.getShopId());
//				Date date = new Date();
//				Notice notice=new Notice();
//				notice.setSendTime(date);
//				notice.setSenderId(orderInfo.getUserId());//发送人id
//				notice.setSenderName(user1.getRealName());//发送人名
//				notice.setTypeCode("ProviderNotice");//设置类型Code
////			notice.setContent(orderInfo.getRemark());//发送人的加备注详细信息
//				notice.setReceiverGroupId(orderInfo.getShopId());//接受的店铺id
//				notice.setReceiverId(provider.getId());//接受的店铺负责人id
//				notice.setReceiverIds(provider.getUsername());//设置接受的技师名
//				notice.setTitle("来自"+user1.getRealName()+"的一个新的服务提醒！");
//				noticeService.insert(notice);
				}else if(orderInfo.getOrderStatusCode().equals("order_status_todo")&&orderInfo.getPayStatusCode().equals("pay_status_todo")){
				//  没付款取消订单直接设为订单结束
					orderInfo.setOrderStatusCode("order_status_invalid");
					orderInfoService.updateByPrimaryKeySelective(orderInfo);
				}
				json.put("sequence","ok");
				// json = orderInfoService.receiveGoods(user, orderId, json);
				} catch (Exception e) {
					Result.catchError(e, logger, "LH_ERROR_服务提醒出现异常_", json);
				}
					return Result.success(json);
				}
	/** 设置订单的收货地址 */
	@ResponseBody
	@RequestMapping(value = "/updateOrderAddress", method = RequestMethod.POST)
	public JSONObject updateOrderAddress(HttpServletRequest request, HttpSession session, @RequestParam Integer orderGoodsId, @RequestParam Integer addressId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示 }
														// json =
				// orderInfoService.updateOrderAddress(user, json, orderGoodsId,
				// addressId);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_为订单商品付款出现异常_", json);
		}
		return Result.success(json);
	}

	/** 付款（订单商品） */
	@ResponseBody
	@RequestMapping(value = "/payMoneyForOrderGoods", method = RequestMethod.POST)
	public JSONObject payMoneyForOrderGoods(HttpServletRequest request, HttpSession session, @RequestParam Integer orderGoodsId, @RequestParam String payPass, @RequestParam Integer addressId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示 }
			}
			// Integer userId = user.getId();
			// json = orderInfoService.payMoney(user, json, payPass,
			// orderGoodsId, addressId);
			if (json.containsKey("error_desc")) {
				json.put("status", "failure");
				return Result.success(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_为订单商品付款出现异常_", json);
		}
		return Result.success(json);
	}

	/** 发货（订单商品） */

	@ResponseBody

	@RequestMapping(value = "/sendGoods", method = RequestMethod.POST)
	public JSONObject sendGoods(HttpServletRequest request, HttpSession session, @RequestParam Integer orderGoodsId, @RequestParam Integer expressId, @RequestParam String expressOrder) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示 }
			}
			// json = orderInfoService.sendGoods(user, json, orderGoodsId,
			// expressId,expressOrder);
			if (json.containsKey("error_desc")) {
				json.put("status", "failure");
				return Result.success(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_发货出现异常_", json);
		}
		return Result.success(json);
	}

	/** 确认收货（订单商品） */
	@ResponseBody
	@RequestMapping(value = "/receiveGoods", method = RequestMethod.POST)
	public JSONObject receiveGoods(HttpServletRequest request, HttpSession session, @RequestParam Integer orderId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示 }
			} // //Integer userId =
			user.getId();
			// json = orderInfoService.receiveGoods(user, orderId, json);
			if (json.containsKey("error_desc")) {
				json.put("status", "failure");
				return Result.success(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_确认收货出现异常_", json);
		}
		return Result.success(json);
	}

	/** 取消订单（订单商品） */
	@ResponseBody
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public JSONObject cancelOrder(HttpServletRequest request, HttpSession session, @RequestParam Integer orderId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示 }
			}
			// Integer userId = user.getId();
			json = orderInfoService.cancelOrder(user, orderId, json);
			if (json.containsKey("error_desc")) {
				json.put("status", "failure");
				return Result.success(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_取消订单出现异常_", json);
		}
		return Result.success(json);
	}

	/** 申请退货（订单商品） */
	@ResponseBody
	@RequestMapping(value = "/applyReturnOrderGoods", method = RequestMethod.POST)
	public JSONObject applyReturnOrderGoods(HttpServletRequest request, HttpSession session, @RequestParam Integer orderGoodsId) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			} // Integer userId = user.getId(); json =
			orderInfoService.applyReturnOrderGoods(user, orderGoodsId, json);
			if (json.containsKey("error_desc")) {
				json.put("status", "failure");
				return Result.success(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_申请退货出现异常_", json);
		}
		return Result.success(json);
	}

	/** 处理退货（订单商品） */

	@ResponseBody
	@RequestMapping(value = "/returnOrderGoods", method = RequestMethod.POST)
	public JSONObject returnOrderGoods(HttpServletRequest request, HttpSession session, @RequestParam Integer orderGoodsId, @RequestParam Integer opt) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			// Integer userId = user.getId();
			json = orderInfoService.returnOrderGoods(user, orderGoodsId, opt, json);
			if (json.containsKey("error_desc")) {
				json.put("status", "failure");
				return Result.success(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_处理退货出现异常_", json);
		}
		return Result.success(json);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteOrderInfo", method = RequestMethod.POST)
	public JSONObject deleteOrderInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());// 验证session中的user，存在即返回
			if (null == user) {
				return Result.userSessionInvalid(json);// 返回session过期的json提示
			}
			Integer userId = user.getId();
			Integer orderId = Integer.valueOf(request.getParameter("orderId"));
			String overCommented = request.getParameter("overCommented");
			if (null != orderId) {
				OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
				Integer db_userId = orderInfo.getUserId();
				if (db_userId != userId) {
					json.put("msg", "对不起,您没有删除该订单信息的权限");
					json.put("status", "failure");
					return Result.success(json);
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderId", orderId);
					map.put("deleteOrder", 1);
					if (overCommented != null) {
						map.put("mainStatus", 2);
					} else {
						map.put("mainStatus", 3);
					}
					List<OrderGoods> orderGoodsList = orderGoodsService.selectListByCondition(map);
					if (orderGoodsList.size() > 0) {
						// OrderGoods orderGoods = orderGoodsList.get(0);
						// Integer orderGoodsId = orderGoods.getId();
						// orderGoodsService.deleteByPrimaryKey(orderGoodsId);//先删除订单商品信息
						orderInfo.setMainStatus(2);
						orderInfoService.updateByPrimaryKeySelective(orderInfo);// 再删除订单信息
						json.put("status", "success");
					} else {
						json.put("msg", "对不起,无法查询到您要删除的订单信息");
						json.put("status", "failure");
						return Result.success(json);
					}
				}
			} else {
				json.put("msg", "对不起,无法查询到您要删除的订单信息");
				json.put("status", "failure");
				return Result.success(json);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_删除用户订单信息出现异常_", json);
		}
		return Result.success(json);
	}

}
