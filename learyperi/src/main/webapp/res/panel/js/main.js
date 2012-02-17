$(function() {
	var layout = $('body').layout({
		defaults : {
			applyDefaultStyles : true,
			fxSpeed : "slow",
			spacing_open : 4,
			spacing_closed : 7,
			slideTrigger_open : "mouseover",
			onopen : function() {
				adjustSize();
			},
			onclose : function() {
				adjustSize();
			}
		},
		north : {
			resizable : false,
			closable : false,
			spacing_open : 0,
			size : 100
		},
		south : {
			resizable : false,
			closable : false,
			spacing_open : 0
		},
		west : {
			initClosed : true,
			size : 250
		},
		east : {
			size : 300
		}
	});
	adjustSize();
	// 设置菜单位置
	var top = $("div.ui-layout-center").offset().top;
	$("#topmenu").css({
		"position" : "absolute",
		"z-index" : 999,
		"top" : top,
		"left" : 20
	});
	$("#tabs").css({
		"top" : top - 90
	});
	// 离开或者刷新页面时提示
	$(window).bind("beforeunload", function() {
		return "您将离开本页面或者页面将被重置，所有打开的页面都将被关闭，确认离开或者刷新页面吗？";
	});
	// 自动调整中央显示区域的大小
	function adjustSize() {
		$("#center").height($("#center").parent().height() - 20);
		$("#center").width($("#center").parent().width() - 20);
	}
});
