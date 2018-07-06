$(function() {
	$("#a_add").click(function() {
		$("#num").val(parseInt($("#num").val()) + 1);
	});
	
	$("#a_dec").click(function() {
		var num = parseInt($("#num").val());
		if(num > 1) {
			$("#num").val( num - 1);
		}
	});
	
	$("#a_cart").click(function() {
		var userId = $("#num").attr("data-userId");
		if(userId == null || userId == "") {
			alert("请先登录！再加入购物车。");
			window.location.href = "login.html";	
		} else {
			$('<form action="cart.html" method="post" style="display:none" ><input type="hidden"  name="goodsId"  value="' + $("#num").attr("data-goodsId") + '" /><input type="hidden"  name="userId"  value="' + $("#num").attr("data-userId") + '" /><input type="hidden"  name="num"  value="' + $("#num").val() + '" /></form>').appendTo('body').submit();
		}
	});
});