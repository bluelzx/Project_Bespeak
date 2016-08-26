package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Treatment <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：商品表 <p>
 */
public class Treatment extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String goodsName;
	private Integer shopId;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 服务ID  */
	private Integer goodsId;
	
	/** 等级  */
	private String treatmentName;
	
	/** 一组商品的数量（疗程等）  */
	private Integer groupNum;
	
	/** 一组商品的数量的价格（疗程等）  */
	private BigDecimal groupPrice;
	
	/** 购买该商品时每笔成功交易赠送的积分数量  */
	private Integer giveIntegral;
	
	/** 备用描述1  */
	private String attrConent1;
	
	/** 备用描述2  */
	private String attrConent2;
	
	/** 备用描述3  */
	private String attrConent3;
	
	/** 备用描述4  */
	private String attrConent4;
	
	/** 数据字典代码：商品状态  */
	private String statusCode;
	
	/** 有效起始时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date validStartTime;
	
	/** 有效结束时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date validEndTime;
	
	/** 是否是实物  */
	private String isReal;
	
	/** 是否是新品  */
	private String isNew;
	
	/** 是否热销  */
	private String isHot;
	
	/** 是否是精品  */
	private String isBest;
	
	/** 该商品是否开放销售  */
	private String isOnSale;
	
	/** 商品关键字  */
	private Integer keywords;
	
	/** 运费（空值为包邮）  */
	private BigDecimal postageFee;
	
	/** 包装费  */
	private BigDecimal packFee;
	
	/** 是否包退(1.包退2，不包退)  */
	private Integer isSevenReturn;
	
	/** 商品名称显示的样式；包括颜色和字体样式  */
	private String goodsNameStyle;
	
	/** 供货人ID  */
	private Integer providerId;
	
	/** 供货人的名称  */
	private String providerName;
	
	/** 商品的重量，以千克为单位  */
	private Integer goodsWeight;
	
	/** 同步标识(1,是2，不是)  */
	private Integer syncFlag;
	
	/** 品牌id  */
	private Integer brandId;
	
	/** 市场售价  */
	private BigDecimal marketPrice;
	
	/** 代理价格  */
	private BigDecimal agentPrice;
	
	/** VIP代理价格  */
	private BigDecimal agentVipPrice;
	
	/** 促销价格  */
	private BigDecimal promotePrice;
	
	/** 拍卖起拍价格  */
	private BigDecimal auctionPrice;
	
	/** 促销价格开始日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promoteStartDate;
	
	/** 促销价格结束日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date promoteEndDate;
	
	/** 商品在前台显示的微缩图片，如在分类筛选时显示的小图片  */
	private Integer goodsThumbId;
	
	/** 商品的实际大小图片，如进入该商品页时介绍商品属性所显示的大图片  */
	private Integer goodsImgId;
	
	/** 上传的商品的原始图片  */
	private Integer originalImgId;
	
	/** 商品的扩展属性  */
	private String extensionCode;
	
	/** 是否能单独销售  */
	private String isAloneSale;
	
	/** 是否特价促销  */
	private String isPromote;
	
	/** 购买该商品所能领到的红包类型（1:固定金额，2百分比）  */
	private Integer bonusTypeId;
	
	/** 奖金金额（红包）  */
	private BigDecimal bonus;
	
	/** 商品所属类型id  */
	private Integer goodsTypeId;
	
	/** 商品的商家备注，仅商家可见  */
	private String sellerNote;
	
	/** 是否公开(1.公开2，不公开)  */
	private Integer isPublic;
	
	/** 商品来源  */
	private Integer goodsFrom;
	
	/** 商品是否允许复制(1,可以2，不可以)  */
	private Integer goodsPrivilege;
	
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatmentName(String treatmentName) {
		 this.treatmentName = treatmentName == null ? null : treatmentName.trim();
	}
	public Integer getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}
	public BigDecimal getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(BigDecimal groupPrice) {
		this.groupPrice = groupPrice;
	}
	public Integer getGiveIntegral() {
		return giveIntegral;
	}
	public void setGiveIntegral(Integer giveIntegral) {
		this.giveIntegral = giveIntegral;
	}
	public String getAttrConent1() {
		return attrConent1;
	}
	public void setAttrConent1(String attrConent1) {
		 this.attrConent1 = attrConent1 == null ? null : attrConent1.trim();
	}
	public String getAttrConent2() {
		return attrConent2;
	}
	public void setAttrConent2(String attrConent2) {
		 this.attrConent2 = attrConent2 == null ? null : attrConent2.trim();
	}
	public String getAttrConent3() {
		return attrConent3;
	}
	public void setAttrConent3(String attrConent3) {
		 this.attrConent3 = attrConent3 == null ? null : attrConent3.trim();
	}
	public String getAttrConent4() {
		return attrConent4;
	}
	public void setAttrConent4(String attrConent4) {
		 this.attrConent4 = attrConent4 == null ? null : attrConent4.trim();
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		 this.statusCode = statusCode == null ? null : statusCode.trim();
	}
	public Date getValidStartTime() {
		return validStartTime;
	}
	public void setValidStartTime(Date validStartTime) {
		this.validStartTime = validStartTime;
	}
	public Date getValidEndTime() {
		return validEndTime;
	}
	public void setValidEndTime(Date validEndTime) {
		this.validEndTime = validEndTime;
	}
	public String getIsReal() {
		return isReal;
	}
	public void setIsReal(String isReal) {
		 this.isReal = isReal == null ? null : isReal.trim();
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
	public String getIsBest() {
		return isBest;
	}
	public void setIsBest(String isBest) {
		 this.isBest = isBest == null ? null : isBest.trim();
	}
	public String getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(String isOnSale) {
		 this.isOnSale = isOnSale == null ? null : isOnSale.trim();
	}
	public Integer getKeywords() {
		return keywords;
	}
	public void setKeywords(Integer keywords) {
		this.keywords = keywords;
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
	public Integer getIsSevenReturn() {
		return isSevenReturn;
	}
	public void setIsSevenReturn(Integer isSevenReturn) {
		this.isSevenReturn = isSevenReturn;
	}
	public String getGoodsNameStyle() {
		return goodsNameStyle;
	}
	public void setGoodsNameStyle(String goodsNameStyle) {
		 this.goodsNameStyle = goodsNameStyle == null ? null : goodsNameStyle.trim();
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
	public Integer getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(Integer goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public Integer getSyncFlag() {
		return syncFlag;
	}
	public void setSyncFlag(Integer syncFlag) {
		this.syncFlag = syncFlag;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getAgentPrice() {
		return agentPrice;
	}
	public void setAgentPrice(BigDecimal agentPrice) {
		this.agentPrice = agentPrice;
	}
	public BigDecimal getAgentVipPrice() {
		return agentVipPrice;
	}
	public void setAgentVipPrice(BigDecimal agentVipPrice) {
		this.agentVipPrice = agentVipPrice;
	}
	public BigDecimal getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(BigDecimal promotePrice) {
		this.promotePrice = promotePrice;
	}
	public BigDecimal getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(BigDecimal auctionPrice) {
		this.auctionPrice = auctionPrice;
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
	public String getExtensionCode() {
		return extensionCode;
	}
	public void setExtensionCode(String extensionCode) {
		 this.extensionCode = extensionCode == null ? null : extensionCode.trim();
	}
	public String getIsAloneSale() {
		return isAloneSale;
	}
	public void setIsAloneSale(String isAloneSale) {
		 this.isAloneSale = isAloneSale == null ? null : isAloneSale.trim();
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
	public BigDecimal getBonus() {
		return bonus;
	}
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
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
	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}
	public Integer getGoodsFrom() {
		return goodsFrom;
	}
	public void setGoodsFrom(Integer goodsFrom) {
		this.goodsFrom = goodsFrom;
	}
	public Integer getGoodsPrivilege() {
		return goodsPrivilege;
	}
	public void setGoodsPrivilege(Integer goodsPrivilege) {
		this.goodsPrivilege = goodsPrivilege;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}