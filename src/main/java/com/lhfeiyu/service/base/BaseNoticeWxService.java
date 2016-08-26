package com.lhfeiyu.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.domain.LhTip;
import com.lhfeiyu.dao.domain.UserMapper;
import com.lhfeiyu.po.domain.User;
import com.lhfeiyu.thirdparty.wx.business.Message;
import com.lhfeiyu.tools.Check;
import com.lhfeiyu.tools.Result;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-业务层：微信通知消息 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日14:30:35 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Service("baseNoticeWxService")
public class BaseNoticeWxService {
	
	@Autowired
	UserMapper userMapper;
	
	public JSONObject sendWxNotice(JSONObject json, Integer receiverId, String templateId, JSONObject messageJson){
		//需要考虑群发的情况
		if(Check.isNull(receiverId)){
			return Result.failure(json, LhTip.msg_receiver_null, LhTip.code_receiver_null);
		}
		if(Check.isNull(templateId)){
			return Result.failure(json, LhTip.msg_wx_notice_templateId_null, LhTip.code_wx_notice_templateId_null);
		}
		if(null == messageJson){
			return Result.failure(json, LhTip.msg_wx_notice_message_null, LhTip.code_wx_notice_message_null);
		}
		
		User user = userMapper.selectByPrimaryKey(receiverId);
		String openid = user.getWxOpenid();
		if (Check.isNotNull(openid)) {// 微信消息通知
			Message.sendMessage(templateId, messageJson);
			// JSONObject resultJson = Message.sendMessage(LhConstField.wx_moban_10,messageJson);
		}
		return Result.success(json);
	}
	
}

