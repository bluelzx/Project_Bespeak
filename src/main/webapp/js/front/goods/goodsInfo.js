var pageNum=10;
$(function(){
    getMainDataList();
    init();
	loadTreatment();
});
function init(){
	var shopPrice=$("#shopPrice").val();
	var timeNum=$("#timeNum").val();
}
var treatmentdata;
function loadTreatment(){
	var goodsId=$("#goodsId").val();
	lh.post('front', '/getTreatment', {goodsId:goodsId}, function(rsp) {
		if(rsp.success){
			 treatmentdata = rsp.rows;
			if(treatmentdata && treatmentdata.length>0){
				makeMainListDom2(treatmentdata, false);
			}else{
				$(".weui-infinite-scroll").text('没有更多数据');
			}
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json');	
}

function getMainDataList(){
	var objectId=$("#goodsId").attr("value");
	var param = {page:lh.page.currentPage, rows:pageNum,objectId:objectId};
	lh.post('front', '/getCommentList', param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom(data, false);
//				lh.page.currentPage ++;
			}else{
				$('#comment').html('暂时没有评论').show();
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
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		getDate:function(){
			var createdAt = this.createdAt;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		stars:function(){
			var stars = '';
			var commentRank3 = this.commentRank3;
			for(var i=0;i<commentRank3;i++){
				stars += '<a href="javascript:" class="star-img"></a>';
			}
			return stars;
		}
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
function makeMainListDom2(mainList, isAppend) {
	var template = $('#template2').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
	});
	if (isAppend) {
		$('#data-container2').append(rendered);
	} else {
		$('#data-container2').html(rendered);
		placrOrder();
	}
}
//关注
function updataFocus(){
	var hits=$("#hits").html();
	var goodsId=$("#goodsId").val();
	var praiseType=2;
	var userId=$("#userId").val();;
	var param = {praiseId:goodsId,praiseType:praiseType,userId:userId};

	lh.post('front', '/addUserPraise', param, function(rsp) {
		if(rsp.success){
			var flag=rsp.flag;
			if(flag){
				hits=parseInt(hits)+1;
				$("#hits").html(hits);
			}else{
				hits=hits-1;
				$("#hits").html(hits);
				lh.post('front', '/deleteUserPraise',param,  function(rsp) {
				}, 'json');
			}
			lh.post('front', '/updatahits', {id:goodsId,hits:hits}, function(rsp) {
			}, 'json');	
		}else{
		}
	}, 'json');	
}
function placrOrder() {
	$('#placeOrderBt').click(function () {
		$('.motai').show();
	})
	$('.orderMiddleShow li').each(function (i) {
		$(this).click(function () {
			$('.orderMiddleShow li').removeClass('placeProviderChoice');
			$('.orderMiddleShow li').eq(i).addClass('placeProviderChoice');
		})
	})
	$('.closeImg').click(function () {
		$('.motai').hide();
	})
	var t = $("#text_box");

	$(".plus").click(function(){
		t.val(parseInt(t.val())+1)
	})
	$(".reduce").click(function(){
		t.val(parseInt(t.val())-1)
		if(t.val()<=1){
			t.val(1);
		}
	})
}
function treatment(id){
	var treatment=_.find(treatmentdata, { 'id': id});
	var groupNum=treatment.groupNum;
	var groupPrice=treatment.groupPrice;
	localStorage.setItem("bespeak_groupNum",groupNum);
	localStorage.setItem("bespeak_groupPrice",groupPrice);
}
function bespeak(){
	var goodsId=$("#goodsId").val();
	var goodsNumber=$("#text_box").val();
	var groupPrice=localStorage.getItem("bespeak_groupPrice");
	var shopPrice=$("#shopPrice").val();
	var total;
	if(groupPrice>0){
		total=groupPrice*goodsNumber;
	}else{
		total=shopPrice*goodsNumber;
	}
	localStorage.setItem("bespeak_total",total);
	localStorage.setItem("bespeak_goodsNumber",goodsNumber);
	lh.jumpR('/goodsBespeak/'+goodsId);
}