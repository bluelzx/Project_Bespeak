<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" href="/css/front/wpknew/common.css" title="v">
<link rel="stylesheet" href="/css/front/wpknew/my_seller.css" title="v">
  <link rel="stylesheet" href="/cssjs_stable/css/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="/css/front/wpknew/font-awesome-ie7.min.css" title="v">
    <![endif]-->
    <script src="/cssjs_stable/js/jquery-1.11.2.js"></script>
    <script src="/cssjs_stable/js/bootstrap.min.js"></script>
   <script src="/js/front/user/myseller.js" title="v"></script>
   <style>
   .col-xs-4{width: 27.7777%}
   .col-xs-3{width: 17.7777%}
   </style>
</head>
<body style="background-color: #f0f0f6;">
    <div class="container-fluid">
        <div class="row auction_title">
            <div class="col-xs-2" onclick="lh.back();">
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-8 pt10 plr0 text-center">
                <span class="fs16">企业实名认证</span>
            </div>
        </div>
        <div class="row orange_bg pb10">
        	<div class="col-xs-3 plr0">
				<img width="28" src="/images/front/authentication_05.png" class="img-responsive center-block">
        		<div class="col-xs-12 plr0 text-center pt10 white">
        			企业信息
        			<img width="15" src="/images/front/authentication_04.png" class="pull-right mt5" />
        		</div>
        	</div>
        	<div class="col-xs-3 plr0">
				<img width="28" src="/images/front/authentication_01.png" class="img-responsive center-block">
        		<div class="col-xs-12 plr0 text-center pt10 white">
        			个人信息
        			<img width="15" src="/images/front/authentication_04.png" class="pull-right mt5" />
        		</div>
        	</div>
        	<div class="col-xs-3 plr0">
				<img width="28" src="/images/front/authentication_02.png" class="img-responsive center-block">
        		<div class="col-xs-12 plr0 text-center pt10 white">
        			店铺信息
        			<img width="15" src="/images/front/authentication_04.png" class="pull-right mt5" />
        		</div>
        	</div>
        	<div class="col-xs-3 plr0">
				<img width="28" src="/images/front/authentication_03.png" class="img-responsive center-block">
        		<div class="col-xs-12 plr0 text-center pt10 white">
        			提交审核
        		</div>
        	</div>
        </div>
        <div class="row">
        	<div class="col-xs-12 gray ptb5">
        		企业认证（请上传真实的企业信息，认证通过后将无法修改）
        	</div>
        </div>
        <div class="row white_bg bdb bdt">
        	<div class="col-xs-3 pr0 pb10 pt15">
				企业名称
        	</div>
        	<div class="col-xs-9 plr0 ptb10">
                <div class="col-xs-12 pl0">
            		<input type="text" class="form-control bdn" placeholder="请输入企业（公司）名称" >
                </div>
        	</div>
        </div>
        <div class="row white_bg bdb">
            <div class="col-xs-3 pr0 pb10 pt15">
                法人姓名
            </div>
            <div class="col-xs-9 plr0 ptb10">
                <div class="col-xs-12 pl0">
                    <input type="text" class="form-control bdn" placeholder="请输入企业（公司）法人姓名" >
                </div>
            </div>
        </div>
        <div class="row white_bg bdb">
            <div class="col-xs-3 pr0 pb10 pt15">
                身份证号
            </div>
            <div class="col-xs-9 plr0 ptb10">
                <div class="col-xs-12 pl0">
                    <input type="text" class="form-control bdn" placeholder="请输入企业（公司）法人身份证" >
                </div>
            </div>
        </div>
        <div class="row">
        	<div class="col-xs-12 ptb10">
        	</div>
        </div>
        <div class="row white_bg">
        	<div class="col-xs-12 ptb5 fs16">营业执照副本照片</div>
        	<div class="col-xs-12 blue fs12 pb15">上传营业执照照片</div>
        	<div class="col-xs-10 col-xs-offset-1 ptb20 bdda">
				<img width="150" src="/images/front/zhizhao.png" class="img-responsive center-block">
        	</div>
	        <div class="col-xs-8 col-xs-offset-2 mt15">
		        <button type="button" class="weui_btn weui_btn_primary fs16">上传营业执照副本</button>
		        <input type="file" name="fileField" class="file_authentication" >
		    </div>
	        <div class="col-xs-12 ptb5 gray-init text-center">
		        照片必须清晰哟！
		    </div>
        </div>
        <div class="row white_bg mt6">
        	<div class="col-xs-12 ptb5 fs16">法人身份证正面照</div>
        	<div class="col-xs-12 blue fs12 pb15">请使用横向拍摄以保证图片正常显示</div>
        	<div class="col-xs-10 col-xs-offset-1 ptb20 bdda">
				<img src="/images/front/ID_01.png" class="img-responsive center-block">
        	</div>
	        <div class="col-xs-8 col-xs-offset-2 mt15">
		        <button type="button" class="weui_btn weui_btn_primary fs16">上传正面照片</button>
		        <input type="file" name="fileField" class="file_authentication" >
		    </div>
	        <div class="col-xs-12 ptb5 gray-init text-center">
		        照片必须清晰哟！
		    </div>
        </div>
        <div class="row white_bg mt6">
        	<div class="col-xs-12 ptb5 fs16">法人身份证反面照</div>
        	<div class="col-xs-12 blue fs12 pb15">请使用横向拍摄以保证图片正常显示</div>
        	<div class="col-xs-10 col-xs-offset-1 ptb20 bdda">
				<img src="/images/front/ID_02.png" class="img-responsive center-block">
        	</div>
	        <div class="col-xs-8 col-xs-offset-2 mt15">
		        <button type="button" class="weui_btn weui_btn_primary fs16">上传反面照片</button>
		        <input type="file" name="fileField" class="file_authentication" >
		    </div>
	        <div class="col-xs-12 ptb5 gray-init text-center">
		        照片必须清晰哟！
		    </div>
        </div>
        <div class="row ptb20">
            <div class="col-xs-12">
                <a href="" rel="button" class="col-xs-12 btn btn-lg btn-orange">下一步</a>
            </div>
        </div>
    </div>
    
    	<%@ include file="/views/front/common/z_div_common.htm"%><!-- 通用DIV -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/cssjs_stable/js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript" src="/third-party/swiper/swiper.3.1.7.jquery.min.js"></script>
	<script type="text/javascript" src="/js/front/wpk/my.js" title="v"></script>
   <!--  <script src="/js/front/user/safety/individualMsgAuthentication.js" title="v"></script> -->
    
    
</body>
</html>