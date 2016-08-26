package com.lhfeiyu.service.base;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ExpressMapper;
import com.lhfeiyu.dao.domain.GoodsLinkMapper;
import com.lhfeiyu.dao.domain.GoodsMapper;
import com.lhfeiyu.dao.domain.NoticeMapper;
import com.lhfeiyu.dao.domain.OrderGoodsMapper;
import com.lhfeiyu.dao.domain.OrderInfoMapper;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.dao.domain.UserAddressMapper;
import com.lhfeiyu.dao.domain.UserFundMapper;
import com.lhfeiyu.po.domain.Express;
import com.lhfeiyu.po.domain.Goods;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.OrderGoods;
import com.lhfeiyu.po.domain.OrderInfo;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserAddress;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.service.domain.CommonFundService;
import com.lhfeiyu.service.domain.ExpressService;
import com.lhfeiyu.service.domain.GoodsService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.service.domain.UserCustomerService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.thirdparty.kuaidi.pojo.TaskResponse;
import com.lhfeiyu.thirdparty.wx.business.Message;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：订单 OrderInfo <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseOrderInfoService")
public class BaseOrderInfoService extends CommonService<OrderInfo> {
	@Autowired
	OrderInfoMapper mapper;
	@Autowired
	OrderGoodsMapper orderGoodsMapper;
	@Autowired
	OrderInfoMapper orderInfoMapper;
	@Autowired
	ShopMapper shopMapper;
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	UserFundMapper userFundMapper;
	@Autowired
	GoodsLinkMapper goodsLinkMapper;
	@Autowired
	CommonFundService commonFundService;
	@Autowired
	UserService userService;
	@Autowired
	UserCustomerService userCustomerService;
	@Autowired
	UserAddressMapper userAddressMapper;
	@Autowired
	NoticeMapper noticeMapper;
	@Autowired
	ExpressMapper expressMapper;
	@Autowired
	ShopService shopService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ExpressService expressService;
	

	//后台加载订单列表
	public JSONObject getOrderInfoList(JSONObject json, Map<String, Object> map) {
		List<OrderInfo> orderInfoList = orderInfoMapper.selectListByCondition(map);
		Integer total = orderInfoMapper.selectCountByCondition(map);
		return Result.gridData(orderInfoList, total, json);
	}
	
	/**
	 * 新增或修改订单
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param orderInfo 订单对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateOrderInfo(JSONObject json, OrderInfo orderInfo, String username){
		if(null == orderInfo.getId()){//添加
			return addOrderInfo(json, orderInfo, username);
		}else{//修改
			return updateOrderInfo(json, orderInfo, username);
		}
	}
	
	/**
	 * 新增订单（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param orderInfo 订单对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addOrderInfo(JSONObject json, OrderInfo orderInfo, String username){
		Date date = new Date();
		OrderInfo order = new OrderInfo();
		order.setId(null);
		//根据拿到的id取到用户名
		User user = userService.selectByPrimaryKey(orderInfo.getUserId());//通过代码取到user对应数据
		order.setUserId(orderInfo.getUserId());
		order.setUsername(user.getUsername());//根据取到的数据拿到用户名
		//根据拿到的id取到店铺名
		Shop shop = shopService.selectByPrimaryKey(orderInfo.getShopId());//通过代码取到shop对应数据
		order.setShopId(orderInfo.getShopId());
		order.setShopName(shop.getName());//根据取到的数据拿到店铺名
//		System.out.println("2："+shop.getName());
		//根据拿到的id取到服务名
		Goods goods = goodsService.selectByPrimaryKey(orderInfo.getGoodsId());//通过代码取到goods对应数据
		order.setGoodsId(orderInfo.getGoodsId());
		order.setGoodsName(goods.getGoodsName());//根据取到的数据拿到服务名
//		System.out.println("3:"+goods.getGoodsName());
//		System.out.println("orderInfo:"+orderInfo);
		order.setOrderSn(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_orderInfo));
		order.setOrderStatusCode(LhConst.order_status_todo);		// 数据字典代码-订单状态：商家未接单，商家已接单，派单中，已派单，订单完成,取消订单，已结束
		order.setShippingStatusCode(LhConst.shipping_status_todo);// 数据字典代码-服务情况：服务中，服务已完成
		order.setPayStatusCode(LhConst.pay_status_todo);			// 数据字典代码-支付状态：未付款，已付款，申请退款中,已退款，拒绝退款
		order.setOrderDoneStausCode(LhConst.order_done_status_no);// 数据字典代码-订单完成大状态：未完成，完成
//		order.setMainStatus(1);// 状态（1未结束订单，2已结束订单，3无效订单）
//		order.setUsername(orderInfo.getUsername());
//		order.setProvince(orderInfo.getProvince());
//		order.setCity(orderInfo.getCity());
//		order.setAddress(orderInfo.getAddress());
//		order.setPhone(orderInfo.getPhone());
//		order.setEmail(orderInfo.getEmail());
		order.setCreatedBy(username);
		json.put("orderId", order.getId());
		order.setCreatedAt(date);
//		System.out.println("order:"+order);
		mapper.insert(order);// 新增订单
		return Result.success(json);
	}
	
	/**
	 * 修改订单（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param orderInfo 订单对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateOrderInfo(JSONObject json, OrderInfo orderInfo, String username){
		Date date = new Date();
		Integer orderInfoId = orderInfo.getId();
		if(null == orderInfoId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		orderInfo.setUpdatedBy(username);
		orderInfo.setUpdatedAt(date);
		orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
		return Result.success(json);
	}
	

	
	/**
	 * 报价同意：新增订单， 待付款：付款 - 修改订单 待发货：发货 = 修改订单 待收货：确认收货 - 修改订单（更新双方资金） 待评价：评论评价
	 * 退款、售后：退货 - 修改订单（未确认收货之前可退货同时退钱）
	 */

