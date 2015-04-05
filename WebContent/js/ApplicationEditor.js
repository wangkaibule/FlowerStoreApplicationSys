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

function choosableClickEventHandler(e) {
	wrapWithInputChoose(e.target, "formPieces/" + $(e.target).attr("name")
			+ ".jsp");
}

function addableClickEventHandler(e) {
	var jtarget = $(e.target);
	var input = $("[group="+jtarget.attr('group')+"].inputAddable");
	
	
}

function addableAppender(e) {
	var add = $("[group=" + $(e).attr('group') + "].hiddenAddable");
	var addParent = adkd;
	var theInitiator = add.attr("name");
	var counter = $("#" + theInitiator + "Title");
	var group = add.attr("group");
	var tmp = "[group=" + group + "][counter=" + group + "]";
	var Index = $(tmp).length;
	var row = counter.attr("rowspan");
	var fetcher = function(data) {

		addParent.before(data);
		counter.attr("rowspan", parseInt(row) + 1);

		$(".pending").each(function() {
			this.setAttribute("data-Index", parseInt(Index));
			this.setAttribute("data-title", theInitiator);
			$(this).removeClass("pending");
		});
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
	});

}

function editableInputClickEventHandler(event) {
	var e = $(event.target);
	var theInput = $($("#protoTextarea")[0].cloneNode());
	var theElementName = e.attr("name");

	if (e.is("td") && !e.attr("pending")) {
		theInput.removeAttr("id").removeAttr("style").attr("name",
				theElementName).addClass(e.attr("class"));
		theInput.val(e.children("pre").hide().text());
		e.append(theInput);
		e.attr("pending", "true");
		theInput.focus();
	}
}

function editableChildrenClickEventHandler(e) {
	$(e.target).parent().click();
}

function deleteLastMember(je) {
	var group = je.attr("group");
	var array = $("[counter=" + group + "]");
	var count = array.length;
	var theInitiator = je.attr("name");
	var counter = $("#" + theInitiator + "Title");
	var row = counter.attr("rowspan");

	if (count > 1) {
		$.ajax({
			url : "AppProjectReciever",
			method : "POST",
			data : {
				index : count - 1,
				title : "memberInfoDelete"
			},
			success : function() {
				$(array[count - 1]).parent().parent().remove();
				console.log(parseInt(row));
				counter.attr("rowspan", parseInt(row) - 1);
			}
		});
	}
}

function datableClickEventHandler(e) {
	var jtarget = $(e.target);
	var parent = jtarget.parent();
	var element = document.createElement('input');
	var je = $(element);
	element.setAttribute('readonly', 'true');
	element.setAttribute('type', 'text');

	if (!jtarget.is('a')) {
		return;
	}

	je.datepicker();
	$(parent).append(element);
	jtarget.remove();
	var year = undefined;
	var date = undefined;

	if (Date() != undefined) {
		date = new Date();
		year = date.getFullYear();
		var month = parseInt(date.getMonth()) + 1;
		var startDate = year + '-' + month + '-' + date.getDate();
		if (jtarget.attr('name') === 'startTime') {
			je.attr(startDate);
		} else if (jtarget.attr('name') === 'finishTime') {
			je.datepicker('option', "yearRange", year + ':'
					+ (parseInt(year) + 1));
			je.datepicker('option', 'minDate', startDate);
		}
	}
	setTimeout(je.focus(), 1);

}

function blurThenSubInputFocusEventHandler(e) {
	e.target.click();
}

function changeNodeType(jtarget, type) {
	var attrs = {};

	$.each(jtarget[0].attributes, function(idx, attr) {
		attrs[attr.nodeName] = attr.nodeValue;
	});

	jtarget.replaceWith(function() {
		return $("<" + type + " />", attrs).append($(this).contents());
	});
}

var blurThenSubCommunicator = {};

function blurThenSubInputClickEventHandler(e) {
	var e = event.target;
	if ($(e).is("a")) {
		var element = $(e);
		var theInput = $(wrapWithInputClick(e));
		element.addClass("pending");
		theInput.attr("data-Index", element.attr("data-Index"));
		theInput.attr("data-title", element.attr("data-title"));
		theInput.on("blur", blurThenSubInputBlurEventHandler);
	}
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

	if (origContent !== d.val()) {
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

function init() {
	if (handlerFlag) {
		$('body').on("click", ".clickable", clickableClickEventHandler);
		$('body').on("click", ".choosable", choosableClickEventHandler);
		$('body')
				.on("click", ".blurthensub", blurThenSubInputClickEventHandler);
		$('body')
				.on("focus", ".blurthensub", blurThenSubInputFocusEventHandler);
		$('body').on("click", ".addable", addableClickEventHandler);
		$('body').on("click", ".editable", editableInputClickEventHandler);
		$('body')
				.on("click", ".editable > ", editableChildrenClickEventHandler);
		$('body').on("click", '.datable', datableClickEventHandler);
	}

	$("#back.toolbut").on('click', function() {
		window.location.href = "DashBoard";
	});
	$("#memberInfoTitle").attr("rowspan", $(".roMemberList").length);
	$('#subbut').on("click", function() {
		$("#theForm").data("changed", false);
		$("#theForm").submit()
	});
	$('#deleteMember').on('click', function(e) {
		deleteLastMember($(e.target));
	})
	$(document).on("keypress", 'form', function(e) {
		var code = e.keyCode || e.which;
		var jtarget = $(e.target)
		console.log(code);
		if (code == 13 && jtarget.is('input')) {
			console.log('Inside');
			e.preventDefault();
			return false;
		} else {
			$("#theForm").data("changed", true);
		}
	});

	$(window).on('beforeunload', function() {
		if ($("#theForm").data("changed")) {
			return "内容已经被修改,确定放弃更改?\n请点击右侧提交保存更改";
		}
	});

	$(".scrollingbox").draggable();

	(function(factory) {
		if (typeof define === "function" && define.amd) {

			// AMD. Register as an anonymous module.
			define([ "../jquery.ui.datepicker" ], factory);
		} else {

			// Browser globals
			factory(jQuery.datepicker);
		}
	}(function(datepicker) {
		datepicker.regional['zh-cn'] = {
			closeText : '关闭',
			prevText : '&#x3C;上月',
			nextText : '下月&#x3E;',
			currentText : '今天',
			monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			monthNamesShort : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
					'九月', '十月', '十一月', '十二月' ],
			dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
			dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
			dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
			weekHeader : '周',
			dateFormat : 'yy-mm-dd',
			firstDay : 1,
			isRTL : false,
			showMonthAfterYear : true,
			yearSuffix : '年',
			changeMonth : true,
			changeYear : true,
		// showOn : 'button'
		};
		datepicker.setDefaults(datepicker.regional['zh-cn']);

		return datepicker.regional['zh-cn'];

	}));
}
