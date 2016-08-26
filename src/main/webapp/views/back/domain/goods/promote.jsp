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
    <!-- 查询条件  开始 -->
	<table>
		<tbody>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>店铺名称：</span><input id="sc_shopName" class="easyui-textbox width120" /></td>
				<td class="td_pad"><button id="clearsSearch" onclick="clearSearch()" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>商品名称：</span><input id="sc_goodsName" class="easyui-textbox width120" /></td>
				<td class="td_pad"><button id="search" onclick="doSearch()" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->
	<!-- 表格  开始 -->
	<div id='datagrid_div'>
		<table id="datagrid"></table>
	</div>
	<!-- 表格  结束 -->
    
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/js/back/goods/promote.js" title="v"></script>
</body>
</html>