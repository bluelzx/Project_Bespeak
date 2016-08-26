/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'article',
	mainObjUpperName : 'Article',
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
	    pageSize:6,//每页数据条数
	    pageList:[2,4,6,8,10],//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-122,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
			{field:'operate',title:'操作',width:120,align:'center',formatter: function(value,row,index){
				return  '<span class="opt_alive">'
				+'<span style="cursor: pointer;color: #EC4949" onclick="openMainObjWin('+row.id+',\'update\')">修改</span>&nbsp;'
				+'|&nbsp;'
				+'<span class="update" style="cursor: pointer;color: green" onclick="openMainObjWinselect('+row.id+',\'read\')">查看'+'</span></span>'
				+'<span class="opt_trash"><span style="cursor: pointer;color: #EC4949;" onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'
				+'&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
			}},
//			/** 子标题  */ subTitle;
//			/** 地址  */ address;
//			/** 自定义属性1  */ attr1;
//			/** 自定义属性2  */ attr2;
//			/** 附件ID  */ fileId;
//			/** 附件ID串  */ fileIds;
//			/** 附件ID  */ attachId;
			{field:'picPaths',title:'图片',width:200,align:'center',formatter: function(value,row,index){
	        	var logo = '<a href="'+value+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+value+'"/></a>';
	        	if(!value){logo = '<span style="line-height:60px;">暂无图片<span>';}
	        	else{
	        		var ary=value.split(",");
//	        		logo='<a href="/back/page/picture"><img style="height:60px;cursor:pointer;" src="'+ary[0]+'"/></a>';
	        		logo='<a href="'+ary[0]+'" target="_blank"><img style="height:60px;cursor:pointer;" src="'+ary[0]+'"/></a>';
	        		}
	        	return logo;
	        }},
	        {field:'title',title:'标题',width:120,align:'center'},
	        {field:'content',title:'内容',width:240,align:'center'},
			{field:'catId',title:'栏目ID',width:120,align:'center'},
			{field:'roleId',title:'角色ID（文章标识）',width:120,align:'center'},
	        {field:'organization',title:'机构名',width:120,align:'center'},
	        {field:'startDate',title:'开始时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'endDate',title:'结束时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
//	        {field:'linkUrl',title:'链接地址',width:140,align:'center',formatter: function(value,row,index){
//	        	var linkUrl ='<a href="http://'+value+'" target="_blank">'+'http://'+value+'</a>'
//	        	return linkUrl;
//	        }},
	        {field:'isHot',title:'是否热门',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'isRecommend',title:'是否推荐',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'isShowIndex',title:'是否首页展示',width:80,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'isGood',title:'是否精华',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'isTop',title:'是否置顶',width:60,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'userId',title:'用户ID',width:120,align:'center'},
	        {field:'author',title:'作者',width:120,align:'center'},
	        {field:'keywords',title:'关键字',width:120,align:'center'},
	        {field:'tags',title:'标签',width:120,align:'center'},
	        {field:'description',title:'描述',width:120,align:'center'},
	        {field:'scans',title:'浏览次数',width:120,align:'center'},
	        {field:'hits',title:'点击次数',width:120,align:'center'},
	        {field:'allowComment',title:'是否允许评论',width:80,align:'center',formatter: function(value,row,index){
	        	return value == 2 ? '<span style="color:orange">是</span>' : '否';
	        }},
	        {field:'orgin',title:'来源',width:120,align:'center'},
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }}
	    ]],
//	   onSelect:function(rowIndex, rowData){onClickRowOfGrid(rowIndex, rowData);},
//	    onClickRow: function(index, row){onClickRowOfGrid(index, row);},
        onLoadError: function(data){
        	lh.onGridLoadError(data);
	    },
	    onDblClickRow: function(row, row){
//	    	lh.onGridDblClickRow(row.id, row);
	    	location.href = "/back/page/article/"+row.id+"";//location.href实现客户端页面的跳转  
        },
	    onLoadSuccess:function(data){
	    	lh.onGridLoadSuccess(data);
	    }  
	});
}

/** 添加修改的弹出方法 */
function addMainObj(){
	location.href = "/back/page/articleAddUpdate";//location.href实现客户端页面的跳转  
//	lh.jumpR('/back/page/articleAddUpdate');
}
function openMainObjWin(index,operation){
	location.href = "/back/page/articleAddUpdate/"+index+"";//location.href实现客户端页面的跳转  
}
function openMainObjWinselect(index,operation){
	location.href = "/back/page/article/"+index+"";//location.href实现客户端页面的跳转  
}
/** 初始化下拉列表数据，存入缓存，便于复用 */
function initComboData(){
//	lh.loadComboDataToCache({url:'/back/getDictArrayByParentCode?parentCode=article_type', cacheName:'articleTypeArray', domId:'#sc_typeCode'});
}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent(){
	
//	$('#sc_typeCode').combobox({
//		valueField : 'code',
//		textField : 'codeName',
//		editable : false,
//		multiple:false,
//		required:false,
//		panelHeight:200,
//		data:lh.getDataFromCache('articleTypeArray')
//	});
	
}

/** 初始化表单中的组件及数据 */
function initFormComponent(){
	/*$('#f_typeCode').combobox({
		valueField : 'code',
		textField : 'codeName',
		editable : false,
		multiple:false,
		required:false,
		panelHeight:200,
		data:lh.getDataFromCache('articleTypeArray')
	});*/
	
}


/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 开始 ========================================*/

//具体扩展方法列表，请查看 /js/common/back_template.js 文件中的顶部说明
//示例：function afterAddOrUpdate(mainObj, rsp){return true;} //执行新增或修改之前的拦截方法，返回false则不执行新增修改
//使用：将需要扩展的方法复制到对应业务JS文件中，在扩展方法体内执行自定义业务逻辑。

/**================================= 模板JS中的拦截方法或回调方法，根据实际业务需求扩展 结束 ========================================*/


