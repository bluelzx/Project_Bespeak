package com.lhfeiyu.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.RoleMenuMapper;
import com.lhfeiyu.po.domain.RoleMenu;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：角色菜单 RoleMenu <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseRoleMenuService")
public class BaseRoleMenuService extends CommonService<RoleMenu> {

	@Autowired
	RoleMenuMapper roleMenumapper;
	
	public RoleMenu selectService(Map<String, Object> map) {
		return super.selectByCondition(map);
	}
	
	public List<RoleMenu> selectListService(Map<String, Object> map) {
		return super.selectListByCondition(map);
	}
	
	public int updateService(RoleMenu roleMenu) {
		return super.updateByPrimaryKeySelective(roleMenu);
	}
	
	public int insertService(RoleMenu roleMenu) {
		return super.insertSelective(roleMenu);
	}
	
	public int updateDeleteService(Integer id, String updatedBy) {
		return super.updateDeletedNowById(id, updatedBy);
	}
	
	public int deleteService(Integer id) {
		return super.deleteByPrimaryKey(id);
	}

}