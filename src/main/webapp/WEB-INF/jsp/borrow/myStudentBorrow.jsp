<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%@ include file="../head.jsp" %>
	
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
	
		<c:set value="0" var="i" />
	<c:forEach items="${myBorrowList }" var="borrow">
		<tr>
			<c:set value="${i+1 }" var="i" />
			<td>${i }</td>
			<td><a href="../book/bookDetails.do?bookId=${borrow.bookId}&tag=2">${borrow.bookId}</a></td>
			<td><a href="../book/bookDetails.do?bookId=${borrow.bookId}&tag=2">${borrow.bookName}</a></td>
			<td>${borrow.readerId}</td>
			<td>${myborrowName}</td>
			<td>${borrow.userName}</td>
			<td>${borrow.day}</td>
			<td><fmt:formatDate value="${borrow.applyTime}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${borrow.outTime}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${borrow.endTime}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${borrow.inTime }" pattern="yyyy-MM-dd"/></td>
		<c:choose>
			<c:when test="${!borrow.due }">	
				<td>${borrow.due}</td>
			</c:when>
			<c:otherwise>
				<td><font color="blue">${borrow.due}</font></td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${borrow.status == '借阅中'}">
				<td><font color="red">${borrow.status}</font></td>
				<td><center><a href="backBook.do?id=${borrow.id}&identity=manager" onclick="return back();">归还图书</a></center></td>
			</c:when>
			<c:when test="${borrow.status == '等待申请'}">
				<td><font color=CD6600>${borrow.status}</font></td>
				<td></td>
			</c:when>
			<c:otherwise>
				<td>${borrow.status}</td>
				<td></td>
			</c:otherwise>
		</c:choose>
		</tr>
	
	</c:forEach>
	
	</table>
	<br>
	<center>
		<a href="../log/login2.do"><img  src="../image/upload/image/cancel.jpg"></a>
	</center>
	<%@ include file="../foot.jsp" %>
</body>
</html>