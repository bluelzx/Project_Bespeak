package com.lhfeiyu.service.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.UserControlMapper;
import com.lhfeiyu.po.domain.UserControl;
import com.lhfeiyu.service.base.BaseUserControlService;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户控制 UserControl <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service
public class UserControlService extends BaseUserControlService{

	@Autowired
	UserControlMapper userControlMapper;
	
	public JSONObject getUserControlList(JSONObject json, Map<String, Object> map) {
		List<UserControl> userControlList = userControlMapper.selectListByCondition(map);
		Integer total = userControlMapper.selectCountByCondition(map);
		return Result.gridData(userControlList, total, json);
	}
	
	/**
	 * 新增或修改用户控制
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userControl 用户控制对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateUserControl(JSONObject json, UserControl userControl, String username){
		/*String content = userControl.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == userControl.getId()){//添加
			return addUserControl(json, userControl, username);
		}else{//修改
			return updateUserControl(json, userControl, username);
		}
	}
	
	/**
	 * 新增用户控制（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userControl 用户控制对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUserControl(JSONObject json, UserControl userControl, String username){
		Date date = new Date();
		userControl.setId(null);
		userControl.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_userControl));
		userControl.setMainStatus(1);
		userControl.setCreatedBy(username);
		userControl.setCreatedAt(date);
		userControlMapper.insert(userControl);
		json.put("id", userControl.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户控制（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userControl 用户控制对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateUserControl(JSONObject json, UserControl userControl, String username){
		Date date = new Date();
		Integer userControlId = userControl.getId();
		if(null == userControlId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		UserControl dbUserControl = userControlMapper.selectByPrimaryKey(userControlId);
		if(null == dbUserControl){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		userControl.setUpdatedBy(username);
		userControl.setUpdatedAt(date);
		userControlMapper.updateByPrimaryKeySelective(userControl);
		return Result.success(json);
	}
	
}
