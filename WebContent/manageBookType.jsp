<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.BookType, java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function del() {  
		var del = window.confirm("确定删除吗？(一旦删除相关信息全部删除!请慎重删除!)");
		return del;
	}
	function limit() {
		var regTypeName = /^[\u4e00-\u9fa5]{2,5}类$/;
		var typeName = document.getElementsByName("typeName")[0].value;
		if(!regTypeName.test(typeName)) {
			alert("图书类型格式为：××类");
			return false;
		}
	}
</script>
<title>图书管理系统->图书类型管理</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<%
		Iterator<BookType> qs = null;
		String error = "";
		if(null != session.getAttribute("bookTypeList")) {
			if(null != request.getAttribute("error")) {
				error = (String)request.getAttribute("error");
			}
			@SuppressWarnings("unchecked")
			List<BookType> bookTypeList = (List<BookType>)session.getAttribute("bookTypeList");
			qs = bookTypeList.iterator();
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
	
	%>
	<h1 align="center">图书类型管理</h1><hr>
	<table border="1" align="center" style="background-color:#88FFAA">
	<tr>
			<th width="100" >图书类型编号</th>
			<th width="150">图形类型</th>
			<th width="100" align="center">操作</th>
		</tr>
	<%
		int i = 1;
		while(qs.hasNext())
		{
			BookType bookType = qs.next();
			String typeName = bookType.getTypeName();
			int typeId = bookType.getTypeId();
	%>
		<tr>
			<td><%=i++ %></td>
			<td><%=typeName %></td>
			<td>
				<center>
					<a href="ManageBookType1Servlet?action=delete&typeId=<%=typeId %>" onclick="return del();">删除</a>
					<a href="ManageBookType1Servlet?action=update&typeId=<%=typeId %>" >修改</a>
				</center>
			</td>
		</tr>
	
	<%
		}
	%>
	</table><br>
	<form action="ManageBookType1Servlet?action=add" method="post">
	<table align="center">
		<tr>
			<td>图书类型：</td> 
			<td><input type="text" name="typeName"><font color="red"><%=error %></font></td>
		</tr>
	</table><br>
	<center>
		<input type="submit" value="添加" align="middle" style="background-color:#66CCFF" onclick="return limit();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</center>
	</form>
	<center>
		<font color="red">
			1、点击修改按钮可以修改图书类型名字。<br>
			2、****请慎重使用删除按钮，一旦使用，相应类型的图书信息会全部删除。****<br>
			3、可以添加图书类型。
		</font><br><br>
		<%
			if(null != session.getAttribute("tag1")) {
				String tag1 = (String)session.getAttribute("tag1");
				if(tag1.equals("1")) {
		%>
		<a href="AddBook1Servlet"><img  src="image/upload/image/cancel.jpg"></a>	
		<%
				}else if(tag1.equals("2")) {
		%>
		<a href="Login1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}
			}
		%>
	</center>
	<%@ include file="foot.jsp" %>
</body>
</html>