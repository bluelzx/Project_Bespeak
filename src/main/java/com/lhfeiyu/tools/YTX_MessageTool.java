package com.lhfeiyu.tools;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lhfeiyu.config.domain.LhConst;
import com.lhfeiyu.dao.domain.SchedulersMapper;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 群发短信 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
@Component
public class YTX_MessageTool {
	
	@Autowired
	private SchedulersMapper sMapper;

	public void sendMsgByCount(Map<String, Object> map, int count, String auctionName) {
		String phones = null;
		String[] params = new String[] { auctionName };// 通知消息只有一个参数：拍场名称
		// phones = "18702827609";
		String mobanId = LhConst.rl_ytx_msg_moban_notice_id;
		// params = new String[] { "123456", "30" };
		if (count <= 50) {
			phones = sMapper.selectNoticeUserPhones(map);
			// TODO 发送短信
			// YTX_MSG.send(phones, mobanId, params);
			System.out.println("发送短信：phones-" + phones + ",mobanId-" + mobanId + ",params-" + params.toString());
		} else {
			int length = count / 50;
			map.put("count", 50);
			for (int i = 0; i <= length; i++) {
				map.put("start", 50 * i);
				phones = sMapper.selectNoticeUserPhones(map);
				// TODO 发送短信
				// YTX_MSG.send(phones, mobanId, params);
				System.out.println("发送短信：phones-" + phones + ",mobanId-" + mobanId + ",params-" + params.toString());
			}
		}
	}

	
}
