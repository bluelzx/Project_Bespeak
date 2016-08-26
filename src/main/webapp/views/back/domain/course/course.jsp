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
				<td class="td_pad"><span>课程名称：</span><input role="textbox" id="sc_nameLike" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>课程地址：</span><input role="textbox" id="sc_addressLike" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>课程类型：</span><input role="combobox" id="sc_typeCode" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>所属店铺：</span><input role="combobox" id="sc_linkId" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>开课时间-从：</span><input role="datebox" id="sc_startTime" class="domain-input easyui-datebox width120" /></td>
				<td class="td_pad"><span>  至 ：</span><input role="datebox" id="sc_endTime" class="domain-input easyui-datebox width120" /></td>
				<td class="td_pad"><span>价格-从：</span><input role="textbox" id="sc_priceFrom" class="domain-input easyui-numberbox width120" /></td>
				<td class="td_pad"><span>  至   ：</span><input role="textbox" id="sc_priceTo" class="domain-input easyui-numberbox width120" /></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->
		<div class="clear-both height10"></div>
	<div id="opt_outer_div">
	<div class="fl_opt_div">
		<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()" class="button button-primary button-rounded button-small">批量删除</button>
		<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加课程</button>
		<button id="userMoneyLink" onclick="jumpToDict()"class="button button-royal button-rounded button-small">管理课程分类</button>
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
		<div id='mainObjWin' class="easyui-window" title="课程详细信息" style="width:720px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr id="winSearchDivisionTr" class="tr_ht" align="left"></tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>课程名称：</span><input role="textbox" id="f_name" data-options="required:true" class="domain-input easyui-textbox width140"/></td>
							<td class="td_pad"><span>所属店铺：</span><input role="combobox" id="f_linkId" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
							<td class="td_pad"><span>课程价格：</span><input role="textbox" id="f_price"  data-options="required:true" class="domain-input easyui-numberbox width140"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<!-- <td class="td_pad"><span>课程状态：</span><input role="combobox" id="f_mainStatus" data-options="required:true" class="domain-input easyui-combobox width140"/></td> -->
							<td class="td_pad"><span>课程类型：</span><input role="combobox" id="f_typeCode" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
							<td class="td_pad"><span>上课地址：</span><input role="textbox" id="f_address"  data-options="required:true" class="domain-input easyui-textbox width140"/></td>
							<td class="td_pad"><span>课程讲师：</span><input role="textbox" id="f_teacher"  data-options="required:true" class="domain-input easyui-textbox width140"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							 <td class="td_pad"><span>开始时间：</span><input id="f_startTime" role="datebox" data-options="editable:false"class="domain-input easyui-datebox width140" /></td>
							 <td class="td_pad"><span>结束时间：</span><input id="f_endTime" role="datebox" data-options="editable:false,onSelect:onSelect"class="domain-input easyui-datebox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td colspan="3"><span>课程描述：</span><input role="textbox" id="f_description" data-options="multiline:true,prompt:'课程详细介绍',width:600,height:100," class="domain-input easyui-textbox width140"/></td>
						</tr>
					</tbody>
				 </table>
			 </form>  
			  <span>课程图片：</span>
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
			 <div class="inline-center mgV40">
			    <button id="mainObjSave" onclick="saveMainObj()" class="button button-primary button-rounded button-small">保存</button>
				<button id="mainObjBack" onclick="closeMainObjWin()" class="button button-primary button-rounded button-small">返回</button>
			 </div>
	     </div>
    </div>
    
    <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/back/domain/course/course.js" title="v"></script>
</body>
</html>