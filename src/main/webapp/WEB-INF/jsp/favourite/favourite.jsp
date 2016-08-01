<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function del() {
		var del = window.confirm("确定删除吗");
		return del;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->收藏夹</title>
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
	<h1 align="center">收藏夹</h1><hr>
	
	<center>
    <table width="900" height="60" cellpadding="0" cellspacing="0" border="0">
      <tr>
         <td> 
          <!-- 商品循环开始 -->

           <c:set value="0" var="i" />
       <c:forEach items="${booksList }" var="book">
          <div id="collect" align="center">
             <dl>
               <dt class="picture">
               <c:set value="${i+1 }" var="i" />
               No${i }:
                 <a href="../book/bookDetails.do?bookId=${book.bookId}&tag=7"> <img src="../image/upload/${book.photo}" width="130" height="180" border="1"/></a>
               </dt>
               	<dt>
               	书名:${book.bookName}<br>
               	作者:${book.author}<br>
               	状态:${book.status}<br>
               </dt>
               <dt>
               <a href="deleteFavourite.do?bookId=${book.bookId}" onclick="return del();"><img  src="../image/upload/image/delete.jpg"></a>
               </dt>
             </dl>
          </div>
          <!-- 商品循环结束 -->
        </c:forEach>

        </td>
        
      </tr>
    </table>
    <a href="../log/login2.do"><img  src="../image/upload/image/cancel.jpg"></a>
    </center>
    <%@ include file="../foot.jsp" %>
</body>
</html>