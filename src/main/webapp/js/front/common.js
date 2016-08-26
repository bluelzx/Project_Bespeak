SEARCH_TYPE = 1;//搜索类型，1：藏品，2：店铺
SEARCH_GOODS_TYPE = null;//搜索藏品类型，129：杂珍...
if(!lh.upload)lh.upload = {};
var navauto = function () {
    $('.new_list,.new_list_down').slideToggle(300);
}
var alertauto = function () {
    $('.zhezhao,.pfreturnforum').slideToggle(0);
}

function initMenu(){
	jQuery(".menu").slide({ type: "menu", titCell: ".nli", targetCell: ".sub", effect: "slideDown", delayTime: 300, triggerTime: 0, returnDefault: true });
	
	$('#navSlide b').click(function () {
        var sub = $(this).parents('li.nli').children('.sub');
        if ($(this).hasClass('hover')) {
            $(this).removeClass('hover');
            sub.slideUp();
        } else {
            $(this).addClass('hover');
            sub.slideDown();
        }
    });
}

function initTopSearch(){
	if(localStorage){
		var searchType = localStorage.getItem("searchType");
		if(searchType == 2){
			SEARCH_TYPE = 2;
			$("#searchLabel,#searchLabel2").attr('role','2').text('商铺');
			$('#ss1_input,#ss1_input2').attr('placeholder', '输入商铺名称');
		}else{
			SEARCH_TYPE = 1;
			$("#searchLabel,#searchLabel2").attr('role','1').text('藏品');
			$('#ss1_input,#ss1_input2').attr('placeholder', '输入藏品名称');
		}
	}
	$('#ss1,#ss2').click(function () {
		var $searchLabelDom = $("#searchLabel");
		var $searchLabelDom2 = $("#searchLabel2");
        if ($searchLabelDom.attr('role') == 1) {
        	SEARCH_TYPE = 2;
            $searchLabelDom.attr('role','2').text('商铺');
            $searchLabelDom2.attr('role','2').text('商铺');
            $('#ss1_input,#ss2_input').attr('placeholder', '输入商铺名称');
            if(localStorage)localStorage.setItem("searchType","2");
        } else {
        	SEARCH_TYPE = 1;
            $searchLabelDom.attr('role','1').text('藏品');
            $searchLabelDom2.attr('role','1').text('藏品');
            $('#ss1_input,#ss2_input').attr('placeholder', '输入藏品名称');
            if(localStorage)localStorage.setItem("searchType","1");
        }
    });
}

function initBanner(){
	var bannerLi = $('#slideBox li');
	if(bannerLi.length <= 0)return;
    TouchSlide({ 
		slideCell:"#slideBox",
		titCell:".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
		mainCell:".bd ul", 
		effect:"leftLoop", 
		autoPage:true,//自动分页
		autoPlay:true //自动播放
	});
	
	var btn = $('.slideBox').width() - $(".slideBox .hd").width();
    var btnl = btn / 2;
    $(".slideBox .hd").css("right", btnl + "px");
    $('#banner').show();
}

function switchSldSrhType(typeId){
	$('#slideSearchTypes li').removeClass('on');
	if(typeId){
		$('#sld_type_'+typeId).addClass('on');
		SEARCH_GOODS_TYPE = typeId;
	}else{
		$('#sld_type_000').addClass('on');
		SEARCH_GOODS_TYPE = null;
	}
}

function search(loc){
	if(!loc)loc = 1;
	var searchName = null;
	if(loc == 1){//页面顶部搜索栏
		searchName = $('#ss1_input').val();
	}else if(loc == 2){//页面侧面滑动显示搜索栏
		searchName = $('#ss2_input').val();
	}
	var url = '';
	if(SEARCH_TYPE == 1){
		url = '/goods?searchType='+SEARCH_TYPE;
		if(SEARCH_GOODS_TYPE)url += '&searchGoodsType='+SEARCH_GOODS_TYPE;//在搜索藏品时可以指定藏品各类
	}else if(SEARCH_TYPE == 2){
		url = '/market?searchType='+SEARCH_TYPE;
	}
	if(searchName)url += '&searchName='+searchName;
	//promoter: r
	lh.jumpR(url);
}

