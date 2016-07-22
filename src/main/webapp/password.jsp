<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.User" %>
<script type="text/javascript">
function limit() {
	var regPassword = /^(\w){6,12}$/;
	var oldPassword = document.getElementsByName("oldPassword")[0].value;
	var newPassword = document.getElementsByName("newPassword")[0].value;
	var newPasswordAgain = document.getElementsByName("newPasswordAgain")[0].value;
	if(!regPassword.test(oldPassword)) {
		alert("旧密码的长度应为6~12!");
		return false;
	}
	if(!regPassword.test(newPassword)) {
		alert("新密码的长度应为6~12!");
		return false;
	}
	if(newPassword != newPasswordAgain) {
		alert("两次密码输入不一致!");
		return false;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->修改密码</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<%
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		String error = "";
		if(null != request.getAttribute("error")) {
			error = (String)request.getAttribute("error");
		}
	%>
	<h1 align="center">修改密码</h1><hr>
	<form action="PasswordServlet" method="post">
		<table align="center" style="font-size: 20px;">
			<tr>
				<td>用户名：</td>
				<td><%=userName %></td>
				<td><input type="hidden" name="userName" value="<%=userName %>"></td>
			</tr>
			<tr>
				<td>旧密码：</td>
				<td><input type="password" name="oldPassword" ></td>
				<td>
	    			<font color="red"><%=error %></font>
	   		 	</td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" name="newPassword" ></td>
			</tr>
			<tr>
				<td>确认新密码：</td> 
				<td><input type="password" name="newPasswordAgain" ></td>
			</tr>
		</table>
		<br>
		<div align="center">
		<input type="submit" value="修改" onclick="return limit();" style="background-color:#66FFAA">&nbsp;&nbsp;&nbsp;
		<input type="button" value="返回" onclick="window.location='main.jsp'" style="background-color:#66FFAA">
		</div>
	</form>
	<%@ include file="foot.jsp" %>
</body>
</html>