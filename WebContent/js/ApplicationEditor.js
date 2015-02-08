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
	alert("submitted...");
	var pending = $("a.pending");
	var d = $(e.target);
	var content = d.val();

	pending.text(content);
	d.remove();
	pending.show();
	pending.removeClass("pending");
}

function choosableClickEventHandler(e) {
	wrapWithInputChoose(e.target,"formPieces/"+$(e.target).attr("name")+".jsp");
}

function addableClickEventHandler(e) {
	var add = $(e.target);
	var addParent = add.parent().parent();
	var theInitiator = add.attr("name");
	var counter = $("#" + theInitiator + "Title");
	var Index = counter.attr("data-nextIndex");
	var row = counter.attr("rowspan");
	var fetcher = function(data) {

		addParent.before(data);
		counter.attr("rowspan", parseInt(row) + 1);
		$(".pending").each(function() {
			this.setAttribute("data-Index", parseInt(Index));
			$(this).removeClass("pending");
		})
	}
	counter.attr("data-nextIndex", parseInt(Index) + 1);
	console.log(Index);

	$.ajax({
		url : "formPieces/" + theInitiator + "Piece.jsp",
		method : "GET",
		success : function(data) {
			fetcher(data)
		},
		dataType : "html"
	})
}
function blurThenSubInputClickEventHandler(event) {
	var e = event.target;
	var theInput = wrapWithInputClick(e);

	$(e).addClass("pending");
	$(theInput).blur(blurThenSubInputBlurEventHandler);

}

function blurThenSubInputFocusEventHandler(event) {
	var e = $(event.target);
	if (e.is("a")) {
		e.click();
	}
}

function editableInputFocusEventHandler(event) {
	var e = $(event.target);

	if (e.is("textarea")) {
		wrapWithInputClick(e[0]);
	}
}

function init() {
	$('body').on("click", ".clickable", clickableClickEventHandler);
	$('body').on("click", ".choosable", choosableClickEventHandler);
	$('body').on("click", ".blurthensub", blurThenSubInputClickEventHandler);
	$('body').on("focus", ".blurthensub", blurThenSubInputFocusEventHandler);
	$('body').on("click", ".addable", addableClickEventHandler);
	$('body').on("focus", ".editable", editableInputFocusEventHandler);
	$("#memberInfoTitle").attr("rowspan",$(".roMemberList").length);
}
