$(function() {
	$("#login_button").click(function() {
		$.ajax({
			type: "post",
			url: "checkLogin.html",
			data: {
				'username': $("#username").val(),
				'password': $("#password").val()
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					window.location.href = "index.html";	
				} else {
					alert("用户名或密码错误！");
				}
			},
			error: function(errorMsg) {
				alert("登录错误，请稍后再试！");
			}
		});
	});
});