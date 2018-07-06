<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<link rel="stylesheet" type="text/css" href="/staticfile/css/index.css">
	<script type="text/javascript" src="/staticfile/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/staticfile/js/index.js"></script>
	<script type="text/javascript" src="/staticfile/js/nav.js"></script>

	<div id="logo_se">
		<div id="logo">
			<a href="index.html"><img src="/staticfile/img/logo.png"></a>
		</div>
		<div id="se">
		<nobr>
			<form action="keyword.html" method="post" id="thisform">
				<input type="text" name="keyword" value="" id="se_in">
				<input type="submit"  value="搜索" id="se_bu">
			</form>
		</nobr>
		</div>
				<div id="cart_bu">
			<a href="javascript:void(0)" data-userId="${userId}" id="nav_cart">购物车</a>
		</div>
		<div id="order_bu">
			<a href="javascript:void(0)"  data-userId="${userId}" id="nav_order">我的订单</a>
		</div>
	</div>
	<div id="nav">
		<ul>
			<li id="all"><a href="index.html">全部商品</a></li>
			<li><a href="javascript:void(0)" class="category">服装</a></li>
			<li><a href="javascript:void(0)" class="category">数码</a></li>
			<li><a href="javascript:void(0)" class="category">图书</a></li>
			<li><a href="javascript:void(0)" class="category">电器</a></li>
			<li><a href="javascript:void(0)" class="category">饰品</a></li>
			<li><a href="javascript:void(0)" class="category">食品</a></li>
			<li><a href="javascript:void(0)" class="category">家居</a></li>
		</ul>
	</div>