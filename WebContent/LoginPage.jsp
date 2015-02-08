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
<title>��½����</title>
</head>
<body onload="javascript:LoginPageInit();">

	<c:if test="${not empty(invalidID) }">
		<span class="LoginFailPrompt">ѧ�Ų���ȷ</span>
	</c:if>
	<c:if test="${not empty(invalidPWD) }">
		<span class="LoginFailPrompt">ѧ�Ż������������</span>
	</c:if>

	<h1>���´�ҵ��Ŀ�걨ϵͳ</h1>
		<div id="mainMenu">
			<a href="javascript:;" id="view">�鿴��Ŀ</a> <a href="javascript:;"
				id="manage">�걨(����)��Ŀ</a>
		</div>
	<div id="forms">
		<div class="formWrapper" id="formManage">
			<form class="loginForm" action="Login" method="post">
				<table>
					<tr>
						<td><span class="formLable">ѧ��</span></td>
						<td><input type="text" name="${LoginManager.formusernameid }"></td>

					</tr>
					<tr>
						<td><span class="formLable">����</span></td>
						<td><input type="password"
							name="${LoginManager.formpasswordid }"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="��½"></td>
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
						<td><span class="formLabel">������ѧ��</span></td>
						<td><input type="text" name="${LoginManager.formusernameid }"
							value="${LoginManager.formtypeviewer }"></td>
					</tr>
					<tr>
						<td><span class="formLabel">����������</span></td>
						<td><input type="text" name="${LoginManager.formrealnameid }">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="��½"></td>
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
