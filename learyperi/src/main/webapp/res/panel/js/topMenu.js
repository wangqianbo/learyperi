$(function() {
	$("#topmenu").find("a").click(function() {
		var title = $(this).html();
		var link = $(this).attr("name");
		var target = $(this).attr("target");
		var href = $(this).attr("href");
		if (link) {
			if (target) {// 弹出窗体
				window.open(href);
			} else {// 在主窗体打开子页面
				addTab(title, link);
			}
		} else {
			if (href) {// 整个页面刷新
				window.location.href = href;
			}
		}
	});
});