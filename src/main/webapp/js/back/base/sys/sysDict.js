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
	    url:'/back/getSysDictList',
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
	        {field:'dictName',title:'字典名称',width:220,align:'center'},
	        {field:'dictValue',title:'字典值',width:220,align:'center'},
	        {field:'parentSysDictName',title:'字典类型',width:220,align:'center'},
	        {field:'operate',title:'操作',width:160,align:'center',formatter: function(value,row,index){
	        	return  '<span class="g_alive"><span  style="cursor: pointer;color: #EC4949" onclick="openSysDictDetailWin('+index+',\'update\')">修改</span>'
	        	+'&nbsp;|&nbsp;<span class="update" style="cursor: pointer;color: green" onclick="openSysDictDetailWin('+index+',\'read\')">查看</span></span>'
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
	    	openSysDictDetailWin(index, 'read');
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
	$("#sc_type").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:400,
	    url:'/back/getSysDictParentTypeList'
	});
}

function loadCombo(){
	$("#f_sysDictType").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:200,
	    url:'/back/getSysDictParentTypeList'
	});
	
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

/**批量删除数据**/
function batchDelete(){
	commonBatchDelete('/back/updateSysDictDelete');//common_back:通用批量恢复
}

/**批量彻底删除**/
function batchThoroughDelete(id){
	commonBatchThoroughDelete('/back/deleteSysDictThorough',null,id);//common_back:通用批量强制删除
}

/**批量恢复数据**/
function batchRecover(id){
	commonBatchRecover('/back/updateSysDictRecover',null,id);//common_back:通用批量恢复
}

/**添加数据*/
function addSysDict(){
	openSysDictDetailWin(-1,'add');
}

function openSysDictDetailWin(index,operation){
	var rows,data;
	if(index >= 0){
		rows = $('#datagrid').datagrid('getRows');
		data = rows[index];//获取该行的数据
	}
	var $form = $('#sysDictDetailForm');
	
	$form.form({
	    url:'',
	    onSubmit: function(){
	       $('#sysDictDetailForm').form('enableValidation');
	       var flag = $('#sysDictDetailForm').form('validate');
	       if(flag){
	       		var id;
	       		if(operation != 'add')id = data.id;
	       		var sysDict = getSysDictDetail(id);//得到需要的字段信息
	       	    $.post('/back/addOrUpdateSysDict',sysDict,function(rsp){
					 if(rsp.success){
						 $('#datagrid').datagrid('reload');
						 //$('#datagrid').datagrid('clearSelections');
						 //$('#datagrid').datagrid('clearChecked');
				       	 setTimeout(function(){
				       		closeSysDictDetailWin();
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
	$('#sysDictDetailWin').window('open');
	$form.form('clear');
	$form.form('disableValidation');
	$('#sysDictDetailTip').html('');
	
	var domIds = "#f_dictName,#f_dictValue,#f_dictType,#f_mainStatus";
	
	if(operation == 'add'){//添加
		$('#sysDictDetailTable .readOnlyTr').hide();//隐藏只读字段
		$('#sysDictDetailSave').show();
		$(domIds).textbox('readonly',false);//新增时设置为可编辑
	}else{//查看或修改
		$('#sysDictDetailTable .readOnlyTr').show();//显示只读字段
		var isReadOnly = false;
		if(operation == 'read'){//查看
			isReadOnly = true;
			$('#sysDictDetailSave').hide();
		}else{//修改
			$('#sysDictDetailSave').show();
		}
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
		setSysDictDetail(data);//设置用户详细信息字段值
	}
}

/** 得到相关信息的字段值 */
function getSysDictDetail(id){
	var dictName = $('#f_dictName').textbox('getValue');
	var dictValue = $('#f_dictValue').textbox('getValue');
	var mainStatus = $('#f_mainStatus').combobox('getValue');
	var dictType = $('#f_sysDictType').combobox('getValue');
	
	var sysDict = {
		dictName:dictName,
		dictValue:dictValue,
		mainStatus:mainStatus,
		dictType:dictType
	};
	if(id){//有id为更新，无id为新增
		sysDict.id = id;
	}
	return sysDict;
}

/** 设置相关信息的字段值 */
function setSysDictDetail(data){
	var mainStatus = data.mainStatus;
	if(!mainStatus)mainStatus = 1;
	$('#f_dictName').textbox('setValue',data.dictName);
	$('#f_dictValue').textbox('setValue',data.dictValue);
	$('#f_mainStatus').combobox('setValue', mainStatus);
	if(data.parentId){
		$('#f_sysDictType').combobox('setValue', data.parentId);
	}else{
		$('#f_sysDictType').combobox('setValue', data.id);
	}	
	//以下为只读字段：
}
//提交数据
function submitSysDictDetail(){
	//$('#sysDictDetailForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#sysDictDetailForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}
//关闭窗口
function closeSysDictDetailWin(){
	$('#sysDictDetailWin').window('close');
}


/**读取删除数据*/
function showTrash(){
	IS_SHOW_TRASH = true;
	commonShowTrash('#batchRecover,#batchThoroughDelete,#returnBack','#batchDelete,#addSysDict,#showTrash');

}
/**读取未删除数据**/
function returnBack(){
	IS_SHOW_TRASH = false;
	commonReturnBack('#batchDelete,#addSysDict,#showTrash','#batchRecover,#batchThoroughDelete,#returnBack');

}

/**根据查询条件查询匹配的数据*/
function doSearch(){
	
	var dictName = $("#name").textbox('getValue');
	var id = $("#sc_type").combobox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.id = id;
	common_queryObj.dictName = dictName;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#name").textbox('setValue','');
	$("#sc_type").combobox('setValue','');
}
