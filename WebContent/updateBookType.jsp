<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*" %>
<script type="text/javascript">
function limit() {
	var regTypeName = /^[\u4e00-\u9fa5]{2,5}类$/;
	var typeName = document.getElementsByName("typeName")[0].value;
	if(!regTypeName.test(typeName)) {
		alert("图书类型格式为：××类");
		return false;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->修改图书类型</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<%	BookType bookType = new BookType();
		if(null != request.getAttribute("bookType")){
			bookType = (BookType)request.getAttribute("bookType");
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	%>
	<h1 align="center"><font color="red">修改图书类型</font></h1><hr>
	<center>
		<form action="ManageBookType1Servlet?action=update1" method="post">
		<table style="font-size: 20px;">
			<tr>
				<td>图书类型编号：</td>
				<td><%=bookType.getTypeId() %></td>
				<td><input type="hidden" name="typeId" value="<%=bookType.getTypeId() %>"></td>
			</tr>
			<tr>
				<td>图书类型：</td>
				<td><input type="text" name="typeName" value="<%=bookType.getTypeName() %>"></td>
			</tr>
		</table>
			<input type="submit" value="修改" onclick="return limit();">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" onclick="window.location='ManageBookTypeServlet'" style="background-color:#66CCFF">
		</form>
	</center>
	<%@ include file="foot.jsp" %>
</body>
</html>