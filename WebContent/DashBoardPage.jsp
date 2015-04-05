<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head data-gwd-animation-mode="quickMode">
<meta name="GCD"
 content="YTk3ODQ3ZWZhN2I4NzZmMzBkNTEwYjJl17921d9840ebd36d1ccbeb5e314c14d8">
<title>项目列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="Google Web Designer 1.2.1.0121">
<link rel="stylesheet" type="text/css" href="css/dashboard.css">
<%@ include file="commonjsfile.html"%>
<script src="js/dashboard.js"></script>
</head>
<body onload="javascrit:init();">
 <div class="gwd-div-c31z" id="elementWrapper">
  <img class="gwd-img-8ns1" id="logo" alt="LOGO" src="pic/logo.png">
  <div class="gwd-div-gqde" id="banner">
   <h1 class="gwd-h1-5jbh">
    <div class="gwd-div-ugko" style="">
     <div class="gwd-div-wdk1">
      <span class="gwd-span-jnop">项目列表</span>
     </div>
    </div>
   </h1>
  </div>
  <div class="gwd-div-z0p4">
   <div class="gwd-div-92tu">
    <div class="gwd-div-sr0k">
     <span class="gwd-span-8k0g"><strong>新建项目</strong></span>
    </div>
   </div>
   <ul class="gwd-ul-qm54">
   <c:forEach items="${DashBoard.projectLists }" var="current">
    <li class="gwd-li-9n23 gwd-new-class-ild5">
     <div class="gwd-div-4a1a gwd-new-class-9wla" style="">
      <div class="gwd-div-plh5" style="">
       <c:if test="${UserInformation.loggedin eq true }">
        <a
         href="DashBoard?method=${DashBoard.rqNew }&ProjectType=${current.type}" class=" gwd-new-class-b7yd">${current.projectName }</a>
       </c:if>
       <c:if test="${UserInformation.loggedin eq false }">
        <a href="Register" class=" gwd-new-class-b7yd">申请表</a>

       </c:if>
      </div>
     </div>
    </li>
   </c:forEach>
    <li
     class="gwd-li-9n23 gwd-new-class-ild5 gwd-li-57mn gwd-new-class-3lh5">
     <div class="gwd-div-4a1a gwd-div-0so7 " style="">
      <div class="gwd-div-plh5 gwd-div-10n3" style="">
       <span class="gwd-span-zdy0 gwd-new-class-b7yd">总结表</span>
      </div>
     </div>
    </li>
   </ul>
  </div>
  <div class="gwd-div-yzur" id="itemWrapper">
   <div class="gwd-div-uxog" style="">
    <div class="gwd-div-bbhs">
     <div class="gwd-div-qs26">
      <span class="gwd-span-1i0n">项目标题</span>
     </div>
    </div>
    <div class="gwd-div-e6w0" style="">
     <div class="gwd-div-m266" style="">
      <div class="gwd-div-uvzt">
       <span class="gwd-span-8k0l">项目操作</span>
      </div>
     </div>
    </div>
   </div>
    <div class="gwd-div-zwqo">
   <c:forEach varStatus="tableCount" var="current" items="${UserInformation.projects }">
     <div class="gwd-div-fp6n">
      <div class="gwd-div-3n5x">
       <div class="gwd-div-k39l">
        <span class="gwd-span-s3vu">${current.projectTitle }</span>
       </div>
      </div>
      <div class="gwd-div-tf9b">
       <div class="gwd-div-fl3r gwd-div-dap0 gwd-div-vicb toolbuttom "
        id="view">
        <a class="gwd-a-34nv"></a>
        <div class="gwd-div-sudk">
         <div class="gwd-div-zbs3">
          <a
           href="DashBoard?method=${DashBoard.rqViewdetail }&ProjectUID=${current.projectUID}&ProjectType=${current.projectItem.projectType}">查看</a>
         </div>
        </div>
       </div>
       <c:if test="${current.level.modifiable }">
        <div
         class="gwd-div-fl3r gwd-div-nfla gwd-div-x5uv gwd-div-9opc toolbuttom"
         id="modify">
         <a class="gwd-a-34nv gwd-a-a33j">
          <div class="gwd-div-sudk gwd-div-c6sm">
           <div class="gwd-div-zbs3 gwd-div-yt65">
            <a
             href="DashBoard?method=${DashBoard.rqModify }&ProjectUID=${current.projectUID}&ProjectType=${current.projectItem.projectType}">修改</a>
           </div>
          </div>
         </a>
        </div>
       </c:if>
       <c:if test="${current.level.removable }">
        <div
         class="gwd-div-fl3r gwd-div-nfla gwd-div-pvzo gwd-div-9uu9 gwd-div-s7zy toolbuttom"
         id="delete">
         <div class="gwd-div-sudk gwd-div-c6sm gwd-div-yoyt">
          <div class="gwd-div-zbs3 gwd-div-yt65 gwd-div-mvxd">
           <a
            href="DashBoard?method=${DashBoard.rqDelete }&ProjectUID=${current.projectUID}" class="deletebut">删除</a>
          </div>
         </div>
        </div>
       </c:if>
       <div
        class="gwd-div-fl3r gwd-div-nfla gwd-div-pvzo gwd-div-1sin toolbuttom"
        id="download">
        <a class="gwd-a-34nv gwd-a-a33j gwd-a-dkaf gwd-a-e0hv"></a>
        <div class="gwd-div-sudk gwd-div-c6sm gwd-div-yoyt gwd-div-rctf">
         <div class="gwd-div-zbs3 gwd-div-yt65 gwd-div-mvxd gwd-div-4jpi">
          <a
           href="DashBoard?method=${DashBoard.rqPrintselection }&ProjectUID=${current.projectUID}&ProjectType=${current.projectItem.projectType}">下载</a>
         </div>
        </div>
       </div>
      </div>
     </div>
   </c:forEach>
    </div>
  </div>
 </div>



</body>
</html>