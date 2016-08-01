<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->更换图书封面</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	<%
		String error2 = "";
		String bookId = "";
		String photo = "";
		if(null != session.getAttribute("bookId1")) {
			bookId = (String)session.getAttribute("bookId1");
			photo = (String)session.getAttribute("photo1");
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
		if(null != request.getAttribute("error2")) {
			error2 = (String)request.getAttribute("error2");
		}
		

	%>
	
	<h1 align="center"><font color="red">更换图书封面</font></h1><hr>
	<center><img alt="${bookId}" src="../image/upload/${photo }" width="350" height="500"></center>
	<br>
	<form action="updatePhoto.do" enctype="multipart/form-data" method="post">
		<center><input type="file" size="20" name="file"></center><br>
		<center><font color="red">${error2 }</font></center>
		<input type="hidden" name="bookId" value="${bookId}">
		<input type="hidden" name="picture" value="${photo }">
		<center>
			<input type="submit" value="更换" style="background-color:#66CCFF">
			<input type="button" value="返回" onclick="window.location='manageBook.do?action=update&bookId=<%=bookId %>'" style="background-color:#66CCFF">	
		</center>
	</form>
	<%@ include file="../foot.jsp" %>
</body>
</html>