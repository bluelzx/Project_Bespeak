package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：CheckEmployee <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 记账-员工表 <p>
 */
public class CheckEmployee extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 用户名  */
	private String name;
	
	/** 用户密码（员工登陆记账）  */
	private String password;
	
	/** 老板-用户ID  */
	private Integer bossUserId;
	
	/** 老板-店铺ID  */
	private Integer bossShopId;
	
	/** 老板-用户ID-MD5加密  */
	private String bossUserIdMd5;
	
	/** 老板-店铺ID-MD5加密  */
	private String bossShopIdMd5;
	
	/** 最后登录时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
	
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
	public Integer getBossUserId() {
		return bossUserId;
	}
	public void setBossUserId(Integer bossUserId) {
		this.bossUserId = bossUserId;
	}
	public Integer getBossShopId() {
		return bossShopId;
	}
	public void setBossShopId(Integer bossShopId) {
		this.bossShopId = bossShopId;
	}
	public String getBossUserIdMd5() {
		return bossUserIdMd5;
	}
	public void setBossUserIdMd5(String bossUserIdMd5) {
		 this.bossUserIdMd5 = bossUserIdMd5 == null ? null : bossUserIdMd5.trim();
	}
	public String getBossShopIdMd5() {
		return bossShopIdMd5;
	}
	public void setBossShopIdMd5(String bossShopIdMd5) {
		 this.bossShopIdMd5 = bossShopIdMd5 == null ? null : bossShopIdMd5.trim();
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}