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
</head>
<body style="background-color: #f0f0f0;">
    <div class="container-fluid">
        <div class="row auction_title">
            <div class="col-xs-3" onclick="lh.back();">
                <i class="icon-angle-left icon-3x"></i>
            </div>
            <div class="col-xs-7 pt10 plr0 text-center">
                <span class="fs16">实名认证</span>
            </div>
        </div>
        <div class="row mt6">
            <div class="col-xs-12 white_bg">
                <div class="col-xs-12 plr0 bdb ptb5">
                	<div class="col-xs-3 plr0">
                		<img width="60" src="${user.avatar}@50w_50h_4e_240-240-246bgc_50Q" class="img-responsive center-block" />
                	</div>
                	<div class="col-xs-7 pl7 pr0">
                        <div class="col-xs-12 plr0 pt5 fs20">${user.username}</div>
                        <div class="col-xs-12 plr0 gray elli">立即实名认证享受更多特权服务</div>
                    </div>
                	<div class="col-xs-2 plr0 text-center red_deep pt20">
                		${isRealAuths}
                	</div>
                </div>
            </div>
        </div>
        <div class="row white_bg mt6">
            <div class="col-xs-12">
	            <div class="col-xs-12 plr0 ptb5 bdb gray">
	            	申请认证
	            </div>
	            <div class="col-xs-12 plr0 ptb10">
		            <div class="col-xs-6 plr0">
                		<img width="70" src="/images/front/real_name_lg_01.png" class="img-responsive center-block" />
                		<div class="text-center pt5">个人实名认证</div>
                		<div class="text-center pt5" onclick="lh.jumpR('/user/safety/individualAuthentication');"><a class="btn btn-realname" href="#" role="button">申请</a></div>
		            </div>
		            <div class="col-xs-6 plr0">
                		<img width="70" src="/images/front/real_name_lg_02.png" class="img-responsive center-block" />
                		<div class="text-center pt5">企业实名认证</div>
                		<div class="text-center pt5" onclick="lh.jumpR('/user/safety/enterpriseAuthentication');"><a class="btn btn-realname"  role="button">申请</a></div>
		            </div>
	            </div>
	        </div>
	    </div>
        <div class="row white_bg mt6">
            <div class="col-xs-12">
	            <div class="col-xs-12 plr0 ptb5 bdb gray">
	            	认证特权
	            </div>
	            <div class="col-xs-12 plr0 pt10 gray">
		            <div class="col-xs-6 plr0 elli">
                		<img width="20" src="/images/front/real_name_sm_01.png" class="dis-lin" />
                		提现次数增加到2次/天
		            </div>
		            <div class="col-xs-6 plr0 elli">
                		<img width="20" src="/images/front/real_name_sm_02.png" class="dis-lin" />
                		店铺当中增加认证标记
		            </div>
		        </div>
	            <div class="col-xs-12 plr0 ptb10 gray">
		            <div class="col-xs-6 plr0 elli">
                		<img width="20" src="/images/front/real_name_sm_03.png" class="dis-lin" />
                		增加点赞次数
		            </div>
		            <div class="col-xs-6 plr0 elli">
                		<img width="20" src="/images/front/real_name_sm_04.png" class="dis-lin" />
                		允许申请产品库
		            </div>
	            </div>
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
    
    
</body>
</html>