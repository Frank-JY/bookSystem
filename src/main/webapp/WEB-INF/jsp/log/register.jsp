<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
function limit() {
	var regUserName = /^[\w]{3,15}$/;
	var regPassword = /^[\w]{6,12}$/;
	var regStudentId = /^[\d]{10}$/;
	var regManagerId = /^[\d]{5}$/;
	var regName = /^[\u4e00-\u9fa5]{2,10}$/;
	var regEmail = /^\w+([.-]\w+)*@\w+([.-]\w+)*\.\w+$/;
	var userName = document.getElementsByName("userName")[0].value;
	var password = document.getElementsByName("password")[0].value;
	var passwordAgain = document.getElementsByName("passwordAgain")[0].value;
	var id = document.getElementsByName("ids")[0].value;
	var name = document.getElementsByName("name")[0].value;
	var email = document.getElementsByName("email")[0].value;
	var myselect = document.getElementsByName("identity")[0];
	var index = myselect.selectedIndex; 
	var identity = myselect.options[index].value;
	if(!regUserName.test(userName)) {
		alert("用户名的长度应为3~15!");
		return false;
	}
	if(!regPassword.test(password)) {
		alert("密码的长度应为6~12!");
		return false;
	}
	if(passwordAgain != password) {
		alert("两次密码输入不一致!");
		return false;
	}
	if(identity == "学生") {
		if(!regStudentId.test(id)) {
			alert("学生学号应为10位数字!");
			return false;
		}
	}else {
		if(!regManagerId.test(id)) {
			alert("管理员的职工号应为5位数字!");
			return false;
		}
	}
	if(!regName.test(name)) {
		alert("姓名应为2~10个汉字!");
		return false;
	}
	if(!regEmail.test(email)) {
		alert("请输入正确的邮箱地址!");
		return false;
	}
}
</script>
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统注册页面</title>
</head>
<body>
<%@ include file="../loginHead.jsp" %>


	<h1 align="center">注册页面</h1>
	<hr>
	<form action="register.do" method="post">
		<table align="center" style="font-size: 20px;">
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" value="${userName }" ></td>
				<td><font color="red">${errorMap.error1 }</font></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="passwordAgain"></td>
			</tr>
			<tr>
				<td>学号(职工号)：</td>
				<td><input type="text" name="ids" value="${id }"></td>
				<td><font color="red">${errorMap.error2 }</font></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><input type="text" name="name" value="${name }"></td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" name="email" value="${email }"></td>
				<td><font color="red">${errorMap.error3 }</font></td>
			</tr>
			<tr>
				<td>身份：</td>
				<td>
					<select name="identity">
						<option value="管理员"  <c:if test="${identity == '管理员' }"> selected="selected"</c:if> >管理员</option>
						<option value="学生" <c:if test="${identity == '学生' }"> selected="selected"</c:if>>学生</option>				
					</select>
		</table>
		
		<br>
		<center>
		<input type="submit" value="注册" onclick="return limit();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="返回" onclick="window.location='toLogin.do'">			
		</center>
	</form>
	<%@ include file="../foot.jsp" %>
</body>
</html>