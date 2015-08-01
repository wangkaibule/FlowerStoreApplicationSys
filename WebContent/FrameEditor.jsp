<%@ page language="java" contentType="text/html; charset=GB18030"
 pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head data-gwd-animation-mode="quickMode">
<c:if test="${not empty Content.name }">
 <title><c:out value="${Content.name }" /></title>
</c:if>
<c:if test="${empty Content.name }">
 <title>新建</title>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="Google Web Designer 1.3.2.0521">
<link rel="stylesheet" href="css/pure.min.css">
<link rel="stylesheet" href="css/rsCSS.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	margin: 0px;
    overflow: hidden;
}

body {
	-webkit-transform: perspective(1400px)
		matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	-webkit-transform-style: preserve-3d;
	background-color: transparent;
}

.gwd-div-14s9 {
	position: relative;
	width: 1349px;
	margin: auto;
	height: 660px;
	-webkit-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	-webkit-transform-style: preserve-3d;
}

.gwd-div-4y94 {
	position: relative;
	height: 660px;
	width: 343px;
}

.gwd-div-a0rm {
	position: relative;
	height: 660px;
	top: -660px;
	left: 347px;
	width: 1001px;
}

.gwd-div-xc54 {
	width: 438px;
	position: relative;
	height: 220px;
}

.gwd-div-jd68 {
	left: 0px;
	position: relative;
	height: 350px;
}

.gwd-div-wsxm {
	width: 127px;
	height: 42px;
	position: relative;
	top: 56px;
	left: 50px;
}

.gwd-div-uxk5 {
	width: 127px;
	top: 30px;
	left: 52px;
}

.gwd-div-hakm {
	top: 30px;
	left: 22px;
}

.gwd-new-class-7ujm {
	
}

.gwd-li-pnjr {
	width: 350.390625px;
}

.gwd-ul-5j41 {
	width: 80%;
	left: 30px;
}

.gwd-ul-owlu {
	-webkit-transform: translate3d(1px, 0px, 0px);
	-webkit-transform-style: preserve-3d;
}

.gwd-div-6u4l {
	top: 0px;
	left: 0px;
	height: 58px;
	position: relative;
	width: 100%;
}

.gwd-div-bque {
	position: relative;
	top: 0px;
	width: 945px;
	height: 602px;
	margin: auto;
	background-color: white;
}

.gwd-li-nxfg {
	-webkit-transform: translate3d(0px, 2px, 0px);
	-webkit-transform-style: preserve-3d;
}
.pure-menu-selected {
	border-left-style: solid;
	border-left-width: 2px;
    background-color: #eee;
}
</style>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/angular-route.min.js"></script>
<script type="text/javascript" src="js/FrameEditor.js"></script>
</head>

<body ng-app="editorFrame">
 <div class="gwd-div-14s9" style="">
  <div class="gwd-div-4y94 rs-back">
   <div class="gwd-div-xc54 rs-list" id="Information" style=""></div>
   <div class="gwd-div-jd68 rs-list pure-menu" id="menu">
    <ul class="pure-menu-list gwd-ul-5j41" id="content-select">
     <li class="pure-menu-item pure-menu-selected" style=""><a
      class="pure-menu-link" href="#!/preview"><i
       class="fa fa-file-text "></i>&nbsp;预览</a></li>
     <li class="pure-menu-heading gwd-li-nxfg" style=""><i
      class="fa fa-pencil"></i>&nbsp;编辑</li>
     <li class="pure-menu-heading" style="">
      <div class="pure-menu-scrollable"
       style="height: 257px; width: 290px;">
       <ul class="pure-menu-list gwd-ul-owlu" style="">
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/members">组成员</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/details">项目细节</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/teacher">指导老师信息</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/tutor">创业实践项目企业导师</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/background">选题背景和意义</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/intro">项目简介</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/goal">预期成果</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/feature">主要创新点与特色</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/request">配套条件要求</a></li>
        <li class="pure-menu-item " style=""><a
         class="pure-menu-link" href="#!/financeRequest">经费预算</a></li>
       </ul>
      </div>
     </li>
    </ul>
   </div>
   <a
    class="gwd-div-wsxm gwd-div-hakm gwd-new-class-7ujm pure-button rs-button"
    id="saveButton" href="#">保存</a> <a
    class="gwd-div-wsxm gwd-div-uxk5 pure-button rs-button" id="dropButton"
    href="#">舍弃修改</a>
  </div>
  <div class="gwd-div-a0rm rs-back">
   <div class="gwd-div-6u4l" style="">
    <span id="titleContent"
     style="font-size: 50px; background-color: white;"> 
     <c:if
      test="${not empty Content.name }">
        <c:out value="${Content.name }" />
     </c:if>
     <c:if test="${empty Content.name }">新建
     </c:if>
    </span>
   </div>
   <div class="gwd-div-bque" id="editor-frame" style="" ng-view></div>
  </div>
 </div>
</body>

</html>
