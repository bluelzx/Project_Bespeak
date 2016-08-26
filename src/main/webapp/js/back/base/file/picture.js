var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
//IS_SHOW_TRASH = false;
//CURRENT_PIC_TYPE = 0;
//SAVING_FLAG = 0;
$(function() {
	lh.GRID_QUERYOBJ = {typeIdISNOTNULL:1};//查询条件
	loadGrid();
	initSearch();
	paramMapData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getPictureList',
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
			{field:'serial',title:'图片编号',width:160,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'title',title:'图片名称',width:180,align:'center'},
	        {field:'picPath',title:'图片展示',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:60px;">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'operate',title:'操作',width:100,align:'center',formatter: function(value,row,index){
	        	return '<span class="g_alive"><span class="opt_cuation" onclick="openPictureWin('+index+',\'update\')">修改</span>'+//jumpToPictureUpdate
	        		   ' |  <span class="opt_green" onclick="openPictureWin('+index+',\'read\')">查看</span></span>'+//jumpToPicture
	        		   '<span class="g_trash"><span class="opt_cuation" onclick="batchThoroughDelete('+row.id+')">彻底删除</span>'+
	        		   ' |  <span class="opt_green" onclick="batchRecover('+row.id+')">恢复</span></span>';
	        }},
	        {field:'typeName',title:'图片类型',width:120,align:'center'},
	       /* {field:'albumName',title:'所属相册',width:120,align:'center'},*/
	        /*{field:'username',title:'所属用户',width:200,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+row.userSerial+'">复制编号</button>';
	        	return dom;
	        }},
	        {field:'shopName',title:'关联店铺',width:200,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+row.shopSerial+'">复制编号</button>';
	        	return dom;
	        }},
	        {field:'goodsName',title:'关联商品',width:120,align:'center'},*/
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
	        {field:'deletedBy',title:'删除人',width:120,align:'center'}
	        ]],
	    /*,onSelect:function(rowIndex, rowData){onClickRowOfGrid(rowIndex, rowData);},
	    onClickRow: function(index, row){},
	    loadFilter: function(data){return data;},
		onLoadSuccess:function(data){} */
	        onLoadError: function(data){
		    	backDatagridErrorCheck(data);
		    },
		    onDblClickRow: function(index, row){
		    	openPictureWin(index, 'read');
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
	
	$('#f_picType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/getDictCombo?parentId=80',
		onChange:function(value){
			$('#album_tr,#user_tr,#goods_tr,#inst_tr,#shop_tr,#forumArticle_tr').hide();
			var id = value;
			folderType = 0;
			if(id == 81){//普通图片
				CURRENT_PIC_TYPE = 81;
				folderType = 1;
			}else if(id == 82){//商品图片
				$('#goods_tr,#album_tr').show();
				folderType = 2;
				CURRENT_PIC_TYPE = 82;
			}else if(id == 83){//用户头像
				$('#user_tr').show();
				folderType = 3;
				CURRENT_PIC_TYPE = 83;
			}else if(id == 84){//店铺图标
				$('#shop_tr').show();
				folderType = 4;
				CURRENT_PIC_TYPE = 84;
			}else if(id == 85){//机构图标
				$('#inst_tr').show();
				folderType = 5;
				CURRENT_PIC_TYPE = 85;
			}else if(id == 86){//资讯图片
				CURRENT_PIC_TYPE = 86;
				folderType = 7;
			}else if(id == 87){//系统图片
				CURRENT_PIC_TYPE = 87;
				folderType = 8;
			}else if(id == 88){//广告图片
				CURRENT_PIC_TYPE = 88;
				folderType = 9;
			}else if(id == 89){//论坛图片
				$('#forumArticle_tr').show();
				CURRENT_PIC_TYPE = 89;
				folderType = 6;
			}
			UPLOAD_PARAM_OBJ.folderType = folderType;
		}
	});
	
}

/** 关闭窗口 */
function closePictureWin(){
	$('#pictureWin').window('close');
	//$('#datagrid').datagrid('clearSelections');
	//$('#datagrid').datagrid('clearChecked');
}

/** 提交表单 */
function submitPicture(){
	//$('#pictureForm').submit();
	var timeRec = preventRepeat(10, SAVING_FLAG);
	if(timeRec){
		SAVING_FLAG = timeRec;
		$('#pictureForm').submit();//执行操作
	}else{//重复提交
		return;//可进行提示或其他操作，这里直接返回，即重复提交时没有反应
	}
}

/** 添加用户 */
function addPicture(){
	openPictureWin(-1,'add');
}

