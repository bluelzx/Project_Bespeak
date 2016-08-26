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
<link rel="stylesheet" type="text/css" href="/css/front/dust.css" title="v" />
<link rel="stylesheet" type="text/css" href="/third-party/weui/weui.min.css"/>
</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<input type="hidden" id="shopId" value="${shopId}">
	<div class="c_0100_17" style="margin-top:50px;">
			<div class="slide_shop" id="slide_shop">
				<div class="extension">
					<%-- <div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
						<a href="/gm/2" class="weui_btn weui_btn_primary pointer" id="showToast">微拍</a>
						<!-- <span id="goodsTypes" onclick="location.href=''" style="width:100%;border:1px solid #DA1919">微拍</span> --><!-- width:48% -->
					</div>
					<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
						<span id="goodsTypes" onclick="location.href='/gm/2'" style="width:100%;border:1px solid #DA1919">即时拍</span><!-- width:48% -->
						<a href="/gm/3" class="weui_btn weui_btn_primary pointer" id="showToast">即时拍</a>
					</div>
					<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
						<span id="goodsTypes" onclick="location.href='/gm/3'" style="width:100%;border:1px solid #DA1919">专场</span><!-- width:48% -->
						<a href="/gm/4" class="weui_btn weui_btn_primary pointer" id="showToast">专场</a>
					</div> --%>
					<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
						<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
							<a href="/gm/1" class="weui_btn weui_btn_primary pointer" id="showToast">店铺</a>
						</div>
					</div>
					<c:if test="${!empty auctionInst.id}">
						<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
							<a href="/myInst" class="weui_btn weui_btn_primary pointer" id="showToast">我的专场</a>
						</div>
					</c:if>
					<c:if test="${empty auctionInst.id}">
						<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
	                		<a class="weui_btn weui_btn_primary pointer" href="/instApply">申请开通专场</a>
	                	</div>
	                </c:if>
					<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
						<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
							<a href="/myAuctionMicro" class="weui_btn weui_btn_primary pointer" >我的微拍</a>
						</div>
					</div>
					<c:if test="${!empty auctionQuickInst.id}">
						<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
							<a href="/myAuctionQuick" class="weui_btn weui_btn_primary pointer" >我的即时拍</a>
						</div>
					</c:if>
					<c:if test="${empty auctionQuickInst.id}">
						<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
	                		<a class="weui_btn weui_btn_primary pointer" href="/auctionQuickInstApply">申请开通即时拍</a>
	                	</div>
	                </c:if>
					<c:if test="${!empty wholesaleId}">
						<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
							<a href="/gm/5" class="weui_btn weui_btn_primary pointer" >批发城</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="pz_down">
			<div class="c_0100_9"></div>
		</div>
		<div class="save bottomFix pointer" style="background-color: #04BE02;">
			<ul>
				<li><a href="/wxMsgCount" class="a_say" id="" >群发消息</a></li>
			</ul>
		</div>
		<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
		<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%-- <%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 --> --%>
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
</body>
</html>
