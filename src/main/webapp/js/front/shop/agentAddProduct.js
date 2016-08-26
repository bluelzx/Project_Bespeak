SAVING_FLAG = false;
OBJ = null;
$(function(){
	/*$("#serviceType,#productType").select2({
		tags: "true",
		placeholder: "",
		allowClear: true
	});*/
	$("#serviceType,#productType").selectpicker({
	    //style: 'btn-info',
		//actionsBox:true,
		//header:'请选择',
	    showTick:true,
	    size: 10
	});
});
function addApply(){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var serviceTypeId = $("#serviceType").val();
	var productTypeId = $("#productType").val();
	var goodsNum = $("#goodsNum").val();
	var userPhone = $("#userPhone").val();
	var userAddress = $("#userAddress").val();
	var platformPhone = $("#platformPhone").val();
	var totalMoney = $("#totalMoney").val();
	if(!serviceTypeId){lh.alert('请选择服务方式');SAVING_FLAG = false;return;}
	if(!productTypeId){lh.alert('请选择产品类型');SAVING_FLAG = false;return;}
	if(!goodsNum){lh.alert('请填写产品件数');SAVING_FLAG = false;return;}
	if(!num){lh.alert('请填写每件产品拍摄张数');SAVING_FLAG = false;return;}
	var obj = {applyType:97,attr1:serviceTypeId,attr2:productTypeId,attr3:goodsNum,attr4:num
	,file1:userPhone,file2:userAddress,file3:platformPhone,file4:totalMoney};
	OBJ = obj;
	$("#offerWin").show();
}

function doAutoOffer(flag){
	if(flag == 'cancel'){
		SAVING_FLAG = false;
		$("#offerWin").hide();
	}else{
		var payPass = $("#payPass").val();
		if(!payPass){
			lh.alert('请输入支付密码');
			SAVING_FLAG = false;
			return;
		}
		OBJ.payPass = payPass;
		addData(OBJ);
	}
}

function addData(obj){
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateApply',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				//window.location.reload();	
				frontBaseConfirm('您的申请提交成功!','reload()');
			}else{
				lh.alert(rsp.msg);
			}
		}
	},'json')
}

function reload(){
	window.location.reload();
}