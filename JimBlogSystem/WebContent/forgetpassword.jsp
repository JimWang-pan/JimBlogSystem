<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
function checkMail(form) {
	var mail = document.getElementById("mail").value;
	var form = document.getElementById("form");
	if(mail==""){
		document.getElementById("mail").style.borderColor="red";
		document.getElementById("mail").focus();
		return false;
	}else{
		form.action="sendVerifyCode";
		return true;
	}
}
function checkAll(form){
	var mail = document.getElementById("mail").value;
	var verifyCode = document.getElementById("verifyCode").value;
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("confirmPassword").value;
	var form = document.getElementById("form");
	if(mail==""){
		document.getElementById("mail").style.borderColor="red";
		document.getElementById("mail").focus();
		return false;
	}else if(verifyCode==""){
		document.getElementById("verifyCode").style.borderColor="red";
		document.getElementById("verifyCode").focus();
		return false;
	}else if(password==""){
		document.getElementById("password").style.borderColor="red";
		document.getElementById("password").focus();
		return false;
	}else if(confirmPassword==""){
		document.getElementById("confirmPassword").style.borderColor="red";
		document.getElementById("confirmPassword").focus();
		return false;
	}else{
		if(password==confirmPassword){
			form.action="retrievePwd";
			return true;
		}else{
			document.getElementById("password").style.borderColor="red";
			document.getElementById("confirmPassword").style.borderColor="red";
			alert("两个密码不匹配");
			return false;
		}
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
	height: 370px;
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
.verifyCode{
	width: 130px;
	display: inline-block;
}
.verifyCodeBtn{
	display: inline-block;
	margin-left: 20px;
}
</style>
</head>
<body>
<%
	String path = "forgetpassword.jsp";
	session.setAttribute("path", path);
%>
	<div class="header">
		<p>博客系统</p>
	</div>
	<div class="content">
		<form method="post" id="form">
			<p class="error">
				<font color="red">${error }</font>
			</p>
			<div class="form_group has-feedback">
					<span class="glyphicon glyphicon-envelope form-control-feedback"
						aria-hidden="true"></span> <input id="mail" type="text"
						class="form-control" name="mail" value="${mail }"
						placeholder="请输入邮箱">
			</div>
			<div class="form_group">
				<input type="text"
					class="form-control verifyCode" id="verifyCode" name="verifyCode" value="${verifyCode }"
					placeholder="请输入验证码">
				<button class="btn btn-primary verifyCodeBtn" onclick="return checkMail(this);">获取验证码</button>
			</div>
				<div class="form_group has-feedback">
					<span class="glyphicon glyphicon-lock form-control-feedback"
						aria-hidden="true"></span> <input id="password"type="password"
						class="form-control" name="password" value="${password }"
						placeholder="请输入新密码">
				</div>
			<div class="form_group has-feedback">
				<span class="glyphicon glyphicon-lock form-control-feedback"
					aria-hidden="true"></span> <input id="confirmPassword" type="password"
					class="form-control" name="confirmPassword" value="${password }"
					placeholder="请确认密码">
			</div>
			<button type="submit" class="btn btn-default" onclick="return checkAll(this);">确认修改</button>
		</form>
	</div>
</body>
</html>