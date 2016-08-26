var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
SAVING_FLAG = 0;
$(function(){
	initData();
	initUploadSimple();//调用完整方法
	$('#goodsPictureForm').form('disableValidation');
	addgoodsPictureForm();
});

function initData(){
	$('#shopType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '商店商品'
		},{
			'id' : 79,
			'name' : '批发城商品'
		}],
		onSelect: function(rec){
            if(rec.id == 1){
            	$('#shopId').textbox('readonly',false);
            	$('#wholesaleId').textbox('readonly',true);
            	$('#wholesaleId').combobox('setValue','');
            }else{
            	$('#wholesaleId').textbox('readonly',false);
            	$('#shopId').textbox('readonly',true);
            	$('#shopId').combobox('setValue','');
            }
        }
	});
	$('#shopId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:400,
	    url:'/back/getShop'
	});
	$('#wholesaleId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:400,
		url:'/back/getWholesale'
	});
	$('#goodsType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:true,
		panelHeight:200,
		url:'/back/getGoodsTypeList'
	});
	$('#mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:true,
		panelHeight:200,
		url:'/back/getGoodsStatusList'
	});
}

/**返回**/
function returnBack(){
	window.location.href="/back/goodsPicture.html";
}

function addGoods(){
	//$('#goodsPictureForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#goodsPictureForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}

function addgoodsPictureForm(){
	var $form = $('#goodsPictureForm');
	$("#upload_outer_div").empty();
	$form.form({
	    url:'',
	    onSubmit: function(){
	       $('#goodsPictureForm').form('enableValidation');
	       var flag = $('#goodsPictureForm').form('validate');
	       if(flag){
				var goodsName = $("#goodsName").textbox('getValue');
				var shopPrice = $("#shopPrice").textbox('getValue');
				var goodsDescription = $("#goodsDescription").textbox('getValue');
				var shopId = $("#shopId").combobox('getValue');
				var wholesaleId = $("#wholesaleId").combobox('getValue');
				var goodsType = $("#goodsType").combobox('getValue');
				var shopType = $("#shopType").combobox('getValue');
				var mainStatus = $("#mainStatus").combobox('getValue');
				var filePaths = $("#filePaths").val();
				var filePathArr = new Array();
				if(filePaths.indexOf(',') >= 0){
					filePaths = filePaths.substring(1);
				}
				if(!filePaths){$.messager.alert('提示','请上传商品图片');return;}
				var obj = {};
				if(shopType == 1){
					obj.shopId = shopId;
				}else{
					obj.shopId = wholesaleId;
				}
				obj.catId = goodsType;
				obj.goodsName = goodsName;
				obj.shopPrice = shopPrice;
				obj.mainStatus = mainStatus;
				obj.goodsDescription = goodsDescription;
				obj.filePaths = filePaths;
				$.post('/back/addOrUpdateGoods',obj,function(rsp){
					if(rsp.status == 'success'){
						window.location.reload();
					}else{
						alert('提示',rsp.msg);
					}
				},'json');
	       }
	    }
	});
}

