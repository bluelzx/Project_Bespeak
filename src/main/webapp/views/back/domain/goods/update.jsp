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
    	<input id="goodsId" type="hidden" />
    	<input id="id" type="hidden" value="${id}" />
		<table>
			<tr>
				<td>店铺：</td>
				<td><input id="shop" class="easyui-combobox"/></td>
				<td>商品名称:</td>
				<td><input id="goodsName" class="easyui-textbox"/></td>
				<td>本店售价:</td>
				<td><input id="shopPrice" class="easyui-numberbox"/></td>
				<td>促销价格:</td>
				<td><input id="promotePrice" class="easyui-numberbox"/></td>
			</tr>
		</table>
	</div>
    <div style="margin:5px 5px 0px 5px;float:left">
		<button id="addGoods" onclick="addGoods();return false;" class="button button-primary button-rounded button-small">确定保存</button>
	</div>
	<div style="margin:5px 5px 0px 5px;float:left">
		<button id="returnBack" onclick="returnBack();return false;" class="button button-primary button-rounded button-small">取消返回</button>
	</div>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/js/back/goods/addOrUpdateGoods.js" title="v"></script>
</body>
</html>