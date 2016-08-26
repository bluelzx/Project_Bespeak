package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Bespeak <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：预约表（挂号，体检，陪诊，癌症筛查） <p>
 */
public class Bespeak extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	 private String shopName;
	 private String goodsName;
	 private String providerName;
	 private String userName;
	 private String provinceName;
	 private String cityName;
	 private String couponName;
	 private Integer timeNum;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 店铺id  */
	private Integer shopId;
	
	/** 技师id  */
	private Integer providerId;
	
	/** 商品ID（服务）  */
	private Integer goodsId;
	
	/** 商品数量  */
	private Integer goodsNumber;
	
	/** 组JS（疗程等）  */
	private String groupJson;
	
	/** 用户id  */
	private Integer userId;
	
	/** 预约时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bespeakTime;
	
	/** 预约开始时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bespeakStartTime;
	
	/** 预约结束时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bespeakEndTime;
	
	/** 优惠券ID  */
	private Integer couponId;
	
	/** 优惠券折扣  */
	private Integer couponDiscount;
	
	/** 价格  */
	private BigDecimal price;
	
	/** 总价格  */
	private BigDecimal totalPrice;
	
	/** 订单ID  */
	private Integer orderInfoId;
	
	/** 订单号,唯一  */
	private String orderSn;
	
	/** 省份  */
	private Integer province;
	
	/** 城市  */
	private Integer city;
	
	/** 详细地址  */
	private String address;
	
	
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getGroupJson() {
		return groupJson;
	}
	public void setGroupJson(String groupJson) {
		 this.groupJson = groupJson == null ? null : groupJson.trim();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getBespeakTime() {
		return bespeakTime;
	}
	public void setBespeakTime(Date bespeakTime) {
		this.bespeakTime = bespeakTime;
	}
	public Date getBespeakStartTime() {
		return bespeakStartTime;
	}
	public void setBespeakStartTime(Date bespeakStartTime) {
		this.bespeakStartTime = bespeakStartTime;
	}
	public Date getBespeakEndTime() {
		return bespeakEndTime;
	}
	public void setBespeakEndTime(Date bespeakEndTime) {
		this.bespeakEndTime = bespeakEndTime;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(Integer couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getOrderInfoId() {
		return orderInfoId;
	}
	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		 this.orderSn = orderSn == null ? null : orderSn.trim();
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		 this.address = address == null ? null : address.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Integer getTimeNum() {
		return timeNum;
	}
	public void setTimeNum(Integer timeNum) {
		this.timeNum = timeNum;
	}
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}