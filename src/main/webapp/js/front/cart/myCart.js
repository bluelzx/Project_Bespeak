/**初始化数据*/
SAVING_FLAG = false;
CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 10;
$(function(){
	loadCartList();
	lh.scrollBottom(loadCartList);
	
});


function loadCartList(){
	var param = {};
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	param.mainStatus = 1;
	$.post('/getCartList',param,function(rsp){
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.success){
				var count = rsp.total;
				if(count && count > 0){
					makeCartListDom(rsp.rows,1);
					CURRENT_PAGE++ ;
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

function makeCartListDom(cartList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:cartList});
	if(isAppend){
		$('#cartList').append(rendered);
	}else{
		$('#cartList').html(rendered);
	}
}


/**数量加减**/
function add(flag,goodsId,remainNumber){
	var goodsNumber = $("#goodsNumber"+goodsId).val();
	if(flag){
		var num = ++goodsNumber;
		if(num > remainNumber){
			lh.alert('商品数量已达到库存数量');
			$("#goodsNumber"+goodsId).val(remainNumber);
			return;
		}
		$("#goodsNumber"+goodsId).val(num);
	}else{
		var num = --goodsNumber;
		if(num <= 0){
			lh.alert('商品数量至少为1件');
			$("#goodsNumber"+goodsId).val(1);
			return;
		}
		$("#goodsNumber"+goodsId).val(num);
	}
}

function removeCartGoods(id){
	$.post('/deleteCartGoods',{id:id},function(rsp){
		if(rsp){
			frontLoginCheck(rsp);
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

function addBatchCartGoods(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var $list = $(".teacher_list");
	var list = [];
	for(var i=0;i<$list.length;i++){
		var obj = {};
		var g = $list[i];
		//var goodsId = g.attr('goodsId');
		var goodsId = g.attributes[0].value;
		//var catId = g.children[0].value;
		var shopId = g.children[0].attributes[0].value;
		var goodsNumber = $("#goodsNumber"+goodsId).val();
		obj.shopId = shopId;
		obj.goodsId = goodsId;
		obj.goodsNumber = goodsNumber;
		list.push(obj);
	}
	if(list.length <= 0 ){
		lh.alert('购物车暂无商品,请前去选购商品');	
		SAVING_FLAG = false;
		return;
	}
	list = JSON.stringify(list);
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addBatchCart', {list:list}, function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);
			if(rsp.status == 'success'){
				window.location.reload();
			}else{
				lh.alert(rsp.msg);	
			}
		}
	},'json')
}


function checkOut(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var $list = $(".teacher_list");
	var list = [];
	for(var i=0;i<$list.length;i++){
		var obj = {};
		var g = $list[i];
		//var goodsId = g.attr('goodsId');
		var goodsId = g.attributes[0].value;
		var cartId = g.childNodes[3].value;
		var shopId = g.children[0].attributes[0].value;
		var goodsNumber = $("#goodsNumber"+goodsId).val();
		var shopPrice = $("#shopPrice"+goodsId).text();
		obj.shopId = shopId;
		obj.id = cartId;
		obj.goodsId = goodsId;
		obj.goodsNumber = goodsNumber;
		obj.shopPrice = shopPrice;
		list.push(obj);
	}
	if(list.length <= 0 ){
		lh.alert('购物车暂无商品,请前去选购商品');	
		SAVING_FLAG = false;
		return;
	}
	list = JSON.stringify(list);
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/cartBatchCheckOut', {list:list}, function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);
			if(rsp.status == 'success'){
				//window.location.href='/waitPayMoney';
				lh.alert(rsp.msg,"reLoad()");
			}else{
				//lh.alert(rsp.obj.msg);	
				lh.alert(rsp.msg);	
			}
		}
	},'json')
}

function reLoad(){
	window.location.reload();
}
