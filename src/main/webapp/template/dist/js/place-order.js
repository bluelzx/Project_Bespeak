$(function(){
    var t = $("#text_box");
    $(".plus").click(function(){
        t.val(parseInt(t.val())+1)
    })
    $(".reduce").click(function(){
        t.val(parseInt(t.val())-1)
        if(t.val()<=1){
            t.val(1);
        }
    })

    $('.xd-fuwu').click(function () {
        $('.motai').show()
    })
    $('.motai').click(function () {
        $('.motai').hide()
    })
    $('#xd-sm').click(function () {
        var text = $(this).html();
        $('.xd-fs').html(text);
    })
    $('#xd-jd').click(function () {
        var text = $(this).html();
        $('.xd-fs').html(text);
    })
});






