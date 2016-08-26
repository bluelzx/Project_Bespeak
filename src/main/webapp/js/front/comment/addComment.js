$(function(){
    init();
});
var comment_rank1;
var comment_rank2;
var comment_rank;
function init(){
	var createdAt=$("#orderInfoCreatedAt").attr("value");
	createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
	$(".pj-time").html("下单时间："+createdAt);
	   function star(choiceName) {
	        var choice = $(choiceName+' img');
	        choice.each(function (i) {
	            $(this).click(function () {
	                clear();
	                choice.slice(0,i+1).attr('src','/images/front/pjx-on.png');
	                if(choiceName=='.choice-pj1'){
	                	 comment_rank2=i+1;
	                }else if(choiceName=='.choice-pj2'){
	                	 comment_rank3=i+1;
	                }else if(choiceName=='.choice-pj3'){
	                	 comment_rank=i+1;
	            }
	            })
	        });
	        function clear() {
	            choice.attr('src','/images/front/pjx.png');
	        }
	    }
	    var num1 = '.choice-pj1';
	    var num2 = '.choice-pj2';
	    var num3 = '.choice-pj3';
	    star(num1);
	    star(num2);
	    star(num3)
}
function addComment(){
	var goodsId=$("#goodsId").val();
	var providerId=$("#providerId").val();
	var comment=$("#pj-input").val();
	var orderInfoId=$("#orderInfoId").val();
	if(!comment_rank){
		lh.alert('请选择综合评分');
		return;
	}
	if(!comment_rank2){
		lh.alert('请选择服务评分');
		return;
	}
	if(!comment_rank3){
		lh.alert('请选择技师评分');
		return;
	}
	var param={goodsId:goodsId,providerId:providerId,comment:comment,orderInfoId:orderInfoId,
			commentRank3:comment_rank3,commentRank2:comment_rank2,commentRank:comment_rank};
	lh.post('front', '/addOrUpdateComment', param, function(rsp) {
		if(rsp.success){
			lh.alert("提交评论成功");
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json');	
}