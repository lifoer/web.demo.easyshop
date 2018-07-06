$(function() {
	$(".pay").click(function() {
		var orderId = $(this).attr("data-order");
		var amount = $(this).attr("data-amount");
		$.ajax({
			type : "post",
			url : "payOrder.html",
			data : {
				'orderId' : orderId,
				'amount' : amount
			},
			success : function(result) {
				var jsonObj = JSON.parse(result);
				if (jsonObj.data != "null") {
					$(jsonObj.data).appendTo('body').submit();
				} else {
					alert("订单支付失败，带来的不便请您谅解，请重新支付！");
				}
			},
			error : function(errorMsg) {
				alert("订单支付错误，带来的不便请您谅解，请重新支付！");
			}
		});
	});
});