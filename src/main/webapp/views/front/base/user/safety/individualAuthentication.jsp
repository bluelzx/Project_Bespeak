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
                <span class="fs16">个人实名认证</span>
            </div>
        </div>
		<div class="row white_bg">
            <div class="col-xs-12 text-center">
	            <div class="ptb20 fs20">
	                个人实名认证流程
	            </div>
            </div>
		</div>
		<div class="row white_bg pb20 pt10">
            <div class="col-xs-12 grrenzheng">
	            <div class="col-xs-12 plr0 gray">
	            	<div class="col-xs-12 plr0">
	            		<img width="25" class="dis-lin pull-left" src="/images/front/individual_authentication_1.png" />
	            		<div class="col-xs-10 pr0 mt-10">填写真实姓名、联系电话、身份证号并上传身份证正面、反面和手持身份证的清晰照。</div>
	            	</div>
	            	<div class="col-xs-12 plr0 pt20">
	            		<img width="25" class="dis-lin pull-left" src="/images/front/individual_authentication_2.png" />
	            		<div class="col-xs-10 pr0">设置店铺名称、店铺Logo、及店铺介绍。</div>
	            	</div>
	            	<div class="col-xs-12 plr0 pt20">
	            		<img width="25" class="dis-lin pull-left mt15" src="/images/front/individual_authentication_3.png" />
	            		<div class="col-xs-10 pr0">提交实名认证申请后，工作人员将在10个工作日内与您联系核对信息。</div>
	            	</div>
	            </div>
            </div>
		</div>
		<div class="row pb20 pt30">
			<div class="col-xs-12 pt20" onclick="lh.jumpR('/user/safety/individualMsgAuthentication');">
				<a href="javascript:;" class="weui_btn weui_btn_primary">我要认证</a>
			</div>
			<div class="col-xs-12 pt10 fs12 text-center gray">
				点击即表示同意《微拍客认证增值服务协议》
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