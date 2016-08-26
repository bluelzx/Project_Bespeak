<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<%@ include file="/views/common/common_front_wpk_css.htm"%>
<%-- 单人聊天页面- ChatAction:/chat --%>
</head>
<body style="background-color: #f3f3f3;">
    <div class="container-fluid">
        <div class="row">
        	<div onclick="lh.back();" style="position:absolute;left:10px;padding:5px 15px;z-index:2">
				<i class="fa fa-angle-left fa-topsize" aria-hidden="true" style="padding-top:2px;"></i>
			</div>
            <div class="col-xs-12 text-center pt20">${receiverName} </div>
        </div>
        <div class="row">
            <div class="col-xs-12 product">
            	<c:if test="${!empty goods.id}">
	            	<div class="col-xs-12 productMs">
	                    <div class="col-xs-4 plr0">
	                        <img class="img-responsive" src="/images/front/pic_01.png" />
	                    </div>
	                    <div class="col-xs-8 pl7">
	                        <div class="blue_tint fs13">${goods.goodsName}</div>
	                        <div class="col-xs-12 plr0 mt5">
	                            <span class="orange pull-left fs20">￥<span>1201.00</span></span>
	                            <!-- <span class="pull-right gray pt5">已售0笔</span> -->
	                        </div>
	                        <!-- <div class="col-xs-12 plr0 mt10 text-center">
	                            <button type="button" class="btn btn-orange btn-sm">发送链接</button>
	                        </div> -->
	                    </div>
	                </div>
            	</c:if>
            </div>
            <div class="col-xs-12">
                <div class="col-xs-12">
                    <div class="col-xs-12 text-center bg_deep_gray ptb10">你们现在可以开始对话了</div>
                </div>
            </div>
        </div>
    </div>
    <div class="enter_scene_fixed bdt">
        <div class="col-xs-2 plr0 pt5" id="smile">
            <img onclick="alertauto();" class="img-responsive center-block" src="/images/front/smile.png" />
        </div>
        <div class="col-xs-7 plr0 pt5">
            <input type="text" class="form-control" id="Input" />
        </div>
        <div onclick="sendMsg();" class="col-xs-3 plr7 pt5">
            <a role="button" class="btn btn-orange col-xs-12">发送</a>
        </div>
    </div>

	<input type="hidden" id="code" value="${code}"/>
	<input type="hidden" id="sig" value="${sig}"/>
	<input type="hidden" id="timeStamp" value="${timeStamp}"/>
	<input type="hidden" id="senderId" value="${senderId}"/>
	<input type="hidden" id="userTokenId" value="${userTokenId}"/>
	<input type="hidden" id="userTokenPswd" value="${userTokenPswd}"/>
	<input type="hidden" id="senderAvatar" value="${senderAvatar}"/>
	<input type="hidden" id="senderName" value="${senderName}"/>
	<input type="hidden" id="receiverId" value="${receiverId}"/>
	<input type="hidden" id="receiverTokenId" value="${receiverTokenId}"/>
	<input type="hidden" id="receiverAvatar" value="${receiverAvatar}"/>
	<input type="hidden" id="receiverName" value="${receiverName}"/>
	<input type="hidden" id="senderSerial" value="${senderSerial}"/>
	<input type="hidden" id="receiverSerial" value="${receiverSerial}"/>
	
	<input type="hidden" value="${r}" id="r"/> 	
	<input type="hidden" value="${loginStatus}" id="loginStatus"/>

	<script type="text/javascript" src="http://app.cloopen.com/im50/ytx-web-im.min.js"></script>
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<%@ include file="/views/common/common_front_wpk_js.htm"%>
	<script type="text/javascript" src="/js/front/chat/common_base_chat.js" title="v"></script>
	<script type="text/javascript" src="/js/front/chat/common_single_chat.js" title="v"></script>
	<script type="text/javascript" src="/js/front/chat/chat.js" title="v"></script>
	<script id="template_self" type="x-tmpl-mustache">
	<div class="chathostory">
		<div class="r_75">
			<a href="javascript:lh.jumpR('/shop/{{senderId}}');"><img src="{{chat.senderAvatar}}" onerror="this.src='/images/front/default_avatar.png'" width="100%" /></a>
		</div>
		<div class="chatboxt">
			<div class="chatben">
				<div class="pf_chet">
					<img src="/images/front/chat_img3.png" width="7" height="13" />
				</div>
				<p style="word-wrap:break-word; word-break:normal;">{{chat.content}}</p>
			</div>
		</div>
	</div>
	</script>
	
	<script id="template_other" type="x-tmpl-mustache">
	<div class="chathostory">
		<div class="l_75">
			<a href="javascript:lh.jumpR('/shop/{{receiverId}}');"><img src="{{chat.receiverAvatar}}" onerror="this.src='/images/front/default_avatar.png'" width="100%" /></a>
		</div>
		<div class="chatboxt">
			<div class="chatbon">
				<div class="pf_chat">
					<img src="/images/front/chat_img2.png" width="7" height="13" />
				</div>
				<p style="word-wrap:break-word; word-break:normal;">{{chat.content}}</p>
			</div>
		</div>
	</div>
	</script>
	
</body>
</html>
