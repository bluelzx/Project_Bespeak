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
<body style="background-color:white;">
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	 	<div class="pz_main" style="margin-top:50px;">
	 		<div id="loadingToast" class="weui_loading_toast">
		        <div class="weui_mask_transparent"></div>
		        <div class="weui_toast">
		            <div class="weui_loading">
		                <div class="weui_loading_leaf weui_loading_leaf_0"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_1"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_2"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_3"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_4"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_5"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_6"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_7"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_8"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_9"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_10"></div>
		                <div class="weui_loading_leaf weui_loading_leaf_11"></div>
		            </div>
		            <p class="weui_toast_content">数据加载中</p>
		        </div>
		    </div>
	
	    </div>
    
    <input type="hidden" value="${noPhone}" id="noPhone">
	<input type="hidden" value="${noPayPassword}" id="noPayPassword">
	<input type="hidden" id="openId" value="${openId}">
	<input id="appId" type="hidden" value="${appId}" />
	<input id="timeStamp2" type="hidden" value="${timeStamp}" />
    <input id="nonceStr2" type="hidden" value="${nonceStr}" />
    <input id="signature" type="hidden" value="${signature}" />
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%-- 
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%> 猴子导航 
	<%@ include file="/views/front/common/z_div_type_slide.htm"%>右侧分类查询
	<%@ include file="/views/front/common/z_div_qrcode.htm"%>二维码弹出框 
	--%>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script src="/js/front/charge/pay.js" title="v"></script>
</body>
</html>
