package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.dao.domain.MessageMapper;
import com.lhfeiyu.po.domain.Message;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：消息记录 message<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseMessageService")
public class BaseMessageService extends CommonService<Message> {

	@Autowired
	MessageMapper messageMapper;
	
	public JSONObject getMessageList(JSONObject json, Map<String, Object> map) {
		List<Message> messageList = messageMapper.selectListByCondition(map);
		Integer total = messageMapper.selectCountByCondition(map);
		return Result.gridData(messageList, total, json);
	}
	
	/**
	 * 新增或修改消息记录
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param message 消息记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateMessage(JSONObject json, Message message, String username){
		String content = message.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, "内容不能为空", "content_null");
		}
		if(null == message.getId()){//添加
			return addMessage(json, message, username);
		}else{//修改
			return updateMessage(json, message, username);
		}
	}
	
	/**
	 * 新增消息记录（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param message 消息记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addMessage(JSONObject json, Message message, String username){
		Date date = new Date();
		message.setId(null);
		message.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_message));
		message.setMainStatus(1);
		message.setCreatedBy(username);
		message.setCreatedAt(date);
		messageMapper.insert(message);
		json.put("id", message.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改消息记录（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param message 消息记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateMessage(JSONObject json, Message message, String username){
		Date date = new Date();
		Integer messageId = message.getId();
		if(null == messageId){//添加
			return Result.failure(json, "编号为空，无法进行执行修改", "messageId_null");
		}
		Message dbMessage = messageMapper.selectByPrimaryKey(messageId);
		if(null == dbMessage){
			return Result.failure(json, "该条数据不存在，无法进行执行修改", "message_null");
		}

		message.setUpdatedBy(username);
		message.setUpdatedAt(date);
		messageMapper.updateByPrimaryKeySelective(message);
		return Result.success(json);
	}
	
	
}

