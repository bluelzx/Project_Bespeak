CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
$(function(){
	getAllBuyerBidList();
	lh.scrollBottom(getAllBuyerBidList);
});

function getAllBuyerBidList(){
	var userId = $("#userId").val();
	var param = {};
	param.userId = userId;
	param.offerStatus = 1;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	param.orderBy = 'offer_status';
	param.ascOrdesc = 'asc';
	$.post('/getGoodsOffersList',param,function(rsp){
		if(rsp.status == 'success'){
			var count =  rsp.total;
			if(count  && count > 0){
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

function makeAllBuyerBidListDom(goodsOffersList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	for(var a in goodsOffersList){
		if(goodsOffersList[a].offerStatus == 4 ){
			goodsOffersList[a].offerStatus = "";
		}else{
			goodsOffersList[a].offerStatus = goodsOffersList[a].offerStatus;
		}
		if(goodsOffersList[a].bonusTypeId == 1){
			goodsOffersList[a].promoteMoney = goodsOffersList[a].bonus;
		}
		if(goodsOffersList[a].bonusTypeId == 2){
			goodsOffersList[a].promoteMoney = goodsOffersList[a].bonus*goodsOffersList[a].goodsPrice;
		}
	}
	var rendered = Mustache.render(template, {rows:goodsOffersList});
	if(isAppend){
		$('#buyerBidList').prepend(rendered);
	}else{
		$('#buyerBidList').html(rendered);
	}
}

function doAutoOffer(operate){
	if(operate == "cancel"){
		$("#offerWin").hide();
	}
	if(operate == "save"){
		var ids = $("#ids").val();
		$.post('/deleteGoodsOffers',{id:ids},function(rsp){
			if(rsp){
				frontLoginCheck(rsp);
				if(rsp.status == 'success'){
					window.location.reload();
				}else{
					lh.alert(rsp.msg);
				}
			}
		},'json');
	}
}	

/**删除**/
function deleteGoodsOffers(ids){
	$("#offerWin").show();
	$("#ids").val(ids);
	$("#tips").text('该数据删除后,无法恢复.');
}

/**不同意**/
function disagreeGoodsOffers(id){
	if(SAVING_FLAG)return;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	var obj = {id:id};
	SAVING_FLAG = true;
	$.post('/disgreeGoodsOffer',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			window.location.reload();
			lh.alert('您已经拒绝了客户的报价','reloadPage');
		}else{
			if(rsp.noPayPassword == 'noPayPassword'){
				lh.alert(rsp.msg,"jumpToPayPasswordFind()");
			}else if(rsp.noPhone == 'noPhone'){
				lh.alert(rsp.msg,"jumpToBindUserPhone()");
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

/**同意**/
function agreeGoodsOffers(ids,dealPrice,goodsId,goodsName,username,goodsOrderUserId){
	$("#id").val(ids);
	$("#goodsId").val(goodsId);
	$("#dealPrice").val(dealPrice);
	$("#goodsOrderUserId").val(goodsOrderUserId);
	$("#goodsName").text(goodsName);
	$("#username").text(username);
	$("#dealPriceWin").show();
}

function dealPriceOffer(operate){
	if(operate == "cancel"){
		$("#dealPriceWin").hide();
	}
	if(operate == "save"){
		if(SAVING_FLAG)return;
		var id = $("#id").val();
		var dealPrice = $("#dealPrice").val();
		dealPrice = parseInt(dealPrice);
		if(!dealPrice || dealPrice <= 0){
			lh.alert('请填写成交价格');
			return;
		}
		$("#dealPrice").val(dealPrice);
		var obj = {id:id,dealPrice:dealPrice};
		SAVING_FLAG = true;
		frontBaseLoadingOpen('正在保存数据...');//加载遮罩
		$.post('/agreeGoodsOffer',obj,function(rsp){
			SAVING_FLAG = false;
			frontLoginCheck(rsp);//登陆检查 
			frontBaseLoadingClose();//解除遮罩
			if(rsp.status == 'success'){
				lh.alert('您已经同意报价，系统已经自动生成订单','reloadPage');
			}else{
				if(rsp.noPayPassword == 'noPayPassword'){
					lh.alert(rsp.msg,"jumpToPayPasswordFind()");
				}else if(rsp.noPhone == 'noPhone'){
					lh.alert(rsp.msg,"jumpToBindUserPhone()");
				}else{
					lh.alert(rsp.msg);
				}
			}
		},'json');
	}
}

