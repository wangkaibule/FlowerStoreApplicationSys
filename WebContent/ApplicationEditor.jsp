<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setCharacterEncoding("UTF-8");
%>



<%-- DO NOT try to change format of this page if it is not necessary !!!--%>




<jsp:setProperty property="*" name="Content" />
<jsp:useBean id="project" class="com.RS.model.project.application.ApplicationProject"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" Content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/ApplicationEditor.css">
<jsp:include page="commonjsfile.html"></jsp:include>
<script src="js/jquery-ui.min.js"></script>
<script src="js/ApplicationEditor.js"></script>
<script type="text/javascript">
	<c:if test="${param.isView }">
	var handlerFlag = false;
	</c:if>
	<c:if test="${not param.isView}">
	var handlerFlag = true;
	</c:if>
</script>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<c:if test="${not empty Content.name }">
 <title><c:out value="${Content.name }" /></title>
</c:if>
<c:if test="${empty Content.name }">
 <title>新建</title>
</c:if>
</head>
<body onload="javascript:init();">
 <div id="ContentWrapper">
  <form action="ApplicationEditor.jsp" method="post" id="theForm">
   <table border="0" cellspacing="0" cellpadding="0" class="ta1">
    <colgroup>
     <col width="99">
     <col width="123">
     <col width="123">
     <col width="123">
     <col width="123">
     <col width="123">
     <col width="123">
     <col width="84">
    </colgroup>
    <tbody>
     <tr class="ro1">
      <td colspan="7" style="width: 0.8898in;" class="ce1"><p id="sp2">华北电力大学大学生创新创业训练计划立项申请表</p></td>
     </tr>
     <tr class="ro2">
      <td colspan="1" style="width: 0.8898in;" class="ce2"><p>项目名称</p></td>
      <td colspan="5" style="width: 1.1118in;" class="ce8"><h2>
        <a href="javascript:;" class="clickable" id="Title" name="name"><c:out
          value="${Content.name }" /></a>
       </h2></td>
      <td></td>
     </tr>
     <tr class="ro2">
      <td colspan="2" style="width: 0.8898in;" class="ce2"><p>项目类别</p></td>
      <td colspan="4" style="width: 1.1118in;" class="ce8"><a
       class="choosable" name="projectCategoryForm" href="javascript:;">
        <c:if
         test="${project.categoryCreationTrain eq Content.category }">创新实验项目</c:if>
        <c:if
         test="${project.categoryBussinessTrain eq Content.category }">创业实验项目</c:if>
        <c:if
         test="${project.categoryBussinessPractice  eq Content.category }">创业实践项目</c:if>
        <c:if
         test="${project.categoryUndefined eq Content.category }">未定义</c:if>
      </a></td>
      <td></td>
     </tr>
     <tr class="ro2">
      <td style="width: 1.1118in;" class="ce8">&nbsp;</td>
      <td style="width: 1.1118in;" class="ce8"><p>姓名</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>院系</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>专业</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>学号</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>分工</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>联系电话</p></td>
     </tr>
     <tr class="ro2 roMemberList">
      <td rowspan="${fn:length(Content.membersList )}"
       style="width: 0.8898in;" class="ce2" id="memberInfoTitle"
       data-nextIndex="2"><p>项目负责人及主要成员</p></td>
      <td style="width: 1.1118in;" class="ce8"><a data-index="0"
       name="name" class="blurthensub" href="javascript:;"
       data-title="memberInfo" group="a" counter="a"><c:out
         value="${Content.membersList[0].name }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a data-index="0"
       name="department" class="blurthensub" href="javascript:;"
       data-title="memberInfo" group="a"><c:out
         value="${Content.membersList[0].department }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a data-index="0"
       name="profession" class="blurthensub" href="javascript:;"
       data-title="memberInfo" group="a"><c:out
         value="${Content.membersList[0].profession }" /></a></td>
      <td style="width: 1.1118in;" class="ce12"><a data-index="0"
       name="studentID" class="blurthensub" href="javascript:;"
       data-title="memberInfo" group="a"><c:out
         value="${Content.membersList[0].studentID }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a data-index="0"
       name="responsibility" class="blurthensub" href="javascript:;"
       data-title="memberInfo" group="a"><c:out
         value="${Content.membersList[0].responsibility }" /></a></td>
      <td style="width: 1.1118in;" class="ce12"><a data-index="0"
       name="tel" class="blurthensub" href="javascript:;"
       data-title="memberInfo" group="a"><c:out
         value="${Content.membersList[0].tel }" /></a></td>
     </tr>
     <c:forEach items="${Content.membersList }" begin="1" var="current"
      varStatus="status">
      <tr class="ro2 roMemberList">
       <td style="width: 1.1118in;" class="ce8"><a
        data-index="<c:out value="${status.index }"/>" name="name"
        class="blurthensub" href="javascript:;" data-title="memberInfo"
        group="a" counter="a"><c:out value="${current.name }" /></a></td>
       <td style="width: 1.1118in;" class="ce8"><a
        data-index="<c:out value="${status.index }"/>" name="department"
        class="blurthensub" href="javascript:;" data-title="memberInfo"
        group="a"><c:out value="${current.department }" /></a></td>
       <td style="width: 1.1118in;" class="ce8"><a
        data-index="<c:out value="${status.index }"/>" name="profession"
        class="blurthensub" href="javascript:;" data-title="memberInfo"
        group="a"><c:out value="${current.profession }" /></a></td>
       <td style="width: 1.1118in;" class="ce12"><a
        data-index="<c:out value="${status.index }"/>" name="studentID"
        class="blurthensub" href="javascript:;" data-title="memberInfo"
        group="a"><c:out value="${current.studentID }" /></a></td>
       <td style="width: 1.1118in;" class="ce8"><a
        data-index="<c:out value="${status.index }"/>"
        name="responsibility" class="blurthensub" href="javascript:;"
        data-title="memberInfo" group="a"><c:out
          value="${current.responsibility }" /></a></td>
       <td style="width: 1.1118in;" class="ce12"><a
        data-index="<c:out value="${status.index }"/>" name="tel"
        class="blurthensub" href="javascript:;" data-title="memberInfo"
        group="a"><c:out value="${current.tel }" /></a></td>
      </tr>
     </c:forEach>
     <tr style="visible:none" class="addable hiddenAddable" group="a" name="memberInfo"></tr>
     
     <tr class="ro2">
      <td style="width: 1.1118in;" class="ce8">&nbsp;</td>
      <td style="width: 1.1118in;" class="ce8"><p>姓名</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>院系</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>专业</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>职称</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>指导内容</p></td>
      <td style="width: 1.1118in;" class="ce12"><p>联系电话</p></td>
     </tr>
     <tr class="ro2">
      <td rowspan="1" style="width: 0.8898in;" class="ce2"><p
        id="TutororTitle">指导教师</p></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="name" data-index="0" href="javascript:;" data-title="teacher"><c:out
         value="${Content.teacher.name }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="department" data-index="0" href="javascript:;"
       data-title="teacher"><c:out
         value="${Content.teacher.department }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="profession" data-index="0" href="javascript:;"
       data-title="teacher"><c:out
         value="${Content.teacher.profession }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="professionalTitle" data-index="0" href="javascript:;"
       data-title="teacher"><c:out
         value="${Content.teacher.professionalTitle }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="responsibility" data-index="0" href="javascript:;"
       data-title="teacher"><c:out
         value="${Content.teacher.responsibility }" /></a></td>
      <td style="width: 1.1118in;" class="ce12"><a class="blurthensub"
       name="tel" data-index="0" href="javascript:;" data-title="teacher"><c:out
         value="${Content.teacher.tel }" /></a></td>
     </tr>
     <tr class="ro2">
      <td style="width: 1.1118in;" class="ce8">&nbsp;</td>
      <td style="width: 1.1118in;" class="ce8"><p>姓名</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>单位</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>专业</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>职称/职务</p></td>
      <td style="width: 1.1118in;" class="ce8"><p>指导内容</p></td>
      <td style="width: 1.1118in;" class="ce12"><p>联系电话</p></td>
     </tr>
     <tr class="ro2">
      <td rowspan="1" style="width: 0.8898in;" class="ce2"><p>创业实践项目企业导师</p></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="name" data-index="0" href="javascript:;" data-title="tutor"><c:out
         value="${Content.companyTutor.name }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="department" data-index="0" href="javascript:;"
       data-title="tutor"><c:out
         value="${Content.companyTutor.department }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="profession" data-index="0" href="javascript:;"
       data-title="tutor"><c:out
         value="${Content.companyTutor.profession }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="professionalTitle" data-index="0" href="javascript:;"
       data-title="tutor"><c:out
         value="${Content.companyTutor.professionalTitle }" /></a></td>
      <td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
       name="responsibility" data-index="0" href="javascript:;"
       data-title="tutor"><c:out
         value="${Content.companyTutor.responsibility }" /></a></td>
      <td style="width: 1.1118in;" class="ce12"><a class="blurthensub"
       name="tel" data-index="0" href="javascript:;" data-title="tutor"><c:out
         value="${Content.companyTutor.tel }" /></a></td>
     </tr>
     <tr class="ro3">
      <td colspan="3" rowspan="4" style="width: 0.8898in;" class="ce2"><p>项目执行时间（起止时间）</p></td>
      <td colspan="4" style="width: 1.1118in; border-bottom: none;"
       class="ce2"><a class="datable" href="javascript:;"
       name="startTime"><c:out value="${Content.startTime }" /> <c:if
         test="${empty(Content.startTime) }">&nbsp&nbsp&nbsp年&nbsp&nbsp&nbsp月&nbsp&nbsp&nbsp日</c:if></a></td>
     </tr>
     <tr>
      <td colspan="4"
       style="width: 1.1118in; border-top: none; border-bottom: none;"
       class="ce2">至</td>
     </tr>
     <tr>
      <td colspan="4"
       style="width: 1.1118in; border-top: none; border-bottom: none;"><a
       class="datable" href="javascript:;" name="finishTime"><c:out
         value="${Content.finishTime }" /> <c:if
         test="${empty(Content.startTime) }">&nbsp&nbsp&nbsp年&nbsp&nbsp&nbsp月&nbsp&nbsp&nbsp日</c:if></a></td>
     </tr>
     <tr>
      <td colspan="4" style="width: 1.1118in; border-top: none;"><p>
        <span class="T1">（注：半年或一年内）</span>
       </p></td>
     </tr>
     <tr class="ro4">
      <td style="width: 0.8898in;" class="ce3"><p>
        选题背景和意义<span class="T1">（应着重说明学生对该项目的自主兴趣所在和原先在该项目上所积累的实践基础和知识基础）</span>
       </p></td>
      <td colspan="6" style="width: 1.1118in;" class="editable"
       name="backgroundDescription"><pre style="text-align: left">
        <c:out value="${Content.backgroundDescription }" />
       </pre></td>
     </tr>
     <tr class="ro5">
      <td style="width: 0.8898in;" class="ce2"><p>
        项目简介<span class="T1">（包括项目内容、主要技术方案和路线、已有工作基础）</span>
       </p></td>
      <td colspan="6" style="width: 1.1118in;" name="projectDescription"
       class="editable"><pre style="text-align: left">
        <c:out value="${Content.projectDescription }" />
       </pre></td>
     </tr>
     <tr class="ro6">
      <td style="width: 0.8898in;" class="ce3"><p>预期成果</p>
       <p>
        <span class="T1">（实验报告、论文、设计、专利、产品、服务等）</span>
       </p></td>
      <td name="goalExpectation" colspan="6" style="width: 1.1118in;"
       class="editable"><pre style="text-align: left">
        <c:out value="${Content.goalExpectation }" />
       </pre></td>
     </tr>
     <tr class="ro7">
      <td style="width: 0.8898in;" class="ce2"><p id="sp1">主要创新点与特色</p></td>
      <td name="projectFeatures" colspan="6" style="width: 1.1118in;"
       class="editable"><pre style="text-align: left">
        <c:out value="${Content.projectFeatures }" />
       </pre></td>
     </tr>
     <tr class="ro8">
      <td style="width: 0.8898in;" class="ce2"><p>配套条件要求</p>
       <p>
        <span class="T1">（包括设备、场地、材料、实验课时及指导教师要求）</span>
       </p></td>
      <td colspan="6" name="equipmentNeeds" style="width: 1.1118in;"
       class="editable"><pre style="text-align: left">
        <c:out value="${Content.equipmentNeeds }" />
       </pre></td>
     </tr>
     <tr class="ro9">
      <td style="width: 0.8898in;" class="ce2"><p>经费预算</p>
       <p>
        <span class="T1">（包括设备、材料、制作及软件等费用）</span>
       </p></td>
      <td name="financialNeed" colspan="6" style="width: 1.1118in;"
       class="editable"><pre style="text-align: left">
        <c:out value="${Content.financialNeed }" />
       </pre></td>
     </tr>
     <tr class="ro10">
      <td rowspan="2" style="text-align: left; width: 0.8898in;"
       class="ce2"><p>指导教师意见</p></td>
      <td colspan="6"
       style="text-align: right; border-bottom: none; width: 1.1118in;"
       class="ce9"><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>
       <p>&nbsp;</p></td>
     </tr>
     <tr>
      <td colspan="6" style="text-align: right; border-top: none;">
       <p style="text-align: right;">签字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
       <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 年 &nbsp; 月 &nbsp; 日</p>
      </td>
     </tr>
     <tr class="ro10">
      <td rowspan="2" style="text-align: left; width: 0.8898in;"
       class="ce2"><p>创业实践项目企业导师意见</p></td>
      <td colspan="6"
       style="text-align: right; border-bottom: none; width: 1.1118in;"
       class="ce9"><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>
       <p>&nbsp;</p></td>
     </tr>
     <tr>
      <td colspan="6" style="text-align: right; border-top: none;">
       <p style="text-align: right;">签字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
       <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 年 &nbsp; 月 &nbsp; 日</p>
      </td>
     </tr>
     <tr class="ro10">
      <td rowspan="2" style="text-align: left; width: 0.8898in;"
       class="ce2"><p>学校专家组评审意见</p></td>
      <td colspan="6"
       style="text-align: right; border-bottom: none; width: 1.1118in;"
       class="ce9"><p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>
       <p>&nbsp;</p></td>
     </tr>
     <tr>
      <td colspan="6" style="text-align: right; border-top: none;">
       <p style="text-align: right;">签字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
       <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 年 &nbsp; 月 &nbsp; 日</p>
      </td>
     </tr>
     <tr class="ro11">
      <td style="text-align: left; width: 0.8898in; height: 2in"
       class="ce2"><p>备注</p></td>
      <td colspan="6" style="text-align: left; width: 1.1118in;"
       class="ce9">&nbsp;</td>
     </tr>
    </tbody>
   </table>
   <div class="scrollingbox">

    <div id="boxtitle">
     <div>选项</div>
     <div class="draggableTag"></div>
    </div>
    <div id="boxcontent">
     <div id="contentTableWrapper">
      <table class="noborder">
       <tr>
        <td><div id="back" class="toolbut">回到项目列表</div></td>
       </tr>
       <c:if test="${not param.isView }">
        <tr>
         <td><div class="toolbut addable" id="addMember" group="a"
           >添加</div></td>
        </tr>
        <tr>
         <td><div class="toolbut" id="deleteMember" group="a"
           name="memberInfo">删除最后一个成员</div></td>
        </tr>
        <tr>
         <td><div class="toolbut" id="subbut">提交</div></td>
        </tr>
       </c:if>
      </table>
     </div>
    </div>
   </div>
  </form>
  <input type="text" class="wrapInput" id="protoInput"
   style="display: none">
  <textarea class="wrapText" id="protoTextarea" style="display: none"
   rows="10"></textarea>
 </div>
</body>
</html>