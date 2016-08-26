var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
IS_SHOW_TRASH = false;
SAVING_FLAG = 0;
$(function() {
	GRID_QUERYOBJ = {};//查询条件
	loadGrid();
	initSearch();
	initUploadSimple();//调用完整方法
	//paramMapData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getGoodsPictureList',
	    pagination:true,//允许分页
		pageSize:20,//每页10条数据
		loadMsg:'',
		width:WIDTH-1,
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
			/*{field:'shopSerial',title:'店铺编号',width:160,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},*/
	        {field:'goodsPictureSn',title:'商品编号',width:160,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'goodsPictureName',title:'商品名称',width:160,align:'center'},
	        {field:'picPath',title:'商品图片',width:140,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:40px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:40px;color:#FF6000">暂无图片<span>';
	        	return logo;
	        }},
	        /*{field:'operate',title:'操作',width:100,align:'center',formatter: function(value,row,index){
	        	return '<span class="g_alive"><span class="opt_cuation" onclick="openGoodsPictureWin('+index+',\'update\')">修改</span>'+//jumpToGoodsPictureUpdate
	        		   ' |  <span class="opt_green" onclick="openGoodsPictureWin('+index+',\'read\')">查看</span></span>'+//jumpToGoodsPicture
	        		   '<span class="g_trash"><span class="opt_cuation" onclick="batchThoroughDelete('+row.id+')">彻底删除</span>'+
	        		   ' |  <span class="opt_green" onclick="batchRecover('+row.id+')">恢复</span></span>';
	        }},*/
	        /*{field:'statusName',title:'状态',width:100,align:'center',formatter: function(value,row,index){
	        	var status = row.mainStatus;
	        	if(status == 75 || status == 76){value = '<span style="color:green">'+value+'</span>';}
	        	return value;
	        }},*/
	        {field:'shopPrice',title:'价格',width:80,align:'center'},
	        /*{field:'goodsPictureAlbumPath',title:'商品相册',width:80,align:'center',formatter: function(value,row,index){
	        	var dom = '<span style="cursor:pointe;color:green">点击查看</span>';
	        	if(!value){dom = '<span style="color:gray">暂无相册</span>';}
	        	return dom;
	        }},*/
	        /*{field:'shopName',title:'所属店铺',width:200,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+row.shopSerial+'">复制编号</button>';
	        	return dom;
	        }},*/
	        /*{field:'username',title:'所属用户',width:200,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+row.userSerial+'">复制编号</button>';
	        	return dom;
	        }},*/
	       /* {field:'realName',title:'真实姓名',width:100,align:'center'},*/
	        {field:'createdAt',title:'添加时间',width:120,align:'center',formatter: function(value,row,index){
	        	return getDateStr(value);
	        }},
	        {field:'createdBy',title:'添加人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return getDateStr(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return getDateStr(value);
	        }},
	        {field:'deletedBy',title:'删除人',width:140,align:'center'}
	        ]],
	    /*,onSelect:function(rowIndex, rowData){onClickRowOfGrid(rowIndex, rowData);},
	    onClickRow: function(index, row){},
	    loadFilter: function(data){return data;},
		onLoadSuccess:function(data){} */
	        onLoadError: function(data){
		    	backDatagridErrorCheck(data);
		    },
		    onDblClickRow: function(index, row){
		    	openGoodsPictureWin(index, 'read');
	        },
	    onLoadSuccess:function(data){
	       	if(IS_SHOW_TRASH){
	       		$('.g_alive').hide();
	       		$('.g_trash').show();
	       	}else{
	       		$('.g_alive').show();
	       		$('.g_trash').hide();
	       	}
	    	initClipboard();//复制功能
	   }  
	});
}

/** grid 行点击事件 */
/**function onClickRowOfGrid(rowIndex, rowData){}*/

function loadCombo(){
	
	$('#f_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/getDictCombo?parentId=70'
	});
	
}

/** 关闭窗口 */
function closeGoodsPictureWin(){
	$('#goodsPictureWin').window('close');
	//$('#datagrid').datagrid('clearSelections');
	//$('#datagrid').datagrid('clearChecked');
}

