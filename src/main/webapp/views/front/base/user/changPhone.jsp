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
        <a onclick="lh.back()" class="back">
            <img src="/images/front/back.png" alt="">
        </a>
        <input type="hidden" value="${user.id}" id="userId" />
        <form action="" class="signInBox c-phone">
            <div class="divInput">
                <label for="">原手机号码</label>
                <input type="text" id="zc-phone" placeholder="原手机号" autocomplete="off" value="${userPhone }" readonly="readonly">
            </div>
            <div class="divInput">
                <label for="">新手机号码</label>
                <input type="text" id="phoneNum" onkeyup="value=value.replace(/[^\d]/g,'') " 
      onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"  placeholder="请设置新手机号" required>
            </div>
            <div class="yzmBox bt">	
                <label for="">输入验证码</label>
                <input type="text" id="phoneCode" placeholder="请输入验证码" autocomplete="off" required>
                <a id="btnMssage" onclick="sendVerify();" class="getYZM">
                    获取验证码
                </a>
            </div>
            <a onclick="checkPhoneCode();" class="zc-zhuce">确认修改</a>
        </form>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/goods/provider.js" title="v"></script>


</body>
</html>