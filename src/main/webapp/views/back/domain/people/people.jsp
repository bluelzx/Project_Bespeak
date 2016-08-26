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
				<td class="td_pad"><span>用户名：</span><input id="sc_username" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>实名认证：</span><input id="sc_isRealAuth" role="combobox" data-options="editable:false" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>省（直辖市）：</span><input id="sc_province" role="combobox" data-options="editable:false" class="domain-input easyui-combobox" style="width: 80px"/></td>
				<td class="td_pad"><span>市（区）：</span><input id="sc_city" role="combobox" data-options="editable:false" class="domain-input easyui-combobox" style="width: 80px"/></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重置</button></td>
			</tr>
			<tr class="tr_ht" align="right">
			<td class="td_pad"><span>真实姓名：</span><input id="sc_realName" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>预约状态：</span><input id="sc_mainStatus" role="combobox" data-options="editable:false" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>电话：</span><input id="sc_phone" role="textbox" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>注册时间：</span><input role="datebox" id="sc_createdAt" data-options="editable:false" class="domain-input easyui-datebox width120" /></td>
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
			<button  onclick="resetPassword()" class="button button-primary button-rounded button-small">重置密码</button>
			<button id="userMoneyLink" onclick="jumpToBespeak()"class="button button-royal button-rounded button-small">审核预约</button>
			<button id="userMoneyLink" onclick="jumpToDict()"class="button button-royal button-rounded button-small">管理月嫂分类</button>
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
		<div id='mainObjWin' class="easyui-window" title="月嫂详细信息" style="width: 750px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>用户名：</span><input id="f_username" role="textbox"
								class=" domain-input easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>真实姓名：</span><input id="f_realName" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>实名认证：</span><input role="combobox"
								id="f_isRealAuth" class="domain-input easyui-combobox width140" data-options="required:true"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>预约状态：</span><input id="f_mainStatus" role="combobox"
								class="domain-input easyui-combobox width140" data-options="editable:false,required:true"/></td>
							<td class="td_pad"><span>省（直辖市）：</span><input role="combobox"
								id="f_province" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>市（区）：</span><input id="f_city" role="combobox"
								class="domain-input easyui-combobox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>性别：</span><input id="f_sex" role="combobox"
								class="domain-input easyui-combobox width140" data-options="editable:false,required:true"/></td>
							<td class="td_pad"><span>登录名：</span><input id="f_loginName" role="textbox"
								class="domain-input easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>擅长领域：</span><input id="f_goodAt" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>电话：</span><input id="f_phone" role="numberbox"
								class="domain-input easyui-numberbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>微信：</span><input id="f_weixin" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
							<td class="td_pad"><span>qq：</span><input id="f_qq" role="numberbox"
								class="domain-input easyui-numberbox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>详细地址：</span><input id="f_address" role="textbox"
								class="domain-input easyui-textbox width140" data-options="required:true"/></td>
							<td class="td_pad"><span>工作经验(年)：</span><input id="f_workYear" role="numberbox"
								class="domain-input easyui-numberbox width140" min="0" max="30"/></td>	
							<td class="td_pad"><span>工作类型：</span><input id="f_typeCode" role="combobox"
								class="domain-input easyui-combobox width140" data-options="editable:false,required:true"/></td>
								<!--  
								<td class="td_pad"><span>邮箱：</span><input id="f_email" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
								<td class="td_pad"><span>职位名称：</span><input id="f_positionNames" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
								<td class="td_pad"><span>职称名：</span><input id="f_titleName" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
								 -->
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>出生日期：</span><input role="datetimebox" id="f_birthday" class="domain-input easyui-datetimebox width140" data-options="editable:false,required:true"/></td>
							<td class="td_pad"><span>身份证号：</span><input id="f_idcardNum" role="numberbox"
								class="domain-input easyui-numberbox width140" /></td>
							<td class="td_pad"><span>所属机构名：</span><input id="f_organization" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
							<!--  
							<td class="td_pad"><span>职称代码：</span><input id="f_titleCode" role="combobox"
								class="domain-input easyui-combobox width140" /></td>
							-->
						</tr>
						<tr class="tr_ht" align="right">
							
							<td class="td_pad"><span>业务范围：</span><input id="f_bussinessScope" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
							<!-- 	
							<td class="td_pad"><span>设置密码：</span><input id="f_password" role="textbox"
								class="domain-input easyui-textbox width140" /></td>
							-->
						</tr>
						<!-- 
						<td class="td_pad"><span>学历：</span><input id="f_educationCode" role="combobox"
								class="domain-input easyui-combobox width140" /></td>
						<tr class="tr_ht" align="right">
						<td class="td_pad"><span>重置密码：</span><input id="f_password" class="easyui-textbox width140"/></td>
						</tr>
						 -->
						<tr class="tr_ht" align="right">
							<td colspan="3" class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>简介及备注：</span><input role="textbox" style="height:80px;width:580px;font-size: 18px;" id="f_introduction" class="domain-input easyui-textbox" data-options="multiline:true,required:true"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td colspan="3" class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>工作收费标准：</span><input role="textbox" style="height:80px;width:580px;font-size: 18px;" id="f_priceText" class="domain-input easyui-textbox" data-options="multiline:true,required:true"/></td>
						</tr>
					</tbody>
				</table>
			</form>
			<div>
			<span>头&nbsp;&nbsp;像：</span>
			<button id="browse" type="button" class="button button-primary button-rounded button-small">选择图片</button>
			<span>(建议图片长宽均为120像素)</span>
			<div style="display: inline-block; float: left;">
				<img id="pic" class="picurl" src="${pic.picPath}" style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" />
				 <input type="hidden" name="filePaths" id="filePaths" value="${filePath}" />
				  <input type="hidden" name="fileDBIds" id="fileDBIds" />
			</div>
			<div id="upload_outer_div" style="margin-top: 30px;">
				<!-- 上传文件进度展示 -->
			</div>
			</div>
			<div class="inline-center mgV40">
				<button id="mainObjSave" onclick="saveMainObj()" class="button button-primary button-rounded button-small">保存</button>
				<button id="mainObjBack" onclick="closeMainObjWin()" class="button button-primary button-rounded button-small">返回</button>
			</div>
		</div>
	</div>
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
    <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
  		lh.paramJsonStr = '${paramJson}';
  	</script>
  	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
  	<script type="text/javascript" src="/js/common/common_back_resetPassword.js" title="v"></script>
	<script type="text/javascript" src="/js/back/domain/people/people.js" title="v"></script>
</body>
</html>