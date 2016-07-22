<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*" %>
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
	<%@ include file="head.jsp" %>

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
		<%
		if(null != session.getAttribute("typeNameSet")) {
			@SuppressWarnings("unchecked")
			Set<String> typeNameSet = (Set<String>)session.getAttribute("typeNameSet");
			for(String typeName : typeNameSet) {
	%>			
					<a href="TypeSearchServlet?typeName=<%=typeName %>"><%=typeName %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
	<%	
			}
		}
	%>
	</div>
	
	<div id="new" align="center">
		<ul>
			<li><a href="NewBookServlet"><img alt="每日新书" src="image/upload/image/newbook.jpg" width="250px" height="180px" title="每日新书"></a></li>
			<li><a href="HotBookServlet"><img alt="热门推介" src="image/upload/image/hotbook.jpg" width="250px" height="180px" title="热门图书推介"></a></li>
		</ul>
	</div>
	<br><br><br>
	 <center>
    <table width="900" height="60" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td>
          
          <!-- 商品循环开始 -->
           <% 
           	Iterator<Books> qs1 = null;
           	if(null != session.getAttribute("booksSet")) {
           		@SuppressWarnings("unchecked")
           		Set<Books> booksSet = (Set<Books>)session.getAttribute("booksSet");
       			qs1 = booksSet.iterator();
       		}else {
       			response.sendRedirect("ban.jsp");
       			return;
   	    	}
           
           	while(qs1.hasNext())
    		{
    			Books book = qs1.next();
    			 
           %>   
          <div id="detail" align="center">
             <dl>
               <dt class="picture">
                 <a href="BookDetailsServleet?bookId=<%=book.getBookId() %>&tag=1"> <img src="image/upload/<%=book.getPhoto() %>" width="130" height="180" border="1"/></a>
               </dt>
               <dd class="dd_name">《<%=book.getBookName() %>》</dd> 
               <dd class="dd_city">作者:<%=book.getAuthor() %></dd>
               	<dd>状态: <%=book.getStatus() %></dd> 
             </dl>
          </div>  
          <!-- 商品循环结束 -->
        
          <% 
             } 
          %>
        </td>
      </tr>
    </table>
    </center>
	
	<%@ include file="foot.jsp" %>
</body>
</html>