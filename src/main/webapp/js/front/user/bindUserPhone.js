SAVING_FLAG = false;
VERIFYCODE = null;
VERIFYCODE_PEROID = 0;
function sendVerify(){
	var phone = $("#phone").val().trim();
	if(!phone){
		lh.alert('请输入要发送验证码的手机号码');
		SAVING_FLAG = false;
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
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.alert(rsp.msg);
				VERIFYCODE = rsp.verifycode;
				/*$("#seMsg").hide();
				$("#reseMsg").show();
				setTimeout(function(){
					$('#baseDialog').hide();
				},'3000');*/
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function nextStep(){
	var verifyCodeNum = $("#verifyCodeNum").val();
	if(!VERIFYCODE){
		lh.alert('请先获取验证码');
		SAVING_FLAG = false;
		return ;
	}
	if(!verifyCodeNum){
		lh.alert('请填入获取的验证码');
		SAVING_FLAG = false;
		return ;
	}
	if(verifyCodeNum != VERIFYCODE){
		lh.alert('输入的验证码输入有误,请确认验证码是否错误或者重新获取验证码');
		SAVING_FLAG = false;
		return ;
	}
	$("#save").show();
	$("#next").hide();
}

function addBindUserPhone(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var phone = $("#phone").val();
	var userId = $("#userId").val();
	if(!phone){
		lh.alert('请输入您的手机号码');SAVING_FLAG = false;return;
	}
	var obj = {};
	obj.phone = phone;
	obj.id = userId;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateUser',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				frontBaseConfirm(rsp.msg,'goBack()');
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function goBack(){
	var url = '/user';
	var r = $("#r").val();
	if(r) url += "?r="+r;
	window.location.href = url;
}