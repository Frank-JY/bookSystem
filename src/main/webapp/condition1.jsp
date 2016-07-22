<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多条件查询</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<h1 align="center">多条件查询书籍</h1><hr>
	<form action="Condition1Servlet" method="post">
	<table align="center" style="font-size: 20px;">
		<tr>
			<td>图书编号</td>
			<td><input type="text" name="bookId"></td>
		</tr>
		<tr>
			<td>图书名</td>
			<td><input type="text" name="bookName"></td>
		</tr>
		<tr>
			<td>作者</td>
			<td><input type="text" name="author"></td>
		</tr>
		<tr>
			<td>出版社</td>
			<td><input type="text" name="publishers"></td>
		</tr>
		<tr>
			<td>图书类型</td>
			<td><input type="text" name="typeName"></td>
		</tr>
	</table>
	<br>
	<center>
	<%
		String value = "";
		if(null != request.getParameter("value")) {
			value = request.getParameter("value");
			if(value.equals("2") && session.getAttribute("identity").equals("学生")) {
				response.sendRedirect("ban.jsp");
				return;
			}
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	%>
		<input type="hidden" name="value" value="<%=value %>">
		<input type="submit" value="查询" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="返回" onclick="window.location='Login1Servlet'" style="background-color:#66CCFF">
	</center>
	</form>
	
	
<%@ include file="foot.jsp" %>	

</body>
</html>