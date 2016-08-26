$(function() {
	var shopId=$("#shopId").val();
	if(shopId){
		getMainDataList();
	}else{
		getMainDataList1();
	}
});
var data;
function getMainDataList(){
	var userId=$("#userId").val();
	var shopId=$("#shopId").val();
	var typeCode=$("#typeCode").val();
	lh.post('front', '/getCouponLists'+"/"+shopId+"/"+typeCode, function(rsp) {
		if(rsp.success){
			data = rsp.rows;
			if(data && data.length>0){
//				alert(data[0].attrStr);
				makeMainListDom(data, true);
//				lh.page.currentPage ++;
			}else{
				$('#resultTip').text('没有更多数据').show();
				$(".weui-infinite-scroll").text('没有更多数据');
			}
		}else{
			lh.page.currentPage = 1;
			$('#resultTip').text(rsp.msg).show();
			lh.alert(rsp.msg);
		}
	}, 'json');
}
function getMainDataList1(){
	var userId=$("#userId").val();
	var shopId=$("#shopId").val();
	var typeCode=$("#typeCode").val();
	lh.post('front', '/getCoupon', function(rsp) {
		if(rsp.success){
			data = rsp.rows;
			if(data && data.length>0){
//				alert(data[0].attrStr);
				makeMainListDom2(data, true);
//				lh.page.currentPage ++;
			}else{
				$('#resultTip').text('没有更多数据').show();
				$(".weui-infinite-scroll").text('没有更多数据');
			}
		}else{
			lh.page.currentPage = 1;
			$('#resultTip').text(rsp.msg).show();
			lh.alert(rsp.msg);
		}
	}, 'json');
}
function makeMainListDom(mainList, isAppend) {
	var template = $('#template').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		startTime:function(){
			var createdAt = this.validStartTime;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'date'});
			return createdAt;
		},
		endTime:function(){
			var createdAt = this.validEndTime;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'date'});
			return createdAt;
		}			
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
function makeMainListDom2(mainList, isAppend) {
	var template = $('#template2').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		startTime:function(){
			var createdAt = this.validStartTime;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'date'});
			return createdAt;
		},
		endTime:function(){
			var createdAt = this.validEndTime;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'date'});
			return createdAt;
		}			
	});
	if (isAppend) {
		$('#data-container2').append(rendered);
	} else {
		$('#data-container2').html(rendered);
	}
}
function choice(id){
	var coupon=_.find(data, { 'id': id});
	var name=coupon.name;
	var money=coupon.money;
	var discount=coupon.discount;
	localStorage.setItem("bespeak_couponName",name);
	localStorage.setItem("bespeak_couponId",id);
	localStorage.setItem("bespeak_couponMoney",money);
	localStorage.setItem("bespeak_couponDiscount",discount);
	lh.back();
}