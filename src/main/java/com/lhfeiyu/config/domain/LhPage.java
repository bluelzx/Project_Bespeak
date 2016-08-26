package com.lhfeiyu.config.domain;

import com.lhfeiyu.config.base.BasePage;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 配置包：路径类（继承基础路径类） <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.config.LhPagePath <p>
 */
public class LhPage extends BasePage {
	
	public static final String backPrefix = "/back/page/";//后台页面前缀
	
	/** 基本模块 */
    public static final String backLogin = backPrefix + "login";//后台登陆页面
    public static final String backMain = backPrefix + "/back/page/main";//后台主页面
    public static final String backUserInfo = backPrefix + "/back/page/base/userInfo";//用户信息
    public static final String backUserControl = backPrefix + "/back/page/base/userControl";//用户控制
    public static final String backUserFund = backPrefix + "/back/page/base/userFund";//用户资金
    public static final String backUserRelation = backPrefix + "/back/page/base/userRelation";//用户关联
    public static final String backArticle = backPrefix + "/back/page/base/article";//资讯管理
    public static final String backArticleAddOrUpdate = backPrefix + "/back/page/base/update";//
    public static final String backAccountLog = backPrefix + "/back/page/base/accountLog";//交易记录
    public static final String backWithdraw = backPrefix + "/back/page/base/withdraw";//申请提现
    public static final String backCharge = backPrefix + "/back/page/base/charge";//充值记录
    public static final String backNotice = backPrefix + "/back/page/base/notice";//消息通知
    public static final String backChat = backPrefix + "/back/page/base/chat";//单人聊天
    public static final String backPicture = backPrefix + "/back/page/base/picture";//图片管理
    public static final String backAlbum= backPrefix + "/back/page/base/album";//相册
    public static final String backSysDict = backPrefix + "/back/page/base/sysDict";//官方账号/费用比例
    public static final String backAnnouncement = backPrefix + "/back/page/base/announcement";//公告
    public static final String backAnnouncementAddOrUpdate = "/back/base/annoUpdate";//
    public static final String backApply = backPrefix + "/back/base/apply";//申请处理
    public static final String backOperationLog = backPrefix + "/back/base/operationLog";//操作记录
    public static final String backLoginLog = backPrefix + "/back/base/loginLog";//
    public static final String backDict = "/back/base/dict";//数据字典
    public static final String backQuickMenu = "/back/base/quickMenu";//快捷菜单
    public static final String backAdmin = "/back/base/admin";//用户
    public static final String backRole = "/back/base/role";//角色
    public static final String backRoleMenu = "/back/base/roleMenu";//用户角色
    public static final String backProvinceCityArea = "/back/base/provinceCityArea";//用户角色
	
	
	
	
	/** 公共页面 开始 */
    public static final String error = "/front/index/error";//错误页面
    public static final String jumpToLogin = "/front/toLogin";//跳转到前台登陆页面
    public static final String jumpToBackLogin = "/back/jumpToBackLogin";//跳转到后台登陆页面
    /** 公共页面 开始 */
    
    /**前台页面 开始*/
    
    public static final String index = "/front/index";//首页
    
    public static final String commonBaseArtilce = "/front/common/baseArticle";//共用 - 基本文章
    

    public static final String frontUser = "/front/base/user/userCenterUser";//用户个人中心
    public static final String aboutUs = "/front/base/user/aboutUs";//关于
    public static final String changPhone = "/front/base/user/changPhone";//修改电话号码
    public static final String myWallet = "/front/base/user/myWallet";//修改电话号码
    public static final String indexAddressSelect = "/front/domain/user/indexAddressSelect";//首页地址选择
    public static final String userCenterShop = "/front/base/user/userCenterShop";//用户中心
    public static final String userCenterUser = "/front/base/user/userCenterUser";//用户中心
    public static final String userMerchantsIndex = "/front/base/user/userMerchantsIndex";//用户首页（供用户访问）
    public static final String userIndex = "/front/base/user/userIndex";//用户首页（供用户访问）
    public static final String personInformation = "/front/base/user/personInformation";//个人信息
    public static final String accountInformation = "/front/base/user/accountInformation";//账户信息
   
    
    
