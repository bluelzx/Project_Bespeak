<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<title>评论</title>
</head>
<body>
     <div id="wrapper">
        <a onclick="lh.back()" class="back">
            <img src="/images/front/back.png" alt="">
        </a>
        <input type="hidden" value="${orderInfo.goodsId}" id="goodsId" />
        <input type="hidden" value="${orderInfo.id}" id="orderInfoId" />
        <input type="hidden" value="${orderInfo.providerId}" id="providerId" />
        <input type="hidden" value="${orderInfo.createdAt}" id="orderInfoCreatedAt" />
        <div class="pj-box">
            <img src="${orderInfo.picPath}" alt="" class="pj-img">
            <div class="xijie">
                <p class="pj-name">${orderInfo.goodsName}</p>
                <p class="pj-money">
                    总价:<span>${orderInfo.totalPrice}</span>
                    <span class="pj-num">数量:<span>${orderInfo.goodsNumber}</span></span>
                </p>
                <p class="pj-time">付款时间:${orderInfo.createdAt}</p>
            </div>
        </div>
        <form action="" class="signInBox jm-box" id="pj-form">
            <div class="divInput pj-xq">
                <label>服务评价</label>
                <div class="tn-line1 pj-star choice-pj1">
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
            <div class="divInput pj-xq">
                <label>技师评价</label>
                <div class="tn-line1 pj-star choice-pj2">
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
            <div class="jm-phone pj-xq">
                <label>总体评价</label>
                <div class="tn-line1 pj-star choice-pj3">
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
            <div class="jm-phone pj-xq">
            <input type="text" placeholder="请留下您宝贵的意见或建议"  id="pj-input">
            </div>
            <div class="jm-zs">
                <p style="margin:0.4rem 0.75rem">晒单</p>
                <a href="#" class="jm-zj">
                    <img src="/images/front/zj.png" alt="">
                </a>
            </div>
            <a onclick="addComment()" class="jm pj">评价</a>
        </form>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/comment/addComment.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
   
	</script>

</body>
</html>