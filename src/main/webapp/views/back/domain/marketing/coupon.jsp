<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_back_css.htm"%>
<link rel="STYLESHEET" type="text/css" href="/css/back/back.css" title="v" />
</head>
<body>
    <!-- 查询条件  开始 -->
      <table id="mainQueryTable">
		<tbody>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>优惠方式：</span><input role="combobox" id="sc_way" class="domain-input easyui-combobox width140"/></td>
				<td class="td_pad"><span>优惠时间-从：</span><input role="datebox" id="sc_validStartTime" class="domain-input easyui-datebox width120" /></td>
				<td class="td_pad"><span>优惠价格（折扣）-从：</span><input role="textbox" id="sc_priceFrom" class="domain-input easyui-numberbox width120" /></td>
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>店铺：</span><input role="combobox" id="sc_shopId" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
				<td class="td_pad"><span>至       ：</span><input role="datebox" id="sc_validEndTime" class="domain-input easyui-datebox width120" /></td>
				<td class="td_pad"><span> 	 至   ：</span><input role="textbox" id="sc_priceTo" class="domain-input easyui-numberbox width120" /></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->
		<div class="clear-both height10"></div>
	<div id="opt_outer_div">
	<div class="fl_opt_div">
		<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()" class="button button-primary button-rounded button-small">批量删除</button>
		<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加优惠券</button>
		<button role="opt_2" id="btn_batchRecover" onclick="lh.commonBatchRecover()" class="hide button button-primary button-rounded button-small">批量恢复</button>
		<button role="opt_2" id="btn_throughDelete" onclick="lh.commonBatchThoroughDelete()" class="hide button button-primary button-rounded button-small">彻底删除</button>
	</div>
	
	<div class="fr_opt_div">
		<button role="opt_1" id="btn_trash" onclick="lh.commonShowTrash()" class="button button-primary button-rounded button-small">回收站</button>
		<button role="opt_2" id="btn_trashBack" onclick="lh.commonReturnBack()" class="hide button button-primary button-rounded button-small">返回</button>

	</div>
	</div>
	<!-- 表格  开始 -->
	<div id='datagrid_div'>
		<table id="datagrid"></table>
	</div>
	<!-- 表格  结束 -->
    	<div id="mainObjWindiv" style="display: none;">
		<div id='mainObjWin' class="easyui-window" title="优惠券详细信息" style="width:720px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr id="winSearchDivisionTr" class="tr_ht" align="left"></tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>店铺：</span><input role="combobox" id="f_shopId" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
							<td class="td_pad"><span>优惠方式：</span><input role="combobox" id="f_way" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
							<td class="td_pad"><span id="select" >优惠金额：</span><input role="textbox" id="f_discount" class="domain-input easyui-textbox width140" /></td>
							<!-- <td class="td_pad" id="money"><span>优惠金额：</span><input  id="f_money"  /></td>
							<td class="td_pad" id="discount" hidden="hidden"><span>优惠折扣：</span><input  id="f_discount"/></td> -->
						</tr>
						<tr class="tr_ht" align="right">
						     <td class="td_pad"><span>优惠券名：</span><input role="textbox" id="sc_name" class="domain-input easyui-textbox width120" /></td>
							 <td class="td_pad"><span>开始时间：</span><input id="f_validStartTime" role="datebox" data-options="editable:false"class="domain-input easyui-datebox width140" /></td>
							 <td class="td_pad"><span>结束时间：</span><input id="f_validEndTime" role="datebox" data-options="editable:false"class="domain-input easyui-datebox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
						<td class="td_pad"><span>优惠服务类型：</span><input id="f_attrStr" role="combobox" data-options="multiple:true" class="domain-input easyui-combobox width140" /></td>
						<td class="td_pad"><span>所属用户：</span><input role="combobox" id="f_userId" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
						<td class="td_pad"><span>使用状态：</span><input role="combobox" id="f_dealStatus" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
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
	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/base/js/common/common_upload.js" title="bv"></script>
	<script type="text/javascript" src="/js/back/domain/marketing/coupon.js" title="v"></script>
</body>
</html>