<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*,java.text.SimpleDateFormat " %>
<script type="text/javascript">
	function del() {
		var del = window.confirm("确定要删除用户吗？(删除后相关信息将会被删除)");
		return del;
	} 

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->显示所有通过申请的用户</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<%	
	Iterator<User> qs1 = null;
	String keyword = "";
	if(null != request.getAttribute("keyword")) {
		keyword = (String)request.getAttribute("keyword");
	}
	if(null != request.getAttribute("studentList")) {
		@SuppressWarnings("unchecked")
		List<User> studentList = (List<User>)request.getAttribute("studentList");
		qs1 = studentList.iterator();
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	%>
	
	<h1 align="center">用户信息</h1><hr>
	<center>
		<a href="ShowAllUserServlet?action=1">学生信息</a>&nbsp;&nbsp;&nbsp;
		<a href="ShowAllUserServlet?action=2">管理员信息</a>
	</center>
	<h2 align="center" ><font color="blue">学生</font></h2>	
	<form action="ShowAllUser1Servlet?action=1" method="post" >
		<table align="center">
				<tr>
					<td>
						<input type="text" name="keyword" value="<%=keyword %>">
						<input type="submit" value="搜索">
					</td>
					<td><a href="condition.jsp?value=1">点击进入条件查询</a></td>
				</tr>
		</table>
	</form>
	<table border="1" align="center" style="background-color:#88FFAA">
	<tr> 
			<th width="80" ></th>
			<th width="100" >用户名</th>
			<th width="150">学号</th>
			<th width="80">姓名</th>
			<th width="80">性别</th>
			<th width="180">专业</th>
			<th width="90">班级</th>
			<th width="80">生日</th>
			<th width="80">手机</th>
			<th width="220">邮箱</th>
			<th width="80">账号身份</th>
			<th width="150" align="center">操作</th>
		</tr>
	<%
		int i = 1;
		while(qs1.hasNext())
		{
			User user = qs1.next();
			Student student = user.getStudent();
			String birthday = "";
			if(null != student.getBirthday() ) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				birthday = df.format(student.getBirthday());
			}
	%>
		<tr>
			<td><%=i++ %></td>
			<td>
				<a href="LookUserServlet?userName=<%=user.getUserName() %>&messageTag=3">
					<%=user.getUserName() %>
				</a>
			</td>
			<td><%=student.getStudentId() %></td>
			<td><%=student.getStudentName() %></td>
			<td><%=student.getSex() %></td>
			<td><%=student.getStudentMajor() %></td>
			<td><%=student.getStudentClass() %></td>
			<td><%=birthday %></td>
			<td><%=student.getMobile() %></td>
			<td><%=student.getEmail() %></td>
			<td><%=user.getIdentity() %></td>
			<td>
				<center>
					<a href="PassUserActionServlet?action=delete&userName=<%=user.getUserName() %>" onclick="return del();">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
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