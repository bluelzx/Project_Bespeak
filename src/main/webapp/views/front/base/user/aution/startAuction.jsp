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
<link rel="stylesheet" href="/third-party/jquery-weui1/lib/weui.min.css">
<link rel="stylesheet" href="/third-party/jquery-weui1/css/jquery-weui.css">

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
.weui-pull-to-refresh-layer{
	display: none;
}
.auction_title{
	position:relative;
}
.bg_grays{
color: #FF5000;
background-color: #FF5000;
}
</style>
</head>
<body style="background-color: #f0f0f6;">
    <div class="container-fluid">
        <div class="row auction_title">
            <div class="col-xs-3" onclick="lh.back();">
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-7 pt10 plr0 text-center">
                <span class="fs16">我的提醒</span>
            </div>
            <div class="col-xs-2 ptb15 pl7 text-right"  onclick="showShare();" >
                <span>分享</span>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12 plr0 jewel_title">
                <ul>
                    <li class="active">专场提醒</li>
                    <li>拍品提醒</li>
                </ul>
            </div>
        </div>
        <div id="data-container">
		
		</div>
       <!--   <div class="row">
        
           <div class="col-xs-12 product plr0 pt6">
                <div class="col-xs-12 productMs">
                    <div class="col-xs-12 plr0 ptb10 bdb">
                        <div class="col-xs-4">
                            <img class="img-responsive" src="/images/front/pic_01.png" />
                        </div>
                        <div class="col-xs-8 plr0 pt10">
                            <div class="gray_deep fs20">翡翠A货小吊坠</div>
                            <div class="col-xs-12 plr0 mt10">
                                <span class="gray pull-left fs16">当前价：<span class="fs20 orange">12万</span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ptb10 orange">
                        <div class="col-xs-6 plr0 pt5">
                            <span>4月13日</span>&nbsp;&nbsp;<span>12:00</span>&nbsp;&nbsp;<span>结束</span>
                        </div>
                        <div class="col-xs-6 plr0">
                            <button class="btn btn-orange-remove pull-right" type="button">删除</button>
                        </div>
                    </div>
                </div>
            </div> 
           
        </div>-->
    </div>

    <%-- <%@ include file="/views/front/common/z_div_menu_bottom.htm"%> --%>
	<!-- 底部菜单 -->
		<%@ include file="/views/common/common_share.htm"%><!-- 分享DIV -->
	<%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
	<!-- <script type="text/javascript">lh.param = ${paramJson}</script> -->
	<!-- <script type="text/javascript">lh.param = ${paramJson}</script> -->
	<script src="/js/front/user/aution/startAuction.js" title="v"></script>
	<!-- -->
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
	<div class="row" id="rows{{id}}">
			<div class="col-xs-12">
				<div class="col-xs-12 plr0 ptb10 bdb">
					<div onclick="lh.jumpR('/ag/page/agList/{{ serial }}')" class="col-xs-11 plr0 elli">【第{{auctionSerial}}场】{{auctionName}}</div>
					<div class="col-xs-1 plr0" onclick="lh.jumpR('/ag/page/agList/{{ serial }}')">
						<img width="20" height="20" class="img-responsive center-block" src="/images/front/pic_06.png" />
					</div>
				</div>
			</div>
			<div class="col-xs-12 ptb10">
				<img onclick="lh.jumpR('/ag/page/agList/{{ serial }}')" class="img-responsive" src="{{getPicPath}}" />
			</div>
			<div class="col-xs-12 pb10">
				<div class="col-xs-8 plr0">
					<div class="col-xs-4 plr0">
						<span class="orange">30</span>件拍品
					</div>
					<div class="col-xs-4 plr0">
						<span class="orange">{{joinNum}}</span>人出价
					</div>
					<div class="col-xs-4 plr0">
						<span class="orange">{{visitNum}}</span>围观						
					</div>
				</div>
				<div class="col-xs-4 plr0">
					{{&statusDom}}
				</div>
			</div>
 					<div class="col-xs-12 ptb10 orange">
                        <div class="col-xs-6 plr0 pt5">
                            <span>{{&date}}</span>&nbsp;&nbsp;&nbsp;<span>结束</span>
                        </div>
                        <div class="col-xs-6 plr0">
                            <button class="btn btn-orange-remove pull-right" type="button" onclick="deleteSchedulers({{id}});">删除</button>
                        </div>
                    </div>

			<div class="col-xs-12 h10 bg_grays"></div>
		</div>
	{{/rows}}	 		 
	</script>
    
</body>
</html>