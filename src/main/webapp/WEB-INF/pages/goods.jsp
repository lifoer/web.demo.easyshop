<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>商品详情</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/product.css">
</head>
<body>
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/goods.js"></script>
	<jsp:include page="./public/head.jsp"></jsp:include>
	<jsp:include page="./public/nav.jsp"></jsp:include>
	
	<div id="product_main">
		<div id="product_img">
			<img src="${goods.image}">
		</div>
		<div id="product_info">
			<div id="product_title">
				<h1>${goods.title}</h1>
				<h2>${goods.des}</h2>
			</div>
			<div id="product_comment">
				<img src="/staticfile/img/star.png" id="comment_img">${goods.comments}条评论
			</div>
			<div id="product_price">
				￥<span>${goods.price}</span>
			</div>
			<div id="product_button">
				<div id="amount">
					<a href="javascript:void(0)" id="a_dec">-</a>
					<input type="type" name="num" value="1" id="num" data-goodsId="${goods.id}" data-userId="${userId}" readonly="readonly">
					<a href="javascript:void(0)" id="a_add">+</a>
				</div>
				<div id="cart"> 
					<a href="javascript:void(0)" id="a_cart">加入购物车</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>