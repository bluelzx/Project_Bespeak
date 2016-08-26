<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<title>预约服务</title>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body id="g-html">
     <div id="wrapper">
        <!--主体内容开始-->
          <input type="hidden" value="${user.id}" id="userId" />
        <input type="hidden" value="${goods.shopId}" id="shopId" />
        <input type="hidden" value="${goods.id}" id="goodsId" />
        <input type="hidden" value="${goods.typeCode}" id="typeCode" />
        <div class="tn-top" style="height:1.6rem;">
            <a onclick="back()" class="back">
                <img src="/images/front/back.png" alt="">
            </a>
        </div>
        <div class="xd-inf">
            <span class="xd-name">${goods.goodsName}</span>
             <input type="hidden" value="${goods.timeNum}" id="timeNum" />
            <span class="xd-time" id="number"></span>
        </div>
        <div class="xd-choice">
            <ul class="xd-cho-line1">
                <li>联系方式<span>${user.phone}</span> </li>
                <li class="xd-fuwu">服务方式<span class="xd-fs">请选择服务方式</span><i></i> </li>
                  <input type="hidden"  id="addressId" />
                <input type="hidden"  id="address" />
                <input type="hidden"  id="city" />
                <input type="hidden"  id="province" />
                <li class="xd-address " onclick="unAddress()">服务地址<span id="address1">请选择服务地址</span><i></i> </li>
                <li class="xd-time">服务时间
                    <input class="weui_input" id="time-format" type="text" placeholder="请选择预约时间" readonly="">
                    <i></i> 
                </li>
                <li><input type="text" placeholder="特殊情况请留言"></li>
            </ul>
            <ul class="choice-ul">
                <li class="xd-time" onclick="$('#provider').popup();">按摩师
                    	<input  id="providerId" hidden="hidden" >
                        <img  id="avatar" alt="" class="choice-img" hidden="hidden">
                        <span class="choice-name" id="providerName">不选择则系统指定</span>
                        <i></i>
                </li>
            </ul>
            <ul class="xd-cho-line2">
            <input type="hidden"  id="couponDiscount" />
            	<input type="hidden"  id="couponMoney" />
            	<input type="hidden"  id="couponId" />
                <li class="xd-yhq" onclick="$('#coupon').popup();">优惠券<span id="couponName"></span><i></i> </li>
                <li>预付款 <span id="totalPrice"  class="xd-money">${goods.shopPrice}元</span> <input type="hidden" value="${goods.shopPrice}" id="shopPrice" /></li>
                <input type="hidden" id="price" />
            </ul>
        </div>
        <div class="xd-careful">
             ${goods.attrConent4}
        </div>
        <a onclick="addBespeak()"class="xd-appo">确认预约</a>
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
	  <div id="provider" class="weui-popup-container">
  		<div class="weui-popup-overlay"></div>
  		<div class="weui-popup-modal">
  			 <div  id="data-container1" style="margin-top:2.1rem">
        </div>
  		</div>
	</div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	 <script type="text/javascript" src="/js/front/goods/goodsBespeak.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	 {{#rows}}
             <div class="yhqBox" onclick="choiceCoupon({{id}})">
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
	<script id="template1" type="x-tmpl-mustache">
		{{#rows}}
            <div class='provider-box' id='provider_{{id}}' onclick="choice({{id}})">
					<input type="hidden" value="{{id}}" id="providerId" />
                    <img src='{{avatar}}' class='provider-img'>
                <div class='provider-inf'>
                    <span class='provider-name'>{{realName}}</span>
                    <span class='provider-juli'>{{provinceName}}{{cityName}}</span>
                    <p class='provider-zy'>{{workYear}}年经验</p>
                    <span class='js-like'>{{focusNum}}人喜欢</span>
                    <div class="provider-pj">
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx-on.png" alt="" class="pj-like">
                    </a>
                    <a href="javascript:">
                        <img src="/images/front/pjx.png" alt="" class="pj-like">
                    </a>
                	</div>
                </div>
            </div>	
	{{/rows}}
	</script>
</body>
</html>