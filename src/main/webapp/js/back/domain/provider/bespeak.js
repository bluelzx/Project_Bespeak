/** 基础数据和基础设置 */
lh.config = {
	mainObjLowerName : 'bespeak',
	mainObjUpperName : 'Bespeak'
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
			{field:'orderSn',title:'订单编号',width:180,align:'center'},
	        {field:'userName',title:'用户名',width:140,align:'center'},
	        {field:'shopName',title:'门店',width:140,align:'center'},
	        {field:'goodsName',title:'服务',width:140,align:'center'},
	        {field:'providerName',title:'技师',width:140,align:'center'},
	        {field:'couponName',title:'优惠券',width:140,align:'center'},
	        {field:'operate',title:'操作',width:200,align:'center',formatter: function(value,row,index){
	        	return '<span class="opt_alive"><span class="opt_cuation" onclick="openMainObjWin('+index+',\'update\')">修改</span>'+//jumpToAuctionInstUpdate
     		   ' |  <span class="opt_green" onclick="openMainObjWin('+index+',\'read\')">查看</span></span>'+//jumpToAuctionInst
    		   '<span class="opt_trash"><span class="opt_cuation"  onclick="lh.commonBatchThoroughDelete('+row.id+')">彻底删除</span>'+
    		   ' |  <span class="opt_green" onclick="lh.commonBatchRecover('+row.id+')">恢复</span></span>';
            }},
//	        {field:'mainStatus',title:'订单状态',width:60,align:'center',formatter: function(value,row,index){
//	        	var status = "空闲";
//	        	if(value == 2){status = '<span style="color:orange">服务中</span>';}
//	        	return status;
//	        }},
	        {field:'typeId',title:'预约类型',width:80,align:'center',formatter: function(value,row,index){
	        	var isRealAuth = "门店服务";
	        	if(value == 2){isRealAuth = '<span style="color:green">上门服务</span>';}
	        	return isRealAuth;
	        }},
	        {field:'province',title:'地区',width:160,align:'center',formatter: function(value,row,index){
	        	var province = row.provinceName,city = row.cityName;
	        	if(!province){province = '';}
	        	if(!city){city = '';}/*else{city = ','+city}*/
	        	return province+city;
	        }},
	        {field:'address',title:'详细地址',width:180,align:'center'},
	        {field:'goodsNumber',title:'预约次数',width:180,align:'center'},
	        {field:'price',title:'支付金额',width:180,align:'center'},
	        {field:'bespeakStartTime',title:'预约开始时间',width:120,align:'center',formatter: function(value,row,index){
	        	return lh.formatGridDateTime(value);
	        }},
	        {field:'bespeakEndTime',title:'预约结束时间',width:120,align:'center',formatter: function(value,row,index){
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
		    	lh.onGridDblClickRow(index, row);
	        },
		    onLoadSuccess:function(data){
		    	lh.onGridLoadSuccess(data);
		   }  
		});
	lh.clipboard();//复制功能
	
}

