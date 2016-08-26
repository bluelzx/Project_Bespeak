package com.lhfeiyu.config.base;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 基础库-配置包：提示信息配置 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong>  <p>
 */
public class BaseTip {
	
	 public static final String commonLoginLogDescription = "正常登陆";//登陆日志描述：正常登陆
	 
	 
	 /**================================== 提示消息 开始 ==================================*/
	 
	 //典型示例：return Result.failure(json, BaseTip.msg_username_null, BaseTip.code_username_null);
	 
	 //msg
	 
	 public static final String msg_address_null = "收货地址不能为空";
	 public static final String msg_address_order_null = "订单不存在，无法设置收货地址";
	 public static final String msg_address_orderGoods_null = "订单商品不存在，无法设置收货地址";
	 public static final String msg_address_orderUser_null = "订单客户不存在，无法设置收货地址";
	 public static final String msg_address_order_auth_lack = "该订单不是您创建的，无法设置收货地址";
	 public static final String msg_address_shipping_status_not_todo = "该订单已经不是未发货状态，无法修改收货地址";
	 public static final String msg_address_order_goods_null = "该商品已经不存在，无法设置收货地址";
	 
	 public static final String msg_admin_null = "用户不存在";
	 public static final String msg_admin_session_invalid = "会话过期，请重新登陆";
	 public static final String msg_apply_user_null = "申请人不能为空";
	 
	 public static final String msg_auth_lack = "权限不足";
	 
	 public static final String msg_avaliableMoney_null = "可用金额不能为空";
	 public static final String msg_avaliableMoney_invalid = "可用金额不符合规范";
	 public static final String msg_avaliableMoney_lack = "账户可用余额不足，无法完成操作";
	 
	 public static final String msg_bindPhone = "您尚未绑定手机号码，请先绑定手机号码";
	 public static final String msg_setPayPassword = "您尚未设置支付密码，请及时设置您的支付密码";
	 public static final String msg_setPassword = "您的登陆密码为默认密码，请及时更新";
	 
	 public static final String msg_chat_self = "不能和自己聊天";
	 
	 public static final String msg_title_null = "标题不能为空";
	 public static final String msg_introduction_null = "简介不能为空";
	 public static final String msg_content_null = "内容不能为空";
	 public static final String msg_comment_obj_null = "评论对象不能为空";
	 
	 public static final String msg_creditMoney_lack = "您目前交纳的诚信保证金为：@money@元，是否增加交纳诚信保证金？";
	 public static final String msg_creditMoney_null = "您尚未交纳诚信保证金，是否交纳诚信保证金？";
	 
	 public static final String msg_receiver_null = "接收人不能为空";
	 public static final String msg_sender_null = "发送人不能为空";
	 
	 public static final String msg_email_repeat = "电子邮箱已存在，请重新输入或将此栏置空";
	 
	 public static final String msg_exist_yes = "已存在相同数据";
	 public static final String msg_exist_no = "不存在相同数据";
	 
	 public static final String msg_frozenMoney_null = "冻结金额不能为空";
	 public static final String msg_frozenMoney_invalid = "冻结金额不符合规范";
	 public static final String msg_frozenMoney_lack = "账户冻结资金不足，无法完成操作";
	 
	 public static final String msg_goods_null = "商品不能为空";
	 public static final String msg_remainNumber_lack = "商品库存不足";
	 public static final String msg_goods_description_null = "暂无商品描述";
	 
	 public static final String msg_money_null = "金额不能为空";
	 public static final String msg_money_invalid = "金额不正确";
	 
	 public static final String msg_old_password_wrong = "旧密码不正确";
	 public static final String msg_operation_success = "操作成功";
	 public static final String msg_operation_failure = "操作失败";
	 public static final String msg_operation_undefined = "无法识别的操作类型";
	 
	 public static final String msg_password_length_lack = "密码长度不能低于为"+BaseConst.password_min_length+"位";
	 public static final String msg_password_special_char = "密码不能包含特殊字符";
	 
	 public static final String msg_pay_password_null = "您目前尚未设置支付密码，为了您的账户安全，请先设置支付密码";
	 public static final String msg_pay_password_ipt_null = "支付密码不能为空";
	 public static final String msg_pay_password_wrong = "支付密码不正确";
	 
	 public static final String msg_pay_null = "收货地址不能为空";
	 public static final String msg_pay_order_null = "订单不存在，无法完成付款";
	 public static final String msg_pay_orderGoods_null = "订单商品不存在，无法完成付款";
	 public static final String msg_pay_orderGoods_price_invalid = "订单商品的价格无效，无法完成付款";
	 public static final String msg_pay_orderUser_null = "订单客户不存在，无法完成付款";
	 public static final String msg_pay_order_auth_lack = "该订单不是您创建的，无法完成付款";
	 public static final String msg_pay_repeat = "该订单已经完成付款，不能重复付款";
	 public static final String msg_pay_order_goods_null = "该商品已经不存在，无法完成付款";
	 public static final String msg_pay_payType_null = "支付类型为空，无法完成付款";
	 public static final String msg_pay_shop_null = "店铺不存在，无法完成付款";
	 
