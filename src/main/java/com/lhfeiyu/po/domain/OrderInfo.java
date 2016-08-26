package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：OrderInfo <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：订单详细信息表 <p>
 */
public class OrderInfo extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String goodsPrice;
	private String picPath;
	private String overComment;
	private Double totalMoney;
	private String goodsAttr;
	private String extensionCode;
	private Integer orderGoodsId;
	private Integer goodsType;
	private String shopUrl;
	private String goodsName;
	private Integer shopUserId;
	private String shopUsername;
	private Integer wholesaleUserId;
	private Double offerPrice;
	private Integer isSeller;
	private String provinceName;
	private String cityName;
	private String shopName;
	private String nameUser;
	private String providerName;
	private String userRealName;//用户真实姓名
	private String providerRealName;//技师真实姓名
	private String couponCode;//优惠码
	private String couponType;//优惠类型
	private BigDecimal couponMoney;//优惠价钱
	private Integer couponzhekou;//优惠折扣
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 订单号,唯一  */
	private String orderSn;
	
	/** 用户ID  */
	private Integer userId;
	
	/** 店铺ID  */
	private Integer shopId;
	
	/** 提供者ID  */
	private Integer providerId;
	
	/** 商品ID（服务）  */
	private Integer goodsId;
	
	/** 商品数量  */
	private Integer goodsNumber;
	
	/** 优惠券ID  */
	private Integer couponId;
	
	/** 优惠券折扣  */
	private Integer couponDiscount;
	
	/** 单价  */
	private BigDecimal shopPrice;
	
	/** 总价格  */
	private BigDecimal totalPrice;
	
	/** 数据字典代码：订单的状态  */
	private String orderStatusCode;
	
	/** 数据字典代码：商品配送情况  */
	private String shippingStatusCode;
	
	/** 数据字典代码：支付状态  */
	private String payStatusCode;
	
	/** 数据字典代码：订单完成状态  */
	private String orderDoneStausCode;
	
	/** 收货人的姓名  */
	private String username;
	
	/** 收货人的国家  */
	private String country;
	
	/** 收货人的省份  */
	private String province;
	
	/** 收货人的城市  */
	private String city;
	
	/** 收货人的地区  */
	private String district;
	
	/** 收货人的详细地址  */
	private String address;
	
	/** 收货人的邮编  */
	private String zipcode;
	
	/** 收货人的手机  */
	private String phone;
	
	/** 收货人的电话  */
	private String tel;
	
	/** 收货人的Email  */
	private String email;
	
	/** 收货人的最佳送货时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bestTime;
	
	/** 订单附言,由用户提交订单前填写  */
	private String postscript;
	
	/** 用户选择的配送方式id  */
	private Integer shippingId;
	
	/** 用户选择的配送方式的名称  */
	private String shippingName;
	
	/** 运费（空值为包邮）  */
	private BigDecimal postageFee;
	
	/** 快递公司ID  */
	private Integer expressId;
	
	/** 快递公司名称  */
	private String expressName;
	
	/** 快递公司编码  */
	private String expressCode;
	
	/** 快递单号  */
	private String expressOrder;
	
	/** 快递100接口的推送订阅状态（200提交成功，701:拒绝订阅的快递公司，700: 订阅方的订阅数据存在错误（如不支持的快递公司、单号为空、单号超长等），600: 您不是合法的订阅者（即授权Key出错），500: 服务器错误，501:重复订阅）  */
	private String pollStatus;
	
	/** 监控状态:polling:监控中，shutdown:结束，abort:中止，updateall：重新推送。其中当快递单为已签收时status=shutdown，当message为“3天查询无记录”或“60天无变化时”status= abort  */
	private String expressStatus;
	
	/** 快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单  */
	private String expressState;
	
	/** 快递100推送数据(Json)  */
	private String expressJson;
	
	/** 快递签收日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expressReceiveTime;
	
	/** 用户选择的支付方式的id  */
	private Integer payId;
	
	/** 用户选择的支付方式名称  */
	private String payName;
	
	/** 缺货处理方式,等待所有商品备齐后再发,取消订单;与店主协商  */
	private String howOos;
	
	/** 浏览次数  */
	private Integer scans;
	
	/** 点击次数  */
	private Integer hits;
	
	
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		 this.orderSn = orderSn == null ? null : orderSn.trim();
	}
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
	public String getOrderStatusCode() {
		return orderStatusCode;
	}
	public void setOrderStatusCode(String orderStatusCode) {
		 this.orderStatusCode = orderStatusCode == null ? null : orderStatusCode.trim();
	}
	public String getShippingStatusCode() {
		return shippingStatusCode;
	}
	public void setShippingStatusCode(String shippingStatusCode) {
		 this.shippingStatusCode = shippingStatusCode == null ? null : shippingStatusCode.trim();
	}
	public String getPayStatusCode() {
		return payStatusCode;
	}
	public void setPayStatusCode(String payStatusCode) {
		 this.payStatusCode = payStatusCode == null ? null : payStatusCode.trim();
	}
	public String getOrderDoneStausCode() {
		return orderDoneStausCode;
	}
	public void setOrderDoneStausCode(String orderDoneStausCode) {
		 this.orderDoneStausCode = orderDoneStausCode == null ? null : orderDoneStausCode.trim();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		 this.username = username == null ? null : username.trim();
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		 this.country = country == null ? null : country.trim();
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		 this.province = province == null ? null : province.trim();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		 this.city = city == null ? null : city.trim();
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		 this.district = district == null ? null : district.trim();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		 this.address = address == null ? null : address.trim();
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		 this.zipcode = zipcode == null ? null : zipcode.trim();
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		 this.phone = phone == null ? null : phone.trim();
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		 this.tel = tel == null ? null : tel.trim();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		 this.email = email == null ? null : email.trim();
	}
	public Date getBestTime() {
		return bestTime;
	}
	public void setBestTime(Date bestTime) {
		this.bestTime = bestTime;
	}
	public String getPostscript() {
		return postscript;
	}
	public void setPostscript(String postscript) {
		 this.postscript = postscript == null ? null : postscript.trim();
	}
	public Integer getShippingId() {
		return shippingId;
	}
	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		 this.shippingName = shippingName == null ? null : shippingName.trim();
	}
	public BigDecimal getPostageFee() {
		return postageFee;
	}
	public void setPostageFee(BigDecimal postageFee) {
		this.postageFee = postageFee;
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
		 this.expressName = expressName == null ? null : expressName.trim();
	}
	public String getExpressCode() {
		return expressCode;
	}
	public void setExpressCode(String expressCode) {
		 this.expressCode = expressCode == null ? null : expressCode.trim();
	}
	public String getExpressOrder() {
		return expressOrder;
	}
	public void setExpressOrder(String expressOrder) {
		 this.expressOrder = expressOrder == null ? null : expressOrder.trim();
	}
	public String getPollStatus() {
		return pollStatus;
	}
	public void setPollStatus(String pollStatus) {
		 this.pollStatus = pollStatus == null ? null : pollStatus.trim();
	}
	public String getExpressStatus() {
		return expressStatus;
	}
	public void setExpressStatus(String expressStatus) {
		 this.expressStatus = expressStatus == null ? null : expressStatus.trim();
	}
	public String getExpressState() {
		return expressState;
	}
	public void setExpressState(String expressState) {
		 this.expressState = expressState == null ? null : expressState.trim();
	}
	public String getExpressJson() {
		return expressJson;
	}
	public void setExpressJson(String expressJson) {
		 this.expressJson = expressJson == null ? null : expressJson.trim();
	}
	public Date getExpressReceiveTime() {
		return expressReceiveTime;
	}
	public void setExpressReceiveTime(Date expressReceiveTime) {
		this.expressReceiveTime = expressReceiveTime;
	}
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		 this.payName = payName == null ? null : payName.trim();
	}
	public String getHowOos() {
		return howOos;
	}
	public void setHowOos(String howOos) {
		 this.howOos = howOos == null ? null : howOos.trim();
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

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public String getShopUsername() {
		return shopUsername;
	}

	public void setShopUsername(String shopUsername) {
		this.shopUsername = shopUsername;
	}
	public Integer getShopUserId() {
		return shopUserId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getWholesaleUserId() {
		return wholesaleUserId;
	}

	public void setShopUserId(Integer shopUserId) {
		this.shopUserId = shopUserId;
	}

	public void setWholesaleUserId(Integer wholesaleUserId) {
		this.wholesaleUserId = wholesaleUserId;
	}

	public String getExtensionCode() {
		return extensionCode;
	}

	public void setExtensionCode(String extensionCode) {
		this.extensionCode = extensionCode;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(Integer orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public String getGoodsAttr() {
		return goodsAttr;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}

	public String getOverComment() {
		return overComment;
	}

	public void setOverComment(String overComment) {
		this.overComment = overComment;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getProviderRealName() {
		return providerRealName;
	}
	public void setProviderRealName(String providerRealName) {
		this.providerRealName = providerRealName;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public BigDecimal getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(BigDecimal couponMoney) {
		this.couponMoney = couponMoney;
	}
	public Integer getCouponzhekou() {
		return couponzhekou;
	}
	public void setCouponzhekou(Integer couponzhekou) {
		this.couponzhekou = couponzhekou;
	}
	
	

	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}