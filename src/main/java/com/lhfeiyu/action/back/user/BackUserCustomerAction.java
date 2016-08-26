package com.lhfeiyu.action.back.user;

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

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.UserCustomer;
import com.lhfeiyu.service.domain.UserCustomerService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：用户客户 UserCustomer <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.userCustomer.BackUserCustomerAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackUserCustomerAction {
	
	@Autowired
	private UserCustomerService userCustomerService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台用户客户页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/userCustomer")
	public ModelAndView userCustomer(ModelMap modelMap){
		String path = LhPage.back_userCustomer;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/userCustomer", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 加载用户客户列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserCustomerList",method=RequestMethod.POST)
	public JSONObject getUserCustomerList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			userCustomerService.getUserCustomerList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getUserCustomerList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改用户客户（新增和修改方法对应Serivce中的不同方法）
	 * @param userCustomer ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateUserCustomer", method = RequestMethod.POST)
	public JSONObject addUpdateUserCustomer(@ModelAttribute UserCustomer userCustomer,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			userCustomerService.addUpdateUserCustomer(json, userCustomer, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateUserCustomer", json);
		}
		return json;
	}

	/**
	 * 逻辑删除用户客户数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserCustomerDelete", method=RequestMethod.POST)
	public JSONObject updateUserCustomerDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userCustomerService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateUserCustomerDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除用户客户
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUserCustomerThorough",method=RequestMethod.POST)
	public JSONObject deleteUserCustomerThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userCustomerService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteUserCustomerThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复用户客户（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserCustomerRecover", method=RequestMethod.POST)
	public JSONObject updateUserCustomerRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userCustomerService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateUserCustomerRecover", json);
		}
		return json;
	}
	

}

