<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" type="text/css" href="../css/about.css">
<script type="text/javascript">
	function limit() {
		var advice = document.getElementsByName("advice")[0].value;			//正则表达式
		if(!(advice.length > 0)) {
			alert("评论不能为空!");
			return false;
		}else if(!(advice.length < 500)){
			alert("评论不能超过500字!");
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统->关于我们</title>
</head>
<body>
<%@ include file="../head.jsp" %>

	<%
	String userName = (String)session.getAttribute("userName");
	String identity = (String)session.getAttribute("identity");
	
	%>

	<div class="about">
   	<h1 align="center">意见反馈</h1>
   	<p class="pp">&nbsp;&nbsp;&nbsp;&nbsp;<%=userName %>(<%=identity %>):</p> 	
   	<form action="advice.do" method="post">
   		<textarea rows="10" cols="80" class="te" name="advice"></textarea>
   		<br>
   		<input type="submit" value="提交" class="bu" onclick="return limit();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input type="reset" value="重置" class="bu">
   	</form>
   	<div class="reg">
   		<ol>
   			<li>在这里，您可以反馈您对我们的看法和意见。</li>
   			<li>我们非常需要您公正而实用的评价</li>
   			<li>您对本系统的支持，是对我们最大的鼓励</li>
   		</ol>
   	</div>
   </div>
	
<%@ include file="../foot.jsp" %>
</body>
</html>