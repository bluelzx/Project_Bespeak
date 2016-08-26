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
<body style="background-color:#f5f5f5;">
	<input type="hidden" value="${noPhone}" id="noPhone">
	<input type="hidden" value="${noPayPassword}" id="noPayPassword">
	<input type="hidden" value="${noEditPassword}" id="noEditPassword">
 	<div class="pz_main">
		<div class="c_0100_21">
		    <div class="t_0100_19">
		        <div class="l_500">
		            <div class="l_114" style="width:21%"><a href="javascript:void(0);">
		            <ul class="jydshop">
						<li onclick=""><img src="${user.avatar}" class="pointer"></li>
						<li style="margin: 5px;font-size: 16px;"> 
							<c:if test="${userId == user.id}">
		                       	<span style="color: #ffffff;"  onclick="lh.jumpR('/editUser/${user.id}');">修改</span>
	                       	</c:if>
	                    </li>
					</ul>
		            </a></div>
	                <div class="r_350" style="width:78%">
	                    <div class="t_350_1"><a href="javascript:void(0);">${user.username}</a>
	                    </div>
	                    <div class="t_350_2">
	                        <div class="l_ys">余额：<span class="span_5">￥</span><span class="span_5">${userFund.avaliableMoney}</span>
	                        </div>
	                    </div>
	                    <div class="t_350_2">
	                        <div class="l_ys">
	                        	<%-- <c:if test="${userId == user.id}">
	                        		<span><a style="color: #ffffff;" href="javascript:lh.jumpR('/editUser/${user.id}');">修改</a></span>
	                        	</c:if> --%>
	                        	<span class="r_cz" style="margin: 0px;">
		                        	<a href="javascript:void(0);" onclick="jumpToRecharge();">充值</a>
		                        </span>
	                        </div>
	                    </div>
	                </div>
		        </div>
	            <div class="r_59"><a href="javascript:lh.jumpR('/credit/${user.id}');>">个人<br />荣誉</a></div>
		    </div>
	        <div class="t_0100_20">
	            <div class="li_my pointer" onclick="lh.jumpR('/notice');">
	                <div id="noticeCt_userCtr" class="t_my">0</div>
	                <div class="d_my">消息</div>
	            </div>
	            <div class="li_my pointer" onclick="lh.jumpR('/fans');">
	                <div class="t_my">${focusCount}</div>
	                <div class="d_my">我的粉丝</div>
	            </div>
	            <div class="li_my pointer" onclick="lh.jumpR('/fans');">
	                <div class="t_my">${fansCount}</div>
	                <div class="d_my">我的关注</div>
	            </div>
	        </div>
	        <div class="t_0100_21">
	            <div class="title_4">
	                <div class="tit_4"><b><img src="/images/front/my_img2.png" width="21" height="24" /></b><span class="span_6">我的订单</span></div>
	                <div class="more_4"><a href="javascript:lh.jumpR('/cart');">查看全部商品&nbsp;<img src="/images/front/my_img3.png" width="12" height="20" /></a></div>
	            </div>
	            <div class="tt_0100_7">
	                <div class="li_order" onclick="lh.jumpR('/buyerBid');">
	                	<div class="img_order">
	                    	<div class="a_or">
	                        	<a href="#"><img src="/images/front/my_img4.png" width="40" height="30" /></a>
	                            <div class="pf_atten" style="z-index:0"><a href="#" id="goodsOffersCount" style="display:none;" class="attenbox"></a></div>
	                        </div>
	                	</div>
	                    <div class="tit_order"><a href="#">买家报价</a></div>
	                </div>
	                <div class="li_order" onclick="lh.jumpR('/waitPayMoney');">
	                	<div class="img_order">
	                    	<div class="a_or"><a href="#"><img src="/images/front/my_img5.jpg" width="40" height="30" /></a>
	                        		<div class="pf_atten" style="z-index:0"><a href="#" id="waitPayMoneyCount"  style="display:none;" class="attenbox"></a></div>
	                        </div>
	                	</div>
	                    <div class="tit_order"><a href="#">待付款</a></div>
	                </div>
	                <div class="li_order" onclick="lh.jumpR('/shipping');">
	                	<div class="img_order">
	                    	<div class="a_or"><a href="#"><img src="/images/front/my_img6.jpg" width="40" height="30" /></a>
	                        	<div class="pf_atten" style="z-index:0"><a href="#" id="shippingCount" style="display:none;" class="attenbox"></a></div>
	                        </div>
	                	</div>
	                    <div class="tit_order"><a href="javascript:void(0);">待发货</a></div>
	                </div>
	                <div class="li_order" onclick="lh.jumpR('/shipped');">
	                	<div class="img_order">
	                    	<div class="a_or"><a href="#"><img src="/images/front/my_img7.jpg" width="40" height="30" /></a>
	                        	<div class="pf_atten" style="z-index:0"><a href="#" id="shipedCount" style="display:none;" class="attenbox"></a></div>
	                        </div>
	                	</div>
	                    <div class="tit_order"><a href="javascript:void(0);">待收货</a></div>
	                </div>
	                <div class="li_order" onclick="lh.jumpR('/commenting');">
	                	<div class="img_order">
	                    	<div class="a_or"><a href="#"><img src="/images/front/my_img8.jpg" width="40" height="30" /></a>
	                        	<div class="pf_atten" style="z-index:0"><a href="#" id="commentingCount" style="display:none;" class="attenbox"></a></div>
	                        </div>
	                	</div>
	                    <div class="tit_order"><a href="javascript:void(0);">待评价</a></div>
	                </div>
	                <div class="li_order">
	                	<div class="img_order">
	                    	<div class="a_or"><a href="javascript:void(0);"><img src="/images/front/my_img9.jpg" width="40" height="30" /></a>
	                        	<div class="pf_atten" style="z-index:0"><a href="javascript:void(0);" id="returnGoodsCount" style="display:none;" class="attenbox"></a></div>
	                        </div>
	                	</div>
	                    <div class="tit_order"><a href="javascript:lh.jumpR('/returnGoods');">退款/售后</a></div>
	                </div>
	            </div>
	        </div>
	        <div class="t_0100_22">
	        	<div class="slide_shop" id="slide_shop">
		    	    <div class="hd">
		    	        <ul>
		    	            <li class="on pointer" onclick="lh.jumpR('/user');">财务管理</li>
		                    <li class="pointer" onclick="lh.jumpR('/myTrade');">我的交易</li>
		                    <li class="pointer" onclick="lh.jumpR('/userInfo');">个人信息</li>
			            </ul>
		    	    </div>
		            <div class="bd">
		            	<div class="shop_show">
		                	<div class="t_0100_23 botwoline">
		                	    <a href="javascript:lh.jumpR('/fundDetails');" class="a_finance">
		                        	<span class="span_7">资金明细</span>
		                            <span class="span_8">余额：${userFund.avaliableMoney}</span>
		                        </a>
		                	</div>
		                    <div class="t_0100_23 botwoline">
		                	    <a href="javascript:lh.jumpR('/withdraw');" class="a_finance">
		                        	申请提现
		                        </a>
		                	</div>
		                    <div class="t_0100_23 botwoline">
		                	    <a href="javascript:lh.jumpR('/payPasswordFind');" class="a_finance">
		                        	设置支付密码
		                        </a>
		                	</div>
		                    <div class="t_0100_23 botwoline">
		                	    <a href="javascript:lh.jumpR('/payPassSet');" class="a_finance">
		                        	找回支付密码
		                        </a>
		                	</div>
		                    <div class="t_0100_23 botwoline">
		                	    <a href="javascript:lh.jumpR('/bindBankCard');" class="a_finance">
		                        	绑定提现银行卡
		                        </a>
		                	</div>
		                    <div class="t_0100_23 botwoline">
		                	    <a href="javascript:lh.jumpR('/bonus');" class="a_finance">
		                        	我的红包
		                        </a>
		                	</div>
		                    <div class="t_0100_23 botwoline topwoline m_15_0_0_0">
		                	    <a href="javascript:;"  onclick="logout();return false;" class="a_finance" style="background:0;">
		                        	切换账号
		                        </a>
		                	</div>
		            	</div>
		            </div>
		    	</div>
	        </div>
		</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>	
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->

	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script src="/js/front/user/user.js" title="v"></script>
</body>
</html>
