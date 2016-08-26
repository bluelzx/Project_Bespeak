UPLOAD_FLAG = true;
CURRENT_POSITION = null;
SAVED_POSITION = null;
CURRENT_PIC_ID = null;
IS_CANCEL = false;
$(function (){
	var shopcoorDinate = $("#shopcoorDinate").val();
 	initMap(shopcoorDinate);
});

function initMap(coordinate){
	if(SAVED_POSITION)coordinate = SAVED_POSITION;
	if(IS_CANCEL)coordinate = '';
	CURRENT_POSITION = null;
	$('#allmap').css('display','');
	$('#togMapId').css('display','none');
	$('#save_tog').css('display','');
	$('#hide_tog').css('display','');
	$('#cancel_tog').css('display','');
	var map = new BMap.Map("allmap");
	if(coordinate){
		co = coordinate.split(',');
		x = Number(co[0]);
		y = Number(co[1]);
		var point = new BMap.Point(x,y);
	}
	if (!point) {
		var point = new BMap.Point(116.404, 39.915);// 初始化地图,设置中心点坐标和地图级别
	}
	map.centerAndZoom(point, 7);
	 // 添加带有定位的导航控件
	 var navigationControl = new BMap.NavigationControl({
	    // 靠左上角位置
	    anchor: BMAP_ANCHOR_TOP_LEFT,
	    // LARGE类型
	    type: BMAP_NAVIGATION_CONTROL_LARGE,
	    // 启用显示定位
	    enableGeolocation: true
	});
	map.addControl(navigationControl);
	var marker = new BMap.Marker(point);// 创建标注
	map.addOverlay(marker);             // 将标注添加到地图中
	marker.enableDragging(); //可以拖拽
	map.addOverlay(marker);
	map.enableScrollWheelZoom(true);//缩放
	marker.addEventListener("dragend",getAttr);
	function getAttr(){
		var p = marker.getPosition();       //获取marker的位置
		CURRENT_POSITION = p;
	}
}

function saveTog(id){
	if(CURRENT_POSITION&&id){
		var p = CURRENT_POSITION       //获取marker的位置
		var coordinate = p.lng + "," + p.lat;
//		alert(coordinate);
		$.post('/shop/saveOrCanelCoordinate',{type:1,id:id,coordinate:coordinate},function(result){
			if(result.status){
				SAVED_POSITION = coordinate;
				IS_CANCEL = false;
				alert("已成功标注地图");
				hideTog();
			}else{
				alert(result.msg);
			}
		});
		
	}
}

function cancelTog(id){
	$.post('/shop/saveOrCanelCoordinate',{type:2,id:id,coordinate:''},function(result){
		if(result.status){
			SAVED_POSITION = null;
			IS_CANCEL = true;
			alert("已成功取消标注地图");
			hideTog();
		}else{
			alert(result.msg);
		}
	});
}

function hideTog(){
	$('#allmap').css('display','none');
	$('#togMapId').css('display','');
	$('#save_tog').css('display','none');
	$('#hide_tog').css('display','none');
	$('#cancel_tog').css('display','none');
}

