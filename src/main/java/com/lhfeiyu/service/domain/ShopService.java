package com.lhfeiyu.service.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.LoginLogMapper;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.po.domain.Admin;
import com.lhfeiyu.po.domain.LoginLog;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.service.base.BaseShopService;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;
import com.lhfeiyu.util.Md5Util;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：店铺 Shop <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 商城交易：店铺表 <p>
 */
@Service
public class ShopService extends BaseShopService {

	@Autowired
	ShopMapper shopMapper;
	@Autowired
	LoginLogMapper loginLogMapper;
	/**
	 * 执行登陆，登陆成功后调用登陆成功方法
	 * @param json
	 * @param session
	 * @param ip 登陆IP
	 * @param user Admin对象
	 * @return JSONObject
	 */
	public JSONObject doLogin(JSONObject json, String loginAccount, String password, String ip, boolean pswdEncrypt){
		Map<String,Object> map = new HashMap<String,Object>();
		String encrypt_pswd = password;
		if(pswdEncrypt){
			encrypt_pswd = Md5Util.encrypt(password);
		}
		map.put("phone", loginAccount);
		map.put("password", encrypt_pswd);
		Shop shop = shopMapper.selectByCondition(map);
		if(null == shop){
			return Result.failure(json, LhTip.msg_phone_wrong, LhTip.code_phone_or_password_wrong);
		}
		return loginSuccess(json, ip, shop);
	}
	/**
	 * 登陆成功后执行的操作：1更新最后登陆时间，2新增登陆日志（Action中负责移除登陆错误次数和将用户存入Session）
	 * @param json
	 * @param session
	 * @param ip 登陆IP
	 * @param user Admin对象
	 * @return JSONObject
	 */
	public JSONObject loginSuccess(JSONObject json, String ip, Shop shop){
		Date date = new Date();
		Shop shop1 = new Shop();
		Integer adminId = shop.getId();
		shop1.setId(adminId);
		shop1.setLastLoginTime(date);
		shopMapper.updateByPrimaryKeySelective(shop);
		LoginLog log = new LoginLog();
		log.setUserId(adminId);
		log.setUsername(shop.getName());
		log.setLoginTime(date);
		log.setLoginIp(ip);
		log.setGradeId(2);//后台管理员登陆admin
		log.setDescription(LhTip.commonLoginLogDescription);
		log.setCreatedAt(date);
		log.setCreatedBy(LhTip.value_sys_createdBy);
		loginLogMapper.insert(log);
		json.put("shop", shop);
		return Result.success(json);
	}
	
	
	/**
	 * 根据用户ID查询店铺
	 * @param userId 用户ID
	 * @return Shop 店铺
	 */ 
	public Shop selectShopByUserId(Integer userId) {
		return shopMapper.selectShopByUserId(userId);
	}
	
	/**
	 * 根据用户serial查询店铺
	 * @param userSerial 用户Serial
	 * @return Shop 店铺
	 */ 
	public Shop selectShopByUserSerial(String userSerial) {
		return shopMapper.selectShopByUserSerial(userSerial);
	}
	
	/**
	 * 根据查询条件，查询列表数据，组装为json返回  （初始版本为自动生成）
	 * @param json
	 * @param map 查询条件map
	 * @return JSONObject json:{rows:dataList,total:total,status:'success',success:'success'}
	 */ 
	public JSONObject getShopListSimple(JSONObject json, Map<String, Object> map) {
		List<Shop> dataList = shopMapper.selectListByCondition(map);
		Integer total = shopMapper.selectCountByCondition(map);
		return Result.gridData(dataList, total, json);
	}
	
	/**
	 * 新增或修改 - Shop  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param shop Shop对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addOrUpdateShopSimple(JSONObject json, Shop obj, String updatedBy){
		//判断不能为空的字段，或验证其他必须条件
		//String content = obj.getContent();
		//if(!Check.isNotNull(content)){
		//	return Result.failure(json, "内容不能为空", "content_null");
		//}
		if(null == obj.getId()){//添加
			return addShopSimple(json, obj, updatedBy);
		}else{//修改
			return updateShopSimple(json, obj, updatedBy);
		}
	}
	
	/**
	 * 新增  - Shop（代码若已经存在则提示失败）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param shop Shop对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addShopSimple(JSONObject json, Shop obj, String updatedBy){
		//判断不能重复的字段，或验证其他必须条件
		Date date = new Date();
		obj.setId(null);
		obj.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_shop));
		obj.setMainStatus(1);
		obj.setCreatedBy(updatedBy);
		obj.setCreatedAt(date);
		shopMapper.insert(obj);
		json.put("id", obj.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改 - Shop（ID不能为空，数据库中必须存在该ID的数据）  （初始版本为自动生成）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param shop Shop对象
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateShopSimple(JSONObject json, Shop obj, String updatedBy){
		Date date = new Date();
		Integer objId = obj.getId();
		if(null == objId){//添加
			return Result.failure(json, "编号为空，无法进行执行修改", "shopId_null");
		}
		Shop dbShop = shopMapper.selectByPrimaryKey(objId);
		if(null == dbShop){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "shop_null");
		}
		obj.setUpdatedBy(updatedBy);
		obj.setUpdatedAt(date);
		shopMapper.updateByPrimaryKeySelective(obj);
		return Result.success(json);
	}
	
	/**
	 * 逻辑删除 - Shop  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateShopDeleteSimple(JSONObject json, String ids, String updatedBy) {
		shopMapper.updateDeletedNowByIds(ids, updatedBy);
		return Result.success(json);
	}
	
	/**
	 * 物理删除 - Shop  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @return JSONObject
	 */
	public JSONObject deleteShopThoroughSimple(JSONObject json, String ids) {
		shopMapper.deleteByIds(ids);
		return Result.success(json);
	}
	
	/**
	 * 恢复 - Shop（去除逻辑删除状态）  （初始版本为自动生成）
	 * @param json
	 * @param ids ID串
	 * @param updatedBy 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateShopRecoverSimple(JSONObject json, String ids, String updatedBy) {
		shopMapper.updateDeletedNullByIds(ids, updatedBy);
		return Result.success(json);
	}


}