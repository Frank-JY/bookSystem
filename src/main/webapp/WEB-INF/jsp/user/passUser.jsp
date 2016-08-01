<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<c:set value="0" var="i" />
	<c:forEach items="${studentList }" var="user1">
		<tr>	
			<c:set value="${i+1 }" var="i" />
			<td>${i }</td>
			<td>${user1.userName }</td>
			<td>${user1.student.studentId }</td>
			<td>${user1.student.studentName }</td>
			<td>${user1.student.email }</td>
			<td>${user1.identity }</td>
			<td>${user1.status }</td>
			<td>
				<a href="passUserAction.do?action=pass&userName=${user1.userName }" onclick="return pass();">同意</a>&nbsp;&nbsp;
				<a href="passUserAction.do?action=reject&userName=${user1.userName }" onclick="return reject();">不同意</a>
			</td>
		</tr>
	</c:forEach>
	

	<c:forEach items="${managerList }" var="user2">
		<tr>
			<c:set value="${i+1 }" var="i" />
			<td>${i }</td>
			<td>${user2.userName}</td>
			<td>${user2.manager.managerId}</td>
			<td>${user2.manager.managerName}</td>
			<td>${user2.manager.email}</td>
			<td>${user2.identity}</td>
			<td>${user2.status}</td>
			<td>
				<a href="passUserAction.do?action=pass&userName=${user2.userName}" onclick="return pass();">同意</a>&nbsp;&nbsp;
				<a href="passUserAction.do?action=reject&userName=${user2.userName}" onclick="return reject();">不同意</a>
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