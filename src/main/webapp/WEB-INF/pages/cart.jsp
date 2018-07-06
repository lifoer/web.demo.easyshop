<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>我的购物车</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/cart.css">
</head>
<body>
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/cart.js"></script>
	<c:if test="${empty cartList}">
		<script type="text/javascript">
			alert("购物车都空了！快去商城购买吧。");
			window.location.href="index.html";
		</script>
	</c:if>
	<div id="logo_cart">
		<div id="logo">
			<a href="index.html"><img src="/staticfile/img/logo.png"></a>
		</div>
		<div id="cart">
			<span id="current">我的购物车</span>
			<span>填写订单</span>
			<span>完成订单</span>
		</div>
	</div>
	<div id="divide">
	</div>
	<div id="cart_list">
		<table>
			<tbody>
				<c:set value="0" var="sum"></c:set>
				<c:forEach items="${cartList}" var="cart" varStatus="status">
				<tr>
					<td class="td_img">
						<a href="">
							<img src="${cart.image}">
						</a>
					</td>
					<td class="td_title">
						<div class="title">
							<a href="">
							${cart.title}
							</a>
						</div>
					</td>
					<td class="td_price">
						<span id="single_${status.count}">${cart.price}</span>
					</td>
					<td>
						<span class="amount">
							<a href="javascript:void(0)" class="a_dec" data-status="${status.count}">-</a>
							<input type="type" name="num" value="${cart.num}" data-goodsId="${cart.goodsId}" data-userId="${cart.userId}" readonly="readonly">
							<a href="javascript:void(0)" class="a_add" data-status="${status.count}">+</a>
						</span>
					</td>
					<td class="td_price">
						<span class="red">¥</span>
						<span class="red total" id="total_${status.count}"><fmt:formatNumber type="number" value="${cart.price * cart.num}" maxFractionDigits="2"/></span>
					</td>
					<td class="td_del">
						<a href="javascript:void(0)" data-goodsId="${cart.goodsId}" data-userId="${cart.userId}" class="a_del">移除</a>
					</td>
				</tr>
				<c:set var="sum">
					<fmt:formatNumber type="number" value="${sum + cart.price * cart.num}" maxFractionDigits="2" />
				</c:set>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr id="tr_fin">
					<td colspan="4">&nbsp;</td>
					<td>
						<span id="word">合计：</span>
						<span class="red" id="sum">${sum}</span>
					</td>
					<td>
						<a href="javascript:void(0)" id="account" data-userId="${userId}">
							结算
						</a>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>