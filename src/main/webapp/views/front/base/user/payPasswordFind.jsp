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
</head>
<body style="background-color:#f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="pz_main" style="margin-top:50px;">
	 		<ul class="information">
				<li>
					<input type="text" style="width:90%;font-size: 16px;padding: 10px" id="phone" value="${user.phone}" placeholder="请输入要发送验证码的手机号码" />
				</li>
				<li style="background-color:#f5f5f5;border-bottom:none;">
					<div class="seMsg" id="seMsg">
						<span class="pointer" style="padding: 10px" id="btnMssage" onclick="sendVerify();return false;">点此发送短信验证码</span>
					</div>
					<!-- <div class="reseMsg" id="reseMsg">
						<span class="pointer" onclick="sendVerify();return false;">点此重新发送短信验证码</span>
					</div> -->
				</li>
				<li>
					<input type="text" style="width:90%;font-size: 16px;padding: 10px;" id="verifyCodeNum" placeholder="请把手机接收到的验证码填到此处"/>
				</li>
				<li style="background-color:#f5f5f5;border-bottom:none;display:none;" id="passTip">
					<div style="text-align:center;">
						<span style="color:red;">密码的长度至少4位数</span>
					</div>
				</li>
				<li id="newPass" style="display:none;">
					<input type="password" style="width:90%;font-size: 16px;padding: 10px;" id="newPassWord" placeholder="请输入新的密码,密码的长度至少4位数"/>
				</li>
			</ul>
			<div class="save bottomFix">
				<ul>
					<li id="next"><a href="javascript:;" class="a_say" onclick="nextStep();return false;">下一步</a></li>
					<li id="save" style="display:none;"><a href="javascript:;" class="a_say" onclick="savePassword();return false;">完成</a></li>
				</ul>
			</div>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script src="/js/front/user/payPasswordFind.js" title="v"></script>
</body>
</html>
