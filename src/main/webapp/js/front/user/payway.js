Z_REAL_PAY_MONEY = 0;
Z_PAY_TYPE = undefined;
Z_PAY_WAY = undefined;
Z_ORDER_GOODS_ID = undefined;
Z_ADDRESS_ID = undefined;
$(function(){
	initData();
});

function initData(){
	var fromUrl = localStorage.getItem("pay_fromUrl");
	var payType = localStorage.getItem("pay_payType");
	var goodsId = localStorage.getItem("pay_goodsId");
	var money = localStorage.getItem("pay_money");
	var orderGoodsId = localStorage.getItem("pay_orderGoodsId");
	
	//TODO 测试
	
	payType = 2;
	orderGoodsId = 1;
	
	
	if(payType == 1){
		if(!money){lh.jumpR(fromUrl);return;}
		$('#creditMoney').text('￥'+money+'元');
	}else if(payType == 2){
		if(!orderGoodsId){lh.jumpR(fromUrl);return;}
		money = $('#orderGoodsMoney').val();
	}else{
		lh.jumpR(fromUrl);return;
	}
	var avaliableMoney = $('#avaliableMoney').val();
	money = new Number(money);
	avaliableMoney = new Number(avaliableMoney);
	if(avaliableMoney < money){
		$('#s11').prop('disabled', 'disabled');
		$('#moneyName').css('color','gray');
	}
	Z_REAL_PAY_MONEY = money;
	Z_PAY_TYPE = payType;
	Z_ORDER_GOODS_ID = orderGoodsId;
	
	if($('#addressNull').val()){
		initAddress();
	}else{
		$("#addressSelect").selectpicker({
		    showTick:true,
		    mobile:true,
		    size: 10
		});
	}
}

function initAddress(){
	$("#province").selectpicker({
	    showTick:true,
	    mobile:true,
	    size: 10
	});
	$('#province').on('changed.bs.select', function (e) {
		getCity();
	});
	$("#city").selectpicker({
		title:'',
		mobile:true,
	    showTick:true,
	    size: 10
	});
	getCity();
}

function choosePay(payWay){
	if(payWay == 1){
		$('#s12').prop('checked',false);
		$('#payPassIpt').show();
	}else if(payWay == 2){
		$('#s11').prop('checked',false);
		$('#payPassIpt').hide();
	}
}

function doPay(){
	var payWay1 = $('#s11').prop('checked');
	var payWay2 = $('#s12').prop('checked');
	if(payWay1){//余额
		Z_PAY_WAY = 1;
		var payPass = $('#payPass').val();
		if(Z_PAY_TYPE == 1){//余额支付保证金
			increaseCreditMoney(payPass);
		}else if(Z_PAY_TYPE == 2){//余额支付订单
			//payMoneyForOrderGoods(payPass);
			checkAddress(payPass);
		}
	}else if(payWay2){//微信支付
		Z_PAY_WAY = 2;
		if(Z_PAY_TYPE == 2){//微信支付订单
			checkAddress(payPass);
		}else{
			lh.jumpR('/fund/charge/pay');
		}
		
	}else{
		lh.alert('请选择支付方式');
	}
}

function payMoneyForOrderGoods(payPass){
	if(!Z_ADDRESS_ID)lh.alert('请选择收货地址');return;
	$.post('/payMoneyForOrderGoods',{orderGoodsId:Z_ORDER_GOODS_ID,payPass:payPass,addressId:Z_ADDRESS_ID},function(rsp){
		if(rsp.success){
			lh.alert('恭喜您支付成功', backToFromUrl);
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function increaseCreditMoney(payPass){
	$.post('/increaseCreditMoney',{money:Z_REAL_PAY_MONEY,payPassword:payPass},function(rsp){
		if(rsp.success){
			lh.alert('恭喜您支付成功', backToFromUrl);
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function backToFromUrl(){
	var fromUrl = localStorage.getItem("pay_fromUrl");
	locatoin.href = fromUrl;
}

function getCity(city){
	var provinceId = $("#province").val();
	$.post('/getCity',{provinceId:provinceId},function(rsp){
		if(rsp){
			var dom = '<select id="city""><option value="">请选择</option>';
			$("#cityDiv").empty();
			for(var i = 0;i < rsp.length;i++){
				dom +=' <option';
				if(city && city == rsp[i].id)dom += ' selected="selected" ';
				dom += ' value="'+rsp[i].id+'">'+rsp[i].name+'</option>';
			}
			dom += '</select>';
			$("#cityDiv").append(dom);
			$("#city").selectpicker({
				title:'',
			    showTick:true,
			    mobile:true,
			    size: 10
			});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}


function checkAddress(payPass){
	if(!Z_ADDRESS_ID){
		var selectObj = $('#addressSelect');
		if(selectObj && selectObj.length > 0){
			var addressSelectId = selectObj.val();
			if(addressSelectId){
				Z_ADDRESS_ID = addressSelectId;
			}else{
				lh.alert('请选择收货地址');return;
			}
		}
	}
	if(Z_ADDRESS_ID){
		if(Z_PAY_WAY == 1){
			payMoneyForOrderGoods(payPass);
		}else if(Z_PAY_WAY == 2){
			updateOrderAddressAndJumpToPay();
		}
	}
	
	var userAddressId = $("#userAddressId").val();
	var receiverName = $("#receiverName").val();
	var addressDetail = $("#addressDetail").val();
	var phone = $("#phone").val();
	var province = $("#province").val();
	var city = $("#city").val();
	if(!receiverName){lh.alert('请输入收货人的姓名');return;}
	if(!province){lh.alert('请输入收货人所在的省或直辖市');return;}
	if(!city){lh.alert('请输入收货人所在的城市');return;}
	if(!addressDetail){lh.alert('请输入收货人的详细地址');return;}
	if(!phone){lh.alert('请输入收货人的联系电话');return;}
	var obj = {receiverName:receiverName,addressDetail:addressDetail,phone:phone,province:province,city:city,isDefault:2};
	frontBaseLoadingOpen('正在保存收货地址...');//加载遮罩
	SAVING_FLAG = true;
	$.post('/addOrUpdateUserAddress',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		$('#baseDialog').hide();
		if(rsp.status == 'success'){
			Z_ADDRESS_ID = rsp.addressId;
			localStorage.setItem("pay_addressId", Z_ADDRESS_ID);
			if(Z_PAY_WAY == 1){
				payMoneyForOrderGoods(payPass);
			}else if(Z_PAY_WAY == 2){
				updateOrderAddressAndJumpToPay();
			}
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
}

function updateOrderAddressAndJumpToPay(addressId){
	if(SAVING_FLAG)return;
	frontBaseLoadingOpen('正在保存收货地址...');//加载遮罩
	SAVING_FLAG = true;
	$.post('/updateOrderAddress', {addressId:Z_ADDRESS_ID, orderGoodsId:Z_ORDER_GOODS_ID},function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		if(rsp.status == 'success'){
			lh.jumpR('/fund/charge/pay');
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
}


