var pageNum=10;
$(function() {
	init();
	//初始执行查询一次
	var jishu="";
	if(jishu==""||jishu==null){
		getMainDataList("course_yuesao");
		jishu="have";
	}
})

function init(){
	 var mySwiper = new Swiper('.swiper-container',{
         slidesPerView : 5.4,
         freeMode : true,
         freeModeMomentum : false,
     })
	 $('.swiper-b').each(function (i) {
        $(this).click(function () {
            $('.swiper-b').removeClass('choice-on').eq(i).addClass('choice-on');
            var id = $('.swiper-b').eq(i).attr('id');
//            alert(id);
            +
            $('#data-container').empty();   //清空
            getMainDataList(id);
        })
        
    })
    //回到顶部
    $(window).scroll(function () {
        var current = $(window).scrollTop();
        if (current > 300){
			$("#go-top").show();
		} else {
            $("#go-top").hide();
        }
    })
    $("#go-top").click(function () {
        $(window).scrollTop(0);
    });
	 
	
}

function getMainDataList(amFlag){
	if(!amFlag){amFlag = lh.current.amFlag;}
//	alert(amFlag);
	var typeCode='course_wu';
	if(amFlag=="course_jishi"){
	   typeCode="course_jishi";
	}else if(amFlag=="course_yuesao"){
	   typeCode='course_yuesao';
	}else if(amFlag=="course_jiazhen"){
		typeCode='course_jiazhen';
	}else if(amFlag=="course_qita"){
		typeCode='course_qita';
	}
//	 if(amFlag != lh.current.amFlag){
//		 lh.page.currentPage = 1;
//		 lh.current.amFlag = amFlag;
//	 }
//	 typeCode:typeCode
	var param = {page:lh.page.currentPage, rows:pageNum, typeCode:typeCode};
	lh.post('front', '/getCourseLists', param, function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
//				alert(data[0].attrStr);
				makeMainListDom(data, false);
//				lh.page.currentPage ++;
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
	var template = $('#template').html();//初始化装配页面
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {//装配页面与｛｝里返回数据同步
		rows : mainList,
		picsDom:function(){
			var picPaths = this.picPaths;
			if(!picPaths)return '';
			var pics = picPaths.split(',');
			var length = pics.length;
			//if(length > 9)length = 9;
			var picsDom = '';
			for(var i=0;i<length;i++){
				var pic = pics[i];
				pic += buildOSSZoom(100, 100); //@@_OSS_IMG_@@
				picsDom += '<li><img src="'+pic+'" width="100" /></li>';
			}
			return picsDom;
		},
		//参数自定义
		date:function(){
			var createdAt = this.createdAt;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		},
		startdate:function(){//名字不和row字段名相同
			var startTime = this.startTime;
			startTime = lh.formatDate({date:new Date(startTime), flag:'date'});
			return startTime;
		},
		enddate:function(){//名字不和row字段名相同
			var endTime = this.endTime;
			endTime = lh.formatDate({date:new Date(endTime), flag:'date'});
			return endTime;
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
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}

