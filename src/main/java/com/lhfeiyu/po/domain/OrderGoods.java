package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：OrderGoods <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：订单商品表 <p>
 */
public class OrderGoods extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String picPath;
	private String username;
	private String dealPrice;
	private String offerPrice;
	private Integer userId;
	private Integer goodsOffersId;
	private Integer shopId;
	private Integer orderStatus;
	private Integer shippingStatus;
	private Integer payStatus;
	private String status;
	private Double totalMoney;
	private String buyer;
	private Integer shopUserId;
	private String shopUsername;
	private Integer wholesaleUserId;
	private Integer orderUserId;
	private String wholesaleName;
	private String promoteUserName;
	private String promoteUserSerial;
	private String shopUrl;
	
	private Integer expressId;
	private String expressName;
	private String expressCode;
	private String expressOrder;
	private String pollStatus;
	private String expressStatus;
	private String expressState;
	private String expressJson;
	private Integer isSeller;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 订单商品信息对应的详细信息id，取值order_info的order_id  */
	private Integer orderId;
	
	/** 商品类型（1普通商品，2批发城商品）  */
	private Integer goodsType;
	
	/** 商品来源（1批发城，2店铺，3专场，4微拍，5即时拍）  */
	private Integer goodsFrom;
	
	/** 商品的的id，取值表goods 的goods_id  */
	private Integer goodsId;
	
	/** 商品的名称  */
	private String goodsName;
	
	/** 商品的唯一货号  */
	private String goodsSn;
	
	/** 商品的购买数量  */
	private Integer goodsNumber;
	
	/** 商品的市场售价  */
	private BigDecimal marketPrice;
	
	/** 商品的本店售价  */
	private BigDecimal shopPrice;
	
	/** 总价格  */
	private BigDecimal totalPrice;
	
	/** 运费（空值为包邮）  */
	private BigDecimal postageFee;
	
	/** 购买该商品时所选择的属性（占用：快递单号）  */
	private String goodsAttr;
	
	/** 当不是实物时，是否已发货  */
	private String sendFlag;
	
	/** 是否是实物  */
	private String isReal;
	
	/** 商品的扩展属性，比如像虚拟卡  */
	private String extensionCode;
	
	/** 父商品id，取值于ecs_cart的parent_id；如果有该值则是值多代表的物品的配件  */
	private Integer parentId;
	
	/** 是否参加优惠活动  */
	private String isGift;
	
	/** 推广人ID  */
	private Integer promoteUserId;
	
	/** 推广人  */
	private String promoteUsername;
	
	/** 推广收益金额  */
	private BigDecimal promoteBenifitMoney;
	
	/** 浏览次数  */
	private Integer scans;
	
	/** 点击次数  */
	private Integer hits;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Integer getGoodsFrom() {
		return goodsFrom;
	}
	public void setGoodsFrom(Integer goodsFrom) {
		this.goodsFrom = goodsFrom;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		 this.goodsName = goodsName == null ? null : goodsName.trim();
	}
	public String getGoodsSn() {
		return goodsSn;
	}
	public void setGoodsSn(String goodsSn) {
		 this.goodsSn = goodsSn == null ? null : goodsSn.trim();
	}
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
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
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getPostageFee() {
		return postageFee;
	}
	public void setPostageFee(BigDecimal postageFee) {
		this.postageFee = postageFee;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public void setGoodsAttr(String goodsAttr) {
		 this.goodsAttr = goodsAttr == null ? null : goodsAttr.trim();
	}
	public String getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(String sendFlag) {
		 this.sendFlag = sendFlag == null ? null : sendFlag.trim();
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getIsGift() {
		return isGift;
	}
	public void setIsGift(String isGift) {
		 this.isGift = isGift == null ? null : isGift.trim();
	}
	public Integer getPromoteUserId() {
		return promoteUserId;
	}
	public void setPromoteUserId(Integer promoteUserId) {
		this.promoteUserId = promoteUserId;
	}
	public String getPromoteUsername() {
		return promoteUsername;
	}
	public void setPromoteUsername(String promoteUsername) {
		 this.promoteUsername = promoteUsername == null ? null : promoteUsername.trim();
	}
	public BigDecimal getPromoteBenifitMoney() {
		return promoteBenifitMoney;
	}
	public void setPromoteBenifitMoney(BigDecimal promoteBenifitMoney) {
		this.promoteBenifitMoney = promoteBenifitMoney;
	}
	public Integer getScans() {
		return scans;
	}
	public void setScans(Integer scans) {
		this.scans = scans;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public Integer getIsSeller() {
		return isSeller;
	}

	public void setIsSeller(Integer isSeller) {
		this.isSeller = isSeller;
	}
	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressOrder() {
		return expressOrder;
	}

	public void setExpressOrder(String expressOrder) {
		this.expressOrder = expressOrder;
	}

	public String getPollStatus() {
		return pollStatus;
	}

	public void setPollStatus(String pollStatus) {
		this.pollStatus = pollStatus;
	}

	public String getExpressStatus() {
		return expressStatus;
	}

	public void setExpressStatus(String expressStatus) {
		this.expressStatus = expressStatus;
	}

	public String getExpressState() {
		return expressState;
	}

	public void setExpressState(String expressState) {
		this.expressState = expressState;
	}

	public String getExpressJson() {
		return expressJson;
	}

	public void setExpressJson(String expressJson) {
		this.expressJson = expressJson;
	}

	public Date getExpressReceiveTime() {
		return expressReceiveTime;
	}

	public void setExpressReceiveTime(Date expressReceiveTime) {
		this.expressReceiveTime = expressReceiveTime;
	}

	private Date expressReceiveTime;

	
	public String getShopUsername() {
		return shopUsername;
	}

	public void setShopUsername(String shopUsername) {
		this.shopUsername = shopUsername;
	}

	private String shopName;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getPromoteUserSerial() {
		return promoteUserSerial;
	}

	public void setPromoteUserSerial(String promoteUserSerial) {
		this.promoteUserSerial = promoteUserSerial;
	}

	public String getPromoteUserName() {
		return promoteUserName;
	}

	public void setPromoteUserName(String promoteUserName) {
		this.promoteUserName = promoteUserName;
	}

	public Integer getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(Integer orderUserId) {
		this.orderUserId = orderUserId;
	}
	
	public String getShopName() {
		return shopName;
	}

	public String getWholesaleName() {
		return wholesaleName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setWholesaleName(String wholesaleName) {
		this.wholesaleName = wholesaleName;
	}

	public Integer getWholesaleUserId() {
		return wholesaleUserId;
	}

	public void setWholesaleUserId(Integer wholesaleUserId) {
		this.wholesaleUserId = wholesaleUserId;
	}

	public Integer getShopUserId() {
		return shopUserId;
	}

	public void setShopUserId(Integer shopUserId) {
		this.shopUserId = shopUserId;
	}


	public Integer getOrderStatus() {
		return orderStatus;
	}

	public Integer getShippingStatus() {
		return shippingStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setShippingStatus(Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getGoodsOffersId() {
		return goodsOffersId;
	}

	public void setGoodsOffersId(Integer goodsOffersId) {
		this.goodsOffersId = goodsOffersId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getPicPath() {
		return picPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDealPrice() {
		return dealPrice;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public void setDealPrice(String dealPrice) {
		this.dealPrice = dealPrice;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}






























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}