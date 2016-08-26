CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
$(function(){
	getAllBuyerBidList();
	lh.scrollBottom(getAllBuyerBidList);
});

function getAllBuyerBidList(){
	var param = {pageFrom:'waitPayMoney',page:CURRENT_PAGE,rows:PAGE_COUNT};
	$.post('/getOrderGoodsList', param, function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			var count = rsp.total;
			if(count && count > 0){
				makeAllBuyerBidListDom(rsp.rows,1);
				CURRENT_PAGE++;
			}else{
				$('#resultTip').text('没有更多数据').show();
			}
		}else{
			lh.alert(rsp.msg);
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}

function makeAllBuyerBidListDom(orderGoodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var r = $("#r").val()
	for(var i in orderGoodsList){
		var orderGoods = orderGoodsList[i];
		if(!orderGoods)continue;
		if(orderGoods.goodsType == 1){
			orderGoods.shopUrl = "/goods/"+orderGoods.goodsId+"?shopId="+orderGoods.shopId+"&selled="+1
		}else if(orderGoods.goodsType == 2){
			orderGoods.shopUrl = "/wsg/"+orderGoods.goodsId+"/"+orderGoods.shopId+"/"+orderGoods.wholesaleUserId+"?selled="+1;
		}
		if(r){
			orderGoods.shopUrl += "&r="+r;
		}
	}
	var rendered = Mustache.render(template, {rows:orderGoodsList});
	if(isAppend){
		$('#buyerBidList').prepend(rendered);
	}else{
		$('#buyerBidList').html(rendered);
	}
}

/*function payMoney(id){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	//frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$("#offerWin").show();
	$("#goodsOfferId").val(id);
	$.post('/payMoney',{id:id},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function doAutoOffer(flag){
	if(flag == 'cancel'){
		$("#offerWin").hide();
	}else{
		var id = $("#goodsOfferId").val();
		var payPass = $("#payPass").val();
		$.post('/payMoney',{id:id,payPass:payPass},function(rsp){
			if(rsp){
				frontLoginCheck(rsp);//登陆检查
				if(rsp.status == 'success'){
					window.location.reload();
				}else{
					lh.alert(rsp.msg);
				}
			}
		},'json');
		//$("#offerWin").hide();
	}
}*/


function showCorfirmWin(orderId){
	$("#dialog").show();
	$("#orderId").val(orderId);
}

function doDelete(operate){
	if(operate == 'cancel'){
		$("#dialog").hide();
		$("#orderId").val('');
	}else if(operate == 'save'){
		if(SAVING_FLAG)return;
		var orderId = $("#orderId").val();
		SAVING_FLAG = true;
		frontBaseLoadingOpen('正在保存数据...');//加载遮罩
		$.post('/cancelOrder',{orderId:orderId},function(rsp){
			SAVING_FLAG = false;
			frontBaseLoadingClose();//解除遮罩
			frontLoginCheck(rsp);//登陆检查 
			if(rsp.status == 'success'){
				$("#dialog").hide();
				$("#orderId").val('');
				lh.alert('您已经成功取消该订单','reloadPage');
			}else{
				lh.alert(rsp.msg);
			}
		},'json');
	}
}
