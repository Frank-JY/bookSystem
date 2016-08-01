<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">

#new li {
	width: 300px;
	float: left;
	list-style: none;
	position:relative;
	left:400px;
	top: 15px;
}


#new li a img:hover{
	-webkit-transform:scale(1.3,1.3);
    -moz-transform:scale(1.3,1.3);
    -transform:scale(1.3,1.3);
}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>主页</title>
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
	<div class="typeName"  align="center">
			
		<c:forEach var="typeName" items="${typeNameSet }">
	
					<a href="../book/typeSearch.do?typeName=${typeName }">${typeName }</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
		</c:forEach>
	
	</div>
	
	<div id="new" align="center">
		<ul>
			<li><a href="../book/newBook.do"><img alt="每日新书" src="../image/upload/image/newbook.jpg" width="250px" height="180px" title="每日新书"></a></li>
			<li><a href="../book/hotBook.do"><img alt="热门推介" src="../image/upload/image/hotbook.jpg" width="250px" height="180px" title="热门图书推介"></a></li>
		</ul>
	</div>
	<br><br><br>
	 <center>
    <table width="900" height="60" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td>
          
          <!-- 商品循环开始 -->
          
           <c:forEach items="${booksSet }" var="book">
           
          <div id="detail" align="center">
             <dl>
               <dt class="picture">
                 <a href="../book/bookDetails.do?bookId=${book.bookId}&tag=1"> <img src="../image/upload/${book.photo}" width="130" height="180" border="1"/></a>
               </dt>
               <dd class="dd_name">《${book.bookName}》</dd> 
               <dd class="dd_city">作者:${book.author}</dd>
               	<dd>状态: ${book.status}</dd> 
             </dl>
          </div>  
          <!-- 商品循环结束 -->
       		 </c:forEach>

        </td>
      </tr>
    </table>
    </center>
	
	<%@ include file="../foot.jsp" %>
</body>
</html>