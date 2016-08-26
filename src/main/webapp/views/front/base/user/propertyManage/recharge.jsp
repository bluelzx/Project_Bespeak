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
                <i class="icon-angle-left icon-3x" onclick="lh.back();"></i>
            </div>
            <div class="col-xs-8 pt10 plr0 text-center">
                <span class="fs16">充值</span>
            </div>
        </div>
        <div class="row white_bg ptb10 bdb">
        	<div class="col-xs-2 pr0 fs16 pt5">
				金额
        	</div>
        	<div class="col-xs-10 pl0">
				<input type="text" id="amount" class="form-control bdn" placeholder="">
        	</div>
        </div>
        <div class="row">
        	<div class="col-xs-12 text-right gray ptb5">
        		单笔线上充值金额5000元
        	</div>
        </div>
        <div class="row pt30">
        	<div class="col-xs-12 pt30" onclick="loadRemainderRecharge();">
                <a rel="button" class="col-xs-12 btn btn-lg btn-orange disabled" >下一步</a>
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
        <script type="text/javascript" src="/js/front/user/propertyManage/recharge.js" title="v"></script>
</body>
</html>