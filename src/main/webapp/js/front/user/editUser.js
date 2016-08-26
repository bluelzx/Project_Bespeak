SAVING_FLAG = false;
$(function(){
	initUploadSimple({showDelBtn:true});//调用完整方法
});
function addUser(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var userId = $("#userId").val();
	var name = $("#name").val();
	var filePaths = $("#filePaths").val();
	var fileDBIds = $("#fileDBIds").val();
	var filePathArr = new Array();
	if(filePaths.indexOf(',') >= 0){
		filePaths = filePaths.substring(1);
		fileDBIds = fileDBIds.substring(1);
	}
	filePathArr = filePaths.split(",");
	if(filePathArr.length >= 2){
		lh.alert('只能上传1张头像图片');
		SAVING_FLAG = false;
		return;
	}
	var obj = {id:userId,name:name,avatar:filePaths};
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateUser',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				var url = "/user";
				var r = $("#r").val();
				if(r) url += "?r="+r;
				window.location.href = url;
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}