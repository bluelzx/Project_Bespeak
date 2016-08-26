<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%-- <%@ include file="/views/common/common_css.htm"%> --%>
<%@ include file="/views/common/common_front_wpk_css.htm"%>
<%-- 产品库- GoodsAction:/goods/page/store --%>
</head>
<body class="bg_gray">
	<div class="container-fluid">
		<div class="row auction_title">
			<div onclick="lh.back();" class="col-xs-3">
				<i class="icon-angle-left icon-3x"></i>
			</div>
			<div class="col-xs-6 pt10 plr0 text-center">
				<span class="fs16">拍品库管理</span>
			</div>
			<div onclick="jumpToAddGoods();" class="col-xs-3 pt10 plr7">
				<span>上传新品</span>
			</div>
		</div>
		<div class="row ptb10 goodsSearch">
			<div class="col-xs-12 plr7">
				<input type="text" class="form-control"/>
				<div class="col-xs-12 plr0 img">
					<div class="col-xs-1 plr0">
						<img src="/images/front/search.png" class="img-responsive" />
					</div>
					<div class="col-xs-11 fs16 plr0 gray">输入商品关键词</div>
				</div>
			</div>
		</div>
		<div class="row mt10">
			<div id="goodsType-container" class="col-xs-3 plr0 text-center bg_white fs16 bdso99">
				<div id="gt_all" onclick="getMyGoodsList('all');" class="col-xs-12 plr0 bdb ptb10 base-active">全部</div>
				<c:forEach items="${goodsTypeList}" var="goodsType">
					<div id="gt_${goodsType.code}" onclick="getMyGoodsList('${goodsType.code}');" class="col-xs-12 plr0 bdb ptb10">${goodsType.codeName}</div>
				</c:forEach>
			</div>
			<div id="data-container" class="col-xs-9 plr7">
			</div>
		</div>
	</div>

	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script> lh.param = ${paramJson}; </script>
	<script src="/js/front/goods/store.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
		<div id="goods_{{id}}" class="col-xs-12 plr7 bg_white">
			<div class="col-xs-12 plr0 ptb10 bdb">
				<div class="col-xs-4 plr0">
					<img class="img-responsive" src="{{getPicPath}}" />
				</div>
				<div class="col-xs-8 pl7 pr0">
					<div class="col-xs-11 plr0">
						<div class="gray">{{goodsName}}</div>
						{{#shopPrice}}<div class="col-xs-12 plr0 mt10 gray">价格：￥{{shopPrice}}</div>{{/shopPrice}}
						<div class="col-xs-12 plr0 mt10 gray">起拍价：￥{{priceBegin}}{{^priceBegin}}0{{/priceBegin}}</div>
						<div class="col-xs-12 plr0 mt10 gray">库存：{{remainNumber}}</div>
					</div>
					<div onclick="chooseGoods({{id}});" class="col-xs-1 plr0 pt20 mt10">
						<img class="img-responsive" src="/images/front/plus.png" />
					</div>
				</div>
			</div>
		</div>
	{{/rows}}	 		 
	</script>
</body>

</body>
</html>
