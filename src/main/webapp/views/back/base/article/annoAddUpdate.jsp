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
    	<input id="announcementId" type="hidden" />
    	<input id="id" type="hidden" value="${id}" />
    	<input id="operation" type="hidden" value="${operation}" />
		<form id="announcementForm">
			<table>
					<tr class="tr_ht" align="right">
						<td >标题:</td>
						<td class="td_pad"><input id="title" class="easyui-textbox" data-options="required:true"/></td>
						<td >描述:</td>
						<td class="td_pad"><input id="description" class="easyui-textbox" data-options="required:true"/></td>
						<!-- <td rowspan="2">附件:</td>
						<td rowspan="2">
							<div>
								<input type="hidden" name="filePaths" id="filePaths"/>
								上传文件进度展示 开始
								id="filelist"
								<div id="upload_outer_div" >
								</div>
								上传文件进度展示 结束
								<div style="min-height:25px;margin-top:5px;"><a id="browse" class="button button-primary button-rounded button-small"><span>上传封面</span></a></div> 
							</div>
						</td> -->
					</tr>
					<tr class="tr_ht" align="right">
						<td >类型:</td>
						<td class="td_pad"><input id="type" class="easyui-combobox"/></td>
						<td >状态:</td>
						<td class="td_pad"><input id="mainStatus" class="easyui-combobox"/></td>
					</tr>
			</table>
		</form>
		内容:
		<div class="announcement_item"  ><!-- id="nameName" -->
			<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
		</div>
	</div>
    <div class="margin-5-5-10-0 fl">
		<button id="addAnnouncement" onclick="addAnnouncement();return false;" class="button button-primary button-rounded button-small">确定保存</button>
		<button id="editAnnouncement" onclick="editAnnouncement();return false;" class="hide	button button-primary button-rounded button-small">编辑</button>
	</div>
	<div class="margin-5-5-10-0 fl">
		<button id="returnBack" onclick="returnBack();return false;" class="button button-primary button-rounded button-small">取消返回</button>
	</div>
<%@ include file="/views/common/common_back_js.htm"%>
<script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/base/js/common/common_upload.js" title="bv"></script>
<script type="text/javascript" src="/js/back/base/article/annoUpdate.js" title="v"></script>
</body>
</html>