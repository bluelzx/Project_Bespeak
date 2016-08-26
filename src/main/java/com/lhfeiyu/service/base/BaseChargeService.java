package com.lhfeiyu.service.base;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ChargeMapper;
import com.lhfeiyu.dao.domain.OrderGoodsMapper;
import com.lhfeiyu.dao.domain.OrderInfoMapper;
import com.lhfeiyu.dao.domain.UserMapper;
import com.lhfeiyu.domain.Trade;
import com.lhfeiyu.po.domain.Charge;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.po.domain.OrderGoods;
import com.lhfeiyu.po.domain.OrderInfo;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.service.domain.CommonFundService;
import com.lhfeiyu.service.domain.ShopService;
import com.lhfeiyu.service.domain.UserFundService;
import com.lhfeiyu.thirdparty.wx.business.Message;
import com.lhfeiyu.thirdparty.wx.util.CommonUtil;
import com.lhfeiyu.thirdparty.wx.util.ConfigUtil;
import com.lhfeiyu.thirdparty.wx.util.MessageUtil;
import com.lhfeiyu.thirdparty.wx.util.PayCommonUtil;
import com.lhfeiyu.thirdparty.wx.util.Sign;
import com.lhfeiyu.thirdparty.wx.util.XMLUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：充值 Charge <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseChargeService")
public class BaseChargeService extends CommonService<Charge> {

	@Autowired
	ChargeMapper chargeMapper;
	@Autowired
	CommonFundService cfService;
	@Autowired
	ShopService shopService;
	@Autowired
	OrderGoodsMapper orderGoodsMapper;
	@Autowired
	OrderInfoMapper orderInfoMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserFundService ufService;
	
	public JSONObject getChargeList(JSONObject json, Map<String, Object> map) {
		List<Charge> chargeList = chargeMapper.selectListByCondition(map);
		Integer total = chargeMapper.selectCountByCondition(map);
		return Result.gridData(chargeList, total, json);
	}
	
