CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
$(function(){
	getOrderGoodsList();
	lh.scrollBottom(getOrderGoodsList);
});

function getOrderGoodsList(){
	var param = {pageFrom:'shipped',page:CURRENT_PAGE,rows:PAGE_COUNT};
	$.post('/getOrderGoodsList', param, function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			var count = rsp.total;
			if(count && count  > 0){
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
		var status = orderGoods.expressState;
		if(!status)status = 0;
		var statusName = '在途中';
		if(status == 1){
			statusName = '已揽收';
		}else if(status == 2){
			statusName = '疑难';
		}else if(status == 3){
			statusName = '已签收';
			orderGoods.canReturn = 1;
		}else if(status == 4){
			statusName = '退签';
			orderGoods.canReturn = 1;
		}else if(status == 5){
			statusName = '同城派送中';
		}else if(status == 6){
			statusName = '退回';
		}else if(status == 7){
			statusName = '转单';
		}
		orderGoods.statusName = statusName;
		if(orderGoods.expressJson){
			orderGoods.expressFlow = JSON.parse(orderGoods.expressJson);
		};
		//，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单
	}
	
	
	/**
	 * {"com":"ems","condition":"F00",
	 * "data":[
	 * {"context":"【重庆市】 投递并签收，签收人：本人收","ftime":"2015-11-18 10:04:52","time":"2015-11-18 10:04:52"},
	 * {"context":"【重庆市】 重庆市邮政速递物流公司黔江分公司直营揽投部安排投递，预计23:59:00前投递（投递员姓名：向昌文;联系电话：）","ftime":"2015-11-18 08:40:22","time":"2015-11-18 08:40:22"},
	 * {"context":"【重庆市】 离开重庆市 发往重庆市邮政速递物流公司武隆分公司直营揽投部","ftime":"2015-11-17 03:54:32","time":"2015-11-17 03:54:32"},
	 * {"context":"【重庆市】 到达  重庆航站 处理中心","ftime":"2015-11-16 15:46:00","time":"2015-11-16 15:46:00"},
	 * {"context":"【武汉市】 离开武汉市 发往重庆市（经转）","ftime":"2015-11-15 14:25:00","time":"2015-11-15 14:25:00"},
	 * {"context":"【武汉市】 到达武汉市航空邮件转运站处理中心（经转）","ftime":"2015-11-14 23:27:00","time":"2015-11-14 23:27:00"},
	 * {"context":"【黄石市】 已离开收寄点，发往武汉市航空邮件转运站","ftime":"2015-11-14 20:05:30","time":"2015-11-14 20:05:30"},
	 * {"context":"【黄石市】 黄石万达揽投部已收件（揽投员姓名：王劲松,联系电话:07146359699）","ftime":"2015-11-14 17:00:00","time":"2015-11-14 17:00:00"}],
	 * "ischeck":"1","message":"ok","nu":"5116741128399","state":"3","status":"200"}
	 */
	
	
	
	
	var rendered = Mustache.render(template, {rows:orderGoodsList});
	if(isAppend){
		$('#shippedList').prepend(rendered);
	}else{
		$('#shippedList').html(rendered);
	}
}

function receivedGoods(orderId, expressState, opt){
	if(SAVING_FLAG)return;
	if(expressState && expressState!= 3 && opt != 'force'){
		frontBaseConfirm('该快递您还没有签收，是否确认收货？','receivedGoods('+orderId+', '+expressState+',\'force\')');return;
	}
	SAVING_FLAG = true;
	var obj = {orderId:orderId};
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/receiveGoods', obj, function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);
		if(rsp.status == 'success'){
			lh.alert('您已经确认收货','reloadPage');
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function returnGoods(orderGoodsId, opt){
	if(SAVING_FLAG)return;
	if(!opt || opt != 'force'){
		frontBaseConfirm('是否确认发起退货申请？','returnGoods('+orderGoodsId+',\'force\')');return;
	}
	var obj = {orderGoodsId:orderGoodsId};
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	SAVING_FLAG = true;
	$.post('/applyReturnOrderGoods',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);
		if(rsp.status == 'success'){
			lh.alert('您已经发起退货申请，商家同意后即可退款','reloadPage');
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function toggleExpressFlow(id){
	$('#'+id).toggle();
}
	
