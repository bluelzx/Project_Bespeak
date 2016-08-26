package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.dao.domain.GoodsPictureMapper;
import com.lhfeiyu.po.domain.GoodsPicture;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：GoodsPicture <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
 * <strong> 编写时间：</strong>2016年3月20日22:22:22<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：商品相册表 <p>
 */
@Service("baseGoodsPictureService")
public class BaseGoodsPictureService extends CommonService<GoodsPicture> {

	@Autowired
	GoodsPictureMapper goodsPictureMapper;
	
	/**
	 * 根据查询条件，查询列表数据，组装为json返回  （初始版本为自动生成）
	 * @param json
	 * @param map 查询条件map
	 * @return JSONObject json:{rows:dataList,total:total,status:'success',success:'success'}
	 */ 
	public JSONObject getGoodsPictureListSimple(JSONObject json, Map<String, Object> map) {
		List<GoodsPicture> dataList = goodsPictureMapper.selectListByCondition(map);
		Integer total = goodsPictureMapper.selectCountByCondition(map);
		return Result.gridData(dataList, total, json);
	}
	
	/**
	 * 新增或修改 - GoodsPicture  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsPicture GoodsPicture对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addOrUpdateGoodsPictureSimple(JSONObject json, GoodsPicture obj, String updatedBy){
		//判断不能为空的字段，或验证其他必须条件
		//String content = obj.getContent();
		//if(!Check.isNotNull(content)){
		//	return Result.failure(json, "内容不能为空", "content_null");
		//}
		if(null == obj.getId()){//添加
			return addGoodsPictureSimple(json, obj, updatedBy);
		}else{//修改
			return updateGoodsPictureSimple(json, obj, updatedBy);
		}
	}
	
	/**
	 * 新增  - GoodsPicture（代码若已经存在则提示失败）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsPicture GoodsPicture对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addGoodsPictureSimple(JSONObject json, GoodsPicture obj, String updatedBy){
		//判断不能重复的字段，或验证其他必须条件
		Date date = new Date();
		obj.setId(null);
		obj.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_goodsPicture));
		obj.setMainStatus(1);
		obj.setCreatedBy(updatedBy);
		obj.setCreatedAt(date);
		goodsPictureMapper.insert(obj);
		json.put("id", obj.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改 - GoodsPicture（ID不能为空，数据库中必须存在该ID的数据）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsPicture GoodsPicture对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsPictureSimple(JSONObject json, GoodsPicture obj, String updatedBy){
		Date date = new Date();
		Integer objId = obj.getId();
		if(null == objId){//添加
			return Result.failure(json, "编号为空，无法进行执行修改", "goodsPictureId_null");
		}
		GoodsPicture dbGoodsPicture = goodsPictureMapper.selectByPrimaryKey(objId);
		if(null == dbGoodsPicture){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "goodsPicture_null");
		}
		obj.setUpdatedBy(updatedBy);
		obj.setUpdatedAt(date);
		goodsPictureMapper.updateByPrimaryKeySelective(obj);
		return Result.success(json);
	}
	
	/**
	 * 逻辑删除 - GoodsPicture  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsPictureDeleteSimple(JSONObject json, String ids, String updatedBy) {
		goodsPictureMapper.updateDeletedNowByIds(ids, updatedBy);
		return Result.success(json);
	}
	
	/**
	 * 物理删除 - GoodsPicture  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @return JSONObject
	 */
	public JSONObject deleteGoodsPictureThoroughSimple(JSONObject json, String ids) {
		goodsPictureMapper.deleteByIds(ids);
		return Result.success(json);
	}
	
	/**
	 * 恢复 - GoodsPicture（去除逻辑删除状态）  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsPictureRecoverSimple(JSONObject json, String ids, String updatedBy) {
		goodsPictureMapper.updateDeletedNullByIds(ids, updatedBy);
		return Result.success(json);
	}


}