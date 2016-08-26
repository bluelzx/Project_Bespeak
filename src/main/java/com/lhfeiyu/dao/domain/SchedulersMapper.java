package com.lhfeiyu.dao.domain;


import java.util.Map;

import com.lhfeiyu.dao.base.CommonMapper;
import com.lhfeiyu.po.domain.Schedulers;

public interface SchedulersMapper extends CommonMapper<Schedulers>{
	
	String selectNoticeUserPhones(Map<String,Object> map);
	
	int selectNoticeUserCount(Map<String,Object> map);

}
