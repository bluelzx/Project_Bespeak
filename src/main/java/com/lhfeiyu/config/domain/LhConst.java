package com.lhfeiyu.config.domain;

import com.lhfeiyu.config.base.BaseConst;
import com.lhfeiyu.util.Md5Util;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 配置包：常量配置类（运营）（继承基础常量配置类） <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.config.ConstField <p>
 */
public class LhConst extends BaseConst {
	
	public static final int USER = 0;
	
	public static final String web_base_url = "http://weipaike.net";
	
	public static final String rl_ytx_sid = "8a48b551523a5c1201523dbb47ea0666";
	public static final String rl_ytx_authToken = "cb79b92ebcc54ceaa2b92fe88222c91d";
	public static final String rl_ytx_appId = "8a48b551523a5c1201523ebeef200aa5";//common_base_chat.js:var resp = RL_YTX.init("8a48b551523a5c1201523ebeef200aa5");//appId
	public static final String rl_ytx_appToken = "32463f3d748492e4302e7f478fc77863";
	
	public static final String rl_ytx_ipHttps = "https://app.cloopen.com";//REST服务器IP
	public static final String rl_ytx_ip = "app.cloopen.com";//REST服务器IP
	public static final String rl_ytx_port = "8883";//REST服务器端口
	public static final String rl_ytx_ip_port = "https://app.cloopen.com:8883";//REST服务器IP+端口
	//上线后：sandboxapp -> app
	public static final String rl_ytx_base_sub_url = "https://app.cloopen.com:8883/2013-12-26/SubAccounts/";
	public static final String rl_ytx_base_main_url = "https://app.cloopen.com:8883/2013-12-26/Accounts/8a48b551523a5c1201523dbb47ea0666";
	public static final String rl_ytx_push_url = "https://app.cloopen.com:8883/2013-12-26/Accounts/8a48b551523a5c1201523dbb47ea0666/IM/PushMsg?sig=";
	
	public static final String rl_ytx_default_subAccount = "4b4aa7d6ba8511e59288ac853d9f54f2";
	public static final String rl_ytx_default_password = "3c7ae373d513e396226e7b658500cf47";
	public static final String rl_ytx_default_voip = "8008953800000002";
	public static final String rl_ytx_default_voip_pswd = "cVSsuUVJ";
	
	//public static final String auction_quick_group_id = "gg800895384";
	public static final String auction_quick_group_121_id = "gg800895384";//即时拍-文玩
	public static final String auction_quick_group_122_id = "gg800895385";//即时拍-钱证
	public static final String auction_quick_group_123_id = "gg800895386";//即时拍-书画
	public static final String auction_quick_group_124_id = "gg800895387";//即时拍-珠宝
	public static final String auction_quick_group_125_id = "gg800895388";//即时拍-玉器
	public static final String auction_quick_group_126_id = "gg800895389";//即时拍-竹木
	public static final String auction_quick_group_127_id = "gg8008953810";//即时拍-金石
	public static final String auction_quick_group_128_id = "gg8008953811";//即时拍-陶瓷
	public static final String auction_quick_group_129_id = "gg8008953812";//即时拍-杂珍
	
	public static final String rl_ytx_msg_moban_id = "62710";
	
	public static final String rl_ytx_msg_moban_notice_id = "62639";
	
