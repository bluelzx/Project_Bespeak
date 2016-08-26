package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.UserFundMapper;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户资金 UserFund <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseUserFundService")
public class BaseUserFundService extends CommonService<UserFund> {

	@Autowired
	UserFundMapper userFundMapper;
	
	public JSONObject getUserFundList(JSONObject json, Map<String, Object> map) {
		List<UserFund> userFundList = userFundMapper.selectListByCondition(map);
		Integer total = userFundMapper.selectCountByCondition(map);
		return Result.gridData(userFundList, total, json);
	}
	
	/**
	 * 新增或修改用户资金
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userFund 用户资金对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateUserFund(JSONObject json, UserFund userFund, String username){
		/*String content = userFund.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == userFund.getId()){//添加
			return addUserFund(json, userFund, username);
		}else{//修改
			return updateUserFund(json, userFund, username);
		}
	}
	
	/**
	 * 新增用户资金（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userFund 用户资金对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUserFund(JSONObject json, UserFund userFund, String username){
		Date date = new Date();
		userFund.setId(null);
		userFund.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_userFund));
		userFund.setMainStatus(1);
		userFund.setCreatedBy(username);
		userFund.setCreatedAt(date);
		userFundMapper.insert(userFund);
		json.put("id", userFund.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户资金（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userFund 用户资金对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateUserFund(JSONObject json, UserFund userFund, String username){
		Date date = new Date();
		Integer userFundId = userFund.getId();
		if(null == userFundId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		UserFund dbUserFund = userFundMapper.selectByPrimaryKey(userFundId);
		if(null == dbUserFund){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		userFund.setUpdatedBy(username);
		userFund.setUpdatedAt(date);
		userFundMapper.updateByPrimaryKeySelective(userFund);
		return Result.success(json);
	}
	
	/**
	 * 根据userId查询用户资金数据
	 * @param userId
	 * @return UserFund
	 */
	public UserFund selectUserFundByUserId(Integer userId){
		return userFundMapper.selectUserFundByUserId(userId);
	}

	public int updatePayPasswordById(UserFund userFund) {
		return userFundMapper.updatePayPasswordById(userFund);
	}

	public int updateBankById(UserFund userFund) {
		return userFundMapper.updateBankById(userFund);
	}

	public void updateMoneyById(UserFund userFund) {
		userFundMapper.updateMoneyById(userFund);
	}
	
}
