/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'comment',
	mainObjUpperName : 'Comment',
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
	    pageSize:10,//每页数据条数
	    pageList:[3,5,10,20,50],//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-122,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
	        {field:'comUserName',title:'评论用户',width:120,align:'center'},
	        {field:'providerName',title:'被评论技师',width:120,align:'center'},
	        {field:'goodsName',title:'被评论服务',width:120,align:'center'},
	        {field:'content',title:'消息内容',width:240,align:'center'},
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
	lh.loadComboDataToCache({url:'/back/getprovidernameArray', cacheName:'providerArray', domId:'#sc_receiverId'});
	lh.loadComboDataToCache({url:'/back/getUserArray', cacheName:'userArray', domId:'#sc_userId'});
	lh.loadComboDataToCache({url:'/back/getgoodsnameArray', cacheName:'goodsArray', domId:'#sc_objectId'});
}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	
	$('#sc_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:180,
	    data:lh.getDataFromCache('userArray')
	});
	$('#sc_objectId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:180,
	    data:lh.getDataFromCache('goodsArray')
	});
	
	$('#sc_receiverId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:180,
	    data:lh.getDataFromCache('providerArray')
	});
	
}

/** 初始化表单中的组件及数据 */
function initFormComponent(){
	$('#f_objectId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:180,
		data:lh.getDataFromCache('goodsArray')
	});
	
	$('#f_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:180,
	    data:lh.getDataFromCache('userArray')
	});
	
	$('#f_receiverId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:180,
	    data:lh.getDataFromCache('providerArray')
	});
	
}


/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 开始 ========================================*/

//具体扩展方法列表，请查看 /js/common/back_template.js 文件中的顶部说明
//示例：function afterAddOrUpdate(mainObj, rsp){return true;} //执行新增或修改之前的拦截方法，返回false则不执行新增修改
//使用：将需要扩展的方法复制到对应业务JS文件中，在扩展方法体内执行自定义业务逻辑。

/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 结束 ========================================*/


