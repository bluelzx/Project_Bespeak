package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：ChatQuick <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：聊天记录表-即时拍 <p>
 */
public class ChatQuick extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 发送人ID  */
	private Integer senderId;
	
	/** 群组ID  */
	private String chatGroupId;
	
	/** 消息类型：普通，出价，系统，主持人等  */
	private String msgGroup;
	
	/** 消息类型1:文本消息 2:语音消息 3:视频消息  4:图片消息  5:位置消息  6:文件  */
	private Integer contentType;
	
	/** 主题  */
	private String subject;
	
	/** 内容  */
	private String content;
	
	/** 发送时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime;
	
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
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}