package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.BespeakMapper;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.po.domain.Bespeak;
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
@Service("baseBespeakService")
public class BaseBespeakService extends CommonService<Bespeak> {

	@Autowired
	BespeakMapper bespeakMapper;
//	@Autowired
//	BespeakFundMapper ufMapper;
	@Autowired
	ShopMapper shopMapper;
	
	public JSONObject getBespeakList(JSONObject json, Map<String, Object> map) {
		List<Bespeak> bespeakList = bespeakMapper.selectListByCondition(map);
		Integer total = bespeakMapper.selectCountByCondition(map);
		return Result.gridData(bespeakList, total, json);
	}
	
	/**
	 * 新增或修改用户
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param bespeak 用户对象
	 * @param bespeakname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateBespeak(JSONObject json, Bespeak bespeak, String bespeakname){
		/*String content = bespeak.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == bespeak.getId()){//添加
			return addBespeak(json, bespeak, bespeakname);
		}else{//修改
			return updateBespeak(json, bespeak, bespeakname);
		}
	}
	
	/**
	 * 新增用户（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param bespeak 用户对象
	 * @param bespeakname 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addBespeak(JSONObject json, Bespeak bespeak, String bespeakname){
		Date date = new Date();
		bespeak.setId(null);
		bespeak.setSerial(CommonGenerator.getSerialByDate("p"));
		bespeak.setMainStatus(1);
		bespeak.setCreatedBy(bespeakname);
		bespeak.setCreatedAt(date);
		bespeakMapper.insert(bespeak);
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
	public JSONObject updateBespeak(JSONObject json, Bespeak bespeak, String bespeakname){
		Date date = new Date();
		Integer bespeakId = bespeak.getId();
		if(null == bespeakId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Bespeak dbBespeak = bespeakMapper.selectByPrimaryKey(bespeakId);
		if(null == dbBespeak){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		bespeak.setUpdatedBy(bespeakname);
		bespeak.setUpdatedAt(date);
		bespeakMapper.updateByPrimaryKeySelective(bespeak);
		return Result.success(json);
	}
	
//	public Bespeak addRegBespeak(JSONObject json, Bespeak bespeak){
//		//FIXME TODO
//		//检查字符合法性
//		//同时新增，账户，店铺，专场等
//		bespeakMapper.insertSelective(bespeak);
//		boolean flag = addBespeakRelationObj(bespeak);
//		if(flag == false)return null;
//		return bespeak;
//	}
	
	/*public boolean addBespeakRelationObj(Bespeak bespeak){
		Integer bespeakId = bespeak.getId();
		String bespeakname = bespeak.getBespeakname();
		Date date = new Date();
		try {
			BigDecimal zero = new BigDecimal(0);
			BespeakFund uf = new BespeakFund();//新增资金账户
			uf.setBespeakId(bespeakId);
			uf.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_bespeakFund));
			uf.setAvaliableMoney(zero);
			uf.setFrozenMoney(zero);
			uf.setOtherFund(zero);
			uf.setOtherFund2(zero);
			uf.setIntegralFund(zero);
			uf.setCoinFund(zero);
			uf.setCreatedAt(date);
			uf.setCreatedBy(bespeakname);
			ufMapper.insert(uf);
			Shop shop = new Shop();//新增店铺
			shop.setBespeakId(bespeakId);
			shop.setName(bespeakname);
			shop.setLogo(bespeak.getAvatar());
			shop.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_shop));
			shop.setCreatedAt(date);
			shop.setCreatedBy(bespeakname);
			shopMapper.insert(shop);
			bespeak.setShopId(shop.getId());
		} catch (Exception e) {
			if(null != bespeakId)bespeakMapper.deleteByPrimaryKey(bespeakId);
			return false;
		}
		return true;
	}*/


	
}
