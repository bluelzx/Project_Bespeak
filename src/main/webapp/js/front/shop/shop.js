/** 鬼市（逛逛）主JS  */
SAVING_FLAG = false;
MODULEID = 1;
$(function(){
	loadShopGoods();
	window.emojiPicker = new EmojiPicker({
		  emojiable_selector: '[data-emojiable=true]',
		  assetsPath: '/third-party/emoji-picker/img/',
		  popupButtonClasses: 'fa fa-smile-o'
		  
		}); 
	window.emojiPicker.discover();
});
/**加载藏品*/
function loadShopGoods(){
	var shopId = $("#shopId").val();
	var userId = $("#userId").val();
	var wholesaleId = $("#wholesaleId").val();
	var param = {};
	param.count = 4;
	param.start = 0;
	param.withGoods = 1;
	if(MODULEID != 5){
		param.forAuction = 1;
		param.shopId = shopId;
		param.forMarket = 1;
	}else{
		param.shopId = wholesaleId;
		param.allWholesaleGoods = 1;
		param.moduleId = 5;
	}
	param.orderBy = "created_at";
	param.ascOrdesc = "DESC";
	$.post('/getGoodsList',param,function(rsp){
		if(rsp){
			if(rsp.success){
				var count = rsp.total;
				if(count && count > 0){
					makeShopGoodsListDom(rsp.rows,1);
				}else{
					var dom = '<div class="div_gg">'
								+'<button type="button" class="btn btn-primary" onclick="sendMessage('+shopId+')">邀请该用户发布藏品</button>'
								+'</div>';
					$("#shopGoods").append(dom);
				}
			}else{
				lh.alert(rsp.msg);
			}
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}

function makeShopGoodsListDom(shopGoodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	/*for(var a in shopGoodsList){
		if(shopGoodsList[a].moduleId == 5){
			shopGoodsList[a].moduleId = shopGoodsList[a].moduleId;
		}else{
			shopGoodsList[a].moduleId = "";
		}
	}*/
	var rendered = Mustache.render(template, {rows:shopGoodsList});
	if(isAppend){
		$('#shopGoods').append(rendered);
	}else{
		$('#shopGoods').html(rendered);
	}
}

function showCommentWin(objectId){
	$("#content").empty();
	$(".textarea-control").empty();
	$("#comment").show();
	$("#objectId").val(objectId);
}

function closeCommentWin(){
	$("#comment").hide();
}

/**关注**/
function focusHe(userId){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var obj = {};
	obj.userId = userId;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateFans',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				location.reload();
			}
		}
	},'json');
}
/**取消关注**/
function cancelFocus(userId){
	$.post('/deleteFans',{userId:userId},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function commonLoadGoods(moduleId,wholesaleId){
	if(moduleId == 3){
		$("#goodsType"+moduleId).addClass("on");
		$("#goodsType2").removeClass("on");
		$("#goodsType4").removeClass("on");
		$("#goodsType1").removeClass("on");
		$("#goodsType5").removeClass("on");
	}else if(moduleId == 4){
		$("#goodsType"+moduleId).addClass("on");
		$("#goodsType1").removeClass("on");
		$("#goodsType2").removeClass("on");
		$("#goodsType3").removeClass("on");
		$("#goodsType5").removeClass("on");
	}else if(moduleId == 1){
		$("#goodsType"+moduleId).addClass("on");
		$("#goodsType4").removeClass("on");
		$("#goodsType3").removeClass("on");
		$("#goodsType2").removeClass("on");
		$("#goodsType5").removeClass("on");
	}else if(moduleId == 5){
		$("#goodsType"+moduleId).addClass("on");
		$("#goodsType1").removeClass("on");
		$("#goodsType3").removeClass("on");
		$("#goodsType4").removeClass("on");
		$("#goodsType2").removeClass("on");
	}else{
		$("#goodsType"+moduleId).addClass("on");
		$("#goodsType5").removeClass("on");
		$("#goodsType3").removeClass("on");
		$("#goodsType4").removeClass("on");
		$("#goodsType1").removeClass("on");
	}
	MODULEID = moduleId;
	$("#shopGoods").empty();
	loadShopGoods();
}

function sendMessage(receiveId){
	var title = "邀请您发布";
	if(MODULEID == 2){
		title +="微拍藏品";
	}else if(MODULEID == 3){
		title +="即时拍藏品";
	}else if(MODULEID == 4){
		title +="专场藏品";
	}else if(MODULEID == 1){
		title +="店铺藏品";
	}else if(MODULEID == 5){
		title +="批发城藏品";
	}
	var obj = {title:title,content:title,receiveId:receiveId};
	$.post('/sendNotice',obj,function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				lh.alert('您的邀请通知已经发送给该用户');
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function addGoodsComment(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var content = $("#content").val();
	var objectId = $("#objectId").val();
	if(!content){
		//lh.alert('请填写评论内容');
		$("#tips").text("请填写评论内容").show();
		SAVING_FLAG = false;
		return ;
	}
	var obj = {};
	obj.objectId = objectId;
	obj.content = content;
	obj.commentTypeId = 4;
	obj.comment = 'yes';
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateComment',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				if(rsp.noPhone == 'noPhone'){
					lh.alert(rsp.msg,"jumpToBindUserPhone()");
				}else{
					lh.alert(rsp.msg);
				}
			}
		}
	},'json');
}
