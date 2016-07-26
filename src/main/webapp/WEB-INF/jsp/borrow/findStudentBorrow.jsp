<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* ,java.text.SimpleDateFormat" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->借阅记录</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	<%
	Iterator<StudentBorrow> qs1 = null;
	if(null != session.getAttribute("borrowList")) {
		@SuppressWarnings("unchecked")
		List<StudentBorrow> borrowList = (List<StudentBorrow>)session.getAttribute("borrowList");
		qs1 = borrowList.iterator();
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	%>
	
	<h1 align="center"><font color="red">借阅记录</font></h1><hr>
	
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
		</tr>
	<%
		int i = 1;
		while(qs1.hasNext())
		{
			StudentBorrow borrow = qs1.next();
			String bookName = borrow.getBookName();
			String name = (String)session.getAttribute("borrowName");
			String applyTime = "";
			String outTime = "";
			String endTime = "";
			String inTime = "";	
			if(null != borrow.getApplyTime() ) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				applyTime = df.format(borrow.getApplyTime());
			}			
			if(null != borrow.getOutTime() ) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				outTime = df.format(borrow.getOutTime());
			}	
			if(null != borrow.getEndTime() ) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				endTime = df.format(borrow.getEndTime());
			}		
			if(null != borrow.getInTime() ) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				inTime = df.format(borrow.getInTime());
			}
	%> 
		<tr>
			<td><%=i++ %></td>
			<td><a href="../book/bookDetails.do?bookId=<%=borrow.getBookId() %>&tag=9"><%=borrow.getBookId() %></a></td>
			<td><a href="../book/bookDetails.do?bookId=<%=borrow.getBookId() %>&tag=9"><%=bookName %></a></td>
			<td><%=borrow.getReaderId() %></td>
			<td><%=name %></td>
			<td><%=borrow.getUserName() %></td>
			<td><%=borrow.getDay() %></td>
			<td><%=applyTime %></td>
			<td><%=outTime %></td>
			<td><%=endTime %></td>
			<td><%=inTime %></td>
		<%
			if(!borrow.isDue()) {
		%>
			<td><%=borrow.isDue() %></td>
		<%
			} else {
		%>	
			<td><font color="blue"><%=borrow.isDue() %></font></td>
		<%
			}
		%>
		<%
			if(borrow.getStatus().equals("借阅中")) {
		%>
			<td><font color="red"><%=borrow.getStatus() %></font></td>
		<%
			}else if(borrow.getStatus().equals("等待申请")) {
		%>		
			<td><font color=CD6600><%=borrow.getStatus() %></font></td>
		<%
			}else {
		%>
			<td><%=borrow.getStatus() %></td>
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
		<a href="../user/showAllUser.do?action=1"><img  src="../image/upload/image/cancel.jpg"></a>
	</center>
	<%@ include file="../foot.jsp" %>
</body>
</html>