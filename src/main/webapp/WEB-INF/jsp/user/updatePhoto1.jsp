<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->更换头像</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	
	<h1 align="center"><font color="red">更换头像</font></h1><hr>
	<center><img alt="${userId2 }" src="../image/upload/${photo }" width="350" height="350"></center>
	<br>
	<form action="updatePhoto1.do" enctype="multipart/form-data" method="post">
		<center><input type="file" size="20" name="photo"></center><br>
		<center><font color="red">${error2 }</font></center>
		<input type="hidden" name="userId" value="${userId2 }">
		<input type="hidden" name="photo" value="${photo }">
		<center>
			<input type="submit" value="更换" style="background-color:#66CCFF">	
			<input type="button" value="返回" onclick="window.location='myUser.do'" style="background-color:#66CCFF">
		</center>
	</form>
	<%@ include file="../foot.jsp" %>
</body>
</html>