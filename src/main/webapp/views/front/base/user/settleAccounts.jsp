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
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="pz_main" style="margin-top:50px;">
	 		<input type="hidden" id="userId" value="${userId}">
	 		<div class="addressUl c_0100_3" id="receiveAddressList">
		 		
			</div>
			<div id="resultTip" class="resultTip frontHide"></div>
			<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
			<div class="save bottomFix" >
				<ul>
					<li>
						<a href="javascript:;" class="a_say" style="width:50%;" onclick="payMoneyDirectly(); return false;">直接付款</a>
						<a href="javascript:;" class="a_say" style="background-color: green;width:50%;" onclick="payMoney('${orderGoodsId}'); return false;">余额支付</a>
					</li>
				</ul>
			</div>
	</div>
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<div id="offerWin" class="szweituo" style="display:none;z-index:1">
		<div class="szweituodiv">
			<div class="szwtcontent">
				<div class="szwtinput">
					<div id="offerTip" class="szwtzuigao">输入支付密码:</div>
					<div id="offerIptDiv" class="szwtinpk">
						<form id="entrustFrom">
							<input type="hidden" id="orderGoodsId">
							<input type="hidden" id="addressId">
							<span><input type="password" id="payPass" placeholder="请在此输入支付密码" value=""></span>
						</form>
						<div style="clear: both;"></div>
					</div>
					<div class="szwtbutton">
						<span id="offerCancel" onclick="doAutoOffer('cancel')" class="szwtbutr entrusta2 pointer">取消</span> 
						<span id="offerSave" onclick="doAutoOffer('save')" class="szwtbutl entrusta1 pointer">确定</span>
						<div style="clear: both;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/views/front/common/z_div_call.htm"%><!-- 出价面板 -->
	
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
	<script type="text/javascript" src="/js/common/call.js" title="v"></script>
	<script src="/js/front/user/settleAccounts.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<div style="font-size: 16px;">
				<input id="check_{{id}}" class="address_check" type="checkbox" onChange="checkedAddress({{id}});"/>
				<input type="hidden" id="addressId" value="{{id}}">
				收货人：{{receiverName}}<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;地址：{{provinceName}}省&nbsp;{{cityName}}市<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;详细地址：{{addressDetail}}&nbsp;&nbsp;<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;联系电话：{{phone}}
			</div>
		{{/rows}}		 
	</script>
</body>
</html>
