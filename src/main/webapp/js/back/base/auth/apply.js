/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'apply',
	mainObjUpperName : 'Apply'
}

$(function() {
	loadGrid();
	initQueryComponent();
	initComboData();
});


/**加载用户列表*/
function loadGrid(){
	lh.$grid.datagrid({
		loadMsg:'',
		idField:'id',
		sortName:'addTime',
		sortOrder:'DESC',
		fitColumns:false,
		selectOnCheck:false,
		checkOnSelect:false,
		singleSelect:true,
		striped:true,
		pagination:true,
		url:lh.config.gridUrl,
	    queryParams:lh.config.queryObj,//查询参数
	    pageSize:lh.grid.pageSize,//每页数据条数
	    pageList:lh.grid.pageList,//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-122,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
	        {field:'userSerial',title:'',hidden:true},
	        {field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){
				return  '<span class="opt_alive"><span style="cursor: pointer;color: #EC4949" onclick="openMainObjWin('+index+',\'update\')">修改</span>'
				+'&nbsp;|&nbsp;<span class="update" style="cursor: pointer;color: green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'
				+'<span class="opt_trash"><span style="cursor: pointer;color: #EC4949;" onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'
				+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
			}},
			{field:'userName',title:'申请用户',width:120,align:'center'},
			{field:'applyType',title:'申请类型',width:120,align:'center',formatter: function(value,row,index){
		        	var status = "加盟商认证";
		        	if(value == 2){status = '<span style="color:orange">申请新的培训课程</span>';}
		        	if(value == 3){status = '<span style="color:gray">月嫂申请</span>';}
		        	if(value == 4){status = '<span style="color:blue">企业优惠申请</span>';}
		        	if(value == 5){status = '<span style="color:red">技师申请</span>';}
		        	return status;
		        }},
	        {field:'titleNames',title:'申请标题名',width:160,align:'center'},
//	        {field:'ShopName',title:'申请加盟店名',width:168,align:'center'},
	        {field:'phone',title:'手机电话',width:200,align:'center'},
	        {field:'address',title:'地址',width:200,align:'center'},
	        {field:'file1',title:'附件地址',width:200,align:'center'},
	        {field:'typeCode',title:'类型Code值',width:200,align:'center'},
	        {field:'sex',title:'性别',width:100,align:'center'},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "未处理";
	        	if(value == 2){status = '<span style="color:orange">已同意</span>';}
	        	if(value == 3){status = '<span style="color:orange">未同意</span>';}
	        	return status;
	        }},
	        {field:'description',title:'备注说明',width:200,align:'center'},
	       
	        {field:'realName',title:'申请用户真实姓名',width:120,align:'center'},
	        {field:'instId',title:'申请机构(对象)',width:180,align:'center'},
	        {field:'applyDate',title:'申请时间',width:160,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'message',title:'申请内容',width:220,align:'center'},
	        //{field:'reply',title:'回复消息',width:220,align:'center'},
	        {field:'deletedBy',title:'删除人',width:220,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);;
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

function initFormComponent(){
	$('#f_applyType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:200,
	    url:'/back/getApplyTypeList'
	});
	$('#agreeType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:true,
	    panelHeight:200,
	    url:'/back/getApplyTypeList'
	});
}

function initQueryComponent(){
	$('#sc_type').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getApplyTypeList'
	});
	$('#sc_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data:[{
		    	'id' : 1,
		    	'name' : '未处理'
	    	},{
	    		'id' : 2,
	    		'name' : '已同意'
	    	},{
	    		'id' : 3,
	    		'name' : '未同意'
	    	}]
	});
}



function initComboData(){


}
function searchUser(){
	lh.searchUserBySerial();//common_back:通用跟据用户编号查询用户
}
function preAddOrUpdate(mainObj){
	mainObj.applyDate += " 00:00:00";
}
//同意申请
function applyDetailAgree(){
	var rows = lh.$grid.datagrid('getSelected');
	var id=rows.id;
	var reply=$("#f_reply").val();
	var userId=$("#f_userId").val();
	var applyType=$("#f_applyType").combobox("getValue");
	if(applyType==1){
		//机构认证
		//企业优惠申请
		lh.post('back', '/back/addOrUpdateShopApply', {id:id,mainStatus:2,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==2){
		//培训申请
		lh.post('back', '/back/addOrUpdateCourseCouponApply', {id:id,mainStatus:2,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==3){
		//月嫂认证
		lh.post('back', '/back/addOrUpdatePeopleApply', {id:id,mainStatus:2,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==4){
		//企业优惠申请
		lh.post('back', '/back/addOrUpdateEnterpriseCouponApply', {id:id,mainStatus:2,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==5){
		//技师申请
		lh.post('back', '/back/addOrUpdateProviderApply', {id:id,mainStatus:2,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}
	closeMainObjWin();
	loadGrid();
}
function afterOpenWin(data, operation, isReadOnly){
	var rows = lh.$grid.datagrid('getSelected');
	$("#f_serial").textbox('setValue',rows.userSerial)
	 $("#f_username").textbox('setValue',rows.userName)
	 $("#f_realName").textbox('setValue',rows.realName)
	 $("#f_userId").attr("value",data.userId)
	return true;
	}

// 不同意申请
function applyDetailDisAgree(){
	var rows = lh.$grid.datagrid('getSelected');
	var id=rows.id;
	var reply=$("#f_reply").val();
	var userId=$("#f_userId").val();
	var applyType=$("#f_applyType").combobox("getValue");
	if(applyType==1){
		//机构认证
		//企业优惠申请
		lh.post('back', '/back/addOrUpdateShopApply', {id:id,mainStatus:3,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==2){
		//培训申请
		lh.post('back', '/back/addOrUpdateCourseCouponApply', {id:id,mainStatus:3,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==3){
		//月嫂认证
		lh.post('back', '/back/addOrUpdatePeopleApply', {id:id,mainStatus:3,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==4){
		//企业优惠申请
		lh.post('back', '/back/addOrUpdateEnterpriseCouponApply', {id:id,mainStatus:3,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}else if(applyType==5){
		//技师申请
		lh.post('back', '/back/addOrUpdateProviderApply', {id:id,mainStatus:3,userId:userId,replyDesc:reply}, function(rsp) {
		}, 'json');
	}
	closeMainObjWin();
	loadGrid();
}

function jumpToUserInfo(){
	var url = "/back/page/userInfo";
	lh.subShowMain("用户信息",url);
	
}

