<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->热门图书推介</title>
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
	<h1 align="center">人气最高的八本书</h1><hr>
	<center>
    <table width="900" height="60" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td> 
          
          <!-- 商品循环开始 -->
          <c:if test="${hotBookList[0] == null }">
           		<center><font color="blue" size="5">无相关记录！</font></center>
           </c:if>
            <c:set value="0" var="i" /> 
       <c:forEach items="${hotBookList }" var="hotBook" begin="0" end="7">
          <div id="search" align="center">
             <dl>
               <dt class="picture">
              <c:set value="${i+1 }" var="i" />
               No${i }
                 <a href="../book/bookDetails.do?bookId=${hotBook.bookId}&tag=12"> <img src="../image/upload/${hotBook.photo}" width="130" height="180" border="1"/></a>
               </dt>
               	<dt>
               	书名:${hotBook.bookName}<br>
               	作者:${hotBook.author}<br>
               	状态:${hotBook.status}<br>
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