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
				<td class="td_pad"><span>服务方式类型:</span><input id="serviceType" class="easyui-combobox width120" /></td>
				<td class="td_pad"><span>产品类型:</span><input id="productType" class="easyui-combobox width120" /></td>
				<td class="td_pad"><span>状态:</span><input id="mainStatus" class="easyui-combobox width120" /></td>
				<td class="td_pad"><button id="searchLoanBusiness" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
				<td class="td_pad"><button id="clearsSearchLoanBusiness" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<div class="clear-both height10"></div>
	<!-- 查询条件 结束 -->
	<div class="fl_opt_div">
		<button id="batchDelete" onclick="batchDelete();return false;"  class="button button-primary button-rounded button-small" >批量删除</button>
		<button id="batchRecover"  onclick="batchRecover();return false;" class="hide button button-primary button-rounded button-small">批量恢复</button>
		<button id="batchThoroughDelete" onclick="batchThoroughDelete();return false;" class="hide button button-primary button-rounded button-small">批量彻底删除</button>
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
    <div id="applyDetailWindiv" style="display:none;">
	     <div id='applyDetailWin' class="easyui-window" title="产品库申请" style="width:300px;height:460px" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
	         <form id="applyDetailForm"><br/>
	       		 <table id="applyDetailTable" class="padL5">
					<tbody>
						<tr>
							<td class="td_pad"> <input type="hidden" id="f_applyId"></td>
						</tr>	
						<tr class="tr_ht">
							<td class="td_pad"><span class="colorGray">服务方式类型：</span><input id="f_serviceType" data-options="readonly:true,required:true" class="easyui-combobox width140"/></td>
						</tr>
						<tr class="tr_ht">
							<td class="td_pad"><span class="colorGray">产品类型：</span><input id="f_productType" data-options="readonly:true,required:true" class="easyui-combobox width140"/></td>
						</tr>
						<tr class="tr_ht">
							<td class="td_pad"><span class="colorGray">产品件数：</span><input id="f_goodsNum" data-options="readonly:true,required:true" class="easyui-textbox width140"/></td>
						</tr>
						<tr class="tr_ht">
							<td class="td_pad"><span class="colorGray">每件产品拍摄张数：</span><input id="f_num" data-options="readonly:true,required:true" class="easyui-textbox width140"/></td>
						</tr>
						<tr class="tr_ht">
							<td class="td_pad"><span class="colorGray">状态：</span><input id="f_mainStatus" data-options="readonly:true,required:true" class="easyui-combobox width140"/></td>
						</tr>
						<tr class="tr_ht">
							<td class="td_pad"><span class="colorGray">审批意见：</span>
								<input id="f_replay" data-options="multiline:true,height:60,width:260"  class="easyui-textbox width140"/>
							</td>
						</tr>
					</tbody>
				 </table>
			 </form>  
			 <div class="inline-center mgV40">
			     <button id="applyDetailSave" onclick="submitApplyDetail();return false;"  class="button button-primary button-rounded button-small" >保存</button>
			     <button id="applyDetailAgree" onclick="applyDetailAgree();return false;"  class="button button-primary button-rounded button-small" >同意</button>
			     <button id="applyDetailDisAgree" onclick="applyDetailDisAgree();return false;"  class="button button-primary button-rounded button-small" >不同意</button>
			     <button id="applyDetailBack" onclick="closeApplyDetailWin();return false;"  class="button button-primary button-rounded button-small" >返回</button>
			 </div>
	     </div>
    </div>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/js/back/shop/agentProduct.js" title="v"></script>
</body>
</html>