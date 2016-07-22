<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*" %>
<script type="text/javascript">
	function limit() {
		var regBookId = /^UTF1[\d]{4}$/;
		var regBookName = /^[\u4e00-\u9fa5|\w]{1,25}$/;
		var regAuthor = /^[\u4e00-\u9fa5]{1,5}(?:·[\u4e00-\u9fa5]{1,5})*$/;
		var regPublishers = /^[\u4e00-\u9fa5]{6,20}$/;
		var regtotal = /^([1-9]|[1-9][\d])$/;
		var regRemarks = /^.{50,800}$/;
		
		var bookId = document.getElementsByName("bookId")[0].value;
		var bookName = document.getElementsByName("bookName")[0].value;
		var author = document.getElementsByName("author")[0].value;
		var publishers = document.getElementsByName("publishers")[0].value;
		var total = document.getElementsByName("total")[0].value;
		var remarks = document.getElementsByName("remarks")[0].value;
		
		if(!regBookId.test(bookId)) {
			alert("图书编号格式为：UTF1××××");
			return false;
		}
		if(!regBookName.test(bookName)) {
			alert("请正确输入图书名(不超过25个字)");
			return false;
		}
		if(!regAuthor.test(author)) {
			alert("请正确输入作者名字!");
			return false;
		}
		if(!regPublishers.test(publishers)) {
			alert("出版社应为6~20个汉字!");
			return false;
		}
		if(!regtotal.test(total)) {
			alert("图书数量应在1~99中取值!");
			return false;
		}
		if(!(remarks.length > 50 && remarks.length < 800)) {
			alert("简介应为50~800字");
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->修改图书信息</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<%
		String bookId = "";
		String bookName = "";
		String author = "";
		String publishers = "";
		int typeId0 = 0;
		String amount = "";
		String total = "";
		String remarks = "";
		String status = "";
		String photo = "";
		String error = "";
		Iterator<BookType> qs2 = null;
		if(null != session.getAttribute("book")) {
			@SuppressWarnings("unchecked")
			Books book = (Books)session.getAttribute("book");
			String typeName = book.getTypeName();
			bookId = book.getBookId();
			bookName = book.getBookName();
			author = book.getAuthor();
			publishers = book.getPublishers();
			typeId0 = book.getTypeId();
			amount = String.valueOf(book.getAmount());
			total = String.valueOf(book.getTotal());
			remarks = book.getRemarks();
			status = book.getStatus();
			photo = book.getPhoto();
			session.setAttribute("bookId1", bookId);
			session.setAttribute("photo1", photo);
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
		if(null != session.getAttribute("bookTypeList")) {
			@SuppressWarnings("unchecked")
			List<BookType> bookTypeList = (List<BookType>)session.getAttribute("bookTypeList");
			qs2 = bookTypeList.iterator();
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
		if(null != request.getAttribute("error")) {
			error = (String)request.getAttribute("error");
		}
	
	%>
	<h1 align="center">修改图书信息</h1><hr>
	<form action="UpdateBookServlet" method="post" >
	<table align="center" style="font-size: 20px;">
		<tr>
			<td>图书封面：</td>
			<td align="center">
				<a href="updatePhoto.jsp">
					<img src="image/upload/<%=photo %>" width="128" height="168">
					更换封面
				</a>
			</td>
		</tr>
		<tr>
			<td>图书编号：</td>
			<td><%=bookId %></td>
			<td><input type="hidden" name="bookId" value="<%=bookId %>"></td>
		</tr>	
		<tr>
			<td>图书名称：</td>
			<td><input type="text" name="bookName" value="<%=bookName %>"></td>
		</tr>
		<tr>
			<td>作者：</td>
			<td><input type="text" name="author" value="<%=author %>"></td>
		</tr>
		<tr>
			<td>出版社：</td>
			<td><input type="text" name="publishers" value="<%=publishers %>"></td>
		</tr>
		<tr>
			<td>图书类型：</td>
			<td>
				<select name="typeId" >
				<%
				while(qs2.hasNext()) {
					BookType bookType = qs2.next();
					String typeName = bookType.getTypeName();
					int typeId = bookType.getTypeId();
				%>
					<option value="<%=typeId %>" <%if(typeId0 == typeId){%> selected="selected"<%} %>><%=typeName %></option>
				<%
				}
				%>
				</select>&nbsp;&nbsp;&nbsp;
				<a href="ManageBookTypeServlet?tag1=3">管理图书类型</a>
			</td>
		</tr>
		<tr>
			<td>图书库存：</td>
			<td><%=amount %></td>
			<td><input type="hidden" name="amount" value="<%=amount %>"></td>
		</tr>
		<tr>
			<td>图书总数量：</td>
			<td><input type="text" name="total" value="<%=total %>"></td>
			<td>
				<input type="hidden" name="oldTotal" value="<%=total %>">
			</td>
		</tr>
		<tr>
			<td>图书简介：</td>
			<td><textarea name="remarks" rows="10" cols="40" ><%=remarks %></textarea></td>
		</tr>
	</table> 
	<div align="center">
	<font color="red"><%=error %></font><br><br>
	<input type="hidden" name="status" value="<%=status %>">
	<input type="submit" value="修改" onclick="return limit();" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="window.location='ShowAllBooksServlet'" style="background-color:#66CCFF">
	</div>
	</form>
	<%@ include file="foot.jsp" %>
</body>
</html>