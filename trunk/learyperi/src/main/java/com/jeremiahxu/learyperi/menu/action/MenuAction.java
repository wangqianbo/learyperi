package com.jeremiahxu.learyperi.menu.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.config.Action;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletRedirectResult;
import org.apache.struts2.views.freemarker.FreemarkerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jeremiahxu.learyperi.menu.pojo.Menu;
import com.jeremiahxu.learyperi.menu.service.MenuService;

/**
 * menu action
 * 
 * @author Jeremiah Xu
 * 
 */
@Controller
@ParentPackage(value = "struts-learyperi")
@Action(namespace = "/page/menu", name = "menu")
@Results({ @Result(name = "list", type = FreemarkerResult.class, value = "/page/menu/menuList.xhtml"),
		@Result(name = "create", type = ServletRedirectResult.class, value = "menu!listMenu.action"),
		@Result(name = "update", type = ServletRedirectResult.class, value = "menu!listMenu.action"),
		@Result(name = "delete", type = ServletRedirectResult.class, value = "menu!listMenu.action"),
		@Result(name = "show", type = FreemarkerResult.class, value = "/page/menu/menuDetail.xhtml"),
		@Result(name = "toEdit", type = FreemarkerResult.class, value = "/page/menu/menuEdit.xhtml") })
@Scope("request")
public class MenuAction {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MenuService menuService;
	private List<Menu> menus;
	private Menu menu;

	/**
	 * 列出所有菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listMenu() throws Exception {
		this.setMenus(this.getMenuService().findAllMenu());
		log.info("list menu.");
		return "list";
	}

	/**
	 * 创建菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createMenu() throws Exception {
		this.getMenuService().createMenu(this.getMenu());
		log.info("create menu.");
		return "create";
	}

	/**
	 * 修改菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateMenu() throws Exception {
		this.getMenuService().updateMenu(this.getMenu());
		log.info("update menu.");
		return "update";
	}

	/**
	 * 删除菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteMenu() throws Exception {
		this.getMenuService().deleteMenu(this.getMenu());
		log.info("delete menu.");
		return "delete";
	}

	/**
	 * 显示菜单明细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showMenu() throws Exception {
		this.setMenu(this.getMenuService().findMenu(this.getMenu().getId()));
		log.info("show menu.");
		return "show";
	}

	/**
	 * 去菜单编辑页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toEdit() throws Exception {
		this.setMenu(this.getMenuService().findMenu(this.getMenu().getId()));
		log.info("to edit menu.");
		return "toEdit";
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
