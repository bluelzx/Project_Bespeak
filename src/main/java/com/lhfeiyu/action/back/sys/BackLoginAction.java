package com.lhfeiyu.action.back.sys;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.service.domain.AdminService;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：后台登陆 Admin <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月13日09:21:01 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackLoginAction <p>
 */
@Controller
@RequestMapping(value = "/back")// /back
public class BackLoginAction {
	
	@Autowired
	private AdminService adminService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 后台登陆页面
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/page/login", method = RequestMethod.GET)
	public ModelAndView backLogin(ModelMap modelMap) {
		String path = LhPage.back_login;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Login-PAGE-/back/page/login-加载登陆页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 后台执行登陆  , 只接受POST请求。同一IP最多能登录错误20次（Session范围）
	 * @param request
	 * @param session
	 * @param verificationCode 验证码
	 * @param loginAccount 登陆账号
	 * @param password 登陆密码
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/doBackLogin", method = RequestMethod.POST)
	public JSONObject doBackLogin(HttpServletRequest request,
			HttpSession session, @RequestParam String verificationCode, 
			@RequestParam String loginAccount, @RequestParam String password) {
		JSONObject json = new JSONObject();
		try{
			int errorNum = 1;
			Object errorNumObj = session.getAttribute("errorNum");
			if(null != errorNumObj){//已经记录了该IP有输入错误记录
				errorNum = (Integer)errorNumObj;
				if(errorNum > 20){
					return Result.failure(json, "输入的错误次数太多，无法进行登陆", "max_errors");
				}
			}
			String ip = request.getRemoteAddr();
			Object codeObj = session.getAttribute("randomCode");
			if(null == codeObj){
				return Result.failure(json, "验证码输入错误", "randomCode_error");//页面需刷新验证码
			}
			String randomCode = codeObj.toString();
			if(!(randomCode.equalsIgnoreCase(verificationCode)) && !"1".equals(verificationCode)){//TODO 1:方便测试，后期删除
				return Result.failure(json, "验证码输入错误", "randomCode_error");//页面需刷新验证码
			}
			loginAccount = loginAccount.trim();
			password = password.trim();//去除空格
			if(!Check.isNotNull(loginAccount)){
				return Result.failure(json, "用户名和密码不能为空", "input_isneed");
			}
			adminService.doLogin(json, loginAccount, password, ip, true);//执行登陆
			if(json.containsKey("success")){
				session.removeAttribute("errorNum");//清除登陆错误次数
				Admin admin = (Admin)json.get("admin");
				session.setAttribute("admin", admin);//将用户存入Session
				session.setAttribute("adminId", admin.getId());
			}else{
				session.setAttribute("errorNum", ++errorNum);//增加登陆错误次数
				return Result.failure(json, "用户名或密码输入错误", "input_error");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Login-AJAX-/back/doBackLogin-后台执行登陆出现异常", json);
		}
		return json;
	}
	
}