/** 提交表单 */
function submitGoodsPicture(){
	//$('#goodsPictureForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#goodsPictureForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}

/** 添加用户 */
function addGoodsPicture(){
	openGoodsPictureWin(-1,'add');
}

/** 打开窗口 */
function openGoodsPictureWin(index,operation){
	var rows,data;
	if(index >= 0){
		rows = $('#datagrid').datagrid('getRows');
		data = rows[index];//获取该行的数据
	}
	var $form = $('#goodsPictureForm');
	
	$form.form({
	    url:'',
	    onSubmit: function(){
	       $('#goodsPictureForm').form('enableValidation');
	       var flag = $('#goodsPictureForm').form('validate');
	       if(flag){
	    	   var filePaths = $("#filePaths").val();
	    		var filePathArr = new Array();
	    		if(filePaths.indexOf(',') >= 0){
	    			filePaths = filePaths.substring(1);
	    		}
	    		if(!filePaths){
	    			$.messager.alert('提示','请上传商品图片');
	    			return;
	    		}
	       		var id;
	       		if(operation != 'add')id = data.id;
	       		var goodsPicture = getGoodsPicture(id);//得到用户信息的字段值
	       		goodsPicture.picPath = filePaths;
	       	    $.post('/back/addOrUpdateGoodsPicture',goodsPicture,function(rsp){
					 if(rsp.success){
						 $('#datagrid').datagrid('reload');
						 //$('#datagrid').datagrid('clearSelections');
						 //$('#datagrid').datagrid('clearChecked');
				       	 setTimeout(function(){
				       		closeGoodsPictureWin();
				       	 },500);
					 }else{
						 $.messager.alert('提示',rsp.msg);
					 }
				},'json');
	       }
	       return false;
	    }
	});
	
	loadCombo();//加载下拉列表数据（省市）
	$('#goodsPictureWin').window('open');
	$form.form('clear');
	$form.form('disableValidation');
	$('#goodsPictureTip').html('');
	
	/*var domIds = "#f_name,#f_tel,#f_picPaths,#f_mainStatus";
	
	if(operation == 'add'){//添加用户
		$('#winSearchTr,#winSearchDivisionTr').show();//显示查询按钮
		$('#goodsPictureTable .readOnlyTr').hide();//隐藏只读字段
		$(domIds).textbox('readonly',false);//新增时设置为可编辑
	}else{//查看或修改
		$('#winSearchTr,#winSearchDivisionTr').hide();//隐藏查询按钮
		$('#goodsPictureTable .readOnlyTr').show();//显示只读字段
		var isReadOnly = false;
		if(operation == 'read'){//查看
			isReadOnly = true;
			$('#goodsPictureSave').hide();
		}else{//修改
			$('#goodsPictureSave').show();
		}
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
		//setGoodsPicture(data);//设置用户详细信息字段值
	}*/
	
}

/** 得到用户信息的字段值 */
function getGoodsPicture(id){
	
	var userId = $('#f_userId').val();
	var goodsId = $('#f_goodsId').val();
	
	var goodsPicture = {
		/*goodsPictureImg:f_goodsPictureImg,*/
		/*goodsPictureThumb:f_goodsPictureThumb,*/
		userId:userId,
		goodsId:goodsId
	};
	if(id){//有id为更新，无id为新增
		goodsPicture.id = id;
	}
	return goodsPicture;
}

/** 设置用户信息的字段值 */
/*function setGoodsPicture(data){
	var f_goodsPictureName = $('#f_goodsPictureName').textbox('getValue');
	var f_goodsPictureSn = $('#f_goodsPictureSn').textbox('getValue');
	var f_price = $('#f_price').textbox('getValue');
	var f_goodsPictureImg = $('#f_goodsPictureImg').textbox('getValue');
	var f_goodsPictureThumb = $('#f_goodsPictureThumb').textbox('getValue');
	
	$('#f_goodsPictureName').textbox('setValue', data.goodsPictureName);
	$('#f_goodsPictureSn').textbox('setValue', data.goodsPictureSn);
	$('#f_price').textbox('setValue', data.price);
	$('#f_goodsPictureImg').textbox('setValue', data.goodsPictureImg);
	$('#f_goodsPictureThumb').textbox('setValue', data.goodsPictureThumb);

	//查询产生的只读字段：
	$('#f_serial').textbox('setValue', data.shopSerial);
	$('#f_shopName').textbox('setValue', data.shopName);
	$('#f_username').textbox('setValue', data.username);
	$('#f_userId').val(data.userId);//隐藏字段-用户ID
	$('#f_shopId').val(data.shopId);//隐藏字段-店铺ID
	//以下为只读字段：
}*/

function search(typeId){
	$('#datagrid').datagrid('load',{
		typeId : typeId
	});
}

function initSearch(){
	
	/*$('#sc_mainStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/getDictCombo?parentId=70'
	});*/
	
}


/**根据查询条件查询匹配的数据*/
function doSearch(){
	/*var sc_shopName = $('#sc_shopName').textbox('getValue');
	var sc_shopSerial = $('#sc_shopSerial').textbox('getValue');
	var sc_username = $('#sc_username').textbox('getValue');
	var sc_realName = $('#sc_realName').textbox('getValue');
	var sc_userSerial = $('#sc_userSerial').textbox('getValue');
	*/var sc_goodsPictureName = $('#sc_goodsPictureName').textbox('getValue');
	var sc_goodsPictureSn = $('#sc_goodsPictureSn').textbox('getValue');
	var sc_priceFrom = $('#sc_priceFrom').textbox('getValue');
	var sc_priceTo = $('#sc_priceTo').textbox('getValue');
	
	/*var sc_mainStatus = $('#sc_mainStatus').combobox('getValue');

	if(!sc_mainStatus){sc_mainStatus=""}*/
	
	var common_queryObj = GRID_QUERYOBJ;
	/*common_queryObj.shopNameLike = sc_shopName;
	common_queryObj.shopSerialLike = sc_shopSerial;
	common_queryObj.usernameLike = sc_username;
	common_queryObj.realName = sc_realName;
	common_queryObj.userSerialLike = sc_userSerial;*/
	common_queryObj.goodsPictureNameLike = sc_goodsPictureName;
	common_queryObj.goodsPictureSnLike = sc_goodsPictureSn;
	common_queryObj.priceFrom = sc_priceFrom;
	common_queryObj.priceTo = sc_priceTo;
	
	/*common_queryObj.mainStatus = sc_mainStatus;*/
	
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$('#sc_shopName,#sc_shopSerial,#sc_username,#sc_userSerial,#sc_realName,#sc_goodsPictureName,#sc_goodsPictureSn').textbox('reset');
	$('#sc_mainStatus').combobox('reset');
	$('#sc_priceFrom,#sc_priceTo').numberbox('reset');
	
}

function searchGoods(){
	searchGoodsBySerial();//common_back:通用跟据店铺编号查询店铺
}

/** 跳转到 修改用户 页面 */
/**function jumpToGoodsPictureUpdate(){}*/
/** 跳转到 用户详情 页面 */
/**function jumpToGoodsPicture(){}*/

/** 批量删除用户 */
function batchDelete(){
	commonBatchDelete('/back/updateGoodsPictureDelete');//common_back:通用批量删除
}

/** 切换到回收站 */
function showTrach(){
	IS_SHOW_TRASH = true;
	commonShowTrash('#batchRecover,#batchThoroughDelete,#returnBack','#batchDelete,#addGoodsPicture,#userInfoLink,#shopLink,#showTrash');
}

/** 从回收站返回 */
function returnBack(){
	IS_SHOW_TRASH = false;
	commonReturnBack('#batchDelete,#addGoodsPicture,#userInfoLink,#shopLink,#showTrash','#batchRecover,#batchThoroughDelete,#returnBack');
}

/** 批量恢复用户 */
function batchRecover(id){
	commonBatchRecover('/back/updateGoodsPictureRecover',null,id);//common_back:通用批量恢复
}

/** 批量强制删除用户 */
function batchThoroughDelete(id){
	commonBatchThoroughDelete('/back/deleteGoodsPictureThorough',null,id);//common_back:通用批量强制删除
}

function jumpToGoods(){
	var url = "/back/goods";
	subShowMain('藏品', url);
}

/** 跳转：用户信息 *//*
function jumpToUserInfo(){
	var url = "/back/userInfo";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	subShowMain('用户信息', url)
}*/

/** 跳转：用户控制 *//*
function jumpToShop(){
	var url = "/back/shop";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	subShowMain('店铺', url)
}


*//** 跳转：新增或修改 *//*
function jumpToGoodsPictureAddOrUpdate(id){
	var url = "/back/goodsPictureAddOrUpdate";
	if(id){
		url += "?goodsPictureId="+id;
	}
	window.location.href = url;
}*/

/*function catGoodsPicture(){
	var url = "/back/picture";
	var f_goodsPictureSn = $("#f_goodsPictureSn").val();
	if(f_goodsPictureSn){
		url += "?goodsPictureSnLike="+f_goodsPictureSn;
	}
	window.location.href = url;
}*/
