var HEIGHT = document.documentElement.clientHeight;
var WIDTH = document.documentElement.clientWidth;
if(WIDTH<1000)WIDTH=1000;
IS_SHOW_TRASH = false;
SAVING_FLAG = 0;
$(function() {
	GRID_QUERYOBJ = {promoteUserIdISNOTNULL:1};//查询条件
	loadGrid();
	initSearch();
	//paramMapData();
});

/**加载用户列表*/
function loadGrid(){
	$('#datagrid').datagrid({
	    url:'/back/getOrderGoodsList',
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
	        {field:'goodsName',title:'商品名称',width:160,align:'center'},
	        {field:'picPath',title:'商品封面',width:140,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:40px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value)logo = '<span style="line-height:40px;color:#FF6000">暂无图片<span>';
	        	return logo;
	        }},
	        {field:'shopPrice',title:'价格',width:80,align:'center'},
	        {field:'shopName',title:'所属店铺',width:200,align:'center',formatter: function(value,row,index){
				if(row.goodsType == 1){
					return row.shopName;
				}else if(row.goodsType == 2){
					return row.wholesaleName;
				}
	        }},
	        {field:'promoteUserName',title:'推广人',width:200,align:'center'},
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
	var sc_shopName = $('#sc_shopName').textbox('getValue');
	var sc_goodsName = $('#sc_goodsName').textbox('getValue');
	var common_queryObj = GRID_QUERYOBJ;
	common_queryObj.shopNameLike = sc_shopName;
	common_queryObj.goodsNameLike = sc_goodsName;
	$('#datagrid').datagrid('load',common_queryObj);  
}
/**重置查询查询条件*/
function clearSearch(){
	$('#sc_shopName').textbox('setValue','');
	$('#sc_goodsName').textbox('setValue','');
}