    public static final String propertyManage = "/front/base/user/propertyManage/propertyManage";//财务管理
    public static final String billDetail = "/front/base/user/propertyManage/billDetail";//账单
    public static final String remainderRecharge = "/front/base/user/propertyManage/remainderRecharge";//余额充值
    public static final String withdrawDeposit = "/front/base/user/propertyManage/withdrawDeposit";//提现
    
    
    public static final String addReceiveAddress = "/front/base/user/addReceiveAddress";//添加用户收货地址
    public static final String receiveAddressList = "/front/base/user/addressManage";//用户收货全部地址
    public static final String AddAddress = "/front/base/user/AddAddress";//用户收货全部地址
    public static final String updateUserAddress = "/front/base/user/updateUserAddress";//用户收货全部地址
    
    public static final String realName = "/front/base/user/realName";//用户认证
    
    public static final String paySafety = "/front/base/user/safety/paySafety";//支付安全
    
    public static final String phoneValidate = "/front/base/user/safety/phoneValidate";//手机号验证
    
    
    public static final String realNameAuthentication = "/front/base/user/safety/realNameAuthentication";//实名认证页面
    
    public static final String individualAuthentication = "/front/base/user/safety/individualAuthentication";//个人实名认证导航
    public static final String individualMsgAuthentication = "/front/base/user/safety/individualMsgAuthentication";//个人实名认证导航
    
    public static final String loginPasswordUpdate = "/front/base/user/loginPasswordUpdate";//修改用户密码
    public static final String loginPassRetrieve = "/front/base/user/safety/loginPassRetrieve";//找回用户密码
   
    public static final String payPasswordUpdate = "/front/base/user/payPasswordUpdate";//用户修改支付密码
    public static final String payPasswordRetrieve = "/front/base/user/safety/payPasswordRetrieve";//用户找回支付密码
    public static final String payPasswordSet = "/front/base/user/safety/payPasswordSet";//用户找回支付密码
    public static final String validateSuccess = "/front/base/user/safety/validateSuccess";//用户找回支付密码
    public static final String enterpriseAuthentication = "/front/base/user/safety/enterpriseAuthentication";//企业认证流程
    public static final String enterpriseMsgAuthentication = "/front/base/user/safety/enterpriseMsgAuthentication";//企业认证流程
    
    
    public static final String buyerBid = "/front/base/user/buyerBid";//用户报价
    public static final String bindBankCard = "/front/base/user/bindBankCard";//用户绑定提现银行卡
    public static final String applyWithdrawals = "/front/base/user/applyWithdrawals";//用户申请提现
    public static final String waitPayMoney = "/front/base/user/waitPayMoney";//待付款
    public static final String FundDetails = "/front/base/user/FundDetails";//资金明细
    public static final String shipping = "/front/base/user/shipping";//待发货
    public static final String shipped = "/front/base/user/shipped";//待收货
    public static final String commenting = "/front/base/user/commenting";//待评价
    public static final String comment = "/front/domain/comment/comment";//待评价
    
    public static final String returnGoods = "/front/base/user/returnGoods";//待评价
    public static final String myTrade = "/front/base/user/myTrade";//我的交易
    public static final String credit = "/front/base/user/credit";//个人信誉
    public static final String logisticsResults = "/front/base/user/logisticsResults";//物流结果
    public static final String settleAccounts = "/front/base/user/settleAccounts";//结账页面
    public static final String courierNum = "/front/base/user/courierNum";//添加快递单号
    public static final String editUser = "/front/base/user/editUser";//修改用户信息
    public static final String bindUserPhone = "/front/base/user/bindUserPhone";//绑定用户的手机号码
    
