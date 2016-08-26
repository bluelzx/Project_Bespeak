SAVING_FLAG = false;

$(function(){
	init();
});

function init(){
	$("#express").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    mobile:true,
	    width:'100%',
	    size:20
	});
}

function sendGoods(orderId){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var orderGoodsId = $("#orderGoodsId").val();
	var expressId = $("#express").val();
	var expressOrder = $("#expressOrder").val();
	if(!express){
		lh.alert('请填写快递公司');
		SAVING_FLAG = false;
		return;
	}
	if(!expressOrder){
		lh.alert('请填写快递单号');
		SAVING_FLAG = false;
		return;
	}
	
	var obj = {expressOrder:expressOrder, expressId:expressId, orderGoodsId:orderGoodsId};
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/sendGoods',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			lh.alert('您已经成功发货，即将返回发货页面',"lh.jumpR('/shipping')");
		}else{
			lh.alert(rsp.msg);
		}
	},'json');	
}