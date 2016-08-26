package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：SysAccountLog <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：系统官方交易记录表 <p>
 */
public class SysAccountLog extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID(产生费用的用户）  */
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
	
	/** 价格  */
	private BigDecimal money;
	
	/** 单位  */
	private String unit;
	
	/** 支付时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;
	
	/** 支付类型ID  */
	private Integer payTypeId;
	
	/** 支付方式名称  */
	private String payName;
	
	/** 收入或支出(1，收入2，支出)  */
	private Integer inOrOut;
	
	/** 交易状态(1未完成，2成功完成，3失败，4其他)  */
	private Integer tradeStatus;
	
	/** 系统总收入记录  */
	private BigDecimal sysTotalIncomeLog;
	
	/** 系统总支出记录  */
	private BigDecimal sysTotalPayLog;
	
	/** 用户可用资金  */
	private BigDecimal avaliableMoneyLog;
	
	/** 用户冻结资金  */
	private BigDecimal frozenMoneyLog;
	
	
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
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getPayTypeId() {
		return payTypeId;
	}
	public void setPayTypeId(Integer payTypeId) {
		this.payTypeId = payTypeId;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		 this.payName = payName == null ? null : payName.trim();
	}
	public Integer getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(Integer inOrOut) {
		this.inOrOut = inOrOut;
	}
	public Integer getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public BigDecimal getSysTotalIncomeLog() {
		return sysTotalIncomeLog;
	}
	public void setSysTotalIncomeLog(BigDecimal sysTotalIncomeLog) {
		this.sysTotalIncomeLog = sysTotalIncomeLog;
	}
	public BigDecimal getSysTotalPayLog() {
		return sysTotalPayLog;
	}
	public void setSysTotalPayLog(BigDecimal sysTotalPayLog) {
		this.sysTotalPayLog = sysTotalPayLog;
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
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}