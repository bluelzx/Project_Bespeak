<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%-- <%@ include file="/views/common/common_css.htm"%> --%>
<%@ include file="/views/common/common_front_wpk_css.htm"%>
<%-- 产品-添加产品- GoodsAction:/goods/page/add --%>
<style type="text/css">
h1{margin:auto;}
.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control{background-color: #fff;opacity: 1;}
.weui-picker-modal {min-height:220px;}
.weui-picker-modal .picker-modal-inner {min-height:220px;}
.weui-picker-modal .picker-item {font-size:20px;}
.weui-picker-modal .toolbar-inner {height:40px;}
.weui-picker-modal .title {font-size:20px;line-height: 40px;}
.weui-picker-modal .picker-button {font-size:18px;line-height: 40px;}
.weui-picker-container p span{padding-left:10px;color:gray;font-size:smaller;}
/**weui,bootstrap冲突*/
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row auction_title">
			<div onclick="lh.back();" class="col-xs-1">
				<i class="icon-angle-left icon-3x"></i>
			</div>
			<div class="col-xs-10 pt10 text-center">
				<span class="fs16">添加拍品</span>
			</div>
		</div>
		<div class="row ptb10">
			<div id="fileUpload" class="col-xs-12 pr7">
				<!-- <div class="col-xs-3 pl0 pr7">
					<img src="/images/front/pic_01.png" class="img-responsive">
				</div>
				<div class="col-xs-3 pl0 pr7">
					<img src="/images/front/pic_01.png" class="img-responsive">
				</div>
				<div class="col-xs-3 pl0 pr7">
					<img src="/images/front/pic_01.png" class="img-responsive">
				</div>
				<div class="col-xs-3 pl0 pr7">
					<i class="weui_icon_cancel fr" onclick="removeSelf()"  style="position: relative;top: 22px;background-color: black;"></i>
					<img src="/images/front/pic_01.png" class="img-responsive">
				</div>
				 -->
			</div>
			<!-- 20160510修改 -->
			
			<div class="col-xs-12 pr7 pt10">
				<div class="col-xs-3 pl0 pr7">
					<div class="col-xs-12 plr0 pos-r">
						<input type="file" class="form-control fileUp" id="backdrop" />
						<div class="col-xs-12 plr0">
							<img src="/images/front/addGoods.png" class="img-responsive">
							<!-- <input class="weui_uploader_input" type="text" onclick="choosePic('showPic')" accept="image/jpg,image/jpeg,image/png,image/gif" > -->
							<span class="weui_uploader_input" onclick="choosePic('showPic')" ></span>
						</div>
					</div>
				</div>
			</div>
			<!-- 20160510修改 -->
		</div>
		<div class="col-xs-12 h10" style="height:2px;"></div><!--  bg_gray -->
		<div class="row">
		<!-- 20160510下午 -->
			<div class="col-xs-12 bdb ptb5">
				<div class="col-xs-12 plr0">
					<input name="title" id="goodsName" class="form-control" type="text" placeholder="请输入拍品名称" maxlength="30" 
						style="background-color: white;border: none; text-align: center;" onkeyup="javascript:setShowLength(this, 30, 'name_word');">
				</div>
				<div class="col-xs-12 plr0 fs10 gray text-right">
					<span class="red" id="name_word">还可以输入30字拍品名称</span>
				</div>
			</div>
		<!-- 20160510下午 -->
			<div class="col-xs-12 h10" style="height:2px;"></div><!--  bg_gray -->
		<!-- 20160510下午 -->
			<div class="col-xs-12 bdb ptb5">
				<div class="col-xs-12 plr0">
					<textarea name="goodsBrief" id="goodsBrief" maxlength="500" onkeyup="javascript:setShowLength(this, 500, 'goodsIntroduce_word');" rows="3" class="form-control" 
						placeholder="请输入拍品描述" style="border: none; text-align: center;"></textarea>
				</div>
				<div class="col-xs-12 plr0 fs10 gray text-right">
					<span class="red" id="goodsIntroduce_word">还可以输入500字拍品描述</span>
				</div>
			</div>
		<!-- 20160510下午 -->
			<div class="col-xs-12 h10" style="height:2px;"></div><!--  bg_gray -->
			<div class="col-xs-12 bdb ptb5">
				<!-- <div class="col-xs-3 plr0 pt5">拍品类型：</div> -->
				<div class="col-xs-12 plr0">
					<input id="typeCode" type="text" class="form-control" placeholder="请选择拍品类型" style="background-color: white;border: none; text-align: center;"/>
				</div>
			</div>
			<div class="col-xs-12 h10 frontHide" id="shopPriceGap" style="height:2px;"></div><!--  bg_gray -->
			<div class="col-xs-12 bdb ptb5 frontHide" id="shopPriceDiv">
				<div class="col-xs-3 plr0 pt5">售价：</div>
				<div class="col-xs-9 plr0">
					<input id="shopPrice" type="number" min="0" class="form-control" placeholder="0元" style="background-color: white;border: none; text-align: center;"/>
				</div>
			</div>
			<!-- <div class="col-xs-12 h10 bg_gray" id="priceBeginGap" style="height:2px;"></div>
			<div class="col-xs-12 bdb ptb5" id="priceBeginDiv">
				<div class="col-xs-3 plr0 pt5">起拍价：</div>
				<div class="col-xs-9 plr0">
					<input id="priceBegin" type="number" min="0" class="form-control" placeholder="0元" style="background-color: white;border: none; text-align: center;"/>
				</div>
			</div> -->
			<div class="col-xs-12 h10 frontHide" style="height:2px;" id="postageFeeGap"></div><!--  bg_gray -->
			<div class="col-xs-12 bdb ptb5 frontHide" id="postageFeeDiv">
				<div class="col-xs-3 plr0 pt5">运费：</div>
				<div class="col-xs-9 plr0">
					<input id="postageFee" type="number" min="0" class="form-control" placeholder="不填则为免运费" style="background-color: white;border: none; text-align: center;"/>
				</div>
			</div>
			<!-- <div class="col-xs-12 h10" style="height:2px;"></div>
			<div class="col-xs-12 bdb ptb5">
				<div class="col-xs-3 plr0 pt5">库存数量：</div>
				<div class="col-xs-9 plr0">
					<input id="remainNumber" type="number" min="1" class="form-control" placeholder="" style="background-color: white;border: none; text-align: center;"/>
				</div>
			</div> -->
		</div>
		<div class="row pt20">
			<div onclick="addGoods();" class="col-xs-12" style="margin-bottom: 3px;">
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

	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script> lh.param = ${paramJson}; </script>
	<script src="/js/front/goods/goodsAdd.js" title="v"></script>
	
	<script id="template" type="x-tmpl-mustache">
	{{#rows}}
	{{/rows}}	 		 
	</script>
</body>
</html>
