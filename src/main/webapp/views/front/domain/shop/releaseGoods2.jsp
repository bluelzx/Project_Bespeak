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
<link rel="stylesheet" type="text/css" href="/third-party/mobiscroll/css/mobiscroll.custom-2.6.2.min.css"/> 
<link rel="stylesheet" type="text/css" href="/third-party/bootstrap-select-1.10.0/css/bootstrap-select.min.css"/>

</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	<input type="hidden" id="shopId" value="${shopId}">
	<input type="hidden" id="auctionInst" value="${auctionInst}">
	<input type="hidden" id="goodsId" value="${goodsId}">
	 <div class="weui_cells weui_cells_form" style="margin-top:50px;min-height:500px;">
	 	<ul class="information">
 			<li class="realName" style="background:#DDDAD6">
 				<button class="btn weui_btn_primary fl pointer" id="draft" style="color: #FFFFFF;" onclick="location.href='/goodsManageType'">产品管理</button>
 				<button class="btn weui_btn_primary fr pointer" id="add" style="color: #FFFFFF;" onclick="addAuction();return false;">选择商品</button>
 			</li>
	 	</ul>
         <div >
         	<div class="weui_cell" id="auctionNames" style="display:none;">
	            <div class="weui_cell_hd"><label>拍卖场次:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="auctionName" placeholder="请填写拍卖场次">
	            </div>
	        </div>
         	<div class="weui_cell" id="start">
	            <div class="weui_cell_hd"><label>开拍时间:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="startTime" placeholder="请选择拍卖时间">
	            </div>
	        </div>
         	<div class="weui_cell" id="end" style="display:none">
	            <div class="weui_cell_hd"><label>结束时间:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
					<select id="day" class="selectpicker">
							<option value="1" >1天之后</option>
							<option value="2" >2天之后</option>
							<option value="3" >3天之后</option>
							<option value="4" >4天之后</option>
							<option value="5" >5天之后</option>
							<option value="6" >6天之后</option>
							<option value="7" >7天之后</option>
							<option value="8" >8天之后</option>
							<option value="9" >9天之后</option>
							<option value="10" >10天之后</option>
							<option value="11" >11天之后</option>
							<option value="12" >12天之后</option>
							<option value="13" >13天之后</option>
							<option value="14" >14天之后</option>
							<option value="15" selected="selected">15天之后</option>
					</select>
	            </div>
	        </div>
         	<div class="weui_cell" id="auctionQuickType">
	            <div class="weui_cell_hd"><label>即时拍拍卖类型:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	            	<select id="typeId" class="selectpicker">
	            		<option value="">请选择类型</option>
		                <c:forEach var="dict" items="${dictList}">
		                	<option value="${dict.id}">${dict.dictName}</option>
		                </c:forEach>
	                </select>
	            </div>
	        </div>
         	<div class="weui_cell" id="bailValue">
	            <div class="weui_cell_hd"><label>保证金:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="bail" placeholder="请填写保证金">
	            </div>
	        </div>
	     </div>
	     <div id="nonAuctionProfession" style="display:none;border-top: 1px solid #d9d9d9;">
	        <!-- <div class="weui_cell" style="background:#DDDAD6">
	        	 <div class="weui_cell_hd"><label class="myLabel">可选设置</label></div>
	        </div> -->
	        <!-- TODO 推荐开发推广红包  -->
            <!--
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
            </div> -->
            <!-- TODO 佣金推广 -->
            <!--  
	        <div class="weui_cell weui_cell_switch" onclick="commissionPromotionCheck()">
                <div class="weui_cell_hd weui_cell_primary">佣金推广</div>
                <div class="weui_cell_ft">
                    <input class="weui_switch" type="checkbox" id="commissionPromotion">
                </div>
            </div>
           <div id="commissionPromotionType" style="display:none">
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
	        </div> -->
        </div>
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
	  
 	<button class="btn weui_btn_primary pointer"  style="color: #FFFFFF;width:100%;position: absolute;bottom: 2px;left: 0px;" onclick="auction('','first');return false;">选择类型</button>
	  
	  <div id="actionSheet_wrap">
        <div class="weui_mask_transition" id="myMask1" style="display: none;"></div>
        <div class="weui_actionsheet" id="weui_actionsheet1">
            <div class="weui_actionsheet_menu">
            	<c:if test="${!empty auctionInst.id}">
                	<div class="weui_actionsheet_cell pointer" onclick="auction(1,'')">专场</div>
                </c:if>
                <c:if test="${empty auctionInst.id}">
                	<div class="weui_actionsheet_cell pointer" onclick="javascript:location.href='/instApply'">申请开通专场</div>
                </c:if>
              <%-- //DOTO tempHide  <c:if test="${!empty auctionQuickInst.id}">
                	<div class="weui_actionsheet_cell pointer" onclick="auction(2,'')">即时拍</div>
                </c:if>
                <c:if test="${empty auctionQuickInst.id}">
                	<div class="weui_actionsheet_cell pointer" onclick="javascript:location.href='/auctionQuickInstApply'">申请开通即时拍</div>
                </c:if>
                 --%>
                <div class="weui_actionsheet_cell pointer" onclick="auction(3,'')">微拍</div>
               <%-- //DOTO tempHide <c:if test="${!empty wholesaleId}">
                	<div class="weui_actionsheet_cell pointer" onclick="jumpToAddWholesale()">批发城</div>
                </c:if>
                <c:if test="${empty wholesaleId}">
                	<div class="weui_actionsheet_cell pointer" onclick="javascript:location.href='/applyWholesale'">申请开通批发城</div>
                </c:if>
                 --%>
            </div>
            
           <!--  <div class="weui_actionsheet_action">
                <div class="weui_actionsheet_cell pointer" id="actionsheet_cancel1">关闭菜单</div>
            </div> -->
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
	<script type="text/javascript" src="/third-party/mobiscroll/js/mobiscroll.custom-2.6.2.min.js"></script>
	<script type="text/javascript" src="/third-party/mobiscroll/dev/js/mobiscroll.core-2.6.2-zh.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="/js/front/shop/releaseGoods.js" title="v"></script>
	
</body>
</html>
