<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.yuanjunye.www.po.*, java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->查询结果</title>
</head>
<body>
<%@ include file="head.jsp" %>
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
	
	<center>
    <table width="900" height="60" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td> 
          
          <!-- 商品循环开始 -->
           <%     
       	Iterator<Books> qs1 = null;
          	if(null != session.getAttribute("searchBooksList")) {
          		@SuppressWarnings("unchecked")
          		List<Books> searchBooksList = (List<Books>)session.getAttribute("searchBooksList");
      			qs1 = searchBooksList.iterator();
      		}else {
      			response.sendRedirect("ban.jsp");
      			return;
  	    	}
          	int i = 1;
          	if(!qs1.hasNext()) {
          		out.write("无相关记录！");
          	}
          	while(qs1.hasNext())
   		{
   			Books book = qs1.next();
   			String typeName = book.getTypeName();		
    			
           %>  
       
          <div id="search" align="center">
             <dl>
               <dt class="picture">
               No<%=i++ %>:
                 <a href="BookDetailsServleet?bookId=<%=book.getBookId() %>&tag=8"> <img src="image/upload/<%=book.getPhoto() %>" width="130" height="180" border="1"/></a>
               </dt>
               	<dt>
               	书名:<%=book.getBookName() %><br>
               	作者:<%=book.getAuthor() %><br>
               	状态: <%=book.getStatus() %><br>
               </dt>
             </dl>
          </div>
          <!-- 商品循环结束 -->
        
          <%
             } 
          %>
        </td>
        
      </tr>
    </table>
    <a href="Login1Servlet"><img  src="image/upload/image/cancel.jpg"></a>
    </center>
    <%@ include file="foot.jsp" %>
</body>
</html>