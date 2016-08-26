var URL = '';
$(function(){	
	var noPhone = $("#noPhone").val();
	if(noPhone){
		frontBaseConfirm("您还未绑定手机号码，是否前往绑定手机号码","jumpToBindUserPhone()",null);
	}
	var noEditPassword = $("#noEditPassword").val();
	if(noEditPassword){
		frontBaseConfirm("您还未修改默认密码，是否前往修改默认密码","jumpToEditPasswd()",null);
	}
	var noPayPassword = $("#noPayPassword").val();
	if(noPayPassword){
		frontBaseConfirm("您还未设置支付密码，是否前往设置支付密码","jumpToPayPasswordFind()",null);
	}
	initPage();
	setTimeout(function(){
		loadNotice(null,renderNoticeCount);
	},1000);
	loadOrderInterval();
});


function loadOrderInterval(timeGap, func){
	getOrderCount();//加载完页面时先加载一次再开始计时
	if(!timeGap)timeGap = 120000;//默认120秒
	 var timer = setInterval(function () {
		 getOrderCount(timeGap, func);
    }, timeGap);//默认120秒
}


function initPage(){
	switchBottomMenu('menu_my');
	/*var redirect_uri = encodeURIComponent('http://weipaike.net/fund/recharge');		
	var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc191ed6c5d1f462e' +
			'&redirect_uri='+redirect_uri+'&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
	URL = url;
	$('#recharge').attr('href', URL);*/
	
}

function getOrderCount(timeGap, func){
	$.post('/getOrderCount',null,function(rsp){
		if(rsp){
			frontLoginCheck(rsp);
			if(rsp.status == 'success'){
				var returnGoodsCount = rsp.returnGoodsCount;
				var goodsOffersCount = rsp.goodsOffersCount;
				var shippingCount = rsp.shippingCount;
				var shipedCount = rsp.shipedCount;
				var commentingCount = rsp.commentingCount;
				var waitPayMoneyCount = rsp.waitPayMoneyCount;
				if(returnGoodsCount > 0){
					$("#returnGoodsCount").text(returnGoodsCount);
					$("#returnGoodsCount").show();
				}
				if(goodsOffersCount > 0){
					$("#goodsOffersCount").text(goodsOffersCount);
					$("#goodsOffersCount").show();
				}
				if(shippingCount > 0){
					$("#shippingCount").text(shippingCount);
					$("#shippingCount").show();
				}
				if(shipedCount > 0){
					$("#shipedCount").text(shipedCount);
					$("#shipedCount").show();
				}
				if(commentingCount > 0){
					$("#commentingCount").text(commentingCount);
					$("#commentingCount").show();
				}
				if(waitPayMoneyCount > 0){
					$("#waitPayMoneyCount").text(waitPayMoneyCount);
					$("#waitPayMoneyCount").show();
				}
			}
		}
	},'json');
}

function logout(){
	$.post('/logout',function(rsp){
		if(rsp.status == 'success'){
			localStorage.clear();
			window.location.href='/login';
		}else{
			lh.alert(rsp.msg);
		}
	});
}

function renderNoticeCount(count){
	$('#noticeCt_userCtr').text(count);
}

function jumpToRecharge(){
	redirectToCharge();
}
