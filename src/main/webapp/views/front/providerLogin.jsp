<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="zh-CN" id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
 <meta charset="UTF-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="迈噜登录">
    <meta name="description" content="迈噜登录">
    <style>
        html{
            height: 100%;
            position: relative;
        }
    </style>
</head>
<body>
    <div id="wrapper">
        <a onclick="lh.back()" class="back">
            <img src="/images/front/back.png" alt="">
        </a>
        <form action="" class="signInBox">
            <div class="divInput">
               <label class="signIn-user" for="signIn-user">&nbsp;</label>
                <input type="text" id="username" placeholder="昵称/手机号/邮箱" autocomplete="off" required>
            </div>
            <div class="divInput">
                <label class="signIn-pass" for="signIn-pass">&nbsp;</label>
                <input type="password" id="password" placeholder="请输入密码" required>
            </div>
            <div class="yzmBox">
                 <label class="yanzm" for="yanzm">&nbsp;</label>
                <input type="text" id="verificationCode" placeholder="请输入验证码" autocomplete="off" required>
                <a href="#">
                    <img src="/images/front/yz.png" alt="" class="yzImg" id="imgcode">
                </a>
            </div>
            <a href="#" class="sign" onclick="doProviderLogin()">登录</a>
            <a href="/providerAdd" class="zhuce">技师申请</a>
            <a href="javascript:;" data-toggle="modal" data-target="#exampleModal" style="float: left; margin-left: 57px; color: #818181; line-height: 35px;">忘记密码？</a>
        </form>
    </div>
        <!--回到顶部-->
 	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
<script type="text/javascript" src="/js/front/login/passwordReset.js" title="v"></script>
<script type="text/javascript" src="/js/front/login/providerLogin.js" title="v"></script>
</body>
</html>



