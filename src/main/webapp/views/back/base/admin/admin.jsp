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
				<td class="td_pad"><span>用户名：</span><input id="username" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>昵称：</span><input id="nickname" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>手机号码：</span><input id="phone" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>邮箱：</span><input id="email" class="easyui-textbox width120" /></td>
				<td class="td_pad"><button id="searchLoanBusiness" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>qq：</span><input id="qq" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>微信：</span><input id="weixin" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>状态：</span><input id="mainStatus" class="easyui-combobox width120" /></td>
				<td class="td_pad"></td>
				<td class="td_pad"><button id="clearsSearchLoanBusiness" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<div class="clear-both height10"></div>
	<!-- 查询条件 结束 -->
	<div class="fl_opt_div">
		<button id="batchDelete" onclick="batchDelete();return false;"  class="button button-primary button-rounded button-small" >批量删除</button>
		<button id="addAdmin" onclick="addAdmin();return false;" class="button button-primary button-rounded button-small">添加</button>
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
    
    <div id="adminDetailWindiv" style="display:none;">
	     <div id='adminDetailWin' class="easyui-window" title="用户角色信息" style="width: 700px;height:360px" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
	         <form id="adminDetailForm"><br/>
	       		 <table id="adminDetailTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>用户名：</span><input id="f_username" class="easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>昵称：</span><input id="f_nickname" class="easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>手机号码：</span><input id="f_phone" class="easyui-textbox width140" data-options="required:true"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>邮箱：</span><input id="f_email" class="easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>qq：</span><input id="f_qq" class="easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>微信：</span><input id="f_weixin" class="easyui-textbox width140"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<!-- <td class="td_pad"><span>用户头像：</span><input id="f_avatar" class="easyui-textbox width140"/></td> -->
							<!-- <td class="td_pad"><span>用户密码：</span><input id="f_password" class="easyui-textbox width140" /></td> -->
							<!-- <td class="td_pad"><span>角色：</span><input id="f_role" class="easyui-combobox width140"/></td> -->
							<td class="td_pad"><span>状态：</span><input id="f_mainStatus" class="easyui-combobox width140"/></td>
							<td class="td_pad"><span class="colorGray">用户密码默认为wpkuser6688</span></td>
						</tr>
						<tr class="tr_ht" align="right">
						</tr>
					</tbody>
				 </table>
			 </form>  
			 <div class="inline-center mgV40">
			     <button id="adminDetailSave" onclick="submitAdminDetail()"  class="button button-primary button-rounded button-small" >保存</button>
			     <button id="adminDetailBack" onclick="closeAdminDetailWin()"  class="button button-primary button-rounded button-small" >返回</button>
			 </div>
	     </div>
    </div>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/js/back/back/admin/admin.js" title="v"></script>
</body>
</html>