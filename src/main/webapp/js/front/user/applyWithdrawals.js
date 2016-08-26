SAVING_FLAG = false;
$(function(){
	var isRealAuth = $("#isRealAuth").val();
	if(isRealAuth != 2){
		frontBaseConfirm('申请提现,需要实名认证.您目前还未实名认证,确定将跳转到实名认证页面.取消将返回上一页面','jumpToRealName()','goBack()');
	}else{
		var bankCard = $("#bankCard").val();
		if(!bankCard){
			frontBaseConfirm('请先绑定银行卡,确定将跳转到绑定银行卡页面.取消将返回上一页面','jumpToBindBankCard()','goBack()');
		}
	}
	/*$("#bankCard").select2({
	    tags: "true",
	    placeholder: "",
	    allowClear: true
	 });*/
	$("#bankCard").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
});

function goBack(){
	var url = "/user";
	var r = $("#r").val();
	if(r) url += "?r="+r;
	window.location.href = url;
}

function jumpToRealName(){
	var url = '/realName';
	var r = $("#r").val();
	if(r) url += "?r="+r;
	window.location.href = url;
}

function jumpToBindBankCard(){
	var url = '/bindBankCard';
	var r = $("#r").val();
	if(r) url += "?r="+r;
	window.location.href = url;
}

function sumMoney(){
	var applyMoney = $("#applyMoney").val();
	var fee = $("#fee").val();
	var applyRealMoney = applyMoney - (applyMoney*fee);
	$("#applyRealMoney").val(applyRealMoney.toFixed(2));
}

function addAccountLog(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var bankCard = $("#bankCard").val();
	var applyMoney = $("#applyMoney").val();
	var fee = $("#fee").val();
	var applyRealMoney = $("#applyRealMoney").val();
	var payPassword = $("#payPassword").val();
	if(!bankCard){
		lh.alert('请选择需要提现的银行卡');
		SAVING_FLAG = false;
		return;
	}
	if(!applyMoney){
		lh.alert('请输入需要提现的金额');
		SAVING_FLAG = false;
		return;
	}
	if(!checkNumber(applyMoney, 'positive', '提现金额')){
		SAVING_FLAG = false;return;
	}
	var obj = {payPassword:payPassword,applyMoney:applyMoney,applyRealMoney:applyRealMoney,fee:fee};
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateWithdraw',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				frontBaseConfirm('您的提现请求,已提交.','jumpToUserCenter()');
			}else{
				if(rsp.noPayPassword == 'noPayPassword'){
					lh.alert(rsp.msg,"jumpToPayPasswordFind()");
				}else if(rsp.noPhone == 'noPhone'){
					lh.alert(rsp.msg,"jumpToBindUserPhone()");
				}else{
					lh.alert(rsp.msg);
				}
			}
		}
	},'json');
}

/*function jumpToPayPasswordFind(){
	var r = $("#r").val();
	var url = '/payPasswordFind';
	if(r){url += "?r="+r}
	window.location.href = url;
}*/

function jumpToUserCenter(){
	var r = $("#r").val();
	var url = '/user';
	if(r){url += "?r="+r}
	window.location.href = url;
}

