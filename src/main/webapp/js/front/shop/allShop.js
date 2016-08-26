CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
$(function(){
	loadallShopList();
	lh.scrollBottom(loadallShopList);
});

function loadallShopList(){
	var param = {rows:PAGE_COUNT,page:CURRENT_PAGE};
	$.post('/getShopList',param,function(rsp){
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
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}


function makeMainListDom(shopList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows:shopList
		});
	if(isAppend){
		$('#shopList').append(rendered);
	}else{
		$('#shopList').html(rendered);
	}
}

function myShop(){
	var userId = $("#userId").val();
	if(userId){
		var url = "/myShop";
		var r = $("#r").val();
		if(r) url += "?r="+r;
		window.location.href = url;
	}else{
		window.location.href="/login"
	}
}



