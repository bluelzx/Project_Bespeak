<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/auction.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/news.css" title="v" />
</head>
<body id="news2" style="background-color: #F0EFEF;margin-top: 58px;">
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<div class="Listpage">
		<div class="top46"></div>
		<div class="page-bizinfo">
			<div></div>
			<div style="position: relative;text-align: center" class="header">
				<h1 id="activity-name">${article.title}</h1>
				<h6>添加时间：<fmt:formatDate value="${article.createdAt}" pattern="yyyy-MM-dd"/></h6>
			</div>
			<!-- <div class="showpic">
				<img id="shopPic" class="picurl">
			</div> -->
			<div id="content" class="text">
				<span style="font-size: 14px;">${article.content}</span>
			</div>
		</div>
	</div>

	<%-- <%@ include file="/views/front/common/z_div_menu_bottom.htm"%> --%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%-- <%@ include file="/views/front/common/z_div_type_slide.htm"%> --%><!-- 右侧分类查询 -->
	<%@ include file="/views/common/common_js.htm"%>
	<script src="/js/front/common.js" title="v"></script>
</body>
</html>
