SAVING_FLAG = false;
$(function(){
	initUploadSimple({showDelBtn:true});//调用完整方法
	/*$("#antiqueCity").select2({
	    tags: "true",
	    placeholder: "",
	    allowClear: true
	 });
	$("#isPcAllowed").select2({
		tags: "true",
	    placeholder: "",
	    allowClear: false,//去掉"X"，删除按钮
	    minimumResultsForSearch: -1//去掉搜索框
	 });*/
	$("#antiqueCity").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
	$("#isPcAllowed").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
	var antiqueCityId = $("#antiqueCityId").val();
	if(antiqueCityId){$("#antiqueCity").selectpicker('val', antiqueCityId);}
	
	var isPcAllowedValue = $("#isPcAllowedValue").val();
	if(isPcAllowedValue){$('#isPcAllowed').selectpicker('val', isPcAllowedValue);}
});

function editShop(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var shopId = $("#shopId").val();
	var isPcAllowed = $("#isPcAllowed").val();
	var name = $("#name").val();
	var remark = $("#remark").val();
	var filePaths = $("#filePaths").val();
	var fileDBIds = $("#fileDBIds").val();
	var antiqueCityId = $("#antiqueCity").val();
	var filePathArr = new Array();
	if(filePaths.indexOf(',') >= 0){
		filePaths = filePaths.substring(1);
		fileDBIds = fileDBIds.substring(1);
	}
	if(!antiqueCityId){
		lh.alert('请选择商圈');
		SAVING_FLAG = false;
		return;
	}
	filePathArr = filePaths.split(",");
	if(filePathArr.length >= 2){
		lh.alert('只能上传1张店铺logo');
		SAVING_FLAG = false;
		return;
	}
	
	if(!isPcAllowed)isPcAllowed = 1;//默认不允许电脑商带上传
	
	var obj = {id:shopId,name:name,remark:remark,antiqueCityId:antiqueCityId,isPcUploadAllowed:isPcAllowed};
	if(filePaths){
		obj.logo = filePaths;
		obj.fileDBIds = fileDBIds;
	}

	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/updateShopBaseInfo',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		if(rsp.status == 'success'){
			var url = "/myShop";
			var r = $("#r").val();
			if(r) url += "?r="+r;
			window.location.href=url;	
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
}

