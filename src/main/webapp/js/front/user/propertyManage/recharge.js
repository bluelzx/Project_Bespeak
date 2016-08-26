$(function(){
//	initPage();
});

function loadRemainderRecharge(){
	var amount = $("#amount").val();
	if(!amount){lh.alert('请输入充值金额');return;}
	if(parseFloat(amount)>parseFloat(5000)){lh.alert('单笔线上充值金额5000元');return;}
	if(parseFloat(amount)<=parseFloat(0)){lh.alert('充值金额不能小于0');return;}
	
	lh.jumpR('/remainderRecharge');
}
