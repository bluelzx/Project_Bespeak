<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body id="g-html">
      <div id="wrapper">
        <!--主体内容开始-->
        <div class="tn-top js-top xd-box">
           <a onclick="back()" class="back">
                <img src="/images/front/back.png" alt="">
            </a>
            <input type="hidden" value="${user.id}" id="userId" />
            <input type="hidden" value="${provider.shopId}" id="shopId" />
            <input type="hidden" value="${provider.id}" id="providerId" />
            <img src="${provider.avatar}" alt="" class="js-img">
            <span class="js-name">${provider.realName}</span>
            <div class="tn-line1 js-top-bt choice-pj1">
                    <a href="javascript:">
                        <img src="/images/front/pjx.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx.png" alt="" class="pj-like">
                    </a>
            </div>
        </div>
        <div class="xd-inf">
        	<input type="hidden" value="${goods.id}" id="goodsId" />
        	<input type="hidden" value="${goods.typeCode}" id="typeCode" />
            <span class="xd-name">${goods.goodsName}</span>
            <span class="xd-time" id="jiaqian">${goods.shopPrice}元/${goods.timeNum}小时</span>
            <input type="hidden" value="${goods.shopPrice}" id="shopPrice" />
            <input type="hidden" value="${goods.timeNum}" id="timeNum" />
            <div class="xd-jj-box">
                <input type="button" value="-" class="reduce">
                <input type="text" value="1" id="text_box">
                <input type="button" value="+" class="plus">
            </div>
        </div>
        <div class="xd-choice">
            <ul class="xd-cho-line1">
                <li>联系方式<span>${user.phone}</span> </li>
                <li class="xd-fuwu">服务方式<span class="xd-fs">请选择服务方式</span><i></i> </li>
                <input type="hidden"  id="addressId" />
                <input type="hidden"  id="address" />
                <input type="hidden"  id="city" />
                <input type="hidden"  id="province" />
                <li class="xd-address" onclick="unAddress()">服务地址<span id="address1">请选择服务地址</span><i></i> </li>
                <li class="xd-time">服务时间
                    <input class="weui_input" id="time-format" type="text" placeholder="请选择预约时间" readonly="">
                    <i></i> 
                </li>
                <li><input type="text" placeholder="特殊情况请留言" id="content"></li>
            </ul>
            <ul class="xd-cho-line2">
            	<input type="hidden"  id="couponDiscount" />
            	<input type="hidden"  id="couponMoney" />
            	<input type="hidden"  id="couponId" />
                <li class="xd-yhq" onclick="$('#coupon').popup();">优惠券<span id="couponName"></span><i></i> </li>
<%--             <li class="xd-yhq" onclick="lh.jump('/coupon/${goods.id}')">优惠券<span id="couponName"></span><i></i> </li> --%>
               <li>预付款 <span id="totalPrice"  class="xd-money">${goods.shopPrice}元</span> <input type="hidden" value="${goods.shopPrice}" id="shopPrice" /></li>
                 <input type="hidden" id="price" />
            </ul>
        </div>
        <div class="xd-careful">
            ${goods.attrConent4}
        </div>
        <a onclick="addBespeak()" class="xd-appo">确认预约</a>
    </div>
    <div class="motai">
        <div class="motai-fw">
            <p id="xd-jd">进店体验</p>
            <p id="xd-sm">上门服务</p>
        </div>
    </div>
  <div id="coupon" class="weui-popup-container">
  	<div class="weui-popup-overlay"></div>
  		<div class="weui-popup-modal">
  			 <div class="boxTop" id="data-container">
        </div>
  		</div>
	</div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
	 <script type="text/javascript" src="/js/front/provider/bespeak.js" title="v"></script>
	  <script id="template" type="x-tmpl-mustache">
        {{#rows}}
             <div class="yhqBox" onclick="choice({{id}})">
                <div class="lt yhq-left">
                    <p class="yhq-q">券</p>
                    <span class="yhq-span">优惠券</span>
                </div>
                <div class="rt yhq-right">
                    <p class="yhq-how">{{name}}</p>
					 <input type="hidden" value="{{money}}" id="money" />
					 <input type="hidden" value="{{discount}}" id="discount" />
                    <p class="yhq-lb">仅限{{attrStr2}}使用</p>
                    <p class="yhq-time">使用期限{{startTime}}至{{endTime}}</p>
                </div>
            </div>
        {{/rows}}
    </script>
</body>
</html>