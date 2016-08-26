<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
    <meta charset="UTF-8">
    <title>培训详情</title>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body>
    <div id="wrapper">
        <div class="train-data-top">
            <a href="/courseIndex" class="back">
                <img src="../images/front/back.png" alt="">
            </a>
             
        </div>
        <div class="train-data-inf">
        <!-- 此页面拿到的是上一页面发送的数据 -->
         <input type="hidden" value="${course.id}" id="courseId" />
         <input type="hidden" value="${course.typeCode}" id="typeCode" />
         <input type="hidden" value="${course.startTime}" id="startTime" />
         <input type="hidden" value="${course.endTime}" id="endTime" />
            <img src="${course.attrStr}" alt="" class="tn-bg">
            <p class="train-data-more">${course.name}</p>
            <p class="train-title">授课讲师：${course.teacher}</p>
            <p class="cour-time" id="courtime">授课时间：${course.startTime}至${course.endTime}</p>
            <p>
            ${course.description}
            <!--  
                一、生活护理<br>
                首先是对自我的保健、身体恢复、起居等方面的护理。生活护理包括的面很广，通俗的说法就是，打理自己的生活起居。
                <br>
                二、乳房护理<br>
                对于乳房护理，这个就有的说了。下面详细介绍一下吧！
                产后体内荷尔蒙下降，母亲彷佛失去生命力一般，加上产后经期立即来潮，使您的身心受到相当大的负担。
                此时应多注意饮食健康，饮用足量的流体(每天至少500cc的牛奶及2000cc的开水或果汁)。如果您哺喂母奶的话，应注意乳房及乳头的护理。
                乳房与乳头
                由于乳房的尺寸及重量均增加，因此应穿着合身舒
                -->
            </p>
            
        </div>
        <a href="/courseApply/${course.id}" class="train-sign">培训报名</a>
        <p class="train-data-more">相关课程：</p>
        <div id="data-container" class="train-container">

        </div>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript">lh.param = ${paramJson};</script>
    <script type="text/javascript" src="/js/front/course/courseInfo.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}	
          <a href="/course/{{id}}" id="{{id}}" class="peixun-box">
                <img src="{{attrStr}}" alt="" class="train-headImg">
                <h2 class="train-title">{{name}}</h2>
                <p class='provider-zy'>授课讲师：{{teacher}}</p>
                <p class='cour-time'>授课时间：{{startdate}}至{{enddate}}</p>
                <span class='provider-juli'>{{address}}</span>
                <p class="train-inf">{{remark}}</p>
            </a>	
	{{/rows}}
	</script>
</body>
</html>