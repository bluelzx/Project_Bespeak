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
<link rel="stylesheet" type="text/css" href="/third-party/swiper/swiper.3.1.7.min.css"/>
<style>
.swiper-pagination-bullet {background: white;}
.tempWrap{max-height: 300px;}
.banner {
    background: -webkit-gradient(linear, left top, left bottom, from(#999), to(#333333));
    background: -webkit-linear-gradient(top, #999, #333333);
    background: -moz-linear-gradient(top, #999, #333333);
    background: -o-linear-gradient(top, #999, #333333);
    background: -ms-linear-gradient(top, #999, #333333);
    background: linear-gradient(top, #999, #333333);
}
</style>
</head>
<body style="background-color: #f5f5f5; margin-top: 48px;">
	<%-- <input type="hidden" id="goodsId" value="${goods.id}" />
	<input type="hidden" id="username" value="${goods.username}" />
	<input type="hidden" id="userId" value="${goods.userId}" />
	<input type="hidden" id="shopId" value="${goods.shopId}" /> --%>
	<input type="hidden" id="shopId" value="${goods.shopId}" />
	<input type="hidden" id="promoteUserSerial" value="${promoteUserSerial}" />
	<input type="hidden" id="r" value="${r}" />
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<!-- Baner 开始 -->
	<div class="banner" style="float: none;">
		<div id="slideBox" class="slideBox">
			<!-- Banner图片 -->
			<div class="bd">
				<ul id="bannerUl" class="frontHide">
					<c:forEach var="pic" items="${GoodsPictureList}" varStatus="status">
						<li><a class="pic" href="javascript:void(0)"><img src="${pic.picPath}" style="height:280px;width: auto;" onclick="showImgView(${status.index});"/></a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="hd">
				<ul></ul>
			</div>
		</div>
	</div>
	<!-- Banner 结束 -->

	<!--  开始 -->
	<div class="jydtitle">
		<div class="jydtitdiv">
			<ul class="jydtitul">
				<li><span id="labBtitle">${goods.goodsSn}.${goods.goodsName}</span></li>
				<li class="jydtitulli" style="border-bottom: none;">
					<div class="jydtiyuldiv" id="iSPrice">
						<span class="yikou">一口价：无</span> <span id="lStartPrice" style="font-size: 14px;">
						价格：
							<c:if test="${!empty goods.shopPrice}">${goods.shopPrice}元</c:if> 
							<c:if test="${empty goods.shopPrice}">议价</c:if> 
						</span>
					</div>
					<div class="jydtiyuldiv" id="iGPrice" style="display: none;">RMB：無底價</div>
				</li>
				<li class="jydtitulli1" id="iChange">
					<div class="jydtitli jydtitlima"></div>
					<div style="clear: both;"></div>
				</li>
				<li id="details" class="jydtitulli1">
					<div class="jydtitli" style="color: red" id="hide4">
						<span id="labBbear">运费： <c:if test="${empty goods.postageFee || goods.postageFee<=0}">卖家承担</c:if> <c:if test="${goods.postageFee > 0}">到付</c:if>
						</span>
					</div>
					<div class="jydtitli" style="color: red" id="hide4">
						<span id="labBbear">是否支持7天退货： <c:if test="${empty goods.isSevenReturn || goods.isSevenReturn == 1}">支持</c:if> <c:if test="${goods.isSevenReturn == 2}">不支持</c:if>
						</span>
					</div>
					<%-- <div class="jydtitli" style="color: red" id="hide5">
						<span id="labBpacking">包装费： <c:if test="${empty goods.packFee || goods.packFee<=0}">卖家承担</c:if> <c:if test="${goods.packFee > 0}">${goods.packFee}</c:if>
						</span>
					</div> --%>
					<div class="jydtitli">
						<span id="labBcontent">描述：${goods.goodsDescription}</span>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!-- <div class="jydtitle" id="iTime">
		<div class="jydtitdiv">
			<div class="endtime">
				<strong id="remainTime">距离开拍：</strong><span class="timer">0天08时10分08秒</span>
			</div>
		</div>
	</div> -->

	<div class="jydtitle" id="iPrice" style="display: none;">
		<div class="jydtitdiv">
			<ul class="jydsubmit">
				<li class="jyddabao"><span class="jyddbspan">进行担保交易,七天可退换货</span></li>
				<li>
					<div style="clear: both;"></div>
				</li>
			</ul>
		</div>
	</div>

	<div class="jydtitle">
		<div class="jydtitdiv">
			<a>
				<ul class="jydshop">
					<li onclick=""><img src="${goods.shopLogo}" class="pointer" onclick="location.href = '/shop/${goods.shopId}'"/></li>
					<li class="comeinshop pointer" onclick="location.href = '/shop/${goods.shopId}'">进入店铺</li>
					<li onclick="location.href = '/shop/${goods.shopId}'" class="shoptittle pointer"><span>${goods.shopName}</span><br></li>
					<li class="jyddabao"><span class="jyddbspan">进行微拍客担保交易,七天可退换货</span></li>
					<div style="clear: both;"></div>
				</ul>
			</a>
		</div>
	</div>

	<div style="padding: 0 8px; cursor: pointer;" onclick="location.href='/tradeDesc'">
		<div class="trade_tip_bar">买卖交易规则说明</div>
	</div>
	<div>
		<span>报价:</span>
		<div class="jydtitdiv">
			<c:if test="${!empty goodsOffersList}">
				<c:forEach var="goodsOffers" items="${goodsOffersList}">
					<div class="newsdiv c_0100_3" style="padding:5px;">
						<div style="display:inline-block"><img src="${goodsOffers.userAvatar}" style="width:40px;" /></div>
						<span>${goodsOffers.userName}</span>
						<span style="padding-left:10px;"><fmt:formatDate value="${goodsOffers.createdAt}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span>
						<span style="padding-left:10px;">寻过价</span>
						<span style="padding-left:10px;">
							<c:if test="${goodsOffers.offerStatus == 1}">
								等待答复
							</c:if>
							<c:if test="${goodsOffers.offerStatus == 4}">
								出价被拒绝
							</c:if>
						</span>
						
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty goodsOffersList}">
				<div class="newsdiv c_0100_3"><span>暂无报价</span></div>
			</c:if>
			<!-- <textarea id="content" style="height:60px;width:100%"></textarea> -->
		</div>
	</div>
	<div class="jydtitle" id="iTime">
		<span>评论:</span>
		<div class="jydtitdiv">
			<c:if test="${!empty commentList}">
				<c:forEach var="comment" items="${commentList}">
					<div class="newsdiv c_0100_3" style="padding:5px;">
						<div style="display:inline-block"><img src="${comment.userAvatar}" style="width:40px;" /></div>
						<span>${comment.userName}:${comment.content}</span>
						<span style="float:right;"><fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
					</div>
				</c:forEach>
			</c:if>
			<c:if test="${empty commentList}">
				<div class="newsdiv c_0100_3"><span>暂无评论</span></div>
			</c:if>
			<!-- <textarea id="content" style="height:60px;width:100%"></textarea> -->
		</div>
		<button onclick="showCommentWin('${goods.id}');return false;" class="weui_btn weui_btn_disabled weui_btn_primary">评论</button>
	</div> 
	<div class="mainbox" id="iOther">
		<div class="mainsmall" id="auctionOnlineMain">
			<div id="main" role="main" style="border-bottom: 1px solid #CDCDCD; background: white;">
				<div class="news" style="margin-bottom: 8px; font-size: 14px; padding: 0 8px;">
					<div class="newsdiv">
						<span class="newsfk"></span> <span>其他推荐商品</span>
					</div>
				</div>
				<div style="padding: 0 5px;">
					<ul id="AuctionCollectionLeft" class="mainul mainul1" style="width:100%">
						<%-- <c:forEach items="${goodsList}" var="goods" step="2">
							<li onclick="location.href='/professionGoodsDetail?goodsId=${goods.id}&auctionId=1'" class="mainulli goods_bg"><span
								style="background: url(${goods.picPath}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
								<h3 style="color: #838381;">编号.${goods.goodsSn}</h3></li>
						</c:forEach> --%>
					</ul>
					<%-- <ul id="AuctionCollectionRight" class="mainul mainul2">
						<c:forEach items="${goodsList}" var="goods" begin="1" step="2">
							<li onclick="location.href='/professionGoodsDetail?goodsId=${goods.id}&auctionId=1'" class="mainulli goods_bg"><span
								style="background: url(${goods.picPath}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
								<h3 style="color: #838381;">编号.${goods.goodsSn}</h3></li>
						</c:forEach>
					</ul> --%>
				</div>
				<div style="clear: both;"></div>
			</div>
			<!-- <div class="loading">对不起，已经加载完啦！</div> -->
			<div id="resultTip" class="resultTip frontHide"></div>
			<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
		</div>
	</div>

	<!--  结束 -->

	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<div class="pz_menu">
		<ul id="top_header" class="jydfoot">
			<c:if test="${empty goods.overCollect}">
				<li id="beginNotice" style="background-image: url(/images/front/day_img2.png);background-position: 3% 10px;" onclick="addCollectGoods(${goods.id});return false;" class="jydfootli4 jydfootlip2 jydfootli8 remind pointer" state="1">喜欢</li>
			</c:if>
			<c:if test="${!empty goods.overCollect}">
				<li id="beginNotice" style="background-image: url(/images/front/day_img2.png);background-position: 3% 10px;" onclick="cancelCollectGoods(${goods.overCollect});return false;" class="jydfootli4 jydfootlip2 jydfootli8 remind pointer" state="1">取消喜欢</li>
			</c:if>
			<li id="chat" style="background-position: 8% -450px;" onclick="location.href='/chat/${goods.shopUserId}?gId=${goods.id}'" class="jydfootli3 pointer">咨询卖家</li>
			<c:if test="${goods.mainStatus == 76 || goods.mainStatus == 1}">
				<li id="offerShow" onclick="showAutoOffer();" class="jydfootli1 jydfootlip1 pointer" style=" text-indent: 0;" state="1">报价</li>
			</c:if>
		</ul>
	</div>

	<div id="offerWin" class="szweituo" style="display: none;z-index:1">
		<div class="szweituodiv">
			<div class="szwtcontent">
				<!-- <h4>设置委托出价</h4> -->
				<div class="szwtinput">
					<div id="offerTip" class="szwtzuigao">输入报价金额(元):</div>
					<div id="offerIptDiv" class="szwtinpk">
						<form id="entrustFrom">
							<span> <input type="tel" id="topPrice" placeholder="请在此输入报价金额" value="">
							</span>
						</form>
						<div style="clear: both;"></div>
					</div>
					<div class="szwtbutton">
						<span id="offerCancel" onclick="doAutoOffer('cancel')" class="szwtbutr entrusta2 pointer">取消</span> 
						<span id="offerSave" onclick="doAutoOffer('save',{goodsId:'${goods.id}',shopId:'${goods.shopId}',userId:'${goods.shopUserId}'})" 
							class="szwtbutl entrusta1 pointer">确定
						</span>
						<div style="clear: both;"></div>
					</div>
					<div class="szwtwhat">什么是报价？</div>
					<div class="szwtmiaosu">商家同意您的报价后即生成订单</div>
				</div>
			</div>
		</div>
	</div>
	<div id="maskDiv" class="">&nbsp;</div>
	
	<div class="weui_dialog_confirm" id="comment" style="display:none;">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
        	<input type="hidden" id="objectId" >
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">请输入评论内容</strong></div>
            <div class="weui_dialog_bd">
            	<div class="weui_cell_bd weui_cell_primary">
            		<div style="display:none;color:red;" id="tips"></div>
                    <textarea class="weui_textarea" id="content" placeholder="请输入评论" rows="3"></textarea>
                    <div class="weui_textarea_counter"><span>0</span>/200</div>
                </div>
            </div>
            <div class="weui_dialog_ft">
                <a href="javascript:;" onclick="closeCommentWin()" class="weui_btn_dialog default">取消</a>
                <a href="javascript:;" onclick="addGoodsComment()" class="weui_btn_dialog primary">确定</a>
            </div>
        </div>
    </div>
	
	<!-- 图片展示 开始 -->
	<div id="imagePreview" onclick="closeImgView();" class="yipu-device" style="height:0px;">
		<div class="pre_title">
			<p class="pre_subhead"></p>
			<p class="pre_subhead"></p>
		</div>
		<span id="pre_closepic" style="color: #6CB4E5; position: absolute; top: 10px; z-index: 9999; right: 10px; cursor: pointer;display: none;">关闭</span>
		<div id="pre_container" class="swiper-container" style="height:100%;width:100%;">
			<div class="swiper-wrapper">
				<c:forEach var="pic" items="${GoodsPictureList}">
				 	<div class="swiper-slide yipu-middle" style="text-align: center;"><img src="${pic.picPath}"/></div>
				</c:forEach>
		    </div>
		    <!-- 如果需要分页器 -->
		    <div class="swiper-pagination"></div>
    		<!-- 如果需要滚动条 -->
    		<div class="swiper-scrollbar"></div>
		</div>
	</div>
	<!-- 图片展示 结束 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script src="/js/front/shop/commodityGoodsDetail.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<li style="width:45%;float:left;margin: 5px;" onclick="location.href='/goods/{{id}}?shopId={{shopId}}'" class="mainulli goods_bg pointer">
				<span style="background: url({{picPath}}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
				<div style="color:black;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">{{goodsName}}</div>
				{{#shopPrice}}
					<h3 style="color:red;">￥ {{shopPrice}}</h3>
				{{/shopPrice}}
				{{^shopPrice}}
					<h3 style="color:red;">议价</h3>
				{{/shopPrice}}
			</li>
		{{/rows}}
	</script>
</body>
</html>
