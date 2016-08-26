var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
MYEDITOR = null;
SAVING_FLAG = 0;
$(function(){
	MYEDITOR = UE.getEditor('editor');
	initData();
	pushData();
	$('#articleForm').form('disableValidation');
	addArticleForm();
	initUploadSimple({showDelBtn:true});//调用完整方法
});

function initData(){
	$('#type').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:400,
	    url:'/back/getArticleTypeList'
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
	$("#isShowIndex").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$("#isTop").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$("#isRecommend").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
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
	var domIds = "#title,#institution,#address,#startDate,#isRecommend,#isTop,"
		+"#endDate,#type,#mainStatus,#description,#isShowIndex,#picDelete";
	commonEditChangeToRead(domIds,'#editArticle','#addArticle,#browse',operation);
}

function editArticle(){
	var domIds = "#title,#institution,#address,#startDate,#isRecommend,#isTop,"
		+"#endDate,#type,#mainStatus,#description,#isShowIndex,#picDelete";
	commonReadChangeToEdit(domIds,'#addArticle,#browse','#editArticle');
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
function returnBack(typeId){
	window.location.href="/back/article.html?typeId="+typeId;
}

function addArticleForm(){
	var $form = $('#articleForm');
	$form.form({
	    url:'',
	    onSubmit: function(){
	    	//SAVING_FLAG = false;
	       $('#articleForm').form('enableValidation');
	       var flag = $('#articleForm').form('validate');
	       if(flag){
				var articleId = $("#articleId").val();
				var title = $("#title").textbox('getValue');
				//var subtitle = $("#subTitle").textbox('getValue');
				var typeId = $("#type").combobox('getValue');
				var mainStatus = $("#mainStatus").combobox('getValue');
				var catId = $("#catId").val();
				var content = MYEDITOR.getContent();
				var description = $("#description").textbox('getValue');
				var obj = {};
				obj.id = articleId;
				obj.catId = catId;
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
				if(!filePathArr[0]){$.messager.alert('提示','请上传封面');return;}
				obj.picPath = filePathArr[0];
				
				if(typeId == 41){
					var institution = $("#institution").textbox('getValue');
					var address = $("#address").textbox('getValue');
					var startDate = $("#startDate").datebox('getValue');
					var endDate = $("#endDate").datebox('getValue');
					var isShowIndex = $("#isShowIndex").combobox('getValue');
					obj.institution = institution;
					obj.address = address;
					obj.startDate = startDate;
					obj.endDate = endDate;
					obj.isShowIndex = isShowIndex;
				}else{
					var isTop = $("#isTop").combobox('getValue');
					var isRecommend = $("#isRecommend").combobox('getValue');
					obj.isTop = isTop;
					obj.isRecommend = isRecommend;
				}
				//SAVING_FLAG = true;	
				$.post('/back/addOrUpdateArticle',obj,function(rsp){
					backLoginCheck(rsp);//后台登陆检查
					//SAVING_FLAG = false;
					if(rsp.status == 'success'){
						$.messager.alert("提示", rsp.msg);
						//window.location.reload();
					}else{
						$.messager.alert('提示',rsp.msg);
					}
				},'json');
	       }
	       return false;
	    }
	});
}

function addArticle(){
	//$('#articleForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#articleForm').submit();//执行操作
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
				$("#articleId").val(article.id);
				$("#catId").val(article.catId);
				$("#title").textbox('setValue',article.title);
				$("#description").textbox('setValue',article.description);
				if(article.typeId == 41){
					$("#institution").textbox('setValue',article.institution);
					$("#address").textbox('setValue',article.address);
					$("#isShowIndex").combobox('setValue',article.isShowIndex);
					if(article.startDate){
						var d = new Date(article.startDate);
						var month = d.getMonth()+1;
				        var dateStr = ''+d.getFullYear()+'-'+month+'-'+d.getDate();
						$("#startDate").datebox('setValue',dateStr);
					}
					if(article.endDate){
						var d = new Date(article.endDate);
						var month = d.getMonth()+1;
				        var dateStr = ''+d.getFullYear()+'-'+month+'-'+d.getDate();
						$("#endDate").datebox('setValue',dateStr);
					}
				}
				$("#mainStatus").combobox('setValue',article.mainStatus);
				if(article.isRecommend){
					$("#isRecommend").combobox('setValue',article.isRecommend);
				}else{
					$("#isRecommend").combobox('setValue',1);
				}
				if(article.isTop){
					$("#isTop").textbox('setValue',article.isTop);
				}else{
					$("#isTop").combobox('setValue',1);
				}
				//$("#subTitle").textbox('setValue',article.subtitle);
				$("#type").combobox('setValue',article.typeId);
				setTimeout(function(){
					MYEDITOR.setContent(article.content,false);
				},300);
				if(article.picPath){
					$('#upload_outer_div').empty();//清空附件DOM
					$("#filePaths").val(article.picPath);
					var dom = '<div id="pic"><a href="'+article.picPath+'" target="_blank"><img src="'+article.picPath+'" class="height80"/></a>'
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
