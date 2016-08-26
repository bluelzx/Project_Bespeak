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
	<input type="hidden" id="userId" value="${userId}"/>
	<div class="pz_main">
		<div class="c_0100_16">
			<div class="pf_return_home">
				<span class="pointer" style="color:white;font-size: 18px;" onclick="history.back(-1);return false;">返回</span>
				<div class="threepoint" style="margin:0"><!-- threepoint -->
					<a href="javascript:;" class="a_po" onclick="$('.pf_point').slideToggle(0)"><img src="/images/front/top_img1.png" width="25" height="25" /></a>
					<div class="pf_point" style="display: none;">
						<div class="img_point">
							<img src="/images/front/top_img2.png" width="8" height="7" />
						</div>
						<div class="ulist_point">
							<c:if test="${userId == currentUserId}">
								<div class="ist_point">
									<a href="/goodsManageType">产品管理</a>
								</div>
							</c:if>
							<div class="ist_point">
								<a href="/creditShop/${shop.id}">店铺信誉</a>
							</div>
							<c:if test="${userId == currentUserId}">
								<c:if test="${!empty wholesale}">
									<div class="ist_point">
										<a href="/editWholesale/${wholesale.id}">批发城设置</a>
									</div>
								</c:if>
							</c:if>
							<c:if test="${userId == currentUserId}">
								<c:if test="${!empty forumMember}">
									<div class="ist_point">
										<a href="/forumArticle/${forumMember.forumId}">我的论坛</a>
									</div>
								</c:if>
							</c:if>
							<c:if test="${userId != currentUserId}">
								<c:if test="${!empty forumMember}">
									<div class="ist_point">
										<a href="/forumArticle/${forumMember.forumId}">他的论坛</a>
									</div>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
				<div class="ewm" onclick="showQRCode('show', '${url}', '${shop.logo}');">
					<a href="javascript:void(0);"><img src="/images/front/shop_img6.png" width="23" height="23" /></a>
				</div>
				<div class="home_pic">
					<a href="/"><img src="/images/front/shop_img7.png" width="27" height="23" /></a>
				</div>
			</div>
			<div class="t_0100_10">
				<div class="m_100">
					<a href="javascript:void(0);" class="a_people"><img src="${shop.logo}" width="80" height="80" /></a>
				</div>
				<div class="tt_0100_1">
					<a href="javascript:void(0);">${shop.name}</a>
					<c:if test="${userId == currentUserId}">
						<a href="/editShop">修改</a>
					</c:if>
				</div>
				<div class="tt_0100_2">
					<a href="javascript:void(0);">关注</a>&nbsp;${focusMeCount}<a href="javascript:void(0);">&nbsp;|&nbsp;粉丝&nbsp;${meFocusCount}</a>&nbsp;|&nbsp;<a href="/shopComment/${userId}">评价&nbsp;${commentCount}</a>
				</div>
				<div class="tt_0100_3">
					<c:if test="${shop.isSevenReturn == 2}">
						<img src="/images/front/shop_img3.png" width="24" height="24" />&nbsp;七天退换&nbsp;&nbsp;&nbsp;
					</c:if>
					<c:if test="${shop.isRealAuth == 2}">
						<img src="/images/front/shop_img4.png" width="24" height="24" />&nbsp;&nbsp;实名认证&nbsp;&nbsp;&nbsp;
					</c:if>
					<c:if test="${shop.isWarrant == 2}">
						<img src="/images/front/shop_img5.png" width="24" height="24" />&nbsp;担保交易
					</c:if>
					<c:if test="${shop.creditMargin > 0}">
						<img src="/images/front/shop_img4.png" width="24" height="24" />&nbsp;已缴纳信誉保证金:${shop.creditMargin}元
						<c:if test="${userId == currentUserId}">
							<button id="add_38" class="weui_btn weui_btn_mini weui_btn_primary  pointer" style="margin:2px;color: rgb(255, 255, 255);" onclick="showCreditMarginWin(${shop.id})">增加保证金</button>
						</c:if>
					</c:if>
					<c:if test="${shop.creditMargin <= 0}">
						<img src="/images/front/shop_img4.png" width="24" height="24" />&nbsp;未缴纳信誉保证金
						<c:if test="${userId == currentUserId}">
							<button id="add_38" class="weui_btn weui_btn_mini weui_btn_primary  pointer" style="margin:2px;color: rgb(255, 255, 255);" onclick="showCreditMarginWin(${shop.id})">增加保证金</button>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
		<div class="c_0100_17">
			<div class="slide_shop" id="slide_shop">
				<div class="hd">
					<ul>
						<c:if test="${empty wholesale}">
							<li class="pointer"  onclick="location.href='/shop/${userId}'">主页</li>
							<li class="pointer" onclick="location.href='/sale/${userId}'">在售</li>
							<li class="pointer on" onclick="location.href='/like/${userId}'">喜欢</li>
						</c:if>
						<c:if test="${!empty wholesale}">
							<li class="pointer" style="width:25%" onclick="location.href='/shop/${userId}'">主页</li>
							<li class="pointer" style="width:25%" onclick="location.href='/sale/${userId}'">在售</li>
							<li class="pointer on" style="width:25%" onclick="location.href='/like/${userId}'">喜欢</li>
							<li class="pointer" style="width:25%" onclick="location.href='/ws/${userId}'">批发</li>
						</c:if>
					</ul>
				</div>
				<!-- <div class="bd">
					<div class="t_0100_11">
						<div class="slide_cp" >
							<div class="obd"  style="margin-top:10px;">
								<ul class="ul_gg" id="collectGoods">
								</ul>
							</div>
						</div>
					</div>
					<div id="resultTip" class="resultTip frontHide"></div>
					<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
				</div> -->
				<div class="mainbox" id="iOther">
					<div class="mainsmall" id="auctionOnlineMain">
						<div id="main" role="main" style="border-bottom: 1px solid #CDCDCD; background: white;">
							<div style="padding: 0 5px;">
								<ul id="collectGoods" class="mainul mainul1" style="width:100%">
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
		<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
		
		<div class="weui_dialog_confirm" id="creditMarginShow" style="display:none;">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
        	<input type="hidden" id="shopId" >
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">请输入需要增加的保证金</strong></div>
            <div class="weui_dialog_bd">
            	<div style="display:none;color:red;" id="tip"></div>
            	<div class="weui_cell_bd weui_cell_primary">
            		  <label >保证金:</label>
            		  <input class="weui_input" style="width:80%" type="text" id="creditMargin"  placeholder="请输入需要增加的保证金">
                </div>
            	<div class="weui_cell_bd weui_cell_primary">
            		<label >支付密码:</label>
            		<input class="weui_input" style="width:80%" type="password" id="payPassword"  placeholder="请输入支付密码">
                </div>
            </div>
            <div class="weui_dialog_ft">
                <a href="javascript:;" onclick="closeCreditMarginWin()" class="weui_btn_dialog default">取消</a>
                <a href="javascript:;" onclick="addCreditMargin()" class="weui_btn_dialog primary">确定</a>
            </div>
        </div>
    </div>
    
		<input type="hidden" value="${r}" id="r"/> 	
		<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	</div>
	</div>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>

	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/shop/collectGoods.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/creditMargin.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<li style="width:45%;float:left;margin: 5px;" onclick="location.href='/goods/{{goodsId}}?shopId={{shopId}}'" class="mainulli goods_bg pointer">
				<span style="background: url({{picPath}}) center center no-repeat #F2F2F2; background-position: 50% 50%; background-size: contain;"></span>
				{{goodsName}}		
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
