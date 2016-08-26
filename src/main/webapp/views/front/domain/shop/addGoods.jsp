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
<body style="background-color: #f5f5f5;">
<%@ include file="/views/front/common/z_div_top_nav.htm"%><!-- 顶部导航 -->
	 <div class="weui_cells weui_cells_form" style="margin-top:50px;">
	 	<ul class="information">
	 			<li class="realName" style="background:#DDDAD6">
	 				<button class="btn weui_btn_primary fl pointer" id="draft" style="color: #FFFFFF;margin:3px;" onclick="location.href='/goodsManageType'">产品管理</button>
	 				<button class="btn weui_btn_primary fr pointer" style="color: #FFFFFF;margin:3px;" onclick="location.href='/asgAddOrUpdate'">产品库中选择</button>
	 				<!-- <button class="btn weui_btn_primary fr pointer" id="nextBtn" style="color: #FFFFFF;display:none" onclick="next()">下一步</button> -->
	 				<%-- <a href="javascript:;" onclick="catValue('${goodsDictListList[0].id}','${goodsDictListList[0].dictName}','first')" id="TypeName" class="weui_btn weui_btn_primary" style="float: right;height: 34px;font-size: 15px;margin:3px;">${goodsDictListList[0].dictName}</a> --%>
	 				<!-- <button class="btn weui_btn_primary fl pointer" id="goBack" style="color: #FFFFFF;display:none" onclick="back()">上一步</button> -->
	 			</li>
	 	</ul>
	 	<div id="first">
		 	<div class="weui_cell">
	            <!-- <div class="weui_cell_hd"><label>藏品名称:</label></div> -->
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="goodsName" placeholder="请填写藏品名称,35字以内">
	            </div>
	        </div>
	        <!-- <div class="weui_cell"><label>藏品描述:</label></div> -->
		 	<div class="weui_cell">
	             <textarea class="weui_textarea" placeholder="请填写藏品描述,500字以内" rows="3" id="goodsDescription"></textarea>
	             <!-- <div class="weui_textarea_counter"><span>0</span>/200</div> -->
	        </div>
	        <div class="weui_uploader_bd">
	            <ul class="weui_uploader_files" id="fileUpload">
		            <!-- <li class="weui_uploader_file" id="weui_uploader_file_1" style="background-image:url('/file/default/1452827347322__20160115001640550_thumb.jpg')">
						<i class="weui_icon_cancel fr" onclick="removeSelf(1)"  style="position: relative;bottom: 5px;background-color: black;"></i>
					</li> -->
	            </ul>
	            <div class="weui_uploader_input_wrp">
	                <input class="weui_uploader_input" type="text" onclick="choosePic(1)" accept="image/jpg,image/jpeg,image/png,image/gif" >
	            </div>
	        </div>
	        <div class="weui_cell">
	            <div class="weui_cell_hd"><label>售价:</label></div>
	            <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="shopPrice" placeholder="请填写藏品售价">
	            </div>
	        </div>
	        <%-- <div class="weui_cell">
        		<div class="weui_cell_hd"><label>分类:</label></div>
	         	<div class="weui_cell_bd weui_cell_primary">
	    		 	<a href="javascript:;" onclick="catValue('${goodsDictListList[0].id}','${goodsDictListList[0].dictName}','first')" id="TypeName" class="weui_btn weui_btn_primary" style="width:100%;float:left">${goodsDictListList[0].dictName}</a>
	    		</div>
	    	</div> --%>
	       <!--  <div class="weui_cell weui_cell_switch" onclick="isSevenReturnCheck()">
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
            </div> -->

             <div class="weui_cell">
             	<div class="weui_cell_bd weui_cell_primary">
		            <a href="javascript:;" onclick="catValue(null,null,'first')" id="goodsTypeName" 
		            class="weui_btn weui_btn_primary" style="height: 36px;width:100%;font-size: 15px;">请选择藏品类型<%-- ${goodsDictListList[0].dictName} --%></a>
	    		</div>
            </div>
            
             <div class="weui_cell">
             	<div class="weui_cell_bd weui_cell_primary">
                   <button class="btn weui_btn_primary fr pointer" id="add" style="color: #FFFFFF;width: 100%;height: 50px;" onclick="addGoods();return false;">发布</button>
	    		</div>
            </div>
            
	         <ul class="information" style="position: fixed;bottom: 5px;width: 100%;">
	 			<li class="realName" style="background:#DDDAD6">
	 				<h5 style="text-align: center;padding: 15px;">电脑端代传商品地址：http://weipaike.net/pc</h5>
	 			</li>
	 		</ul>
        </div>
      </div>
	  <div class="pz_down">
		<div class="c_0100_9"></div>
	 </div>
	 <div id="actionSheet_wrap">
        <div class="weui_mask_transition" id="myMask" style="display: none;"></div>
        <div class="weui_actionsheet" id="weui_actionsheet">
            <div class="weui_actionsheet_menu">
            	<c:forEach var="goodsDictList" items="${goodsDictListList}">
                	<div class="weui_actionsheet_cell pointer"  onclick="catValue('${goodsDictList.id}','${goodsDictList.dictName}')">${goodsDictList.dictName}</div>
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
                <div class="weui_actionsheet_cell pointer" onclick="chooseToJump(1, '继续上传')">继续上传</div>
                <div class="weui_actionsheet_cell pointer" onclick="chooseToJump(2, '发布微拍')">发布微拍</div>
                <div class="weui_actionsheet_cell pointer" onclick="chooseToJump(3, '发布专场')">发布专场</div>
                <div class="weui_actionsheet_cell pointer" onclick="chooseToJump(4, '取消')">取消</div>
            </div>
            <div class="weui_actionsheet_action">
                <div class="weui_actionsheet_cell pointer" onclick="chooseToJump(4, '取消')" id="actionsheet_cancel1">关闭菜单</div>
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
	<input type="hidden" id="shopId" value="${shopId}">
	<input type="hidden" id="from" value="${from}">
	  
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
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/bootstrap-select-1.10.0/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="/js/common/common_upload.js" title="v"></script>
	<script type="text/javascript" src="/js/front/shop/addGoods.js" title="v"></script>
	
</body>
</html>