/** 初始化下拉列表数据，存入缓存，便于复用 */
function initComboData(){
	lh.loadComboDataToCache({url:'/back/getRootDictArray',cacheName:'rootDictArray', domId:'#sc_parentCode'});
}

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
	$('#sc_typeId').combobox({
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
	
	$('#sc_city').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180
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
		multiple:false,
	    required:false,
	    panelHeight:'auto',
		url:'/back/getShopArray',
		onSelect: function(rec){
			var userId = $('#f_userId').combobox('getValue');
			var $couponId = $('#f_couponId');
	    	$couponId.combobox('clear');
	    	if(userId>0){
	    	var url = '/back/getcouponnameArray'+"/"+userId+"/"+rec.id;
	        $couponId.combobox('reload', url);
	    	}
			var typeId=$("#f_typeId").combobox("getValue");
			var userId=$("#f_userId").combobox("getValue");
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
			    var goodsNumber=$("#f_goodsNumber").numberbox("getValue");
	    		var groupNum=$("#f_groupNum").numberbox("getValue");
			    var newValue;
			    var total=0;
	    		if(goodsNumber>0){
	    			var price=$("#price").attr("value");
	    			newValue=goodsNumber
	    		}else if(groupNum>0){
	    			var price=$("#groupPrice").attr("value");
	    			newValue=groupNum
	    		}
	    		
	    		if(money>0){
	    			total=price*newValue-money;
	    		} else if(discount>0){
	    			total=(price*newValue)*(discount/100);
	    		}else{
	    			if(newValue>0){
	    			total=price*newValue;
	    			}
	    		}
	    		$("#f_price").textbox("setValue",total);
			    }
	});
	$('#f_mainStatus').combobox({
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
	$('#f_couponId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180
	    ,onSelect: function(rec){
	    	lh.post('back', '/back/getCoupon?id='+rec.id, function(rsp) {
	    		var data=rsp.rows;
	    		$("#money").attr("value",data.money);
	    		$("#discount").attr("value",data.discount);
	    		var money=$("#money").attr("value");
	    		var discount=$("#discount").attr("value");
	    		var number=$("#number").attr("value");
	    		var goodsNumber=$("#f_goodsNumber").numberbox("getValue");
	    		var groupNum=$("#f_groupNum").numberbox("getValue");
	    		var newValue;
	    		if(goodsNumber>0){
	    			var price=$("#price").attr("value");
	    			newValue=goodsNumber
	    		}else if(groupNum>0){
	    			var price=$("#groupPrice").attr("value");
	    			newValue=groupNum
	    		}
	    		var total=0;
	    		if(money>0){
	    			total=price*newValue-money;
	    		} else if(discount>0){
	    			total=(price*newValue)*(discount/100);
	    		}else{
	    			total=price*newValue;
	    		}
	    		$("#f_price").textbox("setValue",total);
	    	}, 'json');
        }
	});
	$('#f_userId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getusernameArray',
	    onSelect: function(rec){
		    	var typeId=$("#f_typeId").combobox("getValue");
		    	var shopId=$("#f_shopId").combobox("getValue");
		    	if(typeId==1){
			    	lh.post('back', '/back/getShopList?id='+shopId, function(rsp) {
			    		var data=rsp.rows;
			    		$("#f_address").textbox("setValue",data[0].address);
			    	}, 'json');
			    }else if(typeId==2){
				    	lh.post('back', '/back/getUserList?id='+rec.id, function(rsp) {
				    		var data=rsp.rows;
				    		$("#f_address").textbox("setValue",data[0].address);
				    	}, 'json');
			    }
		    	if(shopId>0){
		    	  var $couponId = $('#f_couponId');
		    	    $couponId.combobox('clear');
		    	    var url = '/back/getcouponnameArray'+"/"+rec.id+"/"+shopId;
		            $couponId.combobox('reload', url);
	                }
	    		var goodsNumber=$("#f_goodsNumber").numberbox("getValue");
	    		var groupNum=$("#f_groupNum").numberbox("getValue");
	    		var money=$("#money").attr("value");
	    		var discount=$("#discount").attr("value");
	    		var couponId=$("#f_couponId").combobox("getValue");
	    		var newValue;
	    		if(goodsNumber>0){
	    			var price=$("#price").attr("value");
	    			newValue=goodsNumber
	    		}else if(groupNum>0){
	    			var price=$("#groupPrice").attr("value");
	    			newValue=groupNum
	    		}
	    		var total=0;
	    		if(money>0&&couponId>0){
	    			total=price*newValue-money;
	    		} else if(discount>0&&couponId>0){
	    			total=(price*newValue)*(discount/100);
	    		}else{
	    			total=price*newValue;
	    		}
	    		if(goodsNumber<1){
	    			$("#f_price").textbox("setValue",price);
	    		}else{
	    		$("#f_price").textbox("setValue",total);
	    		}
		    }
	});
	$('#f_goodsId').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight:'auto',
	    url:'/back/getgoodsnameArray',
	    onSelect: function(rec){
	    	lh.post('back', '/back/getGoods?id='+rec.id, function(rsp) {
	    		var data=rsp.rows;
	    		$("#goodsNumber").attr("value",data.groupNum);
	    		$("#groupPrice").attr("value",data.groupPrice);
	    		$("#price").attr("value",data.shopPrice);
	    		$("#timeNum").attr("value",data.timeNum);
	    		var goodsNumber=$("#f_goodsNumber").numberbox("getValue");
	    		var groupNum=$("#f_groupNum").numberbox("getValue");
	    		var money=$("#money").attr("value");
	    		var discount=$("#discount").attr("value");
	    		var couponId=$("#f_couponId").combobox("getValue");
	    		var newValue;
	    		if(goodsNumber>0){
	    			var price=$("#price").attr("value");
	    			newValue=goodsNumber
	    		}else if(groupNum>0){
	    			var price=$("#groupPrice").attr("value");
	    			newValue=groupNum
	    		}
	    		var total=0;
	    		if(money>0&&couponId>0){
	    			total=price*newValue-money;
	    		} else if(discount>0&&couponId>0){
	    			total=(price*newValue)*(discount/100);
	    		}else{
	    			total=price*newValue;
	    		}
	    		if(goodsNumber<1){
	    			$("#f_price").textbox("setValue",price);
	    		}else{
	    		$("#f_price").textbox("setValue",total);
	    		}
	    	}, 'json');
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
	$('#f_typeId').combobox({
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
	
	$('#f_city').combobox({
		valueField : 'id',
		textField : 'name',
		editable : false,
		multiple:false,
	    required:false,
	    panelHeight: 180
	    /*,onSelect: function(rec){
            var url = '/getCity?provinceId='+rec.id;
            $('#area').combobox('reload', url);
        }*/
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
	
	$('#f_goodsNumber').numberbox({
		editable : true,
		onChange: function(newValue,oldValue){
	    	if(!newValue){
	    		$('#f_groupNum').numberbox({editable:true,disabled:false});
	    	}else{
	    		$('#f_groupNum').numberbox({editable:false,disabled:true});
	    		var money=$("#money").attr("value");
	    		var discount=$("#discount").attr("value");
	    		var number=$("#number").attr("value");
	    		var price=$("#price").attr("value");
	    		var couponId=$("#f_couponId").combobox("getValue");
	    		var goodsId=$("#f_goodsId").combobox("getValue");
	    		var total=0;
	    		if(money>0&&couponId>0){
	    			total=price*newValue-money;
	    		} else if(discount>0&&couponId>0){
	    			total=(price*newValue)*(discount/100);
	    		}else{
	    			if(goodsId>0){
	    			total=price*newValue;
	    			}
	    		}
	    		$("#f_price").textbox("setValue",total);
	    	}
        }
	});
	
	$('#f_groupNum').numberbox({
		editable : true,
	    onChange: function(newValue,oldValue){
	    	if(!newValue){
	    		$('#f_groupNum').numberbox({editable:true,disabled:false});
	    		$('#f_goodsNumber').numberbox({editable:true,disabled:false});
	    	}else{
	    		$('#f_goodsNumber').numberbox({editable:false,disabled:true});
	    		var money=$("#money").attr("value");
	    		var discount=$("#discount").attr("value");
	    		var goodsNumber=$("#goodsNumber").attr("value");
	    		var price=$("#groupPrice").attr("value");
	    		var goodsId=$("#f_goodsId").combobox("getValue");
	    		var number=goodsNumber*newValue;
	    		var total=0;
	    		if(money>0){
	    			total=price*newValue-money;
	    		} else if(discount>0){
	    			total=(price*newValue)*(discount/100);
	    		}else{
	    			if(goodsId>0){
		    			total=price*newValue;
		    			}
	    		}
	    		var number=$("#number").attr("value",number);
	    		$("#f_price").textbox("setValue",total);
	    	}
        }
	});
}
function onSelect(d) {
    var issd = false, sd = issd ? d : new Date(), ed = issd ? new Date($('#f_bespeakStartTime').datebox('getValue')) : d;
        if (ed < sd) {
        	$.messager.alert('提示',"只能提前一天预约");
            //只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
            $('#f_bespeakStartTime').datebox('setValue', '').datebox('hidePanel');
        }
    }
function afterOpenWin(data, operation, isReadOnly){
	if(data){
		$("#f_bespeakStartTime").datetimebox("setValue",lh.formatGridDateTime(data.bespeakStartTime));
	}else{
		$('#f_goodsNumber').numberbox({editable:true,disabled:false});
		$('#f_groupNum').numberbox({editable:true,disabled:false});
	}
	
	var province = $('#f_province').combobox('getValue');//更新市
	var city = $('#f_city').combobox('getValue');
    var url = '/back/getCity?provinceId='+province;
    $('#f_city').combobox('reload', url);
    
    var couponId = $('#f_couponId').combobox('getValue');
    var goodsId = $('#f_goodsId').combobox('getValue');
    var shopId=$("#f_shopId").combobox("getValue");
    var userId = $('#f_userId').combobox('getValue');
    if(userId>0){
    var url = '/back/getcouponnameArray'+"/"+userId+"/"+shopId;
     $('#f_couponId').combobox('reload', url);
        }
    //初始化服务信息
    lh.post('back', '/back/getGoods?id='+goodsId, function(rsp) {
		var data=rsp.rows;
		var price=data.shopPrice*data.groupNum;
		$("#goodsNumber").attr("value",data.groupNum);
		$("#groupPrice").attr("value",data.groupPrice);
		$("#price").attr("value",data.shopPrice);
		$("#timeNum").attr("value",data.timeNum);
	}, 'json');
   //初始化优惠券信息
	lh.post('back', '/back/getCoupon?id='+couponId, function(rsp) {
		var data=rsp.rows;
		$("#money").attr("value",data.money);
		$("#discount").attr("value",data.discount);
	}, 'json');
	
}
/** 新增修改操作执行之前的拦截方法，返回false则不执行新增修改，如无对应操作可不用申明此方法 */
function preAddOrUpdate(mainObj){
	var number=$("#number").attr("value");
	if(number>0){
		mainObj.goodsNumber=number;
	}
	var timeNum=$("#timeNum").attr("value");
	mainObj.timeNum=timeNum;
	return true;
}

/** 跳转：用户资金 */
function jumpToProviderMoney(){
	var url = "/back/page/providerFund";
	var id = $('#providerId').val();
	if(id){
		url += "?providerId="+id;
	}
	subShowMain('用户资金', url)
}

/** 跳转：用户控制 */
function jumpToProviderControl(){
	var url = "/back/providerControl";
	var id = $('#providerId').val();
	if(id){
		url += "?providerId="+id;
	}
	subShowMain('用户控制', url)
}


