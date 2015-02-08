<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<jsp:setProperty property="*" name="Content" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" Content="text/html; charset=GB18030">
<link rel="stylesheet" type="text/css" href="css/ApplicationEditor.css">
<jsp:include page="commonjsfile.html"></jsp:include>
<script type="text/javascript" src="js/ApplicationEditor.js"></script>
<c:if test="${not empty Content.name }">
	<title>${Content.name }</title>
</c:if>
<c:if test="${empty Content.name }">
	<title>新建</title>
</c:if>
</head>
<body onload="javascript:init();">
	<div id="ContentWrapper">
		<form action="ApplicationEditor.jsp" method="post" id="formId">
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
						<td colspan="8" style="width: 0.8898in;" class="ce1"><p id="sp2">华北电力大学大学生创新创业训练计划立项申请表</p></td>
					</tr>
					<tr class="ro2">
						<td colspan="1" style="width: 0.8898in;" class="ce2"><p>项目名称</p></td>
						<td colspan="6" style="width: 1.1118in;" class="ce8"><h2>
								<a href="javascript:;" class="clickable" id="Title" name="name">${Content.name }</a>
							</h2></td>
					</tr>
					<tr class="ro2">
						<td colspan="2" style="width: 0.8898in;" class="ce2"><p>项目类别</p></td>
						<td colspan="4" style="width: 1.1118in;" class="ce8"><a
							class="choosable" name="projectCategoryForm" href="javascript:;">
								<c:if test="${ContentManager.projectCategoryCreationTrain eq Content.category }">创新实验项目</c:if>
								<c:if test="${ContentManager.projectCategoryBussinessTrain eq Content.category }">创业实验项目</c:if>
								<c:if test="${ContentManager.projectCategoryBussinessPractice  eq Content.category }">创业实践项目</c:if>
								<c:if test="${ContentManager.projectCategoryUndefined eq Content.category }">未定义</c:if></a></td>
					</tr>
					<tr class="ro2">
						<td style="width: 1.1118in;" class="ce8">&nbsp;</td>
						<td style="width: 1.1118in;" class="ce8"><p>姓名</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>院系</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>专业</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>学号</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>分工</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>联系电话</p></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro2 roMemberList">
						<td rowspan="3" style="width: 0.8898in;" class="ce2"
							id="memberInfoTitle" data-nextIndex="2"><p>项目负责人及主要成员</p></td>
						<td style="width: 1.1118in;" class="ce8"><a data-index="0"
							name="name" class="blurthensub" href="javascript:;"
							data-title="memberInfo">${Content.membersList[0].name }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a data-index="0"
							name="name" class="blurthensub" href="javascript:;"
							data-title="memberInfo">${Content.membersList[0].department }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a data-index="0"
							name="name" class="blurthensub" href="javascript:;"
							data-title="memberInfo">${Content.membersList[0].profession }</a></td>
						<td style="width: 1.1118in;" class="ce12"><a data-index="0"
							name="name" class="blurthensub" href="javascript:;"
							data-title="memberInfo">${Content.membersList[0].studentID }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a data-index="0"
							name="name" class="blurthensub" href="javascript:;"
							data-title="memberInfo">${Content.membersList[0].responsibility }</a></td>
						<td style="width: 1.1118in;" class="ce12"><a data-index="0"
							name="name" class="blurthensub" href="javascript:;"
							data-title="memberInfo">${Content.membersList[0].tel }</a></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<c:forEach items="${Content.membersList }" begin="1" var="current"
						varStatus="status">
						<tr class="ro2 roMemberList">
							<td style="width: 1.1118in;" class="ce8"><a
								data-index="${status.index }" name="name" class="blurthensub"
								href="javascript:;" data-title="memberInfo">${current.name }</a></td>
							<td style="width: 1.1118in;" class="ce8"><a data-index="0"
								name="name" class="blurthensub" href="javascript:;"
								data-title="memberInfo">${current.department }</a></td>
							<td style="width: 1.1118in;" class="ce8"><a data-index="0"
								name="name" class="blurthensub" href="javascript:;"
								data-title="memberInfo">${current.profession }</a></td>
							<td style="width: 1.1118in;" class="ce12"><a data-index="0"
								name="name" class="blurthensub" href="javascript:;"
								data-title="memberInfo">${current.studentID }</a></td>
							<td style="width: 1.1118in;" class="ce8"><a data-index="0"
								name="name" class="blurthensub" href="javascript:;"
								data-title="memberInfo">${current.responsibility }</a></td>
							<td style="width: 1.1118in;" class="ce12"><a data-index="0"
								name="name" class="blurthensub" href="javascript:;"
								data-title="memberInfo">${current.tel }</a></td>
							<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
						</tr>
					</c:forEach>
					<tr class="ro2 roMemberList">
						<td style="width: 1.1118in;" class="ce8" colspan="6"><a
							name="memberInfo" class="addable" href="javascript:;">添加</a></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro2">
						<td style="width: 1.1118in;" class="ce8">&nbsp;</td>
						<td style="width: 1.1118in;" class="ce8"><p>姓名</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>院系</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>专业</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>职称</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>指导内容</p></td>
						<td style="width: 1.1118in;" class="ce12"><p>联系电话</p></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro2">
						<td rowspan="1" style="width: 0.8898in;" class="ce2"><p
								id="TutororTitle">指导教师</p></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="name" data-index="0" href="javascript:;" data-title="teacher">${Content.teacher.name }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="department" data-index="0" href="javascript:;"
							data-title="teacher">${Content.teacher.department }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="profession" data-index="0" href="javascript:;"
							data-title="teacher">${Content.teacher.profession }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="professionalTitle" data-index="0" href="javascript:;"
							data-title="teacher">${Content.teacher.professionalTitle }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="responsibility" data-index="0" href="javascript:;"
							data-title="teacher">${Content.teacher.responsibility }</a></td>
						<td style="width: 1.1118in;" class="ce12"><a class="blurthensub"
							name="tel" data-index="0" href="javascript:;" data-title="teacher">${Content.teacher.tel }</a></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro2">
						<td style="width: 1.1118in;" class="ce8">&nbsp;</td>
						<td style="width: 1.1118in;" class="ce8"><p>姓名</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>单位</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>专业</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>职称/职务</p></td>
						<td style="width: 1.1118in;" class="ce8"><p>指导内容</p></td>
						<td style="width: 1.1118in;" class="ce12"><p>联系电话</p></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro2">
						<td rowspan="1" style="width: 0.8898in;" class="ce2"><p>创业实践项目企业导师</p></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="name" data-index="0" href="javascript:;" data-title="tutor">${Content.companyTutor.name }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="department" data-index="0" href="javascript:;"
							data-title="tutor">${Content.companyTutor.department }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="profession" data-index="0" href="javascript:;"
							data-title="tutor">${Content.companyTutor.profession }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="professionalTitle" data-index="0" href="javascript:;"
							data-title="tutor">${Content.companyTutor.professionalTitle }</a></td>
						<td style="width: 1.1118in;" class="ce8"><a class="blurthensub"
							name="responsibility" data-index="0" href="javascript:;"
							data-title="tutor">${Content.companyTutor.responsibility }</a></td>
						<td style="width: 1.1118in;" class="ce12"><a class="blurthensub"
							name="tel" data-index="0" href="javascript:;" data-title="tutor">${Content.companyTutor.tel }</a></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro3">
						<td colspan="3" style="width: 0.8898in;" class="ce2"><p>项目执行时间（起止时间）</p></td>
						<td colspan="4" style="width: 1.1118in;" class="ce2"><p>
								<span class="T1">YearStart</span><span class="T1">年 &nbsp; 月
									&nbsp; 日</span><span class="T2"> 至 &nbsp; &nbsp;&nbsp;</span><span
									class="T1">yearFinish年 &nbsp; 月 &nbsp; &nbsp;日</span>
							</p>
							<p>
								<span class="T1">（注：半年或一年内）</span>
							</p></td>
					</tr>
					<tr class="ro4">
						<td style="width: 0.8898in;" class="ce3"><p>
								选题背景和意义<span class="T1">（应着重说明学生对该项目的自主兴趣所在和原先在该项目上所积累的实践基础和知识基础）</span>
							</p></td>
						<td colspan="6" style="width: 1.1118in;" class="ce9"><textarea
								class="editable" name="backgroundDescription">${Content.backgroundDescription }</textarea></td>
					</tr>
					<tr class="ro5">
						<td style="width: 0.8898in;" class="ce2"><p>
								项目简介<span class="T1">（包括项目内容、主要技术方案和路线、已有工作基础）</span>
							</p></td>
						<td colspan="6" style="width: 1.1118in;" class="ce9"><textarea
								class="editable" name="projectDescriptioin">${Content.projectDescription }</textarea></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro6">
						<td style="width: 0.8898in;" class="ce3"><p>预期成果</p>
							<p>
								<span class="T1">（实验报告、论文、设计、专利、产品、服务等）</span>
							</p></td>
						<td colspan="6" style="width: 1.1118in;" class="ce9"><textarea
								class="editable" name="goalExpectation">${Content.goalExpectation }</textarea></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro7">
						<td style="width: 0.8898in;" class="ce2"><p id="sp1">主要创新点与特色</p></td>
						<td colspan="6" style="width: 1.1118in;" class="ce9"><textarea
								class="editable" name="projectFeatures">${Content.projectFeatures }</textarea></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro8">
						<td style="width: 0.8898in;" class="ce2"><p>配套条件要求</p>
							<p>
								<span class="T1">（包括设备、场地、材料、实验课时及指导教师要求）</span>
							</p></td>
						<td colspan="6" style="width: 1.1118in;" class="ce9"><textarea
								class="editable" name="equipentNeeds">${Content.equipmentNeeds }</textarea></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
					<tr class="ro9">
						<td style="width: 0.8898in;" class="ce2"><p>经费预算</p>
							<p>
								<span class="T1">（包括设备、材料、制作及软件等费用）</span>
							</p></td>
						<td colspan="6" style="width: 1.1118in;" class="ce9"><textarea
								class="editable" name="financialNeed">${Content.financialNeed }</textarea></td>
						<td style="width: 0.7602in;" class="ce5">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</form>
		<input type="text" class="wrapInput" id="protoInput"
			style="display: none"> <a href="javascript:;" id="submitButton">保存</a>
	</div>
</body>
</html>