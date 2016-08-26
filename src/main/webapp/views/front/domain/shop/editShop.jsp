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
	<input type="hidden" id="shopId" value="${shop.id}">
	<input type="hidden" id="antiqueCityId" value="${antiqueCityId}">
	<input type="hidden" id="isPcAllowedValue" value="${shop.isPcUploadAllowed}">
	<div class="c_0100_3 information_border" style="padding:10px;margin-top:60px;">
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>店铺名称:<br/><input type="text" id="name"  style="width:95%;" value="${shop.name}"/></nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>店铺介绍:<br/><textarea  id="remark"  style="width:95%;" rows="5">${shop.remark}</textarea></nobr>
			</div>
             <div class="weui_cell_hd"><label class="myLabel">所属商圈:</label></div>
             <div class="weui_cell_bd weui_cell_primary">
             	<select id="antiqueCity" class="selectpicker">
					<c:forEach var="antiqueCity" items="${antiqueCityList}">
						<option value="${antiqueCity.id}">${antiqueCity.name}</option>
					</c:forEach>
				</select>
             </div>
             
             <div class="weui_cell_hd"><label class="myLabel">是否允许电脑端代传藏品:</label></div>
             <div class="weui_cell_bd weui_cell_primary">
             	<select id="isPcAllowed" class="selectpicker">
					<option value="2">是</option>
					<option value="1">否</option>
				</select>
             </div>
             
			 <div class="ipt_item_div" style="font-size:14px;margin:20px 0px;">
					<label>上传店铺logo：</label>
					<input type="hidden" id="instLogo" value="${ap.picPaths}">
					<img src="${ap.picPaths}" class="picurl">
					<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
				    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
					<button id="browse" type="button" class="weui_btn weui_btn_mini weui_btn_primary" >上传店铺logo</button>
		</div>
		<div id="upload_outer_div"><!-- 上传文件进度展示 --></div>
	  </div>
	 <div class="save bottomFix" >
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="editShop();return false;">保存</a></li>
		</ul>
	  </div>
	  
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
	<script type="text/javascript" src="/js/front/shop/editShop.js" title="v"></script>
	
</body>
</html>
