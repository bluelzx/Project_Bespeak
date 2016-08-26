<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="zh-CN" id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
 <meta charset="UTF-8">
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="迈噜注册">
    <meta name="description" content="迈噜注册">
    <!-- 
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/jquery-weui.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
     -->
    <style>
        html{
            height: 100%;
            position: relative;
        }
        .protocol{
            margin-top: 3%;
            margin-left: 6%;
        }
    </style>
</head>
<body>
    <div id="wrapper">
        <a href="/login" class="back">
            <img src="images/front/back.png" alt="">
        </a>
        <form action="" class="signInBox">
        <div class="divInput">
                  <label class="signIn-user" for="signIn-user">&nbsp;</label>
                <input type="text" id="username" placeholder="登录昵称" autocomplete="off" required>
            </div>
            <div class="divInput">
                 <label class="zc-phone" for="zc-phone">&nbsp;</label>
                <input type="text" id="phone" placeholder="请输入手机号" autocomplete="off" required>
            </div>
            <div class="divInput">
                <label class="signIn-pass" for="signIn-pass">&nbsp;</label>
                <input type="password" id="password" placeholder="请设置登录密码" required>
            </div>
            <div class="divInput">
                <label class="signIn-pass" for="signIn-pass">&nbsp;</label>
                <input type="password" id="passwordSure" placeholder="请设置确认密码" required>
            </div>
            <div class="yzmBox divInput">
                <label class="yanzm" for="yanzm">&nbsp;</label>
                <input type="text" id="randomCode" placeholder="请输入验证码" autocomplete="off" required>
                 <a href='javascript:loadRandomCode();'>
                    <img src="images/front/yz.png" alt="" class="yzImg" id="imgcode">
                </a>
            </div>
            <div class="protocol" style="font-size:0.6rem;">
            <input id="protocol" type="checkbox" checked="checked" /> &nbsp;同意<a href="#">《网站用户注册协议》</a>
            </div>
            <a href="#" class="zc-zhuce" onclick="register()" style="top:9rem;">注册</a>
            <!-- 
            <a href="/login" class="zc-sign">登录</a>
             -->
        </form>
    </div>
   <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
<script type="text/javascript" src="/js/front/login/passwordReset.js" title="v"></script>
<script type="text/javascript" src="/js/front/login/register.js" title="v"></script>
</body>
</html>

