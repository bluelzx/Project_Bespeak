$(function() {
	
})
function addProvider(){
	var providerName=$("#js-name").val();
	var phone=$("#js-phone").val();
	var shopName=$("#dianpu").val();
	var picPath=$("#js-name").val();
//	  var re =[1][358][0-9]{9};
	if(!providerName){lh.alert("请输入姓名");return;}
	if(!phone){lh.alert("请输入联系电话");return;}
//	if (!re.test(phone)) { lh.alert("请输入正确的联系电话");return;}
	if(!shopName){lh.alert("请输入您所在店铺名字");return;	}
	var typeCode="provider_apply";
	var param = {typeCode:typeCode,replyUsername:providerName,phone:phone,shopName:shopName,attr3:picPath};
	lh.post('front', '/addProvider', param, function(rsp) {
		if(rsp.success){
			lh.alert("申请中，请等待工作人员联系您。。。。。");
			lh.back();
			}else{
				lh.alert(rsp.msg);
			}
	}, 'json');	
}