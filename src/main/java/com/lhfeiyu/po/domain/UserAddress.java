package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：UserAddress <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：用户地址表 <p>
 */
public class UserAddress extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userName;
	private String provinceName;
	private String cityName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 该条收藏记录的会员id  */
	private Integer userId;
	
	/** 收货人详细地址  */
	private String addressDetail;
	
	/** 收货人联系电话  */
	private String phone;
	
	/** 收货人姓名  */
	private String receiverName;
	
	/** 收货人所在省或直辖市  */
	private Integer province;
	
	/** 收货人所在城市  */
	private Integer city;
	
	/** 是否为默认地址  */
	private Integer isDefault;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		 this.addressDetail = addressDetail == null ? null : addressDetail.trim();
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		 this.phone = phone == null ? null : phone.trim();
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		 this.receiverName = receiverName == null ? null : receiverName.trim();
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
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	    public String getUserName() {
		return userName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}