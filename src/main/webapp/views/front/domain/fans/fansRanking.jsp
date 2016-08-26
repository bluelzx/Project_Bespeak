<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
</head>
<body>
<%-- <input type="hidden" id="currentUserId" value="${currentUserId}"> --%>
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->	
	<div class="pz_main" style="margin-top:50px;">
		<div class="c_0100_14">
			<div class="slide_teacher" id="slide_teacher">
				<div class="bd">
					<ul class="ul_out_box" id="ul_out_box2">
						<div class="inbd">
							<div class="inbd_ul" id="fansRanKing">
								
							</div>
						</div>
					</ul>
					<div id="resultTip" class="resultTip frontHide"></div>
					<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
				</div>
			</div>
		</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/fans/fansRanKing.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
		<div class="teacher_list pointer">
			<div class="l_60" onclick="lh.jumpR('/shop/{{id}}');">
				<img src="{{avatar}}" width="100%" />
			</div>
			<div class="r_595 pointer" style="width:60%" onclick="lh.jumpR('/shop/{{id}}');">
				<nobr>
					&nbsp;&nbsp;<span>{{username}}</span>
				</nobr>
			</div>
			<div class="r_595 pointer" style="width:60%" onclick="lh.jumpR('/shop/{{id}}');'">
				<nobr>
					粉丝数量:&nbsp;&nbsp;<span>{{fansSumCount}}</span>
				</nobr>
			</div>
			<div class="r_gz">
				{{#fansId}}<a href="javascript:;" onclick="cancelFocus('{{fansId}}');return false;" class="a_qxgz" >取消关注</a>{{/fansId}}
				{{^fansId}}<a href="javascript:;" onclick="focusHe('{{id}}');return false;" class="a_gz" >+关注</a>{{/fansId}}
			</div>
		</div>
	{{/rows}}		 		 
	</script>
</body>
</html>
