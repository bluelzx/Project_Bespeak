package com.lhfeiyu.po.domain;

import java.math.BigDecimal;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：ShopFund <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：店铺资金表 <p>
 */
public class ShopFund extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 冻结资金  */
	private BigDecimal frozenMoney;
	
	/** 可用金额  */
	private BigDecimal avaliableMoney;
	
	/** 积分资产  */
	private BigDecimal integralFund;
	
	/** 金币资产  */
	private BigDecimal coinFund;
	
	/** 其他资产（占用：诚信保证金）  */
	private BigDecimal otherFund;
	
	/** 其他资产2  */
	private BigDecimal otherFund2;
	
	/** 支付密码  */
	private String payPassword;
	
	/** 银行名称1  */
	private String bankName1;
	
	/** 银行账号用户名称1  */
	private String bankUsername1;
	
	/** 银行卡号1  */
	private String bankCard1;
	
	/** 银行名称2  */
	private String bankName2;
	
	/** 银行账号用户名称2  */
	private String bankUsername2;
	
	/** 银行卡号2  */
	private String bankCard2;
	
	/** 银行名称3  */
	private String bankName3;
	
	/** 银行账号用户名称3  */
	private String bankUsername3;
	
	/** 银行卡号3  */
	private String bankCard3;
	
	/** 银行名称4  */
	private String bankName4;
	
	/** 银行账号用户名称4  */
	private String badkUsername4;
	
	/** 银行卡号4  */
	private String bankCard4;
	
	/** 银行名称5  */
	private String bankName5;
	
	/** 银行账号用户名称5  */
	private String bankUsername5;
	
	/** 银行卡号5  */
	private String bankCard5;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	public BigDecimal getAvaliableMoney() {
		return avaliableMoney;
	}
	public void setAvaliableMoney(BigDecimal avaliableMoney) {
		this.avaliableMoney = avaliableMoney;
	}
	public BigDecimal getIntegralFund() {
		return integralFund;
	}
	public void setIntegralFund(BigDecimal integralFund) {
		this.integralFund = integralFund;
	}
	public BigDecimal getCoinFund() {
		return coinFund;
	}
	public void setCoinFund(BigDecimal coinFund) {
		this.coinFund = coinFund;
	}
	public BigDecimal getOtherFund() {
		return otherFund;
	}
	public void setOtherFund(BigDecimal otherFund) {
		this.otherFund = otherFund;
	}
	public BigDecimal getOtherFund2() {
		return otherFund2;
	}
	public void setOtherFund2(BigDecimal otherFund2) {
		this.otherFund2 = otherFund2;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		 this.payPassword = payPassword == null ? null : payPassword.trim();
	}
	public String getBankName1() {
		return bankName1;
	}
	public void setBankName1(String bankName1) {
		 this.bankName1 = bankName1 == null ? null : bankName1.trim();
	}
	public String getBankUsername1() {
		return bankUsername1;
	}
	public void setBankUsername1(String bankUsername1) {
		 this.bankUsername1 = bankUsername1 == null ? null : bankUsername1.trim();
	}
	public String getBankCard1() {
		return bankCard1;
	}
	public void setBankCard1(String bankCard1) {
		 this.bankCard1 = bankCard1 == null ? null : bankCard1.trim();
	}
	public String getBankName2() {
		return bankName2;
	}
	public void setBankName2(String bankName2) {
		 this.bankName2 = bankName2 == null ? null : bankName2.trim();
	}
	public String getBankUsername2() {
		return bankUsername2;
	}
	public void setBankUsername2(String bankUsername2) {
		 this.bankUsername2 = bankUsername2 == null ? null : bankUsername2.trim();
	}
	public String getBankCard2() {
		return bankCard2;
	}
	public void setBankCard2(String bankCard2) {
		 this.bankCard2 = bankCard2 == null ? null : bankCard2.trim();
	}
	public String getBankName3() {
		return bankName3;
	}
	public void setBankName3(String bankName3) {
		 this.bankName3 = bankName3 == null ? null : bankName3.trim();
	}
	public String getBankUsername3() {
		return bankUsername3;
	}
	public void setBankUsername3(String bankUsername3) {
		 this.bankUsername3 = bankUsername3 == null ? null : bankUsername3.trim();
	}
	public String getBankCard3() {
		return bankCard3;
	}
	public void setBankCard3(String bankCard3) {
		 this.bankCard3 = bankCard3 == null ? null : bankCard3.trim();
	}
	public String getBankName4() {
		return bankName4;
	}
	public void setBankName4(String bankName4) {
		 this.bankName4 = bankName4 == null ? null : bankName4.trim();
	}
	public String getBadkUsername4() {
		return badkUsername4;
	}
	public void setBadkUsername4(String badkUsername4) {
		 this.badkUsername4 = badkUsername4 == null ? null : badkUsername4.trim();
	}
	public String getBankCard4() {
		return bankCard4;
	}
	public void setBankCard4(String bankCard4) {
		 this.bankCard4 = bankCard4 == null ? null : bankCard4.trim();
	}
	public String getBankName5() {
		return bankName5;
	}
	public void setBankName5(String bankName5) {
		 this.bankName5 = bankName5 == null ? null : bankName5.trim();
	}
	public String getBankUsername5() {
		return bankUsername5;
	}
	public void setBankUsername5(String bankUsername5) {
		 this.bankUsername5 = bankUsername5 == null ? null : bankUsername5.trim();
	}
	public String getBankCard5() {
		return bankCard5;
	}
	public void setBankCard5(String bankCard5) {
		 this.bankCard5 = bankCard5 == null ? null : bankCard5.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}