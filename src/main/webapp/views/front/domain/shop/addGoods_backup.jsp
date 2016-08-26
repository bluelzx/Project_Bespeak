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
<input type="hidden" id="shopId" value="${shopId}">
	<input type="hidden" id="auctionInst" value="${auctionInst}">
	 <div class="weui_cells weui_cells_form" style="margin-top:50px;">
	 	<ul class="information">
	 			<li class="realName" style="background:#DDDAD6">
	 				<button class="btn weui_btn_primary fl pointer" id="draft" style="color: #FFFFFF;" onclick="location.href='/goodsManageType'">产品管理</button>
	 				<button class="btn weui_btn_primary fr pointer" id="nextBtn" style="color: #FFFFFF;display:none" onclick="next()">下一步</button>
	 				<button class="btn weui_btn_primary fr pointer" id="add" style="color: #FFFFFF;" onclick="addGoods();return false;">发布</button>
	 				<button class="btn weui_btn_primary fl pointer" id="goBack" style="color: #FFFFFF;display:none" onclick="back()">上一步</button>
	 			</li>
	 	</ul>
	 	<div id="first">
		 	<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="myLabel">产品名称:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="goodsName" placeholder="请填写产品名称">
	            </div>
	        </div>
		 	<div class="weui_cell">
	             <textarea class="weui_textarea" placeholder="请填写产品描述" rows="3" id="goodsDescription"></textarea>
	             <div class="weui_textarea_counter"><span>0</span>/200</div>
	        </div>
	        <div class="weui_cell">
	            <div class="weui_cell_hd"><label class="myLabel">产品参考价:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="shopPrice" placeholder="请填写产品参考价">
	            </div>
	        </div>
	        <div class="weui_cell">
        		<div class="weui_cell_hd"><label class="myLabel">分类:</label></div>
	         	<div class="weui_cell_bd weui_cell_primary">
	    		 	<a href="javascript:;" onclick="catValue('${goodsDidtList[0].id}','${goodsDidtList[0].dictName}','first')" id="TypeName" class="weui_btn weui_btn_primary" style="width:100%;float:left">${goodsDidtList[0].dictName}</a>
	    		</div>
	         	<!-- <div class="weui_cell_bd weui_cell_primary" style="width:48%">
	    		 	<a href="javascript:;" class="weui_btn weui_btn_primary" id="auctionName" onclick="sequenceValue(1,'微拍藏品','first')" style="width:100%;float:left">微拍藏品</a>
	    		</div> -->
	    	</div>
	        <div class="weui_cell weui_cell_switch" onclick="isSevenReturnCheck()">
                <div class="weui_cell_hd weui_cell_primary">是否7天包退</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="isSevenReturn">
                </div>
            </div>
            <div class="weui_cell weui_cell_switch">
                <div class="weui_cell_hd weui_cell_primary">是否包邮</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="postageFee">
                </div>
            </div>
	        <div class="weui_uploader_bd">
	            <ul class="weui_uploader_files" id="fileUpload">
	                <!-- <li class="weui_uploader_file" id="weui_uploader_file_1"style="background-image:url(http://shp.qpic.cn/weixinsrc_pic/pScBR7sbqjOBJomcuvVJ6iacVrbMJaoJZkFUIq4nzQZUIqzTKziam7ibg/)">
	                <i class="weui_icon_cancel fr" onclick="removeSelf(1)" style="position: relative;bottom: 5px;background-color: black;"></i>
	                </li> -->
	            </ul>
	            <div class="weui_uploader_input_wrp">
	                <input class="weui_uploader_input" type="text" onclick="choosePic(1)" accept="image/jpg,image/jpeg,image/png,image/gif" >
	            </div>
	        </div>
	         <ul class="information">
	 			<li class="realName" style="background:#DDDAD6">
	 				<h5 style="text-align: center;padding: 15px;">电脑端代传商品地址：http://weipaike.net/pc</h5>
	 			</li>
	 		</ul>
	        <!-- <ul class="information">
	 			<li class="realName" style="background:#DDDAD6">
	 				<h5 style="text-align: center;padding-top: 15px;">下一步即同意《微拍客竞拍协议》</h5>
	 			</li>
	 		</ul> -->
        </div>
        <%-- <div id="next" style="display:none;">
        	<div class="weui_cell">
        		<div class="weui_cell_hd"><label class="myLabel">分类:</label></div>
	         	<div class="weui_cell_bd weui_cell_primary">
	    		 	<a href="javascript:;" onclick="catValue('${goodsDidtList[0].id}','${goodsDidtList[0].dictName}','first')" id="TypeName" class="weui_btn weui_btn_primary" style="width:100%;float:left">${goodsDidtList[0].dictName}</a>
	    		</div>
	         	<!-- <div class="weui_cell_bd weui_cell_primary" style="width:48%">
	    		 	<a href="javascript:;" class="weui_btn weui_btn_primary" id="auctionName" onclick="sequenceValue(1,'微拍藏品','first')" style="width:100%;float:left">微拍藏品</a>
	    		</div> -->
	    	</div>
	    	<div class="weui_cell">
	            <div class="weui_cell_hd"><label class="myLabel">售价:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="shopPrice"  placeholder="请填写售价">
	            </div>
	        </div>
	        <div class="weui_cell">
	         	<div class="weui_cell_hd"><label class="myLabel">起拍价:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="auctionPrice"  placeholder="请填写市场价">
	            </div>
	        </div>
	        <div class="weui_cell" style="background:#DDDAD6">
	        	 <div class="weui_cell_hd"><label class="myLabel">可选设置</label></div>
	        </div>
	        <div class="weui_cell weui_cell_switch" onclick="isSevenReturnCheck()">
                <div class="weui_cell_hd weui_cell_primary">7天包退</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="isSevenReturn">
                </div>
            </div>
            <div class="weui_cell weui_cell_switch" onclick="postageFeeCheck()">
                <div class="weui_cell_hd weui_cell_primary">不包邮</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="postageFee">
                </div>
            </div>
            <div id="postageFeeShow" style="display:none;">
	            <div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">邮费:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="postageFeeValue"  placeholder="请填写邮费">
		            </div>
		        </div>
		    </div>
	        <div class="weui_cell weui_cell_switch" onclick="bounsPromotionCheck()">
                <div class="weui_cell_hd weui_cell_primary">红包推广</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="bounsPromotion">
                </div>
            </div>
            <div id="bounsPromotionType" style="display:none;">
	            <div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">红包数:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="bounsNum"  placeholder="请填写红包数">
		            </div>
		        </div>
		        <div class="weui_cell">
		        	<div class="weui_cell_hd"><label class="myLabel">红包单价:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="bounsSinglePrice"  placeholder="请填写红包单价">
		            </div>
		        </div>
            </div>
	        <div class="weui_cell weui_cell_switch" onclick="commissionPromotionCheck()">
                <div class="weui_cell_hd weui_cell_primary">佣金推广</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="commissionPromotion">
                </div>
            </div>
            <div id="bonusType" style="display:none">
	            <div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">佣金推广类型:</label></div>
		            <div class="weui_cell_bd weui_cell_primary" style="width:50%">
		                <select id="bonusTypeId" style="width:50%">
		                	<option value="">请选择</option>
							<option value="1">固定金额</option>
							<option value="2">百分比</option>
						</select>
		            </div>
		        </div>
		        <div class="weui_cell">
		        	 <div class="weui_cell_hd"><label class="myLabel">佣金推广金额:</label></div>
		            <div class="weui_cell_bd weui_cell_primary" style="width:50%">
		                <input class="weui_input" type="text" id="bonus"  placeholder="请填写佣金推广金额">
		            </div>
		        </div>
	        </div>
	        <div class="weui_cell" style="background:#DDDAD6">
	        	 <div class="weui_cell_hd"><label class="myLabel">同步发布到如下频道</label></div>
	        </div>
	        <div class="weui_cell weui_cell_switch" onclick="auctionMicroCheck()">
                <div class="weui_cell_hd weui_cell_primary">微拍</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="auctionMicro">
                </div>
            </div>
            <div class="weui_cell weui_cell_switch" onclick="auctionQuickCheck()">
                <div class="weui_cell_hd weui_cell_primary">即时拍</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="auctionQuick">
                </div>
            </div>
            <div class="weui_cell weui_cell_switch" onclick="auctionProfessionCheck()">
                <div class="weui_cell_hd weui_cell_primary">专场</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="auctionProfession">
                </div>
            </div>
            <c:if test="${!empty wholesaleId}">
            <div class="weui_cell weui_cell_switch" onclick="wholesaleCheck()">
                <div class="weui_cell_hd weui_cell_primary">批发城</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="wholesaleCheckBox">
                </div>
            </div>
             <div id="agentType" style="display:none;">
	             <div class="weui_cell weui_cell_switch" onclick="isPublicCheck()">
	                <div class="weui_cell_hd weui_cell_primary">是否公开</div>
	                <div class="weui_cell_ft">
	                    <input class="weui_switch" type="checkbox" id="isPublic">
	                </div>
	            </div>
	            <div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">代理价:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="agentPrice"  placeholder="请填写普通代理价格">
		            </div>
		        </div>
		        <!-- <div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">银牌代理:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="bounsNum"  placeholder="请填写银牌代理价格">
		            </div>
		        </div>
		        <div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">金牌代理:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="bounsNum"  placeholder="请填写金牌代理价格">
		            </div>
		        </div>
		        <div class="weui_cell">
		            <div class="weui_cell_hd"><label class="myLabel">高级代理:</label></div>
		            <div class="weui_cell_bd weui_cell_primary">
		                <input class="weui_input" type="text" id="bounsNum"  placeholder="请填写高级代理价格">
		            </div>
		        </div> -->
		     </div>
		    </c:if>
	        <!-- <ul class="information">
	 			<li class="realName" style="background:#DDDAD6">
	 				<h5 style="text-align: center;padding-top: 15px;">由免费技术支持</h5>
	 			</li>
	 		</ul> -->
        </div> --%>
      </div>
	  <div class="pz_down">
		<div class="c_0100_9"></div>
	 </div>
	 <!-- <div class="save bottomFix" style="z-index: 1;">
		<ul>
			<li><a href="javascript:;" class="a_say" id="" onclick="addGoods();return false;">发布</a>
	  			<a href="javascript:;" class="weui_btn weui_btn_primary" onclick="addGoods();return false;">发布</a>
			</li>
		</ul>
	  </div> -->
	  <div id="actionSheet_wrap">
        <div class="weui_mask_transition" id="myMask" style="display: none;"></div>
        <div class="weui_actionsheet" id="weui_actionsheet">
            <div class="weui_actionsheet_menu">
            	<c:forEach var="goodsDidt" items="${goodsDidtList}">
                	<div class="weui_actionsheet_cell pointer"  onclick="catValue('${goodsDidt.id}','${goodsDidt.dictName}')">${goodsDidt.dictName}</div>
               </c:forEach>
            </div>
            <div class="weui_actionsheet_action">
                <div class="weui_actionsheet_cell pointer" id="actionsheet_cancel">关闭菜单</div>
            </div>
        </div>
    </div>	
	  <div id="actionSheet_wrap">
        <div class="weui_mask_transition" id="myMask1" style="display: none;"></div>
        <div class="weui_actionsheet" id="weui_actionsheet1">
            <div class="weui_actionsheet_menu">
                <div class="weui_actionsheet_cell pointer"  onclick="sequenceValue(1,'微拍藏品')">微拍藏品</div>
                <div class="weui_actionsheet_cell pointer"  onclick="sequenceValue(2,'即时拍藏品')">即时拍藏品</div>
                <div class="weui_actionsheet_cell pointer"  onclick="sequenceValue(3,'专场藏品')">专场藏品</div>
                <div class="weui_actionsheet_cell pointer"  onclick="sequenceValue(4,'店铺藏品')">店铺藏品</div>
                <div class="weui_actionsheet_cell pointer"  onclick="sequenceValue(5,'批发城藏品')">批发城藏品</div>
            </div>
            <div class="weui_actionsheet_action">
                <div class="weui_actionsheet_cell pointer" id="actionsheet_cancel1">关闭菜单</div>
            </div>
        </div>
    </div>	
    
    <div class="weui_dialog_alert" id="dialog" style="display:none">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">输入支付密码</strong></div>
            <div class="weui_dialog_bd">
            	<div class="weui_cell">
	                <div class="weui_cell_hd"><label>输入支付密码</label></div>
	                <div class="weui_cell_bd weui_cell_primary">
	                    <input type="password" id="payPassword" placeholder="请输入支付密码">
	                </div>
	            </div>
            </div>
            <div class="weui_dialog_ft">
                <a href="javascript:;" onclick="addauctionMicroGoods()" class="weui_btn_dialog primary">确定</a>
            </div>
        </div>
    </div>
    
	  <input type="hidden" value="${r}" id="r"/> 	
	  <input type="hidden" value="${loginStatus}" id="loginStatus"/>
	  
	<!-- <button style="margin-bottom:80px;" onclick="scanPic();">浏览图片</button>
	<button style="margin-bottom:100px;" onclick="choosePic();">选择图片</button>
	<button style="margin-bottom:100px;" onclick="testUpload();">上传图片</button>
	<button style="margin-bottom:100px;" onclick="location.href='/wxMsgCount'">群发信息</button> -->
	  
	<input type="hidden" id="openId" value="${openId}">
	<input id="appId" type="hidden" value="${appId}" />
	<input id="timeStamp2" type="hidden" value="${timeStamp}" />
    <input id="nonceStr2" type="hidden" value="${nonceStr}" />
    <input id="signature" type="hidden" value="${signature}" />
    
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/plupload/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="/third-party/select2/js/select2.min.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/addGoods.js" title="v"></script>
	
</body>
</html>
