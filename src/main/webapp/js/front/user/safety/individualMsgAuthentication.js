$(function() {
			initWxSDK(['chooseImage', 'previewImage', 'uploadImage',
					'downloadImage']);
			if (!lh.upload)
				lh.upload = {};
			if (!lh.upload.pathStr)
				lh.upload.pathStr = '';
		});

/** 显示图片* */
function showImg(localId, serverId, param) {
	if (!localId || !serverId)
		return;
	// var domId = getIdFromDate();
	var domId = serverId;
	// lh.alert('domId:'+domId+'-localId:'+localId);
	/*
	 * var dom = '<li class="weui_uploader_file" id="weui_uploader_file_'+domId+'" style="background-image:url('+localId+')">'
	 *  +'<i class="weui_icon_cancel fr" onclick="removeSelf(\''+domId+'\')"
	 * style="position: relative;bottom: 5px;background-color: black;"></i>'
	 *  +'</li>';
	 */
	if (param && param.picType) {
		var picType = param.picType;
		if (picType == 1) {
			$("#pic1").attr("src", localId);
			lh.param.picPath1 = serverId;

		} else if (picType == 2) {
			$("#pic2").attr("src", localId);
			lh.param.picPath2 = serverId;

		} else if (picType == 3) {
			$("#pic3").attr("src", localId);
			lh.param.picPath3 = serverId;
		}
	}
}

/** 删除图片* */
function removeSelf(domId) {
	lh.upload.pathStr = lh.upload.pathStr.replace(',' + domId, '');
	lh.upload.pathStr = lh.upload.pathStr.replace(domId, '');
	$("#weui_uploader_file_" + domId).remove();
}

function addUserAuthentication() {
	var realName = $("#realName").val();
	var idcardNum = $("#idcardNum").val();

	if (!realName) {
		lh.alert('请填写真实姓名');
		return;
	}
	if (!idcardNum) {
		lh.alert('请填写身份证号');
		return;
	}
	if (!idcardNum) {
		lh.alert('请填写身份证号');
		return;
	}
	// if(!lh.param.picPath1){lh.alert('请上传身份证正面照');return;}
	// if(!lh.param.picPath2){lh.alert('请上传身份证背面照');return;}
	// if(!lh.param.picPath3){lh.alert('请上传手持身份证照');return;}

	var picPaths = lh.param.picPath1 + "," + lh.param.picPath2 + ","
			+ lh.param.picPath3;
	// var picPaths =
	// 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg' + ","
	// + 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg'
	// +","+
	// 'http://weipaike.img-cn-qingdao.aliyuncs.com/2016061508550955.jpeg';
	var param = {
		realName : realName,
		idcardNum : idcardNum,
		picPaths : picPaths
	};
	lh.post('front', '/user/safety/addUserAuthentication', param,
			function(rsp) {
				if (rsp.success) {
					if (rsp.msg == '验证成功') {
						lh.alert(rsp.msg, validateSuccess);
					} else {
						lh.alert(rsp.msg);
					}
				} else {
					lh.alert(rsp.msg);
				}
			}, 'json');
}

function validateSuccess() {
	lh.jumpR("/user/safety/validateSuccess");
}