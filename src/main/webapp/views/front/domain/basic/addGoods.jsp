<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/wpk-common.css" title="v"/>
<link rel="stylesheet" type="text/css" href="/css/front/wpk-style.css" title="v"/>
<%-- 上传藏品- BasicAction:/goodsAdd --%>
</head>
<body>
	<div class="container-fluid">
		<div class="row auction_title">
			<div class="col-xs-1">
				<i class="icon-angle-left icon-3x"></i>
			</div>
			<div class="col-xs-10 pt10 text-center">
				<span class="fs16">添加拍品</span>
			</div>
		</div>
		<div class="row ptb10">
			<div class="col-xs-12 pr7">
				<div class="col-xs-3 pl0 pr7">
					<img src="images/pic_01.png" class="img-responsive">
				</div>
				<div class="col-xs-3 pl0 pr7">
					<img src="images/pic_01.png" class="img-responsive">
				</div>
				<div class="col-xs-3 pl0 pr7">
					<img src="images/pic_01.png" class="img-responsive">
				</div>
				<div class="col-xs-3 pl0 pr7">
					<img src="images/pic_01.png" class="img-responsive">
				</div>
			</div>
			<div class="col-xs-12 pr7">
				<div class="col-xs-3 pl0 pr7">
					<div class="col-xs-9 plr0 pos-r">
						<input type="file" class="form-control fileUp" id="backdrop" />
						<div class="col-xs-12 plr0 fileAdd">
							<img src="images/addGoods.png" class="img-responsive">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row h10 bg_gray"></div>
		<div class="row">
			<div class="col-xs-12 bdb ptb5">
				<div class="col-xs-3 plr0 pt5">拍品名：</div>
				<div class="col-xs-9 plr0">
					<input type="text" class="form-control" id="name" placeholder="一生一石 天然红玛瑙手链单圈 "/>
				</div>
			</div>
			<div class="col-xs-12 h10 bg_gray"></div>
			<div class="col-xs-12 bdb ptb5">
				<div class="col-xs-3 plr0 pt5">拍品描述：</div>
				<div class="col-xs-9 plr0">
					<input type="text" class="form-control" id="goodsIntroduce" placeholder="红玛瑙手串"/>
				</div>
			</div>
			<div class="col-xs-12 h10 bg_gray"></div>
			<div class="col-xs-12 bdb ptb5">
				<div class="col-xs-3 plr0 pt5">起拍价：</div>
				<div class="col-xs-9 plr0">
					<input type="text" class="form-control" id="price" placeholder="1元"/>
				</div>
			</div>
			<div class="col-xs-12 h10 bg_gray"></div>
			<div class="col-xs-12 bdb ptb5">
				<div class="col-xs-3 plr0 pt5">运费：</div>
				<div class="col-xs-9 plr0">
					<input type="text" class="form-control" id="freight" placeholder="0元"/>
				</div>
			</div>
		</div>
		<div class="row pt20">
			<div class="col-xs-12">
				<a role="button" class="btn btn-orange col-xs-12">保存</a>
			</div>
		</div>
	</div>
    
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<input type="hidden" id="shopId" value="${shopId}">
	<input type="hidden" id="from" value="${from}">
	  
	<input type="hidden" id="openId" value="${openId}">
	<input id="appId" type="hidden" value="${appId}" />
	<input id="timeStamp2" type="hidden" value="${timeStamp}" />
    <input id="nonceStr2" type="hidden" value="${nonceStr}" />
    <input id="signature" type="hidden" value="${signature}" />
    
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/addGoods.js" title="v"></script>
	
</body>
</html>
