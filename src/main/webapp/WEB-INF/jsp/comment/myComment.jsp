<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="../css/comment.css">

<script type="text/javascript">
function del() {
	var del = window.confirm("确定删除此评论吗？");
	return del;
}
function limit() {
	var page = document.getElementsByName("page")[0].value;
	var regPage = /^([1-9][\d]|[1-9]+)$/;
	if(!regPage.test(page)) {
		alert("请正确输入");
		return false;
	}
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->查看我的书评</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	<h1 align="center">我的书评</h1><hr>
	
   <c:forEach items="${myCommentList }" var="comment">
   <div class="comment">
   			<h1 align="center">《${comment.book.bookName}》书评</h1>
   			<p>&nbsp;&nbsp;&nbsp;&nbsp;${comment.userName}(${comment.identity}):</p>
   			<textarea rows="10" cols="80" class="te" readonly="readonly" >${comment.comments}</textarea>
			<div class="time"><fmt:formatDate value="${comment.time }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			<div class="del">
				<a href="../comment/deleteComment1.do?id=${comment.id}" onclick="return del();">
					<img alt="删除" src="../image/upload/image/del.png" width="50" height="50" title="删除">
				</a>
			</div>
			<div class="commentphoto">
				<a href="../user/lookUser.do?userName=${comment.userName}&identity=${comment.identity}&messageTag=5">
					<img alt="头像" src="../image/upload/${comment.photo}" width="100px" height="100px;" title="头像">
				</a>
			</div>
			<div class="bookcommentphoto">
				<a href="../book/bookDetails.do?bookId=${comment.book.bookId}&tag=13">
					<img alt="${comment.book.bookName}" src="../image/upload/${comment.book.photo}" title="${comment.book.bookName}" width="60px" height="80px">
				</a>
			</div>
   	</div>
 </c:forEach>

   <center>
		   记录总数${pUtil.recordCount}条&nbsp;&nbsp;&nbsp; 
		   当前页/总页数:${pUtil.currentPage}/${pUtil.pageCount}&nbsp;&nbsp;&nbsp;
		   每页显示${pUtil.pageSize}条<br><br>
		<a href="comment1.do?page=1">首页</a>
		<a href="comment1.do?page=${pUtil.currentPage - 1}">上页</a>
		<a href="comment1.do?page=${pUtil.currentPage + 1}">下页</a>
		<a href="comment1.do?page=${pUtil.pageCount}">末页</a>
		<form action="comment1.do">
			<input type="text" value="${pUtil.currentPage}" name="page">
			<input type="submit" value="跳转" onclick="return limit();">
		</form>
	</center>
<%@ include file="../foot.jsp" %>	
</body>
</html>