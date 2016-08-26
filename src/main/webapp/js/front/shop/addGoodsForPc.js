SAVING_FLAG = false;

$(function(){
	initUploadSimple();//初始化上传组件
	initData();
});
/*
function postage(){
	var postageFee = $("#postageFee").val();
	if(postageFee == 2){
		$("#postageFeeValue").show();
	}else{
		$("#postageFeeValue").hide();
	}
}
*/

function initData(){
	$("#filePaths").val('');
	var isSuccess = localStorage.getItem("success");
	var custPhone = localStorage.getItem("custPhone");
	var catId = localStorage.getItem("catId");
	var postageFee = localStorage.getItem("postageFee");
	var isSevenReturn = localStorage.getItem("isSevenReturn");
	if(isSuccess && isSuccess == 2){
		$("#successTip").text('藏品已经上传成功，您可以继续上传。');
	}else{
		$("#successTip").text('电脑端上传藏品');
	}
	if(custPhone){
		$("#custPhone").val(custPhone);
	}
	if(catId){
		$("#catId").val(catId);
	}
	if(catId){
		$("#catId").val(catId);
	}
	if(postageFee){
		$("#postageFee").val(postageFee);
	}
	if(isSevenReturn){
		$("#isSevenReturn").val(isSevenReturn);
	}
}

function addGoods(){
	if(SAVING_FLAG)return;
	
	var custPhone = $("#custPhone").val();
	var shopId = $("#shopId").val();
	var shopPrice = $("#shopPrice").val();
	var goodsName = $("#goodsName").val();
	var goodsDescription = $("#goodsDescription").val();
	var filePaths = $("#filePaths").val();
	var isSevenReturn = $("#isSevenReturn").val();
	var postageFee = $("#postageFee").val();
	var catId = $("#catId").val();
	if(!custPhone){alert('请填写客户手机号码');return;}
	if(!goodsName){alert('请填写藏品名称');return;}
	if(!catId){alert('请选择藏品类型');return;}
	if(!goodsDescription){alert('请填写藏品描述');return;}
	if(!postageFee){alert('请选择是否包邮');return;}
	if(!isSevenReturn){alert('请选择是否7天包退');return;}
	if(!goodsDescription){alert('请填写藏品描述');return;}
	if(goodsName.length>35){alert('藏品名称不能超过35个字');return;}
	if(goodsDescription.length>500){alert('藏品描述不能超过500个字');return;}
	if(!filePaths){alert('请上传图片');return;}
	var filePathArr = new Array();
	if(filePaths.indexOf(',') >= 0){
		filePaths = filePaths.substring(1);
	}
	filePathArr = filePaths.split(",");
	if(filePathArr.length > 9){
		alert('图片数量不能超过9张');
		return;
	}
	var obj = {phone:phone, shopPrice:shopPrice, goodsName:goodsName, goodsDescription:goodsDescription, catId:catId, filePaths:filePaths};
	localStorage.setItem("custPhone", custPhone);
	localStorage.setItem("catId", catId);
	localStorage.setItem("postageFee", postageFee);
	localStorage.setItem("isSevenReturn", isSevenReturn);
	frontBaseLoadingOpen('正在提交...');//打开遮罩
	SAVING_FLAG = true;
	$.post('/addGoodsForPc',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//关闭遮罩
		if(rsp.status == 'success'){
			localStorage.setItem("success", 2);
			window.location.reload();
		}else{
			localStorage.setItem("success", 1);
			alert(rsp.msg, 'reloadPage');
		}
	},'json')
}

