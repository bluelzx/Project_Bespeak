var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
SAVING_FLAG = 0;
$(function(){
	initData();
	pushData();
	initUploadSimple({showDelBtn:true});//调用完整方法
});

function initData(){
	$('#user').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:400,
	    url:'/back/getUser'
	});
}

/**返回**/
function returnBack(){
	window.location.href="/back/shop.html";
}

function addShop(){
	//SAVING_FLAG = false;
	var shopId = $("#shopId").val();
	var name = $("#name").textbox('getValue');
	var userId = $("#user").combobox('getValue');
	var filePaths = $("#filePaths").val();
	var filePathArr = new Array();
	if(filePaths.indexOf(',') >= 0){
		filePaths = filePaths.substring(1);
	}
	filePathArr = filePaths.split(",");
	if(filePathArr.length >= 2){
		$.messager.alert('提示','只能上传1张店铺logo');
		//SAVING_FLAG = false;
		return;
	}
	var obj = {};
	obj.id = shopId;
	obj.name = name;
	obj.userId = userId;
	obj.logo = filePathArr[0];
	//SAVING_FLAG = true;	
	$.post('/back/addOrUpdateShop',obj,function(rsp){
		backLoginCheck(rsp);//后台登陆检查
		//SAVING_FLAG = false;
		if(rsp.status == 'success'){
			$.messager.alert('提示','店铺添加成功');
		}else{
			alert('提示',rsp.msg);
		}
	},'json');
}

function pushData(){
	var id = $("#id").val();
	if(id){
		$.post('/back/getShopList',{id:id},function(rsp){
			if(rsp.status == 'success'){
				var shop = rsp.rows[0];
				$("#shopId").val(shop.id);
				$("#name").textbox('setValue',shop.name);
				$("#user").combobox('setValue',shop.userId);
			}
		},'json');
	}
}