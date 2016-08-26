package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：AlbumPicture <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：相册-图片关联表 <p>
 */
public class AlbumPicture extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String userSerial;
	private String username;
	private String realName;
	private Integer shopId;
	private String shopSerial;
	private String shopName;
	private String picUrls;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 拍卖类型（1：专场，2：微拍，3：即时拍）  */
	private Integer albumId;
	
	/** 拍卖ID  */
	private Integer picId;
	
	/** 用户ID  */
	private Integer userId;
	
	/** 用户的号牌  */
	private Integer instId;
	
	/** 是否显示个人信息  */
	private Integer isCover;
	
	
	public Integer getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getInstId() {
		return instId;
	}
	public void setInstId(Integer instId) {
		this.instId = instId;
	}
	public Integer getIsCover() {
		return isCover;
	}
	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
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





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}