var ACT_SUBMIT = "org!updateOrg.action";
var ACT_CANCLE = "org!listOrg.action";
$(function() {
	$("#save-org").button().click(function() {
		$("#orgEditForm").attr("action", ACT_SUBMIT);
		$("#orgEditForm").submit();
		return false;
	});
	$("#cancle-org").button().click(function() {
		location.href = ACT_CANCLE;
		return false;
	});
});