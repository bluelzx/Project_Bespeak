
SAVING_FLAG = false;
$(function(){
	$("#city,#antiqueCity,#sex").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
	$("#antiqueCityProvince").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
	$('#antiqueCityProvince').on('changed.bs.select', function (e) {
		getCity();
	});
	$("#antiqueCityCity").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
		title:'',
	    showTick:true,
	    size: 10
	});
	var antiqueCityCityValue = $("#antiqueCityCityValue").val();
	if(antiqueCityCityValue){
		getCity(antiqueCityCityValue);
	}
});

function getCity(city){
	var provinceId = $("#antiqueCityProvince").val();
	$.post('/getCity',{provinceId:provinceId},function(rsp){
		if(rsp){
			var dom = '<select id="antiqueCityCity""><option value="">请选择</option>';
			$("#cityDiv").empty();
			for(var i = 0;i < rsp.length;i++){
				dom +=' <option';
				if(city && city == rsp[i].id)dom += ' selected="selected" ';
				dom += ' value="'+rsp[i].id+'">'+rsp[i].name+'</option>';
			}
			dom += '</select>';
			$("#cityDiv").append(dom);
			$("#antiqueCityCity").selectpicker({
				title:'',
			    showTick:true,
			    size: 10
			});
		}else{
			lh.alert(rsp.msg);
		}
	},'json');
}

function saveInformation(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var id = $("#id").val();
	var nickName = $("#nickName").val();
	var phone = $("#phone").val();
	var realName = $("#realName").val();
	var address = $("#address").val();
	var city = $("#city").val();
	var qq = $("#qq").val();
	var sex = $("#sex").val();
	var contactWay = $("#contactWay").val();
	var antiqueCityId = $("#antiqueCity").val();
	//var antiqueCityProvince = $("#antiqueCityProvince").val();
	//var antiqueCityCity = $("#antiqueCityCity").val();
	var obj = {};
	obj.id = id;
	obj.nickName = nickName;
	obj.phone = phone;
	obj.realName = realName;
	obj.address = address;
	obj.qq = qq;
	obj.sex = sex;
	obj.city = city;
	obj.antiqueCityId = antiqueCityId;
	//obj.antiqueCityProvince = antiqueCityProvince;
	//obj.antiqueCityCity = antiqueCityCity;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateUser',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.alert('保存成功');
				setTimeout(function(){
					var url = '/user';
					var r = $("#r").val();
					if(r) url += "?r="+r;
					window.location.href = url;
					$('#baseDialog').hide();
				},'3000');
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

