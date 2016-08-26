package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Investigation <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：调查报名申请表 <p>
 */
public class Investigation extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String courseName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 用户名  */
	private String username;
	
	/** 用户信息  */
	private String userInfo;
	
	/** 用户电话  */
	private String userPhone;
	
	/** 用户姓别  */
	private String userSex;
	
	/** 用户年龄  */
	private Integer userAge;
	
	/** 用户头像  */
	private String userAvatar;
	
	/** 申请时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date applyTime;
	
	/** 开始时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/** 结束时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	
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
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		 this.userInfo = userInfo == null ? null : userInfo.trim();
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		 this.userPhone = userPhone == null ? null : userPhone.trim();
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		 this.userSex = userSex == null ? null : userSex.trim();
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		 this.userAvatar = userAvatar == null ? null : userAvatar.trim();
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}