<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
</head>
<body>
<%-- <input type="hidden" id="currentUserId" value="${currentUserId}"> --%>
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->	
	<div class="pz_main" style="margin-top:50px;">
		<div class="c_0100_14">
			<div class="slide_teacher" id="slide_teacher">
				<div class="bd">
					<ul class="ul_out_box" id="ul_out_box2">
						<div class="inbd">
							<div class="inbd_ul" id="cartList">
								
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
	<div class="c_0100_18 " style="position: fixed;bottom: 0;max-width: 640px;min-width: 320px;">
		<ul>
			<li><a href="javascript:;" class="a_say" onclick="addBatchCartGoods();return false;">保存</a></li>
			<li>
				<a href="javascript:;" class="a_nosay" onclick="checkOut();return false;">提交给商家</a>
			</li>
		</ul>
	</div>
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
	<script type="text/javascript" src="/js/front/cart/myCart.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
		<div goodsId="{{goodsId}}" class="teacher_list pointer">
			<div shopId="{{shopId}}" style="width:40px;height:40px;overflow:hidden;" onclick="lh.jumpR('/goods/{{goodsId}}?shopId={{shopId}}');">
				<img src="{{picPath}}" width="100%" />
			</div>
			<input type="hidden" id="cartId" value="{{id}}">
			<div class="r_595 pointer" style="width:80%" onclick="lh.jumpR('/goods/{{goodsId}}?shopId={{shopId}}');">
				<span style="color:red;">价格:￥<span id="shopPrice{{goodsId}}">{{shopPrice}}</span></span>
			</div>
			<div class="r_595">
				<button type="button" class="btn btn-info" onclick="add('','{{goodsId}}','');return false;">-</button>
				<input id="goodsNumber{{goodsId}}" value="{{goodsNumber}}" style="width:60px;height:34px;"/>
				<button type="button" class="btn btn-info" onclick="add('Y','{{goodsId}}','{{remainNumber}}');return false;">+</button>
				<button type="button" class="btn btn-info" onclick="removeCartGoods('{{id}}');return false;">移除</button>
			</div>
		</div>
	{{/rows}}		 		 
	</script>
</body>
</html>
