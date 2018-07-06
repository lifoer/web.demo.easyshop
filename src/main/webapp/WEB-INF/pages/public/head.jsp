<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<link rel="stylesheet" type="text/css" href="/staticfile/css/index.css">
	<%
		Object objName = session.getAttribute("username");
		String username = null;
		if(objName != null) {
			username = (String)objName;
		}
		 Object objId = session.getAttribute("userId");
		String userId = null;
		if(objId != null) {
			userId = (String)objId;
		}
	%>
	<div id="head">
		<span>欢迎光临easyshop，</span>
		<c:if test="${empty username}">
			请<a href="login.html">登录</a>
			<span>成为会员</span>&emsp;
			<a href="register.html">免费注册</a>
		</c:if>
		<c:if test="${!empty username}">
			<span class="red">${username}&emsp;</span>|&emsp;<a href="logout.html">注销</a>
		</c:if>		
	</div>