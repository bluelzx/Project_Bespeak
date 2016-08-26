/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'message',
	mainObjUpperName : 'Message',
}

$(function() {
	loadGrid();
	initQueryComponent();
	initComboData();
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
	    pageSize:lh.grid.pageSize,//每页数据条数
	    pageList:lh.grid.pageList,//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-122,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
	        {field:'senderSerial',title:'发送人编号',width:120,align:'center'},
	        {field:'senderName',title:'发送人',width:120,align:'center'},
	        {field:'receiverSerial',title:'接收人编号',width:120,align:'center'},
	        {field:'receiverName',title:'接收人',width:120,align:'center'},
	        {field:'messageType',title:'消息类型',width:120,align:'center',formatter:function(value,row,index){
	        	return value ? value : '系统消息';
	        }},
	        {field:'content',title:'消息内容',width:240,align:'center'},
	        {field:'createdAt',title:'发送时间',width:140,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'readStatus',title:'阅读状态',width:120,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '已读' : '<span style="color:orange;">未读</span>';
	        }},
	        {field:'readTime',title:'阅读时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
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
	    /*,onSelect:function(rowIndex, rowData){onClickRowOfGrid(rowIndex, rowData);},
	    onClickRow: function(index, row){onClickRowOfGrid(index, row);},
	    loadFilter: function(data){return loadFilterOfGrid(data);}*/
        onLoadError: function(data){
        	lh.onGridLoadError(data);
	    },
	    onDblClickRow: function(index, row){
	    	lh.onGridDblClickRow(index, row);
        },
	    onLoadSuccess:function(data){
	    	lh.onGridLoadSuccess(data);
	    }  
	});
}

/** 初始化下拉列表数据，存入缓存，便于复用 */
function initComboData(){
	lh.loadComboDataToCache({url:'/back/getUserArray', cacheName:'userArray', domId:'#sc_senderId'});
	lh.loadComboDataToCache({url:'/back/getUserArray', cacheName:'userArray', domId:'#sc_receiverId'});
	lh.loadComboDataToCache({url:'/back/getDictArrayByParentCode?parentCode=message_type', cacheName:'messageTypeArray', domId:'#sc_typeCode'});
}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	
	$('#sc_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:200,
		data:lh.getDataFromCache('messageTypeArray')
	});
	
	$('#sc_senderId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	
	$('#sc_receiverId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	
}

/** 初始化表单中的组件及数据 */
function initFormComponent(){
	$('#f_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:200,
		data:lh.getDataFromCache('messageTypeArray')
	});
	
	$('#f_senderId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	
	$('#f_receiverId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	
}


/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 开始 ========================================*/

//具体扩展方法列表，请查看 /js/common/back_template.js 文件中的顶部说明
//示例：function afterAddOrUpdate(mainObj, rsp){return true;} //执行新增或修改之前的拦截方法，返回false则不执行新增修改
//使用：将需要扩展的方法复制到对应业务JS文件中，在扩展方法体内执行自定义业务逻辑。

/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 结束 ========================================*/


