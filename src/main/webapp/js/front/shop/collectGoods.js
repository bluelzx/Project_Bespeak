/**初始化数据*/
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
$(function(){
	loadCollectGoods();
	lh.scrollBottom(loadCollectGoods);
});

/**加载藏品*/
function loadCollectGoods(){
	var userId = $("#userId").val();
	var param = {};
	param.userId = userId;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	//param.forAuction = 1;
	$.post('/getCollectGoodsList',param,function(rsp){
		if(rsp){
			if(rsp.success){
				 var count =  rsp.total;
				if( count && count  > 0){
					makeCollectGoodsListDom(rsp.rows,1);
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

function makeCollectGoodsListDom(collectGoodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:collectGoodsList});
	if(isAppend){
		$('#collectGoods').append(rendered);
	}else{
		$('#collectGoods').html(rendered);
	}
}