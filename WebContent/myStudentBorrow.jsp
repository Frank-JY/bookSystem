<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* " %>
<script type="text/javascript">
	function back() {
		var back = window.confirm("确定归还吗？");
		return back;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->个人借阅信息</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<%
	Iterator<Map.Entry<StudentBorrow, String>> qs1 = null;
	if(null != session.getAttribute("myBorrowMap") && session.getAttribute("identity").equals("学生")) {
		@SuppressWarnings("unchecked")
		Map<StudentBorrow, String> myBorrowMap = (Map<StudentBorrow, String>)session.getAttribute("myBorrowMap");
		Set<Map.Entry<StudentBorrow, String>> myBorrowSet = myBorrowMap.entrySet();
		qs1 = myBorrowSet.iterator();
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	%>
	
	<h1 align="center"><font color="red">个人借阅信息</font></h1><hr>
	
	<table border="1" align="center" style="background-color:#88FFAA">
	<tr> 
			<th width="40"></th>
			<th width="100" >图书编号</th>
			<th width="120">图书名</th>
			<th width="150">学号</th>
			<th width="100">姓名</th>
			<th width="80">用户名</th>
			<th width="80">借阅天数</th>
			<th width="120">申请时间</th>
			<th width="120">借出时间</th>
			<th width="120">到期时间</th>
			<th width="120">归还时间</th>
			<th width="80">是否超期</th>
			<th width="80">状态</th>
			<th width="80">操作</th>
		</tr>
	<%
		int i = 1;
		while(qs1.hasNext())
		{
			Map.Entry<StudentBorrow, String> set1 = qs1.next();
			StudentBorrow borrow = set1.getKey();
			String bookName = set1.getValue();
			String name = (String)session.getAttribute("myborrowName");
	%> 
		<tr>
			<td><%=i++ %></td>
			<td><a href="BookDetailsServleet?bookId=<%=borrow.getBookId() %>&tag=2"><%=borrow.getBookId() %></a></td>
			<td><a href="BookDetailsServleet?bookId=<%=borrow.getBookId() %>&tag=2"><%=bookName %></a></td>
			<td><%=borrow.getReaderId() %></td>
			<td><%=name %></td>
			<td><%=borrow.getUserName() %></td>
			<td><%=borrow.getDay() %></td>
			<td><%=borrow.getApplyTime() %></td>
			<td><%=borrow.getOutTime() %></td>
			<td><%=borrow.getEndTime() %></td>
			<td><%=borrow.getInTime() %></td>
		<%
			if(!borrow.isDue()) {
		%>
			<td><font color="blue"><%=borrow.isDue() %></font></td>
		<%
			} else {
		%>	
			<td><%=borrow.isDue() %></td>
		<%
			}
		%>
		<%
			if(borrow.getStatus().equals("借阅中")) {
		%>
			<td><font color="red"><%=borrow.getStatus() %></font></td>
			<td><center><a href="BackBookServlet?id=<%=borrow.getId() %>&identity=student" onclick="return back();">归还图书</a></center></td>
		<%
			}else if(borrow.getStatus().equals("等待申请")) {
		%>		
			<td><font color=CD6600><%=borrow.getStatus() %></font></td>
			<td></td>
		<%
			}else {
		%>
			<td><%=borrow.getStatus() %></td>
			<td></td>	
		<%
			}
		%>
		
		</tr>

	<%
		}
	%>
	
	</table>
	<br>
	<center>
		<a href="Login1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
	</center>
	<%@ include file="foot.jsp" %>
</body>
</html>