$(function() {
			loadAnnouncement();
			lh.scrollBottom(loadAnnouncement);
			initPage();
		});

function loadAnnouncement() {
	var typeId = $("#forumId").val();
	var typeCode = 'forumAnnouc';
	var param = {typeId:typeId,typeCode:typeCode};
	$.post('/getAnnouncementList',param, function(rsp) {
				if (rsp.status == 'success') {
					makeAnnouncementListDom(rsp.rows, 1);
				} else {
					$('#data-container').html("	<center><font size='6px' color='#ff5000'>暂无公告</font></center>");
				}
			}, 'json');
}

function makeAnnouncementListDom(announcementList, isAppend) {
	var isManager = $("#isManager").val();
	var template = $('#announcement').html();
	Mustache.parse(template); // optional, speeds up future uses
	var rendered = Mustache.render(template, {
				rows : announcementList,
				getDate : function() {
					var createdAt = this.createdAt;
					createdAt = lh.formatDate({
								date : new Date(createdAt),
								flag : 'datetime'
							});
					return createdAt;
				},
				delete_an:function(){
					var delete_an = '';
					if(isManager == 1){
						delete_an = ' <a rel="button" id="deleteAnnouncement" class="btn btn-sm btn-orange" ' +
								'onclick="deleteAnnouncement('+this.id+')" >删除</a>';
					}else{
						
					}
					return delete_an;
				}
			});
	if (isAppend) {
		$('#data-container').append(rendered);
	} else {
		$('#data-container').html(rendered);
	}
}
function initPage(){
	var isManager = $("#isManager").val();
	if(isManager < 1){
		$("#announcementController").hide();
		$("#deleteAnnouncement").hide();
	}
	
}
function deleteAnnouncement(id){
	var forumsId = $("#forumId").val();
	$.post('/deleteAnnouncement',{id:id}, function(rsp) {
		if (rsp.status == 'success') {
			lh.alert(rsp.msg);
			lh.jumpR('/announcement/'+forumsId);
		} else {
			lh.alert(rsp.msg);
			lh.jumpR('/announcement/'+forumsId);
		}
	}, 'json');
}
