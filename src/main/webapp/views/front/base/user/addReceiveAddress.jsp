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
<link rel="stylesheet" type="text/css" href="/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css"/>
</head>
<body style="background-color:#f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
 	<div class="pz_main" style="margin-top:50px;">
	 		<div class="weui_cells weui_cells_form" style="min-height: 500px;">
						<input type="text" id="receiverName" value="${userAddress.receiverName}" placeholder="(必填)"/> --%>
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">收货人：</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="text" id="receiverName" value="${userAddress.receiverName}" placeholder="(必填)">
			                </div>
			            </div>
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">所在省：</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                	<select id="province" onChange="getCity();return false;">
			                		<option value="">请选择</option>
									<c:forEach var="province" items="${provinceList}">
										<option value="${province.id}" <c:if test="${province.id == userAddress.province}">selected="selected"</c:if>>${province.areaName}</option>
									</c:forEach>
								</select>
			                </div>
			            </div>
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">所在市：</label></div>
			                <div id="cityDiv" class="weui_cell_bd weui_cell_primary">
			                	<select id="city"></select>
			                </div>
			            </div>
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">详细地址：</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="text" id="addressDetail" value="${userAddress.addressDetail}" placeholder="(必填)">
			                </div>
			            </div>
						<div class="weui_cell">
			                <div class="weui_cell_hd"><label class="myLabel">联系电话：</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="text" id="phone"  value="${userAddress.phone}" placeholder="(必填)">
			                </div>
			            </div>
				</div>
			<div class="save bottomFix" >
				<ul>
					<li><a href="javascript:;" class="a_say" onclick="addUserAddress();return false;">保存</a></li>
				</ul>
			</div>
	</div>
	
	<input type="hidden" id="id" value="${id}">
	<input type="hidden" id="userId" value="${user.id}">
	<input type="hidden" id="userAddressId" value="${userAddress.id}">
	<input type="hidden" id="userAddressCity" value="${userAddress.city}">
	<input type="hidden" id="userAddressProvince" value="${userAddress.province}">
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script src="/js/front/user/addReceiveAddress.js" title="v"></script>
	
</body>
</html>
