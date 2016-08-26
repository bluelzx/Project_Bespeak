/** 登陆注册 对应 JS */

LOGIN_REG = 1;
VERIFYCODE_PEROID = 0;

$(function(){
//	localStorage.setItem("mobile", '123456');//TODO 快捷登陆
//	localStorage.setItem("password", '888888');//TODO 快捷登陆
	
//	if(localStorage){
//		var mobile = localStorage.getItem("mobile");
//		var password = localStorage.getItem("password");
//		if(mobile && password){
//			LOGIN_REG = 1;
//			$('#mobile').val(mobile);
//			$('#password').val(password);
//			login();return;
//		}
//		if(mobile){
//			$('#mobile').val(mobile);
//		}
//	}
});

function afterMobile(){
	var mobile = $('#mobile').val();
	if(!mobile){
		lh.alert('请输入您的手机号码');return;
	}
	$.post('/user/safety/checkUserPhoneExist',{phone:mobile},function(rsp){
		if(rsp.status == 'success'){
			LOGIN_REG = 1;
			$('#ulPassword,#iforGet').show();
			$('#ulMobile,#nicknameLable,#nickname,#msgTip').hide();
		}else if(rsp.status == 'none'){
			LOGIN_REG = 2;
			$('#ulCode,#ulPassword,#passwordLable,#nicknameLable,#nickname,#msgTip').show();
			$('#ulMobile,#iforGet').hide();
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function getVerifycode(){
	if(VERIFYCODE_PEROID>0){
		return;
	}else{
		VERIFYCODE_PEROID = 60;
		var timerId = setInterval(function(){
			$('#btnMobile').css('background-color','rgb(163, 163, 172)').text('获取验证码('+VERIFYCODE_PEROID+')');
			VERIFYCODE_PEROID--;
			if(VERIFYCODE_PEROID <= 0){
				clearInterval(timerId);
				$('#btnMobile').css('background-color','#25A431').text('获取验证码');
			}
		},1000);
	}
	var mobile = $('#mobile').val().trim();
	$.post('/getVerifycode',{phone:mobile},function(rsp){
		if(rsp.status == 'success'){
			lh.alert('已将短信验证发送到您的手机，请及时查看');
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

/*function afterCode(){
	var verifycode = $('#verifycode').val().trim();
	if(!verifycode){
		lh.alert('请输入短信验证码');return;
	}
	$('#ulCode,#iforGet').hide();
	$('#ulPassword,#nicknameLable,#nickname').show();
}*/

function login(){
	var mobile = $('#mobile').val().trim();
	var password = $('#password').val().trim();
	var url = '/doMobileLogin';
	var obj = {};
	if(LOGIN_REG == 2){
		url = '/doReg';
		var r = $("#r").val();
		var nickname = $('#nickname').val().trim();
		var verifycode = $('#verifycode').val().trim();
		obj.username = nickname;
		obj.verifycode = verifycode;
		obj.r  = r;
		if(!verifycode){
			lh.alert('请输入验证码');return;
		}
		if(!nickname){
			lh.alert('请输入昵称');return;
		}
		var re =/[`~!@#$%^&*_+<>{}\/'[\]]/gi;
		if(re.test(nickname) || re.test(nickname)){
			lh.alert('昵称不能包含特殊字符');return;
		}
		if(!password){
			lh.alert('请输入您的密码');return;
		}
		if(password.length < 6 || password.length > 20){
			lh.alert('请将密码位数保持在6-20位之间');return;
		}
		//var re = /^[\u4e00-\u9fa5a-z]+$/gi;//只能输入汉字和英文字母
		var re2= /[w]*/i; 
		if(!re2.test(password)){
			lh.alert('密码只能为英文和数字');return;
		}
	}
	if(!mobile){
		lh.alert('请输入您的手机号码');return;
	}
	if(!password){
		lh.alert('请输入您的密码');return;
	}
	obj.phone = mobile;
	obj.password = password;
	$("#loginToast").show();
	$.post(url, obj, function(rsp){
		$("#loginToast").hide();
		var url = $('#jumpUrl').val();
		if(rsp.success){
			if(localStorage){
				localStorage.setItem("mobile",mobile);
				localStorage.setItem("password",password);
			}
			location.href = url || "/";return;
		}else{
			
			loginUserSilent(null, url);return;//登陆密码登陆错误，直接调用微信登陆
			
			if(rsp.status == 'input_error'){
				//$('#mobile').val('');
				$('#password').val('');
				localStorage.removeItem("mobile");
				localStorage.removeItem("password");
			}
			lh.alert(rsp.msg);
			$("#loginToast").hide();
		}
	},'json');
}
