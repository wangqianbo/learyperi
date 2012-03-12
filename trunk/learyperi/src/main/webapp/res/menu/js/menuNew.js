var ACT_SUBMIT = "menu!createMenu.action";
var ACT_CANCLE = "menu!listMenu.action";

$(function() {
	$("#save-menu").button().click(function() {
		$("#menuNewForm").attr("action", ACT_SUBMIT);
		$("#menuNewForm").submit();
		return false;
	});
	$("#cancle-menu").button().click(function() {
		location.href = "menu!listMenu.action";
		return false;
	});
});