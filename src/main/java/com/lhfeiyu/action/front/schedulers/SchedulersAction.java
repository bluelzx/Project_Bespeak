package com.lhfeiyu.action.front.schedulers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhTable;
import com.lhfeiyu.po.domain.Schedulers;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AA_UtilService;
import com.lhfeiyu.service.domain.SchedulersService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.RequestUtil;

@Controller
public class SchedulersAction {
	@Autowired
	private SchedulersService schedulersService;
	@Autowired
	private AA_UtilService utilService;

	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateTask", method=RequestMethod.POST)
	public JSONObject addOrUpdateTask(@ModelAttribute Schedulers scheduler,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer userId = user.getId();
			String username = user.getUsername();
			//TODO 获取userId，存入receiver_id,用于通知用户,可考虑存入notcie表中，而不是scheduler表
			
			scheduler.setReceiverId(userId);//接收人
			
			Date date = new Date();
			Integer typeId = scheduler.getTaskTypeId();
			Integer noticeId = scheduler.getNoticeId();
			Integer linkId = scheduler.getLinkId();
			if(null == typeId || null == linkId || null == noticeId){
				return Result.failure(json, "信息不足，无法执行操作", "param_lack");
			}
			if(null == scheduler.getId()){//添加
				scheduler.setCreatedAt(date);
				scheduler.setCreatedBy(username);
				schedulersService.insert(scheduler);
			}else{//修改
				scheduler.setUpdatedAt(date);
				scheduler.setUpdatedBy(username);
				schedulersService.updateByPrimaryKeySelective(scheduler);
			}
			json.put("taskId", scheduler.getId());
		}catch (Exception e) {
			Result.catchError(e, logger, this.getClass().getName()+"/addOrUpdateTask", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/delTask", method=RequestMethod.POST)
	public JSONObject delTask(HttpServletRequest request,@RequestParam(value="id") Integer id) {
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				json.put("toLogin",1);
			}
			//Integer userId = 0;
			String username = "";
			Map<String,Object> map = new HashMap<String,Object>();
			Schedulers s = schedulersService.selectByPrimaryKey(id);
			if(null == s){
				json.put("status","failure");
				json.put("msg","开拍提醒已经被取消了");
			/*}else if(s.getUserId() != userId){
				json.put("status","failure");
				json.put("msg","您没有权限执行该操作");*/
			}else{
				map.put("table", LhTable.schedulers);
				map.put("id", id);
				map.put("username", username);
				utilService.deleteById(map);
				json.put("status","success");
				json.put("msg","已成功取消开拍提醒");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_删除开拍提醒出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getTask", method=RequestMethod.POST)
	public JSONObject getTask(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = RequestUtil.getRequestParam(request);//自动获取所有参数（查询条件）
			//sc_order格式例子：id___asc,created_at___desc,如果传递了request,则可自动获取分页参数
			Schedulers schedulers = schedulersService.selectByCondition(map);
			if(null != schedulers){
				json.put("taskFlag", 1);
				json.put("task", schedulers);
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_加载任务开拍提醒异常_", json);
		}
		return Result.success(json);
	}
	
}
