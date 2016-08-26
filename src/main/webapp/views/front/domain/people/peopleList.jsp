<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html id="htmlSize">
<head>
<title>月嫂/育婴</title>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body>
    <div id="wrapper">
        <!--主体内容开始-->
        <a href="/index" class="back">
            <img src="images/front/back.png" alt="">
        </a>
        <div id="main">
        <!--根据id自动生成导航-->
        <div class="appointment-provider swiper-fixed" >
            <ul class="train-ul" id="data-container1">
            <li  class="choice-on">
                    月嫂
                </li>
                <li>
                    育婴
                </li>
            </ul>
            </div>
            <!--信息详情-->
            <div class="provider" id="data-container">
                <!--<a href="{{providerYuYue}}">
                    <span class='provider-yuyue'>预约</span>
                </a>-->
            </div>
        </div>
        <!--底部菜单栏结束-->
    </div>
    <!--回到顶部按钮-->
    <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
    <script src="/js/common/train.js"></script>
    <script src="/js/front/people/people.js"></script>
    <script id="template" type="x-tmpl-mustache">
        {{#rows}}
            <div onClick="peoplejump({{id}})" class='provider-box' id='{{id}}'>
                <a href="{{providerInfUrl}}">
                    <img src='{{avatar}}' class='provider-img'>
                </a>
                <div class='provider-inf'>
                    <span class='provider-name'>{{realName}}</span>
                    <span class='provider-juli'>{{provinceName}}&nbsp;{{cityName}}</span>
                    <p class='provider-zy'>{{positionNames}}</p>
                    <span class='js-like'>{{workYear}}年工作经验</span>
                    <p class='provider-p'>{{introduction}}</p>
                </div>
            </div>
        {{/rows}}
    </script>
    <script id="template1" type="x-tmpl-mustache">
		{{#rows}}	 	
			<li class="swiper-a" id="{{code}}" onclick="init1()">{{codeName}}</li>
		{{/rows}}
	</script>
</body>
</html>