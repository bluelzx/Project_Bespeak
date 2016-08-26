package com.lhfeiyu.action.back.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.service.domain.AdminService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：后台用户 Admin <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.admin.BackAdminAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackAdminAction {
	
	@Autowired
	private AdminService adminService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台用户页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/admin")
	public ModelAndView admin(ModelMap modelMap){
		String path = LhPage.back_admin;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/admin", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载后台用户列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getAdminList",method=RequestMethod.POST)
	public JSONObject getAdminList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			adminService.getAdminList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getAdminList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改后台用户（新增和修改方法对应Serivce中的不同方法）
	 * @param admin ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateAdmin", method = RequestMethod.POST)
	public JSONObject addUpdateAdmin(@ModelAttribute Admin admin,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin sessionAdmin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = sessionAdmin.getUsername();
			adminService.addUpdateAdmin(json, admin, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateAdmin", json);
		}
		return json;
	}
	
	/**
	 * 修改后台用户密码（验证密码长度及是否包含特殊字符）
	 * @param session
	 * @param oldPsd 旧密码
	 * @param newPsd 新密码
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePassword")
	public JSONObject updatePassword(HttpSession session, 
			@RequestParam String oldPsd, @RequestParam String newPsd) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.loadAdminBySessionAdminId(session, adminService);
			adminService.updatePassword(json, admin, oldPsd, newPsd);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updatePassword", json);
		}
		return json;
	}
	
	/**
	 * 逻辑删除后台用户数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAdminDelete", method=RequestMethod.POST)
	public JSONObject updateAdminDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			adminService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAdminDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除后台用户
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAdminThorough",method=RequestMethod.POST)
	public JSONObject deleteAdminThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			adminService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteAdminThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复后台用户（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAdminRecover", method=RequestMethod.POST)
	public JSONObject updateAdminRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			adminService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateAdminRecover", json);
		}
		return json;
	}
	

}

