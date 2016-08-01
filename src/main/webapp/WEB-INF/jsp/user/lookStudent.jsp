<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Calendar3.js"></script>
<script type="text/javascript">
	function limit() {	
		var studentMajor = document.getElementsByName("studentMajor")[0].value;
		var studentClass = document.getElementsByName("studentClass")[0].value;
		var mobile = document.getElementsByName("mobile")[0].value;
		var regMajor = /^[\u4e00-\u9fa5]{2,12}$/;
		var regClass = /^[\d]{4}级[\d]{1,2}班$/;
		var regMoblie = /^1[3-8]\d{9}$/;
		if(!regMajor.test(studentMajor)) {
			alert("专业名称应为2~12个汉字!");
			return false;
		}
		if(!regClass.test(studentClass)) {
			alert("班级格式为：2015级6班");
			return false;
		}
		if(!regMoblie.test(mobile)) {
			alert("请正确输入你的手机号码！");
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->查看用户信息</title>
</head>
<body>
<%@ include file="../head.jsp" %>


	<h1 align="center">用户信息表</h1><hr>
		<table align="center" style="font-size: 20px;">
			<tr>
				<td>头像：</td>
				<td align="center">
						<img src="../image/upload/${lookUser.student.photo }" width="180" height="180">
				</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td>${lookUser.userName }</td>
			</tr>
			<tr>
				<td>学号：</td>
				<td>${lookUser.student.studentId }</td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td>${lookUser.student.studentName }</td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>${lookUser.student.sex }</td>
			</tr>
			<tr>
				<td>专业：</td>
				<td>${lookUser.student.studentMajor }</td>
			</tr>
			<tr>
				<td>班级：</td>
				<td>${lookUser.student.studentClass }</td>
			</tr>
			<tr>
				<td>生日：</td>
				<td><fmt:formatDate value="${lookUser.student.birthday  }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td>手机：</td>
				<td>${lookUser.student.mobile }</td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td>${lookUser.student.email }</td>
			</tr>
			<tr>
				<td>身份：</td>
				<td>${lookUser.identity }</td>
			</tr>
		</table>
		<br><br>
		<center><font color="red">${tips }</font></center>
		<br>
		<div align="center">
			<c:if test="${lookUser.userName != userName }">
			<a href="../friend/friend.do?userName=${lookUser.userName }"><img  src="../image/upload/image/friend.jpg"></a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		
		<c:choose>
			<c:when test="${messageTag == '1' }">
				<a href="../borrow/toBookDetails.do"><img  src="../image/upload/image/cancel.jpg"></a>	
			</c:when>
			<c:when test="${messageTag == '2' }">
			<a href="../advice/toShowAdvice.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${messageTag == '3' }">
				<a href="../user/showAllUser.do?action=1"><img  src="../image/upload/image/cancel.jpg"></a>	
			</c:when>
			<c:when test="${messageTag == '4' }">
				<a href="../user/showAllUser.do?action=2"><img  src="../image/upload/image/cancel.jpg"></a>				</c:when>
			<c:when test="${messageTag == '5' }">
				<a href="../comment/comment1.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${messageTag == '6' }">
				<a href="../friend/friend1.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
		</c:choose>
		</div>
	<%@ include file="../foot.jsp" %>
</body> 
</html>