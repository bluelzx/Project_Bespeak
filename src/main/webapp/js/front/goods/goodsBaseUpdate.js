
$(function(){
	//initWxSDK(['chooseImage','previewImage','uploadImage','downloadImage'], 'jumpToUserCenter', choosePic);//TODO 测试
	/*initWxSDK(['chooseImage','previewImage','uploadImage','downloadImage']);
	if(!lh.upload)lh.upload = {};
	if(!lh.upload.pathStr)lh.upload.pathStr ='';*/
	iniPage();
	iniData();
});


function iniPage(){
	$("#typeCode").select({
	  title: "选择拍品类型",
	  items: lh.param.goodsTypeAry
	});
}

function iniData(){
	var goods = lh.param.goods;
	if(!goods)return;
	$("#goodsName").val(goods.goodsName);
	$("#goodsBrief").val(goods.goodsBrief);
	$("#typeCode").attr('data-values', goods.typeCode);
	$("#typeCode").val(goods.goodsTypeName);
}

function setShowLength(obj, maxlength, id) { 
	var rem = maxlength - obj.value.length; 
	var wid = id; 
	if (rem < 0){ 
		rem = 0; 
	} 
	document.getElementById(wid).innerHTML = "还可以输入" + rem + "字数"; 
}

function buildGoods(){
	var goodsName = $("#goodsName").val();
	var goodsBrief = $("#goodsBrief").val();
	var typeCode = $("#typeCode").attr('data-values');
	
	if(!typeCode){lh.alert('请选择藏品类型');return;}
	if(!goodsName){lh.alert('请填写藏品名称');return;}
	if(!goodsBrief){lh.alert('请填写藏品描述');Sreturn;}
	if(goodsName.length>30){lh.alert('藏品名称不能超过30个字');return;}
	if(goodsBrief.length>500){lh.alert('藏品描述不能超过500个字');return;}
	
	/*var priceBegin = $("#priceBegin").val();
	var remainNumber = $("#remainNumber").val();
	var shopPrice = $("#shopPrice").val();
	if(!lh.upload || !lh.upload.pathStr){lh.alert('请上传藏品图片');return;}
	var filePaths = lh.upload.pathStr;
	if(!remainNumber){remainNumber = 1}
	if(!filePaths){lh.alert('请上传藏品图片');return;}
	if(_.startsWith(filePaths, ',')){
		filePaths = filePaths.substring(1);
	}
	var filePathArr = filePaths.split(",");
	if(filePathArr.length > 9){lh.alert('最多只能上传9张藏品图片');return;}*/

	//var goods = {goodsName:goodsName, auctionPrice:priceBegin, goodsBrief:goodsBrief,goodsDescription:goodsBrief,typeCode:typeCode,remainNumber:remainNumber, picPaths:filePaths};
	//if(shopPrice)goods.shopPrice = shopPrice;
	
	var goods = {id:lh.param.goods.id, goodsName:goodsName, goodsBrief:goodsBrief,goodsDescription:goodsBrief,typeCode:typeCode};
	return goods;
}

function updateGoods(paramObj){
	var goods = buildGoods();
	if(!goods)return;
	lh.loading('正在保存数据');//加载遮罩
	lh.post('front', '/goods/updateGoodsBase', goods,function(rsp){
		if(rsp.success){
			if(lh.param.from == 'am'){//微拍
				lh.jumpR('/am/page/addOrUpdate?goodsId='+goods.id);
			}
		}else{
			lh.alert(rsp.msg, '提示', lh.reload);
		}
	},'json', {requesting:'updateBaseGoodsToAM'});
}

