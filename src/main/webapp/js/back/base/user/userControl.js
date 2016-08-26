/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'userControl',
	mainObjUpperName : 'UserControl'
}

$(function() {
	loadGrid();
	initQueryComponent();
	initComboData();
});

/**加载用户控制列表*/
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
			{field:'userSerial',title:'用户编号',width:160,align:'center',formatter: function(value,row,index){
				if(!value)return '';
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'username',title:'用户名',width:140,align:'center'},
	        {field:'realName',title:'真实姓名',width:150,align:'center'},
	        {field:'operate',title:'操作',width:200,align:'center',formatter: function(value,row,index){return '<span class="g_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
    		   '<span class="g_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
            }},
	        {field:'controlTypeId',title:'控制类型',width:150,align:'center',formatter: function(value,row,index){
	        	var status = "临时-禁止访问首页";
	        	if(value == 2){status = '<span style="color:orange">临时-禁止论坛留言</span>';}
	        	return status;
	        }},
	        {field:'startDate',title:'起效时间',width:150,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'endDate',title:'失效时间',width:150,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'createdAt',title:'添加时间',width:150,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'createdBy',title:'添加人',width:150,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:140,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:150,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'deletedBy',title:'删除人',width:140,align:'center'}
	        ]],
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
/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	$('#sc_controlTypeId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
//	    url:'/back/getUserControlTypeList'
		data : [{
			'id' : 1,
			'name' : '临时-禁止访问首页'
		},{
			'id' : 2,
			'name' : '临时-禁止论坛留言'
		}]
	});

}

/** 初始化下拉列表数据，存入缓存，便于复用 */
function initComboData(){
	lh.loadComboDataToCache({url:'/back/getRootDictArray',cacheName:'rootDictArray', domId:'#sc_parentCode'});
}

/** 初始化表单中的组件及数据 */
function initFormComponent(){
	$('#f_controlTypeId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
//	    url:'/back/getUserControlTypeList'
	    data : [{
			'id' : 1,
			'name' : '临时-禁止访问首页'
		},{
			'id' : 2,
			'name' : '临时-禁止论坛留言'
		}]
	});
}
function searchUser(){
	lh.searchUserBySerial();//common_back:通用跟据用户编号查询用户
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	mainObj.startDate+=" 00:00:00";
	mainObj.endDate+=" 00:00:00";
//    var controlTypeId=$("#f_controlType").combobox("getValues");
//	mainObj.controlTypeId=controlTypeId;
	return true;
}

/** 跳转：用户资金 */
function jumpToUserMoney(){
	var url = "/back/userFund";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	lh.subShowMain('用户资金', url)
}

/** 跳转：用户控制 */
function jumpToUserInfo(){
	var url = "/back/userInfo";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	lh.subShowMain('用户信息', url)
}

