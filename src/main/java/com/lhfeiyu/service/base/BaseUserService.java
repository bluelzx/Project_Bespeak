package com.lhfeiyu.service.base;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ShopMapper;
import com.lhfeiyu.dao.domain.UserFundMapper;
import com.lhfeiyu.dao.domain.UserMapper;
import com.lhfeiyu.po.domain.Shop;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.po.domain.UserFund;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：用户 User <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseUserService")
public class BaseUserService extends CommonService<User> {

	@Autowired
	UserMapper userMapper;
	@Autowired
	UserFundMapper ufMapper;
	@Autowired
	ShopMapper shopMapper;
	
	
	public JSONObject getUserList(JSONObject json, Map<String, Object> map) {
		List<User> userList = userMapper.selectListByCondition(map);
		Integer total = userMapper.selectCountByCondition(map);
		return Result.gridData(userList, total, json);
	}
	
	/**
	 * 新增或修改用户
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param user 用户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateUser(JSONObject json, User user, String username){
		/*String content = user.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == user.getId()){//添加
			return addUser(json, user, username);
		}else{//修改
			return updateUser(json, user, username);
		}
	}
	
	/**
	 * 新增用户（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param user 用户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUser(JSONObject json, User user, String username){
		Date date = new Date();
		user.setId(null);
		user.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_user));
		user.setMainStatus(1);
		user.setCreatedBy(username);
		user.setCreatedAt(date);
		userMapper.insert(user);
		json.put("id", user.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改用户（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param user 用户对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateUser(JSONObject json, User user, String username){
		Date date = new Date();
		Integer userId = user.getId();
		if(null == userId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		User dbUser = userMapper.selectByPrimaryKey(userId);
		if(null == dbUser){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		user.setUpdatedBy(username);
		user.setUpdatedAt(date);
		userMapper.updateByPrimaryKeySelective(user);
		return Result.success(json);
	}
	
	public User addRegUser(JSONObject json, User user){
		//FIXME TODO
		//检查字符合法性
		//同时新增，账户，店铺，专场等
		userMapper.insertSelective(user);
		boolean flag = addUserRelationObj(user);
		if(flag == false)return null;
		return user;
	}
	
	public boolean addUserRelationObj(User user){
		Integer userId = user.getId();
		String username = user.getUsername();
		Date date = new Date();
		try {
			BigDecimal zero = new BigDecimal(0);
			UserFund uf = new UserFund();//新增资金账户
			uf.setUserId(userId);
			uf.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_userFund));
			uf.setAvaliableMoney(zero);
			uf.setFrozenMoney(zero);
			uf.setOtherFund(zero);
			uf.setOtherFund2(zero);
			uf.setIntegralFund(zero);
			uf.setCoinFund(zero);
			uf.setCreatedAt(date);
			uf.setCreatedBy(username);
			ufMapper.insert(uf);
			Shop shop = new Shop();//新增店铺
			shop.setUserId(userId);
			shop.setName(username);
			shop.setLogo(user.getAvatar());
			shop.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_shop));
			shop.setCreatedAt(date);
			shop.setCreatedBy(username);
			shopMapper.insert(shop);
			user.setShopId(shop.getId());
		} catch (Exception e) {
			if(null != userId)userMapper.deleteByPrimaryKey(userId);
			return false;
		}
		return true;
	}


	
}
