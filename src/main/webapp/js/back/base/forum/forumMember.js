/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'forum_member',
	mainObjUpperName : 'ForumMember',
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
	        {field:'forumLogo',title:'图片',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:60px;">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'userId',title:'用户id',width:80,align:'center'},
	        {field:'forumId',title:'论坛id',width:80,align:'center'},
	        {field:'forumName',title:'论坛名',width:120,align:'center'},
//	        {field:'userSerial',title:'用户标识',width:120,align:'center'},
	        {field:'username',title:'用户名',width:120,align:'center'},
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
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
	$('#f_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : true,
	    panelHeight : 200,
	    url:'/back/getusernameArray'
	});
//	$('#f_forumId').combobox({
//		valueField : 'id',
//		textField : 'name',
//		editable : false,
//		multiple : false,
//		required : false,
//		filter: lh.comboboxDefaultFilter,
//		panelHeight : 200,
//		url : "/back/getForumArray",
//	});
//	$('#f_userId').combobox({
//		valueField : 'id',
//		textField : 'name',
//		editable : false,
//		multiple : false,
//		filter: lh.comboboxDefaultFilter,
//	    required : false,
//	    panelHeight : 200,
//	    url:'/back/getusernameArray'
//	});
}

function onClickRowOfGrid(){}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	
$('#sc_forumId').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
	required : false,
	filter: lh.comboboxDefaultFilter,
	panelHeight : 200,
	url : "/back/getForumArray",
});

	$('#sc_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : false,
		filter: lh.comboboxDefaultFilter,
	    panelHeight : 200,
	    url:'/back/getusernameArray'
	});

}



/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	var filePaths = $("#filePaths").val();
	if(!filePaths){
		//$.messager.alert('提示',"请上传用户头像"); return;
	}else{
		var ids = lh.idsStr;
		if(filePaths.substring(0,1) != "/"){
			filePaths = filePaths.substring(1);
			ids = ids.substring(1);
		}
		mainObj.avatar = filePaths;
		mainObj.avatarPicId = ids;
	}
//	var passwordReset = $('#passwordReset').textbox('getValue');
//	if(passwordReset)mainObj.passwordReset = passwordReset;
	
	return true;
}

//}


//function exportUser(){
//	var obj = lh.getQueryObj();
//	delete obj.ascOrdesc;
//	window.location.href = '/back/userExcel?obj='+obj;
//}
