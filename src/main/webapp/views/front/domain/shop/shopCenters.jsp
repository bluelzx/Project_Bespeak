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

	<input type="hidden" id="shopcoorDinate" value="${shop.coorDinate}" />
	<a id="togMapId" onclick="initMap('${shop.coorDinate}');return false;"> <img style="cursor: pointer;" src="/images/front/map.png" align="absmiddle" />
	</a>
	<div id="allmap" style="display: none; margin: 10px auto; width: 700px; height: 560px; border: 1px solid #CECECE"></div>

	<center>
		<input id="save_tog" style="display: none; width: 80px; height: 25px; background: green; cursor: pointer; color: white;" onclick="saveTog(${shop.id})" type="button" value="保存标注点" /> <input id="cancel_tog"
			style="display: none; width: 80px; height: 25px; background: green; cursor: pointer; color: white;" onclick="cancelTog(${shop.id})" type="button" value="取消标注" /> <input id="hide_tog"
			style="display: none; width: 80px; height: 25px; background: green; cursor: pointer; color: white;" onclick="hideTog()" type="button" value="隐藏地图" />
	</center>

	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tw6T1u7TM0rWBgh1hGccvBnD"></script>
	<script type="text/javascript" src="/js/front/shop/gm/shopCenters.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
	{{/rows}}
	</script>
</body>
</html>