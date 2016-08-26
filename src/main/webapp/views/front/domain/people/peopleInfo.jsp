<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
    <meta charset="UTF-8">
    <title>月嫂/育婴详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="迈噜月嫂/育婴详情">
    <meta name="description" content="迈噜月嫂/育婴详情">
    <!-- 
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/jquery-weui.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
     -->
</head>
<body>
    <div id="wrapper">
    <!--  yinchan数据 -->
    <input type="hidden" value="${people.id}" id="peopleid" />
    <input type="hidden" value="${people.mainStatus}" id="mainStatus" />
    <input type="hidden" value="${people.createdAt}" id="createdAt" />
    <input type="hidden" value="${people.isRealAuth}" id="isRealAuth" />
     <input type="hidden" value="${people.titleCode}" id="titleCode" />
    <input type="hidden" value="${people.sex}" id="peoplesex" />
        <!--主体内容开始-->
        <div class="tn-top js-top month-top">
            <a href="../peopleIndex" class="back">
                <img src="../images/front/back.png" alt="">
            </a>
            <img src="${people.avatar}" alt="" class="month-img">
            <span class="month-name">${people.realName}</span>
            <div class="tn-line1 js-top-bt month-pj">
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx.png" alt="" class="pj-like">
                </a>
            </div>
            <div class="tn-line1 js-top-bt month-tb">
                <img src="../images/front/xin.png" alt="" class="img-like">
                <span class="tn-like">${people.attrInt}人喜欢</span>
                <a href="#" class="provider-zz"><font color="blank">未认证</font></a>
            </div>
        </div>

        <div class="month-int">
            <p>自我简介：${people.introduction}</p>
        </div>
        <div class="month-int">
            <p>雇佣价格说明：${people.priceText}</p>
        </div>
        <div class="month-int">
            <p>详细位置：${people.address}</p>
        </div>
        <!-- 
        <div class="month-int">
            <p>所在城市：${lh.param.provinceName}&nbsp;${lh.param.cityName}</p>
        </div>
        <div class="month-int">
            <p>月嫂职责：月嫂应该定位于：协助产妇做些粗活，帮助产妇做她不方便做的活。</p>
        </div>
         -->
        <div class="month-time">
            <span class="c-month-time">注册时间：</span>
            <span class="c-month-zt">工作经验:${people.workYear}年</span>
        </div>
        <div class="month-time">
            <!-- <span class="c-month-xb"></span> -->
            <span class="c-month">职称认证：${paramJson.titleName}</span>
            <span class="c-month-zt">联系电话：${people.phone}</span>
        </div>
        <div class="evaluate" style="margin-top:0.15rem;">
            <div class="eva-title"></div>
            <div class="eva-box">
          <a href="/peopleApply/${people.id}" class="xd-appo">填写雇佣申请</a>     
            </div>
        </div>
        <!-- 
        <div class="evaluate" style="margin-top:0.15rem;">
            <div class="eva-title">客户评价</div>
            <div class="eva-box">
                <div class="star">
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <span class="user-phone">133****333</span>
                </div>
                <p class="eva-p">很好，很满意很好，很满意很好，很满意很好，很满意很好，很满意</p>
                <div class="eva-bottom">
                    <span class="eva-tilte">全身理疗</span>
                    <span class="eva-time">2016-07-30</span>
                </div>
            </div>
            <div class="eva-box">
                <div class="star">
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <a href="javascript:" class="star-img"></a>
                    <span class="user-phone">133****333</span>
                </div>
                <p class="eva-p">很好，很满意很好，很满意很好很满意很好，很满意很好很满意很好，很满意很好很满意很好，很满意很好很满意很好，很满意很好很满意很好，很满意很好，很满意很好，很满意很好，很满意</p>
                <div class="eva-bottom">
                    <span class="eva-tilte">全身理疗</span>
                    <span class="eva-time">2016-07-30</span>
                </div>
            </div>
        </div>
         -->   

    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript">lh.param = ${paramJson};</script>
    <script type="text/javascript" src="/js/front/people/peopleInfo.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}	
         	
	{{/rows}}
	</script>
</body>
</html>