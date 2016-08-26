
$(function(){
	var noPhone = $("#noPhone").val();
	if(noPhone){
		frontBaseConfirm("您还未绑定手机号码，是否前往绑定手机号码","jumpToBindUserPhone()",null);
	}
});

function editPassword (){
	var oldPassword = $("#oldPassword").val();
	var newPassword = $("#newPassword").val();
	if(!oldPassword){
		lh.alert('请输入旧密码');
		return ;
	}
	if(!newPassword){
		lh.alert('请输入新密码');
		return ;
	}
	if(oldPassword == newPassword){
		lh.alert('新密码和旧密码一样,请修改');
		return ;
	}
	$.post('/updateUserPassword',{oldPassword:oldPassword,newPassword:newPassword},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.alert(rsp.msg);
				setTimeout(function(){
					window.location.href='/login';
					$('#baseDialog').hide();
				},'3000');
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}