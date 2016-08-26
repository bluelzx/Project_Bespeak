package com.lhfeiyu.action.back.sys;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.QuickMenu;
import com.lhfeiyu.service.domain.QuickMenuService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：快捷菜单 QuickMenu <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackQuickMenuAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackQuickMenuAction {
	
	@Autowired
	private QuickMenuService quickMenuService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台快捷菜单页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/quickMenu")
	public ModelAndView quickMenu(ModelMap modelMap){
		String path = LhPage.back_quickMenu;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-QuickMenu-PAGE-/back/page/quickMenu-加载快捷菜单页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载快捷菜单列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getQuickMenuList",method=RequestMethod.POST)
	public JSONObject getQuickMenuList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			quickMenuService.getQuickMenuList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-QuickMenu-AJAX-/back/getQuickMenuList-加载快捷菜单列表出现异常", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改快捷菜单（新增和修改方法对应Serivce中的不同方法）
	 * @param quickMenu ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateQuickMenu", method = RequestMethod.POST)
	public JSONObject addUpdateQuickMenu(@ModelAttribute QuickMenu quickMenu,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			quickMenuService.addUpdateQuickMenu(json, quickMenu, username);
		}catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-QuickMenu-AJAX-/back/addUpdateQuickMenu-添加或修改快捷菜单出现异常", json);
		}
		return json;
	}
	
	/**
	 * 逻辑删除快捷菜单数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateQuickMenuDelete",method=RequestMethod.POST)
	public JSONObject updateQuickMenuDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			Map<String,Object> map = CommonGenerator.getHashMap();
			map.put("quickMenuIds", ids);
			map.put("authority", 3);//只读
			int count = quickMenuService.selectCountByCondition(map);
			if(count > 0){
				return Result.failure(json, "不能删除权限为只读的数据", "authority_readonly");
			}
			quickMenuService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-QuickMenu-AJAX-/back/updateQuickMenuDelete-删除快捷菜单出现异常", json);
		}
		return json;
	}
	
	/**
	 * 物理删除快捷菜单
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteQuickMenuThorough",method=RequestMethod.POST)
	public JSONObject deleteQuickMenuThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			Map<String,Object> map = CommonGenerator.getHashMap();
			map.put("quickMenuIds", ids);
			map.put("authority", 3);//只读
			int count = quickMenuService.selectCountByCondition(map);
			if(count > 0){
				return Result.failure(json, "不能删除权限为只读的数据", "authority_readonly");
			}
			quickMenuService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-QuickMenu-AJAX-/back/deleteQuickMenuThorough-彻底删除快捷菜单出现异常", json);
		}
		return json;
	}
	
	/**
	 * 恢复数字字典数据（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateQuickMenuRecover",method=RequestMethod.POST)
	public JSONObject updateQuickMenuRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			quickMenuService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-QuickMenu-AJAX-/back/updateQuickMenuRecover-恢复快捷菜单出现异常", json);
		}
		return json;
	}
	
	/**
	 * 加载所有的快捷菜单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllQuickMenuArray")
	public JSONArray getAllQuickMenuArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			array = quickMenuService.getQuickMenuArray(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 加载快捷菜单的根节点
	 * @param request
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getRootQuickMenuArray")
	public JSONArray getRootQuickMenuArray(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentCodeNull", 1);
			array = quickMenuService.getQuickMenuArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 加载可扩展的快捷菜单数组
	 * @param request
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getRootQuickMenuArrayForExpand")
	public JSONArray getRootQuickMenuArrayForExpand(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("parentCodeNull", 1);
			map.put("canExpand", 1);//允许扩展
			array = quickMenuService.getQuickMenuArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * 根据父类型加载快捷菜单，返回数组，一般用于下拉列表
	 * @param request
	 * @param parentCode
	 * @return JSONArray
	 */
	@ResponseBody
	@RequestMapping(value = "/getQuickMenuArrayByParentCode")
	public JSONArray getQuickMenuArrayByParentCode(HttpServletRequest request, @RequestParam String parentCode) {
		JSONArray array = new JSONArray();
		try {
			Map<String,Object> map = CommonGenerator.getHashMap();
			map.put("parentCode", parentCode);
			array = quickMenuService.getQuickMenuArray(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}


}
