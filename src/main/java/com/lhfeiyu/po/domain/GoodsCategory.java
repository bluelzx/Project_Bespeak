package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：GoodsCategory <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：商品分类表 <p>
 */
public class GoodsCategory extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 分类名称  */
	private String name;
	
	/** 分类的关键字,搜索  */
	private String keywords;
	
	/** 该分类的父类ID  */
	private Integer parentId;
	
	/** 该分类的计量单位  */
	private String unit;
	
	/** 是否显示在导航栏  */
	private String showInNav;
	
	/** 是否在前台页面显示  */
	private String isShow;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		 this.name = name == null ? null : name.trim();
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		 this.keywords = keywords == null ? null : keywords.trim();
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		 this.unit = unit == null ? null : unit.trim();
	}
	public String getShowInNav() {
		return showInNav;
	}
	public void setShowInNav(String showInNav) {
		 this.showInNav = showInNav == null ? null : showInNav.trim();
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		 this.isShow = isShow == null ? null : isShow.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	













	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}