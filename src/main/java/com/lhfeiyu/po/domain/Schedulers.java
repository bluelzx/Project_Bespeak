package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Schedulers <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：任务调度表 <p>
 */
public class Schedulers extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 任务类型ID(1开拍提醒)  */
	private Integer taskTypeId;
	
	/** 通知ID(1专场开拍提醒)  */
	private Integer noticeId;
	
	/** 关联连接地址  */
	private String linkUrl;
	
	/** 用户ID  */
	private Integer userId;
	
	/** 接收人ID  */
	private Integer receiverId;
	
	/** 接收人ID串  */
	private String receiverIds;
	
	/** 已执行次数  */
	private Integer executedNum;
	
	/** 图片路径  */
	private String picPath;
	
	/** 图片ID  */
	private Integer picId;
	
	/** 等级  */
	private Integer grade;
	
	/** 是否起效  */
	private String isValid;
	
	/** 触发条件-key  */
	private String triggerKey;
	
	/** 触发条件-value  */
	private String triggerValue;
	
	/** 开始时间（触发时间）  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/** 结束时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	
	public Integer getTaskTypeId() {
		return taskTypeId;
	}
	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		 this.linkUrl = linkUrl == null ? null : linkUrl.trim();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverIds() {
		return receiverIds;
	}
	public void setReceiverIds(String receiverIds) {
		 this.receiverIds = receiverIds == null ? null : receiverIds.trim();
	}
	public Integer getExecutedNum() {
		return executedNum;
	}
	public void setExecutedNum(Integer executedNum) {
		this.executedNum = executedNum;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		 this.picPath = picPath == null ? null : picPath.trim();
	}
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		 this.isValid = isValid == null ? null : isValid.trim();
	}
	public String getTriggerKey() {
		return triggerKey;
	}
	public void setTriggerKey(String triggerKey) {
		 this.triggerKey = triggerKey == null ? null : triggerKey.trim();
	}
	public String getTriggerValue() {
		return triggerValue;
	}
	public void setTriggerValue(String triggerValue) {
		 this.triggerValue = triggerValue == null ? null : triggerValue.trim();
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
	





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}