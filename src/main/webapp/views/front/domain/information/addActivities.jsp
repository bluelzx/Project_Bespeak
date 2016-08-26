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
<link rel="stylesheet" type="text/css" href="/third-party/mobiscroll/css/mobiscroll.custom-2.6.2.min.css"/> 
</head>
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
		<div class="weui_cells weui_cells_form" style="margin-top:50px;">
		 <!-- <ul class="information" style="padding:5px;margin-top:50px;">
	  		<li> -->
				<!-- <span>标题:</span><br/>
				<input type="text" id="title" style="width:85%;" /> -->
				<div class="weui_cell">
	                <div class="weui_cell_hd"><label class="myLabel">标题:</label></div>
	                <div class="weui_cell_bd weui_cell_primary">
	                    <input class="weui_input" type="text" id="title" placeholder="请输入标题">
	                </div>
	            </div>
			<!-- </li>
	  		<li> -->
				<!-- <span>描述:</span><br/>
				<input type="text" id="description" style="width:85%" /> -->
				<div class="weui_cell">
	                <div class="weui_cell_hd"><label class="myLabel">描述:</label></div>
	                <div class="weui_cell_bd weui_cell_primary">
	                    <input class="weui_input" type="text" id="description" placeholder="请输入描述">
	                </div>
	            </div>
			<!-- </li>
	  		<li> -->
				<!-- <span>发起方:</span><br/>
				<input type="text" id="institution" style="width:85%" /> -->
				<div class="weui_cell">
	                <div class="weui_cell_hd"><label class="myLabel">发起方:</label></div>
	                <div class="weui_cell_bd weui_cell_primary">
	                    <input class="weui_input" type="text" id="institution" placeholder="请输入发起方">
	                </div>
	            </div>
			<!-- </li>
	  		<li> -->
				<!-- <span>活动地址:</span><input type="text" id="address" style="width:85%" /> -->
				<div class="weui_cell">
	                <div class="weui_cell_hd"><label class="myLabel">活动地址:</label></div>
	                <div class="weui_cell_bd weui_cell_primary">
	                    <input class="weui_input" type="text" id="address" placeholder="请输入活动地址">
	                </div>
	            </div>
			<!-- </li>
			<li> -->
			    <!-- <span>活动开始时间:</span>
			    <input size="20" type="text" id="startDate"/> -->
			    <div class="weui_cell">
	                <div class="weui_cell_hd"><label class="myLabel">活动开始时间:</label></div>
	                <div class="weui_cell_bd weui_cell_primary">
	                    <input class="weui_input" type="text" id="startDate" placeholder="请选择活动开始时间">
	                </div>
	            </div>
			<!-- </li>
			<li> -->
			    <!-- <span>活动结束时间:</span>
			    <input size="20" type="text" id="endDate" /> -->
			     <div class="weui_cell">
	                <div class="weui_cell_hd"><label class="myLabel">活动结束时间:</label></div>
	                <div class="weui_cell_bd weui_cell_primary">
	                    <input class="weui_input" type="text" id="endDate" placeholder="请选择活动结束时间">
	                </div>
	            </div>
			<!-- </li>
		  <li> -->
		    <!--  <span> 内容:</span>
		 	 <textarea style="width:100%;height:300px;" id="content"></textarea> -->
		 	 <div class="weui_cell">
	                <div class="weui_cell_hd"><label class="myLabel">内容</label></div>
	          </div>
		 	 <div class="weui_cell">
                <div class="weui_cell_bd weui_cell_primary">
                    <textarea class="weui_textarea" placeholder="请输入内容" rows="3" id="content"></textarea>
                    <div class="weui_textarea_counter"><span>0</span>/200</div>
                </div>
            </div>
		 <!--  </li>
		  <li> -->
				<span>活动封面：</span>
				<input type="hidden" id="instLogo" value="${ap.picPaths}">
				<img src="${ap.picPaths}" class="picurl">
				<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
			    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
				<button id="browse" type="button" class="weui_btn weui_btn_mini weui_btn_primary" >上传活动封面</button>
		<!--  	</li>
	  </ul> -->
	 </div>
	  <div id="upload_outer_div"></div>
	  <div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	  <div class="save bottomFix" id="antiqueCityShop">
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="addActivities();return false;">发布</a></li>
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
	<script type="text/javascript" src="/third-party/mobiscroll/js/mobiscroll.custom-2.6.2.min.js"></script>
	<script type="text/javascript" src="/third-party/mobiscroll/dev/js/mobiscroll.core-2.6.2-zh.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/information/addActivities.js" title="v"></script>
</body>
</html>
