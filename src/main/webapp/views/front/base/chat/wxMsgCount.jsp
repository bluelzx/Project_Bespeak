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
	<input type="hidden" value="${wxMsgCount.id}" id="wxMsgCountId">
	<input type="hidden" value="${userId}" id="userId">
	<div class="pz_main">
		<div class="c_0100_14">
			<div class="slide_teacher" id="slide_teacher">
				<div class="bd">
					<div class="ul_out_box" id="ul_out_box1">
						<div class="inbd" style="border-bottom: #d9d7d7 solid 1px;">
							<div id="noticeSwith" class="fr" style="text-align: center;margin-top: 5px;padding-bottom: 5px;">
								<button type="button" class="weui_btn weui_btn_mini weui_btn_primary pointer" style="margin-right:3px;" onclick="allMsg()">群发消息</button>
							</div>
							<!-- <div class="inbd_ul" id="goodsList">
								
							</div> -->
						</div>
						<div class="weui_article" style="border-bottom: #d9d7d7 solid 1px;padding:10px">
							<span style=" font-weight: 700;">今日剩余群发消息:</span>
								<h3 style="display: inline-block;color: #FF2400"><c:if test="${!empty wxMsgCount}">${wxMsgCount.maxCount}</c:if>
								<c:if test="${empty wxMsgCount}">3</c:if>
								</h3>
							<div>多品消息一次能发9个拍品(消息样式:单个/多个)</div>
						</div>
					</div>
					<div class="mainbox" id="iOther">
					<div class="mainsmall" id="auctionOnlineMain">
						<div id="main" role="main" style="border-bottom: 1px solid #CDCDCD; background: white;">
							<div style="padding: 0 5px;">
								<ul id="goodsList" class="mainul mainul1" style="width:100%">
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
				</div>
				<div id="resultTip" class="resultTip frontHide"></div>
				<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
			</div>
		</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>

	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	

	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->

	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/chat/wxMsgCount.js" title="v"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<li style="width:45%;float:left;margin: 5px;" 
				class="mainulli goods_bg pointer" onclick="checkedGoods({{id}});">
				<input type="checkbox" class="goodsChecked" id="goodsId_{{id}}" value="{{id}}" />
				<div >
				<span style="background: url({{picPath}}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
				<div style="color:black;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">{{goodsName}}</div>		
				{{#shopPrice}}
					<h3 style="color:red;">￥ {{shopPrice}}</h3>
				{{/shopPrice}}
				{{^shopPrice}}
					<h3 style="color:red;">议价</h3>
				{{/shopPrice}}
				</div>
			</li>
		{{/rows}}		 
	</script>
	
</body>
</html>
