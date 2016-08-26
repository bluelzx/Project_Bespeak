package com.lhfeiyu.action.back.sys;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：主页面对应Action <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.sys.BackMainAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackMainAction {
	
	private static Logger logger = Logger.getLogger("R");
	
	@RequestMapping(value = "/page/main", method = RequestMethod.GET)
	public ModelAndView backMain(ModelMap modelMap,HttpSession session) {
		String path = LhPage.back_main;
		try{
			Admin admin = ActionUtil.checkSession4Admin(session);
			modelMap.put("admin", admin);
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Main-PAGE-/back/page/main-加载后台主页面出现异常", modelMap);
		}
		return new ModelAndView(path);
	}
	
}
