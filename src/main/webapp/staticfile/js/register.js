$(function() {
	$("#username").blur(function() {
		var username = $("#username").val();
		if(username.length > 0) {
			$.ajax({
				type: "post",
				url: "checkRegister.html",
				data: {
					'username': username
				},
				success: function(result) {
					var jsonObj = JSON.parse(result);
					if( jsonObj.state == 1) {
						$("#td_un").attr("class","red");
						$("#td_un").text("抱歉，用户名已被使用！");
					} else {
						$("#td_un").attr("class","green");
						$("#td_un").text("恭喜，用户名可以使用！");
					}
				},
				error: function(errorMsg) {
					alert("注册错误，请稍后再试！");
				}
			});			
		} else {
			$("#td_un").attr("class","");
			$("#td_un").text("");
		}
	});
	
	$("#register_button").click(function() {
		$.ajax({
			type: "post",
			url: "insertUser.html",
			data: {
				'username': $("#username").val(),
				'password': $("#password").val(),
				'phone': $("#phone").val(),
				
			},
			success: function(result) {
				var jsonObj = JSON.parse(result);
				if( jsonObj.state == 1) {
					alert("注册成功！将跳到登录页面。");
					window.location.href = "login.html";	
				} else {
					alert("注册失败，请检查信息！");
				}
			},
			error: function(errorMsg) {
				alert("注册错误，请稍后再试！");
			}
		});
	});
	
});
