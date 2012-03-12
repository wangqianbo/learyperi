$(function() {
	$("#save-menu").button().click(function() {
		$("#menuEditForm").attr("action", "menu!updateMenu.action");
		$("#menuEditForm").submit();
		return false;
	});
	$("#cancle-menu").button().click(function() {
		location.href = "menu!listMenu.action";
		return false;
	});
});