package com.lhfeiyu.po.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：OperationLog <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：操作日志表 <p>
 */
public class OperationLog extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String compareNow;
	private String titleName;
	private String titleSecondName;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 用户ID  */
	private Integer userId;
	
	/** 用户名  */
	private String username;
	
	/** 操作时间  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operationTime;
	
	/** 操作IP  */
	private String operationIp;
	
	/** 操作类型  */
	private Integer operationType;
	
	/** 操作等级  */
	private Integer operationLevel;
	
	/** 操作关联用户ID  */
	private Integer linkUserId;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		 this.username = username == null ? null : username.trim();
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public String getOperationIp() {
		return operationIp;
	}
	public void setOperationIp(String operationIp) {
		 this.operationIp = operationIp == null ? null : operationIp.trim();
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public Integer getOperationLevel() {
		return operationLevel;
	}
	public void setOperationLevel(Integer operationLevel) {
		this.operationLevel = operationLevel;
	}
	public Integer getLinkUserId() {
		return linkUserId;
	}
	public void setLinkUserId(Integer linkUserId) {
		this.linkUserId = linkUserId;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	    public String getCompareNow() {
		return compareNow;
	}
	public void setCompareNow(String compareNow) {
		this.compareNow = compareNow;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getTitleSecondName() {
		return titleSecondName;
	}
	public void setTitleSecondName(String titleSecondName) {
		this.titleSecondName = titleSecondName;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}