function showMy(){
	var $my = $("#my_nav_div");
	//var $quan = $("#quanpingMask");
	if($my.hasClass('frontHide')){
		$my.removeClass('frontHide');
		//$quan.show();
		switchBottomMenu('menu_my');
	}else{
		$my.addClass('frontHide');
		//$quan.hide();
		switchBottomMenu();
	}
}

/** 切换底部菜单的选中状态 */
function switchBottomMenu(domId){
	$("#menu_index,#menu_shake,#menu_market,#menu_my").removeClass('hover');
	if(domId)$("#"+domId).addClass('hover');
}

function frontLoginCheck(rsp, successFun, failureFun){
	if(!rsp.toLogin && !rsp.loginStatus)return;
	
	loginUserSilent(rsp);return;//TODO 直接重定向
	
	var mobile = null;
	var password = null;
	if(localStorage){
		mobile = localStorage.getItem("mobile");
		password = localStorage.getItem("password");
	}
	if(!mobile || !password){
		lh.loading('会话过期，正跳转到登陆页面...');
		setTimeout(function(){
			loginUserSilent(rsp);
			//lh.jumpR('/login');return;
		},1000);
		return;
	}
	$.post('/doMobileLogin',{phone:mobile, password:password},function(rsp){
		if(rsp.success){
			if(localStorage){
				localStorage.setItem("mobile",mobile);
				localStorage.setItem("password",password);
			}
			if(successFun){//自定义回调方法
				successFun();
			}else{
				lh.loading('会话过期，正在重新登陆...');
				setTimeout(function(){
					location.reload();
				},2000);
			}//刷新页面
		}else{
			if(failureFun){failureFun();}else{lh.alert(rsp.msg, '提示');}
			loginUserSilent(rsp);
		}
	},'json');
	
}

function closeImgView(){
	$('#pre_closepic').hide();
	$('#imagePreview').css('height','0px');
	$('#imagePreview').remove();
}
function showImgView(picPathAry, index){
	//if(!MY_SWIPER)return;
	if( typeof picPathAry == 'string'){
		picPathAry = picPathAry.split(',');
	}
	
	var dom =
	'<div id="imagePreview" onclick="closeImgView();" class="yipu-device" style="height:100%;width:100%;position: absolute;top: 1px;left: 1px;background: rgba(0,0,0,.9);">'+
		'<div class="pre_title"><p class="pre_subhead"></p><p class="pre_subhead"></p></div>'+
		'<span id="pre_closepic" style="color: #6CB4E5; position: absolute; top: 10px; z-index: 9999; right: 10px; cursor: pointer;display: none;">关闭</span>'+
		'<div id="pre_container" class="swiper-container" style="height:100%;width:100%;">'+
			'<div class="swiper-wrapper">';
	
		for(var i in picPathAry){
			dom += 	'<div class="swiper-slide yipu-middle" style="text-align: center;"><img src="'+picPathAry[i]+'"/></div>';
		};
		
	dom +=	'</div>'+
		    '<div class="swiper-pagination"></div>'+
    		'<div class="swiper-scrollbar"></div>'+
		'</div>'+
	'</div>';
	
	$('body').append(dom);
	
	MY_SWIPER = new Swiper ('.swiper-container', {
	    direction: 'horizontal',
	    loop: true,
	    pagination: '.swiper-pagination', // 如果需要分页器
	    nextButton: '.swiper-button-next',// 如果需要前进后退按钮
	    prevButton: '.swiper-button-prev',
	    scrollbar: '.swiper-scrollbar' // 如果需要滚动条
	  });
	
	if(!index)index = 0;
	//MY_SWIPER.slideTo(index+1, 1, false);//切换到第一个slide，速度为1秒
	MY_SWIPER.slideTo(1, 1, false);
	$('#imagePreview').css('height','100%');
	$('#pre_closepic').show();
	
}

function goTop(){
	document.documentElement.scrollTop = document.body.scrollTop = 0;
}

