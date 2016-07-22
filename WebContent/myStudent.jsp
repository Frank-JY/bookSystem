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
		var regMajor = /^([\u4e00-\u9fa5]{2,12})?$/;
		var regClass = /^([\d]{4}级[\d]{1,2}班)?$/;
		var regMoblie = /^(1[3-8]\d{9})?$/;
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
<title>图书管理系统->个人中心</title>
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
	Iterator<Map.Entry<Student,User>> qs1 = null;
	if(null != request.getAttribute("studentMap")) {
		@SuppressWarnings("unchecked")
		Map<Student,User> studentMap = (Map<Student,User>)request.getAttribute("studentMap");
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
			session.setAttribute("userId2", studentId);
			session.setAttribute("photo2", photo);
		}
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	%>


	<h1 align="center">信息修改</h1><hr>
	<form action="UpdateUserServlet?action=student" method="post">
		<table align="center" style="font-size: 20px;">
			<tr>
				<td>头像：</td>
				<td align="center">
					<a href="updatePhoto1.jsp" >
						<img src="image/upload/<%=photo %>" width="180" height="180">
						更换头像
					</a>
				</td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><%=userName %></td>
				<td><input type="hidden" name="userName" value="<%=userName %> "></td>
			</tr>
			<tr>
				<td>学号：</td>
				<td><%=studentId %></td>
				<td><input type="hidden" name="id" value="<%=studentId %> "></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><%=studentName %></td>
				<td><input type="hidden" name="name" value="<%=studentName %> "></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					男<input type="radio" name="sex" value="男" checked="checked">
					女<input type="radio" name="sex" value="女" <%if(sex.equals("女")) {%> checked="checked" <%} %>>
				</td>
			</tr>
			<tr>
				<td>专业：</td>
				<td><input type="text" name="studentMajor" value="<%=studentMajor %>" ></td>
			</tr>
			<tr>
				<td>班级：</td>
				<td><input type="text" name="studentClass" value="<%=studentClass %>" ></td>
			</tr>
			<tr>
				<td>生日：</td>
				<td>
					<input type="text" name="birthday" value="<%=birthday %>" id="control_date" size="10"
                     maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
                </td>
			</tr>
			<tr>
				<td>手机：</td>
				<td><input type="text" name="mobile" value="<%=mobile %>" ></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><%=email %></td>
				<td><input type="hidden" name="email" value="<%=email %> "></td>
			</tr>
			<tr>
				<td>身份：</td>
				<td><%=identity %></td>
				<td><input type="hidden" name="identity" value="<%=identity %> "></td>
			</tr>
		</table>
		<div align="center">
	<input type="reset" value="重置">&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="window.location='Login1Servlet'" >&nbsp;&nbsp;&nbsp;
	<input type="submit" value="修改" onclick="return limit();">
	</div>
	</form>
	<%@ include file="foot.jsp" %>
</body> 
</html>