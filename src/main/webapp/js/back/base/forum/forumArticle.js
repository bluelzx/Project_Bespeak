/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'forum_article',
	mainObjUpperName : 'ForumArticle',
	queryObj:{isLink:1}
}

$(function() {
	loadGrid();
	initQueryComponent();
});

/** 加载主表格 */
function loadGrid(){
	lh.$grid.datagrid({
	    loadMsg:'',
		idField:'id',
		sortName:'id',
		sortOrder:'desc',
		striped:true,
		fitColumns:false,
		singleSelect:true,
		selectOnCheck:false,
		checkOnSelect:false,
		pagination:true,
		url:lh.config.gridUrl,
	    queryParams:lh.config.queryObj,//查询参数
	    pageSize:6,//每页数据条数
	    pageList:[2,4,6,8,10],//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-125,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
			{field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){
				return  '<span class="opt_alive"><span style="cursor: pointer;color: #EC4949" onclick="openMainObjWin('+index+',\'update\')">修改</span>'
				+'&nbsp;|&nbsp;<span class="update" style="cursor: pointer;color: green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'
				+'<span class="opt_trash"><span style="cursor: pointer;color: #EC4949;" onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'
				+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
			}},
			{field:'picPaths',title:'图片',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value){logo = '<span style="line-height:60px;">暂无图片<span>';}
	        	else{
	        		var ary=value.split(",");
	        		logo='<a href="'+ary[0]+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+ary[0]+'"/></a>';
	        		}
	        	return logo;
	        }},
	        {field:'forumName',title:'论坛名',width:120,align:'center'},
	        {field:'title',title:'帖子标题',width:120,align:'center'},
	        {field:'subTitle',title:'副标题',width:120,align:'center'},
	        {field:'description',title:'描述',width:120,align:'center'},
	        {field:'createdBy',title:'发帖人',width:120,align:'center'},
//	       数量取值关联表 {field:'praiseNum',title:'点赞数',width:120,align:'center'},
//	   数量取值关联表     {field:'showArticleCount',title:'评论数',width:120,align:'center'},
	        {field:'visitNum',title:'查看次数',width:120,align:'center'},
	        {field:'isEssence',title:'是否精华',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'isTop',title:'是否置顶',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'isHot',title:'是否热门',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'isRecommend',title:'是否推荐',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'typeCode',title:'帖子类型',width:60,align:'center',formatter: function(value,row,index){
	        	return value != '' ? (value == 'huati' ? '<span style="color:orange">话题</span>' : '晒好物'):'';
	        }},
//	        {field:'typeCode',title:'帖子类型',width:120,align:'center'},
	        {field:'remark',title:'备注',width:120,align:'center'},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 1 ? '<span style="color:orange">启用</span>' : '停用';
	        }},
	        
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }}
	    ]],
        onLoadError: function(data){
	    	lh.backDatagridErrorCheck(data);
	    },
	    onDblClickRow: function(index, row){
	    	openMainObjWin(index, 'read');
        },
	    onLoadSuccess:function(data){
	    	lh.filtGridOperation();
	    	lh.clipboard();//复制功能
	   }  
	});
}

///** 初始化下拉列表数据，存入缓存，便于复用 */



/** 初始化表单中的组件及数据 */
function initFormComponent(){
	if(!lh.hasInit){
		lh.initUploadSimple({showEdBtns:true,showItemDiv:true,multiFlag:false,multiReplace:true,
			successFun:function(fileId, filePath){
				$("#upld_container_"+fileId).remove();
				$("#picPaths").attr('src', filePath);
		}});
	}
	
	$("#upload_outer_div").empty();
	
	$('#f_forumId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		url : "/back/getForumArray",
	});
	$('#f_forumName').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		url : "/back/getForumArray",
	});
	
		$('#f_username').combobox({
			valueField : 'id',
			textField : 'name',
			editable : false,
			multiple : false,
		    required : true,
		    panelHeight : 200,
		    url:'/back/getusernameArray'
		});

	$('#f_isTop').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$('#f_isEssence').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : true,
	    panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$('#f_isHot').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$('#f_isRecommend').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : true,
	    panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$('#f_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : false,
	    panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});
	$('#f_typeCode').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : true,
	    panelHeight : 'auto',
		data : [{
			'id' : 'huati',
			'name' : '话题'
		},{
			'id' : 'shaihaowu',
			'name' : '晒好物'
		},{'id':'','name':''}]
	});
	
}

