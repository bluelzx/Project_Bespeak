package com.lhfeiyu.service.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.UserCustomerMapper;
import com.lhfeiyu.po.domain.UserCustomer;
import com.lhfeiyu.service.base.BaseUserCustomerService;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户客户 UserCustomer <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service
public class UserCustomerService extends BaseUserCustomerService {

	@Autowired
	UserCustomerMapper userCustomerMapper;
	
	public JSONObject getUserCustomerList(JSONObject json, Map<String, Object> map) {
		List<UserCustomer> userCustomerList = userCustomerMapper.selectListByCondition(map);
		Integer total = userCustomerMapper.selectCountByCondition(map);
		return Result.gridData(userCustomerList, total, json);
	}
	
	/**
	 * 新增或修改用户客户
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userCustomer 用户客户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateUserCustomer(JSONObject json, UserCustomer userCustomer, String username){
		/*String content = userCustomer.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == userCustomer.getId()){//添加
			return addUserCustomer(json, userCustomer, username);
		}else{//修改
			return updateUserCustomer(json, userCustomer, username);
		}
	}
	
	/**
	 * 新增用户客户（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userCustomer 用户客户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUserCustomer(JSONObject json, UserCustomer userCustomer, String username){
		Date date = new Date();
		userCustomer.setId(null);
		userCustomer.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_userCustomer));
		userCustomer.setMainStatus(1);
		userCustomer.setCreatedBy(username);
		userCustomer.setCreatedAt(date);
		userCustomerMapper.insert(userCustomer);
		json.put("id", userCustomer.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户客户（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userCustomer 用户客户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateUserCustomer(JSONObject json, UserCustomer userCustomer, String username){
		Date date = new Date();
		Integer userCustomerId = userCustomer.getId();
		if(null == userCustomerId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		UserCustomer dbUserCustomer = userCustomerMapper.selectByPrimaryKey(userCustomerId);
		if(null == dbUserCustomer){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		userCustomer.setUpdatedBy(username);
		userCustomer.setUpdatedAt(date);
		userCustomerMapper.updateByPrimaryKeySelective(userCustomer);
		return Result.success(json);
	}
	
}
