package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Album <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：相册表 <p>
 */
public class Album extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userSerial;
	private String username;
	private String realName;
	private Integer shopId;
	private String shopSerial;
	private String shopName;
	private String picUrls;
	private String typeName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 栏目ID  */
	private Integer catId;
	
	/** 标题  */
	private String title;
	
	/** 子标题  */
	private String subTitle;
	
	/** 图片路径  */
	private String picPath;
	
	/** 图片ID  */
	private Integer picId;
	
	/** 机构  */
	private String institution;
	
	/** 链接地址  */
	private String linkUrl;
	
	/** 自定义属性1  */
	private String attr1;
	
	/** 自定义属性2  */
	private String attr2;
	
	/** 是否热门(1：否，2：是）  */
	private Integer isHot;
	
	/** 是否推荐(1：否，2：是）  */
	private Integer isRecommend;
	
	/** 是否首页展示(1：否，2：是）  */
	private Integer isShowIndex;
	
	/** 是否精华(1：否，2：是）  */
	private Integer isGood;
	
	/** 是否置顶(1：否，2：是）  */
	private Integer isTop;
	
	/** 用户ID  */
	private Integer userId;
	
	/** 作者  */
	private String author;
	
	/** 关键字  */
	private String keywords;
	
	/** 附件ID  */
	private Integer fileId;
	
	/** 浏览次数  */
	private Integer scans;
	
	/** 点击次数  */
	private Integer hits;
	
	/** 是否允许评论  */
	private String allowComment;
	
	/** 来源  */
	private String orgin;
	
	
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
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
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		 this.institution = institution == null ? null : institution.trim();
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		 this.linkUrl = linkUrl == null ? null : linkUrl.trim();
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		 this.attr1 = attr1 == null ? null : attr1.trim();
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		 this.attr2 = attr2 == null ? null : attr2.trim();
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
	public Integer getIsShowIndex() {
		return isShowIndex;
	}
	public void setIsShowIndex(Integer isShowIndex) {
		this.isShowIndex = isShowIndex;
	}
	public Integer getIsGood() {
		return isGood;
	}
	public void setIsGood(Integer isGood) {
		this.isGood = isGood;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		 this.author = author == null ? null : author.trim();
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		 this.keywords = keywords == null ? null : keywords.trim();
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public Integer getScans() {
		return scans;
	}
	public void setScans(Integer scans) {
		this.scans = scans;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public String getAllowComment() {
		return allowComment;
	}
	public void setAllowComment(String allowComment) {
		 this.allowComment = allowComment == null ? null : allowComment.trim();
	}
	public String getOrgin() {
		return orgin;
	}
	public void setOrgin(String orgin) {
		 this.orgin = orgin == null ? null : orgin.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	    public String getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopSerial() {
		return shopSerial;
	}

	public void setShopSerial(String shopSerial) {
		this.shopSerial = shopSerial;
	}
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}