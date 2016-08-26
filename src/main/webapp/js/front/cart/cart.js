/**初始化数据*/
SAVING_FLAG = false;
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;
$(function(){
	loadOrderGoodsList();
	lh.scrollBottom(loadOrderGoodsList);
	
});


function loadOrderGoodsList(){
	var param = {};
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	param.currentUser = 1;
	//param.mainStatus = 2;
	//param.status = 77;
	//param.statusIn = 1;
	param.orderBy = "oi.shipping_status";
	param.ascOrdesc = "ASC"
	$.post('/getOrderGoodsList',param,function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.success){
				var count = rsp.total;
				if(count && count > 0){
					makeOrderGoodsListDom(rsp.rows,1);
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

function makeOrderGoodsListDom(orderGoodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var r = $("#r").val()
	for(var a in orderGoodsList){
		if(orderGoodsList[a].shippingStatus == 1){
			orderGoodsList[a].status = "<span>状态:<span style='color:red;'>未发货</span></span>";
		}else if(orderGoodsList[a].shippingStatus == 2){
			orderGoodsList[a].status = "<span>状态:<span style='color:red;'>已发货</span></span>";
		}else if(orderGoodsList[a].shippingStatus == 3){
			orderGoodsList[a].status = "<span>状态:<span>已收货</span></span>";
		}else if(orderGoodsList[a].shippingStatus == 4){
			orderGoodsList[a].status = "<span>状态:<span style='color:red;'>退货</span></span>";
		}
		if(orderGoodsList[a].goodsType == 1){
			if(r){
				orderGoodsList[a].shopUrl = "/goods/"+orderGoodsList[a].goodsId+"?shopId="+orderGoodsList[a].shopId+"&r="+r+"&selled="+1;
			}else{
				orderGoodsList[a].shopUrl = "/goods/"+orderGoodsList[a].goodsId+"?shopId="+orderGoodsList[a].shopId+"&selled="+1
			}
		}else if(orderGoodsList[a].goodsType == 2){
			if(r){
				orderGoodsList[a].shopUrl = "/wsg/"+orderGoodsList[a].goodsId+
				"/"+orderGoodsList[a].shopId+"/"+orderGoodsList[a].wholesaleUserId+"?r="+r+"&selled="+1;
			}else{
				orderGoodsList[a].shopUrl = "/wsg/"+orderGoodsList[a].goodsId+
				"/"+orderGoodsList[a].shopId+"/"+orderGoodsList[a].wholesaleUserId+"?selled="+1;
			}
		}
	}
	var rendered = Mustache.render(template, {rows:orderGoodsList});
	if(isAppend){
		$('#orderGoodsList').append(rendered);
	}else{
		$('#orderGoodsList').html(rendered);
	}
}



