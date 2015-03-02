<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目列表</title>
</head>
<body>
	<div class="topmenu">
		<c:if test="${UserInformation.loggedin eq true }">
			<a
				href="DashBoard?method=${DashBoard.rqNew }&ProjectType=${DashBoard.projectTypeApplication}">申请新项目</a>
		</c:if>
		<c:if test="${UserInformation.loggedin eq false }">
			<a href="Register">申请新项目</a>

		</c:if>

	</div>
	<div>
		<table id="ProjectItems">
			<thead>
				<tr>
					<td>&nbsp</td>
					<td>序号</td>
					<td>标题</td>
					<td>操作</td>
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
										href="DashBoard?method=${DashBoard.rqDelete }&ProjectUID=${current.projectUID}">删除</a>
								</c:if>
								<c:if test="${current.level.modifiable }">
									<a
										href="DashBoard?method=${DashBoard.rqModify }&ProjectUID=${current.projectUID}&ProjectType=${current.projectItem.projectType}">修改</a>
								</c:if>
								<a
									href="DashBoard?method=${DashBoard.rqPrintselection }&ProjectUID=${current.projectUID}">下载</a>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>