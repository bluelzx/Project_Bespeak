package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：UserFundFrozen <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：用户资金冻结表 <p>
 */
public class UserFundFrozen extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 用户名  */
	private String username;
	
	/** 用户手机  */
	private String phone;
	
	/** 交易类型ID  */
	private Integer tradeTypeId;
	
	/** 链接URL  */
	private String linkUrl;
	
	/** 数据字典代码：资金冻结类型  */
	private String frozenTypeCode;
	
	/** 冻结资金  */
	private BigDecimal frozenMoney;
	
	/** 手续费  */
	private BigDecimal fee;
	
	/** 是否长期把持冻结状态(是：开通专场压金，否：购买商品）  */
	private Integer isKeep;
	
	/** 账户总可用金额记录  */
	private BigDecimal avaliableMoneyLog;
	
	/** 账户总冻结金额记录  */
	private BigDecimal frozenMoneyLog;
	
	/** 冻结时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date freezeTime;
	
	/** 数据字典代码：冻结资金状态  */
	private String frozenMoneyStatusCode;
	
	/** 处理时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dealTime;
	
	/** 对方类型（用户，商店，红包）  */
	private String theOtherType;
	
	/** 对方名称  */
	private String theOtherName;
	
	/** 对方ID  */
	private Integer theOtherId;
	
	/** 关联的充值ID  */
	private Integer chargeId;
	
	/** 用户的微信openId  */
	private String openId;
	
	/** 订单编号  */
	private String orderSn;
	
	/** 标题  */
	private String title;
	
	/** 内容  */
	private String content;
	
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		 this.phone = phone == null ? null : phone.trim();
	}
	public Integer getTradeTypeId() {
		return tradeTypeId;
	}
	public void setTradeTypeId(Integer tradeTypeId) {
		this.tradeTypeId = tradeTypeId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		 this.linkUrl = linkUrl == null ? null : linkUrl.trim();
	}
	public String getFrozenTypeCode() {
		return frozenTypeCode;
	}
	public void setFrozenTypeCode(String frozenTypeCode) {
		 this.frozenTypeCode = frozenTypeCode == null ? null : frozenTypeCode.trim();
	}
	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public Integer getIsKeep() {
		return isKeep;
	}
	public void setIsKeep(Integer isKeep) {
		this.isKeep = isKeep;
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
	public Date getFreezeTime() {
		return freezeTime;
	}
	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
	}
	public String getFrozenMoneyStatusCode() {
		return frozenMoneyStatusCode;
	}
	public void setFrozenMoneyStatusCode(String frozenMoneyStatusCode) {
		 this.frozenMoneyStatusCode = frozenMoneyStatusCode == null ? null : frozenMoneyStatusCode.trim();
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getTheOtherType() {
		return theOtherType;
	}
	public void setTheOtherType(String theOtherType) {
		 this.theOtherType = theOtherType == null ? null : theOtherType.trim();
	}
	public String getTheOtherName() {
		return theOtherName;
	}
	public void setTheOtherName(String theOtherName) {
		 this.theOtherName = theOtherName == null ? null : theOtherName.trim();
	}
	public Integer getTheOtherId() {
		return theOtherId;
	}
	public void setTheOtherId(Integer theOtherId) {
		this.theOtherId = theOtherId;
	}
	public Integer getChargeId() {
		return chargeId;
	}
	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		 this.openId = openId == null ? null : openId.trim();
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		 this.orderSn = orderSn == null ? null : orderSn.trim();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		 this.title = title == null ? null : title.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}