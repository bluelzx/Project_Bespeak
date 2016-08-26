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
<link rel="stylesheet" type="text/css" href="/third-party/select2/css/select2.min.css"/>
</head>
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<input type="hidden" id="wholesaleId" value="${wholesale.id}">
	<input type="hidden" id="userId" value="${userId}">
	<div class="c_0100_3 information_border" style="padding:10px;margin-top:60px;">
	  		<%-- <div style="font-size:14px;margin:5px 0px;">
				<nobr>批发城名称:<br/><input type="text" id="name"  style="width:95%;" value="${wholesale.name}"/></nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>批发城地址:<br/><input type="text"  id="address"  style="width:95%;" value="${wholesale.address}"/></nobr>
			</div> --%>
			 <div class="weui_cells weui_cells_form">
				<div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">批发城名称:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="name" placeholder="请填写批发城名称" value="${wholesale.name}"/>
		            </div>
		        </div>
				<div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">批发城地址:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="address" placeholder="请填写批发城地址" value="${wholesale.address}"/>
		            </div>
		        </div>
				<div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">联系电话:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="tel" placeholder="请填写联系电话" value="${wholesale.tel}"/>
		            </div>
		        </div>
		        <div class="weui_cell">
		             <textarea class="weui_textarea" placeholder="请填写批发城描述" rows="3" id="description">${wholesale.description}</textarea>
		             <div class="weui_textarea_counter"><span>0</span>/200</div>
		        </div>
		        <div class="weui_cell" style="background:#DDDAD6"></div>
		         <div class="weui_cell weui_cell_switch" onclick="goodsPrivilegeCheck()">
	                <div class="weui_cell_hd weui_cell_primary">允许代理复制本店铺商品到个人微店中</div>
	                <div class="weui_cell_ft">
	                	<c:if test="${wholesale.goodsPrivilege == 1}">
	                    	<input class="weui_switch" type="checkbox" checked="checked" id="goodsPrivilege">
	                    </c:if>
	                	<c:if test="${wholesale.goodsPrivilege == 2}">
	                    	<input class="weui_switch" type="checkbox"  id="goodsPrivilege">
	                    </c:if>
	                </div>
	            </div>
		         <!-- <div class="weui_cell weui_cell_switch" onclick="">
	                <div class="weui_cell_hd weui_cell_primary">一件也代发&nbsp;&nbsp;帮代理发货&nbsp;&nbsp;运费代理支付</div>
	                <div class="weui_cell_ft">
	                    <input class="weui_switch" type="checkbox" id="">
	                </div>
	            </div> -->
	        </div>
			 <div class="ipt_item_div" style="font-size:14px;margin:20px 0px;">
					<label>上传批发城logo：</label>
					<input type="hidden" id="instLogo" value="${ap.picPaths}">
					<img src="${ap.picPaths}" class="picurl">
					<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
				    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
					<button id="browse" type="button" class="weui_btn weui_btn_mini weui_btn_primary" >上传批发城logo</button>
		</div>
		<div id="upload_outer_div"><!-- 上传文件进度展示 --></div>
	  </div>
	 <div class="save bottomFix" >
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="editWholesale();return false;">保存</a></li>
		</ul>
	  </div>
	  
	  <input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="/third-party/select2/js/select2.min.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/editWholesale.js" title="v"></script>
	
</body>
</html>
