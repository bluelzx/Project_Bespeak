<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
    <meta charset="UTF-8">
    <title>加盟商家</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="迈噜加盟商家">
    <meta name="description" content="迈噜加盟商家">
    <%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body>
    <div id="wrapper" class="aboutUs-box">
        <!--主体内容开始-->
        <a href="index.html" class="back">
            <img src="../images/front/back.png" alt="" >
        </a>
        <img src="../images/front/jmbg.png" alt="" class="jmbg">
        <div class="joinBox">
            <div class="qywh">
                <img src="../images/front/rjt.png" alt="" class="jt">
                <span>企业文化</span>
                <dl>
                    <dt>一、企业理念</dt>
                    <dd>企业理念介绍</dd>
                    <dt>二、企业愿景</dt>
                    <dd>企业愿景介绍</dd>
                </dl>
            </div>
            <div class="jmzc">
                <img src="../images/front/ljt.png" alt="" class="jt">
                <span>加盟政策</span>
                <dl>
                    <dt>政策介绍</dt>
                    <dd>点一</dd>
                    <dd>点二</dd>
                    <dd>点三</dd>
                </dl>
            </div>
            <div class="qywh">
                <img src="../images/front/rjt.png" alt="" class="jt">
                <span>加盟条件</span>
                <dl>
                    <dt>政策介绍</dt>
                    <dd>点一</dd>
                    <dd>点二</dd>
                    <dd>点三</dd>
                </dl>
            </div>
            <div class="jmzc">
                <img src="../images/front/ljt.png" alt="" class="jt">
                <span>加盟优势</span>
                <dl>
                    <dt>政策介绍</dt>
                    <dd>点一</dd>
                    <dd>点二</dd>
                    <dd>点三</dd>
                </dl>
            </div>
            <div class="qywh" style="margin-bottom: 1rem">
                <img src="../images/front/rjt.png" alt="" class="jt">
                <span>加盟商家展示</span>
                <ul class="joinShopUl" id="shopList">
                                      
                </ul>
                <div class="sqjm">
                    <input type="button" value="申请加盟" class="sqjmBt">
                    <p>/*申请成功后会有相关工作人员联系您*/</p>
                </div>
            </div>
        </div>
    </div>
      <!--回到顶部按钮-->
    <div id="go-top"></div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
	<script src="/js/front/shop/allShop.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}	 	
			<li>
				<img src="{{logo}}" class='sqjmImg'>
				<p>{{name}}</p>
			</li>
		{{/rows}}
	</script>
</body>
</html>