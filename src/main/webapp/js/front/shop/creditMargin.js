/**初始化数据*/
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;

function showCreditMarginWin(objectId){
	/*$("#creditMargin").empty();
	$("#shopId").val(objectId);
	$("#creditMarginShow").show();*/
	showCallActionSheet({opt:pay});
}

function closeCreditMarginWin(){
	$("#creditMarginShow").hide();
}

function getMoney(){
	var money = $("#numView").text();
	if(!money){
		lh.alert("请输入支付金额");
		return null;
	}
	money = parseInt(money);
	if(!money || money <= 0){
		lh.alert("您输入的金额不正确");
		return null;
	}
	return money;
}

function doDirectPay(){
	if(SAVING_FLAG)return;
	var money = getMoney();
	if(money)redirectToPay({money:money, payType:1});
}

function doRemainderPay(){
	if(SAVING_FLAG)return;
	var money = getMoney();
	if(money){
		$("#creditMargin").val(money);
	}
	hideCallActionSheet();
	$("#creditMarginShow").show();
	SAVING_FLAG = false;
}

function addCreditMargin(){
	if(SAVING_FLAG)return;
	var creditMargin = $("#creditMargin").val();
	var payPassword = $("#payPassword").val();
	var shopId = $("#shopId").val();
	creditMargin = parseInt(creditMargin);
	$("#creditMargin").val(creditMargin);
	if(!creditMargin){
		//lh.alert('请填写评论内容');
		$("#creditMargin").val('');
		$("#tip").text("请输入需要增加的保证金").show();
		return ;
	}
	if(creditMargin <= 0){
		//lh.alert('请填写评论内容');
		$("#tip").text("保证金的金额必须大于0").show();
		return ;
	}
	if(!payPassword){
		$("#tip").text("请输入支付密码").show();
		return ;
	}
	var obj = {money:creditMargin,payPassword:payPassword};
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	SAVING_FLAG = true;
	$.post('/increaseCreditMoney',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);
		if(rsp.status == 'success'){
			lh.alert('恭喜您成功增加信誉保证金',"reloadPage");
		}else{
			if(rsp.noPhone == 'noPhone'){
				lh.alert(rsp.msg,"jumpToBindUserPhone()");
			}else if(rsp.error_desc == 'payPass_null'){
				$("#creditMarginShow").hide();
				frontBaseConfirm("您还未设置支付密码，是否前往设置支付密码","jumpToPayPasswordFind()",null);
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}


