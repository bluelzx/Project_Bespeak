package com.lhfeiyu.config.base;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-配置包：路径类 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class BasePage {
	
	/**
	 * 后台页面url统一加前缀 
	 * 前台页面url不加前缀，命名尽量以业务来命名，避免混淆或不清晰
	 */

	public static final String backPrefix = "back/base/";//后台页面前缀
	
	// abcdefg hihjklmn opqrst uvwxzy
	/** 后台 基本模块 */
	public static final String back_admin = 			backPrefix + "admin/admin";//用户
	
	public static final String back_announcement = 		backPrefix + "article/announcement";//公告
	public static final String back_annoAddUpdate =		backPrefix + "article/annoAddUpdate";//
	public static final String back_annoDetail = 		backPrefix + "article/annoDetail";//
	
	public static final String back_article = 			backPrefix + "article/article";//资讯管理
	public static final String back_articleAddUpdate = 	backPrefix + "article/articleAddUpdate";//
	public static final String back_articleDetail = 	backPrefix + "article/articleDetail";//
	
	public static final String back_apply = 			backPrefix + "auth/apply";//申请处理

	public static final String back_album = 			backPrefix + "file/album";//相册
	public static final String back_albumAddUpdate =	backPrefix + "file/albumAddUpdate";//
	public static final String back_albumDetail = 		backPrefix + "file/albumDetail";//
	public static final String back_picture = 			backPrefix + "file/picture";//图片管理
	
	public static final String back_forum = 			backPrefix + "forum/forum";//
	public static final String back_forumArticle = 		backPrefix + "forum/forumArticle";//
	public static final String back_forumMember = 		backPrefix + "forum/forumMember";//
	
	public static final String back_accountLog = 		backPrefix + "fund/accountLog";//交易记录
    public static final String back_charge = 			backPrefix + "fund/charge";//充值记录
    public static final String back_withdraw = 			backPrefix + "fund/withdraw";//申请提现
    
    public static final String back_admentAddUpdate =	backPrefix + "message/admentAddUpdate";//广告增改
    public static final String back_advertisement = 	backPrefix + "message/advertisement";//广告
	public static final String back_admentDetail = 		backPrefix + "message/admentDetail";//广告详细
    public static final String back_chat = 				backPrefix + "message/chat";//单人聊天
    public static final String back_chatProfession = 	backPrefix + "message/chatProfession";//单人聊天
   
	public static final String back_message = 			backPrefix + "message/message";//消息记录
    public static final String back_notice = 			backPrefix + "message/notice";//通知消息
    
    public static final String back_dict = 				backPrefix + "sys/dict";//数据字典
    
    public static final String back_loginLog = 			backPrefix + "sys/loginLog";//
    public static final String back_operationLog = 		backPrefix + "sys/operationLog";//操作记录
    public static final String back_provinceCityArea = 	backPrefix + "sys/provinceCityArea";//用户角色
    public static final String back_quickMenu = 		backPrefix + "sys/quickMenu";//快捷菜单
    public static final String back_role = 				backPrefix + "sys/role";//角色
    public static final String back_roleMenu = 			backPrefix + "sys/roleMenu";//用户角色
    public static final String back_sysDict = 			backPrefix + "sys/sysDict";//官方账号/费用比例
    
    public static final String back_user = 				backPrefix + "user/userInfo";//用户信息
    public static final String back_userControl = 		backPrefix + "user/userControl";//用户控制
    public static final String back_userCustomer = 		backPrefix + "user/userCustomer";//用户客户
    public static final String back_userFund = 			backPrefix + "user/userFund";//用户资金
    public static final String back_userRelation = 		backPrefix + "user/userRelation";//用户关联

    public static final String back_orderinfo =     "back/domain/order/orderinfo";//订单信息
    public static final String back_refundorderinfo ="back/domain/order/refundorderinfo";//订单退款信息
    
    public static final String back_goods =     "back/domain/goods/goods";//服务信息
    public static final String shop_goods =     "shop/goods";//服务信息
    public static final String back_treatment =     "back/domain/goods/treatment";//后台疗程信息
    public static final String shop_treatment =     "shop/treatment";//商家疗程信息
    public static final String back_provider =     "back/domain/provider/providerInfo";//技师信息
    public static final String back_bespeak =     "back/domain/provider/bespeak";//技师信息
    public static final String back_course =     "back/domain/course/course";//课程信息
    public static final String back_investigation =     "back/domain/course/investigation";//报名信息
    public static final String back_coupon =     "back/domain/marketing/coupon";//优惠券信息
    public static final String back_comment = 	"back/domain/message/comment";//评论回复
    public static final String login = "/front/login";//前台登陆页面
    public static final String back_login = "/back/login";//后台登陆页面
    public static final String shop_login = "/shop/login";//商家登陆页面
    public static final String back_main   = "/back/main";//后台主页面
    public static final String shop_main   = "/shop/main";//后台主页面
	public static final String back_error = back_login;//错误页面
	public static final String back_jumpToBackLogin = "/back/jumpToBackLogin";//跳转到后台登陆页面
	
    public static final String error = "/front/index";//错误页面
    public static final String jumpToLogin = "/front/toLogin";//跳转到前台登陆页面
    /** 公共页面 开始 */
   
    
    
}
