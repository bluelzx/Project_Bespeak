var pageNum=10;
var jishu1="";
$(function() {
	//初始执行查询类型
	loadTypeList();
//	setTimeout(function(){init();}, 500);
//	var oDiv = document.getElementById("#data-container1");   //获取元素div
//	oDiv.onclick = function(){   //给元素增加点击事件
//		init1();
//	};
//	oDiv.click();
	//初始执行查询一次
	var jishu="";
	if(jishu==""||jishu==null){
		getMainDataList("people_baomu");
//		 $('.swiper-a').removeClass('choice-on').eq(1).addClass('choice-on');
		jishu="have";
	}
//	$(document).on("click",".train-ul li",function(){
//		if(jishu1==""||jishu1==null){
//			$(this).click(function(){
//			$('.train-ul li').removeClass('choice-on').eq(i).addClass('choice-on');
//			 var id = $('.swiper-a').eq(i).attr('id');
//	          $('#data-container').empty();   //清空    
//	          getMainDataList(id);
//		})}else{
//			$('.train-ul li').eq(0).removeClass('choice-on');
//		}
		
//		$('.train-ul li').each(function(i){
//			$(this).click(function(){
//				$('.train-ul li').removeClass('choice-on').eq(i).addClass('choice-on');
//				 var id = $('.swiper-a').eq(i).attr('id');
//		          $('#data-container').empty();   //清空    
//		          getMainDataList(id);
//			})
//		})
//	});
})
function loadTypeList(){
	lh.post('front', '/getDictList?parentCode='+"people_typeCode", function(rsp) {
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
	 //回到顶部
   var mySwiper = new Swiper('.swiper-container',{
           pagination : '.swiper-pagination',
           loop : true,
           autoplay : 4000,
           speed:400,
           autoplayDisableOnInteraction : false,
       });
//   $(".swiper-a").mouseover(function () {
//	   var index = $(".swiper-a").index();
//	   $('.swiper-a').removeClass('choice-on').eq(index).addClass('choice-on');
//	   alert("我是第"+index+"个li");
//   });
//   $('.swiper-a').each(function (i) {
//	   $('.swiper-a').removeClass('choice-on').eq(i).addClass('choice-on');
//       $(this).click(function () {
//          $('.swiper-a').removeClass('choice-on').eq(i).addClass('choice-on');
//          var id = $('.swiper-a').eq(i).attr('id');
////          alert(id);
//          $('#data-container').empty();   //清空
//          getMainDataList(id);
//      })
//  })
//      if($('.swiper-a').first()){
//		 var initial= $('.swiper-a').eq(0).attr('id');
//		 getMainDataList(initial);
//	  }
}
function init1(){
	$('.swiper-a').each(function (i) {
//			   var index = $(this).index();
		$('.swiper-a').eq(0).addClass('choice-on');
//			   alert("我是第"+index+"个li");
//		 $('.swiper-a').eq(i).addClass('choice-on');
       $(this).click(function () {
          $('.swiper-a').removeClass('choice-on').eq(i).addClass('choice-on');
          var id = $('.swiper-a').eq(i).attr('id');
//          alert(id);
          $('#data-container').empty();   //清空    
          getMainDataList(id);
      })
  })
}
function peoplejump(id){
	location.href = "/people/"+id;
}
function getMainDataList(amFlag){
	if(!amFlag){
//		amFlag=''
		amFlag = lh.current.amFlag;
	}
	 var typeCode= amFlag;
//	 alert(typeCode);
	 if(amFlag != lh.current.amFlag){
		 lh.page.currentPage = 1;
		 lh.current.amFlag = amFlag;
	 }
//	 typeCode:typeCode , 
	//默认只显示可预约的月嫂,mainStatus:1预约状态
	var param = {page:lh.page.currentPage, rows:pageNum, mainStatus:1,typeCode:typeCode};//默认屏蔽不可预约
//	alert(param.typeCode);
	lh.post('front', '/getPeopleLists', param, function(rsp) {
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
		init1();
	} else {
		$('#data-container1').html(rendered);
		init1();
	}
}

