var HEIGHT = document.documentElement.clientHeight;
$(function() {
	hidePasswordWin();
	//loadMyMenu();
	//loadQuickMenu();
	initMenuTree();
});

function initMenuTree(){
	$("#menuPanel .menu_body:eq(0)").show();
	$("#menuPanel h3.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane h3.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
}

function loadMyMenu(){
	lh.post('back', '/back/getMyMenu',null ,function(rsp){
		if(rsp.success){
			var menuList = rsp.rows;
			var nodes = lh.convertTreeJsonData(menuList);
			console.log(nodes);
			$('#menuPanel').html(buildDom({rows:nodes}));
			initMenuTree();
		}else{
			$.messager.alert('提示', rsp.msg);
		}
	},'json');
}

function buildDom(data){
	if(!data) return '';
	var template = $('#template').html();
	Mustache.parse(template);
	var rendered = Mustache.render(template, data);
	return rendered;
}

function hidePasswordWin(){
	$('#updatePasswordWin').window({modal:true,width:300,height:200});
	$('#updatePasswordWin').window('close');
}

/*function loadQuickMenu(){
	lh.post('back', '/back/getQuickMenuList',null ,function(rsp){
		if(rsp.success){
			var quickMenu = rsp.rows;
			var dom ="";
			if(quickMenu.length > 0){
				for(var i = 0 ;i < quickMenu.length;i++){
					dom = '<span class="foot_item" onclick="showMain(\''+quickMenu[i].menuName+'\',\''+quickMenu[i].menuUrl+'\')">'+quickMenu[i].menuName+'</span>&nbsp;';
					$(".foot_items").append(dom);
				}
			}
		}else{
			$.messager.alert('提示', rsp.msg);
		}
	},'json');
}*/

function showMain(name,path){
	var base = '';
	path = base + path;
	var flag = $('#main').tabs('exists',name);
	if(flag){
		//$('#main').tabs('select',name);
		//return;
		$('#main').tabs('close',name);
	}
	$('#main').tabs('add',{
	    title:name,
	    content:'<iframe name="mainFrame" scrolling="auto" frameborder="0" src="'+path+'" style="width: 100%;height: 99.5%;border: 0;"></iframe>',
	    //href: path,
	    closable:true,
	    tools:[/*{
	        iconCls:'icon-mini-refresh',
	        handler:function(){
	            alert('refresh');
	        }
	    }*/]
	});
	var tab = $('#main').tabs('getSelected');
	var index = $('#main').tabs('getTabIndex',tab);
}

function login(){
    window.location.href="/";
}

function logout(){
	$.post('/logout',null,function(rsp){
	     if(rsp.success){     	
	     	window.location.href = "/back/login";
	     }else{
	    	$.messager.alert('提示',rsp.msg);
	     }
	},'json');
	
}


function openUpdatePwdWin(){
	$('#updatePasswordWin').css('display','');
	$('#updatePasswordWin').window('open');
}

function closeUpdatePwdWin(){
	$('#updatePasswordWin').window('close');
	$('#updatePasswordWin').css('display','none');
}

function refresh(){
	location.reload();
}

function openUpdatePassword(){
	$('#updatePasswordWin').css('display','');
	$('#updatePasswordWin').window('open');
	$('#oldPsd').val('');
	$('#newPsd1').val('');
	$('#newPsd2').val('');
}

function updatePassword(){
	$('#updatePsdTip').text();
	var oldPsd = $('#oldPsd').val();
	var newPsd1 = $('#newPsd1').val();
	var newPsd2 = $('#newPsd2').val();
	if(!oldPsd || !newPsd1 || !newPsd2){
		$('#updatePsdTip').text('请填写完整。');return;
	}
	if(newPsd1 && newPsd2 && newPsd1 != newPsd2){
		$('#updatePsdTip').text('新密码两次输入不一致。');return;
	}
	
	lh.post('back', '/back/updatePassword', {oldPsd:oldPsd,newPsd:newPsd1},function(rsp){
	     if(rsp.success){	
	     	$('#updatePasswordWin').window('close');
	     	$.messager.alert('提示','修改密码成功。请重新登录');
	     	localStorage.clear();
	     }else{
	    	 $.messager.alert('提示',rsp.msg);
	     }
	},'json');
	
}

