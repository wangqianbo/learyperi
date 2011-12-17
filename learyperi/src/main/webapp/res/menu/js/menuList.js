$(function() {
	var currentId = 1;
	$("#menutree").jstree({
		"plugins" : [ "themes", "html_data", "ui", "crrm" ]
	}).bind("select_node.jstree", function(event, data) {
		currentId = data.rslt.obj.children("a").attr("id");
	});
	$("#createTopMenu").click(function() {
		location.href = "menu!toEdit.action?menu.parent.code=root_menu";
	});
	$("#createMenu").click(
			function() {
				if (currentId == 1) {
					alert("请选择一个菜单项作为父菜单！");
					return;
				}
				var menuName = $("#" + currentId).text();
				if (confirm("子菜单将被创建在菜单[" + menuName + "]下，确认吗？")) {
					location.href = "menu!toEdit.action?menu.parent.id="
							+ currentId + "&menu.parent.name=" + menuName;
				}
			});
	$("#editMenu").click(function() {
		if (currentId == 1) {
			alert("请选择要编辑的菜单！");
			return;
		}
		location.href = "menu!toEdit.action?menu.id=" + currentId;
	});
	$("#showMenu").click(function() {
		if (currentId == 1) {
			alert("请选择要查看的菜单！");
			return;
		}
		$("#detail").load("menu!showMenu.action?menu.id=" + currentId);
		$("#detail").dialog("open");
	});
	$("#removeMenu").click(function() {
		if (currentId == 1) {
			alert("请选择要删除的菜单！");
			return;
		}
		var menuName = $("#" + currentId).text();
		if (confirm("菜单[" + menuName + "]及其子菜单将被删除，确认吗？")) {
			location.href = "menu!deleteMenu.action?menu.id=" + currentId;
		}
	});
	$("#detail").dialog({
		autoOpen : false,
		modal : true,
		show : "blind",
		hide : "explode",
		position : "top",
		width : "500",
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});
});