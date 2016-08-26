<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN" id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
 <meta charset="UTF-8">
    <title>迈噜打造母婴健康管理第一品牌 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="迈噜打造母婴健康管理第一品牌">
    <meta name="description" content="迈噜打造母婴健康管理第一品牌">
  <!--   <link rel="stylesheet" href="css/front/main.css">
    <link rel="stylesheet" href="css/front/jquery-weui.min.css"> -->
</head>
<body>
    <div id="wrapper">
        <!--顶部栏开始-->
        <header>
            <a href="/addressSelect" id="address" class="a-address">
                <span id="citys" class="city">北京</span>
                <img src="images/front/arrow.png" alt="" class="index-arrow">
            </a>
            <span class="page-name">首页</span>
            <a href="tel:10086" class="a-phone">
                <img src="images/front/phone.png" alt="" class="phone">
            </a>
        </header>
        <!--顶部栏结束-->
        <!--主体内容开始-->
        <div id="main">
            <!--轮播图-->
            <div class="swiper-container index-swiper">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <a href="#" class="swiper-choice">
                            <img src="images/front/lun-4.jpg" alt="" class="swiper-image">
                        </a>
                    </div>
                    <div class="swiper-slide">
                        <a href="#" class="swiper-choice">
                            <img src="images/front/lun-2.jpg" alt="" class="swiper-image">
                        </a>
                    </div>
                    <div class="swiper-slide">
                        <a href="#" class="swiper-choice">
                            <img src="images/front/lun-3.jpg" alt="" class="swiper-image">
                        </a>
                    </div>
                    <div class="swiper-slide">
                        <a href="#" class="swiper-choice">
                            <img src="images/front/lun-1.jpg" alt="" class="swiper-image">
                        </a>
                    </div>
                </div>
                <div class="swiper-pagination" style="bottom:0"></div>
            </div>
            <!--nav菜单栏-->
            <div class="nav">
                <a href="template/dist/enterprise-service.html" class="a-line-1">
                    <img src="images/front/enterprise-service.png" alt="">
                    <span>企业服务</span>
                </a>
                <a href="/courseIndex" class="a-line-1">
                    <img src="images/front/train.png" alt="">
                    <span>健康培训</span>
                </a>
                <a href="/activity" class="a-line-1">
                    <img src="images/front/gongyi.png" alt="">
                    <span>迈噜公益</span>
                </a>
                <a href="#" class="a-line-1">
                    <img src="images/front/recharge.png" alt="">
                    <span>礼金充值</span>
                </a>
                <a href="/providerIndex">
                    <img src="images/front/appointment.png" alt="">
                    <span>预约技师</span>
                </a>
                <a href="#">
                    <img src="images/front/soldier.png" alt="">
                    <span>军人家庭</span>
                </a>
                <a href="/peopleIndex">
                    <img src="images/front/maternity-matron.png" alt="">
                    <span>月嫂/育婴</span>
                </a>
                <a href="/allShop">
                    <img src="images/front/merchant.png" alt="">
                    <span>加盟商家</span>
                </a>
            </div>
            <!--营销广告-->
            <div class="guanggaolan">
                <div class="gg-line-1">
                    <a href="#">
                        <img src="images/front/gg-2.png" alt="">
                    </a>
                </div>
                <div class="gg-line-2">
                    <a href="#">
                        <img src="images/front/gg-1.png" alt="">
                    </a>
                </div>
                <div class="gg-line-2">
                    <a href="#">
                        <img src="images/front/gg-3.png" alt="">
                    </a>
                </div>
            </div>
            <!--服务导航-->
             <div class="appointment-provider swiper-fixed" >
                <div class="swiper-wrapper" id="data-container1">
                </div>
            </div>
            <!--服务细节-->
            <div class="recommend" id="data-container">
                <!--JS插入-->
            </div>
        </div>
        <!--主体内容结束-->
        <!--底部固定菜单栏开始-->
        <footer>
            <a href="javascript:">
                <img src="/images/front/index-on.png" alt="">
                <span class="page-on">首页</span>
            </a>
            <a href="/providerIndex">
                <img src="/images/front/technician.png" alt="">
                <span>技师</span>
            </a>
            <a href="/orderIndex">
                <img src="/images/front/order.png" alt="">
                <span>订单</span>
            </a>
            <a href="/userHome">
                <img src="/images/front/user.png" alt="">
                <span>个人中心</span>
            </a>
        </footer>
        <!--底部固定菜单栏结束-->
    </div>
    <!--回到顶部-->
    <div id="go-top"></div>
    <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
   <!--  <script src="js/common/jquery-3.0.0.min.js"></script>
    <script src="js/common/jquery-weui.min.js"></script>
    <script src="js/common/swiper-3.3.1.jquery.min.js"></script>
    <script src="js/common/mustache.min.js"></script>
    <script src="js/common/main.js"></script>   单位设置 -->
    <!--轮播图JS-->
    <script src="/js/common/index.js" title="v"></script>
    <script id="template" type="x-tmpl-mustache">
        {{#rows}}
            <a href='/goods/{{id}}' class='details' id='details_{{id}}'>
                <img src='{{picPath}}' alt='' class='details-head-img'>
                <div class='details-box'>
                    <span class='details-name'>{{goodsName}}</span>
                    <span class='details-time'>{{shopPrice}}元/{{timeNum}}小时</span>
                    <span class='details-discount'>新用户立减30元</span>
                    <span class='details-num'>{{doneAmount}}人选择</span>
                    <span class='likeNum'>{{hits}}人喜欢</span>
                    <p class='details-p'>{{goodsBrief}}</p>
                </div>
            </a>
        {{/rows}}
    </script>
	<script id="template1" type="x-tmpl-mustache">
		{{#rows}}	 	
			<div class="swiper-slide swiper-a" id="{{code}}">{{codeName}}</div>
		{{/rows}}
	</script>
</body>
</html>