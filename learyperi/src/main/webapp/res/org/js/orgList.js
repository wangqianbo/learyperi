$(function() {
	var currentId = 0;
	$("#orgtree").jstree({
		"plugins" : [ "themes", "html_data", "ui", "crrm" ]
	}).bind("select_node.jstree", function(event, data) {
		currentId = data.rslt.obj.children("a").attr("id");
	});
	$("#createTopOrg").click(function() {
		location.href = "org!toEdit.action?org.parent.code=root_org";
	});
	$("#createOrg").click(
			function() {
				if (currentId == 0) {
					alert("请选择一个机构作为父机构！");
					return;
				}
				var orgName = $("#" + currentId).text();
				if (confirm("子机构将被创建在机构[" + orgName + "]下，确认吗？")) {
					location.href = "org!toEdit.action?org.parent.id="
							+ currentId + "&org.parent.name=" + orgName;
				}
			});
	$("#editOrg").click(function() {
		if (currentId == 0) {
			alert("请选择要编辑的机构！");
			return;
		}
		location.href = "org!toEdit.action?org.id=" + currentId;
	});
	$("#showOrg").click(function() {
		if (currentId == 0) {
			alert("请选择要查看的机构！");
			return;
		}
		$("#detail").load("org!showOrg.action?org.id=" + currentId);
		$("#detail").dialog("open");
	});
	$("#removeOrg").click(function() {
		if (currentId == 0) {
			alert("请选择要删除的机构！");
			return;
		}
		var orgName = $("#" + currentId).text();
		if (confirm("机构[" + orgName + "]及其子机构将被删除，确认吗？")) {
			location.href = "org!deleteOrg.action?org.id=" + currentId;
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