<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/third-party/emoji-picker/css/emoji.css"/>
<!-- <link rel="stylesheet" type="text/css" href="/third-party/emoji-picker/css/cover.css"/> -->
<!-- <link rel="stylesheet" type="text/css" href="/third-party/emoji-picker/css/cover.scss"/> -->
<link rel="stylesheet" type="text/css" href="/third-party/emoji-picker/css/nanoscroller.css"/>
<link rel="stylesheet" type="text/css" href="/css/front/call.css" title="v" />
</head>
<body style="background-color: #f5f5f5;">
	<input type="hidden" id="shopId" value="${shop.id}">
	<input type="hidden" id="userId" value="${userId}">
	<input type="hidden" id="wholesaleId" value="${wholesale.id}">
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
					<a href="javascript:;" ><img src="/images/front/shop_img6.png" width="23" height="23" /></a>
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
					<a href="javascript:void(0);">${shop.name}
					</a>
					<c:if test="${userId == currentUserId}">
						<a href="/editShop">修改</a>
					</c:if>
				</div>
				<div class="tt_0100_2">
					<a href="javascript:void(0);">关注</a>&nbsp;${focusMeCount}
					<a href="javascript:void(0);">&nbsp;|&nbsp;粉丝&nbsp;${meFocusCount}</a>&nbsp;|&nbsp;
					<%-- <a href="/shopComment/${userId}">评价&nbsp;${commentCount}</a> --%>
					<a href="/shopComment/${userId}">评价&nbsp;${commentCount}</a>
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
							<li class="pointer on"  onclick="location.href='/shop/${userId}'">主页</li>
							<li class="pointer" onclick="location.href='/sale/${userId}'">在售</li>
							<li class="pointer" onclick="location.href='/like/${userId}'">喜欢</li>
						</c:if>
						<c:if test="${!empty wholesale}">
							<li class="pointer on" style="width:25%" onclick="location.href='/shop/${userId}'">主页</li>
							<li class="pointer" style="width:25%" onclick="location.href='/sale/${userId}'">在售</li>
							<li class="pointer" style="width:25%" onclick="location.href='/like/${userId}'">喜欢</li>
							<li class="pointer" style="width:25%" onclick="location.href='/ws/${userId}'">批发</li>
						</c:if>
					</ul>
				</div>
				<div class="bd">
					<div class="shop_show">
						<div class="t_0100_11">
							<div class="title_3">
								<div class="tit_3">基本信息</div>
							</div>
							<%-- <div class="t_0100_12 botline">
								<div class="l_110">手机号</div>
								<div class="r_568">
									<span  class="bfom5">${shop.phone}</span> 
								</div>
							</div> --%>
							<div class="t_0100_12 botline">
								<div class="l_110" style="width:20%">店铺介绍</div>
								<div class="r_568" style="width:80%">
									<!-- <span  class="bfom5"></span>  -->
									<span class="weui_textarea" onclick="frontBaseAlert('${shop.remark}')">${shop.remark}</span>
								</div>
							</div>
							<div class="t_0100_12">
								<div class="l_110" style="width:20%">商圈</div>
								<div class="r_568" style="width:80%">
									<span  class="bfom5">${shop.antiqueCityName}</span>
								</div>
							</div>
							<div class="t_0100_12">
								<button onclick="showCommentWin('${shop.id}');return false;" class="weui_btn weui_btn_disabled weui_btn_primary">评论</button>
							</div>
						</div>
						<div class="t_0100_11" id="collectGoods">
							<div class="title_3">
								<div class="tit_3">最新藏品</div>
								<div class="more_3">
									<a href="/sale/${userId}"><img src="/images/front/shop_img9.png" width="14" height="22" /></a>
								</div>
							</div>
							<div class="hd">
								<ul>
									<!-- <li id="goodsType2" class="pointer on" style="width:20%" onclick="commonLoadGoods(2)">微拍</li>
									<li id="goodsType3" class="pointer" style="width:20%" onclick="commonLoadGoods(3)">即时拍</li>
									<li id="goodsType4" class="pointer" style="width:20%" onclick="commonLoadGoods(4)">专场</li> -->
									<li id="goodsType1" class="pointer  on" style="width:20%" onclick="commonLoadGoods('1','')">店铺</li>
									<c:if test="${!empty wholesale.id}">
										<li id="goodsType5" class="pointer" style="width:20%" onclick="commonLoadGoods('5','${wholesale.id}')">批发城</li>
									</c:if>
								</ul>
							</div>
							<div class="slide_cp" id="slide_cp_three0">
								<div class="obd" >
									<ul class="ul_gg" id="shopGoods">
										
									</ul>
								</div>
								<div class="ohd">
									<ul></ul>
								</div>
							</div>
						</div>
						<div class="t_0100_11">
							<div class="title_3">
								<div class="tit_3">他的粉丝也关注</div>
							</div>
							<div class="t_0100_13">
								<ul>
									<c:forEach var="fans" items="${fansList}">
										<li>
											<div class="l_60_3">
												<a href="/shop/${fans.userId}"><img src="${fans.userAvatar}" width="100%" /></a>
											</div>
											<div class="l_315">
												<a href="/shop/${fans.userId}"><nobr>${fans.userName}</nobr></a>
											</div>
											<div class="r_gz">
												<c:if test="${fans.overFoucs == 0}">
													<a href="javascript:;"  class="a_gz" onclick="focusHe('${fans.userId}');return false;">+关注</a>
												</c:if>
												<c:if test="${fans.overFoucs != 0}">
													<a href="javascript:;" class="a_qxgz" onclick="cancelFocus('${fans.userId}');return false;" >取消关注</a>
												</c:if>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<div class="pz_down">
			<div class="c_0100_9"></div>
		</div>
		<div class="c_0100_18 " style="position: fixed;bottom: 0;max-width: 640px;min-width: 320px;">
			<ul>
				<li style="width:33.3%"><a href="/chat/${userId}" class="a_say">在线咨询</a></li>
				<li style="width:33.3%"><a href="tel:${shop.phone}>" class="a_say">拨打电话</a></li>
				<li style="width:33.3%">
					<c:if test="${fans.overFoucs == 0}">
						<a href="javascript:;" class="a_say" onclick="focusHe('${userId}');return false;">+关注</a>
					</c:if>
					<c:if test="${fans.overFoucs != 0}">
						<a href="javascript:;" class="a_nosay" onclick="cancelFocus('${userId}');return false;">取消关注</a>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="weui_dialog_confirm" id="comment" style="display:none;">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
        	<input type="hidden" id="objectId" >
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">请输入评论内容</strong></div>
            <div class="weui_dialog_bd">
            	<div class="weui_cell_bd weui_cell_primary">
            		<div style="display:none;color:red;" id="tips"></div>
	            		 <p class="lead emoji-picker-container">
	                    	<textarea class="weui_textarea form-control textarea-control" id="content" placeholder="请输入评论" rows="6" data-emoji-input="unicode" maxlength="1000" data-emojiable="true"></textarea>
	                    </p>
                    <div class="weui_textarea_counter"><span>0</span>/200</div>
                </div>
            </div>
            <div class="weui_dialog_ft">
                <a href="javascript:;" onclick="closeCommentWin()" class="weui_btn_dialog default">取消</a>
                <a href="javascript:;" onclick="addGoodsComment()" class="weui_btn_dialog primary">确定</a>
            </div>
        </div>
    </div>
	
	<div class="weui_dialog_confirm" id="creditMarginShow" style="display:none;">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
        	<input type="hidden" id="shopId" >
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">请输入您的支付密码</strong></div>
            <div class="weui_dialog_bd">
            	<div style="display:none;color:red;" id="tip"></div>
            	<div class="weui_cell_bd weui_cell_primary" style="display:none;">
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
	
	<%@ include file="/views/front/common/z_div_call.htm"%><!-- 出价面板 -->
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
	<script type="text/javascript" src="/third-party/emoji-picker/js/config.js"></script>
	<script type="text/javascript" src="/third-party/emoji-picker/js/nanoscroller.min.js"></script>
	<script type="text/javascript" src="/third-party/emoji-picker/js/tether.min.js"></script>
	<script type="text/javascript" src="/third-party/emoji-picker/js/util.js"></script>
	<script type="text/javascript" src="/third-party/emoji-picker/js/emoji-picker.js"></script>
	<script type="text/javascript" src="/third-party/emoji-picker/js/jquery.emojiarea.js"></script>
	<script type="text/javascript" src="/js/common/call.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/shop.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/creditMargin.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
		<div class="div_gg" style="width: initial;">
			{{^moduleId}}
				<a href="/goods"><img src="{{picPath}}"  style="max-height: 60px;overflow: hidden;"/></a>
			{{/moduleId}}
			{{#moduleId}}
				<a href="/wsGoods"><img src="{{picPath}}"  style="max-height: 60px;overflow: hidden;"/></a>
			{{/moduleId}}
		</div>
	{{/rows}}
	</script>
</body>
</html>
