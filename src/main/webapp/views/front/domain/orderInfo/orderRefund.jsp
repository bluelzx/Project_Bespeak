<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
   <meta charset="UTF-8">
   <title>申请退款</title>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<style type="text/css">
.jm-zs {
    margin: 0;
    padding: 0;
    text-align: left;
    }
</style>
</head>
<body>
<input type="hidden" value="${orderInfo.id}" id="orderInfoid" />
<input type="hidden" value="${orderInfo.createdAt}" id="orderInfoCreatedAt" />
<input type="hidden" value="${orderInfo.orderStatusCode}" id="orderStatusCode" />
<input type="hidden" value="${orderInfo.payStatusCode}" id="payStatusCode" />
    <div id="wrapper" class="aboutUs-box">
        <!--主体内容开始-->
        <a href="../orderIndex" class="back">
            <img src="../images/front/back.png" alt="" >
        </a>
        <div class="orderInfBox">
            <ul class="orderInf-line1">
                <li>
                    <img src="${orderInfo.picPath}" alt="" class="orderImg">
                    <span class="orderName">服务：${orderInfo.goodsName}</span>
                    <!-- <span class="rt">￥${orderInfo.totalPrice}元</span> -->
                </li>
                <li>
                    <img src="../images/front/q.png" alt="" class="orderQ">
                    <span class="rt">${orderInfo.couponType}${orderInfo.couponMoney}${orderInfo.couponzhekou}${orderInfo.couponCode}</span>
                </li>
                <li class="bt">
                    <span>总计：</span>
                    <span class="rt orderMoney">￥${orderInfo.totalPrice}元</span>
                </li>
            </ul>
            <ul class="orderInf-line2">
                <li>
                    <span>服务方式：</span>
                    <span class="rt">${orderInfo.shippingName}</span>
                </li>
                <li>
                    <span>订单号：</span>
                    <span class="rt">${orderInfo.orderSn}</span>
                </li>
                <li>
                    <span>下单时间：</span>
                    <span class="rt" id="orderInfoxxCreatedAt"></span>
                </li>
                <li>
                    <span>服务地址：</span>
                    <span class="rt">${orderInfo.address}</span>
                </li>
            </ul>
        </div>
        <div class="orderRefundBox">
            <p>退款说明：</p>
            <textarea name="" id="tuikuanshuoming"> </textarea>
        </div>
        <div class="jm-zs">
                <p style="margin:0.4rem 0.75rem">退款凭证截图：</p>
                <a href="#" class="jm-zj" id="tuikuanjietu">
                    <img src="../images/front/zj.png" alt="">
                </a>
        </div>
         <div id="tishibutton">
        <!--  按钮
        <input type="button" value="再次下单" class="orderBtXd">
        <input type="button" value="评价" class="orderBtpJ">
         -->
        </div>
    </div>
    <!--回到顶部按钮-->
    <div id="go-top"></div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
    <script type="text/javascript" src="/js/front/orderInfo/orderInfoRefund.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
        {{/rows}}
	</script>
</body>
</html>