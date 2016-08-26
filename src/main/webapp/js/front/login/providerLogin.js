lh.loginType = 1;//登陆类型：用户登陆
$(function(){
	initData();
});

function initData(){
	loadRandomCode();
}

function loadRandomCode(){ 
	$('#imgcode').attr('src','/login/loadVerificationCode?id='+parseInt(1000*Math.random()));
}
////单用手机号登陆
//function doMobileLogin(){
//	var phone = $("#username").val();
//	if(!phone){
////		$("#usernameTip").show();
//		alert("手机号不能为空！");
//		return;
//	}
//	var password = $("#password").val();
//	if(!password){
//		alert("密码不能为空！");
////		$("#passwordTip").show();
//		return;
//	}
//	var verificationCode = $("#verificationCode").val();
//	if(!verificationCode){
////		$("#verificationCodeTip").show();
//		alert("验证码不能为空！");
//		return;
//	}
//	//
//	var obj = {phone:phone,password:password,verificationCode:verificationCode};
//	lh.post("front", '/doMobileLogin', obj, function(rsp){
//		if(rsp.status == 'success'){
//			lh.alert(rsp.msg,jumpToLoginIndex);
//		}else{
//			lh.alert(rsp.msg);
//		}
//	}, 'json')
//}

// 用户登陆
function doProviderLogin(){
	var username = $("#username").val();
	if(!username){
//		$("#usernameTip").show();
		lh.alert("用户名不能为空！");
		return;
	}
	var password = $("#password").val();
	if(!password){
		lh.alert("密码不能为空！");
//		$("#passwordTip").show();
		return;
	}
	var verificationCode = $("#verificationCode").val();
	if(!verificationCode){
//		$("#verificationCodeTip").show();
		lh.alert("验证码不能为空！");
		return;
	}
	//phone:username,
	var obj = {username:username,password:password,verificationCode:verificationCode};
	lh.post("front", '/providerDoLogin', obj, function(rsp){
		if(rsp.status == 'success'){
//			var targetId = locache.get("user_li_id");
//			if(null != targetId && "" != targetId){
//				locache.set("user_li_id", 4);
//				lh.jumpToUrl('/userReportList');
//			}else{
//				locache.set("user_li_id", 1);
//			alert("返回！");
			lh.jumpR("/userHome");
//				lh.jumpToUrl('/index');
//			}
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json')
}

function jumpToLoginIndex(){
	lh.jumpR("/userHome");
}

function loginQuickly(){
	var obj = {username:lh.test.username,password:lh.test.password,verificationCode:1};
	lh.post("front", '/doLogin', obj, function(rsp){
		if(rsp.status == 'success'){
			var targetId = locache.get("user_li_id");
			if(null != targetId && "" != targetId){
				locache.set("user_li_id", 4);
				lh.jumpToUrl('/userReportList');
			}else{
				locache.set("user_li_id", 1);
				lh.jumpToUrl('/userBaseInformation');
			}
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json')
}