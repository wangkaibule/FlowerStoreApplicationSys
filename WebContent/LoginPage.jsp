<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="notify" class="com.RS.servlet.NotificationOnLoginPage"></jsp:useBean>
<!DOCTYPE html>
<html>
<head data-gwd-animation-mode="quickMode">
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="Google Web Designer 1.2.1.0121">
<script src="js/LoginPage.js"></script>
<jsp:include page="commonjsfile.html"></jsp:include>

<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	margin: 0px;
}

body {
	-webkit-transform: perspective(1400px)
		matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	-webkit-transform-style: preserve-3d;
	background-color: transparent;
}

pre {
	white-space: pre-wrap; /* CSS3 */
	white-space: -moz-pre-wrap; /* Mozilla, post millennium */
	white-space: -pre-wrap; /* Opera 4-6 */
	white-space: -o-pre-wrap; /* Opera 7 */
	word-wrap: break-word; /* Internet Explorer 5.5+ */
	font-size: 20px;
}

#errorPrompt {
	font-color: red;
	position: relative;
	top: -62px;
	left: 116px;
}

.gwd-div-5hjy {
	width: 1027px;
	height: 463px;
	left: 0px;
	margin-right: auto;
	margin-left: auto;
	position: relative;
	top: 97px;
}

.gwd-div-man2 {
	width: 1027px;
	position: relative;
	height: 125px;
	border-width: 1px;
	border-style: solid;
	left: 0px;
	-webkit-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, -2, 2, 0, 1);
	-webkit-transform-style: preserve-3d;
	background-image: none;
	background-color: rgb(241, 241, 242);
}

.gwd-div-jdxw {
	width: 609px;
	height: 341.5px;
	position: relative;
	border-width: 1px;
	border-style: solid;
	-webkit-transform: translate3d(-2px, 2px, 0px);
	-webkit-transform-style: preserve-3d;
	left: 0px;
	top: -0.25px;
	background-image: none;
	background-color: rgb(241, 241, 242);
}

.gwd-div-yzwu {
	width: 418px;
	height: 341px;
	position: relative;
	border-style: solid;
	border-width: 1px;
	-webkit-transform: translate3d(-2px, 2px, 0px);
	-webkit-transform-style: preserve-3d;
	left: 609px;
	top: -344px;
	background-image: none;
	background-color: rgb(241, 241, 242);
}

.gwd-div-bmk1 {
	width: 300px;
	height: 287px;
	position: relative;
	margin-right: auto;
	margin-left: auto;
	top: 22px;
	text-align: center;
}

.gwd-input-1iju {
	height: 53px;
	position: relative;
	width: 265px;
	margin-bottom: 2px;
	left: 1px;
	top: 23px;
	font-size: 30px;
	padding-left: 20px;
}

.gwd-input-x0u5 {
	width: 300px;
}

.gwd-a-5wbn {
	width: 300px;
	height: 28px;
	color: rgb(0, 0, 0);
	position: relative;
	left: 1px;
	top: 63px;
}

.gwd-div-0lvq {
	width: 59px;
	height: 29.5px;
	font-family: 'Times New Roman';
	color: rgb(0, 0, 0);
	position: relative;
	left: -58px;
	top: -161.25px;
	text-align: right;
	font-size: 20px;
}

.gwd-div-0lvq {
	
}

.gwd-div-zh0r {
	left: -58px;
	top: -129.25px;
}

.gwd-div-jnvq {
	left: -58px;
	top: -158.25px;
}

.gwd-a-gb5w {
	width: 63px;
	height: 25px;
	position: relative;
	left: 118px;
	top: -47px;
}

.gwd-img-qatr {
	width: 365px;
	position: relative;
	margin: 2px;
	height: 121px;
}

.gwd-div-b82r {
	position: absolute;
	width: 660px;
	height: 121px;
	font-family: 'Times New Roman';
	text-align: left;
	color: rgb(0, 0, 0);
	left: 367px;
	top: 2px;
	font-size: 50px;
	padding-top: 34px;
}

.gwd-div-60ct {
	text-align: center;
}

.gwd-pre-k0kl {
  position: relative;
  height: 326px;
  width: 594px;
  margin-bottom: 0px;
  margin-right: auto;
  margin-left: auto;
  border-width: 1px;
  border-style: solid;
  overflow-y: scroll;
  background-image: none;
  background-color: rgb(219, 213, 235);
  top: -14px;
}
</style>
</head>

<body onload="javascript:init();">
 <div class="gwd-div-5hjy" style="">
  <div class="gwd-div-man2">
   <img class="gwd-img-qatr" src="pic/logo.png">
   <div class="gwd-div-b82r">
    <div class="gwd-div-60ct">创新创业项目申报系统</div>
   </div>
  </div>
  <div class="gwd-div-jdxw" style="">
   <pre class="gwd-pre-k0kl">${notify.content }</pre>
  </div>
  <div class="gwd-div-yzwu">
   <form action="Login" method="post">
    <div class="gwd-div-bmk1 gwd-div-cu4r" style="" id="LoginWrapper">
     <input class="gwd-input-1iju gwd-input-y1tp"
      name="${LoginManager.formusernameid }"> <input
      class="gwd-input-1iju" name="${LoginManager.formpasswordid }"
      type="password"> <input class="gwd-input-1iju gwd-input-x0u5"
      type="submit" value="登录"><input type="hidden"
      name="${LoginManager.formtypeid}"
      value="${LoginManager.formtypemanage}"> <a class="gwd-a-5wbn"
      id="LoginSwitch" href="javascript:;" style="">以游客身份查看项目</a>
     <div class="gwd-div-0lvq gwd-div-jnvq" style="">学号:</div>
     <div class="gwd-div-f12s gwd-div-0lvq gwd-div-zh0r" style="">密码:</div>
     <a class="gwd-a-gb5w" style="" href="Register">注册</a>
    </div>
   </form>
   <form action="Login" method="post">
    <div class="gwd-div-bmk1" style="display: none;" id="ViewerWrapper">
     <input class="gwd-input-1iju gwd-input-y1tp"
      name="${LoginManager.formrealnameid }"> <input
      class="gwd-input-1iju" name="${LoginManager.formusernameid }">
     <input class="gwd-input-1iju gwd-input-x0u5" style="font-size: 20px;"
      type="submit" value="查看"><input type="hidden"
      name="${LoginManager.formtypeid}"
      value="${LoginManager.formtypeviewer}"> <a class="gwd-a-5wbn"
      id="ViewerSwitch" href="javascript:;" style="">申请/修改项目</a>
     <div class="gwd-div-0lvq gwd-div-jnvq" style="">姓名:</div>
     <div class="gwd-div-f12s gwd-div-0lvq gwd-div-zh0r" style="">学号:</div>
    </div>
   </form>
   <div id="errorPrompt">
    <span style="color: red;"> <c:if test="${not empty(invalidID) }">
                              信息输入错误
     </c:if> <c:if test="${not empty(invalidPWD) }">
                              信息输入错误
     </c:if> <c:if test="${param.status eq LoginManager.statusRegSuccessful }">
                              请登录
     </c:if>
    </span>
   </div>
  </div>
 </div>
</body>

</html>