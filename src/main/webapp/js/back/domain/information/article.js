var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
IS_SHOW_TRASH = false;
SAVING_FLAG = 0;
$(function() {
	var typeId = $("#typeId").val();
	if(typeId == 41){
		GRID_QUERYOBJ = {typeId:typeId};//查询条件
	}else{
		GRID_QUERYOBJ = {allTypeId:1};//查询条件
	}
	loadGrid();
	initData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getArticleList',
	    pagination:true,//允许分页
		pageSize:20,//每页10条数据
		loadMsg:'',
		width:WIDTH-20,
		height:HEIGHT-125,
		idField:'id',
		sortName:'addTime',
		sortOrder:'DESC',
		queryParams:GRID_QUERYOBJ,
		fitColumns:true,
		selectOnCheck:true,
		checkOnSelect:true,
		singleSelect:true,
		striped:true,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
	        {field:'title',title:'标题',width:220,align:'center'},
	        {field:'description',title:'内容描述',width:220,align:'center'},
	        //{field:'content',title:'内容',width:220,align:'center'},
	        {field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){
	        	return  '<span class="g_alive" ><span style="cursor: pointer;color: #EC4949" onclick="updateArticle('+row.id+')">修改</span>'
	        	+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="updateArticle('+row.id+',\'read\')">查看</span></span>'
	        	+'<span class="g_trash"><span  style="cursor: pointer;color: #EC4949" onclick="batchThoroughDelete('+row.id+')">彻底删除</span>'
	        	+'&nbsp;|&nbsp;<span  style="cursor: pointer;color: green" onclick="batchRecover('+row.id+')">恢复</span></span>';
	        }},
	        {field:'mainStatus',title:'状态',width:60,align:'center',formatter: function(value,row,index){
	        	var status = "启用";
	        	if(value == 2){status = '<span style="color:orange">停用</span>';}
	        	return status;
	        }},
	        {field:'deletedBy',title:'删除人',width:220,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }},
	        {field:'createdBy',title:'创建人',width:220,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	var dateStr = "";
	        	if(value){
	        		var d = new Date(value);
	        		var month = d.getMonth()+1;
	        		dateStr = ''+d.getFullYear()+'/'+month+'/'+d.getDate();
	        	}
	        	return dateStr;
	        }}
	    ]],
	    /*onSelect:function(rowIndex, rowData){
	    	onClickRowOfGrid(rowIndex, rowData);*//**当点击表格中的某行数据时执行*//*
	    },
	    onClickRow: function(index, row){
			
		},
	    loadFilter: function(data){
	    	return data;
	    },*/
	    onDblClickRow: function(index, row){
	    	updateArticle(index, 'read');
        },
	    onLoadError: function(data){
	    	backDatagridErrorCheck(data);
	    },
		onLoadSuccess:function(data){
			if(IS_SHOW_TRASH){
	       		$('.g_alive').hide();
	       		$('.g_trash').show();
	       	}else{
	       		$('.g_alive').show();
	       		$('.g_trash').hide();
	       	}
		}
	});
}

function onClickRowOfGrid(){

}

function initData(){
	$("#mainStatus").combobox({
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
	$("#isRecommend").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
	$("#isTop").combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		data : [{
			'id' : 1,
			'name' : '否'
		},{
			'id' : 2,
			'name' : '是'
		}]
	});
}

/**根据查询条件查询匹配的数据*/
function doSearch(){
	var mainStatus = $("#mainStatus").combobox('getValue');
	var title = $("#title").textbox('getValue');
	var description = $("#description").textbox('getValue');
	var content = $("#content").textbox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.status = mainStatus;
	common_queryObj.title = title;
	common_queryObj.description = description;
	common_queryObj.content = content;
	var typeId = $("#typeId").val();
	if(typeId == 41){
		var institution = $("#institution").textbox('getValue');
		var address = $("#address").textbox('getValue');
		var startDate = $("#startDate").datebox('getValue');
		var endDate = $("#endDate").datebox('getValue');
		common_queryObj.institution = institution;
		common_queryObj.address = address;
		common_queryObj.startDate = startDate;
		common_queryObj.endDate = endDate;
	}else{
		var isTop = $("#isTop").combobox('getValue');
		var isRecommend = $("#isRecommend").combobox('getValue');
		if(isTop == 2){
			common_queryObj.isTopNOTNULL = isTop;
		}else{
			common_queryObj.isTopISNULL = isTop;
		}	
		if(isRecommend == 2){
			common_queryObj.isRecommendNOTNULL = isRecommend;
		}else{
			common_queryObj.isRecommendISNULL = isRecommend;
		}
	}
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$("#mainStatus").combobox('setValue','')
	$("#title").textbox('setValue','');
	$("#description").textbox('setValue','');
	$("#content").textbox('setValue','');
	$("#institution").textbox('setValue','');
	$("#address").textbox('setValue','');
	$("#startDate").datebox('setValue','');
	$("#endDate").datebox('setValue','');
	$("#isTop").combobox('setValue','');
	$("#isRecommend").combobox('setValue','');
	var typeId = $("#typeId").val();
	if(typeId == 41){
		GRID_QUERYOBJ = {typeId:typeId};//查询条件
	}else{
		GRID_QUERYOBJ = {allTypeId:1};//查询条件
	}
}
/**跳转到修改页面*/
function updateArticle(id,operation){
	var typeId = $("#typeId").val();
	window.location.href = "/back/articleAddOrUpdate?id="+id+"&typeId="+typeId+"&operation="+operation;
}
/**添加数据*/
function jumpToAddArticle(){
	var typeId = $("#typeId").val();
	window.location.href = "/back/articleAddOrUpdate?typeId="+typeId;
}

/**批量删除数据**/
function batchDelete(){
	commonBatchDelete('/back/updateArticleDelete');//common_back:通用批量恢复
}

/**批量彻底删除**/
function batchThoroughDelete(id){
	commonBatchThoroughDelete('/back/deleteArticleThorough',null,id);//common_back:通用批量强制删除
}

/**批量恢复数据**/
function batchRecover(id){
	commonBatchRecover('/back/updateArticleRecover',null,id);//common_back:通用批量恢复
}

/**读取删除数据*/
function showTrash(){
	IS_SHOW_TRASH = true;
	commonShowTrash('#batchRecover,#batchThoroughDelete,#returnBack','#batchDelete,#jumpToAddArticle,#showTrash');
}
/**读取未删除数据**/
function returnBack(){
	IS_SHOW_TRASH = false;
	commonReturnBack('#batchDelete,#jumpToAddArticle,#showTrash','#batchRecover,#batchThoroughDelete,#returnBack',{typeId:$("#typeId").val()});
}


/** 跳转：新闻资讯 */
function jumpToNews(){
	var url = "/back/article?typeId=42";
	subShowMain('新闻资讯', url)
}
/** 跳转：展会活动 */
function jumpToActivity(){
	var url = "/back/article?typeId=41";
	subShowMain('展会活动', url)
}

