$(function() {
	$("#nav_cart").click(function() {
		var userId = $(this).attr("data-userId");
		if(userId != null && userId != "") {
			$('<form action="queryCart.html" method="post" style="display:none" ><input type="hidden"  name="userId"  value="' + $(this).attr("data-userId") + '" /></form>').appendTo('body').submit();
		} else {
			alert("请先登录！再查看购物车。");
			window.location.href = "login.html";
		}
	});
	
	$("#nav_order").click(function() {
		var userId = $(this).attr("data-userId");
		if(userId != null && userId != "") {
			$('<form action="order.html" method="post" style="display:none" ><input type="hidden"  name="userId"  value="' + userId + '" /></form>').appendTo('body').submit();
		} else {
			alert("请先登录！再查看订单。");
			window.location.href = "login.html";
		}
	});
});