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
               <label class="signIn-user" for="username">&nbsp;</label>
                <input type="text" id="username" placeholder="昵称/手机号/邮箱" autocomplete="off" required>
            </div>
            <div class="divInput">
                <label class="signIn-pass" for="password">&nbsp;</label>
                <input type="password" id="password" placeholder="请输入密码" required>
            </div>
            <div class="yzmBox">
                 <label class="yanzm" for="verificationCode">&nbsp;</label>
                <input type="text" id="verificationCode" placeholder="请输入验证码" autocomplete="off" required>
                <a href="javascript:loadRandomCode();">
                    <img src="/images/front/yz.png" alt="" class="yzImg" id="imgcode">
                </a>
            </div>
            <a href="#" class="sign" onclick="doUserLogin()" style="top:5rem">个人登录</a>
            
            <!-- 商家个人中心地图选点  后期去掉按钮 -->
            <a href="/shopCenters/1" class="sign"style="top:9rem">商家个人中心(后期移植)</a>
            
            <a href="/userRegister" class="zhuce">注册</a>
            <a href="javascript:;" data-toggle="modal" data-target="#exampleModal" style="float: left; margin-left: 2.65rem; color: #818181; line-height: 1.5rem;font-size:0.7rem;">忘记密码？</a>
        </form>
    </div>
        <!--回到顶部-->
   <!-- <div id="go-top"></div>-->
 	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
<script type="text/javascript" src="/js/front/login/passwordReset.js" title="v"></script>
<script type="text/javascript" src="/js/front/login/login.js" title="v"></script>
</body>
</html>



