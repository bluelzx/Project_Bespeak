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
			<!-- <tr class="tr_ht" align="right">
				<td class="td_pad"><span>店铺名称：</span><input id="sc_shopName" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>店铺编号：</span><input id="sc_shopSerial" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>用户名：</span><input id="sc_username" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>用户编号：</span><input id="sc_userSerial" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>真实姓名：</span><input id="sc_realName" class="easyui-textbox width120" /></td>
			</tr> -->
			<tr class="tr_ht" align="right">
				<!-- <td class="td_pad"><span>商品状态：</span><input id="sc_mainStatus" class="easyui-textbox width120" /></td> -->
				<td class="td_pad"><span>商品名称：</span><input id="sc_goodsPictureName" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>商品编号：</span><input id="sc_goodsPictureSn" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>价格-从：</span><input id="sc_priceFrom" class="easyui-numberbox width120" /></td>
				<td class="td_pad"><span>至：</span><input id="sc_priceTo" class="easyui-numberbox width120" /></td>
				<td class="td_pad"><button id="search" onclick="doSearch()" class="button button-primary button-rounded button-small">查 询</button></td>
				<td class="td_pad"><button id="clearsSearch" onclick="clearSearch()" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->
	
	<div class="clear-both height10"></div>
	<div class="fl_opt_div">
		<button id="batchDelete" onclick="batchDelete()"  class="button button-primary button-rounded button-small" >批量删除</button>
		<button id="addGoodsPicture" onclick="addGoodsPicture()" class="button button-primary button-rounded button-small">添加商品图片</button>
		<!--  <button id="userInfoLink" onclick="jumpToUserInfo()" class="button button-royal button-rounded button-small">用户信息</button>
		<button id="shopLink" onclick="jumpToShop()" class="button button-royal button-rounded button-small">店铺信息</button> -->
		<button id="goodsLink" onclick="jumpToGoods()" class="button button-royal button-rounded button-small">藏品信息</button>
		<button id="batchRecover" onclick="batchRecover()" class="hide button button-primary button-rounded button-small">批量恢复</button>
		<button id="batchThoroughDelete" onclick="batchThoroughDelete()" class="hide button button-primary button-rounded button-small">彻底删除</button>
		
	</div>
	<div class="fr_opt_div">
		<button id="showTrash" onclick="showTrach()" class="button button-primary button-rounded button-small">回收站</button>
		<button id="returnBack" onclick="returnBack()" class="hide button button-primary button-rounded button-small">返回</button>
	</div>
	<!-- 表格  开始 -->
	<div id='datagrid_div'>
		<table id="datagrid"></table>
	</div>
	<!-- 表格  结束 -->
     <div id="goodsPictureWindiv" style="display:none;">
	     <div id='goodsPictureWin' class="easyui-window" title="商品" style="width: 700px;height:360px" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
	         <form id="goodsPictureForm"><br/>
	       		 <table id="goodsPictureTable" class="padL5">
					<tbody>
						<tr id="winSearchTr" class="tr_ht" align="left">
							<td class="td_pad"><span>商品查询：</span><input id="f_serialSearch" class="easyui-textbox width140"/></td>
							<td class="td_pad" colspan="2">
								<input type="button" onclick="searchGoods();" class="button button-primary button-rounded button-small" value="查询"/>
								<input type="button" onclick="jumpToGoods()" class="button button-royal button-rounded button-small" value="藏品信息"/>
								<span class="colorGray"> 请输入商品编号（可从商品表格中复制）</span>
							</td>
						</tr>
						<tr id="winSearchDivisionTr" class="tr_ht" align="left"></tr>
						<tr class="tr_ht" align="right">
							<td class="td_pad">
							    <span class="colorGray">商品名称：</span><input id="f_goodsName" data-options="readonly:true" class="easyui-textbox width140" />
								<input id="f_userId" type="hidden"/>
								<input id="f_goodsId" type="hidden"/>
							</td>
						</tr>
					</tbody>
				 </table>
			 </form>  
			 <div>
				<img src="${ap.picPaths}" class="picurl">
				<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
    			<input type="hidden" name="fileDBIds" id="fileDBIds"/>
				<!-- 上传文件进度展示 开始 -->
				<!-- id="filelist" -->
				<div id="upload_outer_div" >
				</div>
				<!-- 上传文件进度展示 结束 -->
				<div style="min-height:25px;margin-top:5px;">
					<a id="browse"  class="button button-primary button-rounded button-small" value="用户头像">
						<span>上传藏品图片</span>
					</a>
				</div> 
			</div>	
			 <div class="inline-center mgV40">
			     <button id="goodsPictureSave" onclick="submitGoodsPicture()"  class="button button-primary button-rounded button-small" >保存</button>
			     <button id="goodsPictureBack" onclick="closeGoodsPictureWin()"  class="button button-primary button-rounded button-small" >返回</button>
			 </div>
	     </div>
    </div> 
    
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
<script type="text/javascript" src="/js/back/goods/goodsPictureLib.js" title="v"></script>
</body>
</html>