
$(function(){
    getMainDataList('all');
    //点击切换技师显示
    $('.swiper-a').each(function (i) {
        $(this).click(function () {
            $('.swiper-a').removeClass('choice-on').eq(i).addClass('choice-on');
            $('#data-container').empty();   //清空
            getMainDataList('all');
        })
    })
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
});
//插入数据
function getMainDataList(statusFlag){
    var rsp = {
        success : 'success',
        total : 100,
        rows :
        [
            {id:'1',providerName:'小明',providerJuli:'15',providerOldTitle:'25岁-高级技师',providerInt:'多年经验'},
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