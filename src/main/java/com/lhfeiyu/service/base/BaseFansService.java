package com.lhfeiyu.service.base;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.dao.domain.FansMapper;
import com.lhfeiyu.po.domain.Fans;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：粉丝 Fans （用户关联表） <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2015-7-4 10:08:21 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseFansService")
public class BaseFansService extends CommonService<Fans> {

	@Autowired
	FansMapper fansMapper;
	
	/**
	 * 检查是否已关注指定用户，如果已关注： json.put("focused", 1);
	 * @param json
	 * @param user
	 * @param userId
	 * @return
	 */
	public JSONObject checkFocus(JSONObject json, User user, Integer userId) {
		if(null == userId){
			return Result.failure(json, "请指定用户", "userId_null");
		}
		Map<String,Object> map = CommonGenerator.getHashMap();
		map.put("fansId", user.getId());
		map.put("userId", userId);
		int count = fansMapper.selectCountByCondition(map);
		if(count > 0){
			json.put("overFoucs", 1);
		}
		return json;
	}
	
	/**
	 * 取消关注指定用户（直接删除该条记录），如果不存在关注记录:Result.failure(json, "您已经取消关注该用户", "fans_null");
	 * @param json
	 * @param user
	 * @param userId
	 * @return
	 */
	public JSONObject cancelFocus(JSONObject json, User user, Integer userId) {
		if(null == userId){
			return Result.failure(json, "请指定需要取消关注的用户", "userId_null");
		}
		Map<String,Object> map = CommonGenerator.getHashMap();
		map.put("fansId", user.getId());
		map.put("userId", userId);
		Fans fans = fansMapper.selectByCondition(map);
		if(null == fans){
			return Result.failure(json, "您已经取消关注该用户", "fans_null");
		}
		fansMapper.deleteByPrimaryKey(fans.getId());
		return json;
	}

}

