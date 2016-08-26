package com.lhfeiyu.tools;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.base.BaseTip;
import com.lhfeiyu.config.domain.LhPage;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：返回结果辅助类 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.Result <p>
 */
public class Result {
	
	/**
	 * 用户session过期，返回json
	 * @param json
	 * @return JSONObject json:{flag:'user', toLogin:1, status:'failure',code:'session_invalid',msg:'您长时间未操作，为了账户安全请重新登陆。'}
	 */
	public static JSONObject userSessionInvalid(JSONObject json){
		return userSessionInvalid(json, BaseTip.value_session_user);
	}
	
	/**
	 * 用户session过期，返回ModelAndView
	 * @param modelMap
	 * @param jumpUrl 页面跳转地址
	 * @return ModelAndView modelMap:{flag:'user', status:'failure',code:'session_invalid_redirect',jumpUrl:jumpUrl}
	 */
	public static ModelAndView userSessionInvalid(ModelMap modelMap, String jumpUrl){
		return userSessionInvalid(modelMap, jumpUrl, BaseTip.value_session_user);
	}
	
	/**
	 * 用户session过期，返回json
	 * @param json
	 * @param flag string 标识（'user','admin'等）
	 * @return JSONObject json:{flag:'user', toLogin:1, status:'failure',code:'session_invalid',msg:'您长时间未操作，为了账户安全请重新登陆。'}
	 */
	public static JSONObject userSessionInvalid(JSONObject json, String flag){
		json.put(BaseTip.key_session_flag, flag);
		json.put(BaseTip.key_toLogin, BaseTip.value_toLogin);
		json.put(BaseTip.key_status, BaseTip.value_status_failure);
		json.put(BaseTip.key_failure, BaseTip.value_failure);
		json.put(BaseTip.key_msg, BaseTip.msg_session_invalid_redirect);
		json.put(BaseTip.key_code, BaseTip.code_session_invalid_redirect);
		json.put(BaseTip.key_loginStatus, BaseTip.value_toLogin);
		return json;
	}
	
	/**
	 * 用户session过期，返回ModelAndView
	 * @param modelMap
	 * @param jumpUrl 页面跳转地址
	 * @param flag string 标识（'user','admin'等）
	 * @return ModelAndView modelMap:{flag:'user', status:'failure',code:'session_invalid_redirect',jumpUrl:jumpUrl}
	 */
	public static ModelAndView userSessionInvalid(ModelMap modelMap, String jumpUrl, String flag){
		String path = LhPage.login;
		if(null != jumpUrl){
			modelMap.put(BaseTip.key_session_flag, flag);
			modelMap.put(BaseTip.key_status, BaseTip.value_status_failure);
			modelMap.put(BaseTip.key_failure, BaseTip.value_failure);
			modelMap.put(BaseTip.key_msg, BaseTip.msg_session_invalid_redirect);
			modelMap.put(BaseTip.key_code, BaseTip.code_session_invalid_redirect);
			modelMap.put(BaseTip.key_jumpUrl, jumpUrl);
			//path = jumpUrl;
		}
		return new ModelAndView(path, modelMap);
	}
	
	/**
	 * 后台用户session过期，返回json
	 * @param json
	 * @return JSONObject json:{toLogin:1, status:'failure',code:'session_invalid',msg:'会话过期，请重新登陆。'}
	 */
	public static JSONObject adminSessionInvalid(JSONObject json){
		json.put(BaseTip.key_toLogin, BaseTip.value_toLogin);
		json.put(BaseTip.key_status, BaseTip.value_status_failure);
		json.put(BaseTip.key_failure, BaseTip.value_failure);
		json.put(BaseTip.key_msg, BaseTip.msg_admin_session_invalid);
		json.put(BaseTip.key_code, BaseTip.code_admin_session_invalid);
		return json;
	}
	
	/**
	 * 后台用户session过期，返回ModelAndView
	 * @param modelMap
	 * @param jumpUrl 页面跳转地址
	 * @return ModelAndView modelMap:{status:'failure',code:'session_invalid_redirect',jumpUrl:jumpUrl}
	 */
	public static ModelAndView adminSessionInvalid(ModelMap modelMap, String jumpUrl){
		if(null != jumpUrl){
			modelMap.put(BaseTip.key_status, BaseTip.value_status_failure);
			modelMap.put(BaseTip.key_failure, BaseTip.value_failure);
			modelMap.put(BaseTip.key_msg, BaseTip.msg_session_invalid_redirect);
			modelMap.put(BaseTip.key_code, BaseTip.code_session_invalid_redirect);
			modelMap.put(BaseTip.key_jumpUrl, jumpUrl);
		}
		return new ModelAndView(LhPage.back_login, modelMap);
	}
	
