$(function(){
    getMainDataList();

});
//处理数据
function getMainDataList(){
	lh.post('front', '/getArticleList', function(rsp) {
		if(rsp.success){
			var data = rsp.rows;
			if(data && data.length>0){
				makeMainListDom(data, false);
			}else{
				$('#data-container').html("暂时无公益信息");
			}
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json');	
}
function makeMainListDom(mainList, isAppend) {
	var template = $('#template').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
		rows : mainList,
		date:function(){
			var createdAt = this.startDate;
			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
			return createdAt;
		}	
	});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
var mySwiper = new Swiper('.swiper-container',{
    pagination : '.swiper-pagination',
    loop : true,
    autoplay : 4000,
    speed:400,
    autoplayDisableOnInteraction : false,
})