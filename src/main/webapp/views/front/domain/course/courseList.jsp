<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<meta charset="UTF-8">
    <title>健康培训</title>
</head>
<body>
    <div id="wrapper">
        <div class="train-header">
            <a href="lh.back()" class="back">
                <img src="images/front/back.png" alt="">
            </a>
           <!--  <a href="#" class="train-sign">培训报名</a> -->
        </div>
        <div class="train-box swiper-container">
            <ul class="train-ul">
                <li class="choice-on swiper-b" id="course_yuesao">月嫂</li>
                <li class="swiper-b" id="course_jiazhen">家政</li>
                <li class="swiper-b" id="course_jishi">技师</li>
                <li class="swiper-b" id="course_qita">
                    <a href="#">其他</a>
                </li>
            </ul>
        </div>
        <div id="data-container" class="train-container">

        </div>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
    <script src="/js/front/course/course.js"></script>
    <script id="template" type="x-tmpl-mustache">
        {{#rows}}
            <a href="/course/{{id}}" id="{{id}}" class="peixun-box">
                <img src="{{attrStr}}" alt="" class="train-headImg">
                <h2 class="train-title">{{name}}</h2>
                <p class='provider-zy'>授课讲师：{{teacher}}</p>
                <p class='cour-time'>授课时间：{{startdate}}至{{enddate}}</p>
                <span class='provider-juli'>{{address}}</span>
                <p class="train-inf">{{remark}}</p>
            </a>
        {{/rows}}
    </script>
</body>
</html>