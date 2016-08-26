SAVING_FLAG = false;
$(function(){
	initUploadSimple({showDelBtn:true,showEdBtns:true});//初始化上传组件
});

function addActivities(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var title = $("#title").val();
	var catId = $("#catId").val();
	var description = $("#description").val();
	var content = $("#content").val();
	var filePaths = $("#filePaths").val();
	var fileDBIds = $("#fileDBIds").val();
	filePaths = filePaths.substring(1).split(",");
	fileDBIds = fileDBIds.substring(1).split(",");
	if(!title){lh.alert('请填写新闻资讯标题');SAVING_FLAG = false;return;}
	if(!description){lh.alert('请填写新闻资讯描述');SAVING_FLAG = false;return;}
	if(!content){lh.alert('请填写新闻资讯内容');SAVING_FLAG = false;return;}
	if(filePaths.length == 0){lh.alert('请上传新闻资讯封面');SAVING_FLAG = false;return;}
	if(filePaths.length > 2){
		lh.alert('请上传一张新闻资讯封面');
		SAVING_FLAG = false;
		return;
	}
	var obj = {};
	obj.typeId = 42;
	obj.catId = catId;
	obj.title = title;
	obj.description = description;
	obj.content = content;
	obj.picPath = filePaths[0];
	obj.picPathIds = fileDBIds[0];
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateArticle',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.alert('您的新闻资讯申请已提交,管理员审核通过后即可群发.','jumpToNews()');
				//window.location.href='/activity';
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function jumpToNews(){
	var url = '/news';
	var r = $("#r").val();
	if(r)url += "?r="+r
	window.location.href=url;
}
