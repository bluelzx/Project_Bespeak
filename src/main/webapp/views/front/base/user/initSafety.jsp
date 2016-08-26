<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/auction.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/userCenter.css" title="v" />
<link rel="stylesheet" type="text/css" href="/third-party/weui/weui.min.css"/>
</head>
<body style="background-color:#f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="pz_main" style="margin-top:50px;">
	 		<input type="hidden" id="userId" value="${userId}">
		 		<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">手机号码：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="phone"  placeholder="请输入需要绑定的手机号码">
		                </div>
		            </div>
				</div>
		 		<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
		                <div class="weui_cell_bd weui_cell_primary">
		                   <div class="seMsg" id="seMsg">
								<span class="pointer" style="padding: 10px 15px;" id="btnMssage" onclick="sendVerify();return false;">点此发送短信验证码</span>
							</div>
							<div class="reseMsg" id="reseMsg">
								<span class="pointer" onclick="sendVerify();return false;">点此重新发送短信验证码</span>
							</div>
		                </div>
		            </div>
				</div>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">验证码：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                   <input type="text" class="weui_input" id="verifyCodeNum" placeholder="请把手机接收到的验证码填到此处"/>
		                </div>
		            </div>
		            <div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">登陆密码：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                   <input type="text" class="weui_input" id="loginPass" placeholder="请设置您的登陆密码"/>
		                </div>
		            </div>
		            <div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">支付密码：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                   <input type="text" class="weui_input" id="payPass" placeholder="请设置您的支付密码"/>
		                </div>
		            </div>
				</div>
			<div class="pz_down">
				<div class="c_0100_9"></div>
			</div>
			<div class="save bottomFix" >
				<ul>
					<li id="save"><a href="javascript:;" class="a_say" onclick="saveData();return false;">保存</a></li>
				</ul>
			</div>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script src="/js/front/user/initSafety.js" title="v"></script>
</body>
</html>
