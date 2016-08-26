<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<title>优惠券</title>
</head>
<body>
      <div id="wrapper">
        <!--主体内容开始-->
        <a onclick="lh.back()" class="back">
            <img src="/images/front/back.png" alt="">
        </a>
        <input type="hidden" value="${user.id}" id="userId" />
        <input type="hidden" value="${goods.shopId}" id="shopId" />
        <input type="hidden" value="${goods.typeCode}" id="typeCode" />
        <div class="boxTop" id="data-container">
        </div>
         <div class="boxTop" id="data-container2">
        </div>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
    <script src="/js/front/coupon/coupon.js"></script>
    <script id="template" type="x-tmpl-mustache">
        {{#rows}}
             <div class="yhqBox" onclick="choice({{id}})">
                <div class="lt yhq-left">
                    <p class="yhq-q">券</p>
                    <span class="yhq-span">优惠券</span>
                </div>
                <div class="rt yhq-right">
                    <p class="yhq-how">{{name}}</p>
					 <input type="hidden" value="{{money}}" id="money" />
					 <input type="hidden" value="{{discount}}" id="discount" />
                    <p class="yhq-lb">仅限{{attrStr2}}使用</p>
                    <p class="yhq-time">使用期限{{startTime}}至{{endTime}}</p>
                </div>
            </div>
        {{/rows}}
    </script>
       <script id="template2" type="x-tmpl-mustache">
        {{#rows}}
             <div class="yhqBox">
                <div class="lt yhq-left">
                    <p class="yhq-q">券</p>
                    <span class="yhq-span">优惠券</span>
                </div>
                <div class="rt yhq-right">
                    <p class="yhq-how">{{name}}</p>
					 <input type="hidden" value="{{money}}" id="money" />
					 <input type="hidden" value="{{discount}}" id="discount" />
                    <p class="yhq-lb">仅限{{attrStr2}}使用</p>
                    <p class="yhq-time">使用期限{{startTime}}至{{endTime}}</p>
                </div>
            </div>
        {{/rows}}
    </script>
</body>
</html>