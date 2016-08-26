<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%-- <%@ include file="/views/common/common_css.htm"%> --%>
<%@ include file="/views/common/common_front_wpk_css.htm"%>
<%-- 新增拍卖专场前选择专场类型- AuctionProfessionAction:/ap/page/typeChoose --%>
</head>
<body class="bg_gray">
	<div class="container-fluid">
		<div class="row auction_title">
			<div class="col-xs-2" onclick="lh.back();">
				<i class="icon-angle-left icon-3x"></i>
			</div>
			<div class="col-xs-8 pt10 plr0 text-center">
				<span class="fs16">诚信保证金</span>
			</div>
		</div>
		<div class="row mt10">
            <div class="col-xs-12 bg_white">
	            <div class="bdb ptb10 fs16">诚信保证金（已交纳保证金：${creditMoney}<c:if test="${null == creditMoney}">0.00</c:if>元）</div>
            </div>
		</div>
		
		<div class="row mt10">
            <div class="col-xs-12 bg_white" style="line-height: 35px;">
	            <div class="col-xs-12 plr0 bdb">
		            <div class="col-xs-3 pt10 plr0">保证金：</div>
		            <div class="col-xs-9 pt10 plr0">
		                <span class="fs18 red">￥<fmt:formatNumber type="number" value="${leftMoney}" maxFractionDigits="2" minFractionDigits="2"/>元</span>
		            </div>
		            <!-- <div class="gray fs10 text-right">（“流拍”或“确认收货”后退还）</div> -->
	            </div>
            </div>
		</div>
		<div class="row">
            <div class="col-xs-12 bg_white">
	            <div class="col-xs-12 plr0 bdb ptb10 mb0">
				    <div class="weui_cells_checkbox">
				        <label class="weui_check_label" for="s11" style="display: flex;">
				            <div class="weui_cell_hd pt8">
				                <input type="radio" class="weui_check" name="checkbox1" id="s11" value="payWay_account" onchange="togglePayPass();">
				                <i class="weui_icon_checked"></i>
				            </div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <div class="col-xs-12 plr7">
				                	<div class="col-xs-2 plr0">
				                		<img class="img-responsive" src="/images/front/pay_01.png">
				                	</div>
				                	<c:choose>
					                	<c:when test="${empty payPass}">
					                		<div class="col-xs-7 plr7">
						                		<div class="gray fs16">支付密码未设置</div>
						                		<div class="gray">可用余额：￥0.00</div>
						                	</div>
						                	<div class="col-xs-3 pt8 plr0 gray" onclick="jumpToPaypass();">
							                	<div class="col-xs-6 plr0 pt5"><span class="blue_tint">开通</span></div>
							                	<div class="col-xs-6 plr0">&nbsp;&nbsp;<i class="icon-angle-right icon-2x"></i></div>
						                	</div>
					                	</c:when>
					                	<c:otherwise>
				                			<c:choose>
							                	<c:when test="${!empty moneyLack}">
							                		<div class="col-xs-7 plr7">
								                		<div class="gray fs16">余额不足</div>
								                		<div class="gray">可用余额：￥<fmt:formatNumber type="number" value="${avaliableMoney}" maxFractionDigits="2" minFractionDigits="2"/></div>
								                	</div>
								                	<div class="col-xs-3 pt8 plr0 gray" onclick="jumpToCharge();">
									                	<div class="col-xs-6 plr0 pt5"><span class="blue_tint">充值</span></div>
									                	<div class="col-xs-6 plr0">	&nbsp;&nbsp;<i class="icon-angle-right icon-2x"></i></div>
								                	</div>
							                	</c:when>
							                	<c:otherwise>
							                		<div class="col-xs-7 plr7">
								                		<div class="gray fs16">余额支付</div>
								                		<div class="gray">可用余额：￥<fmt:formatNumber type="number" value="${avaliableMoney}" maxFractionDigits="2" minFractionDigits="2"/></div>
								                	</div>
							                	</c:otherwise>
						                	</c:choose>
					                	</c:otherwise>
				                	</c:choose>
				                </div>
				            </div>
				        </label>
				    </div>
	            </div>
	            
	            <div class="col-xs-12 plr0 bdb ptb10 mb0 frontHide" id="payPassDiv">
	            	<div class="col-xs-12 plr7">
	                	<div class="col-xs-4 plr0">
	                		<div class="fs16" style="position: relative;top: 5px;text-align: right;">支付密码：</div>
	                	</div>
	                	<div class="col-xs-8 plr7">
	                		<div class="gray fs10"><input id="payPass" type="text" class="form-control" placeholder="******"/></div>
	                	</div>
	                </div>
	            </div>
	            
	            <div class="col-xs-12 plr0 bdb ptb10 mb0">
				    <div class="weui_cells_checkbox">
				        <label class="weui_check_label" for="s12" style="display: flex;">
				            <div class="weui_cell_hd pt8">
				                <input type="radio" class="weui_check" name="checkbox1" id="s12" checked="checked" value="payWay_pay" onchange="togglePayPass();">
				                <i class="weui_icon_checked"></i>
				            </div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <div class="col-xs-12 plr7">
				                	<div class="col-xs-2 plr0">
				                		<img class="img-responsive" src="/images/front/pay_02.png">
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
	            
	           <!--  <div class="col-xs-12 plr0 bdb ptb10 mb0">
				    <div class="weui_cells_checkbox">
				        <label class="weui_check_label" for="s13" style="display: flex;">
				            <div class="weui_cell_hd pt8">
				                <input type="radio" class="weui_check" name="checkbox1" id="s13" checked="checked">
				                <i class="weui_icon_checked"></i>
				            </div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <div class="col-xs-12 plr7">
				                	<div class="col-xs-2 plr0">
				                		<img class="img-responsive" src="/images/front/pay_03.png">
				                	</div>
				                	<div class="col-xs-10 plr7">
				                		<div class="fs16">银行卡支付</div>
				                		<div class="gray fs10">支持储蓄卡、信用卡</div>
				                	</div>
				                </div>
				            </div>
				        </label>
				    </div>
	            </div> -->
	            
            </div>
		</div>
		<div class="row ptb20">
			<div class="col-xs-12">
				<a href="javascript:;" onclick="payCreditMoney();return false;" class="weui_btn weui_btn_primary">安全支付</a>
			</div>
		</div>
	</div>
	
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script>lh.param = ${paramJson}</script>
	<script src="/js/front/fund/creditMoney.js" title="v"></script>
	
</body>
</html>