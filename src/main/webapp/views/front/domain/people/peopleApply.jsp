<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
    <meta charset="UTF-8">
    <title>预约月嫂/育婴</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="lhfeiyu.com">
    <meta name="keywords" content="迈噜预约月嫂/育婴">
    <meta name="description" content="迈噜预约月嫂/育婴">
    <style type="text/css">
    .xiaomaitixin{
    font-size: 0.4rem;
    display: inline-block;
    border-bottom: 0.05rem;
    padding-left: 0.75rem;
    }
    .beizhuxx{
    border: none;
    outline: none;
    width: 100%;
    margin-bottom: 0.1rem;
    height: 2.4rem;
    padding: 0;
    font-family: inherit;
    line-height: 0.8rem;
    }
    .beizhusm{
    
    }
    </style>
</head>
<body>
    <div id="wrapper">
        <!--主体内容开始-->
        <div class="tn-top js-top xd-box">
            <a href="../people/${people.id}" class="back">
                <img src="../images/front/back.png" alt="">
            </a>
            <img src="${people.avatar}" alt="" class="js-img">
            <input type="hidden" value="${people.id}" id="peopleid" />
            <span class="js-name" id="peoplerealName">${people.realName}</span>
            <div class="tn-line1 js-top-bt">
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx-on.png" alt="" class="pj-like">
                </a>
                <a href="javascript:">
                    <img src="../images/front/pjx.png" alt="" class="pj-like">
                </a>
            </div>
        </div>
        <div class="xd-choice">
            <ul class="xd-cho-line1">
                <li>您的姓名:<input type="text" placeholder="请输入您的姓名" class="month-input" id="applyname"></li>
                <li class="xd-fuwu">联系电话:<input type="text" placeholder="请输入您的联系方式" class="month-input" id="applyphone"></li>
               <!--  <li class="xd-address">服务地址<span>请选择你的服务地址</span><i></i> </li>  -->
                <li class="xd-address">服务地址:<input type="text" placeholder="请输入您的地址" class="month-input" id="applyaddress"></li>
                <!-- 
                <li class="xd-time">服务开始时间<input class="weui_input" id="date2" type="text" value="2016-11-12"><i></i> </li>
                <li class="xd-time">服务结束时间<input class="weui_input" id="date1" type="text" value="2016-12-12"><i></i> </li>
                 -->
                 <li class="beizhusm">备注说明:</li>
                 <li><textarea rows="3" cols="50" class="beizhuxx" maxlength="500" id="description"></textarea></li>
                 <!-- <li><input type="text" placeholder="详细预约雇佣情况请在此备注"></li> -->
            </ul>
            <!-- 
            <ul class="xd-cho-line2">
                <li class="xd-yhq">优惠券<i></i> </li>
                <li>预付款 <span class="xd-money">158元</span></li>
            </ul>
             -->
        </div>    
       
        <span class="xiaomaitixin">小迈提醒您：申请完成后系统会为您向预约的服务人员发送一条消息，如过长时间未得到回应可直接通过联系方式联系该名服务人员。</span>
        <a href="#" class="xd-appo" onclick="addPeopleApply()">提交雇佣申请</a>
        
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript">lh.param = ${paramJson};</script>
    <script type="text/javascript" src="/js/front/people/peopleApply.js" title="v"></script>
	<script>
        $("#date2").calendar();
        $("#date1").calendar();
        $("#inline-calendar").calendar({
            container: "#inline-calendar",
            input: "#date3"
        });

    </script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}	
          	
	{{/rows}}
	</script>
</body>
</html>