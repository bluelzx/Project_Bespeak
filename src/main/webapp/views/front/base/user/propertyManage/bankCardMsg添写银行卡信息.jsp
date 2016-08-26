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
                <span class="fs16">添写银行卡信息</span>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 gray-init ptb10">
                信息加密处理，仅用于银行验证
            </div>
        </div>
        <div class="row white_bg ptb10 bdb">
        	<div class="col-xs-3 pr0 pt3">
				卡类型
        	</div>
        	<div class="col-xs-9 pl0 pt3">
        		中国工商银行储蓄卡
        	</div>
        </div>
        <div class="row white_bg ptb10 bdb mt6">
        	<div class="col-xs-3 pr0 pt5">
				手机号
        	</div>
        	<div class="col-xs-9 pl0">
        		<div class="form-group has-default has-feedback mb0">
				  	<input type="text" id="phone" class="form-control bdn" placeholder="银行预留手机号码" />
				  	<span class="glyphicon glyphicon-exclamation-sign form-control-feedback orange" aria-hidden="true"></span>
				</div>
        	</div>
        </div>
        <div class="row">
        	<div class="col-xs-12 gray-init ptb5">
        		同意《服务协议》
        	</div>
        </div>
        <div class="row pt30">
        	<div class="col-xs-12 pt30">
                <button type="button" id="bankCardMsg" disabled class="col-xs-12 btn btn-lg btn-orange">下一步</button>
        	</div>
        </div>
    </div>
    <div class="modelLg">
    	姓名或银行卡号与银行账户信息不一致
    </div>
<script>
    $(function(){
        $("#phone").on("keypress",function(){
            if ($(this).val().length == 10) {
            	$("#bankCardMsg").removeAttr("disabled");
            }
        });
        $("#bankCardMsg").on("click",function(){
            $(".modelLg").show(300);
            $('.modelLg').delay(1000).hide(300);
        });
    })
</script>

 <%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
    
    

</body>
</html>