    public static final String recharge = "/front/base/user/propertyManage/recharge";//充值
    public static final String rechargeSuccess = "/front/base/charge/rechargeSuccess";//充值成功
    
    public static final String startAuction = "/front/base/user/aution/startAuction";//开拍提醒
    
    
    public static final String pay = "/front/base/charge/pay";//直接付款
    public static final String payway = "/front/base/charge/payway";//支付方式
    public static final String initSafety = "/front/base/user/initSafety";//
    
    public static final String antiqueCity = "/front/domain/antiqueCity/antiqueCity";//古玩城
    public static final String antiqueCityDetail = "/front/domain/antiqueCity/antiqueCityDetail";//古玩城详细页面
    public static final String antiqueCityShop = "/front/domain/antiqueCity/antiqueCityShop";//古玩城所有店铺
    public static final String addAntiqueCity = "/front/domain/antiqueCity/addAntiqueCity";//添加古玩城
    
    public static final String wholesale = "/front/domain/wholesale/wholesale";//批发城
    //public static final String addWholesale = "/front/wholesale/addWholesale";//批发城添加页面
    public static final String wholesaleDetail = "/front/domain/wholesale/wholesaleDetail";//批发城详细页面
    public static final String applyWholesale = "/front/domain/wholesale/applyWholesale";//批发城申请页面
    public static final String addWholesaleGoods = "/front/domain/wholesale/addWholesaleGoods";//批发城添加商品页面
    public static final String wholesaleGoods = "/front/domain/wholesale/wholesaleGoods";//批发城商品页面
    public static final String wsGoods = "/front/domain/wholesale/goods";//批发城商品页面
    
    public static final String cart = "/front/base/cart/cart";//当前登录人的购物车历史记录
    public static final String myCart = "/front/base/cart/myCart";//已卖商品
    public static final String purchased = "/front/base/cart/purchased";//已买商品
    
    public static final String auction = "/front/domain/auction";
    public static final String auctionInst = "/front/domain/auction/profession/auctionInst";//拍卖机构
    public static final String myAuctionInst = "/front/domain/auction/profession/myAuctionInst";//我的专场
    public static final String auctionInstApplyDesc = "/front/domain/auction/profession/auctionInstApplyDesc";//专场申请说明
    public static final String auctionInstApply = "/front/domain/auction/profession/auctionInstApply";//专场申请
    public static final String instDesc = "/front/domain/auction/profession/instDesc";//拍卖机构简介
    public static final String instDescUpdate = "/front/domain/auction/profession/instDescUpdate";//拍卖机构简介更新
    public static final String instAnno = "/front/domain/auction/profession/instAnno";//拍卖机构公告
    public static final String instAnnoAddOrUpdate = "/front/domain/auction/profession/instAnnoAddOrUpdate";//拍卖机构公告-添加或更新
    
    public static final String auctionDetail = "/front/domain/auction/profession/auctionDetail";
    public static final String professionHall = "/front/domain/auction/profession/professionHall";//专场拍卖大厅
    public static final String professionGoods = "/front/domain/auction/profession/professionGoods";//专场拍卖-藏品展示
    public static final String professionGoodsDetail = "/front/domain/auction/profession/professionGoodsDetail";//专场拍卖-藏品详情展示
    public static final String tradeDesc = "/front/domain/auction/profession/tradeDesc";//买卖交易规则说明
    public static final String auctionProfession = "/front/domain/auction/profession/auctionProfession";//拍卖专场
    public static final String auctionProfessionTypeChoose = "/front/domain/auction/profession/professionTypeChoose";//拍卖专场添加前选择专场类型
    public static final String auctionProfessionAddOrUpdate = "/front/domain/auction/profession/professionAddOrUpdate";//拍卖专场添加
    public static final String professionGoodsAddOrUpdate = "/front/domain/auction/profession/professionGoodsAddOrUpdate";//拍卖专场修改
    public static final String auctionProfessionSearch = "/front/domain/auction/profession/professionSearch";//拍卖专场搜索
    public static final String auctionProfessionManage = "/front/domain/auction/profession/professionManage";//拍卖专场管理
    public static final String professionAddOrUpdate = "/front/domain/auction/profession/professionAddOrUpdate";//新增或修改专场拍卖
    public static final String professionArticleAgreement = "/front/domain/auction/profession/articleAgreement";//专场拍卖条款协议
    public static final String auctionProfessionGoodsDetail = "/front/domain/auction/profession/professionGoodsDetail";//拍卖专场拍品详情
    
