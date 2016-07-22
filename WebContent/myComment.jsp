<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*, com.yuanjunye.www.service.*, com.yuanjunye.www.util.PageUtil" %>

<link rel="stylesheet" type="text/css" href="css/comment.css">

<script type="text/javascript">
function del() {
	var del = window.confirm("确定删除此评论吗？");
	return del;
}
		
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->查看我的书评</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<h1 align="center">我的书评</h1><hr>
	
	<%
   		String userName = (String)session.getAttribute("userName");
		String identity = (String)session.getAttribute("identity");
		int currentPage = 1;
		PageUtil pUtil = null;
   		if(null != session.getAttribute("myCommentList")) {
   			@SuppressWarnings("unchecked")
   			List<Comment> myCommentList = (List<Comment>)session.getAttribute("myCommentList");
   			String pageStr = request.getParameter("page");
   			if (pageStr != null) {
	   			currentPage = Integer.parseInt(pageStr);
   			}
	   		pUtil = new PageUtil(5, myCommentList.size(), currentPage);
	   		currentPage = pUtil.getCurrentPage();
   			for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++)  {
   				Comment comment = myCommentList.get(i);
   				Books book  = new BookService().findBook(comment.getBookId());
   				String commentphoto = "";
   				if(comment.getIdentity().equals("学生")) {
   					commentphoto = new UserService().findStudentPhoto(comment.getUserName());
   				}else {
   					commentphoto = new UserService().findManagerPhoto(comment.getUserName());
   				}
   
   %>
   <div class="comment">
   			<h1 align="center">《<%=book.getBookName() %>》书评</h1>
   			<p>&nbsp;&nbsp;&nbsp;&nbsp;<%=comment.getUserName() %>(<%=comment.getIdentity() %>):</p>
   			<textarea rows="10" cols="80" class="te" readonly="readonly" ><%=comment.getComments() %></textarea>
			<div class="time"><%=comment.getTime() %></div>
			<div class="del">
				<a href="DeleteComment1Servlet?id=<%=comment.getId() %>" onclick="return del();">
					<img alt="删除" src="image/upload/image/del.png" width="50" height="50" title="删除">
				</a>
			</div>
			<div class="commentphoto">
				<a href="LookUserServlet?userName=<%=comment.getUserName() %>&identity=<%=comment.getIdentity() %>&messageTag=5">
					<img alt="头像" src="image/upload/<%=commentphoto %>" width="100px" height="100px;" title="头像">
				</a>
			</div>
			<div class="bookcommentphoto">
				<a href="BookDetailsServleet?bookId=<%=book.getBookId() %>&tag=13">
					<img alt="<%=book.getBookName() %>" src="image/upload/<%=book.getPhoto() %>" title="<%=book.getBookName() %>" width="60px" height="80px">
				</a>
			</div>
   	</div>
 
   <%
   			}  
   		}else {
   			response.sendRedirect("ban.jsp");
   		}
   %>
   <center>
		   记录总数<%=pUtil.getRecordCount() %>条&nbsp;&nbsp;&nbsp; 
		   当前页/总页数:<%=currentPage %>/<%=pUtil.getPageCount()%>&nbsp;&nbsp;&nbsp;
		   每页显示<%=pUtil.getPageSize()%>条<br><br>
		<a href="myComment.jsp?page=1">首页</a>
		<a href="myComment.jsp?page=<%=(currentPage - 1)%>">上页</a>
		<a href="myComment.jsp?page=<%=(currentPage + 1)%>">下页</a>
		<a href="myComment.jsp?page=<%=pUtil.getPageCount()%>">末页</a>
	</center>
<%@ include file="foot.jsp" %>	
</body>
</html>