package com.lhfeiyu.action.front.sys;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhPage;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.AuthCheckService;
import com.lhfeiyu.service.domain.UserService;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RegexUtil;
import com.lhfeiyu.util.SendMsgUtil;
import com.lhfeiyu.util.loadingVerificationCodeUtil;

@Controller
public class LoginAction {
	@Autowired
	private UserService service;
	@Autowired
	private AuthCheckService authCheckService;
//	@Autowired
//	private IndexService indexService;
	private static Logger logger = Logger.getLogger("R");
	
	/** 登陆页面 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(ModelMap modelMap,HttpServletRequest request,
			@RequestParam(required=false) String r,
			@RequestParam(required=false) String flag) {
		JSONObject json = new JSONObject();
		//微信登陆验证
//		modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
		if(null != flag){//TODO FIXME 方便登陆测试2
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", 1);
			User user = service.selectByCondition(map);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("userId", user.getId());
			return new ModelAndView(LhPage.index);
		}
		return new ModelAndView(LhPage.login);
	}
	
	@RequestMapping(value="/jumpToLogin", method=RequestMethod.GET)
	public ModelAndView jumpToLogin(ModelMap modelMap,HttpServletRequest request,@RequestParam(required=false) String r) {
		JSONObject json = new JSONObject();
	//modelMap = authCheckService.checkWxLogin(request, modelMap, json, false, r);
		return new ModelAndView(LhPage.login);
	}

	/** 主页面  *//*
	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap,HttpSession session,
		   @RequestParam(required=false) String path){
		try{
			User user = ActionUtil.checkSession4User(session);
			if(null == user){
				return new ModelAndView(LhPagePath.login);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orderBy", "moduleId");
			map.put("ascOrdesc", "ASC");
			map.put("taskIng", 1);//查询用户相关的正在进行的任务数量
			List<User> userList = service.selectListByCondition(map);
			modelMap.put("userList", userList);
			modelMap.put("currentUserId", user.getId());
			if(null != path){
				modelMap.put("path", path);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView(LhPagePath.frontIndex,modelMap);
	}*/
	
	@RequestMapping(value="/reg", method=RequestMethod.GET)
	public ModelAndView reg(){
		return new ModelAndView(LhPage.register);
	}
//	/** 进行登陆  , 只接受POST请求 */

	
	/** 进行登陆  , 只接受POST请求 */
	@ResponseBody
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public JSONObject doLogin(HttpServletRequest request,HttpSession session,
			@RequestParam String username, @RequestParam String password,
			@RequestParam(defaultValue="user") String loginType,
			@RequestParam(required=false) String verificationCode) {
		JSONObject json = new JSONObject();
		try{
			int errorTimes = 1;
			Object errorTimesObj = session.getAttribute("errorTimes");
			if(null != errorTimesObj){//已经记录了该IP有输入错误记录
				errorTimes = (Integer)errorTimesObj;
				if(errorTimes > 30){
					return Result.failure(json, "输入的错误次数太多，请点击找回密码或者联系管理员", "max_errors");
				}
			}
			String ip = request.getRemoteAddr();
			Object codeObj = session.getAttribute("randomCode");
			String randomCode = null;
			if(null != verificationCode && null != codeObj){//有验证码
				randomCode = codeObj.toString();
				if(!randomCode.equalsIgnoreCase(verificationCode)){// && !"1".equals(verificationCode) 1:方便测试，后期删除
					return Result.failure(json, "验证码输入错误", "randomCode_error");
				}
			}else{//没有验证码
				Object err_ip_record = session.getServletContext().getAttribute(ip);
				if(null !=  err_ip_record){//已经记录了该IP有输入错误记录
					return Result.failure(json, "验证码输入错误", "randomCode_null");
				}
			}
			username = username.trim();
			password = password.trim();
			if(username.length() < 5){
				return Result.failure(json, "登陆账号不能少于5个字符", "loginId_short");
			}
			if(password.length() < 5){
				return Result.failure(json, "登陆密码不能少于5个字符", "password_short");
			}
			if(Check.isNull(username) || Check.isNull(password)){
				return Result.failure(json, "登陆账号和密码不能为空", "input_null");
			}
			Map<String,Object> map = CommonGenerator.getHashMap();
			String encrypt_pswd = Md5Util.encrypt(password);
			map.put("username", username);
//			map.put("phone", username);
			map.put("password", encrypt_pswd);
			map.put("mainStatus", 1);
			
			//登陆成功
//			if("user".equals(loginType)){
//			System.out.println("session:"+session+"  json:"+json+"  map:"+map+"  errorTimes:"+errorTimes+"  ip:"+ip);
				userLoginSuccess(session, json, map, errorTimes, ip);
				if(Result.hasError(json))return json;
//			}else if("doctor".equals(loginType)){
//				doctorLoginSuccess(session, json, map, errorTimes, ip);
//				if(Result.hasError(json))return json;
//			}else if("hospital".equals(loginType)){
//				hospitalLoginSuccess(session, json, map, errorTimes, ip);
//				if(Result.hasError(json))return json;
//			}
			return Result.success(json, "登陆成功");
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR-Login-AJAX-/doLogin-用户登陆出现异常"+loginType, json);
		}
		return json;
	}
	
