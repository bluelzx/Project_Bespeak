var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
SAVING_FLAG = 0;
$(function(){
	initData();
	pushData();
});

function initData(){
	$('#shop').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:400,
	    url:'/back/getShop'
	});
}

/**返回**/
function returnBack(){
	window.location.href="/back/goods.html";
}

function addGoods(){
	//if(SAVING_FLAG)return false;
	var goodsId = $("#goodsId").val();
	var goodsName = $("#goodsName").textbox('getValue');
	var shopPrice = $("#shopPrice").numberbox('getValue');
	var promotePrice = $("#promotePrice").numberbox('getValue');
	var shopId = $("#shop").combobox('getValue');
	var obj = {};
	obj.id = goodsId;
	obj.goodsName = goodsName;
	obj.shopPrice = shopPrice;
	obj.promotePrice = promotePrice;
	obj.shopId = shopId;
	//SAVING_FLAG = true;	
	$.post('/back/addOrUpdateGoods',obj,function(rsp){
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
		$.post('/back/getGoodsList',{id:id},function(rsp){
			if(rsp.status == 'success'){
				var goods = rsp.rows[0];
				$("#goodsId").val(goods.id);
				$("#goodsName").textbox('setValue',goods.goodsName);
				$("#shop").combobox('setValue',goods.shopId);
				$("#shopPrice").numberbox('setValue',goods.shopPrice);
				$("#promotePrice").numberbox('setValue',goods.promotePrice);
			}
		},'json');
	}
}