function showQRCode(opt, url, picPath, param){
	if(opt == 'hide'){
		$("#qrcodeCanvas").empty();
		$("#yp_menu,#urlText").hide();
		return;
	}
	var urlStr = window.location.href;
	if(url){
		urlStr = 'http://weipaike.net'+url;
	}
	if(param)urlStr += param;
	
	//var urlStr = 'http://weipaike.net'+url || window.location.href;
	var picPathStr = picPath || '';
    $("#qrcodeCanvas").qrcode({
         render : "canvas",    //设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
         text : urlStr,    //扫描二维码后显示的内容,可以直接填一个网址，扫描二维码后自动跳向该链接
         width : "200",               //二维码的宽度
         height : "200",              //二维码的高度
         background : "#ffffff",       //二维码的后景色
         foreground : "#000000",        //二维码的前景色
         src: picPathStr             //二维码中间的图片
     });
     //var boxWidth = $("#yp_menus").css('width');
     var leftMargin = (window.innerWidth - 260)/2;
     $("#yp_menus").css('left', leftMargin);
     //$("#yp_menu").fadeIn();
     $("#yp_menu").show();
     urlStr += '（长按复制）';
     $("#urlText").text(urlStr).show();
     $("#urlText").attr('data-clipboard-text', urlStr);
}

function loadNotice(timeGap, func){
	//$.post('/getMyNoticeList',{onlyCount:1,notRead:1},function(rsp){
	$.ajax({
		type: "POST",
	    url: "/getMyNoticeList",
	    data: {onlyCount:1,notRead:1},
	    success : function(rsp){
		   onLoadNoticeSuccess(rsp, func);
	    },
	    error : function(XmlHttpRequest,textStatus, errorThrown,s) { 
			//console.log('删除失败222'+XmlHttpRequest.responseText); 
			return false;
	    }
    });
}

function onLoadNoticeSuccess(rsp, func){
	var count = rsp.total;
	var chatCount = rsp.chatCount;
	var orderInfoCount = rsp.orderInfoCount;
	var goodsOfferCount = rsp.goodsOfferCount;
	if(!count || count < 0)count = 0;
	if(!chatCount || chatCount < 0)chatCount = 0;
	if(!orderInfoCount || orderInfoCount < 0)orderInfoCount = 0;
	if(!goodsOfferCount || goodsOfferCount < 0)goodsOfferCount = 0;
	if(localStorage){
		localStorage.setItem("noticeCount",count+chatCount+orderInfoCount+goodsOfferCount);
	}
	if(func){
		func(count);return;//执行自定义函数
	}
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
	//count = 0;
	if(count > 0 || chatCount > 0 || orderInfoCount > 0 || goodsOfferCount  > 0){
		if(count > 0 || chatCount > 0){
			var audio = document.getElementById("msgAudio");//消息提示音
			//if(audio)audio.play();
		}
		$('#noticeCount').text(count+chatCount+orderInfoCount+goodsOfferCount).show();
		if(count+chatCount > 0){
			$('#notice,#menuNotice').text(count+chatCount).show();
		}
		if(orderInfoCount+goodsOfferCount > 0){
			$('#orderInfo').text(orderInfoCount+goodsOfferCount).show();
		}
	}else{
		$('#noticeCount').text(0).hide();
	}
}

function loadNoticeInterval(timeGap, func){
	/*$.ajaxSetup({
		//error:function(data, textStatus) {
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest+textStatus+errorThrown);
			if(XMLHttpRequest.readyState === 0 && lh.domain.noticeTimer){
				clearInterval(lh.domain.noticeTimer);
			}
		}
	});*/
	loadNotice();//加载完页面时先加载一次再开始计时
	if(!timeGap)timeGap = 120000;//默认120秒:120000
	 lh.domain.noticeTimer = setInterval(function () {
        loadNotice(timeGap, func);
    }, timeGap);//默认120秒
}

