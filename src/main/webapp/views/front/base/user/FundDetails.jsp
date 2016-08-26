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
<link rel="stylesheet" type="text/css" href="/css/front/news.css" title="v" />
</head>
<body ><!-- style="background-color:#f5f5f5;" -->
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	 <input type="hidden" id="id" value="${userFund.id}">
 	<div class="pz_main" style="margin-top:50px;">
 	 		<div class="c_0100_19">
	 	 		<div class="slide_sale" style="padding:0;text-align: center;">
					<div class="cytopdiv" style="color:black;">
						<span id="IN1" class="cytopdiv1 pointer"  onclick="MyIn()">收入</span>
						<span id="IN2" class="cytopdiv2 pointer"  onclick="MyOut()">支出</span>
						<span id="OUT1" class="cytopdiv1 pointer"  style="display:none;background: white;color:black" onclick="MyIn()">收入</span>
						<span id="OUT2" class="cytopdiv2 pointer" style="display:none;background: #5F6367;color:white;"  onclick="MyOut()">支出</span>
					</div>
				</div>
			</div>
	 		<!-- <div class="mainbox" id="iOther">
					<div class="mainsmall" id="auctionOnlineMain">
						<div id="main" role="main" style="border-bottom: 1px solid #CDCDCD; background: white;">
							<div style="padding: 0 5px;">
						 		<ul class="information" id="fundDetailList">
									
								</ul>
							</div>
						</div>
					</div>
			</div> -->
			<div class="c_0100_15">
				<div class="c_0100_15">
					<ul class="information" id="fundDetailList">
									
					</ul>
				</div>
			</div>
			<div id="resultTip" class="resultTip frontHide"></div>
			<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	<div class="pz_menu">
		<ul id="top_header" class="jydfoot">
			<li  class="pointer" state="1">账户余额(${userFund.frozenMoney+userFund.avaliableMoney})</li>
			<li  class="pointer">可用资金(${userFund.avaliableMoney})</li>
			<li  class="pointer" >冻结资金(${userFund.frozenMoney})</li>
		</ul>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script src="/js/front/user/fundDetails.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<li>
				<div>
					<span style="padding: 5px;">可用资金:{{avaliableMoneyLog}}</span>
					<span style="padding: 5px;">冻结资金:{{frozenMoneyLog}}</span><br/>
					{{#inOrOut}}
						<span style="color:green;padding: 5px;">收入:+{{money}}</span>
					{{/inOrOut}}
					{{^inOrOut}}
						<span style="color:red;padding: 5px;">支出:-{{money}}</span>
					{{/inOrOut}}
					<span style="padding: 5px;">支付时间:{{createdAt}}</span>
				</div>
			</li>	
		{{/rows}}		 
	</script>
</body>
</html>
