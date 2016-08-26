<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_back_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/back/back.css" title="v" />
</head>
<body>
	<!-- 查询条件  开始 -->
	<table id="mainQueryTable">
		<tbody>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>加盟商名：</span><input role="textbox" id="sc_name" class="domain-input easyui-textbox width100" /></td>
				<td class="td_pad"><span>所在省：</span><input role="combobox" id="sc_province" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><span>所在市：</span><input role="combobox" id="sc_city" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><span>排序：</span><input role="combobox" id="sc_ascOrdesc" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->
	<div id="opt_outer_div">
		<div class="fl_opt_div">
			<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()" class="button button-primary button-rounded button-small">批量删除</button>
			<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加</button>
			<button  onclick="resetPassword()" class="button button-primary button-rounded button-small">重置密码</button>
			<!--<button onclick="exportUser()" class="button button-primary button-rounded button-small">导出用户信息</button>-->
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
		<div id='mainObjWin' class="easyui-window" title="加盟商信息管理" style="width: 750px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<table id="mainObjTable" class="padL5">
					<tbody>
						<!-- 
						<tr id="winSearchTr" class="tr_ht" align="left">
							<td class="td_pad"><span>用户查询：</span><input id="f_serialSearch" class="easyui-textbox width140" /></td>
							<td class="td_pad" colspan="2"><input type="button" onclick="searchUser();" class="button button-primary button-rounded button-small" value="查询" /> <input type="button" onclick="jumpToUserInfo()"
								class="button button-royal button-rounded button-small" value="用户信息" /> <span class="colorGray"> 请输入用户编号（可从用户信息表格中复制）</span></td>
						</tr>
						<tr id="winSearchDivisionTr" class="tr_ht" align="left"></tr>
					 -->
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span><span style="color: red; font-weight: bolder;">*</span>加盟商名称：</span> <input id="f_name" data-options="readonly:true" role="textbox" class="easyui-textbox domain-input width140"
								data-options="required:true" /> <!--  <input role="textbox" id="f_userId" class="easyui-textbox domain-input " type="hidden" /> -->
							<td class="td_pad"><span>商家负责人：</span><input id="f_userId" role="combobox" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span><span style="color: red; font-weight: bolder;">*</span>状态：</span><input role="combobox" id="f_mainStatus" class="domain-input easyui-combobox width140" data-options="required:true" /></td>
							<!--<td class="td_pad"><span >真实姓名：</span><input id="f_realName" role="textbox"  class="domain-input easyui-textbox width140" /></td> -->
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>所在省：</span><input role="combobox" id="f_province" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>所在市：</span><input role="combobox" id="f_city" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span><span style="color: red; font-weight: bolder;">*</span>详细地址：</span><input role="textbox" id="f_address" class="domain-input easyui-textbox width140" data-options="required:true" /></td>
						</tr>
						<!--
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>营业执照：</span><input role="textbox" id="f_licence" class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>营业执照路径：</span><input role="textbox" id="f_licencePaths" class="domain-input easyui-textbox width140"/></td>
							<td class="td_pad"><span>组织机构代码：</span><input role="textbox" id="f_organizationCode" class="domain-input easyui-combobox width140"/></td>
						</tr>
						-->
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>成员数：</span><input role="numberbox" id="f_memberNum" class="domain-input easyui-numberbox width140" /></td>
							<td class="td_pad"><span><span style="color: red; font-weight: bolder;">*</span>电话号码：</span><input role="numberbox" id="f_phone" class="domain-input easyui-numberbox width140" data-options="required:true" /></td>
							<td class="td_pad"><span>座机号码：</span><input role="numberbox" id="f_phone2" class="domain-input easyui-numberbox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>信誉保证金：</span><input role="numberbox" id="f_creditMargin" class="domain-input easyui-numberbox width140" /></td>
							<td class="td_pad"><span style="color: red; font-weight: bolder;">*</span><span>等级(1-5)：</span><input role="numberbox" id="f_grade" class="domain-input easyui-numberspinner width140"
								data-options="required:true" min="1" max="5" /></td>
							<td class="td_pad"><span>评分(0-10分)：</span><input role="numberbox" id="f_score" class="domain-input easyui-numberspinner width140" min="0" max="10" /></td>
						</tr>
						<!-- 
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>附件路径：</span><input role="textbox" id="f_filePaths" class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>营业执照附件路径：</span><input role="textbox" id="f_filePaths2" class="domain-input easyui-textbox width140" /></td>
						</tr>
						 -->
					</tbody>
				</table>
			</form>
			<div style="margin-bottom:100px;">
			<span>上传商家logo：</span>
			<button id="browse1" type="button" onmouseover="uploadPic('browse1',1);" class="button button-primary button-rounded button-small">选择图片</button>
			<span>(建议图片长宽均为120像素)</span>
			<div style="display: inline-block; float: left;">
				<img id="pic1" class="picurl" src="${pic.picPath}" style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" /> <input type="hidden" name="filePaths1" id="filePaths1" value="${filePath}" /> <input
					type="hidden" name="fileDBIds1" id="fileDBIds1" />
			</div>
			<div id="upload_outer_div1" style="margin-top: 30px;">
				<!-- 上传文件进度展示 -->
			</div>
			</div>
			

			<span>上传营业执照：</span>
			<button id="browse2" type="button" onmouseover="uploadPic('browse2',2);" class="button button-primary button-rounded button-small">选择图片</button>
			<span>(建议图片长宽均为120像素)</span>
			<div style="display: inline-block; float: left;">
				<img id="pic2" class="picurl" src="${pic.picPath}" style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" /> <input type="hidden" name="filePaths2" id="filePaths2" value="${filePath}" /> <input
					type="hidden" name="fileDBIds2" id="fileDBIds2" />
			</div>
			<div id="upload_outer_div2" style="margin-top: 30px;">
				<!-- 上传文件进度展示 -->
			</div>
			<div class="inline-center mgV40">
				<button id="mainObjSave" onclick="saveMainObj()" class="button button-primary button-rounded button-small">保存</button>
				<button id="mainObjBack" onclick="closeMainObjWin()" class="button button-primary button-rounded button-small">返回</button>
			</div>
		</div>
	</div>
<!-- 重置密码div开始 -->
 <div id="resetPasswordWindiv" style="display:none;">
	     <div id='resetPasswordWin' class="easyui-window" title="重置密码" style="width: 635px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="resetPasswordTip"></div>
	         <form id="resetPasswordForm"><br/>
	       		 <table id="resetPasswordTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>密码：</span><input role="textbox" type="password" id="password" class="domain-input easyui-textbox width100" data-options="required:true"/></td>
						</tr>
					</tbody>
				 </table>
			 </form>  
			<div class="inline-center mgV40">
			     <button id="mainObjSave" onclick="saveResetPassword()" class="button button-primary button-rounded button-small">保存</button>
			     <button id="mainObjBack" onclick="closeResetPasswordWin()" class="button button-primary button-rounded button-small">返回</button>
			 </div>
	     </div>
    </div>
<!-- 重置密码div结束 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>
	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/common/common_back_resetPassword.js" title="v"></script>
	<script type="text/javascript" src="/js/back/domain/shop/shop.js" title="v"></script>
</body>
</html>