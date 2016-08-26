/**初始化数据*/
SAVING_FLAG = false;
CURRENT_PAGE = 1;//当前页数
CURRENT_AUCTIONINST_PAGE = 1;
PAGE_COUNT = 10;
PARAM = {};
$(function(){
	loadGoodsList();
	lh.scrollBottom(loadGoodsList);
	lh.scrollBottom(loadInstList);
});


function loadGoodsList(){
	var shopId = $("#shopId").val();
	var moduleId = $("#moduleId").val();
	var userId = $("#userId").val();
	var param = {};
	param.shopId = shopId;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	if(moduleId && moduleId == 5){
		//param.shopId = shopId;
		//param.currentUserId = userId;
		//param.wholesaleId = 1;
		//param.noCopy = 1;
		//param.noCopyShopId = shopId;
		param.allWholesaleGoods = 1;
		param.moduleId = moduleId;
	}else{
		param.forAuction = 1;
	}
	$.post('/getGoodsList',param,function(rsp){
		frontLoginCheck(rsp);//登陆检查
		if(rsp.success){
			var count = rsp.total;
			if(count && count > 0){
				makeGoodsListDom(rsp.rows,1);
				CURRENT_PAGE++ ;
			}else{
				$('#resultTip').text('没有更多数据').show();
			}
		}else{
			lh.alert(rsp.msg);
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}


function makeGoodsListDom(goodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	/*for(var a in goodsList){
		if(goodsList[a].goodsPrivilege){
			if(goodsList[a].goodsPrivilege == 2){
				goodsList[a].goodsPrivilegeBtn = "<button  type='button' class='btn btn-info' onclick='allowCopy("+goodsList[a].id+");return false;'>允许复制</button>";
			}else if(goodsList[a].goodsPrivilege == 1){
				goodsList[a].goodsPrivilegeBtn = "<button  type='button' class='btn btn-info' onclick='noAllowCopy("+goodsList[a].id+");return false;'>不允许复制</button>";
			}
		}
	}*/
	var rendered = Mustache.render(template, {rows:goodsList});
	if(isAppend){
		$('#goodsList').append(rendered);
	}else{
		$('#goodsList').html(rendered);
	}
}



function allowCopy(id){
	$.post('/allowCopy',{id:id},function(rsp){
		if(rsp){
			if(rsp.status == 'success'){
				lh.alert(rsp.msg);
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}
function noAllowCopy(id){
	$.post('/noAllowCopy',{id:id},function(rsp){
		if(rsp){
			if(rsp.status == 'success'){
				lh.alert(rsp.msg);
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function loadInstList(){
	var param = {};
	param.rows = PAGE_COUNT;
	param.page = CURRENT_AUCTIONINST_PAGE;
	$.post('/getAuctionInstList',param,function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.success){
				var count = rsp.rows.length;
				if(count && count > 0){
					makeInstListDom(rsp.rows,1);
					CURRENT_AUCTIONINST_PAGE++ ;
				}else{
					$('#resultTip').text('没有更多数据').show();
				}
			}else{
				lh.alert(rsp.msg);
			}
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}

function makeInstListDom(instList,isAppend){
	var template = $('#template1').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:instList});
	if(isAppend){
		$('#instList').append(rendered);
	}else{
		$('#instList').html(rendered);
	}
}



function goodsUndercarriage(id){
	$.post('/addOrUpdateGoods',{id:id,mainStatus:75},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

function auctionGoodsId(id){
	$("#Inst").show();
	loadInstList();
	PARAM.id = id;
}

function getInstId(id){
	PARAM.LinkId = id;
}

function auctionGoods(){
	PARAM.mainStatus = 74;
	PARAM.LinkType = 1;
	$.post('/addOrUpdateGoods',PARAM,function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

function deleteGoods(id){
	frontBaseConfirm('是否删除商品.','deleted('+id+')');
}

function deleted(id){
	$.post('/deleteGoods',{id:id},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

function editGoods(id){
	window.location.href="/editGoods/"+id;
}



