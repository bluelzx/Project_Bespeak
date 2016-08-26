<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/news.css" title="v" />
</head>
<body>
<%-- <input type="hidden" id="currentUserId" value="${currentUserId}"> --%>
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->	
	<div class="pz_main" style="margin-top:50px;">
	<div class="c_0100_19">
			 <div class="slide_sale slide_teacher c_0100_3" style="padding:0;text-align: center;" id="slide_sale">
				<div class="cytopdiv">
					<span class="cytopdiv1 pointer"  style="background: white;color:black" onclick="lh.jumpR('/cart');">已买商品</span>
					<span class="cytopdiv2 pointer" style="background: #5F6367;color:white;" onclick="lh.jumpR('/purchased');">已卖商品</span>
				</div>
			</div>
		</div>
		<div class="c_0100_14">
			<div class="slide_teacher" id="slide_teacher">
				<div class="bd">
					<ul class="ul_out_box" id="ul_out_box2">
						<div class="inbd">
							<div class="inbd_ul" id="orderGoodsList">
								
							</div>
						</div>
					</ul>
					<div id="resultTip" class="resultTip frontHide"></div>
					<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
				</div>
			</div>
		</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	<!-- <div class="c_0100_18 " style="position: fixed;bottom: 0;max-width: 640px;min-width: 320px;">
		<ul>
			<li><a href="javascript:;" class="a_say" onclick="addBatchCartGoods();return false;">保存</a></li>
			<li>
				<a href="javascript:;" class="a_nosay" onclick="checkOut();return false;">去结算</a>
			</li>
		</ul>
	</div> -->
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/cart/purchased.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
		<div goodsId="{{goodsId}}" class="teacher_list pointer">
			<div shopId="{{shopId}}" style="width:40px;height:40px;overflow:hidden;float:left" onclick="lh.jumpR('/goods/{{goodsId}}?shopId={{shopId}}');">
				<img src="{{picPath}}" width="100%" />
			</div>
			<div class="r_595 pointer" style="width:85%" onclick="lh.jumpR('/goods/{{goodsId}}?shopId={{shopId}}');">
				<span style="color:red;">价格:￥<span id="shopPrice{{goodsId}}">{{shopPrice}}</span></span>
				<span">商品名称:<span>{{goodsName}}</span></span>
				{{&logisticsStatus}}
			</div>
		</div>
	{{/rows}}		 		 
	</script>
</body>
</html>
