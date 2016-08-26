package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.dao.domain.GoodsLinkMapper;
import com.lhfeiyu.po.domain.GoodsLink;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：GoodsLink <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
 * <strong> 编写时间：</strong>2016年3月20日22:22:22<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：商品关联表（包括分享的商品，送拍的商品） <p>
 */
@Service("baseGoodsLinkService")
public class BaseGoodsLinkService extends CommonService<GoodsLink> {

	@Autowired
	GoodsLinkMapper goodsLinkMapper;
	
	/**
	 * 根据查询条件，查询列表数据，组装为json返回  （初始版本为自动生成）
	 * @param json
	 * @param map 查询条件map
	 * @return JSONObject json:{rows:dataList,total:total,status:'success',success:'success'}
	 */ 
	public JSONObject getGoodsLinkListSimple(JSONObject json, Map<String, Object> map) {
		List<GoodsLink> dataList = goodsLinkMapper.selectListByCondition(map);
		Integer total = goodsLinkMapper.selectCountByCondition(map);
		return Result.gridData(dataList, total, json);
	}
	
	/**
	 * 新增或修改 - GoodsLink  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsLink GoodsLink对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addOrUpdateGoodsLinkSimple(JSONObject json, GoodsLink obj, String updatedBy){
		//判断不能为空的字段，或验证其他必须条件
		//String content = obj.getContent();
		//if(!Check.isNotNull(content)){
		//	return Result.failure(json, "内容不能为空", "content_null");
		//}
		if(null == obj.getId()){//添加
			return addGoodsLinkSimple(json, obj, updatedBy);
		}else{//修改
			return updateGoodsLinkSimple(json, obj, updatedBy);
		}
	}
	
	/**
	 * 新增  - GoodsLink（代码若已经存在则提示失败）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsLink GoodsLink对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addGoodsLinkSimple(JSONObject json, GoodsLink obj, String updatedBy){
		//判断不能重复的字段，或验证其他必须条件
		Date date = new Date();
		obj.setId(null);
		obj.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_goodsLink));
		obj.setMainStatus(1);
		obj.setCreatedBy(updatedBy);
		obj.setCreatedAt(date);
		goodsLinkMapper.insert(obj);
		json.put("id", obj.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改 - GoodsLink（ID不能为空，数据库中必须存在该ID的数据）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param goodsLink GoodsLink对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsLinkSimple(JSONObject json, GoodsLink obj, String updatedBy){
		Date date = new Date();
		Integer objId = obj.getId();
		if(null == objId){//添加
			return Result.failure(json, "编号为空，无法进行执行修改", "goodsLinkId_null");
		}
		GoodsLink dbGoodsLink = goodsLinkMapper.selectByPrimaryKey(objId);
		if(null == dbGoodsLink){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "goodsLink_null");
		}
		obj.setUpdatedBy(updatedBy);
		obj.setUpdatedAt(date);
		goodsLinkMapper.updateByPrimaryKeySelective(obj);
		return Result.success(json);
	}
	
	/**
	 * 逻辑删除 - GoodsLink  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsLinkDeleteSimple(JSONObject json, String ids, String updatedBy) {
		goodsLinkMapper.updateDeletedNowByIds(ids, updatedBy);
		return Result.success(json);
	}
	
	/**
	 * 物理删除 - GoodsLink  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @return JSONObject
	 */
	public JSONObject deleteGoodsLinkThoroughSimple(JSONObject json, String ids) {
		goodsLinkMapper.deleteByIds(ids);
		return Result.success(json);
	}
	
	/**
	 * 恢复 - GoodsLink（去除逻辑删除状态）  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateGoodsLinkRecoverSimple(JSONObject json, String ids, String updatedBy) {
		goodsLinkMapper.updateDeletedNullByIds(ids, updatedBy);
		return Result.success(json);
	}


}