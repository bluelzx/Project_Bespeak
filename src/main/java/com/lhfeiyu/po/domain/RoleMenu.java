package com.lhfeiyu.po.domain;

/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/
import java.util.List;

import com.lhfeiyu.po.base.Parent;


/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：RoleMenu <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong>2016年7月30日22:22:22<p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 3.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 通用：角色菜单表 <p>
 */
public class RoleMenu extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	/** 根菜单的子菜单集合  */
	private List<RoleMenu> subMenuList;
	
	/** 父菜单ID  */
	private Integer parentId;
	
	/** 菜单名称  */
	private String name;
	
	/** 菜单图标  */
	private String icon;
	
	/** 菜单链接地址  */
	private String url;
	
	/** 是否为叶子节点  */
	private String isLeaf;
	
	/** 菜单级别：1根菜单，2子菜单（非叶子），3叶子菜单，4按钮，5其他  */
	private Integer levelId;
	
	/** 菜单描述  */
	private String description;
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	/** 角色ID  */
	private Integer roleId;
	
	/** 菜单ID  */
	private Integer menuId;
	
	/** 菜单ID串  */
	private String menuIds;
	
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		 this.menuIds = menuIds == null ? null : menuIds.trim();
	}
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
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
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<RoleMenu> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<RoleMenu> subMenuList) {
		this.subMenuList = subMenuList;
	}





























	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}