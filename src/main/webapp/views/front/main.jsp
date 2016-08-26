<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
</head>
<!--[if lt IE 9]>
	<script>
		var e = ("abbr,article,aside,audio,canvas,datalist,details," + "figure,footer,header,hgroup,mark,menu,meter,nav,output, " + "progress,section,time,video").split(',');
		for (var i = 0; i < e.length; i++) {document.createElement(e[i]);}
	</script>
<![endif]-->
</head>
<body class="to3">
<div class="to3">
<header class="page">
      <h1 style="background:none;text-align: center;width: 980px;padding:0;margin:55px 0 0 0;letter-spacing:5px;">惟华光能</h1>
</header>
  <div class="page">
		<a href="javascript:lh.jumpR('logout');" class="logout" title="logout">退出</a>
		<a href="javascript:lh.jumpR('ForumDict');">测试页面</a>
  </div>
</div>
<input type="hidden" value="${r}" id="r"/> 	
<input type="hidden" value="${loginStatus}" id="loginStatus"/>
<%@ include file="/views/common/common_js.htm"%>
</body>
</html>
