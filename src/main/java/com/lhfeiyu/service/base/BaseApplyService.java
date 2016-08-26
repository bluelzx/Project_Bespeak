package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ApplyMapper;
import com.lhfeiyu.po.domain.Apply;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：申请 Apply <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseApplyService")
public class BaseApplyService extends CommonService<Apply> {

	@Autowired
	ApplyMapper applyMapper;
	
	public JSONObject getApplyList(JSONObject json, Map<String, Object> map) {
		List<Apply> applyList = applyMapper.selectListByCondition(map);
		Integer total = applyMapper.selectCountByCondition(map);
		return Result.gridData(applyList, total, json);
	}
	
	/**
	 * 新增或修改申请
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param apply 申请对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateApply(JSONObject json, Apply apply, String username){
		Integer userId = apply.getUserId();
		if(!Check.isNotNull(userId)){
			return Result.failure(json, LhTip.msg_apply_user_null, LhTip.code_apply_user_null);
		}
		if(null == apply.getId()){//添加
			return addApply(json, apply, username);
		}else{//修改
			return updateApply(json, apply, username);
		}
	}
	
	/**
	 * 新增申请（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param apply 申请对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addApply(JSONObject json, Apply apply, String username){
		Date date = new Date();
		apply.setId(null);
		apply.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_apply));
		apply.setMainStatus(1);
		apply.setCreatedBy(username);
		apply.setCreatedAt(date);
		applyMapper.insert(apply);
		json.put("id", apply.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改申请（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param apply 申请对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateApply(JSONObject json, Apply apply, String username){
		Date date = new Date();
		Integer applyId = apply.getId();
		if(null == applyId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Apply dbApply = applyMapper.selectByPrimaryKey(applyId);
		if(null == dbApply){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		apply.setUpdatedBy(username);
		apply.setUpdatedAt(date);
		applyMapper.updateByPrimaryKeySelective(apply);
		return Result.success(json);
	}
	
}