function onClickRowOfGrid(){}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	$('#sc_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : false,
	    panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});



//$('#sc_forumId').combobox({
//	valueField : 'id',
//	textField : 'name',
//	editable : true,
//	multiple : false,
//	filter: lh.comboboxDefaultFilter,
//	panelHeight : 200,
//	url : "/back/getForumArray",
//});

//	$('#sc_username').combobox({
//		valueField : 'id',
//		textField : 'name',
//		editable : false,
//		multiple : false,
//		filter: lh.comboboxDefaultFilter,
//	    panelHeight : 200,
//	    url:'/back/getusernameArray'
//	});

$('#sc_isTop').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
	required : false,
	panelHeight : 'auto',
	data : [{
		'id' : 1,
		'name' : '否'
	},{
		'id' : 2,
		'name' : '是'
	}]
});
$('#sc_isEssence').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
    required : false,
    panelHeight : 'auto',
	data : [{
		'id' : 1,
		'name' : '否'
	},{
		'id' : 2,
		'name' : '是'
	}]
});
$('#sc_isHot').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
	required : false,
	panelHeight : 'auto',
	data : [{
		'id' : 1,
		'name' : '否'
	},{
		'id' : 2,
		'name' : '是'
	}]
});
$('#sc_isRecommend').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
    required : false,
    panelHeight : 'auto',
	data : [{
		'id' : 1,
		'name' : '否'
	},{
		'id' : 2,
		'name' : '是'
	}]
});
$('#sc_typeCode').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
    required : false,
    panelHeight : 'auto',
	data : [{
		'id' : 'huati',
		'name' : '话题'
	},{
		'id' : 'shaihaowu',
		'name' : '晒好物'
	},{'id':'','name':''}]
});
//排序
$('#sc_ascOrdesc').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
	required : false,
	panelHeight : 'auto',
	data : [{
		'id' : 1,
		'name' : '用户ID升序'
	},{
		'id' : 2,
		'name' : '用户ID降序'
	},{
		'id' : 3,
		'name' : '注册时间升序'
	},{
		'id' : 4,
		'name' : '注册时间降序'
	}]
});
}
/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
//更新增加前执行的方法
function preAddOrUpdate(mainObj){
	
	if(!mainObj)return false;
//	alert($('#f_username').combobox('getText'));
//	var i=$('#f_username').combobox('getText');
//	mainObj.username=$('#f_username').combobox('getText');
//	mainObj.senderName=mainObj.senderId;
	return true;
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
//function preAddOrUpdate(mainObj){
//	Integer ii=0;
//	mainObj.showArticleCount=null;
//	mainObj.praiseNum=null;
//	mainObj.praiseType=null;
//	mainObj.forumName=null;

//	mainObj.praiseNum=ii;
//	mainObj.praiseType=ii;
//	mainObj.forumName=ii;
//	return true;
//	var filePaths = $("#filePaths").val();
//	if(!filePaths){
//		//$.messager.alert('提示',"请上传用户头像"); return;
//	}else{
//		var ids = UPLOAD_OBJ.idsStr;
//		if(filePaths.substring(0,1) != "/"){
//			filePaths = filePaths.substring(1);
//			ids = ids.substring(1);
//		}
//		mainObj.avatar = filePaths;
//		mainObj.avatarPicId = ids;
//	}
//	
//	if(mainObj && mainObj.birthday){
//		mainObj.birthday += ' 00:00:00';
//	}
//	
//	var passwordReset = $('#passwordReset').textbox('getValue');
//	if(passwordReset)mainObj.passwordReset = passwordReset;
//	
//	return true;
//}
//    
//    if(data.birthday){
//    	$('#f_birthday').datebox('setValue', lh.formatDate({date:new Date(data.birthday)}) );
//    }
	
	
	
//}





//function exportUser(){
//	var obj = lh.getQueryObj();
//	delete obj.ascOrdesc;
//	window.location.href = '/back/userExcel?obj='+obj;
//}
