$(function () {
    function star(choiceName) {
        var choice = $(choiceName+' img');
        choice.each(function (i) {
            $(this).click(function () {
                clear();
                choice.slice(0,i+1).attr('src','images/pjx-on.png');
            })
        });
        function clear() {
            choice.attr('src','images/pjx.png');
        }
    }
    var num1 = '.choice-pj1';
    var num2 = '.choice-pj2';
    var num3 = '.choice-pj3';
    star(num1);
    star(num2);
    star(num3)
});

