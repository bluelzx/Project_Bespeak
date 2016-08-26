var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
IS_SHOW_TRASH = false;
SAVING_FLAG = 0;
$(function() {
	GRID_QUERYOBJ = {};//查询条件
	loadGrid();
	initData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getAdminList',
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
	        
	        {field:'username',title:'用户名',width:220,align:'center'},
	        {field:'nickname',title:'昵称',width:220,align:'center'},
	        {field:'phone',title:'手机号码',width:220,align:'center'},
	        {field:'email',title:'邮箱',width:220,align:'center'},
	        {field:'qq',title:'qq',width:220,align:'center'},
	        {field:'weixin',title:'微信',width:220,align:'center'},
	        {field:'operate',title:'操作',width:160,align:'center',formatter: function(value,row,index){
	        	return  '<span class="g_alive"><span  style="cursor: pointer;color: #EC4949" onclick="openAdminDetailWin('+index+',\'update\')">修改</span>'
	        	+'&nbsp;|&nbsp;<span class="update" style="cursor: pointer;color: green" onclick="openAdminDetailWin('+index+',\'read\')">查看</span></span>'
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
	    onDblClickRow: function(index, row){
	    	openAdminDetailWin(index, 'read');
        },
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
	$('#mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});
}

function loadCombo(){
	$('#f_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]
	});
}

/**根据查询条件查询匹配的数据*/
function doSearch(){
	var username = $("#username").textbox('getValue');
	var nickname = $("#nickname").textbox('getValue');
	var phone = $("#phone").textbox('getValue');
	var email = $("#email").textbox('getValue');
	var qq = $("#qq").textbox('getValue');
	var weixin = $("#weixin").textbox('getValue');
	var mainStatus = $("#mainStatus").combobox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.username = username;
	common_queryObj.nickname = nickname;
	common_queryObj.phone = phone;
	common_queryObj.email = email;
	common_queryObj.qq = qq;
	common_queryObj.weixin = weixin;
	common_queryObj.mainStatus = mainStatus;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#username").textbox('setValue','');
	$("#nickname").textbox('setValue','');
	$("#phone").textbox('setValue','');
	$("#email").textbox('setValue','');
	$("#qq").textbox('setValue','');
	$("#weixin").textbox('setValue','');
	$("#mainStatus").combobox('setValue','');
}

/**添加数据*/
function addAdmin(){
	openAdminDetailWin(-1,'add');
}

function openAdminDetailWin(index,operation){
	var rows,data;
	if(index >= 0){
		rows = $('#datagrid').datagrid('getRows');
		data = rows[index];//获取该行的数据
	}
	var $form = $('#adminDetailForm');
	
	$form.form({
	    url:'',
	    onSubmit: function(){
	       $('#adminDetailForm').form('enableValidation');
	       var flag = $('#adminDetailForm').form('validate');
	       if(flag){
	       		var id;
	       		if(operation != 'add')id = data.id;
	       		var admin = getAdminDetail(id);//得到需要的字段信息
	       	    $.post('/back/addOrUpdateAdmin',admin,function(rsp){
					 if(rsp.success){
						 $('#datagrid').datagrid('reload');
						 //$('#datagrid').datagrid('clearSelections');
						 //$('#datagrid').datagrid('clearChecked');
				       	 setTimeout(function(){
				       		closeAdminDetailWin();
				       	 },500);
					 }else{
						 $.messager.alert('提示',rsp.msg);
					 }
				},'json');
	       }
	       return false;
	    }
	});
	
	loadCombo();
	$('#adminDetailWin').window('open');
	$form.form('clear');
	$form.form('disableValidation');
	$('#adminDetailTip').html('');
	
	var domIds = "#f_username,#f_nickname,#f_phone,#f_email,"
	+"#f_qq,#f_weixin,#f_role,#f_avatar,#f_mainStatus";
	
	if(operation == 'add'){//添加
		$('#adminDetailSave').show();
		$('#adminDetailTable .readOnlyTr').hide();//隐藏只读字段
		$(domIds).textbox('readonly',false);//新增时设置为可编辑
	}else{//查看或修改
		$('#adminDetailTable .readOnlyTr').show();//显示只读字段
		var isReadOnly = false;
		if(operation == 'read'){//查看
			isReadOnly = true;
			$('#adminDetailSave').hide();
		}else{//修改
			$('#adminDetailSave').show();
		}
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
		setAdminDetail(data);//设置用户详细信息字段值
	}
}

/** 得到相关信息的字段值 */
function getAdminDetail(id){
	var username = $('#f_username').textbox('getValue');
	var nickname = $('#f_nickname').textbox('getValue');
	var phone = $('#f_phone').textbox('getValue');
	var email = $('#f_email').textbox('getValue');
	var qq = $('#f_qq').textbox('getValue');
	var weixin = $('#f_weixin').textbox('getValue');
	var mainStatus = $('#f_mainStatus').combobox('getValue');
	
	var admin = {
		username:username,
		nickname:nickname,
		mainStatus:mainStatus,
		phone:phone,
		email:email,
		qq:qq,
		weixin:weixin
		
	};
	if(id){//有id为更新，无id为新增
		admin.id = id;
	}
	return admin;
}

/** 设置相关信息的字段值 */
function setAdminDetail(data){
	var mainStatus = data.mainStatus;
	if(!mainStatus)mainStatus = 1;
	$('#f_username').textbox('setValue',data.username);
	$('#f_nickname').textbox('setValue',data.nickname);
	$('#f_phone').textbox('setValue',data.phone);
	$('#f_email').textbox('setValue',data.email);
	$('#f_qq').textbox('setValue',data.qq);
	$('#f_weixin').textbox('setValue',data.weixin);
	$('#f_mainStatus').combobox('setValue', mainStatus);
	//以下为只读字段：
}
//提交数据
function submitAdminDetail(){
	//$('#adminDetailForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#adminDetailForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}
//关闭窗口
function closeAdminDetailWin(){
	$('#adminDetailWin').window('close');
}


/**批量删除数据**/
function batchDelete(){
	commonBatchDelete('/back/updateAdminDelete');//common_back:通用批量恢复
}

/**批量彻底删除**/
function batchThoroughDelete(id){
	commonBatchThoroughDelete('/back/deleteAdminThorough',null,id);//common_back:通用批量强制删除
}

/**批量恢复数据**/
function batchRecover(id){
	commonBatchRecover('/back/updateAdminRecover',null,id);//common_back:通用批量恢复
}

/**读取删除数据*/
function showTrash(){
	IS_SHOW_TRASH = true;
	commonShowTrash('#batchRecover,#batchThoroughDelete,#returnBack','#batchDelete,#addAdmin,#showTrash');
}
/**读取未删除数据**/
function returnBack(){
	IS_SHOW_TRASH = false;
	commonReturnBack('#batchDelete,#addAdmin,#showTrash','#batchRecover,#batchThoroughDelete,#returnBack');
}

