<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/auction.css" title="v" />
<link rel="stylesheet" type="text/css" href="/css/front/userCenter.css" title="v" />
</head>
<body style="background-color:#f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="pz_main" style="margin-top:50px;">
	 		<input type="hidden" id="userId" value="${userId}">
	 		<div class="c_0100_3" style="padding:0px;">
		 		<ul id="commentingList">
					
				</ul>
			</div>
			<div id="resultTip" class="resultTip frontHide"></div>
			<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
	</div>
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<div class="weui_dialog_confirm" id="dialog" style="display:none">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">是否确定删除这条信息</strong></div>
            <input type="hidden" id="orderId">
            <!-- <div class="weui_dialog_bd"></div> -->
            <div class="weui_dialog_ft">
                <a href="javascript:;" class="weui_btn_dialog default" onclick="doDelete('cancel')">取消</a>
                <a href="javascript:;" class="weui_btn_dialog primary" onclick="doDelete('save')">确定</a>
            </div>
        </div>
    </div>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script src="/js/front/user/commenting.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
				<div class="teacher_list" style="border: none;">
					<div class="pointer" style="float:left;" onclick="javascript:location.href='{{&shopUrl}}'">
						<img src="{{picPath}}" width="80px;" />
					</div>
					<div class="r_595 pointer" style="width:50%" >
						<nobr>
							&nbsp;&nbsp;藏品：<span>{{goodsName}}</span>
						</nobr>
					</div>
					<div class="r_595 pointer" style="width:50%" >
						<nobr>
							&nbsp;&nbsp;报价：<span>{{offerPrice}}元</span>
						</nobr>
					</div>
					<div class="r_595" style="width:50%" >
						<nobr>
							&nbsp;&nbsp;成交价格:<span>{{totalMoney}}元</span>
						</nobr>
					</div>
					<div style="clear:both;"><br/></div>
					{{^overComment}}
						<div class="pointer fr">
							<nobr>
								<button type="button" onclick="location.href='{{&shopUrl}}'" class="weui_btn weui_btn_primary" style="position: relative; z-index: 1;font-size:16px;">评论</button>
							</nobr>
						</div>
					{{/overComment}}
					{{#overComment}}
						<div class="pointer fl">
							<nobr>
								<span style="float:right;color:red;">已评论</span>
							</nobr>
						</div>
						<div class="pointer fr">
							<nobr>
								<button type="button" onclick="showCorfirmWin({{id}});return false;" class="weui_btn weui_btn_primary" style="position: relative; z-index: 1;font-size:16px;">删除</button>
							</nobr>
						</div>
					{{/overComment}}
				</div>
		{{/rows}}		 
	</script>
</body>
</html>
