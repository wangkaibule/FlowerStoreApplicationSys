function wrapWithInputClick(e) {
	var ParentNode = $(e).parent()[0];
	var theInput = $("#protoInput")[0].cloneNode();
	var theElementName = e.getAttribute("name");

	theInput.removeAttribute("id");
	theInput.removeAttribute("style");
	theInput.setAttribute("name", theElementName);

	$(theInput).attr("value", $(e).hide().text());
	$(theInput).addClass($(e).attr("class"));
	ParentNode.appendChild(theInput);
	setTimeout(function() {
		$(theInput).focus();
	}, 1);
	return theInput;
}

function clickableClickEventHandler(e) {
	if ($(e.target).is("a")) {
		wrapWithInputClick(e.target);
	}
}

function wrapWithInputChoose(e, theURL) {
	var ParentNode = $(e).parent()[0];
	var fetcher = function(data) {
		$(ParentNode).append(data);
	};

	$.ajax({
		url : theURL,
		type : "GET",
		data : {
			name : $(e).attr("name")
		},
		success : function(data) {
			fetcher(data)
		},
		dataType : "html"
	})

	$(e).hide();
}

function blurThenSubInputBlurEventHandler(e) {
	var pending = $("a.pending");
	var d = $(e.target);
	var content = d.val();
	var origContent = pending.text();

	if (content === "") {
		content = "-";
	}

	d.hide();
	pending.text(content);
	pending.show();
	pending.removeClass("pending");
	
	if(origContent!==d.val()){
	$.ajax({
		url : "AppProjectReciever",
		type : "POST",
		data : {
			name : d.attr("name"),
			index : d.attr("data-Index"),
			title : d.attr("data-title") + "Update",
			value : content
		}
	})
	}

	d.remove();
}

function choosableClickEventHandler(e) {
	wrapWithInputChoose(e.target, "formPieces/" + $(e.target).attr("name")
			+ ".jsp");
}

function addableClickEventHandler(e) {
	var add = $(e.target);
	var addParent = add.parent().parent();
	var theInitiator = add.attr("name");
	var counter = $("#" + theInitiator + "Title");
	var group = add.attr("group");
	var tmp = "[group="+group+"][counter="+group+"]";
	var Index = $(tmp).length;
	var row = counter.attr("rowspan");
	var fetcher = function(data) {

		addParent.before(data);
		counter.attr("rowspan", parseInt(row) + 1);

		$(".pending").each(function() {
			this.setAttribute("data-Index", parseInt(Index));
			this.setAttribute("data-title", theInitiator);
			$(this).removeClass("pending");
		})
	}

	$.ajax({
		url : "formPieces/" + theInitiator + "Piece.jsp",
		method : "GET",
		success : function(data) {
			fetcher(data)
		},
		dataType : "html"
	})

	console.log(Index);

	$.ajax({
		url : "AppProjectReciever",
		type : "POST",
		data : {
			title : add.attr("name") + "Update",
			index : Index,
		}
	})

}

function blurThenSubInputClickEventHandler(event) {
	var e = event.target;
	if ($(e).is("a")) {
		var element = $(e);
		var theInput = $(wrapWithInputClick(e));
		element.addClass("pending");
		theInput.attr("data-Index",element.attr("data-Index"));
		theInput.attr("data-title",element.attr("data-title"));
		theInput.on("blur",blurThenSubInputBlurEventHandler);
	}
}

function blurThenSubInputFocusEventHandler(event) {
	var e = $(event.target);
	if (e.is("a")) {
		e.click();
	}
}

function editableInputClickEventHandler(event) {
	var e = $(event.target);
	var theInput = $($("#protoTextarea")[0].cloneNode());
	var theElementName = e.attr("name");

	if (e.is("td")&&!e.attr("pending")) {
		theInput.removeAttr("id").removeAttr("style").attr("name",
				theElementName).addClass(e.attr("class"));
		theInput.val(e.children("p").hide().text());
		e.append(theInput);
		e.attr("pending","true");
		theInput.focus();
	}
}

function editableChildrenClickEventHandler(e){
	$(e.target).parent().click();
}

function init() {
	$('body').on("click", ".clickable", clickableClickEventHandler);
	$('body').on("click", ".choosable", choosableClickEventHandler);
	$('body').on("click", ".blurthensub", blurThenSubInputClickEventHandler);
	$('body').on("focus", ".blurthensub", blurThenSubInputFocusEventHandler);
	$('body').on("click", ".addable", addableClickEventHandler);
	$('body').on("click", ".editable", editableInputClickEventHandler);
	$('body').on("click",".editable > ",editableChildrenClickEventHandler);
	$("#memberInfoTitle").attr("rowspan", $(".roMemberList").length);
}
