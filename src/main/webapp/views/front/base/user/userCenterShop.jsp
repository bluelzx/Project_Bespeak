<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" href="/css/front/wpknew/my_seller.css" title="v">
<link rel="stylesheet" href="/css/front/wpknew/common.css" title="v">
<link rel="stylesheet" href="/cssjs_stable/css/font-awesome.min.css">
<!--[if IE 7]>
    <link rel="stylesheet" href="/css/front/wpknew/font-awesome-ie7.min.css" title="v">
    <![endif]-->
<script src="/cssjs_stable/js/jquery-1.11.2.js"></script>
<script src="/js/front/user/myseller.js" title="v"></script>
<script src="/cssjs_stable/js/bootstrap.min.js"></script>
<style type="text/css">
.col-xs-4 {
	width: 16.666%;
}
</style>

</head>
<body style="background-color: #f0f0f6;">
	<input type="hidden" id="fansId" value="${user.id}" />
	<input type="hidden" id="isFans" value="${isFans}" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 clickHead">
				<img width="70" src="${user.avatar}@70w_70h_4e_240-240-246bgc_50Q" class="center-block" /> <i class="weui_icon_safe weui_icon_safe_success"></i>
				<div class="text-center white pt5">
					<div class="fs18">${user.username}</div>
					<div class="pt5">
						不求最好只要更好 <i class="icon-edit"></i>
					</div>
				</div>
			</div>
			<div class="col-xs-12 white_bg bdb maimai">
				<div class="col-xs-6 ptb10 text-center active" onclick="lh.jumpR('/user/${user.serial}')">卖家</div>
				<div class="col-xs-6 ptb10 text-center " onclick="lh.jumpR('/userCenter/${user.serial}')">买家</div>
			</div>
			<div class="col-xs-12 white_bg">
				<div class="col-xs-4 ptb10 text-center">
					<span>违约</span><br> <span class="gray-init">${credit.breakRateB }</span>
				</div>
				<div class="col-xs-4 ptb10 text-center">
					<span>退货</span><br> <span class="gray-init">${credit.backRateB }</span>
				</div>
				<div class="col-xs-4 ptb10 text-center">
					<span>成交</span><br> <span class="gray-init">${credit.doneRateB }</span>
				</div>
				<div class="col-xs-4 ptb10 text-center">
					<span>好评</span><br> <span class="gray-init">${credit.goodRateB }</span>
				</div>
				<div class="col-xs-4 ptb10 text-center">
					<span>粉丝</span><br> <span class="gray-init">${focusCount}</span>
				</div>
				<div class="col-xs-4 ptb10 text-center">
					<span>关注</span><br> <span class="gray-init">${fansCount }</span>
				</div>
			</div>
		</div>
		<div class="row mt6 white_bg">
			<div class="col-xs-12">
				<div class="col-xs-12 plr0 bdb ptb10">
					<div class="col-xs-4 plr0">实名认证</div>
					<div class="col-xs-4 plr0 gray-init">
						<span>${isRealAuth}</span> <i class="weui_icon_safe weui_icon_safe_success weui_icon_safeBefore"></i>
					</div>
					<div class="col-xs-4 plr0 gray-init elli">已由微拍堂官方审核</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="col-xs-12 plr0 bdb ptb10">
					<div class="col-xs-4 plr0">消保金</div>
					<div class="col-xs-4 plr0 gray-init">
						<span>${userFun.otherFund }</span>
					</div>
					<div class="col-xs-4 plr0 gray-init elli">已签署消费者保障协议</div>
				</div>
			</div>
			<div id="data-container"></div>
			<div id="data-container2"></div>
			<!-- <div class="col-xs-12">
				<div class="col-xs-12 plr0 bdb ptb10">
					<div class="col-xs-4 plr0 pt15">微拍拍品</div>
					<div class="col-xs-8 plr0 elli">
						<img width="55" src="/images/front/pic_01.png" class="mr7" /> 
						<img width="55" src="/images/front/pic_01.png" class="mr7" /> 
						<img width="55" src="/images/front/pic_01.png" class="mr7" />
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="col-xs-12 plr0 bdb ptb10">
					<div class="col-xs-4 plr0 pt15">专场拍品</div>
					<div class="col-xs-8 plr0 elli">
						<img width="55" src="/images/front/pic_01.png" class="mr7" /> <img width="55" src="/images/front/pic_01.png" class="mr7" /> <img width="55" src="/images/front/pic_01.png" class="mr7" />
					</div>
				</div>
			</div> -->
			<div class="col-xs-12">
				<div class="col-xs-12 plr0 bdb ptb10">
					<div class="col-xs-4 plr0">
						<img width="10" src="/images/front/head_pic_coo.png" class="dis-lin"> <span>地区</span>
					</div>
					<div class="col-xs-8 plr0 gray-init">${user.provinceName}${user.cityName}</div>
				</div>
			</div>
		</div>
		<div class="row ptb20">
			<div class="col-xs-12">
				<a id="fansManager" rel="button" class="col-xs-12 btn btn-lg btn-orange">关注</a>
			</div>
		</div>
	</div>


	<%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
	<script type="text/javascript" src="/js/front/user/userCenterShop.js" title="v"></script>

	<script id="template" type="x-tmpl-mustache">
		{{#rows}}	 	
			 <div class="col-xs-12" onclick="lh.jumpR('/am');">
				<div class="col-xs-12 plr0 bdb ptb10">
					<div class="col-xs-4 plr0 pt15">微拍拍品</div>
					<div class="col-xs-8 plr0 elli">
						{{&picsDom}}
					</div>
				</div>
			</div>
			
		{{/rows}}
	</script>
	<script id="template2" type="x-tmpl-mustache">
		{{#rows}}	 	
			 <div class="col-xs-12" onclick="lh.jumpR('/ap');">
				<div class="col-xs-12 plr0 bdb ptb10">
					<div class="col-xs-4 plr0 pt15">专场拍品</div>
					<div class="col-xs-8 plr0 elli">
						{{&picsDom}}
					</div>
				</div>
			</div>
		{{/rows}}
	</script>


</body>
</html>