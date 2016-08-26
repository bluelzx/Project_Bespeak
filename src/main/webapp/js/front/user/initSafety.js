SAVING_FLAG = false;
VERIFYCODE = null;
VERIFYCODE_PEROID = 0;
function sendVerify(){
	if(SAVING_FLAG)return;
	var phone = $("#phone").val().trim();
	if(!phone){
		lh.alert('请输入要发送验证码的手机号码');
		return ;
	}
	if(VERIFYCODE_PEROID>0){
		return;
	}else{
		VERIFYCODE_PEROID = 60;
		var timerId = setInterval(function(){
			$('#btnMssage').text('点此发送短信验证码('+VERIFYCODE_PEROID+')');
			VERIFYCODE_PEROID--;
			if(VERIFYCODE_PEROID <= 0){
				clearInterval(timerId);
				$('#btnMssage').text('点此发送短信验证码');
			}
		},1000);
	}
	$.post('/getBindUserPhoneVerifycode',{phone:phone},function(rsp){
		SAVING_FLAG = false;
		frontLoginCheck(rsp);//登陆检查
		if(rsp.status == 'success'){
			lh.alert('验证码已发送到您的手机，请注意查收');
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function saveData(){
	if(SAVING_FLAG)return;
	var phone = $("#phone").val();
	var verifyCodeNum = $("#verifyCodeNum").val();
	var loginPass = $("#loginPass").val();
	var payPass = $("#payPass").val();
	if(!phone){
		lh.alert('请输入您的手机号码');return;
	}
	if(!verifyCodeNum){
		lh.alert('请填入获取的验证码');return ;
	}
	if(!loginPass){
		lh.alert('请输入登陆密码');return;
	}
	if(!payPass){
		lh.alert('请输入支付密码');return;
	}
	var obj = {phone:phone, loginPass:loginPass,payPass:payPass};
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	SAVING_FLAG = true;
	$.post('/initSafety', obj, function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		if(rsp.status == 'success'){
			frontBaseConfirm(rsp.msg,'goBack()');
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function goBack(){
	history.back();
}