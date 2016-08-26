SAVING_FLAG = false;
$(function(){
	initUploadSimple();//初始化上传组件
	 /*$("#attr1").select2({
	    tags: "true",
	    placeholder: "",
	    allowClear: true
	 });*/
	 $("#attr1").selectpicker({
		    //style: 'btn-info',
			//actionsBox:true,
			//header:'请选择',
		    showTick:true,
		    size: 10
		});
});

function saveApply(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var attr1 = $("#attr1").val();
	var attr2 = $("#attr2").val();
	var attr3 = $("#attr3").val();
	var filePaths = $("#filePaths").val();
	if(!attr1){
		lh.alert('请选择证件类型');SAVING_FLAG = false;return ;
	}
	if(!attr2){
		lh.alert('请填写证件号码');SAVING_FLAG = false;return ;
	}
	if(!attr3){
		lh.alert('请填写证件姓名');SAVING_FLAG = false;return ;
	}
	if(!filePaths){
		lh.alert('请上传证件照片');SAVING_FLAG = false;return ;
	}
	var obj = {};
	obj.attr1 = attr1;
	obj.attr2 = attr2;
	obj.attr3 = attr3;
	obj.applyType = 91;
	obj.message = '实名认证';
	filePaths = filePaths.substring(1);
	if(filePaths.indexOf(",") >= 0){
		filePaths = filePaths.split(",");
		for(var i in filePaths){
			var file =  filePaths[i];
			if(file){
				var fileIdx = parseInt(i)+1;
				obj["file"+fileIdx] = file;
			}
			
		}
	}else{
		obj.file1 = filePaths;
	}
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateApply',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);
			if(rsp.status == 'success'){
				frontBaseConfirm('你的申请已经提交,管理员审核通过后即可通过','jumpToUserCenter()');
				
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function jumpToUserCenter(){
	var url = '/user';
	var r = $("#r").val();
	if(r) url += "?r="+r;
	window.location.href = url;
}
