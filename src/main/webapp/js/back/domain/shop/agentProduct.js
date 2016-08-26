var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
IS_SHOW_TRASH = false;
SAVING_FLAG = 0;
$(function() {
	GRID_QUERYOBJ = {applyType:97};//查询条件
	loadGrid();
	initData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getApplyList',
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
			{field:'file1',title:'用户联系方式',width:220,align:'center'},
			{field:'file2',title:'用户详细详细地址',width:220,align:'center'},
			{field:'file3',title:'平台联系方式',width:220,align:'center'},
			{field:'file4',title:'总的费用',width:220,align:'center'},
	        {field:'attr1',title:'服务方式',width:220,align:'center',formatter:function(value,row,index){
	        	if(value == 1){
	        		return "送件上门";
	        	}else{
	        		return "服务人员到店取";
	        	}
	        }},
	        {field:'productTypeName',title:'产品类型',width:220,align:'center'},
	        {field:'attr3',title:'产品件数',width:220,align:'center'},
	        {field:'attr4',title:'每件产品拍摄张数',width:220,align:'center'},
	        //{field:'content',title:'内容',width:220,align:'center'},
	        {field:'operate',title:'操作',width:220,align:'center',formatter: function(value,row,index){
	        	return  '<span class="g_alive" ><span style="cursor: pointer;color: #EC4949" onclick="openApplyDetailWin('+index+',\'edit\')">修改</span>'
	        	+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="openApplyDetailWin('+index+',\'read\')">查看</span></span>'
	        	+'<span class="g_trash"><span  style="cursor: pointer;color: #EC4949" onclick="batchThoroughDelete('+row.id+')">彻底删除</span>'
	        	+'&nbsp;|&nbsp;<span  style="cursor: pointer;color: green" onclick="batchRecover('+row.id+')">恢复</span></span>';
	        }},
	        {field:'mainStatus',title:'状态',width:120,align:'center',formatter: function(value,row,index){
	        	var status = "已审核";
	        	if(value == 1){status = '<span style="color:orange">未审核</span>';}
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
	    	openApplyDetailWin(index, 'read');
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
	$("#mainStatus").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '未审核'
		},{
			'id' : 2,
			'name' : '已审核'
		}]
	});
	$("#serviceType").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '送件上门'
		},{
			'id' : 2,
			'name' : '服务人员到店取件'
		}]
	});
	$("#productType").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    url:'/back/getGoodsTypeList'
	});
}

/**根据查询条件查询匹配的数据*/
function doSearch(){
	var mainStatus = $("#mainStatus").combobox('getValue');
	var productType = $("#productType").combobox('getValue');
	var serviceType = $("#serviceType").combobox('getValue');
	
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.mainStatus = mainStatus;
	common_queryObj.serviceType = serviceType;
	common_queryObj.productType = productType;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#mainStatus").combobox('setValue','')
	$("#productType").combobox('setValue','')
	$("#serviceType").combobox('setValue','')
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
	
	$("#f_serviceType").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '送件上门'
		},{
			'id' : 2,
			'name' : '服务人员到店取件'
		}]
	});
	
	$("#f_productType").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    url:'/back/getGoodsTypeList'
	});
}


