package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.CommentMapper;
import com.lhfeiyu.po.domain.Comment;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：评论回复 comment<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseCommentService")
public class BaseCommentService extends CommonService<Comment> {

	@Autowired
	CommentMapper commentMapper;
	
	public JSONObject getCommentList(JSONObject json, Map<String, Object> map) {
		List<Comment> commentList = commentMapper.selectListByCondition(map);
		Integer total = commentMapper.selectCountByCondition(map);
		return Result.gridData(commentList, total, json);
	}
	
	/**
	 * 新增或修改评论回复
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param comment 评论回复对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateComment(JSONObject json, Comment comment, String username){
		String content = comment.getContent();
		if(Check.isNull(content)){
			return Result.failure(json, LhTip.msg_content_null,  LhTip.code_content_null);
		}
		if(null == comment.getId()){//添加
			return addComment(json, comment, username);
		}else{//修改
			return updateComment(json, comment, username);
		}
	}
	
	/**
	 * 新增评论回复（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param comment 评论回复对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addComment(JSONObject json, Comment comment, String username){
		Date date = new Date();
		comment.setId(null);
		comment.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_comment));
		comment.setMainStatus(1);
		comment.setCreatedBy(username);
		comment.setCreatedAt(date);
		commentMapper.insert(comment);
		json.put("commentId", comment.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改评论回复（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param comment 评论回复对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateComment(JSONObject json, Comment comment, String username){
		Date date = new Date();
		Integer commentId = comment.getId();
		if(null == commentId){//添加
			return Result.failure(json, "编号为空，无法进行执行修改", "commentId_null");
		}
		Comment dbComment = commentMapper.selectByPrimaryKey(commentId);
		if(null == dbComment){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "comment_null");
		}
		comment.setUpdatedBy(username);
		comment.setUpdatedAt(date);
		commentMapper.updateByPrimaryKeySelective(comment);
		return Result.success(json);
	}
	
	
}

