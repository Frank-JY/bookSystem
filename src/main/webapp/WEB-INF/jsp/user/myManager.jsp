<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/Calendar3.js"></script>
<script type="text/javascript">
	function limit() {	
		var age = document.getElementsByName("age")[0].value;
		var mobile = document.getElementsByName("mobile")[0].value;
		var regAge = /^([1-9][\d]|[1-9]?)$/;
		var regMoblie = /^(1[3-8]\d{9})?$/;
		if(!regAge.test(age)) {
			alert("请正确输入你的年龄！");
			return false;
		}
		if(!regMoblie.test(mobile)) {
			alert("请正确输入你的手机号码！");
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->个人中心</title>
</head>
<body>
<%@ include file="../head.jsp" %>

	<h1 align="center">信息修改</h1><hr>
	<form action="../user/updateUser.do" method="post" >
		<table align="center" style="font-size: 20px;">
			<tr>
				<td>头像：</td>
				<td align="center">
					<a href="toUpdatePhoto1.do">
						<img src="../image/upload/${user1.manager.photo}" width="180" height="180">
						更换头像
					</a>
				</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td>${user1.userName}</td>
				<td><input type="hidden" name="userName" value="${user1.userName} "></td>
			</tr>
			<tr>
				<td>职工号：</td>
				<td>${user1.manager.managerId}</td>
				<td><input type="hidden" name="id" value="${user1.manager.managerId} "></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td>${user1.manager.managerName}</td>
				<td><input type="hidden" name="name" value="${user1.manager.managerName} "></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					男<input type="radio" name="sex" value="男" checked="checked">
					女<input type="radio" name="sex" value="女"<c:if test="${user1.manager.sex == '女'}"> checked="checked" </c:if>>				</td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age" value="${user1.manager.age}" ></td>
			</tr>
			<tr>
			<tr>
				<td>生日：</td>
				<td>
					<input type="text" name="birthday" value="<fmt:formatDate value="${user1.manager.birthday}" pattern="yyyy-MM-dd"/>" id="control_date" size="10"
                     maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
                </td>
			</tr>
			<tr>
				<td>手机：</td>
				<td><input type="text" name="mobile" value="${user1.manager.mobile}" ></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td>${user1.manager.email}</td>
				<td><input type="hidden" name="email" value="${user1.manager.email} "></td>
			</tr>
			<tr>
				<td>身份：</td>
				<td>${user1.identity}</td>
				<td><input type="hidden" name="identity" value="${user1.identity} "></td>
			</tr>
		</table>
	
	<div align="center">
	<input type="reset" value="重置">&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="window.location='../log/login2.do'" >&nbsp;&nbsp;&nbsp;
	<input type="submit" value="修改" onclick="return limit();">
	</div>
	</form>
	<%@ include file="../foot.jsp" %>
</body> 
</html>