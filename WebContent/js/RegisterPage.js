/**
 * 
 */
function isvalid() {
	var psd = document.getElementById("psd").value;
	var cfmpsd = document.getElementById("cfmpsd").value;
	if (psd === cfmpsd) {
		document.getElementById("submit").disabled = false;
	} else {
		document.getElementById("submit").disabled = true;
	}
}

function init() {
	$(document).ready(function() {
		$("#cfmpsd").keyup(isvalid);
	});
}