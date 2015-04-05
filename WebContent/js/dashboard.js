function init(){
	$('body').on('click','.deletebut',function(){
		if(!window.confirm("确定删除?")){
			return false;
		}
	})
}