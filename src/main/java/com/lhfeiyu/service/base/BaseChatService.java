package com.lhfeiyu.service.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhAssets;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.ChatMapper;
import com.lhfeiyu.po.domain.Chat;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.CommonGenerator;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：聊天记录 chat<p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseChatService")
public class BaseChatService extends CommonService<Chat> {

	@Autowired
	ChatMapper chatMapper;
	
	public JSONObject getChatList(JSONObject json, Map<String, Object> map) {
		List<Chat> chatList = chatMapper.selectListByCondition(map);
		Integer total = chatMapper.selectCountByCondition(map);
		return Result.gridData(chatList, total, json);
	}
	
	/**
	 * 新增或修改聊天记录
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param chat 聊天记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addUpdateChat(JSONObject json, Chat chat, String username){
		Integer receiverId = chat.getReceiverId();
		Integer senderId = chat.getSenderId();
		String content = chat.getContent();
		if(!Check.isNotNull(content)){
			return Result.failure(json, LhTip.msg_content_null, LhTip.code_content_null);
		}
		if(null == senderId){
			return Result.failure(json, LhTip.msg_sender_null, LhTip.code_sender_null);
		}
		if(null == receiverId){
			return Result.failure(json, LhTip.msg_receiver_null, LhTip.code_receiver_null);
		}
		if(Check.integerEqual(senderId, receiverId)){
			return Result.failure(json, LhTip.msg_chat_self, LhTip.code_chat_self);
		}
		if(null == chat.getId()){//添加
			return addChat(json, chat, username);
		}else{//修改
			return updateChat(json, chat, username);
		}
	}
	
	/**
	 * 新增聊天记录（代码若已经存在则提示失败）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param chat 聊天记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject addChat(JSONObject json, Chat chat, String username){
		Date date = new Date();
		chat.setId(null);
		chat.setSerial(CommonGenerator.getSerialByDate(LhAssets.serial_prefix_chat));
		chat.setSendTime(date);
		chat.setMainStatus(1);
		chat.setCreatedBy(username);
		chat.setCreatedAt(date);
		chatMapper.insert(chat);
		json.put("chatId", chat.getId());
		return Result.success(json);
	}
	
	/**
	 * 修改聊天记录（ID不能为空，数据库中必须存在该ID的数据）
	 * @param json 消息数据容器对象（主要用于保存提示消息或数据）
	 * @param chat 聊天记录对象
	 * @param username 操作人名称（数据库记录）
	 * @return JSONObject
	 */
	public JSONObject updateChat(JSONObject json, Chat chat, String username){
		Date date = new Date();
		Integer chatId = chat.getId();
		if(null == chatId){//添加
			return Result.failure(json, LhTip.msg_update_id_null, LhTip.code_update_id_null);
		}
		Chat dbChat = chatMapper.selectByPrimaryKey(chatId);
		if(null == dbChat){
			return Result.failure(json, LhTip.msg_update_obj_null, LhTip.code_update_obj_null);
		}

		chat.setUpdatedBy(username);
		chat.setUpdatedAt(date);
		chatMapper.updateByPrimaryKeySelective(chat);
		return Result.success(json);
	}
	
	
}

