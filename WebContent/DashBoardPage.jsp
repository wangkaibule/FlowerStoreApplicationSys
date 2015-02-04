<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��Ŀ�б�</title>
</head>
<body>
	<div class="topmenu">
		<c:if test="${UserInformation.loggedin eq true }">
			<a
				href="DashBoard?method=${DashBoard.rqNew }&ProjectType=${DashBoard.projectTypeApplication}">��������Ŀ</a>
		</c:if>
		<c:if test="${UserInformation.loggedin eq false }">
			<a href="register">��������Ŀ</a>

		</c:if>

	</div>
	<div>
		<table id="ProjectItems">
			<thead>
				<tr>
					<td>&nbsp</td>
					<td>���</td>
					<td>����</td>
					<td>����</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach varStatus="tableCount" var="current"
					items="${UserInformation.projects }">
					<tr>
						<td><input type="checkbox" name="${current.projectUID }"></td>
						<td>
							<div class="seriNum">${tableCount.index }</div>
						</td>
						<td>
							<div class="projectThe">${current.projectTitle }</div>
						</td>
						<td><div class="ProjectToolkit">
								<c:if test="${current.level.removable }">
									<a
										href="DashBoard?method=${DashBoard.rqDelete }&ProjectUID=${current.projectUID}">ɾ��</a>
								</c:if>
								<c:if test="${current.level.modifiable }">
									<a
										href="DashBoard?method=${DashBoard.rqModify }&ProjectUID=${current.projectUID}&ProjectType=${current.projectItem.projectType}">�޸�</a>
								</c:if>
								<a
									href="DashBoard?method=${DashBoard.rqPrintselection }&ProjectUID=${current.projectUID}">����</a>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>