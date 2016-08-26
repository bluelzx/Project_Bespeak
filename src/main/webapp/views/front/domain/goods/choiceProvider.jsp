<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
    <title>技师选择</title>
</head>
<body>
     <div id="wrapper">
        <!--主体内容开始-->
        <div id="main">
         <input type="hidden" value="${goods.shopId}" id="shopId" />
         <input type="hidden" value="${goods.typeCode}" id="typeCode" />
            <a onclick="lh.back()" class="back">
                <img src="/images/front/back.png" alt="">
            </a>
            <a href="#" class="moren">
                默认指派
            </a>
            <!--信息详情-->
            <div class="choice-provider" id="data-container1">
          
            </div>
        </div>
        <!--底部菜单栏结束-->
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/goods/provider.js" title="v"></script>
	<script id="template1" type="x-tmpl-mustache">
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