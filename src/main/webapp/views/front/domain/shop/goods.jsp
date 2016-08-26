<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/auction.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/tiaokuan.css" title="v" />
</head>
<body style="background-color: #f5f5f5;">
	<div class="pz_top">
		<div class="c_0100_1">
			<div class="t_0100_1">
				<div class="tt_one">
					<div class="l_118">
						<a href="javascript:void(0);" id="ss1" class="sstext"> 
							<span id="searchLabel" role="1">藏品</span>&nbsp; 
							<img src="/images/front/top_img5.png" width="5" height="4" />
						</a>
					</div>
					<div class="r_305">
						<div class="l_205">
							<input name="" type="text" placeholder="输入藏品名称" value="${searchName}" class="bfom" id="ss1_input" />
						</div>
						<div class="r_18" onclick="search();">
							<a href="javascript:void(0);"><img src="/images/front/top_img6.png" width="18" height="18" /></a>
						</div>
					</div>
				</div>
			</div>
			<div class="threepoint">
				<a href="javascript:;" class="a_po" onclick="$('.pf_point').slideToggle(0)"><img src="/images/front/top_img1.png" width="25" height="25" /></a>
				<div class="pf_point" style="display: none;">
					<div class="img_point">
						<img src="/images/front/top_img2.png" width="8" height="7" />
					</div>
					<div class="ulist_point">
						<div class="ist_point">
							<a href="/addGoods">发布拍品</a>
						</div>
						<div class="ist_point">
							<a href="/market">商家列表</a>
						</div>
					</div>
				</div>
			</div>
			<div class="class">
				<a href="javascript:;" onclick="$('.pf_menu,.class').show(200);"><img src="/images/front/top_img3.png" width="18" height="13" />&nbsp;&nbsp;分类</a>
			</div>
			<%-- <div class="class" style="width: 80px;">
				<a href="javascript:;" onclick="location.href='/market'">&nbsp;&nbsp;商家列表</a>
			</div> --%>
		</div>
	</div>
	<!-- <div class="pz_main">
		<div class="c_0100_15">
			<div  class="mpnzais" id="shopGoods">
			</div>
		</div>
		<div id="resultTip" class="resultTip frontHide"></div>
		<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
	</div> -->
	<div class="mainbox" id="iOther">
		<div class="mainsmall" id="auctionOnlineMain">
			<div id="main" role="main" style="border-bottom: 1px solid #CDCDCD; background: white;">
				<div style="padding: 0 5px;">
					<ul id="shopGoods" class="mainul mainul1" style="width:100%">
						<%-- <c:forEach items="${goodsList}" var="goods" step="2">
							<li onclick="location.href='/professionGoodsDetail?goodsId=${goods.id}&auctionId=1'" class="mainulli goods_bg"><span
								style="background: url(${goods.picPath}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
								<h3 style="color: #838381;">编号.${goods.goodsSn}</h3></li>
						</c:forEach> --%>
					</ul>
				</div>
				<div style="clear: both;"></div>
			</div>
		</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>

	<input type="hidden" id="searchName" value="${searchName}">
	<%-- <input type="hidden" id="searchType" value="${searchType}"> --%>
	<input type="hidden" id="searchGoodsType" value="${searchGoodsType}">
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/market/goods.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
		<li style="width:45%;float:left;margin: 5px;" onclick="location.href='/goods/{{id}}?shopId={{shopId}}'" class="mainulli goods_bg pointer">
			<span style="background: url({{picPath}}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
			{{#shopPrice}}
				<h3 style="color: #838381;margin-top:0px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">{{goodsName}}</h3>
				<h3 style="margin-top:0px;">￥{{shopPrice}}</h3>
			{{/shopPrice}}
			{{^shopPrice}}
				<h3 style="color: #838381;margin-top:0px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">{{goodsName}}</h3>
				<h3 style="margin-top:0px;">议价</h3>
			{{/shopPrice}}
		</li>
	{{/rows}}		 		 
	</script>
</body>
</html>



