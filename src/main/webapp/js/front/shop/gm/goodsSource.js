/**初始化数据*/
SAVING_FLAG = false;
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;
$(function(){
	loadGoodsList();
	lh.scrollBottom(loadGoodsList);
});


function loadGoodsList(){
	var shopId = $("#shopId").val();
	var userId = $("#userId").val();
	var moduleId = $("#moduleId").val();
	//var sequence = $("#sequence").val();
	var param = {};
	param.shopId = shopId;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	//param.goodsPrivilege = 1;
	if(moduleId == 5){
		//param.shopId = shopId;
		//param.currentUserId = userId;
		//param.wholesaleId = 1;
		//param.noCopy = 1;
		//param.noCopyShopId = shopId;
		param.allWholesaleGoods = 1;
		param.moduleId = moduleId;
	}
	/*param.status = 74;
	param.statusIn = 1;*/
	//param.sequence = sequence;
	$.post('/getGoodsList',param,function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.success){
				var count = rsp.total;
				if(count && count > 0){
					makeGoodsListDom(rsp.rows,1);
					CURRENT_PAGE++ ;
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

function makeGoodsListDom(goodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:goodsList});
	if(isAppend){
		$('#goodsList').append(rendered);
	}else{
		$('#goodsList').html(rendered);
	}
}

function saledGoods(id){
	$.post('/addOrUpdateGoods',{id:id,mainStatus:1},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}
