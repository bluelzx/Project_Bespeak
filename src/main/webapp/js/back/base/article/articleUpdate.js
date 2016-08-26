var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
MYEDITOR = null;
SAVING_FLAG = 0;
$(function(){
	MYEDITOR = UE.getEditor('editor');
	initData();
	pushData();
	$('#announcementForm').form('disableValidation');
	addAnnouncementForm();
	
});



function initData(){
	$('#f_author').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		url : "/back/getusernameArray",
	});
	$('#f_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : false,
		panelHeight : 200,
		url : "/back/getusernameArray",
	});
	//栏目选项
	$('#f_catId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : false,
		panelHeight : 200,
		url : "/back/getForumArray",
	});
	$('#f_roleId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		url : "/back/getusernameArray",
	});
	$("#f_mainStatus").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});
	$("#f_isHot").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$("#f_isRecommend").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$("#f_isShowIndex").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$("#f_isGood").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$("#f_isTop").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	
	var operation = $("#operation").val();
	var domIds = "#f_title,#f_author,#f_startDate,"
		+"#f_endDate,#f_mainStatus";
	commonEditChangeToRead(domIds,'#editAnnouncement','#addAnnouncement,#browse',operation);
}

function editAnnouncement(){
	var domIds = "#f_title,#f_author,#f_startDate,"
		+"#f_endDate,#f_mainStatus";
	commonReadChangeToEdit(domIds,'#addAnnouncement,#browse','#editAnnouncement');
	dom ='<a id="picDelete" style="text-decoration: none;" href="javascript:deletePicPath()" class="delete" title="删除">删除</a></div>'
	$("#pic").append(dom);
}


/**通用初始化查看(编辑到只读)**/
function commonEditChangeToRead(domIds,domIdsShow,domIdsHide,flag){
	var isReadOnly = false;
	if(flag != "undefined" && flag){
		isReadOnly = true;
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
		setTimeout(function(){
			MYEDITOR.setDisabled();
		},1000);
		$(domIdsShow).show();
		$(domIdsHide).hide();
	}else{
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
	}
	
}
/**通用从编辑到确定保存(只读到编辑)**/
function commonReadChangeToEdit(domIds,domIdsShow,domIdsHide){
	$(domIdsHide).hide();
	$(domIdsShow).show();
	$(domIds).textbox('readonly',false);//设置只读还是可编辑
	setTimeout(function(){
		MYEDITOR.setEnabled();
	},1000);
}


/**返回**/
function returnBack(){
	lh.back();
//	window.location.href="/back/page/article";
}

