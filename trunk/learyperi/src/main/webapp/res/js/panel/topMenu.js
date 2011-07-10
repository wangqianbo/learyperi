$(function() {
	$("#topmenu").find("a").click(function() {
		var title = $(this).html();
		var link = $(this).attr("name");
		if (link != ".") {
			addTab(title, link);
		}
	});
})