<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" href="/css/front/main.css">
<link rel="stylesheet" href="/css/front/jquery-weui.min.css">
</head>
<body>
	<input type="hidden" id="userId" value="${user.id}" />
	<div id="wrapper">
		<!--主体内容开始-->
		<a onclick="lh.back()" class="back"> 
			<img src="images/front/back.png" alt="">
		</a>
		<div class="addBox" id="data-container">
			
		</div>
		<input type="button" value="新增地址" class="xq-btn  newAdd"
			onclick="lh.jumpR('/addOrUpdateUserAddress');">
		<div class="spmc_bg"></div>
	</div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript">
		lh.param = ${paramJson}
	</script>
	<script type="text/javascript"
		src="/js/front/user/addReceiveAddress.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
  			 <div class="user-Address" >
                <div class="add-top" onclick="choiceAddress({{id}})">
                    <span>{{receiverName}}</span>
                    <span class="rt">{{phone}}</span>
                    <p>{{provinceName}}{{cityName}}{{addressDetail}}</p>
                </div>
                <div class="add-bot">
                    <span class="mrdzS" onclick="setDefult({{id}})">
                        {{&isDefult}}
                    		    设为默认地址
                    </span>
                    <div class="rt">
                        <span class="dzSpan" onclick="lh.jumpR('/addOrUpdateAddress/{{id}}');">编辑</span>
                        <span class="dzSpan" onclick="deleteAddress({{id}});">删除</span>
                    </div>
                </div>
            </div>
	{{/rows}}	
    </script>
</body>
</html>