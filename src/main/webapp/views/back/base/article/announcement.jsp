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
<body style="min-width:1000px;">
    <!-- 查询条件  开始 -->
   	<table id="mainQueryTable">
		<tbody>
			<tr class="tr_ht" align="right">
				<!--<td class="td_pad"><span>发布人：</span><input role="textbox" id="sc_senderName" class="domain-input easyui-textbox width100" /></td>-->
				<td class="td_pad"><span>公告标题：</span><input role="textbox" id="sc_title" class="domain-input easyui-textbox width100" /></td>
				<td class="td_pad"><span>公告内容：</span><input role="textbox" id="sc_content" class="domain-input easyui-textbox width100" /></td>
				<td class="td_pad"><span>排序：</span><input role="combobox" id="sc_ascOrdesc" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><button id="searchYes" onclick="doSearch();return false;" class="button button-primary button-rounded button-small">查 询</button></td>
				<td class="td_pad"><button id="searchClear" onclick="clearSearch();return false;" class="button button-primary button-rounded button-small">重 置</button></td>
			</tr>
			<!-- 
			<tr class="tr_ht" align="right">
				<td class="td_pad"><span>精华：</span><input role="combobox" id="sc_isEssence" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><span>置顶：</span><input role="combobox" id="sc_isTop" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><span>热门：</span><input role="combobox" id="sc_isHot" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><span>推荐：</span><input role="combobox" id="sc_isRecommend" class="domain-input easyui-combobox width100" /></td>
				<td class="td_pad"><span>阅读状态：</span><input role="combobox" id="sc_readStatus" class="domain-input easyui-combobox width100" /></td>
			</tr>
			 -->
		</tbody>
	</table>
	<div class="clear-both height10"></div>
	<!-- 查询条件 结束 -->
	<div id="opt_outer_div">
		<div class="fl_opt_div">
			<button role="opt_1" id="btn_batchDelete" onclick="lh.commonBatchDelete()"  class="button button-primary button-rounded button-small">批量删除</button>
			<button role="opt_1" onclick="addMainObj()" class="button button-primary button-rounded button-small">添加</button>
		<!--	<button  onclick="resetPassword()" class="button button-primary button-rounded button-small">重置密码</button> -->
		<!-- 	<button  onclick="exportUser()" class="button button-primary button-rounded button-small">导出公告信息</button> -->
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
    
    <div id="mainObjWindiv" style="display:none;">
	     <div id='mainObjWin' class="easyui-window" title="公告信息" style="width: 740px;" data-options="modal:true,closed:true,maximizable:false,collapsible:false,minimizable:false">
			<div id="mainObjTip"></div>
	         <form id="mainObjForm"><br/>
	       		 <table id="mainObjTable" class="padL5">
					<tbody>
						<tr class="tr_ht" align="right">
					
						<!-- 默认取当前登录用户为发布人，修改时取原有数据
						<input type="hidden" name="f_isPraise" id="f_isPraise" value='1'/>
							<td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>发布人：</span><input role="textbox" id="f_senderName" class="domain-input easyui-textbox width140" data-options="required:true" /></td>
						-->
							 <td class="td_pad"><span>主题：</span><input role="textbox" id="f_subject" class="domain-input easyui-textbox width140"/></td>
							<td class="td_pad"><span><span style="color:red;font-weight:bolder;"></span>接受组：</span><input role="combobox" id="f_receiverGroupId" class="domain-input easyui-combobox width140" /></td>
							<td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>公告标题：</span><input role="textbox" id="f_title" class="domain-input easyui-textbox width140" data-options="required:true"/></td>
						</tr>
						<tr class="tr_ht" align="right">
							
							<td class="td_pad"><span>链接:http://</span><input role="textbox" id="f_linkUrl" class="domain-input easyui-textbox width140"/></td>
							<td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>有效期：</span><input role="datetimebox" id="f_expiryDate" class="domain-input easyui-datetimebox width140" data-options="required:true"/></td>
						</tr>
						<tr class="tr_ht" align="right">
						<td  colspan="3" class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>内容：</span><input role="textbox" style="height:60px;width:600px;" id="f_content" class="domain-input easyui-textbox width140"/></td>
						<input type="hidden" role="datebox" id="f_sendTime"/>
						<!-- 
						<input type="hidden" name="f_isPraise" id="f_isPraise" value='1'/>
						<td class="td_pad"><span><span style="color:red;font-weight:bolder;">*</span>公告类型：</span><input role="combobox" id="f_contentType" class="domain-input easyui-combobox width140"
							data-options="valueField:'id',textField:'name',editable:false,multiple:false,required:true,panelHeight:'auto',data:[{'id':1,'name':'文本'},{'id':2,'name':'语音'},{'id':3,'name':'视频'},{'id':4,'name':'图片'},{'id':5,'name':'位置'},{'id':5,'name':'文件'}]"/>
						 -->
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
    <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
  		lh.paramJsonStr = '${paramJson}';
  	</script>
  	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/back/base/article/announcement.js" title="v"></script>
</body>
</html>