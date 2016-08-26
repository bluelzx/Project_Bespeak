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
<link rel="stylesheet" type="text/css" href="/third-party/weui/weui.min.css"/>
<link rel="stylesheet" type="text/css" href="/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css"/>
<style type="text/css">
	.information li span{padding:0px;width: 100%;}
	.c_0100_3 li{
		float: none;
  		width: 100%;
	}
</style>
</head>
<body style="background-color:#f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="weui_cells weui_cells_form" style="margin-top:50px;">
	 		<ul class="information">
	 			<li class="realName" style="background:#DDDAD6">
	 				<button class="btn weui_btn_primary fl pointer" style="color: #FFFFFF;" onclick="javascript:history.go(-1);">取消</button>
	 				<button class="btn weui_btn_primary fr pointer" style="color: #FFFFFF;" onclick="javascript:saveApply();return false;">保存</button>
	 			</li>
	 		</ul>
			<div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">证件类型:</label></div>
                <div class="weui_cell_bd weui_cell_primary" >
					<select id="attr1" class="selectpicker">
						<option selected="selected" value="1">身份证</option>
						<option value="2">驾驶证</option>
						<option value="3">军官证</option>
						<option value="4">港澳台通行证</option>
						<option value="5">护照</option>
					</select>
				</div>
			</div>
			<!-- <span>证件号码:</span>
			<input type="text"  id="attr2" placeholder="请输入证件号码" /> -->
			<div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">证件号码:</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="attr2" placeholder="请输入证件号码">
                </div>
            </div>
			<!-- <span>证件姓名:</span>
			<input type="text"  id="attr3" placeholder="请输入您的真实姓名"/> -->
			<div class="weui_cell">
                <div class="weui_cell_hd"><label class="myLabel">证件姓名:</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="attr3" placeholder="请输入您的真实姓名">
                </div>
            </div>
			<div class="ipt_item_div c_0100_3 information_border" style="padding:0px;">
				<label>上传证件：</label>
				<input type="hidden" id="instLogo" value="${ap.picPaths}">
				<img src="${ap.picPaths}" class="picurl">
				<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
			    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
				<button id="browse" type="button" class="weui_btn weui_btn_mini weui_btn_primary" >上传证件</button>
				<div id="upload_outer_div"><!-- 上传文件进度展示 --></div>
				<span class="formateImg">点上传证件，需上传手持身份证照片1张，身份证正面照1张，身份证反面照1张，如图：</span>
			 </div>	
			 <div class="box-item1">
			 	<div class="boxImg">
			 		<img src="/images/front/shenfenzheng1.jpg" style="width: auto;">
			 		<img src="/images/front/shenfenzheng2.jpg" style="width: auto;">
			 		<img src="/images/front/shenfenzheng3.jpg" style="width: auto;">
			 	</div>
			 </div>
			<!-- <div class="save bottomFix" >
				<ul>
					<li>
						<a href="javascript:;" class="a_say" onclick="editPassword();return false;">确定修改</a>
						<a href="javascript:;" class="a_say" onclick="editPassword();return false;">确定修改</a>
					</li>
				</ul>
			</div> -->
	</div>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script src="/js/front/user/realName.js" title="v"></script>
</body>
</html>
