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
import com.lhfeiyu.po.domain.UserControl;
import com.lhfeiyu.service.domain.UserControlService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：用户控制 UserControl <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.userControl.BackUserControlAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackUserControlAction {
	
	@Autowired
	private UserControlService userControlService;
	@Autowired
	private UserService userService;
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台用户控制页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/userControl")
	public ModelAndView userControl(ModelMap modelMap){
		String path = LhPage.back_userControl;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/userControl", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 加载用户控制列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserControlList",method=RequestMethod.POST)
	public JSONObject getUserControlList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
//			if(null!=map.get("serial")){
//			User user=userService.selectByCondition(map);
//			map.put("userId", user.getId());
//			}
			map.put("parentCodeNotNull", 1);//不查跟节点
			userControlService.getUserControlList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getUserControlList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改用户控制（新增和修改方法对应Serivce中的不同方法）
	 * @param userControl ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdateUserControl", method = RequestMethod.POST)
	public JSONObject addUpdateUserControl(@ModelAttribute UserControl userControl,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			System.out.println("添加："+userControl);
			
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			userControlService.addUpdateUserControl(json, userControl, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateUserControl", json);
		}
		return json;
	}

	/**
	 * 逻辑删除用户控制数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserControlDelete", method=RequestMethod.POST)
	public JSONObject updateUserControlDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userControlService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateUserControlDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除用户控制
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUserControlThorough",method=RequestMethod.POST)
	public JSONObject deleteUserControlThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userControlService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteUserControlThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复用户控制（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserControlRecover", method=RequestMethod.POST)
	public JSONObject updateUserControlRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			userControlService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateUserControlRecover", json);
		}
		return json;
	}
	

}

