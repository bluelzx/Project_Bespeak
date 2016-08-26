CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
$(function(){
	getAllReceiveAddressList();
	lh.scrollBottom(getAllReceiveAddressList);
});

function getAllReceiveAddressList(){
	var userId = $("#userId").val();
	var param = {};
	param.userId = userId;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	$.post('/getUserAddressList',param,function(rsp){
		frontLoginCheck(rsp);//登陆检查 
		if(rsp.status == 'success'){
			var count =  rsp.total;
			if(count && count > 0){
				makeAllReceiveAddressListDom(rsp.rows,1);
				CURRENT_PAGE++;
			}else{
				$('#resultTip').text('没有更多数据').show();
				//background-color:#f5f5f5;
				$(".c_0100_3").css('background-color','#f5f5f5');
			}
		}else{
			lh.alert(rsp.msg);
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}

function makeAllReceiveAddressListDom(commentList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:commentList});
	if(isAppend){
		$('#receiveAddressList').prepend(rendered);
	}else{
		$('#receiveAddressList').html(rendered);
	}
}

function deleteUserAddress(ids){

	$.post('/deleteUserAddressThorough',{ids:ids},function(rsp){
		frontLoginCheck(rsp);
		if(rsp.status == 'success'){
			window.location.reload();
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}
