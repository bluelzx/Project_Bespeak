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
                <span class="fs16">提现</span>
            </div>
        </div>
        <div class="row white_bg ptb10 bdb mt6">
        	<div class="col-xs-4 pr0 pt5">
				到账银行卡
        	</div>
        	<div class="col-xs-8 pl0">
        		<select class="form-control bdn blue">
				  	<option>民生银行（6348）</option>
				  	<option>招商银行（6248）</option>
				  	<option>农业银行（63148）</option>
				  	<option>民生银行（6448）</option>
				</select>
        	</div>
        </div>
        <div class="row white_bg ptb10 bdb mt6">
        	<div class="col-xs-4 pr0 pt5">
				提现金额
        	</div>
        	<div class="col-xs-8 pl0">
        		<input type="text" class="form-control bdn" placeholder="可提现的金额：0.06元">
        	</div>
        </div>
        <div class="row white_bg ptb10 bdb mt6">
        	<div class="col-xs-4 pr0 pt5">
				提现手续费
        	</div>
        	<div class="col-xs-8 pl0">
        		<input type="text" class="form-control bdn" placeholder="">
        	</div>
        </div>
        <div class="row">
        	<div class="col-xs-12 gray-init ptb5">
        		<p>提现时间：工作日24小时到帐！（节假日到帐时间顺延） 每日限额50000.00元</p>
        		<p>提现事项：提现时银行扣除0.04的手续费。提现金额必 须大于100.00元</p>
        	</div>
        </div>
        <div class="row pt30">
        	<div class="col-xs-12 pt30">
                <a href="" rel="button" class="col-xs-12 btn btn-lg btn-orange">提现</a>
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