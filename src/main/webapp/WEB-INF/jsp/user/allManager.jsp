<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->显示所有通过申请的用户</title>
</head>
<body>
<%@ include file="../head.jsp" %>							
	
	<h1 align="center">用户信息</h1><hr>
	<center>
		<a href="showAllUser.do?action=1">学生信息</a>&nbsp;&nbsp;&nbsp;
		<a href="showAllUser.do?action=2">管理员信息</a><br>
	</center>
	<h2 align="center" ><font color="blue">管理员</font></h2>	
	<form action="showAllUser1.do?action=2" method="post" >
		<table align="center">
				<tr>
					<td>
						<input type="text" name="keyword" value="${keyword }">
						<input type="submit" value="搜索">
					</td>
					<td><a href="toCondition.do?value=2">点击进入条件查询</a></td>
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
	
	
	 <c:set value="0" var="i" />
	<c:forEach items="${managerList }" var="user">
		<tr>
		<c:set value="${i+1 }" var="i" />
			<td>${i }</td>
			<td>
				<a href="lookUser.do?userName=${user.userName }&messageTag=4">
					${user.userName }
				</a>
			</td>
			<td>${user.manager.managerId }</td>
			<td>${user.manager.managerName }</td>
			<td>${user.manager.sex }</td>
			<td>${user.manager.age }</td>
			<td><fmt:formatDate value="${user.manager.birthday }" pattern="yyyy-MM-dd"/></td>
			<td>${user.manager.mobile }</td>
			<td>${user.manager.email }</td>
			<td>${user.identity }</td>
			<td>
				<center>
					<a href="passUserAction.do?action=borrow&userName=${user.userName }">借阅记录</a>
				</center>
			</td>
		</tr>
	</c:forEach>
	
	</table>
	<br>
		<center>
			<a href="../log/login2.do"><img  src="../image/upload/image/cancel.jpg"></a>
		</center>
	<%@ include file="../foot.jsp" %>
</body>
</html>