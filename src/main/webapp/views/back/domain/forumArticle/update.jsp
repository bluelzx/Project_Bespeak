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
    	<input id="forumArticleId" type="hidden" />
    	<input id="id" type="hidden" value="${id}" />
    	<input id="operation" type="hidden" value="${operation}" />
		<form id="forumArticleForm">
			<table>
				<tr id="winSearchTr" class="tr_ht" align="left">
					<td class="td_pad"><span>用户查询：</span><input id="f_serialSearch" class="easyui-textbox width140"/></td>
					<td class="td_pad" colspan="2">
						<input type="button" onclick="searchUser();" class="button button-primary button-rounded button-small" value="查询"/>
						<input type="button" onclick="jumpToUserInfo()" class="button button-royal button-rounded button-small" value="用户信息"/>
						<span class="colorGray"> 请输入用户编号（可从用户信息表格中复制）</span>
					</td>
				</tr>
				<tr id="winSearchDivisionTr" class="tr_ht" align="left">
				</tr>
				<tr align="right">
					<td class="td_pad">
						<span class="colorGray">用户编号：</span>
						<input id="f_serial" data-options="readonly:true" class="easyui-textbox width140"/>
						<input id="f_userId" type="hidden"/>
						<input id="f_shopId" type="hidden"/>
					</td>
					<td class="td_pad"><span class="colorGray">用户名：</span><input id="f_username" data-options="readonly:true" class="easyui-textbox width140"/></td>
					<td class="td_pad"><span class="colorGray">真实姓名：</span><input id="f_realName" data-options="readonly:true" class="easyui-textbox width140" /></td>
				</tr>
				<tr>
					<!-- <td>帖子标题:</td>
					<td><input id="title" class="easyui-textbox" data-options="required:true"/></td> -->
					<!-- <td>帖子子标题:</td>
					<td><input id="subTitle" class="easyui-textbox" data-options="required:true"/></td>
					 -->
					<td >所属论坛：<input id="type" class="easyui-combobox"/></td>
					<td>状态：<input id="mainStatus" class="easyui-combobox"/></td>
				</tr>
			</table>
		</form>
			描述:
			<input id="content" class="easyui-textbox" style="width:100%;"data-options="multiline:true,height:100,prompt:'请输入内容描述'"/>
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
						<span>上传图片</span>
					</a>
				</div> 
			</div>		
		<!-- <div class="forumArticle_item"  >id="nameName"
			<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
		</div> -->
	</div>
    <div class="margin-5-5-10-0 fl">
		<button id="addForumArticle" onclick="addForumArticle();return false;" class="button button-primary button-rounded button-small">确定保存</button>
		<button id="editForumArticle" onclick="editForumArticle();return false;" class="button button-primary button-rounded button-small">编辑</button>
	</div>
	<div class="margin-5-5-10-0 fl">
		<button id="returnBack" onclick="returnBack();return false;" class="button button-primary button-rounded button-small">取消返回</button>
	</div>
<%@ include file="/views/common/common_back_js.htm"%>
<!-- <script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.all.js"> </script> -->
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
<script type="text/javascript" src="/js/back/forumArticle/addOrUpdateForumArticle.js" title="v"></script>
</body>
</html>