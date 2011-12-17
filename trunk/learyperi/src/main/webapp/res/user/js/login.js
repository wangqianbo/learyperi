var ACT_SUBMIT = "/j_spring_security_check";

$(function() {
	$("#login-submit").button().click(function() {
		var act = $("#loginForm").attr("action") + ACT_SUBMIT;
		$("#loginForm").attr("action", act);
		$("#loginForm").submit();
	});
});