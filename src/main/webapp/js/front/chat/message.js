$(function(){
	loadMessageList();
});

function loadMessageList(){
	var param = {};
	lh.post('front', '/message/getMessageList',param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeListDom(data, true);
				lh.page.currentPage ++;
			}else{
				$('#resultTip').text('没有更多数据').show();
			}
		}else{
			lh.page.currentPage = 1;
			$('#resultTip').text(rsp.msg).show();
			lh.alert(rsp.msg);
		}
	}, 'json');
}
function makeListDom(mainList, isAppend){
	var template = $('#template').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		date:function(){
			var createdAt = this.sendTime;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		}
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
function readMessage(senderSerial,id){
	var param = {id:id};
	lh.post('front', '/message/updateMessage',param, function(rsp) {
		if(rsp.success){
			lh.jumpR('/chat/'+senderSerial);
		}else{
			
		}
	}, 'json');
}

