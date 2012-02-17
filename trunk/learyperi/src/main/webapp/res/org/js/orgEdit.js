$(function() {
	$("#save-org").button().click(function() {
		if ($("#orgId").val() == "" || $("#orgId").val() == "0") {
			$("#orgEditForm").attr("action", "org!createOrg.action");
		} else {
			$("#orgEditForm").attr("action", "org!updateOrg.action");
		}
		$("#orgEditForm").submit();
		return false;
	});
	$("#cancle-org").button().click(function() {
		location.href = "org!listOrg.action";
		return false;
	});
});