<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<%@ include file="/views/common/common_front_wpk_css.htm"%>
<link rel="stylesheet" href="/cssjs_stable/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/third-party/swiper/swiper.3.1.7.min.css" />
<link rel="stylesheet" href="/css/front/wpk/style_v1.css" title="v">
<link rel="stylesheet" href="/css/front/wpk/my_v1.css" title="v">
</head>
<body style="background-color: #f0f0f6;">
	<div class="container-fluid">
		<div class="row customerIndexHead">
			<div class="col-xs-12 pl7 pr0 bdb-white pb10">
				<div class="col-xs-3 pr0 pl7 pt20">
					<img src="${user.avatar}@50w_50h_4e_240-240-246bgc_50Q" width="71" height="71" class="img-responsive">
				</div>
				<div class="col-xs-9 pt20 mt-10">
					<div class="col-xs-12 plr0 text-right" onclick="lh.jumpR('/userMerchantsIndex');">切换到卖家</div>
					<div class="col-xs-12 plr0 fs16">
						${user.username} <img src="/images/front/pic_cheng.png" />
					</div>
					<div class="col-xs-12 plr0 pt10">
						余额<span>${user.avaliableMoney}</span>&nbsp;&nbsp;&nbsp; 保险金<span>${user.otherFund}</span>&nbsp;&nbsp;&nbsp; <a href="/fund/charge/recharge" class="btn btn-xs btn-orange" rel="button">充值</a>
					</div>
				</div>
			</div>
			<div class="col-xs-12 plr0 customerIndexHeadMenu">
				<ul>
					<li>今日成交总额<br> <span>￥${user.nowShopPrice}</span></li>
					<li>今日访客<br> <span>${user.attrInt}</span></li>
					<li>今日订单<br> <span>${user.nowOrderCount}</span></li>
				</ul>
			</div>
		</div>
		<div class="row white_bg">
			<div class="col-xs-12 plr0 ptb10 bdb">
				<div class="col-xs-6 fs16">我的订单</div>
				<div class="col-xs-6 text-right gray ">
					<span>查看全部订单</span> <i class="icon-angle-right icon-large"></i>
				</div>
			</div>
		</div>
		<div class="row customerIndexList">
			<div class="col-xs-12 plr0 ptb10">
				<a href="">
					<div class="com">
						<img class="img-responsive center-block pb5" src="/images/front/customer_index_01.png" /> 待付款 <i class="red icon-circle"></i>
					</div>
				</a> <a href="">
					<div class="com">
						<img class="img-responsive center-block pb5" src="/images/front/customer_index_02.png" /> 待发货 <i class="red icon-circle"></i>
					</div>
				</a> <a href="">
					<div class="com">
						<img class="img-responsive center-block pb5" src="/images/front/customer_index_03.png" /> 待收货 <i class="red icon-circle"></i>
					</div>
				</a> <a href="">
					<div class="com">
						<img class="img-responsive center-block pb5" src="/images/front/customer_index_04.png" /> 待评价 <i class="red icon-circle"></i>
					</div>
				</a> <a href="">
					<div class="com">
						<img class="img-responsive center-block pb5" src="/images/front/customer_index_05.png" /> 退款/售后
					</div>
				</a>
			</div>
		</div>
		<div class="row customerIndexMenu">
			<a href="">
				<div class="col-xs-3 list">
					<img src="/images/front/customer_menu_01.png" height="25" class="img-responsive center-block pb15"> 我的参拍
				</div>
			</a> <a href="/startAuction">
				<div class="col-xs-3 list">
					<img src="/images/front/customer_menu_02.png" height="25" class="img-responsive center-block pb15"> 开拍提醒 <i class="red icon-circle"></i>
				</div>
			</a> <a href="/propertyManage">
				<div class="col-xs-3 list">
					<img src="/images/front/customer_menu_03.png" height="25" class="img-responsive center-block pb15"> 财务管理 <i class="red icon-circle"></i>
				</div>
			</a> <a href="">
				<div class="col-xs-3 list">
					<img src="/images/front/customer_menu_04.png" height="25" class="img-responsive center-block pb15"> 我的红包 <i class="red icon-circle"></i>
				</div>
			</a> <a href="/user/safety/paySafety">
				<div class="col-xs-3 list">
					<img src="/images/front/customer_menu_05.png" height="25" class="img-responsive center-block pb15"> 支付安全
				</div>
			</a> <a href="/address">
				<div class="col-xs-3 list"  >
					<img src="/images/front/customer_menu_06.png" height="25" class="img-responsive center-block pb15"> 地址管理<i class="red icon-circle"></i>
				</div>
			</a> <a href="">
				<div class="col-xs-3 list">
					<img src="/images/front/customer_menu_07.png" height="25" class="img-responsive center-block pb15"> 我的代理
				</div>
			</a> <a href="/forumIndex">
				<div class="col-xs-3 list" >
					<img  src="/images/front/customer_menu_08.png" height="25" class="img-responsive center-block pb15"> 我的社区
				</div>
			</a> 
			</a> <a href="/fans">
				<div class="col-xs-3 list" >
					<img  src="/images/front/customer_menu_08.png" height="25" class="img-responsive center-block pb15"> 师友
				</div>
			</a> 
			</a> <a href="/message/message">
				<div class="col-xs-3 list" >
					<img  src="/images/front/customer_menu_08.png" height="25" class="img-responsive center-block pb15"> 消息
				</div>
			</a> 
		</div>
	</div>



	<!-- 底部导航条 -->
	<div class="h90"></div>
	<div class="performance_fixed">
		<a href="">
			<div class="com">
				<img class="img-responsive center-block" src="/images/front/img_01.png" /> 首页
			</div>
		</a> <a href="">
			<div class="com">
				<img class="img-responsive center-block" src="/images/front/img_02.png" /> 文玩珠宝
			</div>
		</a>
		<div class="release">
			<div class="white_round">
				<div class="orange_round" id="release">
					<img class="img-responsive center-block" src="/images/front/img_05.png" />
				</div>
				<div class="word">发布</div>
			</div>
		</div>
		<div class="com">
			<img class="img-responsive center-block" src="/images/front/img_03.png" /> 消息 <i class="red icon-circle"></i>
		</div>
		<div class="com orange">
			<img class="img-responsive center-block" src="/images/front/img_04_active.png" /> 我的
		</div>
	</div>
	<div class="release_click">
		<div class="row bottom">
			<div class="col-xs-12 plr0 pt10">
				<div class="col-xs-4 text-center plr7">
					<img src="/images/front/release_01.png" class="img-responsive center-block">
				</div>
				<div class="col-xs-4 text-center plr7">
					<img src="/images/front/release_02.png" class="img-responsive center-block">
				</div>
				<div class="col-xs-4 text-center plr7">
					<img src="/images/front/release_03.png" class="img-responsive center-block">
				</div>
			</div>
			<div class="col-xs-12 plr0 pt10">
				<div class="col-xs-4 text-center plr7">
					<img src="/images/front/release_04.png" class="img-responsive center-block">
				</div>
			</div>
		</div>
	</div>
	<div class="closeX">
		<img src="/images/front/closeX.png" id="closeX" class="img-responsive center-block">
	</div>

	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
		<!-- <script type="text/javascript">lh.param = ${paramJson}</script> -->
<script type="text/javascript">
localStorage.setItem("url","/user");
</script>
</body>
</html>