	/**
	 * 将表格所用数据组装进json,并返回json
	 * @param dataList 泛型数据列表集合
	 * @param total 数据库中数据总条数（不是datalist的长度）
	 * @param json
	 * @return JSONObject json:{rows:dataList, total:total, success:'success' status:'success'}
	 */
	public static <T> JSONObject gridData(List<T> dataList,Integer total,JSONObject json){
		if(Check.isLtZero(total))total = 0;
		json.put(BaseTip.key_grid_rows, dataList);
		json.put(BaseTip.key_grid_total, total);
		//json.put("recordsFiltered", null == dataList ? 0 : dataList.size());
		//json.put("recordsTotal", total);
		json.put(BaseTip.key_status, BaseTip.value_status_success);
		json.put(BaseTip.key_success, BaseTip.value_success);
		return json;
	}
	
	/**
	 * 操作成功返回json
	 * @param json
	 * @return JSONObject json:{success:'success' status:'success', msg:'操作成功', code:'operation_success'}
	 */
	public static JSONObject success(JSONObject json){
		return success(json, BaseTip.msg_operation_success, BaseTip.code_operation_success);
	}
	
	/**
	 * 操作成功返回json
	 * @param json
	 * @param msg 提示信息
	 * @return JSONObject json:{success:'success' status:'success', msg:msg, code:'success_code'}
	 */
	public static JSONObject success(JSONObject json, String msg){
		return success(json, msg, BaseTip.code_operation_success);
	}
	
	/**
	 * 操作成功返回json
	 * @param json
	 * @param msg 提示信息
	 * @param code 代码标识
	 * @return JSONObject json:{success:'success' status:'success', msg:msg, code:code}
	 */
	public static JSONObject success(JSONObject json, String msg, String code){
		if(hasError(json) || hasSuccess(json))return json;
		json.put(BaseTip.key_status, BaseTip.value_status_success);
		json.put(BaseTip.key_success, BaseTip.value_success);
		if(!Check.isNotNull(msg))msg = BaseTip.msg_operation_success;
		if(!Check.isNotNull(code))code = BaseTip.code_operation_success;
		json.put(BaseTip.key_msg, msg);
		json.put(BaseTip.key_code, code);
		return json;
	}
	
	/**
	 * 操作成功返回ModelMap
	 * @param modelMap
	 * @return ModelMap modelMap:{success:'success' status:'success', msg:'操作成功', code:'success_code'}
	 */
	public static ModelMap success(ModelMap modelMap){
		return success(modelMap, BaseTip.msg_operation_success, BaseTip.code_operation_success);
	}
	
	/**
	 * 操作成功返回ModelMap
	 * @param modelMap
	 * @param msg 提示信息
	 * @return ModelMap modelMap:{success:'success' status:'success', msg:msg, code:'success_code'}
	 */
	public static ModelMap success(ModelMap modelMap, String msg){
		return success(modelMap, msg, BaseTip.code_operation_success);
	}
	
	/**
	 * 
	 * @param modelMap
	 * @param msg 提示信息
	 * @param code 代码标识
	 * @return ModelMap modelMap:{success:'success' status:'success', msg:msg, code:code}
	 */
	public static ModelMap success(ModelMap modelMap, String msg, String code){
		modelMap.put(BaseTip.key_status, BaseTip.value_status_success);
		modelMap.put(BaseTip.key_success, BaseTip.value_success);
		if(!Check.isNotNull(msg))msg = BaseTip.msg_operation_success;
		if(!Check.isNotNull(code))code = BaseTip.code_operation_success;
		modelMap.put(BaseTip.key_msg, msg);
		modelMap.put(BaseTip.key_code, code);
		return modelMap;
	}
	
	/**
	 * 操作失败返回json
	 * @param json
	 * @param msg 提示信息
	 * @param code 代码标识
	 * @return JSONObject json:{failure:'failure' status:'failure', msg:msg, code:code}
	 */
	public static JSONObject failure(JSONObject json, String msg, String code){
		json.put(BaseTip.key_status, BaseTip.value_status_failure);
		json.put(BaseTip.key_failure, BaseTip.value_failure);
		if(!Check.isNotNull(msg))msg = BaseTip.msg_operation_failure;
		if(!Check.isNotNull(code))code = BaseTip.code_operation_failure;
		json.put(BaseTip.key_msg, msg);
		json.put(BaseTip.key_code, code);
		return json;
	}
	
