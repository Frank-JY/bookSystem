<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<%@ include file="../head.jsp" %>

	<h1 align="center">图书类型管理</h1><hr>
	<table border="1" align="center" style="background-color:#88FFAA">
	<tr>
			<th width="100" >图书类型编号</th>
			<th width="150">图形类型</th>
			<th width="100" align="center">操作</th>
		</tr>

	<c:forEach items="${bookTypeList }" var="bookType">
	<c:set value="0" var="i" />
		<tr>
			<c:set value="${i+1 }" var="i" />
			<td>${i }</td>
			<td>${bookType.typeName}</td>
			<td>
				<center>
					<a href="manageBookType1.do?action=delete&typeId=${bookType.typeId}" onclick="return del();">删除</a>
					<a href="manageBookType1.do?action=update&typeId=${bookType.typeId}" >修改</a>
				</center>
			</td>
		</tr>
	</c:forEach>

	</table><br>
	<form action="manageBookType1.do?action=add" method="post">
	<table align="center">
		<tr>
			<td>图书类型：</td> 
			<td><input type="text" name="typeName"><font color="red">${error}</font></td>
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
		
		<c:choose>
			<c:when test="${tag1 =='1' }">
				<a href="addBook1.do"><img  src="../image/upload/image/cancel.jpg"></a>	
			</c:when>
			<c:when test="${tag1 =='2' }">
				<a href="../log/login2.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag1 =='3' }">
				<a href="showAllBooks.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
		</c:choose>

	</center>
	<%@ include file="../foot.jsp" %>
</body>
</html>