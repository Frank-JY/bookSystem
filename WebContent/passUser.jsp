<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* " %>
<script type="text/javascript">
	function pass() {
		var pass = window.confirm("确定通过申请吗？");
		return pass;
	} 
	function reject() {
		var reject = window.confirm("确定不同意此申请吗？");
		return reject;
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->注册申请处理</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<%
		Iterator<Map.Entry<Student,User>> qs1 = null;
		Iterator<Map.Entry<Manager,User>> qs2 = null;
	if(null != request.getAttribute("studentMap")) {
		@SuppressWarnings("unchecked")
		Map<Student,User> studentMap = (Map<Student,User>)request.getAttribute("studentMap");
		@SuppressWarnings("unchecked")
		Map<Manager,User> managerMap = (Map<Manager,User>)request.getAttribute("managerMap");
		Set<Map.Entry<Student,User>> studentSet = studentMap.entrySet();
		Set<Map.Entry<Manager,User>> managerSet = managerMap.entrySet();
		qs1 = studentSet.iterator();
		qs2 = managerSet.iterator();
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	%>
	
	<h1 align="center">用户注册申请处理</h1><hr>
	<table border="1" align="center" style="background-color:#88FFAA">
	<tr>	
			<th width="50" ></th>
			<th width="100" >用户名</th>
			<th width="150">学号(职工号)</th>
			<th width="80">姓名</th>
			<th width="220">邮箱</th>
			<th width="80">账号身份</th>
			<th width="80">申请状态</th>
			<th width="100" align="center">操作</th>
		</tr>
	<%
		int i = 1;
		while(qs1.hasNext())
		{
			Map.Entry<Student,User> set1 = qs1.next();
			Student student = set1.getKey();
			User user = set1.getValue();
	%>
		<tr>
			<td><%=i++ %></td>
			<td><%=user.getUserName() %></td>
			<td><%=student.getStudentId() %></td>
			<td><%=student.getStudentName() %></td>
			<td><%=student.getEmail() %></td>
			<td><%=user.getIdentity() %></td>
			<td><%=user.getStatus() %></td>
			<td>
				<a href="PassUserActionServlet?action=pass&userName=<%=user.getUserName() %>" onclick="return pass();">同意</a>&nbsp;&nbsp;
				<a href="PassUserActionServlet?action=reject&userName=<%=user.getUserName() %>" onclick="return reject();">不同意</a>
			</td>
		</tr>
	
	<%
		}
	%>
	
	<%
		while(qs2.hasNext())
		{
			Map.Entry<Manager,User> set2 = qs2.next();
			Manager manager = set2.getKey();
			User user = set2.getValue();
	%>
		<tr>
			<td><%=i++ %></td>
			<td><%=user.getUserName() %></td>
			<td><%=manager.getManagerId() %></td>
			<td><%=manager.getManagerName() %></td>
			<td><%=manager.getEmail() %></td>
			<td><%=user.getIdentity() %></td>
			<td><%=user.getStatus() %></td>
			<td>
				<a href="PassUserActionServlet?action=pass&userName=<%=user.getUserName() %>" onclick="return pass();">同意</a>&nbsp;&nbsp;
				<a href="PassUserActionServlet?action=reject&userName=<%=user.getUserName() %>" onclick="return reject();">不同意</a>
			</td>
		</tr>
	
	<%
		}
	%>	
	
	</table>
	<br>
		<center>
			<a href="Login1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
		</center>
	<%@ include file="foot.jsp" %>

	
</body>
</html>