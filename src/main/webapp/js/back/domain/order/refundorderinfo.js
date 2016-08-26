/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'order_info',
	mainObjUpperName : 'OrderInfo'
}

$(function() {
	loadGrid();
	initQueryComponent();
	initParam();//查询条件
//	initComboData();
});
function initParam(){
	if(lh.param){
		var orderStatusCode = lh.param.orderStatusCode;
		if(orderStatusCode){
			$('#sc_orderStatusCode').combobox('setValue', orderStatusCode);
			doSearch();
		}
	}
}

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
	    pageList:[5,10,20,30,50],//每页数据条数选择数组
	    width:lh.dom.clientSafeWidth-1,
	    height:lh.dom.clientHeight-122,
	    columns:[
		[
			{field:'checkbox',title:'多选框',checkbox:true},
			{field:'id',title:'',hidden:true},
			{field:'operate',title:'操作',width:160,align:'center',formatter: function(value,row,index){
		        return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">审核</span>'+//jumpToAuctionInstUpdate
//	     		' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
	    		'<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
	    		' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
	        }},
			{field:'orderSn',title:'订单编号',width:180,align:'center',formatter: function(value,row,index){
				if(!value)return '';
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
	        {field:'nameUser',title:'下单客户名',width:140,align:'center'},
	        {field:'userRealName',title:'客户真实姓名',width:140,align:'center'},
	        {field:'shopName',title:'门店名',width:140,align:'center'},
	        //有用
	        {field:'providerName',title:'技师',width:140,align:'center'},
	        {field:'providerRealName',title:'技师真实姓名',width:140,align:'center'},
//	        {field:'mainStatus',title:'订单状态',width:60,align:'center',formatter: function(value,row,index){
//	        	var status = "";
//	        	if(value == 1){status = '<span style="color:orange">未结束订单</span>';}
//	        	else if(value == 2){status = '<span style="color:orange">未结束订单</span>';}
//	        	else if(value == 3){status = '<span style="color:orange">未结束订单</span>';}
//	        	return status;
//	        }},
	        {field:'orderStatusCode',title:'订单状态code',width:150,align:'center',formatter: function(value,row,index){
	        	var status = "预约成功";
	        	if(value == 'order_status_todo'){status = '<span style="color:orange">商家未接单</span>';}
	        	else if(value == 'order_status_todo1'){status = '<span style="color:blue">商家派单中</span>';}
	        	else if(value == 'shipping_status_todo'){status = '<span style="color:orange">服务未开始</span>';}
	        	else if(value == 'shipping_status_receive'){status = '<span style="color:blue">接受服务</span>';}
	        	else if(value == 'shipping_status_done'){status = '<span style="color:blue">服务完成</span>';}
//	        	else if(value == 'shipping_status_return'){status = '<span style="color:blue">退回服务</span>';}
	        	else if(value == 'order_status_done'){status = '<span style="color:green">订单完成</span>';}
	        	else if(value == 'order_status_cancel'){status = '<span style="color:orange">订单取消</span>';}
	        	else if(value == 'order_status_invalid'){status = '<span style="color:green">订单结束</span>';}
	        	else if(value == 'order_status_apply_return'){status = '<span style="color:red">订单申请退款</span>';}
	        	else if(value == 'order_status_noreturn'){status = '<span style="color:red">拒绝退款申请</span>';}
	        	else if(value == 'order_status_return'){status = '<span style="color:green">订单已退款</span>';}
	        	return status;
	        }},    
	        {field:'goodsName',title:'服务名',width:180,align:'center'},
//	        {field:'shippingStatusCode',title:'服务进度情况code',width:150,align:'center',formatter: function(value,row,index){
//	        	var status = "预约成功";
//	        	if(value == 'shipping_status_todo'){status = '<span>服务未开始</span>';}
//	        	else if(value == 'shipping_status_done'){status = '<span style="color:orange">服务完成</span>';}
//	        	else if(value == 'shipping_status_receive'){status = '<span style="color:orange">接受服务</span>';}
//	        	else if(value == 'shipping_status_return'){status = '<span style="color:orange">服务退回</span>';}
//	        	return status;
//	        }},
	        {field:'shippingId',title:'服务方式（类型）',width:100,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "门店服务";
	        	if(value == 2){isRealAuth = '<span style="color:green">上门服务</span>';}
	        	return isRealAuth;
	        }},
	        {field:'provinceName',title:'所在省',width:120,align:'center'},
	        {field:'cityName',title:'所在市',width:220,align:'center'},
	        {field:'address',title:'详细地址',width:180,align:'center'},
	        {field:'goodsNumber',title:'订单次数',width:120,align:'center'},
	        {field:'shopPrice',title:'单价',width:120,align:'center'},
	        {field:'totalPrice',title:'总支付金额',width:160,align:'center'},
	        {field:'couponCode',title:'优惠券码',width:180,align:'center',formatter: function(value,row,index){
				if(!value)return '';
				var dom = '<span>'+value+'</span>' +
				'<button class="copy_btn pointer fr" data-clipboard-text="'+value+'">复制</button>';
	        	return dom;
	        }},
//	        {field:'couponId',title:'优惠券ID',width:180,align:'center'},
	        {field:'couponDiscount',title:'折扣',width:100,align:'center'},
	        {field:'payName',title:'支付方式',width:120,align:'center'},
	        {field:'phone',title:' 客户手机',width:180,align:'center'},
	        {field:'tel',title:'客户电话',width:160,align:'center'},
	        {field:'email',title:'客户email',width:180,align:'center'},
	        {field:'postscript',title:'订单留言',width:200,align:'center'},
//	        {field:'bespeakStartTime',title:'支付时间',width:120,align:'center',formatter: function(value,row,index){
//	        	return lh.formatGridDateTime(value);
//	        }},
	        {field:'createdBy',title:'创建人',width:120,align:'center'},
	        {field:'createdAt',title:'创建时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'deletedBy',title:'删除人',width:120,align:'center'},
	        {field:'deletedAt',title:'删除时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'updatedBy',title:'修改人',width:120,align:'center'},
	        {field:'updatedAt',title:'修改时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }}
	        ]],
	    /*,onSelect:function(rowIndex, rowData){onClickRowOfGrid(rowIndex, rowData);},
	    onClickRow: function(index, row){},
	    loadFilter: function(data){return data;},
		onLoadSuccess:function(data){} */
	        onLoadError: function(data){
	        	lh.onGridLoadError(data);
		    },
		    onDblClickRow: function(index, row){
		    	openMainObjWin(index);
//		    	lh.onGridDblClickRow(index, row);
	        },
		    onLoadSuccess:function(data){
		    	lh.onGridLoadSuccess(data);
		   }  
		});
	lh.clipboard();//复制功能
	
}

/////** 初始化下拉列表数据，存入缓存，便于复用 */
//function initComboData(){
//	lh.loadComboDataToCache({url:'/back/getRootDictArray',cacheName:'rootDictArray', domId:'#sc_parentCode'});
//}

/** 初始化查询条件中的组件及数据 */
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
			'name' : '空闲'
		},{
			'id' : 2,
			'name' : '服务中'
		}]
	});
	
	$('#sc_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getusernameArray'
	});
	$('#sc_goodsId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getgoodsnameArray'
	});
	$('#sc_providerId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getprovidernameArray'
	});
	$('#sc_shippingId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'id' : 1,
			'name' : '门店服务'
		},{
			'id' : 2,
			'name' : '上门服务'
		}]
	});
	$('#sc_orderStatusCode').combobox({
		valueField : 'code',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'code' : 'order_status_todo',
			'name' : '订单未开始'
		},{
			'code' : 'shipping_status_todo',
			'name' : '服务未开始'
		},{
			'code' : 'shipping_status_done',
			'name' : '服务完成'
		},{
			'code' : 'shipping_status_receive',
			'name' : '服务进行中'
		},{
			'code' : 'shipping_status_return',
			'name' : '服务取消'
		},{
	    	'code' : 'order_status_done',
			'name' : '订单完成'
		},{
			'code' : 'order_status_cancel',
			'name' : '订单取消'
		},{
	    	'code' : 'order_status_invalid',
			'name' : '订单结束'
		},{
			'code' : 'order_status_return',
			'name' : '订单已退款'
		},{
	    	'code' : 'order_status_apply_return',
			'name' : '订单申请退款'
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
	    /*,onSelect: function(rec){
            var url = '/getCity?provinceId='+rec.id;
            $('#area').combobox('reload', url);
        }*/
	});
	$('#sc_province').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180,
		url:'/back/getProvince',
		onSelect: function(rec){
			var $city = $('#sc_city');
			$city.combobox('clear');
            var url = '/back/getCity?provinceId='+rec.id;
            $city.combobox('reload', url);
        }
	});		
}


