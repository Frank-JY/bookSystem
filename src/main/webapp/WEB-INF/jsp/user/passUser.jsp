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
<%@ include file="../head.jsp" %>
	<%
		Iterator<User> qs1 = null;
		Iterator<User> qs2 = null;
	if(null != request.getAttribute("studentList")) {
		@SuppressWarnings("unchecked")
		List<User> studentList = (List<User>)request.getAttribute("studentList");
		@SuppressWarnings("unchecked")
		List<User> managerList = (List<User>)request.getAttribute("managerList");
		qs1 = studentList.iterator();
		qs2 = managerList.iterator();
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
			User user1 = qs1.next();
			Student student = user1.getStudent();
	%>
		<tr>
			<td><%=i++ %></td>
			<td><%=user1.getUserName() %></td>
			<td><%=student.getStudentId() %></td>
			<td><%=student.getStudentName() %></td>
			<td><%=student.getEmail() %></td>
			<td><%=user1.getIdentity() %></td>
			<td><%=user1.getStatus() %></td>
			<td>
				<a href="passUserAction.do?action=pass&userName=<%=user1.getUserName() %>" onclick="return pass();">同意</a>&nbsp;&nbsp;
				<a href="passUserAction.do?action=reject&userName=<%=user1.getUserName() %>" onclick="return reject();">不同意</a>
			</td>
		</tr>
	
	<%
		}
	%>
	
	<%
		while(qs2.hasNext())
		{
			User user2 = qs2.next();
			Manager manager = user2.getManager();
	%>
		<tr>
			<td><%=i++ %></td>
			<td><%=user2.getUserName() %></td>
			<td><%=manager.getManagerId() %></td>
			<td><%=manager.getManagerName() %></td>
			<td><%=manager.getEmail() %></td>
			<td><%=user2.getIdentity() %></td>
			<td><%=user2.getStatus() %></td>
			<td>
				<a href="passUserAction.do?action=pass&userName=<%=user2.getUserName() %>" onclick="return pass();">同意</a>&nbsp;&nbsp;
				<a href="passUserAction.do?action=reject&userName=<%=user2.getUserName() %>" onclick="return reject();">不同意</a>
			</td>
		</tr>
	
	<%
		}
	%>	
	
	</table>
	<br>
		<center>
			<a href="../log/login2.do"><img  src="../image/upload/image/cancel.jpg"></a>
		</center>
	<%@ include file="../foot.jsp" %>

	
</body>
</html>