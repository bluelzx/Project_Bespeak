/** 群组聊天公用 JS  */
TIMMER = null;
lh.param.msgInDom = 0;//DOM中已经存在的消息数量
function commonSendGroupMsg(chat, notDB, notAppend){//msgContent,receiverTokenId,senderAvatar,
	chat.content = chat.content || $("#msgContent").val();
	if(!chat.content){
		lh.alert('请输入您要发送的消息');return;
	}
	var obj = new RL_YTX.MsgBuilder();//新建消息体对象
	var serial = lh.createSerial();
	obj.setId(serial);//设置自定义消息id
	obj.setText(chat.content);//设置发送的文本内容
	obj.setType(1);//设置发送的消息类型1文本消息4 图片消息6 附件消息,发送非文本消息时，text字段将被忽略，发送文本消息时 file字段将被忽略
	var singleOrGroup = 2;//群组聊天消息
	var gId = chat.chatGroupId;
	obj.setReceiver(gId);//设置接收群组
	//if(!chat.bonus)chat.bonus = 1;
	var domain = {
		avt:chat.senderAvatar,
		name:chat.senderName,
		uId:chat.senderId,
		rId:gId,
		sog:singleOrGroup,
		srl:serial,
		mg:chat.msgGroup,
		price:chat.price,
		agId:chat.agId,
		bonus:chat.bonus,
		goodsPic:chat.goodsPic
	};
	domain = JSON.stringify(domain).replace(new RegExp("\"","g"),"'");
	obj.setDomain(domain);
	RL_YTX.sendMsg(obj, function(){//发送消息成功
		if(!notAppend)msgSendAppend(chat, notDB);
	}, function(obj){//发送消息失败
		if(!notAppend){
			chat.content += '-发送失败';
			msgSendAppend(chat, 1);
		}
	}, function(sended, total){//发送图片或附件时的进度条,如果发送文本消息，可以不传该参数
	});
}

function commonReceiveMsg(obj, options){
	var sender = obj.msgSender;//获取发送者为 
	var you_senderNickName = obj.senderNickName;//获取发送者昵称，如果不存在，使用账号代替
	var name = obj.msgSender;
	if(!!you_senderNickName){
	    name = you_senderNickName;
	}
	var content_type = null;
	var version = obj.version;//获取消息版本号
	var time = obj.msgDateCreated;//获取消息发送时间
	var msgType = obj.msgType;//获取消息类型,1:文本消息 2:语音消息4:图片消息6:文件
	if(1 == msgType || 0 == msgType){//文本消息，获取消息内容
		var you_msgContent = obj.msgContent;
	}else if(2 == msgType){//语音消息，获取语音文件url
		var url = obj.msgFileUrl;
	}else if(3 == msgType){//3：视频消息，获取视频url//语音消息，获取语音文件url
		var url = obj.msgFileUrl;
	}else if(4 == msgType){//图片消息 获取图片url
		var url = obj.msgFileUrl;
	}else{
		//后续还会支持(地理位置，视频，以及自定义消息等)
	}
	var date = new Date(parseInt(time));
	obj.sendHour = lh.formatDate({data:date, format:'HH:mm:ss'});
	var domain = obj.msgDomain.replace(new RegExp("'","g"),"\"");
	var d = JSON.parse(domain);
	
	if(d.sog == 2){//只处理群组消息
		if(options.chatGroupId == d.rId){//只处理当前群组消息
			if(you_msgContent == '-sys-weipaike-countdown-begin-'){//开始倒计时
				refreshTimmer(null,d.agId);//发一个更新TIMMER的消息，开始倒计时
				return;
			}
			var avatar = d.avt || '/images/front/default_avatar.jpg';
			var name = d.name || '未显示';
			var price = d.price;
			var priceDom = '';
			if(price){//如果是出价，就同时刷新倒计时
				changePriceGap();
				refreshTimmer(null,d.agId);//如果出价了，发一个更新TIMMER的消息
				$('#currentPrice').text(price);
				$('#currentUser').text(name);
				if(lh.param.ag)$('#offerPrice').val(parseInt(price) + lh.param.ag.priceGap);
				$('#offerTimes').text(++lh.current.ag.offerTimes);
				priceDom = '出价<span style="color:green;"> '+price+'</span> 元';
			}
			var rChat = {content:you_msgContent,receiverAvatar:avatar,senderName:name,
						sendHour:obj.sendHour,msgGroup:d.mg,price:priceDom,senderId:d.uId}
			if(d.bonus)rChat.bonus = d.bonus;
			if(d.goodsPic)rChat.goodsPic = d.goodsPic;
			msgReceiveAppend(rChat);
		}
	}
}

