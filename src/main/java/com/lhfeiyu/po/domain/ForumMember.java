package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：ForumMember <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 论坛：论坛成员表 <p>
 */
public class ForumMember extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String forumLogo;
	private String forumName;
	private String userSerial;
	private String username;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 论坛ID  */
	private Integer forumId;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	    public String getUserSerial() {
		return userSerial;
	}

	public String getUsername() {
		return username;
	}

	public String getRealName() {
		return realName;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	private String realName;
	public String getForumLogo() {
		return forumLogo;
	}


	public String getForumName() {
		return forumName;
	}

	public void setForumLogo(String forumLogo) {
		this.forumLogo = forumLogo;
	}


	public void setForumName(String forumName) {
		this.forumName = forumName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}