var pageNum=10;
$(function(){
//    getMainDataList();
//    init();
});
//function init(){
//	var startTimes=$("#startTime").attr("value");
//	startTimes = lh.formatDate({date:new Date(startTimes), flag:'date'});
//	var endTimes=$("#endTime").attr("value");
//	endTimes = lh.formatDate({date:new Date(endTimes), flag:'date'});
//	$("#courtime").html("授课时间："+startTimes+"至"+endTimes);
//}
//function getMainDataList(){
//	var typeCode=$("#typeCode").attr("value");
//	var param = {page:lh.page.currentPage, rows:pageNum,typeCode:typeCode};
//	lh.post('front', '/getShopLists', param, function(rsp) {//加载课程信息
//		if(rsp.success){
//			var data = rsp.rows;
////			alert(data);
//			if(data && data.length>0){
//				makeMainListDom(data, false);
////				lh.page.currentPage ++;
//			}else{
//				$('#comment').html('暂时没有评论').show();
//				$(".weui-infinite-scroll").text('没有更多数据');
//			}
//		}else{
//			lh.page.currentPage = 1;
//			$('#resultTip').text(rsp.msg).show();
//			lh.alert(rsp.msg);
//		}
//	}, 'json');	
//}
//function makeMainListDom(mainList, isAppend) {
//	var template = $('#template').html();
//	Mustache.parse(template); // optional, speeds up future uses
//	var rendered = Mustache.render(template, {
//		rows : mainList,
//		getDate:function(){
//			var createdAt = this.createdAt;
//			createdAt = lh.formatDate({date:new Date(createdAt), flag:'datetime'});
//			return createdAt;
//		},
//		startdate:function(){//名字不和row字段名相同
//			var startTime = this.startTime;
//			startTime = lh.formatDate({date:new Date(startTime), flag:'date'});
//			return startTime;
//		},
//		enddate:function(){//名字不和row字段名相同
//			var endTime = this.endTime;
//			endTime = lh.formatDate({date:new Date(endTime), flag:'date'});
//			return endTime;
//		}	
//	});
//	if (isAppend) {
//		$('#data-container').append(rendered);
//	} else {
//		$('#data-container').html(rendered);
//	}
//}
//添加报名
function addShopApply() {
	var signInUser = $("#signIn-user").val();//链接的课程id
	var signInPepole = $("#signIn-pepole").val();
	var jmPhone = $("#jm-phone").val();
	var jmdizhi = $("#jm-dizhi").val();
	var jmzj =$("#jm-zj").attr('src');
	//attrInt,attrInt2省市
	//js校验
	// 加盟商名字：
	if(!signInUser || signInUser.length<2 || signInUser.length>30){
		lh.alert('请输入正确加盟店名，字数在2至30个之间');return;
	}
	//联系人
	if(!signInPepole || signInPepole.length<2 || signInPepole.length>12){
		lh.alert('请输入正确联系人名，字数在2至12个之间');return;
	}
	//电话号码
	if(!jmPhone || (jmPhone.length!=8 && jmPhone.length!=11)){
		lh.alert('请输入正确电话号码(不加区号)');return;
	}
	//加盟地址
	if(!jmdizhi || jmdizhi.length<2 || jmdizhi.length>30){
		lh.alert('请输入正确地址，字数在2至30个之间');return;
	}
//	//加盟证书
//	if(!jmzj || jmzj.length<2 ){
//		lh.alert("请上传正确的证书");return;
//	}
	var param = {titleNames:signInUser,attr4:signInPepole,phone:jmPhone,address:jmdizhi,file1:jmzj};
//	if(filePaths)param.file1 = filePaths;
	lh.post('front', '/addShopApply', param, function(rsp) {
		if(rsp.success){
			lh.alert("申请加盟商成功，正在等待审核", jumpToShopIndex);
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json');
}
function jumpToShopIndex(){
	lh.jumpR("/shopIndex");
}
