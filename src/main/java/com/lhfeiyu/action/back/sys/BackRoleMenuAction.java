package com.lhfeiyu.action.back.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.Menu;
import com.lhfeiyu.service.domain.MenuService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：角色菜单 RoleMenu <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackRoleMenuAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackRoleMenuAction {
	
	@Autowired
	private MenuService menuService;
	/*@Autowired
	private RoleMenuService roleMenuService;*/
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value = "/page/role", method = RequestMethod.GET)
	public ModelAndView role(ModelMap modelMap,HttpServletRequest request) {
		String path = LhPage.back_role;
		try{
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			modelMap.put("admin", admin);
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-RoleMenu-PAGE-/back/page/role-加载角色页面出现异常", modelMap);
		}
		return new ModelAndView(path);
	}
	
	@RequestMapping(value = "/page/roleMenu", method = RequestMethod.GET)
	public ModelAndView roleMenu(ModelMap modelMap,HttpServletRequest request) {
		String path = LhPage.back_roleMenu;
		try{
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			modelMap.put("admin", admin);
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-RoleMenu-PAGE-/back/page/roleMenu-加载角色菜单页面出现异常", modelMap);
		}
		return new ModelAndView(path);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getMyMenu",method=RequestMethod.POST)
	public JSONObject getQuickMenuList(HttpServletRequest request) {
		List<Menu> menuList = null;
		JSONObject json = new JSONObject();
		try {//后台登陆权限统一由过滤器验证
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			if(null == admin){Result.adminSessionInvalid(json);}
			Integer roleId = admin.getRoleId();
			if(!Check.isGtZero(roleId)){
				return Result.failure(json, "您的账号尚未配置角色，无法加载菜单", "role_null");
			}
			//Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			Map<String, Object> map = CommonGenerator.getHashMap();
			map.put("orderBy", "sequence");
			map.put("ascOrdesc", "asc");
			map.put("roleId", roleId);
			menuList = menuService.selectListByCondition(map);
			Result.gridData(menuList, null, json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Menu-AJAX-/getMyMenu-加载我的菜单列表出现异常", json);
		}
		return json;
	}
	
}
