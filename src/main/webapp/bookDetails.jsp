<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*, com.yuanjunye.www.service.UserService, com.yuanjunye.www.util.PageUtil,
java.text.SimpleDateFormat" %>

<link rel="stylesheet" type="text/css" href="css/comment.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	function limit() {
		var comments = document.getElementsByName("comments")[0].value;
		if(!(comments.length > 0)) {
			alert("评论不能为空!");
			return false;
		}else if(!(comments.length < 500)){
			alert("评论不能超过500字!");
			return false;
		}
	}
	
	function del() {
		var del = window.confirm("确定删除此评论吗？");
		return del;
	}
	
</script>

<title>图书管理系统->图书细节</title>
</head>
<body>
<%@ include file="head.jsp" %>
	<% 
		String error = "";
		String bookId = "";
		String bookName = "";
		String author = "";
		String publishers = "";
		String typeId = "";
		String amount = "";
		String total = "";
		String number = "";
		String remarks = "";
		String status = "";
		String photo = "";
		String typeName = "";
		if(null != session.getAttribute("aBook")) {
			@SuppressWarnings("unchecked")
			Books book = (Books)session.getAttribute("aBook");
				typeName = book.getTypeName();
				bookId = book.getBookId();
				bookName = book.getBookName();
				author = book.getAuthor();
				publishers = book.getPublishers();
				typeId = String.valueOf(book.getTypeId());
				amount = String.valueOf(book.getAmount());
				total = String.valueOf(book.getTotal());
				number = String.valueOf(book.getNumber());
				remarks = book.getRemarks();
				status = book.getStatus();
				photo = book.getPhoto();
		}else {
			response.sendRedirect("ban.jsp");
			return;
		}
		if(null != request.getAttribute("error")) {
			error = (String)request.getAttribute("error");
		}
		
	%>
	<center>
		<form action="SearchServlet" method="post">
			<table>
				<tr>
					<td><input type="text" name="keyword" ><input type="submit" value="搜索"></td>
					<td><a href="condition1.jsp?value=1">点击进入条件查询</a></td>
				</tr>
			</table>
		</form>
	</center>
	<h1 align="center">图书细节展示</h1><hr>
	
	<center>
      <table  height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
          <!-- 商品详情 -->
       
          <td width="70%" valign="top">
             <table align="center" style="font-size: 20px;">
               <tr>
                 <td rowspan="9"><img src="image/upload/<%=photo %>" width="220" height="300"/></td>
               </tr>
               <tr> 
                 <td><B>《<%=bookName %>》</B></td> 
               </tr>
               <tr>
                 <td>图书编号：<%=bookId %></td>
               </tr>
               <tr>
                 <td>作者：<%=author %></td>
               </tr>
               <tr>
                 <td>出版社：<%=publishers %></td>
               </tr>
               <tr>
                 <td>图片类型：<%=typeName %></td>
               </tr>
               <tr>
                 <td>库存：<%=amount %></td>
               </tr>
               <tr>
                 <td>借阅次数：<%=number %></td>
               </tr>
               <tr>
                 <td>状态：<%=status %></td>
               </tr>
               
             </table>
             
          </td>
        </tr>
          
       </table>	
       <table style="font-size: 20px;">
       	<tr>
			<td>图书简介：</td>
			<td>
			<textarea name="remarks" rows="10" cols="60" readonly="readonly" ><%=remarks %></textarea>
			</td> 
		</tr>
		</table>
		<br>
		<font color="red"><%=error %></font>
		<br>
		<a href="Favourite1Servlet?bookId=<%=bookId %>"><img  src="image/upload/image/collect.jpg"></a>
		<a href="BorrowServlet?bookId=<%=bookId %>"><img  src="image/upload/image/borrow.jpg"></a>
		<%	
			String tag = "";												//返回标签
			if(null != session.getAttribute("tag")) {
				tag = (String)session.getAttribute("tag");
				if(tag.equals("1")) {
		%>
		<a href="Login1Servlet"><img  src="image/upload/image/cancel.jpg"></a>	
		<%
				}else if(tag.equals("2")) {
		%>
		<a href="myStudentBorrow.jsp"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("3")) {
		%>
		<a href="myManagerBorrow.jsp"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("4")) {
		%>
		<a href="PassBorrowServlet?action=1"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("5")) {
		%>
		<a href="PassBorrowServlet?action=2"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("6")) {
		%>
		<a href="ShowAllBooksServlet"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("7")) {
		%>
		<a href="FavouriteServlet"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("8")) {
		%>
		<a href="search.jsp"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("9")) {
		%>
		<a href="findStudentBorrow.jsp"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("10")) {
		%>
		<a href="findManagerBorrow.jsp"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("11")) {
		%>
		<a href="searchNewBook.jsp"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("12")) {
		%>
		<a href="HotBookServlet"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}else if(tag.equals("13")) {
		%>
		<a href="Comment1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
		<%
				}
			}
		%>
   </center>
   <hr><br><br><br>
   <%
   		int k = 1;		//楼层
   		String userName = (String)session.getAttribute("userName");
		String identity = (String)session.getAttribute("identity");
		int currentPage = 1;
		PageUtil pUtil = null;
   		if(null != session.getAttribute("commentList")) {
   			@SuppressWarnings("unchecked")
   			List<Comment> commentList = (List<Comment>)session.getAttribute("commentList");
   			String pageStr = request.getParameter("page");
   			if (pageStr != null) {
	   			currentPage = Integer.parseInt(pageStr);
   			}
	   		pUtil = new PageUtil(5, commentList.size(), currentPage);
	   		currentPage = pUtil.getCurrentPage();
	   		k = (currentPage-1)*5+1;
   			for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++)  {
   				Comment comment = commentList.get(i);
   				String commentphoto = "";
   				String time = "";
   				if(null != comment.getTime() ) {
   					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
   					time = df.format(comment.getTime());
   				}
   				if(comment.getIdentity().equals("学生")) {
   					commentphoto = new UserService().findStudentPhoto(comment.getUserName());
   				}else {
   					commentphoto = new UserService().findManagerPhoto(comment.getUserName());
   				}
   
   %>
   		<div class="comment">
   			<h1 align="center"><%=k++ %>楼</h1>
   			<p>&nbsp;&nbsp;&nbsp;&nbsp;<%=comment.getUserName() %>(<%=comment.getIdentity() %>):</p>
   			<textarea rows="10" cols="80" class="te" readonly="readonly" ><%=comment.getComments() %></textarea>
			<div class="time"><%=time %></div>
	<%
				if(identity.equals("管理员")) {
	%>
		<div class="del">
			<a href="DeleteCommentServlet?id=<%=comment.getId() %>&bookId=<%=bookId %>&tag=<%=tag %>" onclick="return del();">
				<img alt="删除" src="image/upload/image/del.png" width="50" height="50" title="删除">
			</a>
		</div>
	<% 
				}
		
	%>
			<div class="commentphoto">
				<a href="LookUserServlet?userName=<%=comment.getUserName() %>&messageTag=1">
					<img alt="头像" src="image/upload/<%=commentphoto %>" width="100px" height="100px;" title="头像">
				</a>
			</div>
   		</div>
 
   <%
   			}  
   		}
   %>
   
   <div class="comment">
   	<h1 align="center">发表评论</h1>
   	<p>&nbsp;&nbsp;&nbsp;&nbsp;<%=userName %>(<%=identity %>):</p>
   	
   	<form action="CommentServlet" method="post">
   		<textarea rows="10" cols="80" class="te" name="comments"></textarea>
   		<input type="hidden" name="bookId" value="<%=bookId %>">
   		<input type="hidden" name="tag" value="<%=tag %>">
   		<br>
   		<input type="submit" value="发表" class="bu" onclick="return limit();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input type="reset" value="重置" class="bu">
   	</form>
   	<div class="reg">
   		<ol>
   			<li>和谐发言，不作偏激评论</li>
   			<li>文明评论，不恶意刷回复</li>
   			<li>尊重他人意见，拒绝辱骂</li>
   		</ol>
   	</div>
   </div>
   <center>
		   记录总数<%=pUtil.getRecordCount() %>条&nbsp;&nbsp;&nbsp; 
		   当前页/总页数:<%=currentPage %>/<%=pUtil.getPageCount()%>&nbsp;&nbsp;&nbsp;
		   每页显示<%=pUtil.getPageSize()%>条<br><br>
		<a href="bookDetails.jsp?page=1&tag=<%=tag %>&k=<%=k %>">首页</a>
		<a href="bookDetails.jsp?page=<%=(currentPage - 1)%>&tag=<%=tag %>">上页</a>
		<a href="bookDetails.jsp?page=<%=(currentPage + 1)%>&tag=<%=tag %>">下页</a>
		<a href="bookDetails.jsp?page=<%=pUtil.getPageCount()%>&tag=<%=tag %>">末页</a>
	</center>
   
   
  	<%@ include file="foot.jsp" %>	

</body>
</html>