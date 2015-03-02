<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="commonjsfile.html"%>
<script type="text/javascript" src="js/register.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>注册页面</title>
</head>
<body onload="javascript:init();">
	<form class="cregister" id="iregister" action="Register" method="post">
		<p>姓名：</p>
		<input type="text" name="${Register.formRealNameID }"><br>
		<p>学号：</p>
		<input type="text" name="${Register.formIDid }"><br>
		<p>密码：</p>
		<input id="psd" type="password" name="${Register.formPWDid }"><br>
		<p>确认密码：</p>
		<input id="cfmpsd" type="password"><br> <input id="submit"
			type="submit" disabled value="提交">
	</form>
	<c:if test="${Register.statusUserNX eq param.status }">
	 <span>用户已注册或不存在</span>
	 </c:if>
</body>
</html>