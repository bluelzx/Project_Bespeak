$(function(){
	loadAuctionList();
});

function loadAuctionList(){
	var param = {};
	lh.post('front', '/getAuctionProfessionList', param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom(data, true);
				lh.page.currentPage ++;
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
function makeMainListDom(mainList, isAppend) {
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var data = getData(mainList);
	var rendered = Mustache.render(template, data);
	if(!lh.page.currentPage || lh.page.currentPage == 1){//第一页时为html
		$('#data-container').html(rendered);
	}else{
		$('#data-container').append(rendered);
	}
}
function getData(auctionProfessionList){
	var data = {
		rows:auctionProfessionList,
		statusDom:function(){
			var haveBegun = this.haveBegun || 1;
			//var startTime = this.startTime;
			//startTime = new Date(startTime);
			//var now = new Date().getTime();
			//if(startTime < now)haveBegun = 3;
			var statusDom = '';
			if(haveBegun == 2){
				statusDom = '<a role="button" class="btn btn-xs col-xs-12 color_orange">拍卖中</a>';
			}else if(haveBegun == 1){
				statusDom = '<a role="button" class="btn btn-xs col-xs-12 color_green">预展中</a>';
			}else if(haveBegun == 3){
				statusDom = '<a role="button" class="btn btn-xs col-xs-12 color_gray">已结束</a>';
			}
			return statusDom;
		},
		getPicPath:function(){
			var picPaths = this.picPaths;
			if(picPaths){
				picPaths += buildOSSZoom(420,150); //@@_OSS_IMG_@@ //330,120
			}
			return picPaths;
		},
		/*gradeDom:function(){
			var grade = this.grade;
			var gradeDom = '';
			if(grade && grade > 100){
				var grade = grade.toString();
				var diamond = grade[1];
				var star = grade[2];
				for(var j = 0;j<star;j++){
					gradeDom += '<img src="/images/front/sale_img2.png" width="12" height:"12" class="mgH1 fr"/>';
				}
				for(var i = 0;i<diamond;i++){
					gradeDom += '<img src="/images/front/diamond_blue.png" width="15" height:"15" class="fr"/>';
				}
			}
			return gradeDom;
		},*/
		getStartTime:function(){
			var startTime = this.startTime;
			return lh.formatDate(startTime,1);
		},
		date:function(){
			var createdAt = this.endTime;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		}
    }
	return data;
}

function deleteSchedulers(id){
	alert(id);
	var param = {};
	$('#rows'+id).hide();
}







function showShare(){
	$('#shareMask,#share').show();
}
function hideShare(){
	$('#shareMask,#share').hide();
}