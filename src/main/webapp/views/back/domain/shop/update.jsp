<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_back_css.htm"%>
<link rel="STYLESHEET" type="text/css" href="/css/back/back.css" title="v"/>
</head>
<body>
    <div class="">
    	<input id="shopId" type="hidden" />
    	<input id="id" type="hidden" value="${id}" />
		<table>
			<tr>
				<td>店铺名称:</td>
				<td><input id="name" class="easyui-textbox"/></td>
				<td>用户：</td>
				<td><input id="user" class="easyui-combobox"/></td>
			</tr>
		</table>
		<div class="ipt_item_div" style="font-size:14px;margin:20px 0px;">
					<label>上传店铺logo：</label>
					<input type="hidden" id="instLogo" value="${ap.picPaths}">
					<img src="${ap.picPaths}" class="picurl">
					<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
				    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
					<button id="browse" type="button" class="button button-primary button-rounded button-small" style="color:#920808;">上传店铺logo</button>
		</div>
		<div id="upload_outer_div"><!-- 上传文件进度展示 --></div>
	</div>
    <div style="margin:5px 5px 0px 5px;float:left">
		<button id="addShop" onclick="addShop();return false;" class="button button-primary button-rounded button-small">确定保存</button>
	</div>
	<div style="margin:5px 5px 0px 5px;float:left">
		<button id="returnBack" onclick="returnBack();return false;" class="button button-primary button-rounded button-small">取消返回</button>
	</div>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
<script type="text/javascript" src="/js/back/shop/addOrUpdateShop.js" title="v"></script>
</body>
</html>