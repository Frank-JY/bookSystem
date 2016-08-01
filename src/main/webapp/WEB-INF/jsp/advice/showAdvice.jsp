<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*, com.yuanjunye.www.service.UserService, com.yuanjunye.www.util.PageUtil
,java.text.SimpleDateFormat" %>

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
<title>图书管理系统->查看意见反馈</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	<h1 align="center">意见反馈</h1><hr>
	
   <c:forEach items="${adviceList }" var="advice">
   <div class="comment">
   			<h1 align="center">第${advice.id}条意见</h1>
   			<p>&nbsp;&nbsp;&nbsp;&nbsp;${advice.userName}(${advice.identity}):</p>
   			<textarea rows="10" cols="80" class="te" readonly="readonly" >${advice.advice}</textarea>
			<div class="time"><fmt:formatDate value="${advice.time }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		<div class="del">
			<a href="../advice/deleteAdvice.do?id=${advice.id}" onclick="return del();">
				<img alt="删除" src="../image/upload/image/del.png" width="50" height="50" title="删除">
			</a>
		</div>
			<div class="commentphoto">
				<a href="../user/lookUser.do?userName=${advice.userName}&messageTag=2">
					<img alt="头像" src="../image/upload/${advice.photo}" width="100px" height="100px;" title="头像">
				</a>
			</div>
   		</div>
 </c:forEach>

   <center>
		   记录总数${pUtil.recordCount}条&nbsp;&nbsp;&nbsp; 
		   当前页/总页数:${pUtil.currentPage}/${pUtil.pageCount}&nbsp;&nbsp;&nbsp;
		   每页显示${pUtil.pageSize}条<br><br>
		<a href="advice1.do?page=1">首页</a>
		<a href="advice1.do?page=${pUtil.currentPage - 1}">上页</a>
		<a href="advice1.do?page=${pUtil.currentPage + 1}">下页</a>
		<a href="advice1.do?page=${pUtil.pageCount}">末页</a>
		<form action="advice1.do">
			<input type="text" value="${pUtil.currentPage}" name="page">
			<input type="submit" value="跳转" onclick="return limit();">
		</form>
	</center>

<%@ include file="../foot.jsp" %>	
</body>
</html>