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
</style>
</head>
<body style="background-color: #f8f8f8;">
	<div class="container-fluid">
		<div class="row auction_title">
			<div class="col-xs-3" onclick="lh.back();">
				<i class="icon-angle-left icon-3x"></i>
			</div>
			<div class="col-xs-7 pt10 plr0 text-center">
				<span class="fs16">发布公告</span>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 ptb5">
				<div class="col-xs-12 plr0">
					<input name="title" id="title" class="form-control bdn gray_bg_init" type="text" value="" placeholder="请输入标题（5~25个字）" maxlength="25" onkeyup="javascript:setShowLength(this, 25, 'name_word');">
				</div>
				<div class="col-xs-12 plr0 fs10 gray text-right">
					<span class="red" id="name_word">还可以输入25字数</span>
				</div>
			</div>
			<div class="col-xs-12 bdb ptb5" >
				<div class="col-xs-12 plr0">
					<textarea name="content" id="content" maxlength="1000" onkeyup="javascript:setShowLength(this, 1000, 'goodsIntroduce_word');" rows="10" class="form-control gray_bg_init bdn" id="goodsIntroduce" placeholder="请输入内容！（不超过1000字）"></textarea>
				</div>
				<div class="col-xs-12 plr0 fs10 gray text-right">
					<span class="red" id="goodsIntroduce_word">还可以输入1000字数</span>
				</div>
			</div>
		</div>
<input type="hidden" id="forumId" value="${forum.id}"/>
<input type="hidden" id="userId" value="${user.id}"/>
<input type="hidden" id="userName" value="${user.username}"/>
	</div>
	<div class="fixed_topic pt15 pr7" onclick="addAnnouncement();">
		<label for="sharecsbx" class="col-xs-10" style="display: block;" >
			<div class="myCircleIndex" id="announcementController">
		    发布公告
	</div>
		</label> 
	</div>
	<script>
		
	</script>


	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<input type="hidden" id="shopId" value="${shopId}">
	<input type="hidden" id="from" value="${from}">
	  <input type="hidden" id="userId">
	<input type="hidden" id="openId" value="${openId}">
	<input id="appId" type="hidden" value="${appId}" />
	<input id="timeStamp2" type="hidden" value="${timeStamp}" />
    <input id="nonceStr2" type="hidden" value="${nonceStr}" />
    <input id="signature" type="hidden" value="${signature}" />

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
	<script src="/js/front/announcement/addAnnouncement.js" title="v"></script>
	<!-- -->
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
	{{/rows}}	 		 
	</script>

</body>
</html>
