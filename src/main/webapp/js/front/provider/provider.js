var pageNum = 10;

var EARTH_RADIUS = 6378.137; // 单位KM
var PI = Math.PI;
var lat = localStorage.getItem("latitude");
var lag = localStorage.getItem("longitude");

$(function() {
   loadList();
})

function showError(error) {
	switch (error.code) {
		case error.PERMISSION_DENIED :
			alert("定位失败,用户拒绝请求地理定位");
			break;
		case error.POSITION_UNAVAILABLE :
			alert("定位失败,位置信息是不可用");
			break;
		case error.TIMEOUT :
			alert("定位失败,请求获取用户位置超时");
			break;
		case error.UNKNOWN_ERROR :
			alert("定位失败,定位系统失效");
			break;
	}
}
function showPosition(position) {
	var lat = position.coords.latitude; // 纬度
	var lag = position.coords.longitude; // 经度
	alert(lat + lag);
}

function loadList() {
	lh.post('front', '/getDictList?parentCode=' + "goods_type", function(rsp) {
				if (rsp.success) {
					var data = rsp.rows;
					if (data && data.length > 0) {
						makeMainListDom1(data, false);
					} else {
					}
				} else {
					lh.page.currentPage = 1;
					$('#resultTip').text(rsp.msg).show();
					lh.alert(rsp.msg);
				}
			}, 'json');
}
function init() {
	var mySwiper = new Swiper('.swiper-container', {
				slidesPerView : 5.4,
				freeMode : true,
				freeModeMomentum : false
			});
	$('.swiper-a').each(function(i) {
		$('.swiper-a').eq(0).addClass('choice-on');
		$(this).click(function() {
			$('.swiper-a').removeClass('choice-on').eq(i).addClass('choice-on');
			var id = $('.swiper-a').eq(i).attr('id');
			$('#data-container').empty(); // 清空
			getMainDataList(id, lat, lag);
		})

	})
	if ($('.swiper-a').first()) {
		var initial = $('.swiper-a').eq(0).attr('id');
		getMainDataList(initial);
	}
	// 回到顶部
	$(window).scroll(function() {
				var current = $(window).scrollTop();
				if (current > 300) {
					$("#go-top").show();
				} else {
					$("#go-top").hide();
				}
			})
	$("#go-top").click(function() {
				$(window).scrollTop(0);
			});
}
function getMainDataList(amFlag) {
	if (!amFlag)
		amFlag = lh.current.amFlag;
	var typeCode = amFlag;
	if (amFlag != lh.current.amFlag) {
		lh.page.currentPage = 1;
		lh.current.amFlag = amFlag;
	}
	var param = {
		page : lh.page.currentPage,
		rows : pageNum,
		typeCode : typeCode
	};
	lh.post('front', '/getProviderList', param, function(rsp) {
		if (rsp.success) {
			var data = rsp.rows;
			if (data && data.length > 0) {
				makeMainListDom(data, false);
				// lh.page.currentPage ++;
			} else {
				$('#resultTip').text('没有更多数据').show();
				$(".weui-infinite-scroll").text('没有更多数据');
			}
		} else {
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
				picsDom : function() {
					var picPaths = this.picPaths;
					if (!picPaths)
						return '';
					var pics = picPaths.split(',');
					var length = pics.length;
					// if(length > 9)length = 9;
					var picsDom = '';
					for (var i = 0; i < length; i++) {
						var pic = pics[i];
						pic += buildOSSZoom(100, 100); // @@_OSS_IMG_@@
						picsDom += '<li><img src="' + pic
								+ '" width="100" /></li>';
					}
					return picsDom;
				},
				date : function() {
					var createdAt = this.createdAt;
					createdAt = lh.formatDate({
								date : new Date(createdAt),
								flag : 'datetime'
							});
					return createdAt;
				},
				avatar : function() {
					var avatar = '';
					var pic = this.avatar;
					pic += buildOSSZoom(35, 35);
					avatar += '<img width="30" src="' + pic
							+ '" class="img-responsive center-block">';
					return avatar;
				},
				coorDinateJuli : function() {
					var coorDinate = this.coorDinate;
					var coorDinateJuli = '';
					if (coorDinate && lat != null) {
						co = coorDinate.split(',');
						x = Number(co[0]);
						y = Number(co[1]);
						var s = getGreatCircleDistance(lat, lag, y, x);
					    coorDinateJuli = '<span class="provider-juli">'
								+ s
						+'km</span>';
					} else {
						coorDinateJuli = '<span class="provider-juli">距离未知</span>';
					}
					return coorDinateJuli;
				}
			});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}

function getGreatCircleDistance(lat1, lng1, lat2, lng2) {
	var radLat1 = getRad(lat1);
	var radLat2 = getRad(lat2);

	var a = radLat1 - radLat2;
	var b = getRad(lng1) - getRad(lng2);

	var s = 2
			* Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
					+ Math.cos(radLat1) * Math.cos(radLat2)
					* Math.pow(Math.sin(b / 2), 2)));
	s = s * EARTH_RADIUS;
	s = Math.round(s * 100) / 100.0;
	return s;
}
function getRad(d) {
	return d * PI / 180.0;
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
