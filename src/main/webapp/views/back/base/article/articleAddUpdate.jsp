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
    	<input id="id" value="${article.id}" type="hidden"/>
    
		<form id="announcementForm">
			<table>
			        <tr class="tr_ht" align="right">
						<td >标&nbsp;题:</td>
						<td class="td_pad" colspan="5"><input id="f_title" class="domain-input easyui-textbox" style="width:720px" data-options="required:true" value="${article.title}"/></td>
					</tr>
					<tr class="tr_ht" align="right">
						<td >用&nbsp;户:</td>
						<td class="td_pad"><input id="f_userId" class="easyui-textbox" value="${article.userId}"/></td>
						<td >描&nbsp;&nbsp;述:</td>
						<td class="td_pad"><input id="f_description" class="easyui-textbox" value="${article.description}"/></td>
						<td >来&nbsp;&nbsp;源：</td>
						<td class="td_pad"><input id="f_orgin" class="easyui-textbox" value="${article.orgin}"/></td>
						<!--
						<td >网址:http://</td>
						<td class="td_pad"><input id="f_linkUrl" class="easyui-textbox" data-options="required:true" value="${article.linkUrl}"/></td>
						-->
					</tr>
					<tr class="tr_ht" align="right">
						<td >关键字:</td>
						<td class="td_pad"><input id="f_keywords" class="easyui-textbox" value="${article.keywords}"/></td>
						<td >标&nbsp;&nbsp;签:</td>
						<td class="td_pad"><input id="f_tags" class="easyui-textbox" value="${article.tags}"/></td>
						<td>作&nbsp;&nbsp;者:</td>
						<td class="td_pad"><input id="f_author" class="easyui-combobox" value="${article.author}"/></td>
					</tr>
					<tr class="tr_ht" align="right">
						<td >栏目ID:</td>
						<td class="td_pad"><input id="f_catId" class="easyui-combobox" data-options="required:true" value="${article.catId}"/></td>
						<td >文章标识用户:</td>
						<td class="td_pad"><input id="f_roleId" class="easyui-combobox" data-options="required:true" value="${article.roleId}"/></td>
						<td >发布机构:</td>
						<td class="td_pad"><input id="f_organization" class="easyui-textbox" value="${article.organization}"/></td>
					</tr>
				<tr class="tr_ht" align="right">
				    <td >创建日期从:</td>
					<td class="td_pad"><input id="f_startDate" class="easyui-datetimebox" data-options="editable:false" value="${article.startDate}"/></td>
					<td >至  ：</td>
					<td class="td_pad"><input id="f_endDate" class="easyui-datetimebox" data-options="editable:false" value="${article.endDate}"/></td>
				</tr>
			</table>
			<table>
			<tr align="right">
						<td >状&nbsp;态:</td>
						<td class="td_pad"><input id="f_mainStatus" class="easyui-combobox" style="width:60px;"  value="${article.mainStatus}"/></td>
						<td >是否热门:</td>
						<td class="td_pad"><input id="f_isHot" class="easyui-combobox" style="width:60px;"  value="${article.isHot}"/></td>
						<td >是否推荐:</td>
						<td class="td_pad"><input id="f_isRecommend" class="easyui-combobox" style="width:60px;"  value="${article.isRecommend}"/></td>	
					    <td >首页展示:</td>
						<td class="td_pad"><input id="f_isShowIndex" class="easyui-combobox" style="width:60px;"  value="${article.isShowIndex}"/></td>
						<td >是否精华:</td>
						<td class="td_pad"><input id="f_isGood" class="easyui-combobox" style="width:60px;"  value="${article.isGood}"/></td>
						<td >是否置顶:</td>
						<td class="td_pad"><input id="f_isTop" class="easyui-combobox" style="width:60px;"  value="${article.isTop}"/></td>	
			</tr>
			<!-- 
			<tr align="right">
				<td rowspan="2">附件:</td>
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
				</td>
			</tr>
			 -->
		</table>
		</form>
		内容:
		<div class="announcement_item"  ><!-- id="nameName" -->
			<script id="editor" type="text/plain" style="width:100%;height:300px;">${article.content}</script>
		</div>
	</div>
    <div class="margin-5-5-10-0 fl">
		<button id="addAnnouncement" onclick="addAnnouncement();return false;" class="button button-primary button-rounded button-small">确定保存</button>
		<button id="editAnnouncement" onclick="editAnnouncement();return false;" class="hide	button button-primary button-rounded button-small">编辑</button>
				<!-- <button id="mainObjSave" onclick="saveMainObj()" class="button button-primary button-rounded button-small">确定保存</button> -->
	</div>
	<div class="margin-5-5-10-0 fl">
		<button id="returnBack" onclick="returnBack();return false;" class="button button-primary button-rounded button-small">取消返回</button>
	</div>
<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<script type="text/javascript">
  		lh.paramJsonStr = '${paramJson}';
  	</script>
<script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/third-party/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/base/js/common/common_upload.js" title="bv"></script>
<script type="text/javascript" src="/js/back/base/article/articleUpdate.js" title="v"></script>
</body>
</html>