<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_back_css.htm"%>
<link rel="STYLESHEET" type="text/css" href="/css/back/back.css" title="v" />
    <style>
        #setBusBox {
            position: absolute;
            left:50%;
            top:50%;
            width:400px;
            margin:-150px 0 0 -150px;
            background: #fff;
        }
        .setBoxLine {
            height:40px;
            margin-left:60px;
        }
        .setBoxLine a {
            display: inline-block;
            background: #5bc0de;
            color: #fff;
            padding: 2px 5px;
        }
        .btnOk {
            display: block;
            margin: 0 auto;
            margin-bottom: 30px;
            background: #5bc0de;
            color: #fff;
            border: none;
            width:70px;
            height:30px;
        }
        .setBusBoxTop {
            border-bottom: 2px solid #ddd;
            padding:20px 0;
            padding-left:60px;
        }
        .btnTopOk {
            display: block;
            background: #5bc0de;
            color: #fff;
            border: none;
            width:70px;
            height:30px;
            margin: 20px 0 0 100px;
        }
    </style>
</head>
<body>

    <div id="setBusBox">
        <div class="setBusBoxTop">
            <label for="">店铺名</label>
            <input type="text" placeholder="请输入新的店铺名">
            <input type="button" value="保存" class="btnTopOk">
        </div>

        <div class="setBoxLine"  style="margin-top:20px;">
            <label for="lastPhone">手机号:</label>
            <input type="text" placeholder="请输入手机号" id="lastPhone">
        </div>
        <div class="setBoxLine">
            <label for="cerificationCode">验证码:</label>
            <input type="text" placeholder="请输入验证码" id="cerificationCode">
            <a href="#">获取验证码</a>
        </div>
        <div class="setBoxLine">
            <label for="newPassWord">新密码:</label>
            <input type="text" placeholder="请输入新密码" id="newPassWord">
        </div>
        <input type="button" value="保存" class="btnOk">
    </div>

    <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<%@ include file="/views/common/common_upload_js.htm"%>
	<script type="text/javascript">
		lh.paramJsonStr = '${paramJson}';
	</script>
	<script type="text/javascript" src="/js/common/back_template.js" title="v"></script>
	<script type="text/javascript" src="/js/shop/goods.js" title="v"></script>
</body>
</html>