package com.lhfeiyu.po.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Advertisement <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：广告表 <p>
 */
public class Advertisement extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	private String content;//内容
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 栏目ID（位置ID）  */
	private Integer catId;
	
	/** 发送人名称  */
	private String title;
	
	/** 图片ID串  */
	private String picIds;
	
	/** 图片路径  */
	private String picPath;
	
	/** 广告文件类型1:图片 2:第三方广告  */
	private Integer fileType;
	
	/** 宽度  */
	private String width;
	
	/** 高度  */
	private String height;
	
	/** 占几个位置  */
	private Integer cell;
	
	/** 点击数  */
	private Integer hits;
	
	/** 展示次数  */
	private Integer shows;
	
	/** 浏览次数  */
	private Integer scans;
	
	/** 所有人可见  */
	private String toAll;
	
	/** 价格  */
	private BigDecimal price;
	
	/** 有效期-从  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expiryDateFrom;
	
	/** 有效期-至  */
	private String expiryDateTo;
	
	/** 是否置顶  */
	private Integer isTop;
	
	/** 附件ID  */
	private Integer fileId;
	
	/** 链接地址  */
	private String linkUrl;
	
	
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
	public String getPicIds() {
		return picIds;
	}
	public void setPicIds(String picIds) {
		 this.picIds = picIds == null ? null : picIds.trim();
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		 this.picPath = picPath == null ? null : picPath.trim();
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		 this.width = width == null ? null : width.trim();
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		 this.height = height == null ? null : height.trim();
	}
	public Integer getCell() {
		return cell;
	}
	public void setCell(Integer cell) {
		this.cell = cell;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getShows() {
		return shows;
	}
	public void setShows(Integer shows) {
		this.shows = shows;
	}
	public Integer getScans() {
		return scans;
	}
	public void setScans(Integer scans) {
		this.scans = scans;
	}
	public String getToAll() {
		return toAll;
	}
	public void setToAll(String toAll) {
		 this.toAll = toAll == null ? null : toAll.trim();
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getExpiryDateFrom() {
		return expiryDateFrom;
	}
	public void setExpiryDateFrom(Date expiryDateFrom) {
		this.expiryDateFrom = expiryDateFrom;
	}
	public String getExpiryDateTo() {
		return expiryDateTo;
	}
	public void setExpiryDateTo(String expiryDateTo) {
		 this.expiryDateTo = expiryDateTo == null ? null : expiryDateTo.trim();
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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}