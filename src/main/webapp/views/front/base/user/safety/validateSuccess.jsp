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
<input type="hidden" value="${user.isRealAuth}" id="isRealAuth" />
    <div class="container-fluid">
        <div class="row auction_title">
            <div class="col-xs-3" onclick="lh.back();">
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-6 pt10 plr0 text-center">
                <span class="fs16">身份验证成功</span>
            </div>
        </div>
        <div class="row white_bg">
            <div class="col-xs-12">
	            <div class="col-xs-12 plr0 ptb10 bdb">
	                <div class="col-xs-4 pr0">
	                    <img src="/images/front/success_green.png" width="50" class="img-responsive pull-right">
	                </div>
	                <div class="col-xs-8">
	                    <div class="col-xs-12 plr0 fs16 pt5">恭喜您，身份验证成功</div>
	                    <div class="col-xs-12 plr0 fs12 gray-init">
	                        请妥善保管您的个人信息
	                    </div>
	                </div>
	            </div>
            </div>
            <div class="col-xs-12 fs12 gray-init ptb5">
            	请核对个人信息
            </div>
        </div>
        <div class="row white_bg">
            <div class="col-xs-12 pb10 orange text-center">
                姓名：${user.realName}
            </div>
            <div class="col-xs-12 pt10 pb20">
	            <img src="${user.picPath}" width="150" class="img-responsive center-block">
            </div>
        </div>
        <div class="row pt30" id="next" style="display: none;">
        	<div class="col-xs-12 pt30">
                <a rel="button" class="col-xs-12 btn btn-lg btn-orange" onclick="saveUserAuthentication();">下一步</a>
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
	<script src="/js/front/user/safety/saveUserAuthentication.js" title="v"></script>
</body>
</html>