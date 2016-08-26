
$(function(){
	//iniPage();
	iniData();
});


function iniPage(){
}

function iniData(){
	var from = lh.param.from;
	if(from){
		lh.param.addGoodsUrl = '/goods/page/add?from='+from;
		if(from == 'am'){//微拍
			lh.param.pageFrom = 'am';
		}else{//专场
			var param = from.split('-');
			lh.param.pageFrom = param[0];
			lh.param.paramSerial = param[1];
		}
	}
	getMyGoodsList('all');
}

function jumpToAddGoods(){
	var url = lh.param.addGoodsUrl || '/goods/page/add';
	lh.jumpR(url);
}

function getMyGoodsList(goodsTypeCode){
	var cache = lh.status.currentGoodsTypeCode;
	if(!cache)cache = goodsTypeCode;
	if(cache != goodsTypeCode){//切换时清空dom，查询第一页数据
		cache = goodsTypeCode;
		lh.page.currentPage = 1;
		$('#data-container').empty();
		$('#goodsType-container div').removeClass('base-active');
		$('#gt_'+cache).addClass('base-active');
	}
	var obj = {page:lh.page.currentPage, rows:lh.page.rows};
	if(cache != 'all')obj.goodsTypeCode = cache;
	if(lh.param.pageFrom)obj.pageFrom = lh.param.pageFrom;
	if(lh.param.paramSerial)obj.paramSerial = lh.param.paramSerial;
	lh.status.currentGoodsTypeCode = cache;
	lh.post('front', '/goods/getMyGoodsList',obj,function(rsp){
		if(rsp.success){
			if(rsp.rows && rsp.rows.length > 0){
				makeAuctionGoodsDom(rsp.rows, true);
				lh.page.currentPage++;
			}
		}else{
			lh.alert(rsp.msg, '提示');
		}
	},'json');
}

function makeAuctionGoodsDom(dataList, isAppend){
	lh.plugins.template = $('#template').html();
	Mustache.parse(lh.plugins.template);   // optional, speeds up future uses
	var data = {
		rows:dataList,
		getPicPath:function(){
			var picPath = this.picPath;
			if(picPath){
				picPath += buildOSSZoom(85,85); //@@_OSS_IMG_@@
			}
			return picPath;
		}
    }
	var rendered = Mustache.render(lh.plugins.template, data);
	if(lh.page.currentPage > 1){
		$('#data-container').append(rendered);
	}else{
		$('#data-container').html(rendered);
	}
}

function chooseGoods(goodsId){
	if(lh.param.pageFrom == 'ap' && lh.param.paramSerial){//专场
		$('#goods_'+goodsId).remove();
		addOrUpdateAGList(lh.param.paramSerial, goodsId);
	}else if(lh.param.pageFrom == 'am'){//微拍
		lh.jumpR('/goods/page/baseUpdate?from=am&goodsId='+goodsId);
	}
}

function addOrUpdateAGList(apSerial, goodsId){
	lh.loading('正在保存数据');//加载遮罩
	var auctionGoodsAry = '[{goodsId:'+goodsId+'}]';
	lh.post('front', '/ap/addOrUpdateAGList', {apSerial:apSerial,auctionGoodsAry:auctionGoodsAry,opt:'add'},function(rsp){
		if(rsp.success){
			lh.confirm('您已成功将拍品添加到专场，是否继续选择？', '提示', null, lh.back);
			var apSerial = rsp.apSerial;
		}else{
			lh.alert(rsp.msg, lh.reload);
		}
	},'json', {requesting:'addGoodsToAP'});
}

