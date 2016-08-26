/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'shop',
	mainObjUpperName : 'Shop'
}

$(function() {
	loadGrid();
	initQueryComponent();
		// initComboData();
	});

/** 加载主表格 */
function loadGrid() {
	lh.$grid.datagrid({
		loadMsg : '',
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		striped : true,
		fitColumns : false,
		singleSelect : true,
		selectOnCheck : false,
		checkOnSelect : false,
		pagination : true,
		url : lh.config.gridUrl,
		queryParams : lh.config.queryObj,// 查询参数
		pageSize : 6,// 每页数据条数
		pageList : [3, 6, 8, 10, 20],// 每页数据条数选择数组
		width : lh.dom.clientSafeWidth - 1,
		height : lh.dom.clientHeight - 122,
		columns : [[{
					field : 'checkbox',
					title : '多选框',
					checkbox : true
				}, {
					field : 'id',
					title : '',
					hidden : true
				}, {
					field : 'operate',
					title : '操作',
					width : 120,
					align : 'center',
					formatter : function(value, row, index) {
						return '<span class="opt_alive"><span style="cursor: pointer;color: #EC4949" onclick="openMainObjWin('
								+ index
								+ ',\'update\')">修改</span>'
								+ '&nbsp;|&nbsp;<span class="update" style="cursor: pointer;color: green" onclick="openMainObjWin('
								+ index
								+ ',\'read\')">查看</span></span>'
								+ '<span class="opt_trash"><span style="cursor: pointer;color: #EC4949;" onclick="lh.commonBatchThoroughDelete('
								+ row.id
								+ ')">彻底删除</span>'
								+ '&nbsp;|&nbsp;<span style="cursor: pointer;color: green" onclick="lh.commonBatchRecover('
								+ row.id + ')">恢复</span></span>';
					}
				},
				// {field:'userSerial',title:'用户编号',width:220,align:'center',formatter:
				// function(value,row,index){
				// if(!value)return '';
				// var dom = '<span>'+value+'</span>' +
				// '<button class="copy_btn pointer fr"
				// data-clipboard-text="'+value+'">复制</button>';
				// return dom;
				// }},
				{
					field : 'serial',
					title : '加盟商编号',
					width : 220,
					align : 'center',
					formatter : function(value, row, index) {
						if (!value)
							return '';
						var dom = '<span>'
								+ value
								+ '</span>'
								+ '<button class="copy_btn pointer fr" data-clipboard-text="'
								+ value + '">复制</button>';
						return dom;
					}
				},
//				{field:'password',title:'密码',width:178,align:'center'},
				{
					field : 'userName',
					title : '商家负责人',
					width : 89,
					align : 'center',
					formatter : function(value, row, index) {
						return value;
						// return getusername(value);
					}
				}, {
					field : 'name',
					title : '加盟商名称',
					width : 220,
					align : 'center'
				}, {
					field : 'logo',
					title : '店铺LOGO',
					width : 200,
					align : 'center',
					formatter : function(value, row, index) {
						var logo = '<a href="'
								+ value
								+ '" target="_blank"><img style="height:40px;cursor:pointer;" src="'
								+ value + '"/></a>';
						if (!value)
							logo = '<span style="line-height:40px;">暂无图标<span>';
						return logo;
					}
				}, {
					field : 'licence',
					title : '营业执照',
					width : 200,
					align : 'center',
					formatter : function(value, row, index) {
						var licence = '<a href="'
								+ value
								+ '" target="_blank"><img style="height:40px;cursor:pointer;" src="'
								+ value + '"/></a>';
						if (!value)
							licence = '<span style="line-height:40px;">暂无图标<span>';
						return licence;
					}
				}, {
					field : 'provinceName',
					title : '所在省',
					width : 120,
					align : 'center'
				}, {
					field : 'cityName',
					title : '所在市',
					width : 220,
					align : 'center'
				}, {
					field : 'address',
					title : '详细地址',
					width : 220,
					align : 'center'
				},
				// {field:'licence',title:'营业执照',width:200,align:'center'},
				// {field:'licencePaths',title:'营业执照路径',width:220,align:'center'},
				{
					field : 'organizationCode',
					title : '组织机构代码',
					width : 120,
					align : 'center'
				}, {
					field : 'memberNum',
					title : '成员数',
					width : 100,
					align : 'center'
				}, {
					field : 'phone',
					title : '电话号码',
					width : 220,
					align : 'center'
				}, {
					field : 'phone2',
					title : '电话号码2',
					width : 200,
					align : 'center'
				}, {
					field : 'creditMargin',
					title : '信誉保证金',
					width : 100,
					align : 'center'
				}, {
					field : 'grade',
					title : '等级',
					width : 80,
					align : 'center'
				}, {
					field : 'score',
					title : '评分',
					width : 80,
					align : 'center'
				}, {
					field : 'filePaths',
					title : '附件路径',
					width : 220,
					align : 'center'
				}, {
					field : 'filePaths2',
					title : '营业执照的附件路径',
					width : 200,
					align : 'center'
				}, {
					field : 'mainStatus',
					title : '状态',
					width : 60,
					align : 'center',
					formatter : function(value, row, index) {
						var status = "启用";
						if (value == 2) {
							status = '<span style="color:orange">停用</span>';
						}
						return status;
					}
				}, {
					field : 'updatedBy',
					title : '修改人',
					width : 120,
					align : 'center'
				}, {
					field : 'updatedAt',
					title : '修改时间',
					width : 120,
					align : 'center',
					formatter : function(value, row, index) {
						return lh.formatGridDateTime(value);
					}
				}, {
					field : 'deletedBy',
					title : '删除人',
					width : 220,
					align : 'center'
				}, {
					field : 'deletedAt',
					title : '删除时间',
					width : 120,
					align : 'center',
					formatter : function(value, row, index) {
						var dateStr = "";
						if (value) {
							var d = new Date(value);
							var month = d.getMonth() + 1;
							dateStr = '' + d.getFullYear() + '/' + month + '/'
									+ d.getDate();
						}
						return dateStr;
					}
				}, {
					field : 'createdBy',
					title : '创建人',
					width : 220,
					align : 'center'
				}, {
					field : 'createdAt',
					title : '创建时间',
					width : 120,
					align : 'center',
					formatter : function(value, row, index) {
						var dateStr = "";
						if (value) {
							var d = new Date(value);
							var month = d.getMonth() + 1;
							dateStr = '' + d.getFullYear() + '/' + month + '/'
									+ d.getDate();
						}
						return dateStr;
					}
				}]],
		onLoadError : function(data) {
			lh.backDatagridErrorCheck(data);
		},
		onDblClickRow : function(index, row) {
			openMainObjWin(index, 'read');
		},
		onLoadSuccess : function(data) {
			lh.filtGridOperation();
			lh.clipboard();// 复制功能
		}
	});
}

