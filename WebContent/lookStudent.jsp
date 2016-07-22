<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* ,java.text.SimpleDateFormat;" %>
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
<%@ include file="head.jsp" %>
	<%
	String userName = "";
	String studentId = "";
	String studentName = "";
	String sex = "";
	String studentMajor = "";
	String studentClass = "";
	String birthday = "";
	String mobile = "";
	String email = "";
	String photo = "";
	String identity = "";
	String tips = "";
	Iterator<Map.Entry<Student,User>> qs1 = null;
	if(null != session.getAttribute("studentMap")) {
		@SuppressWarnings("unchecked")
		Map<Student,User> studentMap = (Map<Student,User>)session.getAttribute("studentMap");
		Set<Map.Entry<Student,User>> studentSet = studentMap.entrySet();
		qs1 = studentSet.iterator();
		if(qs1.hasNext())
		{
			Map.Entry<Student,User> set1 = qs1.next();
			Student student = set1.getKey();
			User user = set1.getValue();
			userName = user.getUserName();
			studentId = String.valueOf(student.getStudentId());
			studentName = student.getStudentName();
			if(null != student.getSex()) {
				sex = student.getSex();
			}
			if(null != student.getStudentMajor() ) {
				studentMajor = student.getStudentMajor();
			}
			if(null != student.getStudentClass() ) {
				studentClass = student.getStudentClass();
			}
			if(null != student.getBirthday() ) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				birthday = df.format(student.getBirthday());
			}
			if(0 != student.getMobile() ) {
				mobile = String.valueOf(student.getMobile());
			}
			email = student.getEmail();
			photo = student.getPhoto();
			identity = user.getIdentity();
		}
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
						<img src="image/upload/<%=photo %>" width="180" height="180">
				</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><%=userName %></td>
			</tr>
			<tr>
				<td>学号：</td>
				<td><%=studentId %></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><%=studentName %></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td><%=sex %></td>
			</tr>
			<tr>
				<td>专业：</td>
				<td><%=studentMajor %></td>
			</tr>
			<tr>
				<td>班级：</td>
				<td><%=studentClass %></td>
			</tr>
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
		<a href="FriendServlet?userName=<%=userName %>"><img  src="image/upload/image/friend.jpg"></a>&nbsp;&nbsp;&nbsp;&nbsp;
		<%	
			}
			String messageTag = "";
			if(null != session.getAttribute("messageTag")) {
				messageTag = (String)session.getAttribute("messageTag");
				if(messageTag.equals("1")) {
		%>
		<a href="bookDetails.jsp"><img  src="image/upload/image/cancel.jpg"></a>	
		<%
				}else if(messageTag.equals("2")) {
		%>
		<a href="showAdvice.jsp"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("3")) {
		%>
		<a href="ShowAllUserServlet?action=1"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("4")) {
		%>
		<a href="ShowAllUserServlet?action=2"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("5")) {
		%>
		<a href="Comment1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(messageTag.equals("6")) {
		%>
		<a href="Friend1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}
			}
		%>
		</div>
	<%@ include file="foot.jsp" %>
</body> 
</html>