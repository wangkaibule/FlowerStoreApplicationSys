function init(){
	$('body').on('click','.deletebut',function(){
		if(!window.confirm("确定删除?")){
			return false;
		}
	})
	$("#exitBut").on("click",exit);
}

function exit(){
	window.location.replace("exit?invalidUser=true");
}