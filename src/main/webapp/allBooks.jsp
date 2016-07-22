<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.* ,java.text.SimpleDateFormat" %>
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
<%@ include file="head.jsp" %>
	<%	
		String error = "";
		Iterator<Books> qs1 = null;
	if(null != session.getAttribute("booksList")) {								//遍历Map
		@SuppressWarnings("unchecked")
		List<Books> booksList = (List<Books>)session.getAttribute("booksList");
		qs1 = booksList.iterator();
	}else {
		response.sendRedirect("ban.jsp");
		return;
	}
	if(null != request.getAttribute("error")) {
		error = (String)request.getAttribute("error");
	}
	
	%>
	

	<h1 align="center">图书管理</h1><hr>
	<center>
		<form action="ShowAllBooks1Servlet" method="post">
			<table>
				<tr>
					<td><input type="text" name="keyword" ><input type="submit" value="搜索"></td>
					<td><a href="condition1.jsp?value=2">点击进入条件查询</a></td>
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
	<%
		while(qs1.hasNext())
		{
			Books book = qs1.next();
			String typeName = book.getTypeName();
			String time = "";
			if(null != book.getTime() ) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				time = df.format(book.getTime());
			}
	%> 
		<tr>
			<td><a href="BookDetailsServleet?bookId=<%=book.getBookId() %>&tag=6"><%=book.getBookId() %></a></td>
			<td><a href="BookDetailsServleet?bookId=<%=book.getBookId() %>&tag=6"><%=book.getBookName() %></a></td>
			<td><%=book.getAuthor() %></td>
			<td><%=book.getPublishers() %></td>
			<td><%=time %></td>
			<td><%=typeName %></td>
			<td><%=book.getAmount() %></td>
			<td><%=book.getTotal() %></td>
			<td><%=book.getNumber() %></td>
			<td><%=book.getStatus() %></td>
			<td>
				<center>
					<a href="ManageBookServlet?action=delete&bookId=<%=book.getBookId() %>" onclick="return del();">删除</a>&nbsp;
					<a href="ManageBookServlet?action=in&bookId=<%=book.getBookId() %>">上架</a>&nbsp;
					<a href="ManageBookServlet?action=out&bookId=<%=book.getBookId() %>">下架</a>&nbsp;
					<a href="ManageBookServlet?action=update&bookId=<%=book.getBookId() %>">修改</a>
				</center>
			</td>
		</tr>
	
	<%
		}
	%>
	</table>
	<br>
	<center><font color="red"><%=error %></font></center>
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
			<a href="Login1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
	</center>
	<%@ include file="foot.jsp" %>
</body>
</html>