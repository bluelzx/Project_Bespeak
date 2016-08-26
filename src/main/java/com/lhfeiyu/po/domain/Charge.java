package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Charge <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：充值表 <p>
 */
public class Charge extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userSerial;
	private String realName;
	private String userName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 用户名  */
	private String username;
	
	/** 充值日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date chargeDate;
	
	/** 充值金额  */
	private BigDecimal chargeMoney;
	
	/** 手续费  */
	private BigDecimal fee;
	
	/** 备注消息  */
	private String msg;
	
	/** 处理日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dealDate;
	
	/** 处理状态（1发起充值，2充值成功，3取消充值，4充值失败）  */
	private Integer dealStatus;
	
	/** 微信支付-公众账号ID  */
	private String appId;
	
	/** 微信支付-商户号  */
	private String mchId;
	
	/** 微信支付-随机字符串  */
	private String nonceStr;
	
	/** 微信支付-签名  */
	private String sign;
	
	/** 微信支付-预付编号  */
	private String prepayId;
	
	/** 微信支付-交易类型  */
	private String tradeType;
	
	/** 微信-用户标识-OPENID  */
	private String openId;
	
	/** 订单号  */
	private String outTradeNo;
	
	/** 直接付款的类型（1交纳保证金，2购买商品）  */
	private Integer payType;
	
	/** 订单商品ID  */
	private Integer orderGoodsId;
	
	/** 订单ID  */
	private Integer orderId;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		 this.username = username == null ? null : username.trim();
	}
	public Date getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	public BigDecimal getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(BigDecimal chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		 this.msg = msg == null ? null : msg.trim();
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public Integer getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		 this.appId = appId == null ? null : appId.trim();
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		 this.mchId = mchId == null ? null : mchId.trim();
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		 this.nonceStr = nonceStr == null ? null : nonceStr.trim();
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		 this.sign = sign == null ? null : sign.trim();
	}
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		 this.prepayId = prepayId == null ? null : prepayId.trim();
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		 this.tradeType = tradeType == null ? null : tradeType.trim();
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		 this.openId = openId == null ? null : openId.trim();
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		 this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getOrderGoodsId() {
		return orderGoodsId;
	}
	public void setOrderGoodsId(Integer orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}






























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}