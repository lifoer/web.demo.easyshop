<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>便捷商城</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/index.css">
</head>
<body>
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/index.js"></script>
	<jsp:include page="./public/head.jsp"></jsp:include>
	<jsp:include page="./public/nav.jsp"></jsp:include>
	<c:if test="${empty pageInfo.list}">
		<script type="text/javascript">
			alert("什么也没搜到！快去商城首页查看吧。");
			window.location.href="index.html";
		</script>
	</c:if>
	<div id="shop">
	<ul id="list">
	<c:forEach items="${pageInfo.list}" var="goods">
		<li class="goods">
			<p class="image">
			<a href="javascript:void(0)" target="_blank">
				<img class="picture a_goods" src="${goods.image}" data-value="${goods.id}">
				</a>
			</p>
			<p class="price">
				￥${goods.price}
			</p>
			<p class="name">
				<a href="javascript:void(0)" target="_blank" class="a_goods" data-value="${goods.id}">
					${goods.title}
				</a>
			</p>
			<p class="des">
				${goods.des}
			</p>
			<p class="comment">
				<c:if test="${empty goods.comments || goods.comments == ''}">
					0条评论
				</c:if>
				<c:if test="${!empty goods.comments && goods.comments != ''}">
					${goods.comments}条评论
				</c:if>
			</p>
			<p class="cart">
				<img src="/staticfile/img/cart.png" class="icon">
				<a href="javaScript:void(0)" data-goodsId="${goods.id}" data-userId="${userId}" class="a_cart">加入购物车</a>
			</p>
		</li>
	</c:forEach>
	</ul>
</div>
</body>
</html>