package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Apply <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：申请表 <p>
 */
public class Apply extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userSerial;
	private String realName;
	private String userName;
	private String dictName;
	private String codeName;
	private String productTypeName;
	/**
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 机构ID  */
	private Integer instId;
	
	/** 申请类型（1专场申请，2论坛申请,3企业认证）  */
	private Integer applyType;
	
	/** 申请日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date applyDate;
	
	/** 消息  */
	private String message;
	
	/** 回复状态  */
	private String replyStatus;
	
	/** 回复说明  */
	private String replyDesc;
	
	/** 回复用户ID  */
	private Integer replyUserId;
	
	/** 回复用户名  */
	private String replyUsername;
	
	/** 附件1  */
	private String file1;
	
	/** 附件2  */
	private String file2;
	
	/** 附件3  */
	private String file3;
	
	/** 附件4  */
	private String file4;
	
	/** 属性1  */
	private String attr1;
	
	/** 属性2  */
	private String attr2;
	
	/** 属性3  */
	private String attr3;
	
	/** 属性4  */
	private String attr4;
	
	/** 省  */
	private Integer province;
	
	/** 市  */
	private Integer city;
	
	/** 地址  */
	private String address;
	
	/** 定位  */
	private String location;
	
	/** 手机   */
	private String phone;
	
	/** 性别（1男，2女）  */
	private Integer sex;
	
	/** 出生日期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	/** 职业  */
	private Integer jobId;
	
	/** 职称  */
	private String titleNames;
	
	/** 介绍  */
	private String introduction;
	
	/** 身份证  */
	private Integer idcardNum;
	
	/** 金额  */
	private BigDecimal money;
	
	/** 申请开通店铺，机构的名称  */
	private String shopName;
	
	/** 申请开通店铺，机构的LOGO  */
	private String shopLogo;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getInstId() {
		return instId;
	}
	public void setInstId(Integer instId) {
		this.instId = instId;
	}
	public Integer getApplyType() {
		return applyType;
	}
	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		 this.message = message == null ? null : message.trim();
	}
	public String getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(String replyStatus) {
		 this.replyStatus = replyStatus == null ? null : replyStatus.trim();
	}
	public String getReplyDesc() {
		return replyDesc;
	}
	public void setReplyDesc(String replyDesc) {
		 this.replyDesc = replyDesc == null ? null : replyDesc.trim();
	}
	public Integer getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}
	public String getReplyUsername() {
		return replyUsername;
	}
	public void setReplyUsername(String replyUsername) {
		 this.replyUsername = replyUsername == null ? null : replyUsername.trim();
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		 this.file1 = file1 == null ? null : file1.trim();
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		 this.file2 = file2 == null ? null : file2.trim();
	}
	public String getFile3() {
		return file3;
	}
	public void setFile3(String file3) {
		 this.file3 = file3 == null ? null : file3.trim();
	}
	public String getFile4() {
		return file4;
	}
	public void setFile4(String file4) {
		 this.file4 = file4 == null ? null : file4.trim();
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		 this.attr1 = attr1 == null ? null : attr1.trim();
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		 this.attr2 = attr2 == null ? null : attr2.trim();
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		 this.attr3 = attr3 == null ? null : attr3.trim();
	}
	public String getAttr4() {
		return attr4;
	}
	public void setAttr4(String attr4) {
		 this.attr4 = attr4 == null ? null : attr4.trim();
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		 this.location = location == null ? null : location.trim();
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		 this.phone = phone == null ? null : phone.trim();
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getTitleNames() {
		return titleNames;
	}
	public void setTitleNames(String titleNames) {
		 this.titleNames = titleNames == null ? null : titleNames.trim();
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		 this.introduction = introduction == null ? null : introduction.trim();
	}
	public Integer getIdcardNum() {
		return idcardNum;
	}
	public void setIdcardNum(Integer idcardNum) {
		this.idcardNum = idcardNum;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		 this.shopName = shopName == null ? null : shopName.trim();
	}
	public String getShopLogo() {
		return shopLogo;
	}
	public void setShopLogo(String shopLogo) {
		 this.shopLogo = shopLogo == null ? null : shopLogo.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getUserSerial() {
		return userSerial;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}