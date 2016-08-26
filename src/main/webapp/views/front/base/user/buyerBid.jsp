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
<link rel="stylesheet" type="text/css" href="/css/front/userCenter.css" title="v" />
</head>
<body style="background-color:#f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="pz_main" style="margin-top:50px;">
 		<input type="hidden" id="userId" value="${userId}">
		<div class="mainbox" id="iOther">
				<div class="mainsmall" id="auctionOnlineMain">
					<div id="main" role="main" style="border-bottom: 1px solid #CDCDCD; background: white;">
						<div style="padding: 0 5px;">
							<ul id="buyerBidList" class="mainul mainul1" style="width:100%">
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
		<div id="resultTip" class="resultTip frontHide"></div>
		<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
	</div>
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<div id="offerWin" class="szweituo" style="display: none;">
		<div class="szwtbutton">
			<div class="szweituodiv">
				<div class="szwtcontent">
					<div class="szwtinput">
						<input type="hidden" id="ids" >
						<div id="tips" style="margin:15px;"></div>
						<span id="offerCancel" onclick="doAutoOffer('cancel')" class="szwtbutr entrusta2 pointer">取消</span> 
						<span id="offerSave" onclick="doAutoOffer('save')" class="szwtbutl entrusta1 pointer">确定</span>
						<div style="clear: both;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dealPriceWin" class="szweituo" style="display: none;z-index:1">
		<div class="szweituodiv">
			<div class="szwtcontent">
				<!-- <h4>设置委托出价</h4> -->
				<div class="szwtinput">
					<div id="offerTip" class="szwtzuigao">生成订单:</div>
					<div id="offerIptDiv" class="szwtinpk">
						<form id="entrustFrom">
							<input type="hidden" id="id">
							<input type="hidden" id="goodsId">
							<input type="hidden" id="goodsOrderUserId">
							买家用户名:<span id="username"></span><br/>
							商品名称:<span id="goodsName"></span>
							<span> <input type="tel" id="dealPrice" placeholder="请在此输入最终金额" >
							</span>
						</form>
						<div style="clear: both;"></div>
					</div>
					<div class="szwtbutton">
						<span id="offerCancel" onclick="dealPriceOffer('cancel')" class="szwtbutr entrusta2 pointer">取消</span> 
						<span id="offerSave" onclick="dealPriceOffer('save')" class="szwtbutl entrusta1 pointer">确定</span>
						<div style="clear: both;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script src="/js/front/user/buyerBid.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<li style="width:100%;float:left;margin: 5px;" 
				class="mainulli goods_bg pointer">
				<span onclick="lh.jumpR('/goods/{{goodsId}}?shopId={{shopId}}');" style="background: url({{picPath}}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;">
				</span>
				<div style="font-size: 16px;margin: 5px;width: 98%;">&nbsp;&nbsp;商品名称：{{goodsName}}</div>
				<div style="font-size: 16px;;margin: 5px;">&nbsp;&nbsp;报价人：{{userName}}</div>
				<div style="font-size: 16px;margin: 5px;">&nbsp;&nbsp;报价：{{offerPrice}}元</div>
				{{#promoteMoney}}
					<div style="font-size: 16px;margin: 5px;">&nbsp;&nbsp;推广费用：{{promoteMoney}}</div>
				{{/promoteMoney}}
				{{^promoteMoney}}
					<div style="font-size: 16px;margin: 5px;">&nbsp;&nbsp;推广费用：无</div>
				{{/promoteMoney}}
				<br/>
				{{#dealPrice}}
					<div style="font-size: 16px;margin: 5px;>&nbsp;&nbsp;成交价格：{{dealPrice}}</div>
				{{/dealPrice}}
				{{^dealPrice}}
					{{^offerStatus}}
						<div style="font-size: 16px;margin: 5px;>&nbsp;&nbsp;不同意</div>
					{{/offerStatus}}
					{{#offerStatus}}
					<div style="margin: 5px;">
						<button type="button" onclick="disagreeGoodsOffers('{{id}}');return false;" class="btn btn-danger" style="padding: 6px;">拒绝</button>
						<button type="button" onclick="agreeGoodsOffers('{{id}}','{{offerPrice}}','{{goodsId}}','{{goodsName}}','{{username}}','{{userId}}');return false;" class="btn btn-success" style="margin: 2px;float:right;">同意</button>
						<button type="button" class="btn btn-primary" style="margin: 2px;float:right;" onclick="lh.jumpR('/chat/{{userId}}?gId={{goodsId}}');">私聊</button>
					</div>
					{{/offerStatus}}		
				{{/dealPrice}}
			</li>
		{{/rows}}		 
	</script>
</body>
</html>