    public static final String auctionMicroAddOrUpdate = "/front/domain/auction/micro/auctionMicroAddOrUpdate";//新增或修改微拍
    
    public static final String creditMoney = "/front/domain/fund/creditMoney";//诚信保证金
    
    public static final String myAuctionGoods = "/front/domain/auction/profession/myAuctionGoods";//我拍到的藏品
    
    public static final String professionSendGoods = "/front/domain/auction/profession/professionSendGoods";//拍卖机构收到的送拍藏品
    
    public static final String auctionMicro = "/front/domain/auction/micro/auctionMicro";//微拍
    public static final String asgAddOrUpdate = "/front/domain/auction/micro/asgAddOrUpdate";//微拍-新增
    public static final String auctionMicroManage = "/front/domain/auction/micro/auctionMicroManage";//微拍-我的微拍管理
    
    /*
    public static final String auctionQuickInstApply = "/front/domain/auction/quick/auctionQuickInstApply";//即时拍申请
    public static final String auctionQuickInstApplyDesc = "/front/domain/auction/quick/auctionQuickInstApplyDesc";//即时拍申请说明
    public static final String auctionQuickIndex = "/front/domain/auction/quick/auctionQuickIndex";//即时拍首页
    public static final String auctionQuick = "/front/domain/auction/quick/auctionQuick";//即时拍
    public static final String aqAddOrUpdate = "/front/domain/auction/quick/aqAddOrUpdate";//
    public static final String aqgAddOrUpdate = "/front/domain/auction/quick/aqAddOrUpdate";//即时拍-新增
    public static final String myAuctionQuickInst = "/front/domain/auction/quick/myAuctionQuickInst";//即时拍-我的即时拍机构
    public static final String myAuctionQuick = "/front/domain/auction/quick/myAuctionQuick";//即时拍-我的即时拍
    public static final String aqHall = "/front/domain/auction/quick/aqHall";//即时拍拍卖大厅
    public static final String aqGoods = "/front/domain/auction/quick/quickGoods";//即时拍拍卖-藏品展示
    public static final String aqGoodsDetail = "/front/domain/auction/quick/quickGoodsDetail";//即时拍拍卖-藏品详情展示
    public static final String auctionQuickNotice = "/front/domain/auction/quick/auctionQuickNotice";//即时拍提醒
    */ 
    
    public static final String addGoods = "/front/shop/addGoods";
    public static final String addAnnouncement = "/front/base/sys/addAnnouncement";//添加公告
    public static final String announcement = "/front/base/sys/announcement";//公告
    public static final String announcementInfo = "/front/base/sys/announcementInfo";//公告详情
    public static final String login = "/front/login";//用户登录页面
    public static final String providerLogin = "/front/providerLogin";//技师登录页面
    public static final String shopLogin = "/front/shopLogin";//技师登录页面
    public static final String register = "/front/domain/user/register";//用户注册页面
    public static final String mailCheck = "/front/mailCheck";//激活邮箱验证页面
    public static final String mailReCheck = "/front/mailReCheck";//重新激活邮箱验证页面
    public static final String loginPasswordMailUpdate = "/front/loginPasswordMailUpdate";//找回密码的邮箱
    
