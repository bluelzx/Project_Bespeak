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
	    url:'/back/getChatQuickList',
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
	        {field:'serial',title:'编号',width:160,align:'center'},
	        {field:'senderSerial',title:'发送人编号',width:120,align:'center'},
	        {field:'senderName',title:'发送人',width:120,align:'center'},
	        {field:'content',title:'消息内容',width:420,align:'center'},
	        {field:'sendTime',title:'发送时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }},
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
	/*$('#sender').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    url:'/back/getUser'
	});*/
}

/**根据查询条件查询匹配的数据*/
function doSearch(){
	var senderSerial = $("#senderSerial").combobox('getValue');
	var content = $("#content").textbox('getValue');
	var sendTime = $("#sendTime").datebox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.senderSerial = senderSerial;
	common_queryObj.sendTime = sendTime;
	common_queryObj.content = content;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#sender").combobox('setValue','');
	$("#content").textbox('setValue','');
	$("#sendTime").datebox('setValue','');
}

function jumpToUserInfo(){
	var url = "/back/userInfo";
	/*var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}*/
	subShowMain('用户信息', url)
}