function openApplyDetailWin(index,operation){
	var rows,data;
	if(index >= 0){
		rows = $('#datagrid').datagrid('getRows');
		data = rows[index];//获取该行的数据
	}
	var $form = $('#applyDetailForm');
	
	$form.form({
	    url:'',
	    onSubmit: function(){
	       $('#applyDetailForm').form('enableValidation');
	       var flag = $('#applyDetailForm').form('validate');
	       if(flag){
	       		var id;
	       		if(operation != 'add')id = data.id;
	       		var apply = getApplyDetail(id);//得到需要的字段信息
	       	    $.post('/back/addOrUpdateApply',apply,function(rsp){
					 if(rsp.success){
						 $('#datagrid').datagrid('reload');
						 //$('#datagrid').datagrid('clearSelections');
						 //$('#datagrid').datagrid('clearChecked');
				       	 setTimeout(function(){
				       		closeApplyDetailWin();
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
	$('#applyDetailWin').window('open');
	$form.form('clear');
	$('#upload_outer_div').empty();//清空附件DOM
	$form.form('disableValidation');
	$('#dictDetailTip').html('');
	$('#browse').show();
	
	var domIds = "#f_mainStatus,#f_serviceType,#f_productType,#f_goodsNum,#f_num";
	
	if(operation == 'add'){//添加
		//$('#applyDetailTable .readOnlyTr').hide();//隐藏只读字段
		$('#applyDetailDisAgree,#applyDetailAgree').hide();
		$('#applyDetailSave,#applyDetailBack').show();
		$(domIds).textbox('readonly',false);//新增时设置为可编辑
	}else{//查看或修改
		//$('#applyDetailTable .readOnlyTr').show();//显示只读字段
		var isReadOnly = false;
		if(operation == 'read'){//查看
			isReadOnly = true;
			$('#applyDetailSave,#applyDetailBack').hide();
			$('#applyDetailDisAgree,#applyDetailAgree').show();
			$("#f_replay").textbox('readonly',false);
		}else{//修改
			$('#applyDetailSave,#applyDetailBack').show();
			$('#applyDetailDisAgree,#applyDetailAgree').hide();
			$("#f_replay").textbox('readonly',true);
		}
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
		setApplyDetail(data,operation);//设置用户详细信息字段值
	}
}

/** 得到相关信息的字段值 */
function getApplyDetail(id){
	var num = $('#f_num').textbox('getValue');
	var goodsNum = $('#f_goodsNum').textbox('getValue');
	var productTyp = $('#f_productType').combobox('getValue');
	var serviceType = $('#f_serviceType').combobox('getValue');
	var mainStatus = $('#f_mainStatus').combobox('getValue');
	var replay = $('#f_replay').combobox('getValue');
	var apply = {
		attr1:serviceType,
		attr2:productTyp,
		attr3:goodsNum,
		attr4:num,
		mainStatus:mainStatus,
		replay:replay
		
	};
	if(id){//有id为更新，无id为新增
		apply.id = id;
	}
	return apply;
}

/** 设置相关信息的字段值 */
function setApplyDetail(data,operation){
	
	$('#f_applyId').val(data.id);
	$('#f_num').textbox('setValue',data.attr4);
	$('#f_goodsNum').textbox('setValue',data.attr3);
	$('#f_mainStatus').combobox('setValue', data.mainStatus);
	$('#f_serviceType').combobox('setValue', data.attr1);
	$('#f_productType').combobox('setValue', data.attr2);
	//以下为只读字段：
	$('#f_replay').textbox('setValue',data.reply);
	if(data.mainStatus == 2){
		$("#applyDetailAgree,#applyDetailDisAgree").hide();
	}
}
//提交数据
function submitApplyDetail(){
	//$('#applyDetailForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#applyDetailForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}
//关闭窗口
function closeApplyDetailWin(){
	$('#applyDetailWin').window('close');
}

function applyDetailDisAgree(){
	//$('#applyDetailWin').window('close');
	//$("#disAgree").show();
	var id = $("#f_applyId").val();
	var reply = $("#f_replay").textbox('getValue');
	if(!reply){
		$.messager.alert('提示','请填写不同意的理由');
		return;
	}
	var apply = {};
	apply.id = id;
	apply.mainStatus = 2;
	//apply.applyType = 97;
	apply.reply = reply;
	apply.agree = 'no';
	commonAddData(apply);
}

function applyDetailAgree(){
	var id = $("#f_applyId").val();
	var apply = {};
	apply.id = id;
	apply.mainStatus = 2;
	apply.applyType = 97;
	apply.reply = '同意';
	apply.agree = 'yes';
	commonAddData(apply);
}

function commonAddData(apply){
	 $.post('/back/addOrUpdateApply',apply,function(rsp){
		 if(rsp.success){
			 $('#datagrid').datagrid('reload');
			 //$('#datagrid').datagrid('clearSelections');
			 //$('#datagrid').datagrid('clearChecked');
	       	 setTimeout(function(){
	       		closeApplyDetailWin();
	       	 },500);
	       	 if(apply.applyType == 97){
		       	 var url = "/back/goodsPicture";
		       	 subShowMain("产品库",url);
	       	 }
		 }else{
			 $.messager.alert('提示',rsp.msg);
		 }
	},'json');
}
/**批量删除数据**/
function batchDelete(){
	commonBatchDelete('/back/updateApplyDelete');//common_back:通用批量恢复
}

/**批量彻底删除**/
function batchThoroughDelete(id){
	commonBatchThoroughDelete('/back/deleteApplyThorough',null,id);//common_back:通用批量强制删除
}

/**批量恢复数据**/
function batchRecover(id){
	commonBatchRecover('/back/updateApplyRecover',null,id);//common_back:通用批量恢复
}

/**读取删除数据*/
function showTrash(){
	IS_SHOW_TRASH = true;
	commonShowTrash('#batchRecover,#batchThoroughDelete,#returnBack','#batchDelete,#showTrash');
}
/**读取未删除数据**/
function returnBack(){
	IS_SHOW_TRASH = false;
	commonReturnBack('#batchDelete,#showTrash','#batchRecover,#batchThoroughDelete,#returnBack',{applyType:97});
}

