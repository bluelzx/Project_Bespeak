package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.UserRelationMapper;
import com.lhfeiyu.po.domain.UserRelation;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户关联 UserRelation <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseUserRelationService")
public class BaseUserRelationService extends CommonService<UserRelation> {

	@Autowired
	UserRelationMapper userRelationMapper;
	
	public JSONObject getUserRelationList(JSONObject json, Map<String, Object> map) {
		List<UserRelation> userRelationList = userRelationMapper.selectListByCondition(map);
		Integer total = userRelationMapper.selectCountByCondition(map);
		return Result.gridData(userRelationList, total, json);
	}
	
	/**
	 * 新增或修改用户关联
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userRelation 用户关联对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateUserRelation(JSONObject json, UserRelation userRelation, String username){
		/*String content = userRelation.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == userRelation.getId()){//添加
			return addUserRelation(json, userRelation, username);
		}else{//修改
			return updateUserRelation(json, userRelation, username);
		}
	}
	
	/**
	 * 新增用户关联（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userRelation 用户关联对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUserRelation(JSONObject json, UserRelation userRelation, String username){
		Date date = new Date();
		userRelation.setId(null);
		userRelation.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_userRelation));
		userRelation.setMainStatus(1);
		userRelation.setCreatedBy(username);
		userRelation.setCreatedAt(date);
		userRelationMapper.insert(userRelation);
		json.put("id", userRelation.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户关联（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userRelation 用户关联对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateUserRelation(JSONObject json, UserRelation userRelation, String username){
		Date date = new Date();
		Integer userRelationId = userRelation.getId();
		if(null == userRelationId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		UserRelation dbUserRelation = userRelationMapper.selectByPrimaryKey(userRelationId);
		if(null == dbUserRelation){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		userRelation.setUpdatedBy(username);
		userRelation.setUpdatedAt(date);
		userRelationMapper.updateByPrimaryKeySelective(userRelation);
		return Result.success(json);
	}
	
}
