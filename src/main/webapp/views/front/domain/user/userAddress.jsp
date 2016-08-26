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
    <title>预约技师</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="迈噜-预约技师">
    <meta name="description" content="迈噜">
</head>
<body>
    <div id="wrapper">
        <!--主体内容开始-->
        <a href="provider.html" class="back">
            <img src="images/back.png" alt="">
        </a>
        <div class="addBox">
            <div class="user-Address">
                <div class="add-top">
                    <span>王思聪</span>
                    <span class="rt">18644448888</span>
                    <p>四川省成都市金牛区蜀汉路888号四川省成都市金牛区蜀汉路888号</p>
                </div>
                <div class="add-bot">
                    <span>设为默认地址</span>
                    <div class="rt">
                        <span>编辑</span>
                        <span>删除</span>
                    </div>
                </div>
            </div>
        </div>

        <input type="button" value="新增地址" class="xq-btn  newAdd" onclick="document.location='index-yuyue.html';">

    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/provider/providerInfo.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
	{{/rows}}
	</script>
</body>
</html>