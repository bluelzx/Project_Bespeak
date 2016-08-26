<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
    <meta charset="UTF-8">
    <title>申请培训</title>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body>
    <div id="wrapper">
        <div class="qiye-header " id="px-sq">
            <a href="/course/${course.id}" class="back">
                <img src="../images/front/back.png" alt="">
            </a>
        </div>
        <form action="" class="yuyue-box">
        <input type="hidden" value="${course.id}" id="courseId" />
            <div class="yuyue-city yuyue-text">
                <span>所在城市:</span>
                <a href="../template/dist/user-address.html">-选择城市-</a>
                <input type="hidden" value="四川成都" id="coursecity" />
            </div>
            <div class="yuyue-city yuyue-text">
                <span>申请课程:</span>
                 <input type="hidden" value="${course.name}" id="coursename" />
                <a href="#">-${course.name}-</a>
            </div>
            <div class="yuyue-cpy yuyue-text">
                <label for="px-yy-name">姓&nbsp;名:</label>
                <input type="text" placeholder="请输入你的姓名" id="px-yy-name" required>
            </div>
             <div class="yuyue-add yuyue-text">
                <label for="px-yy-xb">性别</label>
                <select name="" id="px-yy-xb">
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
            <!-- 
            <div class="yuyue-add yuyue-text">
                <label for="px-yy-name">性&nbsp;别:</label>
                <input type="text" placeholder="请输入你的性别" id="px-yy-xb" required>
            </div>
             -->
            <div class="yuyue-user yuyue-text">
                <label for="px-yy-old">年&nbsp;龄:</label>
                <input type="number" placeholder="请输入你的年龄" class="easyui-numberspinner" id="px-yy-old" min="10" max="150" required >
            </div>
            <div class="yuyue-phone yuyue-text">
                <label for="px-yy-phone">联系电话:</label>
                <input type="number" placeholder="请输入你的联系电话" id="px-yy-phone" min="10000" required>
            </div>
        </form>
        <div class="yuyue-btn" onclick="addInvestigation()">
            <a href="#" class="qiye-yuyue">申请培训</a>
            <p class="dengji-p">/登记成功后会有相关工作人员联系您/</p>
        </div>
    </div>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript">lh.param = ${paramJson};</script>
    <script type="text/javascript" src="/js/front/course/courseApply.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}	
          	
	{{/rows}}
	</script>
</body>
</html>