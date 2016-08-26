VERIFYCODE_SEND = false;
SAVING_FLAG = false;
VERIFYCODE_PEROID = 0;
function sendVerify() {
	var phone = $("#phone").val();
	if (!phone) {
		lh.alert('请输入要发送验证码的手机号码');
		SAVING_FLAG = false;
		return;
	}
	if (VERIFYCODE_PEROID > 0) {
		return;
	} else {
		VERIFYCODE_PEROID = 60;
		var timerId = setInterval(function() {
			$('#btnMssage').text('点此发送短信验证码(' + VERIFYCODE_PEROID + ')');
			VERIFYCODE_PEROID--;
			if (VERIFYCODE_PEROID <= 0) {
				clearInterval(timerId);
				$('#btnMssage').text('点此发送短信验证码');
			}
		}, 1000);
	}
	
	$.post('/getPayPasswordVerifycode', {phone : phone}, function(rsp) {
		if (rsp) {
			frontLoginCheck(rsp);// 登陆检查
			if (rsp.status == 'success') {
				lh.alert(rsp.msg);
				VERIFYCODE_SEND = true;
				//$("#seMsg").hide();
				//$("#reseMsg").show();
				setTimeout(function() {
					$('#baseDialog').hide();
				}, '3000');
			} else {
				lh.alert(rsp.msg);
			}
		}
	}, 'json');
	
}

function nextStep() {
	var verifyCodeNum = $("#verifyCodeNum").val();
	if (!VERIFYCODE_SEND) {
		lh.alert('请先获取验证码');
		SAVING_FLAG = false;
		return;
	}
	if (!verifyCodeNum) {
		lh.alert('请填入获取的验证码');
		SAVING_FLAG = false;
		return;
	}
	$("#save,#newPass,#passTip").show();
	$("#next").hide();	
}

function savePassword() {
	if (SAVING_FLAG) return;
	SAVING_FLAG = true;
	var newPassWord = $("#newPassWord").val();
	if (!newPassWord) {
		lh.alert('请输入新的密码');
		SAVING_FLAG = false;
		return;
	}
	if (newPassWord.length < 4) {
		lh.alert('密码的长度必须大于或等于4位数');
		SAVING_FLAG = false;
		return;
	}
	var verifyCodeNum = $("#verifyCodeNum").val();
	$.post('/updateUserFundPayPasswordFind', {payPassword : newPassWord,code:verifyCodeNum}, function(rsp) {
		SAVING_FLAG = false;
		frontBaseLoadingClose();// 解除遮罩
		if (rsp) {
			frontLoginCheck(rsp);// 登陆检查
			if (rsp.status == 'success') {
				lh.alert(rsp.msg);
				setTimeout(function() {
					var url = '/user';
					var r = $("#r").val();
					if(r) url += "?r="+r;
					window.location.href = url;
					$('#baseDialog').hide();
				}, '3000');
			} else {
				lh.alert(rsp.msg);
				$("#save,#newPass,#passTip").hide();
				$("#next").show();	
			}
		}
	}, 'json');
}
