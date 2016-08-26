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
	    url:'/back/getAccountLogList',
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
	        {field:'serial',title:'编号',width:220,align:'center'},
	        {field:'payUserSerial',title:'支付人编号',width:220,align:'center'},
	        {field:'username',title:'支付人用户名',width:220,align:'center'},
	        {field:'money',title:'支付费用',width:220,align:'center'},
	        {field:'avaliableMoneyLog',title:'可用资金',width:220,align:'center'},
	        {field:'frozenMoneyLog',title:'冻结资金',width:220,align:'center'},
	        {field:'payTime',title:'支付时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }},
	        {field:'receiveUserSerial',title:'接收人编号',width:220,align:'center',formatter:function(value,row,index){
	        	if(value == null){
	        		return '平台收取'
	        	}else{
	        		return value;
	        	}
	        }},
	        {field:'theOtherName',title:'接收人用户名',width:220,align:'center',formatter:function(value,row,index){
	        	if(value == null){
	        		return '平台收取'
	        	}else{
	        		return value;
	        	}
	        }},
	        {field:'dictName',title:'交易类型',width:220,align:'center'},
	        {field:'inOrOut',title:'支出/收入',width:220,align:'center',formatter: function(value,row,index){
	        	if(value == 1){
	        		return '收入';
	        	}else{
	        		return '支出';
	        	}
	        }},
	        {field:'tradeStatus',title:'交易状态',width:220,align:'center',formatter: function(value,row,index){
	        	if(value == 1){
	        		return '冻结';
	        	}else if(value == 2){
	        		return '解冻';
	        	}else if(value == 3){
	        		return '实际支出';
	        	}else if(value == 4){
	        		return '实际收入';
	        	}else if(value == 5){
	        		return '冻结资金中支出';
	        	}
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
	/*$('#payUser').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    url:'/back/getUser'
	});
	$('#receiveUser').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:200,
		url:'/back/getUser'
	});*/
	$('#tradeType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:200,
		url:'/back/getTradeTypeList'
	});
	$('#InOrOut').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data:[{
		    	'id' : 1,
		    	'name' : '收入'
	    	},{
	    		'id' : 2,
	    		'name' : '支出'
	    	}]
	});
	$('#tradeStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:'auto',
		data:[{
			'id' : 1,
			'name' : '冻结'
		},{
			'id' : 2,
			'name' : '解冻'
		},{
			'id' : 3,
			'name' : '实际支出'
		},{
			'id' : 4,
			'name' : '实际收入'
		},{
			'id' : 5,
			'name' : '冻结资金中支出'
		}]
	});
}

/**根据查询条件查询匹配的数据*/
function doSearch(){
	var payUserSerial = $("#payUserSerial").textbox('getValue');
	var InOrOut = $("#InOrOut").combobox('getValue');
	var receiveUserSerial = $("#receiveUserSerial").textbox('getValue');
	var tradeType = $("#tradeType").combobox('getValue');
	var tradeStatus = $("#tradeStatus").combobox('getValue');
	var payTime = $("#payTime").datebox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.payUserSerial = payUserSerial;
	common_queryObj.InOrOut = InOrOut;
	common_queryObj.tradeTypeId = tradeType;
	common_queryObj.tradeStatus = tradeStatus;
	common_queryObj.payTime = payTime;
	common_queryObj.receiveUserSerial = receiveUserSerial;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#payUserSerial").textbox('setValue','');
	$("#InOrOut").combobox('setValue','');
	$("#receiveUserSerial").textbox('setValue','');
	$("#tradeType").combobox('setValue','');
	$("#tradeStatus").combobox('setValue','');
	$("#payTime").datebox('setValue','');
}

function jumpToUserInfo(){
	var url = "/back/userInfo";
	/*var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}*/
	subShowMain('用户信息', url)
}