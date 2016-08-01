<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*, com.yuanjunye.www.service.UserService, com.yuanjunye.www.util.PageUtil,
java.text.SimpleDateFormat" %>

<link rel="stylesheet" type="text/css" href="../css/comment.css">

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
	function limit1() {
		var page = document.getElementsByName("page")[0].value;
		var regPage = /^([1-9][\d]|[1-9]+)$/;
		if(!regPage.test(page)) {
			alert("请正确输入");
			return false;
		}
	}
</script>

<title>图书管理系统->图书细节</title>
</head>
<body>
<%@ include file="../head.jsp" %>
	
	<center>
		<form action="../book/search.do" method="post">
			<table>
				<tr>
					<td><input type="text" name="keyword" ><input type="submit" value="搜索"></td>
					<td><a href="../book/toCondition1.do?value=1">点击进入条件查询</a></td>
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
                 <td rowspan="9"><img src="../image/upload/${aBook.photo}" width="220" height="300"/></td>
               </tr>
               <tr> 
                 <td><B>《${aBook.bookName}》</B></td> 
               </tr>
               <tr>
                 <td>图书编号：${aBook.bookId}</td>
               </tr>
               <tr>
                 <td>作者：${aBook.author}</td>
               </tr>
               <tr>
                 <td>出版社：${aBook.publishers}</td>
               </tr>
               <tr>
                 <td>图片类型：${aBook.typeName}</td>
               </tr>
               <tr>
                 <td>库存：${aBook.amount}</td>
               </tr>
               <tr>
                 <td>借阅次数：${aBook.number}</td>
               </tr>
               <tr>
                 <td>状态：${aBook.status}</td>
               </tr>
               
             </table>
             
          </td>
        </tr>
          
       </table>	
       <table style="font-size: 20px;">
       	<tr>
			<td>图书简介：</td>
			<td>
			<textarea name="remarks" rows="10" cols="60" readonly="readonly" >${aBook.remarks}</textarea>
			</td> 
		</tr>
		</table>
		<br>
		<font color="red">${error}</font>
		<br>
		<a href="../favourite/favourite1.do?bookId=${aBook.bookId}"><img  src="../image/upload/image/collect.jpg"></a>
		<a href="../borrow/borrow.do?bookId=${aBook.bookId}"><img  src="../image/upload/image/borrow.jpg"></a>
		
		<c:choose>
			<c:when test="${tag == '1'}">
				<a href="../log/login2.do"><img  src="../image/upload/image/cancel.jpg"></a>	
			</c:when>
			<c:when test="${tag == '2'}">
				<a href="../borrow/toMyStudentBorrow.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '3'}">
				<a href="../borrow/toMyManagerBorrow.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '4'}">
				<a href="../borrow/passBorrow.do?action=1"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '5'}">
				<a href="../borrow/passBorrow.do?action=2"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '6'}">
				<a href="../book/showAllBooks.do"><img  src="../image/upload/image/cancel.jpg"></a>	
			</c:when>
			<c:when test="${tag == '7'}">
				<a href="../favourite/favourite.do"><img  src="../image/upload/image/cancel.jpg"></a>	
			</c:when>
			<c:when test="${tag == '8'}">
				<a href="../book/search.do"><img  src="../image/upload/image/cancel.jpg"></a>	
			</c:when>
			<c:when test="${tag == '9'}">
				<a href="../book/toFindStudentBorrow.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '10'}">
				<a href="../book/toFindManagerBorrow.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '11'}">
				<a href="../book/newBook.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '12'}">
				<a href="../book/hotBook.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
			<c:when test="${tag == '13'}">
				<a href="../comment/comment1.do"><img  src="../image/upload/image/cancel.jpg"></a>
			</c:when>
		</c:choose>
		
   </center>
   <hr><br><br><br>

   <c:set var="k" value="${(pUtil.currentPage-1)*5 }"></c:set>
   <c:forEach items="${commentList }" var="comment">
   		<div class="comment">
   			<c:set value="${k+1 }" var="k" />
   			<h1 align="center">${k }楼</h1>
   			<p>&nbsp;&nbsp;&nbsp;&nbsp;${comment.userName}(${comment.identity}):</p>
   			<textarea rows="10" cols="80" class="te" readonly="readonly" >${comment.comments}</textarea>
			<div class="time"><fmt:formatDate value="${comment.time }" pattern="yyyy-MM-dd HH:mm:ss"/></div>

			<c:if test="${identiy == '管理员' }">
		<div class="del">
			<a href="../comment/deleteComment.do?id=${comment.id}&bookId=${aBook.bookId}&tag=${tag}" onclick="return del();">
				<img alt="删除" src="../image/upload/image/del.png" width="50" height="50" title="删除">
			</a>
		</div>
		</c:if>

			<div class="commentphoto">
				<a href="../user/lookUser.do?userName=${comment.userName}&messageTag=1">
					<img alt="头像" src="../image/upload/${comment.photo}" width="100px" height="100px;" title="头像">
				</a>
			</div>
   		</div>
 
   </c:forEach>
   <div class="comment">
   	<h1 align="center">发表评论</h1>
   	<p>&nbsp;&nbsp;&nbsp;&nbsp;${userName}(${identity}):</p>
   	
   	<form action="../comment/comment.do" method="post">
   		<textarea rows="10" cols="80" class="te" name="comments"></textarea>
   		<input type="hidden" name="bookId" value="${aBook.bookId}">
   		<input type="hidden" name="tag" value="${tag} }">
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
		   记录总数${pUtil.recordCount}条&nbsp;&nbsp;&nbsp; 
		   当前页/总页数:${pUtil.currentPage}/${pUtil.pageCount}&nbsp;&nbsp;&nbsp;
		   每页显示${pUtil.pageSize}条<br><br>
		<a href="../borrow/toBookDetails.do?page=1&bookId=${aBook.bookId}">首页</a>
		<a href="../borrow/toBookDetails.do?page=${pUtil.currentPage - 1}&bookId=${aBook.bookId}">上页</a>
		<a href="../borrow/toBookDetails.do?page=${pUtil.currentPage + 1}&bookId=${aBook.bookId}">下页</a>
		<a href="../borrow/toBookDetails.do?page=${pUtil.pageCount}&bookId=${aBook.bookId}">末页</a>
		<form action="../borrow/toBookDetails.do">
			<input type="text" value="${pUtil.currentPage}" name="page">
			<input type="hidden" name="bookId" value="${aBook.bookId}">
			<input type="submit" value="跳转" onclick="return limit1();">
		</form>
	</center> 
   
  	<%@ include file="../foot.jsp" %>	

</body>
</html>