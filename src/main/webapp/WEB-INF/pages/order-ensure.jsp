<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>填写订单</title>
	<link rel="stylesheet" type="text/css" href="/staticfile/css/order-ensure.css">
	<link rel="stylesheet" type="text/css" href="/staticfile/city/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/staticfile/city/css/city-picker.css">
	<link rel="stylesheet" type="text/css" href="/staticfile/city/css/main.css">
</head>
<body>
	<script src="/staticfile/city/js/jquery.js"></script>
	<script src="/staticfile/city/js/bootstrap.js"></script>
	<script src="/staticfile/city/js/city-picker.data.js"></script>
	<script src="/staticfile/city/js/city-picker.js"></script>
	<script src="/staticfile/city/js/main.js"></script>
	<script type="text/javascript" src="/staticfile/js/order-ensure.js"></script>
	<div id="logo_order">
		<div id="logo">
			<img src="/staticfile/img/logo.png">
		</div>
		<div id="order_ensure">
			<span >我的购物车</span>
			<span id="current">填写订单</span>
			<span>完成订单</span>
		</div>
	</div>
	<div id="divide">
	</div>
	<div id="order_info">
		<div id="address">
			<span class="title_info">收货人信息</span>
			<table>
					<tr>
						<td>收货人：</td>
						<td>
							<input type="type" name="" class="order_input" id="inp_name">
						</td>
					</tr>
					<tr>
						<td>手机号码：</td>
						<td>
							<input type="type" name="" class="order_input" id="inp_phone">
						</td>
					</tr>
					<tr>
						<td>所在地区：</td>
						<td>
							<div class="docs-methods">
							<form class="form-inline">
								<div id="distpicker">
									<div class="form-group">
										<div style="position: relative;">
											<input id="city-picker3" class="form-control" readonly type="text" value="山西省/晋中市/榆次区" data-toggle="city-picker">
										</div>
									</div>
									<div class="form-group">
										<button class="btn btn-warning" id="reset" type="button">重置</button>
										<button class="btn btn-danger" id="destroy" type="button">确定</button>
									</div>
								</div>
							</form>
							</div>	
						</td>
					</tr>
					<tr>
						<td>详细地址：</td>
						<td>
							<input type="type" name="" class="order_input" id="inp_address">
							<input type="hidden" value="" id="inp_orderId">
							<input type="hidden" value="${userId}" id="inp_userId">
						</td>
					</tr>
				</table>
		</div>	
		<div id="goods_list">
			<span class="title_info">物品清单</span>
			<div id="cart_list">
				<table>
					<tbody>
						<tr>
							<td>&nbsp;</td>
							<td>商品名称</td>
							<td>单价</td>
							<td>数量</td>
							<td>总价</td>
						</tr>
						<c:set value="0" var="sum"></c:set>
						<c:set value="0" var="count"></c:set>
						<c:forEach items="${cartList}" var="cart">
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
								<span>¥${cart.price}</span>
							</td>
							<td>
								<span>${cart.num}</span>
							</td>
							<td class="td_price">
								<span class="red">¥<fmt:formatNumber type="number" value="${cart.price * cart.num}" maxFractionDigits="2" /></span>
							</td>
							<c:set var="sum">
								<fmt:formatNumber type="number" value="${sum + cart.price * cart.num}" maxFractionDigits="2" />
							</c:set>
							<c:set var="count" value="${count + cart.num}"></c:set>		
						</tr>						
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>	
		<div id="payment">
			<span class="title_info">支付方式</span>
			<div>
				<a href="javascript:void(0)" id="pay_online" class="current">在线支付</a>
				<span >&emsp;在线支付包邮</span>
			</div>
		</div>
		<div id="onsure">
			<span class="title_info">确认订单</span>
			<div id="onsure_info">
				<!-- <p class="p_info">
					<span class="big">寄送至：</span>
					<span>中国 山西省 晋中市 榆次区 大学街319号太原师范学院</span>
					<span>张三 18512345678</span>
				</p> -->
				<p class="p_info">
					共<span class="red">${count}</span>件商品&emsp;
					<span class="big">应付金额：</span>
					<span class="red" id="price_sum">${sum}</span>
					(含运费<span class="red">0.00</span>元)
				</p>
				<p class="p_zf">
					<a href="javascript:void(0)" id="button_zf">去支付</a>
				</p>
			</div>
		</div>		
	</div>
</body>
</html>