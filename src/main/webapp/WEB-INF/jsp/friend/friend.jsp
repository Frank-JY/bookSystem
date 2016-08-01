<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/friend.css">

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
<%@ include file="../head.jsp" %>
<h1 align="center">我的好友</h1>

	<c:forEach items="${applyFriendList }" var="applyFriend">
	<div class="friend1">
		<div>
			<div class="headphoto1" align="center">
				<a href="../user/lookUser.do?userName=${applyFriend.myUserUserName}&messageTag=6">
					<img alt="头像" src="../image/upload/${applyFriend.photo}" width="70px" height="70px;" title="头像">
					<br>${applyFriend.myUserUserName}(${applyFriend.identity})
				</a>
			</div>
			<div class="apply" align="center">
				<a href="friendAction.do?action=pass&friendUserName=${applyFriend.myUserUserName}" onclick="return pass();">同意</a>&nbsp;&nbsp;&nbsp;
				<a href="friendAction.do?action=reject&friendUserName=${applyFriend.myUserUserName}" onclick="return reject();">不同意</a>
			</div>
   		</div>
	</div>
	</c:forEach>

	

	<c:forEach items="${friendList }" var="friend">
	<div class="friend2">
		<div>
			<div class="headphoto1" align="center">
				<a href="LookUserServlet?userName=${friend.friendUserName}&messageTag=6">
					<img alt="头像" src="../image/upload/${friend.photo}" width="70px" height="70px;" title="头像">
					<br>${friend.friendUserName}(${friend.identity})
				</a>
			</div>
			<div class="apply" align="center">
				<a href="../friend/friendAction.do?action=delete&friendUserName=${friend.friendUserName}" onclick="return del();">删除好友</a>
			</div>
		</div>
	</div>
	</c:forEach>


<%@ include file="../foot.jsp" %>
</body>
</html>