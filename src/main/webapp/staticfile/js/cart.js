$(function() {
	
	$(".a_dec").click(function() {
		var $inp = $(this).next();
		var num = parseInt($inp.val());
		if(num > 1) {
			num = parseInt($inp.val()) - 1;
			$inp.val(num);
			var status = $(this).attr("data-status");
			var single = parseFloat($("#single_"+status).text());
			$("#total_"+status).text((single * num).toFixed(2));
			var sum = 0;
			var maxIndex = $(".total").length;
			$(".total").text(function(index, price) {
				if(index < maxIndex) {
					sum += parseFloat(price);
				}
			});
			$("#sum").text(sum);
			$.ajax({
				type: "post",
				url: "updateCart.html",
				data: {
					'userId': $inp.attr("data-userId"),
					'goodsId': $inp.attr("data-goodsId"),
					'num': num
				}
			});
		}
	});
	
	$(".a_add").click(function() {
		var $inp = $(this).prev();
		var num = parseInt($inp.val()) + 1;
		$inp.val(num);
		var status = $(this).attr("data-status");
		var single = parseFloat($("#single_"+status).text());
		$("#total_"+status).text((single * num).toFixed(2));
		var sum = 0;
		var maxIndex = $(".total").length;
		$(".total").text(function(index, price) {
			if(index < maxIndex) {
				sum += parseFloat(price);
			}
		});
		$("#sum").text(sum);
		$.ajax({
			type: "post",
			url: "updateCart.html",
			data: {
				'userId': $inp.attr("data-userId"),
				'goodsId': $inp.attr("data-goodsId"),
				'num': num
			}
		});
	});
	
	$(".a_del").click(function() {
		var userId = $(this).attr("data-userId");
		var goodsId = $(this).attr("data-goodsId");
		if(userId != null && userId != "" && goodsId != null && goodsId != "") {
			$('<form action="deleteCart.html" method="post" style="display:none" ><input type="hidden"  name="goodsId"  value="' + goodsId + '" /><input type="hidden"  name="userId"  value="' + userId + '" /></form>').appendTo('body').submit();
		} else {
			alert("移除失败！请重新登录再试。")
		}
	});
	
	$("#account").click(function() {
		$('<form action="ensureOrder.html" method="post" style="display:none" ><input type="hidden"  name="userId"  value="' + $(this).attr("data-userId") + '" /></form>').appendTo('body').submit();
	});

});