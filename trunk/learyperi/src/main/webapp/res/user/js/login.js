var ACT_SUBMIT = "/j_spring_security_check";

$(function() {
	if ($(window.parent.document).find("#topmenu").html()){
		window.parent.location.reload();
	}
	$("#login-submit").button().click(function() {
		var act = $("#loginForm").attr("action") + ACT_SUBMIT;
		$("#loginForm").attr("action", act);
		$("#loginForm").submit();
		return false;
	});
});