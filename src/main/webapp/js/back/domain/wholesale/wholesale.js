var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
IS_SHOW_TRASH = false;
SAVING_FLAG = 0;
$(function() {
	GRID_QUERYOBJ = {};//查询条件haveGoods:1
	loadGrid();
	initData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getWholesaleList',
	    pagination:true,//允许分页
		pageSize:20,//每页10条数据
		loadMsg:'',
		width:WIDTH-20,
		height:HEIGHT-125,
		idField:'id',
		sortName:'addTime',
		sortOrder:'DESC',
		queryParams:GRID_QUERYOBJ,
		fitColumns:true,
		selectOnCheck:true,
		checkOnSelect:true,
		singleSelect:true,
		striped:true,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
	        {field:'userName',title:'用户名',width:220,align:'center'},
	        {field:'name',title:'商店名称',width:220,align:'center'},
	        {field:'logo',title:'商店logo',width:220,align:'center',formatter:function(value,row,index){
	        	var string = "";
	        	if(value){
	        		var string = '<div style="margin:5px;"><a target="_blank" href="'+value+'"><img src="'+value+'" style="width:60px;height:50px;"/></a><div>';
	        	}
				return  string;
	        }},
	        {field:'operate',title:'操作',width:160,align:'center',formatter: function(value,row,index){
	        	return  '<span class="g_alive"><span  style="cursor: pointer;color: #EC4949" onclick="updateWholesale('+row.id+')">修改</span></span>'
	        	+'<span class="g_trash"><span  style="cursor: pointer;color: #EC4949;" onclick="batchThoroughDelete('+row.id+')">彻底删除</span>'
	        	+'&nbsp;|&nbsp;<span  style="cursor: pointer;color: green" onclick="batchRecover('+row.id+')">恢复</span></span>';
	        }},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "启用";
	        	if(value == 2){status = '<span style="color:orange">停用</span>';}
	        	return status;
	        }},
	        {field:'deletedBy',title:'删除人',width:220,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }},
	        {field:'createdBy',title:'创建人',width:220,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }}
	    ]],
	    /*onSelect:function(rowIndex, rowData){
	    	onClickRowOfGrid(rowIndex, rowData);*//**当点击表格中的某行数据时执行*//*
	    },
	    onClickRow: function(index, row){
			
		},
	    loadFilter: function(data){
	    	return data;
	    },*/
	    onLoadError: function(data){
	    	backDatagridErrorCheck(data);
	    },
		onLoadSuccess:function(data){
			if(IS_SHOW_TRASH){
	       		$('.g_alive').hide();
	       		$('.g_trash').show();
	       	}else{
	       		$('.g_alive').show();
	       		$('.g_trash').hide();
	       	}
		}
	});
}

function onClickRowOfGrid(){

}

function initData(){
	
	$('#user').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    url:'/back/getUser'
	});
}

/**跳转到修改页面*/
function updateWholesale(id){
	window.location.href = "/back/wholesaleAddOrUpdate?id="+id;
}
/**添加数据*/
function jumpToAddWholesale(){
	window.location.href = "/back/wholesaleAddOrUpdate";
}

/**批量删除数据**/
function batchDelete(){
	commonBatchDelete('/back/updateWholesaleDelete');//common_back:通用批量恢复
}

/**批量彻底删除**/
function batchThoroughDelete(id){
	commonBatchThoroughDelete('/back/deleteWholesaleThorough',null,id);//common_back:通用批量强制删除
}

/**批量恢复数据**/
function batchRecover(id){
	commonBatchRecover('/back/updateWholesaleRecover',null,id);//common_back:通用批量恢复
}

/**读取删除数据*/
function showTrash(){
	IS_SHOW_TRASH = true;
	commonShowTrash('#batchRecover,#batchThoroughDelete,#returnBack','#batchDelete,#jumpToAddWholesale,#showTrash');
}
/**读取未删除数据**/
function returnBack(){
	IS_SHOW_TRASH = false;
	commonReturnBack('#batchDelete,#jumpToAddWholesale,#showTrash','#batchRecover,#batchThoroughDelete,#returnBack');
}

/** 跳转：用户信息 */
function jumpToUserInfo(){
	var url = "/back/userInfo";
	subShowMain('用户信息', url);
}

/** 跳转：用户控制 */
function jumpToGoods(){
	var url = "/back/goods";
	subShowMain('店铺', url);
}