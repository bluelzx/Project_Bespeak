/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层PO类-基本公共字段 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 成都蓝海飞鱼科技有限公司开发人员 <p>
 * <strong> 编写时间：</strong> 2015-2016 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
package com.lhfeiyu.po.base;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;

public class Parent {

	private String ids;
	private String condition;
	private JSONObject json;
	
	/** 主键编号  */
	private Integer id;
	/** 序号  */
	private String serial;
	
	/** 类型ID  */
	private Integer typeId;
	
	/** 数据字典类型代码  */
	private String typeCode;
	
	/** 数据字典代码  */
	private String dictCode;
	
	/** 关联类型  */
	private Integer linkType;
	
	/** 拥有者ID  */
	private Integer ownerId;
	
	/** 拥有者ID串  */
	private String ownerIds;
	
	/** 关联ID  */
	private Integer linkId;
	
	/** 组ID  */
	private Integer groupId;
	
	/** 等级  */
	private Integer gradeId;
	
	/** 业务状态  */
	private Integer mainStatus;
	
	/** 逻辑状态  */
	private Integer logicStatus;
	
	/** BigDecimal  */
	private BigDecimal attrDecimal;
	
	/** 备用字段-字符串  */
	private String attrStr;
	
	/** 备用字段-字符串2  */
	private String attrStr2;
	
	/** 备用字段-整型  */
	private Integer attrInt;
	
	/** 备用字段-整型2  */
	private Integer attrInt2;
	
	/** 备用字段-BigDecimal  */
	private BigDecimal attrNum;
	
	/** ID串  */
	private String mainIds;
	
	/** 备用字段-Date  */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date attrDate;
	
	/** 备用字段-Datetime  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date attrDatetime;
	
	/** 排列顺序  */
	private Integer sequence;
	
	/** 描述  */
	private String description;
	
	/** 备注  */
	private String remark;
	
	/** 删除时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deletedAt;
	
	/** 删除人  */
	private String deletedBy;
	
	/** 创建时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
	
	/** 创建人  */
	private String createdBy;
	
	/** 更新时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedAt;
	
	/** 更新人  */
	private String updatedBy;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}
	
	/**=================================================================*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getMainStatus() {
		return mainStatus;
	}

	public void setMainStatus(Integer mainStatus) {
		this.mainStatus = mainStatus;
	}

	public Integer getLogicStatus() {
		return logicStatus;
	}

	public void setLogicStatus(Integer logicStatus) {
		this.logicStatus = logicStatus;
	}

	public BigDecimal getAttrDecimal() {
		return attrDecimal;
	}

	public void setAttrDecimal(BigDecimal attrDecimal) {
		this.attrDecimal = attrDecimal;
	}

	public String getAttrStr() {
		return attrStr;
	}

	public void setAttrStr(String attrStr) {
		this.attrStr = attrStr;
	}

	public Integer getAttrInt() {
		return attrInt;
	}

	public void setAttrInt(Integer attrInt) {
		this.attrInt = attrInt;
	}
	public String getMainIds() {
		return mainIds;
	}
	public void setMainIds(String mainIds) {
		this.mainIds = mainIds;
	}
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public Integer getLinkType() {
		return linkType;
	}
	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerIds() {
		return ownerIds;
	}
	public void setOwnerIds(String ownerIds) {
		this.ownerIds = ownerIds;
	}
	public String getAttrStr2() {
		return attrStr2;
	}
	public void setAttrStr2(String attrStr2) {
		this.attrStr2 = attrStr2;
	}
	public Integer getAttrInt2() {
		return attrInt2;
	}
	public void setAttrInt2(Integer attrInt2) {
		this.attrInt2 = attrInt2;
	}
	public BigDecimal getAttrNum() {
		return attrNum;
	}
	public void setAttrNum(BigDecimal attrNum) {
		this.attrNum = attrNum;
	}
	public Date getAttrDate() {
		return attrDate;
	}
	public void setAttrDate(Date attrDate) {
		this.attrDate = attrDate;
	}
	public Date getAttrDatetime() {
		return attrDatetime;
	}
	public void setAttrDatetime(Date attrDatetime) {
		this.attrDatetime = attrDatetime;
	}
	/**======================额外方法======================*/
	
	/** 改写toString方法，返回json字符串  */
	public String toString(){
    	return JSONObject.toJSONString(this);
    }
	
	
}