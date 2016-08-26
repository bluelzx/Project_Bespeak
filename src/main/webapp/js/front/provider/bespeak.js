var addressId = localStorage.getItem("bespeak_addressId");
var address = localStorage.getItem("bespeak_address");
var province = localStorage.getItem("bespeak_province");
var city = localStorage.getItem("bespeak_city");
var couponName = localStorage.getItem("bespeak_couponName");
var couponMoney = localStorage.getItem("bespeak_couponMoney");
var couponDiscount = localStorage.getItem("bespeak_couponDiscount");
var couponId = localStorage.getItem("bespeak_couponId");
var sm = localStorage.getItem("bespeak_sm");
var jd = localStorage.getItem("bespeak_jd");
$(function() {
		
	if (address) {
		$("#addressId").attr("value", addressId);
		$("#province").attr("value", province);
		$("#city").attr("value", city);
		$("#address").attr("value", address);
		$("#address1").html(address);
		$("#couponMoney").attr("value", couponMoney);
		$("#couponDiscount").attr("value", couponDiscount);
		$("#couponId").attr("value", couponId);
		$("#couponName").html(couponName);
		if (sm) {
			$('.xd-fs').html(sm);
		} else {
			$('.xd-fs').html(jd);
		}
	}

	init();
	datetime();
	getMainDataList();
	if (!address) {
		setDefaultAddress();
	}
		
});

