SAVING_FLAG = false;
COMEFROM = null;
TEMPOBJ = null;
$(function(){
	initUploadSimple();//调用完整方法
	init();
	//showActionSheet1();
	//bounsPromotionCheck();
	//commissionPromotionCheck();
});

function init(){
	
	var from = $("#from").val();
	if(from == 'as'){
		$("#auctionMicroBtn").show();
		$("#auctionTypeBtn").hide();
		var goodsId = $("#goodsId").val();
		if(!goodsId){
			lh.jumpR('/releaseGoods');return;//&from=as&goodsId=1
		}
	}else if(from == 'ap'){
		
	}else{
		$("#auctionMicroBtn").hide();
		$("#auctionTypeBtn").show();
	}
	
	$('#day,#bonusTypeId,#typeId').selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
	
	var date2 = new Date();
	date2.setHours(date2.getHours()+1);
	$("#startTime").mobiscroll().datetime({  
        theme: "android-ics light",  
        lang: "zh",  
        cancelText: '取消',  
        dateFormat: 'yy-mm-dd', //返回结果格式化为年月格式  
        dateOrder: 'yymmdd',
        timeFormat: 'HH:ii:ss', //返回结果格式化为年月格式  
        //maxDate: date,
        minDate: date2,
        stepSecond:10,
        headerText: function (valueText) { //自定义弹出框头部格式  
            return '请选择拍卖开始时间';  
        }  
    });  
}

function showActionSheet1(){
	var mask = $('#myMask1');
	var weuiActionsheet = $('#weui_actionsheet1');
	weuiActionsheet.addClass('weui_actionsheet_toggle');
	mask.show().addClass('weui_fade_toggle').click(function () {
		hideActionSheet(weuiActionsheet, mask);
	});
	$('#actionsheet_cancel1').click(function () {
		hideActionSheet(weuiActionsheet, mask);
	});
	weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');
	
}

function hideActionSheet(weuiActionsheet, mask) {
	weuiActionsheet.removeClass('weui_actionsheet_toggle');
	mask.removeClass('weui_fade_toggle');
	weuiActionsheet.on('transitionend', function () {
		mask.hide();
	}).on('webkitTransitionEnd', function () {
		mask.hide();
	})
}

function chooseAuctionType(id,first){
	if(first){
		showActionSheet1();
	}else{
		var mask = $('#myMask1');
		var weuiActionsheet = $('#weui_actionsheet1');
		hideActionSheet(weuiActionsheet, mask);
	}
	if(id == 1){
		$("#nonAuctionProfession,#start,#auctionNames").show();
		$("#auctionQuickType,#end").hide();
	}else if(id == 2){
		$("#nonAuctionProfession,#auctionQuickType").show();
		$("#start,#end,#auctionNames").hide();
	}else if(id == 3){
		$("#nonAuctionProfession,#end").show();
		$("#start,#auctionNames,#auctionQuickType").hide();
	}else if(id == 4){
		$("#nonAuctionProfession,#end,#start,#bailValue,#auctionNames,#auctionQuickType").hide();
	}
	COMEFROM = id;
}

function addAuctionMicro(){
	if(SAVING_FLAG)return;
	var day = $("#day").val();
	var priceBegin = $("#priceBegin").val();
	var increaseRangePrice = $("#increaseRangePrice").val();
	var buyoutPrice = $("#buyoutPrice").val();
	var bail = $("#bail").val();
	var goodsId = $("#goodsId").val();
	var isSevenReturn = $("#isSevenReturn")[0].checked;
	var postageFee = $("#postageFee")[0].checked;
	isSevenReturn = isSevenReturn ? 1 : 2;
	postageFee = postageFee ? 1 : 2;
	
	if(!day){day = 15;}
	priceBegin = parseInt(priceBegin);
	increaseRangePrice = parseInt(increaseRangePrice);
	buyoutPrice = parseInt(buyoutPrice);
	bail = parseInt(bail);
	if(!priceBegin){priceBegin = null}
	if(!increaseRangePrice){increaseRangePrice = null}
	if(!buyoutPrice){buyoutPrice = null}
	if(!bail){bail = null}
	
	var asg = {goodsId:goodsId, priceBegin:priceBegin, increaseRangePrice:increaseRangePrice, buyoutPrice:buyoutPrice,
				bail:bail, isSevenReturn:isSevenReturn, postageFee:postageFee, day:day};

	$.post('/addAsg', asg,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		if(rsp.status == 'success'){
			var asId = rsp.asId;
			lh.jumpR('/as?auctionId='+asId);
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
	
	
}

function addAuction(){
	if(SAVING_FLAG)return;
	var goodsId = $("#goodsId").val();
	if(COMEFROM == 1){
		var startTime = $("#startTime").val();
		var bail = $("#bail").val();
		var auctionName = $("#auctionName").val();
		if(!auctionName){
			lh.alert('请填写拍卖场次');
			SAVING_FLAG = false;
			return;
		}
		if(!startTime){
			lh.alert('请选择开拍时间');
			SAVING_FLAG = false;
			return;
		}
		if(!bail){
			bail = 0;
		}else{
			bail = parseInt(bail);
			if(!bail){
				lh.alert('请填写正确的保证金金额');
				SAVING_FLAG = false;
				return;
			}
		}
		$("#bail").val(bail);
		/*if(!bail){
			lh.alert('请输入保证金');
			SAVING_FLAG = false;
			return;
		}*/
	}
	if(COMEFROM == 3){
		var day = $("#day").val();
	}
	if(COMEFROM == 2){
		var typeId = $("#typeId").val();
		if(!typeId){
			lh.alert('请选择拍卖类型');
			SAVING_FLAG = false;
			return;
		}
	}
	/* TODO 后期统一升级红包推广功能
	var commissionPromotion = $("#commissionPromotion");
	var  bounsPromotion = $("#bounsPromotion");
	if(bounsPromotion[0].checked){
		var bounsNum = $("#bounsNum").val();
		var bounsSinglePrice = $("#bounsSinglePrice").val();
		if(!bounsNum){
			lh.alert('请填写红包数量');
			SAVING_FLAG = false;
			return;
		}
		if(!bounsSinglePrice){
			lh.alert('请填写红包单价');
			SAVING_FLAG = false;
			return;
		}
	}
	if(commissionPromotion[0].checked){
		var bonusTypeId = $("#bonusTypeId").val();
		var bonus = $("#bonus").val();
		if(!bonusTypeId){
			lh.alert('请选择佣金推广类型');
			SAVING_FLAG = false;
			return;
		}
		if(!bonus){
			lh.alert('请填写佣金推广金额');
			SAVING_FLAG = false;
			return;
		}
	}
	*/
	var url = "/asgAddOrUpdate";
	var r = $("#r").val();
	if(r) url += "?r="+r;
	if(goodsId)localStorage.setItem("goodsId",goodsId);
	localStorage.setItem("comeFrom",COMEFROM);
	localStorage.setItem("bail",bail);
	localStorage.setItem("startTime",startTime);
	localStorage.setItem("auctionName",auctionName);
	localStorage.setItem("typeId",typeId);
	localStorage.setItem("day",day);
	//localStorage.setItem("bonusTypeId",bonusTypeId);
	//localStorage.setItem("bonus",bonus);
	location.href = url;
}

/*function jumpToAddWholesale(){
	localStorage.setItem("comeFrom",5);//批发城
	var url = "/asgAddOrUpdate";
	var r = $("#r").val();
	if(r) url += "?r="+r;
	location.href = url;
}*/

