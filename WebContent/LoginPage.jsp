<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="commonjsfile.html" %>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link rel="stylesheet" type="text/css" href="css/Login.css">
<script src="js/LoginPage.js"></script>
<title>登陆界面</title>
</head>
<body onload="javascript:LoginPageInit();">

	<c:if test="${not empty(invalidID) }">
		<span class="LoginFailPrompt">学号不正确</span>
	</c:if>
	<c:if test="${not empty(invalidPWD) }">
		<span class="LoginFailPrompt">学号或密码输入错误</span>
	</c:if>

	<h1>创新创业项目申报系统</h1>
		<div id="mainMenu">
			<a href="javascript:;" id="view">查看项目</a> <a href="javascript:;"
				id="manage">申报(管理)项目</a>
		</div>
	<div id="forms">
		<div class="formWrapper" id="formManage">
			<form class="loginForm" action="Login" method="post">
				<table>
					<tr>
						<td><span class="formLable">学号</span></td>
						<td><input type="text" name="${LoginManager.formusernameid }"></td>

					</tr>
					<tr>
						<td><span class="formLable">密码</span></td>
						<td><input type="password"
							name="${LoginManager.formpasswordid }"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="登陆"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="${LoginManager.formtypeid }"
							value="${LoginManager.formtypemanage}"></td>
					</tr>
				</table>
			</form>
		</div>

		<div class="formWrapper" id="formViewer">
			<form class="loginForm" action="Login" method="post">
				<table>
					<tr>
						<td><span class="formLabel">请输入学号</span></td>
						<td><input type="text" name="${LoginManager.formusernameid }"
							value="${LoginManager.formtypeviewer }"></td>
					</tr>
					<tr>
						<td><span class="formLabel">请输入姓名</span></td>
						<td><input type="text" name="${LoginManager.formrealnameid }">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="登陆"></td>
					</tr>
					<tr>
						<td><input type="hidden" name="${LoginManager.formtypeid}"
							value="${LoginManager.formtypeviewer}"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>

</body>
</html>
