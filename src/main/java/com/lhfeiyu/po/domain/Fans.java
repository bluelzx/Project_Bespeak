package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Fans <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：粉丝好友表 <p>
 */
public class Fans extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String fansName;
	private String fansAvatar;
	private String userName;
	private String userAvatar;
	private Integer overFoucs;
	private String userShopName;
	private String fansShopName;
	private String thirdName;
	private String wxOpenid;
	private String userSerial;
	private String fansSerial;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID(被关注人）  */
	private Integer userId;
	
	/** 粉丝ID(关注人）  */
	private Integer fansId;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFansId() {
		return fansId;
	}
	public void setFansId(Integer fansId) {
		this.fansId = fansId;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getUserShopName() {
		return userShopName;
	}

	public void setUserShopName(String userShopName) {
		this.userShopName = userShopName;
	}

	public String getFansShopName() {
		return fansShopName;
	}

	public void setFansShopName(String fansShopName) {
		this.fansShopName = fansShopName;
	}

	public Integer getOverFoucs() {
		return overFoucs;
	}

	public void setOverFoucs(Integer overFoucs) {
		this.overFoucs = overFoucs;
	}
	public String getFansName() {
		return fansName;
	}
	public void setFansName(String fansName) {
		this.fansName = fansName;
	}
	public String getFansAvatar() {
		return fansAvatar;
	}
	public void setFansAvatar(String fansAvatar) {
		this.fansAvatar = fansAvatar;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public String getUserSerial() {
		return userSerial;
	}
	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}
	public String getFansSerial() {
		return fansSerial;
	}
	public void setFansSerial(String fansSerial) {
		this.fansSerial = fansSerial;
	}

	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}