<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->每日图书</title>
</head>
<body>
	<%@ include file="../head.jsp" %>
	<hr>
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
	<div class="typeName"  align="center">
		<c:forEach items="${typeNameSet }" var="typeName">
					<a href="typeSearch.do?typeName=${typeName }">${typeName }</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
		</c:forEach>
	</div>
	<br><hr>
	<h1 align="center">新上架图书</h1><hr>
	<center>
    <table width="900" height="60" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td> 
          
          <!-- 商品循环开始 -->
       		<c:if test="${newBookList[0] == null }">
           		<center><font color="blue" size="5">今天暂无图书上架</font></center>
           </c:if>
           <c:set value="0" var="i" />
       <c:forEach items="${newBookList }" var="newBook">
          <div id="search" align="center">
             <dl>
               <dt class="picture">
               <c:set value="${i+1 }" var="i" />
               No${i }
                 <a href="../book/bookDetails.do?bookId=${newBook.bookId}&tag=11"> <img src="../image/upload/${newBook.photo}" width="130" height="180" border="1"/></a>
               </dt>
               	<dt>
               	书名:${newBook.bookName}<br>
               	作者:${newBook.author}<br>
               	状态:${newBook.status}<br>
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