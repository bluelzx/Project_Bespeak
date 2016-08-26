
$(function(){
    getMainDataList('all');

});
//处理数据
function getMainDataList(statusFlag){
    var rsp = {
        success : 'success',
        total : 100,
        rows :
            [
                {id:'1',Url:'#',src:'images/gy.png',title:'南方水灾 爱比水深',time:'7/31',trainInf:'连日来，南方遭遇强降雨侵袭，安徽、湖北等省份出现严重洪涝灾情备受全国……'},
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
var mySwiper = new Swiper('.swiper-container',{
    pagination : '.swiper-pagination',
    loop : true,
    autoplay : 4000,
    speed:400,
    autoplayDisableOnInteraction : false,
})