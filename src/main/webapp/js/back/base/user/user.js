/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'user',
	mainObjUpperName : 'User'
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
			{field:'serial',title:'用户编号',width:160,align:'center',formatter: function(value,row,index){
				if(!value)return '';
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
	     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
	    		   '<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
	    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
	     }},
	        {field:'username',title:'用户名',width:140,align:'center'},
	        {field:'realName',title:'真实姓名',width:100,align:'center'},
	        {field:'avatar',title:'用户头像',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:60px;">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'lastLoginTime',title:'上次登陆时间',width:150,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "启用";
	        	if(value == 2){status = '<span style="color:orange">停用</span>';}
	        	return status;
	        }},
	        {field:'isRealAuth',title:'实名认证',width:80,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "未认证";
	        	if(value == 2){isRealAuth = '<span style="color:green">已认证</span>';}
	        	return isRealAuth;
	        }},
	        {field:'avaliableMoney',title:'可用金额',width:120,align:'center'},
	        {field:'frozenMoney',title:'冻结金额',width:120,align:'center'},
	        {field:'province',title:'地区',width:160,align:'center',formatter: function(value,row,index){
	        	var province = row.provinceName,city = row.cityName;
	        	if(!province){province = '';}
	        	if(!city){city = '';}/*else{city = ','+city}*/
	        	return province+city;
	        }},
	        {field:'email',title:'邮箱',width:180,align:'center'},
	        {field:'phone',title:'电话',width:120,align:'center'},
	        {field:'qq',title:'qq',width:120,align:'center'},
	        {field:'weixin',title:'微信',width:120,align:'center'},
	        {field:'createdAt',title:'注册时间',width:150,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
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
	
	$('#sc_mainStatus').combobox({
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
	
	$('#sc_isRealAuth').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '未认证'
		},{
			'id' : 2,
			'name' : '已认证'
		}]
	});
	
	$('#sc_city').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180
	    /*,onSelect: function(rec){
            var url = '/getCity?provinceId='+rec.id;
            $('#area').combobox('reload', url);
        }*/
	});

	$('#sc_province').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180,
		url:'/getProvince',
		onSelect: function(rec){
			var $city = $('#sc_city');
			$city.combobox('clear');
            var url = '/getCity?provinceId='+rec.id;
            $city.combobox('reload', url);
        }
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
	
	$('#f_mainStatus').combobox({
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
	
	$('#f_isRealAuth').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '未认证'
		},{
			'id' : 2,
			'name' : '已认证'
		}]
	});
	
	$('#f_city').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180
	    /*,onSelect: function(rec){
            var url = '/getCity?provinceId='+rec.id;
            $('#area').combobox('reload', url);
        }*/
	});

	$('#f_province').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180,
		url:'/getProvince',
		onSelect: function(rec){
			var $city = $('#f_city');
			$city.combobox('clear');
            var url = '/getCity?provinceId='+rec.id;
            $city.combobox('reload', url);
        }
	});
}

function afterOpenWin(data, operation, isReadOnly){
	
	if(!data){
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#pic").attr('src', null);
		return;
	}
	$("#pic").attr('src', data.avatar);
	$("#filePaths").val(data.logo);
	$("#fileDBIds").val(data.logoPicId);
	
	var province = $('#f_province').combobox('getValue');//更新市
	var city = $('#f_city').combobox('getValue');
    var url = '/back/getCity?provinceId='+province;
    $('#f_city').combobox('reload', url);
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
		mainObj.avatar = filePaths;
		mainObj.logoPicId = ids;
	}
	mainObj.lastLoginTime+=" 00:00:00";
	mainObj.createdAt+=" 00:00:00";
	return true;
}

/** 跳转：用户资金 */
function jumpToUserMoney(){
	var url = "/back/page/userFund";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	lh.subShowMain('用户资金', url)
}

/** 跳转：用户控制 */
function jumpToUserControl(){
	var url = "/back/page/userControl";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	lh.subShowMain('用户控制', url)
}


