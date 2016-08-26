package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：AntiqueCity <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：古玩城（文玩市场）表 <p>
 */
public class AntiqueCity extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 名称  */
	private String name;
	
	/** 简介  */
	private String introduce;
	
	/** 省市  */
	private Integer province;
	
	/** 城市  */
	private Integer city;
	
	/** 联系地址  */
	private String address;
	
	/** 图片路径  */
	private String picPath;
	
	/** 大图  */
	private String mainPic;
	
	/** 等级  */
	private Integer grade;
	
	/** 等级图片路径  */
	private String gradePic;
	
	/** 电话  */
	private Integer tel;
	
	/** 点赞数量  */
	private Integer praiseNum;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		 this.name = name == null ? null : name.trim();
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		 this.introduce = introduce == null ? null : introduce.trim();
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		 this.address = address == null ? null : address.trim();
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		 this.picPath = picPath == null ? null : picPath.trim();
	}
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		 this.mainPic = mainPic == null ? null : mainPic.trim();
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getGradePic() {
		return gradePic;
	}
	public void setGradePic(String gradePic) {
		 this.gradePic = gradePic == null ? null : gradePic.trim();
	}
	public Integer getTel() {
		return tel;
	}
	public void setTel(Integer tel) {
		this.tel = tel;
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}