
$(function(){
	//initWxSDK(['chooseImage','previewImage','uploadImage','downloadImage'], 'jumpToUserCenter', choosePic);//TODO 测试
	initWxSDK(['chooseImage','previewImage','uploadImage','downloadImage']);
	if(!lh.upload)lh.upload = {};
	if(!lh.upload.pathStr)lh.upload.pathStr ='';
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
	var from = lh.param.from;
	var goodsId = lh.param.goodsId;
	if(from){
		var param = from.split('-');
		lh.param.pageFrom = param[0];
		lh.param.paramSerial = param[1];
		if(lh.param.pageFrom == 'ap' && lh.param.paramSerial){
		}else{
			$("#shopPriceGap,#shopPriceDiv").show();
		}
	}else{
		$("#shopPriceGap,#shopPriceDiv").show();
	}
	//getMyGoodsList('all');
}

function setShowLength(obj, maxlength, id) { 
	var rem = maxlength - obj.value.length; 
	var wid = id; 
	if (rem < 0){ 
		rem = 0; 
	} 
	document.getElementById(wid).innerHTML = "还可以输入" + rem + "字数"; 
}

/**显示图片**/
function showImg(localId, serverId){
	if(!localId || !serverId)return;
	//var domId = getIdFromDate();
	var domId = serverId;
	//lh.alert('domId:'+domId+'-localId:'+localId);
	/*var dom = '<li class="weui_uploader_file" id="weui_uploader_file_'+domId+'" style="background-image:url('+localId+')">'
				+'<i class="weui_icon_cancel fr" onclick="removeSelf(\''+domId+'\')"  style="position: relative;bottom: 5px;background-color: black;"></i>'
			+'</li>';*/
	var dom = 
	'<div class="col-xs-3 pl0 pr7" id="weui_uploader_file_'+domId+'">'+
		'<i class="weui_icon_cancel fr" onclick="removeSelf(\''+domId+'\')"  style="position: relative;top: 22px;background-color: black;"></i>'+
		'<img src="'+localId+'" class="img-responsive">'+
	'</div>';
	
	$("#fileUpload").append(dom);//将图片显示出来
}

/**删除图片**/
function removeSelf(domId){
	lh.upload.pathStr = lh.upload.pathStr.replace(','+domId, '');
	lh.upload.pathStr = lh.upload.pathStr.replace(domId, '');
	$("#weui_uploader_file_"+domId).remove();
}

function addUploadGoodsToAP(){
	if(lh.param.pageFrom == 'ap' && lh.param.paramSerial){
		addGoods({from:'ap', apSerial:lh.param.paramSerial});
	}
}

function buildGoods(){
	
	//TODO 测试数据
	//var goods = {goodsName:'test', priceBegin:100, goodsBrief:'test',typeCode:'goods_type_yushi',remainNumber:10, picPaths:'/images/front/pic_01.png'};
	//return goods;
	
	var goodsName = $("#goodsName").val();
	var goodsBrief = $("#goodsBrief").val();
	var priceBegin = $("#priceBegin").val();
	var remainNumber = $("#remainNumber").val();
	var typeCode = $("#typeCode").attr('data-values');
	var shopPrice = $("#shopPrice").val();
	if(!lh.upload || !lh.upload.pathStr){lh.alert('请上传藏品图片');return;}
	var filePaths = lh.upload.pathStr;
	if(!typeCode){lh.alert('请选择藏品类型');return;}
	if(!goodsName){lh.alert('请填写藏品名称');return;}
	if(!goodsBrief){lh.alert('请填写藏品描述');Sreturn;}
	if(goodsName.length>30){lh.alert('藏品名称不能超过30个字');return;}
	if(goodsBrief.length>500){lh.alert('藏品描述不能超过500个字');return;}
	if(!remainNumber){remainNumber = 1}
	
	if(!filePaths){lh.alert('请上传藏品图片');return;}
	if(_.startsWith(filePaths, ',')){
		filePaths = filePaths.substring(1);
	}
	var filePathArr = filePaths.split(",");
	if(filePathArr.length > 9){lh.alert('最多只能上传9张藏品图片');return;}
	//shopPrice:shopPrice,typeCode:typeCode,  
	var goods = {goodsName:goodsName, auctionPrice:priceBegin, goodsBrief:goodsBrief,goodsDescription:goodsBrief,typeCode:typeCode,remainNumber:remainNumber, picPaths:filePaths};
	if(shopPrice)goods.shopPrice = shopPrice;
	return goods;
	
}

function addGoods(paramObj){
	var goods = buildGoods();
	if(!goods)return;
	
	var goodsId = lh.param.goodsId;
	if(!goodsId)return;
	goods.id = goodsId;
	
	if(lh.param.pageFrom == 'ap' && lh.param.paramSerial){
		goods.apSerial = lh.param.paramSerial;
	}
	
	lh.loading('正在保存数据');//加载遮罩
	lh.post('front', '/goods/updateGoods', goods,function(rsp){
		if(rsp.success){
			if(lh.param.pageFrom == 'ap' && lh.param.paramSerial){
				lh.confirm('您已成功上传拍品到专场，是否继续添加？', lh.reload, lh.back);
			}else{
				lh.confirm('您已成功上传藏品，是否继续添加？', lh.reload, lh.back);
			}
			var apSerial = rsp.apSerial;
		}else{
			lh.alert(rsp.msg, '提示', lh.reload);
		}
	},'json', {requesting:'addUploadGoodsToAP'});
}