	/**
	 * 生成订单
	 * @param json 
	 * @param sessionUser 当前session用户
	 * @param customerId 订单所属人ID
	 * @param oi 订单对象
	 * @return JSONObject json
	 */
	public JSONObject addOrder(JSONObject json, User sessionUser, Integer customerId, OrderInfo oi) {
		Date date = new Date();
		String sessionUsername = LhConst.operater_system;
		if(null != sessionUser)sessionUsername = sessionUser.getUsername();
		OrderInfo order = new OrderInfo();
		order.setOrderSn(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_orderInfo));
		order.setUserId(customerId);
		order.setOrderStatusCode(LhConst.order_status_todo);		// 数据字典代码-订单状态：未完成
		order.setShippingStatusCode(LhConst.shipping_status_todo);// 数据字典代码-商品配送情况：未发货
		order.setPayStatusCode(LhConst.pay_status_todo);			// 数据字典代码-支付状态：未付款
		order.setOrderDoneStausCode(LhConst.order_done_status_no);// 数据字典代码-订单完成状态：未完成
		order.setMainStatus(1);// 状态（1未结束订单，2已结束订单，3无效订单）
		order.setUsername(oi.getUsername());
		order.setProvince(oi.getProvince());
		order.setCity(oi.getCity());
		order.setAddress(oi.getAddress());
		order.setPhone(oi.getPhone());
		order.setEmail(oi.getEmail());
		order.setCreatedAt(date);
		order.setCreatedBy(sessionUsername);
		mapper.insert(order);// 新增订单
		json.put("orderId", order.getId());
		return Result.success(json);
	}
	
	/**
	 * 订单商品-设置收货地址 
	 * @param json
	 * @param sessionUser
	 * @param orderGoodsId
	 * @param addressId
	 * @return json
	 */
	public JSONObject updateOrderAddress(JSONObject json, User sessionUser, Integer orderGoodsId, Integer addressId) {
		Date date = new Date();
		Map<String,Object> map = CommonGenerator.getHashMap();
		map.put("orderGoodsId", orderGoodsId);
		OrderGoods orderGoods = orderGoodsMapper.selectByCondition(map);//订单商品
		if(null == orderGoods){
			return Result.failure(json, LhTip.msg_address_orderGoods_null, LhTip.code_address_orderGoods_null);//订单商品为空
		}
		Integer customerId = orderGoods.getUserId();//订单用户ID
		if(null == customerId){
			return Result.failure(json, LhTip.msg_address_orderUser_null, LhTip.code_address_orderUser_null);//订单用户为空
		}
		Integer sessionUserId = sessionUser.getId();//订单用户ID
		String sessionUsername = sessionUser.getUsername();//订单用户名称
		
		if(Check.integerEqual(customerId, sessionUserId)){//操作权限判断
			return Result.failure(json, LhTip.msg_address_order_auth_lack, LhTip.code_address_order_auth_lack);//操作权限，创建人
		}
		
		Integer goodsId = orderGoods.getGoodsId();//商品ID
		Integer orderId = orderGoods.getOrderId();//订单ID
		OrderInfo orderInfo = mapper.selectByPrimaryKey(orderId);
		if(null == orderInfo){
			return Result.failure(json, LhTip.msg_address_order_null, LhTip.code_address_order_null);//订单为空
		}
		
		//判断发货状态，只有在商品未发货状态下才能修改地址
		if(!Check.strEqual(LhConst.shipping_status_todo, orderInfo.getShippingStatusCode())){
			return Result.failure(json, LhTip.msg_address_shipping_status_not_todo, LhTip.code_address_shipping_status_not_todo);//订单不是未发货状态
		}
		
		map.clear();
		map.put("goodsId", goodsId);
		int goodsCount = goodsMapper.selectCountByCondition(map);
		if(goodsCount <= 0){
			return Result.failure(json, LhTip.msg_address_order_goods_null, LhTip.code_address_order_goods_null);//商品为空
		}
		
		map.clear();
		map.put("userId", customerId);
		map.put("addressId", addressId);
		UserAddress userAddress = userAddressMapper.selectByCondition(map);
		if(null == userAddress){
			return Result.failure(json, LhTip.msg_address_null, LhTip.code_address_null);//收货地址为空
		}

		orderInfo.setUsername(userAddress.getReceiverName());
		orderInfo.setAddress(userAddress.getAddressDetail());
		orderInfo.setPhone(userAddress.getPhone());
		orderInfo.setProvince(userAddress.getProvinceName());
		orderInfo.setCity(userAddress.getCityName());
		orderInfo.setUpdatedAt(date);
		orderInfo.setUpdatedBy(sessionUsername);
		mapper.updateByPrimaryKeySelective(orderInfo);// 更新订单的收货地址
		
		return Result.success(json);
	}

	/** 订单商品-付款 */
	public JSONObject payMoney(JSONObject json, User sessionUser, String payPass, Integer orderId) {
		return json;
		/*
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", orderId);
		List<OrderGoods> orderGoodsList = orderGoodsMapper.selectListByCondition(map);
		if(!Check.isNotNull(orderGoodsList)){
			return Result.failure(json, LhTip.msg_pay_orderGoods_null, LhTip.code_pay_orderGoods_null);//订单商品为空
		}
		
		OrderInfo orderInfo = mapper.selectByPrimaryKey(orderId);
		if(null == orderInfo){
			return Result.failure(json, LhTip.msg_address_order_null, LhTip.code_address_order_null);//订单为空
		}
		
		for(OrderGoods orderGoods : orderGoodsList){
			
		}
		
		Integer customerId = orderInfo.getUserId();
		if(null == customerId){
			return Result.failure(json, LhTip.msg_pay_orderUser_null, LhTip.code_pay_orderUser_null);//订单用户为空
		}
		Integer sessionUserId = sessionUser.getId();
		String sessionUsername = sessionUser.getUsername();
		
		if(Check.integerEqual(customerId, sessionUserId)){//操作权限判断
			return Result.failure(json, LhTip.msg_address_order_auth_lack, LhTip.code_address_order_auth_lack);//操作权限，创建人
		}
		
		Integer goodsId = orderGoods.getGoodsId();
		Integer orderId = orderGoods.getOrderId();
		OrderInfo orderInfo = mapper.selectByPrimaryKey(orderId);
		if(null == orderInfo){
			return Result.failure(json, LhTip.msg_pay_order_null, LhTip.code_pay_order_null);
		}
		
		if(null != orderInfo.getPayStatus() && orderInfo.getPayStatus() == 3){
			return Result.failure(json, LhTip.msg_pay_repeat, LhTip.code_pay_repeat);
		}
		
		map.clear();
		map.put("goodsId", goodsId);
		List<Goods> goodsList = goodsMapper.selectListByCondition(map);
		if(!Check.isNotNull(goodsList)){
			return Result.failure(json, "该藏品已经不存在，无法付款", LhTip.code_pay_order_goods_null);
		}
		
		map.clear();
		map.put("userId", customerId);
		map.put("id", addressId);
		List<UserAddress>  userAddressList = userAddressMapper.selectListByCondition(map);
		if(!Check.isNotNull(userAddressList)){
			return Result.failure(json, "收货地址为空，无法付款", LhTip.code_pay_address_null);
		}
		UserAddress userAddress = userAddressList.get(0);
		Goods goods = goodsList.get(0);
		Date date = new Date();
		String sellerName = goods.getUsername();
		Integer sellerId = goods.getUserId();
		String linkUrl = "/cart?r=" + sessionUser.getSerial();
		String username = orderInfo.getUsername();
		BigDecimal shopPrice = orderGoods.getShopPrice();
		Integer goodsNum = orderGoods.getGoodsNumber();
		if (null == goodsNum || goodsNum <= 0)goodsNum = 1;
		BigDecimal num = new BigDecimal(goodsNum);
		BigDecimal payMoney = shopPrice.multiply(num);

		payMoney = payMoney.setScale(2, BigDecimal.ROUND_HALF_UP);// 设置保留两位小数

		// 冻结用户资金
		json = commonFundService.freezeMoney(payPass, payMoney, sessionUserId, username, 141, sellerId, sellerName, json,
				false, linkUrl);
		if (json.containsKey("error_desc") || json.containsKey("msg")) {
			json.put("status", "falure");
			return json;
		}
		orderInfo.setUsername(userAddress.getReceiverName());
		orderInfo.setAddress(userAddress.getAddressDetail());
		orderInfo.setPhone(userAddress.getPhone());
		orderInfo.setPayStatus(3);// 支付状态;1未付款;2付款中;3已付款
		orderInfo.setProvince(userAddress.getProvinceName());
		orderInfo.setCity(userAddress.getCityName());
		orderInfo.setUpdatedAt(date);
		orderInfo.setUpdatedBy(sessionUsername);
		mapper.updateByPrimaryKeySelective(orderInfo);// 更新订单的收货地址

		Notice notice = new Notice();// 给用户发消息通知
		notice.setReceiverId(sessionUserId);
		String content = "恭喜您，商品【" + goods.getGoodsName() + "】付款成功，金额：（" + payMoney.toString() + "元）";
		notice.setTitle(content);
		String serial = CommonGenerator.getSerialByDate("n");
		notice.setSerial(serial);
		notice.setLinkUrl(getLinkUrl(goodsId));
		notice.setContent(content);
		notice.setReadStatus(1);
		notice.setCreatedAt(date);
		notice.setCreatedBy(sessionUsername);
		noticeMapper.insert(notice);

		User db_user = userService.selectByPrimaryKey(sessionUserId);
		String openId = db_user.getThirdName();
		if (Check.isNotNull(openId)) {// 微信消息通知
			String firstValue = goods.getGoodsName();
			String attr1Value = payMoney.toString();
			String attr2Value = "收货人:【" + userAddress.getReceiverName() + "】手机号码:【" + userAddress.getPhone() + "】地址：【"
					+ userAddress.getProvinceName() + "-" + userAddress.getCityName() + "】" + "详细地址:【"
					+ userAddress.getAddressDetail() + "】";
			JSONObject messageJson = Message.buildMsg(openId, LhConst.wx_moban_5, LhConst.page_user_center,
					"first", firstValue, "orderProductPrice", attr1Value, "orderAddress", attr2Value, null, null, null,
					null, "remark", LhConst.wx_message_remark);
			Message.sendMessage(LhConst.wx_moban_5, messageJson);
			// JSONObject resultJson = Message.sendMessage(LhConstField.wx_moban_10,messageJson);
		}
		return Result.success(json, "恭喜您付款成功", null);
	*/}

	/** 订单商品-发货 @throws Exception */
	public JSONObject sendGoods(User sessionUser, JSONObject json, Integer orderGoodsId, Integer expressId,
			String expressOrder) throws Exception {
		Date date = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderGoodsId", orderGoodsId);
		List<OrderGoods> orderGoodsList = orderGoodsMapper.selectListByCondition(map);
		if(!Check.isNotNull(orderGoodsList)){
			return Result.failure(json, "订单商品不存在，无法发货", null);
		}
		OrderGoods orderGoods = orderGoodsList.get(0);
		Integer goodsId = orderGoods.getGoodsId();
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if (null == goods) {
			return Result.failure(json, "该藏品已经不存在，无法发货", null);
		}
		Integer orderId = orderGoods.getOrderId();
		map.clear();
		map.put("orderId", orderId);
		List<OrderInfo> orderInfoList = mapper.selectListByCondition(map);
		if (!Check.isNotNull(orderInfoList)) {
			return Result.failure(json, "订单不存在，无法发货", null);
		}
		OrderInfo orderInfo = orderInfoList.get(0);
		Integer sessionUserId = sessionUser.getId();
		//String sessionUsername = sessionUser.getUsername();
		if(orderInfo.getShopUserId().intValue() != sessionUserId.intValue()){//操作权限判断
			return Result.failure(json, "您不是该订单的卖家，无法发货", null);
		}
		
		User seller = userService.selectByPrimaryKey(sessionUserId);
		
		Integer customerId = orderInfo.getUserId();// 订单客户ID，向客户发送通知消息

		String payStatus = orderInfo.getPayStatusCode();// 支付状态;1未付款;2付款中;3已付款
		String shippingStatus = orderInfo.getShippingStatusCode();// 商品配送情况;1未发货,2已发货,3已收货,4退货
		String orderStatus = orderInfo.getOrderStatusCode();// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
		if (null == payStatus || "pay_status_todo".equals(payStatus)) {
			json.put("status", "falure");
			json.put("msg", "订单尚未付款，无法发货");
			return json;
		}
		if (null != shippingStatus && !"shipping_status_todo".equals(shippingStatus)) {
			return Result.failure(json, "订单已经发货，无法再次发货", null);
		}
		if (null != orderStatus && "order_done_status_yes".equals(orderStatus)) {
			return Result.failure(json, "订单已经结束，无法发货", null);
		}
		Express express = expressMapper.selectByPrimaryKey(expressId);
		if (null == express) {
			return Result.failure(json, "快递公司不存在，无法发货", null);
		}

		orderInfo.setOrderStatusCode(LhConst.order_status_todo);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
		orderInfo.setShippingStatusCode(LhConst.shipping_status_done);// 商品配送情况;1未发货,2已发货,3已收货,4退货
		orderInfo.setPayStatusCode(LhConst.pay_status_done);// 支付状态;1未付款;2付款中;3已付款
		orderInfo.setExpressId(expressId);
		orderInfo.setExpressCode(express.getCode());
		orderInfo.setExpressName(express.getBriefName());
		orderInfo.setExpressOrder(expressOrder);
		TaskResponse resp = null;
		//TaskResponse resp = expressService.postOrder(seller, orderId, express.getCode(), null, null, expressOrder);// 快递100接口，订单物流状态推送
		if(resp.getResult() != true){
			return Result.failure(json, resp.getMessage(), null);
		}
		orderInfo.setExpressState("0");// 快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单
		mapper.updateByPrimaryKeySelective(orderInfo);// 更新订单的状态和物流信息
		Notice notice = new Notice();// 给用户发消息通知
		notice.setReceiverId(customerId);
		String content = "卖家已发货:【" + orderInfo.getGoodsName() + "】，请注意查收";
		String serial = CommonGenerator.getSerialByDate("n");
		notice.setTitle(content);
		notice.setSerial(serial);
		notice.setLinkUrl(getLinkUrl(goodsId));
		notice.setContent(content);
		notice.setReadStatus(1);
		notice.setCreatedAt(date);
		noticeMapper.insert(notice);

		User db_user = userService.selectByPrimaryKey(customerId);
		String openId = db_user.getThirdName();
		// 微信消息通知
		if (Check.isNotNull(openId)) {
			String firstValue = content;
			String attr1Value = orderInfo.getExpressOrder();
			String attr2Value = orderInfo.getExpressName();
			String remarkValue = LhConst.wx_message_remark;
			JSONObject messageJson = Message.buildMsg(openId, LhConst.wx_moban_6, LhConst.page_user_center,
					"first", firstValue, "ordername", attr1Value, "delivername", attr2Value, null, null, null, null,
					"remark", remarkValue);
			Message.sendMessage(LhConst.wx_moban_6, messageJson);
			// JSONObject resultJson = Message.sendMessage(LhConstField.wx_moban_10,messageJson);
		}
		return Result.success(json, "操作成功", null);
	}

	public JSONObject receiveGoods(User sessionUser, Integer orderId, JSONObject json) {
		Date date = new Date();
		Integer goodsType = null;
		Integer wholesaleShopId = null;
		Integer shopIdOrWholesaleShopId = null;
		//String goodsName = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", orderId);
		List<OrderInfo> orderInfoList = orderInfoMapper.selectListByCondition(map);
		if(!Check.isNotNull(orderInfoList)){
			return Result.failure(json, "订单商品不存在，无法确认收货", null);
		}
		OrderInfo orderInfo = orderInfoList.get(0);
		Integer customerId = orderInfo.getUserId();// 订单客户ID，向客户发送通知消息
		String customerName = orderInfo.getUsername();
		
		Integer sessionUserId = sessionUser.getId();
		//String sessionUsername = sessionUser.getUsername();
		if(customerId.intValue() != sessionUserId.intValue()){//操作权限判断
			return Result.failure(json, "您不是该订单的买家，无法确认收货", null);
		}
		
		map.clear();
		map.put("orderId", orderId);
		List<OrderGoods> orderGoodsList = orderGoodsMapper.selectListByCondition(map);// 查询出该订单的所有商品
		if (!Check.isNotNull(orderGoodsList)) {
			return Result.failure(json, "该订单没有对应的商品，无法确认收货", null);
		}
		for (OrderGoods og : orderGoodsList) {
			map.clear();
			Integer goodsNum = og.getGoodsNumber();// 商品数量
			if (null == goodsNum || goodsNum <= 0) {
				goodsNum = 1;
			}
			BigDecimal shopPrice = og.getShopPrice();//商品价格
			if (null == shopPrice || shopPrice.doubleValue() <= 0) {
				return Result.failure(json, "商品单价出现异常，无法确认收货", null);
			}
			BigDecimal totalMoney = shopPrice.multiply(new BigDecimal(goodsNum));//总价格
			if (null == totalMoney || totalMoney.doubleValue() <= 0) {
				return Result.failure(json, "商品总价格出现异常，无法确认收货", null);
			}
			totalMoney = totalMoney.setScale(2, BigDecimal.ROUND_HALF_UP);// 设置保留两位小数
			
			goodsType = og.getGoodsType();//商品类型（1普通商品，2批发城商品）
			if(null == goodsType)goodsType = 1;//默认为普通商品
			//goodsName = og.getGoodsName();
			Integer shopUserId = null;// 卖家id
			String shopUsername = null;// 卖家名称
			Integer shopId = og.getShopId();
			Integer goodsId = og.getGoodsId();// 商品id
			map.clear();
			map.put("id", shopId);
			if (goodsType == 1) {// 根据商品类型查处对应的商家姓名和id
				List<Shop> shopList = shopMapper.selectListByCondition(map);
				if (Check.isNotNull(shopList)) {
					Shop shop = shopList.get(0);
					shopUserId = shop.getUserId();
					shopUsername = shop.getUserName();
				}
			}
			if (goodsType == 2) {
				/*List<Wholesale> wholesaleList = wholesaleMapper.selectListByCondition(map);
				if (Check.isNotNull(wholesaleList)) {
					Wholesale wholesale = wholesaleList.get(0);
					shopUserId = wholesale.getUserId();
					shopUsername = wholesale.getUserName();
					wholesaleShopId = wholesale.getWholesaleShopId();
				}*/
			}
			if (goodsType == 1) {
				shopIdOrWholesaleShopId = shopId;
			} else {
				shopIdOrWholesaleShopId = wholesaleShopId;
			}
			//1.实际扣买家，卖家增加
			UserFund uf = userFundMapper.selectUserFundByUserId(customerId);
			//json = commonFundService.outcomeFrozenMoneyToUserAvaliable(uf.getPayPassword(), totalMoney, customerId, customerName, 140, shopUserId, shopUsername, json, false, getLinkUrl(goodsId));
			if (json.containsKey("error_desc") || json.containsKey("msg")) {
				json.put("status", "falure");
				return json;
			}
			// 扣除卖家推广费用 - //TODO tempHide
			BigDecimal promoteBenifitMoney = og.getPromoteBenifitMoney();// 推广金额
			if (null != promoteBenifitMoney && promoteBenifitMoney.doubleValue() > 0) {
				//json = commonFundService.outcomeFrozenMoney(null, promoteBenifitMoney, shopUserId, shopUsername, 140, null, "微拍客平台", json, true, getLinkUrl(goodsId));
				if (json.containsKey("error_desc") || json.containsKey("msg")) {
					json.put("status", "falure");
					return json;
				}
			}
			// 2.更新goods、order_info、order_goods状态
			Goods goodsNew = new Goods();
			goodsNew.setId(goodsId);
			goodsNew.setMainStatus(77);
			goodsNew.setUpdatedAt(date);
			goodsNew.setUpdatedBy(customerName);
			goodsMapper.updateByPrimaryKeySelective(goodsNew);// 更新goods状态
			og.setMainStatus(2);
			og.setUpdatedAt(date);
			og.setUpdatedBy(customerName);
			orderGoodsMapper.updateByPrimaryKeySelective(og);// 更新order_goods状态
			OrderInfo oi = new OrderInfo();
			oi.setId(orderId);
			oi.setOrderStatusCode(LhConst.order_status_done);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
			oi.setShippingStatusCode(LhConst.shipping_status_receive);// 商品配送情况;1未发货,2已发货,3已收货,4退货
			oi.setMainStatus(2);//状态（1未结束订单，2已结束订单，3无效订单）
			oi.setUpdatedAt(date);
			oi.setUpdatedBy(customerName);
			mapper.updateByPrimaryKeySelective(oi);
			Integer goodsOffersId = og.getGoodsOffersId();
			if(null != goodsOffersId){
				/*GoodsOffers goodsOffers = new GoodsOffers();
				goodsOffers.setUpdatedAt(date);
				goodsOffers.setUpdatedBy(customerName);
				goodsOffers.setId(goodsOffersId);
				goodsOffers.setOfferStatus(8);//报价的状态（1未阅读，2已通知，3已阅读,4未同意，5已同意,6已生成订单,7交易中，8交易已完成,9已取消订单,10已退货）
				goodsOffersMapper.updateByPrimaryKeySelective(goodsOffers);*/
			}
			// 3.更新商家信誉，个人信誉
			//creditService.updateUserCredit(customerId);// 更新用户的信誉
			//creditService.updateShopCredit(shopIdOrWholesaleShopId, shopUserId);
			
			//4.检查是否扣平台管理费
			/*
			 * SysDict sysDict = sysDictMapper.selectByPrimaryKey(6);//查询管理费 
			 * Double fee = Double.parseDouble(sysDict.getDictValue());
			 * BigDecimal sysFee = new BigDecimal(fee);
			 * sysDictService.incomeMoney(sysFee,6, json);
			 */
			
			//TODO-tempHide-暂时关闭淘客功能
			// 5.检查商品是否有推广人，如果有推广人，更新推广人的收入（检查所有淘客相关的收入）
			//calculatePromoteMonety();
			
			//通知消息
			Notice notice = new Notice();// 给用户发消息通知
			notice.setReceiverId(shopUserId);
			String content = "买家:【"+customerName+"】已确认收货:【" + og.getGoodsName() + "】";
			notice.setTitle(content);
			String serial = CommonGenerator.getSerialByDate("n");
			notice.setSerial(serial);
			notice.setLinkUrl(getLinkUrl(goodsId));
			notice.setContent(content);
			notice.setReadStatus(1);
			notice.setCreatedAt(date);
			noticeMapper.insert(notice);

			User db_user = userService.selectByPrimaryKey(shopUserId);
			String openId = db_user.getThirdName();
			if(Check.isNotNull(openId)){
				String firstValue = content;
				String attr1Value = orderInfo.getGoodsAttr();
				String attr2Value = orderInfo.getGoodsName();
				String attr3Value = CommonGenerator.getDateStr(orderInfo.getCreatedAt());
				String attr4Value = CommonGenerator.getDateStr(orderInfo.getUpdatedAt());
				String remarkValue = LhConst.wx_message_remark;
				JSONObject messageJson = Message.buildMsg(openId, LhConst.wx_moban_7, LhConst.page_user_center, 
								 "first", firstValue, "keyword1", attr1Value, "keyword2", attr2Value, 
								 "keyword3", attr3Value, "keyword5", attr4Value, "remark", remarkValue);
				Message.sendMessage(LhConst.wx_moban_7, messageJson);//JSONObject resultJson = Message.sendMessage(LhConstField.wx_moban_10, messageJson);
			}
		}
		return Result.success(json, "操作成功", null);
	}

	//TODO-tempHide-暂时关闭淘客功能
	/*private calculatePromoteMonety(){
		map.clear();
		String userSerial = og.getPromoteUserSerial();
		User db_user = null;
		Integer db_userId = null;// 推广人id
		String db_userName = null;// 推广人姓名
		if (null != userSerial && !"".equals(userSerial)) {// 检查是否为NULL或“”，检查是否为自己
			map.put("serial", userSerial);
			List<User> userList = userService.selectListByCondition(map);
			if (null != userList) {
				if (userList.size() > 0) {
					db_user = userList.get(0);
					db_userId = db_user.getId();
					db_userName = db_user.getUsername();
				}
			}
		}
		map.clear();
		map.put("customerId", receiverId);
		List<UserCustomer> userCustomerList = userCustomerService.selectListByCondition(map);
		UserCustomer db_userCustomer = null;
		Integer db_userCustomerId = null;// 介绍人id
		String db_userCustomerName = null;// 介绍人姓名
		if (null != userCustomerList) {
			if (userCustomerList.size() > 0) {
				db_userCustomer = userCustomerList.get(0);
				db_userCustomerId = db_userCustomer.getUserId();
				db_userCustomerName = db_userCustomer.getUsername();
			}
		}
		if (null != promoteBenifitMoney) {// 判断是否有推广费
			if (promoteBenifitMoney.intValue() > 0) { // 判断是否大于0
				// 1.判断是否为自己,自己不计算收益
				if (receiverId.intValue() != db_userId.intValue()) {//判断买家和推广人是不是自己
																	
					// 2.判断他的介绍人是谁，和推广人是否是同一个人，是：70% 不是：30% 推广人：40%
					if (db_userCustomerId.equals(db_userId)) {// 介绍人和推广人是同一个人,收益70%
						BigDecimal promoteMoney = promoteBenifitMoney.multiply(new BigDecimal(0.7));// 应该是从卖家的冻结金额中转到淘客用户账户可用余额
						json = commonFundService.incomeMoney(null, promoteMoney, db_userId, db_userName, 148, 0,
								"-SYS-", json, false, getLinkUrl(goodsId));
						if (json.containsKey("error_desc") || json.containsKey("msg")) {
							json.put("status", "falure");
							return json;
						}
						// 分销收入
						commonDistributionIncome(db_userId, theOtherId, goodsName, promoteMoney);
					}
					if (!db_userCustomerId.equals(db_userId)) {// 介绍人和推广人不是同一个人,介绍人30%,// 推广人：40%
						// 推广人收益
						BigDecimal promoteMoney = promoteBenifitMoney.multiply(new BigDecimal(0.4));// 应该是从卖家的冻结金额中转到淘客用户账户可用余额
						json = commonFundService.incomeMoney(null, promoteMoney, db_userId, db_userName, 148, 0,
								"-SYS-", json, false, getLinkUrl(goodsId));
						if (json.containsKey("error_desc") || json.containsKey("msg")) {
							json.put("status", "falure");
							return json;
						}
						commonDistributionIncome(db_userId, theOtherId, goodsName, promoteMoney);// 分销收入
						// 介绍人收益
						BigDecimal promoteUserCustomerMoney = promoteBenifitMoney.multiply(new BigDecimal(0.3));// 应该是从卖家的冻结金额中转到淘客用户账户可用余额
						json = commonFundService.incomeMoney(null, promoteUserCustomerMoney, db_userCustomerId,
								db_userCustomerName, 148, 0, "-SYS-", json, false, getLinkUrl(goodsId));
						if (json.containsKey("error_desc") || json.containsKey("msg")) {
							json.put("status", "falure");
							return json;
						}
						commonDistributionIncome(db_userCustomerId, theOtherId, goodsName, promoteUserCustomerMoney);// 分销收入
					}
					BigDecimal promoteUserCustomerMoney = promoteBenifitMoney.multiply(new BigDecimal(0.3));// 平台收取收益
					sysDictService.incomeMoney(promoteUserCustomerMoney, 148, json);
				}
				if ((null == db_userId || "".equals(db_userId)) && (null == db_userCustomerId || "".equals(db_userCustomerId))) {// 没有介绍人和推广人：不扣钱(推广费)从冻结的资金激活为可用
					json = commonFundService.activateMoney(null, promoteBenifitMoney, receiverId, username, 150, 0,
							"-SYS-", json, false, getLinkUrl(goodsId));
					if (json.containsKey("error_desc") || json.containsKey("msg")) {
						json.put("status", "falure");
						return json;
					}
				}
			}
			}
	}*/
	
	 //TODO-tempHide-暂时关闭淘客功能 
	 /** private void commonDistributionIncome(Integer userId, Integer customerId, String goodsName,
			BigDecimal promoteMoney) {
		DistributionIncome distributionIncome1 = new DistributionIncome();
		Date d = new Date();
		distributionIncome1.setUserId(userId);// 介绍人,推广人
		distributionIncome1.setCustomerId(customerId);// 卖家
		distributionIncome1.setTypeId(131);
		distributionIncome1.setObjectName(goodsName);
		distributionIncome1.setDealTime(d);
		distributionIncome1.setMoneyIncome(promoteMoney);
		distributionIncome1.setMainStatus(1);
		distributionIncome1.setCreatedAt(d);
		distributionIncomeService.insert(distributionIncome1);
	}*/

	public JSONObject cancelOrder(User sessionUser, Integer orderId, JSONObject json) {
		Date date = new Date();
		Integer goodsType = null;
		Integer wholesaleShopId = null;
		Integer shopIdOrWholesaleShopId = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId", orderId);
		List<OrderInfo> orderInfoList = orderInfoMapper.selectListByCondition(map);
		if(!Check.isNotNull(orderInfoList)){
			return Result.failure(json, "订单商品不存在，无法取消订单", null);
		}
		OrderInfo orderInfo = orderInfoList.get(0);
		String username = orderInfo.getUsername();
		Integer customerId = orderInfo.getUserId();
		Integer sessionUserId = sessionUser.getId();
		//String sessionUsername = sessionUser.getUsername();
		if(customerId.intValue() != sessionUserId.intValue()){//操作权限判断
			return Result.failure(json, "您不是该订单的买家，无法取消订单", null);
		}
		map.clear();
		//Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		List<OrderGoods> orderGoodsList = orderGoodsMapper.selectListByCondition(map);// 查询出该订单的所有商品
		if (!Check.isNotNull(orderGoodsList)) {
			return Result.failure(json, "该订单没有对应的商品，无法取消订单", null);
		}
		Integer shopUserId = null;
		//String shopUsername = null;
		for (OrderGoods og : orderGoodsList) {
			map.clear();
			goodsType = og.getGoodsType();
			Integer shopId = og.getShopId();
			Integer goodsId = og.getGoodsId();
			Integer goodsOffersId = og.getGoodsOffersId();
			map.put("id", shopId);
			if (null == goodsType || goodsType == 1) {
				List<Shop> shopList = shopMapper.selectListByCondition(map);
				if (Check.isNotNull(shopList)) {
					Shop shop = shopList.get(0);
					shopUserId = shop.getUserId();
					//shopUsername = shop.getUserName();
				}
				shopIdOrWholesaleShopId = shopId;
			} else {
				/*List<Wholesale> wholesaleList = wholesaleMapper.selectListByCondition(map);
				if (Check.isNotNull(wholesaleList)) {
					Wholesale wholesale = wholesaleList.get(0);
					shopUserId = wholesale.getUserId();
					wholesaleShopId = wholesale.getWholesaleShopId();
				}
				shopIdOrWholesaleShopId = wholesaleShopId;*/
			}
			Goods goodsNew = new Goods();
			goodsNew.setId(goodsId);
			goodsNew.setMainStatus(76);// 在售商品
			goodsNew.setUpdatedAt(date);
			goodsNew.setUpdatedBy(username);
			goodsMapper.updateByPrimaryKeySelective(goodsNew);// 更新goods状态
			if(null != goodsOffersId){
				/*GoodsOffers goodsOffers = new GoodsOffers();
				goodsOffers.setUpdatedAt(date);
				goodsOffers.setUpdatedBy(username);
				goodsOffers.setId(goodsOffersId);
				goodsOffers.setOfferStatus(9);//报价的状态（1未阅读，2已通知，3已阅读,4未同意，5已同意,6已生成订单,7交易中，8交易已完成,9已取消订单,10已退货）
				goodsOffersMapper.updateByPrimaryKeySelective(goodsOffers);*/
			}
			og.setMainStatus(3);//订单商品完成状态（1未完成，2已完成，3已退货或取消，4未发货，5申请退货）
			orderGoodsMapper.updateByPrimaryKeySelective(og);// 更新order_goods状态
			
			OrderInfo oi = new OrderInfo();
			oi.setId(orderId);
			oi.setOrderStatusCode(LhConst.order_status_cancel);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
			oi.setMainStatus(3);//状态（1未结束订单，2已结束订单，3无效订单）
			//oi.setShippingStatus(3);// 商品配送情况;1未发货,2已发货,3已收货,4退货
			oi.setUpdatedAt(date);
			oi.setUpdatedBy(username);
			mapper.updateByPrimaryKeySelective(oi);// 更新order_info状态
			// 更新商家信誉，个人信誉
			//creditService.updateUserCredit(customerId);// 更新用户的信誉
			//creditService.updateShopCredit(shopIdOrWholesaleShopId, shopUserId);// 更新商家信誉
		}
		return Result.success(json, "操作成功", null);
	}

	public JSONObject applyReturnOrderGoods(User sessionUser, Integer orderGoodsId, JSONObject json){
		Date date = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderGoodsId", orderGoodsId);
		List<OrderGoods> orderGoodsList = orderGoodsMapper.selectListByCondition(map);
		if (!Check.isNotNull(orderGoodsList)) {
			return Result.failure(json, "该订单没有对应的商品，无法申请退货", null);
		}
		OrderGoods og = orderGoodsList.get(0);
		Integer orderId = og.getOrderId();
		OrderInfo orderInfo = mapper.selectByPrimaryKey(orderId);
		if (null == orderInfo) {
			return Result.failure(json, "订单不存在，无法申请退货", null);
		}
		Integer customerId = orderInfo.getUserId();
		Integer sessionUserId = sessionUser.getId();
		//String sessionUsername = sessionUser.getUsername();
		if(customerId.intValue() != sessionUserId.intValue()){//操作权限判断
			return Result.failure(json, "您不是该订单的买家，无法申请退货", null);
		}
		
		String orderStatus = orderInfo.getOrderStatusCode();
		String shippingStatus = orderInfo.getShippingStatusCode();
		Integer mainStatus = orderInfo.getMainStatus();
		
		if((null != orderStatus && "order_status_done".equals(orderStatus)) || 
		   (null != shippingStatus && "shipping_status_receive".equals(shippingStatus)) || 
		   (null != mainStatus && mainStatus == 2)
		){
			return Result.failure(json, "抱歉，您已经确认收货，无法退货", null);
		}
		
		//oi.setOrderStatus(2);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
		//oi.setShippingStatus(3);// 商品配送情况;1未发货,2已发货,3已收货,4退货
		//oi.setMainStatus(2);//状态（1未结束订单，2已结束订单，3无效订单）
		
		String expressState = orderInfo.getExpressState();//快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单
		Date receiveTime = orderInfo.getExpressReceiveTime();//快递签收日期
		
		//已签收，如果已签收，判断是否超过7天，7天后不能退货
		if(null != expressState && expressState.equals(3) && null != receiveTime){
			Calendar receiveDate = Calendar.getInstance();
			Calendar now = Calendar.getInstance();
			receiveDate.setTime(receiveTime);
			now.add(Calendar.DATE, -7);//七天之前
			if(now.after(receiveDate)){//当前日期减去七天后都还在签收日期之后，那说明已经超过七天，无法退货
				return Result.failure(json, "抱歉，至商品签收至今已经超过七天，无法退货", null);
			}
		}
		//订单商品更新为申请退货状态
		String username = orderInfo.getUsername();
		OrderInfo oi = new OrderInfo();
		oi.setId(orderId);
		oi.setOrderStatusCode(LhConst.order_status_apply_return);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
		oi.setUpdatedAt(date);
		oi.setUpdatedBy(username);
		mapper.updateByPrimaryKeySelective(oi);
		
		OrderGoods newOg = new OrderGoods();
		newOg.setId(og.getId());
		newOg.setMainStatus(5);//订单商品完成状态（1未完成，2已完成，3已退货或取消，4未发货，5申请退货）
		orderGoodsMapper.updateByPrimaryKeySelective(newOg);// 更新order_goods状态
		
		return Result.success(json, "操作成功", null);
	}
	
	public JSONObject returnOrderGoods(User sessionUser, Integer orderGoodsId, Integer opt, JSONObject json){
		Date date = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderGoodsId", orderGoodsId);
		List<OrderGoods> orderGoodsList = orderGoodsMapper.selectListByCondition(map);
		if (!Check.isNotNull(orderGoodsList)) {
			return Result.failure(json, "该订单没有对应的商品，无法退货", null);
		}
		OrderGoods og = orderGoodsList.get(0);
		Integer orderId = og.getOrderId();
		OrderInfo orderInfo = mapper.selectByPrimaryKey(orderId);
		if (null == orderInfo) {
			return Result.failure(json, "订单不存在，无法退货", null);
		}
		
		String orderStatus = orderInfo.getOrderStatusCode();
		if(null == orderStatus || !LhConst.order_status_apply_return.equals(orderStatus)){
			return Result.failure(json, "抱歉，该商品没有申请退货，无法退货", null);
		}
		
		//Integer customerId = orderInfo.getUserId();
		Integer sessionUserId = sessionUser.getId();
		//String sessionUsername = sessionUser.getUsername();
		if(orderInfo.getShopUserId().intValue() != sessionUserId.intValue() && opt == 1){//操作权限判断
			return Result.failure(json, "您不是该订单的卖家，无法退货", null);
		}
		
		//oi.setOrderStatus(2);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
		//oi.setShippingStatus(3);// 商品配送情况;1未发货,2已发货,3已收货,4退货
		//oi.setMainStatus(2);//状态（1未结束订单，2已结束订单，3无效订单）
		
		String shopUsername = "";
		Integer shopUserId = null;
		List<Shop> shopList = shopMapper.selectListByCondition(map);
		if (Check.isNotNull(shopList)) {
			Shop shop = shopList.get(0);
			shopUserId = shop.getUserId();
			shopUsername = shop.getUserName();
		}
		
		if(opt != null && opt == 2){//1同意，2拒绝
			OrderInfo oi = new OrderInfo();
			oi.setId(orderId);
			oi.setOrderStatusCode(LhConst.order_status_todo);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
			oi.setMainStatus(1);//状态（1未结束订单，2已结束订单，3无效订单）
			oi.setUpdatedAt(date);
			oi.setUpdatedBy(shopUsername);
			mapper.updateByPrimaryKeySelective(oi);// 更新order_info状态
			return Result.success(json, "您已成功取消该次退货", null);
		}
		
		if(opt != null && opt == 3){//1同意，2拒绝,3取消
			OrderInfo oi = new OrderInfo();
			oi.setId(orderId);
			oi.setOrderStatusCode(LhConst.order_status_todo);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
			oi.setMainStatus(1);//状态（1未结束订单，2已结束订单，3无效订单）
			oi.setUpdatedAt(date);
			oi.setUpdatedBy(shopUsername);
			mapper.updateByPrimaryKeySelective(oi);// 更新order_info状态
			return Result.success(json, "您已成功拒绝该次退货", null);
		}
		
		//执行退货流程：1解冻用户资金，2更改订单，订单商品，商品状态

		// 解冻用户资金
		BigDecimal money = og.getShopPrice();
		if(null == money || money.doubleValue()<0){
			return Result.failure(json, "商品价格为空，无法退货", null);
		}
		Integer userId = orderInfo.getUserId();
		String username = orderInfo.getUsername();
		//json = commonFundService.activateMoney(null, money, userId, username, 141, shopUserId, shopUsername, json, true, getLinkUrl(og.getGoodsId()));
		if (json.containsKey("error_desc") || json.containsKey("msg")) {
			json.put("status", "falure");
			return json;
		}
		
		Goods goodsNew = new Goods();
		goodsNew.setId(og.getGoodsId());
		goodsNew.setMainStatus(76);// 在售商品
		goodsNew.setUpdatedAt(date);
		goodsNew.setUpdatedBy(shopUsername);
		goodsMapper.updateByPrimaryKeySelective(goodsNew);// 更新goods状态
		Integer goodsOffersId = og.getGoodsOffersId();
		if(null != goodsOffersId){
			/*GoodsOffers goodsOffers = new GoodsOffers();
			goodsOffers.setUpdatedAt(date);
			goodsOffers.setUpdatedBy(shopUsername);
			goodsOffers.setId(goodsOffersId);
			goodsOffers.setOfferStatus(10);//报价的状态（1未阅读，2已通知，3已阅读,4未同意，5已同意,6已生成订单,7交易中，8交易已完成,9已取消订单,10已退货）
			goodsOffersMapper.updateByPrimaryKeySelective(goodsOffers);*/
		}
		og.setMainStatus(3);//订单商品完成状态（1未完成，2已完成，3已退货或取消，4未发货，5申请退货）
		orderGoodsMapper.updateByPrimaryKeySelective(og);// 更新order_goods状态
		
		OrderInfo oi = new OrderInfo();
		oi.setId(orderId);
		oi.setOrderStatusCode(LhConst.order_status_return);// 订单的状态;1未完成,2已完成,3已取消,4无效,5退货6，申请退货
		oi.setShippingStatusCode(LhConst.shipping_status_return);// 商品配送情况;1未发货,2已发货,3已收货,4退货
		oi.setMainStatus(2);//状态（1未结束订单，2已结束订单，3无效订单）
		oi.setUpdatedAt(date);
		oi.setUpdatedBy(shopUsername);
		mapper.updateByPrimaryKeySelective(oi);// 更新order_info状态
		
		//通知消息
		String title = "买家:【"+username+"】退货:【"+orderInfo.getGoodsName()+"】";
		String content = "买家:【"+username+"】退货:【"+orderInfo.getGoodsName()+"】";
		
		return Result.success(json, "操作成功", null);
	}
	
	public String getLinkUrl(Integer goodsId) {
		String linkUrl = null;
		Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if (null != goods && !"".equals(goods)) {
			Integer moduleId = goods.getModuleId();
			Integer shopId = goods.getShopId();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", shopId);
			/*if (moduleId != null) {
				List<Wholesale> wholesalesList = wholesaleMapper.selectListByCondition(map);
				if (wholesalesList.size() > 0) {
					Wholesale wholesales = wholesalesList.get(0);
					Integer wholesalesUserId = wholesales.getUserId();
					String userSerial = wholesales.getUserSerial();
					linkUrl = "/wsg/" + goodsId + "/" + shopId + "/" + wholesalesUserId + "?moduleId=" + moduleId
							+ "&r=" + userSerial;
				}
			} else {
				List<Shop> shopList = shopMapper.selectListByCondition(map);
				if (shopList.size() > 0) {
					Shop shop = shopList.get(0);
					String userSerial = shop.getUserSerial();
					linkUrl = "/goods/" + goodsId + "?shopId=" + shopId + "&r=" + userSerial;
				}
			}*/
		}
		return linkUrl;
	}

}
