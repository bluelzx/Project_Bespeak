<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" href="/css/front/wpknew/my_seller.css" title="v">
 <link rel="stylesheet" href="/css/front/wpknew/common.css" title="v">
  <link rel="stylesheet" href="/cssjs_stable/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="/css/front/wpknew/font-awesome-ie7.min.css" title="v">
    <![endif]-->
    <script src="/cssjs_stable/js/jquery-1.11.2.js"></script>
   <script src="/js/front/user/myseller.js" title="v"></script>
    <script src="/cssjs_stable/js/bootstrap.min.js"></script>
   <style>
	.btn-sma{
		padding: 5px 8px;
		font-size: 14px;
	}
	.btn-red:focus,.btn-red:hover,.btn-red:active,.btn-red{
	    padding: 5px 8px;
	    color: #cc0000;
	    background-color: #fff;
	    border: 1px solid #cc0000;
	}
	.right_menu{
		position: absolute;
		right: 0px;
		top: 10px;
		width: 30px;
		font-size: 16px;
		text-align: center;
	}
   </style>
    
</head>
<body>
<input type="hidden" value="${user.id }" id="userId"/>
    <div class="container-fluid gray-init">
        <div class="row auction_title">
            <div class="col-xs-3" onclick="lh.back();"> 
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-6 pt10 plr0 text-center">
                <span class="fs16">师友管理</span>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 pr30">
	           <div id="teacherAndFriend"></div>
	           <!--  <div class="col-xs-12 ptb5 pr0 bdb">
		            <div class="col-xs-12 ptb10 plr0">
		            	<div class="col-xs-5 plr0">
		            		<img width="50" src="/images/front/portrait_03.png" class="dis-lin" />&nbsp;&nbsp;&nbsp;
		            		<span class="fs18">冷饮</span>
		            	</div>
		            	<div class="col-xs-7 pr0 pl7 pt8">
		            		<button type="button" class="btn btn-green btn-sma pull-right"><i class="icon-comments"></i>聊天</button>
		            		<button type="button" class="btn btn-red mr5 pull-right">+ 已关注</button>
		            	</div>
		            </div>
	            </div> -->
	            
		     <!--    <div class="right_menu gray-init">
		        	<a href="#"><span>#</span></a><br>
		        	<a href="#A"><span>A</span></a><br>
		        	<a><span>B</span></a><br>
		        	<a><span>C</span></a><br>
		        	<a><span>D</span></a><br>
		        	<a><span>F</span></a><br>
		        	<a><span>G</span></a><br>
		        	<a><span>H</span></a><br>
		        	<a><span>J</span></a><br>
		        	<a><span>K</span></a><br>
		        	<a><span>L</span></a><br>
		        	<a><span>M</span></a><br>
		        	<a><span>N</span></a><br>
		        	<a><span>P</span></a><br>
		        	<a><span>Q</span></a><br>
		        	<a><span>R</span></a><br>
		        	<a><span>S</span></a><br>
		        	<a><span>T</span></a><br>
		        	<a><span>U</span></a><br>
		        	<a><span>W</span></a><br>
		        	<a><span>X</span></a><br>
		        	<a><span>Y</span></a><br>
		        	<a><span>Z</span></a><br>
		        </div> -->
            </div>
        </div>
    </div>
    
    <div id="voice">
	</div>
    
    <%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
    <%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
	<script type="text/javascript" src="/js/front/fans/fans.js" title="v"></script>
    
   <script id="template" type="x-tmpl-mustache">
		{{#rows}}	 	
			<a href="javascript:void(0);">
				<div class="col-xs-12 ptb5 pr0 bdb">
		            <div class="col-xs-12 ptb10 plr0">
		            	<div class="col-xs-5 plr0" onclick="lh.jumpR('/user/{{userSerial}}');">
		            		<img width="50" src="{{fansAvatar}}@50w_50h_4e_240-240-246bgc_50Q" class="dis-lin" />&nbsp;&nbsp;&nbsp;
		            		<span class="fs18">{{fansName}}</span>
		            	</div>
		            	<div class="col-xs-7 pr0 pl7 pt8">
		            		<button type="button" class="btn btn-green btn-sma pull-right"  onclick="lh.jumpR('/chat/{{userSerial}}');"><i class="icon-comments"></i>聊天</button>
		            		
		            	</div>
		            </div>
	            </div> 
			</a>	 
		{{/rows}}
	</script>
    
</body>
</html>