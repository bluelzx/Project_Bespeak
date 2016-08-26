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
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});
	
	var operation = $("#operation").val();
	var domIds = "#title,#institution,#address,#startDate,"
		+"#endDate,#type,#mainStatus,#description,#isShowIndex,#picDelete";
	commonEditChangeToRead(domIds,'#editAnnouncement','#addAnnouncement,#browse',operation);
}

function editAnnouncement(){
	var domIds = "#title,#institution,#address,#startDate,"
		+"#endDate,#type,#mainStatus,#description,#isShowIndex,#picDelete";
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
	window.location.href="/back/announcement.html";
}

function addAnnouncementForm(){
	var $form = $('#announcementForm');
	$form.form({
	    url:'',
	    onSubmit: function(){
	    	 //if(SAVING_FLAG)return false;
	       $('#announcementForm').form('enableValidation');
	       var flag = $('#announcementForm').form('validate');
	       if(flag){
				var announcementId = $("#announcementId").val();
				var title = $("#title").textbox('getValue');
				//var subtitle = $("#subTitle").textbox('getValue');
				var typeId = $("#type").combobox('getValue');
				var mainStatus = $("#mainStatus").combobox('getValue');
				var content = MYEDITOR.getContent();
				var description = $("#description").textbox('getValue');
				var obj = {};
				obj.id = announcementId;
				obj.title = title;
				//obj.subtitle = subtitle;
				if(!content){$.messager.alert('提示','请填写内容');return;}
				obj.content = content;
				obj.typeId = typeId;
				obj.mainStatus = mainStatus;
				obj.description = description;
				var filePaths = $("#filePaths").val();
				var filePathArr = new Array();
				if(filePaths.indexOf(',') >= 0){
					filePaths = filePaths.substring(1);
				}
				filePathArr = filePaths.split(",");
				if(filePathArr.length > 1){
					$.messager.alert('提示','请先删除以前的图片,再重新上传');
					return;
				}
				//if(!filePathArr[0]){$.messager.alert('提示','请上传封面');return;}
				obj.attachFiles = filePathArr[0];
				//SAVING_FLAG = true;	
				$.post('/back/addOrUpdateAnnouncement',obj,function(rsp){
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

function addAnnouncement(){
	//$('#announcementForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#announcementForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}

function pushData(){
	var id = $("#id").val();
	if(id){
		$.post('/back/getAnnouncementList',{id:id},function(rsp){
			if(rsp.status == 'success'){
				var announcement = rsp.rows[0];
				$("#announcementId").val(announcement.id);
				$("#title").textbox('setValue',announcement.title);
				$("#mainStatus").combobox('setValue',announcement.mainStatus);
				$("#description").textbox('setValue',announcement.description);
				//$("#subTitle").textbox('setValue',announcement.subtitle);
				$("#type").combobox('setValue',announcement.typeId);
				setTimeout(function(){
					MYEDITOR.setContent(announcement.content,false);
				},300);
				if(announcement.picPath){
					$('#upload_outer_div').empty();//清空附件DOM
					$("#filePaths").val(announcement.picPath);
					var dom = '<div id="pic"><a href="'+announcement.picPath+'" target="_blank"><img src="'+announcement.picPath+'" class="height80"/></a>'
					var operation = $("#operation").val();
					if(operation == "undefined"){
						dom +='<a id="picDelete" style="text-decoration: none;" href="javascript:deletePicPath()" class="delete" title="删除">删除</a></div>'
					}
					$('#upload_outer_div').append(dom);//添加到DOM中
				}
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
