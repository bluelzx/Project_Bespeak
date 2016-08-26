package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Provider <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：服务提供者表 <p>
 */
public class Provider extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String shopName;
	private String cityName;
	private String provinceName;
	private String resetPassword;
	private Integer totalRank;
	private BigDecimal avaliableMoney;// 可用金额
	private BigDecimal frozenMoney;// 冻结资金
	private String coorDinate;//地点坐标
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 类型名称  */
	private String typeNames;
	
	/** 店铺ID  */
	private Integer shopId;
	
	/** 用户名  */
	private String username;
	
	/** 用户密码  */
	private String password;
	
	/** 昵称  */
	private String nickName;
	
	/** 真实姓名  */
	private String realName;
	
	/** 是否实名认证  */
	private Integer isRealAuth;
	
	/** 性别：1女，2：男  */
	private Integer sex;
	
	/** 用户手机号码  */
	private String phone;
	
	/** 登录id  */
	private String loginName;
	
	/** 座机  */
	private String tel;
	
	/** 手机是否公开  */
	private Integer phoneConceal;
	
	/** 邮箱  */
	private String email;
	
	/** 用户头像  */
	private String avatar;
	
	/** 图片路径  */
	private String picPath;
	
	/** 多张图片  */
	private String picPaths;
	
	/** 联系方式  */
	private String contactWay;
	
	/** 最后登录时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
	/** 最后登录IP  */
	private String lastLoginIp;
	
	/** 详细地址  */
	private String address;
	
	/** 省份  */
	private Integer province;
	
	/** 城市  */
	private Integer city;
	
	/** 微信  */
	private String weixin;
	
	/** QQ  */
	private String qq;
	
	/** 微博  */
	private String weibo;
	
	/** 工作ID  */
	private Integer jobId;
	
	/** 工作代码  */
	private String jobCode;
	
	/** 出生日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	/** 职称代码  */
	private String titleCode;
	
	/** 二维码  */
	private String qrCode;
	
	/** 二维码2  */
	private String qrCode2;
	
	/** 第三方平台的名称  */
	private String thirdName;
	
	/** 第三方ID（聊天接口等）  */
	private String thirdId;
	
	/** 第三方平台的密码  */
	private String thirdPassword;
	
	/** 微信openid（标识微信用户）  */
	private String wxOpenid;
	
	/** 角色ID：1.普通用户2.管理员  */
	private Integer roleId;
	
	/** 公司，团队，机构  */
	private String organization;
	
	/** 身份证号  */
	private String idcardNum;
	
	/** 证件类型  */
	private Integer certificateType;
	
	/** 证件号码  */
	private String certificateNumber;
	
	/** 证件姓名  */
	private String certificateName;
	
	/** 访问数量  */
	private Integer visitNum;
	
	/** 点赞数量  */
	private Integer praiseNum;
	
	/** 关注数量  */
	private Integer focusNum;
	
	/** 其他数值记录  */
	private Integer otherNum;
	
	/** 关联ID2  */
	private Integer linkId2;
	
	/** 关联ID3  */
	private Integer linkId3;
	
	/** 关联ID4  */
	private Integer linkId4;
	
	/** 关联ID5  */
	private Integer linkId5;
	
	/** 关联代码  */
	private String linkCode;
	
	/** 关联代码2  */
	private String linkCode2;
	
	/** 关联代码3  */
	private String linkCode3;
	
	/** 关联代码4  */
	private String linkCode4;
	
	/** 关联代码5  */
	private String linkCode5;
	
	/** 关联字符串  */
	private String linkStr;
	
	/** json格式参数  */
	private String paramJson;
	
	/**   */
	private String attrStr3;
	
	/** 简介  */
	private String introduction;
	
	/** 标签  */
	private String tags;
	
	/** 业务范围  */
	private String bussinessScope;
	
	/** 学历  */
	private String educationCode;
	
	/** 善长领域ID串  */
	private String goodAtIds;
	
	/** 擅长领域  */
	private String goodAt;
	
	/** 职称名称  */
	private String titleNames;
	
	/** 职称ID串  */
	private String titleIds;
	
	/** 职位ID串  */
	private String positionIds;
	
	/** 职位名称  */
	private String positionNames;
	
	/**   */
	private BigDecimal price;
	
	/**   */
	private String priceText;
	
	/** 工作年限  */
	private Integer workYear;
	
	
	public String getTypeNames() {
		return typeNames;
	}
	public void setTypeNames(String typeNames) {
		 this.typeNames = typeNames == null ? null : typeNames.trim();
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		 this.username = username == null ? null : username.trim();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		 this.password = password == null ? null : password.trim();
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		 this.nickName = nickName == null ? null : nickName.trim();
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		 this.realName = realName == null ? null : realName.trim();
	}
	public Integer getIsRealAuth() {
		return isRealAuth;
	}
	public void setIsRealAuth(Integer isRealAuth) {
		this.isRealAuth = isRealAuth;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		 this.phone = phone == null ? null : phone.trim();
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		 this.loginName = loginName == null ? null : loginName.trim();
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		 this.tel = tel == null ? null : tel.trim();
	}
	public Integer getPhoneConceal() {
		return phoneConceal;
	}
	public void setPhoneConceal(Integer phoneConceal) {
		this.phoneConceal = phoneConceal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		 this.email = email == null ? null : email.trim();
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		 this.avatar = avatar == null ? null : avatar.trim();
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		 this.picPath = picPath == null ? null : picPath.trim();
	}
	public String getPicPaths() {
		return picPaths;
	}
	public void setPicPaths(String picPaths) {
		 this.picPaths = picPaths == null ? null : picPaths.trim();
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		 this.contactWay = contactWay == null ? null : contactWay.trim();
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		 this.address = address == null ? null : address.trim();
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
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		 this.weixin = weixin == null ? null : weixin.trim();
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		 this.qq = qq == null ? null : qq.trim();
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		 this.weibo = weibo == null ? null : weibo.trim();
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		 this.jobCode = jobCode == null ? null : jobCode.trim();
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		 this.titleCode = titleCode == null ? null : titleCode.trim();
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		 this.qrCode = qrCode == null ? null : qrCode.trim();
	}
	public String getQrCode2() {
		return qrCode2;
	}
	public void setQrCode2(String qrCode2) {
		 this.qrCode2 = qrCode2 == null ? null : qrCode2.trim();
	}
	public String getThirdName() {
		return thirdName;
	}
	public void setThirdName(String thirdName) {
		 this.thirdName = thirdName == null ? null : thirdName.trim();
	}
	public String getThirdId() {
		return thirdId;
	}
	public void setThirdId(String thirdId) {
		 this.thirdId = thirdId == null ? null : thirdId.trim();
	}
	public String getThirdPassword() {
		return thirdPassword;
	}
	public void setThirdPassword(String thirdPassword) {
		 this.thirdPassword = thirdPassword == null ? null : thirdPassword.trim();
	}
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		 this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		 this.organization = organization == null ? null : organization.trim();
	}
	public String getIdcardNum() {
		return idcardNum;
	}
	public void setIdcardNum(String idcardNum) {
		 this.idcardNum = idcardNum == null ? null : idcardNum.trim();
	}
	public Integer getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		 this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
	}
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		 this.certificateName = certificateName == null ? null : certificateName.trim();
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	public Integer getFocusNum() {
		return focusNum;
	}
	public void setFocusNum(Integer focusNum) {
		this.focusNum = focusNum;
	}
	public Integer getOtherNum() {
		return otherNum;
	}
	public void setOtherNum(Integer otherNum) {
		this.otherNum = otherNum;
	}
	public Integer getLinkId2() {
		return linkId2;
	}
	public void setLinkId2(Integer linkId2) {
		this.linkId2 = linkId2;
	}
	public Integer getLinkId3() {
		return linkId3;
	}
	public void setLinkId3(Integer linkId3) {
		this.linkId3 = linkId3;
	}
	public Integer getLinkId4() {
		return linkId4;
	}
	public void setLinkId4(Integer linkId4) {
		this.linkId4 = linkId4;
	}
	public Integer getLinkId5() {
		return linkId5;
	}
	public void setLinkId5(Integer linkId5) {
		this.linkId5 = linkId5;
	}
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		 this.linkCode = linkCode == null ? null : linkCode.trim();
	}
	public String getLinkCode2() {
		return linkCode2;
	}
	public void setLinkCode2(String linkCode2) {
		 this.linkCode2 = linkCode2 == null ? null : linkCode2.trim();
	}
	public String getLinkCode3() {
		return linkCode3;
	}
	public void setLinkCode3(String linkCode3) {
		 this.linkCode3 = linkCode3 == null ? null : linkCode3.trim();
	}
	public String getLinkCode4() {
		return linkCode4;
	}
	public void setLinkCode4(String linkCode4) {
		 this.linkCode4 = linkCode4 == null ? null : linkCode4.trim();
	}
	public String getLinkCode5() {
		return linkCode5;
	}
	public void setLinkCode5(String linkCode5) {
		 this.linkCode5 = linkCode5 == null ? null : linkCode5.trim();
	}
	public String getLinkStr() {
		return linkStr;
	}
	public void setLinkStr(String linkStr) {
		 this.linkStr = linkStr == null ? null : linkStr.trim();
	}
	public String getParamJson() {
		return paramJson;
	}
	public void setParamJson(String paramJson) {
		 this.paramJson = paramJson == null ? null : paramJson.trim();
	}
	public String getAttrStr3() {
		return attrStr3;
	}
	public void setAttrStr3(String attrStr3) {
		 this.attrStr3 = attrStr3 == null ? null : attrStr3.trim();
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		 this.introduction = introduction == null ? null : introduction.trim();
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		 this.tags = tags == null ? null : tags.trim();
	}
	public String getBussinessScope() {
		return bussinessScope;
	}
	public void setBussinessScope(String bussinessScope) {
		 this.bussinessScope = bussinessScope == null ? null : bussinessScope.trim();
	}
	public String getEducationCode() {
		return educationCode;
	}
	public void setEducationCode(String educationCode) {
		 this.educationCode = educationCode == null ? null : educationCode.trim();
	}
	public String getGoodAtIds() {
		return goodAtIds;
	}
	public void setGoodAtIds(String goodAtIds) {
		 this.goodAtIds = goodAtIds == null ? null : goodAtIds.trim();
	}
	public String getGoodAt() {
		return goodAt;
	}
	public void setGoodAt(String goodAt) {
		 this.goodAt = goodAt == null ? null : goodAt.trim();
	}
	public String getTitleNames() {
		return titleNames;
	}
	public void setTitleNames(String titleNames) {
		 this.titleNames = titleNames == null ? null : titleNames.trim();
	}
	public String getTitleIds() {
		return titleIds;
	}
	public void setTitleIds(String titleIds) {
		 this.titleIds = titleIds == null ? null : titleIds.trim();
	}
	public String getPositionIds() {
		return positionIds;
	}
	public void setPositionIds(String positionIds) {
		 this.positionIds = positionIds == null ? null : positionIds.trim();
	}
	public String getPositionNames() {
		return positionNames;
	}
	public void setPositionNames(String positionNames) {
		 this.positionNames = positionNames == null ? null : positionNames.trim();
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPriceText() {
		return priceText;
	}
	public void setPriceText(String priceText) {
		 this.priceText = priceText == null ? null : priceText.trim();
	}
	public Integer getWorkYear() {
		return workYear;
	}
	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
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
	public String getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}
	public Integer getTotalRank() {
		return totalRank;
	}
	public void setTotalRank(Integer totalRank) {
		this.totalRank = totalRank;
	}
	public BigDecimal getAvaliableMoney() {
		return avaliableMoney;
	}
	public void setAvaliableMoney(BigDecimal avaliableMoney) {
		this.avaliableMoney = avaliableMoney;
	}
	public BigDecimal getFrozenMoney() {
		return frozenMoney;
	}
	public void setFrozenMoney(BigDecimal frozenMoney) {
		this.frozenMoney = frozenMoney;
	}
	public String getCoorDinate() {
		return coorDinate;
	}
	public void setCoorDinate(String coorDinate) {
		this.coorDinate = coorDinate;
	}
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}