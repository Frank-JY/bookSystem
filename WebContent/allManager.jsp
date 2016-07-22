<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* " %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->显示所有通过申请的用户</title>
</head>
<body>
<%@ include file="head.jsp" %>							
	<%
	Iterator<Map.Entry<Manager,User>> qs2 = null;
	if(null != request.getAttribute("managerMap")) {
		@SuppressWarnings("unchecked")
		Map<Manager,User> managerMap = (Map<Manager,User>)request.getAttribute("managerMap");
		Set<Map.Entry<Manager,User>> managerSet = managerMap.entrySet();
		qs2 = managerSet.iterator();
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	%>
	
	<h1 align="center">用户信息</h1><hr>
	<center>
		<a href="ShowAllUserServlet?action=1">学生信息</a>&nbsp;&nbsp;&nbsp;
		<a href="ShowAllUserServlet?action=2">管理员信息</a><br>
	</center>
	<h2 align="center" ><font color="blue">管理员</font></h2>	
	<form action="ShowAllUser1Servlet?action=2" method="post" >
		<table align="center">
				<tr>
					<td><input type="text" name="keyword" ><input type="submit" value="搜索"></td>
					<td><a href="condition.jsp?value=2">点击进入条件查询</a></td>
				</tr>
		</table>
	</form>
	<table border="1" align="center" style="background-color:#88FFAA">
	<tr>
			<th width="80"></th>
			<th width="100" >用户名</th>
			<th width="150">职工号</th>
			<th width="80">姓名</th>
			<th width="80">性别</th>
			<th width="80">年龄</th>
			<th width="80">生日</th>
			<th width="80">手机</th>
			<th width="220">邮箱</th>
			<th width="80">账号身份</th>
			<th width="100" >操作</th>
		</tr>
	
	<%
		int i = 1;
		while(qs2.hasNext())
		{
			Map.Entry<Manager,User> set2 = qs2.next();
			Manager manager = set2.getKey();
			User user = set2.getValue();
	%>
		<tr>
			<td><%=i++ %></td>
			<td>
				<a href="LookUserServlet?userName=<%=user.getUserName() %>&messageTag=4">
					<%=user.getUserName() %>
				</a>
			</td>
			<td><%=manager.getManagerId() %></td>
			<td><%=manager.getManagerName() %></td>
			<td><%=manager.getSex() %></td>
			<td><%=manager.getAge() %></td>
			<td><%=manager.getBirthday() %></td>
			<td><%=manager.getMobile() %></td>
			<td><%=manager.getEmail() %></td>
			<td><%=user.getIdentity() %></td>
			<td>
				<center>
					<a href="PassUserActionServlet?action=borrow&userName=<%=user.getUserName() %>">借阅记录</a>
				</center>
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