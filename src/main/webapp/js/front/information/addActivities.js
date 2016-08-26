SAVING_FLAG = false;
$(function(){
	initUploadSimple({showDelBtn:true,showEdBtns:true});//初始化上传组件
	initData();
});

function initData(){
	/*$("#datetimepicker").datetimepicker({
        format: "yyyy-MM-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        autoclose:true,
        minView:2,
        todayHighlight:true,
        keyboardNavigation:false
    });
	$("#datetimepicker1").datetimepicker({
        format: "yyyy-MM-dd",
        autoclose: true,
        todayBtn: true,
        language:'zh-CN',
        autoclose:true,
        minView:2,
        todayHighlight:true,
        keyboardNavigation:false
    });
   $(".datetimepicker").hide();*/
	//var date = new Date();
	//date.setMonth(date.getMonth()+1)
	var date2 = new Date();
	date2.setHours(date2.getHours()+1);
	$("#startDate").mobiscroll().date({  
        theme: "android-ics light",  
        lang: "zh",  
        cancelText: '取消',  
        dateFormat: 'yy-mm-dd', //返回结果格式化为年月格式  
        dateOrder: 'yymmdd',
        //timeFormat: 'HH:ii:ss', //返回结果格式化为年月格式  
        //maxDate: date,
        minDate: date2,
        stepSecond:10,
        headerText: function (valueText) { //自定义弹出框头部格式  
            return '请选择活动开始时间';  
        }  
    });  
	$("#endDate").mobiscroll().date({  
        theme: "android-ics light",  
        lang: "zh",  
        cancelText: '取消',  
        dateFormat: 'yy-mm-dd', //返回结果格式化为年月格式  
        dateOrder: 'yymmdd',
        //timeFormat: 'HH:ii:ss', //返回结果格式化为年月格式  
        //maxDate: date,
        minDate: date2,
        stepSecond:10,
        headerText: function (valueText) { //自定义弹出框头部格式  
            return '请选择活动结束时间';  
        }  
    });  
}

/*function startDate(){
	$('#datetimepicker > div').show();
    $('#datetimepicker').datetimepicker().on('changeDate', function(ev){
	    if (ev.date){
	    	$("#startDate").val(formatDate(ev.date));
	    	$('#datetimepicker > div').hide();
	    }else{
	    	lh.alert('请选择开始时间');
	    }
	});
}

function endDate(){
	$('#datetimepicker1 > div').show();
    $('#datetimepicker1').datetimepicker().on('changeDate', function(ev){
	    if (ev.date){
	    	$("#endDate").val(formatDate(ev.date));
	    	$('#datetimepicker1 > div').hide();
	    }else{
	    	lh.alert('请选择开始时间');
	    }
	});
}*/

function addActivities(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var title = $("#title").val();
	var description = $("#description").val();
	var institution = $("#institution").val();
	var address = $("#address").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var content = $("#content").val();
	var filePaths = $("#filePaths").val();
	var fileDBIds = $("#fileDBIds").val();
	filePaths = filePaths.substring(1).split(",");
	fileDBIds = fileDBIds.substring(1).split(",");
	if(!title){lh.alert('请填写活动标题');SAVING_FLAG = false;return;}
	if(!description){lh.alert('请填写活动描述');SAVING_FLAG = false;return;}
	if(!institution){lh.alert('请填写活动公司');SAVING_FLAG = false;return;}
	if(!address){lh.alert('请填写活动公司地址');SAVING_FLAG = false;return;}
	if(!startDate){lh.alert('请填写活动开始时间');SAVING_FLAG = false;return;}
	if(!endDate){lh.alert('请填写活动结束时间');SAVING_FLAG = false;return;}
	if(!content){lh.alert('请填写活动内容');SAVING_FLAG = false;return;}
	if(filePaths.length > 2){
		lh.alert('请上传一张活动封面');
		SAVING_FLAG = false;
		return;
	}
	var obj = {};
	obj.typeId = 41;
	obj.title = title;
	obj.description = description;
	obj.institution = institution;
	obj.address = address;
	obj.startDate = startDate;
	obj.endDate = endDate;
	obj.content = content;
	obj.picPath = filePaths[0];
	obj.picPathIds = fileDBIds[0];
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateArticle',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				frontBaseConfirm('您的活动申请已提交,管理员审核通过后即可展示出来.','jumpToActivity()');
				//window.location.href='/activity';
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json');
}

function jumpToActivity(){
	var url = '/activity';
	var r = $("#r").val();
	if(r)url += "?r="+r
	window.location.href=url;
}
