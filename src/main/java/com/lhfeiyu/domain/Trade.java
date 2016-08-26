package com.lhfeiyu.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务模型层：交易相关字段 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class Trade {
	
	private Integer id;
	private Integer userId;
	private String username;
	private String phone;
	private String tel;
	private String email;
	
	/** 交易对方ID */
	private Integer tradeUserId;
	/** 交易对方名称 */
	private String tradeUsername;
	/** 交易对方手机  */
	private String tradeUserPhone;
	/** 交易对方电话  */
	private String tradeUserTel;
	/** 交易对方邮箱  */
	private String tradeUserEmail;
	
	/** 交易对方ID */
	private Integer theOtherId;
	/** 交易对方名称 */
	private String theOtherType;
	/** 交易对方类型 */
	private String theOtherName;
	
	/** 数据字典代码：交易状态 */
	private String tradeStatusCode;
	/** 数据字典代码：交易类型 */
	private String tradeTypeCode;
	/** 数据字典代码：资金操作类型 */
	private String fundOptTypeCode;
	/** 数据字典代码：资金操作等级 */
	private String fundOptLevelCode;
	/** 数据字典代码：支付状态 */
	private String payStatusCode;
	/** 数据字典代码：订单状态 */
	private String orderStatusCode;
	/** 数据字典代码：商品配送情况 */
	private String shippingStatusCode;
	/** 数据字典代码：快递状态 */
	private String expressStatusCode;
	/** 数据字典代码：支付方式 */
	private String payWayCode;
	/** 数据字典代码：通知类型 */
	private String noticeTypeCode;
	/** 数据字典代码：日志记录类型 */
	private String logTypeCode;
	/** 数据字典代码：冻结资金状态 */
	private String frozenMoneyStatusCode;
	/** 数据字典代码：交结资金类型 */
	private String frozenTypeCode;
	/** 用户资金冻结记录ID */
	private Integer userFundFrozenId;
	/** 用户资金冻结记录序号 */
	private String userFundFrozenSerial;
	
	/** 收入或支出：1收入，2支出，3总金额不变 */
	private Integer inOrOut;
	/** 支付密码 */
	private String payPass;
	/** 支付时间 */
	private Date payTime;
	/** 关联地址 */
	private String linkUrl;
	/** 交易金额  */
	private BigDecimal money;
	/** 可用金额  */
	private BigDecimal avaliableMoney;
	/** 冻结金额  */
	private BigDecimal frozenMoney;
	/** 可用金额记录  */
	private BigDecimal avaliableMoneyLog;
	/** 冻结金额记录 */
	private BigDecimal frozenMoneyLog;
	/** 描述 */
	private String description;
	/** 备注 */
	private String remark;
	/** 创建人 */
	private String createdBy;
	/** 修改人 */
	private String updatedBy;
	
	public Trade switchUser(){
		Integer userId = this.getUserId();
		String username = this.getUsername();
		String email = this.getEmail();
		String tel = this.getTel();
		String phone = this.getPhone();
		
		this.setUserId(this.getTradeUserId());
		this.setUsername(this.getTradeUsername());
		this.setEmail(this.getTradeUserEmail());
		this.setTel(this.getTradeUserTel());
		this.setPhone(this.getTradeUserPhone());
		
		this.setTradeUserId(userId);
		this.setTradeUsername(username);
		this.setTradeUserEmail(email);
		this.setTradeUserTel(tel);
		this.setTradeUserPhone(phone);
		
		return this;
		
	}
	
	public String toString(){
		return JSONObject.toJSONString(this);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTradeUserId() {
		return tradeUserId;
	}
	public void setTradeUserId(Integer tradeUserId) {
		this.tradeUserId = tradeUserId;
	}
	public String getTradeUsername() {
		return tradeUsername;
	}
	public void setTradeUsername(String tradeUsername) {
		this.tradeUsername = tradeUsername;
	}
	public String getTradeUserPhone() {
		return tradeUserPhone;
	}
	public void setTradeUserPhone(String tradeUserPhone) {
		this.tradeUserPhone = tradeUserPhone;
	}
	public String getTradeUserTel() {
		return tradeUserTel;
	}
	public void setTradeUserTel(String tradeUserTel) {
		this.tradeUserTel = tradeUserTel;
	}
	public String getTradeUserEmail() {
		return tradeUserEmail;
	}
	public void setTradeUserEmail(String tradeUserEmail) {
		this.tradeUserEmail = tradeUserEmail;
	}
	public String getTradeStatusCode() {
		return tradeStatusCode;
	}
	public void setTradeStatusCode(String tradeStatusCode) {
		this.tradeStatusCode = tradeStatusCode;
	}
	public String getTradeTypeCode() {
		return tradeTypeCode;
	}
	public void setTradeTypeCode(String tradeTypeCode) {
		this.tradeTypeCode = tradeTypeCode;
	}
	public String getFundOptTypeCode() {
		return fundOptTypeCode;
	}
	public void setFundOptTypeCode(String fundOptTypeCode) {
		this.fundOptTypeCode = fundOptTypeCode;
	}
	public String getFundOptLevelCode() {
		return fundOptLevelCode;
	}
	public void setFundOptLevelCode(String fundOptLevelCode) {
		this.fundOptLevelCode = fundOptLevelCode;
	}
	public String getPayStatusCode() {
		return payStatusCode;
	}
	public void setPayStatusCode(String payStatusCode) {
		this.payStatusCode = payStatusCode;
	}
	public String getOrderStatusCode() {
		return orderStatusCode;
	}
	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
	public String getShippingStatusCode() {
		return shippingStatusCode;
	}
	public void setShippingStatusCode(String shippingStatusCode) {
		this.shippingStatusCode = shippingStatusCode;
	}
	public String getExpressStatusCode() {
		return expressStatusCode;
	}
	public void setExpressStatusCode(String expressStatusCode) {
		this.expressStatusCode = expressStatusCode;
	}
	public String getPayWayCode() {
		return payWayCode;
	}
	public void setPayWayCode(String payWayCode) {
		this.payWayCode = payWayCode;
	}
	public String getNoticeTypeCode() {
		return noticeTypeCode;
	}
	public void setNoticeTypeCode(String noticeTypeCode) {
		this.noticeTypeCode = noticeTypeCode;
	}
	public String getLogTypeCode() {
		return logTypeCode;
	}
	public void setLogTypeCode(String logTypeCode) {
		this.logTypeCode = logTypeCode;
	}
	public Integer getUserFundFrozenId() {
		return userFundFrozenId;
	}

	public void setUserFundFrozenId(Integer userFundFrozenId) {
		this.userFundFrozenId = userFundFrozenId;
	}

	public String getUserFundFrozenSerial() {
		return userFundFrozenSerial;
	}

	public void setUserFundFrozenSerial(String userFundFrozenSerial) {
		this.userFundFrozenSerial = userFundFrozenSerial;
	}

	public Integer getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(Integer inOrOut) {
		this.inOrOut = inOrOut;
	}
	public String getPayPass() {
		return payPass;
	}
	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getAvaliableMoney() {
		return avaliableMoney;
	}
	public void setAvaliableMoney(BigDecimal avaliableMoney) {
		this.avaliableMoney = avaliableMoney;
	}
	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	public BigDecimal getAvaliableMoneyLog() {
		return avaliableMoneyLog;
	}
	public void setAvaliableMoneyLog(BigDecimal avaliableMoneyLog) {
		this.avaliableMoneyLog = avaliableMoneyLog;
	}
	public BigDecimal getFrozenMoneyLog() {
		return frozenMoneyLog;
	}
	public void setFrozenMoneyLog(BigDecimal frozenMoneyLog) {
		this.frozenMoneyLog = frozenMoneyLog;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getTheOtherId() {
		return theOtherId;
	}

	public void setTheOtherId(Integer theOtherId) {
		this.theOtherId = theOtherId;
	}

	public String getTheOtherType() {
		return theOtherType;
	}

	public void setTheOtherType(String theOtherType) {
		this.theOtherType = theOtherType;
	}

	public String getTheOtherName() {
		return theOtherName;
	}

	public void setTheOtherName(String theOtherName) {
		this.theOtherName = theOtherName;
	}

	public String getFrozenMoneyStatusCode() {
		return frozenMoneyStatusCode;
	}

	public void setFrozenMoneyStatusCode(String frozenMoneyStatusCode) {
		this.frozenMoneyStatusCode = frozenMoneyStatusCode;
	}

	public String getFrozenTypeCode() {
		return frozenTypeCode;
	}

	public void setFrozenTypeCode(String frozenTypeCode) {
		this.frozenTypeCode = frozenTypeCode;
	}
	
		
			
}
	