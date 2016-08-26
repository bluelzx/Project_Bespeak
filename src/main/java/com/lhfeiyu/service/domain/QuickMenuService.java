package com.lhfeiyu.service.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.dao.domain.QuickMenuMapper;
import com.lhfeiyu.po.domain.QuickMenu;
import com.lhfeiyu.service.base.BaseQuickMenuService;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：快捷菜单 quickMenu<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service
public class QuickMenuService extends BaseQuickMenuService{

	@Autowired
	QuickMenuMapper quickMenuMapper;
	
	public JSONObject getQuickMenuList(JSONObject json, Map<String, Object> map) {
		List<QuickMenu> quickMenuList = quickMenuMapper.selectListByCondition(map);
		Integer total = quickMenuMapper.selectCountByCondition(map);
		return Result.gridData(quickMenuList, total, json);
	}
	
	/**
	 * 根据map中的查询条件加载快捷菜单数组
	 * @param map
	 * @return JSONArray
	 */
	public JSONArray getQuickMenuArray(Map<String,Object> map) {
		JSONArray array = new JSONArray();
		List<QuickMenu> quickMenuList = quickMenuMapper.selectListByCondition(map);
		for(QuickMenu d : quickMenuList){
			JSONObject json = new JSONObject();
			json.put("id", d.getId());
			json.put("menuName", d.getMenuName());
			json.put("menuUrl", d.getMenuUrl());
			array.add(json);
		}
		return array;
	}
	
	/**
	 * 新增或修改快捷菜单
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param quickMenu 快捷菜单对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateQuickMenu(JSONObject json, QuickMenu quickMenu, String username){
		Integer userId = quickMenu.getUserId();
		Integer menuId = quickMenu.getMenuId();
		if(!Check.isNotNull(userId)){
			return Result.failure(json, "用户不能为空", "userId_null");
		}
		if(!Check.isNotNull(menuId)){
			return Result.failure(json, "菜单不能为空", "menuId_null");
		}
		if(null == quickMenu.getId()){//添加
			return addQuickMenu(json, quickMenu, username);
		}else{//修改
			return updateQuickMenu(json, quickMenu, username);
		}
	}
	
	/**
	 * 新增快捷菜单（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param quickMenu 快捷菜单对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addQuickMenu(JSONObject json, QuickMenu quickMenu, String username){
		Date date = new Date();
		quickMenu.setId(null);
		quickMenu.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_quickMenu));
		quickMenu.setMainStatus(1);
		quickMenu.setCreatedBy(username);
		quickMenu.setCreatedAt(date);
		quickMenuMapper.insert(quickMenu);
		json.put("id", quickMenu.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改快捷菜单（ID不能为空，数据库中必须存在该ID的数据，该数据权限不能为只读，否则提示失败； code值不会被修改）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param quickMenu 快捷菜单对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateQuickMenu(JSONObject json, QuickMenu quickMenu, String username){
		Date date = new Date();
		Integer quickMenuId = quickMenu.getId();
		if(null == quickMenuId){//添加
			return Result.failure(json, "编号为空，无法进行执行修改", "quickMenuId_null");
		}
		QuickMenu dbQuickMenu = quickMenuMapper.selectByPrimaryKey(quickMenuId);
		if(null == dbQuickMenu){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "quickMenu_null");
		}
		quickMenu.setUpdatedBy(username);
		quickMenu.setUpdatedAt(date);
		quickMenuMapper.updateByPrimaryKeySelective(quickMenu);
		return Result.success(json);
	}
	
	
}

