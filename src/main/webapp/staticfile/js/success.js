var fun = function() {
	$(function() {
		$('<form action="order.html" method="post" style="display:none" ><input type="hidden"  name="userId"  value="' + $("#userId").val() + '" /></form>').appendTo('body').submit();
	});	
}

$(function() {	
	setTimeout(
		"fun()",
		3000
	);
});