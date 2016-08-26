package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.AnnouncementMapper;
import com.lhfeiyu.po.domain.Announcement;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：公告 Announcement <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseAnnouncementService")
public class BaseAnnouncementService extends CommonService<Announcement> {

	@Autowired
	AnnouncementMapper announcementMapper;
	
	public JSONObject getAnnouncementList(JSONObject json, Map<String, Object> map) {
		List<Announcement> announcementList = announcementMapper.selectListByCondition(map);
		Integer total = announcementMapper.selectCountByCondition(map);
		return Result.gridData(announcementList, total, json);
	}
	
	/**
	 * 新增或修改公告
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param announcement 公告对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateAnnouncement(JSONObject json, Announcement announcement, String username){
		String content = announcement.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}
		if(null == announcement.getId()){//添加
			return addAnnouncement(json, announcement, username);
		}else{//修改
			return updateAnnouncement(json, announcement, username);
		}
	}
	
	/**
	 * 新增公告（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param announcement 公告对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addAnnouncement(JSONObject json, Announcement announcement, String username){
		Date date = new Date();
		announcement.setId(null);
		announcement.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_announcement));
		announcement.setMainStatus(1);
		announcement.setSenderName(username);
		announcement.setCreatedBy(username);
		announcement.setCreatedAt(date);
		announcementMapper.insert(announcement);
		json.put("id", announcement.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改公告（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param announcement 公告对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateAnnouncement(JSONObject json, Announcement announcement, String username){
		Date date = new Date();
		Integer announcementId = announcement.getId();
		if(null == announcementId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Announcement dbAnnouncement = announcementMapper.selectByPrimaryKey(announcementId);
		if(null == dbAnnouncement){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		announcement.setUpdatedBy(username);
		announcement.setUpdatedAt(date);
		announcementMapper.updateByPrimaryKeySelective(announcement);
		return Result.success(json);
	}
	
}
