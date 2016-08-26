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
                <span class="fs16">手机号验证</span>
            </div>
        </div>
        <div class="row white_bg mt6">
        	<div class="col-xs-2 pr0 pb10 pt15">
				<i class="icon-mobile-phone icon-2x"></i>
        	</div>
        	<div class="col-xs-10 plr0 ptb10">
                <div class="col-xs-12 pl0">
            		<input type="text" style="font-size: 20px;" id="phoneNum" class="form-control bdn" placeholder="请输入手机号" value="${showPhone}" />
                </div>
        	</div>
        </div>
        <div class="row white_bg mt6">
        	<div class="col-xs-2 pr0 pb10 pt15">
				<i class="icon-comment-alt icon-large"></i>
        	</div>
        	<div class="col-xs-6 plr0 ptb10">
                <div class="col-xs-12 pl0">
            		<input type="number" id="phoneCode" class="form-control bdn" placeholder="请输入验证码" >
                </div>
        	</div>
            <div class="col-xs-4 pl0 text-right pt10">
                <a rel="button" id="btnMssage" onclick="sendVerify();" class="btn btn-orange">获取验证码</a>
            </div>
        </div>
        <div class="row pt30">
            <div class="col-xs-12 pt30">
                <button href="" rel="button" class="col-xs-12 btn btn-lg btn-orange" onclick="checkPhoneCode();">完成</button>
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
	<script type="text/javascript" src="/js/front/user/safety/phoneValidate.js" title="v"></script>    
</body>
</html>