//初始化组件
function initFormComponent(){
	$('#f_shopId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		readonly:true,
		disabled:true,
		multiple : false,
		required : false,
		panelHeight : 200,
		url:'/back/getShopArray',
		onSelect: function(rec){
			 var typeId=$("#f_shippingId").combobox("getValue");
			 var userId=$("#f_userId").combobox("getValue");
			 //选择店铺后只将该店铺所有服务取出
			 var $goodsId = $('#f_goodsId');
			 $goodsId.combobox('clear');
	         var url = '/back/getgoodss?shopId='+rec.id;
	         $goodsId.combobox('reload', url);
	         //选择店铺后只将该店铺所有技师取出
	         var $providerId = $('#f_providerId');
	         $providerId.combobox('clear');
	         var url1 = '/back/getproviders?shopId='+rec.id;
	         $providerId.combobox('reload', url1);
	         
			    if(typeId==1){
				 lh.post('front', '/back/getShopList?id='+rec.id, function(rsp) {
				    var data=rsp.rows;
				    $("#f_address").textbox("setValue",data[0].address);
				    }, 'json');
			     }else if(typeId==2){
			    	 lh.post('front', '/back/getUserList?id='+userId, function(rsp) {
						    var data=rsp.rows;
						    $("#f_address").textbox("setValue",data[0].address);
						    }, 'json');
			     }
			    }
	});
	
	$('#f_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    readonly:true,
	    disabled:true,
	    panelHeight:'auto',
	    url:'/back/getusernameArray',
	    onSelect: function(rec){
		    	  var typeId=$("#f_shippingId").combobox("getValue");
		    	  var shopId=$("#f_shopId").combobox("getValue");
		    	  if(typeId==1){
			    	lh.post('front', '/back/getShopList?id='+shopId, function(rsp) {
			    		var data=rsp.rows;
			    		$("#f_address").textbox("setValue",data[0].address);
			    	}, 'json');
			    }else if(typeId==2){
				    	lh.post('front', '/back/getUserList?id='+rec.id, function(rsp) {
				    		var data=rsp.rows;
				    		$("#f_address").textbox("setValue",data[0].address);
				    	}, 'json');
			    	 
			    }
		    }
	});
	$('#f_goodsId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		readonly:true,
		disabled:true,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
//	    url:'/back/getgoodss'
	    url:'/back/getgoodsnameArray',
		onSelect: function(rec){
			 var $shopId = $('#f_shopId');
	         var url = '/back/getgoodss?shopId='+$shopId;
	         $goodsId.combobox('reload', url);
		}
	});
	$('#f_providerId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getprovidernameArray'
	});
	$('#f_couponId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getcouponnameArray',
	    onSelect: function(rec){
	    	  var couponId=$("#f_couponId").combobox("getValue");
	    	  var totalPrice=$("#f_totalPrice").textbox("getValue");
//	    	  if(typeId==1){
		    	lh.post('front', '/back/getCouponList?id='+couponId, function(rsp) {
		    		var data=rsp.rows;
		    		if(data[0].way==1){
		    		$("#f_couponDiscount").textbox("setValue",data[0].discount);
		    		$("#f_totalPrice").textbox("setValue",totalPrice-(data[0].discount));
		    		}else if(data[0].way==2){
		    		$("#f_couponDiscount").textbox("setValue",data[0].discount);
		    		$("#f_totalPrice").textbox("setValue",totalPrice*(data[0].discount)%100);
		    		}else{
		    			$("#f_couponDiscount").textbox("");
		    			alert("未有优惠！");
		    		}
		    	}, 'json');
//		    }else if(typeId==2){
//			    	lh.post('front', '/back/getUserList?id='+rec.id, function(rsp) {
//			    		var data=rsp.rows;
//			    		$("#f_address").textbox("setValue",data[0].address);
//			    	}, 'json');
//		    	 
//		    }
	    }	
	});
	$('#f_shippingId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
	    	'id' : 1,
			'name' : '到店服务'
		},{
			'id' : 2,
			'name' : '上门服务'
		}],
	  onSelect: function(rec){
		  if(rec.id==1){
			  var shopId=$("#f_shopId").combobox("getValue");
			  if(shopId>0){
	    	lh.post('front', '/back/getShopList?id='+shopId, function(rsp) {
	    		var data=rsp.rows;
	    		$("#f_address").textbox("setValue",data[0].address);
	    	}, 'json');
			  }
	    }else if(rec.id==2){
	    	  var userId=$("#f_userId").combobox("getValue");
	    	  if(userId>0){
		    	lh.post('front', '/back/getUserList?id='+userId, function(rsp) {
		    		var data=rsp.rows;
		    		$("#f_address").textbox("setValue",data[0].address);
		    	}, 'json');
	    	  }
	    }
	    }
	});
	$('#f_orderStatusCode').combobox({
		valueField : 'code',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    data : [{
			'code' : 'order_status_todo',
			'name' : '订单未开始'
		},{
			'code' : 'shipping_status_todo',
			'name' : '服务未开始'
		},{
			'code' : 'shipping_status_done',
			'name' : '服务完成'
		},{
			'code' : 'shipping_status_receive',
			'name' : '服务进行中'
		},{
			'code' : 'shipping_status_return',
			'name' : '服务取消'
		},{
	    	'code' : 'order_status_done',
			'name' : '订单完成'
		},{
			'code' : 'order_status_cancel',
			'name' : '订单取消'
		},{
	    	'code' : 'order_status_invalid',
			'name' : '订单结束'
		},{
			'code' : 'order_status_return',
			'name' : '订单已退款'
		},{
	    	'code' : 'order_status_apply_return',
			'name' : '订单申请退款'
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
		url:'/back/getProvince',
		onSelect: function(rec){
			var $city = $('#f_city');
			$city.combobox('clear');
            var url = '/back/getCity?provinceId='+rec.id;
            $city.combobox('reload', url);
        }
	});
	
}

function afterOpenWin(data, operation, isReadOnly){
	//初始化按店铺选中服务
	var shopId = $('#f_shopId').combobox('getValue');//获取店铺
	var goodsId = $('#f_goodsId').combobox('getValue');
	var url = '/back/getgoodss?shopId='+shopId;
//	alert(shopId);
    $('#f_goodsId').combobox('reload', url);
  //初始化按店铺选中技师
//	var shopId = $('#f_shopId').combobox('getValue');//获取技师
//	var providerId = $('#f_providerId').combobox('getValue');
	var url1 = '/back/getproviders?shopId='+shopId;
//	alert(shopId);
    $('#f_providerId').combobox('reload', url1);
}
/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	return true;
}

