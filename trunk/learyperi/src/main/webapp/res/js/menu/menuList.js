$(function() {
	$("#menutree").jstree({
		"plugins" : [ "themes", "html_data", "checkbox", "ui" ]
	}).bind("select_node.jstree", function(event, data) {
		alert(data.rslt.obj.attr("id"));
	}).delegate("a", "click", function(event, data) {
		event.preventDefault();
	});
})