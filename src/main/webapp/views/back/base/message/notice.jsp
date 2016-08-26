<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/common/meta_info.htm"%>
	<%@ include file="/views/common/common_back_css.htm"%>
	<link rel="stylesheet" type="text/css" href="/css/back/back.css" title="v"/>
</head>
<body>
    <!-- 查询条件  开始 -->
   	<table id="mainQueryTable">
		<tbody>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>发送用户：</span><input role="combobox" id="sc_senderId" class="domain-input easyui-combobox width140" /></td>
				<td class="td_pad"><span>接收用户：</span><input role="combobox" id="sc_receiverId" class="domain-input easyui-combobox width140" /></td>
				<td class="td_pad"><span>消息类型：</span><input role="combobox" id="sc_contentType" class="domain-input easyui-combobox width140" data-options="editable:false"/></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>消息标题：</span><input role="textbox" id="sc_title" class="domain-input easyui-textbox width140" /></td>
				<td class="td_pad"><span>消息内容：</span><input role="textbox" id="sc_content" class="domain-input easyui-textbox width140" /></td>
				<td class="td_pad"><span>阅读状态：</span><input role="combobox" id="sc_readStatus" class="domain-input easyui-combobox width140" 
				data-options="valueField:'id',textField:'name',editable:false,multiple:false,required:false,panelHeight:'auto',data:[{'id':1,'name':'未读'},{'id':2,'name':'已读'}]"/>
				</td>
				<!--
				<td class="td_pad"><span>发送日期：</span><input role="datebox" id="sc_sendTime" class="domain-input easyui-datebox width140" data-options="editable:false"/></td>
				<td class="td_pad"><span>阅读日期：</span><input role="combobox" id="sc_readTime" class="domain-input easyui-datetimebox width140" data-options="editable:false"/></td>
				 -->
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
		</tbody>
	</table>
	<div class="clear-both height10"></div>
	<!-- 查询条件 结束 -->
	<div id="opt_outer_div">
		<div class="fl_opt_div">
			<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()"  class="button button-primary button-rounded button-small">批量删除</button>
			<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加</button>
			
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
    
    <div id="mainObjWindiv" style="display:none;">
	     <div id='mainObjWin' class="easyui-window" title="通知消息" style="width: 720px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
	         <form id="mainObjForm" style="width:720px;"><br/>
	       		 <table id="mainObjTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
						    <td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>发送人：</span><input role="combobox" id="f_senderId" class="domain-input easyui-combobox width140" data-options="required:true"/></td>
						    <td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>发送人昵称：</span><input role="textbox" id="f_senderName" class="domain-input easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>接收用户：</span><input role="combobox" id="f_receiverId" class="domain-input easyui-combobox width140" data-options="required:true" data-options="editable:false"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>发送日期：</span><input role="datebox" id="f_sendTime" class="domain-input easyui-datebox width140" data-options="editable:false"/></td>
							<td class="td_pad"><span>链接:http://</span><input role="textbox" id="f_linkUrl" class="domain-input easyui-textbox width140"/></td>
							<td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>有效期：</span><input role="datebox" id="f_expiryDate" class="domain-input easyui-datebox width140" data-options="required:true,editable:false"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<!-- 
							<td class="td_pad"><span>是否匿名：</span><input role="combobox" id="f_anonymous" class="domain-input easyui-combobox width140" 
							data-options="valueField:'id',textField:'name',editable:false,multiple:false,required:false,panelHeight:'auto',data:[{'id':1,'name':'匿名'},{'id':2,'name':'不匿名'}]"/>
							</td>
							 -->
							 <td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>接受组：</span><input role="combobox" id="f_receiverIds" class="domain-input easyui-combobox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>阅读状态：</span><input role="combobox" id="f_readStatus" class="domain-input easyui-combobox width140" 
							data-options="valueField:'id',textField:'name',editable:false,multiple:false,required:false,panelHeight:'auto',data:[{'id':1,'name':'未读'},{'id':2,'name':'已读'}]"/>
							</td>
							<td class="td_pad"><span>消息类型：</span><input role="combobox" id="f_contentType" class="domain-input easyui-combobox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td colspan="3" class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>消息标题：</span><input role="textbox" style="width:580px;" id="f_title" class="domain-input easyui-textbox" data-options="multiline:false,required:true"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td colspan="3" class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>消息内容：</span><input role="textbox" style="height:100px;width:580px;" id="f_content" class="domain-input easyui-textbox" data-options="multiline:true,required:true"/></td>
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
	<script type="text/javascript">
  		lh.paramJsonStr = '${paramJson}';
  	</script>
  	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/back/base/message/notice.js" title="v"></script>
</body>
</html>