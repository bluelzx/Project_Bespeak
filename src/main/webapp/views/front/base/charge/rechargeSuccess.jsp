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
 		<ul class="information" style="margin-top:10px;">
			<li>
				<div id="status" style="text-align: center;padding: 20px 5px 20px 5px;font-size: 30px;color:green;">充值成功</div>
			</li>
			<li id="moneyLi" style="display: none;">
				<div style="padding: 5px 5px 5px 25px;font-size: 14px;">本次充值金额：<span id="money" style="color: red;"></span> 元</div>
			</li>
			<li>
				<div id="msg" style="padding: 30px 5px 30px 25px;font-size: 14px;"></div>
			</li>
			<li id="sysTip" style="text-align: center;color:gray;padding:20px 5px;"></li>
		</ul>
		<div class="save bottomFix" >
			<ul>
				<li><a href="javascript:lh.jumpR('/user');" class="a_say">返回个人中心</a></li>
			</ul>
		</div>
	</div>
	
	<input type="hidden" id="rspJson" value="${rspJson}">
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>

	<script src="/js/front/charge/rechargeSuccess.js" title="v"></script>
	
</body>
</html>
