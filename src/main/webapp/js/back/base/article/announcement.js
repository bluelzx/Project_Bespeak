/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'announcement',
	mainObjUpperName : 'Announcement',
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
	    pageSize:10,//每页数据条数
	    pageList:[3,5,10,20,50],//每页数据条数选择数组
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
	        {field:'senderName',title:'发布人',width:120,align:'center'},
	        {field:'receiverGroupId',title:'接受组',width:120,align:'center'},
	        {field:'title',title:'标题',width:120,align:'center'},
	        {field:'subject',title:'主题',width:120,align:'center'},
	        {field:'content',title:'内容',width:120,align:'center'},
	        {field:'linkUrl',title:'公告链接',width:200,align:'center',formatter: function(value,row,index){
	        	var linkUrl ='<a href="http://'+value+'" target="_blank">'+'http://'+value+'</a>'
	        	return linkUrl;
	        }},
	        {field:'sendTime',title:'发布时间',width:140,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'expiryDate',title:'有效期',width:140,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }}
//	        {field:'receiverId',title:'接受人ID',width:120,align:'center'},
//	        {field:'anonymous',title:'公告状态',width:120,align:'center',formatter: function(value,row,index){
//        	return value == 2 ? '<span style="color:orange;">匿名</span>' : '未匿名';
//        }},
//	        {field:'contentType',title:'公告类型',width:120,align:'center',formatter: function(value,row,index){
//		        	return if(value == 1){"文本公告"}else if(value == 2){"语音公告"}else if(value == 3){"视频公告"}else if(value == 4){"图片公告"}else if(value == 5){"位置公告"}else{"文件"}}},
//	        {field:'readStatus',title:'阅读状态',width:120,align:'center',formatter: function(value,row,index){
//	        	return value == 2 ? '已读' : '<span style="color:orange;">未读</span>';
//	        }},
//	        {field:'readTime',title:'阅读时间',width:120,align:'center',formatter: function(value,row,index){
//	        	return lh.formatGridDateTime(value);
//	        }},
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
//	if(!lh.hasInit){
//		lh.initUploadSimple({showEdBtns:true,showItemDiv:true,multiFlag:false,multiReplace:true,
//			successFun:function(fileId, filePath){
//				$("#upld_container_"+fileId).remove();
//				$("#picPaths").attr('src', filePath);
//		}});
//	}
	
	$("#upload_outer_div").empty();
	
//	$('#f_senderId').combobox({
//		valueField : 'id',
//		textField : 'name',
//		editable : false,
//		multiple : false,
//		required : false,
//		panelHeight : 200,
//		url : "/back/getusernameArray",
//	});
	// 接受组
//	$('#f_receiverGroupId').combobox({
//		valueField : 'id',
//		textField : 'name',
//		editable : false,
//		multiple : false,
//	    required : true,
//	    panelHeight : 200,
//	    url:'/back/getusernameArray'
//	});
}

function onClickRowOfGrid(){}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	
$('#sc_senderId').combobox({
	valueField : 'id',
	textField : 'name',
	editable : false,
	multiple : false,
	required : false,
	filter: lh.comboboxDefaultFilter,
	panelHeight : 200,
	url : "/back/getusernameArray",
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
		'name' : '有效期升序'
	},{
		'id' : 2,
		'name' : '有效期降序'
	},{
		'id' : 3,
		'name' : '发布时间升序'
	},{
		'id' : 4,
		'name' : '发布时间降序'
	}]
});

//接受组
//	$('#sc_receiverGroupId').combobox({
//		valueField : 'id',
//		textField : 'name',
//		editable : false,
//		multiple : false,
//		required : false,
//		filter: lh.comboboxDefaultFilter,
//	    panelHeight : 200,
//	    url:'/back/getusernameArray'
//	});

}



/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	//  尾加字段匹配到po的格式
//	if(mainObj && mainObj.expiryDate){
//		mainObj.expiryDate += ' 00:00:00';
//	}
//	mainObj.senderName="1111";
//	alert(mainObj.username);
	if(mainObj.sendTime!=null && mainObj.sendTime!=''){
		
	}else{
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		var hour = date.getHours();
		var minute = date.getMinutes();
		var second = date.getSeconds();
		mainObj.sendTime = (year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second);
	}
//	if(mainObj.sendTime==null && mainObj.sendTime==''){
//		mainObj.linkUrl=("http://"+mainObj.linkUrl);
//	}else{
//	
//	}
	return true;
}

//function exportUser(){
//	var obj = lh.getQueryObj();
//	delete obj.ascOrdesc;
//	window.location.href = '/back/userExcel?obj='+obj;
//}
