<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/login.css">
</head>
<body>
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/login.js"></script>
	<div id="logo">
		<img src="/staticfile/img/logo.png">
	</div>
	<div id= "login">
		用户名:<input type="type" name="username" class="login_input" id="username">
		密码：<input type="password" name="password" class="login_input" id="password">
		<input type="button" id="login_button" value="登录">
		<p>
			<a href="register.html" id="register">立即注册</a>
		</p>
	</div>
</body>
</html>