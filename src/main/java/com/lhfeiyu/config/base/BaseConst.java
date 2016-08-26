package com.lhfeiyu.config.base;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-配置包：常量配置类（运营）
 * <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华
 * <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03
 * <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com
 * <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0
 * <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>
 * <p>
 */
public class BaseConst {

	public static final int USER = 0;
	/** 前台用户默认密码 */
	public static final String user_default_password = "888888";
	/** 后台用户默认密码 */
	public static final String admin_default_password = "lh888888";
	/** 系统操作对应操作人 */
	public static final String operater_system = "-SYS-";
	/** 密码最低长度 */
	public static final int password_min_length = 6;

	// dict code

	public static final String comment_type_goods = "comment_type_goods";

	public static final String frozenMoney_status_frozen = "frozenMoney_status_frozen";
	public static final String frozenMoney_status_deduct = "frozenMoney_status_deduct";
	public static final String frozenMoney_status_return = "frozenMoney_status_return";

	public static final String frozenMoney_type_credit = "frozenMoney_type_credit";
	public static final String frozenMoney_type_goods = "frozenMoney_type_goods";
	public static final String frozenMoney_type_bonus = "frozenMoney_type_bonus";

	public static final String fund_opt_type_freezeMoney = "fund_opt_type_freezeMoney";
	public static final String fund_opt_type_activateMoney = "fund_opt_type_activateMoney";
	public static final String fund_opt_type_incomeMoney = "fund_opt_type_incomeMoney";
	public static final String fund_opt_type_outcomeMoney = "fund_opt_type_outcomeMoney";
	public static final String fund_opt_type_outcomeFrozenMoney = "fund_opt_type_outcomeFrozenMoney";
	public static final String fund_opt_type_incomeFrozenMoney = "fund_opt_type_incomeFrozenMoney";

	public static final String fund_opt_level_sys = "fund_opt_level_sys";
	public static final String fund_opt_level_admin = "fund_opt_level_admin";
	public static final String fund_opt_level_user = "fund_opt_level_user";

	public static final String goods_status_sell = "goods_status_sell";
	public static final String goods_status_lack = "goods_status_lack";
	public static final String goods_status_frozen = "goods_status_frozen";
	public static final String goods_status_off = "goods_status_off";
	
	//订单总状态
	public static final String order_done_status_no = "order_done_status_no";
	public static final String order_done_status_yes = "order_done_status_yes";

	//订单状态
	public static final String order_status_todo = "order_status_todo";
	public static final String order_status_done = "order_status_done";
	public static final String order_status_cancel = "order_status_cancel";
	public static final String order_status_invalid = "order_status_invalid";
	public static final String order_status_return = "order_status_return";
	public static final String order_status_noreturn = "order_status_noreturn";//拒绝退款
	public static final String order_status_apply_return = "order_status_apply_return";

	//支付状态
	public static final String pay_status_todo = "pay_status_todo";
	public static final String pay_status_ing = "pay_status_ing";
	public static final String pay_status_done = "pay_status_done";
	
	//服务状态
	public static final String shipping_status_todo = "shipping_status_todo";
	public static final String shipping_status_done = "shipping_status_done";
	public static final String shipping_status_receive = "shipping_status_receive";
	public static final String shipping_status_return = "shipping_status_return";

	 //交易状态
	public static final String trade_status_todo = "trade_status_todo";
	public static final String trade_status_done = "trade_status_done";
	public static final String trade_status_cancel = "trade_status_cancel";
	public static final String trade_status_invalid = "trade_status_invalid";

	public static final String noticeTypeCode = "money_invalid";

	// 微信相关
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

}
