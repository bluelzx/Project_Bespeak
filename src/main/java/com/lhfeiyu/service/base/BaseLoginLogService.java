package com.lhfeiyu.service.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.dao.domain.LoginLogMapper;
import com.lhfeiyu.po.domain.LoginLog;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：登陆日志 LoginLog <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
 * <strong> 编写时间：</strong>2016年3月20日22:22:22<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseLoginLogService")
public class BaseLoginLogService extends CommonService<LoginLog> {

	@Autowired
	LoginLogMapper loginLogMapper;
	
	public JSONObject getLoginLogList(JSONObject json, Map<String, Object> map) {
		List<LoginLog> loginLogList = loginLogMapper.selectListByCondition(map);
		Integer total = loginLogMapper.selectCountByCondition(map);
		return Result.gridData(loginLogList, total, json);
	}
	
}

