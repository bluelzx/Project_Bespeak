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
	    url:'/back/getWithdrawList',
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
	        {field:'userSerial',title:'提现人编号',width:120,align:'center'},
	        {field:'userName',title:'提现人用户名',width:120,align:'center'},
	        {field:'applyMoney',title:'提现金额',width:120,align:'center'},
	        {field:'applyRealMoney',title:'实提金额',width:120,align:'center'},
	        {field:'fee',title:'手续费',width:120,align:'center'},
	        {field:'applyDate',title:'申请提现时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }},
	        {field:'attr2',title:'处理意见',width:220,align:'center'},
	        {field:'operate',title:'操作',width:160,align:'center',formatter: function(value,row,index){
	        	return  '<span  style="cursor: pointer;color: green" onclick="dealWin('+index+')">处理</span>';
	        }},
	        {field:'dealDate',title:'处理时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }},
	        {field:'dealStatus',title:'状态',width:120,align:'center',formatter: function(value,row,index){
	        	if(value == 1){
	        		return '未处理';
	        	}else{
	        		return '已处理';
	        	}
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
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
	var userSerial = $("#userSerial").textbox('getValue');
	var dealStatus = $("#dealStatus").combobox('getValue');
	var applyDate = $("#applyDate").datebox('getValue');
	var dealDate = $("#dealDate").datebox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.userSerial = userSerial;
	common_queryObj.applyDate = applyDate;
	common_queryObj.dealDate = dealDate;
	common_queryObj.dealStatus = dealStatus;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#userSerial").textbox('setValue','');
	$("#dealStatus").combobox('setValue','');
	$("#applyDate").datebox('setValue','');
	$("#dealDate").datebox('setValue','');
}

/** 关闭窗口 */
function closeAgreeDetailWin(){
	$('#agreeDetailWin').window('close');
}

function dealWin(index){
	$('#agreeDetailWin').window('open');
	$("#attr2").textbox('setValue','');
	var rows,data;
	if(index >= 0){
		rows = $('#datagrid').datagrid('getRows');
		data = rows[index];//获取该行的数据
	}
	 $("#Id").val(data.id);
}

function submitAgreeDetail(){
	var id = $("#Id").val();
	var attr2 = $("#attr2").textbox('getValue');
	if(!attr2){
		$.messager.alert('提示','请填写处理意见');
		return;
	}
	$.post('/back/addOrUpdateWithdraw',{id:id,attr2:attr2},function(rsp){
		 if(rsp.success){
			 $('#datagrid').datagrid('reload');
	       	 setTimeout(function(){
	       		closeAgreeDetailWin();
	       	 },500);
		 }else{
			 $.messager.alert('提示',rsp.msg);
		 }
	},'json');
}

function jumpToUserInfo(){
	var url = "/back/userInfo";
	/*var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}*/
	subShowMain('用户信息', url)
}
