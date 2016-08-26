<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/common/meta_info.htm"%>
<%@ include file="/views/common/common_css.htm"%>
<link rel="stylesheet" type="text/css" href="/css/front/front.css" title="v" />
</head>
<body>
	<input type="hidden" value="${currentUserId}" id="currentUserId">
	<div class="pz_main">
		<div class="c_0100_13">
			<div class="t_0100_7">
				<div class="l_580">
					<input type="text" name="textfield" id="searchKey" placeholder="输入好友关键词" class="bfom3" />
				</div>
				<div class="bfom4" onclick="searchUser();">
					<a href="javascript:void(0);"><input name="" type="image" src="/images/front/top_img6.png" width="18" height="18" /></a>
				</div>
			</div>
		</div>
		<div class="c_0100_14">
			<div class="slide_teacher" id="slide_teacher">
				<div class="hd">
					<ul>
						<li style="position: relative;" class="on pointer">消息
							<div class="pf_teacher" style="position: relative;display: inline-block;top: 3px;left: 0px;">
								<a id="notice" href="javascript:void(0);"></a>
							</div>
						</li>
						<li class="pointer">师友</li>
					</ul>
				</div>
				<div class="bd">
					<!-- <div class="inhd hide">
						<ul class="inhd_ul">
							<li class="inhd_li on">我关注的</li>
							<li class="inhd_li">关注我的</li>
							<li class="inhd_li">推荐师友</li>
						</ul>
					</div> -->
					<div class="ul_out_box" id="ul_out_box1">
						<div class="inbd">
							<div id="noticeSwith" style="text-align: center;margin-top: 5px;padding-bottom: 5px;border-bottom: #d9d7d7 solid 1px;}">
								<button type="button" class="btn btn-sm btn-success" style="margin-right:3px;" onclick="showNotice('all');">所有未读</button>
								<button type="button" class="btn btn-sm btn-primary" style="margin-right:3px;" onclick="showNotice('notice');">通知提醒</button>
								<button type="button" class="btn btn-sm btn-primary" style="margin-right:3px;" onclick="showNotice('chat');">聊天消息</button>
							</div>
							<div class="inbd_ul" id="noticeList">
								<!-- 通知提醒容器 -->
								<!-- <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#noticeList" aria-expanded="false" aria-controls="collapseExample">
  									Button with data-target
								</button>
								<div class="collapse" >
										<div class="teacher_list">
											<div class="l_60" onclick="location.href='/chat/2'">
												<img src="/images/front/default_avatar.png" width="100%">
											</div>
											<div class="r_595" style="width:80%">
												<nobr onclick="location.href='/chat/2'">
													&nbsp;&nbsp;<span>[ 琉璃盏 ]</span>
												</nobr>
												<button type="button" class="btn weui_btn_primary fr" style="color: #FFFFFF;" onclick="location.href='/chat/2'">聊天</button>
												<button type="button" class="btn weui_btn_primary fr" style="color: #FFFFFF;" onclick="readed('2');return false;">已读</button>
											</div>
										</div>
								 </div> -->
							</div>
						</div>
					</div>
					<ul class="ul_out_box" id="ul_out_box2" style="display: none;">
						<div class="inhd">
							<ul class="inhd_ul">
								<li id="fans_tab1" class="inhd_li on pointer">我关注的<span id="meFocusCount"></span></li>
								<li id="fans_tab2" class="inhd_li pointer">关注我的<span id="focusMeCount"></span></li>
								<li id="fans_tab3" class="inhd_li pointer">推荐师友<span id="recommendCount"></span></li>
							</ul>
						</div>
						<div class="inbd">
							<div id="batchBonus" style="text-align: center;margin-top: 5px;padding-bottom: 5px;border-bottom: #d9d7d7 solid 1px;}">
								<button type="button" class="btn btn-sm btn-danger" style="margin-right:3px;" onclick="addBatchBonusChat();">群发红包</button>
							</div>
							<div class="inbd_ul" id="meFocus"></div>
							<div class="inbd_ul" style="display: none;" id="focusMe"></div>
							<div class="inbd_ul" style="display: none;" id="recommend"></div>
						</div>
					</ul>
				</div>
				<div id="resultTip" class="resultTip frontHide"></div>
				<div id="loadingTip" class="loadingTip  frontHide">正在加载，请稍候...</div>
			</div>
		</div>
	</div>
	<div class="pz_down">
		<div class="c_0100_9"></div>
	</div>
	
	<a id="gotop" onclick="goTop();" href="javascript:void(0);">︿</a>
	<div id="voice">
	</div>
	<input type="hidden" id="labelShow" value="${label}">
	<input type="hidden" value="${r}" id="r"/> 	<input type="hidden" value="${loginStatus}" id="loginStatus"/>
	<input type="hidden" id="self" value="${self}"/>
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

	<%@ include file="/views/front/common/z_div_menu_bottom.htm"%><!-- 底部菜单 -->
	<%@ include file="/views/front/common/z_div_monkey_nav.htm"%><!-- 猴子导航 -->
	<%@ include file="/views/front/common/z_div_type_slide.htm"%><!-- 右侧分类查询 -->

	<%@ include file="/views/front/common/z_div_qrcode.htm"%><!-- 二维码弹出框 -->
	<%@ include file="/views/common/common_js.htm"%>
	<%@ include file="/views/common/common_front_js.htm"%>
	<script type="text/javascript" src="/third-party/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/third-party/other/TouchSlide.1.1.js"></script>
	<script type="text/javascript" src="/third-party/other/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="/third-party/mustache/mustache.min.js"></script>
	<script type="text/javascript" src="/js/front/fans/fans.js" title="v"></script>
	<script type="text/javascript" src="/js/front/bonus/bonusSend.js" title="v"></script>
	<script id="template" type="x-tmpl-mustache">
		{{#rows}}
			<a href="javascript:void(0);">
				<div class="teacher_list">
					<div class="l_60" style="max-width:12%" onclick="lh.jumpR('/shop/{{userId}}');">
						<img src="{{userAvatar}}" width="100%" />
					</div>
					<div class="r_595" style="width:87%">
						<nobr onclick="lh.jumpR('/shop/{{userId}}');'">
							&nbsp;&nbsp;<span>{{userName}}&nbsp;&nbsp;[ {{userShopName}} ]</span>
						</nobr>
						<button type="button" class="btn btn-sm btn-success fr" style="padding:5px 15px;" onclick="lh.jumpR('/chat/{{userId}}');">聊天</button>
						<button type="button" class="btn btn-sm btn-danger fr" style="margin-right:3px;" onclick="addBonusChat({{userId}});">发红包</button>
					</div>
				</div>
			</a>
		{{/rows}}		 		 
	</script>
	<script id="template1" type="x-tmpl-mustache">
		{{#rows}}
			<a href="javascript:void(0);">
				<div class="teacher_list">
					<div class="l_60" style="max-width:12%" onclick="lh.jumpR('/shop/{{fansId}}');">
						<img src="{{fansAvatar}}" width="100%" />
					</div>
					<div class="r_595" style="width:87%">
						<nobr onclick="lh.jumpR('/shop/{{fansId}}');">
							&nbsp;&nbsp;<span>{{fansName}}&nbsp;&nbsp;[ {{fansShopName}} ]</span>
						</nobr>
						<button type="button" class="btn btn-sm btn-success fr" style="padding:5px 15px;" onclick="lh.jumpR('/chat/{{fansId}}');">聊天</button>
						<button type="button" class="btn btn-sm btn-danger fr" style="margin-right:3px;" onclick="addBonusChat({{fansId}});">发红包</button>
					</div>
				</div>
			</a>
		{{/rows}}		 		 
	</script>
	<script id="template2" type="x-tmpl-mustache">
		{{#rows}}
			<a href="javascript:void(0);">
				<div class="teacher_list">
					<div class="l_60" style="max-width:12%" onclick="lh.jumpR('/shop/{{userId}}');">
						<img src="{{userAvatar}}" width="100%" />
					</div>
					<div class="r_595" style="width:87%">
						<nobr onclick="lh.jumpR('/shop/{{userId}}');">
							&nbsp;&nbsp;<span>{{userName}}&nbsp;&nbsp;[ {{userShopName}} ]</span>
						</nobr>
						<button type="button" class="btn btn-sm btn-success fr" style="padding:5px 15px;" onclick="lh.jumpR('/chat/{{userId}}');">聊天</button>
						<button type="button" class="btn btn-sm btn-danger fr" style="margin-right:3px;" onclick="addBonusChat({{userId}});">发红包</button>
					</div>
				</div>
			</a>
		{{/rows}}		 		 
	</script>
	
	<script id="template_notice" type="x-tmpl-mustache">
		<!-- {{#linkUrl}}onclick="lh.jumpR('/{{linkUrl}}'{{/linkUrl}}');"-->
		{{#rows}}
			<div class="teacher_list">
				<!--<div class="l_60" style="max-width:12%"></div>-->
				<div class="r_595" style="width:100%">
					<img src="/images/front/laba.png" style="width:30px;float:left;margin-right:5px;"/>
					<a href="javascript:void(0);" style="width:70%;text-overflow: ellipsis;">
						&nbsp;<nobr id="notice_content" style="word-wrap:break-word; word-break:normal;" onclick="showNoticeAndRead('{{content}}','{{serial}}','{{linkUrl}}');">&nbsp;{{content}}</nobr>
					</a>
					{{^readTime}}
						<button type="button" class="btn btn-sm btn-info fr" style="" onclick="noticeReaded('{{serial}}');return false;">已读</button>
					{{/readTime}}
				</div>
			</div>
		{{/rows}}		 		 
	</script>
	<script id="template_chat" type="x-tmpl-mustache">
		{{#rows}}
			{{#repeat}}
					<div class="teacher_list">
						<div class="l_60" style="max-width:12%" onclick="lh.jumpR('/chat/{{senderId}}');">
							<img src="{{senderAvatar}}" width="100%" />
						</div>
						<div class="r_595" style="width:87%">
							<nobr onclick="lh.jumpR('/chat/{{senderId}}');">
								&nbsp;&nbsp;<span>[ {{senderName}} ]</span>
							</nobr>
							<button class="btn btn-sm btn-primary fr" type="button" data-toggle="collapse" data-target="#noticeList{{serial}}" aria-expanded="false" aria-controls="collapse{{serial}}">
								更多
							</button>
								<div class="collapse" id="noticeList{{serial}}">	
								{{#brothers}}
										<div class="teacher_list">
											<div class="r_595" style="width:100%">
												<span style="width:60%;display:inline-block;text-overflow: ellipsis;overflow:hidden;">
												<nobr>
													&nbsp;<span style="word-wrap:break-word; word-break:normal;" onclick="showChatAndRead('{{content}}','{{serial}}')">[{{senderName}}]&nbsp;&nbsp;{{content}}</span>
												</nobr>
												</span>
												<button type="button" class="btn btn-sm btn-success fr" style="" onclick="lh.jumpR('/chat/{{senderId}}');">聊天</button>
												{{^typeId}}
													<button type="button" class="btn btn-sm btn-info fr" style="margin-right:3px;" onclick="readed('{{serial}}');return false;">已读</button>
												{{/typeId}}
												{{#typeId}}
													<button type="button" class="btn btn-sm btn-danger fr" style="margin-right:3px;" onclick="receiveBonus({{senderId}},{{id}},1);return false;">抢红包</button>
												{{/typeId}}
											</div>
										</div>
								{{/brothers}}
							 </div>
						</div>
					</div>
			{{/repeat}}

			{{^brothers}}
				<a href="javascript:void(0);">
					<div class="teacher_list">
						<div class="l_60" onclick="lh.jumpR('/chat/{{senderId}}');">
							<img src="{{senderAvatar}}" width="100%" />
						</div>
						<div class="r_595" style="width:80%">
							<nobr style="text-overflow: ellipsis;">
								&nbsp;<span style="word-wrap:break-word; word-break:normal;" onclick="showChatAndRead('{{content}}','{{serial}}')">[{{senderName}}]&nbsp;&nbsp;{{content}}</span>
							</nobr>
							<button type="button" class="btn weui_btn_primary fr" style="color: #FFFFFF;" onclick="location.href='lh.jumpR('/chat/{{senderId}}');">聊天</button>
							{{^readTime}}
								{{^typeId}}
									<button type="button" class="btn btn-sm btn-info fr" style="margin-right:3px;" onclick="readed('{{serial}}');return false;">已读</button>
								{{/typeId}}
								{{#typeId}}
									<button type="button" class="btn btn-sm btn-danger fr" style="margin-right:3px;" onclick="receiveBonus({{senderId}},{{id}},1);return false;">抢红包</button>
								{{/typeId}}
							{{/readTime}}
						</div>
					</div>
				</a>
			{{/brothers}}
		{{/rows}}		 		 
	</script>
	
	<script id="template_all_chat" type="x-tmpl-mustache">
		<!-- onclick="lh.jumpR('/chat/{{senderId}}');"-->
		{{#rows}}
				<a href="javascript:void(0);">
					<div class="teacher_list">
						<div style="display:inline-block;" class="l_60" onclick="lh.jumpR('/chat/{{senderId}}');">
							<img src="{{senderAvatar}}" width="100%" />
						</div>
						<div class="r_595" style="width:55%;">
							<nobr style="text-overflow: ellipsis;">
								&nbsp;<span style="width:100%;display:inline-block;text-overflow: ellipsis;overflow:hidden;" onclick="showChatAndRead('{{content}}','{{serial}}')">[{{senderName}}]&nbsp;&nbsp;{{content}}</span>
							</nobr>
						</div>
						<button type="button" class="btn btn-sm weui_btn_primary fr" style="color: #FFFFFF;" onclick="lh.jumpR('/chat/{{senderId}}');">聊天</button>
						{{^readTime}}
							{{^typeId}}
								<button type="button" class="btn btn-sm btn-info fr" style="margin-right:3px;" onclick="readed('{{serial}}');return false;">已读</button>
							{{/typeId}}
							{{#typeId}}
								<button type="button" class="btn btn-sm btn-danger fr" style="margin-right:3px;" onclick="receiveBonus({{senderId}},{{id}},1);return false;">抢红包</button>
							{{/typeId}}
						{{/readTime}}
					</div>
				</a>
		{{/rows}}		 		 
	</script>
	
</body>
</html>
