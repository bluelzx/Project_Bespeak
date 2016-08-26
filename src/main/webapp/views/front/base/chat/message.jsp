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
  <style>
		.h90{
			height: 90px;
		}
		.round_red{
			width: 15px;
			height: 15px;
			border-radius: 50%;
			background-color: #f00;
			color: #fff;
			float: right;
			text-align: center;
		}
		.message_icon{
			position: absolute;
			top: -3px;
			right: -3px;
		}
    </style>
</head>
<body style="background-color: #f0f0f6;">
    <div class="container-fluid">
        <div class="row white_bg">
            <div class="col-xs-12 pl25">
				<div class="col-xs-12 plr0 gray ptb10 bdb">
					<div class="col-xs-2 plr0">
						<img width="45" src="/images/front/message_01.png" class="center-block" />
					</div>
					<div class="col-xs-5 plr7 pt10 fs18">${notice.content}</div>
					<div class="col-xs-5 plr7 fs28 text-right"><i class="icon-angle-right"></i></div>
				</div>
				<div class="col-xs-12 plr0 gray ptb10 bdb"  onclick="lh.jumpR('/fans');">
					<div class="col-xs-2 plr0">
						<img width="45" src="/images/front/message_02.png" class="center-block" />
					</div>
					<div class="col-xs-5 plr7 pt10 fs18">师友管理</div>
					<div class="col-xs-5 plr7 fs28 text-right"><i class="icon-angle-right"></i></div>
				</div>
            </div>
        </div>
        <div class="row white_bg mt6">
             <!-- <div class="col-xs-12 pl25">
				<div class="col-xs-12 plr0 gray ptb10 bdb">
					 <div class="col-xs-2 plr0">
						<img width="45" src="/images/front/message_03.png" class="center-block" />
						<i class="red icon-circle message_icon"></i>
					</div> 
					<div class="col-xs-10 pl7 pr0">
						<div class="fs18">
							<span>微拍客消息</span>
							<span class="fs12 gray-init pull-right">11:02</span>
						</div>
						<div class="fs12 gray-init">
							<span>您好，您需要咨询什么业务</span>
							<span class="round_red">1</span>
						</div>
					</div> 
				</div>  -->
				<div id="data-container"></div>
				
				<!--  <div class="col-xs-12 plr0 gray ptb10 bdb">
					<div class="col-xs-2 plr0">
						<img width="45" src="/images/front/message_04.png" class="center-block" />
					</div>
					<div class="col-xs-10 pl7 pr0">
						<div class="fs18">
							<span>Martian</span>
							<span class="fs12 gray-init pull-right">昨天</span>
						</div>
						<div class="fs12 gray-init">
							<span>在么？</span>
						</div>
					</div>
				</div>  -->
				
            </div>
        </div>
    </div>

	 <!-- 底部导航条 -->
	<!-- <div class="h90"></div>
	<div class="performance_fixed">
		<a href="">
			<div class="com orange">
				<img class="img-responsive center-block" src="/images/front/img_01.png" />
				首页
			</div>
		</a>
		<a href="">
			<div class="com">
				<img class="img-responsive center-block" src="/images/front/img_02.png" />
				文玩珠宝
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
		<div class="com orange">
			<img class="img-responsive center-block" src="/images/front/img_03_orange.png" />
			消息
			<i class="red icon-circle"></i>
		</div>
		<div class="com">
			<img class="img-responsive center-block" src="/images/front/img_04.png" />
			我的
		</div>
	</div> -->
	<!-- <div class="release_click">
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
	</div> -->

	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	 <%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
	<script type="text/javascript" src="/js/front/chat/message.js" title="v"></script>
    
   <script id="template" type="x-tmpl-mustache">
		{{#rows}}	 	
			<div class="col-xs-12 plr0 gray ptb10 bdb" onclick="readMessage('{{senderSerial}}',{{id}});">
					<div class="col-xs-2 plr0">
						<img width="45" src="{{senderAvatar}}@50w_50h_4e_240-240-246bgc_50Q" class="center-block" />
						<i class="red icon-circle message_icon"></i>
					</div>
					<div class="col-xs-10 pl7 pr0">
						<div class="fs18">
							<span>{{senderName}}</span>
							<span class="fs12 gray-init pull-right">{{&date}}</span>
						</div>
						<div class="fs12 gray-init">
							<span>{{content}}</span>
							<span class="round_red">1</span>
						</div>
					</div>
				</div>
		{{/rows}}
	</script>
</body>
</html>