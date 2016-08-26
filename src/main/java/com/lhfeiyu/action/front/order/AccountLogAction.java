package com.lhfeiyu.action.front.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.po.domain.AccountLog;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AccountLogService;
import com.lhfeiyu.tools.ActionUtil;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

@Controller
public class AccountLogAction {
	@Autowired
	private AccountLogService accountLogService;
	private static Logger logger = Logger.getLogger("R");
	
	@ResponseBody
	@RequestMapping(value="/addOrUpdateAccountLog", method=RequestMethod.POST)
	public JSONObject addOrUpdateAccountLog(@ModelAttribute AccountLog accountLog,HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Integer userId = user.getId();
			String username = user.getUsername();
			Date date = new Date();
			if(null == accountLog.getId()){//添加
				String serial = CommonGenerator.getSerialByDate("ac");
				accountLog.setSerial(serial);
				accountLog.setUserId(userId);
				accountLog.setPhone(user.getPhone());
				accountLog.setUsername(username);
				accountLog.setMainStatus(1);
				accountLog.setPayTime(date);
				accountLog.setUsername(username);
				accountLog.setCreatedAt(date);
				accountLog.setCreatedBy(username);
				accountLogService.insert(accountLog);
			}else{//修改
				accountLog.setUpdatedAt(date);
				accountLog.setUpdatedBy(username);
				accountLogService.updateByPrimaryKeySelective(accountLog);
			}
			json.put("status", "success");
			json.put("id",accountLog.getId());
			json.put("msg", "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "操作失败");
			Result.catchError(e, logger, "LH_ERROR_添加或修改订单信息出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getAccountLogList", method=RequestMethod.POST)
	public JSONObject getAccountLogList(HttpServletRequest request,HttpServletResponse response) {
		List<AccountLog> accountLogList = null;
		JSONObject json = new JSONObject();
		try {
			User user = ActionUtil.checkSession4User(request.getSession());//验证session中的user，存在即返回
			if(null == user){
				return Result.userSessionInvalid(json);//返回session过期的json提示
			}
			Map<String, Object> map = ActionUtil.getAllParam(request);
			if(null != map.get("flag")){
				map.put("userId", user.getId());
			}
			accountLogList = accountLogService.selectListByCondition(map);
			Integer total = accountLogService.selectCountByCondition(map);
			json.put("rows", accountLogList);
			json.put("total", total);
			json.put("status", "success");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_获取用户信息出现异常_", json);
		}
		return Result.success(json);
	}
	
	
}
