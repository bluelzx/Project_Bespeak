<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_back_css.htm"%>
<link rel="STYLESHEET" type="text/css" href="/css/back/back.css"
	title="v" />
</head>
<body>
	<!-- 查询条件  开始 -->
	<table id="mainQueryTable">
		<tbody>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>用户名：</span><input id="sc_username"
					role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>真实姓名：</span><input id="sc_realName"
					role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>用户编号：</span><input id="sc_serial"
					role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><button id="clearsSearch"
						onclick="clearSearch()"
						class="button button-primary button-rounded button-small">重
						置</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>控制类型：</span><input id="sc_controlTypeId"
					role="combobox" data-options="editable:false"
					class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>有效时间-从：</span><input
					id="sc_validDateFrom" role="datebox" data-options="editable:false"
					class="domain-input easyui-datebox width120" /></td>
				<td class="td_pad"><span>至：</span><input id="sc_validDateTo"
					role="datebox" data-options="editable:false"
					class="domain-input easyui-datebox width120" /></td>
				<td class="td_pad"><button id="search" onclick="doSearch()"
						class="button button-primary button-rounded button-small">查
						询</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->

	<div class="clear-both height10"></div>
	<div id="opt_outer_div">
		<div class="fl_opt_div">
			<button role="opt_1" id="btn_batchDelete"
				onclick="lh.commonBatchDelete()"
				class="button button-primary button-rounded button-small">批量删除</button>
			<button role="opt_1" onclick="addMainObj()"
				class="button button-primary button-rounded button-small">添加用户控制</button>
			<button id="userMoneyLink" onclick="jumpToUserMoney()"
				class="button button-royal button-rounded button-small">用户资金</button>
			<button id="userInfoLink" onclick="jumpToUserInfo()"
				class="button button-royal button-rounded button-small">用户信息</button>
			<button role="opt_2" id="btn_batchRecover"
				onclick="lh.commonBatchRecover()"
				class="hide button button-primary button-rounded button-small">批量恢复</button>
			<button role="opt_2" id="btn_throughDelete"
				onclick="lh.commonBatchThoroughDelete()"
				class="hide button button-primary button-rounded button-small">彻底删除</button>
		</div>
		<div class="fr_opt_div">
			<button role="opt_1" id="btn_trash" onclick="lh.commonShowTrash()"
				class="button button-primary button-rounded button-small">回收站</button>
			<button role="opt_2" id="btn_trashBack"
				onclick="lh.commonReturnBack()"
				class="hide button button-primary button-rounded button-small">返回</button>
		</div>
	</div>
	<!-- 表格  开始 -->
	<div id='datagrid_div'>
		<table id="datagrid"></table>
	</div>
	<!-- 表格  结束 -->

	<div id="mainObjWindiv" style="display: none;">
		<div id='mainObjWin' class="easyui-window" title="用户控制信息"
			style="width: 640px;"
			data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr id="winSearchTr" class="tr_ht" align="left">
							<td class="td_pad"><span>用户查询：</span><input role="textbox"
								id="f_serialSearch" class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad" colspan="2"><input type="button"
								onclick="searchUser();"
								class="button button-primary button-rounded button-small"
								value="查询" /> <input type="button" onclick="jumpToUserInfo()"
								class="button button-royal button-rounded button-small"
								value="用户信息" /> <span class="colorGray">
									请输入用户编号（可从用户信息表格中复制）</span></td>
						</tr>
						<tr id="winSearchDivisionTr" class="tr_ht" align="left">
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span class="colorGray">用户编号：</span> <input role="textbox"
								id="f_serial" data-options="readonly:true"
								class="domain-input easyui-textbox width140" /> <input id="f_userId" role="textbox"
								type="hidden" class="domain-input easyui-textbox"   /> <input id="f_shopId"  type="hidden" /></td>
							<td class="td_pad"><span class="colorGray">用户名：</span><input
								id="f_username" data-options="readonly:true"
								class="easyui-textbox width140" /></td>
							<td class="td_pad"><span class="colorGray">真实姓名：</span><input
								id="f_realName" data-options="readonly:true"
								class="easyui-textbox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>控制类型：</span><input role="combobox"
								id="f_controlTypeId" 
								class="domain-input easyui-combobox width140" data-options="required:true"  /></td>
							<td class="td_pad"><span>有效时间-从：</span><input role="datebox"
								id="f_startDate" data-options="editable:false"
								class="domain-input easyui-datetimebox width140" /></td>
							<td class="td_pad"><span>至：</span><input id="f_endDate" role="datebox"
								data-options="editable:false"
								class="domain-input easyui-datetimebox width140" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<div class="inline-center mgV40">
					<button id="mainObjSave" onclick="saveMainObj()" class="button button-primary button-rounded button-small">保存</button>
				<button id="mainObjBack" onclick="closeMainObjWin()" class="button button-primary button-rounded button-small">返回</button>
			</div>
		</div>
	</div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>

	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/back/base/user/userControl.js" title="v"></script>
</body>
</html>