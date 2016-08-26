var pageNum=10;
$(function(){
//    getMainDataList();
//    init();
});
//function init(){}
//function getMainDataList(){}
//function makeMainListDom(mainList, isAppend) {}
//雇佣月嫂预约
function addPeopleApply() {
	var peopleId = $("#peopleid").val();//预约的月嫂id
	var applyname = $("#applyname").val();
	var applyphone = $("#applyphone").val();
	var applyaddress = $("#applyaddress").val();
	var description = $("#description").val();
	//alert(description);
	//attrInt,attrInt2省市
	//js校验
	if(!peopleId){
		lh.alert('未得到正确的预约雇佣对象！');return;
	}
	//名字：
	if(!applyname || applyname.length<2 || applyname.length>12){
		lh.alert('请输入正确姓名，字数在2至12个之间');return;
	}
//	//性别：
//	if(!InvestigationSex){
//		lh.alert('请选择性别！');return;
//	}
//	else if(InvestigationSex<1 || InvestigationSex>2){
//		lh.alert('请选择正确性别！');return;
//	}
	//电话号码
	if(!applyphone || (applyphone.length!=8 && applyphone.length!=11)){
		lh.alert('请输入正确电话号码(不加区号)');return;
	}
	//地址
	if(!applyaddress || applyaddress.length<4 || applyaddress.length>50){
		lh.alert('请输入正确地址！');return;
	}
	//备注
	if(description.length>500){
		lh.alert('备注说明请勿超过500字');return;
	}
	//providerId对接到月嫂id
	var param = {providerId:peopleId,name:applyname,addres:applyaddress,phone:applyphone,remark:description};
//	if(filePaths)param.file1 = filePaths;
	lh.post('front', '/addPeopleApply', param, function(rsp) {
		if(rsp.success){
			lh.alert("预约申请雇佣月嫂信息已提交，请等待月嫂与您联系！", jumpToPeopleIndex);
		}else{
			lh.alert(rsp.msg);
		}
	}, 'json');
}
function jumpToPeopleIndex(){
	lh.jumpR("/peopleIndex");
}