	 public static final String msg_price_wrong = "金额不正确";
	 public static final String msg_province_city_null = "省市不能为空";
	 public static final String msg_addressDetail_null = "详细地址不能为空";
	 
	 public static final String msg_param_lack = "参数不足，无法进行操作";
	 public static final String msg_param_null = "参数为空，无法进行操作";
	 
	 public static final String msg_phone_repeat = "电话号码已存在，请重新输入或将此栏置空";
	 
	 public static final String msg_server_error = "服务器出现异常，请联系管理员";
	 public static final String msg_session_invalid = "您长时间未操作，为了账户安全请重新登陆";
	 public static final String msg_session_invalid_redirect = "会话过期，重定向到登陆页面";
	 
	 public static final String msg_shop_null = "店铺不存在，无法进行操作";
	 
	 public static final String msg_special_char = "提交的数据中不能包含特殊字符";
	 
	 public static final String msg_loginName_null = "登录名不能为空";
	 
	 public static final String msg_goods_status_wrong = "商品状态异常";
	 
	 public static final String msg_tradeMoney_null = "交易金额不能为空";
	 public static final String msg_tradeMoney_invalid = "交易金额不符合规范";
	 
	 public static final String msg_update_id_null = "编号为空，无法执行修改操作";
	 public static final String msg_update_obj_null = "该条数据不存在，无法执行修改操作";
	 public static final String msg_upload_failure = "文件上传失败";
	 
	 public static final String msg_userFund_null = "用户资金帐户不存在";
	 public static final String msg_userId_null = "用户编号不能为空";
	 public static final String msg_username_null = "用户名称不能为空";
	 public static final String msg_username_repeat = "用户名已存在，请重新输入";
	 public static final String msg_username_or_password_wrong = "用户名或密码输入不正确，请重新输入";
	 public static final String msg_phone_wrong = "用户手机号码输入不正确，请重新输入";
	 
	 public static final String msg_verifyCode_phone_send = "验证码已发送到您的手机，请及时查看";
	 public static final String msg_verifyCode_phone_max_num = "请求验证码的次数太多，请直接联系客服进行操作";
	 
	 public static final String msg_wx_notice_templateId_null = "微信通知消息模板不能为空";
	 public static final String msg_wx_notice_message_null = "微信通知消息内容不能为空";
	 
	 public static final String msg_wx_pay_auth_failure = "微信支付组件验证失败";
	 
	 //code
	 
	 public static final String code_address_null = "address_null";
	 public static final String code_address_order_null = "address_order_null";
	 public static final String code_address_orderGoods_null = "address_orderGoods_null";
	 public static final String code_address_orderUser_null = "address_orderUser_null";
	 public static final String code_address_order_auth_lack = "address_order_auth_lack";
	 public static final String code_address_shipping_status_not_todo = "address_shipping_status_not_todo";
	 public static final String code_address_order_goods_null = "address_order_goods_null";
	 
	 public static final String code_admin_null = "admin_null";
	 public static final String code_admin_session_invalid = "admin_session_invalid";
	 public static final String code_apply_user_null = "apply_user_null";
	 
	 public static final String code_avaliableMoney_null = "avaliableMoney_null";
	 public static final String code_avaliableMoney_invalid = "avaliableMoney_invalid";
	 public static final String code_avaliableMoney_lack = "avaliableMoney_lack";
	 
	 public static final String code_auth_lack = "auth_lack";
	 
	 public static final String code_bindPhone = "bindPhone";
	 public static final String code_setPayPassword = "setPayPassword";
	 public static final String code_setPassword = "setPassword";
	 
	 public static final String code_chat_self = "chat_self";
	 
	 public static final String code_title_null = "title_null";
	 public static final String code_content_null = "content_null";
	 public static final String code_comment_obj_null = "comment_obj_null";
	 
	 public static final String code_creditMoney_lack = "creditMoney_lack";
	 public static final String code_creditMoney_null = "creditMoney_null";
	 
	 public static final String code_receiver_null = "receiver_null";
	 public static final String code_sender_null = "sender_null";
	 
	 public static final String code_email_repeat = "email_repeat";
	 public static final String code_exist_yes = "exist_yes";
	 public static final String code_exist_no = "exist_no";
	 
	 public static final String code_frozenMoney_null = "frozenMoney_null";
	 public static final String code_frozenMoney_invalid = "frozenMoney_invalid";
	 public static final String code_frozenMoney_lack = "frozenMoney_lack";
	 
	 public static final String code_goods_null = "goods_null";
	 public static final String code_goods_status_wrong = "goods_status_wrong";
	 public static final String code_remainNumber_lack = "remainNumber_lack";
	 public static final String code_goods_description_null = "goods_description_null";
	 
	 public static final String code_money_null = "money_null";
	 public static final String code_money_invalid = "money_invalid";
	 
	 public static final String code_old_password_wrong = "old_password_wrong";
	 public static final String code_operation_success = "operation_success";
	 public static final String code_operation_failure = "operation_failure";
	 public static final String code_operation_undefined = "operation_undefined";
	 