function initWxSDK(jsApiList, errorFun, readyFun){
	try {
		if(_.isNull(wx)){ lh.alert('请在微信客户端访问此页面', '提示');return; }
	} catch (e) {
		lh.alert('请在微信客户端访问此页面', '提示');return;
	}
	var appId = $('#appId').val();
    var timeStamp2 = $('#timeStamp2').val();//timeStamp
    var nonceStr2 = $('#nonceStr2').val();
    var signature = $('#signature').val();
    if(!appId || !timeStamp2 || !nonceStr2 || !signature || !jsApiList){
    	 lh.alert('微信组件始化失败（缺少必须的参数）', '提示', errorFun);
    }
    //lh.alert('提示', appId+'___'+timeStamp2+'___'+nonceStr2+'___'+signature);
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印
        appId: appId, // 必填，公众号的唯一标识
        timestamp:timeStamp2 , // 必填，生成签名的时间戳
        nonceStr: nonceStr2, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: jsApiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
    wx.ready(function(){
    	if(readyFun)readyFun();
    });
    /*
    wx.ready(function(){
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中
        wx.chooseWXPay({
            timestamp: timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
            nonceStr: nonceStr, // 支付签名随机串，不长于 32 位
            'package': pk, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
            signType: signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
            paySign: paySign, // 支付签名
            complete: function (res) {// 支付成功后的回调函数
                location.href = '/fund/rechargeSuccess';
            }
        });
    });
    wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名
	});
    */
    //wx.ready(function(){console.log('wx ready')});
    wx.error(function(res){
	    lh.alert('微信组件始化失败', '提示', errorFun);
	});
}

function bottomJumpUrl(url){
	var r = $("#r").val();
	if(r) url += "?r="+r;
	window.location.href = url;
}

function redirectToCharge(){
	var noPhone = $("#noPhone").val();
	if(noPhone){
		lh.confirm("您还未绑定手机号码，是否前往绑定手机号码","jumpToBindUserPhone()",null);
		return;
	}
	var noPayPassword = $("#noPayPassword").val();
	if(noPayPassword){
		lh.confirm("您还未设置支付密码，是否前往设置支付密码","jumpToPayPasswordFind()",null);
		return;
	}
	var redirect_uri = encodeURIComponent('http://weipaike.net/fund/charge/recharge');		
	var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc191ed6c5d1f462e' +
			'&redirect_uri='+redirect_uri+'&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
	location.href = url;
}

function jumpToPaywayPage(param){
	if(param.payType){
		lh.alert('提示', "支付类型为空，无法完成支付");
	}
	if(param.payType != 2){
		var money = param.money;
		if(!money){
			lh.alert('提示', "请输入支付金额");
			return;
		}
		money = parseInt(money);
		if(!money || money <= 0){
			lh.alert('提示', "您输入的金额不正确");
			return;
		}
		param.money = money;
		localStorage.setItem("pay_money", param.money);
	}
	localStorage.setItem("pay_fromUrl", param.fromUrl || location.href);
	localStorage.setItem("pay_payType", param.payType);
	if(param.amId)localStorage.setItem("pay_amId", param.amId);
	if(param.orderGoodsId)localStorage.setItem("pay_orderGoodsId", param.orderGoodsId);
	var url = 'payway';
	if(param.orderGoodsId){
		url += '?orderGoodsId='+param.orderGoodsId;
	}else if(param.amId){
		url += '?amId='+param.amId;
	}
	lh.jumpR(url);
}

function redirectToPay(param){
	//money, payType, fromUrl
	if(!param)return;
	var noPhone = $("#noPhone").val();
	if(noPhone){
		$('#baseConfirmDialog').remove();
		$confirmDialog = [];
		lh.confirm("您还未绑定手机号码，是否前往绑定手机号码","jumpToBindUserPhone()",null);
		return;
	}
	/*
	var noPayPassword = $("#noPayPassword").val();
	if(noPayPassword){
		$('#baseConfirmDialog').remove();
		$confirmDialog = [];
		lh.confirm("您还未设置支付密码，是否前往设置支付密码","jumpToPayPasswordFind()",null);
		return;
	}*/
	if(!param.payType){
		lh.alert('提示', "支付类型为空，无法完成支付");
	}
	if(param.payType != 2){
		var money = param.money;
		if(!money){
			lh.alert('提示', "请输入支付金额");
			return;
		}
		money = parseInt(money);
		if(!money || money <= 0){
			lh.alert('提示', "您输入的金额不正确");
			return;
		}
		param.money = money;
		localStorage.setItem("pay_money", param.money);
	}
	localStorage.setItem("pay_fromUrl", param.fromUrl || location.href);
	localStorage.setItem("pay_payType", param.payType);
	if(param.orderGoodsId)localStorage.setItem("orderGoodsId", param.orderGoodsId);
	
	var redirect_uri = encodeURIComponent('http://weipaike.net/fund/charge/pay');		
	var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc191ed6c5d1f462e' +
			'&redirect_uri='+redirect_uri+'&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';
	
	//TODO 方便测试，直接返回支付成功，返回fromUrl页面 TODO FIXME
	//location.href = param.fromUrl || location.href;return;
	
	location.href = url;
	//location.href = '/fund/charge/pay';
}

