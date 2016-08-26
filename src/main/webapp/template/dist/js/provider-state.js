
$(function(){
    getMainDataList('all');

});
function getMainDataList(statusFlag){
    var rsp = {
        success : 'success',
        total : 100,
        rows :
        [
            {id:'1',serviceName:'足疗',serviceInt:'足疗,从下到上足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体足疗,从下到上唤醒你的身体唤醒你的身体',state:'上架',providerState:'stateOk'},
            {id:'2',serviceName:'按摩',serviceInt:'按摩,放松你的身体',state:'下架',providerState:'stateOk'},
            {id:'3',serviceName:'按摩',serviceInt:'按摩,放松你的身体',state:'审核中',providerState:'stateNo'},
            {id:'4',serviceName:'按摩',serviceInt:'按摩,放松你的身体',state:'审核失败',providerState:'statesh'},
        ]
    }
    if(rsp.success) {
        var data = rsp.rows;
        if(data && data.length > 0){
            makeDataListDom(rsp,true);
        } else{
            $('#resultTip').text('没有更多数据').show();
        }
    }else{
            lh.alert(rsp.msg);
    }
}
function makeDataListDom(rsp,isAppend) {
    var data = {rows : rsp.rows};
    var template = $('#template').html();
    Mustache.parse(template);
    var rendered = Mustache.render(template, data);
    if(isAppend){
        $('#data-container').prepend(rendered);
    }else{
        $('#data-container').html(rendered);
    }
}