    public static final String frontIndex = "/front/index";//首页
    public static final String market = "/front/shop/market";//藏品市场（逛逛，鬼市）页面
    public static final String goods = "/front/shop/goods";//摇一摇页面
    public static final String shop = "/front/shop/shop";//店铺主页面
    public static final String allShop = "/front/domain/shop/allShop";//店铺主页面
    public static final String onSale = "/front/shop/onSale";//店铺在售页面
    public static final String collectGoods = "/front/shop/collectGoods";//店铺喜欢
    public static final String commodityGoodsDetail = "/front/shop/commodityGoodsDetail";//商品详情页面
    public static final String managementGoods = "/front/shop/managementGoods";//商品详情页面
    public static final String gmSaled = "/front/shop/gm/saled";//商品管理已售页面
    public static final String gmUndercarriage = "/front/shop/gm/undercarriage";//商品管理下架页面
    public static final String gmAuction = "/front/shop/gm/auction";//商品管理已送拍页面
    public static final String gmAuctionning = "/front/shop/gm/auctionning";//商品管理拍卖中页面
    public static final String gmLike = "/front/shop/gm/like";//商品管理喜欢页面
    public static final String gmWholesale = "/front/shop/gm/wholesale";//商品管理批发页面
    public static final String gmFrozen = "/front/shop/gm/frozen";//商品管理冻结页面
    public static final String goodsSource = "/front/shop/gm/goodsSource";//商品来源
    public static final String editGoods = "/front/shop/editGoods";//商品管理批发页面
    public static final String creditShop = "/front/shop/creditShop";//店铺信誉
    public static final String editShop = "/front/shop/editShop";//修改店铺信息
    public static final String agentAddProduct = "/front/shop/agentAddProduct";//代发产品申请
    public static final String goodsPictures = "/front/shop/goodsPictures";//产品库图片
    public static final String shopComment = "/front/shop/shopComment";//店铺评论
    public static final String goodsManageType = "/front/shop/goodsManageType";//商品类型管理
    public static final String editWholesale = "/front/shop/editWholesale";//修改批发城
    public static final String addGoodsForPc = "/front/shop/addGoodsForPc";//电脑上传产品
    public static final String releaseGoods = "/front/shop/releaseGoods";//发布
    
    
    public static final String chat = "/front/base/chat/chat";//聊天页面
    public static final String wxMsgCount = "/front/base/chat/wxMsgCount";//群发消息页面
    public static final String message = "/front/base/chat/message";//群发消息页面
    public static final String shake = "/front/domain/shake/shake";//摇一摇页面
    
    public static final String activities = "/front/domain/information/activities";//展会活动
    public static final String activitiesDetail = "/front/domain/information/activitiesDetail";//展会活动详细页面
    public static final String news = "/front/domain/information/news";//新闻资讯
    public static final String newsDetail = "/front/domain/information/newsDetail";//新闻资讯详细页面
    public static final String addActivities = "/front/domain/information/addActivities";//发起活动
    public static final String addNews = "/front/domain/information/addNews";//发起新闻资讯
    
    public static final String goodsList = "/front/domain/goods/goodsList";//产品库
    public static final String order = "/front/domain/orderInfo/orderInfo";//订单
    public static final String orderInfo = "/front/domain/orderInfo/orderInfoxx";//订单详情页面
    public static final String goodsAdd = "/front/domain/goods/goodsAdd";//添加产品
    public static final String goodsBaseUpdate = "/front/domain/goods/goodsBaseUpdate";//修改产品的基本信息：标题，描述，类型
    public static final String goodsUpdate = "/front/domain/goods/goodsUpdate";//修改产品信息
    public static final String orderInfoRefund = "/front/domain/orderInfo/orderRefund";//订单详情页面
    
    
    public static final String fans = "/front/domain/fans/fans";//师友页面
    public static final String fansRanking = "/front/domain/fans/fansRanking";//粉丝排名
    
    
    public static final String courseIndex = "front/domain/course/courseList";//培训首页
    public static final String coupon = "front/domain/coupon/coupon";//优惠券
    public static final String providerIndex = "front/domain/provider/providerList";//技师首页
    public static final String addProvider = "front/domain/provider/addProvider";//技师申请
    public static final String forumIndexs = "/front/base/forum/forumIndexs";//论坛首页
    public static final String peopleIndex = "front/domain/people/peopleList";//月嫂首页
    
