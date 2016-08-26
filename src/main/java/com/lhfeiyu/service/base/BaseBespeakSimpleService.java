package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.BespeakSimpleMapper;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.po.domain.BespeakSimple;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户 Bespeak <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseBespeakSimpleService")
public class BaseBespeakSimpleService extends CommonService<BespeakSimple> {

	@Autowired
	BespeakSimpleMapper bespeakSimpleMapper;
//	@Autowired
//	BespeakFundMapper ufMapper;
	@Autowired
	ShopMapper shopMapper;
	
	public JSONObject getBespeakSimpleList(JSONObject json, Map<String, Object> map) {
		List<BespeakSimple> bespeaksimpleList = bespeakSimpleMapper.selectListByCondition(map);
		Integer total = bespeakSimpleMapper.selectCountByCondition(map);
		return Result.gridData(bespeaksimpleList, total, json);
	}
	
	/**
	 * 新增或修改用户
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param bespeak 用户对象
	 * @param bespeakname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateBespeakSimple(JSONObject json, BespeakSimple bespeak, String bespeakname){
		/*String content = bespeak.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == bespeak.getId()){//添加
			return addBespeakSimple(json, bespeak, bespeakname);
		}else{//修改
			return updateBespeakSimple(json, bespeak, bespeakname);
		}
	}
	
	/**
	 * 新增用户（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param bespeak 用户对象
	 * @param bespeakname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addBespeakSimple(JSONObject json, BespeakSimple bespeak, String bespeakname){
		Date date = new Date();
		bespeak.setId(null);
		bespeak.setSerial(CommonGenerator.getSerialByDate("p"));
		bespeak.setMainStatus(1);
		bespeak.setCreatedBy(bespeakname);
		bespeak.setCreatedAt(date);
		bespeakSimpleMapper.insert(bespeak);
		json.put("id", bespeak.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param bespeak 用户对象
	 * @param bespeakname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateBespeakSimple(JSONObject json, BespeakSimple bespeaksimple, String bespeakname){
		Date date = new Date();
		Integer bespeaksimpleId = bespeaksimple.getId();
		if(null == bespeaksimpleId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		BespeakSimple bespeaksimple1 = bespeakSimpleMapper.selectByPrimaryKey(bespeaksimpleId);
		if(null == bespeaksimple1){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}
		bespeaksimple.setUpdatedBy(bespeakname);
		bespeaksimple.setUpdatedAt(date);
		bespeakSimpleMapper.updateByPrimaryKeySelective(bespeaksimple);
		return Result.success(json);
	}
	
}
