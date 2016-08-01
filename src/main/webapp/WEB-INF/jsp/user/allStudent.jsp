<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%@ include file="../head.jsp" %>
	
	<h1 align="center">用户信息</h1><hr>
	<center>
		<a href="showAllUser.do?action=1">学生信息</a>&nbsp;&nbsp;&nbsp;
		<a href="showAllUser.do?action=2">管理员信息</a>
	</center>
	<h2 align="center" ><font color="blue">学生</font></h2>	
	<form action="showAllUser1.do?action=1" method="post" >
		<table align="center">
				<tr>
					<td>
						<input type="text" name="keyword" value="${keyword }">
						<input type="submit" value="搜索">
					</td>
					<td><a href="toCondition.do?value=1">点击进入条件查询</a></td>
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
		
 
 	<c:set value="0" var="i" />
	<c:forEach items="${studentList }" var="user">
		<tr>
			<c:set value="${i+1 }" var="i" />
			<td>${i }</td>
			<td>
				<a href="lookUser.do?userName=${user.userName }&messageTag=3">
					${user.userName }
				</a>
			</td>
			<td>${user.student.studentId }</td>
			<td>${user.student.studentName }</td>
			<td>${user.student.sex }</td>
			<td>${user.student.studentMajor }</td>
			<td>${user.student.studentClass }</td>
			<td><fmt:formatDate value="${user.student.birthday }" pattern="yyyy-MM-dd"/></td>
			<td>${user.student.mobile }</td>
			<td>${user.student.email }</td>
			<td>${user.identity }</td>
			<td>
				<center>
					<a href="passUserAction.do?action=delete&userName=${user.userName }" onclick="return del();">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
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