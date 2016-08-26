var pageNum=10;
$(function() {
	init();
	getMainDataList();
})

function init(){
    var createdAt=$("#orderInfoCreatedAt").attr("value");
	createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
	$("#orderInfoxxCreatedAt").html(createdAt);
	// 根据状态显示提示进行操作按钮
	var orderStatusCode=$("#orderStatusCode").attr("value");
	var payStatusCode=$("#payStatusCode").attr("value");
	var orderInfoid= $("#orderInfoid").attr("value")
if(orderStatusCode!=""||orderStatusCode!=null){
	if(orderStatusCode=="order_status_done"||orderStatusCode=="order_status_invalid"){
		$("#tishibutton").html("<input type='button' value='再次下单' class='orderBtXd' onclick='zaichixiadan()'> ");
	    //<input type='button' value='评价' class='orderBtpJ' onclick='pingjia()'>
	}
	if(payStatusCode=="pay_status_done"){//判断支付状态
	 if(orderStatusCode=="order_status_todo"){//商家未接单
		$("#tishibutton").html("<input type='button' value='提交申请' class='orderBtXd' onclick='tuikuanshenqin("+orderInfoid+")'> ");
	 }else if(orderStatusCode == 'shipping_status_todo'){//服务未开始显示退款
		$("#tishibutton").html("<input type='button' value='提交申请' class='orderBtXd' onclick='tuikuanshenqin("+orderInfoid+")'> ");
	 }else if(orderStatusCode=="shipping_status_receive"){//接受服务中显示退款
		$("#tishibutton").html("<input type='button' value='提交申请' class='orderBtXd' onclick='tuikuanshenqin("+orderInfoid+")'> ");
	 }else if(orderStatusCode=="shipping_status_done"){//服务完成显示退款
		$("#tishibutton").html("<input type='button' value='提交申请' class='orderBtXd' onclick='tuikuanshenqin("+orderInfoid+")'> ");
	 }else if(orderStatusCode=="order_status_todo1"){//派单中显示退款
		$("#tishibutton").html("<input type='button' value='提交申请' class='orderBtXd' onclick='tuikuanshenqin("+orderInfoid+")'> ");
	 }else if(orderStatusCode=="order_status_cancel"){//取消订单中显示退款
		$("#tishibutton").html("<input type='button' value='提交申请' class='orderBtXd' onclick='tuikuanshenqin("+orderInfoid+")'> ");
	 }else if(orderStatusCode=="order_status_noreturn"){//驳回订单中显示退款
		$("#tishibutton").html("<input type='button' value='提交申请' class='orderBtXd' onclick='tuikuanshenqin("+orderInfoid+")'> ");
	 }
	}else if(payStatusCode!=""||payStatusCode!=null){
		if(orderStatusCode=="order_status_todo"){//商家未接单
			$("#tishibutton").html("<input type='button' value='支付费用' class='orderBtpJ' onclick='giveMoney("+orderInfoid+")'> ");
	}else if(orderStatusCode == 'shipping_status_todo'){//服务未开始显示支付
			$("#tishibutton").html("<input type='button' value='支付费用' class='orderBtpJ' onclick='giveMoney("+orderInfoid+")'> ");
	}else if(orderStatusCode=="shipping_status_receive"){//接受服务中显示支付
			$("#tishibutton").html("<input type='button' value='支付费用' class='orderBtpJ' onclick='giveMoney("+orderInfoid+")'> ");
	}else if(orderStatusCode=="shipping_status_done"){//服务完成显示支付
			$("#tishibutton").html("<input type='button' value='支付费用' class='orderBtpJ' onclick='giveMoney("+orderInfoid+")'> ");
	}else if(orderStatusCode=="order_status_todo1"){//派单中显示支付
			$("#tishibutton").html("<input type='button' value='支付费用' class='orderBtpJ' onclick='giveMoney("+orderInfoid+")'> ");
	}
 }
}else{
		$("#tishibutton").html("");
}
	
    //回到顶部
    $(window).scroll(function () {
        var current = $(window).scrollTop();
        if (current > 300){
			$("#go-top").show();
		} else {
            $("#go-top").hide();
        }
    })
    $("#go-top").click(function () {
        $(window).scrollTop(0);
    });
//	var isRealAuth=$("#isRealAuth").attr("value");
//	if(isRealAuth==2){
//	$(".provider-zz").html("已认证");
//	}else{	
//	}
//	var titleCode=$("#titleCode").attr("value");
}
//function pingjia(Id){ 
//	var url = '/addComment/'+Id;
//	lh.jump(url);
//}
function zaichixiadan(){
	var url = '/providerIndex';
	lh.jump(url);
}
function tuikuanshenqin(orderInfoid){//提交退款申请
	var url = '/ReturnMoneyUIApply';
	var tuikuanshuoming=$("#tuikuanshuoming").val();
	var tuikuanjietu=$("#tuikuanjietu img")[0].src;
//	alert(tuikuanjietu);
	var param = {page:lh.page.currentPage, rows:pageNum,id:orderInfoid,attrStr:tuikuanshuoming,attrStr2:tuikuanjietu};
	lh.post('front', url, param, function(rsp) {
		if (rsp.success) {
			lh.alert('您已提交退款申请',jumpToOrderIndex);
		} else {
			lh.alert('提交退款申请失败！');  
			lh.alert(rsp.msg);
		}
	}, 'json');  
}
function jumpToOrderIndex(){
	lh.jumpR("/orderIndex");
}
function getMainDataList(){
	var peopleid=$("#orderInfoid").attr("value");
	//,typeCode:typeCode
	var param = {page:lh.page.currentPage, rows:pageNum,id:orderInfoid};
	lh.post('front', '/getOrderInfoList', param, function(rsp) {//加载月嫂信息
		if(rsp.success){
			var data = rsp.rows;
//			alert(data);
			if(data && data.length>0){
				makeMainListDom(data, false);
//				lh.page.currentPage ++;
			}else{
				$('#comment').html('暂时没有评论').show();
				$(".weui-infinite-scroll").text('没有更多数据');
			}
		}else{
			lh.page.currentPage = 1;
			$('#resultTip').text(rsp.msg).show();
//			lh.alert(rsp.msg);
		}
	}, 'json');	
}
function makeMainListDom(mainList, isAppend) {
	var template = $('#template').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		getDate:function(){
			var createdAt = this.createdAt;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},	
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}