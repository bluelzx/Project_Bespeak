<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/news.css" title="v" />
</head>
<body style="background-color: #f5f5f5;">
	<input type="hidden" value="${id}" id="id">
	<input type="hidden"  id="CURRENT_PAGE">
<div class="c_0100_22 li_rum">
	<div class="text_frum " style="height: auto;box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.15);">
		<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
		<div style="margin-top:50px;">
			<div class="c_0100_3" style="padding:10px;box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.15);">
				<img src="${article.picPath}" width="100%" />
			</div>
			<div class="c_0100_3" style="padding:10px;  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.15);">
				<div style="line-height: 25px;font-size: 14px;">
					<nobr>${article.title}</nobr>
				</div>
				<div style="line-height: 25px;font-size: 14px;">
					<nobr>日期：<fmt:formatDate value="${article.startDate}" pattern="yyyy-MM-dd"></fmt:formatDate>&nbsp;-&nbsp;
					<fmt:formatDate value="${article.endDate}" pattern="yyyy-MM-dd"></fmt:formatDate></nobr>
				</div>
				<div style="line-height: 25px;font-size: 14px;">
					<nobr>发起方：${article.institution}</nobr>
				</div>
				<div style="line-height: 25px;font-size: 14px;">
					<nobr>地址：${article.address}</nobr>
				</div>
			</div>
		</div>
		<div class="c_0100_3" style="padding:10px;height:auto;box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.15);">
			${article.content}
		</div>
	</div>
	<div class="t_500_3 c_0100_3" style="padding:10px;  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.15);">
		<span>评论该活动</span>
		<textarea style="width:100%;height:60px;" id="content"></textarea>
		<div class="r_gz">
			<a href="javascript:;" onclick="addComment('${article.id}');return false;" class="a_gz">确定</a>
		</div>
	</div>
	<div class="c_0100_3" id="comment" style="padding:0px;box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.15);">
	</div>
	<!-- <div id="more" class="resultTip pointer frontHide" onclick="moreData();return false;">更多...</div> -->
	<div id="resultTip" class="resultTip frontHide"></div>
	<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<div class="save bottomFix" id="antiqueCityShop">
		<ul>
			<li><a href="javascript:;" class="a_say"  onclick="goTop();return false;">↑返回顶部</a></li>
		</ul>
	</div>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/information/activitiesDetail.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
		<div class="pointer" style="padding:10px;" onclick="location.href='/shop/{{userId}}'">
			<img src="{{userAvatar}}" style="width:25px;"/>
			<span style="color:red;">{{userName}}</span>：<span>{{content}}</span>
		</div>
		{{/rows}}		 		 
	</script>
</body>
</html>
