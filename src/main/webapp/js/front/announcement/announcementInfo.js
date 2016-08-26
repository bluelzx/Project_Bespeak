$(function() {
	initPage();
});
function initPage(){
	var isManager = $("#isManager").val();
	if(isManager < 1){
		$("#announcementController").hide();
	}
}