	private JSONObject userLoginSuccess(HttpSession session, JSONObject json, Map<String,Object> map, int errorTimes, String ip){
		User user = service.selectByCondition(map);
		//登陆方式不同的转存对象
		User usersave=new User();
		if(null == user){
			// 当用户名核对登陆失败后进行手机号登陆核对
			Map<String,Object> map1 = CommonGenerator.getHashMap();
			map1.put("phone", map.get("username"));
			map1.put("password", map.get("password"));
			map1.put("mainStatus", 1);
			User user1 = service.selectByCondition(map1);
			usersave=user1;
		if(null == user1){
			session.getServletContext().setAttribute(ip, 1);//在application中标识该IP输错了一次账号密码，再次输入需要验证码
			session.setAttribute("errorTimes", ++errorTimes);
			return Result.failure(json, "登陆账号或密码输入错误", "input_error");
			}else{
				
			}
		}else{
		usersave=user;
		}
		Date date = CommonGenerator.getDate();
		User userSave = new User();
		userSave.setId(usersave.getId());
		userSave.setLastLoginTime(date);
		service.updateByPrimaryKeySelective(userSave);
		usersave.setLastLoginTime(date);
		session.removeAttribute("provider");
		session.setAttribute("user", usersave);
		session.setAttribute("loginType", "user");
		session.setAttribute("userId", usersave.getId());
		session.getServletContext().removeAttribute(ip);
		session.removeAttribute("errorTimes");
		return json;
	}
	
