CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
$(function(){
	getAllBuyerBidList();
	lh.scrollBottom(getAllBuyerBidList);
});

function getAllBuyerBidList(){
	var param = {pageFrom:'shipping',goodsOfferPrice:1,rows:PAGE_COUNT,page:CURRENT_PAGE};
	$.post('/getOrderGoodsList',param,function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			var count =  rsp.total;
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
		$('#shippingList').prepend(rendered);
	}else{
		$('#shippingList').html(rendered);
	}
}


