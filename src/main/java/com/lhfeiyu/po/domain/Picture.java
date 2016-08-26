package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Picture <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：图片表 <p>
 */
public class Picture extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userSerial;
	private String username;
	private String realName;
	private Integer shopId;
	private String shopSerial;
	private String shopName;
	private String picUrls;
	private String albumName;
	private String typeName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 栏目ID  */
	private Integer catId;
	
	/** 用户ID  */
	private Integer userId;
	
	/** 图片名称  */
	private String title;
	
	/** 图片路径  */
	private String picPath;
	
	/** 本地图片路径  */
	private String localPicPath;
	
	/** OSS服务器图片路径  */
	private String ossPicPath;
	
	/** 链接地址  */
	private String linkPath;
	
	/** 相册ID  */
	private Integer albumId;
	
	/** 有效期  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expiryDate;
	
	/** 是否为封面  */
	private Integer isCover;
	
	/** 是否为主图片  */
	private Integer isMain;
	
	/** 是不断缩略图  */
	private Integer isThumb;
	
	/** 图片大小  */
	private Double size;
	
	/** 图片后缀名  */
	private String ext;
	
	/** 内容  */
	private String content;
	
	/** 是否已经水印  */
	private String hasWatermark;
	
	/** 来源  */
	private String origin;
	
	/** 浏览次数  */
	private Integer scans;
	
	/** 点击次数  */
	private Integer hits;
	
	
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		 this.title = title == null ? null : title.trim();
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		 this.picPath = picPath == null ? null : picPath.trim();
	}
	public String getLocalPicPath() {
		return localPicPath;
	}
	public void setLocalPicPath(String localPicPath) {
		 this.localPicPath = localPicPath == null ? null : localPicPath.trim();
	}
	public String getOssPicPath() {
		return ossPicPath;
	}
	public void setOssPicPath(String ossPicPath) {
		 this.ossPicPath = ossPicPath == null ? null : ossPicPath.trim();
	}
	public String getLinkPath() {
		return linkPath;
	}
	public void setLinkPath(String linkPath) {
		 this.linkPath = linkPath == null ? null : linkPath.trim();
	}
	public Integer getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Integer getIsCover() {
		return isCover;
	}
	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}
	public Integer getIsMain() {
		return isMain;
	}
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
	public Integer getIsThumb() {
		return isThumb;
	}
	public void setIsThumb(Integer isThumb) {
		this.isThumb = isThumb;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
	public String getHasWatermark() {
		return hasWatermark;
	}
	public void setHasWatermark(String hasWatermark) {
		 this.hasWatermark = hasWatermark == null ? null : hasWatermark.trim();
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

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}