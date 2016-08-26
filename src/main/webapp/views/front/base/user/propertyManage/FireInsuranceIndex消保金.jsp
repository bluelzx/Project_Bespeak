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
                <span class="fs16">消保金</span>
            </div>
        </div>
        <div class="row white_bg mt6">
        	<div class="col-xs-3 pr0 ptb10">
				消保金
        	</div>
        	<div class="col-xs-7 pl0 ptb10">
        		0.00元
        	</div>
            <div class="col-xs-2 plr0 ptb10 white text-center orange_bg">
                缴纳
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 gray-init ptb5 text-right">
                消保金在有未完成拍品时不能领取
            </div>
        </div>
        <div class="row white_bg">
            <div class="col-xs-3 pr0 ptb10">
                理赔记录
            </div>
            <div class="col-xs-7 pl0 ptb10">
                
            </div>
            <div class="col-xs-2 plr0 ptb10 text-center">
                <div class="col-xs-12 plr0 bdl">
                    <img src="images/xiaobao_01.png" class="img-responsive center-block">
                </div>
            </div>
        </div>
        <div class="row pt30">
            <div class="col-xs-12 pt30 ptb10 text-center">
                <img src="images/xiaobao_wu.png" class="img-responsive center-block">
                <p class="pt15 fs16 gray">暂无理赔记录</p>
            </div>
        </div>
        <div class="row pt30">
        	<div class="col-xs-12 pt30">
                <a href="javascript:;" class="weui_btn weui_btn_primary">安全支付</a>
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