var ACT_SUBMIT = "org!createOrg.action";
var ACT_CANCLE = "org!listOrg.action";

$(function() {
	$("#save-org").button().click(function() {
		$("#orgNewForm").attr("action", ACT_SUBMIT);
		$("#orgNewForm").submit();
		return false;
	});
	$("#cancle-org").button().click(function() {
		$("#orgNewForm").attr("action", ACT_CANCLE);
		$("#orgNewForm").submit();
	});
});