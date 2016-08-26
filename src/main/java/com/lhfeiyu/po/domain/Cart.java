package com.lhfeiyu.po.domain;

import java.math.BigDecimal;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Cart <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：购物车表 <p>
 */
public class Cart extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String picPath;
	private Integer remainNumber;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 类型ID  */
	private Integer userId;
	
	/** 商店ID  */
	private Integer shopId;
	
	/** session_id  */
	private String sessionId;
	
	/** 商品的ID,取自表goods的id  */
	private Integer goodsId;
	
	/** 商品的货号,取自表goods的goods_sn  */
	private String goodsSn;
	
	/** 商品名称,取自表goods的goods_name  */
	private String goodsName;
	
	/** 商品的本店价,取自表goods的shop_price  */
	private BigDecimal shopPrice;
	
	/** 商品的本店价,取自表市场价  */
	private BigDecimal marketPrice;
	
	/** 商品的购买数量,在购物车时,实际库存不减少  */
	private Integer goodsNumber;
	
	/** 商品的扩展属性, 取自goods的extension_code  */
	private String goodsAttr;
	
	/** 取自goods的is_real  */
	private String isReal;
	
	/** 商品的扩展属性,取自goods的extension_code  */
	private String extensionCode;
	
	/** 该商品的父商品ID,没有该值为0,有的话那该商品就是该id的配件  */
	private Integer parentId;
	
	/** 购物车商品类型(普通，团够，拍卖，夺宝奇兵等)  */
	private String recType;
	
	/** 是否赠品  */
	private String isGift;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		 this.sessionId = sessionId == null ? null : sessionId.trim();
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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
	public BigDecimal getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getGoodsAttr() {
		return goodsAttr;
	}
	public void setGoodsAttr(String goodsAttr) {
		 this.goodsAttr = goodsAttr == null ? null : goodsAttr.trim();
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
	public String getRecType() {
		return recType;
	}
	public void setRecType(String recType) {
		 this.recType = recType == null ? null : recType.trim();
	}
	public String getIsGift() {
		return isGift;
	}
	public void setIsGift(String isGift) {
		 this.isGift = isGift == null ? null : isGift.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public Integer getRemainNumber() {
		return remainNumber;
	}

	public void setRemainNumber(Integer remainNumber) {
		this.remainNumber = remainNumber;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}