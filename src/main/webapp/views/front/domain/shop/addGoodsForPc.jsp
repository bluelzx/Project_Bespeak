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
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<%-- <input type="hidden" id="shopId" value="${shopId}"> --%>
	<div class="c_0100_3 information_border" style="padding:10px;margin-top:60px;margin-bottom: 100px;">
			<div id="successTip" style="font-size:14px;margin:5px 0px;text-align:center;color:green;">电脑端上传藏品</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>客户手机号码:<br/><input type="text" id="custPhone"  style="width:95%;" placeholder="请填写客户手机号码"/></nobr>
			</div>
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>藏品类型:<br/>
				<select  id="catId" style="width:95%">
					<option value="">请选择</option>
					<c:forEach var="goodsDidt" items="${goodsDidtList}">
						<option value="${goodsDidt.id}" >${goodsDidt.dictName}</option>
					</c:forEach>
				</select>
				</nobr>
			</div>
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>藏品名称:<br/><input type="text" id="goodsName"  style="width:95%;" placeholder="请填写藏品名称,35字以内"/></nobr>
			</div>
			<span style="font-size:14px;">藏品描述:</span>
	  		<div style="font-size:14px;margin-top:5px;">
				<textarea id="goodsDescription" style="height:100px;width:95%;" placeholder="请填写藏品描述,500字以内"></textarea>
			</div>
	  		<div style="font-size:14px;margin:5px 0px;">
				<nobr>参考价<span style="color:gray;">(如果不填,则为议价)</span>:
					<br/><input type="text" id="shopPrice" style="width:95%;"/>
				</nobr>
			</div>
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>是否包邮:<br/>
					<select id="postageFee" style="width:95%" ><!-- onchange="postage()" -->
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
				</nobr>
				<!-- <br/><input type="text" id="postageFeeValue" style="width:95%;display:none;" placeholder="请填写邮费"/> -->
			</div>
			
			<div style="font-size:14px;margin:5px 0px;">
				<nobr>是否7天包退:<br/>
					<select id="isSevenReturn" style="width:95%">
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
				</nobr>
			</div>
			 <div class="ipt_item_div" style="font-size:14px;margin:20px 0px;">
					<label>上传藏品图片(第一张默认设置为封面)：</label>
					<input type="hidden" id="instLogo" value="${ap.picPaths}">
					<img src="${ap.picPaths}" class="picurl">
					<input type="hidden" name="filePaths" id="filePaths" value="${ap.picPaths}"/>
				    <input type="hidden" name="fileDBIds" id="fileDBIds"/>
					<button id="browse" type="button" class="btn btn-default" style="color:#920808;">上传藏品图片</button>
					<!-- <button  type="button" onclick="location.href='/goodsPictures'" class="btn btn-default" style="color:#920808;">产品库选择图片</button> -->
		</div>
		<div id="upload_outer_div" style="min-height:220px;"><!-- 上传文件进度展示 --></div>
	  </div>
	 <div class="save bottomFix" >
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="addGoods();return false;">发布</a></li>
		</ul>
	  </div>
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/addGoodsForPc.js" title="v"></script>
	
</body>
</html>
