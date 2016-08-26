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
<link rel="stylesheet" type="text/css" href="/third-party/weui/weui.min.css" />
<link rel="stylesheet" type="text/css" href="/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css"/>
<style type="text/css">
.weui_cells_checkbox .weui_check:checked+.weui_icon_checked:before {
	content: '\EA06';
	color: #09bb07;
	font-size: 40px;
}

.weui_cells_checkbox .weui_icon_checked:before {
	content: '\EA01';
	color: #c9c9c9;
	display: block;
	font-size: 40px;
}
</style>
</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<div class="pz_main" style="margin-top: 50px;">
		<c:if test="${!empty orderGoods}">
			<div class="weui_cells weui_cells_form">
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="myLabel">订单商品信息</label>
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<div style="display: inline-block; height: 120px;">
							<img src="${orderGoods.picPath}@120h_120w_1e_1c_80Q" height="120px" width="120px" />
						</div>
						<div style="display: inline-block; height: 120px; vertical-align: top;">
							<div style="height: 70px; overflow: hidden;">${orderGoods.goodsName}</div>
							<div style="position: absolute; bottom: 5px; right: 5px; color: gray; font-size: 16px;"></div>
						</div>
					</div>
				</div>
				<c:if test="${!empty addressList}">
					<div class="weui_cell">
						<div class="weui_cell_hd" style="color: gray; font-size: 16px;">
							<label class="myLabel">收货地址</label><br/>
							<c:forEach items="${addressList}" var="address">
								<select id="addressSelect" style="max-width:95%;">
									<option value="${address.id}"<c:if test="${address.isDefault == 2}"> selected="selected"</c:if>>
										${address.addressDetail}
									</option>
								</select>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</div>
			
			<c:if test="${empty addressList}">
				<div class="weui_cells weui_cells_form" style="">
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">收货人：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="receiverName" value="${user.realName}" placeholder="(必填)">
		                </div>
		            </div>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">所在省：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                	<select id="province" onChange="getCity();return false;">
		                		<option value="">请选择</option>
								<c:forEach var="province" items="${provinceList}">
									<option value="${province.id}" <c:if test="${province.id == user.province}">selected="selected"</c:if>>${province.areaName}</option>
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
		                    <input class="weui_input" type="text" id="addressDetail" placeholder="(必填)">
		                </div>
		            </div>
					<div class="weui_cell">
		                <div class="weui_cell_hd"><label class="myLabel">联系电话：</label></div>
		                <div class="weui_cell_bd weui_cell_primary">
		                    <input class="weui_input" type="text" id="phone"  value="${user.phone}" placeholder="(必填)">
		                </div>
		            </div>
				</div>
			</c:if>
			
		</c:if>
		
		<c:if test="${!empty asg}">
			<div class="weui_cells weui_cells_form">
				<div class="weui_cell">
					<div class="weui_cell_hd">
						<label class="myLabel">拍品信息</label>
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<div style="display: inline-block; height: 120px;">
							<img src="${asg.picPath}@120h_120w_1e_1c_50Q" height="120px" width="120px" />
						</div>
						<div style="display: inline-block; height: 120px; vertical-align: top;">
							<div style="height: 70px; overflow: hidden;">${asg.goodsName}</div>
							<div style="position: absolute; bottom: 5px; right: 5px; color: gray; font-size: 16px;">最后出价：￥${asg.offerPrice}元</div>
						</div>
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd" style="color: gray; font-size: 16px;">
						<label class="myLabel">起拍价：${asg.priceBegin}元</label>
					</div>
				</div>
			</div>
		</c:if>

		<div class="weui_cells weui_cells_form">
			<div class="weui_cell">
				<c:if test="${!empty orderGoods}">
					<div class="weui_cell_hd">
						<label class="myLabel">成交价格：<span id="dealMoney" style="color: red;">￥${orderGoods.shopPrice}元</span></label>
					</div>
				</c:if>
				<c:if test="${empty orderGoods}">
					<div class="weui_cell_hd">
						<label class="myLabel">保证金：<span id="creditMoney" style="color: red;"></span></label>
					</div>
				</c:if>
				<div class="weui_cell_bd weui_cell_primary"></div>
			</div>
			<div class="weui_cell weui_cells_checkbox">
				<label class="weui_cell weui_check_label" style="position: relative; right: 10px; padding: 0px 5px;" for="s11">
					<div class="weui_cell_hd">
						<input type="checkbox" class="weui_check" name="checkbox1" id="s11" onchange="choosePay(1);"> 
						<i class="weui_icon_checked"></i>
					</div> 
					<img src="/images/front/pay/01.gif" width="50px;" style="margin-left: 5px;" />
				</label>
				<div class="weui_cell_bd weui_cell_primary" style="position: relative; right: 10px;">
					<!-- <div style="font-size:17px;">余额支付</div> -->
					<div id="moneyName" style="font-size:17px;">余额支付</div>
					<c:if test="${empty noPayPass}">
						<div style="font-size: 15px; color: gray;">可用余额：￥${money}元</div>
					</c:if>
					<c:if test="${!empty noPayPass}">
						<div onclick="lh.jumpR('/initSafetyPage');" style="position:absolute;right:0px;bottom:0px;color:#1763C5;">点击开通</div>
					</c:if>
				</div>
			</div>

			<div id="payPassIpt" class="weui_cell_bd weui_cell_primary" style="margin-left: 60px;display:none;">
				<input class="weui_input" type="password" id="payPass" placeholder="请在此输入支付密码">
			</div>

			<div class="weui_cell weui_cells_checkbox">
				<label class="weui_cell weui_check_label" style="position: relative; right: 10px; padding: 0px 5px;" for="s12">
					<div class="weui_cell_hd">
						<input type="checkbox" class="weui_check" name="checkbox1" id="s12" checked="checked" onchange="choosePay(2);"> 
						<i class="weui_icon_checked"></i>
					</div> 
					<img src="/images/front/pay/02.gif" width="50px;" style="margin-left: 5px;" />
				</label>
				<div class="weui_cell_bd weui_cell_primary" style="position: relative; right: 10px;">
					<div style="font-size: 17px;">微信支付</div>
					<div style="font-size: 15px; color: gray;">使用微信支付，简单方便</div>
				</div>
			</div>
		</div>

		<div>
			<a href="javascript:;" onclick="doPay();" class="weui_btn weui_btn_primary" style="width: 90%; margin-top: 20px;">安全支付</a>
		</div>

		<div class="pz_down">
			<div class="c_0100_9"></div>
		</div>
		<div class="save bottomFix">
			<ul>
				<li id="save" style="display: none;"><a href="javascript:;" class="a_say" onclick="addBindUserPhone();return false;">保存</a></li>
			</ul>
		</div>
	</div>
	<input type="hidden" value="${r}" id="r" />
	<input type="hidden" value="${loginStatus}" id="loginStatus" />
	<input type="hidden" value="${orderGoods.shopPrice}" id="orderGoodsMoney" />
	<input type="hidden" value="${money}" id="avaliableMoney" />
	<input type="hidden" value="${addressNull}" id="addressNull" />
	

	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script src="/js/front/user/payway.js" title="v"></script>

</body>
</html>
