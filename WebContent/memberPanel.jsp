<%@ page language="java" contentType="text/html; charset=GB18030"
 pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head data-gwd-animation-mode="quickMode">
<title>addDeleteMember</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="Google Web Designer 1.2.1.0121">
<style type="text/css">
.memberSelector:hover{
   cursor:pointer;
   background-color:rgb(233, 230, 210);
}
.gwd-div-nlvb {
	width: 421px;
	height: 412px;
	background-image: none;
	background-color: rgb(241, 241, 242);
}

.gwd-div-dncl {
	width: 403px;
	height: 73px;
	top: 0px;
	position: relative;
	margin: 2px;
	left: 7px;
	background-image: none;
	background-color: rgb(219, 213, 235);
}

.gwd-div-2hkp {
	width: 403px;
	height: 56px;
	position: relative;
	top: 4px;
	left: 7px;
	padding: 0px;
	margin: 2px;
	background-image: none;
	background-color: rgb(219, 213, 235);
}

.gwd-div-8n22 {
	width: 403px;
	height: 239px;
	position: relative;
	padding: 0px;
	margin: 2px;
	left: 7px;
	top: 19px;
	background-image: none;
	background-color: rgb(219, 213, 235);
}

.gwd-div-igef {
	height: 36px;
	font-family: 'Times New Roman';
	text-align: left;
	color: rgb(0, 0, 0);
	position: relative;
	width: 130px;
	left: 10px;
	top: 24px;
}

.gwd-div-npjo {
	text-align: right;
}

.gwd-div-l34h {
	font-family: 'Times New Roman';
	text-align: left;
	color: rgb(0, 0, 0);
	height: 35px;
	position: relative;
	width: 130px;
	left: 247px;
	top: -12px;
}

.gwd-div-n6gs {
	width: 55px;
	height: 35px;
	position: relative;
	top: -47px;
	left: 165.5px;
    font:bold;
}

.gwd-input-j79g {
	width: 265px;
	height: 29px;
	position: relative;
	left: 21px;
	top: 10px;
}

.gwd-button-y8my {
	width: 59px;
	position: relative;
	float: right;
	left: -43px;
	top: 9.25px;
	-webkit-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
	-webkit-transform-style: preserve-3d;
	height: 36px;
}

.gwd-ul-70oq {
	position: relative;
	top: 0px;
	width: 358px;
	left: 3px;
	height: 100%;
	overflow-y: scroll;
	overflow-x: hidden;
}

.gwd-div-yiy9 {
	width: 146px;
	height: 33px;
	font-family: 'Times New Roman';
	text-align: left;
	color: rgb(0, 0, 0);
	position: relative;
	left: 7px;
	top: -242px;
}

.gwd-span-gstg {
	font-size: 10px;
}

.gwd-li-3luy {
	height: 56px;
	list-style-type: none;
	position: relative;
	width: 377px;
	left: -37px;
}

.gwd-div-q7oq {
	height: 45px;
	list-style-type: disc;
	padding: 2px;
	position: relative;
	width: 385px;
	left: -5px;
}

.gwd-div-iaoj {
	font-family: 'Times New Roman';
	height: 35px;
	list-style-type: disc;
	margin: 2px;
	position: absolute;
	text-align: center;
	width: 141px;
	left: 1px;
	top: 6px;
	background-image: none;
	background-color: rgb(171, 162, 242);
}

.gwd-div-3k1a {
	font-family: 'Times New Roman';
	height: 35px;
	list-style-type: disc;
	margin: 2px;
	position: absolute;
	text-align: center;
	left: 144px;
	top: 6px;
	width: 224px;
	background-image: none;
	background-color: rgb(171, 162, 242);
}

.gwd-div-ltmp {
	border-color: rgba(255, 255, 255, 0);
	color: rgb(255, 0, 0);
	height: 25px;
	outline-color: rgb(255, 0, 0);
	position: relative;
	text-align: left;
	width: 226px;
	left: 178px;
	top: -282px;
	text-align: center;
}
</style>
<script type="text/javascript" src="js/memberPanel.js"></script>
	
</head>

<body>
 <div class="gwd-div-nlvb">
  <div class="gwd-div-dncl" style="" id="countWrapper">
   <div class="gwd-div-igef">
    <div class="gwd-div-npjo">已添加</div>
   </div>
   <div class="gwd-div-l34h">个成员</div>
   <div class="gwd-div-n6gs"><b id="counter"></b></div>
  </div>
  <div class="gwd-div-2hkp" style="">
   <input class="gwd-input-j79g" placeholder="输入学号后回车" id="idInput">
   <button class="gwd-button-y8my" id="addConfirm">确定</button>
  </div>
  <div class="gwd-div-8n22">
   <ul class="gwd-ul-70oq">
   <c:forEach items="${Content.membersList }" var="current" begin="1">
    <li class="gwd-li-3luy member"> 
     <div class="gwd-div-q7oq memberSelector">
      <div class="gwd-div-iaoj">${current.name }</div>
      <div class="gwd-div-3k1a">${current.studentID }</div>
     </div>
    </li>
</c:forEach>
   </ul>
  </div>
  <div class="gwd-div-yiy9">
   <span class="gwd-span-gstg">已选择的组员(点击以删除):</span>
  </div>
  <div class="gwd-div-ltmp" style="display:none;" id="errorDisplay">错误显示  </div>
 </div>
</body>

</html>