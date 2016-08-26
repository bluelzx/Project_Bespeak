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
            <div class="col-xs-2" onclick="lh.back();">
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-8 pt10 plr0 text-center">
                <span class="fs16">支付安全</span>
            </div>
        </div>
        <div class="row mt6">
            <div class="col-xs-12 set_selectRow white_bg">
            	<div class="col-xs-7 ptb15 plr0">
            		允许电脑上传图片
            	</div>
                <div class="col-xs-5 plr0 text-right pt5">
                    <input class="weui_switch" type="checkbox" checked="checked">
                </div>
            </div>
        </div>
        <!-- <div class="row mt6">
            <div class="col-xs-12 ptb15 set_selectRow white_bg">
            	设置登录密码
            	<i class="icon-angle-right icon-2x"></i>
            </div> 
            <div class="col-xs-12 ptb15 set_selectRow white_bg" onclick="lh.jumpR('/user/safety/loginPassRetrieve');">
            	找回登录密码
            	<i class="icon-angle-right icon-2x"></i>
            </div>
        </div> -->
        <div class="row mt6">
            <div class="col-xs-12 ptb15 set_selectRow white_bg" onclick="lh.jumpR('/payPasswordSet');">
            	设置支付密码
            	<i class="icon-angle-right icon-2x"></i>
            </div>
            <div class="col-xs-12 ptb15 set_selectRow white_bg" onclick="lh.jumpR('/payPasswordRetrieve');">
            	找回支付密码
            	<i class="icon-angle-right icon-2x"></i>
            </div>
        </div>
        <div class="row mt6">
            <div class="col-xs-12 ptb15 set_selectRow white_bg" onclick="lh.jumpR('/user/safety/phoneValidate');">
            	手机号验证
            	<i class="icon-angle-right icon-2x"></i>
            </div>
            <div class="col-xs-12 ptb15 set_selectRow white_bg" onclick="lh.jumpR('/user/safety/realNameAuthentication');">
            	实名认证
            	<i class="icon-angle-right icon-2x"></i>
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