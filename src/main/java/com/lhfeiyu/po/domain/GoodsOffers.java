package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：GoodsOffers <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：普通商品报价记录表 <p>
 */
public class GoodsOffers extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 商店ID  */
	private Integer shopId;
	
	/** 拍品ID  */
	private Integer goodsId;
	
	/** 用户ID  */
	private Integer userId;
	
	/** 出价用户  */
	private String username;
	
	/** 出价金额  */
	private BigDecimal offerPrice;
	
	/** 出价时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date offerAt;
	
	/** 报价的状态（1未阅读，2已通知，3已阅读,4未同意，5已同意,6已生成订单,7交易中，8交易已完成,9已取消订单,10已退货）  */
	private Integer offerStatus;
	
	/** 成交价格  */
	private BigDecimal dealPrice;
	
	/** 操作时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operateTime;
	
	/** 推广人ID  */
	private Integer promoteUserId;
	
	/** 推广人编号  */
	private String promoteUserSerial;
	
	/** 推广人名称  */
	private String promoteUsername;
	
	
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
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
	public BigDecimal getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}
	public Date getOfferAt() {
		return offerAt;
	}
	public void setOfferAt(Date offerAt) {
		this.offerAt = offerAt;
	}
	public Integer getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(Integer offerStatus) {
		this.offerStatus = offerStatus;
	}
	public BigDecimal getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(BigDecimal dealPrice) {
		this.dealPrice = dealPrice;
	}
	public Date getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getPromoteUserId() {
		return promoteUserId;
	}
	public void setPromoteUserId(Integer promoteUserId) {
		this.promoteUserId = promoteUserId;
	}
	public String getPromoteUserSerial() {
		return promoteUserSerial;
	}
	public void setPromoteUserSerial(String promoteUserSerial) {
		 this.promoteUserSerial = promoteUserSerial == null ? null : promoteUserSerial.trim();
	}
	public String getPromoteUsername() {
		return promoteUsername;
	}
	public void setPromoteUsername(String promoteUsername) {
		 this.promoteUsername = promoteUsername == null ? null : promoteUsername.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}