<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<title>技师</title>
</head>
<body>
   <div id="wrapper">
        <!--主体内容开始-->
        <div id="main">
            <!--tab滑动文字-->
            <div class="swiper-container appointment-provider swiper-fixed" >
                <div class="swiper-wrapper" id="data-container1">
                </div>
            </div>
            <!--信息详情-->
            <div class="provider" id="data-container">

            </div>
        </div>
        <footer>
            <a href="/index" class="ft-a">
                <img src="/images/front/index.png" alt="">
                <span>首页</span>
            </a>
            <a href="javascript:" class="ft-a">
                <img src="/images/front/technician-on.png" alt="">
                <span class="page-on">技师</span>
            </a>
            <a href="/orderIndex" class="ft-a">
                <img src="/images/front/order.png" alt="">
                <span>订单</span>
            </a>
            <a href="/userHome" class="ft-a">
                <img src="/images/front/user.png" alt="">
                <span>个人中心</span>
            </a>
        </footer>
        <!--底部菜单栏结束-->
    </div>
    <!--回到顶部按钮-->
    <div id="go-top"></div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
    <script type="text/javascript" src="/js/front/provider/provider.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}	 	
			<a href='/getProvider/{{id}}' class='details' id='details_{{id}}'>
	  			<div class='provider-box' id='provider_{{id}}'>
	           		 <img src='{{avatar}}' class='provider-img'>
	       			 <div class='provider-inf'>
	            		<span class='provider-name'>{{username}}</span>
	           		<!--<span class='provider-juli'>{{providerJuli}}km</span>-->
	           		<!--<span class='provider-juli'>{{provinceName}}{{cityName}}</span>-->
	            		{{&coorDinateJuli}}
	           			<p class='provider-zy'>{{shopName}}</p>
	             		<span class='js-like'>{{focusNum}}人喜欢</span>
	           			<p class='provider-p'>{{workYear}}年经验</p>
	        		</div>
	  	  		</div>
        	 </a>
		{{/rows}}
	</script>
	<script id="template1" type="x-tmpl-mustache">
		{{#rows}}	 	
			<div class="swiper-slide swiper-a" id="{{code}}">{{codeName}}</div>
		{{/rows}}
	</script>
</body>
</html>