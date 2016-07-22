<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* ,java.text.SimpleDateFormat;" %>
<script type="text/javascript" src="js/Calendar3.js"></script>
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
<%@ include file="head.jsp" %>
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
	Iterator<Map.Entry<Manager,User>> qs2 = null;
	if(null != request.getAttribute("managerMap")) {
		@SuppressWarnings("unchecked")
		Map<Manager,User> managerMap = (Map<Manager,User>)request.getAttribute("managerMap");
		Set<Map.Entry<Manager,User>> managerSet = managerMap.entrySet();
		qs2 = managerSet.iterator();
		if(qs2.hasNext())
		{
			Map.Entry<Manager,User> set2 = qs2.next();
			Manager manager = set2.getKey();
			User user = set2.getValue();
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
			session.setAttribute("userId2", managerId);
			session.setAttribute("photo2", photo);
		}
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	
	
	%>


	<h1 align="center">信息修改</h1><hr>
	<form action="UpdateUserServlet?action=manager" method="post" >
		<table align="center" style="font-size: 20px;">
			<tr>
				<td>头像：</td>
				<td align="center">
					<a href="updatePhoto1.jsp">
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
				<td>职工号：</td>
				<td><%=managerId %></td>
				<td><input type="hidden" name="id" value="<%=managerId %> "></td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td><%=managerName %></td>
				<td><input type="hidden" name="name" value="<%=managerName %> "></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					男<input type="radio" name="sex" value="男" checked="checked">
					女<input type="radio" name="sex" value="女" <%if(sex.equals("女")) {%> checked="checked" <%} %>>
				</td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age" value="<%=age %>" ></td>
			</tr>
			<tr>
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