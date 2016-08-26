package com.lhfeiyu.po.domain;

import java.util.Date;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;


/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Shop <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：店铺表 <p>
 */
public class Shop extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String picPaths;// 店铺里的藏品图片路径（前6张图片）
	private Integer goodsNum;// 藏品数量
	private String goodsNames;
	private String goodsPrice;
	private String userName;
	private String userSerial;
	private String antiqueCityName;
	private String allShopPicPaths;
	private List<String> picPathList;
	private Integer isRealAuth;
	private Integer antiqueCityId;
	private String cityName;//市名
	private String provinceName;//省名
	private String coorDinate;//地点坐标
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	
	/** 店铺名称  */
	private String name;
	
	/** 密码  */
	private String password;
	
	/** 上次登陆时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
	/** 上次登陆IP  */
	private String lastLoginIp;
	
	/** 店铺图标  */
	private String logo;
	
	/** 省  */
	private Integer province;
	
	/** 市  */
	private Integer city;
	
	/** 店铺地址  */
	private String address;
	
	/** 营业执照  */
	private String licence;
	
	/** 营业执照路径  */
	private String licencePaths;
	
	/** 组织机构代码  */
	private String organizationCode;
	
	/** 成员数量  */
	private Integer memberNum;
	
	/** 电话号码  */
	private String phone;
	
	/** 电话号码2  */
	private String phone2;
	
	/** 担保交易(1.否2.是)  */
	private Integer isWarrant;
	
	/** 是否七天退货(1.否2.是)  */
	private Integer isSevenReturn;
	
	/** 是否包邮(1.否2.是)  */
	private Integer isFreePostage;
	
	/** 信誉保证金  */
	private Integer creditMargin;
	
	/** 等级  */
	private Integer grade;
	
	/** 评分  */
	private String score;
	
	/** 是否电脑端代上传藏品(1否，2是)  */
	private Integer isPcUploadAllowed;
	
	/** 附件路径  */
	private String filePaths;
	
	/**   */
	private String filePaths2;
	
	
	
	
	
	
	public String getCoorDinate() {
		return coorDinate;
	}
	public void setCoorDinate(String coorDinate) {
		this.coorDinate = coorDinate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		 this.name = name == null ? null : name.trim();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		 this.password = password == null ? null : password.trim();
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		 this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		 this.logo = logo == null ? null : logo.trim();
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
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		 this.licence = licence == null ? null : licence.trim();
	}
	public String getLicencePaths() {
		return licencePaths;
	}
	public void setLicencePaths(String licencePaths) {
		 this.licencePaths = licencePaths == null ? null : licencePaths.trim();
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		 this.organizationCode = organizationCode == null ? null : organizationCode.trim();
	}
	public Integer getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		 this.phone = phone == null ? null : phone.trim();
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		 this.phone2 = phone2 == null ? null : phone2.trim();
	}
	public Integer getIsWarrant() {
		return isWarrant;
	}
	public void setIsWarrant(Integer isWarrant) {
		this.isWarrant = isWarrant;
	}
	public Integer getIsSevenReturn() {
		return isSevenReturn;
	}
	public void setIsSevenReturn(Integer isSevenReturn) {
		this.isSevenReturn = isSevenReturn;
	}
	public Integer getIsFreePostage() {
		return isFreePostage;
	}
	public void setIsFreePostage(Integer isFreePostage) {
		this.isFreePostage = isFreePostage;
	}
	public Integer getCreditMargin() {
		return creditMargin;
	}
	public void setCreditMargin(Integer creditMargin) {
		this.creditMargin = creditMargin;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		 this.score = score == null ? null : score.trim();
	}
	public Integer getIsPcUploadAllowed() {
		return isPcUploadAllowed;
	}
	public void setIsPcUploadAllowed(Integer isPcUploadAllowed) {
		this.isPcUploadAllowed = isPcUploadAllowed;
	}
	public String getFilePaths() {
		return filePaths;
	}
	public void setFilePaths(String filePaths) {
		 this.filePaths = filePaths == null ? null : filePaths.trim();
	}
	public String getFilePaths2() {
		return filePaths2;
	}
	public void setFilePaths2(String filePaths2) {
		 this.filePaths2 = filePaths2 == null ? null : filePaths2.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public Integer getAntiqueCityId() {
		return antiqueCityId;
	}

	public void setAntiqueCityId(Integer antiqueCityId) {
		this.antiqueCityId = antiqueCityId;
	}

	public String getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}

	public String getAntiqueCityName() {
		return antiqueCityName;
	}

	public void setAntiqueCityName(String antiqueCityName) {
		this.antiqueCityName = antiqueCityName;
	}

	public String getAllShopPicPaths() {
		return allShopPicPaths;
	}

	public void setAllShopPicPaths(String allShopPicPaths) {
		this.allShopPicPaths = allShopPicPaths;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getPicPathList() {
		return picPathList;
	}

	public void setPicPathList(List<String> picPathList) {
		this.picPathList = picPathList;
	}

	public Integer getIsRealAuth() {
		return isRealAuth;
	}

	public void setIsRealAuth(Integer isRealAuth) {
		this.isRealAuth = isRealAuth;
	}

	public String getPicPaths() {
		return picPaths;
	}

	public void setPicPaths(String picPaths) {
		this.picPaths = picPaths;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsNames() {
		return goodsNames;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsNames(String goodsNames) {
		this.goodsNames = goodsNames;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}