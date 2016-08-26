var ifGetCode = false;
SAVING_FLAG = false;
VERIFYCODE_PEROID = 0;
$(function() {
			initPage();// 初始化页面
		});

//发送短信验证码 先判断手机号码是否被占用
function sendVerify() {
	if($("#phoneNum").attr("disabled") == 'disabled'){
		lh.alert('手机已经通过验证',lh.back);
		return;  
	}
	var phoneNumIsChecked = $("#phoneNum").val().trim();
	lh.post('front', '/user/safety/checkUserPhoneExist', {phone : phoneNumIsChecked}, function(rsp) {
					if (rsp.status == 'success') {
//						send(phoneNumIsChecked);
						if(rsp.msg == '手机号码已占用'){
							lh.alert(rsp.msg); return;
						}else{
							send(phoneNumIsChecked);
						}
					} else {
						lh.alert(rsp.msg);
					}
				}, 'json');
}
//发送短信
function send(phoneNumIsChecked){
	if (phoneNumIsChecked) {
		// getPayPasswordVerifycode
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
			lh.post('front', '/getPayPasswordVerifycode', {phone : phoneNumIsChecked}, function(rsp) {
					if (rsp.status == 'success') {
						ifGetCode = true;
						lh.alert(rsp.msg);
					} else {
						ifGetCode = false;
						lh.alert(rsp.msg);
					}
				}, 'json');
	} else {
		lh.alert("请输入您要验证的手机号码！！");
	}
}
//校验短信验证码
function checkPhoneCode() {
	if($("#phoneNum").attr("disabled") == 'disabled'){
		lh.alert('手机已经验证',lh.back);
		return;
	}
	var phoneCode = $("#phoneCode").val();
	if (!ifGetCode) {
		lh.alert('请先获取验证码');
		ifGetCode = false;
		return;
	}
	if (phoneCode.length < 1) {
		lh.alert('请填入获取的验证码');
		ifGetCode = false;
		return;
	}
	lh.post('front', '/checkVerifyCodeNum', {userVerifyCode : phoneCode}, function(rsp) {
				if (rsp.success) {
					if (rsp.checkedSuccess == '匹配成功') {
						updateUserPhone();
						lh.alert('匹配成功');
					} else {
						lh.alert("验证码错误");
					}
				} else {
					lh.alert("验证码错误");
				}
			}, 'json');
}

function updateUserPhone() {
	var phoneNumIsChecked = $("#phoneNum").val();
	var param = {phone:phoneNumIsChecked};
	lh.post('front', '/user/safety/updateUserPhone', param, function(rsp) {
				if (rsp.success) {
					lh.alert(rsp.msg,lh.back);
					lh.back;
				} else {
					lh.alert("修改失败");
				}
			});
}

function initPage() {// 
	var phoneNumIsChecked = $("#phoneNum").val();
	if (phoneNumIsChecked.length < 1) {
		$("#phoneNum").attr("disabled", false);
		$("#phoneCode").attr("disabled", false)
	} else {
		$("#phoneNum").attr("disabled", true);
		$("#phoneCode").attr("disabled", true);
	}

}