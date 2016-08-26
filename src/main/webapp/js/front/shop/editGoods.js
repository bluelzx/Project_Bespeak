SAVING_FLAG = false;

$(function(){
	/*initUploadSimple({showDelBtn:true});//调用完整方法*/
	/*$("#goodsDidt,#mainStatus,#sequence,#bonusTypeId").select2({
		tags: "true",
		placeholder: "",
		allowClear: true
	});*/
	$("#goodsDidt,#mainStatus,#sequence,#bonusTypeId").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
});


function addGoods(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var id = $("#id").val();
	var moduleId = $("#moduleId").val();
	//var remainNumber = $("#remainNumber").val();
	var shopPrice = $("#shopPrice").val();
	var goodsName = $("#goodsName").val();
	var agentPrice = $("#agentPrice").val();
	var goodsDescription = $("#goodsDescription").val();
	if(!goodsName){lh.alert('请填写商品名称');SAVING_FLAG = false;return;}
	if(!goodsDescription){lh.alert('请填写商品描述');SAVING_FLAG = false;return;}
	var obj = {};
	obj.id = id;
	/*if(remainNumber){
		obj.remainNumber = remainNumber;
	}*/
	if(moduleId == 5){
		agentPrice = parseInt(agentPrice);
		if(!agentPrice){
			lh.alert('请填写正确的代理价格');
			SAVING_FLAG = false;
			return;
		}
	}
	obj.shopPrice = shopPrice;
	obj.agentPrice = agentPrice;
	obj.goodsName = goodsName;
	obj.goodsDescription = goodsDescription;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/updateGoods',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.alert(rsp.msg,'reload()');
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

function reload(){
	location.reload();
}
