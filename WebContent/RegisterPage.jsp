<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="commonjsfile.html"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/Register.css">
<script type="text/javascript" src="js/RegisterPage.js"></script>
<title>注册页面</title>
</head>
<body onload="javascript:init();">
	<h1>创新创业项目申报系统</h1>
	<div class="formWrapper">
		<form class="registerForm" action="Register" method="post">
			<table>
				<tr>
					<td><label for="${Register.formRealNameID }">姓名：</label></td>
					<td><input type="text" id="regName"
						name="${Register.formRealNameID }"></td>
				</tr>
				<tr>
					<td><label for="${Register.formIDid }">学号：</label></td>
					<td><input type="text" id="regID" name="${Register.formIDid }"></td>
				</tr>
				<tr>
					<td><label for="${Register.formPWDid }">密码：</label></td>
					<td><input type="password" id="psd"
						name="${Register.formPWDid }"></td>
				</tr>
				<tr>
					<td><label for="cfmpsd">确认密码：</label></td>
					<td><input type="password" id="cfmpsd" name="cfmpsd"></td>
				</tr>
				<c:if test="${Register.statusUserNX eq param.status }">
					<tr>
						<td></td>
						<td style="text-align: center"><span style="color:red">用户已注册或不存在</span></td>
					</tr>
				</c:if>
				<tr>
					<td></td>
					<td><input id="submit" type="submit" disabled value="提交"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>