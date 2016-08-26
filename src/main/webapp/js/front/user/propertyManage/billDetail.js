var pageNum = 10;//每页加载的条数
var param = {page:lh.page.currentPage, rows:pageNum};
$(function(){
//	initPage();
	getBillDetailList(param,true);
	rollingLoad();//滚动加载
	downRefresh();//下拉刷新
	
});
function getallBill(){
	getBillDetailList(param,true);
}
function getfreezeMoneyBill(){
	param = {payCode:61};
	getBillDetailList(param,false);
}
function getactivateMoneyBill(){
	param = {payCode:62};
	getBillDetailList(param,false);
}
function getwithdrawalBill(){
	param = {attrStr:'提现'};
	getBillDetailList(param,false);
}

function getBillDetailList(param,isAppend){
	/*var param = {page:lh.page.currentPage, rows:pageNum};*/
	lh.post('front', '/getBillDetailList', param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom(data, isAppend);
				lh.page.currentPage ++;
			}else{
				$('#resultTip').text('没有更多数据').show();
				$(".weui-infinite-scroll").text('没有更多数据');
			}
		}else{
			lh.page.currentPage = 1;
			$('#resultTip').text(rsp.msg).show();
			$(".weui-infinite-scroll").text('没有更多数据');
			lh.alert(rsp.msg);
		}
	}, 'json');
}

function makeMainListDom(mainList, isAppend) {
	var template = $('#template').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		date:function(){
			var createdAt = this.payTime;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		isInorOut:function(){
			var inOrOut = this.inOrOut;
			var isInorOut = '';
			if(inOrOut == 2){
				isInorOut = '<div class="col-xs-4 pl7 fs18 green pt15 text-right">+'+this.money+'</div>';
			}else if(inOrOut == 1){
				isInorOut = '<div class="col-xs-4 pl7 fs18 red pt15 text-right">-'+this.money+'</div>';
			}
			return isInorOut;
		},
		payCode:function(){
			var dictName = this.dictName;
			var attrStr = this.attrStr;
			var payCode = '';
			if(dictName == '解冻资金'){
				payCode = '<div class="col-xs-12 plr0 fs16">'+this.dictName+'</div>';
			}else if(dictName == '冻结资金'){
				payCode = '<div class="col-xs-12 plr0 fs16">'+this.dictName+'</div>';
			}else if(attrStr == '充值'){
				payCode = '<div class="col-xs-12 plr0 fs16">'+this.attrStr+'</div>';
			}else if(attrStr == '提现'){
				payCode = '<div class="col-xs-12 plr0 fs16">'+this.attrStr+'</div>';
			}
			return payCode;
		}
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
	checkDom();
}

//下拉刷新
function downRefresh(){
	$("#weui-layer").show();
	$(document.body).pullToRefresh();
	$(document.body).on("pull-to-refresh", function() {
		lh.reload();
	});
	$(document.body).pullToRefreshDone();
}
//滚动加载
function rollingLoad(){
	var loading = false;  //状态标记
	$(document.body).infinite().on("infinite", function() {
	  if(loading) return;
	  loading = true;
	  setTimeout(function() {
	    //$(document.body).destroyInfinite();
//	    $(".weui-infinite-scroll").hide();
	  	getBillDetailList(param,true);
	    loading = false;
	  }, 1000);   //模拟延迟
});
}

function checkDom(){
	var domNum = $(".mt6").size();
	if(domNum>=100){
		$(".mt6:lt(20)").remove();
	}
	if(domNum<10){
		$(".weui-infinite-scroll").text('没有更多数据');
	}
}

function initPage(){
	
}

function toggleBtn(){
	var isOpen = $("#dropdownMenu1").attr("aria-expanded");
	if(isOpen){
		$("#dropdown").attr("class","dropdown open");
	}
}

