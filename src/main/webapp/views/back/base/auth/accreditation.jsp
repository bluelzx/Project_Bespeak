<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta name="renderer" content="webkit"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>中国咨询网-网站后台管理系统 Powered by 成都蓝海飞鱼科技有限公司</title>
<link rel="STYLESHEET" type="text/css" href="/jquery/easyui/themes/gray/easyui.css"/>
<link rel="STYLESHEET" type="text/css" href="/jquery/easyui/themes/icon.css"/>
<link rel="STYLESHEET" type="text/css" href="/css/back/back.css" title="v"/>
<style type="text/css">
	.user_item{
		margin:15px;
	}
	.user_input{
		height: 20px;
  		border: 1px solid #E1E1E1;
  		outline: none;
  		width: 250px;
	}
	.user_label{
		width:100px;
		display:inline-block;
	}

</style>
</head>

<body>
    <div class="">
    	<input id="userId" type="hidden" />
	    	<!-- <div class="user_item">
				<span class="user_label">*用户名：</span><input id="username" class="user_item"/>
			</div>
			<span class="user_label" >身份证照片：</span><br/><a id="idCard" target="_black"><img id="idCardPic" style="width:400px;height:400px;" /></a><em id="Pic"  style="display:none;color:red;" >*未上传</em><br/>
			 -->
			 <c:forEach var="picture" items="${pictureList}" varStatus="status">
			 	<div style="margin: 20px 20px 10px 10px;display: inline-block;"><img  id="liscenseFile${status.index}"  style="width:300px" src="${picture.picPath}"/></div>
			 </c:forEach>
			<!-- <span class="user_label" ></span><br/><img  id="liscenseFile0"  style="width:300px" /><em id="File" style="display:none;color:red;" >*未上传</em><br/>
			<span class="user_label" ></span><br/><img id="liscenseFile1" style="width:300px"/><em id="File2"  style="display:none;color:red;" >*未上传</em><br/>
			<span class="user_label"></span><br/><img id="liscenseFile2" style="width:300px"/><em id="File3"  style="display:none;color:red;" >*未上传</em><br/>
			<span class="user_label"></span><br/><img id="liscenseFile3" style="width:300px"/><em id="File4"  style="display:none;color:red;" >*未上传</em>
 -->	</div>
	<%-- <br/>
	<div style="margin:5px 5px 0px 5px;float:left">
		<input id="addUser" onclick="accreditation()" type="button" class="search_btn" value=" 已认证"/>
	</div>
	<div style="margin:5px 5px 0px 5px;float:left">
		<input id="addUser" onclick="cancellAccreditation()" type="button" class="search_btn" value=" 未认证"/>
	</div>
	<div style="margin:5px 5px 0px 5px;float:left">
		<input id="returnBack" onclick="returnBack()" type="button" class="search_btn" value=" 返回"/>
	</div>
	<br/><br/>
	<div id="userData" style="display:none">${userJson}</div> --%>
	<div id="pictureListData" style="display:none">${pictureListJson}</div> 
	
<script type="text/javascript" src="/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/jquery/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/jquery/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/back/user/accreditation.js" title="v"></script>
<script type="text/javascript" src="/js/util/util_convertTreeJsonData.js" title="v"></script>
<!-- <script type="text/javascript" charset="utf-8" src="/ueditor/jquery-1.10.2.js"></script> -->
<!-- <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script> -->
</body>
</html>