/** 打开窗口 */
function openPictureWin(index,operation){
	$('#album_tr,#user_tr,#goods_tr,#inst_tr,#shop_tr,#forumArticle_tr').hide();//默认隐藏图片的归属类型组件
	var rows,data;
	if(index >= 0){
		rows = $('#datagrid').datagrid('getRows');
		data = rows[index];//获取该行的数据
	}
	var $form = $('#pictureForm');
	
	$form.form({
	    url:'',
	    onSubmit: function(){
	       $('#pictureForm').form('enableValidation');
	       var flag = $('#pictureForm').form('validate');
	       if(flag){
	       		var id;
	       		if(operation != 'add')id = data.id;
	       		var picture = getPicture(id);//得到用户信息的字段值
	       		if(!FILE_PATH_STR || !CURRENT_PIC_TYPE)return;//没有选择图片类型或者图片路径为空
	       	    $.post('/addOrUpdatePicture',picture,function(rsp){
					 if(rsp.success){
					 	 resetUpload();//重置上传组件
						 $('#datagrid').datagrid('reload');
						 //$('#datagrid').datagrid('clearSelections');
						 //$('#datagrid').datagrid('clearChecked');
				       	 setTimeout(function(){
				       		closePictureWin();
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
	$('#pictureWin').window('open');
	$form.form('clear');
	$form.form('disableValidation');
	$('#pictureTip').html('');
	
	var domIds = "#f_name,#f_tel,#f_picPaths,#f_mainStatus";
	
	if(operation == 'add'){//添加用户
		$('#winSearchTr,#winSearchDivisionTr').show();//显示查询按钮
		$('#pictureSave').show();
		$('#pictureTable .readOnlyTr').hide();//隐藏只读字段
		$(domIds).textbox('readonly',false);//新增时设置为可编辑
	}else{//查看或修改
		$('#winSearchTr,#winSearchDivisionTr').hide();//隐藏查询按钮
		$('#pictureTable .readOnlyTr').show();//显示只读字段
		var isReadOnly = false;
		if(operation == 'read'){//查看
			isReadOnly = true;
			$('#pictureSave').hide();
		}else{//修改
			$('#pictureSave').show();
		}
		$(domIds).textbox('readonly',isReadOnly);//设置只读还是可编辑
		setPicture(data);//设置用户详细信息字段值
	}
	
}

/** 得到用户信息的字段值 */
function getPicture(id){
	var typeId = CURRENT_PIC_TYPE;
	if(!CURRENT_PIC_TYPE){
		$.messager.alert('提示','请选择图片类型');return;
	}
	if(!FILE_PATH_STR){
		$.messager.alert('提示','请选择要上传的图片，若已经选择了图片仍然出现此提示，请点击上传重置按钮后重新选择图片进行上传。');return;
	}
	var picture = {
		typeId:typeId,
		picUrls:FILE_PATH_STR
	};
	var linkId = undefined;
	if(id == 81){//普通图片
	}else if(id == 82){//商品图片
		linkId = $('#f_goodsId').val();
	}else if(id == 83){//用户头像
		linkId = $('#f_userId').val();
	}else if(id == 84){//店铺图标
		linkId = $('#f_shopId').val();
	}else if(id == 85){//机构图标
		linkId = $('#f_instId').val();
	}else if(id == 86){//资讯图片
	}else if(id == 87){//系统图片
	}else if(id == 88){//广告图片
	}else if(id == 89){//论坛图片
		linkId = $('#f_forumArticleId').val();
	}
	
	if(linkId)picture.linkId = linkId;
	
	var albumId = $('#f_albumId').val();
	if(albumId)picture.albumId = albumId;
	
	if(id){//有id为更新，无id为新增
		picture.id = id;
	}
	return picture;
}

/** 设置用户信息的字段值 */
function setPicture(data){
	$('#f_serial').textbox('setValue', data.serial);
	$('#f_username').textbox('setValue', data.username);
	$('#f_realName').textbox('setValue', data.realName);
	$('#f_name').textbox('setValue', data.name);
	$('#f_tel').textbox('setValue', data.tel);
	var mainStatus = data.mainStatus;
	if(!mainStatus)mainStatus = 1;
	$('#f_mainStatus').combobox('setValue', mainStatus);
	//图片LOGO设置
	//查询产生的只读字段：
	$('#f_serial').textbox('setValue', data.userSerial);
	$('#f_username').textbox('setValue', data.username);
	$('#f_realName').textbox('setValue', data.realName);
	$('#f_userId').val(data.userId);//隐藏字段-用户ID
	$('#f_shopId').val(data.shopId);//隐藏字段-店铺ID
	//以下为只读字段：
}

function search(typeId){
	$('#datagrid').datagrid('load',{
		typeId : typeId
	});
}

function initSearch(){
	
	$('#sc_picType').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/getDictCombo?parentId=80'
	});
	
}


/**根据查询条件查询匹配的数据*/
function doSearch(){
	var sc_picType = $('#sc_picType').combobox('getValue');
	var sc_serial = $('#sc_serial').textbox('getValue');
	var sc_title = $('#sc_title').textbox('getValue');
	/*var sc_albumName = $('#sc_albumName').textbox('getValue');
	var sc_albumSerial = $('#sc_albumSerial').textbox('getValue');*/
	
	var sc_userSerial = $('#sc_userSerial').textbox('getValue');
	var sc_goodsSn = $('#sc_goodsSn').textbox('getValue');
	var sc_shopSerial = $('#sc_shopSerial').textbox('getValue');
	
	var sc_startTimeFrom = $('#sc_startTimeFrom').datebox('getValue');
	var sc_startTimeTo = $('#sc_startTimeTo').datebox('getValue');
	if(sc_startTimeFrom && !sc_startTimeTo){
		sc_startTimeTo = sc_startTimeFrom;
	}
	if(!sc_startTimeFrom && sc_startTimeTo){
		sc_startTimeFrom = sc_startTimeTo;
	}
	if(!sc_picType){sc_picType=""}
	/*var sc_mainStatus = $('#sc_mainStatus').combobox('getValue');
	if(!sc_mainStatus){sc_mainStatus=""}*/
	
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.typeId = sc_picType;
	common_queryObj.serialLike = sc_serial;
	common_queryObj.titleLike = sc_title;
	//common_queryObj.albumNameLike = sc_albumName;
	//common_queryObj.albumSerialLike = sc_albumSerial;
	common_queryObj.userSerialLike = sc_userSerial;
	common_queryObj.goodsSnLike = sc_goodsSn;
	common_queryObj.shopSerialLike = sc_shopSerial;
	common_queryObj.startTimeFrom = sc_startTimeFrom;
	common_queryObj.startTimeTo = sc_startTimeTo;
	
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$('#sc_serial,#sc_title,#sc_albumName,#sc_albumSerial,#sc_userSerial,#sc_goodsSn,#sc_shopSerial').textbox('reset');
	$('#sc_picType').combobox('reset');
	$('#sc_startTimeFrom,#sc_startTimeTo').datebox('reset');
	
}

function searchUser(){
	searchUserBySerial();//common_back:通用跟据用户编号查询用户
}

/** 跳转到 修改用户 页面 */
/**function jumpToPictureUpdate(){}*/
/** 跳转到 用户详情 页面 */
/**function jumpToPicture(){}*/

/** 批量删除用户 */
function batchDelete(){
	commonBatchDelete('/back/updatePictureDelete');//common_back:通用批量删除
}

/** 切换到回收站 */
function showTrach(){
	IS_SHOW_TRASH = true;
	commonShowTrash('#batchRecover,#batchThoroughDelete,#returnBack','#batchDelete,#addPicture,#userInfoLink,#shopLink,#showTrash');
}

/** 从回收站返回 */
function returnBack(){
	IS_SHOW_TRASH = false;
	commonReturnBack('#batchDelete,#addPicture,#userInfoLink,#shopLink,#showTrash','#batchRecover,#batchThoroughDelete,#returnBack');
}

/** 批量恢复用户 */
function batchRecover(id){
	commonBatchRecover('/back/updatePictureRecover',null,id);//common_back:通用批量恢复
}

/** 批量强制删除用户 */
function batchThoroughDelete(id){
	commonBatchThoroughDelete('/back/deletePictureThorough',null,id);//common_back:通用批量强制删除
}

/** 跳转：用户信息 */
function jumpToUserInfo(){
	var url = "/back/userInfo";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	subShowMain('用户信息', url)
}

/** 跳转：用户控制 */
function jumpToShop(){
	var url = "/back/shop";
	var id = $('#userId').val();
	if(id){
		url += "?userId="+id;
	}
	subShowMain('店铺', url)
}

/** 跳转：新增或修改 */
function jumpToPictureAddOrUpdate(id){
	var url = "/back/pictureAddOrUpdate";
	if(id){
		url += "?pictureId="+id;
	}
	window.location.href = url;
}

