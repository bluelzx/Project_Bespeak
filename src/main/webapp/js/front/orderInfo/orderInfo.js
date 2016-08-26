var pageNum=20;
//if(lh.xiaoxi==null||lh.xiaoxi==""){}
lh.xiaoxi=0;
$(function() {
	getMainDataList();
	init();
})
function pingjia(Id){ 
	var url = '/addComment/'+Id;
	lh.jump(url);
}
function not(Id){//没有时默认
	
}
function tixingsj(Id){
	if(lh.xiaoxi<1){
//	lh.alert("提醒消息已发送");
	lh.xiaoxi++;
	var url = '/addOneShopNotice';
	lh.loading('正在发送消息...');//加载遮罩
//	SAVING_FLAG = true;
	lh.post('front', url, {orderId : Id}, function(rsp) {
//		SAVING_FLAG = false;
		if (rsp.success) {
			lh.alert('您已经成功发送提醒消息', lh.reload);//, lh.reload刷新页面
		} else {
			lh.alert(rsp.msg);
		}
	}, 'json');
	}else{
	lh.alert("警告：请勿频繁发送消息");
	}
}
function tixingjs(Id){
	if(lh.xiaoxi<1){
//	lh.alert("提醒消息已发送");
	lh.xiaoxi++;
	var url = '/addOneProviderNotice';
	lh.loading('正在发送消息...');//加载遮罩
//	SAVING_FLAG = true;
	lh.post('front', url, {orderId : Id}, function(rsp) {
//		SAVING_FLAG = false;
		if (rsp.success) {
			lh.alert('您已经成功发送提醒消息',lh.reload);
		} else {
			lh.alert(rsp.msg);
		}
	}, 'json');
	}else{
	lh.alert("警告：请勿频繁发送消息");
	}
}
function querentuikuan(Id){
	lh.confirm('是否确认退款已完成?<br/><span style="color:red">确认后您将不能再申请退款!</span>','提示',function(){
		var url = '/ReturnMoneyOk';
		lh.post('front', url, {orderId : Id}, function(rsp) {
			if (rsp.success) {
				lh.alert('您已确认退款',lh.reload);
			} else {
				lh.alert('确认退款失败！');  
				lh.alert(rsp.msg);
			}
		}, 'json');  
    },function(){//取消
		    
    });
}
function lianxikefu(Id){
	lh.alert("客服热线：400-400-8888");
}
function quxiaodindan(Id,payCode){
//	lh.alert("商家未接单时取消订单");
	var payStatusCode = "";
	if(payCode==1){
		payStatusCode="pay_status_done"
	}else{
		payStatusCode="pay_status_todo"
	}
	var newcaozuo=""
	var newcaozuo1=""
//	alert(payStatusCode);
	if(payStatusCode=="pay_status_done"){
		newcaozuo="已付款";
		newcaozuo1="您可以申请退款"
	}else if(payStatusCode=="pay_status_todo"){
		newcaozuo="未付款";
		newcaozuo1=	"订单将结束"
	}
	lh.confirm("是否直接取消订单?<br/>你当前支付状态为:<span style='color:red'>"+newcaozuo+"</span><br/>确认后"+newcaozuo1+"!","提示",function(){
		var url = '/cancelOrderApply';
		lh.post('front', url, {orderId : Id}, function(rsp) {
			if (rsp.success) {
				lh.alert('取消订单成功',lh.reload);
			} else {
				lh.alert('取消订单失败！');  
				lh.alert(rsp.msg);
			}
		}, 'json');
    },function(){//取消
		    
    });
}
//退款功能开始
function tuikuansq(Id){//退款驳回再次申请
//	lh.alert("申请退款点击");
	var url = '/orderInfoRefund/'+Id;
	lh.jump(url);
}
function tuikuanshengqing(Id){//服务未开始时点击申请退款
//	lh.alert("商家已派单-服务未开始点击申请退款");
	var url = '/orderInfoRefund/'+Id;
	lh.jump(url);
}
function tuikuanshengqing1(Id){//接受服务中点击申请退款
//	lh.alert("接受服务中点击申请退款");
	var url = '/orderInfoRefund/'+Id;
	lh.jump(url);
}
function tuikuanshengqing2(Id){//接受服务中点击申请退款
//	lh.alert("服务完成不满意点击申请退款");
	var url = '/orderInfoRefund/'+Id;
	lh.jump(url);
}
function tuikuanshengqing3(Id){//派单中点击申请退款
//	lh.alert("派单中如果技师不接单显示退款点击申请退款");
	var url = '/orderInfoRefund/'+Id;
	lh.jump(url);
}
//退款功能结束
function querenGoods(Id){//服务中点击完成服务
//	lh.alert("用户点击完成服务");
	lh.confirm('是否直接确认服务已完成?<br/><span style="color:red">确认后您将不能再改变服务状态!</span>','提示',function(){
		var url = '/GoodsStateOk';
		lh.post('front', url, {orderId : Id}, function(rsp) {
			if (rsp.success) {
				lh.alert('服务已确认完成',lh.reload);
			} else {
				lh.alert('服务确认完成失败！');  
				lh.alert(rsp.msg);
			}
		}, 'json');  
    },function(){//取消
		    
    });
}
function querenOrder(Id){//服务完成中点击申请退款
//	lh.alert("用户点击完成订单");
	lh.confirm('服务已全部完成,<br/>是否确认该订单已完成？','提示',function(){
		var url = '/OrderStateOk';
		lh.post('front', url, {orderId : Id}, function(rsp) {
			if (rsp.success) {
				lh.alert('已确认完成订单',lh.reload);
			} else {
				lh.alert('确认完成订单失败！');  
				lh.alert(rsp.msg);
			}
		}, 'json');  
    },function(){//取消
		    
    });
}
function zailaiyidan(Id){
	var url = '/providerIndex';
	lh.jump(url);
}
function init(){
	 var mySwiper = new Swiper('.swiper-container',{
         slidesPerView : 5.4,
         freeMode : true,
         freeModeMomentum : false,
     });
	   //切换订单
	    $('.order-ul li').each(function (i) {
	        $(this).click(function () {
	            $('.order-ul li').removeClass('choice-on').eq(i).addClass('choice-on');
	            var id = $('.order-ul li').eq(i).attr('id');
	            $('#data-container').empty();   //清空
	            getMainDataList(id);
	        })
	    });
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
}
function getMainDataList(amFlag){
	if(!amFlag){amFlag = lh.current.amFlag;}
//	alert(amFlag);
	var typeCode=amFlag;
	var url = '/getOrderInfoList';
	var param = {page:lh.page.currentPage, rows:pageNum, pageFrom:typeCode};
	lh.post('front', url, param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
//				alert(data[0].attrStr);
				makeMainListDom(data, false);
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
//	lh.post('front', url, function(rsp) {
//		if(rsp.success){
//			var data = rsp.rows;
//			if(data && data.length>0){
//				makeMainListDom(data, false);
////				lh.page.currentPage ++;
//			}else{
//				$('#resultTip').text('没有更多数据').show();
//				$(".weui-infinite-scroll").text('没有更多数据');
//			}
//		}else{
//			lh.page.currentPage = 1;
//			$('#resultTip').text(rsp.msg).show();
//			lh.alert(rsp.msg);
//		}
//	}, 'json');	
}
function orderInfojump(id){
	location.href = "/orderInfo/"+id;
}
function makeMainListDom(mainList, isAppend) {
	var template = $('#template').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		date:function(){
			var createdAt = this.createdAt;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		status:function(){//订单状态
			var avatar = '';
			var pic = this.avatar;
			pic += buildOSSZoom(35,35);
			avatar += '<img width="30" src="'+pic+'" class="img-responsive center-block">';
			return avatar;
		},//参数自定义
		xiadandate:function(){
			var createdAt = this.createdAt;
			if(createdAt==null && createdAt!==''){
			createdAt="时间异常"
			}else{
				var date = new Date(createdAt);
				var year = date.getFullYear();
				var month = date.getMonth()+1;
				var day = date.getDate();
				var hour = date.getHours();
				var minute = date.getMinutes();
				createdAt = (year+'-'+month+'-'+day+' '+hour+':'+minute);
			}
//			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		//2 css
		StateNG:function(){
			// 订单状态
			var orderStatusCode = this.orderStatusCode;
			// 付款状态
			var payStatusCode = this.payStatusCode;
			var newcaozuo="yichang"
			//已付款显示退款
			if(payStatusCode=="pay_status_done"){
			   if(orderStatusCode=="shipping_status_todo"||orderStatusCode=="shipping_status_receive"||orderStatusCode=="shipping_status_done"){
				//服务未开始、商家未接单，已经接单显示申请退款css
				newcaozuo="quxiao"
			   }
			}
			if(orderStatusCode=="order_status_todo"){
				newcaozuo="quxiao"
			}
//			if(orderStatusCode=="order_status_todo1"){
////				//派单中-已经接单显示申请退款内容
////				newcaozuo="quxiao"
////			}
			if(orderStatusCode=="order_status_noreturn"){
				newcaozuo="csstwo"
			}
			return newcaozuo;
		},//2内容
		operationNG:function(){
			// 订单状态
			var orderStatusCode = this.orderStatusCode;
			// 付款状态
			var payStatusCode = this.payStatusCode;
			var newcaozuo=""
			if(orderStatusCode=="order_status_todo"){
				//服务未开始显示申请退款内容
				newcaozuo="取消订单"
			}
			if(payStatusCode=="pay_status_done"){
			  if(orderStatusCode=="shipping_status_todo"||orderStatusCode=="shipping_status_done"){
				//商家未接单，服务完成，派单中-已经接单显示申请退款内容
				newcaozuo="申请退款"
			  }
			  if(orderStatusCode=="shipping_status_receive"){
				newcaozuo="申请退款"
			  }
			}
//			if(orderStatusCode=="order_status_todo1"){
//			//派单中-已经接单显示申请退款内容
//			newcaozuo="申请退款"
//		}
		    if(orderStatusCode=="order_status_noreturn"){
			newcaozuo="联系客服介入"
		    }
			return newcaozuo;
		},
		// 右1内容
		operation:function(){
			var orderStatusCode = this.orderStatusCode;
			// 付款状态
			var payStatusCode = this.payStatusCode;
			var attrInt = this.attrInt;//判断是否商家提醒
			var attrInt2 = this.attrInt2;//判断是否提醒技师
			var newcaozuo=""
			if(orderStatusCode=="order_status_apply_return"){
				newcaozuo="确认完成退款"
			}
			if(orderStatusCode=="shipping_status_receive"){//接受服务中
				newcaozuo="确认完成服务"
			}
			if(orderStatusCode=="shipping_status_done"){//服务完成点击完成订单
//				newcaozuo="确认完成订单"
				newcaozuo="评价"
			}
//			if(orderStatusCode=="order_status_todo1"){
//				//商家派单中显示确认完成内容
//				newcaozuo="确认完成"
//			}
			if(orderStatusCode=="order_status_todo"){
				//商家未接单显示催单css
				if(attrInt==2){
					newcaozuo="已提醒"
				}else{
					newcaozuo="提醒商家"
				}		
			}
			if(orderStatusCode=="shipping_status_todo"){
				//服务未开始显示提醒技师
			if(attrInt2==2){
				newcaozuo="已提醒"
			 }else{
				newcaozuo="提醒技师"
			 }
			}
			if(payStatusCode=="pay_status_done"){
			   if(orderStatusCode=="order_status_cancel"){
				newcaozuo="申请退款"
			   }
			}
			if(orderStatusCode=="order_status_done"){
				newcaozuo="再来一单"
//				newcaozuo="评价"
			}
			if(orderStatusCode=="order_status_noreturn"){
				newcaozuo="再次申请"
			}
			return newcaozuo;
		},
		//右1css
		btnState:function(){
			var orderStatusCode = this.orderStatusCode;
			// 付款状态
			var payStatusCode = this.payStatusCode;
			var newcaozuo="yichang"
			if(orderStatusCode=="order_status_apply_return"){//申请退款
				newcaozuo="querenfwcss"
			}
			if(orderStatusCode=="shipping_status_receive"){
				//服务进行中显示确认完成css
				newcaozuo="querenfwcss"
			}
			if(orderStatusCode=="shipping_status_done"){
				//服务完成显示评价css
				newcaozuo="pingjia"
			}
			//||orderStatusCode=="order_status_todo1"已经接单-派单中
			if(orderStatusCode=="shipping_status_todo"||orderStatusCode=="order_status_todo"){
				//服务未开始、商家未接单显示确认完成css
				newcaozuo="queren"
			}
			if(orderStatusCode=="order_status_done"){//订单完成显示评价
				newcaozuo="pingjia"
			}
			if(payStatusCode=="pay_status_done"){
			   if(orderStatusCode=="order_status_cancel"){//订单取消
				newcaozuo="pingjia"
			   }
			}
			if(orderStatusCode=="order_status_noreturn"){
				newcaozuo="pingjia"
			}
			return newcaozuo;
		},
		//右上状态css
		orderStateClass:function(){
			var orderStatusCode = this.orderStatusCode;
			var newcaozuo="yichang"
			if(orderStatusCode=="order_status_apply_return"||orderStatusCode=="order_status_cancel"||orderStatusCode=="shipping_status_todo"||orderStatusCode=="order_status_todo"){
				newcaozuo="order-ng "
			}
			if(orderStatusCode=="shipping_status_receive"||orderStatusCode=="order_status_todo1"){
				newcaozuo="ordering "
			}
			if(orderStatusCode=="shipping_status_done"||orderStatusCode=="order_status_done"){
				newcaozuo="order-ok "
			}
			return newcaozuo;
		},
		// 右上状态内容
		orderState:function(){
			var orderStatusCode = this.orderStatusCode;
			var newcaozuo="未记录"
			if(orderStatusCode == 'order_status_todo'){
				newcaozuo="商家未接单"
			}
        	else if(orderStatusCode == 'order_status_todo1'){newcaozuo = '商家派单中';}
        	else if(orderStatusCode == 'shipping_status_todo'){newcaozuo = '服务未开始';}
        	else if(orderStatusCode == 'shipping_status_receive'){newcaozuo = '接受服务中';}
        	else if(orderStatusCode == 'shipping_status_done'){newcaozuo = '服务完成';}
        	else if(orderStatusCode == 'shipping_status_return'){newcaozuo = '取消服务';}
        	else if(orderStatusCode == 'order_status_done'){newcaozuo = '订单完成';}
        	else if(orderStatusCode == 'order_status_cancel'){newcaozuo = '订单取消';}
        	else if(orderStatusCode == 'order_status_invalid'){newcaozuo = '订单结束';}
        	else if(orderStatusCode == 'order_status_apply_return'){newcaozuo = '订单申请退款';}
        	else if(orderStatusCode == 'order_status_noreturn'){newcaozuo = '申请退款驳回';}
        	else if(orderStatusCode == 'order_status_return'){newcaozuo = '订单已退款';}
			return newcaozuo;
		},
		// 支付状态内容
		payType:function(){
			// 付款状态
			var payStatusCode = this.payStatusCode;
			if(payStatusCode=="pay_status_done"){
				var newcaozuo="已付"
			}else{
				var newcaozuo="未付"
			}
			return newcaozuo;
		},
		// 支付状态code
		payCode:function(){
			// 付款状态
			var payStatusCode = this.payStatusCode;
			if(payStatusCode=="pay_status_done"){
				var newcaozuo="1"
			}else{
				var newcaozuo="2"
			}
			return newcaozuo;
		},
		payTypeCss:function(){
			// 付款状态
			var payStatusCode = this.payStatusCode;
			if(payStatusCode=="pay_status_done"){
				var newcaozuo="Je"
			}else{
				var newcaozuo="Wf"
			}
			return newcaozuo;
		},
		// 左一链接
		orderHref:function(){
			var orderStatusCode = this.orderStatusCode;
			var attrInt = this.attrInt;//判断是否商家提醒
			var attrInt2 = this.attrInt2;//判断是否提醒技师
			var newcaozuo="not"
			if(orderStatusCode == 'order_status_todo'){//商家未接单
				if(attrInt==2){
					
				}else{
				newcaozuo="tixingsj"
				}
			}else if(orderStatusCode=="shipping_status_todo"){
				//服务未开始显示提醒技师onclick
				if(attrInt2==2){
					
				}else{
				newcaozuo="tixingjs"
				}
			}
			else if(orderStatusCode=="order_status_todo1"){
				//商家已经接单显示确认完成订单onclick
				newcaozuo="queren"
			}
			else if(orderStatusCode=="shipping_status_receive"){//接受服务中
				newcaozuo="querenGoods"
			}
			else if(orderStatusCode=="shipping_status_done"){//服务完成点击完成订单
				newcaozuo = 'pingjia';
//				newcaozuo="querenOrder"	
			}
        	else if(orderStatusCode == 'order_status_done'){//订单完成
        		newcaozuo = 'zailaiyidan';
//        		newcaozuo = 'pingjia';
        	}
        	else if(orderStatusCode == 'order_status_cancel'){//订单取消显示申请退款
        		newcaozuo = 'tuikuansq';
        	}
        	else if(orderStatusCode == 'order_status_apply_return'){//订单申请退款
        		newcaozuo = 'querentuikuan';
        	}
        	else if(orderStatusCode == 'order_status_noreturn'){newcaozuo = 'tuikuansq';}
//        	else if(orderStatusCode == 'order_status_return'){newcaozuo = '订单已退款';}
			return newcaozuo;
		},
		// 左二链接onclick
		orderHrefNG:function(){
			var orderStatusCode = this.orderStatusCode;
			var newcaozuo="not"
			if(orderStatusCode=="order_status_todo"){//商家未接单
				newcaozuo="quxiaodindan"
			}else if(orderStatusCode == 'shipping_status_todo'){//服务未开始显示退款
				newcaozuo="tuikuanshengqing"
			}else if(orderStatusCode=="shipping_status_receive"){//接受服务中显示退款
				newcaozuo="tuikuanshengqing1";
			}else if(orderStatusCode=="shipping_status_done"){//服务完成显示退款
				newcaozuo="tuikuanshengqing2";
			}else if(orderStatusCode=="order_status_todo1"){//派单中显示退款
				newcaozuo="tuikuanshengqing3";
			}
			if(orderStatusCode=="order_status_noreturn"){//驳回寻找客服
				newcaozuo="lianxikefu";
			}
			return newcaozuo;
		},
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
