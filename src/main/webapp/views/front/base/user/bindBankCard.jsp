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
	 		<input type="hidden" id="isRealAuth" value="${user.isRealAuth}">
		 		<div class="weui_cells weui_cells_form">
		 		<%-- <ul class="information">
					<li>
						<span style="font-size: 16px;">银行卡号1:</span>
						<input type="text" style="font-size: 16px;" id="bankCard1" value="${userFund.bankCard1}" placeholder="请输入需要绑定的银行卡号码"/>
					</li>
					<li>
						<span style="font-size: 16px;">银行卡号2:</span>
						<input type="text" style="font-size: 16px;" id="bankCard2" value="${userFund.bankCard2}" placeholder="请输入需要绑定的银行卡号码"/>
					</li>
					<li>
						<span style="font-size: 16px;">银行卡号3:</span>
						<input type="text" style="font-size: 16px;" id="bankCard3"  value="${userFund.bankCard3}" placeholder="请输入需要绑定的银行卡号码"/>
					</li>
					<li>
						<span style="font-size: 16px;">银行卡号4:</span>
						<input type="text" style="font-size: 16px;" id="bankCard4"  value="${userFund.bankCard4}" placeholder="请输入需要绑定的银行卡号码"/>
					</li>
					<li>
						<span style="font-size: 16px;">银行卡号5:</span>
						<input type="text" style="font-size: 16px;" id="bankCard5"  value="${userFund.bankCard5}" placeholder="请输入需要绑定的银行卡号码"/>
					</li>
				</ul> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">银行卡号1:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="bankCard1" value="${userFund.bankCard1}" placeholder="请输入需要绑定的银行卡号码">
		                </div>
		            </div>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">银行卡号2:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="bankCard2" value="${userFund.bankCard2}" placeholder="请输入需要绑定的银行卡号码">
		                </div>
		            </div>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">银行卡号3:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="bankCard3" value="${userFund.bankCard3}" placeholder="请输入需要绑定的银行卡号码">
		                </div>
		            </div>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">银行卡号4:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="bankCard4" value="${userFund.bankCard4}" placeholder="请输入需要绑定的银行卡号码">
		                </div>
		            </div>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">银行卡号5:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="bankCard5" value="${userFund.bankCard5}" placeholder="请输入需要绑定的银行卡号码">
		                </div>
		            </div>
				</div>
			<div class="pz_down">
				<div class="c_0100_9"></div>
			</div>
			<div class="save bottomFix" >
				<ul>
					<li><a href="javascript:;" class="a_say" onclick="addBindBankCard();return false;">保存</a></li>
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
	<script src="/js/front/user/bindBankCard.js" title="v"></script>
</body>
</html>
