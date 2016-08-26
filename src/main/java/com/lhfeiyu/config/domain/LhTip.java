package com.lhfeiyu.config.domain;

import com.lhfeiyu.config.base.BaseTip;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 配置包：提示信息配置类（继承基础提示类） <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.config.BaseLhPagePath <p>
 */
public class LhTip extends BaseTip {
	
	//msg
	public static final String msg_auction_null = "该次拍卖已经不存在";
	public static final String msg_auction_over = "该次拍卖已经结束";
	public static final String msg_auctionUpdate_ing = "该次拍卖正在拍卖中，不能再进行修改";
	public static final String msg_auctionUpdate_over = "该次拍卖已经结束，不能再进行修改";
	public static final String msg_auction_delete_not_over_offered = "该次拍卖尚未结束并且已经有买家出价，无法执行删除";
	
	public static final String msg_auctionInst_null = "您尚未入驻专场";
	public static final String msg_auctionInstId_null = "专场编号不能为空";
	
	public static final String msg_userPraise_repeat = "您已经赞过此拍场";
	
	public static final String msg_wx_batch_fans_zero = "您当前还没有粉丝，无法群发消息";
	public static final String msg_wx_batch_goods_zero = "您当前还没有已经上传的藏品，无法群发消息给粉丝";
	
	//code
	public static final String code_auction_null = "auction_null";
	public static final String code_auction_over = "auction_over";
	public static final String code_auctionUpdate_ing = "auctionUpdate_ing";
	public static final String code_auctionUpdate_over = "auctionUpdate_over";
	public static final String code_auction_delete_not_over_offered = "auction_delete_not_over_offered";
	
	public static final String code_auctionInst_null = "auctionInst_null";
	public static final String code_auctionInstId_null = "auctionInstId_null";
	
	public static final String code_userPraise_repeat = "userPraise_repeat";
	
	public static final String code_wx_batch_fans_zero = "wx_batch_fans_zero";
	public static final String code_wx_batch_goods_zero = "wx_batch_goods_zero";
	
	
	//template
	public static final String tp_auctionMicro_success = "【-GOODS_NAME-】恭喜您竞拍成功（价格：-PRICE-元），系统已经为您生成订单，可以到个人中心查看订单并付款";
}
