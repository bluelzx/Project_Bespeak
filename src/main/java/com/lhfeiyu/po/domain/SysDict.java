package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：SysDict <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：系统级数据字典表 <p>
 */
public class SysDict extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 父节点ID  */
	private Integer parentId;
	
	/** 名称  */
	private String dictName;
	
	/** 值  */
	private String dictValue;
	
	/** 类型  */
	private String dictType;
	
	/** 等级（用户级，系统级等）  */
	private String dictLevel;
	
	/** 备用值（次值）  */
	private String secondValue;
	
	/** 是否为叶子节点  */
	private String isLeaf;
	
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		 this.dictName = dictName == null ? null : dictName.trim();
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		 this.dictValue = dictValue == null ? null : dictValue.trim();
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		 this.dictType = dictType == null ? null : dictType.trim();
	}
	public String getDictLevel() {
		return dictLevel;
	}
	public void setDictLevel(String dictLevel) {
		 this.dictLevel = dictLevel == null ? null : dictLevel.trim();
	}
	public String getSecondValue() {
		return secondValue;
	}
	public void setSecondValue(String secondValue) {
		 this.secondValue = secondValue == null ? null : secondValue.trim();
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		 this.isLeaf = isLeaf == null ? null : isLeaf.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}