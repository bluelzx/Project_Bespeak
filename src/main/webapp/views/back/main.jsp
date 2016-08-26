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
    <!-- <div data-options="region:'south'" style="height:30px;">
    	<div class="footer">
    		<div id="foot_items" class="foot_items">快速通道==>：
    			<span class="foot_item" onclick="showMain('文章管理','/back/page/article')">文章管理</span>
    			<span class="foot_item" onclick="showMain('用户管理','/back/page/user')">用户管理</span>
    			<span class="foot_item" onclick="showMain('会员申请','/back/page/application')">会员申请</span>
    			<span class="foot_item" onclick="showMain('公告管理','/back/page/announcement')">公告管理</span>
    		</div>
    	</div>
    </div> -->
    <div data-options="region:'west',split:true,collapsible:true,title:'菜单'" style="width:200px;">
    	<div id="menuPanel" class="menu_list">
			<h3 class="menu_head current">申请管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('申请处理','/back/page/apply')">申请处理</a>
			</div>
			
			<h3 class="menu_head">文章管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('新闻资讯','/back/page/article')">文章新闻</a>
			</div>
			
			<h3 class="menu_head">用户管理</h3>
			<div style="display:none;" class="menu_body">
				<a onclick="showMain('用户信息','/back/page/userInfo')">用户信息</a>
				<a onclick="showMain('用户控制','/back/page/userControl')">用户控制</a>
			</div>
			<h3 class="menu_head">服务商管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('加盟商','/back/page/shop')">加盟商</a>
				<a onclick="showMain('服务','/back/page/goods')">服务</a>
				<a onclick="showMain('疗程','/back/page/treatment')">疗程</a>
				<a onclick="showMain('技师','/back/page/provider')">技师</a>
				<a onclick="showMain('技师预约','/back/page/bespeak')">技师预约</a>
				<a onclick="showMain('月嫂','/back/page/people')">月嫂</a>
				<a onclick="showMain('月嫂预约','/back/page/bespeak/?typeId=people')">月嫂预约</a>
			</div>
			<h3 class="menu_head">订单管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('订单信息管理','/back/page/orderinfo')">订单信息管理</a>
				<a onclick="showMain('审核退款申请','/back/page/orderinfo?ordertype=order')">审核退款申请</a>
			</div>
			<h3 class="menu_head">培训管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('培训课程','/back/page/course')">培训课程</a>
				<a onclick="showMain('培训报名','/back/page/investigation')">培训报名</a>
			</div>
			<h3 class="menu_head">营销管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('优惠券','/back/page/coupon')">优惠券</a>
			</div>
			<h3 class="menu_head">财务管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('充值记录','/back/page/charge')">充值记录</a>
				<a onclick="showMain('交易记录','/back/page/accountLog')">交易记录</a>
				<a onclick="showMain('提现申请','/back/page/withdraw')">提现申请</a>
				<!-- <a onclick="showMain('资金监控','/back/page/moneyWatch')">资金监控</a> -->
			</div>
			
			<h3 class="menu_head">图片管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('图片','/back/page/picture')">图片</a>
				<!-- <a onclick="showMain('相册','/back/page/album')">相册</a> -->
				
			</div>
			
			<h3 class="menu_head">论坛管理</h3>
			<div style="display:none" class="menu_body">
				<a onclick="showMain('论坛','/back/page/forum')">论坛</a>
				<a onclick="showMain('论坛帖子','/back/page/forumArticle')">帖子</a>
				<a onclick="showMain('论坛用户','/back/page/forumMember')">论坛用户</a>
				<a onclick="showMain('公告','/back/page/announcement')">公告</a>
			</div>
			
			<h3 class="menu_head">消息管理</h3>
			<div style="display:block" class="menu_body">
				<a onclick="showMain('评论','/back/page/comment')">评论</a>
			    <a onclick="showMain('广告管理','/back/page/advertisement')">广告管理</a>
				<a onclick="showMain('消息通知','/back/page/notice')">消息通知</a>
				<!-- <a onclick="showMain('单人记录','/back/page/chat')">单人记录</a>
				<a onclick="showMain('专场聊天记录','/back/page/backChatProfession')">专场聊天记录</a> -->
			</div>
			
			<h3 class="menu_head">系统管理</h3>
			<div style="display:none;" class="menu_body">
				<a onclick="showMain('数据字典','/back/page/dict')">数据字典</a><!--包括通知消息类型-->
				<a onclick="showMain('登录日志','/back/page/loginLog')">登录日志</a><!--包括登陆，交易，后台操作，用户操作等-->
				<a onclick="showMain('省市区','/back/page/provinceCityArea')">省市区</a><!--针对后台人员：管理员，财务等-->
				<!-- <a onclick="showMain('平台配置','/back/page/sysDict')">平台配置</a> --><!--包括云通讯，短信，快递，银行，支付宝，微信，QQ等账号-->
				<!-- <a onclick="showMain('用户角色','/back/page/admin')">用户角色</a> --><!--针对后台人员：管理员，财务等-->
				<!-- <a onclick="showMain('快速通道','/back/page/quickMenu')">快速通道</a> -->
				<!-- <a onclick="showMain('费用比例','/back/page/sysDict?dictCode=rateType')">费用比例</a> --><!--官方账号和费用比例，可专门用一张表保存，因为属于重要信息，不放数据字典-->
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