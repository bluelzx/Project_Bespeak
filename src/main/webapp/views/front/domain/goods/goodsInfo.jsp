<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<title>服务详情</title>
</head>
<body>
     <div id="wrapper">
        <!--主体内容开始-->
        <div class="tn-top">
            <a onclick="lh.back()" class="back">
                <img src="/images/front/back.png" alt="">
            </a>
            <input type="hidden" value="${user.id}" id="userId" />
            <input type="hidden" value="${goods.id}" id="goodsId" />
            <img src="${goods.picPath}" alt="" class="tn-bg">
            <div class="tn-line1">
                <img src="/images/front/xin.png" onclick="updataFocus()" alt="" class="img-like">
                <span class="tn-like" id="hits">${goods.hits}</span><span class="tn-like">人喜欢</span>
                <span class="tn-time" id="jiaqian">${goods.shopPrice}元/${goods.timeNum}小时</span>
                <input type="hidden" value="${goods.shopPrice}" id="shopPrice" />
                <input type="hidden" value="${goods.timeNum}" id="timeNum" />
            </div>
        </div>
        <div class="tn-inf-1">
            <div class="tn-inf-line1">
                <span class="tn-title">服务流程:</span>
                <p class="tn-p">
                    ${goods.attrConent1}
                </p>
            </div>
            <div class="tn-inf-line2">
                <span class="tn-title">调理症疾:</span>
                <p class="tn-p">
                 ${goods.attrConent2}
                </p>
            </div>
        </div>
        <div class="tn-inf-2">
            <div class="tn-inf-line1">
                <span class="tn-title">适用人群:</span>
                <p class="tn-p">
                 ${goods.attrConent3}
                </p>
            </div>
            <div class="tn-inf-line2">
                <span class="tn-title">预约须知:</span>
                <p class="tn-p">
                 ${goods.attrConent4}
                </p>
            </div>
        </div>
        <div class="evaluate" >
        <div class="eva-title">客户评价</div>
        <!-- 物品评论 -->
         <div id="data-container">
          <div id="comment" style="text-align:center"></div>
         </div>
        </div>
        <a href="javascript:">
        <input type="button" value="立即预约"  class="xq-btn" id="placeOrderBt">
        </a>
        <div class="motai" style="display:hidden">
            <div class='motaiBoxShow'>
                <div class="orderTopShow">
                    <p>${goods.goodsName}</p>
                    <span>￥${goods.shopPrice}</span>
                    <img src="/images/front/imgQX.png" alt="" class="closeImg">
                </div>
                <div class="orderMiddleShow">
                    <p>分类</p>
                    <ul id="data-container2">
                    </ul>
                </div>
                <div class="orderBottomShow">
					<div class="xd-inf">
						<span>购买数量</span>
						<div class="xd-jj-box">
							<input type="button" value="-" class="reduce"> 
							<input type="text" value="1" id="text_box">
							<input type="button" value="+" class="plus">
						</div>
					</div>
				</div>
    		<input type="button" value="确定" onclick="bespeak()" class="orderBotShowBt">
   		 </div>
   	 </div>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/goods/goodsInfo.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
    
	{{#rows}}
            <div class="eva-box" >
                <div class="star">
					{{&stars}}
                    <span class="user-phone"> {{comUserName}}</span>
                </div>
                <p class="eva-p">{{content}}</p>
                <div class="eva-bottom">
                    <span class="eva-tilte">{{goodsName}}</span>
                    <span class="eva-time">{{getDate}}</span>
                </div>
            </div>	 	
	{{/rows}}
	</script>
	<script id="template2" type="x-tmpl-mustache">
	{{#rows}}
    	<li id="Single" class="placeProviderChoice">单次</li>
    	<li id="treatment{{id}}" onclick="treatment({{id}})">{{treatmentName}}</li>
	{{/rows}}
	</script>

</body>
</html>