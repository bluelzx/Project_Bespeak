/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'provider',
	mainObjUpperName : 'Provider'
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
	        {field:'username',title:'用户名',width:140,align:'center'},
	        {field:'realName',title:'真实姓名',width:100,align:'center'},
	        {field:'avatar',title:'用户头像',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:60px;">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'operate',title:'操作',width:200,align:'center',formatter: function(value,row,index){
	        	return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
    		   '<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
            }},
	        /*{field:'lastLoginTime',title:'上次登陆时间',width:100,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},*/
            {field:'sex',title:'性别',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "男";
	        	if(value == 2){status = '<span style="color:orange">女</span>';}
	        	return status;
	        }},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "空闲";
	        	if(value == 2){status = '<span style="color:orange">服务中</span>';}
	        	return status;
	        }},
	        {field:'isRealAuth',title:'实名认证',width:80,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "未认证";
	        	if(value == 2){isRealAuth = '<span style="color:green">已认证</span>';}
	        	return isRealAuth;
	        }},
	        {field:'avaliableMoney',title:'可用金额',width:120,align:'center'},
	        {field:'frozenMoney',title:'冻结金额',width:120,align:'center'},
	        {field:'shopName',title:'店铺',width:140,align:'center'},
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
	        {field:'createdAt',title:'注册时间',width:120,align:'center',formatter: function(value,row,index){
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
	$('#sc_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '空闲'
		},{
			'id' : 2,
			'name' : '服务中'
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
	$('#f_shopId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getShopArray'
	});
	$('#f_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:true,
		required:false,
		panelHeight:'auto',
		url:'/back/getProviderTypeList?parentCode='+"goods_type"
	});
	
	$('#f_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '空闲'
		},{
			'id' : 2,
			'name' : '服务中'
		}]
	});
	$('#f_sex').combobox({
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
		url:'/back/getProvince',
		onSelect: function(rec){
			var $city = $('#f_city');
			$city.combobox('clear');
            var url = '/back/getCity?provinceId='+rec.id;
            $city.combobox('reload', url);
        }
	});
}
var jj = '';
function uploadPic(browse,i){
	initUploadSimple({
			showEdBtns : true,
			showItemDiv : true,
			multiFlag : true,
			multiReplace : true,
			chooseBtnDomId:browse,
			successFun : function(fileId, filePath) {
				var pic='<img  class="picurl" src='+filePath+' style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" />';
				$("#certificates"+i).append(pic);
				jj += ","+filePath;
			}
		});	
	if(jj.substring(0,1) == ","){
		jj = jj.substring(1);
	}
		$("#filePaths"+i).val(jj);
}
function afterOpenWin(data, operation, isReadOnly){
	if(!data){
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#certificates1").empty();
		$("#certificates2").empty();
		return;
	}
	var pic='<img  class="picurl" src='+data.avatar+' style="height: 60px; max-width: 100px; overflow: hidden; padding: 5px;" />';
	$("#certificates1").append(pic);
	$("#filePaths1").val(data.avatar);
	$("#filePaths2").val(data.picPath);
	$('#f_typeCode').combobox('setValues',data.typeCode);
	var province = $('#f_province').combobox('getValue');//更新市
	var city = $('#f_city').combobox('getValue');
    var url = '/back/getCity?provinceId='+province;
    $('#f_city').combobox('reload', url);
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	// 技师头像
	var filePaths = $("#filePaths1").val();
	if (!filePaths) {
	} else {
		var ids = UPLOAD_OBJ.idsStr;
		if (filePaths.substring(0, 1) != "/") {
			filePaths = filePaths.substring(1);
			ids = ids.substring(1);
		}
		mainObj.avatar = filePaths;
		// mainObj.logoPicId = ids;
	}
	// 技师证件
	var filePaths1 = $("#filePaths2").val();
	if (!filePaths1) {
	} else {
		var ids = UPLOAD_OBJ.idsStr;
		if (filePaths1.substring(0, 1) != "/") {
			filePaths1 = filePaths1.substring(1);
			ids = ids.substring(1);
		}
		mainObj.picPath = filePaths1;
	}
	var typeAry = $('#f_typeCode').combobox('getValues');
	var typeCode="";
	for(var i in typeAry){
		typeCode += ','+typeAry[i];
	}
	if(typeCode.substring(0,1) == ","){
		typeCode = typeCode.substring(1);
	}
	var typeNames = $('#f_typeCode').combobox('getText');
	mainObj.typeCode = typeCode;
	mainObj.typeNames = typeNames;
	return true;
}


//跳转到管理分类
function jumpToDict(){
		var url = "/back/page/dict";
		var parentCode = "goods_type";
		if(parentCode){
			url += "?parentCode="+parentCode;
		}
		lh.subShowMain('技师分类管理', url)
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


