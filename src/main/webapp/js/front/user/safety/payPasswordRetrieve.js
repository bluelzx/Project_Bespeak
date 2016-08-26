var ifGetCode = false;
SAVING_FLAG = false;
VERIFYCODE_PEROID = 0;
$(function() {
			// according();// 初始化密码框
		});

function sendVerify() {
	var phone = $("#phone").val().trim();
	if (!phone) {
		lh.alert('您的手机号码没有验证！！',jumpPhoneCheck);
		
		SAVING_FLAG = false;
		return;
	}
	if (VERIFYCODE_PEROID > 0) {
		return;
	} else {
		VERIFYCODE_PEROID = 60;
		var timerId = setInterval(function() {
					$('#btnMssage').text('获取验证码(' + VERIFYCODE_PEROID + ')');
					VERIFYCODE_PEROID--; 
					if (VERIFYCODE_PEROID <= 0) {
						clearInterval(timerId);
						$('#btnMssage').text('获取验证码');
					}
				}, 1000);
	}
	$.post('/getPayPasswordVerifycode', {
				phone : phone
			}, function(rsp) {
				if (rsp.status == 'success') {
					ifGetCode = true;
					lh.alert(rsp.msg);
				} else {
					ifGetCode = false;
					lh.alert(rsp.msg);
				}
			}, 'json');
}
function updatePayPassword(userId) {
	var userVerifyCode = $("#usererifyCodeNum").val();
	checkVerifyCodeNum(userVerifyCode);
}
function checkVerifyCodeNum(userVerifyCode) {
	if (!ifGetCode) {
		lh.alert('请先获取验证码');
		ifGetCode = false;
		return;
	}
	if (userVerifyCode.length < 1) {
		lh.alert('请填入获取的验证码');
		ifGetCode = false;
		return;
	}
	lh.post('front', '/checkVerifyCodeNum', {
				userVerifyCode : userVerifyCode
			}, function(rsp) {
				if (rsp.success) {
					if (rsp.checkedSuccess == '匹配成功') {
						savePayPassword();
					} else {
						lh.alert("验证码错误");
					}
				} else {
					lh.alert("验证码错误");
				}
			}, 'json');
}
function savePayPassword() {
	var newPayPassword = $("#newPayPassword").val();
	if(newPayPassword.length < 6){
		lh.alert("支付密码不能少于6位");return;
	}
	var userId = $("#userId").val();
	var param = {
		newPayPassword : newPayPassword
	};
	lh.post('front', '/updateUserFundPayPassword', param, function(rsp) {
				if (rsp.success) {
					lh.alert(rsp.msg,lh.back);
					
				} else {
					lh.alert("修改失败");
				}
			});
}



function jumpPhoneCheck(){
	lh.jumpR('/user/safety/phoneValidate');
}
//
// function according() {// 显示密码明文
// var tem = $("#passwordeyes");
// $("#passwordeyes").mousedown(function() {
// $(".form-control").attr("type", "text");
// });
// $("#passwordeyes").mouseup(function() {
// $(".form-control").attr("type", "password");
// });
// }
