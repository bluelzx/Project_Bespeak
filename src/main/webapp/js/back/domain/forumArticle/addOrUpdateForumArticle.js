var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
MYEDITOR = null;
SAVING_FLAG = 0;
$(function(){
	//MYEDITOR = UE.getEditor('editor');
	initData();
	pushData();
	$('#forumArticleForm').form('disableValidation');
	addForumArticleForm();
	initUploadSimple();//初始化上传组件
});

function initData(){
	$('#type').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:200,
	    url:'/back/getForum'
	});
	
	$("#mainStatus").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:'auto',
	    data:[{
	    	'id' : 1,
	    	'name' : '启用'
	    },{
	    	'id' : 2,
	    	'name' : '停用'
	    }]
	});
	
	var operation = $("#operation").val();
	var domIds = "#title,#subTitle,#description,#type,#mainStatus"
	commonEditChangeToRead(domIds,'#editForumArticle','#addForumArticle',operation);
	if(operation == "undefined" || operation == ""){
		$("#editForumArticle").hide();//修改显示问题
	}
}

function editForumArticle(){
	var domIds = "#title,#subTitle,#description,#type,#mainStatus"
	commonReadChangeToEdit(domIds,'#addForumArticle','#editForumArticle');
}


/**通用初始化查看(编辑到只读)**/
function commonEditChangeToRead(domIds,domIdsShow,domIdsHide,flag){
	var isReadOnly = false;
	if(flag != "undefined" && flag){
		isReadOnly = true;
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
		/*setTimeout(function(){
			MYEDITOR.setDisabled();
		},1000);*/
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
	window.location.href="/back/forumArticle.html";
}

function addForumArticleForm(){
	$form = $("#forumArticleForm");
	$form.form({
		url:'',
		onSubmit:function(){
			//if(SAVING_FLAG)return false;
			$('#forumArticleForm').form('enableValidation');
			
	       var flag = $('#forumArticleForm').form('validate');
	       if(flag){
	       		var forumArticleId = $("#forumArticleId").val();
				//var title = $("#title").textbox('getValue');
				//var subTitle = $("#subTitle").textbox('getValue');
				var content = $("#content").textbox('getValue');
				var typeId = $("#type").combobox('getValue');
				var mainStatus = $("#mainStatus").combobox('getValue');
				//var content = MYEDITOR.getContent();
				if(!content){
					$.messager.alert('提示','请填写内容');
					//if(SAVING_FLAG)return false;
					return;
				}
				var userId = $("#f_userId").val();
				if(!userId){
					$.messager.alert('提示','请查询一位用户,为他添加帖子.');
					//if(SAVING_FLAG)return false;
					return;
				}
				var filePaths =  $("#filePaths").val();
				var fileDBIds =  $("#fileDBIds").val();
				var filePathArr = new Array();
				if(filePaths.indexOf(',') >= 0){
					filePaths = filePaths.substring(1);
				}
				filePathArr = filePaths.split(",");
				if(!filePaths){$.messager.alert('提示', '请上传图片');SAVING_FLAG = false;return;}
				if(filePathArr.length >= 9){
					$.messager.alert('提示', '只能上传8张图片');
					SAVING_FLAG = false;
					return;
				}
				var obj = {};
				obj.id = forumArticleId;
				obj.name = name;
				obj.userId = userId;
				obj.picPaths = fileDBIds;
				//obj.subTitle = subTitle;
				//obj.description = description;
				//obj.title = title;
				obj.forumId = typeId;
				obj.content = content;
				obj.description = content;
				obj.mainStatus = mainStatus;
				//SAVING_FLAG = true;	
				$.post('/back/addOrUpdateForumArticle',obj,function(rsp){
					backLoginCheck(rsp);//后台登陆检查
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

function addForumArticle(){
	//$("#forumArticleForm").submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#forumArticleForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}

function pushData(){
	var id = $("#id").val();
	if(id){
		$.post('/back/getForumArticleList',{id:id},function(rsp){
			if(rsp.status == 'success'){
				var forumArticle = rsp.rows[0];
				$("#forumArticleId").val(forumArticle.id);
				//$("#title").textbox('setValue',forumArticle.title);
				//$("#subTitle").textbox('setValue',forumArticle.subTitle);
				$("#content").textbox('setValue',forumArticle.content);
				$("#type").combobox('setValue',forumArticle.typeId);
				$("#mainStatus").combobox('setValue',forumArticle.mainStatus);
				/*setTimeout(function(){
					MYEDITOR.setContent(forumArticle.content,false);
				},300);*/
			}
		},'json');
	}
}


/** 跳转：用户信息 */
function jumpToUserInfo(){
	var url = "/back/userInfo";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	subShowMain('用户信息', url)
}

function searchUser(){
	searchUserBySerial();//common_back:通用跟据用户编号查询用户
}