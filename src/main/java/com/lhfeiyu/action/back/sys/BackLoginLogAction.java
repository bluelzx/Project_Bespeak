package com.lhfeiyu.action.back.sys;

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
import com.lhfeiyu.service.domain.LoginLogService;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：登陆日志 LoginLog <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月13日09:21:01 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackLoginLogAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackLoginLogAction {
	
	@Autowired
	private LoginLogService loginLogService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 后台登陆日志页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/loginLog")
	public ModelAndView loginLog(ModelMap modelMap){
		String path = LhPage.back_loginLog;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-LoginLog-PAGE-/back/page/loginLog-加载登陆日志页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 后台加载登陆日志列表
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getLoginLogList",method=RequestMethod.POST)
	public JSONObject getLoginLogList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			loginLogService.getLoginLogList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-LoginLog-AJAX-/back/getLoginLogList-加载登录日志列表出现异常", json);
		}
		return json;
	}
	
}
