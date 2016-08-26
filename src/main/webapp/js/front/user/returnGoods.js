CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
$(function(){
	getReturnGoodsList();
	lh.scrollBottom(getReturnGoodsList);
});

function getReturnGoodsList(){
	var param = {pageFrom:'returnGoods',page:CURRENT_PAGE,rows:PAGE_COUNT};
	$.post('/getOrderGoodsList',param,function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			var count =  rsp.total;
			if(count && count > 0){
				makeReturnGoodsListDom(rsp.rows,1);
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

function makeReturnGoodsListDom(orderGoodsList,isAppend){
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
		$('#returnGoods').prepend(rendered);
	}else{
		$('#returnGoods').html(rendered);
	}
}

function agree(orderGoodsId, opt){
	if(SAVING_FLAG)return;
	if(!opt || opt != 'force'){
		frontBaseConfirm('是否确认同意退货？','returnGoods('+orderGoodsId+', 1, \'force\')');return;
	}
}

function disagree(orderGoodsId, opt){
	if(SAVING_FLAG)return;
	if(!opt || opt != 'force'){
		frontBaseConfirm('是否确认拒绝退货？','returnGoods('+orderGoodsId+', 2, \'force\')');return;
	}
}

function cancel(orderGoodsId, opt){
	if(SAVING_FLAG)return;
	if(!opt || opt != 'force'){
		frontBaseConfirm('是否确认取消退货？','returnGoods('+orderGoodsId+', 3, \'force\')');return;
	}
}

function returnGoods(orderGoodsId, agreeOrDisagree, opt){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	$.post('/returnOrderGoods',{orderGoodsId:orderGoodsId, opt:agreeOrDisagree},function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			if(agreeOrDisagree == 1){
				lh.alert('您已经同意退货','reloadPage');
			}else{
				lh.alert('您已经拒绝退货','reloadPage');
			}
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

