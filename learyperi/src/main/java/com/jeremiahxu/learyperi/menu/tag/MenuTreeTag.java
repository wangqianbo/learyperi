package com.jeremiahxu.learyperi.menu.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jeremiahxu.learyperi.menu.pojo.Menu;
import com.jeremiahxu.learyperi.menu.service.MenuService;

/**
 * 展示菜单树的标签
 * 
 * @author Jeremiah Xu
 * 
 */
public class MenuTreeTag extends SimpleTagSupport {
	private static final long serialVersionUID = 1L;
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	private Menu menu;

	@Override
	public void doTag() throws JspException, IOException {
		if (menu == null) {// 如果标签的menu属性没有值则读取数据库取得菜单树
			ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			MenuService menuService = (MenuService) ctx.getBean("menuService");
			this.setMenu(menuService.findMenu(1));
		}
		// 生成第一层菜单
		List<Menu> menuList = this.getMenu().getChildren();
		StringBuffer html = new StringBuffer();
		html.append("<ul class=\"dropdown\">");
		for (Menu menuItem : menuList) {
			html.append("<li>");
			html.append("<a id=\".").append(menuItem.getUrl()).append("\" name=\"topmenu\" href=\"#\">")
					.append(menuItem.getName()).append("</a>");
			// 递归生成菜单
			html.append(this.writeHTML(menuItem));
			html.append("</li>");
		}
		html.append("</ul>");
		try {
			getJspContext().getOut().print(html);
		} catch (Exception ex) {
			log.error("menutree's tag error.", ex);
		}
	}

	/**
	 * 递归菜单生成HTML
	 * 
	 * @param menu
	 * @return
	 */
	private String writeHTML(Menu menu) {
		List<Menu> menuList = menu.getChildren();
		if (menuList.size() == 0) {
			return "";
		}
		StringBuffer html = new StringBuffer();
		html.append("<ul class=\"sub_menu\">");
		for (Menu menuItem : menuList) {
			html.append("<li>");
			html.append("<a id=\".").append(menuItem.getUrl()).append("\" name=\"topmenu\" href=\"#\">")
					.append(menuItem.getName()).append("</a>");
			html.append(this.writeHTML(menuItem));
			html.append("</li>");
		}
		html.append("</ul>");
		return html.toString();
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
