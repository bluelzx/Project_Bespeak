SAVING_FLAG = false;
CATID = null;
TEMPOBJ = null;
PAGE_FROM = '';
CURRENT_GOODS_ID = null;
$(function(){
	initUploadSimple();//调用完整方法
	PAGE_FROM = $("#from").val();
	initWxSDK(['chooseImage','previewImage','uploadImage','downloadImage'], 'jumpToUserCenter',choosePic);//TODO 测试
	$("#goodsDictList,#mainStatus,#sequence,#bonusTypeId").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
});

/**显示图片**/
function showImg(localId, serverId){
	if(!localId || !serverId)return;
	//var domId = getIdFromDate();
	var domId = serverId;
	//lh.alert('domId:'+domId+'-localId:'+localId);
	var dom = '<li class="weui_uploader_file" id="weui_uploader_file_'+domId+'" style="background-image:url('+localId+')">'
				+'<i class="weui_icon_cancel fr" onclick="removeSelf(\''+domId+'\')"  style="position: relative;bottom: 5px;background-color: black;"></i>'
			+'</li>';
	$("#fileUpload").append(dom);//将图片显示出来
}

/**删除图片**/
function removeSelf(domId){
	UPLOAD_OBJ.pathStr = UPLOAD_OBJ.pathStr.replace(','+domId, '');
	UPLOAD_OBJ.pathStr = UPLOAD_OBJ.pathStr.replace(domId, '');
	$("#weui_uploader_file_"+domId).remove();
}

/*function next(){
	$("#next,#add,#goBack").show();
	$("#first,#nextBtn,#draft").hide();
}
function back(){
	$("#next,#add,#goBack").hide();
	$("#first,#nextBtn,#draft").show();
}
function commissionPromotionCheck(){
	var  commissionPromotion = $("#commissionPromotion");
	if(commissionPromotion[0].checked){
		$('#commissionPromotion').prop('checked', true);
		$("#bonusType").show();
	}else{
		$('#commissionPromotion').prop('checked',false);
		$("#bonusType").hide();
	}
}*/

