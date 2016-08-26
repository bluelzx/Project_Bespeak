SAVING_FLAG = false;
$(function(){
	getUserAddress();
	init();
});

function init(){
	$("#province").selectpicker({
	    style: 'btn-info',
		actionsBox:true,
		header:'请选择',
	    showTick:true,
	    size: 10
	});
	$('#province').on('changed.bs.select', function (e) {
		getCity();
	});
	$("#city").selectpicker({
	    style: 'btn-info',
		actionsBox:true,
		header:'请选择',
		title:'',
	    showTick:true,
	    size: 10
	});
}
var data;
function getUserAddress(){
	var userId = $("#userId").val();
		$.post('/getUserAddressList',function(rsp){
			frontLoginCheck(rsp);
			if(rsp.success){
				 data = rsp.rows;
				if(data && data.length>0){
					makeMainListDom(data, true);
				}else{
					$('#resultTip').text('没有更多数据').show();
					$(".weui-infinite-scroll").text('没有更多数据');
				}
			}else{
				lh.page.currentPage = 1;
				$('#resultTip').text(rsp.msg).show();
				lh.alert(rsp.msg);
			}
		}, 'json');
	
}
function choiceAddress(id){
	var address=_.find(data, { 'id': id});
	localStorage.setItem("bespeak_addressId",id);
	localStorage.setItem("bespeak_province",address.province);
	localStorage.setItem("bespeak_city",address.city);
	localStorage.setItem("bespeak_address",address.provinceName+address.cityName+address.addressDetail);
	lh.back();
}

function makeMainListDom(mainList, isAppend) {
	var template = $('#template').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		picsDom:function(){
			var picPaths = this.picPaths;
			if(!picPaths)return '';
			var pics = picPaths.split(',');
			var length = pics.length;
			//if(length > 9)length = 9;
			var picsDom = '';
			for(var i=0;i<length;i++){
				var pic = pics[i];
				pic += buildOSSZoom(100, 100); //@@_OSS_IMG_@@
				picsDom += '<li><img src="'+pic+'" width="100" /></li>';
			}
			return picsDom;
		},
		date:function(){
			var createdAt = this.createdAt;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		isDefult:function(){
			var isDefult = '';
			var defult = this.isDefault;
			if(defult == 1){
				isDefult = '<img src="/images/front/choice-on.png" alt="" class="mrdzC">';
			}else{
				isDefult = '<img src="/images/front/choice-no.png" alt="" class="mrdzC">';
			}
			return isDefult;
		}	
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
//设置默认地址
function setDefult(addressId){
	   $('.mrdzS').each(function (i) {
           $(this).click(function () {
               $('.mrdzS img').attr('src', '/images/front/choice-no.png')
               $('.mrdzS').eq(i).children('img').attr('src', '/images/front/choice-on.png')
           })
       });
	var userId = $("#userId").val();
	var param = {id:addressId,userId:userId};
	$.post('/setDefultAddress',param,function(rsp){
		if(rsp.success){
			
		}
	}, 'json');
}
function getCity(city){
	var provinceId = $("#province").val();
	$.post('/getCity',{provinceId:provinceId},function(rsp){
		if(rsp){
			var dom = '<select id="city""><option value="">请选择</option>';
			$("#cityDiv").empty();
			for(var i = 0;i < rsp.length;i++){
				dom +=' <option';
				if(city && city == rsp[i].id)dom += ' selected="selected" ';
				dom += ' value="'+rsp[i].id+'">'+rsp[i].name+'</option>';
			}
			dom += '</select>';
			$("#cityDiv").append(dom);
			$("#city").selectpicker({
				title:'',
			    showTick:true,
			    size: 10
			});
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
	var receiverName = $("#receiverName").val();
	var addressDetail = $("#addressDetail").val();
	var phone = $("#phone").val();
	var province = $("#province").val();
	var city = $("#city").val();
	if(!receiverName){lh.alert('请输入收货人的姓名');SAVING_FLAG = false;return;}
	if(!province){lh.alert('请输入收货人所在的省或直辖市');SAVING_FLAG = false;return;}
	if(!city){lh.alert('请输入收货人所在的城市');SAVING_FLAG = false;return;}
	if(!addressDetail){lh.alert('请输入收货人的详细地址');SAVING_FLAG = false;return;}
	if(!phone){lh.alert('请输入收货人的联系电话');SAVING_FLAG = false;return;}
	var obj = {};
	obj.userId = userId;
	obj.id = userAddressId;
	obj.receiverName = receiverName;
	obj.addressDetail = addressDetail;
	obj.phone = phone;
	obj.province = province;
	obj.city = city;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateUserAddress',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		frontLoginCheck(rsp);//登陆检查
		$('#baseDialog').hide();
		if(rsp.status == 'success'){
			lh.jumpR('/address');
			//frontBaseConfirm('收货地址已经成功保存，是否跳转到个人中心？',"lh.jumpR('/user')");
		}else{
			lh.alert(rsp.msg);
		}
	},'json')
}

function deleteAddress(id){
		lh.post('front','/deleteUserAddressThorough',{id:id},function(rsp){
			if(rsp.success){
				lh.alert(rsp.msg,lh.reload);
			}else{
				lh.alert(rsp.msg,lh.reload);
			}
		});	
}


