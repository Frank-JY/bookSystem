function limit() {
		alert("!!!");
		var regUserName = /^(\w){4,15}$/;
		var regPassword = /^(\w){6,12}$/;
		var userName = document.getElementsByName("userName")[0].value;
		var password = document.getElementsByName("password")[0].value;
		if(!regUserName.test(userName)) {
			alert("用户名的长度应为4~15!");
			return false;
		}
		if(!regPassword.test(password)) {
			alert("密码的长度应为6~12!");
			return false;
		}
	}