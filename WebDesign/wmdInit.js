var isCookieEnabled=false;

new WMDEditor({
		input: "notes",
		button_bar: "wmd-button-bar",
		preview: "wmd-preview",
		buttons: "bold italic  blockquote image  ol ul heading  undo",
		modifierKeys: false,
		autoFormatting: {list:true},
		isFileInput:true,
});
