package com.lhfeiyu.po.domain;

/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/
import java.util.List;

import com.lhfeiyu.po.base.Parent;


/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：ForumArticle <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 论坛：论坛文章表 <p>
 */
public class ForumArticle extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String username;
	private String userAvatar;
	private String userSerial;
	private String shopName;
	private Integer shopId;
	private Integer overCollect;
	private String shopLogo;
	private String compareNow;
	private List<String> picPathList;
	private String forumName;
	private Integer showArticleCount;
	private String typeCode;//帖子类型（话题、晒好物）
	private Integer typeId;//关联goodsId
	private Integer isPraise;//是否点赞
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 论坛ID  */
	private Integer forumId;
	
	/** 标题  */
	private String title;
	
	/** 副标题  */
	private String subTitle;
	
	/** 内容  */
	private String content;
	
	/** 关联图片路径  */
	private String picPaths;
	
	/** 点赞数量  */
	private Integer praiseNum;
	
	/** 作者id  */
	private Integer authorId;
	
	/** 关联的用户ID  */
	private Integer userId;
	
	/** 关联的用户ID串  */
	private String userIds;
	
	/** 查看次数  */
	private Integer visitNum;
	
	/** 是否是精华 1:否2:是  */
	private Integer isEssence;
	
	/** 是否是置顶 1:否2:是  */
	private Integer isTop;
	
	/** 是否是热门 1:否2:是  */
	private Integer isHot;
	
	/** 是否是推荐 1:否2:是  */
	private Integer isRecommend;
	
	
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		 this.title = title == null ? null : title.trim();
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		 this.subTitle = subTitle == null ? null : subTitle.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
	public String getPicPaths() {
		return picPaths;
	}
	public void setPicPaths(String picPaths) {
		 this.picPaths = picPaths == null ? null : picPaths.trim();
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		 this.userIds = userIds == null ? null : userIds.trim();
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	public Integer getIsEssence() {
		return isEssence;
	}
	public void setIsEssence(Integer isEssence) {
		this.isEssence = isEssence;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public Integer getOverCollect() {
		return overCollect;
	}

	public void setOverCollect(Integer overCollect) {
		this.overCollect = overCollect;
	}
	public String getCompareNow() {
		return compareNow;
	}

	public void setCompareNow(String compareNow) {
		this.compareNow = compareNow;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public List<String> getPicPathList() {
		return picPathList;
	}

	public void setPicPathList(List<String> picPathList) {
		this.picPathList = picPathList;
	}

	public String getShopName() {
		return shopName;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getUserAvatar() {
		return userAvatar;
	}
	public String getForumName() {
		return forumName;
	}   

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserSerial() {
		return userSerial;
	}
	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}
	public Integer getShowArticleCount() {
		return showArticleCount;
	}
	public void setShowArticleCount(Integer showArticleCount) {
		this.showArticleCount = showArticleCount;
	}
	public Integer getIsPraise() {
		return isPraise;
	}
	public void setIsPraise(Integer isPraise) {
		this.isPraise = isPraise;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}