<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" href="/third-party/reset/reset.css"/> <!-- CSS reset -->
<link rel="stylesheet" href="/css/back/login.css" title="v"/> <!-- Gem style -->
    <style>
        .djUl img {
            margin-left:48%;
        }
        .ddUl img {
            margin-left:45%;
        }
        .setSpan {
            display: none;
            width:2.75rem;
            line-height:2.75rem;
            text-align: center;
            border-radius: 50%;
            background: rgba(0,0,0,0.7);
            color: #fff;
            font-size: 0.6rem;
            position: absolute;
            right: 0;
            top: 0;
        }
        .showSpan {
            margin-left: 2rem;line-height: 2.75rem;display: inline-block;position: relative
        }
        .boxYYZZ {
            margin-left: 1rem;
            font-size: 0.7rem;
            text-align: center;
            width: 5rem;
        }
        .boxYYZZ .pushYY {
            width: 60px;
            height:60px;
        }
        .setLogoImg {
            font-size: 20px;
            color: #000;
            bottom: 0;
            line-height: normal;
            display: inline-block;
            font-size: 14px;
            left: 2.7rem;
            bottom: 5px;
            position: absolute;
            border: none;
            background: #5bc0de;
            color: #fff;
            border-radius: 5px;
        }
        .businessBg {
            position: relative;
        }
    </style>
</head>
<body>
    <div id="wrapper">
        <div class="businessBg">
            <a href="setBusiness.html" class="showSpan">
                <img src="/images/front/no-dl.png" alt="" class="user-img">
                <span class="setSpan">设置</span>
            </a>
            <button class="setLogoImg">修改头像</button>

            <span class="user-name">王思聪</span>
            <img src="/images/front/ewm.png" alt="" class="ewm">
            <a href="#" class="beginFu">添加服务</a>
        </div>
        <div class="djBox">
            <div class="fugl">
                <img src="/images/front/fw.png" alt="" class="fwImg">
                服务管理
                <span class="glAll">全部</span>
                <i class="qb-i glI"></i>
            </div>
            <ul class="djUl">
                <li>
                    <img src="/images/front/xs.png" alt="">
                    线上服务
                </li>
                <li>
                    <img src="/images/front/xs.png" alt="">
                    待上服务
                </li>
                <li>
                    <img src="/images/front/sh.png" alt="">
                    审核中
                </li>
            </ul>
        </div>
        <div class="jsglBox">
            <div class="fugl">
                <img src="/images/front/jsgl.png" alt="" class="fwImg">
                技师管理
                <span class="glAll">全部</span>
                <i class="qb-i glI"></i>
            </div>
            <ul class="djUl">
                <li>
                    <img src="/images/front/gzz.png" alt="">
                    工作中
                </li>
                <li>
                    <img src="/images/front/ky.png" alt="">
                    可约技师
                </li>
                <li>
                    <img src="/images/front/bky.png" alt="">
                    不可约技师
                </li>
            </ul>
        </div>
        <div class="jsglBox">
            <div class="fugl">
                <img src="/images/front/dd.png" alt="" class="fwImg">
                订单管理
                <span class="glAll">全部</span>
                <i class="qb-i glI"></i>
            </div>
            <ul class="ddUl">
                <li>
                    <img src="/images/front/xd.png" alt="">
                    新订单
                </li>
                <li>
                    <img src="/images/front/pj.png" alt="">
                    待评价
                </li>
                <li>
                    <img src="/images/front/wc.png" alt="">
                    已完成
                </li>
                <li>
                    <img src="/images/front/qx.png" alt="">
                    已取消
                </li>
            </ul>
        </div>
        <div class="boxYYZZ">
            <p>上传营业执照</p>
            <img src="/images/front/zj.png" alt="" class="pushYY">
        </div>
        <!--主体内容结束-->
    </div>
    <!--回到顶部-->
    <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>
	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/shop/goods.js" title="v"></script>
    <script>
        $('.showSpan').mouseover(function () {
            $('.setSpan').show()
        });
        $('.showSpan').mouseout(function () {
            $('.setSpan').hide()
        })
    </script>
</body>
</html>