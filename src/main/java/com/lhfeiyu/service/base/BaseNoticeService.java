package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.NoticeMapper;
import com.lhfeiyu.po.domain.Notice;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：通知消息 notice<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseNoticeService")
public class BaseNoticeService extends CommonService<Notice> {
	
	@Autowired
	NoticeMapper noticeMapper;
	
	public JSONObject getNoticeList(JSONObject json, Map<String, Object> map) {
		List<Notice> noticeList = noticeMapper.selectListByCondition(map);
		Integer total = noticeMapper.selectCountByCondition(map);
		return Result.gridData(noticeList, total, json);
	}
	
	/**
	 * 新增或修改通知消息
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param notice 通知消息对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateNotice(JSONObject json, Notice notice, String username){
		String content = notice.getContent();
		String title = notice.getTitle();
		Integer receiverId = notice.getReceiverId();
		String receiverIds = notice.getReceiverIds();
		
		if(Check.isNull(title)){
			return Result.failure(json, LhTip.msg_title_null, LhTip.code_title_null);
		}
		if(Check.isNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}
		if(Check.isNull(receiverId) || Check.isNull(receiverIds)){
			return Result.failure(json, LhTip.msg_receiver_null, LhTip.code_receiver_null);
		}
		if(null == notice.getId()){//添加
			return addNotice(json, notice, username);
		}else{//修改
			return updateNotice(json, notice, username);
		}
	}
	
	/**
	 * 新增通知消息（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param notice 通知消息对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addNotice(JSONObject json, Notice notice, String username){
		Date date = new Date();
		notice.setId(null);
		notice.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_notice));
		notice.setMainStatus(1);
		notice.setReadStatus(1);
		notice.setCreatedBy(username);
		notice.setCreatedAt(date);
		noticeMapper.insert(notice);
		json.put("noticeId", notice.getId());
		return Result.success(json);
	}
	
	public Notice buildNotice(Integer receiverId, String title, String content, String linkUrl){
		Notice notice = new Notice();
		notice.setReceiverId(receiverId);
		notice.setTitle(title);
		notice.setLinkUrl(linkUrl);
		notice.setContent(content);
		return notice;
	}
	
	public Notice buildNotice(String receiverIds, String title, String content, String linkUrl){
		Notice notice = new Notice();
		notice.setReceiverIds(receiverIds);
		notice.setTitle(title);
		notice.setLinkUrl(linkUrl);
		notice.setContent(content);
		return notice;
	}
	
	/**
	 * 修改通知消息（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param notice 通知消息对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateNotice(JSONObject json, Notice notice, String username){
		Date date = new Date();
		Integer noticeId = notice.getId();
		if(null == noticeId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Notice dbNotice = noticeMapper.selectByPrimaryKey(noticeId);
		if(null == dbNotice){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		notice.setUpdatedBy(username);
		notice.setUpdatedAt(date);
		noticeMapper.updateByPrimaryKeySelective(notice);
		return Result.success(json);
	}
	
	
}

