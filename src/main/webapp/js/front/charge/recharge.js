SAVING_FLAG = false;
$(function(){
	//initWeiXin();
	initWxSDK(['chooseWXPay'], 'jumpToUserCenter');
	var noPhone = $("#noPhone").val();
	if(noPhone){
		frontBaseConfirm("您还未绑定手机号码，是否前往绑定手机号码","jumpToBindUserPhone()",null);
	}
	var noPayPassword = $("#noPayPassword").val();
	if(noPayPassword){
		frontBaseConfirm("您还未设置支付密码，是否前往设置支付密码","jumpToPayPasswordFind()",null);
	}
});

function jumpToUserCenter(){
	var r = $('#r').val();
	if(r){
		location.href = '/user?r='+r;
	}else{
		location.href = '/user';
	}
}

function checkMoney(flag){
	var applyMoney = $("#applyMoney").val();
	if(flag == 2){
		if(!$.isNumeric(applyMoney) || applyMoney < 0){
			applyMoney = 0;
		}
		applyMoney = parseDouble(applyMoney);
	}else{
		if(!$.isNumeric(applyMoney) || applyMoney < 0){
			applyMoney = '';
		}
	}
	$("#applyMoney").val(applyMoney);
	$("#payTotal").text(applyMoney);
}

function inputChargeMoney(){
	showCallActionSheet({opt:'charge'});//call面板
}

function doCharge(){
	var price = $('#numView').text();
	$('#applyMoney').val(price);
	$('#payTotal').val(price);
	doPay();
	hideCallActionSheet();
}

function doPay(){
	if(SAVING_FLAG)return;
	var applyMoney = $("#applyMoney").val();
	applyMoney = parseInt(applyMoney);
	if(applyMoney<=0){
		lh.alert('充值金额不正确');return;
	}else if(applyMoney>=100000){
		lh.alert('充值金额不能超过十万元');return;
	}
	lh.loading('正在调用微信支付，请稍候...');
	SAVING_FLAG = true;
	$("#chargeToast").show();
	$.post('/fund/charge/doRecharge',{money:applyMoney},function(rsp){//ajax调用微信统一接口获取prepayId
		 SAVING_FLAG = false;
		if(rsp.status == 'success'){
			$("#chargeToast").hide();
			var obj = rsp;
            if(parseInt(obj.agent)<5){  
                lh.alert("您的微信版本低于5.0无法使用微信支付");return;  
            }  
            var appId = obj.appId;
		    var timeStamp = obj.timeStamp;
		    var nonceStr = obj.nonceStr;
		    var pk = obj.packageValue;
		    var signType = obj.signType;
		    var paySign = obj.paySign;
		    
		    var chargeId = rsp.chargeId;
		    if(!chargeId){
		    	lh.alert('生成充值信息失败，请重新发起充值', "jumpToUserCenter");return;
		    }
		    //lh.alert('appId-'+appId+'-timeStamp-'+timeStamp+'-nonceStr-'+nonceStr+'-pk-'+pk+'-signType-'+signType+'-paySign-'+paySign);
		    //success：接口调用成功时执行的回调函数。
			//fail：接口调用失败时执行的回调函数。
			//complete：接口调用完成时执行的回调函数，无论成功或失败都会执行。
			//cancel：用户点击取消时的回调函数，仅部分有用户取消操作的api才会用到。
			//trigger: 监听Menu中的按钮点击时触发的方法，该方法仅支持Menu中的相关接口。
		    wx.chooseWXPay({
	            'timestamp': timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
	            'nonceStr': nonceStr, // 支付签名随机串，不长于 32 位
	            'package': pk, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	            'signType': signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	            'paySign': paySign, // 支付签名
	            complete: function (res) {// 支付完成后的回调函数（成功和失败都会执行）
					//lh.alert('complete');
	                frontBaseMaskClose();
	            },
	            cancel: function (res) {// 支付取消后的回调函数
	            	//for(var i in res){lh.alert(i+'__cancel_'+res[i]);}
					//lh.alert('cancel');
	            	lh.alert('您取消了本次充值', 'jumpToUserCenter');
	            },
	            success: function (res) { // 支付成功后的回调函数
	            	//for(var i in res){lh.alert(i+'__success_'+res[i]);}
					//lh.alert('success');
	            	lh.alert('本次充值成功', 'jumpToUserCenter');
			    },
			    fail: function (res) { // 支付失败后的回调函数
	            	//for(var i in res){lh.alert(i+'_fail__'+res[i]);}
					//lh.alert('fail');
			    	lh.alert('本次充值失败', 'jumpToUserCenter');
			    }
	        });
            
		}else{
			frontBaseMaskClose();
			SAVING_FLAG = false;
			lh.alert(rsp.msg);
		}
	});
	
}