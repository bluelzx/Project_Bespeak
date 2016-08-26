CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
INOROUT = 1;
$(function(){
	getAllAccountLogList();
	lh.scrollBottom(getAllAccountLogList);
});

function getAllAccountLogList(){
	var param = {};
	param.page = CURRENT_PAGE;
	param.rows = PAGE_COUNT;
	param.flag = 1;
	param.InOrOut = INOROUT;
	$.post('/getAccountLogList',param,function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp){
			if(rsp.status == 'success'){
				var count = rsp.total;
				if( count && count > 0){
					makeAllAccountLogListDom(rsp.rows,1);
					CURRENT_PAGE++;
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

function makeAllAccountLogListDom(accountLogList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	for(var a in accountLogList){
		accountLogList[a].createdAt = formatDate(accountLogList[a].createdAt,1,1);
		if(accountLogList[a].inOrOut == 2){
			accountLogList[a].inOrOut = "";
		}
	}
	var rendered = Mustache.render(template, {rows:accountLogList});
	if(isAppend){
		$('#fundDetailList').prepend(rendered);
	}else{
		$('#fundDetailList').html(rendered);
	}
}

function MyIn(){
	$("#OUT1,#OUT2").hide();
	$("#IN1,#IN2").show();
	INOROUT = 1;
	CURRENT_PAGE = 1;//当前页数
	$('#fundDetailList').empty();
	getAllAccountLogList();
}

function MyOut(){
	$("#IN1,#IN2").hide();
	$("#OUT1,#OUT2").show();
	INOROUT = 2;
	CURRENT_PAGE = 1;//当前页数
	$('#fundDetailList').empty();
	getAllAccountLogList();
}

