package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Comment <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：评论表 <p>
 */
public class Comment extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String comUserName;
	private String userAvatar;
	private String receiverAvatar;
	private Integer showReplyCount;
	private Integer praiseNum;
	private String thisTitle;
	private String providerName;
	private String goodsName;
	private Integer orderInfoId;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 数据字典代码：评论类型  */
	private String commentTypeCode;
	
	/** 文章或者商品的id  */
	private Integer objectId;
	
	/** 内容  */
	private String content;
	
	/** 评论人ID  */
	private Integer userId;
	
	/** 评论人姓名  */
	private String username;
	
	/** 被评论人ID  */
	private Integer receiverId;
	
	/** 被评论人名称  */
	private String receiverName;
	
	/** 评论时的IP地址  */
	private String userIp;
	
	/** 评论打分星级,只有1到5星;其中5代表5星  */
	private Integer commentRank;
	
	/** 等级评价2  */
	private Integer commentRank2;
	
	/** 等级评价3  */
	private Integer commentRank3;
	
	/**   */
	private String picPaths;
	
	/** 评论的父节点,取值该表的comment_id字段,如果该字段为0,则是一个普通评论,否则该条评论就是该字段的值所对应的评论的回复  */
	private Integer parentId;
	
	/** 是否置顶  */
	private Integer isTop;
	
	
	public String getCommentTypeCode() {
		return commentTypeCode;
	}
	public void setCommentTypeCode(String commentTypeCode) {
		 this.commentTypeCode = commentTypeCode == null ? null : commentTypeCode.trim();
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
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
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		 this.receiverName = receiverName == null ? null : receiverName.trim();
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		 this.userIp = userIp == null ? null : userIp.trim();
	}
	public Integer getCommentRank() {
		return commentRank;
	}
	public void setCommentRank(Integer commentRank) {
		this.commentRank = commentRank;
	}
	public Integer getCommentRank2() {
		return commentRank2;
	}
	public void setCommentRank2(Integer commentRank2) {
		this.commentRank2 = commentRank2;
	}
	public Integer getCommentRank3() {
		return commentRank3;
	}
	public void setCommentRank3(Integer commentRank3) {
		this.commentRank3 = commentRank3;
	}
	public String getPicPaths() {
		return picPaths;
	}
	public void setPicPaths(String picPaths) {
		 this.picPaths = picPaths == null ? null : picPaths.trim();
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getReceiverAvatar() {
		return receiverAvatar;
	}

	public void setReceiverAvatar(String receiverAvatar) {
		this.receiverAvatar = receiverAvatar;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}


	public String getComUserName() {
		return comUserName;
	}
	public void setComUserName(String comUserName) {
		this.comUserName = comUserName;
	}
	public Integer getShowReplyCount() {
		return showReplyCount;
	}
	public void setShowReplyCount(Integer showReplyCount) {
		this.showReplyCount = showReplyCount;
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	public String getThisTitle() {
		return thisTitle;
	}
	public void setThisTitle(String thisTitle) {
		this.thisTitle = thisTitle;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getOrderInfoId() {
		return orderInfoId;
	}
	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}