/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'course',
	mainObjUpperName : 'Course'
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
			{field:'operate',title:'操作',width:156,align:'center',formatter: function(value,row,index){
	        	return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
	     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
	    		   '<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
	    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
	            }},
	        {field:'serial',title:'课程编号',width:160,align:'center',formatter: function(value,row,index){
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'name',title:'课程名称',width:160,align:'center'},
<<<<<<< .mine
	        {field:'attrStr',title:'课程图片',width:200,align:'center',formatter: function(value,row,index){
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
=======
	        {field:'attrStr',title:'课程图片',width:140,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:40px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:40px;color:#FF6000">暂无图片<span>';
	        	return logo;
	        }},
>>>>>>> .r479
	        {field:'typeName',title:'课程类型',width:80,align:'center'},
	        {field:'shopName',title:'所属店铺',width:156,align:'center'},
	        {field:'teacher',title:'课程讲师',width:80,align:'center'},
	        {field:'price',title:'价格',width:80,align:'center'},
	        {field:'address',title:'上课地址',width:80,align:'center'},
	        {field:'startTime',title:'开始时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDate(value);
	        }},
	        {field:'endTime',title:'结束时间',width:120,align:'center',formatter: function(value,row,index){
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

function initComboData(){
	
}
function initQueryComponent(){
	$('#sc_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getCourseTypeList?parentCode='+"course_type"
	});
	$('#sc_linkId').combobox({
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
	initUploadSimple({showEdBtns:true,showItemDiv:true,multiFlag:false,multiReplace:true,
		successFun:function(fileId, filePath){
			$("#upld_container_"+fileId).remove();
			$("#pic").attr('src', filePath);
	}});
	$("#upload_outer_div").empty();
	
	$('#f_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getCourseTypeList?parentCode='+"course_type"
	});
	$('#f_linkId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getShopArray'
	});
}
function onSelect(d) {
    var issd = false, sd = issd ? d : new Date($('#f_startTime').datebox('getValue')), ed = issd ? new Date($('#f_startTime').datebox('getValue')) : d;
        if (ed < sd) {
        	$.messager.alert('提示',"结束时间不能小于开始时间");
            //只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
            $('#f_endTime').datebox('setValue', '').datebox('hidePanel');
        }
    }
function afterOpenWin(data, operation, isReadOnly){
	if(!data){
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#pic").attr('src', null);
		return;
	}
	$("#pic").attr('src', data.attrStr);
	$("#filePaths").val(data.attrStr);
	$("#fileDBIds").val(data.logoPicId);
	if(data){
		$('#f_startTime').textbox('setValue', lh.formatGridDate(data.startTime));
		$('#f_endTime').textbox('setValue', lh.formatGridDate(data.endTime));
	}
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	var filePaths = $("#filePaths").val();
	if(!filePaths){
	}else{
		var ids = UPLOAD_OBJ.idsStr;
		if(filePaths.substring(0,1) != "/"){
			filePaths = filePaths.substring(1);
			ids = ids.substring(1);
		}
		mainObj.attrStr = filePaths;
	}
	mainObj.startTime+=" 00:00:00";
	mainObj.endTime+=" 00:00:00";
	return true;
}

//跳转到管理分类
function jumpToDict(){
		var url = "/back/page/dict";
		var parentCode = "course_type";
		if(parentCode){
			url += "?parentCode="+parentCode;
		}
		lh.subShowMain('培训分类管理', url)
}