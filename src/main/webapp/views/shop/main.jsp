<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_back_css.htm"%>
<link rel="STYLESHEET" type="text/css" href="/css/back/back.css" title="v"/>
</head>

<body class="easyui-layout">
    <div data-options="region:'north'" style="height:60px;">
    	<div class="header">
    		<div class="head_icons">
	    		<div id="head_logout" onclick="logout()" class="head_icon"><img src="/images/back/bg14.png" title="安全退出"/></div>
	    		<div id="head_refresh" onclick="refresh()" class="head_icon"><img src="/images/back/bg13.png" title="更新缓存"/></div>
	    		<div id="head_password" onclick="openUpdatePassword()" class="head_icon"><img src="/images/back/bg12.png" title="修改密码"/></div>
	    		<div id="head_message" onclick="showMain('消息通知管理','/back/page/notice')" class="head_icon"><img src="/images/back/bg09.png" title="站内短信"/></div>
    			<div id="head_index" onclick="login()"  class="head_icon"><img src="/images/back/bg08.png" title="网站首页"/></div>
    		</div>
    	</div>
    </div>
    <div data-options="region:'west',split:true,collapsible:true,title:'菜单'" style="width:200px;">
    	<div id="menuPanel" class="menu_list">
    		<h3 class="menu_head current">个人中心</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('个人中心','/shop/shoperUpdate')">个人中心</a>
				<a onclick="showMain('地图标注','/shop/shopCenters/${shop.id}')">地图标注</a>
				<a onclick="showMain('修改密码','/shop/shopCenters/${shop.id}')">修改密码</a>
			</div>
    	
			<h3 class="menu_head current">服务管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('服务申请','/shop/goodsApply')">服务申请</a>
				<a onclick="showMain('服务设置','/shop/goods')">服务设置</a>
				<a onclick="showMain('疗程设置','/shop/treatment')">疗程设置</a>
			</div>
			<h3 class="menu_head current">技师管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('技师审核','/shop/providerCheck ')">技师审核</a>
				<a onclick="showMain('技师设置','/shop/providerSet')">技师设置</a>
			</div>
			<h3 class="menu_head current">月嫂管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('技师管理','/shop/people')">月嫂管理</a>
			</div>
			<h3 class="menu_head">订单管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('订单信息管理','/back/page/orderinfo')">订单信息管理</a>
				<a onclick="showMain('审核退款申请','/back/page/orderinfo?ordertype=order')">审核退款申请</a>
			</div>
		</div>
    </div>
    <div data-options="region:'center'" id="main" class="easyui-tabs" style="padding:0px;background:#eee;">
    	<!--<div id="main" class="easyui-tabs" style="width:500px;height:250px;"></div>-->
    </div>

	 <div style="display:none;" id="updatePasswordWin" title="修改密码">
        <div style="margin:8px 8px 8px 35px;">
       		<div id="updatePsdTip" style="color:red;"></div>
            	旧密码：<input type="text" id="oldPsd"></input><br/><br/>
            	新密码：<input type="text" id="newPsd1"></input><br/><br/>
            	新密码：<input type="text" id="newPsd2"></input><br/><br/>
           		<a id="submitUptPsd" onclick="updatePassword()" class="easyui-linkbutton">确认</a>&nbsp;
           		<a id="returnUptPsd" onclick="closeUpdatePwdWin()" class="easyui-linkbutton">返回</a>
       	</div>
	</div>
	
	<input type="hidden" value="${user.id}" id="userId">
	
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_back_js.htm"%>
	<script type="text/javascript" src="/js/back/main.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<h3 class="menu_head">{{text}}</h3>
			<div style="display:none" class="menu_body">
				{{#children}}
				<a onclick="showMain('{{text}}','{{attr.url}}')">{{text}}</a><!--包括通知消息类型-->
				{{/children}}
			</div>
		{{/rows}}
	</script>

</body>
</html>