<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多条件查询</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	<h1 align="center">多条件查询用户</h1><hr>
	
	<c:choose>
		<c:when test="${param.value=='1'}">
	<h2 align="center" ><font color="blue">学生</font></h2>	
	<form action="condition.do" method="post">
	<table align="center" style="font-size: 20px;">
		<tr>
			<td>用户名</td>
			<td><input type="text" name="userName"></td>
		</tr>
		<tr>
			<td>学号</td>
			<td><input type="text" name="studentId"></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="studentName"></td>
		</tr>
		<tr>
			<td>性别</td>
			<td><input type="text" name="sex"></td>
		</tr>
		<tr>
			<td>专业</td>
			<td><input type="text" name="studentMajor"></td>
		</tr>
		<tr>
			<td>班级</td>
			<td><input type="text" name="studentClass"></td>
		</tr>
	</table>
	<br>
	<center>
		<input type="hidden" name="value" value="1">
		<input type="submit" value="查询" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="返回" onclick="window.location='showAllUser.do?action=1'" style="background-color:#66CCFF">
	</center>
	</form>
	</c:when>
	<c:when test="${param.value == '2' }">
	<h2 align="center" ><font color="blue">管理员</font></h2>	
	<form action="condition.do" method="post">
	<table align="center" style="font-size: 20px;">
		<tr>
			<td>用户名</td>
			<td><input type="text" name="userName"></td>
		</tr>
		<tr>
			<td>职工号</td>
			<td><input type="text" name="managerId"></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="managerName"></td>
		</tr>
		<tr>
			<td>性别</td>
			<td><input type="text" name="sex"></td>
		</tr>
		<tr>
			<td>年龄</td>
			<td><input type="text" name="age"></td>
		</tr>
	</table>
	<br>
	<center>
		<input type="hidden" name="value" value="2">
		<input type="submit" value="查询" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="返回" onclick="window.location='showAllUser.do?action=2'" style="background-color:#66CCFF">
	</center>
	</form>
		</c:when>
	</c:choose>
<%@ include file="../foot.jsp" %>	

</body>
</html>