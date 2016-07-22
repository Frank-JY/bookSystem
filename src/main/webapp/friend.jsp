<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import="com.yuanjunye.www.po.*, java.util.* ,com.yuanjunye.www.service.UserService" %>

<link rel="stylesheet" type="text/css" href="css/friend.css">

<script type="text/javascript">
function pass() {
	var pass = window.confirm("确定通过申请吗？");
	return pass;
} 
function reject() {
	var reject = window.confirm("确定不同意此申请吗？");
	return reject;
}
function del() {
	var del = window.confirm("确定删除好友吗？");
	return del;
}
</script>

<title>图书管理系统->我的好友</title>
</head>
<body>
<%@ include file="head.jsp" %>
<h1 align="center">我的好友</h1>
	<%
		if(null != session.getAttribute("applyFriendList")) {
			@SuppressWarnings("unchecked")
			List<Friend> applyFriendList = (List<Friend>)session.getAttribute("applyFriendList");
			for(Friend friend : applyFriendList) {
				String myUserUserName = friend.getMyUserName();
				String friendUserName = friend.getFriendUserName();
				String identity = friend.getIdentity();
				String headphoto = "";
   				if(identity.equals("学生")) {
   					headphoto = new UserService().findStudentPhoto(myUserUserName);
   				}else {
   					headphoto = new UserService().findManagerPhoto(myUserUserName);
   				}	
	%>
	<div class="friend1">
		<div>
			<div class="headphoto1" align="center">
				<a href="LookUserServlet?userName=<%=myUserUserName %>&messageTag=6">
					<img alt="头像" src="image/upload/<%=headphoto %>" width="70px" height="70px;" title="头像">
					<br><%=myUserUserName %>(<%=identity %>)
				</a>
			</div>
			<div class="apply" align="center">
				<a href="FriendActionServlet?action=pass&friendUserName=<%=myUserUserName %>" onclick="return pass();">同意</a>&nbsp;&nbsp;&nbsp;
				<a href="FriendActionServlet?action=reject&friendUserName=<%=myUserUserName %>" onclick="return reject();">不同意</a>
			</div>
   		</div>
	</div>
	<%
			}
		}
	%>
	
	<%
		if(null != session.getAttribute("friendList")) {
			@SuppressWarnings("unchecked")
			List<Friend> friendList = (List<Friend>)session.getAttribute("friendList");
			for(Friend friend : friendList) {
				String myUserUserName = friend.getMyUserName();
				String friendUserName = friend.getFriendUserName();
				String identity = friend.getIdentity();
				String headphoto = "";
   				if(identity.equals("学生")) {
   					headphoto = new UserService().findStudentPhoto(friendUserName);
   				}else {
   					headphoto = new UserService().findManagerPhoto(friendUserName);
   				}	
	%>
	<div class="friend2">
		<div>
			<div class="headphoto1" align="center">
				<a href="LookUserServlet?userName=<%=friendUserName %>&messageTag=6">
					<img alt="头像" src="image/upload/<%=headphoto %>" width="70px" height="70px;" title="头像">
					<br><%=friendUserName %>(<%=identity %>)
				</a>
			</div>
			<div class="apply" align="center">
				<a href="FriendActionServlet?action=delete&friendUserName=<%=friendUserName %>" onclick="return del();">删除好友</a>
			</div>
		</div>
	</div>
	<%
			}
		}
	%>

<%@ include file="foot.jsp" %>
</body>
</html>