package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ForumMemberMapper;
import com.lhfeiyu.po.domain.ForumMember;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：论坛成员 ForumMember <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseForumMemberService")
public class BaseForumMemberService extends CommonService<ForumMember> {

	@Autowired
	ForumMemberMapper forumMemberMapper;
	
	public JSONObject getForumMemberList(JSONObject json, Map<String, Object> map) {
		List<ForumMember> forumMemberList = forumMemberMapper.selectListByCondition(map);
		Integer total = forumMemberMapper.selectCountByCondition(map);
		return Result.gridData(forumMemberList, total, json);
	}
	
	/**
	 * 新增或修改论坛成员
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forumMember 论坛成员对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateForumMember(JSONObject json, ForumMember forumMember, String username){
		/*String content = forumMember.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}*/
		if(null == forumMember.getId()){//添加
			return addForumMember(json, forumMember, username);
		}else{//修改
			return updateForumMember(json, forumMember, username);
		}
	}
	
	/**
	 * 新增论坛成员（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forumMember 论坛成员对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addForumMember(JSONObject json, ForumMember forumMember, String username){
		Date date = new Date();
		forumMember.setId(null);
		forumMember.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_forumMember));
		forumMember.setMainStatus(1);
		forumMember.setCreatedBy(username);
		forumMember.setCreatedAt(date);
		forumMemberMapper.insert(forumMember);
		json.put("id", forumMember.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改论坛成员（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forumMember 论坛成员对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateForumMember(JSONObject json, ForumMember forumMember, String username){
		Date date = new Date();
		Integer forumMemberId = forumMember.getId();
		if(null == forumMemberId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		ForumMember dbForumMember = forumMemberMapper.selectByPrimaryKey(forumMemberId);
		if(null == dbForumMember){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		forumMember.setUpdatedBy(username);
		forumMember.setUpdatedAt(date);
		forumMemberMapper.updateByPrimaryKeySelective(forumMember);
		return Result.success(json);
	}
	
}
