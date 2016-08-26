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
			 <td class="td_pad"><span>预约用户：</span><input id="sc_userId" role="combobox" class="domain-input easyui-combobox width140" /></td>
             <td class="td_pad"><span>预约门店：</span><input id="sc_shopId" role="combobox" class="domain-input easyui-combobox width140" /></td>
             <td class="td_pad"><span>预约服务：</span><input id="sc_goodsId"  role="combobox" class="domain-input easyui-combobox width140" /></td>
			 <td class="td_pad"><span>预约技师：</span><input id="sc_providerId" role="combobox" class="domain-input easyui-combobox width140" /></td>
			 <td class="td_pad"><span>预约时间：</span><input id="sc_bespeakStartTime" role="datebox" class="domain-input easyui-datebox width140" /></td>
			 <td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重置</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>省（直辖市）：</span><input id="sc_province" role="textbox" class="domain-input easyui-textbox width140" /></td>
				<td class="td_pad"><span>市（区）：</span><input id="sc_city" role="textbox" class="domain-input easyui-textbox width140" /></td>
				<td class="td_pad"><span>预约次数：</span><input id="sc_goodsNumber" role="textbox" class="domain-input easyui-textbox width140" /></td>
				<td class="td_pad"><span>预约类型：</span><input id="sc_typeId" role="textbox" class="domain-input easyui-textbox width140" /></td>
			  	<td class="td_pad"></td>
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查询</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->

	<div class="clear-both height10"></div>
	<div id="opt_outer_div">
	<div class="fl_opt_div">
		<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()" class="button button-primary button-rounded button-small">批量删除</button>
		<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加技师预约</button>
		<button id="userMoneyLink" onclick="jumpToUserMoney()" class="button button-royal button-rounded button-small">订单详情</button>
		
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
		<div id='mainObjWin' class="easyui-window" title="技师预约信息" style="width: 730px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
			<form id="mainObjForm">
				<br />
				<table id="mainObjTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
                            <td class="td_pad"><span>预约用户：</span><input id="f_userId"  data-options="required:true" role="combobox" class="domain-input easyui-combobox width140" /></td>
                            <td class="td_pad"><span>预约门店：</span><input id="f_shopId" data-options="required:true" role="combobox" class="domain-input easyui-combobox width140" /></td>
                            <td class="td_pad"><span>预约服务：</span><input id="f_goodsId"  data-options="required:true" role="combobox" class="domain-input easyui-combobox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>预约技师：</span><input id="f_providerId" role="combobox" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>预约类型：</span><input id="f_typeId" role="combobox" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>订单编号：</span><input id="f_orderSn" role="textbox" data-options="prompt:'不用填，写自动生成',editable:false"  class="domain-input easyui-textbox width140" /></td>
						</tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad"><span>省（直辖市）：</span><input id="f_province" role="combobox" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>市（区）：</span><input id="f_city" role="combobox" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span>详细地址：</span><input id="f_address" role="textbox" class="domain-input easyui-textbox width140" /></td>
						</tr> 
						<tr class="tr_ht" align="right">
							 <td class="td_pad"><span>预约次数：</span><input id="f_goodsNumber"  role="numberbox" class="domain-input easyui-numberbox width140" /></td>
						  	 <td class="td_pad"><span>预约疗程：</span><input id="f_groupNum"  class="easyui-numberbox width140"    /></td>
							 <td class="td_pad"><span>支付金额：</span><input id="f_price"  role="numberbox" class="domain-input easyui-numberbox width140" data-options="min:0,precision:2"/></td>
						</tr>
						<tr class="tr_ht" align="right">
						 	 <td class="td_pad"><span>优惠券：</span><input id="f_couponId" role="combobox" class="domain-input easyui-combobox width140" /></td>
							 <td class="td_pad"><span>预约时间：</span><input id="f_bespeakStartTime" role="datetimebox"  data-options="editable:false,onSelect:onSelect,required:true" class="domain-input easyui-datetimebox width140" /></td>
							 <input id="number" hidden="hidden" />
							 <input id="price" hidden="hidden"  />
							 <input id="groupPrice" hidden="hidden"  />
							 <input id="goodsNumber" hidden="hidden" />
							 <input id="money" hidden="hidden"  />
							 <input id="discount" hidden="hidden" />
							 <input id="timeNum" hidden="hidden" />
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

	<div id="paramMapJson" style="display: none;">${user_search_condition}</div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>
	<script type="text/javascript" src="/js/common/back_template.js"
		title="v"></script>
	<script type="text/javascript" src="/base/js/common/common_upload.js"
		title="bv"></script>
	<script type="text/javascript" src="/js/back/domain/provider/bespeak.js"
		title="v"></script>
</body>
</html>