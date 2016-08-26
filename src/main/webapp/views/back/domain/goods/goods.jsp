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
				<td class="td_pad"><span>店铺名称：</span><input role="combobox" id="sc_shopId" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>服务状态：</span><input role="combobox" id="sc_mainStatus" class="domain-input easyui-combobox width120" /></td>
				<td class="td_pad"><span>服务时长：</span><input role="textbox" id="sc_timeNume" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>服务类型：</span><input role="combobox" id="sc_typeCode" class="domain-input easyui-combobox width120" /></td>
				<td></td>
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>服务名称：</span><input role="textbox" id="sc_goodsName" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>赠送积分：</span><input role="textbox" id="sc_giveIntegral" class="domain-input easyui-textbox width120" /></td>
				<td class="td_pad"><span>价格-从：</span><input role="textbox" id="sc_priceFrom" class="domain-input easyui-numberbox width120" /></td>
				<td class="td_pad"><span>至：</span><input role="textbox" id="sc_priceTo" class="domain-input easyui-numberbox width120" /></td>
				<td class="td_pad"><span>疗程：</span><input role="textbox" id="sc_groupNum" class="domain-input easyui-numberbox width120" /></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->
		<div class="clear-both height10"></div>
	<div id="opt_outer_div">
	<div class="fl_opt_div">
		<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()" class="button button-primary button-rounded button-small">批量删除</button>
		<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加服务</button>
		<button id="userMoneyLink" onclick="jumpToDict()"class="button button-royal button-rounded button-small">管理服务分类</button>
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
		<div id='mainObjWin' class="easyui-window" title="服务详细信息" style="width:720px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr id="winSearchDivisionTr" class="tr_ht" align="left"></tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>服务名称：</span><input role="textbox" id="f_goodsName" data-options="required:true" class="domain-input easyui-textbox width140"/></td>
							<!-- <td class="td_pad"><span>服务编号：</span><input role="textbox" id="f_serial" data-options="prompt:'不用填，写自动生成',editable:false" class="domain-input easyui-numberbox width140"/></td> -->
							<td class="td_pad"><span>服务价格：</span><input role="textbox" id="f_shopPrice"  data-options="required:true,min:0,precision:2" class="domain-input easyui-numberbox width140"/></td>
							<td class="td_pad"><span>赠送积分：</span><input role="textbox" id="f_giveIntegral" data-options="required:true,prompt:'购买该服务赠送积分数'" class="domain-input easyui-numberbox width140"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>服务状态：</span><input role="combobox" id="f_mainStatus" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
							<td class="td_pad"><span>服务类型：</span><input role="combobox" id="f_typeCode" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
							<td class="td_pad"><span>服务时长：</span><input role="textbox" id="f_timeNum" data-options="required:true,prompt:'服务总时间（小时）'" class="domain-input easyui-textbox width140"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>成交量：</span><input role="textbox" id="f_doneAmount" data-options="prompt:'可手动修改'" class="domain-input easyui-numberbox width140"/></td>
							<td class="td_pad"><span>浏览量：</span><input role="textbox" id="f_scans" data-options="prompt:'可手动修改'" class="domain-input easyui-numberbox width140"/></td>
							<td class="td_pad"><span>精品：</span><input role="combobox" id="f_isBest" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>店铺：</span><input role="combobox" id="f_shopId" data-options="required:true" class="domain-input easyui-combobox width140"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad" colspan="3"><span>服务流程：</span><input role="textbox" id="f_attrConent1" data-options="multiline:true,height:60,width:550,prompt:'服务流程介绍'"class="domain-input easyui-textbox"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad" colspan="3" ><span>服务疗效：</span><input role="textbox" id="f_attrConent2" data-options="multiline:true,prompt:'服务好处介绍',width:550,height:60," class="domain-input easyui-textbox "/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad" colspan="3" ><span>适用人群：</span><input role="textbox" id="f_attrConent3" data-options="multiline:true,prompt:'主要适用于那些人群',width:550,height:60," class="domain-input easyui-textbox "/></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad" colspan="3" ><span>预约须知：</span><input role="textbox" id="f_attrConent4" data-options="multiline:true,width:550,height:60," class="domain-input easyui-textbox "/></td>
						</tr>
					</tbody>
				 </table>
			 </form>  
			 <span>服务图片：</span>
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
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>
	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/back/domain/goods/goods.js" title="v"></script>
</body>
</html>