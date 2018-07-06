$(function() {
	$(".category").click(function() {
		$('<form action="query.html" method="post" style="display:none" ><input type="hidden"  name="category"  value="' + $(this).text() + '" /></form>').appendTo('body').submit();  
	});
	
	$(".a_goods").click(function() {
		$('<form action="goods.html" method="post" style="display:none" ><input type="hidden"  name="id"  value="' + $(this).attr("data-value") + '" /></form>').appendTo('body').submit();  
	});
	
	$(".a_cart").unbind("click").click(function() {
		var userId = $(this).attr("data-userId");
		if(userId == null || userId == "") {
			alert("请先登录！再加入购物车。");
			window.location.href = "login.html";	
		} else {
			$('<form action="cart.html" method="post" style="display:none" ><input type="hidden"  name="goodsId"  value="' + $(this).attr("data-goodsId") + '" /><input type="hidden"  name="userId"  value="' + userId + '" /><input type="hidden"  name="num"  value="1" /></form>').appendTo('body').submit();
		}
	});
});


