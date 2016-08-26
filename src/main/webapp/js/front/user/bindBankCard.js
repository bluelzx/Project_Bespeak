SAVING_FLAG = false;

$(function(){
	var isRealAuth = $("#isRealAuth").val();
	if(isRealAuth != 2){
		lh.alert('申请提现,需要实名认证.您目前还未实名认证,3秒后将跳转到实名认证页面');
		setTimeout(function(){
			var url = '/realName';
			var r = $("#r").val();
			if(r) url += "?r="+r;
			window.location.href = url;
		},3000);
	}
});

function addBindBankCard(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var bankCard1 = $("#bankCard1").val();
	var bankCard2 = $("#bankCard2").val();
	var bankCard3 = $("#bankCard3").val();
	var bankCard4 = $("#bankCard4").val();
	var bankCard5 = $("#bankCard5").val();
	if(!bankCard1){
		lh.alert('请绑定至少一张提现银行卡');SAVING_FLAG = false;return;
	}
	var obj = {};
	obj.bankCard1 = bankCard1;
	obj.bankCard2 = bankCard2;
	obj.bankCard3 = bankCard3;
	obj.bankCard4 = bankCard4;
	obj.bankCard5 = bankCard5;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/bindBankUserCard',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				var url = '/user';
				var r = $("#r").val();
				if(r) url += "?r="+r;
				window.location.href = url;
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}