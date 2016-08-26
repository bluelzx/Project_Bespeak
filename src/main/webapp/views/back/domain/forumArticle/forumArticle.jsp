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
				<td class="td_pad"><span>名称：</span><input id="title" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>内容：</span><input id="content" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>描述：</span><input id="description" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>类型：</span><input id="sc_type" class="easyui-combobox width120" /></td>
				<td class="td_pad"><button id="searchLoanBusiness" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
				<td class="td_pad"><button id="clearsSearchLoanBusiness" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<div class="clear-both height10"></div>
	<!-- 查询条件 结束 -->
	<div class="fl_opt_div">
		<button id="batchDelete" onclick="batchDelete();return false;"  class="button button-primary button-rounded button-small" >批量删除</button>
		<button id="jumpToAddForumArticle" onclick="jumpToAddForumArticle();return false;" class="button button-primary button-rounded button-small">添加</button>
		<button id="batchRecover"  onclick="batchRecover();return false;" class="hide button button-primary button-rounded button-small">批量恢复</button>
		<button id="batchThoroughDelete" onclick="batchThoroughDelete();return false;" class="hide button button-primary button-rounded button-small">批量彻底删除</button>
		<button id="forumLink" onclick="jumpToForum();return false;" class="button button-royal button-rounded button-small">论坛</button>
		<button id="forumMemberLink" onclick="jumpToForumMember();return false;" class="button button-royal button-rounded button-small">论坛用户</button>
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
<script type="text/javascript" src="/js/back/forumArticle/forumArticle.js" title="v"></script>
</body>
</html>