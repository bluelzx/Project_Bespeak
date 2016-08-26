package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.AdvertisementMapper;
import com.lhfeiyu.po.domain.Advertisement;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 业务层：通用-Advertisement <p>
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：后台用户 Admin <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseAdvertisementService")
public class BaseAdvertisementService extends CommonService<Advertisement> {

	@Autowired
	AdvertisementMapper advertisementmapper;
	
	public JSONObject getAdvertisementList(JSONObject json, Map<String, Object> map) {
		List<Advertisement> advertisementList = advertisementmapper.selectListByCondition(map);
		Integer total = advertisementmapper.selectCountByCondition(map);
		return Result.gridData(advertisementList, total, json);
	}
	
	/**
	 * 新增或修改公告
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param advertisement 公告对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateAdvertisement(JSONObject json, Advertisement advertisement, String username){
//		String content = advertisement.getContent();
//		if(!Check.isNotNull(content)){
//			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
//		}
		if(null == advertisement.getId()){//添加
			return addAdvertisement(json, advertisement, username);
		}else{//修改
			return updateAdvertisement(json, advertisement, username);
		}
	}
	
	/**
	 * 新增公告（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param advertisement 公告对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addAdvertisement(JSONObject json, Advertisement advertisement, String username){
		Date date = new Date();
		advertisement.setId(null);
		advertisement.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_advertisement));
		advertisement.setMainStatus(1);
		advertisement.setCreatedBy(username);
		advertisement.setTitle(username);
		advertisement.setCreatedAt(date);
		advertisement.setExpiryDateFrom(date);
		advertisement.setHits(0);
		advertisement.setShows(0);
		advertisement.setScans(0);
		advertisementmapper.insert(advertisement);
		json.put("id", advertisement.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改公告（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param advertisement 公告对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateAdvertisement(JSONObject json, Advertisement advertisement, String username){
		Date date = new Date();
		Integer advertisementId = advertisement.getId();
		if(null == advertisementId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Advertisement dbAdvertisement = advertisementmapper.selectByPrimaryKey(advertisementId);
		if(null == dbAdvertisement){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}
		advertisement.setUpdatedBy(username);
		advertisement.setUpdatedAt(date);
		advertisementmapper.updateByPrimaryKeySelective(advertisement);
		return Result.success(json);
	}

	
	public Advertisement selectService(Map<String, Object> map) {
		return super.selectByCondition(map);
	}
	
	public List<Advertisement> selectListService(Map<String, Object> map) {
		return super.selectListByCondition(map);
	}
	
	public int updateService(Advertisement advertisement) {
		return super.updateByPrimaryKeySelective(advertisement);
	}
	
	public int insertService(Advertisement advertisement) {
		return super.insertSelective(advertisement);
	}
	
	public int updateDeleteService(Integer id, String updatedBy) {
		return super.updateDeletedNowById(id, updatedBy);
	}
	
	public int deleteService(Integer id) {
		return super.deleteByPrimaryKey(id);
	}

}