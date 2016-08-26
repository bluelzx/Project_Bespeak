$(function() {
	initPage();
});
function initPage() {
	var userId=$("#userId").val();
	if(userId){
		lh.post('front', '/getCoupon', function(rsp) {
			if(rsp.success){
				$("#coupon").html(rsp.total);
			}else{
				lh.alert(rsp.msg,"提示");
			}
		}, 'json');	
	}
	var user=lh.param.user;
	 if(user){
		 $("#provider").hide();
		 $("#grDj").hide();
		 $("#addProvider").show();
	 }
	 var provider=lh.param.provider
	 if(provider){
		 $("#provider").show();
		 $("#grDj").hide();
		 $("#addProvider").hide();
		 $("#grJf").hide();
		 $("#grAdd").hide();
		 $("#grQ").hide();
		 $(".user-img").attr("src",provider.avatar);
		 $(".user-name").html(provider.realName);
		 $("#phone").html(provider.phone);
		 $("#money").html("￥"+provider.avaliableMoney);
		 $("#avaliableMoney").val(provider.avaliableMoney);
	 }
	 var fund=$("#fund").html();
	 var coupon=$("#coupon").html();
	 var money=$("#avaliableMoney").val();
	 var phone=$("#phone").html();
	 var reg = /1(\d{2})\d{4}(\d{4})/g;
	 var getTel= phone.replace(reg,"1$1****$2");
	 $("#phone").html(getTel)
	 if(fund<1){
		 $("#fund").html(0)
	 }
	 if(coupon<1){
		 $("#coupon").html(0)
	 }
	 if(money<1){
		 $("#money").html('￥0.00');
	 }
}

