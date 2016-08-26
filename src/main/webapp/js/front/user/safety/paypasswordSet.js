$(function() {
	according();// 初始化密码框
	
});

function passwordSet(userId){
	var newpayPassword = $(".form-control").val();
	if(newpayPassword.length < 6){
		lh.alert("密码不能少于6位"); return;
	}
//	/payPasswordSet
	$.post('/payPasswordSet',{userId:userId,payPassword:newpayPassword},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.alert(rsp.msg,lh.back);
			}else{
				lh.alert(rsp.msg,lh.back);
			}
		}
	},'json');
}		
			
function according() {// 显示密码明文
	var tem = $("#passwordeyes");
	$("#passwordeyes").mousedown(function() {
				$(".form-control").attr("type", "text");
			});
	$("#passwordeyes").mouseup(function() {
				$(".form-control").attr("type", "password");
			});
}