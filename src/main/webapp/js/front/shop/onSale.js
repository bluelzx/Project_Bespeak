/**初始化数据*/
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
$(function(){
	loadShopGoods();
	lh.scrollBottom(loadShopGoods);
});

/**加载藏品*/
function loadShopGoods(){
	var userId = $("#userId").val();
	var param = {};
	param.userId = userId;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	param.goodsQuery = 1;
	param.goodsLinkQuery = 1;
	$.post('/getGoods',param,function(rsp){
		if(rsp.success){
			var count =  rsp.rows.length;
			if(count && count > 0){
				makeShopGoodsListDom(rsp.rows,1);
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

function makeShopGoodsListDom(shopGoodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:shopGoodsList});
	if(isAppend){
		$('#onSale').append(rendered);
	}else{
		$('#onSale').html(rendered);
	}
}
