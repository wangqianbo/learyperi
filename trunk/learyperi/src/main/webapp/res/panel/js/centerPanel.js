var tab_counter = 1;
var tab_closed_counter = 0;
function addTab(title, url) {
	$tabs.tabs("add", "#tabs-" + tab_counter, title);
	$iframe = $("#tabs-" + tab_counter).find("iframe");
	$iframe.attr("src", url);
	$iframe.load(function(){
		$(this).height($(this).contents().find("body").height());
	});
	$tabs.tabs("option", "selected", tab_counter - tab_closed_counter);
	tab_counter++;
}
$(function() {
	$tabs = $("#tabs")
			.tabs(
					{
						tabTemplate : "<li><a href='#{href}'>#{label}</a><span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
						add : function(event, ui) {
							var tab_content = "<iframe height='100%' width='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='no'></iframe>";
							$(ui.panel).append(tab_content);
						}
					});
	$("#tabs span.ui-icon-close").live("click", function() {
		var index = $("li", $tabs).index($(this).parent());
		$tabs.tabs("remove", index);
		tab_closed_counter++;
	});
});
