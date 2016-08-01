<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<h1 align="center">图书上架</h1><hr>
	<form action="addBook2.do" method="post" enctype="multipart/form-data">
	<table align="center" style="font-size: 20px;">
		<tr>
			<td>图书编号：</td>
			<td><input type="text" name="bookId" value="${book.bookId}"><font color="red">${error1 }</font></td>
		</tr>	
		<tr>
			<td>图书名称：</td>
			<td><input type="text" name="bookName" value="${book.bookName}"></td>
		</tr>
		<tr>
			<td>作者：</td>
			<td><input type="text" name="author" value="${book.author}"></td>
		</tr>
		<tr>
			<td>出版社：</td>
			<td><input type="text" name="publishers" value="${book.publishers}"></td>
		</tr>
		<tr>
			<td>图书类型：</td>
			<td>
				<select name="typeId">
				
				<c:forEach items="${bookTypeList }" var="bookType">
					<option value="${bookType.typeId}" <c:if test="${book.typeId == bookType.typeId }"> selected="selected" </c:if>>${bookType.typeName}</option>
				</c:forEach>
				
				</select>&nbsp;&nbsp;&nbsp;
				<a href="manageBookType.do?tag1=1">管理图书类型</a>
			</td>
		</tr>
		<tr>
			<td>图书总数量：</td>
			<td><input type="text" name="total" value="${book.total}"></td>
		</tr>
		<tr>
			<td>图书简介：</td>
			<td><textarea name="remarks" rows="10" cols="40" >${book.remarks}</textarea></td>
		</tr>
		<tr>
			<td>图书封面：</td>
			<td><input type="file" name="file" size="20" value="${book.photo}"></td>
		</tr>
	</table>
	<center><font color="red">${error2 }</font></center>
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