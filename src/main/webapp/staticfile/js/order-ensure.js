function GetDateNow() {
	var vNow = new Date();
	var sNow = "" + (Math.floor(Math.random()*9000) + 1000);
	sNow += String(vNow.getFullYear());
	sNow += String(vNow.getMonth() + 1);
	sNow += String(vNow.getDate());
	sNow += String(vNow.getHours());
	sNow += String(vNow.getMinutes());
	sNow += String(vNow.getSeconds());
	sNow += String(vNow.getMilliseconds());
	sNow = sNow.substring(0,12);
	return sNow;
}

$(function() {
	$("#button_zf").click(function() {
		var orderId = GetDateNow();
		var name = $("#inp_name").val();
		var phone = $("#inp_phone").val();
		var address = $("#city-picker3").val() + $("#inp_address").val();
		var userId = $("#inp_userId").val();
		var amount = $("#price_sum").text();
//		$('<form action="submitOrder.html" method="post" style="display:none" ><input type="hidden"  name="orderId"  value="' + orderId + '" /><input type="hidden"  name="name"  value="' + name + '" /><input type="hidden"  name="phone"  value="' + phone + '" /><input type="hidden"  name="address"  value="' + address + '" /><input type="hidden"  name="userId"  value="' + userId + '" /><input type="hidden"  name="amount"  value="' + amount + '" /></form>').appendTo('body').submit();
		$.ajax({
			type: "post",
			url: "submitOrder.html",
			data: {
				'orderId': orderId,
				'name': name,
				'phone': phone,
				'address': address,
				'userId': userId,
				'amount': amount
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.data != "null") {
					$(jsonObj.data).appendTo('body').submit();
				} else {
					alert("订单提交失败，带来的不便请您谅解，请重新下单！");
				}
			},
			error: function(errorMsg) {
				alert("订单提交错误，带来的不便请您谅解，请重新下单！");
			}
		});
	});
});

