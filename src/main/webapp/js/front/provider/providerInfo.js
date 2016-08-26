var pageNum=10;
$(function(){
    getMainDataList();
    init();
});
function init(){
	  if($('.swiper-a').first()){
			 var initial= $('.swiper-a').eq(0).attr('id');
			 getMainDataList1(initial);
		 }
	 var mySwiper = new Swiper('.swiper-container',{
         slidesPerView : 5.4,
         freeMode : true,
         freeModeMomentum : false,
     });
	 $('.swiper-a').each(function (i) {
	        $(this).click(function () {
	            $('.swiper-a').removeClass('choice-on').eq(i).addClass('choice-on');
	            var id = $('.swiper-a').eq(i).attr('id');
	            $('#data-container1').empty();   //清空
	            getMainDataList1(id);
	        })
	    })
}
function getMainDataList(){
	var receiverId=$("#providerId").attr("value");
	var typeCode=$("#typeCode").val();
	var typeAry=typeCode.split(',');
	var typeNames=$("#typeNames").val();
	var typeNameAry=typeNames.split(',');
	for(var i in typeAry){
		var str="<div class='swiper-slide swiper-a' id='"+typeAry[i]+"'>"+typeNameAry[i]+"</div>";
		$("#data-container2").append(str);
	}
	$('.swiper-a').eq(0).addClass('choice-on');
	
	var param = {page:lh.page.currentPage, rows:pageNum,receiverId:receiverId};
	lh.post('front', '/getCommentList', param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom(data, false);
//				lh.page.currentPage ++;
			}else{
				$('#comment').html('暂时没有评论').show();
			}
		}else{
			lh.page.currentPage = 1;
			$('#resultTip').text(rsp.msg).show();
			lh.alert(rsp.msg);
		}
	}, 'json');	
}
function getMainDataList1(amFlag){
	if(!amFlag)amFlag = lh.current.amFlag;
	var  typeCode= amFlag;
	 if(amFlag != lh.current.amFlag){
		 lh.page.currentPage = 1;
		 lh.current.amFlag = amFlag;
	 }
	var receiverId=$("#providerId").attr("value");
	var shopId=$("#shopId").attr("value");
	
	var param = {page:lh.page.currentPage, rows:pageNum,shopId:shopId,typeCode:typeCode};
	lh.post('front', '/getGoodsList', param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom1(data, false);
//				lh.page.currentPage ++;
			}else{
				$('#data-container1').html("暂时无服务");
//				$(".weui-infinite-scroll").text('没有更多数据');
			}
		}else{
			lh.page.currentPage = 1;
//			$('#resultTip').text(rsp.msg).show();
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
function makeMainListDom1(mainList, isAppend) {
	var template = $('#template1').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		getDate:function(){
			var createdAt = this.createdAt;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		avatar:function(){
			var avatar = '';
			var pic = this.avatar;
			pic += buildOSSZoom(35,35);
			avatar += '<img width="30" src="'+pic+'" class="img-responsive center-block">';
			return avatar;
		}	
	});
	if (isAppend) {
		$('#data-container1').append(rendered);
	} else {
		$('#data-container1').html(rendered);
	}
}
//关注
function updataFocus(){
	var focusNum=$("#focusNum").html();
	var providerId=$("#providerId").val();
	var praiseType=1;
	var userId=$("#userId").val();
	var param = {praiseId:providerId,praiseType:praiseType,userId:userId};

	lh.post('front', '/addUserPraise', param, function(rsp) {
		if(rsp.success){
			var flag=rsp.flag;
			if(flag){
				focusNum=parseInt(focusNum)+1;
				$("#focusNum").html(focusNum);
			}else{
				focusNum=focusNum-1;
				$("#focusNum").html(focusNum);
				lh.post('front', '/deleteUserPraise',param,  function(rsp) {
				}, 'json');
			}
			lh.post('front', '/updataFocusNum', {id:providerId,focusNum:focusNum}, function(rsp) {
			}, 'json');	
		}else{
		}
	}, 'json');	
}