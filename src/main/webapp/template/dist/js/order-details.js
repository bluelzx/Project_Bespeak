
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
    //切换订单
    $('.order-ul li').each(function (i) {
        $(this).click(function () {
            $('.order-ul li').removeClass('choice-on').eq(i).addClass('choice-on');
            $('#data-container').empty();
            getMainDataList('all');
        })
    });
});

function getMainDataList(statusFlag){
    var rsp = {
        success : 'success',
        total : 100,
        rows :
        [
            {id:'1',detailsName:'小儿推拿',je:'250元',providerOldTitle:'32岁-高级技师32岁32岁-高级技师32岁32岁-高级技师32岁32岁-高级技师32岁32岁-高级技师32岁32岁-高级技师32岁32岁-高级技师32岁-高级技师32岁-高级技师32岁-高级技师技师',orderStateClass:'ordering',orderState:'进行中',time:'2016.06.29',orderHref:'#',btnState:'queren',operation:'确认完成',StateNG:'quxiao',operationNG:'取消订单'},
            {id:'2',detailsName:'足疗',providerOldTitle:'28岁-高级技师',orderStateClass:'order-pj',orderState:'待评价',time:'2016.05.29',orderHref:'order-evaluate.html',btnState:'pingjia',operation:'评价'},
            {id:'3',detailsName:'通乳',providerOldTitle:'28岁-高级技师',orderStateClass:'order-ok',orderState:'已完成',time:'2016.05.30',orderHref:'#',btnState:'try',operation:'再次下单'},
            {id:'4',detailsName:'通乳',providerOldTitle:'38岁-高级技师',orderStateClass:'order-ng',orderState:'已取消',time:'2016.05.30',orderHref:'#',btnState:'try',operation:'再次下单'},
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
<!--轮播图JS-->
var mySwiper = new Swiper('.swiper-container',{
    slidesPerView : 5.4,
    freeMode : true,
    freeModeMomentum : false,
})