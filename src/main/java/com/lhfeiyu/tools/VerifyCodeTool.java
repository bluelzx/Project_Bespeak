package com.lhfeiyu.tools;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.lhfeiyu.config.base.BaseTip;
import com.lhfeiyu.domain.YtxMessage;
import com.lhfeiyu.util.SendMsgUtil;
import com.lhfeiyu.util.YTX_MSG;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：验证码 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class VerifyCodeTool {

	/** 发送验证码到手机（采用云通讯模板）  */
	public JSONObject sendVerifycodeToPhone(HttpSession session, YtxMessage ytx){
		JSONObject json = new JSONObject();
		int verifyCodeNum = 1;
		Object verifyCodeNumObj = session.getAttribute("verifyCodeNum");
		if(null != verifyCodeNumObj){
			verifyCodeNum = (Integer)verifyCodeNumObj;
			if(verifyCodeNum > 20){
				return Result.failure(json, BaseTip.msg_verifyCode_phone_max_num, BaseTip.code_verifyCode_phone_max_num);
			}
		}
		//phone : 短信接口发送短信验证码
		String verifycode = SendMsgUtil.createRandomVcode();
		System.out.println("sendVerifycodeToPhone-verifycode: "+verifycode);
		String[] params = new String[] { verifycode, "30" };
		ytx.setParams(params);//设置参数
		YTX_MSG.send(ytx);
		session.setAttribute("verifycode", verifycode);
		session.setAttribute("verifyCodeNum", ++verifyCodeNum);
		//json.put("verifycode", verifycode); - 不应该将验证码直接返回到页面，不安全
		return Result.success(json, BaseTip.msg_verifyCode_phone_send, BaseTip.code_verifyCode_phone_send);
	}

}
