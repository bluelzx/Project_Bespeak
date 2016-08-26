package com.lhfeiyu.service.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.dao.domain.ProvinceCityAreaMapper;
import com.lhfeiyu.po.domain.ProvinceCityArea;
import com.lhfeiyu.service.base.BaseProvinceCityAreaService;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：省市区 provinceCityArea<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service
public class ProvinceCityAreaService extends BaseProvinceCityAreaService {

	@Autowired
	ProvinceCityAreaMapper provinceCityAreaMapper;
	
	public JSONObject getProvinceCityAreaList(JSONObject json, Map<String, Object> map) {
		List<ProvinceCityArea> provinceCityAreaList = provinceCityAreaMapper.selectListByCondition(map);
		Integer total = provinceCityAreaMapper.selectCountByCondition(map);
		return Result.gridData(provinceCityAreaList, total, json);
	}
	
	/**
	 * 根据map中的查询条件加载省市区数组
	 * @param map
	 * @return JSONArray
	 */
	public JSONArray getProvinceCityAreaArray(Map<String,Object> map) {
		JSONArray array = new JSONArray();
		List<ProvinceCityArea> provinceCityAreaList = provinceCityAreaMapper.selectListByCondition(map);
		for(ProvinceCityArea d : provinceCityAreaList){
			JSONObject json = new JSONObject();
			json.put("id", d.getId());
			json.put("name", d.getAreaName());
			array.add(json);
		}
		return array;
	}
	
	/**
	 * 新增或修改省市区
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param provinceCityArea 省市区对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateProvinceCityArea(JSONObject json, ProvinceCityArea provinceCityArea, String username){
		String areaName = provinceCityArea.getAreaName();
		if(!Check.isNotNull(areaName)){
			return Result.failure(json, "地区名称不能为空", "areaName_null");
		}
		if(null == provinceCityArea.getId()){//添加
			return addProvinceCityArea(json, provinceCityArea, username);
		}else{//修改
			return updateProvinceCityArea(json, provinceCityArea, username);
		}
	}
	
	/**
	 * 新增省市区（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param provinceCityArea 省市区对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addProvinceCityArea(JSONObject json, ProvinceCityArea provinceCityArea, String username){
		String areaName = provinceCityArea.getAreaName();
		Map<String, Object> map = CommonGenerator.getHashMap();
		map.put("areaName", areaName);
		int count = provinceCityAreaMapper.selectCountByCondition(map);
		if(count > 0){
			return Result.failure(json, "该地区已经存在，不能新增重复的地区", "areaName_repeat");
		}
		Date date = new Date();
		provinceCityArea.setId(null);
		provinceCityArea.setMainStatus(1);
		provinceCityArea.setCreatedBy(username);
		provinceCityArea.setCreatedAt(date);
		provinceCityAreaMapper.insert(provinceCityArea);
		json.put("id", provinceCityArea.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改省市区（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param provinceCityArea 省市区对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateProvinceCityArea(JSONObject json, ProvinceCityArea provinceCityArea, String username){
		Date date = new Date();
		Integer provinceCityAreaId = provinceCityArea.getId();
		if(null == provinceCityAreaId){
			return Result.failure(json, "编号为空，无法进行执行修改", "provinceCityAreaId_null");
		}
		ProvinceCityArea dbProvinceCityArea = provinceCityAreaMapper.selectByPrimaryKey(provinceCityAreaId);
		if(null == dbProvinceCityArea){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "provinceCityArea_null");
		}
		
		provinceCityArea.setUpdatedBy(username);
		provinceCityArea.setUpdatedAt(date);
		provinceCityAreaMapper.updateByPrimaryKeySelective(provinceCityArea);
		return Result.success(json);
	}
	
	
}

