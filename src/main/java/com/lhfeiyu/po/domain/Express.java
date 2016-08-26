package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/


/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Express <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：快递公司表 <p>
 */
public class Express extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 快递公司简称  */
	private String briefName;
	
	/** 快递公司编号  */
	private String code;
	
	/** 快递公司全称  */
	private String wholeName;
	
	/** 快递公司拼音  */
	private String pinyinName;
	
	/** 快递公司英文名称  */
	private String englishName;
	
	/** 国家  */
	private String country;
	
	
	public String getBriefName() {
		return briefName;
	}
	public void setBriefName(String briefName) {
		 this.briefName = briefName == null ? null : briefName.trim();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		 this.code = code == null ? null : code.trim();
	}
	public String getWholeName() {
		return wholeName;
	}
	public void setWholeName(String wholeName) {
		 this.wholeName = wholeName == null ? null : wholeName.trim();
	}
	public String getPinyinName() {
		return pinyinName;
	}
	public void setPinyinName(String pinyinName) {
		 this.pinyinName = pinyinName == null ? null : pinyinName.trim();
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		 this.englishName = englishName == null ? null : englishName.trim();
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		 this.country = country == null ? null : country.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}