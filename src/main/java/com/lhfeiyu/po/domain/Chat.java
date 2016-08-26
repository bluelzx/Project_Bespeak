package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Chat <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：聊天记录表 <p>
 */
public class Chat extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String senderAvatar;
	private String receiverName;
	private String receiverAvatar;
	private String senderSerial;
	private String receiverSerial;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 发送人ID  */
	private Integer senderId;
	
	/** 发送人名称  */
	private String senderName;
	
	/** 接收人ID  */
	private Integer receiverId;
	
	/** 接收人ID串  */
	private String receiverIds;
	
	/** 接收组ID  */
	private Integer receiverGroupId;
	
	/** 群组ID  */
	private String chatGroupId;
	
	/** 消息类型组  */
	private String msgGroup;
	
	/** 消息类型1:文本消息 2:语音消息 3:视频消息  4:图片消息  5:位置消息  6:文件  */
	private Integer contentType;
	
	/** 标题  */
	private String title;
	
	/** 主题  */
	private String subject;
	
	/** 内容  */
	private String content;
	
	/** 发送时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime;
	
	/** 阅读时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date readTime;
	
	/** 隐私等级  */
	private Integer concealLevel;
	
	/**   */
	private String toAll;
	
	/** 有效期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expiryDate;
	
	/** 是否匿名  */
	private String anonymous;
	
	/** 是否置顶  */
	private Integer isTop;
	
	/** 附件ID  */
	private Integer fileId;
	
	/** 链接地址  */
	private String linkUrl;
	
	
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		 this.senderName = senderName == null ? null : senderName.trim();
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
	public Integer getReceiverGroupId() {
		return receiverGroupId;
	}
	public void setReceiverGroupId(Integer receiverGroupId) {
		this.receiverGroupId = receiverGroupId;
	}
	public String getChatGroupId() {
		return chatGroupId;
	}
	public void setChatGroupId(String chatGroupId) {
		 this.chatGroupId = chatGroupId == null ? null : chatGroupId.trim();
	}
	public String getMsgGroup() {
		return msgGroup;
	}
	public void setMsgGroup(String msgGroup) {
		 this.msgGroup = msgGroup == null ? null : msgGroup.trim();
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		 this.title = title == null ? null : title.trim();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		 this.subject = subject == null ? null : subject.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public Integer getConcealLevel() {
		return concealLevel;
	}
	public void setConcealLevel(Integer concealLevel) {
		this.concealLevel = concealLevel;
	}
	public String getToAll() {
		return toAll;
	}
	public void setToAll(String toAll) {
		 this.toAll = toAll == null ? null : toAll.trim();
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(String anonymous) {
		 this.anonymous = anonymous == null ? null : anonymous.trim();
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		 this.linkUrl = linkUrl == null ? null : linkUrl.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getSenderSerial() {
		return senderSerial;
	}

	public String getReceiverSerial() {
		return receiverSerial;
	}

	public void setSenderSerial(String senderSerial) {
		this.senderSerial = senderSerial;
	}

	public void setReceiverSerial(String receiverSerial) {
		this.receiverSerial = receiverSerial;
	}

	public String getSenderAvatar() {
		return senderAvatar;
	}

	public void setSenderAvatar(String senderAvatar) {
		this.senderAvatar = senderAvatar;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAvatar() {
		return receiverAvatar;
	}

	public void setReceiverAvatar(String receiverAvatar) {
		this.receiverAvatar = receiverAvatar;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}