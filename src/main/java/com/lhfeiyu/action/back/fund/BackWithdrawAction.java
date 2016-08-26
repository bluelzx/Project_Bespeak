package com.lhfeiyu.action.back.fund;

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
import com.lhfeiyu.po.domain.Withdraw;
import com.lhfeiyu.service.domain.WithdrawService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：取现 Withdraw <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.withdraw.BackWithdrawAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackWithdrawAction {
	
	@Autowired
	private WithdrawService withdrawService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台取现页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/withdraw")
	public ModelAndView withdraw(ModelMap modelMap){
		String path = LhPage.back_withdraw;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, this.getClass().getName()+"/back/page/withdraw", modelMap);
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 加载取现列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getWithdrawList",method=RequestMethod.POST)
	public JSONObject getWithdrawList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			map.put("parentCodeNotNull", 1);//不查跟节点
			withdrawService.getWithdrawList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/getWithdrawList", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改取现（新增和修改方法对应Serivce中的不同方法）
	 * @param withdraw ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateWithdraw", method = RequestMethod.POST)
	public JSONObject addUpdateWithdraw(@ModelAttribute Withdraw withdraw,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			withdrawService.addUpdateWithdraw(json, withdraw, username);
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/addUpdateWithdraw", json);
		}
		return json;
	}

	/**
	 * 逻辑删除取现数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateWithdrawDelete", method=RequestMethod.POST)
	public JSONObject updateWithdrawDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			withdrawService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateWithdrawDelete", json);
		}
		return json;
	}
	
	/**
	 * 物理删除取现
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteWithdrawThorough",method=RequestMethod.POST)
	public JSONObject deleteWithdrawThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			withdrawService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/deleteWithdrawThorough", json);
		}
		return json;
	}
	
	/**
	 * 恢复取现（去除逻辑删除状态）
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateWithdrawRecover", method=RequestMethod.POST)
	public JSONObject updateWithdrawRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			withdrawService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/back/updateWithdrawRecover", json);
		}
		return json;
	}
	

}

