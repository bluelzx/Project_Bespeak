<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<!-- <link rel="stylesheet" href="/css/front/...." title="v"> -->
   <meta charset="UTF-8">
    <title>关于我们</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="关于我们">
    <meta name="description" content="关于我们">
</head>
<body>
     <div id="wrapper" class="aboutUs-box">
        <!--主体内容开始-->
        <a onclick="lh.back()" class="back">
            <img src="/images/front/back.png" alt="" >
        </a>
        <img src="/images/front/about.png" alt="" class="aboutUs">
        <ul class="aboutUl">
            <li>
                版本信息
                <span>V0.1</span>
            </li>
            <li class="bt">
                说明:
            </li>
        </ul>
        <input type="button" value="检查更新" class="xq-btn aboutBt" onclick="document.location='';">
        <p class="aP">迈噜技术有限公司 版权所有</p>
        <p>Copyright © 2012-2016 All Rights Reserved</p>
    </div>
    <!--回到顶部-->
    <div id="go-top"></div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/goods/provider.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
    
	{{#rows}}
            <div class='provider-box' id='provider_{{id}}' onclick="choice({{id}})">
					<input type="hidden" value="{{id}}" id="providerId" />
                    <img src='{{avatar}}' class='provider-img'>
                <div class='provider-inf'>
                    <span class='provider-name'>{{realName}}</span>
                    <span class='provider-juli'>{{provinceName}}{{cityName}}</span>
                    <p class='provider-zy'>{{workYear}}年经验</p>
                    <span class='js-like'>{{focusNum}}人喜欢</span>
                    <div class="provider-pj">
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx.png" alt="" class="pj-like">
                    </a>
                	</div>
                </div>
            </div>	
	{{/rows}}
	</script>

</body>
</html>