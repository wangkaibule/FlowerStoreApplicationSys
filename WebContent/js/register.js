	function isvalid() {
		var psd = document.getElementById("psd").value;
		var cfmpsd = document.getElementById("cfmpsd").value;
		if (psd === cfmpsd) {
			document.getElementById("submit").disabled = false;
		}
	}

	function init() {
		$("#cfmpsd").keyup(isvalid);
	}