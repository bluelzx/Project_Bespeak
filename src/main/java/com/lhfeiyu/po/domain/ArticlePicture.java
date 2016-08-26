package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：ArticlePicture <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：文章（论坛，资讯）图片表 <p>
 */
public class ArticlePicture extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 图片属性商品的id  */
	private Integer articleId;
	
	/** 图片路径  */
	private String picPath;
	
	/** 用户ID  */
	private Integer userId;
	
	/** 栏目ID  */
	private Integer catId;
	
	/** 相册ID  */
	private Integer albumId;
	
	/** 是否为封面图片（主图片）2：是，其他：否  */
	private Integer isCover;
	
	/** 图片名称  */
	private String title;
	
	/** 内容  */
	private String content;
	
	/** 链接地址  */
	private String linkPath;
	
	/** 图片大小  */
	private Double size;
	
	/** 图片后缀名  */
	private String ext;
	
	/** 是否已经水印  */
	private String hasWatermark;
	
	/** 级别  */
	private Integer grade;
	
	/** 来源  */
	private String origin;
	
	/** 浏览次数  */
	private Integer scans;
	
	/** 点击次数  */
	private Integer hits;
	
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		 this.picPath = picPath == null ? null : picPath.trim();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	public Integer getIsCover() {
		return isCover;
	}
	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		 this.title = title == null ? null : title.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
	public String getLinkPath() {
		return linkPath;
	}
	public void setLinkPath(String linkPath) {
		 this.linkPath = linkPath == null ? null : linkPath.trim();
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		 this.ext = ext == null ? null : ext.trim();
	}
	public String getHasWatermark() {
		return hasWatermark;
	}
	public void setHasWatermark(String hasWatermark) {
		 this.hasWatermark = hasWatermark == null ? null : hasWatermark.trim();
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		 this.origin = origin == null ? null : origin.trim();
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
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}