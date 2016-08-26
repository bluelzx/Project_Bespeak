package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：WholesaleGoods <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：批发城商品表-暂未使用 <p>
 */
public class WholesaleGoods extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 批发商ID  */
	private Integer wholesaleId;
	
	/** 商品的唯一货号  */
	private String goodsSn;
	
	/** 商品的名称  */
	private String goodsName;
	
	/** 商品名称显示的样式；包括颜色和字体样式  */
	private String goodsNameStyle;
	
	/** 品牌id  */
	private Integer brandId;
	
	/** 供货人ID  */
	private Integer providerId;
	
	/** 供货人的名称  */
	private String providerName;
	
	/** 商品库存数量  */
	private Integer remainNumber;
	
	/** 商品的重量，以千克为单位  */
	private Integer goodsWeight;
	
	/** 市场售价  */
	private BigDecimal marketPrice;
	
	/** 本店售价  */
	private BigDecimal shopPrice;
	
	/** 促销价格  */
	private BigDecimal promotePrice;
	
	/** 促销价格开始日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promoteStartDate;
	
	/** 促销价格结束日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promoteEndDate;
	
	/** 商品报警数量  */
	private Integer warnNumber;
	
	/** 商品关键字  */
	private Integer keywords;
	
	/** 商品的简短描述  */
	private String goodsBrief;
	
	/** 商品的详细描述  */
	private String goodsDescription;
	
	/** 商品在前台显示的微缩图片，如在分类筛选时显示的小图片  */
	private Integer goodsThumbId;
	
	/** 商品的实际大小图片，如进入该商品页时介绍商品属性所显示的大图片  */
	private Integer goodsImgId;
	
	/** 上传的商品的原始图片  */
	private Integer originalImgId;
	
	/** 是否是实物  */
	private String isReal;
	
	/** 商品的扩展属性  */
	private String extensionCode;
	
	/** 该商品是否开放销售  */
	private String isOnSale;
	
	/** 是否能单独销售  */
	private String isAloneSale;
	
	/** 购买该商品可以使用的积分数量  */
	private Integer integral;
	
	/** 是否是精品  */
	private String isBest;
	
	/** 是否是新品  */
	private String isNew;
	
	/** 是否热销  */
	private String isHot;
	
	/** 是否特价促销  */
	private String isPromote;
	
	/** 购买该商品所能领到的红包类型  */
	private Integer bonusTypeId;
	
	/** 商品所属类型id  */
	private Integer goodsTypeId;
	
	/** 商品的商家备注，仅商家可见  */
	private String sellerNote;
	
	/** 购买该商品时每笔成功交易赠送的积分数量  */
	private Integer giveIntegral;
	
	/** 运费（是否包邮）  */
	private BigDecimal postageFee;
	
	/** 包装费  */
	private BigDecimal packFee;
	
	/** 浏览次数  */
	private Integer scans;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getWholesaleId() {
		return wholesaleId;
	}
	public void setWholesaleId(Integer wholesaleId) {
		this.wholesaleId = wholesaleId;
	}
	public String getGoodsSn() {
		return goodsSn;
	}
	public void setGoodsSn(String goodsSn) {
		 this.goodsSn = goodsSn == null ? null : goodsSn.trim();
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		 this.goodsName = goodsName == null ? null : goodsName.trim();
	}
	public String getGoodsNameStyle() {
		return goodsNameStyle;
	}
	public void setGoodsNameStyle(String goodsNameStyle) {
		 this.goodsNameStyle = goodsNameStyle == null ? null : goodsNameStyle.trim();
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		 this.providerName = providerName == null ? null : providerName.trim();
	}
	public Integer getRemainNumber() {
		return remainNumber;
	}
	public void setRemainNumber(Integer remainNumber) {
		this.remainNumber = remainNumber;
	}
	public Integer getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(Integer goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}
	public BigDecimal getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(BigDecimal promotePrice) {
		this.promotePrice = promotePrice;
	}
	public Date getPromoteStartDate() {
		return promoteStartDate;
	}
	public void setPromoteStartDate(Date promoteStartDate) {
		this.promoteStartDate = promoteStartDate;
	}
	public Date getPromoteEndDate() {
		return promoteEndDate;
	}
	public void setPromoteEndDate(Date promoteEndDate) {
		this.promoteEndDate = promoteEndDate;
	}
	public Integer getWarnNumber() {
		return warnNumber;
	}
	public void setWarnNumber(Integer warnNumber) {
		this.warnNumber = warnNumber;
	}
	public Integer getKeywords() {
		return keywords;
	}
	public void setKeywords(Integer keywords) {
		this.keywords = keywords;
	}
	public String getGoodsBrief() {
		return goodsBrief;
	}
	public void setGoodsBrief(String goodsBrief) {
		 this.goodsBrief = goodsBrief == null ? null : goodsBrief.trim();
	}
	public String getGoodsDescription() {
		return goodsDescription;
	}
	public void setGoodsDescription(String goodsDescription) {
		 this.goodsDescription = goodsDescription == null ? null : goodsDescription.trim();
	}
	public Integer getGoodsThumbId() {
		return goodsThumbId;
	}
	public void setGoodsThumbId(Integer goodsThumbId) {
		this.goodsThumbId = goodsThumbId;
	}
	public Integer getGoodsImgId() {
		return goodsImgId;
	}
	public void setGoodsImgId(Integer goodsImgId) {
		this.goodsImgId = goodsImgId;
	}
	public Integer getOriginalImgId() {
		return originalImgId;
	}
	public void setOriginalImgId(Integer originalImgId) {
		this.originalImgId = originalImgId;
	}
	public String getIsReal() {
		return isReal;
	}
	public void setIsReal(String isReal) {
		 this.isReal = isReal == null ? null : isReal.trim();
	}
	public String getExtensionCode() {
		return extensionCode;
	}
	public void setExtensionCode(String extensionCode) {
		 this.extensionCode = extensionCode == null ? null : extensionCode.trim();
	}
	public String getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(String isOnSale) {
		 this.isOnSale = isOnSale == null ? null : isOnSale.trim();
	}
	public String getIsAloneSale() {
		return isAloneSale;
	}
	public void setIsAloneSale(String isAloneSale) {
		 this.isAloneSale = isAloneSale == null ? null : isAloneSale.trim();
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getIsBest() {
		return isBest;
	}
	public void setIsBest(String isBest) {
		 this.isBest = isBest == null ? null : isBest.trim();
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		 this.isNew = isNew == null ? null : isNew.trim();
	}
	public String getIsHot() {
		return isHot;
	}
	public void setIsHot(String isHot) {
		 this.isHot = isHot == null ? null : isHot.trim();
	}
	public String getIsPromote() {
		return isPromote;
	}
	public void setIsPromote(String isPromote) {
		 this.isPromote = isPromote == null ? null : isPromote.trim();
	}
	public Integer getBonusTypeId() {
		return bonusTypeId;
	}
	public void setBonusTypeId(Integer bonusTypeId) {
		this.bonusTypeId = bonusTypeId;
	}
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getSellerNote() {
		return sellerNote;
	}
	public void setSellerNote(String sellerNote) {
		 this.sellerNote = sellerNote == null ? null : sellerNote.trim();
	}
	public Integer getGiveIntegral() {
		return giveIntegral;
	}
	public void setGiveIntegral(Integer giveIntegral) {
		this.giveIntegral = giveIntegral;
	}
	public BigDecimal getPostageFee() {
		return postageFee;
	}
	public void setPostageFee(BigDecimal postageFee) {
		this.postageFee = postageFee;
	}
	public BigDecimal getPackFee() {
		return packFee;
	}
	public void setPackFee(BigDecimal packFee) {
		this.packFee = packFee;
	}
	public Integer getScans() {
		return scans;
	}
	public void setScans(Integer scans) {
		this.scans = scans;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}