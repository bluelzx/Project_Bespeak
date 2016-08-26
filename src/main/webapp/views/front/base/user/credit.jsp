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
<link rel="stylesheet" type="text/css" href="/css/front/news.css" title="v" />
</head>
<body style="background-color:#f5f5f5;">
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<input type="hidden" id="shopId" value="${shopId}">
	<input type="hidden" id="userId" value="${userId}">
	<div class="pz_main" style="margin-top:50px;">
		<div class="c_0100_19">
			 <div class="slide_sale slide_teacher c_0100_3" style="padding:0;text-align: center;" id="slide_sale">
				<div class="cytopdiv">
					<span id="shop1" class="cytopdiv1 pointer" style="color:white;" onclick="creditShop()">作为商家</span>
					<span id="shop2" class="cytopdiv2 pointer" onclick="creditCustomer()">作为客户</span>
					<span id="customer1" class="cytopdiv1 pointer"  style="display:none;background: white;color:black" onclick="creditShop()">作为商家</span>
					<span id="customer2" class="cytopdiv2 pointer" style="display:none;background: #5F6367;color:white;" onclick="creditCustomer()">作为客户</span>
				</div>
			</div>
		</div>
		<div class="c_0100_15">
			<div class="c_0100_15">
		    	<ul id="credit">
		    		<li id="shop">
		    			<c:if test="${!empty credit}" >
			    			<div class="teacher_list pointer">
			    				<div>
									<span class="btn" style="border: none;" >商家好评率:</span>
									<c:if test="${!empty credit.goodRateB}">
										${credit.goodRateB}%
									</c:if>
									<c:if test="${empty credit.goodRateB}">
										0.00%
									</c:if>
									<span class="btn" style="border: none;" >商家退货率:</span>
									<c:if test="${!empty credit.backRateB}">
										${credit.backRateB}%
									</c:if>
									<c:if test="${empty credit.backRateB}">
										0.00%
									</c:if>
									<br/>
									<span class="btn" style="border: none;" >商家成交率:</span>
									<c:if test="${!empty credit.doneRateB}">
										${credit.doneRateB}%
									</c:if>
									<c:if test="${empty credit.doneRateB}">
										0.00%
									</c:if>
									<span class="btn " style="border: none;" >商家违约率:</span>
									<c:if test="${!empty credit.breakRateB}">
										${credit.breakRateB}%
									</c:if>
									<c:if test="${empty credit.breakRateB}">
										0.00%
									</c:if>
								</div>
							</div>
						</c:if>
						<c:if test="${empty credit}">
							 <div id="resultTip" class="resultTip">暂无数据</div>
						</c:if>
		    		</li>
		    		<li style="display:none;" id="customer">
		    			<c:if test="${!empty credit}">
		    				<div class="teacher_list pointer">
								<div>
									<span class="btn " style="border: none;" >客户好评率:</span>
									<c:if test="${!empty credit.goodRateC}">
										${credit.goodRateC}%
									</c:if>
									<c:if test="${empty credit.goodRateC}">
										0.00%
									</c:if>
									<span class="btn " style="border: none;" >客户退货率:</span>
									<c:if test="${!empty credit.backRateC}">
										${credit.backRateC}%
									</c:if>
									<c:if test="${empty credit.backRateC}">
										0.00%
									</c:if>
									<br/>
									<span class="btn " style="border: none;" >客户成交率:</span>
									<c:if test="${!empty credit.doneRateC}">
										${credit.doneRateC}%
									</c:if>
									<c:if test="${empty credit.doneRateC}">
										0.00%
									</c:if>
									<span class="btn " style="border: none;" >客户违约率:</span>
									<c:if test="${!empty credit.breakRateC}">
										${credit.breakRateC}%
									</c:if>
									<c:if test="${empty credit.breakRateC}">
										0.00%
									</c:if>
								</div>
							</div>
						</c:if>
						<c:if test="${empty credit}">
							 <div id="resultTip" class="resultTip">暂无数据</div>
						</c:if>
					</li>
		        </ul>
		    	
	        </div>
	    </div>
	    <div id="resultTip" class="resultTip frontHide"></div>
		<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
	</div>
	  <div style="text-align: center;"><span>每隔一个月可以重置一次店铺信誉</span></div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	<c:if test="${currentUserId == userId}">
		<div class="save bottomFix">
			<ul>
				<li><a href="javascript:;" class="a_say" id="" onclick="resetCredit('${credit.id}');return false;">重置</a></li>
			</ul>
		</div>
	</c:if>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/js/front/user/credit.js" title="v"></script>

</body>
</html>
