
$(function(){
    getMainDataList('all');
    //回到顶部
    $(window).scroll(function () {
        var current = $(window).scrollTop();
        if (current > 300){
            $("#go-top").show();
        }else {
            $("#go-top").hide();
        }
    })
    $("#go-top").click(function () {
        $(window).scrollTop(0);
    });
    //主页服务切换
    $('.ul-nav').each(function (i) {
        $(this).click(function () {
            $('.ul-nav').removeClass('on').eq(i).addClass('on');
            $('#data-container').empty();
            getMainDataList('all');
        })
    })
});
function getMainDataList(statusFlag){
    var rsp = {
        success : 'success',
        total : 100,
        rows :
        [
            {id:'1',serviceName:'足疗',serviceMoneyTime:'188元/90分钟',serviceDiscount:'25',serviceNum:'999',likeNum:'99',serviceInt:'足疗,从下到上唤醒你的身体'},
            {id:'2',serviceName:'按摩',serviceMoneyTime:'108元/90分钟',serviceDiscount:'50',serviceNum:'119',likeNum:'29',serviceInt:'按摩,放松你的身体'},
            {id:'3',serviceName:'按摩',serviceMoneyTime:'108元/90分钟',serviceDiscount:'50',serviceNum:'119',likeNum:'39',serviceInt:'按摩,放松你的身体'},
            {id:'4',serviceName:'按摩',serviceMoneyTime:'108元/90分钟',serviceDiscount:'50',serviceNum:'119',likeNum:'59',serviceInt:'按摩,放松你的身体'},
            {id:'5',serviceName:'按摩',serviceMoneyTime:'108元/90分钟',serviceDiscount:'50',serviceNum:'119',likeNum:'69',serviceInt:'按摩,放松你的身体'},
            {id:'6',serviceName:'按摩',serviceMoneyTime:'108元/90分钟',serviceDiscount:'50',serviceNum:'119',likeNum:'39',serviceInt:'按摩,放松你的身体'},
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
