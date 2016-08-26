/**初始化数据*/
PAGE_COUNT = 10;//取多少条数据
CURRENT_PAGE = 1;//当前页数
CURRENT_FANS_PAGE = 1;//当前页数
CURRENT_RECOMMEND_PAGE = 1;
CURRENT_NOTICE_PAGE = 1;
CURRENT_CHAT_PAGE = 1;
var userId = $("#userId").val();
$(function(){
	var u = navigator.userAgent;
	if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
		var dom ="<audio id='msgAudio' src='/file/audio/newMsg.ogg' preload='auto'></audio>";
		$("#voice").append(dom);
	} else if (u.indexOf('iPhone') > -1 || u.indexOf("Safari") > -1) {//苹果手机
		var dom ="<audio id='msgAudio' src='/file/audio/newMsg.mp3' preload='auto'></audio>";
		$("#voice").append(dom);
	} /*else if (u.indexOf('Windows Phone') > -1) {//winphone手机
		alert("winphone手机");
	}*/
	loadRecommend();
});

function loadRecommend(){
	var param = {rows:PAGE_COUNT,page:CURRENT_RECOMMEND_PAGE,userId:userId};
	$.post('/getFansList',param,function(rsp){
		if(rsp){
			if(rsp.success){
				var count = rsp.total
				if(count && count > 0){
					makeRecommendListDom(rsp.rows,true);
					CURRENT_RECOMMEND_PAGE ++;
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

function makeRecommendListDom(fansList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:fansList});
	if(isAppend){
		$('#teacherAndFriend').append(rendered);
	}else{
		$('#teacherAndFriend').html(rendered);
	}
}
