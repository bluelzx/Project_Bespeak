package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：AccountLog <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：交易记录表 <p>
 */
public class AccountLog extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private BigDecimal avaliableMoney;
	private BigDecimal frozenMoney;
	private String dictName;
	private String receiveUserSerial;
	private String payUserSerial;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 用户名  */
	private String username;
	
	/** 用户邮箱  */
	private String email;
	
	/** 用户手机  */
	private String phone;
	
	/** 用户座机  */
	private String tel;
	
	/** 折扣  */
	private BigDecimal discount;
	
	/** 金额  */
	private BigDecimal money;
	
	/** 单位  */
	private String unit;
	
	/** 收入或支出(1，收入2，支出，3总金额不变)  */
	private Integer inOrOut;
	
	/** 交易类型代码  */
	private String tradeTypeCode;
	
	/** 交易状态代码  */
	private String tradeStatusCode;
	
	/** 资金操作类型代码  */
	private String fundOptTypeCode;
	
	/** 资金操作等级代码  */
	private String fundOptLevelCode;
	
	/** 通知类型代码  */
	private String noticeTypeCode;
	
	/** 日志记录类型代码  */
	private String logTypeCode;
	
	/** 支付时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;
	
	/** 支付方式代码  */
	private String payWayCode;
	
	/** 支付方式名称(微信，余额)  */
	private String payName;
	
	/** 交易对方ID  */
	private Integer tradeUserId;
	
	/** 交易对方名称  */
	private String tradeUsername;
	
	/** 交易对方手机号码  */
	private String tradeUserPhone;
	
	/** 交易对方邮箱  */
	private String tradeUserEmail;
	
	/** 交易对方电话  */
	private String tradeUserTel;
	
	/** 用户可用资金记录  */
	private BigDecimal avaliableMoneyLog;
	
	/** 用户冻结资金记录  */
	private BigDecimal frozenMoneyLog;
	
	/** 用户积分  */
	private Integer userIntegral;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		 this.email = email == null ? null : email.trim();
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		 this.phone = phone == null ? null : phone.trim();
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		 this.tel = tel == null ? null : tel.trim();
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		 this.unit = unit == null ? null : unit.trim();
	}
	public Integer getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(Integer inOrOut) {
		this.inOrOut = inOrOut;
	}
	public String getTradeTypeCode() {
		return tradeTypeCode;
	}
	public void setTradeTypeCode(String tradeTypeCode) {
		 this.tradeTypeCode = tradeTypeCode == null ? null : tradeTypeCode.trim();
	}
	public String getTradeStatusCode() {
		return tradeStatusCode;
	}
	public void setTradeStatusCode(String tradeStatusCode) {
		 this.tradeStatusCode = tradeStatusCode == null ? null : tradeStatusCode.trim();
	}
	public String getFundOptTypeCode() {
		return fundOptTypeCode;
	}
	public void setFundOptTypeCode(String fundOptTypeCode) {
		 this.fundOptTypeCode = fundOptTypeCode == null ? null : fundOptTypeCode.trim();
	}
	public String getFundOptLevelCode() {
		return fundOptLevelCode;
	}
	public void setFundOptLevelCode(String fundOptLevelCode) {
		 this.fundOptLevelCode = fundOptLevelCode == null ? null : fundOptLevelCode.trim();
	}
	public String getNoticeTypeCode() {
		return noticeTypeCode;
	}
	public void setNoticeTypeCode(String noticeTypeCode) {
		 this.noticeTypeCode = noticeTypeCode == null ? null : noticeTypeCode.trim();
	}
	public String getLogTypeCode() {
		return logTypeCode;
	}
	public void setLogTypeCode(String logTypeCode) {
		 this.logTypeCode = logTypeCode == null ? null : logTypeCode.trim();
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getPayWayCode() {
		return payWayCode;
	}
	public void setPayWayCode(String payWayCode) {
		 this.payWayCode = payWayCode == null ? null : payWayCode.trim();
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		 this.payName = payName == null ? null : payName.trim();
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
		 this.tradeUsername = tradeUsername == null ? null : tradeUsername.trim();
	}
	public String getTradeUserPhone() {
		return tradeUserPhone;
	}
	public void setTradeUserPhone(String tradeUserPhone) {
		 this.tradeUserPhone = tradeUserPhone == null ? null : tradeUserPhone.trim();
	}
	public String getTradeUserEmail() {
		return tradeUserEmail;
	}
	public void setTradeUserEmail(String tradeUserEmail) {
		 this.tradeUserEmail = tradeUserEmail == null ? null : tradeUserEmail.trim();
	}
	public String getTradeUserTel() {
		return tradeUserTel;
	}
	public void setTradeUserTel(String tradeUserTel) {
		 this.tradeUserTel = tradeUserTel == null ? null : tradeUserTel.trim();
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
	public Integer getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getReceiveUserSerial() {
		return receiveUserSerial;
	}

	public String getPayUserSerial() {
		return payUserSerial;
	}

	public void setReceiveUserSerial(String receiveUserSerial) {
		this.receiveUserSerial = receiveUserSerial;
	}

	public void setPayUserSerial(String payUserSerial) {
		this.payUserSerial = payUserSerial;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public BigDecimal getAvaliableMoney() {
		return avaliableMoney;
	}

	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}

	public void setAvaliableMoney(BigDecimal avaliableMoney) {
		this.avaliableMoney = avaliableMoney;
	}

	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}