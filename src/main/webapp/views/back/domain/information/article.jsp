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
	<input type="hidden" id="typeId" value="${typeId}">
    <!-- 查询条件  开始 -->
   <table>
		<tbody>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>标题:</span><input id="title" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>描述:</span><input id="description" class="easyui-textbox width120" /></td>
				<c:if test="${typeId != 41}">
					<td class="td_pad"><span>是否推荐:</span><input id="isRecommend" class="easyui-combobox width120" /></td>
				</c:if>
				<c:if test="${typeId == 41}">
				<td class="td_pad"><span>发起方:</span><input id="institution" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>活动地址:</span><input id="address" class="easyui-textbox width120" /></td>
				</c:if>
				<td class="td_pad"><button id="searchLoanBusiness" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<c:if test="${typeId == 41}">
				<td class="td_pad"><span>活动开始时间:</span><input id="startDate" class="easyui-datebox width120" data-options="editable:false"/></td>
				<td class="td_pad"><span>活动结束时间:</span><input id="endDate" class="easyui-datebox width120" data-options="editable:false"/></td>
				</c:if>
				<c:if test="${typeId != 41}">
					<td class="td_pad"><span>是否头条:</span><input id="isTop" class="easyui-combobox width120" /></td>
				</c:if>
				<td class="td_pad"><span>内容:</span><input id="content" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>状态:</span><input id="mainStatus" class="easyui-combobox width120" /></td>
				<td class="td_pad"><button id="clearsSearchLoanBusiness" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<div class="clear-both height10"></div>
	<!-- 查询条件 结束 -->
	<div class="fl_opt_div">
		<button id="batchDelete" onclick="batchDelete();return false;"  class="button button-primary button-rounded button-small" >批量删除</button>
		<button id="jumpToAddArticle" onclick="jumpToAddArticle();return false;" class="button button-primary button-rounded button-small">添加</button>
		<button id="batchRecover"  onclick="batchRecover();return false;" class="hide button button-primary button-rounded button-small">批量恢复</button>
		<button id="batchThoroughDelete" onclick="batchThoroughDelete();return false;" class="hide button button-primary button-rounded button-small">批量彻底删除</button>
		<c:if test="${typeId == 41}">
			<button id="newsLink" onclick="jumpToNews();return false;" class="button button-royal button-rounded button-small">新闻资讯</button>
		</c:if>
		<c:if test="${typeId != 41}">
			<button id="activityLink" onclick="jumpToActivity();return false;" class="button button-royal button-rounded button-small">展会活动</button>
		</c:if>
	</div>
	<div class="fr_opt_div">
		<button id="showTrash" onclick="showTrash();return false;" class="button button-primary button-rounded button-small">回收站</button>
		<button id="returnBack" onclick="returnBack();return false;" class="hide button button-primary button-rounded button-small">返回</button>
	</div>
	<!-- 表格  开始 -->
	<div id='datagrid_div'>
		<table id="datagrid"></table>
	</div>
	<!-- 表格  结束 -->
    
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/js/back/information/article.js" title="v"></script>
</body>
</html>