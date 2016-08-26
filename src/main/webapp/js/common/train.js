
$(function(){
    getMainDataList('all');
    //切换培训
    $('.train-ul li').each(function (i) {
        $(this).click(function () {
            $('.train-ul li').removeClass('choice-on').eq(i).addClass('choice-on');
            $('#data-container').empty();
            getMainDataList('all');
        })
    });
});

//处理数据
function getMainDataList(statusFlag){
    var rsp = {
        success : 'success',
        total : 100,
        rows :
        [
            {id:'1',Url:'#',src:'images/front/jieshao.png',title:'孕妇及产后护理',trainInf:'十月怀胎经历了生产，孕妇护理变得格外重要...'},
        ]
    }
    if(rsp.success) {
        var data = rsp.rows;
        if(data && data.length > 0){
            makeDataListDom(rsp,true);
        }
        else{
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