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
	    <form id="goodsPictureForm">
			<table>
				<tr class="tr_ht">
					<td>商品所属类型:</td>
					<td><input id="shopType" class="easyui-combobox"/></td>
					<td>商店商品(只能选择商店店铺,不能选择批发城店铺),批发城商品(只能选择批发城店铺,不能选择商店店铺)</td>
				</tr>
				<tr class="tr_ht">
					<td>商店店铺名称:</td>
					<td><input id="shopId" class="easyui-combobox" data-options="readonly:true"/></td>
				</tr>
				<tr class="tr_ht">
					<td>批发城店铺名称:</td>
					<td><input id="wholesaleId" class="easyui-combobox" data-options="readonly:true"/></td>
				</tr>
				<tr class="tr_ht">
					<td>商品类型:</td>
					<td><input id="goodsType" class="easyui-combobox"/></td>
				</tr>
				<tr class="tr_ht">
					<td>商品名称:</td>
					<td><input id="goodsName" class="easyui-textbox" data-options="required:true"/></td>
				</tr>
				<tr class="tr_ht">
					<td>商品价格:</td>
					<td><input id="shopPrice" class="easyui-textbox" data-options="prompt:'不填默认为议价'"/></td>
				</tr>
				<tr class="tr_ht">
					<td>商品状态:</td>
					<td><input id="mainStatus" class="easyui-combobox" data-options="required:true"/></td>
				</tr>
				<tr class="tr_ht">
					<td>商品介绍:</td>
					<td><input id="goodsDescription" class="easyui-textbox" data-options="required:true,multiline:true,width:300,height:100"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="ipt_item_div" >
		<label>上传藏品图片(第一张默认设置为封面)：</label>
		<input type="hidden" id="instLogo" value="${ap.picPaths}">
		<img src="${ap.picPaths}" class="picurl">
		<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
	    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
		<button id="browse" type="button" class="button button-primary button-rounded button-small" style="color:#920808;">上传藏品图片</button>
	</div>
	<!-- 上传文件进度展示 开始   -->
	<div id="upload_outer_div" class="fieldset"></div>
    <div style="margin:5px 5px 0px 5px;float:left">
		<button id="addGoods" onclick="addGoods();return false;" class="button button-primary button-rounded button-small">确定保存</button>
	</div>
	<div style="margin:5px 5px 0px 5px;float:left">
		<button id="returnBack" onclick="returnBack();return false;" class="button button-primary button-rounded button-small">取消返回</button>
	</div>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
<script src="/js/front/common.js" title="v"></script>
<script type="text/javascript" src="/js/back/goods/goodsPicture.js" title="v"></script>
</body>
</html>