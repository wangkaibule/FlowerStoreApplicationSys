/**
 * 
 */
function LoginPageInit(){
 $("#view").click(function(){
  $("#mainMenu").hide();
  $("#formViewer").show();
  $("#formManage").css("float","left");
 });

 $("#manage").click(function(){
   $("#mainMenu").hide();
   $("#formManage").show();
   $("#formViewer").css("float","left");
 });
}