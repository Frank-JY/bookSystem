<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center"><a href="log/toRegister.do">注册</a></h1>
	<br><br><br><br><br><br>
	<h1 align="center"><a href="log/toLogin.do">登录</a></h1>
	<br>
	<form action="user.html" method="post">
 <table width="207" border="0" align="center">
        <tr>
          <td colspan="2" align="center" nowrap="nowrap">用户注册</td>
        </tr>
        <tr>
          <td width="68" nowrap="nowrap">用户名</td>
          <td width="127" nowrap="nowrap"><label>
            <input name="username" type="text" id="username" size="20" />
          </label></td>
        </tr>
        <tr>
          <td nowrap="nowrap">密　码</td>
          <td nowrap="nowrap"><input name="password" type="password" id="password" size="20" maxlength="10" /></td>
        </tr>
        <tr><td>验证码</td><td><input id="index_code" name="code" type="text" /></td>
       <td> <img id="imgObj" alt="验证码" src="code.html" />
    <a href="#" onclick="changeImg()">换一张</a></td></tr>
        <tr>
          <td colspan="2" align="center" nowrap="nowrap"><label>
            <input type="submit"  value="注册" />
            <input type="reset"  value="重填" />
          </label></td>
        </tr>
  </table>
</form>
</body>
</html>