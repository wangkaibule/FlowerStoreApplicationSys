<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="commonjsfile.html"%>
<script type="text/javascript" src="js/register.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>ע��ҳ��</title>
</head>
<body onload="javascript:init();">
	<form class="cregister" id="iregister" action="Register" method="post">
		<p>������</p>
		<input type="text" name="${Register.formRealNameID }"><br>
		<p>ѧ�ţ�</p>
		<input type="text" name="${Register.formIDid }"><br>
		<p>���룺</p>
		<input id="psd" type="password" name="${Register.formPWDid }"><br>
		<p>ȷ�����룺</p>
		<input id="cfmpsd" type="password"><br> <input id="submit"
			type="submit" disabled value="�ύ">
	</form>
	<c:if test="${Register.statusUserNX eq param.status }">
	 <span>�û���ע��򲻴���</span>
	 </c:if>
</body>
</html>