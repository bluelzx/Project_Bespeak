var pageNum=10;
$(function(){
    getMainDataList('index-tuijian');
    getLocation();
    loadList();
});

function getLocation(){ 
    if (navigator.geolocation){ 
        navigator.geolocation.getCurrentPosition(showPosition,showError); 
    }else{ 
        alert("浏览器不支持地理定位。"); 
    } 
} 
function showError(error){ 
    switch(error.code) { 
        case error.PERMISSION_DENIED: 
            alert("定位失败,用户拒绝请求地理定位"); 
            break; 
        case error.POSITION_UNAVAILABLE: 
            alert("定位失败,位置信息是不可用"); 
            break; 
        case error.TIMEOUT: 
            alert("定位失败,请求获取用户位置超时"); 
            break; 
        case error.UNKNOWN_ERROR: 
            alert("定位失败,定位系统失效"); 
            break; 
    } 
} 
//function showPosition(position){ 
//    var lat = position.coords.latitude; // 纬度
//    var lag = position.coords.longitude; // 经度
//    alert('纬度:'+lat+',经度:'+lag); 
//} 
function showPosition(position){ 
    var latlon = position.coords.latitude+','+position.coords.longitude; 
    // baidu
    var url = "http://api.map.baidu.com/geocoder/v2/?ak=C93b5178d7a8ebdb830b9b557abce78b&callback=renderReverse&location="+latlon+"&output=json&pois=0"; 
    $.ajax({  
        type: "GET",  
        dataType: "jsonp",  
        url: url, 
        beforeSend: function(){ 
            //lh.alert('正在定位...');
        }, 
        success: function (json) {  
            if(json.status==0){ 
//                lh.alert(json.result.formatted_address);
                  var city = json.result.addressComponent.city;
                $("#citys").text(city);
  		        var lat = position.coords.latitude; // 纬度
    		    var lag = position.coords.longitude; // 经度
//    		    alert('纬度:'+lat+',经度:'+lag); 
    		    localStorage.setItem("latitude",lat);
    		    localStorage.setItem("longitude",lag);
    		    
            } 
        }, 
        error: function (XMLHttpRequest, textStatus, errorThrown) {  
            lh.alert(latlon+"地址位置获取失败");
        } 
    }); 
}
//function calcDistance(lat,lag){
//	lh.post('front','',{},function(rsp){
//		
//	});
//}


function loadList(){
	lh.post('front', '/getDictList?parentCode='+"goods_type", function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom1(data, false);
			}else{
			}
		}else{
			lh.page.currentPage = 1;
			$('#resultTip').text(rsp.msg).show();
			lh.alert(rsp.msg);
		}
	}, 'json');	
}
function init(){
	 // 回到顶部
    var mySwiper = new Swiper('.swiper-container',{
            pagination : '.swiper-pagination',
            loop : true,
            autoplay : 4000,
            speed:400,
            autoplayDisableOnInteraction : false,
        });
    var mySwiper2 = new Swiper('.swiper-fixed',{
        slidesPerView : 5.4,
        freeMode : true,
        freeModeMomentum : false,
    })
    $('.swiper-a').each(function (i) {
		 $('.swiper-a').eq(0).addClass('choice-on');
        $(this).click(function () {
           $('.swiper-a').removeClass('choice-on').eq(i).addClass('choice-on');
           var id = $('.swiper-a').eq(i).attr('id');
           $('#data-container').empty();   // 清空
           getMainDataList(id);
       })
       
   })
   if($('.swiper-a').first()){
		 var initial= $('.swiper-a').eq(0).attr('id');
		 getMainDataList(initial);
	 }
    $(window).scroll(function () {
        var current = $(window).scrollTop();
        if (current > 300){
            $("#go-top").show();
        }else {
            $("#go-top").hide();
        }
    })
    $("#go-top").click(function () {
        $(window).scrollTop(0);
    });
    // 主页服务切换
    $('.ul-nav').each(function (i) {
        $(this).click(function () {
            $('.ul-nav').removeClass('on').eq(i).addClass('on');
            var id = $('.ul-nav').eq(i).attr('id');
            $('#data-container').empty();
            getMainDataList(id);
        })
    })
}
function getMainDataList(amFlag){
	if(!amFlag)amFlag = lh.current.amFlag;
	var  typeCode;
	var  typeCode= amFlag;
	 if(amFlag != lh.current.amFlag){
		 lh.page.currentPage = 1;
		 lh.current.amFlag = amFlag;
	 }
	var param = {page:lh.page.currentPage, rows:pageNum,typeCode:typeCode};
	lh.post('front', '/getGoodsList', param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom(data, false);
// lh.page.currentPage ++;
			}else{
				$('#data-container').html("暂时无服务");
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
		picsDom:function(){
			var picPaths = this.picPaths;
			if(!picPaths)return '';
			var pics = picPaths.split(',');
			var length = pics.length;
			// if(length > 9)length = 9;
			var picsDom = '';
			for(var i=0;i<length;i++){
				var pic = pics[i];
				pic += buildOSSZoom(100, 100); // @@_OSS_IMG_@@
				picsDom += '<li><img src="'+pic+'" width="100" /></li>';
			}
			return picsDom;
		},
		date:function(){
			var createdAt = this.createdAt;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		picPath:function(){
			var avatar = '';
			var pic = this.picPath;
			pic += buildOSSZoom(35,35);
			avatar += '<img width="30" src="'+pic+'" class="img-responsive center-block">';
			return avatar;
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
		rows : mainList	
	});
	if (isAppend) {
		$('#data-container1').append(rendered);
		
	} else {
		$('#data-container1').html(rendered);
		init();
	}
}
