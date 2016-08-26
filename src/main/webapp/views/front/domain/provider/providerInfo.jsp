<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<title>预约技师</title>
</head>
<body>
    <div id="wrapper">
        <!--主体内容开始-->
        <div class="tn-top js-top">
            <a onclick="lh.back()" class="back">
                <img src="/images/front/back.png" alt="">
            </a>
            <input type="hidden" value="${provider.id}" id="providerId" />
            <input type="hidden" value="${user.id}" id="userId" />
            <input type="hidden" value="${provider.shopId}" id="shopId" />
            <input type="hidden" value="${provider.typeCode}" id="typeCode" />
            <input type="hidden" value="${provider.typeNames}" id="typeNames" />
            <img src="${provider.avatar}" alt="" class="js-img">
            <div class="tn-line1 js-top-bt">
                <img src="/images/front/xin.png" onclick="updataFocus()" alt="" class="img-like">
                <span id="focusNum" class="tn-like">${provider.focusNum}</span> <span class="tn-like">人喜欢</span>
                <a href="#" class="provider-zz">资质证书</a>
            </div>
        </div>
        <div class="swiper-container appointment-provider swiper-fixed" style="background: #fff;">
            <div class="swiper-wrapper" id="data-container2">
               
            </div>
        </div>
        <div class="provider" id="data-container1">
           <!-- 技师服务 -->
            
        </div>
        <div class="evaluate" style="margin-top:0.15rem;">
            <div class="eva-title">客户评价</div>
            <div id="data-container">
               <div id="comment" style="text-align:center"></div>
            </div>
        </div>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/provider/providerInfo.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
            <div class="eva-box" >
                <div class="star">
                    {{&stars}}
                    <span class="user-phone"> {{comUserName}}</span>
                </div>
                <p class="eva-p">{{content}}</p>
                <div class="eva-bottom">
                    <span class="eva-tilte">{{goodsName}}</span>
                    <span class="eva-time">{{getDate}}</span>
                </div>
            </div>	 	
	{{/rows}}
	</script>
 	<script id="template1" type="x-tmpl-mustache">
	{{#rows}}
 			<div class="provider-inf-yy">
                <p class="yy-title">{{goodsName}}</p>
                <p class="provider-yy-time">{{shopPrice}}元/{{timeNum}}小时</p>
                <a href="/providerBespeak/${provider.id}/{{id}}" class="provider-yy-a">预约</a>
            </div>
	{{/rows}}
	</script>
</body>
</html>