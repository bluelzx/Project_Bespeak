var pageNum=10;
$(function(){
    getMainDataList();
    init();
});
function init(){
	var startTimes=$("#startTime").attr("value");
	startTimes = lh.formatDate({date:new Date(startTimes), flag:'date'});
	var endTimes=$("#endTime").attr("value");
	endTimes = lh.formatDate({date:new Date(endTimes), flag:'date'});
	$("#courtime").html("授课时间："+startTimes+"至"+endTimes);
}
function getMainDataList(){
	var typeCode=$("#typeCode").attr("value");
	var param = {page:lh.page.currentPage, rows:pageNum,typeCode:typeCode};
	lh.post('front', '/getCourseLists', param, function(rsp) {//加载课程信息
		if(rsp.success){
			var data = rsp.rows;
//			alert(data);
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
		startdate:function(){//名字不和row字段名相同
			var startTime = this.startTime;
			startTime = lh.formatDate({date:new Date(startTime), flag:'date'});
			return startTime;
		},
		enddate:function(){//名字不和row字段名相同
			var endTime = this.endTime;
			endTime = lh.formatDate({date:new Date(endTime), flag:'date'});
			return endTime;
		}	
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
