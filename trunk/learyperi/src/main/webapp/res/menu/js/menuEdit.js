$(function() {
	$("#save-menu").button().click(function() {
		if ($("#menuId").val() == "" || $("#menuId").val() == "0") {
			$("#menuEditForm").attr("action", "menu!createMenu.action");
		} else {
			$("#menuEditForm").attr("action", "menu!updateMenu.action");
		}
		$("#menuEditForm").submit();
		return false;
	});
	$("#cancle-menu").button().click(function() {
		location.href = "menu!listMenu.action";
		return false;
	});
})