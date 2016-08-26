package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：ProvinceCityArea <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：省市区表 <p>
 */
public class ProvinceCityArea extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String provinceName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 上级地区  */
	private Integer parentId;
	
	/** 地区名称  */
	private String areaName;
	
	/** 地区的拼音  */
	private String areaPinyin;
	
	/** 地区拼音的开头字母  */
	private String areaPinyinHead;
	
	/** 地区短名称  */
	private String areaShortName;
	
	/** 地区级别（1省，2市,3区）  */
	private Integer levelId;
	
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		 this.areaName = areaName == null ? null : areaName.trim();
	}
	public String getAreaPinyin() {
		return areaPinyin;
	}
	public void setAreaPinyin(String areaPinyin) {
		 this.areaPinyin = areaPinyin == null ? null : areaPinyin.trim();
	}
	public String getAreaPinyinHead() {
		return areaPinyinHead;
	}
	public void setAreaPinyinHead(String areaPinyinHead) {
		 this.areaPinyinHead = areaPinyinHead == null ? null : areaPinyinHead.trim();
	}
	public String getAreaShortName() {
		return areaShortName;
	}
	public void setAreaShortName(String areaShortName) {
		 this.areaShortName = areaShortName == null ? null : areaShortName.trim();
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	    public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}