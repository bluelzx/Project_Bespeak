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
//添加报名
function addInvestigation() {
	var courseId = $("#courseId").val();//链接的课程id
	var coursename = $("#coursename").val();
	var coursecity = $("#coursecity").val();
	var InvestigationName = $("#px-yy-name").val();
	var InvestigationSex = $("#px-yy-xb").val();
	var InvestigationOld = $("#px-yy-old").val();
	var InvestigationPhone = $("#px-yy-phone").val();
	//attrInt,attrInt2省市
	//js校验
	//名字：
	if(!InvestigationName || InvestigationName.length<2 || InvestigationName.length>12){
		lh.alert('请输入正确姓名，字数在2至12个之间');return;
	}
	//性别：
	if(!InvestigationSex){
		lh.alert('请选择性别！');return;
	}
	else if(InvestigationSex<1 || InvestigationSex>2){
		lh.alert('请选择正确性别！');return;
	}
	//电话号码
	if(!InvestigationPhone || (InvestigationPhone.length!=8 && InvestigationPhone.length!=11)){
		lh.alert('请输入正确电话号码(不加区号)');return;
	}
	//年龄
	if(!InvestigationOld || InvestigationOld<10 || InvestigationOld>150){
		lh.alert('请输入正确年龄');return;
	}
	
	
	var param = {courseName:coursename,username:InvestigationName,userSex:InvestigationSex,userAge:InvestigationOld,userPhone:InvestigationPhone,linkId:courseId};
//	if(filePaths)param.file1 = filePaths;
	lh.post('front', '/addInvestigation', param, function(rsp) {
		if(rsp.success){
			lh.alert("报名培训成功，正在等待审核", jumpToCourseIndex);
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json');
}
function jumpToCourseIndex(){
	lh.jumpR("/courseIndex");
}
