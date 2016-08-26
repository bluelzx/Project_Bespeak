SAVING_FLAG = false;
$(function(){
	initUploadSimple({showDelBtn:true});//调用完整方法
});

function goodsPrivilegeCheck(){
	var  goodsPrivilege = $("#goodsPrivilege");
	if(goodsPrivilege[0].checked){
		$('#goodsPrivilege').prop('checked', true);
	}else{
		$('#goodsPrivilege').prop('checked',false);
	}
}

function editWholesale(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var wholesaleId = $("#wholesaleId").val();
	var name = $("#name").val();
	var address = $("#address").val();
	var tel = $("#tel").val();
	var description = $("#description").val();
	var  goodsPrivilege = $("#goodsPrivilege");
	if(!name){
		lh.alert('请填写批发城名称');
		SAVING_FLAG = false;
		return;
	}
	if(!address){
		lh.alert('请填写批发城地址');
		SAVING_FLAG = false;
		return;
	}
	if(!tel){
		lh.alert('请填写联系方式');
		SAVING_FLAG = false;
		return;
	}
	if(!description){
		lh.alert('请填写批发城描述');
		SAVING_FLAG = false;
		return;
	}
	var filePaths = $("#filePaths").val();
	var fileDBIds = $("#fileDBIds").val();
	var filePathArr = new Array();
	if(filePaths.indexOf(',') >= 0){
		filePaths = filePaths.substring(1);
		fileDBIds = fileDBIds.substring(1);
	}
	filePathArr = filePaths.split(",");
	if(filePathArr.length >= 2){
		lh.alert('只能上传1张批发城logo');
		SAVING_FLAG = false;
		return;
	}
	
	var obj = {id:wholesaleId,name:name,tel:tel,description:description,address:address};
	if(filePaths){
		obj.logo = filePaths;
		obj.fileDBIds = fileDBIds;
	}
	if(goodsPrivilege[0].checked){
		obj.goodsPrivilege = 1;
	}else{
		obj.goodsPrivilege = 2;
	}
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateWholesale',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				var userId = $("#userId").val();
				var url = "/ws/"+userId;
				var r = $("#r").val();
				if(r) url += "?r="+r;
				window.location.href=url;	
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}
