<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* ,java.text.SimpleDateFormat" %>
<script type="text/javascript" src="js/Calendar3.js"></script>
<script type="text/javascript">
	function limit() {	
		var age = document.getElementsByName("age")[0].value;
		var mobile = document.getElementsByName("mobile")[0].value;
		var regAge = /^([1-9][\d]|[1-9]?)$/;
		var regMoblie = /^1[3-8]\d{9}$/;
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
<title>图书管理系统->查看用户信息</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	<%
	String userName = "";
	String managerId = "";
	String managerName = "";
	String sex = "";
	String age = "";
	String birthday = "";
	String mobile = "";
	String email = "";
	String photo = "";
	String identity = "";
	String tips = "";
	Iterator<Map.Entry<Manager,User>> qs2 = null;
	if(null != session.getAttribute("lookUser")) {
		@SuppressWarnings("unchecked")
		User user = (User)session.getAttribute("lookUser");
		Manager manager = user.getManager();
		userName = user.getUserName();
		managerId = String.valueOf(manager.getManagerId());
		managerName = manager.getManagerName();
		if(null != manager.getSex()) {
			sex = manager.getSex();
		}
		if(0 != manager.getAge() ) {
			age = String.valueOf(manager.getAge());
		}
		if(null != manager.getBirthday() ) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			birthday = df.format(manager.getBirthday());
		}
		if(0 != manager.getMobile() ) {
			mobile = String.valueOf(manager.getMobile());
		}
		email = manager.getEmail();
		photo = manager.getPhoto();
		identity = user.getIdentity();
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	
	
	%>


	<h1 align="center">用户信息表</h1><hr>
		<table align="center" style="font-size: 20px;">
			<tr>
				<td>头像：</td>
				<td align="center">				
						<img src="../image/upload/<%=photo %>" width="180" height="180">				
				</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><%=userName %></td>
			</tr>
			<tr>
				<td>职工号：</td>
				<td><%=managerId %></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><%=managerName %></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td><%=sex %></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><%=age %></td>
			</tr>
			<tr>
			<tr>
				<td>生日：</td>
				<td><%=birthday %></td>
			</tr>
			<tr>
				<td>手机：</td>
				<td><%=mobile %></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><%=email %></td>
			</tr>
			<tr>
				<td>身份：</td>
				<td><%=identity %></td>
			</tr>
		</table>
	<br><br>
		<%
			if(null != request.getAttribute("tips")) {
				tips = (String)request.getAttribute("tips");
			}
		%>
		<center><font color="red"><%=tips %></font></center>
	<br>
	<div align="center">
		<%
			if(!userName.equals(session.getAttribute("userName"))) {
		%>
			<a href="../friend/friend.do?userName=<%=userName %>"><img  src="../image/upload/image/friend.jpg"></a>&nbsp;&nbsp;&nbsp;&nbsp;
		<%	
			}
			String messageTag = "";
			if(null != session.getAttribute("messageTag")) {
				messageTag = (String)session.getAttribute("messageTag");
				if(messageTag.equals("1")) {
		%>
		<a href="../borrow/toBookDetails.do"><img  src="../image/upload/image/cancel.jpg"></a>	
		<%
				}else if(messageTag.equals("2")) {
		%>
		<a href="../advice/toShowAdvice.do"><img  src="../image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("3")) {
		%>
		<a href="../user/showAllUser.do?action=1"><img  src="../image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("4")) {
		%>
		<a href="../user/showAllUser.do?action=2"><img  src="../image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("5")) {
		%>
		<a href="../comment/comment1.do"><img  src="../image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("6")) {
		%>
		<a href="../friend/friend1.do"><img  src="../image/upload/image/cancel.jpg"></a>
		<%
				}
			}
		%>	</div>
	<%@ include file="../foot.jsp" %>
</body> 
</html>