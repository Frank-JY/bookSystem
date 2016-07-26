<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*" %>
<script type="text/javascript">
	function limit() {
		var regBookId = /^UTF1[\d]{4}$/;										//js正则表达式
		var regBookName = /^[\u4e00-\u9fa5|\w]{1,25}$/;    							
		var regAuthor = /^[\u4e00-\u9fa5]{1,5}(?:·[\u4e00-\u9fa5]{1,5})*$/;
		var regPublishers = /^[\u4e00-\u9fa5]{6,20}$/;
		var regtotal = /^([1-9]|[1-9][\d])$/;
		var regRemarks = /^.{50,800}$/;
		
		var bookId = document.getElementsByName("bookId")[0].value;				//获取元件的值
		var bookName = document.getElementsByName("bookName")[0].value;
		var author = document.getElementsByName("author")[0].value;
		var publishers = document.getElementsByName("publishers")[0].value;
		var total = document.getElementsByName("total")[0].value;
		var remarks = document.getElementsByName("remarks")[0].value;
		
		if(!regBookId.test(bookId)) {											//警告框
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
<title>图书管理系统->图书上架</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	<%
		String bookId = "";														//定义空的字符串
		String bookName = "";
		String author = "";
		String publishers = "";
		int typeIds = -1; 														
		String total = "";
		String remarks = "";
		String photo = "";
		String error1 = "";
		String error2 = "";
		Iterator<BookType> qs = null;
		if(null != session.getAttribute("bookTypeList")) {
			@SuppressWarnings("unchecked")					//忽略警告
			List<BookType> bookTypeList = (List<BookType>)session.getAttribute("bookTypeList");
			qs = bookTypeList.iterator();
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
		if(null != request.getAttribute("error2")) {
			error2 = (String)request.getAttribute("error2");
		}
		if(null != request.getAttribute("error1")) {
			error1 = (String)request.getAttribute("error1");
		}
		if(null != request.getAttribute("book")) {
			Books book = (Books)request.getAttribute("book");
			bookId = book.getBookId();
			bookName = book.getBookName();
			author = book.getAuthor();
			publishers = book.getPublishers();
			typeIds = Integer.valueOf(book.getTypeId());
			total = String.valueOf(book.getTotal());
			remarks = book.getRemarks();
			photo = book.getPhoto();
		}
	
	%>
	<h1 align="center">图书上架</h1><hr>
	<form action="addBook2.do" method="post" enctype="multipart/form-data">
	<table align="center" style="font-size: 20px;">
		<tr>
			<td>图书编号：</td>
			<td><input type="text" name="bookId" value="<%=bookId %>"><font color="red"><%=error1 %></font></td>
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
				<select name="typeId">
				<%
				while(qs.hasNext()) {									//遍历List
					BookType bookType = qs.next();
					String typeName = bookType.getTypeName();
					int typeId = bookType.getTypeId();
				%>
					<option value="<%=typeId %>" <%if(typeIds == typeId) { %> selected="selected" <%} %>><%=typeName %></option>
				<%
				}
				%>
				</select>&nbsp;&nbsp;&nbsp;
				<a href="manageBookType.do?tag1=1">管理图书类型</a>
			</td>
		</tr>
		<tr>
			<td>图书总数量：</td>
			<td><input type="text" name="total" value="<%=total %>"></td>
		</tr>
		<tr>
			<td>图书简介：</td>
			<td><textarea name="remarks" rows="10" cols="40" ><%=remarks %></textarea></td>
		</tr>
		<tr>
			<td>图书封面：</td>
			<td><input type="file" name="file" size="20" value="<%=photo %>"></td>
		</tr>
	</table>
	<center><font color="red"><%=error2 %></font></center>
	<br>
	<div align="center">
	<input type="reset" value="重置" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="submit" value="添加" onclick="return limit();" style="background-color:#66CCFF">&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="返回" onclick="window.location='Login1Servlet'" style="background-color:#66CCFF">
	</div>
	</form>
	<%@ include file="../foot.jsp" %>
</body>
</html>