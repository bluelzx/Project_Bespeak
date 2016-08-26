package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.dao.domain.GoodsCategoryMapper;
import com.lhfeiyu.po.domain.GoodsCategory;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：GoodsCategory <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
 * <strong> 编写时间：</strong>2016年3月20日22:22:22<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：商品分类表 <p>
 */
@Service("baseGoodsCategoryService")
public class BaseGoodsCategoryService extends CommonService<GoodsCategory> {

	@Autowired
	GoodsCategoryMapper goodsCategoryMapper;
	
	/**
	 * 根据查询条件，查询列表数据，组装为json返回  （初始版本为自动生成）
	 * @param json
	 * @param map 查询条件map
	 * @return JSONObject json:{rows:dataList,total:total,status:'success',success:'success'}
	 */ 
	public JSONObject getGoodsCategoryListSimple(JSONObject json, Map<String, Object> map) {
		List<GoodsCategory> dataList = goodsCategoryMapper.selectListByCondition(map);
		Integer total = goodsCategoryMapper.selectCountByCondition(map);
		return Result.gridData(dataList, total, json);
	}
	
	/**
	 * 新增或修改 - GoodsCategory  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsCategory GoodsCategory对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addOrUpdateGoodsCategorySimple(JSONObject json, GoodsCategory obj, String updatedBy){
		//判断不能为空的字段，或验证其他必须条件
		//String content = obj.getContent();
		//if(!Check.isNotNull(content)){
		//	return Result.failure(json, "内容不能为空", "content_null");
		//}
		if(null == obj.getId()){//添加
			return addGoodsCategorySimple(json, obj, updatedBy);
		}else{//修改
			return updateGoodsCategorySimple(json, obj, updatedBy);
		}
	}
	
	/**
	 * 新增  - GoodsCategory（代码若已经存在则提示失败）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsCategory GoodsCategory对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addGoodsCategorySimple(JSONObject json, GoodsCategory obj, String updatedBy){
		//判断不能重复的字段，或验证其他必须条件
		Date date = new Date();
		obj.setId(null);
		obj.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_goodsCategory));
		obj.setMainStatus(1);
		obj.setCreatedBy(updatedBy);
		obj.setCreatedAt(date);
		goodsCategoryMapper.insert(obj);
		json.put("id", obj.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改 - GoodsCategory（ID不能为空，数据库中必须存在该ID的数据）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsCategory GoodsCategory对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsCategorySimple(JSONObject json, GoodsCategory obj, String updatedBy){
		Date date = new Date();
		Integer objId = obj.getId();
		if(null == objId){//添加
			return Result.failure(json, "编号为空，无法进行执行修改", "goodsCategoryId_null");
		}
		GoodsCategory dbGoodsCategory = goodsCategoryMapper.selectByPrimaryKey(objId);
		if(null == dbGoodsCategory){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "goodsCategory_null");
		}
		obj.setUpdatedBy(updatedBy);
		obj.setUpdatedAt(date);
		goodsCategoryMapper.updateByPrimaryKeySelective(obj);
		return Result.success(json);
	}
	
	/**
	 * 逻辑删除 - GoodsCategory  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsCategoryDeleteSimple(JSONObject json, String ids, String updatedBy) {
		goodsCategoryMapper.updateDeletedNowByIds(ids, updatedBy);
		return Result.success(json);
	}
	
	/**
	 * 物理删除 - GoodsCategory  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @return JSONObject
	 */
	public JSONObject deleteGoodsCategoryThoroughSimple(JSONObject json, String ids) {
		goodsCategoryMapper.deleteByIds(ids);
		return Result.success(json);
	}
	
	/**
	 * 恢复 - GoodsCategory（去除逻辑删除状态）  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsCategoryRecoverSimple(JSONObject json, String ids, String updatedBy) {
		goodsCategoryMapper.updateDeletedNullByIds(ids, updatedBy);
		return Result.success(json);
	}


}