package com.lhfeiyu.po.domain;

/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/
import com.lhfeiyu.po.base.Parent;


/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-持久层对象：BaseForumArticleCollect <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年4月20日13:53:16<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 论坛：论坛文章收藏表 <p>
 */
public class ArticleCollect extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userAvatar;
	private String forumArticleTitle;
	private String userName;
	private String forumArticleCreatedAt;
	private Integer collectedUserId;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 论坛ID  */
	private Integer forumId;
	
	/** 文章ID  */
	private Integer articleId;
	
	
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
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	    public String getUserName() {
		return userName;
	}

	public String getForumArticleCreatedAt() {
		return forumArticleCreatedAt;
	}

	public void setForumArticleCreatedAt(String forumArticleCreatedAt) {
		this.forumArticleCreatedAt = forumArticleCreatedAt;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCollectedUserId() {
		return collectedUserId;
	}

	public void setCollectedUserId(Integer collectedUserId) {
		this.collectedUserId = collectedUserId;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public String getForumArticleTitle() {
		return forumArticleTitle;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public void setForumArticleTitle(String forumArticleTitle) {
		this.forumArticleTitle = forumArticleTitle;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}