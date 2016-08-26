<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<title>技师申请</title>
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
        <!--主体内容开始-->
            <a onclick="lh.back()" class="back">
                <img src="/images/front/back.png" alt="">
            </a>
              <form action="" class="signInBox jm-box">
            <div class="divInput">
                <label for="js-name">姓名</label>
                <input type="text" id="js-name" placeholder="请输入你的真实姓名" autocomplete="off" required>
            </div>
            <div class="jm-phone">
                <label for="js-phone">联系电话</label>
                <input type="text" id="js-phone" placeholder="请输入你的联系电话" autocomplete="off" required>
            </div>
            <div class="jm-address">
                <label for="dianpu">所在店铺</label>
                <input type="text" id="dianpu" placeholder="请输入你所在店铺名" autocomplete="off" required>
            </div>
            <div class="jm-zs">
                <p style="margin:0.4rem 0.75rem">上传证书</p>
                <a href="#" class="jm-zj">
                    <img src="/images/front/zj.png" alt="">
                </a>
            </div>
            <a onclick="addProvider()" class="jm">确认申请</a>
            <p class="jm-p" style="top:23.4rem">/*申请成功后会有相关工作人员联系您*/</p>
        </form>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
    <script type="text/javascript" src="/js/front/provider/addProvider.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
           
		{{/rows}}
	</script>
</body>
</html>