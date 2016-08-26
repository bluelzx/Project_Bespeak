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
<link rel="stylesheet" type="text/css" href="/css/front/call.css" title="v" />
</head>
<body style="background-color:#f5f5f5;">
<%-- <%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 --> --%>
	<input type="hidden" value="${noPhone}" id="noPhone">
	<input type="hidden" value="${noPayPassword}" id="noPayPassword">
 	<div class="pz_main" style="margin-top:50px;">
 		<ul class="information" style="margin-top:10px;">
			<li>
				<span style="font-size:14px;">金额:</span>
				<input type="text" id="applyMoney" readonly="readonly" onclick="inputChargeMoney();" placeholder="请输入需要充值的金额" onkeyup="checkMoney(1);return false;" onblur="checkMoney(2);return false;"/>
			</li>
			<li class="enveluli enveluli1" style="background-color:#F5F5F5;text-align: right;margin-top: 12px;color: #A0A0A0;font-size: 12px;margin-bottom: 15px;text-indent: 0.2em">
				<div>充值金额：<span id="payTotal" style="color: red;font-size: 16px;">0</span>&nbsp;&nbsp;&nbsp;元&nbsp;</div>
			</li>
			<!-- 
			<li>
				<span style="font-size:14px;">支付密码:</span>
				<input type="password" id="payPassword" />
			</li> -->
		</ul>
		<div class="save bottomFix" >
			<ul>
				<li><a href="javascript:;" class="a_say" onclick="doPay();return false;">确认充值</a></li>
			</ul>
		</div>
	</div>
	<div id="chargeToast" class="weui_loading_toast" style="display: none;">
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
            <p class="weui_toast_content">正在提交数据....</p>
        </div>
    </div>
    
    <%@ include file="/views/front/common/z_div_call.htm"%><!-- 出价面板 -->
    
	<input type="hidden" id="openId" value="${openId}">
	<input id="appId" type="hidden" value="${appId}" />
	<input id="timeStamp2" type="hidden" value="${timeStamp}" />
    <input id="nonceStr2" type="hidden" value="${nonceStr}" />
    <input id="signature" type="hidden" value="${signature}" />
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="/js/common/call.js" title="v"></script>
	<script src="/js/front/charge/recharge.js" title="v"></script>
</body>
</html>
