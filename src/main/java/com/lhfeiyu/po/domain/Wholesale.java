package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Wholesale <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 业务：批发城表-暂未使用 <p>
 */
public class Wholesale extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 店铺名称  */
	private String name;
	
	/** 店铺图标  */
	private String logo;
	
	/** 店铺地址  */
	private String address;
	
	/** 联系电话  */
	private String tel;
	
	/** 是否允许复制(1,允许2，不允许)  */
	private Integer goodsPrivilege;
	
	/** 担保交易  */
	private Integer isWarrant;
	
	/** 是否七天退货  */
	private Integer isSevenReturn;
	
	/** 是否包邮  */
	private Integer isFreePostage;
	
	/** 等级  */
	private Integer grade;
	
	/** 评分  */
	private String score;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		 this.name = name == null ? null : name.trim();
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		 this.logo = logo == null ? null : logo.trim();
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		 this.address = address == null ? null : address.trim();
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		 this.tel = tel == null ? null : tel.trim();
	}
	public Integer getGoodsPrivilege() {
		return goodsPrivilege;
	}
	public void setGoodsPrivilege(Integer goodsPrivilege) {
		this.goodsPrivilege = goodsPrivilege;
	}
	public Integer getIsWarrant() {
		return isWarrant;
	}
	public void setIsWarrant(Integer isWarrant) {
		this.isWarrant = isWarrant;
	}
	public Integer getIsSevenReturn() {
		return isSevenReturn;
	}
	public void setIsSevenReturn(Integer isSevenReturn) {
		this.isSevenReturn = isSevenReturn;
	}
	public Integer getIsFreePostage() {
		return isFreePostage;
	}
	public void setIsFreePostage(Integer isFreePostage) {
		this.isFreePostage = isFreePostage;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		 this.score = score == null ? null : score.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}