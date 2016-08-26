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
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<div class="c_0100_3 information_border" style="padding:10px;margin-top:50px;">
			<div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">快递公司：</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <select id="express">
						<c:forEach var="e" items="${expressList}">
							<option value="${e.id}">${e.briefName}</option>
						</c:forEach>
					</select>
                </div>
            </div>
			<div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">快递单号：</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="expressOrder" value="${orderInfo.expressOrder}" placeholder="(必填)">
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">收货人：</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="username" value="${orderInfo.username}" placeholder="(必填)">
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">联系电话：</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="phone" value="${orderInfo.phone}" placeholder="(必填)">
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">收货人详细地址：</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="address" value="${orderInfo.address}" placeholder="(必填)">
                </div>
            </div>
			
	  		<%-- <div style="font-size:14px;margin:5px 0px;">
				<nobr>快递单号:<br/><input type="text" id="goodsAttr" style="width:95%;"/></nobr>
			</div>
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>收货人:<br/><input type="text" value="${orderInfo.username}" readonly="readonly" style="width:95%;"/></nobr>
			</div>
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>联系电话:<br/><input type="text" value="${orderInfo.phone}" readonly="readonly" style="width:95%;"/></nobr>
			</div>
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>收货人详细地址:<br/><input type="text" value="${orderInfo.address}" readonly="readonly" style="width:95%;"/></nobr>
			</div> --%>
	  </div>		
	 <div class="save bottomFix" >
		<ul>
			<li><a href="javascript:;" class="a_say" onclick="sendGoods('${orderGoodsId}');return false;">保存</a></li>
		</ul>
	  </div>
	  <input type="hidden" id="orderGoodsId" value="${orderGoodsId}">
	  <input type="hidden" id="orderId" value="${orderId}">
	  <input type="hidden" value="${r}" id="r"/> 	
	  <input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/user/courierNum.js" title="v"></script>
	
</body>
</html>
