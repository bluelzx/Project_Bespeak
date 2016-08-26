package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.InvestigationMapper;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.po.domain.Investigation;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户 Investigation <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseInvestigationService")
public class BaseInvestigationService extends CommonService<Investigation> {

	@Autowired
	InvestigationMapper investigationMapper;
//	@Autowired
//	InvestigationFundMapper ufMapper;
	@Autowired
	ShopMapper shopMapper;
	
	public JSONObject getInvestigationList(JSONObject json, Map<String, Object> map) {
		List<Investigation> investigationList = investigationMapper.selectListByCondition(map);
		Integer total = investigationMapper.selectCountByCondition(map);
		return Result.gridData(investigationList, total, json);
	}
	
	/**
	 * 新增或修改用户
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param investigation 用户对象
	 * @param investigationname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateInvestigation(JSONObject json, Investigation investigation, String investigationname){
		/*String content = investigation.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == investigation.getId()){//添加
			return addInvestigation(json, investigation, investigationname);
		}else{//修改
			return updateInvestigation(json, investigation, investigationname);
		}
	}
	
	/**
	 * 新增用户（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param investigation 用户对象
	 * @param investigationname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addInvestigation(JSONObject json, Investigation investigation, String investigationname){
		Date date = new Date();
		investigation.setId(null);
		investigation.setSerial(CommonGenerator.getSerialByDate("p"));
		investigation.setMainStatus(1);
		investigation.setCreatedBy(investigationname);
		investigation.setCreatedAt(date);
		investigationMapper.insert(investigation);
		json.put("id", investigation.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param investigation 用户对象
	 * @param investigationname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateInvestigation(JSONObject json, Investigation investigation, String investigationname){
		Date date = new Date();
		Integer investigationId = investigation.getId();
		if(null == investigationId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Investigation dbInvestigation = investigationMapper.selectByPrimaryKey(investigationId);
		if(null == dbInvestigation){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		investigation.setUpdatedBy(investigationname);
		investigation.setUpdatedAt(date);
		investigationMapper.updateByPrimaryKeySelective(investigation);
		return Result.success(json);
	}
	
//	public Investigation addRegInvestigation(JSONObject json, Investigation investigation){
//		//FIXME TODO
//		//检查字符合法性
//		//同时新增，账户，店铺，专场等
//		investigationMapper.insertSelective(investigation);
//		boolean flag = addInvestigationRelationObj(investigation);
//		if(flag == false)return null;
//		return investigation;
//	}
	
	/*public boolean addInvestigationRelationObj(Investigation investigation){
		Integer investigationId = investigation.getId();
		String investigationname = investigation.getInvestigationname();
		Date date = new Date();
		try {
			BigDecimal zero = new BigDecimal(0);
			InvestigationFund uf = new InvestigationFund();//新增资金账户
			uf.setInvestigationId(investigationId);
			uf.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_investigationFund));
			uf.setAvaliableMoney(zero);
			uf.setFrozenMoney(zero);
			uf.setOtherFund(zero);
			uf.setOtherFund2(zero);
			uf.setIntegralFund(zero);
			uf.setCoinFund(zero);
			uf.setCreatedAt(date);
			uf.setCreatedBy(investigationname);
			ufMapper.insert(uf);
			Shop shop = new Shop();//新增店铺
			shop.setInvestigationId(investigationId);
			shop.setName(investigationname);
			shop.setLogo(investigation.getAvatar());
			shop.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_shop));
			shop.setCreatedAt(date);
			shop.setCreatedBy(investigationname);
			shopMapper.insert(shop);
			investigation.setShopId(shop.getId());
		} catch (Exception e) {
			if(null != investigationId)investigationMapper.deleteByPrimaryKey(investigationId);
			return false;
		}
		return true;
	}*/


	
}
