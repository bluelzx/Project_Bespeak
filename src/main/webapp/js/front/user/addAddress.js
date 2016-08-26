SAVING_FLAG = false;
$(function(){
	init();
});
function init(){
	var userAddressProvince = $("#province1").val();
	$.post('/getProvince',function(rsp){
		if(rsp){
			var dom = '<option value="">请选择</option>';
			$("#province").empty();
			for(var i = 0;i < rsp.length;i++){
				dom +=' <option';
				if(userAddressProvince == rsp[i].id){
					getCity(rsp[i].id);
					dom +=' <option';
				dom += ' value="'+rsp[i].id+'" selected="selected">'+rsp[i].name+'</option>';
				}
				dom += ' value="'+rsp[i].id+'">'+rsp[i].name+'</option>';
			}
			
			$("#province").append(dom);
			$('#province').change(function(){
				getCity($("#province").val());
			});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}
function getCity(city){
	var userAddressCity = $("#city1").val();
	//var provinceId = $("#province").val();
	$.post('/getCity',{provinceId:city},function(rsp){
		if(rsp){
			var dom = '<option value="">请选择</option>';
			$("#city").empty();
			for(var i = 0;i < rsp.length;i++){
				dom +=' <option';
				if(userAddressCity == rsp[i].id)dom += ' selected="selected" ';
				dom += ' value="'+rsp[i].id+'">'+rsp[i].name+'</option>';
			}
			$("#city").append(dom);
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}
function addUserAddress(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var userId = $("#userId").val();
	var userAddressId = $("#userAddressId").val();
	var receiverName = $("#yname").val();
	var addressDetail = $("#addressDetail").val();
	var phone = $("#yphone").val();
	var province = $("#province").val();
	var city = $("#city").val();
	var length=phone.length;
	if(!receiverName){lh.alert('请输入收货人的姓名');SAVING_FLAG = false;return;}
	if(receiverName.length>20){lh.alert('收货人姓名过长');SAVING_FLAG = false;return;}
	if(!phone){lh.alert('请输入收货人的联系电话');SAVING_FLAG = false;return;}
	if(phone>=0){}else{lh.alert('输入的联系电话格式不对');SAVING_FLAG = false;return;}
	if(length!=11){lh.alert('请输入11位联系电话');SAVING_FLAG = false;return;}
	if(!province){lh.alert('请输入收货人所在的省或直辖市');SAVING_FLAG = false;return;}
	if(!city){lh.alert('请输入收货人所在的城市');SAVING_FLAG = false;return;}
	if(!addressDetail){lh.alert('请输入收货人的详细地址');SAVING_FLAG = false;return;}
	
	var obj = {};
	obj.userId = userId;
	obj.id = userAddressId;
	obj.receiverName = receiverName;
	obj.addressDetail = addressDetail;
	obj.phone = phone;
	obj.province = province;
	obj.city = city;
//	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateUserAddress',obj,function(rsp){
		SAVING_FLAG = false;
		if(rsp.status == 'success'){
			lh.alert(rsp.msg);
			lh.jumpR('/address');
			//frontBaseConfirm('收货地址已经成功保存，是否跳转到个人中心？',"lh.jumpR('/user')");
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
}