	/**
	 * 操作失败返回ModelMap
	 * @param modelMap
	 * @param msg 提示信息
	 * @param code 代码标识
	 * @return ModelMap modelMap:{failure:'failure' status:'failure', msg:msg, code:code}
	 */
	public static ModelMap failure(ModelMap modelMap, String msg, String code){
		modelMap.put(BaseTip.key_status, BaseTip.value_status_failure);
		modelMap.put(BaseTip.key_failure, BaseTip.value_failure);
		if(!Check.isNotNull(msg))msg = BaseTip.msg_operation_failure;
		if(!Check.isNotNull(code))code = BaseTip.code_operation_failure;
		modelMap.put(BaseTip.key_msg, msg);
		modelMap.put(BaseTip.key_code, code);
		return modelMap;
	}
	
	/**
	 * 操作失败跳转到页面（调用failure方法）
	 * @param modelMap
	 * @param path 跳转页面路径
	 * @param msg 提示信息
	 * @param code 代码标识
	 * @return ModelAndView modelMap:{failure:'failure' status:'failure', msg:msg, code:code}
	 */
	public static ModelAndView failureToLhPage(ModelMap modelMap, String path, String msg, String code){
		return new ModelAndView(path, failure(modelMap, msg, code));
	}
	
	/**
	 * 检查json中是否包含错误信息标识：json.containsKey("failure")，如果包含错误则返回true，否则返回false
	 * @param json
	 * @return boolean
	 */
	public static boolean hasError(JSONObject json){
		if(null != json && json.containsKey(BaseTip.key_failure)){
			return true;
		}
		return false;
	}
	
	/**
	 * 检查json中是否包含成功标识：json.containsKey("success")，如果包含成功标识则返回true，否则返回false
	 * @param json
	 * @return boolean
	 */
	public static boolean hasSuccess(JSONObject json){
		if(null != json && json.containsKey(BaseTip.key_success)){
			return true;
		}
		return false;
	}
	
	/**
	 * 控制层捕获异常
	 * @param e
	 * @param logger 日志记录
	 * @param errorMsg 错误消息
	 * @param json json对象
	 * @return JSONObject
	 */
	public static ModelMap catchError(Exception e, Logger logger, String errorMsg, ModelMap modelMap){
		e.printStackTrace();
		errorMsg = "LH_ERROR-PAGE-" + errorMsg;
		logger.error(errorMsg+"-"+e.getMessage());
		modelMap.put(BaseTip.key_msg, BaseTip.msg_server_error);
		modelMap.put(BaseTip.key_status, BaseTip.value_status_failure);
		modelMap.put(BaseTip.key_failure, BaseTip.value_failure);
		return modelMap;
	}
	
	/**
	 * 控制层捕获异常
	 * @param e
	 * @param logger 日志记录
	 * @param errorMsg 错误消息
	 * @param json json对象
	 * @return JSONObject
	 */
	public static JSONObject catchError(Exception e, Logger logger, String errorMsg, JSONObject json){
		e.printStackTrace();
		errorMsg = "LH_ERROR-AJAX-" + errorMsg;
		logger.error(errorMsg+"-"+e.getMessage());
		json.put(BaseTip.key_msg, BaseTip.msg_server_error);
		json.put(BaseTip.key_status, BaseTip.value_status_failure);
		json.put(BaseTip.key_failure, BaseTip.value_failure);
		return json;
	}
	
	/**
	 * 控制层捕获异常
	 * @param e
	 * @param logger 日志记录
	 * @param errorMsg 错误消息
	 * @param array json数组
	 * @return
	 */
	public static JSONArray catchError(Exception e, Logger logger, String errorMsg, JSONArray array){
		e.printStackTrace();
		logger.error(errorMsg+"-"+e.getMessage());
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(BaseTip.key_msg, BaseTip.msg_server_error);
		jsonObj.put(BaseTip.key_status, BaseTip.value_status_failure);
		jsonObj.put(BaseTip.key_failure, BaseTip.value_failure);
		array.add(jsonObj);
		return array;
	}
	
	
	/**============================================具体业务逻辑==================================================**/
	
	
	
	
}