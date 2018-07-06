<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/register.css">
</head>
<body>
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/register.js"></script>
	<div id="logo">
		<img src="/staticfile/img/logo.png">
	</div>
	<div id= "login">
		<nobr>
				<table>
					<tr>
						<td>用户名:</td>
						<td>
							<input type="type" name="username" class="login_input" id="username">
						<td>
						<td id="td_un"></td>
					</tr>	
					<tr>
						<td>密码：</td>
						<td>
							<input type="password" name="password" class="login_input" id="password">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>手机号：</td>
						<td>
							<input type="type" name="phone" class="login_input" id="phome">
						</td>
						<td></td>
					</tr>
					<tr>
						<td>验证码：</td>
						<td class="td_center">
							<input type="type" name="yzm" id="login_yz">
							<img src="/staticfile/img/yzm.png" id="yzm">
						</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" class="td_center">
							<input type="button" id="register_button" value="注册">
						</td>
					</tr>
					<tr>
						<td class="td_left" colspan="2">
							<a href="login.html">已有用户名</a>
						</td>
					</tr>
				</table>	
		 </nobr> 
	</div>
</body>
</html>