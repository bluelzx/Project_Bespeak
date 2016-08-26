package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ForumArticleMapper;
import com.lhfeiyu.po.domain.ForumArticle;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：论坛文章 ForumArticle <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseForumArticleService")
public class BaseForumArticleService extends CommonService<ForumArticle> {

	@Autowired
	ForumArticleMapper forumArticleMapper;
	
	public JSONObject getForumArticleList(JSONObject json, Map<String, Object> map) {
		List<ForumArticle> forumArticleList = forumArticleMapper.selectListByCondition(map);
		Integer total = forumArticleMapper.selectCountByCondition(map);
		return Result.gridData(forumArticleList, total, json);
	}
	
	/**
	 * 新增或修改论坛文章
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forumArticle 论坛文章对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateForumArticle(JSONObject json, ForumArticle forumArticle, String username){
		String content = forumArticle.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}
		if(null == forumArticle.getId()){//添加
			return addForumArticle(json, forumArticle, username);
		}else{//修改
			return updateForumArticle(json, forumArticle, username);
		}
	}
	
	/**
	 * 新增论坛文章（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forumArticle 论坛文章对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addForumArticle(JSONObject json, ForumArticle forumArticle, String username){
		Date date = new Date();
		forumArticle.setId(null);
		forumArticle.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_forumArticle));
		forumArticle.setMainStatus(1);
		forumArticle.setCreatedBy(username);
		forumArticle.setCreatedAt(date);
		forumArticleMapper.insert(forumArticle);
		json.put("id", forumArticle.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改论坛文章（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param forumArticle 论坛文章对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateForumArticle(JSONObject json, ForumArticle forumArticle, String username){
		Date date = new Date();
		Integer forumArticleId = forumArticle.getId();
		if(null == forumArticleId){
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		ForumArticle dbForumArticle = forumArticleMapper.selectByPrimaryKey(forumArticleId);
		if(null == dbForumArticle){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		forumArticle.setUpdatedBy(username);
		forumArticle.setUpdatedAt(date);
		forumArticleMapper.updateByPrimaryKeySelective(forumArticle);
		return Result.success(json);
	}


}
