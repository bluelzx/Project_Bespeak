package com.lhfeiyu.po.domain;

import com.lhfeiyu.po.base.Parent;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/

/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：Forum <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 论坛：论坛表 <p>
 */
public class Forum extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	private String dictName;
	private Integer forumUserId;
	private Integer instId;
	private Integer userId;
	private String userSerial;
	private String username;
	
	private Integer memberCount;//人数
	private Integer isJoin;
	private Integer articleMemberCount;//帖子数
	private String newGongGao;//最新公告
	private int announcementId;
	
	private String moderatorName;//上级论坛
	private String subModeratorName;//副版主
	private String parentName;//版主
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 上级论坛ID  */
	private Integer parentId;
	
	/** 论坛名称  */
	private String name;
	
	/** 论坛图标  */
	private String logo;
	
	/** 版主id  */
	private Integer moderatorId;
	
	/** 副版本ID  */
	private Integer subModeratorId;
	
	/** 类型描述  */
	private String typeDesc;
	
	/** 版规描述  */
	private String ruleDesc;
	
	/** 论坛主地址  */
	private String mainPath;
	
	/** 链接地址  */
	private String linkPath;
	
	/** 访问数  */
	private Integer visitNum;
	
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public Integer getModeratorId() {
		return moderatorId;
	}
	public void setModeratorId(Integer moderatorId) {
		this.moderatorId = moderatorId;
	}
	public Integer getSubModeratorId() {
		return subModeratorId;
	}
	public void setSubModeratorId(Integer subModeratorId) {
		this.subModeratorId = subModeratorId;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		 this.typeDesc = typeDesc == null ? null : typeDesc.trim();
	}
	public String getRuleDesc() {
		return ruleDesc;
	}
	public void setRuleDesc(String ruleDesc) {
		 this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
	}
	public String getMainPath() {
		return mainPath;
	}
	public void setMainPath(String mainPath) {
		 this.mainPath = mainPath == null ? null : mainPath.trim();
	}
	public String getLinkPath() {
		return linkPath;
	}
	public void setLinkPath(String linkPath) {
		 this.linkPath = linkPath == null ? null : linkPath.trim();
	}
	public Integer getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Integer getForumUserId() {
		return forumUserId;
	}

	public void setForumUserId(Integer forumUserId) {
		this.forumUserId = forumUserId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserSerial() {
		return userSerial;
	}
	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	public Integer getIsJoin() {
		return isJoin;
	}
	public void setIsJoin(Integer isJoin) {
		this.isJoin = isJoin;
	}
	public String getNewGongGao() {
		return newGongGao;
	}
	public void setNewGongGao(String newGongGao) {
		this.newGongGao = newGongGao;
	}
	public int getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	public Integer getArticleMemberCount() {
		return articleMemberCount;
	}
	public void setArticleMemberCount(Integer articleMemberCount) {
		this.articleMemberCount = articleMemberCount;
	}
	public String getModeratorName() {
		return moderatorName;
	}
	public void setModeratorName(String moderatorName) {
		this.moderatorName = moderatorName;
	}
	public String getSubModeratorName() {
		return subModeratorName;
	}
	public void setSubModeratorName(String subModeratorName) {
		this.subModeratorName = subModeratorName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}