function commonGroupAppend(rendered){
	$('#msg_container').append(rendered);
	lh.param.msgInDom++;
	if(lh.param.msgInDom > 100){
		$('#msg_container .host_say:lt(10)').remove();
		lh.param.msgInDom -= 10;
	}
	showMsgBottom();//调整滚动条到最新消息
}

function msgSendAppend(chat,notDB){
	var content = chat.content;
	var price = chat.price;
	if(price){
		chat.content = null;
		chat.price = chat.localPrice;
	}
	if(chat.bonus){
		var template = lh.plugins.templateBonus;
	}else{
		var template = lh.plugins.templateOther;
	}
	var rendered = Mustache.render(template, {chat:chat});
	commonGroupAppend(rendered);
	$("#msgContent").val('');
	if(!notDB){
		chat.content = content;
		chat.price = price;
		addChat(chat);
	}
}

function msgReceiveAppend(chat){
	if(chat.price)chat.content = null;
	if(chat.bonus){
		var template = lh.plugins.templateBonus;
	}else{
		var template = lh.plugins.templateOther;
	}
	var rendered = Mustache.render(template, {chat:chat});
	commonGroupAppend(rendered);
	goToBottom();//滚动到最底部
}

function appendChatHistory(rows,userId){
	for(var i = 0;i<rows.length;i++){
		var c = rows[i];
		var date = new Date(c.sendTime);
		c.sendHour = lh.formatDate({data:date, format:'HH:mm:ss'});
		if(c.senderId == userId){
			msgSendAppend(c,true);
		}else{
			msgReceiveAppend(c);
		}
	}
}

function loadChatHistory(options,isShowHour){
	return;//暂时不加载群组消息记录
	var receiverId = options.receiverId;
	var chatGroupId = options.chatGroupId;
	var param = {sc_order:'send_time___ASC',chatGroupId:options.chatGroupId,typeId:2,page:1,rows:10};
	$.post('/getChatProfessionList',param,function(rsp){
		if(rsp){
			if(rsp.success){
				var total = rsp.total;
				var rows = rsp.rows;
				if(rows && rows.length>0){
					appendChatHistory(rows,rsp.userId);
				}
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function countdownWithMask(auctionSuccessFun){
	if(!lh.param.timmer)lh.param.timmer = 120;
	var timeId = setInterval(function(){
		if(lh.param.timmer <= 15){
			showMask();
			if(lh.param.timmer <= 0){
				clearInterval(timeId);
				closeMask();
				auctionSuccessFun();
			}
		}
		lh.param.timmer--;
	},1000);
}

function refreshTimmer(seconds, auctionGoodsId){
	closeMask();
	if(seconds){
		lh.param.timmer = seconds;
		if(!lh.param.timmer || lh.param.timmer < 0)lh.param.timmer = 120;
	}else{
		getRemainSeconds(auctionGoodsId);
	}
}

function showMask(){
	//$("#msg_container").css('overflow','hidden').addClass('mask');
	lh.mask();
	$("#timmer_number").text(lh.param.timmer).show();
}

function closeMask(){
	//$("#msg_container").css('overflow','scroll').removeClass('mask');
	lh.hideMask();
	$("#timmer_number").text(10).hide();
}

