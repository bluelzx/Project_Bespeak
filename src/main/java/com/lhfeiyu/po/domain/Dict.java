package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Dict <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：数据字典表 <p>
 */
public class Dict extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String parentCodeName;
	private String parentDictName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 代码(固定的ID标识)  */
	private String code;
	
	/** 代码名称(主要向客户展示，易于理解)  */
	private String codeName;
	
	/** 父代码  */
	private String parentCode;
	
	/** 名称  */
	private String dictName;
	
	/** 值  */
	private String dictValue;
	
	/** 类型  */
	private String dictType;
	
	/** 等级（用户级，系统级等）  */
	private Integer dictLevel;
	
	/** 备用值（次值）  */
	private String secondValue;
	
	/** 描述  */
	private String dictDesc;
	
	/** 是否为叶子节点  */
	private Integer isLeaf;
	
	/** 权限（1修改，2修改删除,3只读）  */
	private Integer authority;
	
	/** 是否允许增加子节点  */
	private Integer expandable;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		 this.code = code == null ? null : code.trim();
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		 this.codeName = codeName == null ? null : codeName.trim();
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		 this.parentCode = parentCode == null ? null : parentCode.trim();
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
	public Integer getDictLevel() {
		return dictLevel;
	}
	public void setDictLevel(Integer dictLevel) {
		this.dictLevel = dictLevel;
	}
	public String getSecondValue() {
		return secondValue;
	}
	public void setSecondValue(String secondValue) {
		 this.secondValue = secondValue == null ? null : secondValue.trim();
	}
	public String getDictDesc() {
		return dictDesc;
	}
	public void setDictDesc(String dictDesc) {
		 this.dictDesc = dictDesc == null ? null : dictDesc.trim();
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	public Integer getExpandable() {
		return expandable;
	}
	public void setExpandable(Integer expandable) {
		this.expandable = expandable;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public String getParentCodeName() {
		return parentCodeName;
	}

	public void setParentCodeName(String parentCodeName) {
		this.parentCodeName = parentCodeName;
	}
	public String getParentDictName() {
		return parentDictName;
	}

	public void setParentDictName(String parentDictName) {
		this.parentDictName = parentDictName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}