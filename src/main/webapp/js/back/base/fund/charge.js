var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
IS_SHOW_TRASH = false;
TEMPDATA = null;
SAVING_FLAG = 0;
$(function() {
	GRID_QUERYOBJ = {};//查询条件
	loadGrid();
	initData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getChargeList',
	    pagination:true,//允许分页
		pageSize:20,//每页10条数据
		loadMsg:'',
		width:WIDTH-20,
		height:HEIGHT-125,
		idField:'id',
		sortName:'addTime',
		sortOrder:'DESC',
		queryParams:GRID_QUERYOBJ,
		fitColumns:false,
		selectOnCheck:false,
		checkOnSelect:false,
		singleSelect:true,
		striped:true,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
	        {field:'serial',title:'编号',width:120,align:'center'},
	        {field:'userSerial',title:'充值人编号',width:120,align:'center'},
	        {field:'userName',title:'充值人',width:120,align:'center'},
	        {field:'chargeMoney',title:'充值金额',width:120,align:'center'},
	        /*{field:'fee',title:'手续费',width:120,align:'center'},*/
	        {field:'createdAt',title:'充值时间',width:120,align:'center',formatter: function(value,row,index){
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
	/*$('#user').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    url:'/back/getUser'
	});*/
	$('#dealStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    data:[{
	    	'id' : 1,
	    	'name' : '未处理'
    	},{
    		'id' : 2,
    		'name' : '已处理'
    	}]
	});
}

/**根据查询条件查询匹配的数据*/
function doSearch(){
	var user = $("#user").textbox('getValue');
	var userName = $("#userName").textbox('getValue');
	var createdAt = $("#createdAt").datebox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.userSerial = user;
	common_queryObj.userName = userName;
	common_queryObj.createdAt = createdAt;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#user").textbox('setValue','');
	$("#userName").textbox('setValue','');
	$("#createdAt").datebox('setValue','');
}

function jumpToUserInfo(){
	var url = "/back/userInfo";
	/*var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}*/
	subShowMain('用户信息', url)
}
