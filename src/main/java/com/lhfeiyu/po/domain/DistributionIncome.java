package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：DistributionIncome <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：分销收入表 <p>
 */
public class DistributionIncome extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 对象名称（藏品名称，店铺名称，拍场名称）  */
	private String objectName;
	
	/** 客户ID  */
	private Integer customerId;
	
	/** 店铺ID  */
	private Integer shopId;
	
	/** 拍卖ID  */
	private Integer auctionId;
	
	/** 关联地址  */
	private String linkUrl;
	
	/** 成交时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dealTime;
	
	/** 收入金额  */
	private BigDecimal moneyIncome;
	
	/** 冻结资金-记录作用  */
	private BigDecimal frozenMoney;
	
	/** 产生收入之前的可用金额-记录作用  */
	private BigDecimal avaliableMoneyBefore;
	
	/** 产生收入之后的可用金额-记录作用  */
	private BigDecimal avaliableMoneyNow;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		 this.objectName = objectName == null ? null : objectName.trim();
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(Integer auctionId) {
		this.auctionId = auctionId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		 this.linkUrl = linkUrl == null ? null : linkUrl.trim();
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public BigDecimal getMoneyIncome() {
		return moneyIncome;
	}
	public void setMoneyIncome(BigDecimal moneyIncome) {
		this.moneyIncome = moneyIncome;
	}
	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	public BigDecimal getAvaliableMoneyBefore() {
		return avaliableMoneyBefore;
	}
	public void setAvaliableMoneyBefore(BigDecimal avaliableMoneyBefore) {
		this.avaliableMoneyBefore = avaliableMoneyBefore;
	}
	public BigDecimal getAvaliableMoneyNow() {
		return avaliableMoneyNow;
	}
	public void setAvaliableMoneyNow(BigDecimal avaliableMoneyNow) {
		this.avaliableMoneyNow = avaliableMoneyNow;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}