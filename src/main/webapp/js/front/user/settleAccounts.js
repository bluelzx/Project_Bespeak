CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
$(function(){
	getAllReceiveAddressList();
	lh.scrollBottom(getAllReceiveAddressList);
});

function getAllReceiveAddressList(){
	var userId = $("#userId").val();
	var param = {};
	param.userId = userId;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	$.post('/getUserAddressList',param,function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			if(rsp.rows.length <= 0 && CURRENT_PAGE == 1){
				frontBaseConfirm('您还没有添加收货地址,请先到个人信息下的设置收货地址中添加收货地址.取消返回上一页面，确定将跳转到添加收货地址页面.','jumpToAddress()','goBack()');
			}else{
				var count = rsp.total;
				if(count && count > 0){
					makeAllReceiveAddressListDom(rsp.rows,1);
					CURRENT_PAGE++;
				}else{
					$('#resultTip').text('没有更多数据').show();
				}
			}
		}else{
			lh.alert(rsp.msg);
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}

function jumpToAddress(){
	var r = $("#r").val();
	var url = "/address";
	if(r)url += "?r="+r;
	window.location.href = url;
}
function goBack(){
	var r = $("#r").val();
	var url = "/waitPayMoney";
	if(r)url += "?r="+r;
	window.location.href = url;
}

function makeAllReceiveAddressListDom(commentList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:commentList});
	if(isAppend){
		$('#receiveAddressList').prepend(rendered);
	}else{
		$('#receiveAddressList').html(rendered);
	}
}

function payMoney(orderGoodsId){
	/*if(SAVING_FLAG)return;
	SAVING_FLAG = true;*/
	//frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	var $list = $(".address_check:checked");
	if($list.length<=0){
		lh.alert('请选择一个收货地址');
		return;
	}else{
		var addressId = $list[0].nextElementSibling.value;
		$("#addressId").val(addressId);
	}
	$("#offerWin").show();
	$("#orderGoodsId").val(orderGoodsId);
	
	/*$.post('/payMoney',{id:id},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');*/
}

function checkedAddress(id){
	$(".address_check").prop('checked',false);
	$("#check_"+id).prop('checked',true);
}

function doAutoOffer(flag){
	if(flag == 'cancel'){
		$("#offerWin").hide();
	}else{
		if(SAVING_FLAG)return;
		var orderGoodsId = $("#orderGoodsId").val();
		var addressId = $("#addressId").val();
		var payPass = $("#payPass").val();
		if(!payPass){lh.alert('请输入支付密码');return;}
		frontBaseLoadingOpen('正在保存数据...');//加载遮罩
		SAVING_FLAG = true;
		$.post('/payMoneyForOrderGoods',{orderGoodsId:orderGoodsId,payPass:payPass,addressId:addressId},function(rsp){
			SAVING_FLAG = false;
			frontLoginCheck(rsp);//登陆检查
			frontBaseLoadingClose();//解除遮罩
			if(rsp.status == 'success'){
				lh.alert('您已经成功付款，卖家即将开始发货，请注意查收', "lh.jumpR('/user')");
			}else{
				if(rsp.code == 'alreay_paid'){
					lh.alert(rsp.msg, "lh.jumpR('/user')");
				}else{
					lh.alert(rsp.msg, 'reloadPage');
				}
			}
		},'json');
	}
}

function payMoneyDirectly(){
	var orderGoodsId = $("#orderGoodsId").val();
	if(!orderGoodsId)return;
	var addressId = $("#addressId").val();
	updateOrderAddressAndJumpToPay(addressId, orderGoodsId);
	//redirectToPay({payType:2,orderGoodsId:orderGoodsId});
}

function updateOrderAddressAndJumpToPay(addressId,orderGoodsId){
	if(SAVING_FLAG)return;
	frontBaseLoadingOpen('正在保存收货地址...');//加载遮罩
	SAVING_FLAG = true;
	$.post('/updateOrderAddress', {addressId:addressId, orderGoodsId:orderGoodsId},function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		if(rsp.status == 'success'){
			redirectToPay({payType:2,orderGoodsId:orderGoodsId});
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
}




