/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'goods',
	mainObjUpperName : 'Goods'
}

$(function() {
	loadGrid();
	initQueryComponent();
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
	        {field:'serial',title:'服务编号',width:160,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'goodsName',title:'服务名称',width:160,align:'center'},
	        {field:'picPath',title:'服务封面',width:140,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:40px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:40px;color:#FF6000">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'shopName',title:'所属店铺',width:200,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+row.shopSerial+'">复制编号</button>';
	        	return dom;
	        }},
	        {field:'operate',title:'操作',width:200,align:'center',formatter: function(value,row,index){
	        	return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
	     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
	    		   '<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
	    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
	            }},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "上架";
	        	if(value == 2){status = '<span style="color:orange">下架</span>';}
	        	return status;
	        }},
	        {field:'isBest',title:'精品',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "否";
	        	if(value == 1){status = '<span style="color:orange">是</span>';}
	        	return status;
	        }},
	        {field:'goodsTypeName',title:'服务类型',width:80,align:'center'},
	        {field:'shopPrice',title:'价格',width:80,align:'center'},
	        {field:'groupNum',title:'疗程',width:80,align:'center'},
	        {field:'timeNum',title:'服务时长',width:80,align:'center'},
	        {field:'giveIntegral',title:'赠送积分',width:160,align:'center'},
	        {field:'scans',title:'浏览量',width:160,align:'center'},
	        {field:'doneAmount',title:'成交量',width:160,align:'center'},
	        /*{field:'goodsAlbumPath',title:'服务相册',width:80,align:'center',formatter: function(value,row,index){
	        	var dom = '<span style="cursor:pointe;color:green">点击查看</span>';
	        	if(!value){dom = '<span style="color:gray">暂无相册</span>';}
	        	return dom;
	        }},*/
	       
	        {field:'createdAt',title:'添加时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'createdBy',title:'添加人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'deletedBy',title:'删除人',width:140,align:'center'}
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
			'name' : '上架'
		},{
			'id' : 2,
			'name' : '下架'
		}]
	});
	
	$('#sc_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getGoodsTypeList?parentCode='+"goods_type"
	});
}
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
			'name' : '上架'
		},{
			'id' : 2,
			'name' : '下架'
		}]
	});
	$('#f_isBest').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '是'
		},{
			'id' : 2,
			'name' : '否'
		}]
	});
	
	$('#f_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getGoodsTypeList?parentCode='+"goods_type"
	});

	$('#f_shopId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getShopArray'
	});
}

function afterOpenWin(data, operation, isReadOnly){
	if(!data){
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#pic").attr('src', null);
		return;
	}
	$("#pic").attr('src', data.picPath);
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
		mainObj.picPath = filePaths;
	}
	
	if(!mainObj.groupNum){
		mainObj.groupNum=0;
	}
	if(!mainObj.doneAmount){
		mainObj.doneAmount=0;
	}
	if(!mainObj.scans){
		mainObj.scans=0;
	}
	return true;
}
//跳转到管理分类
function jumpToDict(){
		var url = "/back/page/dict";
		var parentCode = "goods_type";
		if(parentCode){
			url += "?parentCode="+parentCode;
		}
		lh.subShowMain('服务分类管理', url)
}
