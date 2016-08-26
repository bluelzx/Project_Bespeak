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
<style>
.weui-pull-to-refresh-layer{
	display: none;
}
.auction_title{
	position:relative;
}
</style>
<body>

 <div id="weui-layer" class="weui-pull-to-refresh-layer" style="display: none;height:50px;">
    <div class="pull-to-refresh-arrow"></div> <!-- 上下拉动的时候显示的箭头 -->
    <div class="pull-to-refresh-preloader"></div> <!-- 正在刷新的菊花 -->
    <div class="down">下拉刷新</div><!-- 下拉过程显示的文案 -->
    <div class="up">释放刷新</div><!-- 下拉超过50px显示的文案 -->
    <div class="refresh">正在刷新...</div><!-- 正在刷新时显示的文案 -->
  </div>

    <div class="container-fluid">
        <div class="row auction_title">
            <div class="col-xs-2" onclick="lh.back();">
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-8 pt10 plr0 text-center">
                <span class="fs16">账单</span>
            </div>
        </div>
        <div class="row white_bg ptb10">
        	<div class="col-xs-6 pr0">
				<div id="dropdown" class="dropdown">
			  		<button class="btn btn-default dropdown-toggle bdn fs16" type="button" 
			  		id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" 
			  		aria-expanded="true" onclick="toggleBtn();">
            			<i class="icon-align-justify"></i>
			    		款项筛选
			  		</button>
				  	<ul class="dropdown-menu text-center" aria-labelledby="dropdownMenu1">
				    	<li class="text-center" id="getallBill" onclick="getallBill();"><a>全部款项</a></li>
				    	<li role="separator" class="divider"></li>
				    	<li class="text-center" id="getfreezeMoneyBill" onclick="getfreezeMoneyBill();"><a>冻结中款项</a></li>
				    	<li role="separator" class="divider"></li>
				    	<li class="text-center" id="getactivateMoneyBill" onclick="getactivateMoneyBill();"><a>已解冻款项</a></li>
				    	<li role="separator" class="divider"></li>
				    	<li class="text-center" id="getwithdrawalBill" onclick="getwithdrawalBill();"><a>已提现款项</a></li>
				  	</ul>
				</div>
        	</div>
        	<div class="col-xs-6 pl0 text-right fs16 pt5">
				余额 <span class="orange">￥${userFUN.avaliableMoney}</span>
        	</div>
        </div>
        <div id="data-container"></div>
        <!-- <div class="row white_bg bdb">
        	<div class="col-xs-12 hi6"></div>
            <div class="col-xs-8 pr7 ptb10">
            	<div class="col-xs-12 plr0 fs16">充值</div>
            	<div class="col-xs-12 plr0 gray">微信-2016-06-28 16:22</div>
            </div>
            <div class="col-xs-4 pl7 fs18 green pt15 text-right">
    			+1.00
            </div>
        </div> -->
        
        <div class="weui-infinite-scroll">
  <div class="infinite-preloader"></div><!-- 菊花 -->
  正在加载... <!-- 文案，可以自行修改 -->
</div>
        
    </div>
    
     <%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
	<script type="text/javascript" src="/js/front/user/propertyManage/billDetail.js" title="v"></script>
	
    <script id="template" type="x-tmpl-mustache">
		{{#rows}}	 	
			<div class="row white_bg bdb mt6">
        	<div class="col-xs-12 hi6"></div>
            <div class="col-xs-8 pr7 ptb10">
					{{&payCode}}
            	<div class="col-xs-12 plr0 gray">{{payName}}-{{&date}}</div>
            </div>
				{{&isInorOut}}
        </div>
		{{/rows}}
	</script>
    
</body>
</html>