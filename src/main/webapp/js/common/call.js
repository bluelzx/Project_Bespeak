 //拨打页

$(function(){
	callPageFun();
});

var callPageFun = function(){
	var that = this;
	var $thisBox = $('#callPage');
	var $numView = $('#numView',$thisBox);
	// 数字键
	$('.numKey',$thisBox).click(function(){
	    var thisNum = $('h1',this).html();
	    $numView.append(thisNum);
	});
	// 退格键
	$('.backBtn',$thisBox).click(function(){
	    var thisViewValue = $numView.html();
	    $numView.html(  thisViewValue.substr(0,thisViewValue.length-1) );
	});
	// 清除键
	$('.call_clear_num',$thisBox).click(function(){
	    var thisViewValue = $numView.html();
	    $numView.html('');
	});
}

function showCallActionSheet(param){
	if(!param)param = {};
	if(!param.opt)param.opt = 'pay';
	if(param.opt == 'amBid'){
		$('#call_current_price').text(param.price);
		$('#numView').text(param.nextPrice || '');
		param.buyoutPrice = 200;
		if(param.buyoutPrice){
			$('#call_opt_buyout').show();
			$('#buyoutPrice').text(param.buyoutPrice);
			$('#call_opt_buyout').css('width','40%').show();
			$('#call_opt_offer').css('width','55%');
		}else{
			$('#call_opt_offer').css('width','100%');
			$('#call_opt_buyout').hide();
		}
	}else if(param.opt == 'pay'){
	}else if(param.opt == 'charge'){
	}else if(param.opt == 'professionBid'){
		$('#call_current_price').text(param.price);
		$('#numView').text(param.nextPrice || '');
	}
	
	var mask = $('#call_mask');
    var weuiActionsheet = $('#call_weui_actionsheet');
    weuiActionsheet.addClass('weui_actionsheet_toggle');
    mask.show().addClass('weui_fade_toggle').click(function () {
    	hideCallActionSheet(weuiActionsheet, mask);
    });
    $('#call_actionsheet_cancel').click(function () {
    	hideCallActionSheet(weuiActionsheet, mask);
    });
    weuiActionsheet.unbind('transitionend').unbind('webkitTransitionEnd');
}

function hideCallActionSheet(weuiActionsheet, mask) {
	if(!weuiActionsheet)weuiActionsheet = $('#call_weui_actionsheet');
	if(!mask)mask = $('#call_mask');
	weuiActionsheet.removeClass('weui_actionsheet_toggle');
	mask.removeClass('weui_fade_toggle');
	weuiActionsheet.on('transitionend', function () {
		mask.hide();
	}).on('webkitTransitionEnd', function () {
		mask.hide();
	})
}


