/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'coupon',
	mainObjUpperName : 'Coupon'
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
		sortName:'addTime',
		sortOrder:'DESC',
		fitColumns:false,
		selectOnCheck:false,
		checkOnSelect:false,
		singleSelect:true,
		striped:true,
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
	        {field:'code',title:'优惠码',width:160,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'name',title:'优惠券名',width:160,align:'center'},
	        {field:'couponUserName',title:'所属用户',width:160,align:'center'},
	        {field:'operate',title:'操作',width:200,align:'center',formatter: function(value,row,index){
	        	return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
	     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
	    		   '<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
	    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
	            }},
	        {field:'way',title:'优惠方式',width:80,align:'center',formatter: function(value,row,index){
		        	var status = "金额减免";
		        	if(value == 2){status = '<span style="color:orange">消费折扣</span>';}
		        	return status;
		        }},
		    {field:'money',title:'优惠金额',width:80,align:'center'},
		    {field:'discount',title:'优惠折扣',width:80,align:'center'},
	        {field:'shopName',title:'所属店铺',width:80,align:'center'},
	        {field:'dealStatus',title:'使用状态',width:80,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "未使用";
	        	if(value == 2){isRealAuth = '<span style="color:green">已使用</span>';}
	        	if(value == 3){isRealAuth = '<span style="color:green">已过期</span>';}
	        	if(value == 4){isRealAuth = '<span style="color:green">异常</span>';}
	        	return isRealAuth;
	        }},
	        {field:'validStartTime',title:'开始时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'validEndTime',title:'结束时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
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
	$('#sc_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getCourseTypeList?parentCode='+"link_type"
	});
	$('#sc_shopId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getShopArray'
	});
}
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
	$('#f_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getusernameArray'
	})
		$('#f_dealStatus').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '未使用'
		},{
			'id' : 2,
			'name' : '已使用'
		},{
			'id' : 3,
			'name' : '已过期'
		},{
			'id' : 4,
			'name' : '异常'
		}]
	})
	$('#f_attrStr').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:true,
		required:false,
		panelHeight:'auto',
		url:'/back/getProviderTypeList?parentCode='+"goods_type"
	});
	$('#f_way').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '金额'
		},{
			'id' : 2,
			'name' : '折扣'
		}],
		onSelect: function(rec){
			  if(rec.id==1){
//				  $("#money").show();
//				  $("#discount").hide();
				  $("#select").html("优惠金额：")
			  }else if(rec.id==2){
//				  $("#money").hide();
//				  $("#discount").show();
				  $("#select").html("优惠折扣：")
			  }
		}
	});
	
}

function afterOpenWin(data, operation, isReadOnly){
	if(data){
	if(data.discount!=0){
		$("#f_way").combobox("setValue",2);
		$("#f_discount").textbox("setValue",data.discount);
		 $("#select").html("优惠折扣：")
	}else if(data.money!=0){
		$("#f_way").combobox("setValue",1);
		 $("#select").html("优惠金额：")
		$("#f_discount").textbox("setValue",data.money);
		 $('#f_attrStr').combobox('setValues',data.attrStr);
	}
	}else{
		$('#f_dealStatus').combobox("setValue",1);
	}
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	var discount=$("#f_discount").textbox("getValue");
	var way=$("#f_way").combobox("getValue")
	if(way==1){
	mainObj.money=discount;
	mainObj.discount=0;
	}else if(way==2){
	mainObj.discount=discount;
	mainObj.money=0;
	}
	var typeAry = $('#f_attrStr').combobox('getValues');
	var typeCode="";
	for(var i in typeAry){
		typeCode += ','+typeAry[i];
	}
	if(typeCode.substring(0,1) == ","){
		typeCode = typeCode.substring(1);
	}
	var attrStr = $('#f_attrStr').combobox('getText');
	mainObj.attrStr2 = attrStr;
	mainObj.attrStr = typeCode;
	mainObj.validStartTime+=" 00:00:00";
	mainObj.validEndTime+=" 00:00:00";
//	var shop=$("f_shopId").combobox("getValues");
//	var way=$("f_way").combobox("getValues");
//	mainObj.name=shop+way+money+discount;
	return true;
}