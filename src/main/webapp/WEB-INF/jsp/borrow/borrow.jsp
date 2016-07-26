<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
      function add()
      {
         var num = parseInt(document.getElementById("number").value);
         if(num<60)
         {
            document.getElementById("number").value = ++num;
         }
      }
      function sub()
      {
         var num = parseInt(document.getElementById("number").value);
         if(num>10)
         {
            document.getElementById("number").value = --num;
         }
      }
</script>

<style type="text/css">
	span{
	     padding:0 1px;border:1px #c0c0c0 solid;cursor:pointer;background: #c0CCDD
	   }
</style>

<title>图书管理系统->申请图书借阅</title>
</head>
<body>
	<%@ include file="../head.jsp" %>

	<h1 align="center">申请图书借阅</h1><hr>
	
	<center>
		<form action="borrow1.do" method="post">
			<table style="font-size: 20px;">
				<tr>
					<td>图书编号：</td>
					<td>${borrowUser.bookId }</td>
					<td><input type="hidden" name="bookId" value="${borrowUser.bookId }"></td>
				</tr>
				<tr>
					<td>图书名：</td>
					<td>${borrowUser.bookName }</td>
					<td><input type="hidden" name="bookName" value="${borrowUser.bookName }"></td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td>${borrowUser.userName }</td>
					<td><input type="hidden" name="userName" value="${borrowUser.userName }"></td>
				</tr>
				<tr>
					<td>学号(职工号)：</td>
					<td>${borrowUser.readerId }</td>
					<td><input type="hidden" name="readerId" value="${borrowUser.readerId }"></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td>${borrowUser.name }</td>
					<td><input type="hidden" name="name" value="${borrowUser.name }"></td>
				</tr>
				<tr>
					<td>身份：</td>
					<td>${borrowUser.identity }</td>
					<td><input type="hidden" name="identity" value="${borrowUser.identity }"></td>
				</tr>
				<tr>
                 <td>借阅天数：</td>
                 <td>
                 	<span id="sub" onclick="sub();">－</span><input type="text" id="number" name="day" value="30" size="2" readonly="readonly" align="middle"/><span id="add" onclick="add();">＋</span>
               	 </td>
               </tr> 
			</table>
			<br>
			<font color="red">${error }</font>
			<br>
			<input type="submit" value="申请">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" onclick="window.location='toBookDetails.do'">
		</form>
	</center>
<%@ include file="../foot.jsp" %>
</body>
</html>