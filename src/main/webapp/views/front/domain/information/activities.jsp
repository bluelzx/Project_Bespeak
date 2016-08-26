<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body >
 <div id="wrapper">
        <!--主体内容开始-->
        <a onclick="lh.back()" class="back">
            <img src="/images/front/back.png" alt="">
        </a>
        <!--轮播图-->
        <div class="swiper-container index-swiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <a href="#" class="swiper-choice">
                        <img src="/images/front/gy-bg.png" alt="" class="swiper-image">
                    </a>
                </div>
                <div class="swiper-slide">
                    <a href="#" class="swiper-choice">
                        <img src="/images/front/gy-bg-2.png" alt="" class="swiper-image">
                    </a>
                </div>
            </div>
            <div class="swiper-pagination" id="pubCir"></div>
        </div>
        <div id="data-container" class="train-container">

        </div>
    </div>
    <!--回到顶部-->
    <div id="go-top"></div>
	
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
	<script type="text/javascript" src="/js/front/information/activities.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		  {{#rows}}
            <a href="#" id="{{id}}" class="gongyi-box">
                <img src="{{picPaths}}" alt="" class="gongyi-headImg">
                <h2 class="gongyi-title">{{title}}</h2>
                <span class='p-time'>{{&date}}</span>
                <p class="gongyi-inf">{{&content}}</p>
            </a>
        {{/rows}}
	</script>
</body>
</html>