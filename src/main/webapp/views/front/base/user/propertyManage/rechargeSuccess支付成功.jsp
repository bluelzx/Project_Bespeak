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
</head>
<body style="background-color: #f0f0f6;">
	<div class="container-fluid">
        <div class="row auction_title">
            <div class="col-xs-2">
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-8 pt10 plr0 text-center">
                <span class="fs16">余额充值</span>
            </div>
        </div>
		<div class="row">
            <div class="col-xs-12 white_bg">
	            <div class="bdb ptb10 fs16">
	                余额充值
	            </div>
            </div>
		</div>
		<div class="row">
            <div class="col-xs-12 white_bg">
	            <div class="col-xs-12 plr0 ptb10 bdb pos-r">
					<div class="col-xs-4 plr0">
						<img width="97" class="img-responsive" src="images/recharge.png">
					</div>
					<div class="col-xs-8 pt30 pl7 pr0 text-right">
						<div class="col-xs-12 plr0 pt30 fs16">
							<i class="weui_icon_success fs20"></i>
							<span class="fs18">支付成功</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt6 pb20">
            <div class="col-xs-12 white_bg">
	            <div class="col-xs-12 plr0 bdb">
		            <div class="col-xs-2 pt10 plr0">
						<img width="40" class="img-responsive" src="images/pay_success.png">
		            </div>
		            <div class="col-xs-10 ptb10 plr0 fs16">
		            	<div class="col-xs-12 plr0">
			            	<div class="col-xs-12 plr0">充值金额</div>
			            	<div class="col-xs-12 plr0 fs14 gray-init">￥0.11元</div>
		            	</div>
		            	<div class="col-xs-12 plr0 pt20">
			            	<div class="col-xs-12 plr0">支付成功</div>
			            	<div class="col-xs-12 plr0 fs14 gray-init">06月28日 19：20</div>
			            </div>
		            </div>
	            </div>
            </div>
		</div>
		<div class="row pb20 pt30">
			<div class="col-xs-12 pt30">
				<a href="javascript:;" class="weui_btn weui_btn_primary">完成</a>
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
    
	
</body>
</html>