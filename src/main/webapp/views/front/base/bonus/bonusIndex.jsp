<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" href="/css/front/wpknew/my_seller.css">
 <link rel="stylesheet" href="/css/front/wpknew/common.css">
  <link rel="stylesheet" href="/css/front/wpknew/weui.min.css">
    <link rel="stylesheet" href="/css/front/wpknew/bootstrap.min.css">
    <link rel="stylesheet" href="/css/front/wpknew/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="/css/front/wpknew/font-awesome-ie7.min.css">
    <![endif]-->
   <script src="/js/front/user/myseller.js"></script>
    <script src="/js/front/user/jquery-1.11.2.js"></script>
    <script src="/js/front/user/bootstrap.min.js"></script>
</head>
<body style="background:url(images/bonus_index_bg.png) no-repeat; background-size: 100% 100%;">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 bonus-head plr0">
                <div class="col-xs-2 ptb5 head-left">
                    <img src="images/icon-left.png" width="22" height="21" class="img-responsive center-block">
                </div>
                <div class="col-xs-7 mt-5">
                    <span class="fs18">微信红包</span><br>
                    <span>微信安全支付</span>
                </div>
                <div class="col-xs-3 mt-5 plr0" style="line-height: 40px;">
                    <div class="dropdown">
                        <div class="dropdown-toggle fs16" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            我的红包
                        </div>
                        <ul class="dropdown-menu" style="min-width: 112px;left: auto;right: 0;" aria-labelledby="dropdownMenu1">
                            <li><a href="#">收到的红包</a></li>
                            <li><a href="#">发出的红包</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-12 mt40p">
                    <a href="#" rel="button" class="col-xs-12 btn-lg btn btn-yellow-tint">拼手气群红包</a>
                    <a href="#" rel="button" class="col-xs-12 btn-lg btn btn-yellow-deep mt20">普通红包</a>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom_word">
        可直接使用收到的零钱发红包
    </div>
    <script>
        $(function(){
            var width = $(document).width();//当前的浏览器的宽度
            var height = $(document).height();//当前浏览器的高度
            $(".container-fluid").height(height);
        });
    </script>
</body>
</html>