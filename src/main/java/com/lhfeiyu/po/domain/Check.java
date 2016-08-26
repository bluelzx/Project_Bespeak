package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Check <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 记帐表 <p>
 */
public class Check extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 藏品图片路径  */
	private String picPaths;
	
	/** 藏品名称  */
	private String name;
	
	/** 采购价格  */
	private BigDecimal priceIn;
	
	/** 意向售价  */
	private BigDecimal priceOut;
	
	/** 规格  */
	private String specification;
	
	/** 提供商（卖家）  */
	private String provider;
	
	/** 提供商（卖家）ID  */
	private Integer providerId;
	
	/** 提供商（卖家）电话  */
	private String providerTel;
	
	/** 采购地点  */
	private String purchaseAddress;
	
	/** 采购日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date purchaseDate;
	
	/** 藏品编码  */
	private String serialNum;
	
	/** 实际售价（卖出价格）  */
	private BigDecimal priceRealOut;
	
	/** 买家名称  */
	private String buyer;
	
	/** 买家ID  */
	private Integer buyerId;
	
	/** 买家电话  */
	private String buyerTel;
	
	/** 卖出时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sellTime;
	
	
	public String getPicPaths() {
		return picPaths;
	}
	public void setPicPaths(String picPaths) {
		 this.picPaths = picPaths == null ? null : picPaths.trim();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		 this.name = name == null ? null : name.trim();
	}
	public BigDecimal getPriceIn() {
		return priceIn;
	}
	public void setPriceIn(BigDecimal priceIn) {
		this.priceIn = priceIn;
	}
	public BigDecimal getPriceOut() {
		return priceOut;
	}
	public void setPriceOut(BigDecimal priceOut) {
		this.priceOut = priceOut;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		 this.specification = specification == null ? null : specification.trim();
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		 this.provider = provider == null ? null : provider.trim();
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public String getProviderTel() {
		return providerTel;
	}
	public void setProviderTel(String providerTel) {
		 this.providerTel = providerTel == null ? null : providerTel.trim();
	}
	public String getPurchaseAddress() {
		return purchaseAddress;
	}
	public void setPurchaseAddress(String purchaseAddress) {
		 this.purchaseAddress = purchaseAddress == null ? null : purchaseAddress.trim();
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		 this.serialNum = serialNum == null ? null : serialNum.trim();
	}
	public BigDecimal getPriceRealOut() {
		return priceRealOut;
	}
	public void setPriceRealOut(BigDecimal priceRealOut) {
		this.priceRealOut = priceRealOut;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		 this.buyer = buyer == null ? null : buyer.trim();
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerTel() {
		return buyerTel;
	}
	public void setBuyerTel(String buyerTel) {
		 this.buyerTel = buyerTel == null ? null : buyerTel.trim();
	}
	public Date getSellTime() {
		return sellTime;
	}
	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}