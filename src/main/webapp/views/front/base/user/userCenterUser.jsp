<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html id="htmlSize">
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
</head>
<body >
 <div id="wrapper">
        <div class="mid-header">
            <a href="signIn.html">
                <img src="${user.avatar}" alt="" class="user-img">
            </a>
            <input type="hidden" value="${user.id}" id="userId" />
            <span class="user-name">${user.username}</span>
            <img src="/images/front/ewm.png" alt="" class="ewm">
        </div>
        <div id="main">
            <ul class="gr-ul">
                <li class="gr-li" onclick="lh.jumpR('/myWallet')">
                    <img src="/images/front/grQb.png" alt="" class="grqb gr-img">
                    <b>我的钱包</b>
                    <div class="rt gr-div">
                        <span>余额:</span>
                         <input type="hidden" value="${user.avaliableMoney}" id="avaliableMoney" />
                        <span id="money" >￥${user.avaliableMoney}</span>
                        <i class="qb-i"></i>
                    </div>
                </li>
                <li class="gr-li" id="grAdd" onclick="lh.jumpR('/address')">
                    <img src="/images/front/grAdd.png" alt="" class="grAdd gr-img">
                    <b>地址管理</b>
                    <div class="rt gr-div">
                        <i class="qb-i"></i>
                    </div>
                </li>
                <li class="gr-li" id="grQ" onclick="lh.jump('/coupon')">
                    <img src="/images/front/grQ.png" alt="" class="grQ gr-img">
                    <b>我的优惠券</b>
                    <div class="rt gr-div">
                        <span id="coupon"></span>
                        <i class="qb-i"></i>
                    </div>
                </li>
                <li class="gr-li" id="grJf">
                    <img src="/images/front/grJf.png" alt="" class="grJf gr-img">
                    <b>积分</b>
                    <div class="rt gr-div">
                        <span id="fund">${user.integralFund}</span>
                        <i class="qb-i"></i>
                    </div>
                </li>
                <li class="gr-li" id="grDj">
                    <img src="/images/front/grDj.png" alt="" class="grDj gr-img">
                    <b>我是店家</b>
                    <div class="rt gr-div">
                        <i class="qb-i"></i>
                    </div>
                </li>
                <li class="gr-li" id="provider" onclick="lh.jump('/providerLogin')">
                    <img src="/images/front/grJs.png" alt="" class="grJs gr-img">
                    <b>我是技师/月嫂</b>
                    <div class="rt gr-div">
                        <i class="qb-i"></i>
                    </div>
                </li>
                 <li class="gr-li" id="addProvider" onclick="lh.jump('/providerAdd')">
                    <img src="/images/front/grJs.png" alt="" class="grJs gr-img">
                    <b>技师申请</b>
                    <div class="rt gr-div">
                        <i class="qb-i"></i>
                    </div>
                </li>
                
                <li class="gr-li bt"  onclick="lh.jumpR('/changPhone')">
                    <img src="/images/front/grPh.png" alt="" class="grPh gr-img">
                    <b>我的手机</b>
                    <div class="rt gr-div">
                        <span id="phone">${user.phone}</span>
                        <i class="qb-i"></i>
                    </div>
                </li>
            </ul>
            <ul class="gr-ul-t">
                <li class="gr-li">
                    <img src="/images/front/grFu.png" alt="" class="grFu gr-img">
                    <b>服务与反馈</b>
                    <div class="rt gr-div">
                        <i class="qb-i"></i>
                    </div>
                </li>
                <li class="gr-li bt" onclick="lh.jumpR('/aboutUs')">
                    <img src="/images/front/grGy.png" alt="" class="grGy gr-img">
                    <b>关于我们</b>
                    <div class="rt gr-div">
                        <i class="qb-i"></i>
                    </div>
                </li>
            </ul>
            <input type="button" onclick="lh.jump('/logout')" value="退出登录" class="outSign">
        </div>

        <!--主体内容结束-->
        <!--底部固定菜单栏开始-->
        <footer>
            <a href="/index" class="ft-a">
                <img src="/images/front/index.png" alt="">
                <span>首页</span>
            </a>
            <a href="/providerIndex" class="ft-a">
                <img src="/images/front/technician.png" alt="">
                <span>技师</span>
            </a>
            <a href="/orderIndex" class="ft-a">
                <img src="/images/front/order.png" alt="">
                <span>订单</span>
            </a>
            <a href="javascript:" class="ft-a">
                <img src="/images/front/user-on.png" alt="">
                <span class="page-on">个人中心</span>
            </a>
        </footer>
        <!--底部固定菜单栏结束-->
    </div>
	
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript">lh.param = ${paramJson}</script>
	<script type="text/javascript" src="/js/front/user/userCenterShop.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}	 	
			
		{{/rows}}
	</script>
</body>
</html>