/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'loginLog',
	mainObjUpperName : 'LoginLog',
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
	    pageSize:lh.grid.pageSize,//每页数据条数
	    pageList:lh.grid.pageList,//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-50,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
			{field:'username',title:'用户名',width:220,align:'center'},
	        {field:'loginTime',title:'登录时间',width:180,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'loginIp',title:'登录ip',width:200,align:'center'},
	        {field:'description',title:'描述',width:300,align:'center'},
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

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){

}

/** 初始化表单中的组件及数据 */
function initFormComponent(){

}


/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 开始 ========================================*/

//具体扩展方法列表，请查看 /js/common/back_template.js 文件中的顶部说明
//示例：function afterAddOrUpdate(mainObj, rsp){return true;} //执行新增或修改之前的拦截方法，返回false则不执行新增修改
//使用：将需要扩展的方法复制到对应业务JS文件中，在扩展方法体内执行自定义业务逻辑。

/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 结束 ========================================*/

