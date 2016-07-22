<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* ,java.text.SimpleDateFormat;" %>
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
	Iterator<ManagerBorrow> qs1 = null;
	if(null != session.getAttribute("myBorrowList") && session.getAttribute("identity").equals("管理员")) {
		@SuppressWarnings("unchecked")
		List<ManagerBorrow> myBorrowList = (List<ManagerBorrow>)session.getAttribute("myBorrowList");
		qs1 = myBorrowList.iterator();
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
			<th width="110">申请时间</th>
			<th width="110">借出时间</th>
			<th width="110">到期时间</th>
			<th width="110">归还时间</th>
			<th width="80">是否超期</th>
			<th width="80">状态</th>
			<th width="80">操作</th>
		</tr>
	<%
		int i = 1;
		while(qs1.hasNext())
		{
			String applyTime = "";
			String outTime = "";
			String endTime = "";
			String inTime = "";
			ManagerBorrow borrow = qs1.next();
			String bookName = borrow.getBookName();
			String name = (String)session.getAttribute("myborrowName");			
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
			<td><a href="BookDetailsServleet?bookId=<%=borrow.getBookId() %>&tag=3"><%=borrow.getBookId() %></a></td>
			<td><a href="BookDetailsServleet?bookId=<%=borrow.getBookId() %>&tag=3"><%=bookName %></a></td>
			<td><%=borrow.getReaderId() %></td>
			<td><%=name %></td>
			<td><%=borrow.getUserName() %></td>
			<td><%=borrow.getDay() %></td>
			<td><%=applyTime %></td>
			<td><%=outTime %></td>
			<td><%=endTime %></td>
			<td><%=inTime %></td>
		<%
			if(borrow.isDue()) {
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
			<td><center><a href="BackBookServlet?id=<%=borrow.getId() %>&identity=manager" onclick="return back();">归还图书</a></center></td>
		<%
			}else if(borrow.getStatus().equals("等待申请")){
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