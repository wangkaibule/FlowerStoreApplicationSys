function newPopWindow(url,title, height, width) {
	window.open(url, title, 'height=' + height + ',width=' + width
			+ ',left=10' + ',top=10,resizable=yes' + ',scrollbars=yes'
			+ ',toolbar=yes' + ',menubar=no' + ',location=no'
			+ ',directories=no' + ',status=yes');
}