CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
$(function(){
	getAllBuyerBidList();
	lh.scrollBottom(getAllBuyerBidList);
});

function getAllBuyerBidList(){
	var userId = $("#userId").val();
	var param = {};
	param.userId = userId;
	//param.offerStatus = 5;
	param.payStatus = 3;
	param.shippingStatus = 3;
	param.orderGoodsStatus = 2;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	param.goodsOfferPrice = 1;
	$.post('/getOrderInfoList',param,function(rsp){
		if(rsp){
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
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}

function makeAllBuyerBidListDom(goodsOffersList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var r = $("#r").val()
	for(var a in goodsOffersList){
		if(goodsOffersList[a].goodsType == 1){
			if(r){
				goodsOffersList[a].shopUrl = "/goods/"+goodsOffersList[a].goodsId+"?shopId="+goodsOffersList[a].shopId+"&r="+r+"&selled="+1;
			}else{
				goodsOffersList[a].shopUrl = "/goods/"+goodsOffersList[a].goodsId+"?shopId="+goodsOffersList[a].shopId+"&selled="+1
			}
		}else if(goodsOffersList[a].goodsType == 2){
			if(r){
				goodsOffersList[a].shopUrl = "/wsg/"+goodsOffersList[a].goodsId+
				"/"+goodsOffersList[a].shopId+"/"+goodsOffersList[a].wholesaleUserId+"?r="+r+"&selled="+1;
			}else{
				goodsOffersList[a].shopUrl = "/wsg/"+goodsOffersList[a].goodsId+
				"/"+goodsOffersList[a].shopId+"/"+goodsOffersList[a].wholesaleUserId+"?selled="+1;
			}
		}
	}
	var rendered = Mustache.render(template, {rows:goodsOffersList});
	if(isAppend){
		$('#commentingList').prepend(rendered);
	}else{
		$('#commentingList').html(rendered);
	}
}

function showCorfirmWin(orderId){
	$("#dialog").show();
	$("#orderId").val(orderId);
}

function doDelete(operate){
	if(operate == 'cancel'){
		$("#dialog").hide();
		$("#orderId").val('');
	}else if(operate == 'save'){
		var orderId = $("#orderId").val();
		$.post('/deleteOrderInfo',{orderId:orderId,overCommented:1},function(rsp){
			frontLoginCheck(rsp);//登陆检查 
			if(rsp.status == 'success'){
				location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		},'json');
	}
}
