<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<%@ include file="/views/common/common_front_wpk_css.htm"%>
<link rel="stylesheet" href="/cssjs_stable/css/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="/third-party/swiper/swiper.3.1.7.min.css" />
<link rel="stylesheet" href="/css/front/wpk/my_v1.css" title="v" />


<style>
.scrollbar_ul li {
    float: left;
    width: 110px;
    position: relative;
}
.weui_dialog {
	z-index: 9999 !important;
}

h1 {
	margin: 0 !important;
}

.swiper-pagination-bullet {
	background: white;
}

.tempWrap {
	max-height: 300px;
}
.icon-heart-emptys:before {
    content: "\f08a";
}
</style>
</head>
<body style="background-color: #ffffff;">
<input type="hidden" id="usersId" value="${user.id}"/>
<input type="hidden" id="isManager" value="${isManager}"/>
<input type="hidden" id="forumId" value="${forum.id}"/>


	<div class="container-fluid">
	
		<div class="row auction_title">
			<div class="col-xs-3" onclick="lh.back();">
				<i class="icon-angle-left icon-3x"></i>
			</div>
			<div class="col-xs-7 pt10 plr0 text-center">
				<span class="fs16">公告详情</span>
			</div>
		</div>
		<div id="data-container">
		<div class="row white_bg myTeam mt6">
			<div class="col-xs-12 ptb5" style="padding-top: 10px;padding-bottom: 5px;">
				<div class="col-xs-10 plr0" >
					<font size="5px" > 【公告】${announcement.title}</font>
				</div>
               <div class="col-xs-9 plr0 fs16 pt5 blue_deep" > 
                ${createdAt}
               </div>
			</div>
		
			</div>
			
		</div>
			<div style="word-break:break-all; width:100%; hight:100%; background:#FFFFFF">
			    <font  size="4px">
				${announcement.content}
				</font>
			</div>
			
		</div>
			<br/>
	<div class="myCircleIndex" id="announcementController" onclick="lh.jumpR('/addAnnouncement/${announcement.typeId}')">
		    发布公告
	</div>
	</div>

	<%-- <%@ include file="/views/front/common/z_div_menu_bottom.htm"%> --%>
	<!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
	<script type="text/javascript">lh.param = ${paramJson}</script>
	<!-- <script type="text/javascript">lh.param = ${paramJson}</script> -->
	  <script src="/js/front/announcement/announcementInfo.js" title="v"></script>
	<a style="margin-left: "></a>
	<script id="announcement" type="x-tmpl-mustache">
	{{#rows}}
		
	{{/rows}}	 		 
	</script>

</body>
</html>
