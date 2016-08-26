package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ForumMapper;
import com.lhfeiyu.po.domain.Forum;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：论坛 Forum <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseForumService")
public class BaseForumService extends CommonService<Forum> {

	@Autowired
	ForumMapper forumMapper;
	
	public JSONObject getForumList(JSONObject json, Map<String, Object> map) {
		List<Forum> forumList = forumMapper.selectListByCondition(map);
		Integer total = forumMapper.selectCountByCondition(map);
		return Result.gridData(forumList, total, json);
	}
	
	/**
	 * 新增或修改论坛
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forum 论坛对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateForum(JSONObject json, Forum forum, String username){
		/*String content = forum.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == forum.getId()){//添加
			return addForum(json, forum, username);
		}else{//修改
			return updateForum(json, forum, username);
		}
	}
	
	/**
	 * 新增论坛（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forum 论坛对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addForum(JSONObject json, Forum forum, String username){
		Date date = new Date();
		forum.setId(null);
		forum.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_forum));
		forum.setMainStatus(1);
		forum.setCreatedBy(username);
		forum.setCreatedAt(date);
		forumMapper.insert(forum);
		json.put("id", forum.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改论坛（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forum 论坛对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateForum(JSONObject json, Forum forum, String username){
		Date date = new Date();
		Integer forumId = forum.getId();
		if(null == forumId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Forum dbForum = forumMapper.selectByPrimaryKey(forumId);
		if(null == dbForum){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		forum.setUpdatedBy(username);
		forum.setUpdatedAt(date);
		forumMapper.updateByPrimaryKeySelective(forum);
		return Result.success(json);
	}

	public Forum selectByPrimaryKeyAndIsJoin(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return forumMapper.selectByPrimaryKeyAndIsJoin(map);
	}

	public Forum selectByName(String forumName) {
		// TODO Auto-generated method stub
		return forumMapper.selectByName(forumName);
	}
	
}
