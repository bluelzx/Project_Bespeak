<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/auction.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/userCenter.css" title="v" />
<link rel="stylesheet" type="text/css" href="/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css"/>
</head>
<body style="background-color:#f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="pz_main" style="margin-top:50px;padding:10px;">
	 		<input type="hidden" id="id" value="${userFund.id}">
	 		<input type="hidden" id="isRealAuth" value="${user.isRealAuth}">
	 		<input type="hidden" id="fee" value="${sysDict.dictValue}">
		 		<div class="weui_cells weui_cells_form">
		 		<!-- <ul class="information">
					<li> -->
						<!-- <span style="font-size:14px;">银行卡号:</span> -->
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">银行卡号:</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <select id="bankCard" style="width:80%">
									<c:if test="${!empty userFund.bankCard1}">
										<option value="${userFund.bankCard1}">${userFund.bankCard1}</option>
									</c:if>
									<c:if test="${!empty userFund.bankCard2}">
										<option value="${userFund.bankCard2}">${userFund.bankCard2}</option>
									</c:if>
									<c:if test="${!empty userFund.bankCard3}">
										<option value="${userFund.bankCard3}">${userFund.bankCard3}</option>
									</c:if>
									<c:if test="${!empty userFund.bankCard4}">
										<option value="${userFund.bankCard4}">${userFund.bankCard4}</option>
									</c:if>
									<c:if test="${!empty userFund.bankCard5}">
										<option value="${userFund.bankCard5}">${userFund.bankCard5}</option>
									</c:if>
								</select>
			                </div>
			            </div>
					<!-- </li>
					
					<li> -->
						<!-- <span style="font-size:14px;">金额:</span>
						<br/>
						<input type="text" id="applyMoney"  placeholder="请输入需要提现的金额" onblur="sumMoney();return false;"/> -->
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">金额:</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="text" id="applyMoney"  placeholder="请输入需要提现的金额" onblur="sumMoney();return false;">
			                </div>
			            </div>
					<!-- </li>
						
					<li> -->
						<!-- <span style="font-size:14px;">实提金额:</span>
						<input type="text" id="applyRealMoney" readonly="readonly" /> -->
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">实提金额:</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="text" id="applyRealMoney" readonly="readonly">
			                </div>
			            </div>
					<!-- </li>
					
					<li> -->
						<!-- <span style="font-size:14px;">支付密码:</span>
						<input type="password" id="payPassword" /> -->
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">支付密码:</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="password" id="payPassword" placeholder="请输入您的支付密码">
			                </div>
			            </div>
					<!-- </li>
				</ul> -->
				</div>
				<div class="weui_cells_tips">
					<span style="font-size:14px;">提现时间：工作日24小时内到账！<span style="color:red;">（节假日到账时间顺延）</span></span>
					<span style="font-size:14px;">每日限额50000.00元</span></br>
					<span style="font-size:14px;">提现时，银行扣除${sysDict.dictValue}的手续费。</span></br>
					<span style="font-size:14px;">提现金额必须大于100.00元</span><a href="javascript:lh.jumpR('/payPasswordFind');" style="font-size:14px;float:right;color:red;">忘记支付密码</a>
				</div>
			<div class="pz_down">
				<div class="c_0100_9"></div>
			</div>
			<div class="save bottomFix" >
				<ul>
					<li><a href="javascript:;" class="a_say" onclick="addAccountLog();return false;">确认提现</a></li>
				</ul>
			</div>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script src="/js/front/user/applyWithdrawals.js" title="v"></script>
</body>
</html>
