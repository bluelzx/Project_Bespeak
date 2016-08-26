CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
PARAM = {allTypeId:1};
$(function(){
	loadNews();
	lh.scrollBottom(loadNews);
});

function loadNews(){
	var param = {};
	if(PARAM.typeId){
		param.typeId = PARAM.typeId;
	}
	param.mainStatus = 1;
	if(PARAM.allTypeId){
		param.allTypeId = 1;
	}
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	$.post('/getArticleIsTopIsRecommendList',param,function(rsp){
		if(rsp){
			if(rsp.status == 'success'){
				var rows = rsp.rows;
				if(rows && rows.length > 0){
					makeNewsListDom(rsp.rows,1);
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


function makeNewsListDom(newsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	for(var a in newsList){
		if(newsList[a].isRecommend == 1){
			newsList[a].isRecommend = null;
		}
		if(newsList[a].isTop == 1){
			newsList[a].isTop = null;
		}
	}
	var rendered = Mustache.render(template, {rows:newsList});
	if(isAppend){
		$('#news').append(rendered);
	}else{
		$('#news').html(rendered);
	}
}

function allNews(){
	PARAM = {};
	$("#news").empty();
	$("#allNews").css({'color':'red','border-bottom':'1px solid red'});
	$("#antiqueNews").css({'color':'','border-bottom':''});
	$("#antiqueKnowloge").css({'color':'','border-bottom':''});
	CURRENT_PAGE = 1;
	PARAM.allTypeId = 1;
	loadNews();
}

function antiqueNews(){
	PARAM = {};
	$("#news").empty();
	$("#antiqueNews").css({'color':'red','border-bottom':'1px solid red'});
	$("#allNews").css({'color':'','border-bottom':''});
	$("#antiqueKnowloge").css({'color':'','border-bottom':''});
	CURRENT_PAGE = 1;
	PARAM.typeId = '42';
	loadNews();
}

function antiqueKnowloge(){
	PARAM = {};
	$("#news").empty();
	$("#antiqueKnowloge").css({'color':'red','border-bottom':'1px solid red'});
	$("#antiqueNews").css({'color':'','border-bottom':''});
	$("#allNews").css({'color':'','border-bottom':''});
	CURRENT_PAGE = 1;
	PARAM.typeId = '45';
	loadNews();
}


