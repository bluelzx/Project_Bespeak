var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
SAVING_FLAG = 0;
if(WIDTH<1000)WIDTH=1000;
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
	window.location.href="/back/wholesale.html";
}

function addWholesale(){
	//SAVING_FLAG = false;
	var wholesaleId = $("#wholesaleId").val();
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
	obj.id = wholesaleId;
	obj.name = name;
	obj.userId = userId;
	obj.logo = filePathArr[0];
	//SAVING_FLAG = true;
	$.post('/back/addOrUpdateWholesale',obj,function(rsp){
		backLoginCheck(rsp);//后台登陆检查
		//SAVING_FLAG = false;
		if(rsp.status == 'success'){
			window.location.reload();
		}else{
			alert('提示',rsp.msg);
		}
	},'json');
}

function pushData(){
	var id = $("#id").val();
	if(id){
		$.post('/back/getWholesaleList',{id:id},function(rsp){
			if(rsp.status == 'success'){
				var wholesale = rsp.rows[0];
				$("#wholesaleId").val(wholesale.id);
				$("#name").textbox('setValue',wholesale.name);
				$("#user").combobox('setValue',wholesale.userId);
			}
		},'json');
	}
}