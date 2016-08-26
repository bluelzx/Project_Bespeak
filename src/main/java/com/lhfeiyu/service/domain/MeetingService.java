package com.lhfeiyu.service.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhfeiyu.dao.domain.MeetingMapper;
import com.lhfeiyu.po.domain.Meeting;
import com.lhfeiyu.service.base.BaseMeetingService;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：会议 Meeting <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
 * <strong> 编写时间：</strong>2016年3月20日22:22:22<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service
public class MeetingService extends BaseMeetingService {

	@Autowired
	MeetingMapper meetingmapper;
	
	public Meeting selectService(Map<String, Object> map) {
		return super.selectByCondition(map);
	}
	
	public List<Meeting> selectListService(Map<String, Object> map) {
		return super.selectListByCondition(map);
	}
	
	public int updateService(Meeting meeting) {
		return super.updateByPrimaryKeySelective(meeting);
	}
	
	public int insertService(Meeting meeting) {
		return super.insertSelective(meeting);
	}
	
	public int updateDeleteService(Integer id, String updatedBy) {
		return super.updateDeletedNowById(id, updatedBy);
	}
	
	public int deleteService(Integer id) {
		return super.deleteByPrimaryKey(id);
	}

}