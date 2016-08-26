<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
   <meta charset="UTF-8">
    <title>加盟商家</title>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
    <style>
        html{
            height: 100%;
            position: relative;
        }
    </style>
</head>
<body>
    <div id="wrapper">
        <a href="index.html" class="back">
            <img src="../images/front/back.png" alt="">
        </a>
        <form action="" class="signInBox jm-box">
            <div class="divInput">
                <label for="signIn-user">商家名称</label>
                <input type="text" id="signIn-user" placeholder="请输入商家名称" autocomplete="off" required>
            </div>
            <div class="divInput">
                <label for="signIn-pass">联系人</label>
                <input type="text" id="signIn-pepole" placeholder="请输入联系人姓名" required>
            </div>
            <div class="jm-phone">
                <label for="yanzm">联系电话</label>
                <input type="number" id="jm-phone" placeholder="请输入联系电话" min="100000" max="99999999999" autocomplete="off" required>
            </div>
            <div class="jm-address">
                <label for="jm-dizhi">联系地址</label>
                <input type="text" id="jm-dizhi" placeholder="请输入联系地址" autocomplete="off" required>
            </div>
            <div class="jm-zs">
                <p style="margin:0.4rem 0.75rem">上传证书</p>
                <a href="#" class="jm-zj">
                    <img src="../images/front/zj.png" alt="">
                </a>
            </div>
            <a href="#" class="jm" onclick="addShopApply()">申请加盟</a>
            <p class="jm-p">/*申请通过后会有相关工作人员联系您完善详细资料*/</p>
        </form>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript">lh.param = ${paramJson};</script>
    <script type="text/javascript" src="/js/front/shop/shopApply.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}	
          	
	{{/rows}}
	</script>
</body>
</html>