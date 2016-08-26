<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<!-- <link rel="stylesheet" href="/css/front/...." title="v"> -->
   <meta charset="UTF-8">
    <title>关于我们</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="关于我们">
    <meta name="description" content="关于我们">
</head>
<body>
  <div id="wrapper">
        <!--主体内容开始-->
        <div class="wallet-top">
            <a onclick="lh.back()" class="back">
                <img src="/images/front/back.png" alt="">
            </a>
            <span class="ky-yue">可用余额</span>
            <h3 class="wallet">${user.avaliableMoney}元</h3>
        </div>
        <p class="payP">钱包充值</p>
        <div class="payIn">
            <ul class="payUl">
                <li class="payLi">
                    充1000元
                    <span>送200元</span>
                    <a href="#" class="payA">
                        充值
                    </a>
                </li>
                <li class="payLi">
                    充3000元
                    <span>送650元</span>
                    <a href="#" class="payA">
                        充值
                    </a>
                </li>
                <li class="payLi">
                    充5000元
                    <span>送1200元</span>
                    <a href="#" class="payA">
                        充值
                    </a>
                </li>
                <li class="payLi">
                    充10000元
                    <span>送2500元</span>
                    <a href="#" class="payA">
                        充值
                    </a>
                </li>
                <li class="payLi bt">
                    充50000元
                    <span>送5000元</span>
                    <a href="#" class="payA">
                        充值
                    </a>
                </li>
            </ul>
        </div>
        <a href="#" class="payP">我的健康卡<i class="qb-i payI"></i></a>
        <ul class="payUl">
            <li class="payLi bt">
                <input type="text" placeholder="请输入健康卡激活码" class="jihuo-inp">
                <a href="#" class="payA">
                    激活
                </a>
            </li>
        </ul>
        <p class="payInf">
            健康卡是我们为客户提供实体礼品卡,客户可使用健康卡上的激活码直接激活充值.
        </p>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/goods/provider.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
    
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