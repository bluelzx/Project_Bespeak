package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.AdminMapper;
import com.lhfeiyu.dao.domain.LoginLogMapper;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.LoginLog;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：后台用户 Admin <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseAdminService")
public class BaseAdminService extends CommonService<Admin> {

	@Autowired
	AdminMapper adminMapper;
	@Autowired
	LoginLogMapper loginLogMapper;
	
	public JSONObject getAdminList(JSONObject json, Map<String, Object> map) {
		List<Admin> adminList = adminMapper.selectListByCondition(map);
		Integer total = adminMapper.selectCountByCondition(map);
		return Result.gridData(adminList, total, json);
	}
	
	/**
	 * 根据map中的查询条件加载后台用户数组
	 * @param map
	 * @return JSONArray
	 */
	public JSONArray getAdminArray(Map<String,Object> map) {
		JSONArray array = new JSONArray();
		List<Admin> adminList = adminMapper.selectListByCondition(map);
		for(Admin d : adminList){
			JSONObject json = new JSONObject();
			json.put("id", d.getId());
			json.put("name", d.getUsername());
			array.add(json);
		}
		return array;
	}
	
	/**
	 * 新增或修改后台用户
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param admin 后台用户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateAdmin(JSONObject json, Admin admin, String username){
		String adminName = admin.getUsername();
		if(!Check.isNotNull(adminName)){
			return Result.failure(json, LhTip.msg_username_null, LhTip.code_username_null);
		}
		if(null == admin.getId()){//添加
			return addAdmin(json, admin, username);
		}else{//修改
			return updateAdmin(json, admin, username);
		}
	}
	
	/**
	 * 新增后台用户（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param admin 后台用户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addAdmin(JSONObject json, Admin admin, String username){
		Map<String, Object> map = CommonGenerator.getHashMap();
		String adminName = admin.getUsername();
		String phone = admin.getPhone();
		String email = admin.getEmail();
		if(Check.isNotNull(adminName)){
			map.clear();
			map.put("username", adminName);
			int count = adminMapper.selectCountByCondition(map);
			if(count > 0){
				return Result.failure(json, LhTip.msg_username_repeat, LhTip.code_username_repeat);
			}
		}
		if(Check.isNotNull(phone)){
			map.clear();
			map.put("phone", phone);
			int count = adminMapper.selectCountByCondition(map);
			if(count > 0){
				return Result.failure(json, LhTip.msg_phone_repeat, LhTip.code_phone_repeat);
			}
		}
		if(Check.isNotNull(email)){
			map.clear();
			map.put("email", email);
			int count = adminMapper.selectCountByCondition(map);
			if(count > 0){
				return Result.failure(json, LhTip.msg_email_repeat, LhTip.code_email_repeat);
			}
		}
		String password = Check.isNotNull(admin.getPassword()) ? admin.getPassword() : LhConst.admin_default_password;
		admin.setPassword(Md5Util.encrypt(password));
		Date date = new Date();
		admin.setId(null);
		admin.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_admin));
		admin.setMainStatus(1);
		admin.setCreatedBy(username);
		admin.setCreatedAt(date);
		adminMapper.insert(admin);
		json.put("id", admin.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改后台用户（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param admin 后台用户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateAdmin(JSONObject json, Admin admin, String username){
		Date date = new Date();
		Integer adminId = admin.getId();
		if(null == adminId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Admin dbAdmin = adminMapper.selectByPrimaryKey(adminId);
		if(null == dbAdmin){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}
		admin.setPassword(null);//不更新密码，有专用方法进行重置密码
		admin.setUpdatedBy(username);
		admin.setUpdatedAt(date);
		adminMapper.updateByPrimaryKeySelective(admin);
		return Result.success(json);
	}
	
	/**
	 * 执行登陆，登陆成功后调用登陆成功方法
	 * @param json
	 * @param session
	 * @param ip 登陆IP
	 * @param user Admin对象
	 * @return JSONObject
	 */
	public JSONObject doLogin(JSONObject json, String loginAccount, String password, String ip, boolean pswdEncrypt){
		Map<String,Object> map = new HashMap<String,Object>();
		String encrypt_pswd = password;
		if(pswdEncrypt){
			encrypt_pswd = Md5Util.encrypt(password);
		}
		map.put("loginAccount", loginAccount);
		map.put("password", encrypt_pswd);
		Admin admin = adminMapper.selectByCondition(map);
		if(null == admin){
			return Result.failure(json, LhTip.msg_username_or_password_wrong, LhTip.code_username_or_password_wrong);
		}
		return loginSuccess(json, ip, admin);
	}
	
	/**
	 * 登陆成功后执行的操作：1更新最后登陆时间，2新增登陆日志（Action中负责移除登陆错误次数和将用户存入Session）
	 * @param json
	 * @param session
	 * @param ip 登陆IP
	 * @param user Admin对象
	 * @return JSONObject
	 */
	public JSONObject loginSuccess(JSONObject json, String ip, Admin user){
		Date date = new Date();
		Admin admin = new Admin();
		Integer adminId = user.getId();
		admin.setId(adminId);
		admin.setLastLoginTime(date);
		adminMapper.updateByPrimaryKeySelective(user);
		LoginLog log = new LoginLog();
		log.setUserId(adminId);
		log.setUsername(user.getUsername());
		log.setLoginTime(date);
		log.setLoginIp(ip);
		log.setGradeId(2);//后台管理员登陆admin
		log.setDescription(LhTip.commonLoginLogDescription);
		log.setCreatedAt(date);
		log.setCreatedBy(LhTip.value_sys_createdBy);
		loginLogMapper.insert(log);
		json.put("admin", user);
		return Result.success(json);
	}
	
	/**
	 * 修改后台用户密码（验证密码长度及是否包含特殊字符）
	 * @param oldPsd 旧密码
	 * @param newPsd 新密码
	 * @return JSONObject
	 */
	public JSONObject updatePassword(JSONObject json, Admin admin, String oldPsd, String newPsd) {
		if(null == admin){
			return Result.failure(json, LhTip.msg_admin_null, LhTip.code_admin_null);
		}
		oldPsd = Md5Util.encrypt(oldPsd);
		if(!admin.getPassword().equals(oldPsd)){//验证旧密码是否正确
			return Result.failure(json, LhTip.msg_old_password_wrong, LhTip.code_old_password_wrong);
		}
		if(newPsd.length() < LhConst.password_min_length){//验证密码长度
			return Result.failure(json, LhTip.msg_password_length_lack, LhTip.code_password_length_lack);
		}
		if(!Check.haveNoSpecialChar(newPsd)){//验证密码是否包含特殊字符
			return Result.failure(json, LhTip.msg_password_special_char, LhTip.code_password_special_char);
		}
		newPsd = Md5Util.encrypt(newPsd);
		admin.setPassword(newPsd);
		updateAdmin(json, admin, admin.getUsername());
		return Result.success(json);
	}

}