//同意申请
function AuditRefundYes(){
	var rows = lh.$grid.datagrid('getSelected');
	var id=rows.id;
	var howOos=$("#f_howOos").val();
	var userId=$("#f_userId").val();
		//通过申请
		lh.post('back', '/back/addOrUpdateAuditRefund', {id:id,AttrInt:1,userId:userId,howOos:howOos}, function(rsp) {
		}, 'json');
	closeMainObjWin();
	location.href = "/back/page/orderinfo?ordertype=order";//location.href实现客户端页面的跳转  
//	loadGrid();
}
//不同意申请
function AuditRefundNo(){
	var rows = lh.$grid.datagrid('getSelected');
	var id=rows.id;
	var howOos=$("#f_howOos").val();
	var userId=$("#f_userId").val();
		//不通过认证
		lh.post('back', '/back/addOrUpdateAuditRefund', {id:id,AttrInt:2,userId:userId,howOos:howOos}, function(rsp) {
		}, 'json');
	closeMainObjWin();
	location.href = "/back/page/orderinfo?ordertype=order";//location.href实现客户端页面的跳转  
//	loadGrid();
}

///** 跳转：用户资金 */
//function jumpToProviderMoney(){
//	var url = "/back/page/providerFund";
//	var id = $('#providerId').val();
//	if(id){
//		url += "?providerId="+id;
//	}
//	subShowMain('用户资金', url)
//}
//
///** 跳转：用户控制 */
//function jumpToProviderControl(){
//	var url = "/back/providerControl";
//	var id = $('#providerId').val();
//	if(id){
//		url += "?providerId="+id;
//	}
//	subShowMain('用户控制', url)
//}


