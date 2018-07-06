<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>完成订单</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/success.css">
</head>
<body>
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/success.js"></script>
	<div id="logo_order">
		<div id="logo">
			<img src="/staticfile/img/logo.png">
		</div>
		<div id="order_ensure">
			<span>我的购物车</span>
			<span>填写订单</span>
			<span id="current">完成订单</span>
		</div>
	</div>
	<div id="divide">
	</div>
	<div id="content">
		<table id="table_info">
			<tr>
				<td><img src="/staticfile/img/success.png"></td>
			</tr>
			<tr>
				<td><span class="title_info">提交成功</span></td>
			</tr>
			<tr >
				<td class="explain">
					您的订单<span class="red">(${orderId})</span>已提交成功。
					<input type="hidden" value="${userId}" id="userId"/>
				</td>
			</tr>
			<tr>
				<td class="explain_td">
					<span>3s后自动跳转到订单页面。</span>
				</td>
			</tr>
		</table>
		
		
		
	</div>
</body>
</html>