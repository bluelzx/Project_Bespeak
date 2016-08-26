/** 群组聊天公用 JS  */
//http://www.yuntongxun.com/api/im/im_1_3#point_box


lh.param.loginFailTimes = 0;
lh.param.joinGroupFailTimes = 0;
/** 初始化聊天组件  */
function initCommonChat(options, onLoginFun){
	//初始化SDK，App的通知消息显示号码
	//var resp = RL_YTX.init("8a48b551510f653b0151135666060d07");  //appId
	var resp = RL_YTX.init("8a48b551523a5c1201523ebeef200aa5"); //appId
	if(102 == resp.code){//缺少必要参数,用户逻辑处理
		lh.alert('缺少必要参数');
	}else if(101 == resp.code){//不支持HTML5，关闭页面,用户逻辑处理
		lh.alert('您的浏览器不支持HTML5');
	}else if(200 == resp.code){//初始化成功,用户逻辑处理
		console.log('init success');
		chatLogin(options, onLoginFun);
	}  
}

/** 登陆聊天组件  */
function chatLogin(options, onLoginFun){
	lh.param.chatOptions = options;
	lh.param.chatLoginFun = onLoginFun;
	//注意：sig字段又用户请求自己的服务器生成，sig规则：MD5(appid+userName+timestamp+apptoken); 当voip账号密码登录时，可以不传sig
	//var loginBuilder = new RL_YTX.LoginBuilder(3, options.userTokenId,options.userTokenPswd);
	//var loginBuilder = new RL_YTX.LoginBuilder(1, USER_TOKEN_ID, '', CHAT_SIG, CHAT_TIMESTAMP);
	var loginBuilder = new RL_YTX.LoginBuilder();
	loginBuilder.setType(1);//登录类型 1：完整帐号登录 2：精简认证 3：voip帐号密码登录
	loginBuilder.setUserName(options.senderSerial);//用户账号或voip账号
	//loginBuilder.setPwd();//Voip密码 type值为1时，密码可以不赋值
	loginBuilder.setSig(options.chatSig);//设置sig 登录sig值，由页面请求第三方服务器生成
	loginBuilder.setTimestamp(options.chatTimeStamp);//设置时间戳 登录时间戳 yyyyMMddHHmmss格式
	//执行用户登录
	RL_YTX.login(loginBuilder, function(obj){//登录成功回调
		RL_YTX.onMsgReceiveListener(function(obj){//收到push消息或者离线消息
			commonReceiveMsg(obj,options);//将接收到的消息显示到页面
		});
		//注册群组通知事件监听
		RL_YTX.onNoticeReceiveListener(function(obj){//收到群组通知
			console.log('onNoticeReceiveListener');
		});
		RL_YTX.onConnectStateChangeLisenter(function(obj){//连接状态变更
			console.log('onConnectStateChangeLisenter');
		});
		loadChatHistory(options);//历史聊天信息-fromDB
		if(onLoginFun)onLoginFun();
	},function(obj){//登录失败方法回调
		if(lh.param.loginFailTimes > 10){
			lh.alert('聊天组件登陆失败');
			setTimeout(function(){
				lh.back();
			}, 2000);
			return;
		}
		setTimeout(function(){
			lh.param.loginFailTimes++;
			chatLogin(lh.param.chatOptions, lh.param.chatLoginFun);
			console.log(obj);
		}, 1000);
	});
}

function chatLogout(){
	RL_YTX.logout(function(){//登出成功处理
		console.log('logout success');
	}, function(obj){//登出失败处理
		lh.alert('聊天组件登出失败');
	});
}

function showMsgBottom(domId){
	/*if(!domId)domId = 'msgSend';
	$('html,body').animate({scrollTop:$('#'+domId).offset().top}, 800);*/
	//TODO 需要恢复下面的代码，定位到最新发言位置
	//$("#msg_container").scrollTop($("#msg_container")[0].scrollHeight+60); 
}

function joinGroup(groupId, onJoinGroupFun){
	lh.param.chatGroupId = groupId;
	lh.param.chatGroupFun = onJoinGroupFun;
	var builder= new RL_YTX.JoinGroupBuilder();
	builder.setGroupId(groupId);//设置申请群组id
	RL_YTX.joinGroup(builder, function(d){//操作成功
		//console.log('join group success');
		if(onJoinGroupFun)onJoinGroupFun();
	},function(obj){//操作失败
		//PARAMETER_EMPTY 170002 必选参数为空
		var code = obj.code;
		if(code == 590017){//成员已经存在,不做处理
			if(onJoinGroupFun)onJoinGroupFun();
		}else if(code == 590010){//群组不存在
			lh.alert('本次拍卖已结束');
		}else if(code == 170003){//未登录
			lh.jumpR('/');
			//lh.alert('您未成功登录');
		}else{
			if(lh.param.loginFailTimes > 10){
				lh.alert('加入群组失败');
				setTimeout(function(){
					lh.jumpR('/');
				}, 2000);
				return;
			}
			setTimeout(function(){
				lh.param.loginFailTimes++;
				chatLogin(lh.param.chatOptions, lh.param.chatLoginFun);
				console.log(obj);
			}, 1000);
			
		}
	});
}