	 public static final String code_password_length_lack = "password_length_lack";
	 public static final String code_password_special_char = "password_special_char";
	 public static final String code_pay_password_null = "pay_password_null";
	 public static final String code_pay_password_ipt_null = "pay_password_ipt_null";
	 public static final String code_pay_password_wrong = "pay_password_wrong";
	 public static final String code_phone_repeat = "phone_repeat";
	 
	 public static final String code_pay_address_null = "address_null";
	 public static final String code_pay_order_null = "pay_order_null";
	 public static final String code_pay_orderGoods_null = "pay_orderGoods_null";
	 public static final String code_pay_orderGoods_price_invalid = "pay_orderGoods_price_invalid";
	 public static final String code_pay_orderUser_null = "pay_orderUser_null";
	 public static final String code_pay_order_auth_lack = "pay_order_auth_lack";
	 public static final String code_pay_repeat = "pay_repeat";
	 public static final String code_pay_order_goods_null = "pay_order_goods_null";
	 public static final String code_pay_payType_null = "pay_payType_null";
	 public static final String code_pay_shop_null = "pay_shop_null";
	 
	 public static final String code_price_wrong = "price_wrong";
	 
	 public static final String code_province_city_null = "province_city_null";
	 public static final String code_addressDetail_null = "addressDetail_null";
	 
	 public static final String code_param_lack = "param_lack";
	 public static final String code_param_null = "param_null";
	 
	 public static final String code_server_error = "server_error";
	 public static final String code_session_invalid = "session_invalid";
	 public static final String code_session_invalid_redirect = "session_invalid_redirect";
	 
	 public static final String code_shop_null = "shop_null";
	 
	 public static final String code_special_char = "special_char";
	 
	 public static final String code_tradeMoney_null = "tradeMoney_null";
	 public static final String code_tradeMoney_invalid = "tradeMoney_invalid";
	 
	 public static final String code_update_id_null = "id_null";
	 public static final String code_update_obj_null = "obj_null";
	 public static final String code_upload_failure = "upload_failure";
	 
	 public static final String code_userFund_null = "userFund_null";
	 public static final String code_userId_null = "userId_null";
	 public static final String code_username_null = "username_null";
	 public static final String code_username_repeat = "username_repeat";
	 public static final String code_username_or_password_wrong = "username_or_password_error";
	 public static final String code_phone_or_password_wrong = "code_phone_or_password_wrong";
	 
	 public static final String code_verifyCode_phone_send = "verifyCode_phone_send";
	 public static final String code_verifyCode_phone_max_num = "verifyCode_phone_max_num";
	 
	 public static final String code_wx_notice_templateId_null = "wx_notice_templateId_null";
	 public static final String code_wx_notice_message_null = "wx_notice_message_null";
	 
	 public static final String code_wx_pay_auth_failure = "wx_pay_auth_failure";
	 
	 public static final String code_login_doLogin = "doLogin";
	 public static final String code_login_wxRedirect = "wxRedirect";
	 public static final String code_login_already = "already";
	 public static final String code_login_jumpToFocus = "jumpToFocus";
	 public static final String code_login_bindPhoneSetPassword = "bindPhoneSetPassword";
	 public static final String code_login_notForce = "notForce";
	 
	 //key
	 
	 public static final String key_code = "code";
	 
	 public static final String key_failure = "failure";
	 
	 public static final String key_grid_rows = "rows";
	 public static final String key_grid_total = "total";
	 
	 public static final String key_jumpUrl = "jumpUrl";
	 
	 public static final String key_msg = "msg";
	 
	 public static final String key_session_flag = "session_flag";
	 public static final String key_status = "status";
	 public static final String key_success = "success";
	 
	 public static final String key_toLogin = "toLogin";
	 
	 public static final String key_loginStatus = "loginStatus";
	 
	 //value
	 
	 public static final String value_failure =  "failure";
	 
	 public static final String value_session_user = "user";
	 public static final String value_status_failure = "failure";
	 public static final String value_status_server_error = "status_server_error";
	 public static final String value_status_success = "success";
	 public static final String value_success = "success";
	 
	 public static final String value_toLogin = "1";
	 
	 public static final String value_sys_createdBy = "-SYS-";
	 public static final String value_sys_updatedBy = "-SYS-";
	 public static final String value_sys_deletedBy = "-SYS-";
	 
	//template
	 
	 public static final String tp_title_pay_goods_success = "恭喜您，商品【-GOODS_NAME-】付款成功，金额：（-MONEY-元）";
	 public static final String tp_title_pay_order_success = "恭喜您，订单【-ORDER_SERIAL-】付款成功，金额：（-MONEY-元）";
	 public static final String tp_content_pay_order_success = "收货人:【-RECEIVER_NAME-】手机号码:【-PHONE-】地址：【-ADDRESS-】详细地址:【-DETAIL_ADDRESS-】";
	 
	 public static final String tp_comment_goods_success = "-USERNAME-评论了您的商品【-GOODS_NAME-】";
	 
	 /**================================== 提示消息 结束  ==================================*/
}