// /** 初始化下拉列表数据，存入缓存，便于复用 */

function uploadPic(browse,i){
	initUploadSimple({
			showEdBtns : true,
			showItemDiv : true,
			multiFlag : false,
			multiReplace : true,
			chooseBtnDomId:browse,
			successFun : function(fileId, filePath) {
//				$("#upld_container_" + fileId).remove();
				$("#pic"+i).attr('src', filePath);
//				var picc = '<img src='+filePath+'>';
//				$("#piccc").append(picc);
				
			}
		});	
//	$("#upload_outer_div").empty();
//	$("#upload_outer_div1").empty();

}

/** 初始化表单中的组件及数据 */
function initFormComponent() {
	
//	var picNum = $("[name^='browse']").length;
//	var i = 1;
//	for(i;i<=picNum;i++){
//		initUploadSimple({
//				showEdBtns : true,
//				showItemDiv : true,
//				multiFlag : false,
//				multiReplace : true,
//				chooseBtnDomIds:'browse'+i,
//				successFun : function(fileId, filePath) {
//					$("#upld_container_" + fileId).remove();
//					$("#pic"+i).attr('src', filePath);
//				}
//			});
//	}
//	
//	$("#upload_outer_div").empty();
//	$("#upload_outer_div1").empty();

	
	$('#f_userId').combobox({
				valueField : 'id',
				textField : 'name',
				editable : false,
				multiple : false,
				required : false,
				panelHeight : 200,
				url : "/back/getusernameArray"
				,
			});

	$('#f_mainStatus').combobox({
				valueField : 'id',
				textField : 'name',
				editable : false,
				multiple : false,
				required : false,
				panelHeight : 'auto',
				data : [{
							'id' : 1,
							'name' : '启用'
						}, {
							'id' : 2,
							'name' : '停用'
						}]
			});
	$('#f_city').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : false,
		panelHeight : 180,
		url : '/getCitys'
		,
			// onSelect: function(rec){
			// var url = '/getCity?cityId='+rec.id;
			// alert(rec.id);
			// $('#f_city').combobox('reload', url);
			// }
		});

	$('#f_province').combobox({
				valueField : 'id',
				textField : 'name',
				editable : false,
				multiple : false,
				required : false,
				panelHeight : 180,
				url : '/getProvince',
				onSelect : function(rec) {
					var $city = $('#f_city');
					$city.combobox('clear');
					var url = '/getCity?provinceId=' + rec.id;
					$city.combobox('reload', url);
				}
			});
}

