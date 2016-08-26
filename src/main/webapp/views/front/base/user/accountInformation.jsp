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
 	<div class="pz_main" style="margin-top:50px;padding:10px;">
	 		<div class="modify">不可更改</div>
	 		<input type="hidden" value="${user.id}" id="id">
	 		<input type="hidden" value="${user.city}" id="cityValue">
	 		<%-- <input type="hidden" value="${antiqueCityId}" id="antiqueCityId">
	 		<input type="hidden" value="${antiqueCity.city}" id="antiqueCityCityValue"> --%>
	 		<%-- <input type="hidden" value="${user.sex}" id="sexValue"> --%>
	 		<div class="weui_cells weui_cells_form">
	 		<!-- <ul class="information">
				<li> -->
					<%-- <span>您的昵称:</span><br/>
					<input type="text" id="nickName" value="${user.username}" readonly="readonly"/> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">您的昵称:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="nickName" value="${user.username}" readonly="readonly" />
		                </div>
		            </div>
				<!-- </li>
				<li> -->
					<%-- <span>手机号码:</span><br/>
					<input type="text" id="phone" value="${user.phone}" readonly="readonly"/> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">手机号码:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="phone" value="${user.phone}" readonly="readonly" />
		                </div>
		            </div>
			<!-- 	</li>
			</ul> -->
			</div>
	 		<div class="modify">可更改</div>
	 		<div class="weui_cells weui_cells_form">
	 		<!-- <ul class="information">
				<li> -->
					<!-- <span style="color:red;">*</span> -->
					<%-- <span>真实姓名:</span>
					<input type="text" id="realName" value="${user.realName}" placeholder="请输入您的真实姓名" /> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">真实姓名:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="realName" value="${user.realName}" placeholder="请输入您的真实姓名"/>
		                </div>
		            </div>
				<!-- </li>
				<li> -->
					<%-- <span style="color:red;">*</span>
					<span>详细地址:</span>
					<input type="text" id="address"value="${user.address}" placeholder="请填写您的详细地址" /> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">详细地址:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="address"value="${user.address}" placeholder="请填写您的详细地址"/>
		                </div>
		            </div>
				<!-- </li>
				<li> -->
					<%-- <span style="color:red;">*</span>
					<span>城市:</span><br/>
					<select id="city" value="${user.city}">
						<option value="">请选择</option>
						<c:forEach var="provinceCityArea" items="${provinceCityAreaList}">
							<option value="${provinceCityArea.id}">${provinceCityArea.areaName}</option>
						</c:forEach>
					</select> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">城市:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <select id="city">
								<c:forEach var="provinceCityArea" items="${provinceCityAreaList}">
									<option value="${provinceCityArea.id}">${provinceCityArea.areaName}</option>
								</c:forEach>
							</select>
		                </div>
		            </div>
				<!-- </li>
				<li> -->
					<%-- <span style="color:red;">*</span>
					<span>商圈:</span><br/>
					<select id="antiqueCity" value="${user.antiqueCityId}">
						<option value="">请选择</option>
						<c:forEach var="antiqueCity" items="${antiqueCityList}">
							<option value="${antiqueCity.id}">${antiqueCity.name}</option>
						</c:forEach>
					</select> --%>
					<%-- <div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">商圈所在省：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                	<select id="antiqueCityProvince" onChange="getCity();return false;">
		                		<option value="">请选择</option>
								<c:forEach var="province" items="${provinceList}">
									<option value="${province.id}" <c:if test="${province.id == antiqueCity.province}">selected="selected"</c:if>>${province.areaName}</option>
								</c:forEach>
							</select>
		                </div>
		            </div>
		            <div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">商圈所在市：</label></div>
		                <div id="cityDiv" class="weui_cell_bd weui_cell_primary">
		                	<select id="antiqueCityCity"></select>
		                </div>
		            </div> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">加入的商圈:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                	<select id="antiqueCity" >
								<c:forEach var="antiqueCity" items="${antiqueCityList}">
									<option value="${antiqueCity.id}" <c:if test="${antiqueCity.id == antiqueCityId}">selected="selected"</c:if>>${antiqueCity.name}</option>
								</c:forEach>
							</select>
		                </div>
		             </div>
				<!-- </li>
				<li> -->
					<%-- <span style="color:red;">*</span>
					<span>您的QQ:</span>
					<input type="text" id="qq" value="${user.qq}" placeholder="请输入您的QQ号码"/> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">您的QQ:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="qq" value="${user.qq}" placeholder="请输入您的QQ号码"/>
		                </div>
		            </div>
				<!-- </li>
				<li> -->
					<!-- <span style="color:red;">*</span>
					<span>性别:</span><br/>
					<select  id="sex">
						<option value="">请选择性别</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select> -->
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">性别:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                	<select  id="sex" style="width:30%">
		                		<c:if test="${user.sex == 1}">
									<option value="1" selected="selected">男</option>
									<option value="2" >女</option>
								</c:if>
								<c:if test="${user.sex == 2}">
									<option value="2" selected="selected">女</option>
									<option value="1" >男</option>
								</c:if>
								<c:if test="${empty user.sex}">
									<option value="1" >男</option>
									<option value="2" >女</option>
								</c:if>
							</select>
		                </div>
		             </div>
				<!-- </li>
				<li> -->
					<%-- <span style="color:red;">*</span>
					<span>联系方式:</span>
					<c:if test="${!empty user.contactWay}">
						<input type="text" id="contactWay" value="${user.contactWay}"/>
					</c:if>
					<c:if test="${empty user.contactWay}">
						<input type="text" id="contactWay" value="${user.phone}"/>
					</c:if> --%>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">联系方式:</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <c:if test="${!empty user.contactWay}">
								<input type="text" class="weui_input" id="contactWay" value="${user.contactWay}"/>
							</c:if>
							<c:if test="${empty user.contactWay}">
								<input type="text" class="weui_input" id="contactWay" value="${user.phone}"/>
							</c:if>
		                </div>
		            </div>
		            <div class="weui_cell">
		            </div>
			<!-- 	</li>
			</ul> -->
			</div>
			<div class="pz_down">
				<div class="c_0100_9"></div>
			</div>
		<div class="save">
			<ul>
				<li><a href="javascript:;" class="a_say" onclick="saveInformation();return false;">保存</a></li>
			</ul>
		</div>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<input type="hidden" id="antiqueCityCityValue" value="${antiqueCity.city}">
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script src="/js/front/user/accountInformation.js" title="v"></script>
</body>
</html>
