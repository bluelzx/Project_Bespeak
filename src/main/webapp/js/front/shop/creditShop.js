function resetCredit(id){
	var obj = {};
	obj.id = id;
	obj.reset = 1;
	$.post('/addOrUpdateCredit',obj,function(rsp){
		if(rsp){
			 frontLoginCheck(rsp);//登陆检查
			 if(rsp.status == 'success'){
			 	lh.alert('重置成功', 'reloadPage');
			 }else{
			 	lh.alert(rsp.msg);
			 }
		}
	},'json')
}

function creditShop(){
	$("#shop").show();
	$("#customer").hide();
	$("#customer1,#customer2").hide();
	$("#shop1,#shop2").show();
}

function creditCustomer(){
	$("#shop").hide();
	$("#customer").show();
	$("#shop1,#shop2").hide();
	$("#customer1,#customer2").show();
}
