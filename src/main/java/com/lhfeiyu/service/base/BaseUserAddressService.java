package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.UserAddressMapper;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserAddress;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户地址 userAddress<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseUserAddressService")
public class BaseUserAddressService extends CommonService<UserAddress> {
	
	@Autowired
	UserAddressMapper userAddressMapper;
	
	public JSONObject getUserAddressList(JSONObject json, Map<String, Object> map) {
		List<UserAddress> userAddressList = userAddressMapper.selectListByCondition(map);
		Integer total = userAddressMapper.selectCountByCondition(map);
		return Result.gridData(userAddressList, total, json);
	}
	
	/**
	 * 新增或修改用户地址
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userAddress 用户地址对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateUserAddress(JSONObject json, UserAddress userAddress, String username){
		Integer userId = userAddress.getUserId();
		Integer province = userAddress.getProvince();
		Integer city = userAddress.getCity();
		String detail = userAddress.getAddressDetail();
		
		if(Check.isNull(userId)){
			return Result.failure(json, LhTip.msg_userId_null, LhTip.code_userId_null);
		}
		if(Check.isNull(province) || Check.isNull(city)){
			return Result.failure(json, LhTip.msg_province_city_null, LhTip.code_province_city_null);
		}
		if(Check.isNull(detail)){
			return Result.failure(json, LhTip.msg_addressDetail_null, LhTip.code_addressDetail_null);
		}
		if(null == userAddress.getId()){//添加
			return addUserAddress(json, userAddress, username);
		}else{//修改
			return updateUserAddress(json, userAddress, username);
		}
	}
	
	/**
	 * 新增用户地址（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userAddress 用户地址对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUserAddress(JSONObject json, UserAddress userAddress, String username){
		Date date = new Date();
		userAddress.setId(null);
		userAddress.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_userAddress));
		userAddress.setMainStatus(1);
		userAddress.setCreatedBy(username);
		userAddress.setCreatedAt(date);
		userAddressMapper.insert(userAddress);
		json.put("userAddressId", userAddress.getId());
		return Result.success(json);
	}
	
	
	/**
	 * 修改用户地址（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param userAddress 用户地址对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateUserAddress(JSONObject json, UserAddress userAddress, String username){
		Date date = new Date();
		Integer userAddressId = userAddress.getId();
		if(null == userAddressId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		UserAddress dbUserAddress = userAddressMapper.selectByPrimaryKey(userAddressId);
		if(null == dbUserAddress){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}
		userAddress.setUpdatedBy(username);
		userAddress.setUpdatedAt(date);
		userAddressMapper.updateByPrimaryKeySelective(userAddress);
		return Result.success(json);
	}
	
	/**
	 * 彻底删除用户地址数据，根据ID串删除
	 * @param json 
	 * @param sessionUser 
	 * @param ids ID串
	 * @return JSONObject
	 */
	public JSONObject deleteMyUserAddressThorough(JSONObject json, User sessionUser, Integer id){
		//Date date = new Date();
		Integer userId = sessionUser.getId();
		if(null == userId){
			return Result.failure(json, LhTip.msg_userId_null, LhTip.code_userId_null);
		}
//		int idsLength = id.split(",").length;
//		if(idsLength <= 0 ){
//			return Result.failure(json, LhTip.msg_param_lack, LhTip.code_param_lack);
//		}
//		Map<String, Object> map = CommonGenerator.getHashMap();
//		map.put("userId", userId);
//		//TODO 权限
//		int count = userAddressMapper.selectCountByCondition(map);
//		if(idsLength != count){
//			return Result.failure(json, LhTip.msg_auth_lack, LhTip.code_auth_lack);
//		}
		userAddressMapper.deleteByPrimaryKey(id);
		return Result.success(json);
	}

	public Integer cleanDefultAddress(Integer userId) {
		return userAddressMapper.cleanDefultAddress(userId);
	}

	
	
}

