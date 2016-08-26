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

function register(){
	
	var protocol = $("#protocol").prop('checked');
	if(!protocol){lh.alert('您未同意《服务使用协议》，无法进行注册');return;}
	
	var randomCode = $("#randomCode").val();//验证码
//	var idcardNum = $("#idcardNum").val();
	var phone = $("#phone").val();
	var username= $("#username").val();
	var password = $("#password").val();
	var passwordSure = $("#passwordSure").val();
//	if(!idcardNum){lh.alert('请输入身份证号码');return;}
	if(!phone){lh.alert('请输入电话号码');return;}
	if(!username){lh.alert('请输入登陆用户名');return;}
	if(!password){lh.alert('请输入用户密码');return;}
	if(!passwordSure){lh.alert('请输入确认密码');return;}
	if(passwordSure != password){lh.alert('确认密码与用户密码不一致');return;}
	//idcardNum:idcardNum,
	var obj={randomCode:randomCode,username:username,phone:phone,password:password};
	saveData(obj);
}


function saveData(obj){
	if(lh.preventRepeat()){//防止重复提交
		lh.post("front", '/doReg', obj, function(rsp){
			if(rsp.status == 'success'){
				location.href="/login";//后期改为用户信息完善页面
			}else if(rsp.status == 'failure'){
				lh.alert(rsp.msg);
			}
		}, "json")
	}else{
		//lh.showRepeatTip();//提示重复提交
		lh.alert('请不要重复提交数据');
	}
}