	/**
	 * 新增或修改充值
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param charge 充值对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateCharge(JSONObject json, Charge charge, String username){
		/*String content = charge.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == charge.getId()){//添加
			return addCharge(json, charge, username);
		}else{//修改
			return updateCharge(json, charge, username);
		}
	}
	
	/**
	 * 新增充值（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param charge 充值对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addCharge(JSONObject json, Charge charge, String username){
		Date date = new Date();
		charge.setId(null);
		charge.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_charge));
		charge.setMainStatus(1);
		charge.setCreatedBy(username);
		charge.setCreatedAt(date);
		chargeMapper.insert(charge);
		json.put("id", charge.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改充值（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param charge 充值对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateCharge(JSONObject json, Charge charge, String username){
		Date date = new Date();
		Integer chargeId = charge.getId();
		if(null == chargeId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Charge dbCharge = chargeMapper.selectByPrimaryKey(chargeId);
		if(null == dbCharge){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		charge.setUpdatedBy(username);
		charge.setUpdatedAt(date);
		chargeMapper.updateByPrimaryKeySelective(charge);
		return Result.success(json);
	}
	

	/** 执行充值 @throws Exception */
	public JSONObject doWxPay(JSONObject json, User user, String openId, 
			String spbill_create_ip, String userAgent, Double money, 
			String notify_url, Integer payType, Integer orderGoodsId) throws Exception{
		Date date = new Date();
		Integer userId = user.getId();
		String username = user.getUsername();
		if(null != payType && payType == 2){//payType:1直接支付诚信金，2直接支付订单商品金额，3支付红包
			if(null == orderGoodsId){
				return Result.failure(json, LhTip.msg_pay_orderGoods_null, LhTip.code_pay_orderGoods_null);
			}
			OrderGoods og = orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
			if(null == og){
				return Result.failure(json, LhTip.msg_pay_orderGoods_null, LhTip.code_pay_orderGoods_null);
			}
			BigDecimal shopPrice = og.getShopPrice();
			if(null != shopPrice && shopPrice.doubleValue() >= 0){
				money = shopPrice.doubleValue();
			}else{
				return Result.failure(json, LhTip.msg_pay_orderGoods_price_invalid, LhTip.code_pay_orderGoods_price_invalid);
			}
		}
		if(null == money || money <= 0){
			return Result.failure(json, LhTip.msg_price_wrong,  LhTip.code_price_wrong);
		}

		String usernameAndSerial = "用户："+username;//+" - 用户编号："+userId
		String body = "微拍客-微信支付-" + usernameAndSerial;			//内容描述
		String out_trade_no = Sign.create_timestamp()+"_"+user.getSerial();//订单号
		String nonce_str = Sign.create_nonce_str().substring(0,32);				//随机字符串
		String timeStamp = Sign.create_timestamp();						//时间
		
		Double moneyFen = money * 100;//充值金额（以分为单位）,所以需要乘以100
		
		String total_fee = moneyFen.toString();	//充值金额（以分为单位）
		
		//TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     TODO 测试     
		total_fee = "1";
		
		if(Check.isNull(notify_url))notify_url = ConfigUtil.NOTIFY_URL;
		
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", ConfigUtil.APPID);
		parameters.put("body", body);
		parameters.put("mch_id", ConfigUtil.MCH_ID);
		parameters.put("nonce_str", nonce_str);
		parameters.put("notify_url", notify_url);//微信支付统一接口的回调地址
		parameters.put("openid", openId);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("spbill_create_ip", spbill_create_ip);
		parameters.put("total_fee", total_fee);
		parameters.put("trade_type", "JSAPI");

		String orderSign = PayCommonUtil.createSign("UTF-8", parameters);

		parameters.put("sign", orderSign);
		String requestXML = PayCommonUtil.getRequestXml(parameters);
		String result =CommonUtil.httpsRequestForStr(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
		Map<String, String> map = MessageUtil.parseXml(result);
		String prepayId = map.get("prepay_id");//订单预付编号
		
		SortedMap<Object,Object> params = new TreeMap<Object,Object>();
        params.put("appId", ConfigUtil.APPID);
        params.put("timeStamp", timeStamp);
        params.put("nonceStr", nonce_str);
        params.put("package", "prepay_id="+prepayId);
        params.put("signType", ConfigUtil.SIGN_TYPE);
        String paySign =  PayCommonUtil.createSign("UTF-8", params);//paySign的生成规则和Sign的生成规则一致
        
        if( null == prepayId || "".equals(prepayId) || null == paySign || "".equals(paySign) ){
			json.put("reload", 1);
			return Result.failure(json, LhTip.msg_wx_pay_auth_failure, LhTip.code_wx_pay_auth_failure);
        }
        Charge c = new Charge();
        c.setUserId(userId);
        c.setUsername(username);
        c.setPayType(payType);//直接付款对应的付款类型,，1交纳保证金，2购买商品
        c.setOrderGoodsId(orderGoodsId);
        c.setOutTradeNo(out_trade_no);//订单号
        c.setChargeMoney(new BigDecimal(money));//money:单位-元
        c.setChargeDate(date);
        c.setAppId(ConfigUtil.APPID);
        c.setMchId(ConfigUtil.MCH_ID);
        c.setNonceStr(nonce_str);
        c.setPrepayId(prepayId);
        c.setSign(orderSign);
        c.setTradeType("JSAPI");
        c.setOpenId(openId);
		c.setDealStatus(1);//处理状态（1发起充值，2充值成功，3取消充值，4充值失败）
		c.setCreatedAt(date);
		c.setCreatedBy(LhConst.operater_system);
		chargeMapper.insertSelective(c);//添加充值记录：默认状态为1：发起充值
		json.put("chargeId", c.getId());
		json.put("out_trade_no", out_trade_no);
        json.put("appId", ConfigUtil.APPID);
        json.put("timeStamp", timeStamp);
        json.put("nonceStr", nonce_str);
        json.put("package", "prepay_id="+prepayId);
        json.put("packageValue", "prepay_id="+prepayId);   		//这里用packageValue是预防package是关键字在js获取值出错
        json.put("signType", ConfigUtil.SIGN_TYPE);
        json.put("paySign", paySign);   									//paySign的生成规则和Sign的生成规则一致
        json.put("sendUrl", ConfigUtil.SUCCESS_URL);    					//付款成功后跳转的页面
        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
        json.put("agent", new String(new char[]{agent}));					//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
        json.put("status", "success");
		return json;
	
	}
	
	public String chargeSuccessNotify(InputStream inStream, int optType) throws Exception{
		Date date = new Date();
	    ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int len = 0;
	    while ((len = inStream.read(buffer)) != -1) {
	        outSteam.write(buffer, 0, len);
	    }
	    outSteam.close();
	    inStream.close();
	    String result  = new String(outSteam.toByteArray(),"utf-8");//获取微信调用我们notify_url的返回信息
	    Map<Object, Object> resultMap = XMLUtil.doXMLParse(result);
	    String return_code = resultMap.get("return_code").toString();
	    String result_code = resultMap.get("result_code").toString();
	    if (!return_code.equalsIgnoreCase("SUCCESS")) {
	    	return XMLUtil.setXML("FAIL", "return_code验证失败");   //告诉微信服务器，验证失败
	    }
	    if (!result_code.equalsIgnoreCase("SUCCESS")) {
	    	return XMLUtil.setXML("FAIL", "result_code验证失败");   //告诉微信服务器，验证失败
	    }
	    String appid = resultMap.get("appid").toString();
	    String mch_id = resultMap.get("mch_id").toString();
	    String nonce_str = resultMap.get("nonce_str").toString();
	    String out_trade_no = resultMap.get("out_trade_no").toString();
	    String openid = resultMap.get("openid").toString();
	    String trade_type = resultMap.get("trade_type").toString();
	    String transaction_id = resultMap.get("transaction_id").toString();
	    
	    if(!Check.isNotNull(appid) || !Check.isNotNull(mch_id) || !Check.isNotNull(nonce_str)
	    || !Check.isNotNull(out_trade_no) || !Check.isNotNull(trade_type)){
	    	return XMLUtil.setXML("FAIL", "参数不足");   //告诉微信服务器，验证失败
	    }
	    
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("appid", appid);
		map.put("mch_id", mch_id);
		map.put("nonce_str", nonce_str);
		map.put("out_trade_no", out_trade_no);
		map.put("openid", openid);
		map.put("trade_type", trade_type);
		List<Charge> chargeList = chargeMapper.selectListByCondition(map);//根据微信返回的这几个字段来判断是否为合法的请求
		if(!Check.isNotNull(chargeList)){
			return XMLUtil.setXML("FAIL", "该订单不存在");   //告诉微信服务器，验证失败
		}
		Charge charge = chargeList.get(0);
		Double money = charge.getChargeMoney().doubleValue();
		Integer dealStatus = charge.getDealStatus();
		if(null == dealStatus)dealStatus = 1;
		int status = dealStatus.intValue();
		charge.setSerial(transaction_id);//将交易号设置为序号
		if(status != 1){
			return XMLUtil.setXML("FAIL", "订单状态不正确");   //告诉微信服务器，验证失败
		}
		JSONObject json = null;
		if(optType == 1){
			json = chargeSuccess(money, charge, date);//充值-验证通过，修改支付状态并增加账户金额
		}else if(optType == 2){
			json = paySuccess(money, charge, date);//直接支付-验证通过，修改支付状态并增加账户金额
		}
		if(json.containsKey("error_desc")){
			return XMLUtil.setXML("FAIL", "出现异常");   //告诉微信服务器，验证失败
		}
		return XMLUtil.setXML("SUCCESS", "");   //支付成功 告诉微信服务器，我收到信息了，不要在调用回调action了
	}
		
	private JSONObject chargeSuccess(Double money, Charge charge, Date date){
		String openId = charge.getOpenId();
		String username = charge.getUserName();
		
		if(null == username)username = "___";
		Charge c = new Charge();
		c.setId(charge.getId());
		c.setDealDate(date);
		c.setDealStatus(2);//充值成功
		c.setUpdatedAt(date);
		c.setUpdatedBy("-SYS-");
		chargeMapper.updateByPrimaryKeySelective(c);//更新充值记录
		System.out.println("ChargeService:chargeSuccess: "+username);
		//TODO 执行充值145:充值
		JSONObject json = new JSONObject();
		//String linkUrl = "/user";
		//TODO 修改
		//json = commonFundService.incomeMoney(null, new BigDecimal(money), charge.getUserId(), charge.getUsername(), 145, 0, "平台", json, false,linkUrl);
		User user = userMapper.selectByPrimaryKey(charge.getUserId());
		Integer userId = user.getId();
		String userPhone = user.getPhone();
		
		Trade trade = new Trade();
		trade.setUserId(userId);
		trade.setPhone(userPhone);
		trade.setUsername(username);
		trade.setMoney(new BigDecimal(money));
		//trade.setFundOptLevelCode(fundOptLevelCode);
		//trade.setPayPass(payPass);
		trade.setInOrOut(2);
		trade.setPayTime(date);
		trade.setTradeUserId(userId);
		trade.setTradeUsername(username);
		trade.setTradeUserPhone(userPhone);
		trade.setTradeStatusCode("trade_status_done");
		trade.setCreatedBy(username);
		Notice notice = new Notice();
		cfService.incomeMoney(json, trade, notice, username);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
				
		//充值-增加资金成功
		if(Check.isNotNull(openId)){
			String firstValue = "您好，您已充值成功。";
			String attr1Value = "会员";
			String attr2Value = username;
			String attr3Value = String.valueOf(money)+"元";
			String attr4Value = "充值成功。";
			String remarkValue = LhConst.wx_message_remark;
			JSONObject messageJson = Message.buildMsg(openId, LhConst.wx_moban_10, LhConst.page_user_center, 
							 "first", firstValue, "accountType", attr1Value, "account", attr2Value, 
							 "amount", attr3Value, "result", attr4Value, "remark", remarkValue);
			System.out.println("ChargeService:chargeSuccess_messageJson: "+messageJson);
			Message.sendMessage(LhConst.wx_moban_10, messageJson);//JSONObject resultJson = Message.sendMessage(LhConstField.wx_moban_10, messageJson);
		}
		return json;
	}
	
	private JSONObject paySuccess(Double money, Charge charge, Date date){
		//String openId = charge.getOpenId();
		String username = charge.getUserName();
		if(null == username)username = "___";
		Integer chargeId = charge.getId();
		Charge c = new Charge();
		c.setId(chargeId);
		c.setDealDate(date);
		c.setDealStatus(2);//充值成功
		c.setUpdatedAt(date);
		c.setUpdatedBy("-SYS-");
		chargeMapper.updateByPrimaryKeySelective(c);//更新充值记录
		System.out.println("ChargeService:paySuccess: "+username);
		//TODO 执行充值145:充值
		JSONObject json = new JSONObject();
		//String linkUrl = "/user";
		//TODO 修改
		//json = commonFundService.incomeMoney(null, new BigDecimal(money), charge.getUserId(), charge.getUsername(), 145, 0, "平台", json, false,linkUrl);
		//json = commonFundService.freezeMoney(null, new BigDecimal(money), charge.getUserId(), charge.getUsername(), 151, null, "平台冻结", json, true,linkUrl);
		User user = userMapper.selectByPrimaryKey(charge.getUserId());
		Integer userId = user.getId();
		String userPhone = user.getPhone();
		
		Trade trade = new Trade();
		trade.setUserId(userId);
		trade.setPhone(userPhone);
		trade.setUsername(username);
		trade.setMoney(new BigDecimal(money));
		//trade.setFundOptLevelCode(fundOptLevelCode);
		//trade.setPayPass(payPass);
		trade.setInOrOut(2);
		trade.setPayTime(date);
		trade.setTradeUserId(userId);
		trade.setTradeUsername(username);
		trade.setTradeUserPhone(userPhone);
		trade.setTradeStatusCode("trade_status_done");
		trade.setCreatedBy(username);
		Notice notice = new Notice();
		trade.setFundOptLevelCode(LhConst.fund_opt_level_sys);//系统操作，不需要支付密码
		cfService.incomeMoney(json, trade, notice, username);//直接支付：先充值，然后冻结
		cfService.freezeMoney(json, trade, notice, username);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		c = chargeMapper.selectByPrimaryKey(chargeId);
		if(null == c){
			return Result.failure(json, "充值记录不存在", "charge_null");
		}
		Integer payType = c.getPayType();//占用:支付类型
		if(null == payType){
			return Result.failure(json, LhTip.msg_pay_payType_null,  LhTip.code_pay_payType_null);
		}
		//Charge oldCharge = chargeMapper.selectByPrimaryKey(chargeId);
		if(payType == 1){//交纳信誉保证金
			Integer chargerId = c.getUserId();
			System.out.println("paySuccess:payType==1:money: "+ money+" chargerId: "+chargerId);
			UserFund uf = cfService.selectUserFundByUserId(chargerId);
			BigDecimal creditMoneyBD = uf.getOtherFund();//otherFund:诚信保证金
			if(null == creditMoneyBD)creditMoneyBD = new BigDecimal(0);
			double creditMoney = creditMoneyBD.doubleValue();
			creditMoney += money;
			UserFund newUF = new UserFund();
			newUF.setId(uf.getId());
			newUF.setOtherFund(new BigDecimal(creditMoney));
			ufService.updateMoneyById(newUF);
			
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", chargerId);
			Shop shop = shopService.selectByCondition(map);
			if(null == shop){
				return Result.failure(json, LhTip.msg_pay_shop_null,  LhTip.code_pay_shop_null);
			}
			Integer creditMoney = shop.getCreditMargin();
			if(null == creditMoney)creditMoney = 0;
			creditMoney += money;
			Shop newShop = new Shop();
			newShop.setId(shop.getId());
			newShop.setCreditMargin(creditMoney);
			shopService.updateByPrimaryKeySelective(shop);*/
		}else if(payType == 2){//支付订单商品
			Integer orderGoodsId = c.getOrderGoodsId();
			if(null == orderGoodsId){
				return Result.failure(json, LhTip.msg_pay_orderGoods_null, LhTip.code_pay_orderGoods_null);
			}
			OrderGoods og = orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
			if(null == og){
				return Result.failure(json, LhTip.msg_pay_orderGoods_null, LhTip.code_pay_orderGoods_null);
			}
			Integer orderId = og.getOrderId();
			if(null != orderId){
				OrderInfo order = new OrderInfo();
				order.setId(orderId);
				order.setPayStatusCode(LhConst.pay_status_done);//已付款
				orderInfoMapper.updateByPrimaryKeySelective(order);
			}
			
		}else if(payType == 3){//支付红包
			
		}
		
		//充值-增加资金成功
		/*if(Check.isNotNull(openId)){
			String firstValue = "您好，您已充值成功。";
			String attr1Value = "会员";
			String attr2Value = username;
			String attr3Value = String.valueOf(money)+"元";
			String attr4Value = "充值成功。";
			String remarkValue = LhConstField.wx_message_remark;
			JSONObject messageJson = Message.buildMsg(openId, LhConstField.wx_moban_10, LhConstField.page_user_center, 
							 "first", firstValue, "accountType", attr1Value, "account", attr2Value, 
							 "amount", attr3Value, "result", attr4Value, "remark", remarkValue);
			System.out.println("ChargeService:chargeSuccess_messageJson: "+messageJson);
			Message.sendMessage(LhConstField.wx_moban_10, messageJson);//JSONObject resultJson = Message.sendMessage(LhConstField.wx_moban_10, messageJson);
		}*/
		return json;
	}
	
	public JSONObject payCreditMoneyByAccount(JSONObject json, User user, String payPass, Double money){
		user = userMapper.selectByPrimaryKey(user.getId());
		Date date = new Date();
		Integer userId = user.getId();
		String username = user.getUsername();
		String userPhone = user.getPhone();
		
		if(Check.isNull(payPass)){
			return Result.failure(json, "请输入您的支付密码", "payPass_null");
		}
		if(null == money || money <= 0){
			return Result.failure(json, "您的支付金额不正确", "money_null");
		}
		
		Trade trade = new Trade();
		trade.setUserId(userId);
		trade.setPhone(userPhone);
		trade.setUsername(username);
		trade.setMoney(new BigDecimal(money));
		//trade.setFundOptLevelCode(fundOptLevelCode);
		//trade.setPayPass(payPass);
		trade.setInOrOut(2);
		trade.setPayTime(date);
		trade.setTradeUserId(userId);
		trade.setTradeUsername(username);
		trade.setTradeUserPhone(userPhone);
		trade.setTradeStatusCode("trade_status_done");
		trade.setCreatedBy(username);
		trade.setPayPass(payPass);
		Notice notice = new Notice();
		cfService.freezeMoney(json, trade, notice, username);
		if(Result.hasError(json))return json;//如果出现错误，直接返回错误信息
		UserFund uf = cfService.selectUserFundByUserId(userId);
		BigDecimal creditMoneyBD = uf.getOtherFund();//otherFund:诚信保证金
		if(null == creditMoneyBD)creditMoneyBD = new BigDecimal(0);
		double creditMoney = creditMoneyBD.doubleValue();
		creditMoney += money;
		UserFund newUF = new UserFund();
		newUF.setId(uf.getId());
		newUF.setOtherFund(new BigDecimal(creditMoney));
		ufService.updateMoneyById(newUF);
			
		return json;
	}
	

	
}