function setDefaultAddress() {
	lh.post('front', '/getDefaultAddress', function(rsp) {
		if (rsp.success) {
			var data = rsp.rows;
			$("#address1").html(
					data.provinceName + data.cityName + data.addressDetail);
			$("#providerId").val(data.province);
			$("#city").val(data.city);
			$("#address").val(data.addressDetail);
			var dz = $("#address").val();
			if (dz != "" && couponName) {
				localStorage.clear();
			}
		} else {
			lh.alert(rsp.msg, "提示");
		}
	}, 'json');
}
function init() {
	var stars = lh.param.stars;
	var choice = $('.choice-pj1 img');
	choice.each(function(i) {
		choice.slice(0, stars).attr('src', '/images/front/pjx-on.png');
	});
	var shopPrice = $("#shopPrice").val();
	var timeNum = $("#timeNum").val();
	$("#jiaqian").html(shopPrice + "元/" + timeNum + "小时");
	var mySwiper = new Swiper('.swiper-container', {
		slidesPerView : 5.4,
		freeMode : true,
		freeModeMomentum : false,
	})
	var t = $("#text_box");
	var shopPrice = $("#shopPrice").val();
	var money = $("#couponMoney").val();
	var discount = $("#couponDiscount").val();
	$(".plus").click(function() {
		t.val(parseInt(t.val()) + 1)
		if (money > 0) {
			var price = t.val() * shopPrice - money;
		} else if (discount > 0) {
			var price = t.val() * shopPrice * discount / 100;
		} else {
			var price = t.val() * shopPrice;
		}

		$("#totalPrice").html(price + "元");
		$("#price").val(price);
	})
	$(".reduce").click(function() {
		t.val(parseInt(t.val()) - 1)
		if (t.val() <= 1) {
			t.val(1);
		}
		if (money > 0) {
			var price = t.val() * shopPrice - money;
		} else if (discount > 0) {
			var price = t.val() * shopPrice * discount / 100;
		} else {
			var price = t.val() * shopPrice;
		}
		$("#totalPrice").html(price + "元");
		$("#price").val(price);
	})

	$('.xd-fuwu').click(function() {
		$('.motai').show()
	})
	$('.motai').click(function() {
		$('.motai').hide()
	})
	$('#xd-sm').click(function() {
		var text = $(this).html();
		$('.xd-fs').html(text);
		localStorage.setItem("bespeak_sm", text);
		localStorage.removeItem("bespeak_jd");
		if (!address) {
			setDefaultAddress();
		}else{
			$("#addressId").attr("value", addressId);
			$("#province").attr("value", province);
			$("#city").attr("value", city);
			$("#address").attr("value", address);
			$("#address1").html(address);
		}
	})
	$('#xd-jd').click(function() {
		var text = $(this).html();
		$('.xd-fs').html(text);
		var shopId = $("#shopId").val();
		localStorage.setItem("bespeak_jd", text);
		localStorage.removeItem("bespeak_sm");
		lh.post('front', '/getShop/' + shopId, function(rsp) {
			if (rsp.success) {
				var data = rsp.rows;
				$("#address1").html(data.address);
				$("#providerId").val(data.province);
				$("#city").val(data.city);
				$("#address").val(data.addressDetail);
			} else {
				lh.alert(rsp.msg, "提示");
			}
		}, 'json');

	})
}
function datetime() {
	$("#time-format").datetimePicker(
			{
				title : '预约时间',
				hours : [ '08', '09', '10', '11', '12', '13', '14', '15', '16',
						'17', '18' ],
				minutes : [ '00', '15', '30', '45' ],
				// dateSplit: '-',
				onChange : function(picker, values, displayValues) {
				}
			});
}
function unAddress() {
	var fs = $(".xd-fs").html();
	if (fs == "进店体验") {
		return;
	} else {
		lh.jump('/address')
	}
}
function back(){
	localStorage.removeItem("bespeak_couponMoney");
	localStorage.removeItem("bespeak_addressId");
	localStorage.removeItem("bespeak_address");
	localStorage.removeItem("bespeak_province");
	localStorage.removeItem("bespeak_city");
	localStorage.removeItem("bespeak_couponName");
	localStorage.removeItem("bespeak_couponMoney");
	localStorage.removeItem("bespeak_couponDiscount");
	localStorage.removeItem("bespeak_couponId");
	localStorage.removeItem("bespeak_sm");
	localStorage.removeItem("bespeak_jd");
	lh.back();
}
function addBespeak() {
	var userId = $("#userId").val();
	var shopId = $("#shopId").val();
	var goodsId = $("#goodsId").val();
	var providerId = $("#providerId").val();
	var typeId = $(".xd-fs").html();
	if(typeId=="请选择服务方式"){
		lh.alert('请选择服务方式');
		return;
	}
	if (typeId == "上门服务") {
		typeId = 2;
	} else {
		typeId = 1;
	}
	var address = $("#address").val();
	var bespeakStartTime = $("#time-format").val();
	if (bespeakStartTime == "" || bespeakStartTime == null) {
		lh.alert('选择预约时间');
		return;
	}
	var issd = false, sd = issd ? bespeakStartTime : new Date(), ed = issd ? new Date(
			$("#time-format").val())
			: new Date(bespeakStartTime);
	if (ed < sd) {
		lh.alert("只能提前一天预约", '提示');
		return;
	}
	bespeakStartTime += ":00"
	var goodsNumber = $("#text_box").val();
	var price = $("#price").val();
	var city = $("#city").val();
	var province = $("#province").val();
	var content = $("#content").val();
	var timeNum = $("#timeNum").val();
	var couponId = $("#couponId").val();
	var couponDiscount = $("#couponDiscount").val();
	var couponMoney = $("#couponMoney").val();
	var address = $("#address1").html();
	if (couponMoney > 0) {
		var discount = couponMoney;
	} else if (couponDiscount > 0) {
		var discount = couponDiscount;
	}
	var param = {
		goodsId : goodsId,
		providerId : providerId,
		typeId : typeId,
		address : address,
		bespeakStartTime : bespeakStartTime,
		content : content,
		city : city,
		province : province,
		goodsNumber : goodsNumber,
		timeNum : timeNum,
		couponId : couponId,
		couponDiscount : discount
	};
	lh.post('front', '/addOrUpdateBespeak', param, function(rsp) {
		if (rsp.success) {
			lh.alert("预约成功", "提示");
			lh.back();
		} else {
			lh.alert(rsp.msg, "提示");
		}
	}, 'json');
	back();
}
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
function choice(id){
	var coupon=_.find(data, { 'id': id});
	var name=coupon.name;
	var money=coupon.money;
	var discount=coupon.discount;
	localStorage.setItem("bespeak_couponName",name);
	localStorage.setItem("bespeak_couponId",id);
	localStorage.setItem("bespeak_couponMoney",money);
	localStorage.setItem("bespeak_couponDiscount",discount);
	var t = $("#text_box");
	$("#couponMoney").attr("value", money);
	$("#couponDiscount").attr("value", discount);
	$("#couponId").attr("value", id);
	$("#couponName").html(name);
	var shopPrice = $("#shopPrice").val();
	var money = $("#couponMoney").val();
	var discount = $("#couponDiscount").val();
	if (money > 0) {
		$("#totalPrice").html(t.val() * shopPrice - money + "元");
	} else if (discount > 0) {
		$("#totalPrice").html(t.val() * shopPrice * discount / 100 + "元");
	}
	$.closePopup();
}