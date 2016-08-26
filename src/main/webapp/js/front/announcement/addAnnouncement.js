SAVING_FLAG = false;

function addAnnouncement(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var title = $("#title").val();
	var content = $("#content").val();
	if (!title) {
		lh.alert('请填写公告的标题');
		SAVING_FLAG = false;
		return;
	}
	if (!content) {
		lh.alert('请填写公告的内容');
		SAVING_FLAG = false;
		return;
	}

	
	var typeId = $("#forumId").val();
	var typeCode="forumAnnouc";
	var userId=$("#userId").val();
	var userName=$("#userName").val();
	var obj = {};
	obj.title = title;
	obj.content = content;
	obj.typeId = typeId;
	obj.typeCode = typeCode;
	obj.userId = userId;
	obj.senderId = userId;
	obj.senderName= userName;
	lh.loading('正在保存数据');//加载遮罩
	$.post('/addOrUpdateAnnouncement',obj,function(rsp){
		SAVING_FLAG = false;
		if(rsp){
			if(rsp.success){
				lh.alert('您已成功发布公告', jumpToFrom);
				;
			}else{
				lh.alert(rsp.msg, '提示', lh.reload);
			}
		}
	},'json');
}

//跳转回主页
function jumpToFrom(){
	var typeId = $("#forumId").val();
	lh.jump("/forumIndexs/"+typeId);
}
function setShowLength(obj, maxlength, id) {
	var rem = maxlength - obj.value.length;
	var wid = id;
	if (rem < 0) {
		rem = 0;
	}
	document.getElementById(wid).innerHTML = "还可以输入" + rem + "字数";
}