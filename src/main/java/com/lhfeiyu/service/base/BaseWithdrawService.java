package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.WithdrawMapper;
import com.lhfeiyu.po.domain.Withdraw;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：取现 Withdraw <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseWithdrawService")
public class BaseWithdrawService extends CommonService<Withdraw> {

	@Autowired
	WithdrawMapper withdrawMapper;
	
	public JSONObject getWithdrawList(JSONObject json, Map<String, Object> map) {
		List<Withdraw> withdrawList = withdrawMapper.selectListByCondition(map);
		Integer total = withdrawMapper.selectCountByCondition(map);
		return Result.gridData(withdrawList, total, json);
	}
	
	/**
	 * 新增或修改取现
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param withdraw 取现对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateWithdraw(JSONObject json, Withdraw withdraw, String username){
		//String content = withdraw.getContent();
		//if(!Check.isNotNull(content)){
		//	return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		//}
		if(null == withdraw.getId()){//添加
			return addWithdraw(json, withdraw, username);
		}else{//修改
			return updateWithdraw(json, withdraw, username);
		}
	}
	
	/**
	 * 新增取现（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param withdraw 取现对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addWithdraw(JSONObject json, Withdraw withdraw, String username){
		Date date = new Date();
		withdraw.setId(null);
		withdraw.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_withdraw));
		withdraw.setMainStatus(1);
		withdraw.setCreatedBy(username);
		withdraw.setCreatedAt(date);
		withdrawMapper.insert(withdraw);
		json.put("id", withdraw.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改取现（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param withdraw 取现对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateWithdraw(JSONObject json, Withdraw withdraw, String username){
		Date date = new Date();
		Integer withdrawId = withdraw.getId();
		if(null == withdrawId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Withdraw dbWithdraw = withdrawMapper.selectByPrimaryKey(withdrawId);
		if(null == dbWithdraw){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		withdraw.setUpdatedBy(username);
		withdraw.setUpdatedAt(date);
		withdrawMapper.updateByPrimaryKeySelective(withdraw);
		return Result.success(json);
	}
	
}