function showActionSheet(){
	var mask = $('#myMask');
    var weuiActionsheet = $('#weui_actionsheet');
    weuiActionsheet.addClass('weui_actionsheet_toggle');
    mask.show().addClass('weui_fade_toggle').click(function () {
        hideActionSheet(weuiActionsheet, mask);
    });
    $('#actionsheet_cancel').click(function () {
        hideActionSheet(weuiActionsheet, mask);
    });
    weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');

//    function hideActionSheet(weuiActionsheet, mask) {
//        weuiActionsheet.removeClass('weui_actionsheet_toggle');
//        mask.removeClass('weui_fade_toggle');
//        weuiActionsheet.on('transitionend', function () {
//            mask.hide();
//        }).on('webkitTransitionEnd', function () {
//            mask.hide();
//        })
//    }

}
function showChooseToJumpSheet(){
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

function catValue(id,name,first){
	if(first){
		showActionSheet();
	}else{
		var mask = $('#myMask');
		var weuiActionsheet = $('#weui_actionsheet');
		hideActionSheet(weuiActionsheet, mask);
	}
	if(id && name){
		CATID = id;
		$("#goodsTypeName").text(name);
	}
	return CATID;
}
function chooseToJump(opt, name){
	$('#myMask1,#weui_actionsheet1').hide();
	$('#weui_actionsheet1').removeClass('weui_actionsheet_toggle');
	$('#myMask1').removeClass('weui_fade_toggle');
	if(opt == 2){
		var url = '/releaseGoods?from=as';
		if(CURRENT_GOODS_ID)url += '&goodsId='+goodsId;
		lh.jumpR(url);
	}else if(opt == 3){
		var url = '/releaseGoods?from=ap';
		if(CURRENT_GOODS_ID)url += '&goodsId='+goodsId;
		lh.jumpR(url);
	}
	window.location.reload();
}

function addGoods(){
	if(SAVING_FLAG)return;
	/*var wholesaleId = $("#wholesaleId").val();*/
	//var postageFee = $("#postageFee").val();
	//var packFee = $("#packFee").val();
	//var auctionPrice = $("#auctionPrice").val();
	//var agentPrice = $("#agentPrice").val();
	//var filePaths ="/file/default/1452827347322__20160115001639269_thumb.jpg";
	//var remainNumber = $("#remainNumber").val();
	//var postageFee = $("#postageFee");
	//var  isSevenReturn = $("#isSevenReturn");
	//var  isPublic = $("#isPublic");
	/*var commissionPromotion = $("#commissionPromotion");
	if(commissionPromotion[0].checked){
		var bonusTypeId = $("#bonusTypeId").val();
		if(!bonusTypeId){
			lh.alert('请选择佣金推广类型');
			SAVING_FLAG = false;return;
		}
		var bonus = $("#bonus").val();
		if(!checkNumber(bonus, 'positive', '佣金金额')){
			 SAVING_FLAG = false;return;
		}
	}
	var bounsPromotion = $("#bounsPromotion");
	if(bounsPromotion[0].checked){
		var bounsNum = $("#bounsNum").val();
		if(!bounsNum){
			lh.alert('请填写红包数');
			SAVING_FLAG = false;return;
		}
		var bounsSinglePrice = $("#bounsSinglePrice").val();
		if(!bounsSinglePrice){
			lh.alert('请填写红包单价');
			SAVING_FLAG = false;return;
		}
	}*/
	/*if(postageFee[0].checked){
		obj.postageFee = 1;
	}else{
		obj.postageFee = 2;
	}
	if(isPublic[0].checked){
		obj.isPublic = 1;
	}else{
		obj.isPublic = 2;
	}
	if(isSevenReturn[0].checked){
		obj.isSevenReturn = 1;
	}else{
		obj.isSevenReturn = 2;
	}*/
	/*if(commissionPromotion[0].checked){
		obj.bonus = bonus;
		obj.bonusTypeId = bonusTypeId;
	}
	if(bounsPromotion[0].checked){
		obj.bounsSinglePrice = bounsSinglePrice;
		obj.bounsNum = bounsNum;
	}*/
	/*var auctionQuick = $("#auctionQuick");
	var auctionMicro = $("#auctionMicro");
	var auctionProfession = $("#auctionProfession");
	var wholesaleCheckBox = $("#wholesaleCheckBox");*/
	/*var moduleIds =  1;
	if(auctionQuick[0].checked){
		moduleIds +=","+3;
	}
	if(auctionProfession[0].checked){
		moduleIds +=","+4;
	}
	if(wholesaleCheckBox[0].checked){
		moduleIds +=","+5;
	}
	if(auctionMicro[0].checked){
		moduleIds +=","+2;
	}
	obj.moduleIds = moduleIds;*/
	//obj.agentPrice = agentPrice;
	//obj.auctionPrice = auctionPrice;
	//obj.postageFee = postageFee;
	//obj.packFee = packFee;
	/*if(bounsPromotion[0].checked){
		$("#dialog").show();
		$("#payPassword").val('');
	}else{
		commonAddgoods();
	}*/
	
	var shopId = $("#shopId").val();
	var goodsName = $("#goodsName").val();
	var goodsDescription = $("#goodsDescription").val();
	var shopPrice = $("#shopPrice").val();
	var filePaths = UPLOAD_OBJ.pathStr;
	if(!CATID){lh.alert('请选择藏品类型');return;}
	if(!goodsName){lh.alert('请填写藏品名称');return;}
	if(!goodsDescription){lh.alert('请填写藏品描述');Sreturn;}
	if(goodsName.length>35){lh.alert('藏品名称不能超过35个字');return;}
	if(goodsDescription.length>500){lh.alert('藏品描述不能超过500个字');return;}
	
	if(!filePaths){lh.alert('请上传藏品图片');return;}
	if(filePaths.indexOf(',') >= 0){
		filePaths = filePaths.substring(1);
	}
	var filePathArr = filePaths.split(",");
	if(filePathArr.length > 9){lh.alert('最多只能上传9张藏品图片');return;}
	var catId = CATID;
	var obj = {catId:catId, shopPrice:shopPrice, goodsName:goodsName, goodsDescription:goodsDescription, filePaths:filePaths};
	commonAddgoods(obj);
}

function commonAddGoods(goods){
	if(!goods)return;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	SAVING_FLAG = true;
	$.post('/addBaseGoods', goods,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		if(rsp.status == 'success'){//根据参数决定提示信息，或者微拍发布，专场发布，普通上传把页面分开写
			var goodsId = rsp.id;
			CURRENT_GOODS_ID = goodsId;
			if(PAGE_FROM == 'as'){
				lh.jumpR('/releaseGoods?goodsId='+goodsId+'+&from=as');
			}else{
				showChooseToJumpSheet();
			}
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
}

function reload(){
	window.location.reload();
}