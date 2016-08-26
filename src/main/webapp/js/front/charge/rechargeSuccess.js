SAVING_FLAG = false;
$(function(){
	initRspJson();
});

function initRspJson(){
    var rspJson = $('#rspJson').val();
    var rsp = JSON.parse(rspJson);
    if(rsp.status == 'success'){
		$('#status').text('充值成功').css('color','green');
		$('#money').text(rsp.money);
    	$('#moneyLi').show();
    }else{
    	$('#moneyLi').hide();
		$('#status').text('充值失败').css('color','red');
    }
	$('#msg').text('提示：'+rsp.msg);
	$('#sysTip').text('如果遇到任何问题或疑问，请即时联系客服人员。');
}

