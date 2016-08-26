package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.base.AssetsPath;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ProviderMapper;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.service.domain.DictService;
import com.lhfeiyu.po.domain.Provider;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;
import com.lhfeiyu.util.RegexUtil;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户 Provider <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseProviderService")
public class BaseProviderService extends CommonService<Provider> {

	@Autowired
	ProviderMapper providerMapper;
	@Autowired
	ShopMapper shopMapper;
	@Autowired
	private DictService  dictService;
	
	public JSONObject getProviderList(JSONObject json, Map<String, Object> map) {
		List<Provider> providerList = providerMapper.selectListByCondition(map);
		Integer total = providerMapper.selectCountByCondition(map);
		return Result.gridData(providerList, total, json);
	}
	
	/**
	 * 新增或修改用户
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param provider 用户对象
	 * @param providername 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateProvider(JSONObject json, Provider provider, String providername){
		/*String content = provider.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == provider.getId()){//添加
			return addProvider(json, provider, providername);
		}else{//修改
			return updateProvider(json, provider, providername);
		}
	}
	
	/**
	 * 新增用户（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param provider 用户对象
	 * @param providername 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addProvider(JSONObject json, Provider provider, String providername){
		Date date = new Date();
		provider.setId(null);
		provider.setSerial(CommonGenerator.getSerialByDate("p"));
		provider.setMainStatus(1);
		provider.setCreatedBy(providername);
		provider.setCreatedAt(date);
		providerMapper.insert(provider);
		json.put("id", provider.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param provider 用户对象
	 * @param providername 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateProvider(JSONObject json, Provider provider, String providername){
		Date date = new Date();
		Integer providerId = provider.getId();
		if(null == providerId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Provider dbProvider = providerMapper.selectByPrimaryKey(providerId);
		if(null == dbProvider){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		provider.setUpdatedBy(providername);
		provider.setUpdatedAt(date);
		providerMapper.updateByPrimaryKeySelective(provider);
		return Result.success(json);
	}
	public JSONObject validateRegProvider(JSONObject json, Provider user){
		validateProviderMajor(json, user);	//验证字段合法性
		if(Result.hasError(json))return json;
		validateProviderExist(json, user); //判断是否为重复用户
		return json;
	}
	/** 用户进行注册时验证：用户名，邮箱，密码 等 */
	private JSONObject validateProviderMajor(JSONObject json,Provider provider){
		String password = provider.getPassword();
		String phone = provider.getPhone();
		String username = provider.getUsername();
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
		map.put("username", user.getProvidername());
		List<Provider> usernameList = service.selectListByCondition(map);
		if(null != usernameList && usernameList.size()>0){//检查数据库是否已存在该用户名
			json.put("msg", "用户名已存在");
			json.put("status", "username_already_exist");
			return json;
		}
		map.put("email", user.getEmail());
		List<Provider> emailList = service.selectListByCondition(map);
		if(null != emailList && emailList.size()>0){//检查数据库是否已存在该邮箱
			json.put("msg", "邮箱已存在");
			json.put("status", "email_already_exist");
			return json;
		}*/
		return json;
	}
	/**验证用户是否存在**/
	private JSONObject validateProviderExist(JSONObject json, Provider provider){
		
		String phone = provider.getPhone();
		String username = provider.getUsername();
		String idcardNum = provider.getIdcardNum();
		if(Check.isNull(phone)){
			return Result.failure(json, "请输入手机号码", "phone_null");
		}
		if(Check.isNull(username)){
			return Result.failure(json, "请输入用户名", "idcardNum_null");
		}
			
		Map<String, Object> map = CommonGenerator.getHashMap();
//		System.out.println("phone:"+phone);
		map.put("phone", phone);
		int count = providerMapper.selectCountByCondition(map);
		if(count > 0){
			return Result.failure(json, "手机号码已存在，请重新输入", "phone_repeat");
		}
		map.clear();
		//验证用户名重复
		Map<String, Object> map1 = CommonGenerator.getHashMap();
//		System.out.println("phone:"+phone);
		map1.put("username", username);
		int count1 = providerMapper.selectCountByCondition(map1);
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
		Provider dbProvider = userMapper.selectByCondition(map);
		if(null != dbProvider){
			Result.failure(json, "用户已经存在", "user_exist");
		}*/
		return json;
	}
	public Provider addRegProvider(JSONObject json, Provider provider, String ip){
		return resetUserField(provider, ip);
	}
	
	/** 重置用户的重要字段 */
	private Provider resetUserField(Provider provider,String ip){
		Date date = new Date();
		String dictValue = dictService.getDictValueByCode(AssetsPath.code_defaultUserAvatar);
		Provider newProvider = new Provider();
		String serial = CommonGenerator.getSerialByDate("u");
		newProvider.setSerial(serial);
		newProvider.setUsername(provider.getUsername());
		newProvider.setPhone(provider.getPhone());
		newProvider.setPassword(Md5Util.encrypt(provider.getPassword()));//密码MD5加密
		newProvider.setMainStatus(1);//启用
		newProvider.setIdcardNum(provider.getIdcardNum());
		newProvider.setLastLoginTime(date);
		newProvider.setCreatedAt(date);
		newProvider.setCreatedBy("-reg-");
		newProvider.setAvatar(dictValue);
		if(Check.isNull(newProvider.getUsername()))newProvider.setUsername(serial);//没有用户名自动设置一个
		providerMapper.insertSelective(newProvider);
		return newProvider;
	}

	
}
