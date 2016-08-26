package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Withdraw <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：取现表 <p>
 */
public class Withdraw extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userName;
	private String userSerial;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 申请日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date applyDate;
	
	/** 取现金额  */
	private BigDecimal applyMoney;
	
	/** 实际取现金额  */
	private BigDecimal applyRealMoney;
	
	/** 申请提现的银行名称  */
	private String applyBankName;
	
	/** 申请提现的银行卡号  */
	private String applyBankCardNum;
	
	/** 申请提现的银行账户姓名  */
	private String applyBankUsername;
	
	/** 手续费  */
	private BigDecimal fee;
	
	/** 备注消息  */
	private String applyMsg;
	
	/** 处理日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dealDate;
	
	/** 处理状态  */
	private Integer dealStatus;
	
	/** 处理用户ID  */
	private Integer dealUserId;
	
	/** 处理用户名  */
	private String dealUsername;
	
	/** 备用整形字段  */
	private Integer attr1;
	
	/** 备用字符串字段  */
	private String attr2;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public BigDecimal getApplyMoney() {
		return applyMoney;
	}
	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}
	public BigDecimal getApplyRealMoney() {
		return applyRealMoney;
	}
	public void setApplyRealMoney(BigDecimal applyRealMoney) {
		this.applyRealMoney = applyRealMoney;
	}
	public String getApplyBankName() {
		return applyBankName;
	}
	public void setApplyBankName(String applyBankName) {
		 this.applyBankName = applyBankName == null ? null : applyBankName.trim();
	}
	public String getApplyBankCardNum() {
		return applyBankCardNum;
	}
	public void setApplyBankCardNum(String applyBankCardNum) {
		 this.applyBankCardNum = applyBankCardNum == null ? null : applyBankCardNum.trim();
	}
	public String getApplyBankUsername() {
		return applyBankUsername;
	}
	public void setApplyBankUsername(String applyBankUsername) {
		 this.applyBankUsername = applyBankUsername == null ? null : applyBankUsername.trim();
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getApplyMsg() {
		return applyMsg;
	}
	public void setApplyMsg(String applyMsg) {
		 this.applyMsg = applyMsg == null ? null : applyMsg.trim();
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
	public Integer getDealUserId() {
		return dealUserId;
	}
	public void setDealUserId(Integer dealUserId) {
		this.dealUserId = dealUserId;
	}
	public String getDealUsername() {
		return dealUsername;
	}
	public void setDealUsername(String dealUsername) {
		 this.dealUsername = dealUsername == null ? null : dealUsername.trim();
	}
	public Integer getAttr1() {
		return attr1;
	}
	public void setAttr1(Integer attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		 this.attr2 = attr2 == null ? null : attr2.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	    public String getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}