function jumpToBonus(){
	lh.jumpR('/bonus?from'+location.href);
}

function scanPic(picPathAry, currentIndx){
	if(!picPathAry || picPathAry.length<=0)return;
	if(!currentIndx || currentIndx <= 0)currentIndx = 0;
	wx.previewImage({
	    current: picPathAry[currentIndx], // 当前显示图片的http链接
	    urls: picPathAry // 需要预览的图片http链接列表
	});
}

function choosePic(param){
	if(!param)param = {showPic : false};
	if(param == 'showPic')param = {showPic : true};
	if(param.showPic !== false)param.showPic = true;
	if(!param.count)param.count = 9;
	/*var localIds = '/file/default/1452827347322__20160115001640550_thumb.jpg';
	var paths = lh.upload.pathStr;
	paths += ','+localIds;
	lh.upload.pathStr = paths;//保存文件路径到变量中
	return localIds;*/
	wx.chooseImage({
		count: param.count, // 默认9
	    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有 ['original', 'compressed']
	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	    success: function (res) {
	    	//lh.loading('正在上传图片，请稍候...');
	        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        //lh.alert('提示', localIds);
	        //console.log(localIds);
	        syncUploadPic(localIds, param);
	        //lh.upload.uploader.addFile(localIds, localIds)
	        
	    }
	});
}

/*function wxPicUpload(){
	var obj = {filePaths:lh.upload.pathStr,shopPrice:100}
	lh.alert('提示', "filePaths:"+lh.upload.pathStr);
	$.post('/addOrUpdateGoods',obj,function(rsp){
		SAVING_FLAG = false;
		lh.hideLoading();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.confirm('您的藏品添加成功!','reload()');
				//window.location.reload();	
			}else{
				lh.alert(rsp.msg, '提示');
			}
		}
	},'json')
}*/

var syncUploadPic = function(localIds, param){
	//lh.loading('正在上传图片，请稍候...');
    var localId = localIds.pop();
    //if(showPic && showImg){showImg(localId);}
    wx.uploadImage({
        localId: localId,
        isShowProgressTips: 1,// 默认为1，显示进度提示
        success: function (res) {
            var serverId = res.serverId; // 返回图片的服务器端ID
        	var paths = lh.upload.pathStr;
        	paths += ','+serverId;
        	if(param.showPic && showImg){showImg(localId, serverId, param);}
        	//fileDom.val(paths);//保存文件路径到DOM中
        	lh.upload.pathStr = paths;//保存文件路径到变量中
            //其他对serverId做处理的代码
            if(localIds.length > 0){
            	syncUploadPic(localIds, param);
            }else{
            	lh.hideLoading();
            }
        }
    });
}

function getIdFromDate(){
	var date = new Date();
	return date.getTime()+'_'+ parseInt(Math.random()*10000);
}

function jumpToLastPage(){
	window.history.go(-1);
}

function checkBail(){
	var bail = $("#bail").val();
	var creditMoney = $("#creditMoney").val();
	var creditMoneyLack = $("#creditMoneyLack").val();
	if(creditMoneyLack){
		bail = parseInt(bail);
		creditMoney = parseInt(creditMoney);
		if(!bail)bail = 0;
		if(!creditMoney)creditMoney = 0;
		var gap = bail - creditMoney;
		if(gap && gap>0){
			var tip = '本次拍卖需要交纳的保证金为'+bail+'元，您当前交纳的信誉保证金为'+creditMoney+'元，还差'+gap+'元，是否增加交纳保证金？';
			lh.confirm(tip, 'redirectToPay({money:'+gap+',payType:1})');
			return false;
		}
	}
	return true;
}

