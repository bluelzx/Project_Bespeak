/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'people',
	mainObjUpperName : 'People',
}

$(function() {
	loadGrid();
	initQueryComponent();
	initComboData();
});

/** 加载主表格 */
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
			{field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){
				return  '<span class="opt_alive">'
				+'<span style="cursor: pointer;color: #EC4949" onclick="openMainObjWin('+index+',\'update\')">修改</span>&nbsp;|&nbsp;'
				+'<span class="update" style="cursor: pointer;color: green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'
				+'<span class="opt_trash"><span style="cursor: pointer;color: #EC4949;" onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'
				+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
			}},
			{field:'username',title:'用户名',width:140,align:'center'},
	        {field:'realName',title:'真实姓名',width:100,align:'center'},
	        {field:'avatar',title:'用户头像',width:220,align:'center',formatter:function(value,row,index){
	        	var avatar = '<a href="'+value+'" target="_blank"><img style="height:40px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)avatar = '<span style="line-height:40px;">暂无图标<span>';
	        	return avatar;
	        }},
//	        {field:'password',title:'MD5密码',width:120,align:'center'},
	        {field:'sex',title:'性别',width:80,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "";
	        	if(value == 1){isRealAuth = '男';}
	        	else if(value == 2){isRealAuth = '<span style="color:blue">女</span>';};
	        	return isRealAuth;
	        }},
	        {field:'loginName',title:'登录名',width:100,align:'center'},
	        {field:'address',title:'详细地址',width:200,align:'center'},
	        {field:'positionNames',title:'工作名称',width:100,align:'center'},
	        {field:'birthday',title:'出生日期',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'typeCode',title:'职称代码',width:100,align:'center'},
//	        {field:'titleCode',title:'职称代码',width:100,align:'center'},
	        {field:'organization',title:'所属机构',width:100,align:'center'},
	        {field:'idcardNum',title:'身份证号',width:100,align:'center'},
	        {field:'introduction',title:'简介',width:200,align:'center'},
	        {field:'bussinessScope',title:'业务范围',width:100,align:'center'},
//	        {field:'educationCode',title:'学历',width:100,align:'center'},
	        {field:'goodAt',title:'擅长领域',width:200,align:'center'},
	        {field:'titleName',title:'职称名称',width:100,align:'center'},
	        {field:'priceText',title:'工作收费标准说明',width:100,align:'center'},
	        {field:'workYear',title:'工作经验',width:100,align:'center',formatter: function(value,row,index){
	        	if(value<1){value="1年以下"}else{
	        		value=value+"年";
	        	}
	        	return value;
	        }},
	        {field:'attrInt',title:'评星：',width:100,align:'center'},
	        {field:'attrInt2',title:'喜欢数',width:100,align:'center'},
	        {field:'mainStatus',title:'预约状态',width:60,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "";
	        	if(value == 1){isRealAuth = '<span style="color:blue">能</span>';}
	        	else if(value == 2){isRealAuth = '<span style="color:green">不能</span>';};
	        	return isRealAuth;
	        }},
	        {field:'isRealAuth',title:'实名认证',width:80,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "未认证";
	        	if(value == 2){isRealAuth = '<span style="color:blue">已认证</span>';}
	        	return isRealAuth;
	        }},
//	        {field:'lastLoginIp',title:'最后登录IP',width:100,align:'center'},
//	        {field:'province',title:'地区',width:160,align:'center',formatter: function(value,row,index){
//	        	var province = row.provinceName,city = row.cityName;
//	        	if(!province){province = '';}
//	        	if(!city){city = '';}/*else{city = ','+city}*/
//	        	return province+city;
//	        }},
	        {field:'provinceName',title:'所在省',width:120,align:'center'},
	        {field:'cityName',title:'所在市',width:220,align:'center'},
//	        {field:'province',title:'所在省',width:120,align:'center'},
//	        {field:'city',title:'所在市',width:120,align:'center'},
	        {field:'email',title:'邮箱',width:180,align:'center'},
	        {field:'phone',title:'电话',width:120,align:'center'},
	        {field:'qq',title:'qq',width:120,align:'center'},
	        {field:'weixin',title:'微信',width:120,align:'center'},
	        {field:'createdAt',title:'注册时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
//	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
//	        	return lh.formatGridDateTime(value);
//	        }},
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }}
	        //未用字段
