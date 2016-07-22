<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" type="text/css" href="css/head.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>
	<%
		String userName0 = (String)session.getAttribute("userName");
		String photo0 = (String)session.getAttribute("photo");
	%>
	<!--  
	<div id="menu" class="menu">
      <div>
      	<div>
      		<a href="main.jsp" id="key">主页</a>
      	</div>
        <ul class="menu-ul">
        <li><a href="PassUserServlet">查看新用户注册申请</a></li>
        <li><a href="ShowAllUserServlet?action=1">查看用户信息</a></li>
        <li><a href="ManageBookTypeServlet?tag1=2">管理图书类型</a></li>
        <li><a href="AddBook1Servlet">上架图书</a></li>
        <li><a href="ShowAllBooksServlet">图书管理</a></li>
        <li><a href="MyBorrowServlet">个人借阅记录</a></li>
        <li><a href="PassBorrowServlet?action=1">查看借阅申请</a></li>
        <li><a href="FavouriteServlet">收藏夹</a></li>
        </ul>
      </div>	  
	</div>
	-->
	<div class="top">
		<div id="logo"><a href="Login1Servlet">主页</a></div>
		<div id="text">图书管理系统</div>
		<ul class="function1">
		<li><a href="MyUserServlet">个人信息</a></li>
		<li><a href="password.jsp">修改密码</a></li>
		<li><a href="advice.jsp">意见反馈</a></li>
		<li><a href="CancelServlet">注销</a></li>
		</ul>
		<div class="headphoto">
			<a href="MyUserServlet">
				<img alt="头像" src="image/upload/<%=photo0 %>" width="80px" height="80px">
			</a>
		</div>
	</div>
	<div class="top2">
		<div class="user">
			<%=userName0 %>，你好
		</div>
	</div>
	<%
		String identity0 = (String)session.getAttribute("identity");
		if(identity0.equals("学生")) {
	
	%>
	<div class="top3">
		<div>
      		<a href="Login1Servlet" id="keys">主页</a>
      	</div>
        <ul class="top3-ul">
        <li><a href="MyBorrowServlet">个人借阅记录</a></li>
        <li><a href="FavouriteServlet">收藏夹</a></li>
        <li><a href="Comment1Servlet">个人书评记录</a></li> 
        <li><a href="Friend1Servlet">我的好友</a></li> 
        </ul>
	</div>
	<%
		}else {
	%>
	<div class="top3">
		<div>
      		<a href="Login1Servlet" id="keys">主页</a>
      	</div>
        <ul class="top3-ul">
        <li><a href="MyBorrowServlet">个人借阅记录</a></li>
        <li><a href="FavouriteServlet">收藏夹</a></li>
        <li><a href="Comment1Servlet">个人书评记录</a></li>
        <li><a href="Friend1Servlet">我的好友</a></li> 
        <li><a href="PassUserServlet">查看注册申请</a></li>
        <li><a href="PassBorrowServlet?action=1">查看借阅申请</a></li>
        <li><a href="AddBook1Servlet">上架图书</a></li>   
        <li><a href="ShowAllBooksServlet">图书管理</a></li> 
        <li><a href="ManageBookTypeServlet?tag1=2">管理图书类型</a></li>
        <li><a href="ShowAllUserServlet?action=1">查看用户信息</a></li>  
        <li><a href="Advice1Servlet">查看意见反馈</a></li> 
        </ul>
	</div>
	<%
		}
	%>
	<hr id="hr1">
	<div class="backtop">
		<a href="#">顶部</a>
	</div>
</body>
</html>