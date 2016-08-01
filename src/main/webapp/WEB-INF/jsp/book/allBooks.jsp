<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function del() {														//提示框
		var del = window.confirm("确定删除该图书吗？");
		return del;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->图书管理</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	

	<h1 align="center">图书管理</h1><hr>
	<center>
		<form action="showAllBooks1.do" method="post">
			<table>
				<tr>
					<td><input type="text" name="keyword" value="${keyword1 }" ><input type="submit" value="搜索"></td>
					<td><a href="toCondition1.do?value=2">点击进入条件查询</a></td>
				</tr>
			</table>
		</form>
	</center>
	<table border="1" align="center" style="background-color:#88FFAA">
	<tr> 
			<th width="100" >图书编号</th>
			<th width="150">图书名</th>
			<th width="150">作者</th>
			<th width="200">出版社</th>
			<th width="80">上架时间</th>
			<th width="80">图书类型</th>
			<th width="80">图书库存</th>
			<th width="80">图书总数量</th>
			<th width="80">借阅次数</th>
			<th width="80">状态</th>
			<th width="200" align="center">操作</th>
		</tr>
	
	<c:forEach items="${booksList }" var="book">
		<tr>
			<td><a href="bookDetails.do?bookId=${book.bookId}&tag=6">${book.bookId}</a></td>
			<td><a href="bookDetails.do?bookId=${book.bookId}&tag=6">${book.bookName}</a></td>
			<td>${book.author}</td>
			<td>${book.publishers}</td>
			<td><fmt:formatDate value="${book.time }" pattern="yyyy-MM-dd"/></td>
			<td>${book.typeName}</td>
			<td>${book.amount}</td>
			<td>${book.total}</td>
			<td>${book.number}</td>
			<td>${book.status}</td>
			<td>
				<center>
					<a href="manageBook.do?action=delete&bookId=${book.bookId}" onclick="return del();">删除</a>&nbsp;
					<a href="manageBook.do?action=in&bookId=${book.bookId}">上架</a>&nbsp;
					<a href="manageBook.do?action=out&bookId=${book.bookId}">下架</a>&nbsp;
					<a href="manageBook.do?action=update&bookId=${book.bookId}">修改</a>
				</center>
			</td>
		</tr>
	</c:forEach>
	
	</table>
	<br>
	<center><font color="red">${error }</font></center>
	<br>

	<br>
	<center>
		<font color="blue">
			1、已下架状态代表此图书不可外借。<br>
			2、无库存状态代表此图书重新有库存时，状态会转为可借阅。<br>
			3、当图书全部归还（即总数量=库存）时，可以使用删除按钮彻底删除此图书信息。<br>
			4、点击修改可进入该图书的修改页面。<br>
		</font>
	</center>
	<br>
	<center>
			<a href="../log/login2.do"><img  src="../image/upload/image/cancel.jpg"></a>
	</center>
	<%@ include file="../foot.jsp" %>
</body>
</html>