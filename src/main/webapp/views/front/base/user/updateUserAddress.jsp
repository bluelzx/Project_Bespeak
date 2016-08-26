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
        <!--主体内容开始-->
        <a onclick="lh.back()" class="back">
            <img src="/images/front/back.png" alt="">
        </a>
         <input type="hidden" value="${user.id}" id="userId" />
         <input type="hidden" value="${userAddress.id}" id="userAddressId" />
        <div class="addTopBox">
            <div class="yNameBox">
                <label for="yname">联系人</label>
                <input type="text" placeholder="您的姓名" value="${userAddress.receiverName}" id="yname">
            </div>
            <div class="yAddBox">
                <label for="yphone">手机号</label>
                <input type="text" placeholder="联系您的电话" value="${userAddress.phone}" id="yphone">
            </div>
            <div class="yProvince">
                <span>所在省</span>
                <input type="hidden" value="${userAddress.province}" id="province1" />
                <select name="" id="province">
                </select>
            </div>
            <div class="yCity">
                <span>所在地区</span>
                <input type="hidden" value="${userAddress.city}" id="city1" />
                <select name="" id="city">
                </select>
            </div>
            <div class="xxAdd">
                <p>详细地址</p>
                <textarea name="" id="addressDetail" cols="38" rows="10" class="drawAdd">${userAddress.addressDetail}</textarea>
            </div>
        </div>
        <input type="button" value="修改地址" class="xq-btn" onclick="addUserAddress()">
    </div>
  <%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%> 
	<script type="text/javascript" src="/js/front/user/addAddress.js" title="v"></script>
</body>
</html>