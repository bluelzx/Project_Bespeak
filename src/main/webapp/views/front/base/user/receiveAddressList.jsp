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
	 		<input type="hidden" id="userId" value="${userId}">
	 		<div class="addressUl c_0100_3" >
		 		<ul id="receiveAddressList">
					
				</ul>
			</div>
			<div id="resultTip" class="resultTip frontHide"></div>
			<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
			<div class="save bottomFix" >
				<ul>
					<li><a href="javascript:;" class="a_say" onclick="lh.jumpR('/addReceiveAddress');">新增收货地址</a></li>
				</ul>
			</div>
	</div>
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script src="/js/front/user/receiveAddressList.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<li style="width:100%;padding-left:5px;font-size:16px;">收货人：{{receiverName}}</li>
			<li style="width:100%;padding-left:5px;font-size:16px;">地址：{{provinceName}}省&nbsp;{{cityName}}市</li>
			<li style="width:100%;padding-left:5px;font-size:16px;">详细地址：{{addressDetail}}</li>
			<li style="width:100%;padding-left:5px;font-size:16px;">联系电话：{{phone}}</li>
			<div class="pointer fr" style="margin: 5px;">
				<nobr>
					<button type="button" onclick="lh.jumpR('/addReceiveAddress?id={{id}}')" class="weui_btn weui_btn_primary" style="position: relative; z-index: 1;font-size:16px;">修改</button>
				<nobr>
			</div>
			<div class="pointer fl" style="margin: 5px;">
				<nobr>
					<button type="button" onclick="deleteUserAddress({{id}});return false;" class="weui_btn weui_btn_warn" style="position: relative; z-index: 1;font-size:16px;">删除</button>
				</nobr>
			</div>
		{{/rows}}		 
	</script>
</body>
</html>