	//微信相关
	/** 拍卖结束提醒 */
	public static final String wx_moban_1 = "7fHYb4X_O7ihIpNrM1-r8zJobQ6AY9UcyvnBs3i3R3g";
	/** 实时交易提醒 */
	public static final String wx_moban_2 = "Q0EXJns1vjgS2VwWHywGPr6BgkrKh8fDzWFjcV4aoCw";
	/** 评价完成结果通知 */
	public static final String wx_moban_3 = "QH26XFEVxCOd6rI1zDOqLE7BGt2lYfrRlOJv7nrpMYA";
	/** 竞拍成功通知 */
	public static final String wx_moban_4 = "UiPQ4Bl3T58IDBAiNU6uhjqTI_ya2aRfqVCtXpTw9_0";
	/** 付款成功通知 */
	public static final String wx_moban_5 = "W2xJkIBolM7oPgesdtZHP9dHyDIgtyrsQFi6LEXy8bk";
	/** 商品已发出通知 */
	public static final String wx_moban_6 = "cDuLYLkJb-EZqYHoR_FrsBZGUQswR8b9Cy39ivoRV9o";
	/** 订单确认收货通知 */
	public static final String wx_moban_7 = "eaGaAL5yfsi8Llynwd1kDM0a1HJ9uUvz5gt8FqPy8Pc";
	/** 出价被超越通知 */
	public static final String wx_moban_8 = "pKpg4VTuQOUmmhLXyUlWv39K1kQF_0_X-aKCVlhb_8Y";
	/** 提现成功通知 */
	public static final String wx_moban_9 = "s2sAtrNSCk5SNxP5eNJV8kuA6fMQEcaOB5oQe615mXo";
	/** 充值通知 */
	public static final String wx_moban_10 = "wLtGKHx7f6W3vxyilmeCFhnZ-8qh_d_27n1_Wi7F4T4";
	
	public static final String page_user_center = "http://weipaike.net/user";
	public static final String wx_message_remark = "备注：如有疑问，请联系微拍客客服人员。";
	
	/** 快递相关 */
	public static final String kuaidi_key = "ttCDrbtZ441";
	public static final String kuaidi_pollurl = "http://www.kuaidi100.com/poll";
	public static final String kuaidi_callbackurl = "http://www.weipaike.net/kuaidi/callback";
	
	/** 阿里云OSS相关 */
	public static final String oss_endpoint = "http://oss-cn-qingdao.aliyuncs.com";
	public static final String oss_bucketName = "weipaike";
	public static final String oss_bucketEndpoint = "http://weipaike.oss-cn-qingdao.aliyuncs.com";
	public static final String oss_accessKeyId = "4ujBM3rcXPHOXKHE";
	public static final String oss_accessKeySecret = "chveJncslljPBHhRp1ENLjT56QVwSW";
	
	public static String generateSig(String timeStamp){//目前采用的是帐号密码登陆，后期需要改为应用登陆 - JS端采用就用登陆，服务器端采用账号登陆
		///{SoftVersion}/Accounts/{accountSid}/IM/PushMsg
		//https://app.cloopen.com:8883/2013-12-26/SubAccounts/{subAccountSid}/{func}/{funcdes}?sig={SigParameter}
		//使用MD5加密（主帐号Id + 主帐号授权令牌 +时间戳）。其中主帐号Id和主帐号授权令牌分别对应管理控制台中的           ACCOUNT SID和AUTH TOKEN。
		//时间戳是当前系统时间，格式"yyyyMMddHHmmss"。时间戳有效时间为24小时，如：20140416142030
		String sid = LhConst.rl_ytx_sid;
		String authToken = LhConst.rl_ytx_authToken;
		String sig = Md5Util.encrypt(sid+authToken+timeStamp);
		return sig;
	}
	
	public static String generateSubSig(String subAccount,String password, String timeStamp){//帐号密码登陆
		String subSig = Md5Util.encrypt(subAccount+password+timeStamp);
		return subSig;
	}
	public static String generateSubSig(String userSerial, String timeStamp){//应用登陆
		String appId = LhConst.rl_ytx_appId;
		String appToken = LhConst.rl_ytx_appToken;
		String subSig = Md5Util.encrypt(appId+userSerial+timeStamp+appToken);
		return subSig;
	}
	
	public static String getPushMsgUrl(String timeStamp){
		String baseUrl = LhConst.rl_ytx_push_url;
		String url = baseUrl+generateSig(timeStamp);
		return url;
	}
	
	public static String getAuthorization(String timeStamp){
		String sid = LhConst.rl_ytx_sid;
		String authStr =sid+":"+timeStamp;
		String authorization = Md5Util.base64Encode(authStr.getBytes());
		return authorization;
	}
	
	public static String getSubAuthorization(String subAccount,String timeStamp){
		String authStr =subAccount+":"+timeStamp;
		String authorization = Md5Util.base64Encode(authStr.getBytes());
		return authorization;
	}
	
	
}
