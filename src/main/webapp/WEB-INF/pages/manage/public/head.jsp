<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	Object objName = session.getAttribute("adminName");
	String adminName = null;
	if(objName != null) {
		adminName = (String)objName;
	}
	Object objId = session.getAttribute("adminId");
	String adminId = null;
	if(objId != null) {
		adminId = (String)objId;
	}
	Object objRole = session.getAttribute("adminRole");
	String adminRole = null;
	if(objRole != null) {
		adminRole = (String)objRole;
	}
%>
<c:if test="${empty adminName}">
	<script type="text/javascript">
			alert("请先登录！");
		 	window.location.href="login.html";
	</script>
</c:if>
<!--_header 作为公共模版分离出去-->
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top" style="background:#F11C24;">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml" >EasyShop</a>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<c:if test="${!empty adminName}">
					<li>${adminRole}</li>
					<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${adminName}<i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="logout.html" >退出</a></li>
						</ul>
					</li>
					</c:if>
				</ul>
			</nav>
</div>
</div>
</header>
<!--/_header 作为公共模版分离出去-->