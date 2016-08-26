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
<link rel="stylesheet" type="text/css" href="/css/front/dust.css" title="v" />
<link rel="stylesheet" type="text/css" href="/third-party/weui/weui.min.css"/>
</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<input type="hidden" id="shopId" value="${shopId}">
	<input type="hidden" id="moduleId" value="${moduleId}">
	<input type="hidden" id="userId" value="${userId}">
	<div class="c_0100_17" style="margin-top:50px;">
			<div class="slide_shop" id="slide_shop">
				<div class="extension">
					<div style="text-align: center;padding-top: 5px;padding-left:0px;padding-right:0px;">
						<!-- <span style="width:48%;border:1px solid #DA1919">推广分类：全部 ∨</span> -->
						<!-- <span id="goodsTypes" onclick="$('.navcdbox').show();" style="width:100%;border:1px solid #DA1919">藏品分类：在售 ∨</span> --><!-- width:48% -->
						<a href="javascript:;" class="weui_btn weui_btn_primary" onclick="$('.navcdbox').show();" id="showToast">藏品分类：在售 ∨</a>
					</div>
				</div>
				<div class="pz_main">
					<div class="mainbox" id="iOther">
						<div class="mainsmall" id="auctionOnlineMain">
							<div id="main" role="main" style="border-bottom: 1px solid #CDCDCD; background: white;">
								<div style="padding: 0 5px;">
									<ul id="goodsList" class="mainul mainul1" style="width:100%">
									</ul>
								</div>
								<div style="clear: both;"></div>
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
		<input type="hidden" value="${r}" id="r"/> 	
		<input type="hidden" value="${loginStatus}" id="loginStatus"/>
		 <div class="navcdbox" style="display: none;">
	            <div class="menu-listbox">
	                <div id="firstpane" class="top_menu fr"><!-- menu_list -->
	                    <p class="menu_head dbbstype" onclick="location.href='/gm/${moduleId}'">在售</p>
	                    <p class="menu_head dbbstype" onclick="location.href='/gm/saled/${moduleId}'">已售</p>
	                    <p class="menu_head dbbstype" onclick="location.href='/gm/undercarriage/${moduleId}'">下架</p>
	                    <p class="menu_head dbbstype" onclick="location.href='/gm/auction/${moduleId}'">已送拍</p>
	                    <p class="menu_head dbbstype" onclick="location.href='/gm/auctionning/${moduleId}'">拍卖中</p>
	                    <p class="menu_head dbbstype" onclick="location.href='/gm/like/${moduleId}'">喜欢</p>
	                    <p class="menu_head dbbstype" onclick="location.href='/gm/frozen/${moduleId}'">冻结</p>
	                    <c:if test="${moduleId == 5}">
	                    	<p class="menu_head dbbstype" onclick="location.href='/gm/goodsSource/${moduleId}'">商品来源</p>
	                    </c:if>
	                    <p class="menu_head current" onclick="$('.navcdbox').hide();">关闭</p>
	                </div>
	            </div>
	        </div>
	        <div class="pf_menu" id="Inst" style="display: none;"><!-- style="display: none;" -->
				<div class="top_menu">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="20%" class="menu_span1"><a href="javascript:;" onclick="$('.pf_menu,.cha').hide(200);">取消</a></td>
							<td width="20%" align="right" class="menu_span1" onclick="auctionGoods()"><a href="javascript:void(0);">确定</a></td>
						</tr>
					</table>
				</div>
				<div class="menu">
					<ul id="instList">
					
					</ul>
				</div>
				<div id="resultTip" class="resultTip frontHide"></div>
				<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
			</div>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%-- <%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 --> --%>
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/shop/managementGoods.js" title="v"></script>
	<%-- <c:if test="${moduleId == 5}">
		{{&goodsPrivilegeBtn}}
	</c:if> --%>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<li style="width:45%;float:left;margin: 5px;"  class="mainulli goods_bg">
				<span class="pointer" 
				<c:if test="${moduleId != 5}">
 					onclick="lh.jumpR('/goods/{{id}}?shopId={{shopId}}')" 
				</c:if>
				<c:if test="${moduleId == 5}">
 					onclick="lh.jumpR('/wsg/{{id}}/{{shopId}}/{{userId}}?moduleId={{moduleId}}')" 
				</c:if>
				style="background: url({{picPath}}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
				<h3 style="color:black;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">{{goodsName}}</h3>
				{{#shopPrice}}
					<h3>￥{{shopPrice}}</h3>
				{{/shopPrice}}
				{{^shopPrice}}
					<h3>议价</h3>
				{{/shopPrice}}
					<button style="padding: 6px 3px;" type="button" class="btn btn-info" onclick="goodsUndercarriage('{{id}}');return false;">下架</button>
					<button style="padding: 6px 3px;" type="button" class="btn btn-info" onclick="deleteGoods('{{id}}');return false;">删除</button>
					<button style="padding: 6px 3px;" type="button" class="btn btn-info" onclick="editGoods('{{id}}');return false;">修改</button>
					<button style="padding: 6px 3px;" type="button" class="btn btn-info" onclick="auctionGoodsId('{{id}}');return false;">送拍</button>
			</li>
	{{/rows}}		 		 
	</script>
	<script id="template1" type="x-tmpl-mustache">
	{{#rows}}
		<c:if test="${moduleId != 5}">
			<li id="inst_{{id}}" onclick="getInstId('{{id}}');" class="l1"><a href="javascript:void(0);" class="l1_a">{{name}}</a></li>	
		</c:if>
		<c:if test="${moduleId == 5}">
			<li id="inst_{{wholesaleId}}" onclick="getInstId('{{wholesaleId}}');" class="l1"><a href="javascript:void(0);" class="l1_a">{{name}}</a></li>	
		</c:if>
	{{/rows}}		 		 
	</script>
</body>
</html>
