/**初始化数据*/
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;//取多少条数据

//------
var fromUrl = localStorage.getItem("fromUrl");//点击进入公众号的链接地址
var haveJumped = localStorage.getItem("haveJumped");
if(fromUrl && !haveJumped){
	localStorage.removeItem("fromUrl");
	localStorage.setItem('haveJumped', 1);
	var currentUrl = location.href;
	var idx = currentUrl.indexOf('?');
	if(idx>5){
		currentUrl = currentUrl.substring(0,idx);
	}
	var fromUrlBase = fromUrl;
	var idx2 = fromUrlBase.indexOf('?');
	if(idx2>5){
		fromUrlBase = fromUrlBase.substring(0,idx2);
	}
	//lh.alert("currentUrl:"+currentUrl+"---fromUrlBase:"+fromUrlBase);
	if(currentUrl != fromUrlBase)window.location.href = fromUrl;//TODO 后期取消注释
}
//------

$(function(){
	initPage();
	//loadUser();
	//loadCollectGoods();
	loadCollectGoods();
	lh.scrollBottom(loadCollectGoods);
});

function tests(){
	$('#tests').animates('flash');//bounce,
}

function initPage(){
	$('#banner').show();
	initMenu();
	initTopSearch();
	initBanner();
	switchBottomMenu('menu_index');
}

/**加载藏品*/
function loadCollectGoods(){
	var param = {};
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	param.forAuction = 1;
	param.orderBy = "g.bonus_type_id DESC,go.offer_price DESC,g.created_at";
	param.ascOrdesc = "DESC";
	param.orderSellerPrice = 1;
	$.post('/getCollectGoodsList',param,function(rsp){
		if(rsp){
			if(rsp.success){
				var count = rsp.total;
				if(count && count > 0){
					$('#noCollectGoods').hide();
					makeCollectGoodsListDom(rsp.rows,1);
					CURRENT_PAGE++;
				}else{
					if(CURRENT_PAGE == 0){
						$('#noCollectGoods').show();
					}else{
						$('#noCollectGoods').hide();
						$('#resultTip').text('没有更多数据').show();
					}
				}
			}else{
				$('#resultTip').text(rsp.msg).show();
			}
		}
	},'json');
}

function makeCollectGoodsListDom(collectGoodsList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:collectGoodsList});
	if(isAppend){
		$('#collectGoods').append(rendered);
	}else{
		$('#collectGoods').html(rendered);
	}
}

