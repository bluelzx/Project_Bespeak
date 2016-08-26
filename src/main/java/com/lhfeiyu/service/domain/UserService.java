package com.lhfeiyu.service.domain;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.base.AssetsPath;
import com.lhfeiyu.dao.domain.UserMapper;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.base.BaseUserService;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RegexUtil;
/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户 User <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service
public class UserService extends BaseUserService {
	//处理用户注册Service
	@Autowired
	UserMapper userMapper;
	@Autowired
	private DictService  dictService;
	
	public User addRegUser(JSONObject json, User user, String ip){
		return resetUserField(user, ip);
	}
	/** 重置用户的重要字段 */
	private User resetUserField(User user,String ip){
		Date date = new Date();
		String dictValue = dictService.getDictValueByCode(AssetsPath.code_defaultUserAvatar);
		User newUser = new User();
		String serial = CommonGenerator.getSerialByDate("u");
		newUser.setSerial(serial);
		newUser.setUsername(user.getUsername());
		newUser.setPhone(user.getPhone());
		newUser.setPassword(Md5Util.encrypt(user.getPassword()));//密码MD5加密
		newUser.setMainStatus(1);//启用
		newUser.setIdcardNum(user.getIdcardNum());
		newUser.setLastLoginTime(date);
		newUser.setCreatedAt(date);
		newUser.setCreatedBy("-reg-");
		newUser.setAvatar(dictValue);
		if(Check.isNull(newUser.getUsername()))newUser.setUsername(serial);//没有用户名自动设置一个
		userMapper.insertSelective(newUser);
		return newUser;
	}
	public JSONObject validateRegUser(JSONObject json, User user){
		validateUserMajor(json, user);	//验证字段合法性
		if(Result.hasError(json))return json;
		validateUserExist(json, user); //判断是否为重复用户
		return json;
	}
	/** 用户进行注册时验证：用户名，邮箱，密码 等 */
	private JSONObject validateUserMajor(JSONObject json,User user){
		String password = user.getPassword();
		String phone = user.getPhone();
		String username = user.getUsername();
		//String email = user.getEmail();
		if(!phone.matches(RegexUtil.phone_regexp)){
			return Result.failure(json, "手机号码错误", "phone_type_error");
		}
		if(null == password || "".equals(password.trim()) || password.trim().length()<6 || password.trim().length()>20){
			return Result.failure(json, "密码长度应该在6个字符到20个字符之间", "password_length_error");
		}
		if(!password.matches(RegexUtil.non_special_char_regexp)){
			return Result.failure(json, "密码不能包含特殊字符", "password_specialChar_error");
		}
		
		
		if(null == username || "".equals(username.trim()) || username.trim().length()<5 || username.trim().length()>20){
			json.put("msg", "用户名长度应该在5个字符到20个字符之间");
			json.put("status", "username_length_error");
			return json;
		}
		
		if(!username.matches(RegexUtil.non_special_char_regexp)){
			json.put("msg", "用户名不能包含特殊字符");
			json.put("status", "username_specialChar_error");
			return json;
		}
		/*
		if(null == email || "".equals(email.trim()) || email.trim().length()<5 || email.trim().length()>20){
			json.put("msg", "邮箱长度应该在5个字符到20个字符之间");
			json.put("status", "username_length_error");
			return json;
		}
		if(!email.matches(RegexUtil.non_special_char_regexp)){
			json.put("msg", "邮箱不能包含特殊字符");
			json.put("status", "username_specialChar_error");
			return json;
		}
		if(!email.matches(RegexUtil.email_regexp)){
			json.put("msg", "邮箱格式不正确");
			json.put("status", "username_specialChar_error");
			return json;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", user.getUsername());
		List<User> usernameList = service.selectListByCondition(map);
		if(null != usernameList && usernameList.size()>0){//检查数据库是否已存在该用户名
			json.put("msg", "用户名已存在");
			json.put("status", "username_already_exist");
			return json;
		}
		map.put("email", user.getEmail());
		List<User> emailList = service.selectListByCondition(map);
		if(null != emailList && emailList.size()>0){//检查数据库是否已存在该邮箱
			json.put("msg", "邮箱已存在");
			json.put("status", "email_already_exist");
			return json;
		}*/
		return json;
	}
	/**验证用户是否存在**/
	private JSONObject validateUserExist(JSONObject json, User user){
		
		String phone = user.getPhone();
		String username = user.getUsername();
		String idcardNum = user.getIdcardNum();
		if(Check.isNull(phone)){
			return Result.failure(json, "请输入手机号码", "phone_null");
		}
		if(Check.isNull(username)){
			return Result.failure(json, "请输入用户名", "idcardNum_null");
		}
			
		Map<String, Object> map = CommonGenerator.getHashMap();
//		System.out.println("phone:"+phone);
		map.put("phone", phone);
		int count = userMapper.selectCountByCondition(map);
		if(count > 0){
			return Result.failure(json, "手机号码已存在，请重新输入", "phone_repeat");
		}
		map.clear();
		//验证用户名重复
		Map<String, Object> map1 = CommonGenerator.getHashMap();
//		System.out.println("phone:"+phone);
		map1.put("username", username);
		int count1 = userMapper.selectCountByCondition(map1);
		if(count1 > 0){
			return Result.failure(json, "用户已存在，请重新输入", "phone_repeat");
		}
		map1.clear();
//		map.put("idcardNumCheck", idcardNum);
//		int count2 = userMapper.selectCountByCondition(map);
//		if(count2 > 0){
//			return Result.failure(json, "身份证号码已存在，请重新输入", "idcardNum_repeat");
//		}
		
		/*Map<String,Object> map = CommonGenerator.getHashMap();
		map.put("phone", user.getPhone());
		User dbUser = userMapper.selectByCondition(map);
		if(null != dbUser){
			Result.failure(json, "用户已经存在", "user_exist");
		}*/
		return json;
	}
}
