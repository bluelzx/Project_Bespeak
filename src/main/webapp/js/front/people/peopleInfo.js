var pageNum=10;
$(function(){
    getMainDataList();
    init();
});
function init(){
	var createdAt=$("#createdAt").attr("value");
	createdAt = lh.formatDate({date:new Date(createdAt), flag:'date'});
	$(".c-month-time").html("注册日期："+createdAt);
	var isRealAuth=$("#isRealAuth").attr("value");
	if(isRealAuth==2){
	$(".provider-zz").html("已认证");
	}else{	
	}
	var titleCode=$("#titleCode").attr("value");
	
//	var sex=$("#peoplesex").attr("value");
//	if(sex==2){
//	$(".c-month-xb").html("性别:女");
//	}else if(sex==1){	
//	$(".c-month-xb").html("性别:男");
//	}
}
function getMainDataList(){
	var typeCode=$("#typeCode").attr("value");
	var peopleid=$("#peopleid").attr("value");
	//,typeCode:typeCode
	var param = {page:lh.page.currentPage, rows:pageNum,id:peopleid};
	lh.post('front', '/getPeopleLists', param, function(rsp) {//加载月嫂信息
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
//			lh.alert(rsp.msg);
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
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
