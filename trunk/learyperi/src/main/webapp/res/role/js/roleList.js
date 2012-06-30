function test(){
	alert("test");
}
$(function() {
	$("#roleList").flexigrid({
		url: '/page/role/role!listRole.action',
		method: 'POST',
		dataType: 'json',
		colModel : [
			{display: '角色ID', name : 'id', width : 100, sortable : true, align: 'center'},
			{display: '角色代码', name : 'code', width : 200, sortable : true, align: 'left'},
			{display: '角色名称', name : 'name', width : 200, sortable : true, align: 'left'},
			{display: '角色描述', name : 'description', width : 400, sortable : false, align: 'left'}
			],
		buttons : [
			{name: '新增', bclass: 'add', onpress : test},
			{separator: true},
			{name: '修改', bclass: 'edit', onpress : test},
			{separator: true},
			{name: '删除', bclass: 'delete', onpress : test},
			{separator: true}
			],
		sortname: "code",
		sortorder: "asc",
		usepager: true,
		title: '角色列表',
		useRp: true,
		rp: 20,
		width: 'auto',
		height: 240
	});   
	var currentId = 0;
	$("#createRole").click(function() {
		location.href = "role!toNew.action";
	});
	$("#editRole").click(function() {
		if (currentId == 0) {
			alert("请选择要编辑的角色！");
			return;
		}
		location.href = "role!toEdit.action?role.id=" + currentId;
	});
	$("#showRole").click(function() {
		if (currentId == 0) {
			alert("请选择要查看的角色！");
			return;
		}
		$("#detail").load("role!showRole.action?role.id=" + currentId);
		$("#detail").dialog("open");
	});
	$("#removeRole").click(function() {
		if (currentId == 0) {
			alert("请选择要删除的角色！");
			return;
		}
		var roleName = $("#" + currentId).text();
		if (confirm("角色[" + roleName + "]将被删除，确认吗？")) {
			location.href = "role!deleteRole.action?role.id=" + currentId;
		}
	});
});