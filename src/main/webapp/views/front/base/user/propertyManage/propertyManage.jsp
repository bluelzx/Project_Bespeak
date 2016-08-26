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
                <span class="fs16">财产管理</span>
            </div>
        </div>
        <div class="row white_bg">
        	<div class="col-xs-12 qing ptb10 text-right" onclick="lh.jumpR('/billDetail');">
        		<img width="18" src="/images/front/property_01.png" class="img-responsive dis-lin" />
        		账单明细
        	</div>
        </div>
        <div class="row white_bg ptb10 text-center pb15">
        	<div class="col-xs-12">
        		<span class="fs40 orange">${user.avaliableMoney}</span>元
        	</div>
        	<div class="col-xs-12">
        		冻结款项：${user.frozenMoney}
        	</div>
        </div>
        <div class="row mt6 white_bg fs16">
            <div class="col-xs-12 ptb15 set_selectRow" onclick="lh.jumpR('/recharge');">
    			<img src="/images/front/property_02.png" class="img-responsive propertyManageImg" />
        		充值
            	<i class="icon-angle-right icon-2x"></i>
            </div>
            <div class="col-xs-12 ptb15 set_selectRow bdb" onclick="lh.jumpR('/withdrawDeposit');">
    			<img src="/images/front/property_03.png" class="img-responsive propertyManageImg plr3" />
        			提现
            	<i class="icon-angle-right icon-2x"></i>
            </div>
        </div>
        <div class="row mt6 white_bg fs16">
            <div class="col-xs-12 ptb15 set_selectRow">
    			<img src="/images/front/property_04.png" class="img-responsive propertyManageImg" />
        		管理银行卡
        		<i class="icon-angle-right icon-2x"></i>
            </div>
            <div class="col-xs-12 ptb15 set_selectRow bdb">
    			<img src="/images/front/property_05.png" class="img-responsive propertyManageImg plr3" />
        		诚信保证金
				<span>${user.otherFund}</span>
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