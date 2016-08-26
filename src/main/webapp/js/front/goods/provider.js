var pageNum = 10;
var ifGetCode = false;
SAVING_FLAG = false;
VERIFYCODE_PEROID = 0;

$(function() {
			// getMainDataList1();
		})

// 发送短信验证码 先判断手机号码是否被占用
function sendVerify() {
	var phoneNumIsChecked = $("#phoneNum").val().trim();
	if(!phoneNumIsChecked){lh.alert("请输入您要验证的手机号码！！");return;}
	lh.post('front', '/checkUserPhoneExist', {
				phone : phoneNumIsChecked
			}, function(rsp) {
				if (rsp.status == 'success') {
					if (rsp.msg == '手机号码已占用') {
						lh.alert(rsp.msg);
						return;
					} else {
						send(phoneNumIsChecked);
					}
				} else {
					lh.alert(rsp.msg);
				}
			}, 'json');
}

// 发送短信
function send(phoneNumIsChecked) {
	if (phoneNumIsChecked) {
		// getPayPasswordVerifycode
		if (VERIFYCODE_PEROID > 0) {
			return;
		} else {
			VERIFYCODE_PEROID = 60;
			var timerId = setInterval(function() {
						$('#btnMssage')
								.text('获取验证码(' + VERIFYCODE_PEROID + ')');
						VERIFYCODE_PEROID--;
						if (VERIFYCODE_PEROID <= 0) {
							clearInterval(timerId);
							$('#btnMssage').text('获取验证码');
						}
					}, 1000);
		}
		lh.post('front', '/getPhoneVerifycode', {
					phone : phoneNumIsChecked
				}, function(rsp) {
					if (rsp.status == 'success') {
						ifGetCode = true;
						lh.alert(rsp.msg);
					} else {
						ifGetCode = false;
						lh.alert(rsp.msg);
					}
				}, 'json');
	} else {
		lh.alert("请输入您要验证的手机号码！！");
	}
}
// 校验短信验证码
function checkPhoneCode() {
	// if($("#phoneNum").attr("disabled") == 'disabled'){
	// lh.alert('手机已经验证',lh.back);
	// return;
	// }
	var phoneCode = $("#phoneCode").val();
	if (!ifGetCode) {
		lh.alert('请先获取验证码');
		ifGetCode = false;
		return;
	}
	if (phoneCode.length < 1) {
		lh.alert('请填入获取的验证码');
		ifGetCode = false;
		return;
	}
	lh.post('front', '/checkVerifyCodeNum', {
				userVerifyCode : phoneCode
			}, function(rsp) {
				if (rsp.success) {
					updateUserPhone();
				} else {
					lh.alert(rsp.msg);
				}
			}, 'json');
}
function updateUserPhone() {
	var phoneNumIsChecked = $("#phoneNum").val();
	var param = {
		phone : phoneNumIsChecked
	};
	lh.post('front', '/updateUserPhone', param, function(rsp) {
				if (rsp.success) {
					lh.alert(rsp.msg, lh.reload);
				} else {
					lh.alert("修改失败");
				}
			});
}





var data1;
function getMainDataList1() {
	var shopId = $("#shopId").val();
	var typeCode = $("#typeCode").val();
	var param = {
		page : lh.page.currentPage,
		rows : pageNum,
		typeCode : typeCode,
		shopId : shopId
	};
	lh.post('front', '/getProviderList', param, function(rsp) {
				if (rsp.success) {
					data = rsp.rows;
					if (data && data.length > 0) {
						makeMainListDom1(data, false);
						// lh.page.currentPage ++;
					} else {
						$('#resultTip').text('没有更多数据').show();
						$(".weui-infinite-scroll").text('没有更多数据');
					}
				} else {
					lh.page.currentPage = 1;
					$('#resultTip').text(rsp.msg).show();
					lh.alert(rsp.msg);
				}
			}, 'json');

}
function makeMainListDom1(mainList, isAppend) {
	var template = $('#template1').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
				rows : mainList,
				picsDom : function() {
					var picPaths = this.picPaths;
					if (!picPaths)
						return '';
					var pics = picPaths.split(',');
					var length = pics.length;
					// if(length > 9)length = 9;
					var picsDom = '';
					for (var i = 0; i < length; i++) {
						var pic = pics[i];
						pic += buildOSSZoom(100, 100); // @@_OSS_IMG_@@
						picsDom += '<li><img src="' + pic
								+ '" width="100" /></li>';
					}
					return picsDom;
				},
				date : function() {
					var createdAt = this.createdAt;
					createdAt = lh.formatDate({
								date : new Date(createdAt),
								flag : 'datetime'
							});
					return createdAt;
				},
				avatar : function() {
					var avatar = '';
					var pic = this.avatar;
					pic += buildOSSZoom(35, 35);
					avatar += '<img width="30" src="' + pic
							+ '" class="img-responsive center-block">';
					return avatar;
				}
			});
	if (isAppend) {
		$('#data-container1').append(rendered);
	} else {
		$('#data-container1').html(rendered);
	}
}
function choice(id) {
	var provider = _.find(data1, {
				'id' : id
			});
	var providerAvatar = provider.avatar;
	var providerName = provider.realName;
	localStorage.setItem("bespeak_providerId", id);
	localStorage.setItem("bespeak_providerAvatar", providerAvatar);
	localStorage.setItem("bespeak_providerName", providerName);
	lh.back();
}