    public static final String forumApply = "/front/base/forum/forumApply";//论坛首页
    
    public static final String courseInfo = "/front/domain/course/courseInfo";//培训详情页面
    public static final String courseApply = "/front/domain/course/courseApply";//培训申请页面
    
    public static final String ShopApply = "/front/domain/shop/shopApply";//加盟商申请页面
    public static final String shopCenters = "/front/domain/shop/shopCenters";//加盟商申请页面
    
    
    public static final String businessMiddle = "/shop/businessMiddle";//加盟商个人中心
    public static final String setBusiness = "/shop/setBusiness";//加盟商个人中心修改密码
    
    public static final String peopleInfo = "/front/domain/people/peopleInfo";//培训详情页面
    public static final String peopleApply = "/front/domain/people/peopleApply";//培训申请页面
    
    public static final String goodsInfo = "/front/domain/goods/goodsInfo";//服务详情页面
    public static final String goodsBespeak = "/front/domain/goods/goodsBespeak";//服务详情页面
    public static final String choiceProvider = "/front/domain/goods/choiceProvider";//服务详情页面
    public static final String providerInfo = "/front/domain/provider/providerInfo";//技师详情页面
    public static final String bespeak = "/front/domain/provider/bespeak";//预约下单页面
    public static final String myCommunity = "/front/base/forum/myCommunity";//我的社区
    public static final String hotForumSquare = "/front/base/forum/hotForumSquare";//论坛圈子页面
    public static final String myForumSquare = "/front/base/forum/myForumSquare";//
    
    public static final String frontForum = "/front/base/forum/forum";//论坛页面.
    public static final String forumArticle = "/front/base/forum/forumArticle";//论坛文章详细页面
    public static final String forumUserArticle = "/front/base/forum/forumUserArticle";//论坛用户首页文章
    public static final String forumUserReply = "/front/base/forum/forumUserReply";
    public static final String forumDict = "/front/base/forum/forumDict";//论坛分类页面
    public static final String myForum = "/front/base/forum/myForum";//论坛分类页面

    
    public static final String forumRuleDetail = "/front/base/forum/forumRuleDetail";//论坛规则页面
    public static final String forumRule = "/front/base/forum/forumRule";//论坛规则说明页面
    public static final String forumRuleAndAnnouncementList = "/front/base/forum/forumRuleAndAnnouncementList";//论坛规则和公告页面
    public static final String forumArticleAdd = "/front/base/forum/forumArticleAdd";//添加帖子
    public static final String forumArticleUpdate = "/front/base/forum/forumArticleUpdate";//更新帖子
    public static final String forumArticleGoodsAddOrUpdate = "/front/base/forum/forumArticleGoodsAddOrUpdate";//更新帖子
    public static final String forumArticleAddOrUpdate = "/front/base/forum/forumArticleAddOrUpdate";//添加或更新帖子
    public static final String forumArticledetail = "/front/base/forum/forumDetail";//帖子详情页面
    public static final String myForumArticleCollect = "/front/base/forum/myForumArticleCollect";//我的收藏
    public static final String addForum = "/front/base/forum/addForum";//我的论坛申请
    public static final String chooseOrderGoods = "/front/base/forum/chooseOrderGoods";//我的论坛申请
    
    
    
