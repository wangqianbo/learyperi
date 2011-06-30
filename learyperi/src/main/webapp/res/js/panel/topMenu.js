$(function() {
	$("a[name='topmenu']").click(function() {
		var title = $(this).html();
		var link = $(this).attr("id");
		if (link != ".") {
			addTab(title, link);
		}
	});
})