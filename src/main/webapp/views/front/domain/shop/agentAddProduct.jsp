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
<style type="text/css">
	.c_0100_3 li{
		float: none;
  		width: 100%;
	}
</style>
</head>
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<div class="c_0100_3 information_border" style="padding:10px;margin-top:60px;">
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>服务方式:<br/>
				<select  id="serviceType" style="width:95%">
					<option value="">请选择</option>
					<option value="1" >送件上门</option>
					<option value="2" >服务人员到店取件(取件加10元服务费)</option>
				</select>
				</nobr>
			</div>
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>产品类型:<br/>
				<select id="productType"  style="width:95%">
					<option value="">请选择</option>
					<c:forEach var="goodsDidt" items="${goodsDidtList}">
						<option value="${goodsDidt.id}" >${goodsDidt.dictName}</option>
					</c:forEach>
				</select>
				</nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>产品件数:<br/><input type="text" id="goodsNum"  style="width:95%;"/></nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>每件产品拍摄张数:<br/><input type="text" id="num"  style="width:95%;"/></nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>用户联系方式:<br/><input type="text" id="userPhone" value="${user.phone}"  style="width:95%;"/></nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>用户详细地址:<br/><input type="text" id="userAddress" value="${user.address}"  style="width:95%;"/></nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>平台联系方式:<br/><input type="text" id="platformPhone" value=""  style="width:95%;"/></nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>总的费用:<br/><input type="text" id="totalMoney" readOnly="readOnly"  style="width:95%;"/></nobr>
			</div>
	  </div>
	 <div class="save bottomFix" >
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="addApply();return false;">提交申请</a></li>
		</ul>
	  </div>
	  
	  <div id="offerWin" class="szweituo" style="display: none;">
		<div class="szweituodiv">
			<div class="szwtcontent">
				<div class="szwtinput">
					<div id="offerTip" class="szwtzuigao">输入支付密码:</div>
					<div id="offerIptDiv" class="szwtinpk">
						<form id="entrustFrom">
							<span> <input type="password" id="payPass" placeholder="请在此输入输入支付密码" >
							</span>
						</form>
						<div style="clear: both;"></div>
					</div>
					<div class="szwtbutton">
						<span id="offerCancel" onclick="doAutoOffer('cancel')" class="szwtbutr entrusta2 pointer">取消</span> 
						<span id="offerSave" onclick="doAutoOffer('save')" class="szwtbutl entrusta1 pointer">确定</span>
						<div style="clear: both;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
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
	<script type="text/javascript" src="/js/front/shop/agentAddProduct.js" title="v"></script>
	
</body>
</html>
