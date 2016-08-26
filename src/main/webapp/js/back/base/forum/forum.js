/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'forum',
	mainObjUpperName : 'Forum'
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
			{field:'logo',title:'logo',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:60px;">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'name',title:'论坛名',width:120,align:'center'},
//	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
//	        	return value == 1 ? '<span style="color:orange">启用</span>' : '停用';
//	        }},
//	        {field:'gradeId',title:'是否热门',width:60,align:'center',formatter: function(value,row,index){
//	        	return value == 1 ? '<span style="color:orange">热门</span>' : '不热门';
//	        }},
	    	{field:'memberCount',title:'人数',width:80,align:'center'},
	    	{field:'articleMemberCount',title:'帖子数',width:80,align:'center'},
	        {field:'visitNum',title:'访问数',width:80,align:'center'},
	        {field:'parentName',title:'上级论坛',width:80,align:'center'},
	        {field:'moderatorName',title:'版主',width:80,align:'center'},
	        {field:'subModeratorName',title:'副版主',width:80,align:'center'},
	        {field:'mainPath',title:'论坛首页',width:140,align:'center',formatter: function(value,row,index){
	        	var mainPath ='<a href="http://'+value+'" target="_blank">'+'http://'+value+'</a>'
	        	return mainPath;
	        }},
	        {field:'ruleDesc',title:'版规描述',width:80,align:'center'},
	        {field:'typeDesc',title:'类型描述',width:80,align:'center'},
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
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

function onClickRowOfGrid(){}

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
	
	
	//取上级论坛
	$('#f_parentId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		url : "/back/getForumArray",
	});
	//取版主
	$('#f_moderatorId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		url : "/back/getusernameArray",
	});
	///副版主
	$('#f_subModeratorId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 200,
		url : "/back/getusernameArray",
	});
	
	
//	$('#f_mainStatus').combobox({
//		valueField : 'id',
//		textField : 'name',
//		editable : false,
//		multiple : false,
//	    required : false,
//	    panelHeight : 'auto',
//		data : [{
//			'id' : 1,
//			'name' : '启用'
//		},{
//			'id' : 2,
//			'name' : '停用'
//		}]
//	});
	
}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	
	//版主选择来自用户
	$('#sc_moderatorId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : true,
		multiple : false,
		required : false,
		filter: lh.comboboxDefaultFilter,
		panelHeight : 200,
		url : "/back/getusernameArray",
	});
	//取所有论坛上级
	$('#sc_parentId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : true,
		multiple : false,
		required : false,
		filter: lh.comboboxDefaultFilter,
		panelHeight : 200,
		url : "/back/getForumArray",
	});

	$('#sc_ascOrdesc').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : false,
		panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '用户名升序'
		},{
			'id' : 2,
			'name' : '用户名降序'
		},{
			'id' : 3,
			'name' : '注册时间升序'
		},{
			'id' : 4,
			'name' : '注册时间降序'
		}]
	});
	
	/*	
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
	*/
}


/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	var filePaths = $("#filePaths").val();
	if(!filePaths){
		//$.messager.alert('提示',"请上传用户头像"); return;
	}else{
		var ids = UPLOAD_OBJ.idsStr;
		if(filePaths.substring(0,1) != "/"){
			filePaths = filePaths.substring(1);
			ids = ids.substring(1);
		}
		mainObj.avatar = filePaths;
		mainObj.avatarPicId = ids;
	}
	
	if(mainObj && mainObj.birthday){
		mainObj.birthday += ' 00:00:00';
	}
	
//	var passwordReset = $('#passwordReset').textbox('getValue');
//	if(passwordReset)mainObj.passwordReset = passwordReset;
//	
	return true;
}
/*
function afterOpenWin(data, operation, isReadOnly){
	if(!data)return;
	$("#pic").attr('src', data.avatar);
	$("#filePaths").val(data.avatar);
	$("#fileDBIds").val(data.avatarPicId);
	
	var province = $('#f_province').combobox('getValue');//更新市
	var city = $('#f_city').combobox('getValue');
    var url = '/back/getCityArray?provinceId='+province;
    $('#f_city').combobox('reload', url);
    
    if(data.birthday){
    	$('#f_birthday').datebox('setValue', lh.formatDate({date:new Date(data.birthday)}) );
    }
}*/



//function exportUser(){
//	var obj = lh.getQueryObj();
//	delete obj.ascOrdesc;
//	window.location.href = '/back/userExcel?obj='+obj;
//}
