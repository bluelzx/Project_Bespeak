<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_back_css.htm"%>
<link rel="STYLESHEET" type="text/css" href="/css/back/back.css" title="v"/>
<link rel="STYLESHEET" type="text/css" href="/css/back/upload.css" title="v"/>
</head>
<body>
    <!-- 查询条件  开始 -->
	<table>
		<tbody>
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>图片类型：</span><input id="sc_picType" class="easyui-combobox width120" /></td>
				<td class="td_pad"><span>图片编号：</span><input id="sc_serial" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>图片名称：</span><input id="sc_title" class="easyui-textbox width120" /></td>
				<!-- <td class="td_pad"><span>相册名称：</span><input id="sc_albumName" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>相册编号：</span><input id="sc_albumSerial" class="easyui-textbox width120" /></td> -->
				<td class="td_pad"><button id="search" onclick="doSearch()" class="button button-primary button-rounded button-small">查 询</button></td>
			</tr>
			<tr class="tr_ht" align="right">
				<!-- <td class="td_pad"><span>商品名称：</span><input id="sc_goodsName" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>店铺名称：</span><input id="sc_shopName" class="easyui-textbox width120" /></td> 
				<td class="td_pad"><span>用户名：</span><input id="sc_username" class="easyui-textbox width120" /></td>-->
				<td class="td_pad"><span>用户编号：</span><input id="sc_userSerial" class="easyui-textbox width120" /></td>
				<!-- <td class="td_pad"><span>商品编号：</span><input id="sc_goodsSn" class="easyui-textbox width120" /></td>
				<td class="td_pad"><span>店铺编号：</span><input id="sc_shopSerial" class="easyui-textbox width120" /></td> -->
				<td class="td_pad"><span>上传时间-从：</span><input id="sc_startTimeFrom" data-options="editable:false" class="easyui-datebox width120" /></td>
				<td class="td_pad"><span>至：</span><input id="sc_startTimeTo" data-options="editable:false" class="easyui-datebox width120" /></td>
				<td class="td_pad"><button id="clearsSearch" onclick="clearSearch()" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
		</tbody>
	</table>
	<!-- 查询条件 结束 -->
	
	<div class="clear-both height10"></div>
	<div class="fl_opt_div">
		<button id="batchDelete" onclick="batchDelete()"  class="button button-primary button-rounded button-small" >批量删除</button>
		<!-- <button id="batchAdd" onclick="batchAdd()"  class="button button-primary button-rounded button-small" >批量上传图片</button> -->
		<button id="addPicture" onclick="addPicture()" class="button button-primary button-rounded button-small">添加图片</button>
		<button id="userInfoLink" onclick="jumpToUserInfo()" class="button button-royal button-rounded button-small">用户信息</button>
		<!-- <button id="shopLink" onclick="jumpToShop()" class="button button-royal button-rounded button-small">店铺信息</button> -->
		
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
    
     <div id="pictureWindiv" style="display:none;">
	     <div id='pictureWin' class="easyui-window" title="图片" style="width: 720px;height:420px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="upload_div" style="margin:5px 0px 5px 5px;" class="">
				<span>图片类型：</span><input id="f_picType" data-options="required:true" class="easyui-combobox width140"/>
				<input id="browse" type="button" class="button button-primary button-small" value="上传图片"/>
				<input type="button" onclick="resetUpload();" class="button button-rounded button-primary button-small" value="上传重置"/>
				<span class="colorGray">图片名称不能包含:!$%;,+[]{}?/\等特殊符号</span>
				<div id="attacments"></div>
	    		<input type="hidden" name="filePaths" id="filePaths">
				<div id="file_upload_progress" class="fieldset"></div>
			</div>
	         <form id="pictureForm"><br/>
	       		 <table id="pictureTable" class="padL5">
					<tbody>
						<!-- <tr class="tr_ht" align="right">
							<td colspan="1" align="left" class="td_pad">
								<input id="browse" type="button" class="button button-primary button-small" value="上传图片"/>
							</td>
							<td colspan="2" align="left" class="td_pad">
								<span class="colorGray">图片地址会自动生成，不需手动填写。只有使用网络图片时才需要输入图片地址。</span>
							</td>
						</tr> -->
						<!-- <tr class="tr_ht" align="right">
							<td class="td_pad"><span>图片类型：</span><input id="f_picType" data-options="required:true" class="easyui-combobox width140"/></td>
							<td align="left" class="td_pad"></td>
							<td class="td_pad"><span>图片名称：</span><input id="f_picTitle" data-options="required:true" class="easyui-textbox width140" /></td>
							<td colspan="2" class="td_pad"><span>图片地址：</span><input id="f_picPaths" class="easyui-textbox width380"
								data-options="required:true,readonly:false,prompt:'只有使用网络图片，才需要输入图片地址，多张图片用英文逗号隔开。'"/></td>
						</tr> -->
						<tr id="album_tr" class="tr_ht hide" align="right">
							<td class="td_pad"><span>相册编号：</span><input id="f_albumSerial" class="easyui-textbox width140" data-options="required:false,prompt:'非必填。'"/></td>
							<td align="left" class="td_pad">
								<input type="button" onclick="searchAlbumBySerial(null,'#f_albumSerial');" class="button button-rounded button-small" value="查询"/>
								<input id="f_albumId" type="hidden"/>
								<input id="f_userId" type="hidden"/>
							</td>
							<td class="td_pad"><span class="colorGray">所属相册：</span><input id="f_albumName" data-options="readonly:true" class="easyui-textbox width140"/></td>
						</tr>
						<tr id="user_tr" class="tr_ht hide" align="right">
							<td class="td_pad"><span>用户编号：</span><input id="f_userSerial" class="easyui-textbox width140"/></td>
							<td align="left" class="td_pad">
								<input type="button" onclick="searchUserBySerial(null,'#f_userSerial');" class="button button-rounded button-small" value="查询"/>
								<input id="f_userId" type="hidden"/>
							</td>
							<td class="td_pad"><span class="colorGray">所属用户：</span><input id="f_username" data-options="readonly:true" class="easyui-textbox width140"/></td>
						</tr>
						<tr id="goods_tr" class="tr_ht hide" align="right">
							<td class="td_pad"><span>商品编号：</span><input id="f_goodsSn" class="easyui-textbox width140"/></td>
							<td align="left" class="td_pad">
								<input type="button" onclick="searchGoodsBySerial(null,'#f_goodsSn');" class="button button-rounded button-small" value="查询"/>
								<input id="f_goodsId" type="hidden"/>
							</td>
							<td class="td_pad"><span class="colorGray">所属商品：</span><input id="f_goodsName" data-options="readonly:true" class="easyui-textbox width140"/></td>
						</tr>
						<tr id="inst_tr" class="tr_ht hide" align="right">
							<td class="td_pad"><span>机构编号：</span><input id="f_instSerial" class="easyui-textbox width140"/></td>
							<td align="left" class="td_pad">
								<input type="button" onclick="searchInstBySerial(null,'#f_instSerial');" class="button button-rounded button-small" value="查询"/>
								<input id="f_instId" type="hidden"/>
							</td>
							<td class="td_pad"><span class="colorGray">所属机构：</span><input id="f_instName" data-options="readonly:true" class="easyui-textbox width140"/></td>
						</tr>
						<tr id="shop_tr" class="tr_ht hide" align="right">
							<td class="td_pad"><span>店铺编号：</span><input id="f_shopSerial" class="easyui-textbox width140"/></td>
							<td align="left" class="td_pad">
								<input type="button" onclick="searchShopBySerial(null,'#f_shopSerial');" class="button button-rounded button-small" value="查询"/>
								<input id="f_shopId" type="hidden"/>
							</td>
							<td class="td_pad"><span class="colorGray">所属店铺：</span><input id="f_shopName" data-options="readonly:true" class="easyui-textbox width140"/></td>
						</tr>
						<tr id="forumArticle_tr" class="tr_ht hide" align="right">
							<td class="td_pad"><span>帖子编号：</span><input id="f_forumArticleSerial" class="easyui-textbox width140"/></td>
							<td align="left" class="td_pad">
								<input type="button" onclick="searchForumArticleBySerial(null,'#f_forumArticleSerial');" class="button button-rounded button-small" value="查询"/>
								<input id="f_forumArticleId" type="hidden"/>
							</td>
							<td class="td_pad"><span class="colorGray">所属帖子：</span><input id="f_forumArticleName" data-options="readonly:true" class="easyui-textbox width140"/></td>
						</tr>
					</tbody>
				 </table>
			 </form>  
			 <div class="inline-center mgV40">
			     <button id="pictureSave" onclick="submitPicture()"  class="button button-primary button-rounded button-small" >保存</button>
			     <button id="pictureBack" onclick="closePictureWin()"  class="button button-primary button-rounded button-small" >返回</button>
			 </div>
	     </div>
    </div>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>
	<script type="text/javascript" src="/base/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/back/base/forum/forum.js" title="v"></script>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/base/js/common/common_upload.js" title="bv"></script>
<script type="text/javascript" src="/js/back/base/file/picture.js" title="v"></script>
</body>
</html>