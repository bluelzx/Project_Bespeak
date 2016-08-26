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
	/*$("#bonusTypeId,#typeId").select2({
		tags: "true",
		allowClear: false,//去掉"X"，删除按钮
	    minimumResultsForSearch: 20,//去掉搜索框
	    placeholder: "",
	});*/
	
	//http://silviomoreto.github.io/bootstrap-select/options/
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

/**显示图片**/
function showImg(localId){
	if(!localId)return;
	var domId = localId.substr(localId.length-16);
	var dom = '<li class="weui_uploader_file" id="weui_uploader_file_'+domId+'" style="background-image:url('+localId+')">'
				+'<i class="weui_icon_cancel fr" onclick="removeSelf('+domId+')"  style="position: relative;bottom: 5px;background-color: black;"></i>'
			+'</li>'
	$("#fileUpload").append(dom);//将图片显示出来
}

/**删除图片**/
function removeSelf(domId){
	$("#weui_uploader_file_"+domId).remove();
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

function  auction(id,first){
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

function bounsPromotionCheck(){
	/*var  bounsPromotion = $("#bounsPromotion");
	if(bounsPromotion[0].checked){
		$('#bounsPromotion').prop('checked', true);
		$("#bounsPromotionType").show();
	}else{
		$('#bounsPromotion').prop('checked',false);
		$("#bounsPromotionType").hide();
	}*/
}

/*function commissionPromotionCheck(){
	var  commissionPromotion = $("#commissionPromotion");
	if(commissionPromotion[0].checked){
		$('#commissionPromotion').prop('checked', true);
		$("#commissionPromotionType").show();
	}else{
		$('#commissionPromotion').prop('checked',false);
		$("#commissionPromotionType").hide();
	}
}*/

function addAuction(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
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

function jumpToAddWholesale(){
	localStorage.setItem("comeFrom",5);//批发城
	var url = "/asgAddOrUpdate";
	var r = $("#r").val();
	if(r) url += "?r="+r;
	location.href = url;
}

