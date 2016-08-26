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
</head>
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<input type="hidden" id="userId" value="${user.id}">
	<div class="c_0100_3 information_border" style="padding:10px;margin-top:60px;">
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>用户名:<br/><input type="text" id="name"  style="width:95%;" value="${user.username}"/></nobr>
			</div>
			 <div class="ipt_item_div" style="font-size:14px;margin:20px 0px;">
					<label>上传用户头像：</label>
					<input type="hidden" id="instLogo" value="${ap.picPaths}">
					<img src="${ap.picPaths}" class="picurl">
					<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
				    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
					<button id="browse" type="button" class="weui_btn weui_btn_mini weui_btn_primary" >上传用户头像</button>
		</div>
		<div id="upload_outer_div"><!-- 上传文件进度展示 --></div>
	  </div>
	 <div class="save bottomFix" >
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="addUser();return false;">保存</a></li>
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
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/user/editUser.js" title="v"></script>
	
</body>
</html>