	//注册跳转页面
	@RequestMapping(value = "/userRegister", method = RequestMethod.GET)
	public ModelAndView reg(ModelMap modelMap,HttpServletRequest request){
//		modelMap = indexService.getData(modelMap,request);
		return new ModelAndView(LhPage.register,modelMap);
	}
	//注册用户处理
	/** 进行注册  , 只接受POST请求 */
	@ResponseBody
	@RequestMapping(value="/doReg", method = RequestMethod.POST)
	public JSONObject doReg(HttpServletRequest request,HttpSession session,
			@ModelAttribute User user, @RequestParam String randomCode){
		JSONObject json = new JSONObject();
		try{
			Object codeObj = session.getAttribute("randomCode");
			if(null == codeObj){
				return Result.failure(json, "请输入验证码", "randomCode_null");
			}
			String code = (String)codeObj;
			if(!code.equalsIgnoreCase(randomCode.trim())){
				return Result.failure(json, "验证码输入错误", "randomCode_error");
			}
			session.removeAttribute("randomCode");
			String ip = request.getRemoteAddr();//客户端IP地址
			service.validateRegUser(json, user);//验证输入数据合法性,判断该用户是否已经注册
			if(Result.hasError(json))return json;
			user = service.addRegUser(json, user, ip);
			session.removeAttribute("provider");
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getId());
			session.setAttribute("loginType", "user");
				
			session.getServletContext().removeAttribute(ip);
			session.removeAttribute("errorTimes");
			
			Result.success(json);
		}catch(Exception e){
			Result.catchError(e, logger, "LH_ERROR-Login-AJAX-/doReg-用户注册出现异常", json);
		}
		return json;
	}
	
	/**  只进行手机登陆  , 只接受POST请求 */
	@ResponseBody
	@RequestMapping(value="/doMobileLogin", method=RequestMethod.POST)
	public JSONObject doMobileLogin(HttpServletRequest request,HttpSession session,@RequestParam String phone,@RequestParam String password) {
		JSONObject json = new JSONObject();
		try{
			int errorNum = 1;
			Object errorNumObj = session.getAttribute("errorNum");
			if(null != errorNumObj){//已经记录了该IP有输入错误记录
				errorNum = (Integer)errorNumObj;
				if(errorNum > 30){
					json.put("status","max_errors");
					json.put("msg","输入的错误次数太多，请点击找回密码或者联系管理员");
					Result.failure(json, "输入的错误次数太多，请点击找回密码或者联系管理员", "max_errors");
					return Result.success(json);
				}
			}
			phone = phone.trim();//因为方法参数已经过滤为空的情况，所以不用担心username为null
			password = password.trim();//去除空格
			if(!"".equals(phone) && !"".equals(phone)){
				Map<String,Object> map = new HashMap<String,Object>();
				String encrypt_pswd = Md5Util.encrypt(password);
				map.put("phone", phone);
				map.put("password", encrypt_pswd);
				List<User> userList = service.selectListByCondition(map);//判断密码通过拿到的phone和密码去查询到该条对应用户数据
				if(null != userList && userList.size()>0){
					User user = userList.get(0);
					user.setLastLoginTime(new Date());//设置登陆时间
					service.updateByPrimaryKeySelective(user);
					session.removeAttribute("provider");
					session.setAttribute("user", user);
					session.setAttribute("userId", user.getId());
					session.removeAttribute("errorNum");
				}else{
					Result.failure(json, "手机号或密码输入错误", "input_error");
					session.setAttribute("errorNum", ++errorNum);
				}
			}else{
				Result.failure(json, "手机号和密码不能为空", "input_isneed");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_登陆异常出现异常_", json);
		}
		return Result.success(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/loginCheckCode", method=RequestMethod.POST)
	public JSONObject loginCheckCode(HttpServletRequest request,HttpSession session) {
		JSONObject json = new JSONObject();
		try{
			String ip = request.getRemoteAddr();
			Object err_ip_record = session.getServletContext().getAttribute(ip);
			if(null != err_ip_record){//已经记录了该IP有输入错误记录
				json.put("status","randomCode_isneed");
			}else{
				json.put("status","doLogin");
			}
		} catch (Exception e) {
			Result.catchError(e, logger, "LH_ERROR_验证出现异常_", json);
		}
		return Result.success(json);
	}
	
	/** 进行注册  , 只接受POST请求 */
	@ResponseBody
	@RequestMapping(value="/getVerifycode", method=RequestMethod.POST)
	public JSONObject getVerifycode(HttpServletRequest request,HttpSession session,@RequestParam String phone){
		JSONObject json = new JSONObject();
		try{
			int verifyCodeNum = 1;
			Object verifyCodeNumObj = session.getAttribute("verifyCodeNum");
			if(null != verifyCodeNumObj){
				verifyCodeNum = (Integer)verifyCodeNumObj;
				if(verifyCodeNum>20){
					json.put("status","max_errors");
					json.put("msg","请求验证码的次数太多，请直接联系管理员帮助您注册吧");
					return Result.success(json);
				}
			}
			//phone : 短信接口发送短信验证码
			String verifycode = SendMsgUtil.createRandomVcode();
			String mobanId = LhConst.rl_ytx_msg_moban_id;
			String[] params = new String[] { verifycode, "30" };
			//YTX_MSG.send(phone, mobanId, params);
			//TODO 修改
			session.setAttribute("verifycode", verifycode);
			json.put("verifycode", verifycode);
			json.put("status", "success");
			json.put("msg","验证码已发送到您的手机,请及时查看.!");
			session.setAttribute("verifyCodeNum", ++verifyCodeNum);
			
		}catch(Exception e){
			Result.catchError(e, logger, "LH_ERROR_注册出现异常_", json);
		}
		return Result.success(json);
	}
	
	
	/**验证用户是否存在**/
	private JSONObject validateUser(JSONObject json,User user){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", user.getPhone());
		List<User> userList = service.selectListByCondition(map);
		if(userList.size() > 0){
			json.put("msg", "用户已经存在");
			json.put("status", "user_exist");
			return Result.success(json);
		}
		return null;
		
	}
	
	/** 用户进行注册时验证：用户名，邮箱，密码 等 */
	private JSONObject validateUserMajor(JSONObject json,User user){
		String password = user.getPassword();
		String phone = user.getPhone();
		//String username = user.getUsername();
		//String email = user.getEmail();
		if(!phone.matches(RegexUtil.phone_regexp)){
			json.put("msg", "手机号码错误");
			json.put("status", "phone_type_error");
			return Result.success(json);
		}
		if(null == password || "".equals(password.trim()) || password.trim().length()<6 || password.trim().length()>20){
			json.put("msg", "密码长度应该在6个字符到20个字符之间");
			json.put("status", "password_length_error");
			return Result.success(json);
		}
		if(!password.matches(RegexUtil.non_special_char_regexp)){
			json.put("msg", "密码不能包含特殊字符");
			json.put("status", "password_specialChar_error");
			return Result.success(json);
		}
		
		/*
		if(null == username || "".equals(username.trim()) || username.trim().length()<5 || username.trim().length()>20){
			json.put("msg", "用户名长度应该在5个字符到20个字符之间");
			json.put("status", "username_length_error");
			return Result.success(json);
		}
		if(!username.matches(RegexUtil.non_special_char_regexp)){
			json.put("msg", "用户名不能包含特殊字符");
			json.put("status", "username_specialChar_error");
			return Result.success(json);
		}

		if(null == email || "".equals(email.trim()) || email.trim().length()<5 || email.trim().length()>20){
			json.put("msg", "邮箱长度应该在5个字符到20个字符之间");
			json.put("status", "username_length_error");
			return Result.success(json);
		}
		if(!email.matches(RegexUtil.non_special_char_regexp)){
			json.put("msg", "邮箱不能包含特殊字符");
			json.put("status", "username_specialChar_error");
			return Result.success(json);
		}
		if(!email.matches(RegexUtil.email_regexp)){
			json.put("msg", "邮箱格式不正确");
			json.put("status", "username_specialChar_error");
			return Result.success(json);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", user.getUsername());
		List<User> usernameList = service.selectListByCondition(map);
		if(null != usernameList && usernameList.size()>0){//检查数据库是否已存在该用户名
			json.put("msg", "用户名已存在");
			json.put("status", "username_already_exist");
			return Result.success(json);
		}
		map.put("email", user.getEmail());
		List<User> emailList = service.selectListByCondition(map);
		if(null != emailList && emailList.size()>0){//检查数据库是否已存在该邮箱
			json.put("msg", "邮箱已存在");
			json.put("status", "email_already_exist");
			return Result.success(json);
		}*/
		return null;
	}
	
	/** 重置用户的重要字段 */
	private User resetUserField(User user,String ip,Date curdate){
		User newUser = new User();
		newUser.setSerial(CommonGenerator.getSerialByDate("u"));
		newUser.setUsername(user.getUsername());
		newUser.setPhone(user.getPhone());
		newUser.setPassword(Md5Util.encrypt(user.getPassword()));//密码MD5加密
		newUser.setMainStatus(1);//启用
		newUser.setLastLoginTime(curdate);
		newUser.setCreatedAt(curdate);
		newUser.setAvatar("/images/front/default_avatar.png");
		return newUser;
	}
	
	@ResponseBody
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public JSONObject logout(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			request.getSession().invalidate();
			json.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("LH_ERROR_注销异常_"+e.getMessage());
			json.put("status", "server_error");
			json.put("msg", "服务器出现异常");
		}
		return Result.success(json);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout2(HttpServletRequest request) {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("LH_ERROR_注销异常_"+e.getMessage());
		}
		return new ModelAndView(LhPage.login);
	}
	
	/** 加载验证码 */
	@RequestMapping(value="/login/loadVerificationCode", method=RequestMethod.GET)
	public String loadVerificationCode(HttpServletResponse response,HttpServletRequest request) {
		BufferedImage bais = loadingVerificationCodeUtil.createImage();
	    response.setHeader("Pragma", "No-cache");//禁止缓存
	    response.setHeader("Cache-Control", "No-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("image/jpeg");//指定生成的响应是图片
	    request.getSession().removeAttribute("randomCode");//销毁验证码
	    request.getSession().setAttribute("randomCode",loadingVerificationCodeUtil.strCode);//把验证码存到session
	    try {
			ImageIO.write(bais,"JPEG",response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("LH_ERROR_加载验证码异常_"+e.getMessage());
		} 
	    return null;
	}
	
}