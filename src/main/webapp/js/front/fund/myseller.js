$(function(){
    $("#storePortrait").click(function(){
    	$('.storePortrait').modal('show');
    });
    $(".ppkgl").on("click",function(){
        $(".spmc_bg,.spmc").show();
    });
    $(".spmc_quit,.spmc_bg").on("click",function(){
        $(".spmc_bg,.spmc").hide();
        $(".alert_madol").hide();
    });
    $(".removeAddress").on("click",function(){
    	$(".alert_madol,.spmc_bg").show();
    	var thisAddress = $(this);
	    $("#removeBtn").on("click",function(){
	    	$(".alert_madol,.spmc_bg").hide();
	    	thisAddress.parent().parent().remove();
            $(".modelAddress").show(300);
            $('.modelAddress').delay(1000).hide(300);
	    });
    });
});




