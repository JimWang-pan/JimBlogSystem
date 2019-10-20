<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Jim博客系统登录界面</title>
<link
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	/*
		判断用户是否输入用户名或密码
	 */
	function check(form) {
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		if (userName == "") {
			document.getElementById("userName").style.borderColor = "red";
			document.getElementById("userName").focus();
			return false;
		} else if (password == "") {
			document.getElementById("password").style.borderColor = "red";
			document.getElementById("password").focus();
			return false;
		}
	}
</script>
<style type="text/css">
body {
	padding: 0px;
	margin: 0px;
	background-color: #05A3D0;
	width: 1500px;
	height: 700px;
}

.header {
	padding-top: 200px;
	margin: 0 auto;
	width: 500px;
	height: 50px;
	text-align: center;
	letter-spacing: 5px;
	font-size: 25px;
	font-weight: bold;
	margin-bottom: 50px;
}

.content {
	margin: 0 auto;
	width: 350px;
	height: 250px;
	background-color: white;
	padding-top: 15px;
	padding-left: 19px;
}

.form-control {
	margin-top: 20px;
	width: 250px;
	margin-left: 30px;
	padding-left: 25px;
}

.form-control-feedback {
	left: 25px;
}

.checkbox {
	margin-top: 13px;
	margin-left: 50px;
	margin-bottom: 3px;
}

.checkbox span {
	margin-left: 108px;
}

.btn-default {
	width: 250px;
	margin-left: 30px;
	margin-top: 7px;
}

.error {
	padding-top: 10px;
	height: 13px;
	margin-left: 30px;
	font-size: 13px;
}

.register {
	margin-top: 5px;
	margin-left: 28px;
}
</style>
</head>
<body>
	<%
		String userName = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userNamePassword")) {
					userName = cookie.getValue().split("-")[0];
					password = cookie.getValue().split("-")[1];
					pageContext.setAttribute("userName", userName);
					pageContext.setAttribute("password", password);
				}
			}
		}
		String path = "index.jsp";
		session.setAttribute("path", path);
	%>

	<div class="header">
		<p>博客系统</p>
	</div>
	<div class="content">
		<form action="login" method="post" id="form"
			onsubmit="return check(this);">
			<p class="error">
				<font color="red">${error }</font>
			</p>
			<div class="form_group has-feedback">
				<span class="glyphicon glyphicon-user form-control-feedback"
					aria-hidden="true"></span> <input id="userName" type="text"
					class="form-control" name="userName" value="${userName }"
					placeholder="请输入用户名">
			</div>
			<div class="form_group has-feedback">
				<span class="glyphicon glyphicon-lock form-control-feedback"
					aria-hidden="true"></span> <input id="password" type="password"
					class="form-control" name="password" value="${password }"
					placeholder="请输入密码">
			</div>
			<div class="checkbox">
				<input type="checkbox" name="remember" value="remember-me" checked>记住密码
				<span><a href="forgetpassword.jsp">忘记密码?</a></span>
			</div>
			<button type="submit" class="btn btn-default">登录</button>
		</form>
		<div class="register">
			<a href="register.jsp"><font color="blue">没有账号？点击注册</font></a>
		</div>
	</div>
</body>
</html>