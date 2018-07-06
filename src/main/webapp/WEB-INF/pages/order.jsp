<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>我的订单</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/order.css">
</head>
<body>
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/order.js"></script>
	<input type="hidden" value="${username}" id="inp_name"/>
	<c:if test="${empty orderList}">
		<script type="text/javascript">
			alert("还没有订单哦！快去商城购买吧。");
			window.location.href="index.html";
		</script>
	</c:if>
	
	<jsp:include page="./public/head.jsp"></jsp:include>
	<jsp:include page="./public/nav.jsp"></jsp:include>
	
	<div id="order_menu">
		<ul id="order_list">
			<li id="order_all">
				<a href="javascript:void(0)">全部订单</a>
			</li>
			<li class="order_state">
				<a href="javascript:void(0)">待付款</a>
			</li>
			<li class="order_state">
				<a href="javascript:void(0)">待发货</a>
			</li>
		</ul>
	</div>
	<div id="order_divide">
	</div>
	<c:forEach items="${orderList}" var="order">
		<c:set value="${order.orderId}" var="flag1"></c:set>
		<c:if test="${flag1 != flag2}">
			<div class="order_head">
				<span>订单号：${order.orderId}</span>
				<span >${order.calendar}</span>
				<span>
					订单金额：￥${order.amount}
				</span>
				<c:if test="${order.state == 0}">
					<span class="red">未支付</span>
					<span class="pay_span">
						<a href="javascript:void(0)" class="pay" style="color: #fff;" data-order="${order.orderId}" data-amount="${order.amount}">立即付款</a>
					</span>
				</c:if>
				<c:if test="${order.state == 1}">
					<span class="green">已支付</span>
				</c:if>
			</div>
		</c:if>
		<c:set value="${order.orderId}" var="flag2"></c:set>
		<div id="cart_list">
		<table>
			<tbody>
				<tr>
					<td class="td_img">
						<a href="">
							<img src="${order.image}">
						</a>
					</td>
					<td class="td_title">
						<div class="title">
							<a href="">
							${order.title}
							</a>
						</div>
					</td>
					<td class="td_price">
						<span>¥${order.price}</span>
					</td>
					<td>
						<span>${order.num}</span>
					</td>
					<td class="td_price">
						<span class="red">¥<fmt:formatNumber type="number" value="${order.price * order.num}" maxFractionDigits="2" /></span>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
	</c:forEach>	
</body>
</html>