    public static final String distributionIndex = "/front/distribution/distributionIndex";//分销首页
    public static final String distributionDesc = "/front/distribution/distributionDesc";//分销介绍页面
    public static final String myIncome = "/front/distribution/myIncome";//分销-我的收入列表
    public static final String myCustomer = "/front/distribution/myCustomer";//分销-我的客户列表
    public static final String distributionGoods = "/front/distribution/distributionGoods";//分销-藏品
    
    public static final String bonusIndex = "/front/domain/bonus/bonusIndex";//我的红包首页
    public static final String randomBonus = "/front/domain/bonus/randomBonus";//拼手气红包
    public static final String commonBonus = "/front/domain/bonus/commonBonus";//普通红包
    public static final String bonusReceived = "/front/domain/bonus/bonusReceived";//收到的红包
    public static final String bonusSend = "/front/domain/bonus/bonusSend";//发出的红包
    public static final String bonusDetail = "/front/domain/bonus/bonusDetail";//红包-详情
    /*
    public static final String bonusReceivedDetail = "/front/bonus/bonusReceivedDetail";//发出的红包-详情
    public static final String bonusSendDetail = "/front/bonus/bonusSendDetail";//发出的红包-详情
    */    
    /**前台页面 结束*/
    
    /** 后台页面 开始 */
    public static final String back_chatProfession = "/back/domain/chat/chatProfession";//专场聊天
    public static final String back_chatQuick = "/back/domain/chat/chatQuick";//即时拍聊天
    
    public static final String backAntiqueCity = "/back/domain/antiqueCity/antiqueCity";//古玩城申请
    
    public static final String backWholesale = "/back/domain/wholesale/wholesale";//批发城
    public static final String backWholesaleAddOrUpdate = "/back/domain/wholesale/update";//批发城
    
    public static final String backUserCustomer = "/back/domain/user/userCustomer";//客户关联
    public static final String backPromote = "/back/domain/goods/promote";//客户关联
    
    public static final String backForum = "/back/domain/forum/forum";//论坛管理
    public static final String backForumAddOrUpdate = "/back/domain/forum/update";//
    public static final String backForumMember = "/back/domain/forumMember/forumMember";//论坛用户
    public static final String backForumArticle = "/back/domain/forumArticle/forumArticle";//帖子管理
    public static final String backForumArticleAddOrUpdate = "/back/domain/forumArticle/update";//
    
    public static final String backAuctionInst = "/back/domain/auction/auctionInst";//拍卖机构
    public static final String backAuctionProfession = "/back/domain/auction/auctionProfession";//拍卖专场
    public static final String backAuctionQuick = "/back/domain/auction/auctionQuick";//即时拍
    public static final String backAuctionMicro = "/back/domain/auction/auctionMicro";//微拍
    public static final String backAuctionMicroOffers = "/back/domain/auction/auctionMicroOffers";//微拍出价记录
    public static final String backAuctionGoods = "/back/domain/auction/auctionGoods";//拍卖专场商品
    public static final String backAuctionMicroGoods = "/back/domain/auction/auctionMicroGoods";//微拍专场商品
    public static final String backAuctionQuickGoods = "/back/domain/auction/auctionQuickGoods";//即时拍专场商品
    public static final String backAuctionQuickInst = "/back/domain/auction/auctionQuickInst";//即时拍机构
	
    public static final String backShop = "/back/domain/shop/shop";// 加盟商管理
    public static final String backShopAddOrUpdate = "/back/domain/shop/update";//
    public static final String backAgentProduct = "/back/domain/shop/agentProduct";//
    
    public static final String backpeople = "/back/domain/people/people";//月嫂管理
    public static final String backGoods = "/back/domain/goods/goods";//藏品管理
    public static final String backGoodsAddOrUpdate = "/back/domain/goods/update";//
    public static final String backGoodsPictureLib = "/back/domain/goods/goodsPictureLib";//产品库图片
    public static final String backGoodsPicture = "/back/domain/goods/goodsPicture";//产品库添加页面
    
    /** 后台页面 结束 */
	
	
	
    
	
    
}
