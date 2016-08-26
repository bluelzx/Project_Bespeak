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
    <div class="">
    	<input id="articleId" type="hidden" />
    	<input id="catId" type="hidden" />
    	<input id="id" type="hidden" value="${id}" />
    	<input id="operation" type="hidden" value="${operation}" />
		<form id="articleForm">
			<table>
				<c:if test="${typeId == 41}">
					<tr class="tr_ht" align="right">
						<td >标题:</td>
						<td class="td_pad"><input id="title" class="easyui-textbox" data-options="required:true"/></td>
							<td>发起方:</td>
							<td class="td_pad"><input id="institution" class="easyui-textbox" data-options="required:true"/></td>
							<td >活动地址:</td>
							<td class="td_pad"><input id="address" class="easyui-textbox" data-options="required:true"/></td>
						<td rowspan="3">封面:</td>
						<td rowspan="3">
							<div>
								<img src="${ap.picPaths}" class="picurl">
								<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
				    			<input type="hidden" name="fileDBIds" id="fileDBIds"/>
								<!-- 上传文件进度展示 结束 -->
								<div style="min-height:25px;margin-top:5px;"><a id="browse" class="button button-primary button-rounded button-small"><span>上传封面</span></a></div> 
							</div>
						</td>
						<!-- <td>子标题：</td>
						<td><input id="subTitle" class="easyui-textbox"/></td> -->
					</tr>
					<tr class="tr_ht" align="right">
						<td >活动开始时间:</td>
						<td class="td_pad"><input id="startDate" class="easyui-datebox" data-options="editable:false,required:true"/></td>
						<td >活动结束时间:</td>
						<td class="td_pad"><input id="endDate" class="easyui-datebox" data-options="editable:false,required:true"/></td>
						<td >是否上首页展示:</td>
						<td class="td_pad"><input id="isShowIndex" class="easyui-combobox"/></td>
					</tr>
					<tr class="tr_ht" align="right">
						<td >类型:</td>
						<td class="td_pad"><input id="type" class="easyui-combobox"/></td>
						<td >状态:</td>
						<td class="td_pad"><input id="mainStatus" class="easyui-combobox"/></td>
						<td >描述:</td>
						<td class="td_pad"><input id="description" class="easyui-textbox" data-options="required:true"/></td>
					</tr>
				</c:if>
				<c:if test="${typeId != 41}">
					<tr class="tr_ht" align="right">
						<td >标题:</td>
						<td class="td_pad"><input id="title" class="easyui-textbox" data-options="required:true"/></td>
						<td >描述:</td>
						<td class="td_pad"><input id="description" class="easyui-textbox" data-options="required:true"/></td>
						<td >是否头条:</td>
						<td class="td_pad"><input id="isTop" class="easyui-combobox"/></td>
						<td rowspan="2">封面:</td>
						<td rowspan="2">
							<div>
								<img src="${ap.picPaths}" class="picurl">
								<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
				    			<input type="hidden" name="fileDBIds" id="fileDBIds"/>
								<!-- 上传文件进度展示 结束 -->
								<div style="min-height:25px;margin-top:5px;"><a id="browse" class="button button-primary button-rounded button-small"><span>上传封面</span></a></div> 
							</div>
						</td>
					</tr>
					<tr class="tr_ht" align="right">
						<td >类型:</td>
						<td class="td_pad"><input id="type" class="easyui-combobox"/></td>
						<td >状态:</td>
						<td class="td_pad"><input id="mainStatus" class="easyui-combobox"/></td>
						<td >是否推荐:</td>
						<td class="td_pad"><input id="isRecommend" class="easyui-combobox"/></td>
					</tr>
				</c:if>
			</table>
		</form>
		<div id="upload_outer_div"><!-- 上传文件进度展示 --></div>
		内容:
		<div class="article_item"  ><!-- id="nameName" -->
			<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
		</div>
	</div>
    <div class="margin-5-5-10-0 fl">
		<button id="addArticle" onclick="addArticle();return false;" class="button button-primary button-rounded button-small">确定保存</button>
		<button id="editArticle" onclick="editArticle();return false;" class="hide	button button-primary button-rounded button-small">编辑</button>
	</div>
	<div class="margin-5-5-10-0 fl">
		<button id="returnBack" onclick="returnBack(${typeId});return false;" class="button button-primary button-rounded button-small">取消返回</button>
	</div>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
<script type="text/javascript" src="/js/back/information/addOrUpdateArticle.js" title="v"></script>
</body>
</html>