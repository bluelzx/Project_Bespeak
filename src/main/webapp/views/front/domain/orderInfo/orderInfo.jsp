<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
   <meta charset="UTF-8">
    <title>订单查看</title>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
    <style type="text/css">
    .csstwo{
    border: 0.05rem solid #9E899E;
    right: 4.25rem;
    color: #967878;
    width: 3.8rem;
    }
    .querenfwcss{
    position: absolute;
    right: 0.4rem;
    border: 0.05rem solid #5DC2D0;
    color: #5DC2D0;
    width: 3.6rem;
    }
    .orderWf{
    display: block;
    color: #CE1818;
    }
    .b-btn {
  position: absolute;
  bottom: 0.25rem;
  line-height: 1.1rem;
  background: #fff;
  font-size: 0.505rem;
  text-align: center;
}
    </style>
</head>
<body>
     <div id="wrapper">
        <!--主体内容开始-->
        <div id="main">
            <ul class="order-ul">
                <li class="choice-on" id="order-all">全部</li>
                <li id="order-ing">进行中</li>
                <li id="order-evaluated">已完成</li>
                <!-- <li id="order-ok">yiwancheng</li> -->
                <li id="order-tuikuang">退款中</li>
                <li id="order-cancel">取消中</li>
            </ul>
            <!--订单信息-->
            <div class="order" id="data-container">

            </div>
        </div>
        <!--主体内容结束-->
        <!--底部菜单栏开始-->
        <footer>
            <a href="/index" class="ft-a">
                <img src="/images/front/index.png" alt="">
                <span>首页</span>
            </a>
            <a href="/providerIndex" class="ft-a">
                <img src="/images/front/technician.png" alt="">
                <span>技师</span>
            </a>
            <a href="javascript:" class="ft-a">
                <img src="/images/front/order-on.png" alt="">
                <span class="page-on">订单</span>
            </a>
            <a href="/userHome" class="ft-a">
                <img src="/images/front/user.png" alt="">
                <span>个人中心</span>
            </a>
        </footer>
        <!--底部菜单栏结束-->
    </div>
    <!--回到顶部按钮-->
    <div id="go-top"></div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
    <script type="text/javascript" src="/js/front/orderInfo/orderInfo.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
    {{#rows}}
          <div class='order-box id='order_{{id}}'>
            <div class='order-top-box' onClick="orderInfojump({{id}})">
                <img src='{{picPath}}' class='order-provider-img fl'>
                <div class='providerInf'>
                    <p class='order-name'>{{goodsName}}</p>
                    <p class='orderIndexzY'>{{shopName}}</span>
                    <span class='{{orderStateClass}} guding'>{{orderState}}</span>
                    <p class='orderP'>
                        <span>下单时间：{{xiadandate}}</span>
                        <span class='order{{payTypeCss}}'>{{payType}}金额:¥ {{totalPrice}}</span>
                    </p>
                </div>
            </div>
                <div class='orderIndexBot'>
                    <a class='a-btn b-btn {{StateNG}}' onclick='{{orderHrefNG}}({{id}},{{payCode}})'>{{operationNG}}</a>
                    <a class='a-btn b-btn {{btnState}}' onclick='{{orderHref}}({{id}})'>{{operation}}</a>
                </div>
            </div>
        {{/rows}}
	</script>
</body>
</html>