function checkAuctionMicroBail(bail, creditMoney, url, auctionId){
	bail = parseInt(bail);
	creditMoney = parseInt(creditMoney);
	if(!bail)bail = 0;
	if(!creditMoney)creditMoney = 0;
	var gap = bail - creditMoney;
	if(gap && gap>0){//money, payType, fromUrl
		var tip = '本次拍卖需要交纳的保证金为'+bail+'元，您当前交纳的信誉保证金为'+creditMoney+'元，还差'+gap+'元，是否增加交纳保证金？';
		lh.confirm(tip, 'jumpToPaywayPage({money:'+gap+',payType:1,fromUrl:\''+url+'\',amId:'+auctionId+'})');
		return false;
	}
	return true;
}

/**显示分享*/
function showShare(){
	lh.mask(hideShare);
	$('.zhezhao,#share').show();
	$('#shareMask').show();
	
}

/**隐藏分享*/
function hideShare(){
	lh.hideMask();
	$('.zhezhao,#share').hide();
	$('#shareMask').hide();
}

function jumpToFocus(){
	window.location.href = "http://mp.weixin.qq.com/s?__biz=MzA5MzE0NDI1MA==&mid=402786205&idx=1&sn=38426e095ea4c4168e8e9dc05ad07cd0#rd";
}
function calcelJumpToFocus(){
	localStorage.removeItem("fromUrl");
}

function checkLoginStatus(rsp){
	//var status = $("#loginStatus").val();
	//if(!status)return;
	if(rsp){
		var loginStatus = rsp.loginStatus;
		if(!loginStatus)return;
	}else{
		if(!lh || !lh.param)return;
		var param = lh.param;
		var loginStatus = param.loginStatus;
	}
	if(loginStatus == 'doLogin'){
		//lh.alert('提示', 'loginUserSilent');
		loginUserSilent();
	}else if(loginStatus == 'bindPhoneAndUpdatePswd'){
		//lh.alert('提示', 'bindPhoneAndUpdatePswd');
		//TODO 暂时不绑定手机
		//lh.confirm('您还没有绑定手机和更改默认密码，先去绑定手机吧？', '提示', jumpToBindUserPhone);
	}else if(loginStatus == 'jumpToFocus'){
		//lh.alert('提示', 'jumpToFocus');
		lh.confirm('您还未关注微拍客，是否关注？', '提示', jumpToFocus, calcelJumpToFocus);
	}else if(loginStatus == 'wxRedirect'){
		loginUserSilent();
	}
	
	
	/*else{//loginId
		loginUserSilent(status);
	}*/
}

function loginUserSilent(wxOpenId, fromUrl){
	fromUrl = fromUrl || window.location.href;
//	var redirectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc191ed6c5d1f462e&redirect_uri=http%3A%2F%2Fweipaike.net%2F&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect";
//	localStorage.setItem("fromUrl", fromUrl);
//	window.location.href = redirectUrl;
//	return;
	/*if(!localStorage){
	}*/
	var mobile = localStorage.getItem("mobile");
	var password = localStorage.getItem("password");
	/*if(!mobile){
		mobile = wxOpenId;
		localStorage.setItem("mobile", mobile);
	}
	if(!mobile || !password){
	*/
	if(fromUrl.indexOf('/login') < 0){
		localStorage.setItem("fromUrl", fromUrl);
		window.location.href = redirectUrl;
		return;
	}else{
		if(!mobile || !password)return;
		$.post('/doMobileLogin', {phone:mobile,password:password}, function(rsp){
			if(rsp.status == 'success'){
				location.reload();
			}else{
				//lh.alert(rsp.msg, '提示');
				lh.jumpR('/login');
			}
		},'json');
	}
}

function buildOSSZoom(width, height, quality){//@@_OSS_IMG_@@   @120w_120h_4e_50Q  @100w_100h_4e_240-240-246bgc
	if(!quality)quality = '50Q';
	var suffix = '@'+width+'w_'+height+'h_4e_240-240-246bgc_'+quality; 
	return suffix;
}

function buildOSSZoomByPath(path, width, height, quality){//@@_OSS_IMG_@@   @120w_120h_4e_50Q  @100w_100h_4e_240-240-246bgc
	if(!quality)quality = '50Q';
	var suffix = '@'+width+'w_'+height+'h_4e_240-240-246bgc_'+quality; 
	return path+suffix;
}

$(function(){
	checkLoginStatus();
	loadNoticeInterval();//检查是否有未读通知
});
