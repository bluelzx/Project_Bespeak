//TOP_PRICE = null;
//AUTO_OFFER_ID = null;
//NOTICE_ID = null;
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据
SAVING_FLAG = false;
MY_SWIPER = null;
$(function(){
	initBanner();//common.js
	initData();
	lh.scrollBottom(initData);
});

/** 初始化数据 - 是否开拍提醒，是否委托出价 */
function initData(){
	 $('#bannerUl').show();//先隐藏图片，页面初始化完成后再加载图片，避免加载时出现图片跳动
	 MY_SWIPER = new Swiper ('.swiper-container', {
	    direction: 'horizontal',
	    loop: true,
	    // 如果需要分页器
	    pagination: '.swiper-pagination',
	    // 如果需要前进后退按钮
	    nextButton: '.swiper-button-next',
	    prevButton: '.swiper-button-prev',
	    // 如果需要滚动条
	    scrollbar: '.swiper-scrollbar'
	  })        
	var shopId = $("#shopId").val();
	var paramObj = {shopId:shopId}
	paramObj.rows = PAGE_COUNT;
	paramObj.page = CURRENT_PAGE;
	paramObj.forAuction = 1;
	$.post('/getGoodsList',paramObj,function(rsp){
		if(rsp){
			if(rsp.success){
				 var count =  rsp.total;
				if( count && count > 0){
					makeCommodityGoodsListDom(rsp.rows,1);
					CURRENT_PAGE++;
				}else{
					$('#resultTip').text('没有更多数据').show();
				}
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
	
}

function makeCommodityGoodsListDom(GoodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:GoodsList});
	if(isAppend){
		$('#AuctionCollectionLeft').append(rendered);
	}else{
		$('#AuctionCollectionLeft').html(rendered);
	}
}

function showCommentWin(objectId){
	$("#comment").show();
	$("#objectId").val(objectId);
}

function closeCommentWin(){
	$("#comment").hide();
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
	obj.commentTypeId = 3;
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

/** 显示委托竞价窗口 */
function showAutoOffer(){
	var $offerShow = $('#offerShow');
	var state = $offerShow.attr('state');
	$('#offerWin').show();
}

/** 委托竞价：确认，取消 */
function doAutoOffer(operation,paramObj){
	if(operation == 'cancel'){
		$('#offerWin').hide();
	}else if(operation == 'save'){
		var state = $('#offerShow').attr('state');
		if(state && state == 2){//已参与 - 取消
			delAutoOffer();SAVING_FLAG = false;return;
		}
		if(SAVING_FLAG)return;
		SAVING_FLAG = true;
		var topPrice = $('#topPrice').val();
		if(!topPrice){
			lh.alert('请输入您的报价金额');SAVING_FLAG = false;return;
		}
		if(!checkNumber(topPrice, 'positive', '报价金额')){
			SAVING_FLAG = false;return;
		}
		topPrice = parseInt(topPrice);
		$('#topPrice').val(topPrice);
		var promoteUserSerial = $("#promoteUserSerial").val();
		paramObj.offerPrice = topPrice;
		paramObj.promoteUserSerial = promoteUserSerial;
		paramObj.offerAt = formatDate(new Date(),1,1);
		addOrUpdateAutoOffer(paramObj);
	}
}

function addOrUpdateAutoOffer(paramObj){
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addGoodsOffers',paramObj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		$('#offerWin').hide();
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				//window.location.reload();
				lh.alert('已经成功进行报价，请等待卖家回复','reload()');
			}else{
				if(rsp.noPayPassword == 'noPayPassword'){
					lh.alert(rsp.msg,"jumpToPayPasswordFind()");
				}else if(rsp.noPhone == 'noPhone'){
					lh.alert(rsp.msg,"jumpToBindUserPhone()");
				}else{
					lh.alert(rsp.msg);
				}
			}
		}
	},'json')
}

function reload(){
	window.location.reload();
}

function addCollectGoods(id){
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateCollectGoods',{goodsId:id},function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				$('#AuctionCollectionLeft').empty();
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}


function cancelCollectGoods(id){
	$.post('/deleteCollectGoods',{id:id},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				$('#AuctionCollectionLeft').empty();
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