//	    	/** 手机是否公开  */
//	    	private Integer phoneConceal;
//	    	/** 图片路径  */
//	    	private String picPath;	
//	    	/** 最后登录时间  */
//	    	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	    	private Date lastLoginTime;
//	    	/** 二维码  */
//	    	private String qrCode;
//	    	/** 第三方平台的密码  */
//	    	private String thirdPassword;
//	    	/** 微信openid（标识微信用户）  */
//	    	private String wxOpenid;
//	    	/** 证件类型  */
//	    	private Integer certificateType;
//	    	/** 证件号码  */
//	    	private String certificateNumber;
//	    	/** 证件姓名  */
//	    	private String certificateName;
//	    	/** 关联代码1-5  */
//	    	private String linkCode;
	    ]],
	    /*,onSelect:function(rowIndex, rowData){onClickRowOfGrid(rowIndex, rowData);},
	    onClickRow: function(index, row){onClickRowOfGrid(index, row);},
	    loadFilter: function(data){return loadFilterOfGrid(data);}*/
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

/** 初始化下拉列表数据，存入缓存，便于复用 */

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
			'name' : '能'
		},{
			'id' : 2,
			'name' : '不能'
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
	    panelHeight: 180,
	    url:'/getCitys'
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
			'name' : '能'
		},{
			'id' : 2,
			'name' : '不能'
		}]
	});
//	$('#f_workYear').numberspinner({
//		valueField : 'id',
//		textField : 'name',
//		editable : true,
//		multiple:false,
//	    required:false,
//	    panelHeight:'auto',
//	    data : [{
//			'id' : 0,
//			'name' : '1年以下'
//		},{
//			'id' : 1,
//			'name' : '1年'
//		},{
//			'id' : 2,
//			'name' : '2年'
//		},{
//			'id' : 3,
//			'name' : '3年'
//		},{
//			'id' : 10,
//			'name' : '10年'
//		},]
//	});
	$('#f_typeCode').combobox({
		valueField : 'code',
		textField : 'name',
		editable : false,
		multiple : false,
	    required : true,
	    panelHeight : 200,
	    url : '/back/getPeopletypeCodeTypeArray'
		/*data : [{
			'id' : 1,
			'name' : '启用'
		},{
			'id' : 2,
			'name' : '停用'
		}]*/
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
	    panelHeight: 180,
	    url:'/getCitys'
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
	$('#f_sex').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : true,
		panelHeight : 'auto',
		data : [{
			'id' : 1,
			'name' : '男'
		},{
			'id' : 2,
			'name' : '女'
		}]
	});
}


function search(typeId){
	$('#datagrid').datagrid('load',{
		typeId : typeId
	});
}


/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 开始 ========================================*/

//具体扩展方法列表，请查看 /js/common/back_template.js 文件中的顶部说明
//示例：function afterAddOrUpdate(mainObj, rsp){return true;} //执行新增或修改之前的拦截方法，返回false则不执行新增修改
//使用：将需要扩展的方法复制到对应业务JS文件中，在扩展方法体内执行自定义业务逻辑。

/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 结束 ========================================*/
//更新增加前执行的方法
function preAddOrUpdate(mainObj){
	
	if(!mainObj)return false;
	var passwordReset = $('#f_password').textbox('getValue');
	if(passwordReset)mainObj.password = passwordReset;
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
		mainObj.picPaths = ids;
	}
////	mainObj.senderName=mainObj.senderId;
//	if(mainObj.sendTime)mainObj.sendTime+=' 00:00:00';
//	if(mainObj.expiryDate)mainObj.expiryDate += ' 00:00:00';
	return true;
}

function afterOpenWin(data, operation, isReadOnly){
	if(!data){
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#pic").attr('src', null);
		return;
	}
	$("#pic").attr('src', data.avatar);
	$("#filePaths").val(data.filePaths);
//	$("#fileDBIds").val(data.logoPicId);
	
	/*var province = $('#f_province').combobox('getValue');//更新市
	var city = $('#f_city').combobox('getValue');
    var url = '/back/getCityArray?provinceId='+province;
    $('#f_city').combobox('reload', url);
    
    if(data.birthday){
    	$('#f_birthday').datebox('setValue', lh.formatDate({date:new Date(data.birthday)}) );
    }*/
}
//更新密码引用操作
function getResetPasswordUrl(){
	return '/back/updatePeoplePassword';
}
//跳转到管理分类
function jumpToDict(){
		var url = "/back/page/dict";
		var parentCode = "people_typeCode";
		if(parentCode){
			url += "?parentCode="+parentCode;
		}
		lh.subShowMain('月嫂分类管理', url)
//	location.href = "/back/page/dict/?parentCode=people";//location.href实现客户端页面的跳转  
//	return '/back/page/dict';
}
//跳转到管理预约
function jumpToBespeak(){
	location.href = "/back/page/bespeak/?code=people";//location.href实现客户端页面的跳转  
//	return '/back/page/bespeak/?typeId=people';
}