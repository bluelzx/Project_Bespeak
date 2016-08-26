$(function () {
    $('.weiPay').click(function () {
        $('#wei').show();
        $('#zfb').hide();
        $(this).children('i').css('backgroundImage','url(../images/choice-on.png)')
        $('.aliPay').children('i').css('backgroundImage','url(../images/choice-no.png)')
    })
    $('.aliPay').click(function () {
        $('#wei').hide();
        $('#zfb').show();
        $(this).children('i').css('backgroundImage','url(../images/choice-on.png)')
        $('.weiPay').children('i').css('backgroundImage','url(../images/choice-no.png)')
    })
})