/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'notice',
	mainObjUpperName : 'Notice',
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
			{field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){
				return  '<span class="opt_alive">'
//				+'<span style="cursor: pointer;color: #EC4949" onclick="openMainObjWin('+index+',\'update\')">修改</span>&nbsp;|&nbsp;'
				+'<span class="update" style="cursor: pointer;color: green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'
				+'<span class="opt_trash"><span style="cursor: pointer;color: #EC4949;" onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'
				+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
			}},
//			{field:'receiverId',title:'接收人ID',width:120,align:'center'},
			{field:'userName',title:'接收人',width:120,align:'center'},
			{field:'receiverIds',title:'接受的对象',width:120,align:'center'},
	        {field:'contentType',title:'消息类型',width:120,align:'center',formatter:function(value,row,index){
	        	if(value==1){value='文本消息'}else if(value==2){value='语音消息'}else if(value==3){value='视频消息'}else if(value==4){value='图片消息'}else if(value==5){value='位置消息'}else if(value==6){value='文件'}else{value="未分类"};
	        	return value;
	        }},
//	        {field:'senderId',title:'发送人ID',width:140,align:'center'},
	        {field:'senderName',title:'发送人',width:140,align:'center'},
	        {field:'title',title:'消息标题',width:240,align:'center'},
	        {field:'content',title:'消息内容',width:240,align:'center'},
	        {field:'linkUrl',title:'链接地址',width:200,align:'center',formatter: function(value,row,index){
	        	var linkUrl ='<a href="http://'+value+'" target="_blank">'+'http://'+value+'</a>'
	        	return linkUrl;
	        }},
	        {field:'sendTime',title:'发送时间',width:140,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'readStatus',title:'阅读状态',width:120,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '已读' : '<span style="color:orange;">未读</span>';
	        }},
	        {field:'readTime',title:'阅读时间',width:120,align:'center',formatter: function(value,row,index){
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
	lh.loadComboDataToCache({url:'/back/getUserArray', cacheName:'userArray', domId:'#sc_receiverName'});
}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	// 接受人ID
	$('#sc_receiverId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	// 发送人ID人
	$('#sc_senderId').combobox({
		valueField : 'id',
		textField : 'name',
		multiple:false,
		editable : false,
	    required:false,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	
	//发送人名
	$('#sc_senderName').combobox({
		valueField : 'id',
		textField : 'name',
		multiple:false,
	    required:false,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	
	$('#sc_contentType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
		data:[
		        {'id' : 1,'name' : '文本消息'},
		    	{'id' : 2,'name' : '语音消息'},
		    	{'id' : 3,'name' : '视频消息'},
		    	{'id' : 4,'name' : '图片消息'},
		    	{'id' : 5,'name' : '位置消息'},
		    	{'id' : 6,'name' : '文件'},
		    	{'id' : '','name' : '未分类'}
	    	]
	});

}

/** 初始化表单中的组件及数据 */
function initFormComponent(){
	//接Id
	$('#f_receiverId').combobox({
		valueField : 'id',
		textField : 'name',
		multiple:false,
	    required:true,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
//	//发送人名
//	$('#f_senderName').combobox({
//		valueField : 'id',
//		textField : 'name',
//		multiple:false,
//	    required:true,
//	    panelHeight:200,
//	    data:lh.getDataFromCache('userArray')
//	});
	//发送热ID
	$('#f_senderId').combobox({
		valueField : 'id',
		textField : 'name',
		multiple:false,
		editable : false,
	    required:true,
	    panelHeight:200,
	    data:lh.getDataFromCache('userArray')
	});
	
	//消息类型
	$('#f_contentType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
		data:[
		        {'id' : 1,'name' : '文本消息'},
		    	{'id' : 2,'name' : '语音消息'},
		    	{'id' : 3,'name' : '视频消息'},
		    	{'id' : 4,'name' : '图片消息'},
		    	{'id' : 5,'name' : '位置消息'},
		    	{'id' : 6,'name' : '文件'}
	    	]
	});
	
}


/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 开始 ========================================*/

//具体扩展方法列表，请查看 /js/common/back_template.js 文件中的顶部说明
//示例：function afterAddOrUpdate(mainObj, rsp){return true;} //执行新增或修改之前的拦截方法，返回false则不执行新增修改
//使用：将需要扩展的方法复制到对应业务JS文件中，在扩展方法体内执行自定义业务逻辑。

/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 结束 ========================================*/
//更新增加前执行的方法
function preAddOrUpdate(mainObj){
	
	if(!mainObj)return false;
//	mainObj.senderName=mainObj.senderId;
	if(mainObj.sendTime)mainObj.sendTime+=' 00:00:00';
	if(mainObj.expiryDate)mainObj.expiryDate += ' 00:00:00';
	return true;
}
