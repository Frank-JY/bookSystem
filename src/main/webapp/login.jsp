<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>

<script type="text/javascript">
function limit() {
	var regUserName = /^(\w){3,15}$/;
	var regPassword = /^(\w){6,12}$/;
	var userName = document.getElementsByName("userName")[0].value;
	var password = document.getElementsByName("password")[0].value;
	if(!regUserName.test(userName)) {
		alert("用户名的长度应为3~15!");
		return false;
	}
	if(!regPassword.test(password)) {
		alert("密码的长度应为6~12!");
		return false;
	}
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统登录页面</title>
</head>
<body>	
<%@ include file="loginHead.jsp" %>
	<%
		String userName = "";
		String password = "";
		String identity = "";
		String error1 = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().equals("userName"))
	              {
	                   userName = c.getValue();
	              }
	            if(c.getName().equals("password"))
	              {
	                   password =  c.getValue();
	              }
	            if(c.getName().equals("identity"))
	              {
	                   identity =  URLDecoder.decode(c.getValue(),"utf-8");
	              }
			}
		}
		if(null != request.getAttribute("userName")) {
			userName = (String)request.getAttribute("userName");
		}
		if(null != request.getAttribute("password")) {
			password = (String)request.getAttribute("password");
		}
		if(null != request.getAttribute("identity")) {
			identity = (String)request.getAttribute("identity");
		}
		if(null != request.getAttribute("error1")) {
			error1 = (String)request.getAttribute("error1");
		}
		
	
	%>
	<h1 align="center">登录页面</h1>
	<hr>
	<form action="LoginServlet" method="post">
	<table align="center" style="font-size: 20px;">
	<tr>
		<td>
	     	用户名：
	    </td>
	    <td>
	    	<input type="text" name="userName" value="<%=userName %>" id="userName">
	    </td>
	</tr>
	<tr>
		<td>
			密码:
		</td>
		<td>
			<input type="password" name="password" value="<%=password %>">
		</td>
	</tr>
	<tr>
		<td>
			身份：
		</td>
		<td>
			管理员<input type="radio" name="identity" value="管理员" checked="checked" >&nbsp;
			学生<input type="radio" name="identity" value="学生" <%if(identity.equals("学生")) {%> checked="checked" <%} %>>
		</td>
	</tr>
	
	</table>
	<center>
	<font color="red"><%=error1 %></font><br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="checkbox" name="checkBox" checked="checked">记住密码&nbsp;
	<br>
	<input type="submit" value="登录" onclick="return limit();">&nbsp;&nbsp;&nbsp;
	<input type="button" value="注册" onclick="window.location='register.jsp'">
	</center>
	</form>
<%@ include file="foot.jsp" %>

</body>
</html>