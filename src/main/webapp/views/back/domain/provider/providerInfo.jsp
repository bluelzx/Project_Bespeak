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
				<td class="td_pad"><span>用户名：</span><input id="sc_usernameLike"  role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>真实姓名：</span><input id="sc_realNameLike" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>省（直辖市）：</span><input id="sc_province" role="combobox" data-options="editable:false" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>市（区）：</span><input id="sc_city" role="combobox" data-options="editable:false" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重置</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>实名认证：</span><input id="sc_isRealAuth" role="combobox" data-options="editable:false" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>状态：</span><input id="sc_mainStatus" role="combobox" data-options="editable:false" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>电话：</span><input id="sc_phoneLike" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>店铺：</span><input id="sc_shopId" role="combobox" data-options="editable:false" class="domain-input easyui-combobox width120" /></td>
				<!-- <td class="td_pad"><span>注册时间-从：</span><input role="datebox" id="sc_creatTimeFrom" data-options="editable:false" class="domain-input easyui-datebox width120" /></td>
				<td class="td_pad"><span>至：</span><input role="datebox" id="sc_creatTimeTo" data-options="editable:false" class="domain-input easyui-datebox width120" /></td> -->
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查询</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->

	<div class="clear-both height10"></div>
	<div id="opt_outer_div">
	<div class="fl_opt_div">
		<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()" class="button button-primary button-rounded button-small">批量删除</button>
		<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加技师</button>
		<button id="userMoneyLink" onclick="jumpToDict()"class="button button-royal button-rounded button-small">管理技师分类</button>
	<!-- 	<button id="userMoneyLink" onclick="jumpToUserMoney()"
			class="button button-royal button-rounded button-small">用户资金</button>
		<button id="userControlLink" onclick="jumpToUserControl()"
			class="button button-royal button-rounded button-small">用户控制</button> -->

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
		<div id='mainObjWin' class="easyui-window" title="技师详细信息" style="width: 710px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>用户名：</span><input id="f_username" data-options="required:true" role="textbox" class=" domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>真实姓名：</span><input id="f_realName" role="textbox" class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>实名认证：</span><input role="combobox" id="f_isRealAuth" class="domain-input easyui-combobox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
						<td class="td_pad"><span>状态：</span><input id="f_mainStatus" role="combobox" data-options="required:true" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>省（直辖市）：</span><input role="combobox" data-options="required:true"id="f_province" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>市（区）：</span><input id="f_city" role="combobox" data-options="required:true"class="domain-input easyui-combobox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>性别：</span><input id="f_sex" role="combobox" data-options="required:true" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>店铺：</span><input id="f_shopId" role="combobox" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>技师类型：</span><input id="f_typeCode" role="combobox" data-options="multiple:true" class="domain-input easyui-combobox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>电话：</span><input id="f_phone" role="textbox" class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>qq：</span><input id="f_qq" role="textbox" class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>邮箱：</span><input id="f_email" role="textbox" class="domain-input easyui-textbox width140" /></td>
							<!-- <td class="td_pad"><span>注册时间：</span><input id="f_createdAt" role="datebox"
								data-options="editable:false"
								class="domain-input easyui-datetimebox width140" /></td>-->
						</tr> 
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>微信：</span><input id="f_weixin" role="textbox" class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>工作年限：</span><input id="f_workYear" role="numberbox" data-options="min:0,max:50"  class="domain-input easyui-numberbox width140" /></td>
							<td class="td_pad"><span>重置密码：</span><input id="f_resetPassword"  data-options="prompt:'请输入密码'"  role="textbox" class="domain-input easyui-textbox width140" /></td>
						</tr>
						<tr class="tr_ht readOnlyTr" align="right">
							<td class="td_pad"><span class="colorGray">可用金额：</span><input role="textbox"
								id="f_avaliableMoney" data-options="readonly:true"
								class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span class="colorGray">冻结金额：</span><input role="textbox"
								id="f_frozenMoney" data-options="readonly:true"
								class="domain-input easyui-textbox width140" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<span>技师头像：</span>
			<button id="browse1" type="button" onmouseover="uploadPic('browse1',1);" class="button button-primary button-rounded button-small">选择图片</button>
			<span>(建议图片长宽均为120像素)</span>
			<div style="display: inline-block; float: left;">
			<div id="certificates1"></div>
				<%-- <img id="pic1" class="picurl" src="${pic.picPath}" style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" /> --%>
				<input type="hidden" name="filePaths1" id="filePaths1" value="${filePath}" />
				<input type="hidden" name="fileDBIds1" id="fileDBIds1" />
			</div>
			<div id="upload_outer_div1" style="margin-top: 30px;">
				<!-- 上传文件进度展示 -->
			</div>
			
				<span>技师证件：</span>
			<button id="browse2" type="button" onmouseover="uploadPic('browse2',2);" class="button button-primary button-rounded button-small">选择图片</button>
			<span>(建议图片长宽均为120像素)</span>
			<div style="display: inline-block; float: left;">
				<%-- <img id="pic2" class="picurl" src="${pic.picPath}" style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" /> --%>
				<div id="certificates2"></div>
				<input type="hidden" name="filePaths2" id="filePaths2" value="${filePath}" />
				<input type="hidden" name="fileDBIds2" id="fileDBIds2" />
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

	<div id="paramMapJson" style="display: none;">${user_search_condition}</div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>
	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/back/domain/provider/provider.js" title="v"></script>
</body>
</html>