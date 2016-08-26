CURRENT_PAGE = 1;//当前页数
PAGE_COUNT = 7;//取多少条数据
SAVING_FLAG = false;
$(function(){
	loadNewsComment();
	//lh.scrollBottom(loadNewsComment);
});

function moreData(){
	loadNewsComment();
}

function goTop(){
	document.documentElement.scrollTop = document.body.scrollTop = 0;
}

function loadNewsComment(){
	var id = $("#id").val();
	//var CURRENT_PAGE = $("#CURRENT_PAGE").val();
	//if(!CURRENT_PAGE)CURRENT_PAGE = 1;
	var param = {};
	param.objectId = id;
	//param.mainStatus = 1;
	param.commentTypeId = 1;
	param.rows = PAGE_COUNT;
	param.page = CURRENT_PAGE;
	$.post('/getCommentList',param,function(rsp){
		if(rsp){
			if(rsp.status == 'success'){
				var count = rsp.total;
				if(count && count > 0){
					makeNewsCommentListDom(rsp.rows,1);
					CURRENT_PAGE++;
					$("#more").show();
					$("#CURRENT_PAGE").val(CURRENT_PAGE);
				}else{
					if(rsp.rows.length == 0){
						$('#resultTip').text('没有更多数据').hide();
						$("#more").hide();
					}else{
						$('#resultTip').text('没有更多数据').show();
					}
				}
			}else{
				lh.alert(rsp.msg);
			}
		}
		SCROLL_LOADING = false;//设置为加载完毕
	},'json');
}


function makeNewsCommentListDom(newsCommentList,isAppend){
	var template = $('#template').html();
	Mustache.parse(template);   // optional, speeds up future uses
	var rendered = Mustache.render(template, {rows:newsCommentList});
	if(isAppend){
		$('#comment').append(rendered);
	}else{
		$('#comment').html(rendered);
	}
}


function addComment(objectId){
	if(SAVING_FLAG)return;
	SAVING_FLAG = true;
	var content = $("#content").val();
	var obj = {};
	obj.objectId = objectId;
	obj.content = content;
	obj.commentTypeId = 1;
	frontBaseLoadingOpen('正在保存数据...');//加载遮罩
	$.post('/addOrUpdateComment',obj,function(rsp){
		SAVING_FLAG = false;
		frontBaseLoadingClose();//解除遮罩
		if(rsp){
			frontLoginCheck(rsp);//登陆检查
			if(rsp.status == 'success'){
				//window.location.reload();
				CURRENT_PAGE = 1;
				$('#comment').empty();
				$('#content').val('');
				loadNewsComment();
			}else{
				if(rsp.noPhone == 'noPhone'){
					lh.alert(rsp.msg,"jumpToBindUserPhone()");
				}else{
					lh.alert(rsp.msg);
				}
			}
		}
	},'json');
}
