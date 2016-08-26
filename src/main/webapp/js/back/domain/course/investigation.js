/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'investigation',
	mainObjUpperName : 'Investigation'
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
			{field:'serial',title:'申请编号',width:160,align:'center',formatter: function(value,row,index){
				if(!value)return '';
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'courseName',title:'报名课程',width:100,align:'center'},
	        {field:'username',title:'姓名',width:100,align:'center'},
	        {field:'userAvatar',title:'用户头像',width:220,align:'center',formatter:function(value,row,index){
	        	var userAvatar = '<a href="'+value+'" target="_blank"><img style="height:40px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)userAvatar = '<span style="line-height:40px;">暂无图标<span>';
	        	return userAvatar;
	        }},
	        {field:'operate',title:'操作',width:200,align:'center',formatter: function(value,row,index){
	        	return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
    		   '<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
            }},
	        {field:'userPhone',title:'联系电话',width:120,align:'center'},
	        {field:'userAge',title:'年龄',width:120,align:'center'},
	        {field:'userSex',title:'性别',width:80,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "男";
	        	if(value == 2){isRealAuth = '<span style="color:green">女</span>';}
	        	return isRealAuth;
	        }},
	        {field:'applyTime',title:'申请时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }}
	        ]],
	    /*,onSelect:function(rowIndex, rowData){onClickRowOfGrid(rowIndex, rowData);},
	    onClickRow: function(index, row){},
	    loadFilter: function(data){return data;},
		onLoadSuccess:function(data){} */
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
	lh.clipboard();//复制功能
	
}

/** 初始化下拉列表数据，存入缓存，便于复用 */
function initComboData(){
	lh.loadComboDataToCache({url:'/back/getRootDictArray',cacheName:'rootDictArray', domId:'#sc_parentCode'});
}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	$('#sc_shopId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getShopArray'
	});
	$('#sc_userSex').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '男'
		},{
			'id' : 2,
			'name' : '女'
		}]
	});
	$('#sc_linkId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getcoursenameArray'
	});

}
//初始化组件
function initFormComponent(){
	initUploadSimple({showEdBtns:true,showItemDiv:true,multiFlag:false,multiReplace:true,
		successFun:function(fileId, filePath){
			$("#upld_container_"+fileId).remove();
			$("#pic").attr('src', filePath);
	}});
	$("#upload_outer_div").empty();
	
	$('#f_linkId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getcoursenameArray'
	});
	$('#f_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getusernameArray',
		 onSelect: function(rec){
			 lh.post('front', '/back/getUserList?id='+rec.id, function(rsp) {
		    		var data=rsp.rows;
		    		var brithday=lh.formatGridDateTime(data[0].birthday).substring(0,4);
		    		var date = new Date().getFullYear();
		    		var age =date-brithday;
		    		if(data[0].realName!=null&&data[0].realName!=""){
		    		$("#f_username").textbox("setValue",data[0].realName);
		    		}else{
		    			$("#f_username").textbox("setValue",data[0].username);
		    		}
		    		$("#f_userPhone").textbox("setValue",data[0].phone);
		    		$("#f_userSex").combobox("setValue",data[0].sex);
		    		$("#f_userAge").textbox("setValue",age);
		    		
		    		//$("#f_userAge").textbox("setValue",data[0].avatar);
		    	}, 'json');
		 }
	});
	$('#f_userSex').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '男'
		},{
			'id' : 2,
			'name' : '女'
		}]
	});
}

function afterOpenWin(data, operation, isReadOnly){
	if(!data){
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#pic").attr('src', null);
		return;
	}
	$("#pic").attr('src', data.logo);
	$("#filePaths").val(data.logo);
	$("#fileDBIds").val(data.logoPicId);
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	var filePaths = $("#filePaths").val();
	if(!filePaths){
		//$.messager.alert('提示',"请上传用户头像"); return;
	}else{
		var ids = UPLOAD_OBJ.idsStr;
		if(filePaths.substring(0,1) != "/"){
			filePaths = filePaths.substring(1);
			ids = ids.substring(1);
		}
		mainObj.userAvatar = filePaths;
		mainObj.logoPicId = ids;
	}
	mainObj.applyTime+=" 00:00:00";
//	mainObj.createdAt+=" 00:00:00";
	return true;
}

/** 跳转：用户资金 */
function jumpToProviderMoney(){
	var url = "/back/page/providerFund";
	var id = $('#providerId').val();
	if(id){
		url += "?providerId="+id;
	}
	subShowMain('用户资金', url)
}

/** 跳转：用户控制 */
function jumpToProviderControl(){
	var url = "/back/providerControl";
	var id = $('#providerId').val();
	if(id){
		url += "?providerId="+id;
	}
	subShowMain('用户控制', url)
}