function onClickRowOfGrid() {
}

/** 初始化查询条件中的组件及数据 */
function initQueryComponent() {

	$('#sc_mainStatus').combobox({
				valueField : 'id',
				textField : 'name',
				editable : false,
				multiple : false,
				required : false,
				panelHeight : 'auto',
				data : [{
							'id' : 1,
							'name' : '启用'
						}, {
							'id' : 2,
							'name' : '停用'
						}]
			});

	$('#sc_city').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple : false,
		required : false,
		panelHeight : 180
			/*
			 * ,onSelect: function(rec){ var url =
			 * '/getCity?provinceId='+rec.id; $('#area').combobox('reload',
			 * url); }
			 */
		});

	$('#sc_province').combobox({
				valueField : 'id',
				textField : 'name',
				editable : false,
				multiple : false,
				required : false,
				panelHeight : 180,
				url : '/getProvince',
				onSelect : function(rec) {
					var $city = $('#sc_city');
					$city.combobox('clear');
					var url = '/getCity?provinceId=' + rec.id;
					$city.combobox('reload', url);
				}
			});

	$('#sc_ascOrdesc').combobox({
				valueField : 'id',
				textField : 'name',
				editable : false,
				multiple : false,
				required : false,
				panelHeight : 'auto',
				data : [{
							'id' : 1,
							'name' : '注册时间升序'
						}, {
							'id' : 2,
							'name' : '注册时间降序'
						}, {
							'id' : 3,
							'name' : '人员数升序'
						}, {
							'id' : 4,
							'name' : '人员数降序'
						}]
			});

}

function afterOpenWin(data, operation, isReadOnly) {
	if (!data) {
		$('#f_mainStatus').combobox('setValue', 1);
		$("#upload_outer_div").empty();
		$("#pic").attr('src', null);
		$('#f_mainStatus1').combobox('setValue', 1);
		$("#upload_outer_div1").empty();
		$("#pic1").attr('src', null);
		return;
	}
	$("#pic").attr('src', data.avatar);
	$("#filePaths").val(data.logo);
	$("#fileDBIds").val(data.logoPicId);
	$("#pic1").attr('src', data.avatar);
	$("#filePaths1").val(data.filePath);
	$("#fileDBIds1").val(data.logoPicId);
}

//更新密码reserpassword.js引用(action)url操作
function getResetPasswordUrl(){
	return '/back/updateShopPassword';
}

/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj) {
	// LOGO图片
	var filePaths = $("#filePaths").val();
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
	// 营业执照图片
	var filePaths1 = $("#filePaths1").val();
	if (!filePaths1) {
	} else {
		var ids = UPLOAD_OBJ.idsStr;
		if (filePaths1.substring(0, 1) != "/") {
			filePaths1 = filePaths1.substring(1);
			ids = ids.substring(1);
		}
		mainObj.filePaths = filePaths1;
	}
	// 尾加字段匹配到po的格式
	// if(mainObj && mainObj.expiryDate){
	// mainObj.expiryDate += ' 00:00:00';
	// }

	// if(mainObj.sendTime!=null && mainObj.sendTime!=''){
	//		
	// }else{
	// var date = new Date();
	// var year = date.getFullYear();
	// var month = date.getMonth()+1;
	// var day = date.getDate();
	// var hour = date.getHours();
	// var minute = date.getMinutes();
	// var second = date.getSeconds();
	// mainObj.sendTime = (year+'-'+month+'-'+day+'
	// '+hour+':'+minute+':'+second);
	// }
	// if(mainObj.sendTime==null && mainObj.sendTime==''){
	// mainObj.linkUrl=("http://"+mainObj.linkUrl);
	// }else{
	//	
	// }
	return true;
}

// /** 跳转：用户信息 */
// function jumpToUserInfo(){
// var url = "/back/userInfo";
// lh.subShowMain('用户信息', url);
// }
