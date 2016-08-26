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
                <span class="fs16">余额充值</span>
            </div>
        </div>
		<div class="row">
            <div class="col-xs-12 white_bg">
	            <div class="bdb ptb10 fs16">
	                余额充值
	            </div>
            </div>
		</div>
		<div class="row">
            <div class="col-xs-12 white_bg">
	            <div class="col-xs-12 plr0 ptb10 bdb pos-r">
					<div class="col-xs-4 plr0">
						<img width="97" class="img-responsive" src="/images/front/recharge.png">
					</div>
					<div class="col-xs-8 pt30 pl7 pr0 text-right">
						<div class="col-xs-12 plr0 pt30 red_deep fs16">
							￥<span class="fs24">1,000.00元</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt10">
            <div class="col-xs-12 white_bg">
	            <div class="col-xs-12 plr0 bdb">
		            <div class="col-xs-3 pt10 plr0">
		            	需要支付：
		            </div>
		            <div class="col-xs-9 ptb10 plr0">
		                <span class="fs18 red_deep">￥1000.00元</span>
		            </div>
	            </div>
            </div>
		</div>
		<div class="row">
            <div class="col-xs-12 white_bg">
	            <div class="col-xs-12 plr0 bdb ptb10 mb0">
				    <div class="weui_cells_checkbox">
				        <label class="weui_check_label" for="s11" style="display: flex;">
				            <div class="weui_cell_hd pt8">
				                <input type="radio" class="weui_check" name="checkbox1" id="s11">
				                <i class="weui_icon_checked"></i>
				            </div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <div class="col-xs-12 plr7">
				                	<div class="col-xs-2 plr0">
				                		<img width="41" class="img-responsive" src="/images/front/pay_01.png">
				                	</div>
				                	<div class="col-xs-7 plr7">
				                		<div class="gray fs16">支付密码未设置</div>
				                		<div class="gray">可用余额：￥${userFUN.avaliableMoney}</div>
				                	</div>
				                	<div class="col-xs-3 pt8 plr0 gray">
					                	<div class="col-xs-6 plr0 pt5">
					                		<span class="blue_deep">开通</span>
					                	</div>
					                	<div class="col-xs-6 plr0">
					                		&nbsp;&nbsp;<i class="icon-angle-right icon-2x"></i>
					                	</div>
				                	</div>
				                </div>
				            </div>
				        </label>
				    </div>
	            </div>
	            <div class="col-xs-12 plr0 bdb ptb10 mb0">
				    <div class="weui_cells_checkbox">
				        <label class="weui_check_label" for="s12" style="display: flex;">
				            <div class="weui_cell_hd pt8">
				                <input type="radio" class="weui_check" name="checkbox1" id="s12" checked="checked">
				                <i class="weui_icon_checked"></i>
				            </div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <div class="col-xs-12 plr7">
				                	<div class="col-xs-2 plr0">
				                		<img width="41" class="img-responsive" src="/images/front/pay_02.png">
				                	</div>
				                	<div class="col-xs-10 plr7">
				                		<div class="fs16">微信支付</div>
				                		<div class="gray fs10">使用微信支付，简单方便</div>
				                	</div>
				                </div>
				            </div>
				        </label>
				    </div>
	            </div>
	            <div class="col-xs-12 plr0 bdb ptb10 mb0">
				    <div class="weui_cells_checkbox">
				        <label class="weui_check_label" for="s13" style="display: flex;">
				            <div class="weui_cell_hd pt8">
				                <input type="radio" class="weui_check" name="checkbox1" id="s13">
				                <i class="weui_icon_checked"></i>
				            </div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <div class="col-xs-12 plr7">
				                	<div class="col-xs-2 plr0">
				                		<img width="41" class="img-responsive" src="/images/front/pay_03.png">
				                	</div>
				                	<div class="col-xs-10 plr7">
				                		<div class="fs16">银行卡支付</div>
				                		<div class="gray fs10">支持储蓄卡、信用卡</div>
				                	</div>
				                </div>
				            </div>
				        </label>
				    </div>
	            </div>
            </div>
		</div>
		<div class="row ptb20">
			<div class="col-xs-12">
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