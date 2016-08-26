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
<div class="pz_main">
		<div class="c_0100_19">
			 <div class="slide_sale slide_teacher c_0100_3" style="padding:0;text-align: center;" id="slide_sale">
				<div class="cytopdiv">
					<!-- <ul>
						<li style="margin:0px;position: relative;border-radius: 5px 0 0 5px;border-right: none;" class="pointer on" >展会活动</li>
						<li style="margin:0px;border-radius: 5px 0 0 5px;" class="pointer" onclick="location.href='/news'">新闻资讯</li>
					</ul> -->
					<span class="cytopdiv1 pointer" style="background: white;color:black" onclick="lh.jumpR('/activity');'">展会活动</span>
					<span class="cytopdiv2 pointer" style="background: #5F6367;color:white;" onclick="lh.jumpR('/news');">新闻资讯</span>
				</div>
			</div>
			<div class="slide_sale slide_teacher c_0100_3" style="padding:0;text-align: center;" id="slide_sale">
				<div class="cytopul">
					 <ul>
						<li id="allNews" class="pointer" style="border-bottom:1px solid red;color:red;width:30%" onclick="allNews();return false;">全部</li>
						<li id="antiqueNews" style="width:30%" onclick="antiqueNews();return false;">古玩资讯</li>
						<li id="antiqueKnowloge" style="width:30%" onclick="antiqueKnowloge(); return false;">古玩知识</li>
					</ul>
				</div>
			</div>
			<div id="news" class="t_0100_3">
			</div>
			<div id="resultTip" class="resultTip frontHide"></div>
			<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
		</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/information/news.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
		<ul class="c_0100_3" style="padding:0">
			{{#isRecommend}}
				<li onclick="lh.jumpR('/news/{{id}}');" class="pointer" style="padding:0">
					<div class="focus-tit">
						<div style="clear:both">
							<a href="javascript:;" class="btnRecommend">推荐</a>
							<a href="javascript:;" class="box">
								<img src="{{picPath}}" alt="{{title}}"/>
								<span>{{title}}</span>
							</a>
						</div>
					</div>
				</li>
			{{/isRecommend}}
			{{#isTop}}
				<li onclick="lh.jumpR('/news/{{id}}');" class="pointer" style="padding:0">
					<div class="focus-tit">
						<h3 class="tit"><i></i><a href="javascript:;">{{title}}</a></h3>
					</div>
				</li>
			{{/isTop}}
			{{^isRecommend}}	
				{{^isTop}}
					<li onclick="lh.jumpR('/news/{{id}}');" class="pointer">
						<div class="l_155" style="float:right;">
							<img src="{{picPath}}" width="100%" />
						</div>
						<div class="r_500 newsfont" style="float:left;">
							<nobr><h3>{{title}}</h3></nobr>
						</div>
					</li>
				{{/isTop}}
			{{/isRecommend}}
		</ul>
		{{/rows}}		 		 
	</script>
</body>
</html>
