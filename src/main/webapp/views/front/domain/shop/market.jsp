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
</head>
<body style="background-color: #f5f5f5;">
	<div class="pz_top">
		<div class="c_0100_1">
			<div class="t_0100_1">
				<div class="tt_one">
					<div class="l_118">
						<a href="javascript:void(0);" id="ss1" class="sstext"> 
							<span id="searchLabel" role="2">商铺</span>&nbsp; 
							<img src="/images/front/top_img5.png" width="5" height="4" />
						</a>
					</div>
					<div class="r_305">
						<div class="l_205">
							<input type="text" placeholder="输入商铺名称" value="${searchName}" class="bfom" id="ss1_input" />
						</div>
						<div class="r_18" onclick="search(1);">
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
						<!-- <div class="ist_point">
							<a href="javascript:void(0);">推荐会员</a>
						</div> -->
						<div class="ist_point">
							<a href="/goodsManageType">产品管理</a>
						</div>
						<div class="ist_point">
							<a href="/goods">藏品列表</a>
						</div>
					</div>
				</div>
			</div>
			<div class="class">
				<a href="javascript:;" onclick="$('.pf_menu,.class').show(200);"><img src="/images/front/top_img3.png" width="18" height="13" />&nbsp;&nbsp;分类</a>
			</div>
			<!-- <div class="class" style="width: 80px;">
				<a href="javascript:;" onclick="location.href=''">&nbsp;&nbsp;藏品列表</a>
			</div> -->
		</div>
	</div>
	<div class="pz_main">
		<div class="c_0100_15">
			<div class="c_0100_15">
				<div class="newsdiv c_0100_3">
					<div class="gg_item" style="text-align: center;">共有<span id="shopCount"></span>商家</div>
				</div>
				<ul id="shopItems">
				</ul>
			</div>
		</div>
		<div id="resultTip" class="resultTip frontHide"></div>
		<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<input type="hidden" id="searchName" value="${searchName}">
	<%-- <input type="hidden" id="searchType" value="${searchType}">
	<input type="hidden" id="searchGoodsType" value="${searchGoodsType}"> --%>

	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/market/market.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
	<li>
		<div class="t_0100_8">
			<div class="l_60_2 pointer"><img src="{{logo}}" width="100%" onclick="location.href='/sale/{{userId}}'" /></div>
			<div class="gg_tit pointer" onclick="location.href='/sale/{{userId}}'">{{name}}</div>
			<div class="gg_item">共<span>{{goodsNum}}</span>件</div>
		</div>
		<div class="slide_gg" id="slide_gg_one">
			<div class="bd" onclick="location.href='/sale/{{userId}}'">
				{{&picDom}}
			</div>
			<div class="hd">
				<ul></ul>
			</div>
		</div>
		<div class="t_0100_9"><a href="/sale/{{userId}}">进店逛逛</a></div>
	</li>
	{{/rows}}		 		 
	</script>

</body>
</html>
