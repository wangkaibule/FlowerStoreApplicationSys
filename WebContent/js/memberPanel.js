function inputReturnHitEventHandler(e) {
	var code = e.keyCode || e.which;
	if (code == 13) {
		var inputContent = $("#idInput").val();
		var insertPoint = $(".hiddenAddable");
		var title = $("#memberInfoTitle");
		var rowspan = parseInt(title.attr("rowspan"));
		var finish = function() {
			$("#counter").html($(".member").length);
			$("#idInput").focus();
		}

		var fetcher = function(data) {
			insertPoint.before(data);
			title.attr("rowspan", rowspan + 1);
			$("#memberPanel").load("memberPanel.jsp", finish);
		}

		var tofHandler = function() {
			$("#errorDisplay").html("该成员已存在!");
			$("#errorDisplay").show();
			return false;
		}
		
		var fooHandler = function(){
			$("#errorDisplay").html("学号输入错误!");
			$("#errorDisplay").show();
		}

		$.ajax({
			url : "AppProjectReciever",
			method : "GET",
			data : {
				id : inputContent,
				title : "memberInfoUpdate",
				index : $("[group=a][counter=a]").length
			},
			dataType : "html",
			statusCode:{
				204:tofHandler,
				400:fooHandler,
				200:fetcher
			}
		})
		e.preventDefault();
	}
}

function memberItemClickEventHandler(e) {
	var jtarget = $(e.target);
	var handler = function() {
		var index = jtarget.index() + 1;
		var resultHandler = function() {
			$(".roMemberList")[index].remove();
			title.attr("rowspan",rowspan-1);
			jtarget.remove();
			$("#counter").html($(".member").length);
		}
		var title = $("#memberInfoTitle");
		var rowspan = parseInt(title.attr("rowspan"));
		var errorHandler= function(){
			jtarget.show();
		}
		

		jtarget.hide();
		$.ajax({
			url : "AppProjectReciever",
			success : resultHandler,
			method : "POST",
			data : {
				title : "memberInfoDelete",
				index : index
			},
			error:errorHandler
		})
		console.log(e.target);
	}
	if (!jtarget.hasClass("member")) {
		jtarget = jtarget.closest(".member");
	}
	handler();
}

function addButtonClickEventHandler(e){
	e.witch=13;
	inputReturnHitEventHandler(e);
}
