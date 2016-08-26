package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Meeting <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 管理系统：会议表 <p>
 */
public class Meeting extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 会议名称  */
	private String name;
	
	/** 发起人（召集人）名称  */
	private String initiator;
	
	/** 发起人（召集人）编号  */
	private Integer initiatorId;
	
	/** 联系人  */
	private String linkman;
	
	/** 联系方式  */
	private String linkway;
	
	/** 会议地址  */
	private String address;
	
	/** 会议内容  */
	private String content;
	
	/** 开始时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	
	/** 结束时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	/** 会议成员ID串  */
	private String memberIds;
	
	/** 会议成员名字串  */
	private String memberNames;
	
	/** 提醒时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date noticeTime;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		 this.name = name == null ? null : name.trim();
	}
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		 this.initiator = initiator == null ? null : initiator.trim();
	}
	public Integer getInitiatorId() {
		return initiatorId;
	}
	public void setInitiatorId(Integer initiatorId) {
		this.initiatorId = initiatorId;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		 this.linkman = linkman == null ? null : linkman.trim();
	}
	public String getLinkway() {
		return linkway;
	}
	public void setLinkway(String linkway) {
		 this.linkway = linkway == null ? null : linkway.trim();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		 this.address = address == null ? null : address.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getMemberIds() {
		return memberIds;
	}
	public void setMemberIds(String memberIds) {
		 this.memberIds = memberIds == null ? null : memberIds.trim();
	}
	public String getMemberNames() {
		return memberNames;
	}
	public void setMemberNames(String memberNames) {
		 this.memberNames = memberNames == null ? null : memberNames.trim();
	}
	public Date getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}