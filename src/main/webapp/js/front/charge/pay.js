SAVING_FLAG = false;
PAY_FROM_URL = null;
if(!lh.param)lh.param = {};
$(function(){
	//initWeiXin();
	initWxSDK(['chooseWXPay'], jumpToUserCenter);
	/*var noPhone = $("#noPhone").val();
	if(noPhone){
		frontBaseConfirm("您还未绑定手机号码，是否前往绑定手机号码","jumpToBindUserPhone()",null);
	}
	var noPayPassword = $("#noPayPassword").val();
	if(noPayPassword){
		frontBaseConfirm("您还未设置支付密码，是否前往设置支付密码","jumpToPayPasswordFind()",null);
	}*/
	initParam();
	checkMoney();
	
});

function initParam(){
	lh.param.pay_money = localStorage.getItem("pay_money");
	lh.param.pay_payType = localStorage.getItem("pay_payType");
	lh.param.pay_fromUrl = localStorage.getItem("pay_fromUrl");
	lh.param.orderGoodsId = localStorage.getItem("orderGoodsId");
	localStorage.removeItem("pay_money");
	localStorage.removeItem("pay_payType");
	localStorage.removeItem("pay_fromUrl");
	localStorage.removeItem("orderGoodsId");
}

function doWholeParamJump(){
	if(lh.param.pay_fromUrl){
		window.location.href = lh.param.pay_fromUrl;
	}else{
		jumpToUserCenter();
	}
}

function jumpToUserCenter(){
	lh.jumpR('/user');
}

function checkMoney(){
	var money = lh.param.pay_money;
	var payType = lh.param.pay_payType;
	var flag = true;
	if(!payType){
		flag = false;
		var tip = '支付类型不能为空，无法完成支付';
	}
	if(payType == 2){
		var orderGoodsId = lh.param.orderGoodsId;
		doPay(0, payType);
	}else{
		if(!money){
			flag = false;
			var tip = '支付金额不能为空，无法完成支付';
		}else{
			money = new Number(money);
			if(!money || money <= 0){
				flag = false;
				var tip = '支付金额不正确，无法完成支付';
			}else{
				doPay(money, payType);
			}
		}
	}
	if(!flag){
		lh.alert(tip, doWholeParamJump);
	}
}

function doPay(money, payType){
	if(SAVING_FLAG)return;
	if(payType != 2){
		if(money<=0){
			lh.alert('支付金额不正确', doWholeParamJump);return;
		}else if(money>=100000){
			lh.alert('支付金额不能超过十万元', doWholeParamJump);return;
		}
	}
	//lh.loading('正在调用微信支付，请稍候...');
	lh.loading('加载中...');
	$("#chargeToast").show();
	SAVING_FLAG = true;
	$.post('/fund/charge/doPay', {money : money,payType : payType}, function(rsp){//ajax调用微信统一接口获取prepayId
		lh.hideLoading();
		SAVING_FLAG = false;
		if(rsp.status == 'success'){
			$("#chargeToast").hide();
			var obj = rsp;
            if(parseInt(obj.agent)<5){  
                lh.alert("您的微信版本低于5.0无法使用微信支付", doWholeParamJump);return;
            }  
            var appId = obj.appId;
		    var timeStamp = obj.timeStamp;
		    var nonceStr = obj.nonceStr;
		    var pk = obj.packageValue;
		    var signType = obj.signType;
		    var paySign = obj.paySign;
		    
		    var chargeId = rsp.chargeId;
		    if(!chargeId){
		    	lh.alert('生成支付信息失败，请重新发起支付', doWholeParamJump);return;
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
	                //frontBaseMaskClose();return;
	                lh.hideLoading();return;
	            },
	            cancel: function (res) {// 支付取消后的回调函数
	            	lh.alert('您取消了本次支付', doWholeParamJump);return;
	            },
	            success: function (res) { // 支付成功后的回调函数
	            	localStorage.setItem("pay_chargeId", chargeId);
	            	lh.alert('本次支付成功', doWholeParamJump);return;
			    },
			    fail: function (res) { // 支付失败后的回调函数
			    	lh.alert('本次支付失败', doWholeParamJump);return;
			    }
	        });
		}else{
			//frontBaseMaskClose();
			lh.hideLoading();
			SAVING_FLAG = false;
			lh.alert(rsp.msg, doWholeParamJump);return;
		}
	});
	
}