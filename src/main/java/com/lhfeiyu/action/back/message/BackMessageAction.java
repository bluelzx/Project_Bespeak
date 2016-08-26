package com.lhfeiyu.action.back.message;

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
import com.lhfeiyu.po.domain.Message;
import com.lhfeiyu.service.domain.MessageService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Pagination;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-控制层-后台：消息记录 Message <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.action.back.message.BackMessageAction <p>
 */
@Controller
@RequestMapping(value="/back")
public class BackMessageAction {
	
	@Autowired
	private MessageService messageService;
	
	private static Logger logger = Logger.getLogger("R");
	
	/**
	 * 加载后台消息记录页面
	 * @param modelMap
	 * @return ModelAndView
	 */
	@RequestMapping(value="/page/message")
	public ModelAndView message(ModelMap modelMap){
		String path = LhPage.back_message;
		try{
			
		}catch(Exception e){
			path = LhPage.back_error;
			Result.catchError(e, logger, "LH_ERROR-Message-PAGE-/back/page/message-加载消息记录页面出现异常", modelMap);
		}
		return new ModelAndView(path,modelMap);
	}
	
	/**
	 * 加载消息记录列表数据
	 * @param request
	 * @return JSONObject(rows,total,status,success)
	 */
	@ResponseBody
	@RequestMapping(value = "/getMessageList",method=RequestMethod.POST)
	public JSONObject getMessageList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = Pagination.getOrderByAndLhPage(RequestUtil.getRequestParam(request), request);
			messageService.getMessageList(json, map);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Message-AJAX-/back/getMessageList-加载消息记录列表出现异常", json);
		}
		return json;
	}
	
	/**
	 * 新增或修改消息记录（新增和修改方法对应Serivce中的不同方法）
	 * @param message ModelAttribute
	 * @param request
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/addUpdateMessage", method = RequestMethod.POST)
	public JSONObject addUpdateMessage(@ModelAttribute Message message,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			String username = admin.getUsername();
			messageService.addUpdateMessage(json, message, username);
		}catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Message-AJAX-/back/addUpdateMessage-添加或修改消息记录出现异常", json);
		}
		return json;
	}
	
	/**
	 * 逻辑删除消息记录数据
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMessageDelete",method=RequestMethod.POST)
	public JSONObject updateMessageDelete(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			messageService.updateDeletedNowByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Message-AJAX-/back/updateMessageDelete-删除消息记录出现异常", json);
		}
		return json;
	}
	
	/**
	 * 物理删除消息记录
	 * @param request
	 * @param ids ID串
	 * @return JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMessageThorough",method=RequestMethod.POST)
	public JSONObject deleteMessageThorough(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			//Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			messageService.deleteByIds(ids);
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Message-AJAX-/back/deleteMessageThorough-彻底删除消息记录出现异常", json);
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
	@RequestMapping(value = "/updateMessageRecover",method=RequestMethod.POST)
	public JSONObject updateMessageRecover(HttpServletRequest request, @RequestParam String ids) {
		JSONObject json = new JSONObject();
		try {
			Admin admin = ActionUtil.checkSession4Admin(request.getSession());//验证session中的user，存在即返回
			messageService.updateDeletedNullByIds(ids, admin.getUsername());
			Result.success(json);
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Message-AJAX-/back/updateMessageRecover-恢复消息记录出现异常", json);
		}
		return json;
	}
	
}
