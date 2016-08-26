
$(function(){
	initPage();
});

function initPage(){
	var param = lh.param;
	if(param && param.leftMoney){
		lh.param.leftMoney = param.leftMoney;
	}else{
		lh.param.leftMoney = undefined;
	}
}

function togglePayPass(){
	var payWay = $("input[name='checkbox1']:checked").val();  
	if(!payWay){
	}else if(payWay == 'payWay_pay'){
		$('#payPassDiv').hide();
	}else if(payWay == 'payWay_account'){
		$('#payPassDiv').show();
	}else{
		$('#payPassDiv').hide();
	}
}

function payCreditMoney(){
	var payWay = $("input[name='checkbox1']:checked").val();  
	if(!payWay){
		lh.alert('请选择支付方式');
	}else if(payWay == 'payWay_pay'){
		jumpToPay();
	}else if(payWay == 'payWay_account'){
		$('#payPassDiv').show();
		payCreditMoneyByAccount();
	}else{
		lh.alert('请选择正确的支付方式');
	}
}

function payCreditMoneyByAccount(){
	if(lh.param.moneyLack){
		lh.alert('您的可用余额不足，无法使用余额支付');return;
	}
	var payPass = $("#payPass").val();  
	if(!payPass){
		lh.alert('请输入您的支付密码');return;
	}
	if(!lh.param.leftMoney){
		lh.alert('无法确定您支付的金额，无法完成支付');return;
	}
	lh.loading('正在支付...');
	lh.post('front', '/fund/payCreditMoneyByAccount', {money:lh.param.leftMoney,payPass:payPass}, function(rsp){
		lh.hideLoading();
		if(rsp.success){
			lh.alert('您已成功支付诚信认证金', '提示', lh.back);
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function jumpToPay(){
	//money, payType, fromUrl
	if(!lh.param.leftMoney)return;
	var fromUrl = localStorage.getItem("fromUrl");
	if(!fromUrl){
		if(lh.param.fromUrl){
			fromUrl = lh.param.fromUrl;
		}else{
			fromUrl = '/ap/page/addOrUpdate?typeId=2';//添加专场
		}
	}
	var param = {money:lh.param.leftMoney, payType:1, fromUrl:fromUrl};
	localStorage.removeItem("fromUrl");
	redirectToPay(param);
}

function jumpToPaypass(){
	lh.post('front', '/updateUserFundPayPassword', {oldPassword:'', newPassword:'888888'}, function(rsp){
		if(rsp.success){
			lh.alert('此链接跳转到个人中心的设置支付密码页面，开发个人中心模块时更新此链接，已将支付密码设置为888888', '提示', lh.reload);
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
	//lh.jumpR('/user');
}

function jumpToCharge(){
	lh.alert('此链接跳转到个人中心的充值页面，开发个人中心模块时更新此链接');
	return;
	var param = {};
	redirectToPay();
}