function addAnnouncementForm(){
	var $form = $('#announcementForm');
	$form.form({
	    url:'',
	    onSubmit: function(){
//	    	if(SAVING_FLAG)return false;
	       $('#announcementForm').form('enableValidation');
	       var flag = $('#announcementForm').form('validate');
	       if(flag){
				var articleId = $("#id").val();
				var title = $("#f_title").textbox('getValue');
				var mainStatus = $("#f_mainStatus").combobox('getValue');
				var author = $("#f_author").combobox('getValue');
				var content = MYEDITOR.getContent();
				var description = $("#f_description").textbox('getValue');
				var orgin = $("#f_orgin").textbox('getValue');
				var userId = $("#f_userId").combobox('getValue');
				var keywords = $("#f_keywords").textbox('getValue');
				var tags = $("#f_tags").textbox('getValue');
				var isHot = $("#f_isHot").combobox('getValue');
				var isRecommend = $("#f_isRecommend").combobox('getValue');
				var isShowIndex = $("#f_isShowIndex").combobox('getValue');
				var isGood = $("#f_isGood").combobox('getValue');
				var isTop = $("#f_isTop").combobox('getValue');
				
				var roleId = $("#f_roleId").combobox('getValue');
				var catId = $("#f_catId").combobox('getValue');
				var organization = $("#f_organization").textbox('getValue');
				var startDate = $("#f_startDate").datetimebox('getValue');
				var endDate = $("#f_endDate").datetimebox('getValue');			
				
				var obj = {};
				obj.id = articleId;
				obj.title = title;
				if(!content){$.messager.alert('提示','请填写内容');return;}
				obj.content = content;
				obj.mainStatus = mainStatus;
				obj.orgin = orgin;
				obj.description = description;
				obj.author = author;
				obj.userId = userId;
				obj.keywords = keywords;
				obj.tags = tags;
				obj.isHot = isHot;
				obj.isRecommend = isRecommend;
				obj.isShowIndex = isShowIndex;
				obj.isGood = isGood;
				obj.isTop = isTop;
				obj.roleId = roleId;
				obj.catId = catId;
				obj.organization = organization;
				obj.startDate = startDate;
				obj.endDate = endDate;
				
				var filePaths = $("#filePaths").val();
				var filePathArr = new Array();
				
//				if(filePaths.indexOf(',') >= 0){
//					filePaths = filePaths.substring(1);
//				}
//				filePathArr = filePaths.split(",");
//				if(filePathArr.length > 1){
//					$.messager.alert('提示','请先删除以前的图片,再重新上传');
//					return;
//				}
				
				//if(!filePathArr[0]){$.messager.alert('提示','请上传封面');return;}
				obj.attachFiles = filePathArr[0];
				//SAVING_FLAG = true;	
				$.post('/back/addOrUpdateArticle',obj,function(rsp){
					//backLoginCheck(rsp);//后台登陆检查
					//SAVING_FLAG = false;
					if(rsp.status == 'success'){
						window.location.reload();
					}else{
						$.messager.alert('提示',rsp.msg);
					}
				},'json');
	       }
	       return false;
	    }
	});
}
//添加修改方法
function addAnnouncement(){
	//$('#announcementForm').submit();
	var timeRec = lh.preventRepeat(10, SAVING_FLAG);
	//手动重写提交判断
	if(!(MYEDITOR.getContent())){$.messager.alert('提示','请填写内容');timeRec=false; return;};
	if(!$("#f_title").textbox('getValue')){$.messager.alert('提示','请填写标题');timeRec=false; return;}
	if(!$("#f_roleId").combobox('getValue')){$.messager.alert('提示','请选择用户标识');timeRec=false; return;}
	if(!$("#f_author").combobox('getValue')){$.messager.alert('提示','请选择作者');timeRec=false; return;}
	if(!$("#f_mainStatus").combobox('getValue')){$.messager.alert('提示','请选择文章状态');timeRec=false; return;}
	if(!$("#f_catId").combobox('getValue')){$.messager.alert('提示','请选择所属栏目');timeRec=false; return;}
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#announcementForm').submit();//执行操作
		//提交成功后跳转
		location.href = "/back/page/article";//location.href实现客户端页面的跳转  
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}

function pushData(){
	var id = $("#id").val();
	if(id){
		$.post('/back/getArticleList',{id:id},function(rsp){
			if(rsp.status == 'success'){
				var article = rsp.rows[0];
				$("#f_articleId").val(id);
				$("#f_title").textbox('setValue',article.title);
				$("#f_mainStatus").combobox('setValue',article.mainStatus);
				$("#f_author").combobox('setValue',article.author);
				$("#f_userId").combobox('setValue',article.userId);
				$("#f_orgin").textbox('setValue',article.orgin);
				$("#f_description").textbox('setValue',article.description);
				$("#f_keywords").textbox('setValue',article.keywords);
				$("#f_tags").textbox('setValue',article.tags);
				$("#f_isHot").combobox('setValue',article.isHot);
				$("#f_isRecommend").combobox('setValue',article.isRecommend);
				$("#f_isShowIndex").combobox('setValue',article.isShowIndex);
				$("#f_isGood").combobox('setValue',article.isGood);
				$("#f_isTop").combobox('setValue',article.isTop);
				$("#f_roleId").combobox('setValue',article.roleId);
				$("#f_catId").combobox('setValue',article.catId);
				$("#f_organization").combobox('setValue',article.organization);
				$("#f_startDate").datetimebox('setValue',article.startDate);
				$("#f_endDate").datetimebox('setValue',article.endDate);	
				setTimeout(function(){
					MYEDITOR.setContent(article.content,false);
				},300);
//				if(article.picPath){
//					$('#upload_outer_div').empty();//清空附件DOM
//					$("#filePaths").val(article.picPath);
//					var dom = '<div id="pic"><a href="'+article.picPath+'" target="_blank"><img src="'+article.picPath+'" class="height80"/></a>'
//					var operation = $("#operation").val();
//					if(operation == "undefined"){
//						dom +='<a id="picDelete" style="text-decoration: none;" href="javascript:deletePicPath()" class="delete" title="删除">删除</a></div>'
//					}
//					$('#upload_outer_div').append(dom);//添加到DOM中
//				}
			}
		},'json');
	}
}

function deletePicPath(){
	$("#filePaths").val('');
	//$("#pic img").remove();
	//$("#pic a").remove();
	$("#upload_outer_div").empty();
}
