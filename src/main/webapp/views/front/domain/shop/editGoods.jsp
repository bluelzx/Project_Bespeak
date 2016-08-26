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
<style type="text/css">
	.c_0100_3 li{
		float: none;
  		width: 100%;
	}
</style>
</head>
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<input type="hidden" id="id" value="${goods.id}">
	<input type="hidden" id="moduleId" value="${goods.moduleId}">
	 <div class="weui_cells weui_cells_form" style="margin-top:50px;">
			<div class="weui_cell">
	            <div class="weui_cell_hd"><label>产品名称:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="goodsName" placeholder="请填写产品名称" value="${goods.goodsName}">
	            </div>
	        </div>
			<div class="weui_cell"><label>产品描述:</label></div>
			<div class="weui_cell">
	             <textarea class="weui_textarea" placeholder="请填写产品描述" rows="3" id="goodsDescription">${goods.goodsDescription}</textarea>
	             <!-- <div class="weui_textarea_counter"><span>0</span>/200</div> -->
	        </div>
			<c:if test="${goods.moduleId != 5}">
				<div class="weui_cell">
		            <div class="weui_cell_hd"><label>售价:<span style="color:red;">(如果不填,则为议价)</span></label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="shopPrice" placeholder="请填写产品售价" value="${goods.shopPrice}">
		            </div>
		        </div>
			</c:if>
			<c:if test="${goods.moduleId == 5}">
				<div class="weui_cell">
		            <div class="weui_cell_hd"><label>代理价:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="agentPrice" placeholder="请填写产品售价"  value="${goods.agentPrice}">
		            </div>
		        </div>
			</c:if>
	</div>
	 <div class="save bottomFix" >
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="addGoods();return false;">保存</a></li>
		</ul>
	  </div>
	  <input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
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
	<script type="text/javascript" src="/js/front/shop/editGoods.js" title="v"></script>
	
</body>
</html>
