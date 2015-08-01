/**
 * 
 */
function init(){
	$("#LoginSwitch").on("click",switchToViewer);
	$("#ViewerSwitch").on("click",switchToLogin);
}

function switchToViewer(){
	$("#LoginWrapper").hide();
	$("#ViewerWrapper").show();
}

function switchToLogin(){
	$("#ViewerWrapper").hide();
	$("#LoginWrapper").show();
}