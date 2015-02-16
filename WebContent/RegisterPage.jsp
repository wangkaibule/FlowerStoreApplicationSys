<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="commonjsfile.html"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Register.css">
<script type="text/javascript" src="js/RegisterPage.js"></script>
<title>注册页面</title>
</head>
<body>
	<h1>创新创业项目申报系统</h1>
	<div class="formWrapper">
		<form class="registerForm" action="Register" method="post">
			<table>
				<tr>
					<td><label for="${RegisterManager.formRealNameID }">姓名：</label></td>
					<td><input type="text" id="regName"
						name="${RegisterManager.formRealNameID }"></td>
				</tr>
				<tr>
					<td><label for="${RegisterManager.formIDid }">学号：</label></td>
					<td><input type="text" id="regID"
						name="${RegisterManager.formIDid }"></td>
				</tr>
				<tr>
					<td><label for="${RegisterManager.formPWDid }">密码：</label></td>
					<td><input type="password" id="psd"
						name="${RegisterManager.formPWDid }"></td>
				</tr>
				<tr>
					<td><label for="cfmpsd">确认密码：</label></td>
					<td><input type="password" id="cfmpsd" name="cfmpsd"></td>
				</tr>
				<tr>
					<td></td>
					<td><input id="submit" type="submit" disabled value="提交"></td>
			</table>
		</form>
		<c:if test="${Register.statusUserNX eq param.status }">
 			<span>用户已注册或不存在</span>
 		</c:if>
	</div>
</body>
</html>