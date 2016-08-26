$(function() {
	var isRealAuth = $("#isRealAuth").val();
	if(isRealAuth != 1){
		$("#next").show();
	}
});

function saveUserAuthentication(){
lh.post('front', '/user/safety/saveUserAuthentication', function(rsp) {
		if(rsp.success){
			if(rsp.msg == '验证成功'){
				lh.alert(rsp.msg,validateSuccess);
			}else{
				lh.alert(rsp.msg,jumpTosafetyIndex);
			}
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json');
}
function jumpTosafetyIndex(){
	lh.jumpR('/user/safety/paySafety');
}