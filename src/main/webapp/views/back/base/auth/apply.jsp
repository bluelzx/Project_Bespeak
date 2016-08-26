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
   <table id="mainQueryTable">
		<tbody>
			<tr class="tr_ht" align="right">
				
				<td class="td_pad"><span>申请内容：</span><input role="textbox" id="message" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>回复内容：</span><input id="reply" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>申请人编号：</span><input id="applySerial" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>申请日期：</span><input id="applyDate"  role="datebox" data-options="editable:false" class="domain-input easyui-datebox width120" /></td>
			
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<!-- <td class="td_pad"><span>申请用户：</span><input id="userName" role="textbox" class="domain-input easyui-textbox width120" /></td>  -->
				<td class="td_pad"><span>申请人真实姓名：</span><input id="realName" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>申请机构编号：</span><input id="instSerial" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>类型：</span><input id="sc_type" role="combobox" class="domain-input easyui-combobox width120" /></td>
					<td class="td_pad"><span>状态：</span><input id="sc_mainStatus" role="combobox" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<div class="clear-both height10"></div>
	<!-- 查询条件 结束 -->
	<div id="opt_outer_div">
	<div class="fl_opt_div">
		<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()" class="button button-primary button-rounded button-small">批量删除</button>
		<!--  <button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加</button>  -->
		<button role="opt_2" id="btn_batchRecover" onclick="lh.commonBatchRecover()" class="hide button button-primary button-rounded button-small">批量恢复</button>
		<button role="opt_2" id="btn_throughDelete" onclick="lh.commonBatchThoroughDelete()" class="hide button button-primary button-rounded button-small">彻底删除</button>
		
		<button id="userInfoLink" onclick="jumpToUserInfo();return false;" class="button button-royal button-rounded button-small">用户信息</button>
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
		<div id='mainObjWin' class="easyui-window" title="申请详情" style="width:700px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
							<td class="td_pad">
								<span class="colorGray">用户编号：</span>
								<input id="f_id" type="hidden" data-options="readonly:true"  class="easyui-textbox width140"/>
								
								<input id="f_serial" data-options="readonly:true" style="display:none;"data-options="readonly:true" />
								<input id="f_userId" role="textbox" style="display:none;"data-options="readonly:true"/>
							</td>
							<td class="td_pad"><span class="colorGray">用户名：</span><input id="f_username" data-options="readonly:true" class="easyui-textbox width140" /></td>
							<td class="td_pad"><span class="colorGray">真实姓名：</span><input id="f_realName" data-options="readonly:true" class="easyui-textbox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span class="colorGray">申请日期：</span><input role="datetimebox" id="f_applyDate"  class="domain-input easyui-datetimebox width140"/></td>
							<td class="td_pad"><span class="colorGray">申请类型：</span><input role="combobox" id="f_applyType"  class="domain-input easyui-combobox width140"/></td>
							<!-- <td class="td_pad hide" id="applyInstType"><span class="colorGray">申请机构：</span><input id="f_instType" class="domain-inputeasyui-combobox width140"/></td> -->
						</tr>
						<tr class="tr_ht" >
							<td class="td_pad" colspan="3"><span class="colorGray">申请内容:</span>
								<input id="f_message" role="textbox" data-options="multiline:true,height:60,width:550" class="domain-input easyui-textbox "/>
							</td><br/>
						</tr>
						<tr class="tr_ht">
							<td class="td_pad" colspan="3"><span class="colorGray">回复内容:</span>
								<input id="f_reply" role="textbox" data-options="required:true,multiline:true,height:60,width:550,prompt:'请填写不同意理由'"  class="domain-input easyui-textbox "/>
							</td>
						</tr>
					</tbody>
				 </table>
			 </form>  
			 <div class="inline-center mgV40">
			      <button id="applyDetailAgree" onclick="applyDetailAgree();return false;"  class="button button-primary button-rounded button-small" >同意</button>
			     <button id="applyDetailDisAgree" onclick="applyDetailDisAgree();return false;"  class="button button-primary button-rounded button-small" >不同意</button>
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
<script type="text/javascript" src="/js/back/base/auth/apply.js" title="v"></script>
</body>
</html>